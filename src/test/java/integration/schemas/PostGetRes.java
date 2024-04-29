package integration.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostGetRes {

    @JsonProperty("id")
    private  String id;
    @JsonProperty ("title")
    private  String title;
    @JsonProperty ("description")
    private  String description;
    @JsonProperty ("body")
    private  String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    @JsonProperty ("imageUrl")
    private  String imageUrl;
    @JsonProperty ("draft")
    private  boolean draft;
}
