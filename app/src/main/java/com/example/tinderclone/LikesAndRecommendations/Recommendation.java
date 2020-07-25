package com.example.tinderclone.LikesAndRecommendations;

public class Recommendation {
    private long id;
    private String nameAge;
    private String imageUrl;
    private String userMail;

    public Recommendation() { }

    public Recommendation(long id, String nameAge, String imageUrl, String userMail) {
        this.id = id;
        this.nameAge = nameAge;
        this.imageUrl = imageUrl;
        this.userMail = userMail;
    }

    public String getNameAge() {
        return nameAge;
    }
    public void setNameAge(String nameAge) {
        this.nameAge = nameAge;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserMail() {
        return userMail;
    }
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
