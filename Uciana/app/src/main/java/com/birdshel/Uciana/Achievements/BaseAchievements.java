package com.birdshel.Uciana.Achievements;

import android.app.Activity;
import android.content.SharedPreferences;

import com.birdshel.Uciana.Game;
import com.google.android.gms.games.Games;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BaseAchievements {

    /* renamed from: a  reason: collision with root package name */
    protected Game game;
    private final SharedPreferences achievements;
    private final SharedPreferences.Editor achievementsEditor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseAchievements(Game game, Activity activity) {
        this.game = game;
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("achievements", 0);
        this.achievements = sharedPreferences;
        this.achievementsEditor = sharedPreferences.edit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(AchievementID achievementID) {
        if (this.game.getActivity().getGoogleApiClient().isConnected()) {
            Games.Achievements.unlock(this.game.getActivity().getGoogleApiClient(), achievementID.getID());
            this.achievementsEditor.putInt(achievementID.getID(), 1);
            this.achievementsEditor.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(AchievementID achievementID) {
        if (c(achievementID)) {
            return;
        }
        a(achievementID);
    }

    public boolean c(AchievementID achievementID) {//    public boolean c(AchievementID achievementID)
        return this.achievements.getInt(achievementID.getID(), 0) == 1;
    }
}
