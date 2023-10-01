package com.birdshel.Uciana.Achievements;

import android.app.Activity;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipType;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MiscAchievements extends BaseAchievements {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MiscAchievements(Game game, Activity activity) {
        super(game, activity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(Fleet fleet) {
        if (c(AchievementID.ALL_SHIP_TYPES_IN_A_FLEET)) {
            return;
        }
        ShipType[] shipTypeArr = {ShipType.SCOUT, ShipType.COLONY, ShipType.CONSTRUCTION, ShipType.TRANSPORT, ShipType.DESTROYER, ShipType.CRUISER, ShipType.BATTLESHIP, ShipType.DREADNOUGHT};
        int[] countOfEachType = fleet.getCountOfEachType(true);
        for (int i = 0; i < 8; i++) {
            if (countOfEachType[shipTypeArr[i].id] == 0) {
                return;
            }
        }
        a(AchievementID.ALL_SHIP_TYPES_IN_A_FLEET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(SystemObject systemObject) {
        if (systemObject.getSystemObjectType() == SystemObjectType.ASTEROID_BELT) {
            b(AchievementID.MINING_OUTPOST);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(int i) {
        int wormholeCount = this.game.galaxy.getStarSystem(i).getWormholeCount();
        if (wormholeCount > 1) {
            b(AchievementID.TWO_WORMHOLES_IN_SYSTEM);
        }
        if (wormholeCount > 2) {
            b(AchievementID.THREE_WORMHOLES_IN_SYSTEM);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(Treaty treaty) {
        if (treaty == Treaty.ALLIANCE) {
            b(AchievementID.FORM_AN_ALLIANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        b(AchievementID.GAME_OVER);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        b(AchievementID.WORMHOLE_TRAVEL);
    }
}
