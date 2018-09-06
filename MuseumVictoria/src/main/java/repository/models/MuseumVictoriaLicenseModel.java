package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("licence")
public class MuseumVictoriaLicenseModel {
    @JsonProperty("name")
    public String name;

    @JsonProperty("shortName")
    public String shortName;

    @JsonProperty("uri")
    public String uri;
}
