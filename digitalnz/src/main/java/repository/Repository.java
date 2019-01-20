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

	private static final String QUERY_BY_TERM = "http://api.digitalnz.org/v3/records.json?api_key=%s&text=%s";

	private static final String SOURCE_PAGE_BASE_URL = "https://digitalnz.org/records/%d";
	private static final String SOURCE_DATA_BASE_URL = "http://api.digitalnz.org/v3/records/%d.json?api_key=%s";

	private String repName = null;
	private String key = null;
	private static String repositoryID = null;

	public List<Result> SearchByBox(int latitudeFrom, int latitudeTo, int longitudeFrom, int longitudeTo,
			boolean ignoreExtraProperties) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Result getResult(String idResult, boolean ignoreExtraProperties) throws Exception {

		final RestTemplate restTemplate = new RestTemplate();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		final HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);

		int id;
		try {
			id = Integer.parseInt(idResult);
		} catch (Exception e) {
			return null;
		}
		
		System.out.println(SOURCE_DATA_BASE_URL);
		System.out.println(idResult);
		String request = String.format(SOURCE_DATA_BASE_URL, id, getKey());

		// Request
		String response = restTemplate.getForObject(request, String.class);

		// Convert to Results
		JSONObject item = new JSONObject(response).getJSONObject("record");
		JSONArray keyValues;

		Result result = new Result();
		result.getSourcePage().add(String.format(SOURCE_PAGE_BASE_URL, item.getInt("id")));
		result.setID(String.format(getRepositoryID() + "/" + item.getInt("id")));
		result.getSourceData().add(String.format(SOURCE_DATA_BASE_URL, item.getInt("id"), getKey()));
		result.getSourcePage().add(item.getString("landing_url"));

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

		return result;

	}

	public List<Result> SearchByTerm(String term, boolean ignoreExtraProperties) throws Exception {

		final RestTemplate restTemplate = new RestTemplate();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		final HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);

		String request = String.format(QUERY_BY_TERM, getKey(), term);

		// Request
		String response = restTemplate.getForObject(request, String.class);

		// Convert to Results
		List<Result> resultsList = new ArrayList<Result>();
		JSONObject responseJson = new JSONObject(response);
		JSONArray items = responseJson.getJSONObject("search").getJSONArray("results");

		JSONObject item;
		JSONArray keyValues;

		for (Object itemObject : items) {

			item = (JSONObject) itemObject;
			Result result = new Result();
			result.getSourcePage().add(String.format(SOURCE_PAGE_BASE_URL, item.getInt("id")));
			result.setID(String.format(getRepositoryID() + "/" + item.getInt("id")));
			result.getSourceData().add(String.format(SOURCE_DATA_BASE_URL, item.getInt("id"), getKey()));
			result.getSourcePage().add(item.getString("landing_url"));

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

	private void connectData(String key, List<String> values, Result result, boolean ignoreExtraProperties) {

		LanguageString lang;

		switch (key) {

		case "title":
		case "alternate_title":

			lang = getLanguageStringDef(result.getDcTitle());
			addAllWithoutDuplicate(lang.getText(), values);

			break;

		case "description":
		case "additional_description":

			lang = getLanguageStringDef(result.getDcDescription());
			addAllWithoutDuplicate(lang.getText(), values);

			break;

		case "creator":

			lang = getLanguageStringDef(result.getDcCreator());
			addAllWithoutDuplicate(lang.getText(), values);

			break;

		case "rights":

			lang = getLanguageStringDef(result.getDcRights());
			addAllWithoutDuplicate(lang.getText(), values);

			break;

		case "dc_type":

			lang = getLanguageStringDef(result.getDcType());
			addAllWithoutDuplicate(lang.getText(), values);

			break;

		case "display_date":

			lang = getLanguageStringDef(result.getDcDate());
			addAllWithoutDuplicate(lang.getText(), values);

			break;

		case "thumbnail_url":
		case "large_thumbnail_url":

			for (String value : values) {

				if (value.equals("null"))
					continue;

				boolean duplicate = false;
				for (Resource res : result.getResources()) {
					if (res.getUrl().equals(value)) {
						duplicate = true;
						continue;
					}
				}

				if (duplicate)
					continue;

				Resource res = new Resource();
				res.setType("Image");
				res.setUrl(value);
				result.getResources().add(res);
			}

			break;

		case "dc_identifier":

			lang = getLanguageStringDef(result.getDcIdentifier());
			addAllWithoutDuplicate(lang.getText(), values);

			break;

		default:

			if (ignoreExtraProperties)
				break;
			ExtraProperty properties = new ExtraProperty();
			properties.setName(key);

			properties.getValue().add(values.toString());

			result.getExtraProperties().add(properties);
		}
	}

	private static LanguageString getLanguageStringDef(List<LanguageString> langs) {

		if (langs.size() == 1)
			return langs.get(0);

		LanguageString lang = new LanguageString();
		lang.setLanguage("def");
		langs.add(lang);

		return lang;

	}

	private static void addAllWithoutDuplicate(List<String> list, List<String> values) {

		for (String value : values) {
			addAllWithoutDuplicate(list, value);
		}

	}

	private static void addAllWithoutDuplicate(List<String> list, String value) {

		if (!list.contains(value))
			list.add(value);

	}

	private String getRepositoryName() throws IOException {

		if (repName == null) {

			Properties prop = new Properties();
			InputStream input = null;

			String name = new java.io.File(
					Repository.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();

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

	private String getKey() throws IOException {

		if (key == null) {

			Properties prop = new Properties();
			InputStream input = null;

			String name = new java.io.File(
					Repository.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();

			int pos = name.lastIndexOf(".");
			if (pos > 0) {
				name = name.substring(0, pos);
			}

			input = new FileInputStream("Repositorios/" + name + ".properties");
			prop.load(input);

			key = prop.getOrDefault("Key", "").toString();

		}

		return key;

	}

	private static String getRepositoryID() {

		if (repositoryID == null) {

			Properties prop = new Properties();
			InputStream input = null;

			String name = new java.io.File(
					Repository.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();

			int pos = name.lastIndexOf(".");
			if (pos > 0) {
				name = name.substring(0, pos);
			}

			try {
				input = new FileInputStream("Repositorios/" + name + ".properties");
				prop.load(input);

			} catch (IOException e) {
				repositoryID = "-1";
				return repositoryID;
			}

			repositoryID = prop.getOrDefault("ID", "").toString();

		}

		return repositoryID;

	}

}
