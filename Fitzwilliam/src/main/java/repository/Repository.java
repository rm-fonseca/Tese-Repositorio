package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import plataforma.modelointerno.ExtraProperty;
import plataforma.modelointerno.LanguageString;
import plataforma.modelointerno.Resource;
import plataforma.modelointerno.Result;
import plataforma.modelointerno.Result.CreatedIn;

public class Repository implements RepositoryAbstract {

	private static final String QUERY_BY_TERM = "http://data.fitzmuseum.cam.ac.uk/api/?query=";
	private static final String SOURCE_PAGE_BASE_URL = "http://data.fitzmuseum.cam.ac.uk/id/object/";
	private static final String SOURCE_DATA_BASE_URL = "http://data.fitzmuseum.cam.ac.uk/api/?q=_id:";
	private String repName = null;

	private static List<String> valuesDateEarly;
	private static List<String> valuesDateLate;

	@Override
	public List<Result> SearchByBox(int latitudeFrom, int latitudeTo, int longitudeFrom, int longitudeTo,
			boolean ignoreExtraProperties) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Result> SearchByTerm(String term, boolean ignoreExtraProperties) throws IOException {
		final RestTemplate restTemplate = new RestTemplate();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		final HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);
		// Request
		String response = restTemplate.getForObject(QUERY_BY_TERM + term, String.class);

		// Convert to Results
		List<Result> resultsList = new ArrayList<Result>();
		JSONObject responseJson = new JSONObject(response);
		JSONArray items = responseJson.getJSONArray("results");

		JSONObject item;
		JSONArray keyValues;

		for (Object itemObject : items) {

			valuesDateEarly = null;
			valuesDateLate = null;

			item = (JSONObject) itemObject;
			Result result = new Result();
			result.getSourcePage().add(SOURCE_PAGE_BASE_URL + item.getString("priref"));
			result.getSourceData().add(SOURCE_DATA_BASE_URL + item.getString("priref"));
			result.getSourceRepositorie().add(getRepositoryName());

			Iterator<String> keys = item.keys();

			String key;

			while (keys.hasNext()) {
				key = keys.next();

				List<String> values = new ArrayList<String>();
				keyValues = item.optJSONArray(key);

				if (keyValues != null)
					for (int j = 0; j < keyValues.length(); j++)
						values.add(keyValues.get(j).toString());
				else
					values.add(item.get(key).toString());

				connectData(key, values, result, ignoreExtraProperties);

			}

			resultsList.add(result);

		}

		return resultsList;

	}

	private static void connectData(String key, List<String> values, Result result, boolean ignoreExtraProperties) {

		String newKey;
		JSONArray keyValues;
		LanguageString lang;
		switch (key) {

		case "images":

			for (String item : values) {
				JSONObject json = new JSONObject(item);
				Iterator<String> keys = json.keys();

				while (keys.hasNext()) {
					newKey = keys.next();

					List<String> valuesAgre = new ArrayList<String>();
					keyValues = json.optJSONArray(newKey);

					if (keyValues != null)
						for (int j = 0; j < keyValues.length(); j++)
							valuesAgre.add(keyValues.get(j).toString());
					else
						valuesAgre.add(json.get(newKey).toString());

					connectDataImages(newKey, valuesAgre, result, ignoreExtraProperties);

				}
			}

			break;

		case "Category":

			
			lang = getLanguageStringDef(result.getDcType());
			lang.getText().addAll(values);

			break;

		case "Maker":
			
			
			lang = getLanguageStringDef(result.getDcCreator());
			lang.getText().addAll(values);

			break;

		case "TechniqueDescription":
		case "Technique":
			lang = getLanguageStringDef(result.getTechnique());
			lang.getText().addAll(values);
			break;

		case "Material":
			lang = getLanguageStringDef(result.getPhysicalCharacteristics());
			lang.getText().addAll(values);

			break;

		case "Name":
		case "Title":
			
			lang = getLanguageStringDef(result.getDcTitle());
			lang.getText().addAll(values);

			break;

		case "DateLate":
			valuesDateLate = values;

			if (valuesDateEarly != null) {

				lang = new LanguageString();
				lang.setLanguage("def");
				lang.getText().addAll(values);

				if (valuesDateEarly.get(0).equalsIgnoreCase(valuesDateLate.get(0)))
					lang.getText().add(valuesDateEarly.get(0));
				else
					lang.getText().add(valuesDateEarly.get(0) + "-" + valuesDateLate.get(0));

				result.getDcDate().add(lang);

			}
			break;

		case "DateEarly":
			valuesDateEarly = values;

			if (valuesDateLate != null) {

				lang = new LanguageString();
				lang.setLanguage("def");
				lang.getText().addAll(values);

				if (valuesDateEarly.get(0).equalsIgnoreCase(valuesDateLate.get(0)))
					lang.getText().add(valuesDateEarly.get(0));
				else
					lang.getText().add(valuesDateEarly.get(0) + "-" + valuesDateLate.get(0));

				result.getDcDate().add(lang);

			}
			break;

		case "ProductionPlaceName":

			if (result.getCreatedIn() == null)
				result.setCreatedIn(new CreatedIn());

			
			
			lang = getLanguageStringDef(result.getCreatedIn().getName());
			lang.getText().addAll(values);

			break;

		default:

			if (ignoreExtraProperties)
				break;

			ExtraProperty properties = new ExtraProperty();
			properties.setName(key);
			properties.getValue().addAll(values);

			result.getExtraProperties().add(properties);
		}
	}

	private static void connectDataImages(String key, List<String> values, Result result,
			boolean ignoreExtraProperties) {

		switch (key) {

		case "licenceURI":
			LanguageString lang = new LanguageString();

			lang = getLanguageStringDef(result.getDcRights());
			lang.getText().addAll(values);
			break;

		case "thumbnailURI":

			for (String value : values) {
		
				
				Resource res = new Resource();
				res.setType("Image");
				res.setUrl(value);
				result.getResources().add(res);
			}

			break;

		default:
			if (ignoreExtraProperties)
				break;

			ExtraProperty properties = new ExtraProperty();
			properties.setName("images_" + key);
			properties.getValue().addAll(values);

			result.getExtraProperties().add(properties);
		}

	}
	
	private static LanguageString getLanguageStringDef (List<LanguageString> langs) {
		
		
		
		if(langs.size() == 1)
			return langs.get(0);
		
		
		
		LanguageString lang = new LanguageString();
		lang.setLanguage("def");
		langs.add(lang);

		return lang;

	}
	
	private String getRepositoryName() throws IOException {

		if (repName == null) {

			Properties prop = new Properties();
			InputStream input = null;

			String name = new java.io.File(Repository.class.getProtectionDomain()
					  .getCodeSource()
					  .getLocation()
					  .getPath())
					.getName();
			
			int pos = name.lastIndexOf(".");
			if (pos > 0) {
			    name = name.substring(0, pos);
			}
			
	
			input = new FileInputStream("Repositorios/" + name + ".properties");
			prop.load(input);
			
			
			repName = prop.getOrDefault("Name", "").toString();
			
		}

		return repName;

	}
	

}
