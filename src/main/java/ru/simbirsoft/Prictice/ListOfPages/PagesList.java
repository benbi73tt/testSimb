package ru.simbirsoft.Prictice.ListOfPages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.simbirsoft.Prictice.page.WebPage;

import java.util.ArrayList;
import java.util.List;

@Component("PageComponent")
public class PagesList {
    private List<WebPage> webPages;

    @Value("WebSite")
    private String name;

    public PagesList(String nameList) {
        this.name = nameList;
        this.webPages = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "List pages {" +
                "name" + name + '\'' +
                '}';
    }

    public List<WebPage> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<WebPage> webPages) {
        this.webPages = webPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
