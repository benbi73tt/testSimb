package ru.simbirsoft.Prictice.service;

import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NullPointer;
import ru.simbirsoft.Prictice.ListOfPages.PagesList;
import ru.simbirsoft.Prictice.page.WebPage;

import java.io.IOException;
import java.util.List;

public interface ServicePage {
    PagesList getPage();
    void addPage(WebPage page) throws IOException, NullPointer, InvalidURL;
    WebPage infoPage(int id);
    void deletePlayer(int id);
    List<String> repeatedWord(int id) throws IOException;
    List<String> uniqueWord(int id) throws IOException;
}
