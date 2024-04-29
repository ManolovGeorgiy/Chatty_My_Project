package integration.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshTokenReq {
    @JsonProperty("refreshToken")
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
