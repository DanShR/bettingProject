package com.sport.betting.Controller;

import com.sport.betting.Repo.BetRepo;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/bet")
public class BetController {

    private BetRepo betRepo;

    @Autowired
    EntityManager em;

    @Autowired
    public BetController(BetRepo betRepo) {
        this.betRepo = betRepo;
    }

    @GetMapping
    public Page<BetDto> bet(@RequestParam(value = "perPage", defaultValue = "10") int perPage,
                            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        PageRequest pageR = new PageRequest(currentPage - 1,perPage);
        Page<BetDto> betPage = betRepo.betList(pageR);
        return betPage;
    }
}
