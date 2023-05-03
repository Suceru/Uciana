package com.birdshel.Uciana.AI.Managers;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Players.Empire;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EconomicAI {
    private final Empire empire;

    public EconomicAI(Empire empire) {
        this.empire = empire;
    }

    private void adjustTaxRate() {
        float[] fArr = {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f};
        int taxRate = ((int) this.empire.getTaxRate()) * 10;
        if (this.empire.getCreditsPerTurn() < 0) {
            while (this.empire.getCreditsPerTurn() < 0) {
                taxRate++;
                if (taxRate == 6) {
                    this.empire.setTaxRate(0.5f);
                    return;
                }
                this.empire.setTaxRate(fArr[taxRate]);
            }
            return;
        }
        while (true) {
            if (this.empire.getCreditsPerTurn() <= 0) {
                break;
            }
            taxRate--;
            if (taxRate == -1) {
                this.empire.setTaxRate(0.0f);
                break;
            }
            this.empire.setTaxRate(fArr[taxRate]);
        }
        if (this.empire.getCreditsPerTurn() < 0) {
            this.empire.setTaxRate(fArr[taxRate + 1]);
        }
    }

    private void sellUnneededBuildings() {
        for (Colony colony : GameData.colonies.getColonies(this.empire.id)) {
            if (colony.isFull()) {
                BuildingID buildingID = BuildingID.CLONING_CENTER;
                if (colony.hasBuilding(buildingID)) {
                    colony.sellBuilding(buildingID);
                }
            }
        }
    }

    public void manage() {
        adjustTaxRate();
        sellUnneededBuildings();
    }
}
