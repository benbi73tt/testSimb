package ru.simbirsoft.Prictice.dao;

import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NoEntityException;
import ru.simbirsoft.Prictice.Exception.NullPointer;
import ru.simbirsoft.Prictice.page.WebPage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PageListDAO {
    void addPage(WebPage page) throws IOException, NullPointer, InvalidURL;
    WebPage infoPage(int id) ;
    void deletePage(int id);
    List<String> repeatedWord(int id) throws IOException;
    List<String> uniqueWord(int id) throws IOException;
}
