package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuseumVictoriaCommentModel {
    @JsonProperty("author")
    public String author;

    @JsonProperty("content")
    public String content;

    @JsonProperty("created")
    public String created;
}
