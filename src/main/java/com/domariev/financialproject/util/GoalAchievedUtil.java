package com.domariev.financialproject.util;

import com.domariev.financialproject.model.Contribution;
import com.domariev.financialproject.model.MoneyJar;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class GoalAchievedUtil {

    public static void checkGoalAchievement(MoneyJar moneyJar) {
        BigDecimal jarBalanceSum = moneyJar.getJarBalance().stream().map(Contribution::getAmounting).reduce(BigDecimal.ZERO, BigDecimal::add);
        Integer compare = jarBalanceSum.compareTo(moneyJar.getGoal());
        moneyJar.setAchievedGoal(compare > 0 || compare.equals(0));
        log.info("checked goal achievement - " + moneyJar.getAchievedGoal());
    }
}
