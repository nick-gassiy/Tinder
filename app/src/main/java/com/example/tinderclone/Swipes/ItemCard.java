package com.example.tinderclone.Swipes;

public class ItemCard {
    private int userId;
    private String image;
    private String name, age, about;

    public ItemCard(){}

    public ItemCard(String image, String name, String age, String about, int userId) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.about = about;
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
