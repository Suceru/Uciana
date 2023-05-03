package com.birdshel.Uciana.Resources;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum TutorialID {
    PLANET_LIST("planetListTutorial"),
    RESEARCH("researchTutorial"),
    SYSTEM("systemTutorial"),
    TURN("turnButtonTutorial"),
    FLEET_CONTROL("fleetControlTutorial"),
    GALAXY("galaxyTutorial"),
    GALAXY_BUTTONS("galaxyButtonsTutorial"),
    SYSTEM_PEEK("systemPeek"),
    COLONY("colonyTutorial"),
    FLEET_MAINTENANCE("fleetMaintenance"),
    BATTLE_GRID("battleGrid"),
    FIRST_TIME("firstTime"),
    HUGE_MAP("hugeMap"),
    COLONY_SHIP_ARRIVED("colonyShipArrived");
    
    private final String key;

    TutorialID(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }
}
