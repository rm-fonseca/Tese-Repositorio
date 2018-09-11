package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MuseumVictoriaImageMediaModel.class, name = "image"),
        @JsonSubTypes.Type(value = MuseumVictoriaFileMediaModel.class, name = "file"),
        @JsonSubTypes.Type(value = MuseumVictoriaUriMediaModel.class, name = "uri")
})
public abstract class AMuseumVictoriaMediaModel {
    @JsonProperty("type")
    public String type;

    @JsonProperty("dateModified")
    public String dateModified;

    @JsonProperty("caption")
    public String caption;

    @JsonProperty("creators")
    public List<String> creators;

    @JsonProperty("sources")
    public List<String> sources;

    @JsonProperty("credit")
    public String credit;

    @JsonProperty("rightsStatement")
    public String rightsStatement;

    @JsonProperty("licence")
    public MuseumVictoriaLicenseModel license;
}
