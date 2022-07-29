package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*
 * POJO for an Id object
 */
@JsonPropertyOrder({"userId", "name", "github"})
public class Id {
    @JsonProperty("userid")
    private String userId = "";
    @JsonProperty("name")
    private String name = "";
    @JsonProperty("github")
    private String github = "";


    public Id (String name, String githubId) {
        this.name = name;
        this.github = githubId;
    }

    public Id(String githubId) {
        this.github = githubId;
    }
    public Id() {

    }

    @JsonProperty("userid")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userid")
    public void setUserId(String uid) {
        this.userId = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.github + ") ";
    }
}