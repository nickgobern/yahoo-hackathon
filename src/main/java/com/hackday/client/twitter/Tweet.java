package com.hackday.client.twitter;

/**
 * Created by paulhd on 4/8/16.
 */
public class Tweet {

    private String id_str;
    private String text;
    private TwitterUser user;

    public Boolean getPossibly_sensitive() {
        return possibly_sensitive;
    }

    public void setPossibly_sensitive(Boolean possibly_sensitive) {
        this.possibly_sensitive = possibly_sensitive;
    }

    public TwitterUser getUser() {
        return user;
    }

    public void setUser(TwitterUser user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    private Boolean possibly_sensitive;



    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tweet: ").append(getText()).append(" ")
                .append(getId_str()).append(" ")
                .append(user.getProfile_image_url());

        return sb.toString();
    }
}
