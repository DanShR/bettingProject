package com.sport.betting.Service;

import com.sport.betting.Repo.BetRepo;
import com.sport.betting.domain.Bet;
import com.sport.betting.domain.User;
import com.sport.betting.domain.dto.BetDto;
import com.sport.betting.domain.dto.BetStatisticsByPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<BetStatisticsByPeriod> betStatisticsByWeek(float defaultSumm) {
        HashMap<Integer, Float> statMap = new HashMap<>();
        List<Bet> bets = betRepo.betListWithoutDuplicateGames();
        Calendar calendar = Calendar.getInstance();
        for (Bet bet : bets) {
            calendar.setTime(new Date(bet.getGame().getDate()));
            int week = calendar.get(Calendar.WEEK_OF_YEAR);
            float betSumm;
            if (bet.getResult() == 1) {
                betSumm = defaultSumm * bet.getOdd() - defaultSumm;
            } else {
                betSumm = -1 * defaultSumm;
            }
            if (statMap.get(week) == null) {
                statMap.put(week, betSumm);
            } else {
                statMap.replace(week, statMap.get(week) + betSumm);
            }
        }

        List<BetStatisticsByPeriod> statistics = new ArrayList<>();
        for (Map.Entry<Integer, Float> entry: statMap.entrySet()) {
            calendar.set(Calendar.WEEK_OF_YEAR, entry.getKey());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            long startDate = calendar.getTime().getTime();
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            long endDate = calendar.getTime().getTime();
            statistics.add(new BetStatisticsByPeriod(entry.getKey(), startDate, endDate, entry.getValue()));
        }

        return statistics;
    }

    public List<BetDto> betListByPerid(Date startDate, Date endDate) {
        return betRepo.betListByPerid(startDate, endDate);
    }
}
