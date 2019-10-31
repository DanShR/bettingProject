package com.sport.betting.Controller;

import com.sport.betting.Service.BookmakerService;
import com.sport.betting.domain.Bookmaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookmaker")
public class BookmakerController {

    private final BookmakerService bookmakerService;

    @Autowired
    public BookmakerController(BookmakerService bookmakerService) {
        this.bookmakerService = bookmakerService;
    }

    @GetMapping
    public List<Bookmaker> bookmakerList() {
        return bookmakerService.findAll();
    }

}
