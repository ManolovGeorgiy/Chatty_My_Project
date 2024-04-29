package integration.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostGetReq {
    @JsonProperty("id")
    private String id;

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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getDraft() {
        return draft;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;
    @JsonProperty("body")
    private String body;

    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("createAt")
    private String createAt;
    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("draft")
    private int draft; //boolean

    @JsonProperty("user")
    private String user;
}