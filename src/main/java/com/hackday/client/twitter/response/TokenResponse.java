package com.hackday.client.twitter.response;

/**
 * Created by paulhd on 4/8/16.
 */
public class TokenResponse {

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    private String token_type;
    private String access_token;
}
