package com.birdshel.Uciana.Achievements;

import android.app.Activity;
import com.birdshel.Uciana.Game;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AttackAchievements extends BaseAchievements {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AttackAchievements(Game game, Activity activity) {
        super(game, activity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        b(AchievementID.ASCENDED_SHIP_DESTROYED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        b(AchievementID.KILL_OFF_AN_EMPIRE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        b(AchievementID.USED_BIO_WEAPON);
    }
}
