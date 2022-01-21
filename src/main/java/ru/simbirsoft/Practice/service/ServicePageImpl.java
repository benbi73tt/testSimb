package ru.simbirsoft.Practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbirsoft.Practice.Exception.InvalidURL;
import ru.simbirsoft.Practice.Exception.NullPointer;
import ru.simbirsoft.Practice.ListOfPages.PagesList;
import ru.simbirsoft.Practice.dao.PageListDAO;
import ru.simbirsoft.Practice.page.WebPage;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;


@Service
public class ServicePageImpl implements ServicePage {

    @Autowired
    private PagesList pagesList;


    @Autowired
    PageListDAO pageListDAO;


    @Override
    public PagesList getPage() {
        return pagesList;
    }

    @Override
    @Transactional
    public void addPage(WebPage page) throws IOException, NullPointer, InvalidURL {
        pageListDAO.addPage(page);
    }

    @Override
    @Transactional
    public WebPage infoPage(int id) {
        return pageListDAO.infoPage(id);
    }

    @Override
    @Transactional
    public void deletePage(int id) {
        pageListDAO.deletePage(id);
    }

    //todo В ТЗ не было указано для каких целей требуется полный список повторяющийхся слов,
    // поэтому, я не знал стоит ли добавлять "Листы" в бд.

    @Override
    public List<String> repeatedWord(int id) throws IOException {
        return pageListDAO.repeatedWord(id);
    }

    @Override
    public List<String> uniqueWord(int id) throws IOException {
        return pageListDAO.uniqueWord(id);
    }


}
