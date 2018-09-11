package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("file")
public class MuseumVictoriaFileMediaModel extends AMuseumVictoriaMediaModel{
    @JsonProperty("file")
    public MuseumVictoriaFileModel file;
}
