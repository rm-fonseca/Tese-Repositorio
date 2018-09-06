package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MuseumVictoriaMediaModel {
    @JsonProperty("type")
    public String type;

    @JsonProperty("alternativeText")
    public String alternativeText;

    @JsonProperty("large")
    public MuseumVictoriaMediaTypeModel large;

    @JsonProperty("medium")
    public MuseumVictoriaMediaTypeModel medium;

    @JsonProperty("small")
    public MuseumVictoriaMediaTypeModel small;

    @JsonProperty("thumbnail")
    public MuseumVictoriaMediaTypeModel thumbnail;

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
