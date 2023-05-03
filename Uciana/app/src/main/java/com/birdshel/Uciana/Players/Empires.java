package com.birdshel.Uciana.Players;

import android.content.Context;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.StarSystems.StarSystem;
import java.util.ArrayList;
import java.util.List;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Empires {
    public static final int AMEOLI_ID = 5;
    public static final int BYLON_ID = 4;
    public static final int DARGATHI_ID = 3;
    public static final int HUMAN_ID = 1;
    public static final int MARRENAREE_ID = 6;
    public static final int SOTHREN_ID = 2;
    public static final int TARLISH_ID = 0;
    private static final RaceAttribute[][] defaultRaceAttributes;
    private final Game game;
    private static final boolean[] hasRings = {true, false, false, true, false, false, true};
    private static final int[] moonImage = {2, 4, 3, 5, 1, 0, 3};
    private static final float[] moonSize = {1.25f, 1.0f, 1.0f, 0.75f, 1.25f, 0.75f, 0.5f};
    private static final Color[] empireColor = {new Color(0.8f, 0.04f, 0.04f), new Color(0.04f, 0.67f, 0.08f), new Color(0.19f, 0.45f, 0.82f), new Color(0.98f, 0.63f, 0.13f), new Color(1.0f, 1.0f, 0.33f), new Color(1.0f, 0.32f, 1.0f), new Color(0.0f, 1.0f, 1.0f)};
    private static final int[] homeworldClimate = {28, 29, 30, 31, 32, 33, 36};
    private List<Empire> empires = new ArrayList();
    private final String[] empireNames = new String[7];
    private final String[] empireDescriptions = new String[7];
    private final String[] homeSystemNames = new String[7];
    private final String[] homeWorldNames = new String[7];
    private final String[] shipIDPrefix = {"TI-", "HA-", "SF-", "DE-", "BC-", "AS-", "ME-"};

    static {
        RaceAttribute raceAttribute = RaceAttribute.EXPERT_SOLDIERS;
        RaceAttribute[] raceAttributeArr = {raceAttribute, RaceAttribute.DEFENSIVE};
        RaceAttribute[] raceAttributeArr2 = {RaceAttribute.GOOD_WORKERS, RaceAttribute.EXPERT_TRADERS};
        RaceAttribute raceAttribute2 = RaceAttribute.GOOD_FARMERS;
        RaceAttribute raceAttribute3 = RaceAttribute.CONTENT;
        RaceAttribute[] raceAttributeArr3 = {RaceAttribute.MILITARISTIC, raceAttribute};
        RaceAttribute raceAttribute4 = RaceAttribute.GOOD_SCIENTISTS;
        defaultRaceAttributes = new RaceAttribute[][]{raceAttributeArr, raceAttributeArr2, new RaceAttribute[]{raceAttribute2, raceAttribute3}, raceAttributeArr3, new RaceAttribute[]{raceAttribute4, RaceAttribute.EXPERT_TARGETEERS}, new RaceAttribute[]{RaceAttribute.POPULATION, raceAttribute2}, new RaceAttribute[]{raceAttribute3, raceAttribute4}};
    }

    public Empires(Game game) {
        this.game = game;
    }

    public static RaceAttribute[] getDefaultRaceAttributes(int i) {
        RaceAttribute[][] raceAttributeArr = defaultRaceAttributes;
        RaceAttribute[] raceAttributeArr2 = {raceAttributeArr[i][0], raceAttributeArr[i][1]};
        if (Game.options.isOn(OptionID.MODDING)) {
            for (int i2 = 0; i2 < 2; i2++) {
                if (Game.modValues.getRaceAttribute(i, i2) != null) {
                    raceAttributeArr2[i2] = Game.modValues.getRaceAttribute(i, i2);
                }
                if (raceAttributeArr2[0].ordinal() == raceAttributeArr2[1].ordinal()) {
                    RaceAttribute[][] raceAttributeArr3 = defaultRaceAttributes;
                    raceAttributeArr2[0] = raceAttributeArr3[i][0];
                    if (raceAttributeArr2[0].ordinal() == raceAttributeArr2[1].ordinal()) {
                        raceAttributeArr2[0] = raceAttributeArr3[i][1];
                    }
                }
            }
        }
        return raceAttributeArr2;
    }

    public static Color getEmpireColor(int i) {
        return empireColor[i];
    }

    public static int getHomeworldClimate(int i) {
        return homeworldClimate[i];
    }

    public static int getHomeworldMoonImage(int i) {
        return moonImage[i];
    }

    public static float getHomeworldMoonSize(int i) {
        return moonSize[i];
    }

    public static String getVictoryText(Context context, int i) {
        switch (i) {
            case 0:
                return context.getString(R.string.victory_tarlish);
            case 1:
                return context.getString(R.string.victory_human);
            case 2:
                return context.getString(R.string.victory_sothren);
            case 3:
                return context.getString(R.string.victory_dargathi);
            case 4:
                return context.getString(R.string.victory_bylon);
            case 5:
                return context.getString(R.string.victory_ameoli);
            case 6:
                return context.getString(R.string.victory_marrenaree);
            default:
                throw new AssertionError("Invalid EmpireID for victory");
        }
    }

    public static boolean homeworldHasMoon(int i) {
        return moonImage[i] != -1;
    }

    public static boolean homeworldHasRings(int i) {
        return hasRings[i];
    }

    public String a(int i) {
        return this.shipIDPrefix[i];
    }

    public void add(Empire empire) {
        this.empires.add(empire);
    }

    public void clear() {
        this.empires = new ArrayList();
    }

    public int createHomeworld(int i, int i2, EmpireType empireType) {
        int nextInt;
        StarSystem starSystem = GameData.galaxy.getStarSystem(i2);
        do {
            nextInt = Functions.random.nextInt(5);
        } while (starSystem.isOrbitSpecial(nextInt));
        Moon moon = new Moon(moonImage[i], moonSize[i]);
        Climate climate = Climate.values()[getHomeworldClimate(i)];
        GameData.galaxy.getStarSystem(i2).getSystemObjects().set(nextInt, new Planet.Builder().systemID(i2).orbit(nextInt).hasRing(hasRings[i]).hasMoon(homeworldHasMoon(i)).moon(moon).climate(climate).terraformedClimate(climate).buildHomeworld(empireType));
        return nextInt;
    }

    public Empire get(int i) {
        return this.empires.get(i);
    }

    public String getDefaultName(int i) {
        return this.empireNames[i];
    }

    public String getEmpireDescription(int i) {
        return this.empireDescriptions[i];
    }

    public List<Empire> getEmpires() {
        return this.empires;
    }

    public String getHomeSystemName(int i) {
        return this.homeSystemNames[i];
    }

    public String getHomeworldName(int i) {
        return this.homeWorldNames[i];
    }

    public void processTurn() {
        for (Empire empire : this.empires) {
            if (empire.isAlive()) {
                empire.d();
            }
        }
    }

    public void setValues() {
        this.empireNames[0] = this.game.getActivity().getString(R.string.empire_name_tarlish);
        this.empireNames[1] = this.game.getActivity().getString(R.string.empire_name_human);
        this.empireNames[2] = this.game.getActivity().getString(R.string.empire_name_sothren);
        this.empireNames[3] = this.game.getActivity().getString(R.string.empire_name_dargathi);
        this.empireNames[4] = this.game.getActivity().getString(R.string.empire_name_bylon);
        this.empireNames[5] = this.game.getActivity().getString(R.string.empire_name_ameoli);
        this.empireNames[6] = this.game.getActivity().getString(R.string.empire_name_marrenaree);
        this.empireDescriptions[0] = this.game.getActivity().getString(R.string.empire_description_tarlish);
        this.empireDescriptions[1] = this.game.getActivity().getString(R.string.empire_description_human);
        this.empireDescriptions[2] = this.game.getActivity().getString(R.string.empire_description_sothren);
        this.empireDescriptions[3] = this.game.getActivity().getString(R.string.empire_description_dargathi);
        this.empireDescriptions[4] = this.game.getActivity().getString(R.string.empire_description_bylon);
        this.empireDescriptions[5] = this.game.getActivity().getString(R.string.empire_description_ameoli);
        this.empireDescriptions[6] = this.game.getActivity().getString(R.string.empire_description_marrenaree);
        this.homeSystemNames[0] = this.game.getActivity().getString(R.string.homesystem_tarlish);
        this.homeSystemNames[1] = this.game.getActivity().getString(R.string.homesystem_human);
        this.homeSystemNames[2] = this.game.getActivity().getString(R.string.homesystem_sothren);
        this.homeSystemNames[3] = this.game.getActivity().getString(R.string.homesystem_dargathi);
        this.homeSystemNames[4] = this.game.getActivity().getString(R.string.homesystem_bylon);
        this.homeSystemNames[5] = this.game.getActivity().getString(R.string.homesystem_ameoli);
        this.homeSystemNames[6] = this.game.getActivity().getString(R.string.homesystem_marrenaree);
        this.homeWorldNames[0] = this.game.getActivity().getString(R.string.homeworld_tarlish);
        this.homeWorldNames[1] = this.game.getActivity().getString(R.string.homeworld_human);
        this.homeWorldNames[2] = this.game.getActivity().getString(R.string.homeworld_sothren);
        this.homeWorldNames[3] = this.game.getActivity().getString(R.string.homeworld_dargathi);
        this.homeWorldNames[4] = this.game.getActivity().getString(R.string.homeworld_bylon);
        this.homeWorldNames[5] = this.game.getActivity().getString(R.string.homeworld_ameoli);
        this.homeWorldNames[6] = this.game.getActivity().getString(R.string.homeworld_marrenaree);
        if (Game.options.isOn(OptionID.MODDING)) {
            for (int i = 0; i < 7; i++) {
                if (Game.modValues.getEmpireName(i) != null) {
                    this.empireNames[i] = Game.modValues.getEmpireName(i);
                }
                if (Game.modValues.getEmpireDescription(i) != null) {
                    this.empireDescriptions[i] = Game.modValues.getEmpireDescription(i);
                }
                if (Game.modValues.getHomeSystemName(i) != null) {
                    this.homeSystemNames[i] = Game.modValues.getHomeSystemName(i);
                }
                if (Game.modValues.getHomeWorldName(i) != null) {
                    this.homeWorldNames[i] = Game.modValues.getHomeWorldName(i);
                }
            }
        }
    }
}
