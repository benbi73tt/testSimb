package ru.simbirsoft.Practice.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebPage {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Name should not be empty")//нельзя пустое значение(Ошибка)
    @Size(min = 6, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "name")
    private String name;

    @URL
    @Column(name = "url")
    private String url;


    @Column(name = "countWord")
    private int countWord;

    @Column(name = "uniqueWord")
    private int uniqueWord;


    public void setName(String name) {
        this.name = name
                .replaceAll("http://|https://|www.|ws://|wss://","")
                .replaceAll("/.*","")
                .split(".com")[0];
    }
}
