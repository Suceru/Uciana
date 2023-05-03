package com.birdshel.Uciana.AI;

import com.birdshel.Uciana.AI.AIObjects.AttackTarget;
import com.birdshel.Uciana.AI.AIObjects.AttackTargetType;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Scenes.AttackSceneData;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AIAttack {
    private final Empire empire;
    private final Game game;
    private final boolean spaceBattle;
    private final int systemID;
    private final AttackTarget target;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.AIAttack$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1334a;

        static {
            int[] iArr = new int[AttackTargetType.values().length];
            f1334a = iArr;
            try {
                iArr[AttackTargetType.FLEET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1334a[AttackTargetType.SYSTEM_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1334a[AttackTargetType.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public AIAttack(Game game, int i, int i2) {
        this.game = game;
        this.systemID = i2;
        Empire empire = game.empires.get(i);
        this.empire = empire;
        this.target = empire.getAttackAI().getAttackTarget(i2);
        this.spaceBattle = spaceBattleNeeded();
    }

    private void attackPlanet() {
        PlanetAttack planetAttack = new PlanetAttack(this.empire.id, this.target.getSystemID(), this.target.getOrbit());
        if (planetAttack.attack()) {
            if (this.game.empires.get(this.target.getEmpireID()).isHuman()) {
                this.game.getCurrentScene().changeScene(this.game.attackScene, new AttackSceneData(0, this.empire.id, this.target.getEmpireID(), this.target.getSystemID(), this.target.getOrbit(), false));
                this.game.attackScene.showAIPlanetAttackResult(planetAttack);
                return;
            }
            this.game.beginTurn();
            return;
        }
        this.game.beginTurn();
    }

    private void calculateSpaceBattleResult() {
        new AutoBattle(this.game, false).set(this.systemID, this.empire.id, this.target.getEmpireID());
        this.game.beginTurn();
    }

    private void executeSpaceBattle() {
        if (!GameProperties.isNonNormalEmpire(this.target.getEmpireID()) && this.game.empires.get(this.target.getEmpireID()).isHuman()) {
            showAutoAttackPrompt();
        } else {
            calculateSpaceBattleResult();
        }
    }

    private void showAutoAttackPrompt() {
        if (this.target.isFleet()) {
            this.game.getCurrentScene().changeScene(this.game.attackScene, new AttackSceneData(1, this.empire.id, this.target.getEmpireID(), this.target.getSystemID(), false));
            return;
        }
        boolean hasCombatShips = this.game.fleets.isFleetInSystem(this.target.getEmpireID(), this.systemID) ? this.game.fleets.getFleetInSystem(this.target.getEmpireID(), this.systemID).hasCombatShips() : false;
        boolean hasDefences = this.game.colonies.isColony(this.systemID, this.target.getOrbit()) ? this.game.colonies.getColony(this.systemID, this.target.getOrbit()).hasDefences() : false;
        if (!hasCombatShips && !hasDefences) {
            attackPlanet();
        } else {
            this.game.getCurrentScene().changeScene(this.game.attackScene, new AttackSceneData(2, this.empire.id, this.target.getEmpireID(), this.target.getSystemID(), this.target.getOrbit(), false));
        }
    }

    private boolean spaceBattleNeeded() {
        int i = AnonymousClass1.f1334a[this.target.getAttackTargetType().ordinal()];
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
        } else if (this.spaceBattle) {
            executeSpaceBattle();
        } else {
            attackPlanet();
        }
    }
}
