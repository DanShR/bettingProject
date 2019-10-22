package com.sport.betting.Service;

import com.sport.betting.Repo.BetRepo;
import com.sport.betting.domain.User;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    private final BetRepo betRepo;

    @Autowired
    public BetService(BetRepo betRepo) {
        this.betRepo = betRepo;
    }

    public Page<BetDto> betList(Pageable page, User user) {
        return betRepo.betList(page, user);
    }
}
