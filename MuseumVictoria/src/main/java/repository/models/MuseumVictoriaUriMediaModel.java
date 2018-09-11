package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("uri")
public class MuseumVictoriaUriMediaModel extends AMuseumVictoriaMediaModel{
    @JsonProperty("uri")
    public String uri;
}
