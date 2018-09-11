package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("image")
public class MuseumVictoriaImageMediaModel extends AMuseumVictoriaMediaModel {
    @JsonProperty("alternativeText")
    public String alternativeText;

    @JsonProperty("large")
    public MuseumVictoriaMediaTypeModel large;

    @JsonProperty("medium")
    public MuseumVictoriaMediaTypeModel medium;

    @JsonProperty("small")
    public MuseumVictoriaMediaTypeModel small;

    @JsonProperty("thumbnail")
    public MuseumVictoriaMediaTypeModel thumbnail;
}
