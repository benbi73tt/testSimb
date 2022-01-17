package ru.simbirsoft.Prictice.dto;

import lombok.Data;
import ru.simbirsoft.Prictice.page.WebPage;

@Data
public class PageDTO {
    private int id;
    private String name;
    private int uniqueWord;
    private int countWord;

    public static PageDTO from(WebPage webPage){
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCountWord(webPage.getCountWord());
//        pageDTO.setId(webPage.getId());
        pageDTO.setName(webPage.getName());
        pageDTO.setUniqueWord(webPage.getUniqueWord());
        return pageDTO;
    }




}
