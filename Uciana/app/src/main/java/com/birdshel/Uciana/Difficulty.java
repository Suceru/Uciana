package com.birdshel.Uciana;

import com.birdshel.Uciana.Resources.InfoIconEnum;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum Difficulty {
    EASIEST(4.0f, 4.0f, 6, InfoIconEnum.DESTROYER_ICON.ordinal(), R.string.difficulty_easiest),
    EASIER(3.0f, 3.0f, 4, InfoIconEnum.CRUISER_ICON.ordinal(), R.string.difficulty_easier),
    NORMAL(1.0f, 1.0f, 1, InfoIconEnum.BATTLESHIP_ICON.ordinal(), R.string.difficulty_normal),
    HARDER(0.75f, 0.75f, 0, InfoIconEnum.DREADNOUGHT_ICON.ordinal(), R.string.difficulty_harder),
    HARDEST(0.5f, 0.75f, 0, InfoIconEnum.STAR_BASE.ordinal(), R.string.difficulty_hardest);
    
    private final int infoIconIndex;
    private final int labelID;
    private final float productionModifier;
    private final float researchCostModifier;
    private final int turnsBetweenColonyShips;

    Difficulty(float f2, float f3, int i, int i2, int i3) {
        this.productionModifier = f2;
        this.researchCostModifier = f3;
        this.turnsBetweenColonyShips = i;
        this.infoIconIndex = i2;
        this.labelID = i3;
    }

    public int getInfoIconIndex() {
        return this.infoIconIndex;
    }

    public int getLabelID() {
        return this.labelID;
    }

    public float getProductionModifier() {
        return this.productionModifier;
    }

    public float getResearchCostModifier() {
        return this.researchCostModifier;
    }

    public int getTurnsBetweenColonyShips() {
        return this.turnsBetweenColonyShips;
    }
}
