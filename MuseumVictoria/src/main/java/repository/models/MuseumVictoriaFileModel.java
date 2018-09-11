package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaFileModel {
    @JsonProperty("uri")
    public String uri;

    @JsonProperty("size")
    public String size;
}
