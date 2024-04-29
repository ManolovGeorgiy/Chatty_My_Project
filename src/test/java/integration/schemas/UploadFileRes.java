package integration.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadFileRes {
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("imageUrl")
    private String imageUrl;

}
