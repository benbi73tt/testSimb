package ru.simbirsoft.Prictice.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NoEntityException;
import ru.simbirsoft.Prictice.Exception.NullPointer;
import ru.simbirsoft.Prictice.URLprocessing.*;
import ru.simbirsoft.Prictice.dao.repository.WebPageRepository;
import ru.simbirsoft.Prictice.page.WebPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Primary
@Component
public class PageListDAOImpl2 implements PageListDAO {

    @Autowired
    private WebPageRepository webPageRepository;

    @Override
    public void addPage(WebPage page) throws IOException, NullPointer, InvalidURL {
        if (verify(page.getUrl())) {
            ParsReaderService service = parsReaderService(page.getUrl());
            page.setCountWord(service.getCountWords());
            page.setUniqueWord(service.getUniqueWords());
            page.setName(page.getUrl());
        } else {
            log.info("InvalidURL");
            throw new InvalidURL("Неверный URL адрес");
        }
        webPageRepository.save(page);

    }

    @Override
    public WebPage infoPage(int id) {
        return webPageRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePage(int id) {
        webPageRepository.deleteById(id);
    }

    @Override
    public List<String> repeatedWord(int id) throws IOException {
        WebPage page = infoPage(id);
        ParsReaderService service = parsReaderService(page.getUrl());
        return service.getRepeatedWord();
    }

    @Override
    public List<String> uniqueWord(int id) throws IOException {
        WebPage page = infoPage(id);
        ParsReaderService service = parsReaderService(page.getUrl());
        return service.getUniqueWord();
    }

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
}
