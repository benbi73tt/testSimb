package ru.simbirsoft.Practice.dto;


import lombok.Data;
import ru.simbirsoft.Practice.ListOfPages.PagesList;
import ru.simbirsoft.Practice.page.WebPage;

import java.util.List;

@Data
public class PagesListDTO {
    private String name;
    private List<WebPage> webPage;

    public static PagesList from(PagesList pagesList) {
        PagesListDTO pagesListDTO = new PagesListDTO();
        pagesListDTO.setName(pagesList.getName());
        pagesListDTO.setWebPage(pagesList.getWebPages());
        return pagesList;
    }

}
