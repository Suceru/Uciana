package com.birdshel.Uciana.AI;

import com.birdshel.Uciana.AI.AIObjects.AttackTarget;
import com.birdshel.Uciana.AI.AIObjects.AttackTargetType;
import com.birdshel.Uciana.AI.Managers.AttackAI;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Scenes.AttackSceneData;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.StarSystems.StarSystem;
import java.util.Iterator;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AscendedAttack {
    private final Game game;
    private final boolean spaceBattle = spaceBattleNeeded();
    private final int systemID;
    private final AttackTarget target;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.AscendedAttack$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1335a;

        static {
            int[] iArr = new int[AttackTargetType.values().length];
            f1335a = iArr;
            try {
                iArr[AttackTargetType.FLEET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1335a[AttackTargetType.SYSTEM_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1335a[AttackTargetType.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public AscendedAttack(Game game, int i) {
        this.game = game;
        this.systemID = i;
        this.target = new AttackAI(8).getAttackTarget(i);
    }

    private void attackPlanet() {
        PlanetAttack planetAttack = new PlanetAttack(8, this.target.getSystemID(), this.target.getOrbit());
        if (planetAttack.attack()) {
            if (this.game.empires.get(this.target.getEmpireID()).isHuman()) {
                this.game.getCurrentScene().changeScene(this.game.attackScene, new AttackSceneData(0, 8, this.target.getEmpireID(), this.target.getSystemID(), this.target.getOrbit(), false));
                this.game.attackScene.showAIPlanetAttackResult(planetAttack);
                return;
            }
            this.game.beginTurn();
            return;
        }
        this.game.beginTurn();
    }

    private void calculateSpaceBattleResult() {
        new AutoBattle(this.game, false).set(this.systemID, 8, this.target.getEmpireID());
        this.game.beginTurn();
    }

    public static boolean canAttack(int i) {
        boolean z;
        if (GameData.fleets.isFleetInSystem(8, i)) {
            Fleet fleetInSystem = GameData.fleets.getFleetInSystem(8, i);
            if (fleetInSystem.hasCombatShips()) {
                Iterator<Ship> it = fleetInSystem.getCombatShips().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it.next().hasRetreated()) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    if (GameData.fleets.getFleetsInSystem(i).size() > 1) {
                        return true;
                    }
                    StarSystem starSystem = GameData.galaxy.getStarSystem(i);
                    for (int i2 = 0; i2 < 5; i2++) {
                        if (starSystem.isOccupied(i2) && starSystem.getOccupier(i2) != 8) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private void executeSpaceBattle() {
        if (this.game.empires.get(this.target.getEmpireID()).isHuman()) {
            showAutoAttackPrompt();
        } else {
            calculateSpaceBattleResult();
        }
    }

    private void showAutoAttackPrompt() {
        if (this.target.isFleet()) {
            this.game.getCurrentScene().changeScene(this.game.attackScene, new AttackSceneData(1, 8, this.target.getEmpireID(), this.target.getSystemID(), false));
            return;
        }
        boolean hasCombatShips = this.game.fleets.isFleetInSystem(this.target.getEmpireID(), this.systemID) ? this.game.fleets.getFleetInSystem(this.target.getEmpireID(), this.systemID).hasCombatShips() : false;
        boolean hasDefences = this.game.colonies.isColony(this.systemID, this.target.getOrbit()) ? this.game.colonies.getColony(this.systemID, this.target.getOrbit()).hasDefences() : false;
        if (!hasCombatShips && !hasDefences) {
            attackPlanet();
        } else {
            this.game.getCurrentScene().changeScene(this.game.attackScene, new AttackSceneData(2, 8, this.target.getEmpireID(), this.target.getSystemID(), this.target.getOrbit(), false));
        }
    }

    private boolean spaceBattleNeeded() {
        int i = AnonymousClass1.f1335a[this.target.getAttackTargetType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return false;
            }
            if (this.game.colonies.isColony(this.systemID, this.target.getOrbit())) {
                Colony colony = this.game.colonies.getColony(this.systemID, this.target.getOrbit());
                if (colony.hasDefences()) {
                    return true;
                }
                if (this.game.fleets.isFleetInSystem(colony.getEmpireID(), this.systemID)) {
                    return this.game.fleets.getFleetInSystem(colony.getEmpireID(), this.target.getSystemID()).hasCombatShips();
                }
            } else if (this.game.outposts.isOutpost(this.systemID, this.target.getOrbit())) {
                Outpost outpost = this.game.outposts.getOutpost(this.systemID, this.target.getOrbit());
                if (this.game.fleets.isFleetInSystem(outpost.getEmpireID(), this.systemID)) {
                    return this.game.fleets.getFleetInSystem(outpost.getEmpireID(), this.target.getSystemID()).hasCombatShips();
                }
            }
            return false;
        }
        return true;
    }

    public void execute() {
        if (this.target.isNone()) {
            this.game.beginTurn();
        } else if (this.game.events.getEvents(this.target.getEmpireID(), EventType.ASCENDED_ENCOUNTER).size() > 0 && this.game.fleets.isFleetInSystem(this.target.getEmpireID(), this.target.getSystemID())) {
            Fleet fleetInSystem = this.game.fleets.getFleetInSystem(this.target.getEmpireID(), this.target.getSystemID());
            fleetInSystem.move(this.game.galaxy.getClosestEmpireSystem(this.target.getSystemID(), this.game.empires.get(fleetInSystem.empireID).getFriendlyStarSystems()));
            fleetInSystem.set(true, false);
            this.game.beginTurn();
        } else if (this.spaceBattle) {
            executeSpaceBattle();
        } else {
            attackPlanet();
        }
    }
}
