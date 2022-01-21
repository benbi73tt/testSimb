package ru.simbirsoft.Practice.URLprocessing;

import ru.simbirsoft.Practice.Exception.NullPointer;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Parser implements InterfaceParser {
    private String[] arr;
    private String textURL;
    private int countUnique = 0;
    public List<String> stringWebPage = new ArrayList<>();
    public Map<String, Integer> counter = new HashMap<>();


    @Override
    public void splitter(String buffer) throws IOException {
        textURL = buffer.replaceAll("(?U)[\\pP\\s]", " ");
        arr = textURL.split(" ");
        Collections.addAll(stringWebPage, arr);
    }

    @Override
    public List<String> repeatedWords() {
        if (counter.size() == 0) countingWords();
        return counter.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()
                .reversed()).map(it -> {
            return it.getKey() + " = " + it.getValue();
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> uniqueWords() {
        if (counter.size() == 0) countingWords();
        return counter.entrySet().stream().filter((it) -> {
            if(it.getValue()==1) countUnique++;
            return it.getValue() == 1;
        }).map(it -> {
            return it.getKey();
        }).collect(Collectors.toList());
    }

    @Override
    public void countingWords() {
        for (String x : stringWebPage) {
            int newValue = counter.getOrDefault(x, 0) + 1;
            counter.put(x, newValue);
        }
    }

    @Override
    public String textURL() {
        return textURL;
    }


    @Override
    public int countWord() throws NullPointer {
        if (arr == null) {
            throw new NullPointer("Нет входных данных");
        }
        return arr.length;
    }

    @Override
    public int countUnique() {
        return countUnique;
    }

}
