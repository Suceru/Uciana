package com.birdshel.Uciana.Colonies;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonyFoodNeeded {
    public Colony colony;
    public int foodNeeded;

    public ColonyFoodNeeded(Colony colony) {
        this.colony = colony;
        this.foodNeeded = Math.abs(colony.getTotalFoodPerTurn());
    }
}
