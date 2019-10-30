package com.sport.betting.Repo;

import com.sport.betting.domain.Bookmaker;
import com.sport.betting.domain.dto.BetDto;

import java.util.Date;
import java.util.List;

public interface UserBetRepoCustom {
    List<BetDto> findByFilterText(Date startDate, Date endDate, List<Bookmaker> bookmakerList);
}
