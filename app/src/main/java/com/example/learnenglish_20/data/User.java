package com.example.learnenglish_20.data;

public class User {

    public String id;
    public String progress;

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public boolean firstTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String eMail;

    public User(String id, String progress, String eMail, boolean firstTime) {
        this.id = id;
        this.progress = progress;
        this.eMail=eMail;
        this.firstTime=firstTime;
    }


    public User() {
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }


}
