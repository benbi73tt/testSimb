package ru.simbirsoft.Prictice.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NullPointer;
import ru.simbirsoft.Prictice.URLprocessing.*;
import ru.simbirsoft.Prictice.page.WebPage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PageListDAOImpl implements PageListDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public ParsReaderService parsReaderService(String url) throws IOException {
        InterfaceURL read = new Reader(url);
        InterfaceParser parse = new Parser();
        ParsReaderService service = new ParsReaderService(read, parse);
        service.convert();
        return service;
    }

    public boolean verify(String testURL) {
        try {
            URL url = new URL(testURL);
            URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            log.error("MalformedURLException e");
            return false;
        } catch (IOException e) {
            log.error("IOException e");
            return false;
        }
    }

    @Override
    public void addPage(WebPage page) throws IOException, NullPointer, InvalidURL {
        if (verify(page.getUrl())) {
            ParsReaderService service = parsReaderService(page.getUrl());
            page.setCountWord(service.getCountWords());
            page.setUniqueWord(service.getUniqueWords());
            page.setName(page.getUrl());
        } else{
            log.info("InvalidURL");
            throw new InvalidURL("Неверный URL адрес");
        }
        entityManager.persist(page);
    }

    @Override
    public WebPage infoPage(int id) {
        WebPage webPage = entityManager.find(WebPage.class, id);
        entityManager.detach(webPage);
        return webPage;
    }

    @Override
    public void deletePage(int id) {
        WebPage webPage = entityManager.find(WebPage.class, id);
        entityManager.remove(webPage);
    }

    @Override
    public List<String> repeatedWord(int id) throws IOException {
        WebPage webPage = entityManager.find(WebPage.class, id);
        ParsReaderService service = parsReaderService(webPage.getUrl());
        return service.getRepeatedWord();
    }

    @Override
    public List<String> uniqueWord(int id) throws IOException {
        WebPage webPage = entityManager.find(WebPage.class, id);
        ParsReaderService service = parsReaderService(webPage.getUrl());
        return service.getUniqueWord();
    }
}
