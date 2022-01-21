package ru.simbirsoft.Practice.URLprocessing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Reader implements InterfaceURL {
    private BufferedReader buffer;
    private String pageText;
    private URL urlURL;
    Document page;

    public Reader(String s) throws IOException {
        urlURL = new URL(s);
        page = Jsoup.parse(urlURL,3000);
        pageText = page.text();
    }

    @Override
    public BufferedReader buffer() throws IOException {
        buffer = new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(pageText.getBytes(StandardCharsets.UTF_8))));
        return buffer;
    }

    @Override
    public String stringPageURL() {
        return pageText;
    }
}
