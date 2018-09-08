package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import repository.models.MuseumVictoriaArticleModel;
import repository.models.MuseumVictoriaRecordTypeModel;

public class Repository implements RepositoryAbstract {

	private static final String QUERY_BY_TERM = "https://collections.museumvictoria.com.au/api/search?query=";
	private static final String SOURCE_PAGE_BASE_URL = "https://collections.museumvictoria.com.au/"; //Get id attribute from json to concatnate
	private static final String SOURCE_DATA_BASE_URL = "https://collections.museumvictoria.com.au/api/"; //Get id attribute from json to concatnate
	private static String repName = null;

	private static List<String> valuesDateEarly;
	private static List<String> valuesDateLate;


	@Override
	public List<Result> SearchByBox(int latitudeFrom, int latitudeTo, int longitudeFrom, int longitudeTo,
			boolean ignoreExtraProperties) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Result> SearchByTerm(String term, boolean ignoreExtraProperties) throws Exception {
		final RestTemplate restTemplate = new RestTemplate();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		final HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);

		// Convert to Results
		List<Result> resultsList = new ArrayList<Result>();

		// Request
		String response = restTemplate.getForObject(QUERY_BY_TERM + term, String.class);
		ObjectMapper mapper = new ObjectMapper();
		final ObjectNode[] nodes = mapper.readValue(response, ObjectNode[].class);
		//MuseumVictoriaRecordTypeModel[] article = mapper.readValue(response, MuseumVictoriaRecordTypeModel[].class);

		for(ObjectNode node : nodes) {
			if(node.has("recordType")) {
				String recordType = node.get("recordType").asText().toUpperCase();
				switch (recordType) {
					case MuseumVictoriaRecordTypeModel.ARTICLE_RECORD_TYPE:
						MuseumVictoriaArticleModel articleModel = mapper.readValue(node.toString(), MuseumVictoriaArticleModel.class);
						connectDataArticle(articleModel, resultsList, ignoreExtraProperties);
					default:
						break;
				}
			}
		}

		return resultsList;
	}

	private static void connectDataArticle(MuseumVictoriaArticleModel articleModel, List<Result> results, boolean ignoreExtraProperties) throws IOException {
		Result result = new Result();
		result.getSourcePage().add(SOURCE_PAGE_BASE_URL + articleModel.id);
		result.getSourceData().add(SOURCE_DATA_BASE_URL + articleModel.id);
		result.getSourceRepositorie().add(getRepositoryName());

		//Record Type
		result.getDcType().add(new LanguageString() {{
			setLanguage("en");
			getText().add(articleModel.recordType); }});
		//License
		result.getDcRights().add(new LanguageString() {{
			setLanguage("en");
			getText().add(articleModel.licence.name);}});
		result.getDcRights().add(new LanguageString() {{
			setLanguage("en");
			getText().add(articleModel.licence.uri);}});
		//Date Modified
		result.getTermsModified().add(new LanguageString(){{
			setLanguage("en");
			getText().add(articleModel.dateModified);
		}});
		//Title & Display Title
		result.getDcTitle().add(new LanguageString(){{
			setLanguage("en");
			getText().add(articleModel.title);
			getText().add(articleModel.displayTitle);
		}});
		//Keywords
		for(String keyword : articleModel.keywords) {
			ExtraProperty extraProperty = new ExtraProperty();
			extraProperty.setName("Keyword");
			extraProperty.getValue().add(keyword);
			result.getExtraProperties().add(extraProperty);
		}
		//Content & ContentSummary
		result.getExtraProperties().add(new ExtraProperty(){{
			setName("Content");
			getValue().add(articleModel.content);
		}});
		result.getExtraProperties().add(new ExtraProperty(){{
			setName("Content Summary");
			getValue().add(articleModel.contentSummary);
		}});
		//Types
		for(String type : articleModel.types) {
			result.getDcType().add(new LanguageString(){{
				setLanguage("en");
				getText().add(type);
			}});
		}
		results.add(result);
	}

	private static void connectDataItem(Object itemModel, List<Result> results, boolean ignoreExtraProperties) {

	}

	private static void connectDataSpeciment(Object specimenModel, List<Result> results, boolean ignoreExtraProperties) {

	}

	private static LanguageString getLanguageStringDef (List<LanguageString> langs) {
		if(langs.size() == 1)
			return langs.get(0);

		LanguageString lang = new LanguageString();
		lang.setLanguage("def");
		langs.add(lang);

		return lang;
	}

	private static String getRepositoryName() throws IOException {
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
			//input = new FileInputStream("/home/rgarcia/storage/workspace/Tese-Repositorio/MuseumVictoria/src/main/java/museumvictoria.properties");
			prop.load(input);

			repName = prop.getOrDefault("Name", "").toString();
		}

		return repName;
	}

}
