package com.birdshel.Uciana.Colonies;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ProductionItem {
    private String id;
    private boolean isRepeatOn;
    private String name;
    private int requiredProduction;
    private ManufacturingType type;

    public ProductionItem(ManufacturingType manufacturingType, String str, String str2, int i) {
        constructProductionItem(manufacturingType, str, str2, i, false);
    }

    private void constructProductionItem(ManufacturingType manufacturingType, String str, String str2, int i, boolean z) {
        this.type = manufacturingType;
        this.id = str;
        this.name = str2;
        this.requiredProduction = i;
        this.isRepeatOn = z;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getRequiredProduction() {
        return this.requiredProduction;
    }

    public ManufacturingType getType() {
        return this.type;
    }

    public boolean isRepeatOn() {
        return this.isRepeatOn;
    }

    public void setRepeatOn(boolean z) {
        this.isRepeatOn = z;
    }

    public ProductionItem(ManufacturingType manufacturingType, String str, String str2, int i, boolean z) {
        constructProductionItem(manufacturingType, str, str2, i, z);
    }
}
