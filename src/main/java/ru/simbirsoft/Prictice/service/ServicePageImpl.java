package ru.simbirsoft.Prictice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NullPointer;
import ru.simbirsoft.Prictice.ListOfPages.PagesList;
import ru.simbirsoft.Prictice.URLprocessing.*;
import ru.simbirsoft.Prictice.dao.PageListDAO;
import ru.simbirsoft.Prictice.page.WebPage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
