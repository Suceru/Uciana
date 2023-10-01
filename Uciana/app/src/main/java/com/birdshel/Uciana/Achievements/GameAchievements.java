package com.birdshel.Uciana.Achievements;

import android.app.Activity;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Planets.ExplorationFind;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Technology.TechCategory;
import com.birdshel.Uciana.Technology.TechID;
import com.birdshel.Uciana.Technology.TechType;

import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameAchievements {
    private final AttackAchievements attackAchievements;
    private final ColonyAchievements colonyAchievements;
    private final Game game;
    private final MiscAchievements miscAchievements;
    private final TechAchievements techAchievements;
    private final VictoryAchievements victoryAchievements;

    public GameAchievements(Game game, Activity activity) {
        this.game = game;
        this.victoryAchievements = new VictoryAchievements(game, activity);
        this.techAchievements = new TechAchievements(game, activity);
        this.colonyAchievements = new ColonyAchievements(game, activity);
        this.miscAchievements = new MiscAchievements(game, activity);
        this.attackAchievements = new AttackAchievements(game, activity);
    }

    public void a(int i) {
        if (this.game.getActivity().isSignedIn()) {
            this.colonyAchievements.i(i);
        }
    }

    public void ascendedShipDestroyed() {
        if (this.game.getActivity().isSignedIn()) {
            this.attackAchievements.d();
        }
    }

    public void checkForAllShipTypesFleet(Fleet fleet) {
        if (this.game.getActivity().isSignedIn()) {
            this.miscAchievements.d(fleet);
        }
    }

    public void checkForBuildingFinished(Colony colony, String str) {
        if (this.game.getActivity().isSignedIn()) {
            this.colonyAchievements.d(colony, str);
        }
    }

    public void checkForColonizingAchievements(Planet planet) {
        if (this.game.getActivity().isSignedIn()) {
            this.colonyAchievements.e(planet);
        }
    }

    public void checkForColonyTakeOverAchievements(Colony colony, boolean z) {
        if (this.game.getActivity().isSignedIn()) {
            this.colonyAchievements.f(colony, z);
        }
    }

    public void checkForEmpireVictory(int i, int i2) {
        if (this.game.getActivity().isSignedIn()) {
            this.victoryAchievements.d(i, i2);
        }
    }

    public void checkForEndOfTurnAchievements(Colony colony) {
        if (this.game.getActivity().isSignedIn()) {
            this.colonyAchievements.g(colony);
        }
    }

    public void checkForExplorationAchievements(ExplorationFind explorationFind) {
        if (this.game.getActivity().isSignedIn()) {
            this.colonyAchievements.h(explorationFind);
        }
    }

    public void checkForOutpostAchievements(SystemObject systemObject) {
        if (this.game.getActivity().isSignedIn()) {
            this.miscAchievements.e(systemObject);
        }
    }

    public void checkForSystemDiscoveryAchievements(int i) {
        if (this.game.getActivity().isSignedIn()) {
            this.miscAchievements.f(i);
        }
    }

    public void checkForTechAchievements(int i, TechCategory techCategory, TechType techType, List<TechID> list) {
        if (this.game.getActivity().isSignedIn()) {
            this.techAchievements.d(i, techCategory, techType, list);
        }
    }

    public void checkForTerraformAchievements(Planet planet) {
        if (this.game.getActivity().isSignedIn()) {
            this.colonyAchievements.j(planet);
        }
    }

    public void checkForTreatyAchievements(Treaty treaty) {
        if (this.game.getActivity().isSignedIn()) {
            this.miscAchievements.g(treaty);
        }
    }

    public void gameOver() {
        if (this.game.getActivity().isSignedIn()) {
            this.miscAchievements.h();
        }
    }

    public void killOffAnEmpire() {
        if (this.game.getActivity().isSignedIn()) {
            this.attackAchievements.e();
        }
    }

    public void usedBioWeapon() {
        if (this.game.getActivity().isSignedIn()) {
            this.attackAchievements.f();
        }
    }

    public void wormholeTravel() {
        if (this.game.getActivity().isSignedIn()) {
            this.miscAchievements.i();
        }
    }
}
