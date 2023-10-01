package com.birdshel.Uciana.Achievements;

import android.app.Activity;

import com.birdshel.Uciana.Game;

public class AttackAchievements extends BaseAchievements {

    public AttackAchievements(Game game, Activity activity) {
        super(game, activity);
    }

    public void d() {
        b(AchievementID.ASCENDED_SHIP_DESTROYED);
    }

    public void e() {
        b(AchievementID.KILL_OFF_AN_EMPIRE);
    }

    public void f() {
        b(AchievementID.USED_BIO_WEAPON);
    }
}
