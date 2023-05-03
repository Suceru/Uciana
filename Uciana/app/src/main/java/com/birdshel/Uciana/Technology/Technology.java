package com.birdshel.Uciana.Technology;

import android.util.SparseIntArray;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Players.EmpireType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Technology {
    private List<TechID> allowedTechsForRandomProgression;
    private Tech currentTech;
    private final int empireID;
    private final EmpireType empireType;
    private final Map<TechCategory, Integer> techLevels;
    private Map<TechType, Integer> techValues;
    private final Techs techs;

    public Technology(int i, EmpireType empireType) {
        this.techValues = new HashMap();
        HashMap hashMap = new HashMap();
        this.techLevels = hashMap;
        this.allowedTechsForRandomProgression = new ArrayList();
        this.techs = new Techs(this);
        this.empireID = i;
        this.empireType = empireType;
        setCurrentTech(TechID.NONE);
        this.techValues.put(TechType.POWER_CORE, 10);
        this.techValues.put(TechType.FASTER_THEN_LIGHT_DRIVE, 3);
        this.techValues.put(TechType.COLONY_SCANNER, 7);
        this.techValues.put(TechType.SHIP_SCANNER, 5);
        this.techValues.put(TechType.SHIP_COMMUNICATION, 0);
        this.techValues.put(TechType.TROOP_IMPROVEMENT, 5);
        hashMap.put(TechCategory.ENGINEERING, 0);
        hashMap.put(TechCategory.PHYSICS, 0);
        hashMap.put(TechCategory.CHEMISTRY, 0);
        hashMap.put(TechCategory.ENERGY, 0);
        hashMap.put(TechCategory.NONE, 0);
        if (GameData.gameSettings.techProgressionType() == TechProgressionType.ALLOW_ONLY_ONE_RANDOM_TECH_PER_LEVEL) {
            setAllowedTechsForRandomProgression();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmpireType a() {
        return this.empireType;
    }

    public void addResearch(int i) {
        if (this.currentTech.addResearch(i)) {
            GameData.empires.get(this.empireID).checkForUpgrade(this.currentTech.getID());
            GameData.events.addTechEvent(this.empireID, this.currentTech.getID().ordinal(), 0);
            if (d().isEmpty()) {
                setCurrentTech(TechID.MINIATURIZATION);
            } else {
                setCurrentTech(TechID.NONE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(TechType techType) {
        return this.techValues.get(techType).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Tech> c(TechCategory techCategory, int i) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<TechID, Tech> entry : this.techs.a().entrySet()) {
            Tech value = entry.getValue();
            if (value.getCategory() == techCategory && value.getLevel() == i && value.isNormalTech()) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<TechID> d() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<TechID, Tech> entry : this.techs.a().entrySet()) {
            if (entry.getValue().getType() != TechType.NONE && !entry.getValue().isDone()) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(TechCategory techCategory, int i) {
        this.techLevels.put(techCategory, Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(TechType techType, int i) {
        this.techValues.put(techType, Integer.valueOf(i));
    }

    public List<TechID> getAllowedTechsForRandomProgression() {
        return this.allowedTechsForRandomProgression;
    }

    public float getArmorMultiplier() {
        float f2 = hasTech(TechID.VANADIUM_ARMOR) ? 2.0f : 1.0f;
        if (hasTech(TechID.DETRUTIUM_ARMOR)) {
            f2 = 3.0f;
        }
        if (hasTech(TechID.THETRIUM_ARMOR)) {
            f2 = 4.0f;
        }
        if (hasTech(TechID.CRYSTALIUM_ARMOR)) {
            f2 = 5.0f;
        }
        if (hasTech(TechID.NEUTRONIUM_ARMOR)) {
            return 6.0f;
        }
        return f2;
    }

    public List<TechID> getAvailableTechsToResearch() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<TechID, Tech> entry : this.techs.a().entrySet()) {
            Tech value = entry.getValue();
            if (!value.isDone() && value.getType() != TechType.NONE && value.canBeResearched()) {
                arrayList.add(value.getID());
            }
        }
        return arrayList;
    }

    public int getColonyScanningRange() {
        return this.techValues.get(TechType.COLONY_SCANNER).intValue();
    }

    public int getCountOfTechsFromCategory(TechCategory techCategory) {
        int i = 0;
        for (Map.Entry<TechID, Tech> entry : this.techs.a().entrySet()) {
            if (entry.getValue().getCategory() == techCategory && entry.getValue().getType() != TechType.NONE) {
                i++;
            }
        }
        return i;
    }

    public int getCountOfUnfinishedTechsFromCategory(TechCategory techCategory) {
        int i = 0;
        for (Map.Entry<TechID, Tech> entry : this.techs.a().entrySet()) {
            Tech value = entry.getValue();
            if (value.getCategory() == techCategory && value.getType() != TechType.NONE && !value.isDone()) {
                i++;
            }
        }
        return i;
    }

    public Tech getCurrentTech() {
        return this.currentTech;
    }

    public int getCurrentTechLevel(TechCategory techCategory) {
        return this.techLevels.get(techCategory).intValue();
    }

    public int getEmpireID() {
        return this.empireID;
    }

    public int getEngineSpeed() {
        return this.techValues.get(TechType.FASTER_THEN_LIGHT_DRIVE).intValue();
    }

    public List<TechID> getFinishedTechs() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<TechID, Tech> entry : this.techs.a().entrySet()) {
            if (entry.getValue().getType() != TechType.NONE && entry.getValue().isDone()) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    public List<Tech> getFinishedTechsSorted() {
        List<TechID> finishedTechs = getFinishedTechs();
        ArrayList arrayList = new ArrayList();
        for (TechID techID : finishedTechs) {
            arrayList.add(getTech(techID));
        }
        Collections.sort(arrayList, new Comparator<Tech>(this) { // from class: com.birdshel.Uciana.Technology.Technology.1
            @Override // java.util.Comparator
            public int compare(Tech tech, Tech tech2) {
                return tech.getName().compareToIgnoreCase(tech2.getName());
            }
        });
        return arrayList;
    }

    public int getFuelCellRange() {
        return this.techValues.get(TechType.POWER_CORE).intValue();
    }

    public int getHighestTechLevel(TechCategory techCategory) {
        return this.techs.getHighestTechLevel(techCategory);
    }

    public float getPowerCoreMultiplier() {
        float f2 = hasTech(TechID.FUSION_REACTOR) ? 1.75f : 1.0f;
        if (hasTech(TechID.QUANTUM_GENERATOR)) {
            f2 = 2.5f;
        }
        if (hasTech(TechID.ANTIMATTER_REACTOR)) {
            f2 = 3.25f;
        }
        if (hasTech(TechID.ZERO_POINT_ENERGY)) {
            return 4.0f;
        }
        return f2;
    }

    public TechID getRandomTech(TechCategory techCategory, int i) {
        List<TechID> techsFromCategoryAndLevel = this.techs.getTechsFromCategoryAndLevel(techCategory, i);
        return techsFromCategoryAndLevel.get(Functions.random.nextInt(techsFromCategoryAndLevel.size()));
    }

    public int getShipCommunicationRange() {
        return this.techValues.get(TechType.SHIP_COMMUNICATION).intValue();
    }

    public int getShipScanningRange() {
        return this.techValues.get(TechType.SHIP_SCANNER).intValue();
    }

    public Tech getTech(TechID techID) {
        return this.techs.get(techID);
    }

    public int getTechValue() {
        int i = 0;
        for (Map.Entry<TechID, Tech> entry : this.techs.a().entrySet()) {
            Tech value = entry.getValue();
            if (value.isDone()) {
                i += value.getResearchCost();
            }
        }
        return i;
    }

    public Map<TechType, Integer> getTechValues() {
        return this.techValues;
    }

    public int getTroopStrength() {
        return this.techValues.get(TechType.TROOP_IMPROVEMENT).intValue();
    }

    public boolean hasTech(TechID techID) {
        return this.techs.get(techID).isDone();
    }

    public void setAllowedTechsForRandomProgression() {
        TechCategory[] values;
        this.allowedTechsForRandomProgression.clear();
        for (TechCategory techCategory : TechCategory.values()) {
            if (techCategory != TechCategory.NONE) {
                for (int i = 1; i <= getHighestTechLevel(techCategory); i++) {
                    this.allowedTechsForRandomProgression.add(getRandomTech(techCategory, i));
                }
            }
        }
    }

    public void setCurrentTech(TechID techID) {
        this.currentTech = this.techs.get(techID);
    }

    public void setTech(TechID techID) {
        this.currentTech = this.techs.get(techID);
    }

    public void setAllowedTechsForRandomProgression(List<TechID> list) {
        this.allowedTechsForRandomProgression = list;
    }

    public Technology(Map<TechType, Integer> map, SparseIntArray sparseIntArray, int i, EmpireType empireType) {
        this.techValues = new HashMap();
        HashMap hashMap = new HashMap();
        this.techLevels = hashMap;
        this.allowedTechsForRandomProgression = new ArrayList();
        this.empireID = i;
        this.empireType = empireType;
        this.techValues = map;
        hashMap.put(TechCategory.ENGINEERING, 0);
        hashMap.put(TechCategory.PHYSICS, 0);
        hashMap.put(TechCategory.CHEMISTRY, 0);
        hashMap.put(TechCategory.ENERGY, 0);
        hashMap.put(TechCategory.NONE, 0);
        this.techs = new Techs(this);
        for (int i2 = 0; i2 < sparseIntArray.size(); i2++) {
            int keyAt = sparseIntArray.keyAt(i2);
            this.techs.get(TechID.getTechID(keyAt)).a(sparseIntArray.get(keyAt));
        }
        setCurrentTech(TechID.NONE);
    }
}
