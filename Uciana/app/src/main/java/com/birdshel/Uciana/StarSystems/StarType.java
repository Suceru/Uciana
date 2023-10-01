package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.MineralRichness;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.OptionID;

import org.andengine.util.adt.color.Color;

import java.util.HashMap;
import java.util.Map;
//import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum StarType {
    BLUE(new Builder().m(50).n(35).i(new Color(0.255f, 0.5f, 1.0f)).j(R.string.star_blue_description).h(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.2
        {
            put(Climate.MOLTEN, 20);
            put(Climate.CORROSIVE, 13);
            put(Climate.RADIATED, 26);
            put(Climate.BARREN, 17);
            put(Climate.DESERT, 17);
            put(Climate.ICE, 4);
            put(Climate.TUNDRA, 3);
        }
    }).k(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.1
        {
            put(MineralRichness.ABUNDANT, 40);
            put(MineralRichness.RICH, 41);
            put(MineralRichness.VERY_RICH, 19);
        }
    }).l(25)),
    WHITE(new Builder().m(40).n(25).i(new Color(1.0f, 1.0f, 1.0f)).j(R.string.star_white_description).h(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.4
        {
            put(Climate.MOLTEN, 10);
            put(Climate.CORROSIVE, 7);
            put(Climate.RADIATED, 17);
            put(Climate.BARREN, 16);
            put(Climate.DESERT, 16);
            put(Climate.ICE, 6);
            put(Climate.TUNDRA, 6);
            put(Climate.OCEAN, 3);
            put(Climate.SWAMP, 4);
            put(Climate.ARID, 4);
            put(Climate.JUNGLE, 5);
            put(Climate.TERRAN, 5);
            put(Climate.GAIA, 1);
        }
    }).k(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.3
        {
            put(MineralRichness.POOR, 19);
            put(MineralRichness.ABUNDANT, 41);
            put(MineralRichness.RICH, 29);
            put(MineralRichness.VERY_RICH, 11);
        }
    }).l(30)),
    YELLOW(new Builder().m(60).n(45).i(new Color(0.973f, 0.894f, 0.349f)).j(R.string.star_yellow_description).h(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.6
        {
            put(Climate.MOLTEN, 10);
            put(Climate.CORROSIVE, 7);
            put(Climate.RADIATED, 11);
            put(Climate.BARREN, 11);
            put(Climate.DESERT, 11);
            put(Climate.ICE, 9);
            put(Climate.TUNDRA, 7);
            put(Climate.OCEAN, 7);
            put(Climate.SWAMP, 6);
            put(Climate.ARID, 6);
            put(Climate.JUNGLE, 6);
            put(Climate.TERRAN, 6);
            put(Climate.GAIA, 3);
        }
    }).k(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.5
        {
            put(MineralRichness.VERY_POOR, 4);
            put(MineralRichness.POOR, 26);
            put(MineralRichness.ABUNDANT, 40);
            put(MineralRichness.RICH, 20);
            put(MineralRichness.VERY_RICH, 10);
        }
    }).l(40)),
    ORANGE(new Builder().m(55).n(40).i(new Color(1.0f, 0.792f, 0.267f)).j(R.string.star_orange_description).h(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.8
        {
            put(Climate.MOLTEN, 7);
            put(Climate.CORROSIVE, 6);
            put(Climate.RADIATED, 9);
            put(Climate.BARREN, 10);
            put(Climate.DESERT, 10);
            put(Climate.ICE, 10);
            put(Climate.TUNDRA, 8);
            put(Climate.OCEAN, 9);
            put(Climate.SWAMP, 7);
            put(Climate.ARID, 6);
            put(Climate.JUNGLE, 7);
            put(Climate.TERRAN, 8);
            put(Climate.GAIA, 3);
        }
    }).k(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.7
        {
            put(MineralRichness.VERY_POOR, 10);
            put(MineralRichness.POOR, 40);
            put(MineralRichness.ABUNDANT, 36);
            put(MineralRichness.RICH, 10);
            put(MineralRichness.VERY_RICH, 4);
        }
    }).l(35)),
    RED(new Builder().m(45).n(30).i(new Color(0.965f, 0.3f, 0.3f)).j(R.string.star_red_description).h(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.10
        {
            put(Climate.MOLTEN, 15);
            put(Climate.CORROSIVE, 12);
            put(Climate.RADIATED, 6);
            put(Climate.BARREN, 22);
            put(Climate.DESERT, 6);
            put(Climate.ICE, 13);
            put(Climate.TUNDRA, 9);
            put(Climate.OCEAN, 4);
            put(Climate.SWAMP, 4);
            put(Climate.ARID, 3);
            put(Climate.JUNGLE, 3);
            put(Climate.TERRAN, 3);
        }
    }).k(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.9
        {
            put(MineralRichness.VERY_POOR, 20);
            put(MineralRichness.POOR, 35);
            put(MineralRichness.ABUNDANT, 40);
            put(MineralRichness.RICH, 5);
        }
    }).l(20)),
    BROWN(new Builder().m(40).n(25).i(new Color(0.78f, 0.682f, 0.478f)).j(R.string.star_brown_description).h(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.12
        {
            put(Climate.MOLTEN, 8);
            put(Climate.CORROSIVE, 18);
            put(Climate.RADIATED, 26);
            put(Climate.BARREN, 8);
            put(Climate.DESERT, 13);
            put(Climate.ICE, 8);
            put(Climate.TUNDRA, 7);
            put(Climate.OCEAN, 2);
            put(Climate.SWAMP, 2);
            put(Climate.ARID, 2);
            put(Climate.JUNGLE, 2);
            put(Climate.TERRAN, 3);
            put(Climate.GAIA, 1);
        }
    }).k(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.StarType.11
        {
            put(MineralRichness.VERY_POOR, 5);
            put(MineralRichness.POOR, 11);
            put(MineralRichness.ABUNDANT, 61);
            put(MineralRichness.RICH, 18);
            put(MineralRichness.VERY_RICH, 5);
        }
    }).l(25));
    
    private final Map<Object, Integer> climatePercents;
    private final Color color;
    private final int message;
    private final Map<Object, Integer> richnessPercents;
    private final int scoutValue;
    private final int systemObjectPercent;
    private final int systemObjectPercentLow;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    private static class Builder {
        private Map<Object, Integer> climatePercents;
        private Color color;
        private int message;
        private Map<Object, Integer> richnessPercents;
        private int scoutValue;
        private int systemObjectPercent;
        private int systemObjectPercentLow;

        Builder() {
        }

        Builder h(Map<Object, Integer> map) {
            this.climatePercents = map;
            return this;
        }

        Builder i(Color color) {
            this.color = color;
            return this;
        }

        Builder j(int i) {
            this.message = i;
            return this;
        }

        Builder k(Map<Object, Integer> map) {
            this.richnessPercents = map;
            return this;
        }

        Builder l(int i) {
            this.scoutValue = i;
            return this;
        }

        Builder m(int i) {
            this.systemObjectPercent = i;
            return this;
        }

        Builder n(int i) {
            this.systemObjectPercentLow = i;
            return this;
        }
    }

    StarType(Builder builder) {
        this.systemObjectPercent = builder.systemObjectPercent;
        this.systemObjectPercentLow = builder.systemObjectPercentLow;
        this.color = builder.color;
        this.message = builder.message;
        this.climatePercents = builder.climatePercents;
        this.richnessPercents = builder.richnessPercents;
        this.scoutValue = builder.scoutValue;
    }

    public Climate getClimate() {
        return (Climate) Functions.getItemByPercent(this.climatePercents);
    }

    public Color getColor() {
        return this.color;
    }

    public String getMessage() {
        return GameData.activity.getString(this.message);
    }

    public MineralRichness getMineralRichness() {
        return (MineralRichness) Functions.getItemByPercent(this.richnessPercents);
    }

    public int getScoutValue() {
        return this.scoutValue;
    }

    public int getSystemObjectPercent() {
        if (Game.options.getOption(OptionID.SYSTEM_OBJECT_PERCENT, 1.0f) == 1.0f) {
            return this.systemObjectPercent;
        }
        return this.systemObjectPercentLow;
    }
}
