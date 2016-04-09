package com.hackday.client.twitter;

import org.apache.commons.codec.binary.Base64;
import com.hackday.client.twitter.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("twitterClient")
@ComponentScan
public class TwitterClient {

    @Value("${twitter.api.key}")
    private String apiKey;

    @Value("${twitter.api.secret}")
    private String apiSecret;

    @Value("${twitter.api.tokenURL}")
    private String oauthURL;

    @Value("${twitter.api.timelineURL}")
    private String timelineURL;


    private String encodeKeys(String consumerKey, String consumerSecret) {
        try {
            String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
            String encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");

            String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
            byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());
            return new String(encodedBytes);
        }
        catch (UnsupportedEncodingException e) {
            return new String();
        }
    }


    private String getBearerToken() {
        String encodedKey = encodeKeys(apiKey, apiSecret);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Basic " + encodedKey);
        headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8"));

        HttpEntity<String> entity = new HttpEntity<String>("grant_type=client_credentials", headers);

        ResponseEntity<TokenResponse> tokenEntity = restTemplate.exchange(oauthURL, HttpMethod.POST, entity, TokenResponse.class);

        TokenResponse tokenResponse = tokenEntity.getBody();

        String accessToken = tokenResponse.getAccess_token();

        return accessToken;

    }

    private List<Tweet> getTweets(String bearerToken) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + bearerToken);

        HttpEntity<String> entity = new HttpEntity<String>("screen_name=kanyewest", headers);

        ResponseEntity<Tweet[]> tokenEntity = restTemplate.exchange(timelineURL + "?screen_name=kanyewest&since_id=715365723372175362&max_id=716380100820754432", HttpMethod.GET, entity, Tweet[].class);

        List<Tweet> tweets = new ArrayList<Tweet>(Arrays.asList(tokenEntity.getBody()));
        return tweets;

    }

    public List<Tweet> getTweets() {
        String bearerToken = getBearerToken();
        return getTweets(bearerToken);

    }

}
