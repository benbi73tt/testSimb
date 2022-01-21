package ru.simbirsoft.Practice.dao;

import ru.simbirsoft.Practice.Exception.InvalidURL;
import ru.simbirsoft.Practice.Exception.NullPointer;
import ru.simbirsoft.Practice.page.WebPage;

import java.io.IOException;
import java.util.List;

public interface PageListDAO {
    void addPage(WebPage page) throws IOException, NullPointer, InvalidURL;
    WebPage infoPage(int id) ;
    void deletePage(int id);
    List<String> repeatedWord(int id) throws IOException;
    List<String> uniqueWord(int id) throws IOException;
}
