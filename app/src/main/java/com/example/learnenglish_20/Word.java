package com.example.learnenglish_20;

public class Word {
    public String id;
    public String english;
    public String russian;
    public String myId;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String picture;

    public Word(String id, String english, String russian, String myId) {
        this.id = id;
        this.english = english;
        this.russian = russian;
        this.myId = myId;
        picture = "URL";
    }

    public Word() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }
}
