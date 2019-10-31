package com.sport.betting.Service;

import com.sport.betting.Repo.BookmakerRepo;
import com.sport.betting.domain.Bookmaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmakerService {

    private final BookmakerRepo bookmakerRepo;

    @Autowired
    public BookmakerService(BookmakerRepo bookmakerRepo) {
        this.bookmakerRepo = bookmakerRepo;
    }


    public List<Bookmaker> findAll() {
        return bookmakerRepo.findAll();
    }
}
