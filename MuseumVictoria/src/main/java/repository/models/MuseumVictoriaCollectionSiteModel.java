package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MuseumVictoriaCollectionSiteModel {
    @JsonProperty("siteCode")
    public String siteCode;

    @JsonProperty("ocean")
    public String ocean;

    @JsonProperty("continent")
    public String continent;

    @JsonProperty("country")
    public String country;

    @JsonProperty("state")
    public String state;

    @JsonProperty("district")
    public String district;

    @JsonProperty("town")
    public String town;

    @JsonProperty("nearestNamedPlace")
    public String nearestNamedPlace;

    @JsonProperty("preciseLocation")
    public String preciseLocation;

    @JsonProperty("minimumElevation")
    public String minimumElevation;

    @JsonProperty("maximumElevation")
    public String maximumElevation;

    @JsonProperty("latitudes")
    public List<String> latitudes;

    @JsonProperty("longitudes")
    public List<String> longitudes;

    @JsonProperty("geodeticDatum")
    public String geodeticDatum;

    @JsonProperty("siteRadius")
    public String siteRadius;

    @JsonProperty("georeferenceBy")
    public String georeferenceBy;

    @JsonProperty("georeferenceDate")
    public String georeferenceDate;

    @JsonProperty("georeferenceProtocol")
    public String georeferenceProtocol;

    @JsonProperty("georeferenceSource")
    public String georeferenceSource;

    @JsonProperty("geologyEra")
    public String geologyEra;

    @JsonProperty("geologyPeriod")
    public String geologyPeriod;

    @JsonProperty("geologyEpoch")
    public String geologyEpoch;

    @JsonProperty("geologyStage")
    public String geologyStage;

    @JsonProperty("geologyGroup")
    public String geologyGroup;

    @JsonProperty("geologyFormation")
    public String geologyFormation;

    @JsonProperty("geologyMember")
    public String geologyMember;

    @JsonProperty("geologyRockType")
    public String geologyRockType;

}
