package com.sport.betting.Repo;

import com.sport.betting.domain.UserBet;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class UserBetRepoImpl implements UserBetRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BetDto> findByFilterText(Date startDate, Date endDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserBet> query = criteriaBuilder.createQuery(UserBet.class);
        Root<UserBet> UserBetRoot = query.from(UserBet.class);

        List<Predicate> predicates = new ArrayList<>();

        if (startDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(UserBetRoot.get("bet").get("game").get("date"), startDate));
        }
        if (endDate != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(UserBetRoot.get("bet").get("game").get("date"), endDate));
        }
        List<Order> orderList = new ArrayList();
        orderList.add(criteriaBuilder.desc(UserBetRoot.get("bet").get("game").get("date")));
        query.orderBy(orderList);

        query.select(UserBetRoot)
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        List<UserBet> resultList = entityManager.createQuery(query).getResultList();

        ArrayList<BetDto> betDtos = new ArrayList<>();
        for (UserBet userBet : resultList) {
            betDtos.add(new BetDto(
                    userBet.getId(),
                    userBet.getBet().getGame(),
                    userBet.getBet().getBookmaker(),
                    userBet.getBet().getEvent(),
                    userBet.getOdd(),
                    userBet.getBet().getAddTime(),
                    userBet.getBet().getRatio(),
                    userBet.getBet().getResult(),
                    userBet));
        }
        return betDtos;
    }
}
