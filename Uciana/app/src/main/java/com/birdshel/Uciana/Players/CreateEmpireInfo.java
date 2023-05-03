package com.birdshel.Uciana.Players;

import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class CreateEmpireInfo {
    private int bannerID;
    private EmpireType empireType;
    private List<RaceAttribute> raceAttributes;
    private int raceID;
    private int shipStyleID;

    public int getBannerID() {
        return this.bannerID;
    }

    public EmpireType getEmpireType() {
        return this.empireType;
    }

    public List<RaceAttribute> getRaceAttributes() {
        return this.raceAttributes;
    }

    public int getRaceID() {
        return this.raceID;
    }

    public int getShipStyleID() {
        return this.shipStyleID;
    }

    public void setBannerID(int i) {
        this.bannerID = i;
    }

    public void setEmpireType(EmpireType empireType) {
        this.empireType = empireType;
    }

    public void setRaceAttributes(List<RaceAttribute> list) {
        this.raceAttributes = list;
    }

    public void setRaceID(int i) {
        this.raceID = i;
    }

    public void setShipStyleID(int i) {
        this.shipStyleID = i;
    }
}
