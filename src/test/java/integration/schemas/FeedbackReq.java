package integration.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackReq {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("name")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("email")
    private String email;
    @JsonProperty("content")
    private String content;
}
