package ru.simbirsoft.Prictice;

import ru.simbirsoft.Prictice.Exception.NullPointer;

import java.io.IOException;
import java.util.List;

public class Service {
    private InterfaceURL url;
    private InterfaceParser textParse;

    public Service(InterfaceURL read, InterfaceParser parse){
        this.url = read;
        this.textParse = parse;
    }

    public void convert() throws IOException {
        String text = url.stringPageURL();
        textParse.splitter(text);
    }

    public void getResult() throws NullPointer {
        System.out.println("Полный текст: " + textParse.textURL());
        System.out.println("Какие слова повторяются: " + textParse.repeatedWords());
        System.out.println("Количество слов в файле: " + textParse.countWord());
        System.out.println("Какие слова уникальные: " + textParse.uniqueWords()
                + "\n Количество уникальных слов: "+ textParse.countUnique());
    }
    public int getCountWords() throws NullPointer {
        return textParse.countWord();
    }

    public int getUniqueWords() {
        textParse.uniqueWords();
        return textParse.countUnique();
    }

    public List<String> getRepeatedWord(){
        return textParse.repeatedWords();
    }
    public List<String> getUniqueWord(){
        return textParse.uniqueWords();
    }
}
