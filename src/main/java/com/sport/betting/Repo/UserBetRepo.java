package com.sport.betting.Repo;

import com.sport.betting.domain.User;
import com.sport.betting.domain.UserBet;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserBetRepo extends JpaRepository<UserBet, Integer>, UserBetRepoCustom {
    @Query("select new com.sport.betting.domain.dto.BetDto(bet.id, bet.game, bet.bookmaker, bet.event, bet.odd, bet.addTime, bet.ratio, bet.result, userBet) " +
            "from UserBet userBet left join userBet.bet bet " +
            "where userBet.user = :user " +
            "order by userBet.id desc")
    List<BetDto> findUserBets(User user);
}
