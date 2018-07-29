package repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import plataforma.modelointerno.ExtraProperty;
import plataforma.modelointerno.LanguageString;
import plataforma.modelointerno.Location;
import plataforma.modelointerno.Result;

public class Repositorio implements RepositoryAbstract {

	private static final String QUERY_BY_TERM_PART1 = "https://services.arcgis.com/1dSrzEWVQn5kHHyK/arcgis/rest/services/Cultura_CasasReligiosas/FeatureServer/0/query?where=UPPER(DESIGNACAO) like '%";
	private static final String QUERY_BY_TERM_PART2 = "%'&outFields=*&outSR=4326&f=json";
	private String repName = null;

	@Override
	public List<Result> SearchByBox(int latitudeFrom, int latitudeTo, int longitudeFrom, int longitudeTo,
			boolean ignoreExtraProperties) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Result> SearchByTerm(String term, boolean ignoreExtraProperties) throws Exception {
		final RestTemplate restTemplate = new RestTemplate();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		final HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);
		// Request

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		String request = QUERY_BY_TERM_PART1 + term.toUpperCase() + QUERY_BY_TERM_PART2;

		ResponseEntity<String> responseResponseEntity = restTemplate.exchange(request, HttpMethod.GET, entity,
				String.class);

		String response = responseResponseEntity.getBody();

		// restTemplate.getForObject(request, String.class);

		// Convert to Results
		List<Result> resultsList = new ArrayList<Result>();
		JSONObject responseJson = new JSONObject(response);

		JSONArray items = responseJson.getJSONArray("features");

		JSONObject item;
		JSONObject attributes, geometry;
		JSONArray keyValues;

		for (Object itemObject : items) {

			item = (JSONObject) itemObject;
			attributes = item.getJSONObject("attributes");
			geometry = item.getJSONObject("geometry");
			Result result = new Result();
			result.getSourcePage().add(attributes.getString("URL"));
			result.getSourceData()
					.add(QUERY_BY_TERM_PART1 + attributes.getString("DESIGNACAO").toUpperCase() + QUERY_BY_TERM_PART2);
			result.getSourceRepositorie().add(getRepositoryName());

			Iterator<String> keys = attributes.keys();

			String key;

			while (keys.hasNext()) {
				key = keys.next();

				List<String> values = new ArrayList<String>();
				keyValues = attributes.optJSONArray(key);

				if (keyValues != null)
					for (int j = 0; j < keyValues.length(); j++)
						values.add(keyValues.get(j).toString());
				else
					values.add(attributes.get(key).toString());

				connectData(key, values, result, ignoreExtraProperties);

			}

			Location loc = new Location();
			loc.getLatitude().add(geometry.getFloat("y"));
			loc.getLongitude().add(geometry.getFloat("x"));

			result.getLocations().add(loc);

			resultsList.add(result);

		}

		return resultsList;

	}

	private void connectData(String key, List<String> values, Result result, boolean ignoreExtraProperties) {

		LanguageString lang;

		switch (key) {

		case "DESIGNACAO":

			lang = getLanguageStringDef(result.getDcTitle());
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

	private LanguageString getLanguageStringDef(List<LanguageString> langs) {

		if (langs.size() == 1)
			return langs.get(0);

		LanguageString lang = new LanguageString();
		lang.setLanguage("pt");
		langs.add(lang);

		return lang;

	}

	private String getRepositoryName() throws IOException {

		if (repName == null) {

			Properties prop = new Properties();
			InputStream input = null;

			String name = new java.io.File(Repositorio.class.getProtectionDomain()
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
