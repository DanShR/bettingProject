package com.sport.betting.Service;

import com.sport.betting.Repo.UserBetRepo;
import com.sport.betting.domain.Bookmaker;
import com.sport.betting.domain.UserBet;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserBetService {

    private final UserBetRepo userBetRepo;

    @Autowired
    public UserBetService(UserBetRepo userBetRepo) {
        this.userBetRepo = userBetRepo;
    }

    public List<BetDto> findByFilterText(Date startDateFilter, Date endDateFilter, List<Bookmaker> bookmakerList) {
        return userBetRepo.findByFilterText(startDateFilter, endDateFilter, bookmakerList);
    }

    public void save(UserBet userBet) {
        userBetRepo.save(userBet);
    }

    public void delete(UserBet userBet) {
        userBetRepo.delete(userBet);
    }
}
