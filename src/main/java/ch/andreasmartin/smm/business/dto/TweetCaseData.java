package ch.andreasmartin.smm.business.dto;

import ch.andreasmartin.smm.data.domain.Tweet;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by andreas.martin on 17.04.2017.
 */
public class TweetCaseData implements Serializable {
    private Long tweetCaseId;
    private String tweetText;
    private Date tweetDate;
    private String content;
    private String personName;
    private String email;
    private String projectName;


    public TweetCaseData() {}

    public TweetCaseData(Tweet tweet){
        this.tweetCaseId = tweet.getId();
        this.tweetText = tweet.getTweetText();
        this.tweetDate = tweet.getTweetDate();
        this.content = tweet.getEmailContent();
        this.personName = tweet.getAuthor().getPersonName();
        this.email = tweet.getAuthor().getEmail();
        this.projectName = tweet.getProject().getProjectName();
    }

    public Long getTweetCaseId() { return tweetCaseId; }

    public void setTweetCaseId(Long tweetCaseId) { this.tweetCaseId = tweetCaseId; }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public Date getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(Date tweetDate) {
        this.tweetDate = tweetDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
