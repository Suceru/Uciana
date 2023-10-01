package com.birdshel.Uciana.Players;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum RaceAttribute {
    GOOD_FARMERS(new Builder().d(R.string.perk_good_farmers).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.1
        {
            put(RaceAttributeType.FOOD_PER_FARMER, Float.valueOf(1.0f));
        }
    })),
    GOOD_WORKERS(new Builder().d(R.string.perk_good_workers).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.2
        {
            put(RaceAttributeType.PRODUCTION_PER_WORKER, Float.valueOf(1.0f));
        }
    })),
    GOOD_SCIENTISTS(new Builder().d(R.string.perk_good_scientists).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.3
        {
            put(RaceAttributeType.SCIENCE_PER_SCIENTIST, Float.valueOf(1.0f));
        }
    })),
    MILITARISTIC(new Builder().d(R.string.perk_militaristic).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.4
        {
            put(RaceAttributeType.COMMAND_POINTS_PER_COLONY, Float.valueOf(1.0f));
        }
    })),
    EXPERT_SOLDIERS(new Builder().d(R.string.perk_expert_soldiers).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.5
        {
            put(RaceAttributeType.STARTING_TROOPS_FOR_COLONY, Float.valueOf(2.0f));
            put(RaceAttributeType.GROUND_COMBAT, Float.valueOf(7.0f));
        }
    })),
    POPULATION(new Builder().d(R.string.perk_population).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.6
        {
            RaceAttributeType raceAttributeType = RaceAttributeType.BIRTHRATE_INCREASE;
            Float valueOf = Float.valueOf(0.1f);
            put(raceAttributeType, valueOf);
            put(RaceAttributeType.MAX_POPULATION_INCREASE, valueOf);
        }
    })),
    EXPERT_TRADERS(new Builder().d(R.string.perk_expert_traders).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.7
        {
            put(RaceAttributeType.CREDITS_INCREASE, Float.valueOf(0.1f));
        }
    })),
    EXPERT_TARGETEERS(new Builder().d(R.string.perk_expert_targeteers).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.8
        {
            put(RaceAttributeType.GROUND_COMBAT, Float.valueOf(3.0f));
            put(RaceAttributeType.BEAM_ACCURACY, Float.valueOf(10.0f));
        }
    })),
    CONTENT(new Builder().d(R.string.perk_content).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.9
        {
            put(RaceAttributeType.ASSIMILATION_RATE_BONUS, Float.valueOf(0.0f));
            put(RaceAttributeType.HAPPINESS, Float.valueOf(0.05f));
        }
    })),
    DEFENSIVE(new Builder().d(R.string.perk_defensive).c(new HashMap<RaceAttributeType, Float>() { // from class: com.birdshel.Uciana.Players.RaceAttribute.10
        {
            RaceAttributeType raceAttributeType = RaceAttributeType.DEFENDING_GROUND_COMBAT;
            Float valueOf = Float.valueOf(5.0f);
            put(raceAttributeType, valueOf);
            put(RaceAttributeType.BEAM_EVASION, valueOf);
        }
    }));
    
    private final Map<RaceAttributeType, Float> attributeTypes;
    private final int name;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    private static class Builder {
        private Map<RaceAttributeType, Float> attributeTypes;
        private int name;

        Builder c(Map<RaceAttributeType, Float> map) {
            this.attributeTypes = map;
            return this;
        }

        Builder d(int i) {
            this.name = i;
            return this;
        }
    }

    RaceAttribute(Builder builder) {
        this.name = builder.name;
        this.attributeTypes = builder.attributeTypes;
    }

    public Map<RaceAttributeType, Float> getAttributeTypes() {
        return this.attributeTypes;
    }

    public String getName() {
        return GameData.activity.getString(this.name);
    }
}
