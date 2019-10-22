package com.sport.betting.Controller;

import com.sport.betting.Repo.BetRepo;
import com.sport.betting.Repo.UserBetRepo;
import com.sport.betting.Repo.UserDetailsRepo;
import com.sport.betting.domain.Bet;
import com.sport.betting.domain.User;
import com.sport.betting.domain.UserBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
