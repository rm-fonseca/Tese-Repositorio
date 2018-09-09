package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaTaxonomyModel {
    @JsonProperty("kingdom")
    public String kingdom;

    @JsonProperty("phylum")
    public String phylum;

    @JsonProperty("subphylum")
    public String subphylum;

    @JsonProperty("superclass")
    public String superclass;

    @JsonProperty("class")
    public String className;

    @JsonProperty("subclass")
    public String subclass;

    @JsonProperty("superorder")
    public String superorder;

    @JsonProperty("order")
    public String order;

    @JsonProperty("suborder")
    public String suborder;

    @JsonProperty("infraorder")
    public String infraorder;

    @JsonProperty("superfamily")
    public String superfamily;

    @JsonProperty("family")
    public String family;

    @JsonProperty("subfamily")
    public String subfamily;

    @JsonProperty("genus")
    public String genus;

    @JsonProperty("subgenus")
    public String subgenus;

    @JsonProperty("species")
    public String species;

    @JsonProperty("subspecies")
    public String subspecies;

    @JsonProperty("author")
    public String author;

    @JsonProperty("code")
    public String code;

    @JsonProperty("taxonName")
    public String taxonName;

    @JsonProperty("commonName")
    public String commonName;

    @JsonProperty("otherCommonNames")
    public String otherCommonNames;
}
