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
import plataforma.modelointerno.*;
import repository.models.*;

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
		int i = 0;
		for(ObjectNode node : nodes) {
			System.out.println(i);
			i++;
			if(node.has("recordType")) {
				String recordType = node.get("recordType").asText().toUpperCase();
				switch (recordType) {
					case MuseumVictoriaRecordTypeModel.ARTICLE_RECORD_TYPE:
						MuseumVictoriaArticleModel articleModel = mapper.readValue(node.toString(), MuseumVictoriaArticleModel.class);
						connectDataArticle(articleModel, resultsList, ignoreExtraProperties);
						break;
					case MuseumVictoriaRecordTypeModel.ITEM_RECORD_TYPE:
						MuseumVictoriaItemModel itemModel = mapper.readValue(node.toString(), MuseumVictoriaItemModel.class);
						connectDataItem(itemModel, resultsList, ignoreExtraProperties);
						break;
					case MuseumVictoriaRecordTypeModel.SPECIMEN_RECORD_TYPE:
						MuseumVictoriaSpecimenModel specimenModel = mapper.readValue(node.toString(), MuseumVictoriaSpecimenModel.class);
						connectDataSpecimen(specimenModel, resultsList, ignoreExtraProperties);
						break;
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
		for (String keyword : articleModel.keywords) {
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
		for (String type : articleModel.types) {
			result.getDcType().add(new LanguageString(){{
				setLanguage("en");
				getText().add(type);
			}});
		}
		//Authors
		for (MuseumVictoriaAuthorModel author : articleModel.authors) {
			result.getAgents().add(new Agent(){{
				getName().add(new LanguageString(){{
					setLanguage("en");
					getText().add(author.fullName);
				}});
			}});
		}
		//Contributors
		for (MuseumVictoriaAuthorModel contributor : articleModel.contributers) {
			result.getDcContributor().add(new LanguageString(){{
				setLanguage("en");
				getText().add(contributor.fullName);
			}});
		}
		//Media
		for (AMuseumVictoriaMediaModel media : articleModel.media) {
			connectMedia(media, result);
		}
		//Year Written
		result.getTermsCreated().add(new LanguageString(){{
			setLanguage("en");
			getText().add(articleModel.yearWritten);
		}});
		//Parent Article Id
		result.getDcRelation().add(new LanguageString(){{
			setLanguage("en");
			getText().add(SOURCE_PAGE_BASE_URL + articleModel.parentArticleId);
		}});
		//Child Article Ids
		for(String childArticle : articleModel.childArticleIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + childArticle);
			}});
		}
		//Related Article Ids
		for(String relatedArticle : articleModel.relatedArticleIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedArticle);
			}});
		}
		//Related Item Ids
		for(String relatedItem : articleModel.relatedItemIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedItem);
			}});
		}
		//Related Specimen Ids
		for(String relatedSpecimen : articleModel.relatedSpecimenIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedSpecimen);
			}});
		}
		results.add(result);
	}

	private static void connectDataItem(MuseumVictoriaItemModel itemModel, List<Result> results, boolean ignoreExtraProperties) throws IOException {
		Result result = new Result();
		result.getSourcePage().add(SOURCE_PAGE_BASE_URL + itemModel.id);
		result.getSourceData().add(SOURCE_DATA_BASE_URL + itemModel.id);
		result.getSourceRepositorie().add(getRepositoryName());

		//Record Type, Category, Discipline, Type
		result.getDcType().add(new LanguageString() {{
			setLanguage("en");
			getText().add(itemModel.recordType);
			getText().add(itemModel.category);
			getText().add(itemModel.discipline);
			getText().add(itemModel.type);
		}});
		//Comments -- Not implemented
		//License
		result.getDcRights().add(new LanguageString() {{
			setLanguage("en");
			getText().add(itemModel.licence.name);
		}});
		result.getDcRights().add(new LanguageString() {{
			setLanguage("en");
			getText().add(itemModel.licence.uri);
		}});
		//Date Modified
		result.getTermsModified().add(new LanguageString(){{
			setLanguage("en");
			getText().add(itemModel.dateModified);
		}});
		//Display Title
		result.getDcTitle().add(new LanguageString(){{
			setLanguage("en");
			getText().add(itemModel.displayTitle);
		}});
		//CollectingAreas & Classifications
		for(String collectingArea : itemModel.collectingAreas) {
			ExtraProperty extraProperty = new ExtraProperty();
			extraProperty.setName("collectingAreas");
			extraProperty.getValue().add(collectingArea);
			result.getExtraProperties().add(extraProperty);
		}
		for(String classification : itemModel.classifications) {
			ExtraProperty extraProperty = new ExtraProperty();
			extraProperty.setName("collectingAreas");
			extraProperty.getValue().add(classification);
			result.getExtraProperties().add(extraProperty);
		}
		//ObjectName & ObjectSummary
		result.getExtraProperties().add(new ExtraProperty(){{
			setName("Object Name");
			getValue().add(itemModel.objectName);
		}});
		result.getExtraProperties().add(new ExtraProperty(){{
			setName("Object Summary");
			getValue().add(itemModel.objectSummary);
		}});
		//Physical Description
		result.getPhysicalCharacteristics().add(new LanguageString(){{
			setLanguage("en");
			getText().add(itemModel.physicalDescription);
		}});
		//Keywords
		for (String keyword : itemModel.keywords) {
			ExtraProperty extraProperty = new ExtraProperty();
			extraProperty.setName("Keyword");
			extraProperty.getValue().add(keyword);
			result.getExtraProperties().add(extraProperty);
		}
		//Shape
		result.getPhysicalCharacteristics().add(new LanguageString(){{
			setLanguage("en");
			getText().add(itemModel.shape);
		}});
		//Dimensions
		for(MuseumVictoriaDimensionModel dimension : itemModel.dimensions) {
			result.getPhysicalCharacteristics().add(new LanguageString(){{
				setLanguage("en");
				getText().add(dimension.configuration);
				getText().add(dimension.dimensions);
			}});
		}
		//Related Item Ids
		for(String relatedItem : itemModel.relatedItemIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedItem);
			}});
		}
		//Related Speciment Ids
		for(String relatedSpecimen : itemModel.relatedSpecimenIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedSpecimen);
			}});
		}
		//Related Articles Ids
		for(String relatedArticle : itemModel.relatedArticleIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedArticle);
			}});
		}
		//Related Species Ids
		for(String relatedSpecies : itemModel.relatedSpeciesIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedSpecies);
			}});
		}
		//Media
		for (AMuseumVictoriaMediaModel media : itemModel.media) {
			connectMedia(media, result);
		}
		results.add(result);
	}

	private static void connectDataSpecimen(MuseumVictoriaSpecimenModel specimenModel, List<Result> results, boolean ignoreExtraProperties) throws IOException {
		Result result = new Result();
		result.getSourcePage().add(SOURCE_PAGE_BASE_URL + specimenModel.id);
		result.getSourceData().add(SOURCE_DATA_BASE_URL + specimenModel.id);
		result.getSourceRepositorie().add(getRepositoryName());

		//TODO: Complete mapping of Json to Result
		//Record Type, Category, Discipline, Type
		result.getDcType().add(new LanguageString() {{
			setLanguage("en");
			getText().add(specimenModel.recordType);
			getText().add(specimenModel.category);
			getText().add(specimenModel.discipline);
			getText().add(specimenModel.type);
			getText().add(specimenModel.scientificGroup);
		}});
		//License
		result.getDcRights().add(new LanguageString() {{
			setLanguage("en");
			getText().add(specimenModel.licence.name);}});
		result.getDcRights().add(new LanguageString() {{
			setLanguage("en");
			getText().add(specimenModel.licence.uri);}});
		//ObjectName & ObjectSummary
		result.getExtraProperties().add(new ExtraProperty(){{
			setName("Object Name");
			getValue().add(specimenModel.objectName);
		}});
		result.getExtraProperties().add(new ExtraProperty(){{
			setName("Object Summary");
			getValue().add(specimenModel.objectSummary);
		}});
		//Media
		for (AMuseumVictoriaMediaModel media : specimenModel.media) {
			connectMedia(media, result);
		}
		//Related Item Ids
		for(String relatedItem : specimenModel.relatedItemIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedItem);
			}});
		}
		//Related Speciment Ids
		for(String relatedSpecimen : specimenModel.relatedSpecimenIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedSpecimen);
			}});
		}
		//Related Articles Ids
		for(String relatedArticle : specimenModel.relatedArticleIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedArticle);
			}});
		}
		//Related Species Ids
		for(String relatedSpecies : specimenModel.relatedSpeciesIds) {
			result.getDcRelation().add(new LanguageString(){{
				setLanguage("en");
				getText().add(SOURCE_PAGE_BASE_URL + relatedSpecies);
			}});
		}
		results.add(result);
	}

	private static void connectMedia(AMuseumVictoriaMediaModel abstractMediaModel, Result result) {
		if(abstractMediaModel instanceof MuseumVictoriaFileMediaModel) {
			MuseumVictoriaFileMediaModel media = (MuseumVictoriaFileMediaModel) abstractMediaModel;
			result.getResources().add(new Resource(){{
				setType("Image");
				setUrl(media.file.uri);
			}});
		}
		else if (abstractMediaModel instanceof MuseumVictoriaImageMediaModel) {
			MuseumVictoriaImageMediaModel media = (MuseumVictoriaImageMediaModel) abstractMediaModel;
			result.getResources().add(new Resource(){{
				setType("Image");
				setUrl(media.small.uri);
			}});
			result.getResources().add(new Resource(){{
				setType("Image");
				setUrl(media.medium.uri);
			}});
			result.getResources().add(new Resource(){{
				setType("Image");
				setUrl(media.large.uri);
			}});
			result.getResources().add(new Resource(){{
				setType("Image");
				setUrl(media.thumbnail.uri);
			}});
		}
		else if(abstractMediaModel instanceof MuseumVictoriaUriMediaModel) {
			MuseumVictoriaUriMediaModel media = (MuseumVictoriaUriMediaModel) abstractMediaModel;
			result.getResources().add(new Resource(){{
				setType("Other");
				setUrl(media.uri);
			}});
		}
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
