package com.birdshel.Uciana.Scenes;

import android.os.AsyncTask;

import com.birdshel.Uciana.AI.Managers.BattleAI;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Controls.ShipControl;
import com.birdshel.Uciana.Elements.Battle.BattleCallBack;
import com.birdshel.Uciana.Elements.Battle.BattleGrid;
import com.birdshel.Uciana.Elements.Battle.PayloadSprite;
import com.birdshel.Uciana.Elements.BattleScene.HexSprite;
import com.birdshel.Uciana.Elements.IonStormSprite;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.Tutorials.BattleGridTutorial;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.ShipStatsOverlay;
import com.birdshel.Uciana.Planets.IonStorm;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Resources.Options;
import com.birdshel.Uciana.Resources.TutorialID;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.SpecialComponent;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponType;
import com.birdshel.Uciana.Ships.ShipSpriteBattle;
import com.birdshel.Uciana.Ships.ShipStatus;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.Ships.SpacialCharge;
import com.birdshel.Uciana.Ships.StarBase;
import com.birdshel.Uciana.Ships.Torpedo;
import com.birdshel.Uciana.Ships.TorpedoTurret;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.Utility.Log;
import com.google.android.gms.games.GamesActivityResultCodes;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.ScaleAtModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BattleScene extends ExtendedScene implements BattleCallBack {
    public static final int SHIP_SIZE = 80;
    private int afterExplosionAction;
    private boolean areAttackingSystemObject;
    private Point attackPosition;
    private BattleAI attackerBattleAI;
    private int attackerID;
    private TiledSprite autoButton;
    private TiledSprite autoButtonHighlight;
    private TiledSprite autoButtonIcon;
    private Text autoText;
    private TiledSprite backArrow;
    private BattleGrid battleGrid;
    private Line beamAttack;
    private VertexBufferObjectManager bufferManager;
    private BattleAI defenderBattleAI;
    private int defenderID;
    private TiledSprite fleetIcon1;
    private TiledSprite fleetIcon2;
    private TiledSprite fleetIcon3;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private float oldShipControlX;
    private float oldShipControlY;
    private PlanetSprite planetSprite;
    private TiledSprite pressedHex;
    private TiledSprite retreatButton;
    private TiledSprite selectedHexOverlay;
    private SpecialComponent selectedSpecial;
    private Weapon selectedWeapon;
    private ShipControl shipControl;
    private Point shipControlPressedPoint;
    private int shipDebrisIndex;
    private int shipPartsIndex;
    private int shipSpriteIndex;
    private ShipStatsOverlay shipStatsOverlay;
    private TiledSprite showGridButton;
    private TiledSprite showGridButtonHighlight;
    private ShipSpriteBattle starbaseSprite;
    private long startTime;
    private int systemID;
    private SystemObject systemObject;
    private ShipSpriteBattle turret1Sprite;
    private ShipSpriteBattle turret2Sprite;
    private final List<Entity> explosionList = new ArrayList();
    private final List<Entity> spacialShockwaves = new ArrayList();
    private final List<PayloadSprite> payloadSprites = new ArrayList();
    private final HexSprite[][] hexGrid = (HexSprite[][]) Array.newInstance(HexSprite.class, 15, 7);
    private boolean shipControlMoved = false;
    private boolean shipControlPressed = false;
    private boolean anyShipsMoving = false;
    private int explosionCount = 0;
    private final int NO_ACTION = 0;
    private final int NEXT_SHIP = 1;
    private final int SHIP_DESTROYED_DONE = 2;
    private final int ATTACK_DONE = 3;
    private boolean isShipDone = false;
    private boolean endBattle = false;
    private boolean alreadyOver = false;
    private final List<ShipSpriteBattle> shipSpritesList = new ArrayList();
    private final Map<String, ShipSpriteBattle> shipSprites = new HashMap();
    private final List<IonStormSprite> ionStorms = new ArrayList();
    private final List<TiledSprite> asteroids = new ArrayList();
    private final List<TiledSprite> shipDebrisList = new ArrayList();
    private final List<TiledSprite> shipParts = new ArrayList();

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.BattleScene$22 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass22 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1442a;

        static {
            int[] iArr = new int[WeaponType.values().length];
            f1442a = iArr;
            try {
                iArr[WeaponType.BEAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1442a[WeaponType.TORPEDO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1442a[WeaponType.SPACIAL_CHARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public BattleScene(Game game) {
        this.B = game;
    }

    private void AIMoveDone(Ship ship) {
        this.anyShipsMoving = false;
        if (ship.getEmpireID() == this.attackerID) {
            this.attackerBattleAI.moveDone(ship);
        } else {
            this.defenderBattleAI.moveDone(ship);
        }
    }

    private void AIMoveShip(Ship ship) {
        this.anyShipsMoving = true;
        if (ship.getEmpireID() == this.attackerID) {
            this.attackerBattleAI.moveShip(ship);
        } else {
            this.defenderBattleAI.moveShip(ship);
        }
    }

    static /* synthetic */ int R(BattleScene battleScene) {
        int i = battleScene.explosionCount;
        battleScene.explosionCount = i - 1;
        return i;
    }

    private void addShip(Ship ship, ShipSpriteBattle shipSpriteBattle, int i, int i2) {
        HexSprite hexSprite = this.hexGrid[i][i2];
        this.battleGrid.setObject(i, i2, ship.getID());
        ship.setBattleLocation(new Point(i, i2));
        shipSpriteBattle.setPosition(hexSprite.getX() + 10.0f, hexSprite.getY() + 10.0f);
        shipSpriteBattle.setZIndex(GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED);
        this.shipSprites.put(ship.getID(), shipSpriteBattle);
        setShipStats(ship);
    }

    private void attack(Weapon weapon, Point point) {
        int i = AnonymousClass22.f1442a[weapon.getType().ordinal()];
        if (i == 1) {
            if (!this.battleGrid.hasShip(point)) {
                attackDone();
                return;
            }
            boolean beamAttack = beamAttack(weapon, point);
            this.shipControl.setDisabled(true);
            showBeamAttack(weapon.getWeaponColor(), this.battleGrid.getSelectedShip(), this.battleGrid.getShip(point), beamAttack);
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            SpacialCharge spacialChargeFired = this.battleGrid.spacialChargeFired(weapon.getID(), this.battleGrid.getSelectedHex(), point, weapon.getComponentCount(), this.battleGrid.getSelectedShip().getEmpireID());
            weapon.setAllWeaponsUsed();
            Point moveCharge = this.battleGrid.moveCharge(spacialChargeFired);
            ShipSpriteBattle shipSpriteBattle = this.shipSprites.get(this.battleGrid.getSelectedShip().getID());
            float size = ((int) (shipSpriteBattle.getSize() / 2.0f)) - 37;
            showPayloadAttack(spacialChargeFired.getID(), WeaponType.SPACIAL_CHARGE, new Point(shipSpriteBattle.getX() + size, shipSpriteBattle.getY() + size), moveCharge);
        } else if (this.battleGrid.hasShip(point)) {
            String str = this.battleGrid.torpedoFired(weapon.getID(), this.battleGrid.getSelectedHex(), this.battleGrid.getShip(point).getID(), weapon.getComponentCount(), this.battleGrid.getSelectedShip().getEmpireID());
            weapon.setAllWeaponsUsed();
            BattleGrid battleGrid = this.battleGrid;
            Point moveTorpedo = battleGrid.moveTorpedo(battleGrid.getTorpedo(str));
            ShipSpriteBattle shipSpriteBattle2 = this.shipSprites.get(this.battleGrid.getSelectedShip().getID());
            float size2 = ((int) (shipSpriteBattle2.getSize() / 2.0f)) - 37;
            showPayloadAttack(str, WeaponType.TORPEDO, new Point(shipSpriteBattle2.getX() + size2, shipSpriteBattle2.getY() + size2), moveTorpedo);
        }
    }

    public void attackDone() {
        Log.message("BattleScene", "Attack Done");
        this.anyShipsMoving = false;
        if (this.battleGrid.getSelectedShip() == null) {
            selectNextShip();
            return;
        }
        Point selectedHex = this.battleGrid.getSelectedHex();
        if (selectedHex == null) {
            selectNextShip();
            return;
        }
        if (!this.battleGrid.isShipAlive(this.battleGrid.getObject(selectedHex))) {
            selectNextShip();
            return;
        }
        Ship selectedShip = this.battleGrid.getSelectedShip();
        if (selectedShip == null) {
            return;
        }
        if (isAI(selectedShip.getEmpireID())) {
            AIMoveDone(selectedShip);
        } else {
            this.shipControl.setVisible(true);
            if (!this.shipControl.hasWeaponSelected()) {
                attackShip();
            }
        }
        this.shipControl.setDisabled(false);
    }

    private void attackShip() {
        Point point = this.attackPosition;
        if (point != null && this.battleGrid.hasShip(point)) {
            for (ShipComponent shipComponent : this.battleGrid.getSelectedShip().getShipComponents()) {
                if (shipComponent instanceof Weapon) {
                    Weapon weapon = (Weapon) shipComponent;
                    if (weapon.getType() != WeaponType.BOMB && weapon.getAvailableCount() != 0) {
                        this.selectedWeapon = weapon;
                        attack(weapon, this.attackPosition);
                        this.shipControl.update();
                        return;
                    }
                }
            }
            clearGrid();
            showMoves();
            setShipDisplay();
            return;
        }
        Log.message("BattleScene", "attackingPosition is null or has no ship");
        clearGrid();
        showMoves();
        setShipDisplay();
    }

    private void autoPressed() {
        float f2;
        if (isAutoOn()) {
            this.autoButtonHighlight.setVisible(false);
        } else {
            this.autoButtonHighlight.setVisible(true);
            this.shipControl.setVisible(false);
            if (this.battleGrid.getSelectedShip() != null) {
                AIMoveShip(this.battleGrid.getSelectedShip());
                f2 = 0.4f;
                this.retreatButton.setAlpha(f2);
                this.backArrow.setAlpha(f2);
                this.fleetIcon1.setAlpha(f2);
                this.fleetIcon2.setAlpha(f2);
                this.fleetIcon3.setAlpha(f2);
                this.B.sounds.playButtonPressSound();
                Game game = this.B;
                game.vibrate(game.BUTTON_VIBRATE);
            }
        }
        f2 = 1.0f;
        this.retreatButton.setAlpha(f2);
        this.backArrow.setAlpha(f2);
        this.fleetIcon1.setAlpha(f2);
        this.fleetIcon2.setAlpha(f2);
        this.fleetIcon3.setAlpha(f2);
        this.B.sounds.playButtonPressSound();
        Game game2 = this.B;
        game2.vibrate(game2.BUTTON_VIBRATE);
    }

    private boolean beamAttack(Weapon weapon, Point point) {
        String str;
        Ship selectedShip = this.battleGrid.getSelectedShip();
        Ship ship = this.battleGrid.getShip(point);
        Log.message("BattleScene", selectedShip.getID() + " attacks " + ship.getID() + " at " + point.toString());
        int hexDistance = Functions.getHexDistance(selectedShip.getBattleLocation(), ship.getBattleLocation());
        int i = 0;
        if (Functions.percent(this.battleGrid.getChanceToHit(selectedShip, ship))) {
            boolean percent = Functions.percent(25);
            float f2 = percent ? 0.5f : 0.0f;
            int i2 = 0;
            while (weapon.getAvailableCount() != 0 && ship.isAlive()) {
                float weaponDamage = selectedShip.getWeaponDamage(weapon, hexDistance);
                Point takeDamage = ship.takeDamage((int) (weaponDamage + (f2 * weaponDamage)));
                i += (int) takeDamage.getX();
                i2 += (int) takeDamage.getY();
            }
            ShipStatus checkShipStatusNeedsToBeAdded = this.battleGrid.checkShipStatusNeedsToBeAdded(ship);
            if (checkShipStatusNeedsToBeAdded != ShipStatus.NONE) {
                this.shipSprites.get(ship.getID()).setShipStatus(checkShipStatusNeedsToBeAdded);
            }
            if (percent) {
                str = this.B.getActivity().getString(R.string.battle_critical) + "\n";
            } else {
                str = "";
            }
            this.shipSprites.get(ship.getID()).setDamageToShow(str + Integer.toString(i + i2));
            this.shipSprites.get(ship.getID()).setDamageBar(i, i2);
            return true;
        }
        weapon.setAllWeaponsUsed();
        this.shipSprites.get(ship.getID()).setDamageToShow(this.B.getActivity().getString(R.string.battle_miss));
        return false;
    }

    private void checkActionDown(Point point) {
        if (isPositionOnShipControl(point)) {
            this.shipControlPressedPoint = new Point(this.shipControl.getX(), this.shipControl.getY());
            this.shipControlPressed = true;
            this.oldShipControlX = point.getX();
            this.oldShipControlY = point.getY();
            this.I = false;
            this.shipControl.pressed();
            return;
        }
        this.pressedHex.setVisible(false);
        checkPressed(point);
    }

    private void checkActionMove(Point point) {
        if (this.shipControlPressed) {
            float x = this.shipControl.getX() + (point.getX() - this.oldShipControlX);
            float y = this.shipControl.getY() + (point.getY() - this.oldShipControlY);
            if (x < 0.0f) {
                x = 0.0f;
            }
            if (y < 0.0f) {
                y = 0.0f;
            }
            if (this.shipControl.getWidth() + x > 1160.0f) {
                x = 1160.0f - this.shipControl.getWidth();
            }
            if (this.shipControl.getHeight() + y > 720.0f) {
                y = 720.0f - this.shipControl.getHeight();
            }
            this.shipControl.setControlPosition(x, y);
            this.oldShipControlX = point.getX();
            this.oldShipControlY = point.getY();
            float x2 = this.shipControlPressedPoint.getX() - this.shipControl.getX();
            float y2 = this.shipControlPressedPoint.getY() - this.shipControl.getY();
            if (x2 > 50.0f || x2 < -50.0f || y2 > 50.0f || y2 < -50.0f) {
                this.shipControlMoved = true;
                return;
            }
            return;
        }
        this.pressedHex.setVisible(false);
        checkPressed(point);
    }

    private void checkActionUp(Point point) {
        if (!this.shipControlPressed) {
            this.pressedHex.setVisible(false);
            checkGrid(point);
            checkButtons(point);
        }
        this.shipControlMoved = false;
        this.I = true;
        this.shipControlPressed = false;
        this.shipControl.unPressed();
    }

    public void checkBreachShockwaveHits(Ship ship, Point point) {
        for (Point point2 : this.battleGrid.getNodesInRange(point, 1)) {
            if (this.battleGrid.hasShip(point2)) {
                Ship ship2 = this.battleGrid.getShip(point2);
                if (!ship2.hasBeenDestroyed()) {
                    showShockwaveHit(ship2, ship.getCoreBreachDamage(), ship.getID());
                }
            }
        }
    }

    private void checkButtons(Point point) {
        if (isClicked(this.retreatButton, point)) {
            retreatButtonPressed();
        }
        if (isClicked(this.showGridButton, point)) {
            showGridButtonPressed();
        }
        if (isClicked(this.autoButton, point)) {
            if (!this.anyShipsMoving || isAutoOn()) {
                autoPressed();
            }
        }
    }

    public void checkChargeShockwaveHits(SpacialCharge spacialCharge) {
        for (Point point : this.battleGrid.getNodesInRange(spacialCharge.getTargetPosition(), 1)) {
            if (this.battleGrid.hasShip(point)) {
                Ship ship = this.battleGrid.getShip(point);
                if (!ship.hasBeenDestroyed()) {
                    showShockwaveHit(ship, spacialCharge.getDamage(), spacialCharge.getID());
                }
            }
        }
    }

    public void checkForPayloadsInBlastRange(Point point) {
        for (String str : this.battleGrid.checkTorpedoesInRangeOfShockwave(point)) {
            if (doesPayloadSpritesContain(str)) {
                Log.message("BattleScene", "Error getting torpedo sprite");
            } else {
                TiledSprite sprite = getPayloadSprite(str).getSprite();
                showExplosion(sprite.getX(), sprite.getY(), sprite.getWidthScaled());
            }
            removeTorpedo(str);
        }
        for (String str2 : this.battleGrid.checkSpacialChargesInRangeOfShockwave(point)) {
            if (doesPayloadSpritesContain(str2)) {
                Log.message("BattleScene", "Error getting charge sprite");
            } else {
                try {
                    TiledSprite sprite2 = getPayloadSprite(str2).getSprite();
                    showExplosion(sprite2.getX(), sprite2.getY(), sprite2.getWidthScaled());
                } catch (AssertionError unused) {
                }
            }
            removeCharge(str2);
        }
    }

    private void checkGrid(Point point) {
        Point gridPosition = getGridPosition(point);
        if (gridPosition.getX() >= 15.0f || gridPosition.getY() >= 7.0f) {
            return;
        }
        HexSprite hexSprite = this.hexGrid[(int) gridPosition.getX()][(int) gridPosition.getY()];
        if (this.selectedHexOverlay.isVisible()) {
            if (this.battleGrid.canMove(gridPosition)) {
                moveShip(gridPosition);
            } else if (this.battleGrid.canAttack(gridPosition)) {
                if (this.battleGrid.hasShip(gridPosition)) {
                    if (this.shipControl.hasWeaponSelected()) {
                        attackShip(gridPosition, this.selectedWeapon);
                        return;
                    }
                    this.attackPosition = gridPosition;
                    attackShip();
                    clearGrid();
                    showMoves();
                } else if (this.shipControl.hasWeaponSelected() && this.selectedWeapon.getType() == WeaponType.SPACIAL_CHARGE) {
                    attackShip(gridPosition, this.selectedWeapon);
                }
            } else if (this.battleGrid.canSpecial(gridPosition) && this.battleGrid.hasShip(gridPosition)) {
                specialShip(gridPosition, this.selectedSpecial);
            } else {
                checkHex(hexSprite, gridPosition);
            }
        }
    }

    private void checkHex(HexSprite hexSprite, Point point) {
        Ship ship;
        int i;
        if (this.battleGrid.hasShip(point)) {
            Fleet attackingFleet = this.battleGrid.getAttackingFleet();
            if (attackingFleet.hasShip(this.battleGrid.getObject(point))) {
                ship = attackingFleet.getShip(this.battleGrid.getObject(point));
                i = this.attackerID;
            } else {
                Fleet defendingFleet = this.battleGrid.getDefendingFleet();
                String object = this.battleGrid.getObject(point);
                if (this.battleGrid.hasStarbase() && object.equals("starbase")) {
                    ship = this.battleGrid.getStarBase();
                } else if (object.startsWith("turret")) {
                    ship = this.battleGrid.getTurret(object);
                } else {
                    ship = defendingFleet.getShip(object);
                }
                i = this.defenderID;
            }
            this.shipStatsOverlay.setOverlay(i, ship);
            this.shipStatsOverlay.setPosition(hexSprite.getX(), hexSprite.getY());
            setChildScene(this.shipStatsOverlay, false, false, true);
        }
    }

    private boolean checkIfShipDestroyed(Ship ship) {
        if (ship.isAlive()) {
            return false;
        }
        ship.setDestroyed();
        Log.message("BattleScene", "Ship destroyed: " + ship.getID());
        if (this.battleGrid.getSelectedShip() == null || this.battleGrid.getSelectedShip().getID().equals(ship.getID())) {
            Log.message("BattleScene", "checkIfShipDestroyed: afterExplosionAction = NEXT_SHIP");
            this.afterExplosionAction = 1;
            this.battleGrid.setSelectedShip(null);
        }
        if (Functions.percent(25)) {
            shipCoreBreach(ship);
            return true;
        }
        setShipStats(ship, false);
        ShipSpriteBattle shipSpriteBattle = this.shipSprites.get(ship.getID());
        shipSpriteBattle.hideShipImage();
        showShipDebris(shipSpriteBattle);
        this.explosionCount++;
        Log.message("BattleScene", "explosion Count increased ++, current: " + this.explosionCount);
        AnimatedSprite animatedSprite = new AnimatedSprite(shipSpriteBattle.getX(), shipSpriteBattle.getY(), this.B.graphics.explosionTexture, this.bufferManager);
        animatedSprite.setSize(shipSpriteBattle.getSize(), shipSpriteBattle.getSize());
        attachChild(animatedSprite);
        setTimerForAfterExplosionAnimation();
        animatedSprite.animate(new long[]{105, 105, 105, 105, 105, 105, 105, 105}, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, false);
        animatedSprite.registerEntityModifier(new AlphaModifier(0.84f, 1.0f, 0.0f));
        animatedSprite.registerEntityModifier(new ScaleAtModifier(0.84f, 1.0f, 5.0f, shipSpriteBattle.getSize() / 2.0f, shipSpriteBattle.getSize() / 2.0f));
        this.explosionList.add(animatedSprite);
        this.B.sounds.playExplosionSound();
        this.battleGrid.shipDestroyed(ship);
        return true;
    }

    private void checkPressed(Point point) {
        Point gridPosition = getGridPosition(point);
        if (gridPosition.getX() >= 15.0f || gridPosition.getY() >= 7.0f) {
            return;
        }
        this.pressedHex.setVisible(true);
        this.pressedHex.setX(this.hexGrid[(int) gridPosition.getX()][(int) gridPosition.getY()].getX());
        this.pressedHex.setY(this.hexGrid[(int) gridPosition.getX()][(int) gridPosition.getY()].getY());
    }

    private boolean checkShipControl(int i, Point point) {
        if (point.getX() <= this.shipControl.getX() || point.getX() >= this.shipControl.getX() + this.shipControl.getWidth() || point.getY() <= this.shipControl.getY() || point.getY() >= this.shipControl.getY() + this.shipControl.getHeight() || this.shipControlMoved || !this.shipControl.checkInputOnControl(i, point)) {
            return false;
        }
        this.shipControlPressed = false;
        this.shipControl.unPressed();
        return true;
    }

    private void clearElements() {
        this.autoButtonHighlight.setVisible(false);
        unselect();
        this.planetSprite.setSpritesInvisible();
        this.shipSprites.clear();
        for (TiledSprite tiledSprite : this.shipParts) {
            tiledSprite.setVisible(false);
        }
        for (TiledSprite tiledSprite2 : this.shipDebrisList) {
            tiledSprite2.setVisible(false);
        }
        w(this.explosionList, this);
        w(this.spacialShockwaves, this);
        for (PayloadSprite payloadSprite : this.payloadSprites) {
            payloadSprite.clear();
        }
        this.battleGrid.clear();
        this.retreatButton.setAlpha(1.0f);
        this.backArrow.setAlpha(1.0f);
        this.fleetIcon1.setAlpha(1.0f);
        this.fleetIcon2.setAlpha(1.0f);
        this.fleetIcon3.setAlpha(1.0f);
    }

    private void clearGrid() {
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 15; i2++) {
                this.hexGrid[i2][i].setMoveGrid(false);
                this.hexGrid[i2][i].setAttackGrid(false);
                this.hexGrid[i2][i].setSpecialGrid(false);
                this.hexGrid[i2][i].setAttackHighlightedGrid(false);
                this.hexGrid[i2][i].setSpecialHighlightedGrid(false);
                this.battleGrid.setMoveHex(i2, i, false);
                this.battleGrid.setAttackHex(i2, i, false);
            }
        }
        for (Map.Entry<String, ShipSpriteBattle> entry : this.shipSprites.entrySet()) {
            entry.getValue().hideHitPercentage();
        }
    }

    private void createAttackGraphics() {
        Line line = new Line(0.0f, 0.0f, 0.0f, 0.0f, this.bufferManager);
        this.beamAttack = line;
        line.setVisible(false);
        this.beamAttack.setZIndex(GamesActivityResultCodes.RESULT_SIGN_IN_FAILED);
        attachChild(this.beamAttack);
    }

    private void createButtons() {
        float width = getWidth() - 120.0f;
        TiledTextureRegion tiledTextureRegion = this.B.graphics.buttonsTexture;
        VertexBufferObjectManager vertexBufferObjectManager = this.bufferManager;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK;
        TiledSprite H = H(width, 0.0f, tiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.retreatButton = H;
        H.setIgnoreUpdate(true);
        s(this.retreatButton);
        TiledSprite H2 = H(getWidth() - 110.0f, 23.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.LEFT_ARROW.ordinal(), true);
        this.backArrow = H2;
        H2.setIgnoreUpdate(true);
        this.backArrow.setSize(40.0f, 40.0f);
        TiledSprite H3 = H(getWidth() - 70.0f, 15.0f, this.B.graphics.shipsTextures[0], this.bufferManager, 0, true);
        this.fleetIcon1 = H3;
        H3.setIgnoreUpdate(true);
        this.fleetIcon1.setSize(25.0f, 25.0f);
        TiledSprite H4 = H(getWidth() - 70.0f, 45.0f, this.B.graphics.shipsTextures[0], this.bufferManager, 0, true);
        this.fleetIcon2 = H4;
        H4.setIgnoreUpdate(true);
        this.fleetIcon2.setSize(25.0f, 25.0f);
        TiledSprite H5 = H(getWidth() - 40.0f, 30.0f, this.B.graphics.shipsTextures[0], this.bufferManager, 0, true);
        this.fleetIcon3 = H5;
        H5.setIgnoreUpdate(true);
        this.fleetIcon3.setSize(25.0f, 25.0f);
        float width2 = getWidth() - 120.0f;
        TiledTextureRegion tiledTextureRegion2 = this.B.graphics.buttonsTexture;
        VertexBufferObjectManager vertexBufferObjectManager2 = this.bufferManager;
        ButtonsEnum buttonsEnum2 = ButtonsEnum.PRESSED;
        TiledSprite H6 = H(width2, 634.0f, tiledTextureRegion2, vertexBufferObjectManager2, buttonsEnum2.ordinal(), false);
        this.showGridButtonHighlight = H6;
        H6.setIgnoreUpdate(true);
        this.showGridButtonHighlight.setAlpha(0.4f);
        TiledSprite H7 = H(getWidth() - 120.0f, 634.0f, this.B.graphics.buttonsTexture, this.bufferManager, buttonsEnum.ordinal(), true);
        this.showGridButton = H7;
        H7.setIgnoreUpdate(true);
        s(this.showGridButton);
        float width3 = getWidth() - 80.0f;
        TiledTextureRegion tiledTextureRegion3 = this.B.graphics.gameIconsTexture;
        VertexBufferObjectManager vertexBufferObjectManager3 = this.bufferManager;
        GameIconEnum gameIconEnum = GameIconEnum.HEX_GRID;
        I(width3, 651.0f, tiledTextureRegion3, vertexBufferObjectManager3, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        I(getWidth() - 61.0f, 663.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        I(getWidth() - 80.0f, 676.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        I(getWidth() - 80.0f, 651.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        I(getWidth() - 61.0f, 663.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        I(getWidth() - 80.0f, 676.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        I(getWidth() - 80.0f, 651.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        I(getWidth() - 61.0f, 663.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        I(getWidth() - 80.0f, 676.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), true, 0.25f).setIgnoreUpdate(true);
        TiledSprite H8 = H(getWidth() - 120.0f, 546.0f, this.B.graphics.buttonsTexture, this.bufferManager, buttonsEnum2.ordinal(), false);
        this.autoButtonHighlight = H8;
        H8.setAlpha(0.4f);
        this.autoButtonHighlight.setIgnoreUpdate(true);
        TiledSprite H9 = H(getWidth() - 120.0f, 546.0f, this.B.graphics.buttonsTexture, this.bufferManager, buttonsEnum.ordinal(), false);
        this.autoButton = H9;
        H9.setIgnoreUpdate(true);
        TiledSprite H10 = H(getWidth() - 85.0f, 555.0f, this.B.graphics.shipComponentIconsTexture, this.bufferManager, 13, true);
        this.autoButtonIcon = H10;
        H10.setSize(50.0f, 50.0f);
        Game game = this.B;
        Text F = F(0.0f, 603.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.production_auto), this.E, this.bufferManager);
        this.autoText = F;
        F.setIgnoreUpdate(true);
        this.autoText.setX((getWidth() - 60.0f) - (this.autoText.getWidthScaled() / 2.0f));
        s(this.autoButton);
    }

    private void createHexGrid() {
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 15; i2++) {
                float f2 = i2 * 75;
                float f3 = (i * 100) - 15;
                if (i2 % 2 != 0) {
                    f3 += 50.0f;
                }
                this.hexGrid[i2][i] = new HexSprite(this.B, this, this.bufferManager, f2, f3);
            }
        }
        TiledTextureRegion tiledTextureRegion = this.B.graphics.gameIconsTexture;
        VertexBufferObjectManager vertexBufferObjectManager = this.bufferManager;
        GameIconEnum gameIconEnum = GameIconEnum.SELECTED_HEX_GRID;
        TiledSprite H = H(0.0f, 0.0f, tiledTextureRegion, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
        this.selectedHexOverlay = H;
        H.setZIndex(2);
        blinkSprite(this.selectedHexOverlay);
        TiledSprite H2 = H(0.0f, 0.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), false);
        this.pressedHex = H2;
        H2.setZIndex(2);
        this.pressedHex.setAlpha(0.75f);
    }

    private void createNebula() {
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
    }

    private void createOverlays() {
        ShipControl shipControl = new ShipControl(this.B, this.bufferManager);
        this.shipControl = shipControl;
        shipControl.setZIndex(19999);
        attachChild(this.shipControl);
        this.shipStatsOverlay = new ShipStatsOverlay(this.B, this.bufferManager);
        this.H = new MessageOverlay(this.B, this.bufferManager);
    }

    private void createPayloadSprite(String str, WeaponType weaponType, Point point) {
        Weapon weapon;
        if (weaponType == WeaponType.TORPEDO) {
            weapon = (Weapon) ShipComponents.get(this.battleGrid.getTorpedo(str).getTorpedoType());
        } else {
            weapon = (Weapon) ShipComponents.get(this.battleGrid.getSpacialCharge(str).getChargeType());
        }
        boolean z = true;
        Iterator<PayloadSprite> it = this.payloadSprites.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PayloadSprite next = it.next();
            if (!next.isVisible()) {
                next.setPayload(str, weapon, weaponType, point);
                z = false;
                break;
            }
        }
        if (z) {
            PayloadSprite payloadSprite = new PayloadSprite(this.B, this.bufferManager);
            payloadSprite.setPayload(str, weapon, weaponType, point);
            this.payloadSprites.add(payloadSprite);
            attachChild(payloadSprite);
        }
        sortChildren();
    }

    public void doAfterExplosionAction() {
        Log.message("BattleScene", "Doing after explosion action ID: " + this.afterExplosionAction);
        int i = this.afterExplosionAction;
        if (i == 1) {
            this.anyShipsMoving = false;
            this.isShipDone = true;
        } else if (i == 2) {
            shipDestroyedDone();
        } else if (i == 3) {
            attackDone();
        }
        this.afterExplosionAction = 0;
    }

    private boolean doesPayloadSpritesContain(String str) {
        for (PayloadSprite payloadSprite : this.payloadSprites) {
            if (payloadSprite.getID().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void doneRetreating(Ship ship) {
        this.isShipDone = true;
    }

    private void endBattle() {
        if (this.alreadyOver) {
            return;
        }
        this.alreadyOver = true;
        Log.message("BattleScene", "Battle Over");
        if (Game.options.isOn(OptionID.MUSIC)) {
            this.B.music.resumeMainTheme();
        }
        this.battleGrid.sendRetreatingShipsToNearestSystem();
        for (PayloadSprite payloadSprite : this.payloadSprites) {
            payloadSprite.clear();
        }
        int i = this.battleGrid.getAttackingFleet().empireID;
        if (this.battleGrid.getShips(i).isEmpty()) {
            this.B.beginTurn();
        } else if (!GameProperties.isNonNormalEmpire(i) && !this.B.empires.get(i).isAI()) {
            changeScene(this.B.attackScene, new AttackSceneData());
        } else {
            endBattleForAI();
        }
    }

    private void endBattleForAI() {
        if (this.B.fleets.isFleetInSystem(this.defenderID, this.systemID)) {
            Fleet fleetInSystem = this.B.fleets.getFleetInSystem(this.defenderID, this.systemID);
            for (Ship ship : fleetInSystem.getNonCombatShips()) {
                fleetInSystem.removeShip(ship.getID());
            }
            if (fleetInSystem.isEmpty()) {
                this.B.fleets.remove(fleetInSystem);
            }
        }
        this.B.attackScene.M(true);
    }

    private Point getGridPosition(Point point) {
        int x = ((int) point.getX()) / 75;
        float y = point.getY();
        if (x % 2 != 0) {
            y -= 50.0f;
        }
        return new Point(x, ((int) y) / 100);
    }

    private PayloadSprite getPayloadSprite(String str) {
        for (PayloadSprite payloadSprite : this.payloadSprites) {
            if (payloadSprite.getID().equals(str)) {
                return payloadSprite;
            }
        }
        throw new AssertionError("No payload sprite with that ID");
    }

    private boolean isAI(int i) {
        return GameProperties.isNonNormalEmpire(i) || this.B.empires.get(i).isAI() || isAutoOn();
    }

    private boolean isAutoOn() {
        return this.autoButtonHighlight.isVisible();
    }

    private boolean isPositionOnShipControl(Point point) {
        return point.getX() > this.shipControl.getX() && point.getX() < this.shipControl.getX() + this.shipControl.getWidth() && point.getY() > this.shipControl.getY() && point.getY() < this.shipControl.getY() + this.shipControl.getHeight();
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (ShipSpriteBattle shipSpriteBattle : this.shipSpritesList) {
            detachChild(shipSpriteBattle);
            this.B.shipSpriteBattlePool.push(shipSpriteBattle);
        }
        this.shipSpritesList.clear();
        detachChild(this.starbaseSprite);
        this.B.shipSpriteBattlePool.push(this.starbaseSprite);
        detachChild(this.turret1Sprite);
        this.B.shipSpriteBattlePool.push(this.turret1Sprite);
        detachChild(this.turret2Sprite);
        this.B.shipSpriteBattlePool.push(this.turret2Sprite);
        detachChild(this.planetSprite);
        this.B.planetSpritePool.push(this.planetSprite);
        this.shipControl.releasePoolElements(extendedScene, obj);
        this.B.shipDetailOverlay.releasePoolElements();
        extendedScene.getPoolElements();
        c0(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void moveCharge(SpacialCharge spacialCharge) {
        Log.message("BattleScene", "Move charge: " + spacialCharge.getID());
        Point copy = spacialCharge.getPosition().copy();
        Point moveCharge = this.battleGrid.moveCharge(spacialCharge);
        if (this.battleGrid.isLineOfSightBlocked(spacialCharge, copy, moveCharge)) {
            moveCharge = this.battleGrid.getIntersectionPoint(spacialCharge, copy, moveCharge);
            spacialCharge.setPayloadDestroyed(true);
        }
        showPayloadAttack(spacialCharge.getID(), WeaponType.SPACIAL_CHARGE, copy, moveCharge);
    }

    public void moveDone() {
        this.anyShipsMoving = false;
        Ship selectedShip = this.battleGrid.getSelectedShip();
        if (selectedShip == null) {
            this.isShipDone = true;
            return;
        }
        setShipStats(selectedShip, true);
        if (isAI(selectedShip.getEmpireID())) {
            Log.message("BattleScene", "Move ship done for AI or AUTO: " + selectedShip.getID());
            AIMoveDone(selectedShip);
            return;
        }
        Log.message("BattleScene", "Move ship done: " + selectedShip.getID());
        setShipDisplay();
        select(selectedShip.getBattleLocation());
    }

    private void moveTorpedo(Torpedo torpedo) {
        if (torpedo.shouldSelfDestruct()) {
            Log.message("BattleScene", "Removing Torpedo");
            if (!doesPayloadSpritesContain(torpedo.getTargetID())) {
                Log.message("BattleScene", "Error getting torpedo sprite");
            } else {
                TiledSprite sprite = getPayloadSprite(torpedo.getID()).getSprite();
                showExplosion(sprite.getX(), sprite.getY(), sprite.getWidthScaled());
            }
            removeTorpedo(torpedo.getID());
            this.anyShipsMoving = false;
            this.isShipDone = true;
            return;
        }
        Log.message("BattleScene", "Move torpedo: " + torpedo.getID());
        Point copy = torpedo.getPosition().copy();
        Point moveTorpedo = this.battleGrid.moveTorpedo(torpedo);
        if (this.battleGrid.isLineOfSightBlocked(torpedo, copy, moveTorpedo)) {
            Log.message("BattleScene", "Torpedo hit Asteroid going from: " + copy.toString() + " and to: " + moveTorpedo.toString());
            moveTorpedo = this.battleGrid.getIntersectionPoint(torpedo, copy, moveTorpedo);
            torpedo.setPayloadDestroyed(true);
        }
        showPayloadAttack(torpedo.getID(), WeaponType.TORPEDO, copy, moveTorpedo);
    }

    private void placeShips(Fleet fleet, Point[] pointArr, int i) {
        int i2 = 0;
        if (this.areAttackingSystemObject && this.systemObject.hasColony()) {
            Colony colony = this.systemObject.getColony();
            if (colony.getEmpireID() == fleet.empireID) {
                if (colony.hasBuilding(BuildingID.STAR_BASE)) {
                    this.starbaseSprite.setVisible(true);
                    ShipSpriteBattle shipSpriteBattle = this.starbaseSprite;
                    ShipType shipType = ShipType.STAR_BASE;
                    shipSpriteBattle.setShipIcon(0, shipType, 80.0f, shipType.getBattleScreenSize(), 1, true);
                    addShip(this.battleGrid.getStarBase(), this.starbaseSprite, (int) pointArr[0].getX(), (int) pointArr[0].getY());
                    i2 = 1;
                }
                if (colony.hasBuilding(BuildingID.TORPEDO_TURRET)) {
                    this.turret1Sprite.setVisible(true);
                    ShipSpriteBattle shipSpriteBattle2 = this.turret1Sprite;
                    ShipType shipType2 = ShipType.TORPEDO_TURRET;
                    shipSpriteBattle2.setShipIcon(0, shipType2, 80.0f, shipType2.getBattleScreenSize(), 1, true);
                    TorpedoTurret turret = this.battleGrid.getTurret("turret-1");
                    ShipSpriteBattle shipSpriteBattle3 = this.turret1Sprite;
                    Point point = GameProperties.BATTLE_TURRET_1_POSITION;
                    addShip(turret, shipSpriteBattle3, (int) point.getX(), (int) point.getY());
                    this.turret2Sprite.setVisible(true);
                    this.turret2Sprite.setShipIcon(0, shipType2, 80.0f, shipType2.getBattleScreenSize(), 1, true);
                    TorpedoTurret turret2 = this.battleGrid.getTurret("turret-2");
                    ShipSpriteBattle shipSpriteBattle4 = this.turret2Sprite;
                    Point point2 = GameProperties.BATTLE_TURRET_2_POSITION;
                    addShip(turret2, shipSpriteBattle4, (int) point2.getX(), (int) point2.getY());
                }
            }
        }
        for (Ship ship : fleet.getCombatShips()) {
            ShipSpriteBattle shipSpriteBattle5 = this.shipSpritesList.get(this.shipSpriteIndex);
            this.shipSpriteIndex++;
            shipSpriteBattle5.setVisible(true);
            shipSpriteBattle5.setShip(ship, 80.0f, ship.getShipType().getBattleScreenSize(), i, true);
            addShip(ship, shipSpriteBattle5, (int) pointArr[i2].getX(), (int) pointArr[i2].getY());
            i2++;
            if (i2 == 21) {
                return;
            }
        }
    }

    public void removeCharge(String str) {
        this.battleGrid.removeSpacialCharge(str);
        removePayloadSprite(str);
    }

    private void removePayloadSprite(String str) {
        if (doesPayloadSpritesContain(str)) {
            getPayloadSprite(str).clear();
        }
    }

    private void removeTorpedo(String str) {
        this.battleGrid.removeTorpedo(str);
        removePayloadSprite(str);
    }

    private void retreatButtonPressed() {
        int currentTileIndex = this.fleetIcon1.getCurrentTileIndex() / 7;
        if (isAI(currentTileIndex)) {
            return;
        }
        for (Ship ship : this.battleGrid.getShips(currentTileIndex)) {
            if (!this.battleGrid.isStation(ship) && ship.canGoFTL() && !ship.hasRetreated()) {
                this.shipSprites.get(ship.getID()).setShipStatus(ShipStatus.RETREAT);
            }
        }
        if (this.battleGrid.getSelectedShip() != null) {
            if (!this.battleGrid.getSelectedShip().getStatuses().contains(ShipStatus.RETREAT)) {
                shipDone();
            }
        } else {
            this.anyShipsMoving = false;
            unselect();
            this.isShipDone = true;
        }
        this.battleGrid.retreatShips(currentTileIndex);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void retreatShip() {
        SequenceEntityModifier sequenceEntityModifier;
        final Ship selectedShip = this.battleGrid.getSelectedShip();
        selectedShip.addStatus(ShipStatus.RETREAT);
        ShipSpriteBattle shipSpriteBattle = this.shipSprites.get(selectedShip.getID());
        setShipStats(selectedShip, false);
        float f2 = selectedShip.getEmpireID() == this.battleGrid.getAttackingFleet().empireID ? -1200.0f : 2480.0f;
        MoveModifier moveModifier = new MoveModifier(0.2f, shipSpriteBattle.getX(), f2, shipSpriteBattle.getY(), 360.0f);
        float angle = Functions.getAngle(new Point(shipSpriteBattle.getX(), shipSpriteBattle.getY()), new Point(f2, 360.0f));
        this.battleGrid.shipRetreated(selectedShip);
        if (angle >= 270.0f && shipSpriteBattle.getRotation() <= 90.0f) {
            sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.12f, shipSpriteBattle.getRotation(), 0.0f), new RotationModifier(0.12f, 359.0f, angle) { // from class: com.birdshel.Uciana.Scenes.BattleScene.13
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: k */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.B.sounds.playMoveFleetSound();
                }
            }, moveModifier}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.14
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: e */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.doneRetreating(selectedShip);
                }
            };
        } else if (angle <= 90.0f && shipSpriteBattle.getRotation() >= 270.0f) {
            sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.12f, shipSpriteBattle.getRotation(), 359.0f), new RotationModifier(0.12f, 0.0f, angle) { // from class: com.birdshel.Uciana.Scenes.BattleScene.15
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: k */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.B.sounds.playMoveFleetSound();
                }
            }, moveModifier}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.16
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: e */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.doneRetreating(selectedShip);
                }
            };
        } else {
            sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.25f, shipSpriteBattle.getRotation(), angle) { // from class: com.birdshel.Uciana.Scenes.BattleScene.17
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: k */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.B.sounds.playMoveFleetSound();
                }
            }, moveModifier}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.18
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: e */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.doneRetreating(selectedShip);
                }
            };
        }
        shipSpriteBattle.registerEntityModifier(sequenceEntityModifier);
    }

    private void select(Point point) {
        this.selectedHexOverlay.setVisible(true);
        this.selectedHexOverlay.setX(this.hexGrid[(int) point.getX()][(int) point.getY()].getX());
        this.selectedHexOverlay.setY(this.hexGrid[(int) point.getX()][(int) point.getY()].getY());
        showMoves();
    }

    private void selectNewShip() {
        if (this.battleGrid.isBattleDone()) {
            this.endBattle = true;
            this.startTime = System.currentTimeMillis();
            return;
        }
        if (this.battleGrid.isShipListEmpty()) {
            this.battleGrid.endTurn();
            for (Ship ship : this.battleGrid.getShips()) {
                if (ship.getShieldRegenPoints() > 0) {
                    this.shipSprites.get(ship.getID()).showShieldRegen(ship.getShieldRegenPoints());
                    this.shipSprites.get(ship.getID()).setShipStats(ship, true);
                }
            }
        }
        Ship nextShip = this.battleGrid.getNextShip();
        if (nextShip == null) {
            selectNewShip();
        }
        this.battleGrid.setSelectedShip(nextShip);
        Log.message("BattleScene", "New ship selected: " + nextShip.getID());
        if (nextShip instanceof Torpedo) {
            moveTorpedo((Torpedo) nextShip);
        } else if (nextShip instanceof SpacialCharge) {
            moveCharge((SpacialCharge) nextShip);
        } else {
            selectShip(nextShip);
        }
    }

    private void selectShip(Ship ship) {
        if (!GameProperties.isNonNormalEmpire(ship.getEmpireID()) && this.B.empires.get(ship.getEmpireID()).isHuman()) {
            setRetreatButton(ship.getEmpireID());
        }
        if (this.battleGrid.isShipRetreating()) {
            retreatShip();
        } else if (this.battleGrid.isShipWaitingToRetreat()) {
            this.battleGrid.moveShipFromWaitingToRetreat();
            shipDone();
        } else {
            setShipDisplay();
        }
    }

    private void selectSpecial(SpecialComponent specialComponent) {
        clearGrid();
        this.selectedSpecial = specialComponent;
        if (specialComponent.getID() == ShipComponentID.SCANNER) {
            for (int i = 0; i < 7; i++) {
                for (int i2 = 0; i2 < 15; i2++) {
                    if (this.battleGrid.hasShip(new Point(i2, i))) {
                        this.hexGrid[i2][i].setSpecialGrid(true);
                        this.hexGrid[i2][i].setSpecialHighlightedGrid(true);
                        this.battleGrid.setSpecialHex(i2, i, true);
                    }
                }
            }
        }
    }

    private void selectWeapon(Weapon weapon) {
        clearGrid();
        this.battleGrid.clearAttacks();
        this.selectedWeapon = weapon;
        if (weapon.hasUnfiredWeapons()) {
            for (int i = 0; i < 7; i++) {
                for (int i2 = 0; i2 < 15; i2++) {
                    if (Functions.getHexDistance(this.battleGrid.getSelectedX(), this.battleGrid.getSelectedY(), i2, i) != 0 && !isAI(this.battleGrid.getSelectedShip().getEmpireID())) {
                        Point point = new Point(i2, i);
                        if (weapon.getType() == WeaponType.SPACIAL_CHARGE) {
                            BattleGrid battleGrid = this.battleGrid;
                            if (battleGrid.hasLineOfSight(battleGrid.getSelectedHex(), point)) {
                                showAttackHex(i2, i);
                            }
                        } else if (this.battleGrid.hasShip(point)) {
                            Ship ship = this.battleGrid.getShip(point);
                            if (ship.isAlive()) {
                                BattleGrid battleGrid2 = this.battleGrid;
                                if (battleGrid2.hasLineOfSight(battleGrid2.getSelectedHex(), point)) {
                                    if (weapon.getType() == WeaponType.BEAM) {
                                        BattleGrid battleGrid3 = this.battleGrid;
                                        this.shipSprites.get(this.battleGrid.getShip(point).getID()).showHitPercentage(battleGrid3.getChanceToHit(battleGrid3.getSelectedShip(), ship));
                                    }
                                    showAttackHex(i2, i);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void setBattleScene(int i, int i2, int i3) {
        this.nebulas.set(i);
        this.retreatButton.setAlpha(1.0f);
        this.shipSpriteIndex = 0;
        this.shipPartsIndex = 0;
        this.shipDebrisIndex = 0;
        this.anyShipsMoving = false;
        this.systemID = i;
        this.attackerID = i2;
        this.defenderID = i3;
        if (GameProperties.isNonNormalEmpire(i2)) {
            this.attackerBattleAI = new BattleAI();
        } else {
            this.attackerBattleAI = this.B.empires.get(i2).getBattleAI();
        }
        this.attackerBattleAI.set(this.battleGrid, this);
        if (GameProperties.isNonNormalEmpire(i3)) {
            this.defenderBattleAI = new BattleAI();
        } else {
            this.defenderBattleAI = this.B.empires.get(i3).getBattleAI();
        }
        this.defenderBattleAI.set(this.battleGrid, this);
        setupIonStorms();
        setupAsteroids();
        for (ShipSpriteBattle shipSpriteBattle : this.shipSpritesList) {
            shipSpriteBattle.setVisible(false);
        }
        this.starbaseSprite.setVisible(false);
        this.turret1Sprite.setVisible(false);
        this.turret2Sprite.setVisible(false);
        placeShips(this.battleGrid.getAttackingFleet(), GameProperties.STARTING_ATTACK_POSITIONS, 1);
        placeShips(this.battleGrid.getDefendingFleet(), GameProperties.STARTING_DEFENSE_POSITIONS, 0);
        sortChildren();
        boolean z = isAI(i2) || isAI(i3);
        this.autoButton.setVisible(z);
        this.autoText.setVisible(z);
        this.autoButtonIcon.setVisible(z);
        if (Game.options.isOn(OptionID.SHOW_GRID, 1)) {
            setGridDisplay(true);
            this.showGridButtonHighlight.setVisible(true);
        }
        if (!GameProperties.isNonNormalEmpire(i2) && this.B.empires.get(i2).isHuman()) {
            setRetreatButton(i2);
        } else {
            setRetreatButton(i3);
        }
        Log.message("BattleScene", "Battle Starting");
        this.endBattle = false;
        this.alreadyOver = false;
        selectNewShip();
    }

    private void setGridDisplay(boolean z) {
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 15; i2++) {
                this.hexGrid[i2][i].setDisplayGrid(z);
            }
        }
    }

    private void setRetreatButton(int i) {
        this.fleetIcon1.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[i]);
        this.fleetIcon1.setCurrentTileIndex(1);
        this.fleetIcon2.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[i]);
        this.fleetIcon2.setCurrentTileIndex(1);
        this.fleetIcon3.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[i]);
        this.fleetIcon3.setCurrentTileIndex(1);
    }

    private void setShipDisplay() {
        Point battleLocation = this.battleGrid.getSelectedShip().getBattleLocation();
        HexSprite hexSprite = this.hexGrid[(int) battleLocation.getX()][(int) battleLocation.getY()];
        select(battleLocation);
        if (this.battleGrid.getSelectedShip() == null) {
            selectNextShip();
        } else if (isAI(this.battleGrid.getSelectedShip().getEmpireID())) {
            this.shipControl.setVisible(false);
            AIMoveShip(this.battleGrid.getSelectedShip());
        } else {
            this.shipControl.setVisible(true);
            this.shipControl.setControl(this.battleGrid.getSelectedShip(), hexSprite.getPosition());
        }
    }

    private void setShipStats(Ship ship) {
        setShipStats(ship, true);
    }

    private void setTimerForAfterExplosionAnimation() {
        AsyncTask.execute(new Runnable() { // from class: com.birdshel.Uciana.Scenes.BattleScene.20
            {
                BattleScene.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(840L);
                    BattleScene.R(BattleScene.this);
                    Log.message("BattleScene", "explosion Count decreased --, current: " + BattleScene.this.explosionCount);
                    if (BattleScene.this.explosionCount == 0 || BattleScene.this.afterExplosionAction == 3 || BattleScene.this.afterExplosionAction == 2) {
                        BattleScene.this.doAfterExplosionAction();
                    }
                } catch (Exception e2) {
                    Log.message("BattleScene", "Exception thrown during explosion: " + e2);
                }
            }
        });
    }

    private void setupAsteroids() {
        int i;
        Iterator<TiledSprite> it = this.asteroids.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            it.next().setVisible(false);
        }
        for (Point point : this.battleGrid.getAsteroidLocations()) {
            if (i >= this.asteroids.size()) {
                TiledSprite J = J(0.0f, 0.0f, this.B.graphics.gameIconsTexture, this.bufferManager, true);
                J.setIgnoreUpdate(true);
                this.asteroids.add(J);
            }
            this.asteroids.get(i).setVisible(true);
            float x = this.hexGrid[(int) point.getX()][(int) point.getY()].getX();
            float y = this.hexGrid[(int) point.getX()][(int) point.getY()].getY();
            int nextInt = Functions.random.nextInt(7);
            this.asteroids.get(i).setRotation(Functions.random.nextInt(45) + 314);
            this.asteroids.get(i).setCurrentTileIndex(GameIconEnum.ASTEROID1.ordinal() + nextInt);
            this.asteroids.get(i).setScale((Functions.random.nextFloat() * 0.3f) + 0.7f);
            this.asteroids.get(i).setX(x + ((100.0f - this.asteroids.get(i).getWidthScaled()) / 2.0f));
            this.asteroids.get(i).setY(y + ((100.0f - this.asteroids.get(i).getHeightScaled()) / 2.0f));
            i++;
        }
    }

    private void setupIonStorms() {
        int i;
        Iterator<IonStormSprite> it = this.ionStorms.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            it.next().setVisible(false);
        }
        for (Point point : this.battleGrid.getIonStormLocations()) {
            if (i >= this.ionStorms.size()) {
                IonStormSprite ionStormSprite = new IonStormSprite(this.B, this.bufferManager);
                this.ionStorms.add(ionStormSprite);
                attachChild(ionStormSprite);
            }
            Point positionOfHex = this.battleGrid.getPositionOfHex(point);
            positionOfHex.setX(positionOfHex.getX() - 15.0f);
            positionOfHex.setY(positionOfHex.getY() - 15.0f);
            IonStorm ionStorm = new IonStorm(positionOfHex);
            this.ionStorms.get(i).setVisible(true);
            this.ionStorms.get(i).set(ionStorm);
            i++;
        }
    }

    private void shipCoreBreach(final Ship ship) {
        Log.message("BattleScene", "Ship core breach: " + ship.getID());
        final Point copy = ship.getBattleLocation().copy();
        setShipStats(ship, false);
        this.shipControl.setVisible(false);
        ShipSpriteBattle shipSpriteBattle = this.shipSprites.get(ship.getID());
        showShipDebris(shipSpriteBattle);
        this.explosionCount++;
        Log.message("BattleScene", "explosion Count increased ++, current: " + this.explosionCount);
        AnimatedSprite animatedSprite = new AnimatedSprite(shipSpriteBattle.getX(), shipSpriteBattle.getY(), this.B.graphics.explosionTexture, this.bufferManager);
        animatedSprite.setSize(shipSpriteBattle.getSize(), shipSpriteBattle.getSize());
        attachChild(animatedSprite);
        setTimerForAfterExplosionAnimation();
        animatedSprite.animate(new long[]{105, 105, 105, 105, 105, 105, 105, 105}, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, false, new AnimatedSprite.IAnimationListener() { // from class: com.birdshel.Uciana.Scenes.BattleScene.19
            {
                BattleScene.this = this;
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFinished(AnimatedSprite animatedSprite2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFrameChanged(AnimatedSprite animatedSprite2, int i, int i2) {
                if (i2 == 5) {
                    BattleScene.this.checkBreachShockwaveHits(ship, copy);
                    BattleScene.this.checkForPayloadsInBlastRange(copy);
                }
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationLoopFinished(AnimatedSprite animatedSprite2, int i, int i2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationStarted(AnimatedSprite animatedSprite2, int i) {
            }
        });
        animatedSprite.registerEntityModifier(new AlphaModifier(0.84f, 1.0f, 0.0f));
        animatedSprite.registerEntityModifier(new ScaleAtModifier(0.84f, 1.0f, 5.0f, shipSpriteBattle.getSize() / 2.0f, shipSpriteBattle.getSize() / 2.0f));
        this.explosionList.add(animatedSprite);
        shipSpriteBattle.showShockwave();
        this.B.sounds.playExplosionSound();
        this.B.sounds.playShockwaveSound();
        this.battleGrid.shipDestroyed(ship);
    }

    private void shipDestroyedDone() {
        Ship selectedShip = this.battleGrid.getSelectedShip();
        if (selectedShip == null) {
            return;
        }
        if (selectedShip instanceof Torpedo) {
            this.anyShipsMoving = false;
            this.isShipDone = true;
        } else if (isAI(selectedShip.getEmpireID())) {
            AIMoveDone(selectedShip);
        }
    }

    private void showAttackHex(int i, int i2) {
        this.hexGrid[i][i2].setAttackGrid(true);
        this.hexGrid[i][i2].setAttackHighlightedGrid(true);
        this.battleGrid.setAttackHex(i, i2, true);
    }

    public void showBeam(Color color, Ship ship, Ship ship2, boolean z) {
        float f2;
        for (Map.Entry<String, ShipSpriteBattle> entry : this.shipSprites.entrySet()) {
            entry.getValue().setZIndex(GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED);
        }
        this.shipSprites.get(ship.getID()).setZIndex(GamesActivityResultCodes.RESULT_LICENSE_FAILED);
        sortChildren();
        HexSprite hexSprite = this.hexGrid[(int) ship.getBattleLocation().getX()][(int) ship.getBattleLocation().getY()];
        HexSprite hexSprite2 = this.hexGrid[(int) ship2.getBattleLocation().getX()][(int) ship2.getBattleLocation().getY()];
        Point point = new Point(hexSprite.getX() + 50.0f, hexSprite.getY() + 50.0f);
        Point point2 = new Point(hexSprite2.getX() + 50.0f, hexSprite2.getY() + 50.0f);
        if (!z) {
            float angle = Functions.getAngle(point2, point);
            if (Functions.percent(50)) {
                f2 = angle - 90.0f;
                if (f2 > 360.0f) {
                    f2 -= 360.0f;
                }
            } else {
                f2 = angle + 90.0f;
                if (f2 < 0.0f) {
                    f2 += 360.0f;
                }
            }
            point2 = point2.getPositionFromAngleAndDistance(f2, 50.0f);
        }
        this.beamAttack.setPosition(point.getX(), point.getY(), point2.getX(), point2.getY());
        this.beamAttack.setVisible(true);
        this.beamAttack.setColor(color);
        this.beamAttack.setLineWidth(5.0f);
        this.B.sounds.playWeaponSound(this.selectedWeapon.getSoundEffectIndex());
        this.beamAttack.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        SequenceEntityModifier sequenceEntityModifier = new SequenceEntityModifier(new AlphaModifier(0.4f, 0.0f, 1.0f), new AlphaModifier(0.3f, 1.0f, 1.0f), new AlphaModifier(0.4f, 1.0f, 0.0f)) { // from class: com.birdshel.Uciana.Scenes.BattleScene.10
            {
                BattleScene.this = this;
            }

            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: e */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                BattleScene.this.attackDone();
            }
        };
        sequenceEntityModifier.setAutoUnregisterWhenFinished(true);
        this.beamAttack.registerEntityModifier(sequenceEntityModifier);
        this.shipSprites.get(ship2.getID()).updateShipDamage(ship2);
        if (z) {
            showShipHit(ship2);
        }
        showDamage(ship2);
        Log.message("BattleScene", "showBeam: afterExplosionAction = NO_ACTION ");
        this.afterExplosionAction = 0;
        checkIfShipDestroyed(ship2);
    }

    private void showBeamAttack(final Color color, final Ship ship, final Ship ship2, final boolean z) {
        SequenceEntityModifier sequenceEntityModifier;
        if (this.battleGrid.getSelectedShip() instanceof StarBase) {
            showBeam(color, ship, ship2, z);
            return;
        }
        HexSprite hexSprite = this.hexGrid[(int) ship.getBattleLocation().getX()][(int) ship.getBattleLocation().getY()];
        for (Map.Entry<String, ShipSpriteBattle> entry : this.shipSprites.entrySet()) {
            ShipSpriteBattle value = entry.getValue();
            if (value.getX() == hexSprite.getX() + 10.0f && value.getY() == hexSprite.getY() + 10.0f) {
                HexSprite hexSprite2 = this.hexGrid[(int) ship2.getBattleLocation().getX()][(int) ship2.getBattleLocation().getY()];
                float angle = Functions.getAngle(new Point(value.getX(), value.getY()), new Point(hexSprite2.getX() + 10.0f, hexSprite2.getY() + 10.0f));
                if (angle >= 270.0f && value.getRotation() <= 90.0f) {
                    sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.12f, value.getRotation(), 0.0f), new RotationModifier(0.12f, 359.0f, angle)}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.7
                        {
                            BattleScene.this = this;
                        }

                        @Override // org.andengine.util.modifier.BaseModifier
                        /* renamed from: e */
                        public void c(IEntity iEntity) {
                            super.c(iEntity);
                            BattleScene.this.showBeam(color, ship, ship2, z);
                        }
                    };
                } else if (angle <= 90.0f && value.getRotation() >= 270.0f) {
                    sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.12f, value.getRotation(), 359.0f), new RotationModifier(0.12f, 0.0f, angle)}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.8
                        {
                            BattleScene.this = this;
                        }

                        @Override // org.andengine.util.modifier.BaseModifier
                        /* renamed from: e */
                        public void c(IEntity iEntity) {
                            super.c(iEntity);
                            BattleScene.this.showBeam(color, ship, ship2, z);
                        }
                    };
                } else {
                    sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.25f, value.getRotation(), angle)}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.9
                        {
                            BattleScene.this = this;
                        }

                        @Override // org.andengine.util.modifier.BaseModifier
                        /* renamed from: e */
                        public void c(IEntity iEntity) {
                            super.c(iEntity);
                            BattleScene.this.showBeam(color, ship, ship2, z);
                        }
                    };
                }
                value.registerEntityModifier(sequenceEntityModifier);
                return;
            }
        }
    }

    private void showDamage(Ship ship) {
        setShipStats(ship);
        ShipSpriteBattle shipSpriteBattle = this.shipSprites.get(ship.getID());
        shipSpriteBattle.updateShipDamage(ship);
        shipSpriteBattle.showDamage();
    }

    private void showExplosion(float f2, float f3, float f4) {
        final AnimatedSprite animatedSprite = new AnimatedSprite(f2, f3, this.B.graphics.explosionTexture, this.bufferManager);
        animatedSprite.setSize(f4, f4);
        attachChild(animatedSprite);
        animatedSprite.animate(new long[]{45, 45, 45, 45, 45, 45, 45, 45, 45}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, false, new AnimatedSprite.IAnimationListener() { // from class: com.birdshel.Uciana.Scenes.BattleScene.21
            {
                BattleScene.this = this;
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFinished(AnimatedSprite animatedSprite2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFrameChanged(AnimatedSprite animatedSprite2, int i, int i2) {
                if (i == 6) {
                    animatedSprite.setAlpha(0.0f);
                }
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationLoopFinished(AnimatedSprite animatedSprite2, int i, int i2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationStarted(AnimatedSprite animatedSprite2, int i) {
            }
        });
        this.explosionList.add(animatedSprite);
    }

    private void showGridButtonPressed() {
        if (this.showGridButtonHighlight.isVisible()) {
            this.showGridButtonHighlight.setVisible(false);
            setGridDisplay(false);
            Game.options.turnOff(OptionID.SHOW_GRID);
        } else {
            this.showGridButtonHighlight.setVisible(true);
            setGridDisplay(true);
            Game.options.turnOn(OptionID.SHOW_GRID);
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void showMoves() {
        this.battleGrid.clearMoves();
        Ship selectedShip = this.battleGrid.getSelectedShip();
        if (selectedShip == null) {
            selectNextShip();
        } else if (!selectedShip.hasBeenDestroyed() && !selectedShip.hasFullyRetreated()) {
            if (selectedShip.canMove()) {
                for (Point point : this.battleGrid.getNodesInRange(selectedShip.getMovementLeft())) {
                    if (!this.battleGrid.hasShip(point)) {
                        this.hexGrid[(int) point.getX()][(int) point.getY()].setMoveGrid(true);
                        this.battleGrid.setMoveHex((int) point.getX(), (int) point.getY(), true);
                    }
                }
            }
            if (selectedShip.hasWeaponsLeft()) {
                boolean hasBeamWeaponsLeft = selectedShip.hasBeamWeaponsLeft();
                for (Ship ship : this.battleGrid.getEnemyShips(selectedShip.getEmpireID())) {
                    Point battleLocation = ship.getBattleLocation();
                    if (this.battleGrid.hasLineOfSight(selectedShip.getBattleLocation(), battleLocation)) {
                        if (hasBeamWeaponsLeft) {
                            this.shipSprites.get(ship.getID()).showHitPercentage(this.battleGrid.getChanceToHit(selectedShip, ship));
                        }
                        this.hexGrid[(int) battleLocation.getX()][(int) battleLocation.getY()].setAttackGrid(true);
                        this.hexGrid[(int) battleLocation.getX()][(int) battleLocation.getY()].setAttackHighlightedGrid(true);
                        this.battleGrid.setAttackHex((int) battleLocation.getX(), (int) battleLocation.getY(), true);
                    }
                }
            }
        } else {
            this.battleGrid.clearShipFromLists(selectedShip);
            selectNextShip();
        }
    }

    private void showPayloadAttack(final String str, final WeaponType weaponType, Point point, Point point2) {
        MoveModifier moveModifier = new MoveModifier(0.25f, point.getX(), point2.getX(), point.getY(), point2.getY()) { // from class: com.birdshel.Uciana.Scenes.BattleScene.11
            {
                BattleScene.this = this;
            }

            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: m */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                WeaponType weaponType2 = weaponType;
                if (weaponType2 == WeaponType.TORPEDO) {
                    BattleScene.this.torpedoMoveDone(str);
                } else if (weaponType2 == WeaponType.SPACIAL_CHARGE) {
                    BattleScene.this.spacialChargeMoveDone(str);
                }
            }
        };
        moveModifier.setAutoUnregisterWhenFinished(true);
        if (weaponType == WeaponType.TORPEDO) {
            if (this.battleGrid.getTorpedo(str) == null) {
                return;
            }
        } else if (this.battleGrid.getSpacialCharge(str) == null) {
            return;
        }
        if (!doesPayloadSpritesContain(str)) {
            createPayloadSprite(str, weaponType, new Point(point.getX(), point.getY()));
            this.B.sounds.playWeaponSound(this.selectedWeapon.getSoundEffectIndex());
        }
        getPayloadSprite(str).getSprite().registerEntityModifier(moveModifier);
    }

    private void showShipDebris(ShipSpriteBattle shipSpriteBattle) {
        RotationModifier rotationModifier;
        if (this.shipDebrisIndex >= this.shipDebrisList.size()) {
            TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.gameIconsTexture, this.bufferManager);
            tiledSprite.setCurrentTileIndex(GameIconEnum.DEBRIS.ordinal());
            tiledSprite.setZIndex(1);
            tiledSprite.setSize(90.0f, 90.0f);
            tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getWidthScaled() / 2.0f);
            attachChild(tiledSprite);
            this.shipDebrisList.add(tiledSprite);
        }
        this.shipDebrisList.get(this.shipDebrisIndex).setVisible(true);
        this.shipDebrisList.get(this.shipDebrisIndex).setPosition(shipSpriteBattle.getX(), shipSpriteBattle.getY());
        this.shipDebrisList.get(this.shipDebrisIndex).registerEntityModifier(new ScaleAtModifier(0.6f, 0.0f, 1.0f, shipSpriteBattle.getSize() / 2.0f, shipSpriteBattle.getSize() / 2.0f));
        this.shipDebrisList.get(this.shipDebrisIndex).setRotation(Functions.random.nextInt(360));
        this.shipDebrisIndex++;
        for (int i = 0; i < 3; i++) {
            if (this.shipPartsIndex >= this.shipParts.size()) {
                TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, this.B.graphics.shipDebrisTexture, this.bufferManager);
                tiledSprite2.setZIndex(1);
                if (Functions.percent(50)) {
                    rotationModifier = new RotationModifier(Functions.random.nextInt(10) + 15, 0.0f, 360.0f);
                } else {
                    rotationModifier = new RotationModifier(Functions.random.nextInt(10) + 15, 360.0f, 0.0f);
                }
                tiledSprite2.registerEntityModifier(new LoopEntityModifier(rotationModifier));
                attachChild(tiledSprite2);
                this.shipParts.add(tiledSprite2);
            }
            this.shipParts.get(this.shipPartsIndex).setVisible(true);
            int nextInt = Functions.random.nextInt(10) + 20;
            float f2 = nextInt;
            this.shipParts.get(this.shipPartsIndex).setSize(f2, f2);
            float f3 = f2 / 2.0f;
            this.shipParts.get(this.shipPartsIndex).setRotationCenter(f3, f3);
            this.shipParts.get(this.shipPartsIndex).registerEntityModifier(new ScaleAtModifier(0.6f, 0.0f, 1.0f, f3, f3));
            int i2 = 90 - nextInt;
            this.shipParts.get(this.shipPartsIndex).setX(shipSpriteBattle.getX() + Functions.random.nextInt(i2));
            this.shipParts.get(this.shipPartsIndex).setY(shipSpriteBattle.getY() + Functions.random.nextInt(i2));
            this.shipParts.get(this.shipPartsIndex).setCurrentTileIndex(Functions.random.nextInt(3));
            this.shipPartsIndex++;
        }
    }

    private void showShipHit(Ship ship) {
        if (ship.getShieldHitPoints() != 0) {
            this.shipSprites.get(ship.getID()).showShieldHit();
            this.B.sounds.playShieldHit();
            return;
        }
        this.shipSprites.get(ship.getID()).showHitExplosion();
        this.B.sounds.playArmorHit();
    }

    private void showShockwaveHit(Ship ship, int i, String str) {
        showShipHit(ship);
        Point takeDamage = ship.takeDamage(i);
        Log.message("BattleScene", "Shockwave from " + str + " has hit " + ship.getID() + " for " + Integer.toString(i));
        ShipStatus checkShipStatusNeedsToBeAdded = this.battleGrid.checkShipStatusNeedsToBeAdded(ship);
        if (checkShipStatusNeedsToBeAdded != ShipStatus.NONE) {
            this.shipSprites.get(ship.getID()).setShipStatus(checkShipStatusNeedsToBeAdded);
        }
        setShipStats(ship, true);
        ShipSpriteBattle shipSpriteBattle = this.shipSprites.get(ship.getID());
        shipSpriteBattle.updateShipDamage(ship);
        shipSpriteBattle.setDamageToShow(Integer.toString(i));
        shipSpriteBattle.setDamageBar((int) takeDamage.getX(), (int) takeDamage.getY());
        showDamage(ship);
        checkIfShipDestroyed(ship);
    }

    private void spacialChargeDone() {
        Ship selectedShip = this.battleGrid.getSelectedShip();
        if (selectedShip == null) {
            this.isShipDone = true;
            this.anyShipsMoving = false;
        } else if (selectedShip instanceof SpacialCharge) {
            Log.message("BattleScene", "Charge done. instance of charge");
            shipDone();
        } else if (isAI(selectedShip.getEmpireID())) {
            Log.message("BattleScene", "Charge done. AI or AUTO instance of ship/base: " + selectedShip.getID());
            AIMoveDone(selectedShip);
        } else {
            Log.message("BattleScene", "Charge done. Player instance of ship/base: " + selectedShip.getID());
            this.anyShipsMoving = false;
            this.shipControl.setVisible(true);
            this.shipControl.update();
            if (selectedShip.hasWeaponsLeft()) {
                return;
            }
            clearGrid();
            showMoves();
            setShipDisplay();
        }
    }

    private void spacialChargeExplode(final String str) {
        Log.message("BattleScene", "Charge " + str + " has exploded");
        final SpacialCharge spacialCharge = this.battleGrid.getSpacialCharge(str);
        if (this.battleGrid.hasShip(spacialCharge.getTargetPosition())) {
            Ship ship = this.battleGrid.getShip(spacialCharge.getTargetPosition());
            if (!ship.hasBeenDestroyed()) {
                showShockwaveHit(ship, spacialCharge.getDamage(), spacialCharge.getID());
            }
        }
        AnimatedSprite animatedSprite = new AnimatedSprite(0.0f, 0.0f, this.B.graphics.explosionTexture, this.bufferManager);
        animatedSprite.setVisible(false);
        attachChild(animatedSprite);
        animatedSprite.animate(new long[]{105, 105, 105, 105, 105, 105, 105, 105}, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, false, new AnimatedSprite.IAnimationListener() { // from class: com.birdshel.Uciana.Scenes.BattleScene.12
            {
                BattleScene.this = this;
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFinished(AnimatedSprite animatedSprite2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFrameChanged(AnimatedSprite animatedSprite2, int i, int i2) {
                if (i2 == 5) {
                    BattleScene.this.checkChargeShockwaveHits(spacialCharge);
                    BattleScene.this.checkForPayloadsInBlastRange(spacialCharge.getTargetPosition());
                }
                if (i2 == 7) {
                    BattleScene.this.removeCharge(str);
                    if (BattleScene.this.battleGrid.getSelectedShip() instanceof SpacialCharge) {
                        BattleScene.this.removeCharge(str);
                        BattleScene.this.anyShipsMoving = false;
                        BattleScene.this.isShipDone = true;
                        return;
                    }
                    BattleScene.this.removeCharge(str);
                    BattleScene.this.attackDone();
                }
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationLoopFinished(AnimatedSprite animatedSprite2, int i, int i2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationStarted(AnimatedSprite animatedSprite2, int i) {
            }
        });
        this.explosionList.add(animatedSprite);
        if (doesPayloadSpritesContain(str)) {
            TiledSprite sprite = getPayloadSprite(str).getSprite();
            TiledSprite J = J(0.0f, 0.0f, this.B.graphics.gameIconsTexture, this.bufferManager, true);
            J.setCurrentTileIndex(spacialCharge.getShockwaveIndex());
            J.setPosition(sprite.getX(), sprite.getY());
            J.setSize(sprite.getWidthScaled(), sprite.getHeightScaled());
            J.registerEntityModifier(new AlphaModifier(1.0f, 1.0f, 0.0f));
            J.registerEntityModifier(new ScaleAtModifier(1.0f, 0.0f, 6.0f, sprite.getWidthScaled() / 2.0f, sprite.getHeightScaled() / 2.0f));
            this.spacialShockwaves.add(J);
            this.B.sounds.playShockwaveSound();
        }
    }

    private void spacialChargeHitAsteroid(String str) {
        Log.message("BattleScene", "Charge: " + str + " hit asteroid");
        this.battleGrid.getSpacialCharge(str).setPayloadDestroyed(false);
        if (!doesPayloadSpritesContain(str)) {
            Log.message("BattleScene", "Error getting charge sprite");
        } else {
            TiledSprite sprite = getPayloadSprite(str).getSprite();
            showExplosion(sprite.getX(), sprite.getY(), sprite.getWidthScaled());
        }
        this.B.sounds.playArmorHit();
        if (this.battleGrid.getSelectedShip() instanceof SpacialCharge) {
            removeCharge(str);
            this.anyShipsMoving = false;
            this.isShipDone = true;
            return;
        }
        removeCharge(str);
        attackDone();
    }

    public void spacialChargeMoveDone(String str) {
        if (this.battleGrid.checkIfChargeReachedTarget(str)) {
            spacialChargeExplode(str);
        } else if (this.battleGrid.getSpacialCharge(str).isPayloadDestroyed()) {
            spacialChargeHitAsteroid(str);
        } else {
            spacialChargeDone();
        }
    }

    private void specialShip(Point point, SpecialComponent specialComponent) {
        this.selectedSpecial = specialComponent;
        if (specialComponent.getID() == ShipComponentID.SCANNER && this.battleGrid.hasShip(point)) {
            showShipDetails(this.battleGrid.getShip(point));
        }
    }

    private void torpedoDone() {
        Ship selectedShip = this.battleGrid.getSelectedShip();
        if (selectedShip == null) {
            this.isShipDone = true;
            this.anyShipsMoving = false;
        } else if (selectedShip instanceof Torpedo) {
            Log.message("BattleScene", "Torpedo done. instance of torpedo");
            shipDone();
        } else if (isAI(selectedShip.getEmpireID())) {
            Log.message("BattleScene", "Torpedo done. AI or AUTO instance of ship/base: " + selectedShip.getID());
            AIMoveDone(selectedShip);
        } else {
            Log.message("BattleScene", "Torpedo done. Player instance of ship/base: " + selectedShip.getID());
            this.anyShipsMoving = false;
            this.shipControl.setVisible(true);
            this.shipControl.update();
            if (selectedShip.hasWeaponsLeft()) {
                return;
            }
            clearGrid();
            showMoves();
            setShipDisplay();
        }
    }

    private void torpedoHitAsteroid(String str) {
        this.battleGrid.getTorpedo(str).setPayloadDestroyed(false);
        if (doesPayloadSpritesContain(str)) {
            TiledSprite sprite = getPayloadSprite(str).getSprite();
            showExplosion(sprite.getX(), sprite.getY(), sprite.getWidthScaled());
            Log.message("BattleScene", "Torpedo: " + str + " hit asteroid at x: " + sprite.getX() + ", y: " + sprite.getY());
            this.B.sounds.playArmorHit();
            if (this.battleGrid.getSelectedShip() instanceof Torpedo) {
                removeTorpedo(str);
                this.anyShipsMoving = false;
                this.isShipDone = true;
                return;
            }
            removeTorpedo(str);
            attackDone();
            return;
        }
        Log.message("BattleScene", "Error getting torpedo sprite");
    }

    private void torpedoHitShip(String str) {
        String targetID = this.battleGrid.getTorpedo(str).getTargetID();
        Ship ship = this.battleGrid.getShip(targetID);
        showShipHit(ship);
        Map<String, Object> torpedoDamage = this.battleGrid.getTorpedoDamage(str);
        String str2 = (String) torpedoDamage.get("damage");
        if (torpedoDamage.containsKey("status")) {
            this.shipSprites.get(ship.getID()).setShipStatus((ShipStatus) torpedoDamage.get("status"));
        }
        this.shipSprites.get(targetID).setDamageToShow(str2);
        this.shipSprites.get(targetID).setDamageBar(((Integer) torpedoDamage.get("shieldDamage")).intValue(), ((Integer) torpedoDamage.get("armorDamage")).intValue());
        showDamage(ship);
        Log.message("BattleScene", "Torpedo: " + str + " hit ship " + targetID + " for " + str2);
        if (checkIfShipDestroyed(ship)) {
            if (this.battleGrid.getSelectedShip() instanceof Torpedo) {
                Log.message("BattleScene", "torpedoHitShip: afterExplosionAction = SHIP_DESTROYED_DONE");
                this.afterExplosionAction = 2;
            } else {
                Log.message("BattleScene", "torpedoHitShip: afterExplosionAction = ATTACK_DONE");
                this.afterExplosionAction = 3;
            }
            removeTorpedo(str);
        } else if (this.battleGrid.getSelectedShip() instanceof Torpedo) {
            removeTorpedo(str);
            this.anyShipsMoving = false;
            this.isShipDone = true;
        } else {
            removeTorpedo(str);
            attackDone();
        }
    }

    public void torpedoMoveDone(String str) {
        if (this.battleGrid.checkTorpedoForHit(str)) {
            torpedoHitShip(str);
        } else if (this.battleGrid.getTorpedo(str).isPayloadDestroyed()) {
            torpedoHitAsteroid(str);
        } else {
            torpedoDone();
        }
    }

    private void unselect() {
        this.selectedHexOverlay.setVisible(false);
        clearGrid();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public boolean anyShipsMoving() {
        return this.anyShipsMoving;
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
    }

    protected void c0(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof SelectAttackScene) {
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
            int type = attackSceneData.getType();
            if (type == 0) {
                this.B.attackScene.N(empireID, targetID, systemID, orbit);
            } else if (type == 1) {
                this.B.attackScene.set(false, empireID, targetID, systemID);
            } else if (type == 2) {
                this.B.attackScene.set(false, empireID, targetID, systemID, orbit);
            } else if (type != 3) {
            } else {
                this.B.attackScene.O();
            }
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (this.shipControl.isVisible() && checkShipControl(i, point)) {
            return;
        }
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            checkActionUp(point);
        } else if (i != 2) {
        } else {
            checkActionMove(point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.bufferManager = vertexBufferObjectManager;
        createNebula();
        createHexGrid();
        createButtons();
        createAttackGraphics();
        createOverlays();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        for (int i = 0; i < 42; i++) {
            ShipSpriteBattle shipSpriteBattle = this.B.shipSpriteBattlePool.get();
            attachChild(shipSpriteBattle);
            this.shipSpritesList.add(shipSpriteBattle);
        }
        ShipSpriteBattle shipSpriteBattle2 = this.B.shipSpriteBattlePool.get();
        this.starbaseSprite = shipSpriteBattle2;
        attachChild(shipSpriteBattle2);
        ShipSpriteBattle shipSpriteBattle3 = this.B.shipSpriteBattlePool.get();
        this.turret1Sprite = shipSpriteBattle3;
        attachChild(shipSpriteBattle3);
        ShipSpriteBattle shipSpriteBattle4 = this.B.shipSpriteBattlePool.get();
        this.turret2Sprite = shipSpriteBattle4;
        attachChild(shipSpriteBattle4);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.planetSprite = planetSprite;
        planetSprite.setMoonRange(400, 400);
        this.planetSprite.setPosition(1280.0f, 360.0f);
        this.planetSprite.setZIndex(1);
        attachChild(this.planetSprite);
        this.shipControl.getPoolElements();
        this.B.shipDetailOverlay.getPoolElements();
        sortChildren();
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void moveShip(Point point) {
        SequenceEntityModifier sequenceEntityModifier;
        Log.message("BattleScene", "Move ship: " + this.battleGrid.getSelectedShip().getID());
        this.anyShipsMoving = true;
        ShipSpriteBattle shipSpriteBattle = this.shipSprites.get(this.battleGrid.getSelectedShip().getID());
        final Ship selectedShip = this.battleGrid.getSelectedShip();
        Point point2 = new Point(selectedShip.getBattleLocation());
        setShipStats(selectedShip, false);
        this.battleGrid.shipMoved(selectedShip, point);
        HexSprite hexSprite = this.hexGrid[(int) point.getX()][(int) point.getY()];
        MoveModifier moveModifier = new MoveModifier(0.5f, shipSpriteBattle.getX(), hexSprite.getX() + 10.0f, shipSpriteBattle.getY(), hexSprite.getY() + 10.0f);
        float angle = Functions.getAngle(new Point(shipSpriteBattle.getX(), shipSpriteBattle.getY()), new Point(hexSprite.getX() + 10.0f, hexSprite.getY() + 10.0f));
        if (angle >= 270.0f && shipSpriteBattle.getRotation() <= 90.0f) {
            sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.12f, shipSpriteBattle.getRotation(), 0.0f), new RotationModifier(0.12f, 359.0f, angle) { // from class: com.birdshel.Uciana.Scenes.BattleScene.1
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: k */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.B.sounds.playShipMoveSound();
                }
            }, moveModifier}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.2
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: e */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    if (selectedShip.isAlive()) {
                        BattleScene.this.moveDone();
                    } else {
                        BattleScene.this.selectNextShip();
                    }
                }
            };
        } else if (angle <= 90.0f && shipSpriteBattle.getRotation() >= 270.0f) {
            sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.12f, shipSpriteBattle.getRotation(), 359.0f), new RotationModifier(0.12f, 0.0f, angle) { // from class: com.birdshel.Uciana.Scenes.BattleScene.3
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: k */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.B.sounds.playShipMoveSound();
                }
            }, moveModifier}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.4
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: e */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    if (selectedShip.isAlive()) {
                        BattleScene.this.moveDone();
                    } else {
                        BattleScene.this.selectNextShip();
                    }
                }
            };
        } else {
            sequenceEntityModifier = new SequenceEntityModifier(new IEntityModifier[]{new RotationModifier(0.25f, shipSpriteBattle.getRotation(), angle) { // from class: com.birdshel.Uciana.Scenes.BattleScene.5
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: k */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    BattleScene.this.B.sounds.playShipMoveSound();
                }
            }, moveModifier}) { // from class: com.birdshel.Uciana.Scenes.BattleScene.6
                {
                    BattleScene.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: e */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    if (selectedShip.isAlive()) {
                        BattleScene.this.moveDone();
                    } else {
                        BattleScene.this.selectNextShip();
                    }
                }
            };
        }
        sequenceEntityModifier.setAutoUnregisterWhenFinished(true);
        shipSpriteBattle.registerEntityModifier(sequenceEntityModifier);
        selectedShip.movementUsed(Functions.getHexDistance(point2, selectedShip.getBattleLocation()));
        unselect();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.b
            {
                BattleScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                BattleScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void selectComponent(ShipComponent shipComponent) {
        if (shipComponent instanceof Weapon) {
            selectWeapon((Weapon) shipComponent);
        } else {
            selectSpecial((SpecialComponent) shipComponent);
        }
    }

    public void selectMove() {
        clearGrid();
        showMoves();
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void selectNextShip() {
        this.isShipDone = true;
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void selfDestruct(Ship ship) {
        ship.setDestroyed();
        this.afterExplosionAction = 1;
        shipCoreBreach(ship);
    }

    public void set(int i, int i2, int i3) {
        Log.message("BattleScene", "Battle Set 1");
        this.battleGrid = new BattleGrid(this.B, i, -1, i2, i3);
        clearElements();
        this.areAttackingSystemObject = false;
        setBattleScene(i, i2, i3);
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void shipDone() {
        if (this.battleGrid.getSelectedShip() != null) {
            Log.message("BattleScene", "Ship is done: " + this.battleGrid.getSelectedShip().getID());
        }
        this.anyShipsMoving = false;
        unselect();
        this.battleGrid.addDoneShip();
        this.isShipDone = true;
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void shipRetreat(Ship ship) {
        if (this.battleGrid.isStation(ship) || !ship.canGoFTL() || ship.hasRetreated()) {
            return;
        }
        ShipStatus shipStatus = ShipStatus.RETREAT;
        ship.addStatus(shipStatus);
        this.battleGrid.addRetreatingShip(ship);
        this.shipSprites.get(ship.getID()).setShipStatus(shipStatus);
        shipDone();
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void shipWait() {
        if (this.battleGrid.getSelectedShip() != null) {
            Log.message("BattleScene", "Ship is waiting: " + this.battleGrid.getSelectedShip().getID());
        }
        unselect();
        this.battleGrid.addWaitingShip();
        this.isShipDone = true;
    }

    public void showShipDetails(Ship ship) {
        this.B.shipDetailOverlay.setOverlay(ship);
        setChildScene(this.B.shipDetailOverlay, false, false, true);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        this.G.setVisible(false);
        super.switched();
        if (Game.options.isOn(OptionID.MUSIC, 1)) {
            this.B.music.resumeBattleTheme();
        }
        Options options = Game.options;
        TutorialID tutorialID = TutorialID.BATTLE_GRID;
        if (options.shouldTutorialBeShown(tutorialID)) {
            showMessage(new BattleGridTutorial());
            Game.options.markSeen(tutorialID);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        if (this.isShipDone) {
            this.isShipDone = false;
            selectNewShip();
        }
        if (!this.endBattle || System.currentTimeMillis() - this.startTime <= 500) {
            return;
        }
        endBattle();
    }

    private void setShipStats(Ship ship, boolean z) {
        ShipSpriteBattle shipSpriteBattle = this.shipSprites.get(ship.getID());
        if (shipSpriteBattle != null) {
            shipSpriteBattle.setShipStats(ship, z);
        }
    }

    public void set(int i, int i2, int i3, int i4) {
        Log.message("BattleScene", "Battle Set 2");
        this.battleGrid = new BattleGrid(this.B, i, i2, i3, i4);
        clearElements();
        this.areAttackingSystemObject = true;
        SystemObject systemObject = this.B.galaxy.getSystemObject(i, i2);
        this.systemObject = systemObject;
        if (systemObject.isPlanet()) {
            Planet planet = (Planet) this.systemObject;
            this.planetSprite.setPlanet(planet, planet.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene));
        }
        setBattleScene(i, i3, i4);
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void attackShip(Point point, Weapon weapon) {
        this.selectedWeapon = weapon;
        attack(weapon, point);
        clearGrid();
        selectWeapon(weapon);
        this.shipControl.update();
    }
}
