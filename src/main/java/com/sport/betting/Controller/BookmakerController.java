package com.sport.betting.Controller;

import com.sport.betting.Repo.BookmakerRepo;
import com.sport.betting.domain.Bookmaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookmaker")
public class BookmakerController {

    private final BookmakerRepo bookmakerRepo;

    @Autowired
    public BookmakerController(BookmakerRepo bookmakerRepo) {
        this.bookmakerRepo = bookmakerRepo;
    }

    @GetMapping
    public List<Bookmaker> bookmakerList() {
        return bookmakerRepo.findAll();
    }

}
