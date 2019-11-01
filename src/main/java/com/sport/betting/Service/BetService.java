package com.sport.betting.Service;

import com.sport.betting.Repo.BetRepo;
import com.sport.betting.Utils.CalendarUtils;
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

    public List<BetDto> betListByPerid(Date startDate, Date endDate) {
        return betRepo.betListByPerid(startDate, endDate);
    }

    public List<BetStatisticsByPeriod> betStatisticsByDay(float defaultSumm) {
        return calculateStatisticsByPeriod(Calendar.DAY_OF_YEAR, defaultSumm);
    }

    public List<BetStatisticsByPeriod> betStatisticsByWeek(float defaultSumm) {
        return calculateStatisticsByPeriod(Calendar.WEEK_OF_YEAR, defaultSumm);
    }

    public List<BetStatisticsByPeriod> betStatisticsByMonth(float defaultSumm) {
        return calculateStatisticsByPeriod(Calendar.MONTH, defaultSumm);
    }

    private List<BetStatisticsByPeriod> calculateStatisticsByPeriod(int periodType, float defaultSumm) {
        List<Bet> bets = betRepo.betListWithoutDuplicateGames();
        HashMap<Integer, HashMap<Integer, List<Bet>>> betsByPerid = new HashMap<>();
        List<BetStatisticsByPeriod> statistics = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Bet bet : bets) {
            calendar.setTime(new Date(bet.getGame().getDate()));
            int year = calendar.get(Calendar.YEAR);
            int numberOfDateType = calendar.get(periodType);

            if (betsByPerid.get(year) == null) {
                List<Bet> betList = new ArrayList<>();
                betList.add(bet);
                HashMap<Integer, List<Bet>> byNumberMap = new HashMap<>();
                byNumberMap.put(numberOfDateType, betList);
                betsByPerid.put(year, byNumberMap);
            } else {
                HashMap<Integer, List<Bet>> byNumberMap = betsByPerid.get(year);
                if (byNumberMap.get(numberOfDateType) == null) {
                    List<Bet> betList = new ArrayList<>();
                    betList.add(bet);
                    byNumberMap.put(numberOfDateType, betList);
                } else {
                    List<Bet> betList = byNumberMap.get(numberOfDateType);
                    betList.add(bet);
                }
            }
        }

        for(Map.Entry<Integer, HashMap<Integer, List<Bet>>> yearEntry: betsByPerid.entrySet()) {
            int year = yearEntry.getKey();
            HashMap<Integer, List<Bet>> numbersMap = yearEntry.getValue();
            for(Map.Entry<Integer, List<Bet>> numberEntry : numbersMap.entrySet()) {
                int number = numberEntry.getKey();
                List<Bet> betList = numberEntry.getValue();
                int profitSumm = 0;
                int winQuantity = 0;
                int loseQuantity = 0;
                for (Bet bet : betList) {
                    if (bet.getResult() == 1) {
                        winQuantity++;
                        profitSumm += defaultSumm * bet.getOdd() - defaultSumm;
                    } else {
                        loseQuantity++;
                        profitSumm += -1 * defaultSumm;
                    }
                }

                long startDate = 0;
                long endDate = 0;
                if (periodType == Calendar.WEEK_OF_YEAR) {
                    startDate = CalendarUtils.startOfWeekOfYear(year, number);
                    endDate = CalendarUtils.endOfWeekOfYear(year, number);
                } else if (periodType == Calendar.DAY_OF_YEAR) {
                    startDate = CalendarUtils.startOfDayOfYear(year, number);
                    endDate = CalendarUtils.endOfDayOfYear(year, number);
                } else if (periodType == Calendar.MONTH) {
                    startDate = CalendarUtils.startOfMonthOfYear(year, number);
                    endDate = CalendarUtils.endOfMonthOfYear(year, number);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(year).append("_").append(number);
                String key = stringBuilder.toString();
                statistics.add(new BetStatisticsByPeriod(key, startDate, endDate, profitSumm, winQuantity, loseQuantity));
            }
        }

        return statistics;
    }
}
