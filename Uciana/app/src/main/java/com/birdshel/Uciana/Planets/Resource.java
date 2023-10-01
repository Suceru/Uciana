package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Technology.TechID;

import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Resource {
    private final int chancePercent;
    private final Map<ResourceType, Float> effects;
    private final ResourceID id;
    private final int name;
    private final TechID requiredTech;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private Map<ResourceType, Float> effects;
        private ResourceID id;
        private int name;
        private TechID requiredTech = TechID.NONE;
        private int chancePercent = 0;

        public Resource build() {
            return new Resource(this);
        }

        public Builder chancePercent(int i) {
            this.chancePercent = i;
            return this;
        }

        public Builder effects(Map<ResourceType, Float> map) {
            this.effects = map;
            return this;
        }

        public Builder id(ResourceID resourceID) {
            this.id = resourceID;
            return this;
        }

        public Builder name(int i) {
            this.name = i;
            return this;
        }

        public Builder requiredTech(TechID techID) {
            this.requiredTech = techID;
            return this;
        }
    }

    Resource(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.effects = builder.effects;
        this.chancePercent = builder.chancePercent;
        this.requiredTech = builder.requiredTech;
    }

    public int a() {
        return this.chancePercent;
    }

    public TechID b() {
        return this.requiredTech;
    }

    public boolean containsEffect(ResourceType resourceType) {
        return this.effects.containsKey(resourceType);
    }

    public ResourceID getID() {
        return this.id;
    }

    public int getImageIndex() {
        return this.id.ordinal() - 1;
    }

    public String getName() {
        return GameData.activity.getString(this.name);
    }

    public float getValue(ResourceType resourceType) {
        if (this.effects.containsKey(resourceType)) {
            return this.effects.get(resourceType).floatValue();
        }
        return 0.0f;
    }

    public boolean isVisible(int i) {
        if (this.requiredTech == TechID.NONE) {
            return true;
        }
        return GameData.empires.get(i).getTech().getTech(this.requiredTech).isDone();
    }
}
