package com.sport.betting.Repo;

import com.sport.betting.domain.Bookmaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmakerRepo extends JpaRepository<Bookmaker, Integer> {
}
