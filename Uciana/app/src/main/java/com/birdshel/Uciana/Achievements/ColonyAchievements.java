package com.birdshel.Uciana.Achievements;

import android.app.Activity;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.ExplorationFind;
import com.birdshel.Uciana.Planets.Gravity;
import com.birdshel.Uciana.Planets.MineralRichness;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSize;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.SystemObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonyAchievements extends BaseAchievements {
    private final Climate[] homeWorlds;



    /* JADX INFO: Access modifiers changed from: package-private */
    public ColonyAchievements(Game game, Activity activity) {
        super(game, activity);
        this.homeWorlds = new Climate[]{Climate.RED_HOMEWORLD, Climate.GREEN_HOMEWORLD, Climate.BLUE_HOMEWORLD, Climate.ORANGE_HOMEWORLD, Climate.YELLOW_HOMEWORLD, Climate.PURPLE_HOMEWORLD, Climate.CYAN_HOMEWORLD};
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkForColonizeAllFivePlanetsInSystem(Planet planet) {
        int empireID = planet.getColony().getEmpireID();
        for (SystemObject systemObject : this.game.galaxy.getStarSystem(planet.getSystemID()).getSystemObjects()) {
            if (!systemObject.isPlanet() || !systemObject.hasColony() || systemObject.getOccupier() != empireID) {
                return;
            }
            while (r4.hasNext()) {
            }
        }
        a(AchievementID.COLONIZED_A_FULL_SYSTEM);
    }

    private void checkForExpansionAchievements(int i) {
        List<Colony> colonies = this.game.empires.get(i).getColonies();
        if (!c(AchievementID.LARGE_EMPIRE) && colonies.size() >= 10) {
            HashSet hashSet = new HashSet();
            for (Colony colony : colonies) {
                hashSet.add(Integer.valueOf(colony.getSystemID()));
            }
            if (hashSet.size() >= 10) {
                a(AchievementID.LARGE_EMPIRE);
            }
        }
        if (c(AchievementID.ALL_THE_HOME_WORLDS) || colonies.size() < 6) {
            return;
        }
        HashSet hashSet2 = new HashSet();
        for (Colony colony2 : colonies) {
            hashSet2.add(colony2.getPlanet().getClimate());
        }
        if (hashSet2.containsAll(Arrays.asList(this.homeWorlds))) {
            a(AchievementID.ALL_THE_HOME_WORLDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(Colony colony, String str) {
        if (BuildingID.getBuildingID(str) == BuildingID.CAPITAL) {
            AchievementID achievementID = AchievementID.REBUILD_CAPITAL;
            if (c(achievementID) || colony.getPlanet().getClimate() == this.homeWorlds[colony.getEmpireID()]) {
                return;
            }
            a(achievementID);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(Planet planet) {
        if (planet.getMineralRichness() == MineralRichness.ULTRA_RICH) {
            b(AchievementID.ULTRA_RICH);
        }
        if (planet.getPreTerraformedClimate() == Climate.GAIA) {
            b(AchievementID.COLONIZE_GAIA);
            if (planet.getSize() == PlanetSize.EXTRA_LARGE && planet.getMineralRichness() == MineralRichness.VERY_RICH && planet.getGravity() == Gravity.NORMAL) {
                b(AchievementID.PERFECT_WORLD);
            }
        }
        if (planet.getClimate() == Climate.MOLTEN) {
            b(AchievementID.COLONIZE_MOLTEN);
        }
        if (planet.getClimate() == Climate.RING || planet.getClimate() == Climate.POLLUTED || planet.getClimate() == Climate.BROKEN) {
            b(AchievementID.COLONIZE_UNIQUE_WORLD);
        }
        if (planet.hasColony()) {
            checkForExpansionAchievements(planet.getColony().getEmpireID());
            i(planet.getColony().getEmpireID());
            checkForColonizeAllFivePlanetsInSystem(planet);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(Colony colony, boolean z) {
        if (colony.getPlanet().getMineralRichness() == MineralRichness.ULTRA_RICH) {
            b(AchievementID.ULTRA_RICH);
        }
        AchievementID achievementID = AchievementID.INVADE_HOME_WORLD;
        if (!c(achievementID) && !z && Arrays.asList(this.homeWorlds).contains(colony.getPlanet().getClimate())) {
            a(achievementID);
        }
        checkForExpansionAchievements(colony.getEmpireID());
        i(colony.getEmpireID());
        checkForColonizeAllFivePlanetsInSystem(colony.getPlanet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(Colony colony) {
        if (colony.isFull()) {
            b(AchievementID.MAX_POPULATION);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(ExplorationFind explorationFind) {
        if (explorationFind == ExplorationFind.LOST_COLONY) {
            b(AchievementID.LOST_COLONY);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(int i) {
        AchievementID achievementID = AchievementID.ALL_THE_RESOURCES;
        if (c(achievementID) && c(AchievementID.RESOURCE_MONOPOLY)) {
            return;
        }
        Map<ResourceID, Integer> resources = this.game.empires.get(i).getResources();
        if (!c(achievementID) && resources.size() == 16) {
            a(achievementID);
        }
        if (c(AchievementID.RESOURCE_MONOPOLY)) {
            return;
        }
        for (Map.Entry<ResourceID, Integer> entry : resources.entrySet()) {
            if (entry.getValue().intValue() >= 5) {
                a(AchievementID.RESOURCE_MONOPOLY);
            }
        }
    }
    /*   *//* JADX INFO: Access modifiers changed from: package-private *//*
     *//* compiled from: MyApplication *//*
     *//* renamed from: com.birdshel.Uciana.Achievements.ColonyAchievements$1  reason: invalid class name *//*
     *//* loaded from: classes.dex *//*
    public static *//* synthetic *//* class AnonymousClass1 {

     *//* renamed from: a  reason: collision with root package name *//*
        static final *//* synthetic *//* int[] f1350a;

        static {
            int[] iArr = new int[Climate.values().length];
            f1350a = iArr;
            try {
                iArr[Climate.TROPICAL_OCEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1350a[Climate.SUPER_ACIDIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1350a[Climate.PLAGUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1350a[Climate.METALLIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1350a[Climate.VOLCANIC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1350a[Climate.METHANE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1350a[Climate.PLAINS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1350a[Climate.BOREAL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1350a[Climate.APHOTIC_OCEAN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1350a[Climate.BOG.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1350a[Climate.STAGNANT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1350a[Climate.GARDEN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }*/
          /*  switch (AnonymousClass1.f1350a[planet.getTerraformedClimate().ordinal()]) {
            case 1:
                b(AchievementID.TROPICAL_OCEAN_TERRAFORM);
                break;
            case 2:
                b(AchievementID.SUPER_ACIDIC_TERRAFORM);
                break;
            case 3:
                b(AchievementID.PLAGUE_TERRAFORM);
                break;
            case 4:
                b(AchievementID.METALLIC_TERRAFORM);
                if (planet.getMineralRichness() == MineralRichness.ULTRA_RICH) {
                    b(AchievementID.ULTRA_RICH);
                    break;
                }
                break;
            case 5:
                b(AchievementID.VOLCANIC_TERRAFORM);
                break;
            case 6:
                b(AchievementID.METHANE_TERRAFORM);
                break;
            case 7:
                b(AchievementID.PLAINS_TERRAFORM);
                break;
            case 8:
                b(AchievementID.BORALE_TERRAFORM);
                break;
            case 9:
                b(AchievementID.APHOTIC_TERRAFORM);
                break;
            case 10:
                b(AchievementID.BOG_TERRAFORM);
                break;
            case 11:
                b(AchievementID.STAGNANT_TERRAFORM);
                break;
            case 12:
                b(AchievementID.GARDEN_TERRAFORM);
                break;
        }*/
    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(Planet planet) {
        switch (planet.getTerraformedClimate()){

            case SUPER_ACIDIC:
                b(AchievementID.SUPER_ACIDIC_TERRAFORM);
                break;
            case METALLIC:
                b(AchievementID.METALLIC_TERRAFORM);
                if (planet.getMineralRichness() == MineralRichness.ULTRA_RICH) {
                    b(AchievementID.ULTRA_RICH);
                    break;
                }
                break;
            case VOLCANIC:
                b(AchievementID.VOLCANIC_TERRAFORM);
                break;
            case METHANE:
                b(AchievementID.METHANE_TERRAFORM);
                break;
            case PLAINS:
                b(AchievementID.PLAINS_TERRAFORM);
                break;
            case APHOTIC_OCEAN:
                b(AchievementID.APHOTIC_TERRAFORM);
                break;
            case TROPICAL_OCEAN:
                b(AchievementID.TROPICAL_OCEAN_TERRAFORM);
                break;
            case BOG:
                b(AchievementID.BOG_TERRAFORM);
                break;
            case PLAGUE:
                b(AchievementID.PLAGUE_TERRAFORM);
                break;
            case BOREAL:
                b(AchievementID.BORALE_TERRAFORM);
                break;
            case STAGNANT:
                b(AchievementID.STAGNANT_TERRAFORM);
                break;
            case GARDEN:
                b(AchievementID.GARDEN_TERRAFORM);
                break;
        }

        if (planet.hasColony()) {
            i(planet.getColony().getEmpireID());
        }
    }
}
