package com.sport.betting.Repo;

import com.sport.betting.domain.Bet;
import com.sport.betting.domain.User;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BetRepo extends JpaRepository<Bet, Integer> {

    @Query("select new com.sport.betting.domain.dto.BetDto(b.id, b.game, b.bookmaker, b.event, b.odd, max(b.addTime), b.ratio, b.result, ubl) " +
            "from Bet b left join b.game g " +
            "left join b.userBetList ubl " +
            "where b.event=1 and b.odd between 2 and 2.5 and b.ratio >= 1.001 and (ubl.user=:user or ubl.user is null)"   +
            "group by g.id, b.bookmaker,ubl.user,ubl.bet order by g.date desc")
    Page<BetDto> betList(Pageable page, User user);

    List<Bet> findTop10ByGameStatus(int status);
}
