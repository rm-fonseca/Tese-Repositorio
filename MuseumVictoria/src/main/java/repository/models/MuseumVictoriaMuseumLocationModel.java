package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaMuseumLocationModel {
    @JsonProperty("venue")
    public String venue;

    @JsonProperty("gallery")
    public String gallery;

    @JsonProperty("displayLocation")
    public String displayLocation;
}
