package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import plataforma.modelointerno.Agent;
import plataforma.modelointerno.DateTime;
import plataforma.modelointerno.ExtraProperty;
import plataforma.modelointerno.LanguageString;
import plataforma.modelointerno.Location;
import plataforma.modelointerno.Resource;
import plataforma.modelointerno.Result;
import plataforma.modelointerno.TimeSpan;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.json.*;

public class Repositorio implements RepositoryAbstract {

	
	private String repName = null;
	private String key = null;
	
	
	private static String EUROPEANA_KEY_PARAM = "wskey";
	private static String EUROPEANA_QUERY_PARAM = "query";
	private static String EUROPEANA_START_PARAM = "start";
	private static String EUROPEANA_QUERY_BOX_SEARCH = "pl_wgs84_pos_lat:[%d+TO+%d]+AND+pl_wgs84_pos_long:[%d+TO+%d]";
	private static String EUROPEANA_HOST_URL = "https://www.europeana.eu/api/v2/search.json";

	@Override
	public List<Result> SearchByBox(int latitudeFrom, int latitudeTo, int longitudeFrom, int longitudeTo,
			boolean ignoreExtraProperties) throws Exception {

		String requestQuery = String.format(EUROPEANA_QUERY_BOX_SEARCH, latitudeFrom, latitudeTo, longitudeFrom,
				longitudeTo);


		
		int startPage = 1;
		int count = 1;
		UriComponentsBuilder builder;
		List<Result> result = new ArrayList<Result>();
		while (startPage < 50 && count > 0) {

			
			
			// Query parameters
			builder = UriComponentsBuilder.fromUriString(EUROPEANA_HOST_URL)
					// Add query parameter
					.queryParam(EUROPEANA_KEY_PARAM, getKey()).queryParam(EUROPEANA_QUERY_PARAM, requestQuery)
					.queryParam(EUROPEANA_START_PARAM, startPage);

			String response = getRestTemplate().getForObject(builder.buildAndExpand().toUri(), String.class);

			JSONObject responseJson = new JSONObject(response);

			int totalResults = responseJson.getInt("totalResults");

			if (totalResults == 0) {
				return result;
			}

			count = responseJson.getInt("itemsCount");

			result.addAll(ConvertData(response, ignoreExtraProperties));

			startPage += count;
			
		}
		return result;

	}

	@Override
	public List<Result> SearchByTerm(String term, boolean ignoreExtraProperties) throws IOException {
		// Query parameters
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(EUROPEANA_HOST_URL)
				// Add query parameter
				.queryParam(EUROPEANA_KEY_PARAM, getKey()).queryParam(EUROPEANA_QUERY_PARAM, term);

		
		// Request
		String response = getRestTemplate().getForObject(builder.buildAndExpand().toUri(), String.class);

		return ConvertData(response, ignoreExtraProperties);
	}

	private RestTemplate getRestTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		final HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);

		return restTemplate;
	}

	private List<Result> ConvertData(String response, boolean ignoreExtraProperties) throws IOException {
		// Convert to Results
		List<Result> resultsList = new ArrayList<Result>();
		JSONObject responseJson = new JSONObject(response);

		int totalResults = responseJson.getInt("totalResults");

		if (totalResults == 0) {
			return resultsList;
		}

		int count = responseJson.getInt("itemsCount");

		String type;

		JSONArray items = responseJson.getJSONArray("items");

		JSONObject item;
		Object keyValues;

		for (int i = 0; i < count; i++) {
			item = items.getJSONObject(i);

			response = getRestTemplate().getForObject(item.getString("link"), String.class);


			Result result = new Result();
			result.getSourceData().add(item.getString("link"));
			result.getSourceRepositorie().add(getRepositoryName());
			JSONObject responseItem = new JSONObject(response);
			item = responseItem.getJSONObject("object");
			type = item.getString("type");

			Iterator<String> keys = item.keys();

			String key;

			while (keys.hasNext()) {
				key = keys.next();

				keyValues = item.get(key);

				connectData(key, keyValues, result, ignoreExtraProperties, type);

			}

			resultsList.add(result);

		}

		return resultsList;
	}

	private static void connectData(String key, Object values, Result result, boolean ignoreExtraProperties,
			String type) {
		String newKey;
		JSONArray keyValues, jsonArray;

		switch (key) {

		case "dcContributor":
			getLanguageStringFromJson(values, result.getDcContributor());
			break;

		case "dcCoverage":
			getLanguageStringFromJson(values, result.getDcCoverage());
			break;

		case "dcCreator":
			getLanguageStringFromJson(values, result.getDcCreator());
			break;

		case "dcDate":
			getLanguageStringFromJson(values, result.getDcDate());
			break;

		case "dcDescription":
			getLanguageStringFromJson(values, result.getDcDescription());
			break;

		case "dcFormat":
			getLanguageStringFromJson(values, result.getDcFormat());
			break;

		case "dcIdentifier":
			getLanguageStringFromJson(values, result.getDcIdentifier());
			break;

		case "dcLanguage":
			getLanguageStringFromJson(values, result.getDcLanguage());
			break;

		case "dcPublisher":
			getLanguageStringFromJson(values, result.getDcPublisher());
			break;

		case "dcRights":
			getLanguageStringFromJson(values, result.getDcRights());
			break;

		case "dcSource":
			getLanguageStringFromJson(values, result.getDcSource());
			break;

		case "dcSubject":
			getLanguageStringFromJson(values, result.getDcSubject());
			break;

		case "dcTitle":
		case "title":
			getLanguageStringFromJson(values, result.getDcTitle());
			break;

		case "dcType":
		case "type":
			getLanguageStringFromJson(values, result.getDcType());
			break;

		case "aggregations":

			jsonArray = (JSONArray) values;

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject json = jsonArray.getJSONObject(i);
				Iterator<String> keys = json.keys();

				while (keys.hasNext()) {
					newKey = keys.next();

					connectDataAggregations(newKey, json.get(newKey), result, ignoreExtraProperties, type);

				}

			}

			break;

		case "europeanaAggregation":

			JSONObject jsonObject = (JSONObject) values;

			Iterator<String> keysIt = jsonObject.keys();

			while (keysIt.hasNext()) {
				newKey = keysIt.next();

				connectDataEuropeanaAggregation(newKey, jsonObject.get(newKey), result, ignoreExtraProperties);

			}

			break;

		case "proxies":

			jsonArray = (JSONArray) values;

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject json = jsonArray.getJSONObject(i);
				Iterator<String> keys = json.keys();

				while (keys.hasNext()) {
					newKey = keys.next();

					connectDataProxies(newKey, json.get(newKey), result, ignoreExtraProperties);

				}

			}

			break;

		case "places":

			int idPlace = 0;
			jsonArray = (JSONArray) values;

			for (int i = 0; i < jsonArray.length(); i++) {
				Location loc = new Location();
				loc.setId(idPlace++);
				JSONObject json = jsonArray.getJSONObject(i);
				Iterator<String> keys = json.keys();

				while (keys.hasNext()) {
					newKey = keys.next();

					connectDataLocations(newKey, json.get(newKey), result, loc, ignoreExtraProperties);

				}
				result.getLocations().add(loc);

			}

			break;

		case "agents":

			jsonArray = (JSONArray) values;

			for (int i = 0; i < jsonArray.length(); i++) {
				Agent agent = new Agent();

				JSONObject json = jsonArray.getJSONObject(i);
				Iterator<String> keys = json.keys();

				while (keys.hasNext()) {
					newKey = keys.next();

					connectDataAgent(newKey, json.get(newKey), result, agent, ignoreExtraProperties);

				}
				result.getAgents().add(agent);

			}

			break;

		case "timespans":

			jsonArray = (JSONArray) values;

			for (int i = 0; i < jsonArray.length(); i++) {
				TimeSpan timespan = new TimeSpan();

				JSONObject json = jsonArray.getJSONObject(i);
				Iterator<String> keys = json.keys();

				while (keys.hasNext()) {
					newKey = keys.next();

					connectDataTimeSpawn(newKey, json.get(newKey), result, timespan, ignoreExtraProperties);

				}
				result.getTimeSpans().add(timespan);

			}

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

	private static void connectDataAggregations(String key, Object object, Result result, boolean ignoreExtraProperties,
			String type) {

		JSONArray jsonArray;

		switch (key) {

		case "edmObject":
		case "edmIsShownBy":

			Resource res = new Resource();
			String url = object.toString();
			
			String[] values = url.split("\\.");
			String extension = values[values.length-1].split("\\?")[0];
			

			switch (extension) {

			case "jpg":
				res.setType("Image");
				break;
			case "mp3":
				res.setType("Sound");
				break;

			default:

				switch (type) {

				case "VIDEO":

					res.setType("Video");
					break;
				case "IMAGE":

					res.setType("Image");
					break;

				case "SOUND":

					res.setType("Sound");
					break;
				case "3D":
				case "_3D":

					res.setType("3D");
					break;
				default:

					res.setType("Other");

				}

			}

			res.setUrl(url);
			result.getResources().add(res);

			break;

		case "edmIsShownAt":

			addWithoutDuplicate(result.getSourcePage(), object.toString());

			// result.getSourcePage().add(object.toString());
			break;

		default:

			if (ignoreExtraProperties)
				break;
			ExtraProperty properties = new ExtraProperty();
			properties.setName(key);

			properties.getValue().add(object.toString());

			result.getExtraProperties().add(properties);
		}

	}

	private static void connectDataTimeSpawn(String key, Object object2, Result result, TimeSpan timeSpan,
			boolean ignoreExtraProperties) {

		String[] time;
		DateTime dateTime;
		JSONObject object;
		switch (key) {

		case "prefLabel":
			getLanguageStringFromJson(object2, timeSpan.getName());
			break;

		case "begin":
			object = (JSONObject) object2;

			time = object.getJSONArray("def").getString(0).split(" ");

			dateTime = new DateTime();

			dateTime.setYear(Integer.parseInt(time[5]));

			switch (time[1].toLowerCase()) {
			case "jan":
				dateTime.setMonth(1);
				break;
			case "fev":
				dateTime.setMonth(2);
				break;
			case "mar":
				dateTime.setMonth(3);
				break;
			case "apr":
				dateTime.setMonth(4);
				break;
			case "may":
				dateTime.setMonth(5);
				break;
			case "jun":
				dateTime.setMonth(6);
				break;
			case "jul":
				dateTime.setMonth(7);
				break;
			case "aug":
				dateTime.setMonth(8);
				break;
			case "set":
				dateTime.setMonth(9);
				break;
			case "oct":
				dateTime.setMonth(10);
				break;
			case "nov":
				dateTime.setMonth(11);
				break;
			case "dec":
				dateTime.setMonth(12);
				break;
			}

			dateTime.setDay(Integer.parseInt(time[2]));

			time = time[3].split(":");

			dateTime.setHour(Integer.parseInt(time[0]));
			dateTime.setMinute(Integer.parseInt(time[1]));
			dateTime.setSecond(Integer.parseInt(time[2]));

			timeSpan.setBegin(dateTime);

			break;

		case "end":

			object = (JSONObject) object2;

			time = object.getJSONArray("def").getString(0).split(" ");

			dateTime = new DateTime();

			dateTime.setYear(Integer.parseInt(time[5]));

			switch (time[1].toLowerCase()) {
			case "jan":
				dateTime.setMonth(1);
				break;
			case "fev":
				dateTime.setMonth(2);
				break;
			case "mar":
				dateTime.setMonth(3);
				break;
			case "apr":
				dateTime.setMonth(4);
				break;
			case "may":
				dateTime.setMonth(5);
				break;
			case "jun":
				dateTime.setMonth(6);
				break;
			case "jul":
				dateTime.setMonth(7);
				break;
			case "aug":
				dateTime.setMonth(8);
				break;
			case "set":
				dateTime.setMonth(9);
				break;
			case "oct":
				dateTime.setMonth(10);
				break;
			case "nov":
				dateTime.setMonth(11);
				break;
			case "dec":
				dateTime.setMonth(12);
				break;
			}

			dateTime.setDay(Integer.parseInt(time[2]));

			time = time[3].split(":");

			dateTime.setHour(Integer.parseInt(time[0]));
			dateTime.setMinute(Integer.parseInt(time[1]));
			dateTime.setSecond(Integer.parseInt(time[2]));

			timeSpan.setEnd(dateTime);

			break;

		default:

			if (ignoreExtraProperties)
				break;

			ExtraProperty properties = new ExtraProperty();
			properties.setName(key);
			properties.getValue().add(object2.toString());

			timeSpan.getExtraProperties().add(properties);

		}

	}

	private static void connectDataAgent(String key, Object object, Result result, Agent agent,
			boolean ignoreExtraProperties) {

		switch (key) {

		case "prefLabel":
			getLanguageStringFromJson(object, agent.getName());
			break;

		case "rdaGr2DateOfDeath":

			agent.setDateOfDeath(((JSONObject) object).getJSONArray("def").getString(0));

			break;

		case "rdaGr2DateOfBirth":
			agent.setDateOfBirth(((JSONObject) object).getJSONArray("def").getString(0));

			break;

		default:

			if (ignoreExtraProperties)
				break;

			ExtraProperty properties = new ExtraProperty();
			properties.setName(key);
			properties.getValue().add(object.toString());

			agent.getExtraProperties().add(properties);

		}

	}

	private static void connectDataLocations(String key, Object object, Result result, Location location,
			boolean ignoreExtraProperties) {

		JSONArray jsonArray;

		switch (key) {

		case "prefLabel":
		case "altLabel":
			getLanguageStringFromJson(object, location.getName());
			break;

		case "latitude":

			// jsonArray = (JSONArray) object;

			location.getLatitude().add(Float.parseFloat(object.toString()));

			// for (int i = 0; i < jsonArray.length(); i++)
			// location.getLatitude().add(jsonArray.getFloat(i));

			break;

		case "longitude":

			location.getLongitude().add(Float.parseFloat(object.toString()));

			break;

		default:

			if (ignoreExtraProperties)
				break;

			ExtraProperty properties = new ExtraProperty();
			properties.setName(key);
			properties.getValue().add(object.toString());

			location.getExtraProperties().add(properties);

		}

	}

	private static void connectDataEuropeanaAggregation(String key, Object object, Result result,
			boolean ignoreExtraProperties) {

		switch (key) {

		case "edmLandingPage":
			// result.getSourcePage().add(object.toString());
			addWithoutDuplicate(result.getSourcePage(), object.toString());
			break;

		case "dcCreator":
			getLanguageStringFromJson(object, result.getDcCreator());
			break;

		case "edmRights":
			getLanguageStringFromJson(object, result.getDcRights());
			break;

		case "edmPreview":

			for (Resource res : result.getResources()) {
				if (res.getUrl().equals(object.toString()))
					break;
			}

			Resource res = new Resource();
			res.setType("Image");
			res.setUrl(object.toString());

			result.getResources().add(res);
			break;

		default:

			if (ignoreExtraProperties)
				break;

			ExtraProperty properties = new ExtraProperty();
			properties.setName("EuropeanaAggregation_" + key);

			properties.getValue().add(object.toString());

			result.getExtraProperties().add(properties);

		}

	}

	private static void connectDataProxies(String key, Object object, Result result, boolean ignoreExtraProperties) {

		switch (key) {

		case "dcCreator":
			getLanguageStringFromJson(object, result.getDcCreator());
			break;

		case "dcPublisher":
			getLanguageStringFromJson(object, result.getDcPublisher());

			break;

		case "dcContributor":
			getLanguageStringFromJson(object, result.getDcContributor());
			break;

		case "dcDate":

			getLanguageStringFromJson(object, result.getDcDate());
			break;

		case "dcDescription":

			getLanguageStringFromJson(object, result.getDcDescription());
			break;

		case "dcFormat":

			getLanguageStringFromJson(object, result.getDcFormat());

			break;

		case "dcLanguage":

			getLanguageStringFromJson(object, result.getDcLanguage());

			break;

		case "dctermsMedium":

			getLanguageStringFromJson(object, result.getTermsMedium());
			break;

		case "dcIdentifier":

			getLanguageStringFromJson(object, result.getDcIdentifier());

			break;

		case "dcRelation":

			getLanguageStringFromJson(object, result.getDcRelation());

			break;

		case "dcSubject":

			getLanguageStringFromJson(object, result.getDcSubject());

			break;

		case "dcTitle":

			getLanguageStringFromJson(object, result.getDcTitle());
			break;

		case "dctermsIsPartOf":

			getLanguageStringFromJson(object, result.getTermsIsPartOf());

			break;

		case "dctermsExtent":

			getLanguageStringFromJson(object, result.getTermsExtent());

			break;

		case "dcType":

			getLanguageStringFromJson(object, result.getDcType());

			break;

		case "edmType":

			getLanguageStringFromJson(object, result.getDcType());

			break;

		case "dctermsSpatial":
			getLanguageStringFromJson(object, result.getTermsSpatial());

			break;

		default:

			if (ignoreExtraProperties)
				break;

			ExtraProperty properties = new ExtraProperty();
			properties.setName("Proxies_" + key);
			properties.getValue().add(object.toString());

			result.getExtraProperties().add(properties);
		}

	}

	private static List<LanguageString> getLanguageStringFromJson(Object obj, List<LanguageString> langList) {

		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			Iterator<String> keys = json.keys();
			String keyJson;
			JSONArray jsonArray;
			if (keys.hasNext()) {

				while (keys.hasNext()) {

					keyJson = keys.next();
					jsonArray = json.getJSONArray(keyJson);
					LanguageString lang = findLanguageString(keyJson, langList);
					for (int i = 0; i < jsonArray.length(); i++) {
						// lang.getText().add(jsonArray.getString(i));
						addWithoutDuplicate(lang.getText(), jsonArray.getString(i));
					}
				}
			}
			return langList;
		}

		if (obj instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray) obj;
			LanguageString lang = findLanguageString("def", langList);
			lang.setLanguage("def");
			for (int i = 0; i < jsonArray.length(); i++) {
				// lang.getText().add(jsonArray.getString(i));
				addWithoutDuplicate(lang.getText(), jsonArray.getString(i));
			}

			return langList;

		}

		LanguageString lang = findLanguageString("def", langList);
		addWithoutDuplicate(lang.getText(), obj.toString());

		// lang.getText().add(obj.toString());
		return langList;

	}

	private static LanguageString findLanguageString(String langText, List<LanguageString> langs) {

		for (LanguageString lan : langs)
			if (lan.getLanguage().equalsIgnoreCase(langText))
				return lan;

		LanguageString newLang = new LanguageString();
		newLang.setLanguage(langText);
		langs.add(newLang);

		return newLang;
	}

	private static void addWithoutDuplicate(List<String> list, String value) {

		if (!list.contains(value))
			list.add(value);

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
	
	private String getKey() throws IOException {

		if (key == null) {

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
			
			
			key = prop.getOrDefault("Key", "").toString();
			
		}

		return key;

	}
}
