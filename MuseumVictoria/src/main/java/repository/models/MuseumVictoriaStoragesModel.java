package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaStoragesModel {
    @JsonProperty("nature")
    public String nature;

    @JsonProperty("form")
    public String form;

    @JsonProperty("fixativeTreatment")
    public String fixativeTreatment;

    @JsonProperty("medium")
    public String medium;
}
