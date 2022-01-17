package ru.simbirsoft.Prictice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NullPointer;
import ru.simbirsoft.Prictice.InterfaceParser;
import ru.simbirsoft.Prictice.InterfaceURL;
import ru.simbirsoft.Prictice.ListOfPages.PagesList;
import ru.simbirsoft.Prictice.Parser;
import ru.simbirsoft.Prictice.Reader;
import ru.simbirsoft.Prictice.page.WebPage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Service
public class ServicePageImpl implements ServicePage {

    @Autowired
    private PagesList pagesList;

    @PersistenceContext
    private EntityManager entityManager;

    public boolean verify(String testURL) {
        try {
            URL url = new URL(testURL);
            URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public PagesList getPage() {
        return pagesList;
    }

    @Override
    @Transactional
    public void addPage(WebPage page) throws IOException, NullPointer, InvalidURL {
        if (verify(page.getUrl())) {
            InterfaceURL read = new Reader(page.getUrl());
            InterfaceParser parse = new Parser();
            ru.simbirsoft.Prictice.Service service = new ru.simbirsoft.Prictice.Service(read, parse);
            service.convert();
            page.setCountWord(service.getCountWords());
            page.setUniqueWord(service.getUniqueWords());
            page.setName(page.getUrl());
        } else
            throw new InvalidURL("Неверный URL адрес");
        entityManager.persist(page);
    }

    @Override
    @Transactional
    public WebPage infoPage(int id) {
        WebPage webPage = entityManager.find(WebPage.class, id);
        entityManager.detach(webPage);
        return webPage;
    }

    @Override
    public void deletePlayer(int id) {
    }
}
