package com.sport.betting.Controller;

import com.sport.betting.Repo.BetRepo;
import com.sport.betting.Repo.UserBetRepo;
import com.sport.betting.Repo.UserDetailsRepo;
import com.sport.betting.domain.Bet;
import com.sport.betting.domain.User;
import com.sport.betting.domain.UserBet;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/userBet")
public class UserBetController {

    private final UserBetRepo userBetRepo;
    private final UserDetailsRepo userDetailsRepo;
    private final BetRepo betRepo;

    @Autowired
    public UserBetController(UserBetRepo userBetRepo, UserDetailsRepo userDetailsRepo, BetRepo betRepo) {
        this.userBetRepo = userBetRepo;
        this.userDetailsRepo = userDetailsRepo;
        this.betRepo = betRepo;
    }

    @GetMapping
    public List<BetDto> userBetList(@RequestParam(required = false, name = "startDate")  Long startDate,
                                    @RequestParam(required = false, name = "endDate") Long endDate,
                                    @AuthenticationPrincipal User user) {
        Date startDateFilter = null;
        Date endDateFilter = null;
        if (startDate != null) {
            startDateFilter = new Date(startDate);
        }
        if (endDate != null) {
            endDateFilter = new Date(endDate);
        }

        return userBetRepo.findByFilterText(startDateFilter, endDateFilter);
    }


    @PostMapping
    public UserBet add(@RequestParam Bet bet, @RequestBody UserBet userBet, @AuthenticationPrincipal User user) {
        userBet.setBet(bet);
        userBet.setUser(user);
        userBetRepo.save(userBet);
        return userBet;
    }

    @PutMapping
    public UserBet edit(@RequestParam UserBet userBet, @RequestBody HashMap<String, String> body) {
        userBet.setSumm(Integer.parseInt(body.get("summ")));
        userBet.setOdd(Float.parseFloat(body.get("odd")));
        userBetRepo.save(userBet);
        return userBet;
    }


    @DeleteMapping()
    public void delete(@RequestParam UserBet userBet) {

        userBet.getUser().getUserBetList().remove(userBet);
        userBet.getBet().getUserBetList().remove(userBet);
        userBetRepo.delete(userBet);
    }
}
