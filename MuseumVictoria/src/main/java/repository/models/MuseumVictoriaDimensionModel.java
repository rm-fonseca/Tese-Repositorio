package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaDimensionModel {
    @JsonProperty("configuration")
    public String configuration;

    @JsonProperty("dimensions")
    public String dimensions;

    @JsonProperty("comments")
    public String comments;
}
