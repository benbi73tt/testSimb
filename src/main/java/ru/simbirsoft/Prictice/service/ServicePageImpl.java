package ru.simbirsoft.Prictice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbirsoft.Prictice.*;
import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NullPointer;
import ru.simbirsoft.Prictice.ListOfPages.PagesList;
import ru.simbirsoft.Prictice.page.WebPage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Slf4j
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
            log.error("MalformedURLException e");
            return false;
        } catch (IOException e) {
            log.error("IOException e");
            return false;
        }
    }

    public ParsReaderService parsReaderService(String url) throws IOException {
        InterfaceURL read = new Reader(url);
        InterfaceParser parse = new Parser();
        ParsReaderService service = new ParsReaderService(read, parse);
        service.convert();
        return service;
    }

    @Override
    public PagesList getPage() {
        return pagesList;
    }

    @Override
    @Transactional
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
    @Transactional
    public WebPage infoPage(int id) {
        WebPage webPage = entityManager.find(WebPage.class, id);
        entityManager.detach(webPage);
        return webPage;
    }

    @Override
    @Transactional
    public void deletePlayer(int id) {
        WebPage webPage = entityManager.find(WebPage.class, id);
        entityManager.remove(webPage);
    }

    //todo В ТЗ не было указано для каких целей требуется полный список повторяющийхся слов,
    // поэтому, я не знал стоит ли добавлять "Листы" в бд.

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
