package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaMediaTypeModel {
    @JsonProperty("width")
    public Integer width;

    @JsonProperty("height")
    public Integer height;

    @JsonProperty("uri")
    public String uri;

    @JsonProperty("size")
    public Integer size;
}
