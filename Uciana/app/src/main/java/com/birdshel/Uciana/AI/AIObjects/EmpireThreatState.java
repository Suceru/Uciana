package com.birdshel.Uciana.AI.AIObjects;

import com.birdshel.Uciana.Players.Empire;

public enum EmpireThreatState {
    NO_CONTACT,
    PEACE,
    WAR;

    public static EmpireThreatState getState(Empire empire) {
        if (empire.isAtWar()) {
            return WAR;
        }
        if (empire.getKnownEmpires().isEmpty()) {
            return NO_CONTACT;
        }
        return PEACE;
    }
}
