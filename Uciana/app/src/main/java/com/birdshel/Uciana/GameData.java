package com.birdshel.Uciana;

import android.app.Activity;
import com.birdshel.Uciana.Colonies.Colonies;
import com.birdshel.Uciana.Colonies.Outposts;
import com.birdshel.Uciana.Events.Events;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.RandomEvents.RandomEvents;
import com.birdshel.Uciana.Ships.Fleets;
import com.birdshel.Uciana.StarSystems.Galaxy;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameData {

    /* renamed from: a */
    static int f1367a = 0;
    public static Activity activity = null;
    public static Colonies colonies = null;
    public static Empires empires = null;
    public static Events events = null;
    public static Fleets fleets = null;
    public static Galaxy galaxy = null;
    public static GameSettings gameSettings = null;
    public static Outposts outposts = null;
    public static RandomEvents randomEvents = null;
    public static boolean showTurnScene = false;
    private static int techCostVersion;
    public static int turn;

    public static boolean allEmpiresAllied() {
        for (Empire empire : empires.getEmpires()) {
            if (empire.isAlive() && empire.getType() != EmpireType.NONE) {
                for (Empire empire2 : empires.getEmpires()) {
                    if (empire2.isAlive() && empire.id != empire2.id && empire.getType() != EmpireType.NONE && !empire.hasAllianceWith(empire2.id)) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    public static boolean allEmpiresStillAlive() {
        for (Empire empire : empires.getEmpires()) {
            if (empire.getType() != EmpireType.NONE && !empire.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public static Empire getCurrentEmpire() {
        return empires.get(f1367a);
    }

    public static int getCurrentPlayer() {
        return f1367a;
    }

    public static int getTechCostVersion() {
        return techCostVersion;
    }

    public static boolean hasCoalitionVictoryBeenAchieved() {
        return !allEmpiresStillAlive() && allEmpiresAllied();
    }

    public static void setTechCostVersion(int i) {
        techCostVersion = i;
    }
}
