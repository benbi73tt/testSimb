package ru.simbirsoft.Prictice.page;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class WebPage {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Name should not be empty")//нельзя пустое значение(Ошибка)
    @Size(min = 6, max = 100, message = "Name should be between 2 and 12 characters")
    @Column(name = "name")
    private String name;

    @URL
    @Column(name = "url")
    private String url;


    @Column(name = "countWord")
    private int countWord;

    @Column(name = "uniqueWord")
    private int uniqueWord;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name
                .replaceAll("http://|https://|www.|ws://|wss://","")
                .replaceAll("/.*","")
                .split(".com")[0];
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
