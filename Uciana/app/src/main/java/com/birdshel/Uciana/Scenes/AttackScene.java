package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.AI.AutoBattle;
import com.birdshel.Uciana.AI.PlanetAttack;
import com.birdshel.Uciana.Colonies.BombingTarget;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.Battle.BattleLosses;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.AutoBattleSummaryOverlay;
import com.birdshel.Uciana.Overlays.InvasionOverlay;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.PlanetAttackSummaryOverlay;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.StarSystems.SunSprite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ScaleAtModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AttackScene extends ExtendedScene {
    private TiledSprite actionBarBackground;
    private TiledSprite actionBarBanner;
    private boolean alreadyDone;
    private TiledSprite asteroidBeltSprite;
    private boolean atPlanet;
    private TiledSprite attackerBackground;
    private TiledSprite attackerBanner;
    private int attackerID;
    private Text attackerInfantryCount;
    private TiledSprite attackerInfantryIcon;
    private BattleLosses attackerLosses;
    private Text attackerName;
    private boolean attackingPlanet;
    private AutoBattleSummaryOverlay autoBattleSummaryOverlay;
    private TreeMap<ShipComponentID, Integer> availableBombs;
    private int availableTroops;
    private boolean bombard;
    private int bombardCount;
    private TiledSprite bombingDefenseIcon;
    private Text bombingDefensePercent;
    private VertexBufferObjectManager bufferManager;
    private Text buildingCount;
    private TiledSprite buildingIcon;
    private TiledSprite capitalIcon;
    private TiledSprite defenderBackground;
    private TiledSprite defenderBanner;
    private int defenderID;
    private Text defenderInfantryCount;
    private TiledSprite defenderInfantryIcon;
    private BattleLosses defenderLosses;
    private Text defenderName;
    private boolean didAttack;
    private Text displayText;
    private InvasionOverlay invasionOverlay;
    private boolean isAttacker;
    private boolean isTargetDestroyed;
    private Text message;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private int orbit;
    private TiledSprite outpost;
    private PlanetAttackSummaryOverlay planetAttackSummaryOverlay;
    private PlanetSprite planetSprite;
    private TiledSprite popIcon;
    private Sprite populationBar;
    private Sprite populationBarEmpty;
    private Text populationPercentString;
    private Text populationString;
    private TiledSprite showAttackersShipsButton;
    private TiledSprite showDefendersShipsButton;
    private TiledSprite starBase;
    private TiledSprite starBaseInfoIcon;
    private StarSystem starSystem;
    private long startTime;
    private int structureArmor;
    private int structureHitPoints;
    private SunSprite sunSprite;
    private SystemObject systemObject;
    private TiledSprite torpedoTurret1;
    private TiledSprite torpedoTurret2;
    private TiledSprite torpedoTurretIcon;
    private final TiledSprite[] actionButtons = new TiledSprite[8];
    private final TiledSprite[] actionButtonIcons = new TiledSprite[8];
    private final Text[] actionButtonCounts = new Text[8];
    private final List<Entity> explosionList = new ArrayList();
    private final TiledSprite[] attackerShipIcons = new TiledSprite[8];
    private final TiledSprite[] defenderShipIcons = new TiledSprite[8];
    private final TiledSprite[] attackerShipTypeIcons = new TiledSprite[8];
    private final TiledSprite[] defenderShipTypeIcons = new TiledSprite[8];
    private final Text[] attackerShipCounts = new Text[8];
    private final Text[] defenderShipCounts = new Text[8];
    private boolean showNonCombatsShipBeingDestroyed = false;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.AttackScene$2 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1423a;
        static final /* synthetic */ int[] b;

        /* renamed from: c */
        static final /* synthetic */ int[] f1424c;

        static {
            int[] iArr = new int[BombingTarget.values().length];
            f1424c = iArr;
            try {
                iArr[BombingTarget.POPULATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1424c[BombingTarget.BUILDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1424c[BombingTarget.MILITARY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[ButtonsEnum.values().length];
            b = iArr2;
            try {
                iArr2[ButtonsEnum.ATTACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[ButtonsEnum.AUTO_ATTACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[ButtonsEnum.WEAPON.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[ButtonsEnum.BOMBARD.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[ButtonsEnum.INVADE.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[ButtonsEnum.CLOSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[SystemObjectType.values().length];
            f1423a = iArr3;
            try {
                iArr3[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1423a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1423a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public AttackScene(Game game) {
        this.B = game;
    }

    private void bombColony(Colony colony, Weapon weapon, int i, BombingTarget bombingTarget) {
        TiledSprite[] tiledSpriteArr;
        if (bombingTarget == BombingTarget.MISS) {
            return;
        }
        int i2 = this.structureHitPoints;
        int i3 = i2 - i;
        this.structureHitPoints = i3;
        if (i3 > 0) {
            return;
        }
        int i4 = i - i2;
        this.structureHitPoints = this.structureArmor;
        int i5 = AnonymousClass2.f1424c[bombingTarget.ordinal()];
        if (i5 == 1) {
            colony.populationBombed();
        } else if (i5 == 2) {
            colony.buildingBombed();
            if (!colony.isShielded()) {
                for (TiledSprite tiledSprite : this.actionButtons) {
                    if (tiledSprite.getCurrentTileIndex() == ButtonsEnum.INVADE.ordinal()) {
                        tiledSprite.setAlpha(1.0f);
                    }
                }
            }
        } else if (i5 == 3) {
            colony.militaryBombed();
        }
        if (colony.isAlive()) {
            bombColony(colony, weapon, i4, getTargetHit(colony, weapon, false));
        }
    }

    private String calculateDamage(ShipComponentID shipComponentID) {
        Colony colony = this.systemObject.getColony();
        Weapon weapon = (Weapon) ShipComponents.get(shipComponentID);
        int damage = weapon.getDamage(this.attackerID);
        if (colony.isShielded()) {
            damage -= (int) (damage * colony.getShieldingStrength());
        }
        BombingTarget targetHit = getTargetHit(colony, weapon, true);
        bombColony(colony, weapon, damage, targetHit);
        setColony();
        if (!colony.isAlive()) {
            colonyDestroyed(colony);
            if (!this.B.empires.get(this.defenderID).isAlive()) {
                Game.gameAchievements.killOffAnEmpire();
            }
        }
        if (targetHit == BombingTarget.MISS) {
            return !colony.isAlive() ? "" : this.B.getActivity().getString(R.string.attack_miss);
        }
        return Integer.toString(damage);
    }

    private void checkActionUp(Point point) {
        TiledSprite[] tiledSpriteArr;
        if (isClicked(this.showAttackersShipsButton, point)) {
            showShipsButtonPressed(this.attackerID);
        }
        if (isClicked(this.showDefendersShipsButton, point)) {
            showShipsButtonPressed(this.defenderID);
        }
        int i = 0;
        for (TiledSprite tiledSprite : this.actionButtons) {
            if (isClicked(tiledSprite, point)) {
                doAction(tiledSprite.getCurrentTileIndex(), i);
                return;
            }
            i++;
        }
    }

    private boolean checkRelationStatus(String str) {
        return checkRelationStatus(str, ShipComponentID.NO_WEAPON);
    }

    private void closeButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        if (this.didAttack) {
            if (this.isTargetDestroyed) {
                this.B.fleets.getFleetInSystem(this.attackerID, this.starSystem.getID()).checkForChanceToAttack(this.starSystem.getID(), this.defenderID);
            } else {
                this.B.events.addSelectAttackEvent(this.attackerID, this.starSystem.getID());
            }
            this.B.beginTurn();
            return;
        }
        changeScene(this.B.selectAttackScene, new Point(this.attackerID, this.starSystem.getID()));
    }

    private void colonyDestroyed(Colony colony) {
        int empireID = colony.getEmpireID();
        this.B.colonies.removeColony(colony);
        this.B.empires.get(empireID).checkForCapital();
        this.populationBar.setVisible(false);
        this.buildingCount.setVisible(false);
        this.buildingIcon.setVisible(false);
        this.populationBarEmpty.setVisible(false);
        this.populationPercentString.setVisible(false);
        this.populationBar.setVisible(false);
        this.popIcon.setVisible(false);
        this.populationString.setVisible(false);
        this.defenderInfantryCount.setVisible(false);
        this.defenderInfantryIcon.setVisible(false);
        this.bombingDefensePercent.setVisible(false);
        this.bombingDefenseIcon.setVisible(false);
        defenderDestroyed();
    }

    private void defenderDestroyed() {
        TiledSprite[] tiledSpriteArr;
        this.isTargetDestroyed = true;
        int i = 0;
        for (TiledSprite tiledSprite : this.actionButtons) {
            if (tiledSprite.getCurrentTileIndex() != ButtonsEnum.CLOSE.ordinal()) {
                tiledSprite.setAlpha(0.4f);
                this.actionButtonIcons[i].setAlpha(0.4f);
                this.actionButtonCounts[i].setAlpha(0.4f);
            }
            i++;
        }
        if (this.attackingPlanet) {
            if (this.systemObject.hasColony() || this.systemObject.hasOutpost()) {
                return;
            }
            this.defenderBanner.setVisible(false);
            this.defenderName.setVisible(false);
            this.defenderBackground.setVisible(false);
            return;
        }
        this.defenderBanner.setVisible(false);
        this.defenderName.setVisible(false);
        this.defenderBackground.setVisible(false);
    }

    private void destroyNonCombatShips(Fleet fleet) {
        this.showDefendersShipsButton.setVisible(false);
        ArrayList<String> arrayList = new ArrayList();
        for (Ship ship : fleet.getNonCombatShips()) {
            this.defenderLosses.addLoss(ship);
            arrayList.add(ship.getID());
        }
        for (String str : arrayList) {
            fleet.removeShip(str);
        }
        if (fleet.isEmpty()) {
            this.B.fleets.remove(fleet);
        }
        showShipExplosions();
    }

    private void doAction(int i, int i2) {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        switch (AnonymousClass2.b[ButtonsEnum.values()[i].ordinal()]) {
            case 1:
                if (requireSpaceBattle()) {
                    if (checkRelationStatus("attack")) {
                        attackFleet();
                        return;
                    }
                    return;
                } else if (checkRelationStatus("destroyNonCombat")) {
                    destroyNonCombat();
                    return;
                } else {
                    return;
                }
            case 2:
                if (checkRelationStatus("autoAttack")) {
                    autoAttackFleet();
                    return;
                }
                return;
            case 3:
                for (Weapon weapon : ShipComponents.getWeapons()) {
                    if (this.actionButtonIcons[i2].getCurrentTileIndex() == weapon.getIconIndex()) {
                        ShipComponentID id = weapon.getID();
                        if (checkRelationStatus("bomb", id)) {
                            bombPlanet(id);
                            if (id == ShipComponentID.BIO_BOMB) {
                                Game.gameAchievements.usedBioWeapon();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
                return;
            case 4:
                if (checkRelationStatus("bombard")) {
                    bombardPlanet();
                    return;
                }
                return;
            case 5:
                if (checkRelationStatus("invade")) {
                    invadePlanet();
                    return;
                }
                return;
            case 6:
                closeButtonPressed();
                return;
            default:
                return;
        }
    }

    public void explosionFinished() {
        if (this.alreadyDone) {
            return;
        }
        this.alreadyDone = true;
        setFleets();
        if (this.attackingPlanet) {
            if (this.B.empires.get(this.attackerID).isHuman()) {
                defenderDestroyed();
            } else {
                int i = this.attackerID;
                showAutoResult(i, i, this.defenderID, this.attackerLosses, this.defenderLosses);
            }
        } else {
            defenderDestroyed();
            if (GameProperties.isNonNormalEmpire(this.attackerID) || this.B.empires.get(this.attackerID).isAI()) {
                int i2 = this.attackerID;
                showAutoResult(i2, i2, this.defenderID, this.attackerLosses, this.defenderLosses);
            }
        }
        setActionBar();
    }

    private void figureHumanNextStep(boolean z) {
        if (z) {
            O();
        } else {
            this.B.beginTurn();
        }
    }

    private float getActionBarWidth() {
        float widthScaled = this.attackerInfantryIcon.isVisible() ? this.attackerInfantryIcon.getWidthScaled() + 20.0f + this.attackerInfantryCount.getWidthScaled() + 5.0f + 100.0f : 100.0f;
        for (TiledSprite tiledSprite : this.actionButtons) {
            if (tiledSprite.isVisible()) {
                widthScaled += 120.0f;
            }
        }
        return widthScaled;
    }

    private int getAvailableActionIndex() {
        TiledSprite[] tiledSpriteArr = this.actionButtons;
        int length = tiledSpriteArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length && tiledSpriteArr[i2].isVisible(); i2++) {
            i++;
        }
        return i;
    }

    private int getBombCount() {
        int i = 0;
        for (Map.Entry<ShipComponentID, Integer> entry : this.availableBombs.entrySet()) {
            i += entry.getValue().intValue();
        }
        return i;
    }

    private String getHeaderName(int i) {
        if (i == 8) {
            return this.B.getActivity().getString(R.string.empire_name_ascended);
        }
        if (i == 9) {
            return this.B.getActivity().getString(R.string.empire_name_monster);
        }
        return this.B.empires.get(i).getName();
    }

    private BombingTarget getTargetHit(Colony colony, Weapon weapon, boolean z) {
        ArrayList<BombingTarget> arrayList = new ArrayList();
        if (z) {
            arrayList.add(BombingTarget.MISS);
        }
        if (colony.getPopulation() > 0) {
            arrayList.add(BombingTarget.POPULATION);
        }
        if (colony.getInfDivisions() != 0) {
            arrayList.add(BombingTarget.MILITARY);
        }
        if (weapon.getID() != ShipComponentID.BIO_BOMB && !colony.getBuildings().isEmpty()) {
            arrayList.add(BombingTarget.BUILDING);
        }
        if (arrayList.isEmpty()) {
            arrayList.add(BombingTarget.NONE);
        }
        int size = arrayList.size();
        int i = 0;
        int[] iArr = size != 2 ? size != 3 ? size != 4 ? new int[]{100} : new int[]{25, 25, 25, 25} : new int[]{34, 33, 33} : new int[]{50, 50};
        HashMap hashMap = new HashMap();
        for (BombingTarget bombingTarget : arrayList) {
            hashMap.put(bombingTarget, Integer.valueOf(iArr[i]));
            i++;
        }
        return (BombingTarget) Functions.getItemByPercent(hashMap);
    }

    private void hideButtons() {
        for (TiledSprite tiledSprite : this.actionButtons) {
            tiledSprite.setVisible(false);
        }
        for (TiledSprite tiledSprite2 : this.actionButtonIcons) {
            tiledSprite2.setVisible(false);
        }
        for (Text text : this.actionButtonCounts) {
            text.setVisible(false);
        }
    }

    private void hideShipIcons() {
        ShipType[] values;
        for (ShipType shipType : ShipType.values()) {
            if (!shipType.isStation()) {
                this.attackerShipIcons[shipType.ordinal()].setVisible(false);
                this.attackerShipTypeIcons[shipType.ordinal()].setVisible(false);
                this.attackerShipCounts[shipType.ordinal()].setVisible(false);
                this.defenderShipIcons[shipType.ordinal()].setVisible(false);
                this.defenderShipTypeIcons[shipType.ordinal()].setVisible(false);
                this.defenderShipCounts[shipType.ordinal()].setVisible(false);
            }
        }
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        this.autoBattleSummaryOverlay.releasePoolElements();
        extendedScene.getPoolElements();
        P(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void placeActionButtons() {
        Text[] textArr;
        float x = this.actionBarBanner.getX() + this.actionBarBanner.getWidthScaled();
        if (this.attackerInfantryIcon.isVisible()) {
            x = this.attackerInfantryIcon.getX() + this.attackerInfantryIcon.getWidthScaled() + 10.0f;
        }
        float f2 = 30.0f + x;
        for (TiledSprite tiledSprite : this.actionButtons) {
            tiledSprite.setX(x);
            x += 120.0f;
        }
        for (TiledSprite tiledSprite2 : this.actionButtonIcons) {
            tiledSprite2.setX(f2);
            f2 += 120.0f;
        }
        for (Text text : this.actionButtonCounts) {
            text.setX((x + 120.0f) - text.getWidthScaled());
            text.setY(716.0f - text.getHeightScaled());
        }
    }

    private boolean requireSpaceBattle() {
        if (this.B.fleets.isFleetInSystem(this.defenderID, this.starSystem.getID()) && this.B.fleets.getFleetInSystem(this.defenderID, this.starSystem.getID()).hasCombatShips()) {
            return true;
        }
        return systemObjectHasDefenses();
    }

    private void setAsteroidBelt() {
        this.asteroidBeltSprite.setPosition(800.0f, 0.0f);
        this.asteroidBeltSprite.setCurrentTileIndex(((AsteroidBelt) this.systemObject).getImageIndex());
        this.asteroidBeltSprite.setScale(2.0f);
        this.asteroidBeltSprite.setVisible(true);
    }

    private void setAttackButton() {
        int availableActionIndex = getAvailableActionIndex();
        this.actionButtons[availableActionIndex].setCurrentTileIndex(ButtonsEnum.ATTACK.ordinal());
        this.actionButtons[availableActionIndex].setVisible(true);
    }

    private void setAttackButtons() {
        if (GameData.gameSettings.onlyAllowAutoAttack()) {
            setOnlyAutoAttackButton();
            return;
        }
        int availableActionIndex = getAvailableActionIndex();
        this.actionButtons[availableActionIndex].setCurrentTileIndex(ButtonsEnum.ATTACK.ordinal());
        this.actionButtons[availableActionIndex].setVisible(true);
        int i = availableActionIndex + 1;
        this.actionButtons[i].setCurrentTileIndex(ButtonsEnum.AUTO_ATTACK.ordinal());
        this.actionButtons[i].setVisible(true);
    }

    private void setAttackerButtons() {
        this.actionBarBackground.setX(10.0f);
        this.actionBarBanner.setX(10.0f);
        if (this.attackingPlanet) {
            if (!this.B.fleets.isFleetInSystem(this.attackerID, this.starSystem.getID())) {
                setCloseButton();
                return;
            }
            Fleet fleetInSystem = this.B.fleets.getFleetInSystem(this.attackerID, this.starSystem.getID());
            updateBombButtons();
            int troopCount = fleetInSystem.getTroopCount();
            this.availableTroops = troopCount;
            if (troopCount > 0) {
                this.attackerInfantryCount.setText(Integer.toString(troopCount));
                this.attackerInfantryCount.setVisible(true);
                this.attackerInfantryCount.setX(120.0f);
                this.attackerInfantryCount.setY(678.0f - (this.defenderInfantryCount.getHeightScaled() / 2.0f));
                this.attackerInfantryIcon.setVisible(true);
                this.attackerInfantryIcon.setX(this.attackerInfantryCount.getX() + this.attackerInfantryCount.getWidthScaled() + 5.0f);
                this.attackerInfantryIcon.setY(this.attackerInfantryCount.getY() - 3.0f);
            } else {
                this.attackerInfantryCount.setVisible(false);
                this.attackerInfantryIcon.setVisible(false);
            }
        }
        boolean isFleetInSystem = this.B.fleets.isFleetInSystem(this.defenderID, this.starSystem.getID());
        if (isFleetInSystem) {
            isFleetInSystem = this.B.fleets.getFleetInSystem(this.defenderID, this.starSystem.getID()).hasNonRetreatedShips();
        }
        if (isFleetInSystem) {
            if (this.B.fleets.getFleetInSystem(this.defenderID, this.starSystem.getID()).hasCombatShips()) {
                setAttackButtons();
            } else if (this.attackingPlanet) {
                if (this.systemObject.hasColony()) {
                    if (this.systemObject.getColony().hasDefences()) {
                        setAttackButtons();
                    } else {
                        setAttackButton();
                    }
                } else {
                    setAttackButton();
                }
            } else {
                setAttackButton();
            }
        } else if (this.attackingPlanet) {
            if (this.systemObject.hasColony()) {
                if (this.systemObject.getColony().hasDefences()) {
                    setAttackButtons();
                } else {
                    setBombButtons();
                    setInvadeButton();
                }
            } else {
                setAttackButton();
                if (!this.systemObject.hasOutpost()) {
                    this.actionButtons[0].setAlpha(0.4f);
                }
            }
        }
        setCloseButton();
    }

    private void setBackground() {
        this.sunSprite.set(this.starSystem.getStarType());
        this.nebulas.set(this.starSystem.getID());
    }

    private void setBombButtons() {
        int availableActionIndex = getAvailableActionIndex();
        for (Map.Entry<ShipComponentID, Integer> entry : this.availableBombs.entrySet()) {
            this.actionButtons[availableActionIndex].setVisible(true);
            this.actionButtons[availableActionIndex].setCurrentTileIndex(ButtonsEnum.WEAPON.ordinal());
            this.actionButtonIcons[availableActionIndex].setVisible(true);
            this.actionButtonIcons[availableActionIndex].setCurrentTileIndex(ShipComponents.get(entry.getKey()).getIconIndex());
            this.actionButtonCounts[availableActionIndex].setVisible(true);
            if (entry.getKey() == ShipComponentID.BIO_BOMB && this.systemObject.getColony().isShielded()) {
                this.actionButtons[availableActionIndex].setAlpha(0.4f);
                this.actionButtonIcons[availableActionIndex].setAlpha(0.4f);
                this.actionButtonCounts[availableActionIndex].setAlpha(0.4f);
            }
            availableActionIndex++;
        }
        if (getBombCount() > 1) {
            this.actionButtons[availableActionIndex].setVisible(true);
            this.actionButtons[availableActionIndex].setCurrentTileIndex(ButtonsEnum.BOMBARD.ordinal());
            this.actionButtonCounts[availableActionIndex].setVisible(true);
            this.actionButtonCounts[availableActionIndex].setText("000000");
        }
    }

    private void setCloseButton() {
        int availableActionIndex = getAvailableActionIndex();
        this.actionButtons[availableActionIndex].setVisible(true);
        this.actionButtons[availableActionIndex].setCurrentTileIndex(ButtonsEnum.CLOSE.ordinal());
    }

    private void setDefenderButtons() {
        setAttackButtons();
        float width = (getWidth() / 2.0f) - (getActionBarWidth() / 2.0f);
        this.actionBarBackground.setX(width);
        this.actionBarBanner.setX(width);
    }

    private void setFleet(boolean z, int i) {
        boolean isFleetInSystem = this.B.fleets.isFleetInSystem(i, this.starSystem.getID());
        if (isFleetInSystem) {
            isFleetInSystem = this.B.fleets.getFleetInSystem(i, this.starSystem.getID()).hasNonRetreatedShips();
        }
        if (!isFleetInSystem) {
            if (z) {
                return;
            }
            this.showDefendersShipsButton.setVisible(false);
            return;
        }
        Fleet fleetInSystem = this.B.fleets.getFleetInSystem(i, this.starSystem.getID());
        int[] countOfEachType = fleetInSystem.getCountOfEachType(false);
        ShipType[] shipTypeArr = {ShipType.CRUISER, ShipType.DREADNOUGHT, ShipType.BATTLESHIP, ShipType.DESTROYER};
        ShipType[] shipTypeArr2 = {ShipType.CONSTRUCTION, ShipType.COLONY, ShipType.TRANSPORT, ShipType.SCOUT};
        if (z) {
            this.showAttackersShipsButton.setVisible(true);
            setShipColumns(fleetInSystem, countOfEachType, fleetInSystem.getCombatShipTypeCount(false), true, shipTypeArr, 375.0f, this.attackerShipIcons, this.attackerShipTypeIcons, this.attackerShipCounts);
            setShipColumns(fleetInSystem, countOfEachType, fleetInSystem.getNonCombatShipTypeCount(), true, shipTypeArr2, 155.0f, this.attackerShipIcons, this.attackerShipTypeIcons, this.attackerShipCounts);
            return;
        }
        this.showDefendersShipsButton.setVisible(true);
        setShipColumns(fleetInSystem, countOfEachType, fleetInSystem.getCombatShipTypeCount(false), false, shipTypeArr, 775.0f, this.defenderShipIcons, this.defenderShipTypeIcons, this.defenderShipCounts);
        setShipColumns(fleetInSystem, countOfEachType, fleetInSystem.getNonCombatShipTypeCount(), false, shipTypeArr2, 955.0f, this.defenderShipIcons, this.defenderShipTypeIcons, this.defenderShipCounts);
    }

    private void setHeader(String str) {
        this.displayText.setText(str);
        float width = getWidth() / 2.0f;
        if (this.atPlanet) {
            SystemObject systemObject = this.starSystem.getSystemObject(this.orbit);
            this.systemObject = systemObject;
            if (systemObject.hasColony() && this.systemObject.getColony().hasBuilding(BuildingID.CAPITAL)) {
                this.capitalIcon.setVisible(true);
                this.capitalIcon.setX((((getWidth() / 2.0f) - 15.0f) - (this.displayText.getWidthScaled() / 2.0f)) - 15.0f);
                width = (getWidth() / 2.0f) + 15.0f;
            }
        }
        Text text = this.displayText;
        text.setX(width - (text.getWidthScaled() / 2.0f));
        this.attackerBackground.setCurrentTileIndex(this.attackerID);
        this.attackerBackground.setWidth(getWidth() / 2.0f);
        this.attackerBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.attackerID));
        this.attackerName.setText(getHeaderName(this.attackerID));
        this.defenderBackground.setCurrentTileIndex(this.defenderID);
        this.defenderBackground.setVisible(true);
        this.defenderBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.defenderID));
        this.defenderBanner.setVisible(true);
        this.defenderName.setText(getHeaderName(this.defenderID));
        this.defenderName.setVisible(true);
    }

    private void setInvadeButton() {
        int availableActionIndex = getAvailableActionIndex();
        if (this.availableTroops > 0) {
            this.actionButtons[availableActionIndex].setVisible(true);
            this.actionButtons[availableActionIndex].setCurrentTileIndex(ButtonsEnum.INVADE.ordinal());
            if (this.systemObject.getColony().isShielded()) {
                this.actionButtons[availableActionIndex].setAlpha(0.4f);
            }
        }
    }

    private void setOnlyAutoAttackButton() {
        int availableActionIndex = getAvailableActionIndex();
        this.actionButtons[availableActionIndex].setCurrentTileIndex(ButtonsEnum.AUTO_ATTACK.ordinal());
        this.actionButtons[availableActionIndex].setVisible(true);
    }

    private void setOrbit() {
        int i = AnonymousClass2.f1423a[this.systemObject.getSystemObjectType().ordinal()];
        if (i == 1) {
            Planet planet = (Planet) this.systemObject;
            this.planetSprite.setVisible(true);
            this.planetSprite.setPlanet(planet, planet.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene), false);
        } else if (i == 2) {
            GasGiant gasGiant = (GasGiant) this.systemObject;
            this.planetSprite.setVisible(true);
            this.planetSprite.setGasGiant(gasGiant, gasGiant.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene), false);
        } else if (i == 3) {
            setAsteroidBelt();
        }
        if (this.systemObject.hasColony()) {
            setColony();
        } else if (this.systemObject.hasOutpost()) {
            setOutpost();
        }
    }

    private void setOutpost() {
        this.outpost.setVisible(true);
    }

    private void setPopulationBar(Colony colony) {
        float maxPopulation = colony.getPlanet().getMaxPopulation() + 60;
        float x = (this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - (maxPopulation / 2.0f);
        this.populationBar.setVisible(true);
        this.populationBar.setPosition(x, 475.0f);
        this.populationBar.setSize(maxPopulation, 75.0f);
        int population = colony.getPopulation();
        int maxPopulation2 = colony.getPlanet().getMaxPopulation();
        float f2 = population / maxPopulation2;
        this.populationString.setVisible(true);
        this.populationString.setText(population + "m / " + maxPopulation2 + "m ");
        this.populationString.setPosition((this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - (this.populationString.getWidth() / 2.0f), 555.0f);
        this.populationPercentString.setVisible(true);
        String num = Integer.toString((int) (100.0f * f2));
        this.populationPercentString.setText(num + "%");
        this.populationPercentString.setPosition((this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - (this.populationPercentString.getWidth() / 2.0f), 500.0f);
        if (f2 != 1.0f) {
            float f3 = f2 * maxPopulation;
            this.populationBarEmpty.setPosition(x + f3, 489.0f);
            this.populationBarEmpty.setSize((maxPopulation - f3) - 2.0f, 45.0f);
            this.populationBarEmpty.setVisible(true);
        } else {
            this.populationBarEmpty.setVisible(false);
        }
        this.popIcon.setVisible(true);
        this.popIcon.setX((this.populationBar.getX() - this.popIcon.getWidthScaled()) - 5.0f);
    }

    private void setShipColumns(Fleet fleet, int[] iArr, int i, boolean z, ShipType[] shipTypeArr, float f2, TiledSprite[] tiledSpriteArr, TiledSprite[] tiledSpriteArr2, Text[] textArr) {
        float f3 = (590 - (i * WeaponStats.NOVA_BOMB_MAX_DAMAGE)) / 2.0f;
        int i2 = z ? this.attackerID : this.defenderID;
        int i3 = 0;
        for (ShipType shipType : shipTypeArr) {
            if (iArr[shipType.id] != 0) {
                tiledSpriteArr[shipType.ordinal()].setVisible(true);
                tiledSpriteArr[shipType.ordinal()].setX(f2);
                tiledSpriteArr[shipType.ordinal()].setY((i3 * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + 86 + f3 + ((150.0f - tiledSpriteArr[shipType.ordinal()].getHeightScaled()) / 2.0f));
                int icon = shipType.getIcon(i2, fleet.getHullDesignForShipType(shipType));
                tiledSpriteArr[shipType.ordinal()].setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[i2]);
                tiledSpriteArr[shipType.ordinal()].setCurrentTileIndex(icon);
                tiledSpriteArr2[shipType.ordinal()].setVisible(true);
                float x = tiledSpriteArr[shipType.ordinal()].getX() - 30.0f;
                if (!z) {
                    x = tiledSpriteArr[shipType.ordinal()].getX() + tiledSpriteArr[shipType.ordinal()].getWidthScaled();
                }
                tiledSpriteArr2[shipType.ordinal()].setPosition(x, tiledSpriteArr[shipType.ordinal()].getY());
                tiledSpriteArr2[shipType.ordinal()].setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
                textArr[shipType.ordinal()].setVisible(true);
                textArr[shipType.ordinal()].setText(Integer.toString(iArr[shipType.id]));
                float x2 = (tiledSpriteArr[shipType.ordinal()].getX() - 10.0f) - textArr[shipType.ordinal()].getWidthScaled();
                if (!z) {
                    x2 = tiledSpriteArr2[shipType.ordinal()].getX() + 5.0f;
                }
                textArr[shipType.ordinal()].setPosition(x2, tiledSpriteArr[shipType.ordinal()].getY() + 30.0f);
                i3++;
            }
        }
    }

    private void setSpritesInvisible() {
        hideShipIcons();
        this.message.setVisible(false);
        this.planetSprite.setVisible(false);
        this.asteroidBeltSprite.setVisible(false);
        this.showDefendersShipsButton.setVisible(false);
        this.defenderInfantryCount.setVisible(false);
        this.defenderInfantryIcon.setVisible(false);
        this.bombingDefensePercent.setVisible(false);
        this.bombingDefenseIcon.setVisible(false);
        this.attackerInfantryCount.setVisible(false);
        this.attackerInfantryIcon.setVisible(false);
        this.populationBar.setVisible(false);
        this.populationBarEmpty.setVisible(false);
        this.popIcon.setVisible(false);
        this.populationPercentString.setVisible(false);
        this.populationString.setVisible(false);
        this.buildingCount.setVisible(false);
        this.buildingIcon.setVisible(false);
        for (TiledSprite tiledSprite : this.actionButtons) {
            tiledSprite.setAlpha(1.0f);
        }
        for (TiledSprite tiledSprite2 : this.actionButtonIcons) {
            tiledSprite2.setAlpha(1.0f);
        }
        for (Text text : this.actionButtonCounts) {
            text.setAlpha(1.0f);
        }
        this.outpost.setVisible(false);
        this.starBase.setVisible(false);
        this.starBaseInfoIcon.setVisible(false);
        this.torpedoTurret1.setVisible(false);
        this.torpedoTurret2.setVisible(false);
        this.torpedoTurretIcon.setVisible(false);
        this.capitalIcon.setVisible(false);
        w(this.explosionList, this);
    }

    private boolean shouldShowDefenderButtons() {
        if (systemObjectHasDefenses()) {
            return true;
        }
        if (this.B.fleets.isFleetInSystem(this.defenderID, this.starSystem.getID())) {
            if (this.B.fleets.getFleetInSystem(this.defenderID, this.starSystem.getID()).hasCombatShips()) {
                return true;
            }
            this.showNonCombatsShipBeingDestroyed = true;
            this.startTime = System.currentTimeMillis();
            return false;
        }
        return false;
    }

    private void showExplosion(Sprite sprite) {
        float widthScaled = sprite.getWidthScaled();
        AnimatedSprite animatedSprite = new AnimatedSprite(sprite.getX(), sprite.getY(), this.B.graphics.explosionTexture, this.bufferManager);
        animatedSprite.setSize(widthScaled, widthScaled);
        animatedSprite.animate(new long[]{105, 105, 105, 105, 105, 105, 105, 105, 105}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, false, new AnimatedSprite.IAnimationListener() { // from class: com.birdshel.Uciana.Scenes.AttackScene.1
            {
                AttackScene.this = this;
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFinished(AnimatedSprite animatedSprite2) {
                AttackScene.this.explosionFinished();
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFrameChanged(AnimatedSprite animatedSprite2, int i, int i2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationLoopFinished(AnimatedSprite animatedSprite2, int i, int i2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationStarted(AnimatedSprite animatedSprite2, int i) {
            }
        });
        animatedSprite.registerEntityModifier(new AlphaModifier(0.84f, 1.0f, 0.0f));
        animatedSprite.registerEntityModifier(new ScaleAtModifier(0.84f, 1.0f, 5.0f, sprite.getWidthScaled() / 2.0f, sprite.getHeightScaled() / 2.0f));
        this.explosionList.add(animatedSprite);
        attachChild(animatedSprite);
    }

    private void showOutpostExplosion() {
        hideButtons();
        this.outpost.setVisible(false);
        this.B.sounds.playExplosionSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        showExplosion(this.outpost);
    }

    private void showShipExplosions() {
        TiledSprite[] tiledSpriteArr;
        hideButtons();
        for (TiledSprite tiledSprite : this.defenderShipIcons) {
            if (tiledSprite.getX() >= getWidth() / 2.0f && tiledSprite.isVisible()) {
                tiledSprite.setVisible(false);
                this.B.sounds.playExplosionSound();
                Game game = this.B;
                game.vibrate(game.BUTTON_VIBRATE);
                showExplosion(tiledSprite);
            }
        }
    }

    private void showShipsButtonPressed(int i) {
        this.B.shipSelectOverlay.setOverlay(this.B.fleets.getFleetInSystem(i, this.starSystem.getID()).id);
        setChildScene(this.B.shipSelectOverlay, false, false, true);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private boolean systemObjectHasDefenses() {
        if (this.attackingPlanet && this.B.colonies.isColony(this.starSystem.getID(), this.orbit)) {
            return this.B.colonies.getColony(this.starSystem.getID(), this.orbit).hasDefences();
        }
        return false;
    }

    private void updateBombButtons() {
        TiledSprite[] tiledSpriteArr;
        int i;
        int i2 = 0;
        for (TiledSprite tiledSprite : this.actionButtonIcons) {
            if (this.actionButtons[i2].getCurrentTileIndex() == ButtonsEnum.WEAPON.ordinal()) {
                if (tiledSprite.isVisible()) {
                    Iterator<Weapon> it = ShipComponents.getWeapons().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Weapon next = it.next();
                        if (next.getIconIndex() == tiledSprite.getCurrentTileIndex()) {
                            if (this.availableBombs.containsKey(next.getID())) {
                                i = this.availableBombs.get(next.getID()).intValue();
                            }
                        }
                    }
                    i = 0;
                    this.actionButtonCounts[i2].setText(Integer.toString(i));
                    this.actionButtonCounts[i2].setX((this.actionButtons[i2].getX() + 100.0f) - this.actionButtonCounts[i2].getWidthScaled());
                    this.actionButtonCounts[i2].setY((this.actionButtons[i2].getY() + 75.0f) - this.actionButtonCounts[i2].getHeightScaled());
                    if (i == 0) {
                        this.actionButtons[i2].setAlpha(0.4f);
                        this.actionButtonIcons[i2].setAlpha(0.4f);
                        this.actionButtonCounts[i2].setAlpha(0.4f);
                    }
                }
            } else if (this.actionButtons[i2].getCurrentTileIndex() == ButtonsEnum.BOMBARD.ordinal()) {
                this.actionButtonCounts[i2].setText(Integer.toString(getBombCount()));
                this.actionButtonCounts[i2].setX((this.actionButtons[i2].getX() + 100.0f) - this.actionButtonCounts[i2].getWidthScaled());
                this.actionButtonCounts[i2].setY((this.actionButtons[i2].getY() + 75.0f) - this.actionButtonCounts[i2].getHeightScaled());
                if (getBombCount() == 0) {
                    this.actionButtons[i2].setAlpha(0.4f);
                    this.actionButtonIcons[i2].setAlpha(0.4f);
                    this.actionButtonCounts[i2].setAlpha(0.4f);
                }
            }
            i2++;
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public void M(boolean z) {
        if (this.attackingPlanet && z) {
            O();
            if (this.starSystem.getSystemObject(this.orbit).isOccupied()) {
                PlanetAttack planetAttack = new PlanetAttack(this.attackerID, this.starSystem.getID(), this.orbit);
                if (planetAttack.attack()) {
                    if (!(this.B.getCurrentScene() instanceof AttackScene)) {
                        this.B.getCurrentScene().changeScene(this.B.attackScene, new AttackSceneData());
                    }
                    showAIPlanetAttackResult(planetAttack);
                    return;
                }
            }
        }
        this.B.beginTurn();
    }

    public void N(int i, int i2, int i3, int i4) {
        this.attackerID = i;
        this.defenderID = i2;
        StarSystem starSystem = this.B.galaxy.getStarSystem(i3);
        this.starSystem = starSystem;
        this.orbit = i4;
        this.systemObject = starSystem.getSystemObject(i4);
        setSpritesInvisible();
        setBackground();
        setOrbit();
        setHeader(this.starSystem.getName() + " " + (i4 + 1));
        setFleets();
        hideButtons();
        this.actionBarBackground.setVisible(false);
        this.actionBarBanner.setVisible(false);
    }

    public void O() {
        if (this.B.fleets.isFleetInSystem(this.attackerID, this.starSystem.getID())) {
            this.availableBombs = this.B.fleets.getFleetInSystem(this.attackerID, this.starSystem.getID()).getBombs();
        }
        setSpritesInvisible();
        setBackground();
        String string = this.B.getActivity().getString(R.string.attack_system, new Object[]{this.starSystem.getName()});
        if (this.atPlanet) {
            SystemObject systemObject = this.starSystem.getSystemObject(this.orbit);
            this.systemObject = systemObject;
            if (systemObject.hasColony() || this.systemObject.hasOutpost()) {
                this.attackingPlanet = true;
            }
            string = this.starSystem.getName() + " " + (this.orbit + 1);
            setOrbit();
        }
        setHeader(string);
        setFleets();
        setActionBar();
    }

    protected void P(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof BattleScene) {
            if (this.attackingPlanet) {
                this.B.battleScene.set(this.starSystem.getID(), this.systemObject.getOrbit(), this.attackerID, this.defenderID);
            } else {
                this.B.battleScene.set(this.starSystem.getID(), this.attackerID, this.defenderID);
            }
        } else if (extendedScene instanceof SelectAttackScene) {
            Point point = (Point) obj;
            this.B.selectAttackScene.set((int) point.getX(), (int) point.getY());
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof TurnScene) {
            this.B.turnScene.set();
        } else if (extendedScene instanceof AttackScene) {
            AttackSceneData attackSceneData = (AttackSceneData) obj;
            int empireID = attackSceneData.getEmpireID();
            int targetID = attackSceneData.getTargetID();
            int systemID = attackSceneData.getSystemID();
            int orbit = attackSceneData.getOrbit();
            boolean isAttacker = attackSceneData.isAttacker();
            int type = attackSceneData.getType();
            if (type == 0) {
                this.B.attackScene.N(empireID, targetID, systemID, orbit);
            } else if (type == 1) {
                this.B.attackScene.set(isAttacker, empireID, targetID, systemID);
            } else if (type != 2) {
            } else {
                this.B.attackScene.set(isAttacker, empireID, targetID, systemID, orbit);
            }
        }
    }

    public void attackFleet() {
        this.didAttack = true;
        if ((this.B.fleets.isFleetInSystem(this.defenderID, this.starSystem.getID()) ? this.B.fleets.getFleetInSystem(this.defenderID, this.starSystem.getID()).hasNonRetreatedCombatShips() : false) || systemObjectHasDefenses()) {
            changeScene(this.B.battleScene);
        }
    }

    public void autoAttackFleet() {
        this.didAttack = true;
        AutoBattle autoBattle = new AutoBattle(this.B, true);
        if (this.attackingPlanet) {
            autoBattle.set(this.starSystem.getID(), this.orbit, this.attackerID, this.defenderID);
        } else {
            autoBattle.set(this.starSystem.getID(), this.attackerID, this.defenderID);
        }
    }

    public void autoResultClosed(boolean z) {
        this.autoBattleSummaryOverlay.back();
        if (!GameProperties.isNonNormalEmpire(this.attackerID) && !this.B.empires.get(this.attackerID).isAI()) {
            figureHumanNextStep(z);
        } else {
            M(z);
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        if (hasChildScene()) {
            this.B.shipSelectOverlay.back();
            this.H.back();
        } else if (this.isAttacker) {
            if (this.didAttack) {
                this.B.beginTurn();
            } else {
                changeScene(this.B.selectAttackScene, new Point(this.attackerID, this.starSystem.getID()));
            }
        }
    }

    public void bombPlanet(ShipComponentID shipComponentID) {
        if (!this.attackingPlanet || getBombCount() <= 0) {
            return;
        }
        this.didAttack = true;
        int intValue = this.availableBombs.get(shipComponentID).intValue() - 1;
        if (intValue == 0) {
            this.availableBombs.remove(shipComponentID);
        } else {
            this.availableBombs.put(shipComponentID, Integer.valueOf(intValue));
        }
        updateBombButtons();
        String calculateDamage = calculateDamage(shipComponentID);
        if (this.bombard) {
            this.bombardCount++;
        } else {
            this.B.sounds.playBombExplosionSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        }
        if (this.bombardCount < 50) {
            this.planetSprite.showExplosion(shipComponentID, calculateDamage, this.bombard);
        }
    }

    public void bombardPlanet() {
        if (this.availableBombs.containsKey(ShipComponentID.BIO_BOMB)) {
            Game.gameAchievements.usedBioWeapon();
        }
        this.bombard = true;
        this.B.sounds.playBombExplosionSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        while (getBombCount() != 0 && this.systemObject.hasColony()) {
            bombPlanet(this.availableBombs.entrySet().iterator().next().getKey());
        }
    }

    public void bombingSummaryClosed() {
        this.B.beginTurn();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i != 1) {
            return;
        }
        checkActionUp(point);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v37 */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        float f2;
        int i;
        super.createScene(vertexBufferObjectManager);
        this.bufferManager = vertexBufferObjectManager;
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        SunSprite sunSprite = new SunSprite(this.B, vertexBufferObjectManager, true);
        this.sunSprite = sunSprite;
        attachChild(sunSprite);
        int i2 = (int) (Moon.SPRITE_SIZE * 1.125f);
        this.planetSprite = new PlanetSprite(this.B, vertexBufferObjectManager, ((int) (getWidth() / 2.0f)) - i2, (620 - i2) + 100);
        this.planetSprite.setPosition(getWidth() - 180.0f, 360.0f);
        attachChild(this.planetSprite);
        TiledSprite H = H(1150.0f, 330.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.OUTPOST.ordinal(), false);
        this.outpost = H;
        H.setZIndex(6);
        TiledSprite H2 = H(1100.0f, 305.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.STAR_BASE.ordinal(), false);
        this.starBase = H2;
        H2.setSize(150.0f, 150.0f);
        this.starBase.setZIndex(7);
        TiledSprite H3 = H(1250.0f, 330.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.STAR_BASE.ordinal(), false);
        this.starBaseInfoIcon = H3;
        H3.setZIndex(7);
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.gameIconsTexture;
        GameIconEnum gameIconEnum = GameIconEnum.TORPEDO_TURRET;
        TiledSprite H4 = H(0.0f, 0.0f, iTiledTextureRegion, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
        this.torpedoTurret1 = H4;
        H4.setSize(75.0f, 75.0f);
        this.torpedoTurret1.setZIndex(7);
        TiledSprite H5 = H(0.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
        this.torpedoTurret2 = H5;
        H5.setSize(75.0f, 75.0f);
        this.torpedoTurret2.setZIndex(7);
        TiledSprite H6 = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.TURRET_ICON.ordinal(), false);
        this.torpedoTurretIcon = H6;
        H6.setZIndex(7);
        Sprite E = E(0.0f, 500.0f, this.B.graphics.popTexture, vertexBufferObjectManager, false);
        this.populationBar = E;
        E.setZIndex(7);
        Sprite E2 = E(0.0f, 500.0f, this.B.graphics.popEmptyTexture, vertexBufferObjectManager, false);
        this.populationBarEmpty = E2;
        E2.setZIndex(7);
        Text F = F(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.populationPercentString = F;
        F.setZIndex(7);
        Text F2 = F(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.populationString = F2;
        F2.setZIndex(7);
        this.popIcon = H(0.0f, 500.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.POPULATION.ordinal(), false);
        Text G = G(0.0f, 590.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, false);
        this.buildingCount = G;
        G.setZIndex(7);
        TiledSprite H7 = H(0.0f, 577.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.BUILDINGS.ordinal(), false);
        this.buildingIcon = H7;
        H7.setSize(50.0f, 50.0f);
        this.buildingIcon.setZIndex(7);
        Text G2 = G(0.0f, 590.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, false);
        this.defenderInfantryCount = G2;
        G2.setZIndex(7);
        TiledSprite H8 = H(0.0f, 590.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.INFANTRY.ordinal(), false);
        this.defenderInfantryIcon = H8;
        H8.setZIndex(7);
        Text G3 = G(0.0f, 595.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false);
        this.bombingDefensePercent = G3;
        G3.setZIndex(7);
        TiledSprite H9 = H(0.0f, 590.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.BOMB_SHIELDING.ordinal(), false);
        this.bombingDefenseIcon = H9;
        H9.setZIndex(7);
        this.asteroidBeltSprite = J(0.0f, 0.0f, this.B.graphics.asteroidBeltsTexture, vertexBufferObjectManager, false);
        this.displayText = F(120.0f, 95.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        TiledSprite H10 = H(575.0f, 80.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.CAPITAL.ordinal(), false);
        this.capitalIcon = H10;
        H10.setSize(50.0f, 50.0f);
        Game game = this.B;
        Text F3 = F(0.0f, 130.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.attack_you_are_being_attacked), this.E, vertexBufferObjectManager);
        this.message = F3;
        F3.setX((getWidth() / 2.0f) - (this.message.getWidthScaled() / 2.0f));
        TiledSprite J = J(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.attackerBackground = J;
        J.setAlpha(0.6f);
        this.attackerBackground.setSize(getWidth() / 2.0f, 86.0f);
        this.attackerBanner = J(0.0f, -7.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.attackerName = F(120.0f, 30.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        TiledSprite J2 = J(getWidth() / 2.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.defenderBackground = J2;
        J2.setAlpha(0.6f);
        this.defenderBackground.setSize(getWidth() / 2.0f, 86.0f);
        this.defenderBanner = J(getWidth() / 2.0f, -7.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.defenderName = F((getWidth() / 2.0f) + 120.0f, 30.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        ShipType[] values = ShipType.values();
        int length = values.length;
        ?? r5 = 0;
        int i3 = 0;
        while (true) {
            f2 = 0.0f;
            if (i3 >= length) {
                break;
            }
            ShipType shipType = values[i3];
            if (shipType.isStation()) {
                i = i3;
            } else {
                this.attackerShipIcons[shipType.ordinal()] = new TiledSprite(0.0f, 0.0f, this.B.graphics.shipsTextures[r5], vertexBufferObjectManager);
                float scale = shipType.getScale() * 150.0f;
                this.attackerShipIcons[shipType.ordinal()].setSize(scale, scale);
                this.attackerShipIcons[shipType.ordinal()].setVisible(r5);
                attachChild(this.attackerShipIcons[shipType.ordinal()]);
                this.attackerShipTypeIcons[shipType.ordinal()] = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.attackerShipTypeIcons[shipType.ordinal()].setVisible(r5);
                attachChild(this.attackerShipTypeIcons[shipType.ordinal()]);
                i = i3;
                this.attackerShipCounts[shipType.ordinal()] = new Text(0.0f, 30.0f, this.B.fonts.smallFont, "#####", vertexBufferObjectManager);
                this.attackerShipCounts[shipType.ordinal()].setVisible(false);
                attachChild(this.attackerShipCounts[shipType.ordinal()]);
            }
            i3 = i + 1;
            r5 = 0;
        }
        ShipType[] values2 = ShipType.values();
        int length2 = values2.length;
        int i4 = 0;
        while (i4 < length2) {
            ShipType shipType2 = values2[i4];
            if (!shipType2.isStation()) {
                this.defenderShipIcons[shipType2.ordinal()] = new TiledSprite(f2, f2, this.B.graphics.shipsTextures[0], vertexBufferObjectManager);
                float scale2 = shipType2.getScale() * 150.0f;
                this.defenderShipIcons[shipType2.ordinal()].setSize(scale2, scale2);
                this.defenderShipIcons[shipType2.ordinal()].setRotationCenter(this.defenderShipIcons[shipType2.ordinal()].getWidthScaled() / 2.0f, this.defenderShipIcons[shipType2.ordinal()].getHeightScaled() / 2.0f);
                this.defenderShipIcons[shipType2.ordinal()].setRotation(180.0f);
                this.defenderShipIcons[shipType2.ordinal()].setVisible(false);
                attachChild(this.defenderShipIcons[shipType2.ordinal()]);
                this.defenderShipTypeIcons[shipType2.ordinal()] = new TiledSprite(f2, f2, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.defenderShipTypeIcons[shipType2.ordinal()].setVisible(false);
                attachChild(this.defenderShipTypeIcons[shipType2.ordinal()]);
                this.defenderShipCounts[shipType2.ordinal()] = new Text(0.0f, 30.0f, this.B.fonts.smallFont, "#####", vertexBufferObjectManager);
                this.defenderShipCounts[shipType2.ordinal()].setVisible(false);
                attachChild(this.defenderShipCounts[shipType2.ordinal()]);
            }
            i4++;
            f2 = 0.0f;
        }
        ITiledTextureRegion iTiledTextureRegion2 = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.SHIP_SELECT;
        TiledSprite H11 = H(0.0f, 90.0f, iTiledTextureRegion2, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.showAttackersShipsButton = H11;
        s(H11);
        TiledSprite H12 = H(getWidth() - 120.0f, 90.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        this.showDefendersShipsButton = H12;
        H12.setZIndex(8);
        s(this.showDefendersShipsButton);
        TiledSprite J3 = J(0.0f, 630.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.actionBarBackground = J3;
        J3.setAlpha(0.6f);
        this.actionBarBackground.setHeight(86.0f);
        this.actionBarBackground.setZIndex(8);
        TiledSprite J4 = J(0.0f, 623.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.actionBarBanner = J4;
        J4.setZIndex(8);
        int i5 = 0;
        Text G4 = G(0.0f, 630.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, false);
        this.attackerInfantryCount = G4;
        G4.setZIndex(8);
        TiledSprite H13 = H(0.0f, 630.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.INFANTRY.ordinal(), false);
        this.attackerInfantryIcon = H13;
        H13.setZIndex(8);
        while (true) {
            TiledSprite[] tiledSpriteArr = this.actionButtons;
            if (i5 < tiledSpriteArr.length) {
                tiledSpriteArr[i5] = H(580.0f, 630.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.BLANK.ordinal(), true);
                this.actionButtons[i5].setZIndex(8);
                s(this.actionButtons[i5]);
                this.actionButtonIcons[i5] = J(580.0f, 643.0f, this.B.graphics.shipComponentIconsTexture, vertexBufferObjectManager, false);
                this.actionButtonIcons[i5].setSize(60.0f, 60.0f);
                this.actionButtonIcons[i5].setZIndex(8);
                this.actionButtonCounts[i5] = G(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false);
                this.actionButtonCounts[i5].setZIndex(8);
                i5++;
            } else {
                this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
                this.invasionOverlay = new InvasionOverlay(this.B, vertexBufferObjectManager);
                this.autoBattleSummaryOverlay = new AutoBattleSummaryOverlay(this.B, vertexBufferObjectManager);
                this.planetAttackSummaryOverlay = new PlanetAttackSummaryOverlay(this.B, vertexBufferObjectManager);
                return;
            }
        }
    }

    public void destroyNonCombat() {
        this.didAttack = true;
        if (this.B.fleets.isFleetInSystem(this.defenderID, this.starSystem.getID())) {
            this.showDefendersShipsButton.setVisible(false);
            destroyNonCombatShips(this.B.fleets.getFleetInSystem(this.defenderID, this.starSystem.getID()));
        }
        if (this.attackingPlanet && this.B.galaxy.getSystemObject(this.starSystem.getID(), this.systemObject.getOrbit()).hasOutpost()) {
            this.B.outposts.removeOutpost(this.starSystem.getID(), this.systemObject.getOrbit());
            this.didAttack = true;
            showOutpostExplosion();
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
    }

    public void invadePlanet() {
        this.didAttack = true;
        this.invasionOverlay.set(this.systemObject.getColony(), this.attackerID);
        setChildScene(this.invasionOverlay, false, false, true);
        this.invasionOverlay.startInvasion();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.a
            {
                AttackScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AttackScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void set(boolean z, int i, int i2, int i3) {
        set(z, i, i2, i3, false, -1);
    }

    public void setActionBar() {
        TiledSprite[] tiledSpriteArr;
        int i;
        hideButtons();
        this.actionBarBackground.setVisible(true);
        this.actionBarBanner.setVisible(true);
        int i2 = 0;
        for (TiledSprite tiledSprite : this.actionButtons) {
            tiledSprite.setCurrentTileIndex(ButtonsEnum.BLANK.ordinal());
            tiledSprite.setAlpha(1.0f);
            this.actionButtonIcons[i2].setAlpha(1.0f);
            this.actionButtonCounts[i2].setAlpha(1.0f);
            i2++;
        }
        if (this.isAttacker) {
            i = this.attackerID;
            setAttackerButtons();
        } else if (shouldShowDefenderButtons()) {
            i = this.defenderID;
            setDefenderButtons();
        } else {
            i = -1;
        }
        if (i != -1) {
            this.actionBarBackground.setVisible(true);
            this.actionBarBanner.setVisible(true);
            this.actionBarBackground.setCurrentTileIndex(i);
            this.actionBarBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(i));
        } else {
            this.actionBarBackground.setVisible(false);
            this.actionBarBanner.setVisible(false);
        }
        placeActionButtons();
        updateBombButtons();
        this.actionBarBackground.setWidth(getActionBarWidth());
    }

    public void setColony() {
        float x = this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f);
        Colony colony = this.systemObject.getColony();
        setPopulationBar(colony);
        this.buildingCount.setVisible(true);
        this.buildingCount.setText(Integer.toString(colony.getBuildings().size()));
        this.buildingIcon.setVisible(true);
        this.planetSprite.setColony(colony, colony.getPlanet().getScale(this.B.planetScene), false);
        this.defenderInfantryCount.setVisible(true);
        this.defenderInfantryCount.setText(Integer.toString(colony.getInfDivisions()));
        this.defenderInfantryIcon.setVisible(true);
        float shieldingStrength = colony.getShieldingStrength();
        if (shieldingStrength != 0.0f) {
            String num = Integer.toString((int) (100.0f * shieldingStrength));
            Text text = this.bombingDefensePercent;
            text.setText(num + "%");
            this.bombingDefensePercent.setVisible(true);
            this.bombingDefenseIcon.setVisible(true);
        } else {
            this.bombingDefensePercent.setVisible(false);
            this.bombingDefenseIcon.setVisible(false);
        }
        float widthScaled = this.buildingCount.getWidthScaled() + 5.0f + this.buildingIcon.getWidthScaled() + 10.0f + this.defenderInfantryCount.getWidthScaled() + 5.0f + this.defenderInfantryIcon.getWidthScaled();
        if (shieldingStrength != 0.0f) {
            widthScaled += this.bombingDefensePercent.getWidthScaled() + 10.0f + 5.0f + this.bombingDefenseIcon.getWidthScaled();
        }
        float f2 = x - (widthScaled / 2.0f);
        this.buildingCount.setX(f2);
        float widthScaled2 = f2 + this.buildingCount.getWidthScaled() + 5.0f;
        this.buildingIcon.setX(widthScaled2);
        float widthScaled3 = widthScaled2 + this.buildingIcon.getWidthScaled() + 10.0f;
        this.defenderInfantryCount.setX(widthScaled3);
        float widthScaled4 = widthScaled3 + this.defenderInfantryCount.getWidthScaled() + 5.0f;
        this.defenderInfantryIcon.setX(widthScaled4);
        float widthScaled5 = widthScaled4 + this.defenderInfantryIcon.getWidthScaled() + 10.0f;
        this.bombingDefensePercent.setX(widthScaled5);
        this.bombingDefenseIcon.setX(widthScaled5 + this.bombingDefensePercent.getWidthScaled() + 5.0f);
        if (colony.hasBuilding(BuildingID.STAR_BASE)) {
            this.starBase.setVisible(true);
            this.starBaseInfoIcon.setVisible(true);
        }
        if (colony.hasBuilding(BuildingID.TORPEDO_TURRET)) {
            this.torpedoTurret1.setVisible(true);
            this.torpedoTurret2.setVisible(true);
            this.torpedoTurretIcon.setVisible(true);
            if (this.starBase.isVisible()) {
                this.torpedoTurret1.setPosition(1120.0f, 185.0f);
                this.torpedoTurret2.setPosition(1175.0f, 230.0f);
                this.torpedoTurretIcon.setPosition(1250.0f, 200.0f);
                return;
            }
            this.torpedoTurret1.setPosition(1120.0f, 335.0f);
            this.torpedoTurret2.setPosition(1175.0f, 380.0f);
            this.torpedoTurretIcon.setPosition(1250.0f, 350.0f);
        }
    }

    public void setFleets() {
        hideShipIcons();
        setFleet(true, this.attackerID);
        setFleet(false, this.defenderID);
    }

    public void showAIPlanetAttackResult(PlanetAttack planetAttack) {
        this.planetAttackSummaryOverlay.setOverlay(planetAttack);
        setChildScene(this.planetAttackSummaryOverlay, false, false, true);
    }

    public void showAutoResult(int i, int i2, int i3, BattleLosses battleLosses, BattleLosses battleLosses2) {
        this.autoBattleSummaryOverlay.setOverlay(i, i2, i3, battleLosses, battleLosses2);
        setChildScene(this.autoBattleSummaryOverlay, false, false, true);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        this.B.shipSelectOverlay.back();
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        if (!this.showNonCombatsShipBeingDestroyed || System.currentTimeMillis() - this.startTime <= 1000) {
            return;
        }
        this.showNonCombatsShipBeingDestroyed = false;
        destroyNonCombatShips(this.B.fleets.getFleetInSystem(this.defenderID, this.starSystem.getID()));
    }

    private boolean checkRelationStatus(String str, ShipComponentID shipComponentID) {
        if (!GameProperties.isNonNormalEmpire(this.attackerID) && !GameProperties.isNonNormalEmpire(this.defenderID)) {
            if (!GameData.empires.get(this.attackerID).isAtWar(this.defenderID)) {
                this.B.confirmOverlay.setOverlay(this.attackerID, this.defenderID, str, shipComponentID);
                setChildScene(this.B.confirmOverlay, false, false, true);
                return false;
            }
            GameData.empires.get(this.attackerID).getTreaties().declareWar(this.defenderID);
            GameData.empires.get(this.defenderID).getTreaties().declareWar(this.attackerID);
        }
        return true;
    }

    public void set(boolean z, int i, int i2, int i3, int i4) {
        set(z, i, i2, i3, true, i4);
    }

    private void set(boolean z, int i, int i2, int i3, boolean z2, int i4) {
        float armorMultiplier;
        this.didAttack = false;
        this.isTargetDestroyed = false;
        this.attackingPlanet = false;
        this.isAttacker = z;
        this.attackerID = i;
        this.defenderID = i2;
        this.starSystem = this.B.galaxy.getStarSystem(i3);
        this.orbit = i4;
        this.atPlanet = z2;
        this.attackerLosses = new BattleLosses();
        this.defenderLosses = new BattleLosses();
        this.alreadyDone = false;
        this.bombard = false;
        this.bombardCount = 0;
        O();
        if (GameProperties.isNonNormalEmpire(i2)) {
            armorMultiplier = ShipComponents.getArmors().get(0).getArmorMultiplier();
        } else {
            armorMultiplier = this.B.empires.get(i2).getTech().getArmorMultiplier();
        }
        int i5 = (int) (armorMultiplier * 50.0f);
        this.structureArmor = i5;
        this.structureHitPoints = i5;
        this.message.setVisible(false);
        if (!z && this.B.fleets.isFleetInSystem(i2, i3) && this.B.fleets.getFleetInSystem(i2, i3).hasCombatShips()) {
            this.message.setVisible(true);
        }
    }
}
