package com.example.learnenglish_20.modal;

public class User {

    public String id;
    public String progress;

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

    public User(String id, String progress, String eMail) {
        this.id = id;
        this.progress = progress;
        this.eMail=eMail;
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
