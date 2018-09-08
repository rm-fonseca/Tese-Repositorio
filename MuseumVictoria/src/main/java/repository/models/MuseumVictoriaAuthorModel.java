package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaAuthorModel {
    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("biography")
    public String biography;

    @JsonProperty("profileImage")
    public MuseumVictoriaMediaModel profileImage;
}
