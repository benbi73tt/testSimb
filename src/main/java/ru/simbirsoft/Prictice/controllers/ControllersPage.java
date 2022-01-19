package ru.simbirsoft.Prictice.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NullPointer;
import ru.simbirsoft.Prictice.ListOfPages.PagesList;
import ru.simbirsoft.Prictice.dto.PageDTO;
import ru.simbirsoft.Prictice.dto.PagesListDTO;
import ru.simbirsoft.Prictice.page.WebPage;
import ru.simbirsoft.Prictice.service.ServicePage;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/pages-list")
public class ControllersPage {

    @Autowired
    private ServicePage servicePage;

    @GetMapping("/page")
    public PagesList pageDTO() {
//        return BookDTO.from(servicePage.getPage());
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
        servicePage.deletePlayer(index);
        return ResponseEntity.ok().build();
    }

}
