package com.example.learnenglish_20;

public class WordPair {

    private String englishWord;
    private String russianWord;

    public WordPair(String englishWord, String russianWord) {
        this.englishWord = englishWord;
        this.russianWord = russianWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getRussianWord() {
        return russianWord;
    }

    public void setRussianWord(String russianWord) {
        this.russianWord = russianWord;
    }
}
