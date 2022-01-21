package ru.simbirsoft.Practice.URLprocessing;

import ru.simbirsoft.Practice.Exception.NullPointer;

import java.io.IOException;
import java.util.List;

public interface InterfaceParser {
    void splitter(String buffer) throws IOException;
    List<String> repeatedWords();
    List<String> uniqueWords();
    void countingWords();
    String textURL();
    int countUnique();
    int countWord() throws NullPointer;
}
