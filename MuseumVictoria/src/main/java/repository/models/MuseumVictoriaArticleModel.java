package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MuseumVictoriaArticleModel {
    @JsonProperty("recordType")
    public String recordType;

    @JsonProperty("licence")
    public MuseumVictoriaLicenseModel licence;

    @JsonProperty("dateModified")
    public String dateModified;

    @JsonProperty("title")
    public String title;

    @JsonProperty("displayTitle")
    public String displayTitle;

    @JsonProperty("keywords")
    public List<String> keywords;

    @JsonProperty("localities")
    public List<String> localities;

    @JsonProperty("content")
    public String content;

    @JsonProperty("contentSummary")
    public String contentSummary;

    @JsonProperty("types")
    public List<String> types;

    @JsonProperty("authors")
    public List<MuseumVictoriaAuthorModel> authors;

    @JsonProperty("contributors")
    public List<MuseumVictoriaAuthorModel> contributers;

    @JsonProperty("media")
    public List<AMuseumVictoriaMediaModel> media;

    @JsonProperty("yearWritten")
    public String yearWritten;

    @JsonProperty("parentArticleId")
    public String parentArticleId;

    @JsonProperty("childArticleIds")
    public List<String> childArticleIds;

    @JsonProperty("relatedArticleIds")
    public List<String> relatedArticleIds;

    @JsonProperty("relatedItemIds")
    public List<String> relatedItemIds;

    @JsonProperty("relatedSpecimenIds")
    public List<String> relatedSpecimenIds;

    @JsonProperty("id")
    public String id;
}
