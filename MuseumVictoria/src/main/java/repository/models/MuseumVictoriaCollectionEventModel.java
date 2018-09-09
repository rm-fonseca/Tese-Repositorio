package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaCollectionEventModel {
    @JsonProperty("expeditionName")
    public String expeditionName;

    @JsonProperty("collectionEventCode")
    public String collectionEventCode;

    @JsonProperty("samplingMethod")
    public String samplingMethod;

    @JsonProperty("dateVisitedFrom")
    public String dateVisitedFrom;

    @JsonProperty("dateVisitedTo")
    public String dateVisitedTo;

    @JsonProperty("depthTo")
    public String depthTo;

    @JsonProperty("depthFrom")
    public String depthFrom;

    @JsonProperty("collectedBy")
    public String collectedBy;
}
