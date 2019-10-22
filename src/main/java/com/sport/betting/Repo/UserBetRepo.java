package com.sport.betting.Repo;

import com.sport.betting.domain.UserBet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBetRepo extends JpaRepository<UserBet, Integer> {
}
