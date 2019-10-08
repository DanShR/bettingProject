package com.sport.betting.Repo;

import com.sport.betting.domain.Bet;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BetRepo extends JpaRepository<Bet, Integer> {

    @Query("select new com.sport.betting.domain.dto.BetDto(b.id, b.game, b.bookmaker, b.event, b.odd, max(b.addTime), b.ratio, b.result) " +
            "from Bet b left join b.game g " +
            "where b.odd<=4 " +
            "group by g.id order by g.date desc")
    Page<BetDto> betList(Pageable page);

    List<Bet> findTop10ByGameStatus(int status);
}
