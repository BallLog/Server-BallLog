package com.ddoddo.balllog.balllog.util;

import com.ddoddo.balllog.balllog.dto.projection.UserMatchStats;
import org.springframework.stereotype.Component;

@Component
public class GameStatsUtils {

    public static Integer calculateWinRate(UserMatchStats stats) {
        long total = stats.totalGames();
        long wins  = stats.winCount();

        if (total == 0) {
            return 0;
        }

        double rawRate = (double) wins / total * 100;
        return (int) Math.round(rawRate);
    }
}
