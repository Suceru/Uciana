package com.birdshel.Uciana.Ships.ShipComponents;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Technology.TechID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipComponent {

    /* renamed from: a  reason: collision with root package name */
    protected int f1558a;
    private final int description;
    private final int iconIndex;
    private final ShipComponentID id;
    private final int name;
    private final int productionCost;
    private final TechID requiredTechID;
    private final int shortName;
    private final int spaceRequired;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        ShipComponentID f1559a;
        int b;

        /* renamed from: c  reason: collision with root package name */
        int f1560c = 0;

        /* renamed from: d  reason: collision with root package name */
        TechID f1561d;

        /* renamed from: e  reason: collision with root package name */
        int f1562e;

        /* renamed from: f  reason: collision with root package name */
        int f1563f;
        int g;
        int h;
        int i;

        public ShipComponent build() {
            return new ShipComponent(this);
        }

        public Builder count(int i) {
            this.f1563f = i;
            return this;
        }

        public Builder description(int i) {
            this.h = i;
            return this;
        }

        public Builder iconIndex(int i) {
            this.g = i;
            return this;
        }

        public Builder id(ShipComponentID shipComponentID) {
            this.f1559a = shipComponentID;
            return this;
        }

        public Builder name(int i) {
            this.b = i;
            return this;
        }

        public Builder productionCost(int i) {
            this.i = i;
            return this;
        }

        public Builder requiredTechID(TechID techID) {
            this.f1561d = techID;
            return this;
        }

        public Builder shortName(int i) {
            this.f1560c = i;
            return this;
        }

        public Builder spaceRequired(int i) {
            this.f1562e = i;
            return this;
        }
    }

    public ShipComponent(Builder builder) {
        this.id = builder.f1559a;
        this.name = builder.b;
        this.shortName = builder.f1560c;
        this.requiredTechID = builder.f1561d;
        this.spaceRequired = builder.f1562e;
        this.f1558a = builder.f1563f;
        this.iconIndex = builder.g;
        this.description = builder.h;
        this.productionCost = builder.i;
    }

    public int getComponentCount() {
        return this.f1558a;
    }

    public String getDescription() {
        return GameData.activity.getString(this.description);
    }

    public ShipComponentID getID() {
        return this.id;
    }

    public int getIconIndex() {
        return this.iconIndex;
    }

    public String getName() {
        return GameData.activity.getString(this.name);
    }

    public int getProductionCost() {
        return this.productionCost;
    }

    public TechID getRequiredTech() {
        return this.requiredTechID;
    }

    public String getShortName() {
        int i = this.shortName;
        return i == 0 ? "" : GameData.activity.getString(i);
    }

    public int getSpaceRequired() {
        return this.spaceRequired;
    }

    public void setComponentCount(int i) {
        this.f1558a = i;
    }

    public ShipComponent(ShipComponent shipComponent) {
        this.id = shipComponent.id;
        this.name = shipComponent.name;
        this.shortName = shipComponent.shortName;
        this.description = shipComponent.description;
        this.iconIndex = shipComponent.iconIndex;
        this.requiredTechID = shipComponent.requiredTechID;
        this.spaceRequired = shipComponent.spaceRequired;
        this.productionCost = shipComponent.productionCost;
        this.f1558a = 1;
    }
}
