package com.sport.betting.Controller;

import com.sport.betting.Service.UserBetService;
import com.sport.betting.domain.Bet;
import com.sport.betting.domain.Bookmaker;
import com.sport.betting.domain.User;
import com.sport.betting.domain.UserBet;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/userBet")
public class UserBetController {

    private final UserBetService userBetService;

    @Autowired
    public UserBetController(UserBetService userBetService) {
        this.userBetService = userBetService;
    }

    @GetMapping
    public List<BetDto> userBetList(@RequestParam(required = false, name = "startDate")  Long startDate,
                                    @RequestParam(required = false, name = "endDate") Long endDate,
                                    @RequestParam(required = false, name = "bookmaker") List<Bookmaker> bookmakerList,
                                    @AuthenticationPrincipal User user) {
        Date startDateFilter = null;
        Date endDateFilter = null;
        if (startDate != null) {
            startDateFilter = new Date(startDate);
        }
        if (endDate != null) {
            endDateFilter = new Date(endDate);
        }

        return userBetService.findByFilterText(startDateFilter, endDateFilter, bookmakerList);
    }


    @PostMapping
    public UserBet add(@RequestParam Bet bet, @RequestBody UserBet userBet, @AuthenticationPrincipal User user) {
        userBet.setBet(bet);
        userBet.setUser(user);
        userBetService.save(userBet);
        return userBet;
    }

    @PutMapping
    public UserBet edit(@RequestParam UserBet userBet, @RequestBody HashMap<String, String> body) {
        userBet.setSumm(Integer.parseInt(body.get("summ")));
        userBet.setOdd(Float.parseFloat(body.get("odd")));
        userBetService.save(userBet);
        return userBet;
    }


    @DeleteMapping()
    public void delete(@RequestParam UserBet userBet) {

        userBet.getUser().getUserBetList().remove(userBet);
        userBet.getBet().getUserBetList().remove(userBet);
        userBetService.delete(userBet);
    }
}
