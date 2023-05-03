package com.birdshel.Uciana.AI.Managers;

import com.birdshel.Uciana.AI.FleetTasks.FleetTaskAI;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Technology.TechID;
import com.birdshel.Uciana.Technology.TechType;
import com.birdshel.Uciana.Technology.Technology;
import java.util.Arrays;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ResearchAI {
    private final Empire empire;

    public ResearchAI(Empire empire) {
        this.empire = empire;
    }

    public void manage() {
        TechID id = this.empire.getTech().getCurrentTech().getID();
        TechID techID = TechID.STAR_BASE;
        if (id == techID) {
            return;
        }
        if (!this.empire.getTech().hasTech(techID) && !this.empire.getKnownEmpires().isEmpty()) {
            this.empire.setTech(techID);
        } else if (this.empire.getTech().getCurrentTech().getID() == TechID.NONE || this.empire.getTech().getCurrentTech().getID() == TechID.MINIATURIZATION) {
            List<TechID> availableTechsToResearch = this.empire.getTech().getAvailableTechsToResearch();
            if (availableTechsToResearch.isEmpty()) {
                this.empire.setTech(TechID.MINIATURIZATION);
                return;
            }
            TechID techID2 = TechID.AUTOMATED_FACTORY;
            if (availableTechsToResearch.contains(techID2) && !this.empire.getTech().hasTech(techID2)) {
                this.empire.setTech(techID2);
                return;
            }
            Technology tech = this.empire.getTech();
            TechID techID3 = TechID.CLONING_CENTER;
            if (!tech.hasTech(techID3)) {
                this.empire.setTech(techID3);
                return;
            }
            this.empire.setTech(availableTechsToResearch.get(Functions.random.nextInt(availableTechsToResearch.size())));
            if (this.empire.isAtWar() && Functions.percent(66)) {
                for (TechID techID4 : availableTechsToResearch) {
                    if (Arrays.asList(TechType.SHIP_WEAPON, TechType.SHIP_ARMOR, TechType.SHIP_SHIELD).contains(this.empire.getTech().getTech(techID4).getType())) {
                        this.empire.setTech(techID4);
                    }
                }
            } else if (this.empire.isAI()) {
                Empire empire = this.empire;
                TechID techID5 = TechID.ASTEROID_MINING_OUTPOST;
                if (!empire.hasTech(techID5) && FleetTaskAI.getBuildOutpostTasksCount() != 0) {
                    this.empire.setTech(techID5);
                } else if (this.empire.getTech().getFuelCellRange() != 1000 && FleetTaskAI.getColonizationTasksCount() == 0) {
                    Empire empire2 = this.empire;
                    TechID techID6 = TechID.ZERO_POINT_ENERGY;
                    if (!empire2.hasTech(techID6) && this.empire.getTech().getTech(techID6).canBeResearched()) {
                        this.empire.setTech(techID6);
                        return;
                    }
                    Empire empire3 = this.empire;
                    TechID techID7 = TechID.ANTIMATTER_REACTOR;
                    if (!empire3.hasTech(techID7) && this.empire.getTech().getTech(techID7).canBeResearched()) {
                        this.empire.setTech(techID7);
                        return;
                    }
                    Empire empire4 = this.empire;
                    TechID techID8 = TechID.QUANTUM_GENERATOR;
                    if (!empire4.hasTech(techID8) && this.empire.getTech().getTech(techID8).canBeResearched()) {
                        this.empire.setTech(techID8);
                        return;
                    }
                    Empire empire5 = this.empire;
                    TechID techID9 = TechID.FUSION_REACTOR;
                    if (empire5.hasTech(techID9) || !this.empire.getTech().getTech(techID9).canBeResearched()) {
                        return;
                    }
                    this.empire.setTech(techID9);
                }
            }
        }
    }
}
