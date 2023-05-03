package com.birdshel.Uciana.Players;

import com.birdshel.Uciana.GameData;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Migrants {
    private final int orbit;
    private int populationCount;
    private final int systemID;
    private int turns;

    public Migrants(int i, int i2, int i3, int i4) {
        this.systemID = i;
        this.orbit = i2;
        this.populationCount = i3;
        this.turns = i4;
    }

    private void done(int i) {
        if (GameData.colonies.isColony(this.systemID, this.orbit) && GameData.colonies.getColony(this.systemID, this.orbit).getPlanet().getOccupier() == i) {
            GameData.colonies.getColony(this.systemID, this.orbit).addPopulation(this.populationCount);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        this.populationCount += i;
    }

    public int getOrbit() {
        return this.orbit;
    }

    public int getPopulationCount() {
        return this.populationCount;
    }

    public int getSystemID() {
        return this.systemID;
    }

    public int getTurns() {
        return this.turns;
    }

    public boolean update(int i) {
        int i2 = this.turns - 1;
        this.turns = i2;
        if (i2 == 0) {
            done(i);
            return true;
        }
        return false;
    }
}
