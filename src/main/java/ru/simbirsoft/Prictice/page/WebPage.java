package ru.simbirsoft.Prictice.page;

import java.util.List;

public class WebPage {
    private String name;
    private int countWord;
    private int uniqueWord;
    private List<String> word;


    public List<String> getWord() {
        return word;
    }

    public void setWord(List<String> word) {
        this.word = word;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountWord() {
        return countWord;
    }

    public void setCountWord(int countWord) {
        this.countWord = countWord;
    }



    public int getUniqueWord() {
        return uniqueWord;
    }

    public void setUniqueWord(int uniqueWord) {
        this.uniqueWord = uniqueWord;
    }



}
