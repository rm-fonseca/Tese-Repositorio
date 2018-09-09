package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaAssociationModel {
    @JsonProperty("type")
    public String type;

    @JsonProperty("name")
    public String name;

    @JsonProperty("date")
    public String date;

    @JsonProperty("comments")
    public String comments;

    @JsonProperty("streetAddress")
    public String streetAddress;

    @JsonProperty("locality")
    public String locality;

    @JsonProperty("region")
    public String region;

    @JsonProperty("state")
    public String state;

    @JsonProperty("country")
    public String country;
}
