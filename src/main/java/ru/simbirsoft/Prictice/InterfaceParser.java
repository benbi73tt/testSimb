package ru.simbirsoft.Prictice;

import ru.simbirsoft.Prictice.Exception.NullPointer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InterfaceParser {
    void splitter(String buffer) throws IOException;
    List<String> repeatedWords();
    List<Map.Entry<String, Integer>> uniqueWords();
    void countingWords();
    String textURL();
    int countUnique();
    int countWord() throws NullPointer;
}
