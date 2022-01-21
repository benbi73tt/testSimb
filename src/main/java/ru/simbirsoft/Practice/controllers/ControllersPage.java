package ru.simbirsoft.Practice.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.Practice.Exception.InvalidURL;
import ru.simbirsoft.Practice.Exception.NullPointer;
import ru.simbirsoft.Practice.ListOfPages.PagesList;
import ru.simbirsoft.Practice.dto.PageDTO;
import ru.simbirsoft.Practice.dto.PagesListDTO;
import ru.simbirsoft.Practice.page.WebPage;
import ru.simbirsoft.Practice.service.ServicePage;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/pages-list")
public class ControllersPage {

    @Autowired
    private ServicePage servicePage;

    @GetMapping("/page")
    public PagesList pageDTO() {
        return PagesListDTO.from(servicePage.getPage());
    }

    @PostMapping("/page")
    public ResponseEntity addPage(@RequestBody WebPage page) throws NullPointer, InvalidURL, IOException {
        log.info("add page");
        servicePage.addPage(page);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page/{index}")
    public ResponseEntity getPageByIndex(@PathVariable int index) {
        log.info("get pages by index " + index);
        try {
//            return ResponseEntity.ok(servicePage.infoPage(index));
            PageDTO dto = PageDTO.from(servicePage.infoPage(index));
            return ResponseEntity.ok(dto);
        } catch (IndexOutOfBoundsException e) {
            log.error("IndexOutOfBoundsException e");
            return ResponseEntity.notFound().build();
        }
    }

    //переделать на другую бд
    @GetMapping("/unique-word/{index}")
    public ResponseEntity getUniqueWordByIndex(@PathVariable int index) throws IOException {
        log.info("get unique word by index " + index);
        return ResponseEntity.ok(servicePage.uniqueWord(index));
    }

    @GetMapping("/repeated-word/{index}")
    public ResponseEntity getRepeatedWordByIndex(@PathVariable int index) throws IOException {
        log.info("get unique word by index " + index);
        return ResponseEntity.ok(servicePage.repeatedWord(index));
    }

    @DeleteMapping("/page/{index}")
    public ResponseEntity deletePlayerByIndex(@PathVariable int index) {
        log.info("delete player by index " + index);
        servicePage.deletePage(index);
        return ResponseEntity.ok().build();
    }

}
