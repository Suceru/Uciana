package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Math.SystemOrbit;
import com.birdshel.Uciana.Players.RaceAttributeType;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.Armor;
import com.birdshel.Uciana.Ships.ShipComponents.Shield;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.SpecialComponent;
import com.birdshel.Uciana.Ships.ShipComponents.SublightEngine;
import com.birdshel.Uciana.Ships.ShipComponents.TargetingComputer;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponType;
import com.birdshel.Uciana.Utility.Log;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Ship implements Comparable<Ship> {

    /* renamed from: a */
    protected ShipType f1548a;
    private SystemOrbit aiTask;
    protected String b;
    private Point battleLocation;
    private boolean beenUsed;

    /* renamed from: c */
    protected String f1549c;

    /* renamed from: d */
    protected int f1550d;
    private int designNumber;

    /* renamed from: e */
    protected int f1551e;

    /* renamed from: f */
    protected int f1552f;
    protected int g;
    protected int h;
    private boolean hasBeenDestroyed;
    private boolean hasFullyRetreated;
    protected Armor i;
    private int initiative;
    private boolean isAuto;
    protected Shield j;
    protected TargetingComputer k;
    protected SublightEngine l;
    protected List<ShipComponent> m;
    protected int n;
    protected int o;
    private int productionCost;
    private List<Integer> route;
    private int shieldRegenPoints;
    private final Set<ShipStatus> statuses;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: a */
        ShipType f1553a;
        String b;

        /* renamed from: c */
        String f1554c;

        /* renamed from: e */
        int f1556e;

        /* renamed from: f */
        int f1557f;
        int g;
        int h;
        Armor i;
        Shield j;
        TargetingComputer k;
        SublightEngine l;
        int n;
        int o;
        int p;
        boolean q;
        int r;
        int s;
        SystemOrbit t;
        boolean u;

        /* renamed from: d */
        int f1555d = 0;
        List<ShipComponent> m = new ArrayList();

        private void setCombatShip() {
            this.f1557f = (int) (this.f1553a.getSizeClass() * 100 * this.i.getArmorMultiplier());
            for (ShipComponent shipComponent : this.m) {
                ShipComponentID id = shipComponent.getID();
                ShipComponentID shipComponentID = ShipComponentID.HARDENED_ALLOY;
                if (id == shipComponentID) {
                    int i = this.f1557f;
                    this.f1557f = (int) (i + (i * ((SpecialComponent) ShipComponents.get(shipComponentID)).getEffectValue()));
                }
            }
            int strengthMultiplier = this.j.getStrengthMultiplier() * this.f1553a.getSizeClass();
            this.h = strengthMultiplier;
            this.g = strengthMultiplier;
            this.n = this.l.getCombatSpeed();
            this.r = this.f1553a.getInitiative() + this.l.getCombatSpeed();
            this.t = new SystemOrbit(-1, -1);
        }

        public Builder armor(Armor armor) {
            this.i = armor;
            return this;
        }

        public Builder armorHitPoints(int i) {
            this.f1556e = i;
            return this;
        }

        public Builder beenUsed(boolean z) {
            this.q = z;
            return this;
        }

        public Ship buildCombatShip() {
            setCombatShip();
            this.f1556e = this.f1557f;
            this.u = false;
            return new Ship(this);
        }

        public Ship buildNonCombatShip() {
            this.p = this.f1553a.getBaseProductionCost();
            this.q = false;
            this.b = this.f1553a.getString(this.s);
            this.t = new SystemOrbit(-1, -1);
            this.u = false;
            return new Ship(this);
        }

        public Builder designNumber(int i) {
            this.o = i;
            return this;
        }

        public Builder empireID(int i) {
            this.s = i;
            return this;
        }

        public Builder hullDesign(int i) {
            this.f1555d = i;
            return this;
        }

        public Builder id(String str) {
            this.f1554c = str;
            return this;
        }

        public Builder isAuto(boolean z) {
            this.u = z;
            return this;
        }

        public Ship loadCombatShip() {
            setCombatShip();
            return new Ship(this);
        }

        public Ship loadNonCombatShip() {
            this.p = this.f1553a.getBaseProductionCost();
            this.b = this.f1553a.getString(this.s);
            this.t = new SystemOrbit(-1, -1);
            return new Ship(this);
        }

        public Builder name(String str) {
            this.b = str;
            return this;
        }

        public Builder productionCost(int i) {
            this.p = i;
            return this;
        }

        public Builder shield(Shield shield) {
            this.j = shield;
            return this;
        }

        public Builder shipComponents(List<ShipComponent> list) {
            this.m = list;
            return this;
        }

        public Builder shipType(ShipType shipType) {
            this.f1553a = shipType;
            return this;
        }

        public Builder sublightEngine(SublightEngine sublightEngine) {
            this.l = sublightEngine;
            return this;
        }

        public Builder targetingComputer(TargetingComputer targetingComputer) {
            this.k = targetingComputer;
            return this;
        }
    }

    public Ship() {
        this.m = new ArrayList();
        this.statuses = new HashSet();
        this.hasBeenDestroyed = false;
        this.hasFullyRetreated = false;
    }

    private float getAdditionalShieldRechargeRate() {
        for (ShipComponent shipComponent : this.m) {
            if (shipComponent.getID() == ShipComponentID.SHIELD_CAPACITOR) {
                return ((SpecialComponent) shipComponent).getEffectValue();
            }
        }
        return 0.0f;
    }

    private int getInitiative() {
        return this.initiative;
    }

    private int getShieldRechargeHP() {
        return (int) (this.h * (this.j.getRechargeRate() + getAdditionalShieldRechargeRate()));
    }

    public boolean a() {
        if (this.f1548a == ShipType.SCOUT) {
            return true;
        }
        for (ShipComponent shipComponent : this.m) {
            if (shipComponent.getID() == ShipComponentID.SCOUTING) {
                return true;
            }
        }
        return false;
    }

    public void addStatus(ShipStatus shipStatus) {
        this.statuses.add(shipStatus);
    }

    public int b() {
        return getProductionCost() / 2;
    }

    public void c() {
        this.g = this.h;
    }

    public boolean canGoFTL() {
        return !this.statuses.contains(ShipStatus.FTL_DISABLED);
    }

    public boolean canMove() {
        return !this.statuses.contains(ShipStatus.SUBLIGHT_DISABLED);
    }

    public Ship createClone(String str) {
        if (this.f1548a.isCombatShip()) {
            ArrayList arrayList = new ArrayList();
            for (ShipComponent shipComponent : this.m) {
                ShipComponent shipComponent2 = ShipComponents.get(shipComponent.getID());
                shipComponent2.setComponentCount(shipComponent.getComponentCount());
                arrayList.add(shipComponent2);
            }
            return new Builder().id(str).shipType(this.f1548a).name(this.b).empireID(this.o).designNumber(this.designNumber).productionCost(this.productionCost).armor(this.i).shield(this.j).targetingComputer(this.k).sublightEngine(this.l).shipComponents(arrayList).hullDesign(this.f1550d).buildCombatShip();
        }
        return new Builder().id(str).shipType(this.f1548a).name(this.b).empireID(this.o).buildNonCombatShip();
    }

    public void d() {
        this.f1551e = this.f1552f;
    }

    public void e() {
        this.statuses.clear();
        this.hasFullyRetreated = false;
    }

    public void f() {
        this.beenUsed = false;
    }

    public SystemOrbit getAiTask() {
        return this.aiTask;
    }

    public Armor getArmor() {
        return this.i;
    }

    public int getArmorHitPoints() {
        return this.f1551e;
    }

    public int getArmorPercent() {
        return Math.round((getArmorHitPoints() / getMaxArmorHitPoints()) * 100.0f);
    }

    public Entity getArmorShieldHpDescription(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        Entity entity = new Entity();
        Font font = game.fonts.smallInfoFont;
        String num = Integer.toString(getMaxArmorHitPoints());
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 8.0f, font, num, new TextOptions(horizontalAlign), vertexBufferObjectManager);
        entity.attachChild(text);
        float widthScaled = text.getWidthScaled() + 0.0f;
        TiledSprite tiledSprite = new TiledSprite(widthScaled, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.ARMOR.ordinal());
        entity.attachChild(tiledSprite);
        float widthScaled2 = widthScaled + tiledSprite.getWidthScaled() + 5.0f;
        if (this.j.getID() != ShipComponentID.NO_SHIELDS) {
            Text text2 = new Text(widthScaled2, 8.0f, game.fonts.smallInfoFont, Integer.toString(getMaxShieldHitPoints()), new TextOptions(horizontalAlign), vertexBufferObjectManager);
            entity.attachChild(text2);
            TiledSprite tiledSprite2 = new TiledSprite(widthScaled2 + text2.getWidthScaled(), 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.SHIELD.ordinal());
            tiledSprite2.setScaleCenter(0.0f, 0.0f);
            tiledSprite2.setSize(30.0f, 30.0f);
            entity.attachChild(tiledSprite2);
        }
        return entity;
    }

    public Point getBattleLocation() {
        return this.battleLocation;
    }

    public int getCombatStrength() {
        if (isCombatShip()) {
            int i = this.f1551e + 0 + this.h;
            for (ShipComponent shipComponent : this.m) {
                if (shipComponent instanceof Weapon) {
                    Weapon weapon = (Weapon) shipComponent;
                    if (weapon.isShipToShipWeapon()) {
                        i += weapon.getMaxDamage() * weapon.getComponentCount();
                    }
                }
            }
            return i;
        }
        return 0;
    }

    public Entity getComponentsDescription(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        Entity entity = new Entity();
        float f2 = 0.0f;
        for (ShipComponent shipComponent : this.m) {
            if (shipComponent.getComponentCount() > 1) {
                Text text = new Text(f2, 8.0f, game.fonts.smallInfoFont, Integer.toString(shipComponent.getComponentCount()), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
                entity.attachChild(text);
                f2 += text.getWidthScaled() + 1.0f;
            }
            TiledSprite tiledSprite = new TiledSprite(f2, 0.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager);
            tiledSprite.setCurrentTileIndex(shipComponent.getIconIndex());
            tiledSprite.setScaleCenter(0.0f, 0.0f);
            tiledSprite.setSize(30.0f, 30.0f);
            entity.attachChild(tiledSprite);
            f2 += tiledSprite.getWidthScaled() + 5.0f;
        }
        return entity;
    }

    public List<ShipComponent> getCopyOfShipComponents() {
        ArrayList arrayList = new ArrayList();
        for (ShipComponent shipComponent : this.m) {
            if (shipComponent instanceof Weapon) {
                Weapon weapon = new Weapon((Weapon) shipComponent);
                weapon.setComponentCount(shipComponent.getComponentCount());
                arrayList.add(weapon);
            } else {
                SpecialComponent specialComponent = new SpecialComponent((SpecialComponent) shipComponent);
                specialComponent.setComponentCount(shipComponent.getComponentCount());
                arrayList.add(specialComponent);
            }
        }
        return arrayList;
    }

    public int getCoreBreachDamage() {
        return (int) (Functions.getRandom(20, 40) * (this.f1548a.id - 3) * (!GameProperties.isNonNormalEmpire(this.o) ? GameData.empires.get(this.o).getTech().getPowerCoreMultiplier() : 3.25f));
    }

    public int getDesignNumber() {
        return this.designNumber;
    }

    public int getEmpireID() {
        return this.o;
    }

    public int getEvasionBonus() {
        if (GameProperties.isNonNormalEmpire(this.o)) {
            return 0;
        }
        return (int) (0 + GameData.empires.get(this.o).getAttributeTypeBonus(RaceAttributeType.BEAM_EVASION));
    }

    public int getHullDesign() {
        return this.f1550d;
    }

    public String getID() {
        return this.f1549c;
    }

    public int getMaxArmorHitPoints() {
        return this.f1552f;
    }

    public int getMaxShieldHitPoints() {
        return this.h;
    }

    public int getMovementLeft() {
        return this.n;
    }

    public String getName() {
        return this.b;
    }

    public int getProductionCost() {
        return this.productionCost;
    }

    public List<Integer> getRoute() {
        return this.route;
    }

    public Shield getShield() {
        return this.j;
    }

    public int getShieldHitPoints() {
        return this.g;
    }

    public int getShieldRegenPoints() {
        return this.shieldRegenPoints;
    }

    public ShipComponent getShipComponent(int i) {
        return this.m.get(i);
    }

    public List<ShipComponent> getShipComponents() {
        return this.m;
    }

    public List<Weapon> getShipToShipWeapons() {
        ArrayList arrayList = new ArrayList();
        for (ShipComponent shipComponent : this.m) {
            if (shipComponent instanceof Weapon) {
                Weapon weapon = (Weapon) shipComponent;
                if (weapon.isShipToShipWeapon()) {
                    arrayList.add(weapon);
                }
            }
        }
        return arrayList;
    }

    public ShipType getShipType() {
        return this.f1548a;
    }

    public Set<ShipStatus> getStatuses() {
        return this.statuses;
    }

    public SublightEngine getSublightEngine() {
        return this.l;
    }

    public int getTargetAccuracyBonus() {
        int targetingBonus = getTargetingComputer().getTargetingBonus();
        return GameProperties.isNonNormalEmpire(this.o) ? targetingBonus : (int) (targetingBonus + GameData.empires.get(this.o).getAttributeTypeBonus(RaceAttributeType.BEAM_ACCURACY));
    }

    public TargetingComputer getTargetingComputer() {
        return this.k;
    }

    public int getWeaponDamage(Weapon weapon, int i) {
        return weapon.getDamage() + this.k.getDamageBonus();
    }

    public boolean hasBeamWeaponsLeft() {
        for (ShipComponent shipComponent : getShipComponents()) {
            if (shipComponent instanceof Weapon) {
                Weapon weapon = (Weapon) shipComponent;
                if (weapon.getType() == WeaponType.BEAM && weapon.getAvailableCount() != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasBeenDestroyed() {
        return this.hasBeenDestroyed;
    }

    public boolean hasBeenUsed() {
        return this.beenUsed;
    }

    public boolean hasFullyRetreated() {
        return this.hasFullyRetreated;
    }

    public boolean hasRetreated() {
        return this.statuses.contains(ShipStatus.RETREAT);
    }

    public boolean hasWeaponsLeft() {
        for (ShipComponent shipComponent : getShipComponents()) {
            if (shipComponent instanceof Weapon) {
                Weapon weapon = (Weapon) shipComponent;
                if (weapon.getType() != WeaponType.BOMB && weapon.getAvailableCount() != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAlive() {
        return this.f1551e > 0;
    }

    public boolean isAutoOn() {
        return this.isAuto;
    }

    public boolean isCombatShip() {
        return this.f1548a.isCombatShip();
    }

    public void movementUsed(int i) {
        this.n -= i;
    }

    public void removeShipComponent(ShipComponentID shipComponentID) {
        for (ShipComponent shipComponent : this.m) {
            if (shipComponent.getID() == shipComponentID) {
                this.m.remove(shipComponent);
                return;
            }
        }
    }

    public void removeStatus(ShipStatus shipStatus) {
        this.statuses.remove(shipStatus);
    }

    public void reset() {
        this.n = this.l.getCombatSpeed();
        for (ShipComponent shipComponent : this.m) {
            if (shipComponent instanceof Weapon) {
                ((Weapon) shipComponent).reset();
            }
        }
        this.shieldRegenPoints = 0;
        if (this.g != this.h) {
            int shieldRechargeHP = getShieldRechargeHP();
            this.shieldRegenPoints = shieldRechargeHP;
            int i = this.g + shieldRechargeHP;
            this.g = i;
            int i2 = this.h;
            if (i > i2) {
                this.shieldRegenPoints = shieldRechargeHP - (i - i2);
                this.g = i2;
            }
        }
    }

    public void retreatedFromBattle() {
        this.hasFullyRetreated = true;
    }

    public void setAiTask(SystemOrbit systemOrbit) {
        this.aiTask = systemOrbit;
    }

    public void setAuto(boolean z) {
        this.isAuto = z;
    }

    public void setBattleLocation(Point point) {
        this.battleLocation = point;
    }

    public void setDestroyed() {
        this.hasBeenDestroyed = true;
    }

    public void setID(String str) {
        this.f1549c = str;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void setRoute(List<Integer> list) {
        this.route = list;
    }

    public void setUsed() {
        this.beenUsed = true;
    }

    public Point takeDamage(int i) {
        int absorption = i - this.j.getAbsorption();
        int i2 = 0;
        if (absorption < 0) {
            absorption = 0;
        }
        int i3 = this.g;
        if (i3 <= 0) {
            this.f1551e -= absorption;
        } else if (absorption > i3) {
            this.g = 0;
            this.f1551e -= absorption + 0;
            i2 = i3;
            absorption -= i3;
        } else {
            this.g = i3 - absorption;
            i2 = absorption;
            absorption = 0;
        }
        return new Point(i2, absorption);
    }

    @Override // java.lang.Comparable
    public int compareTo(Ship ship) {
        if (ship == null) {
            return 1;
        }
        try {
            return getInitiative() > ship.getInitiative() ? 1 : -1;
        } catch (Exception unused) {
            Log.message("BattleGrid", "Error comparing ship");
            return 0;
        }
    }

    public Ship(Builder builder) {
        this.m = new ArrayList();
        this.statuses = new HashSet();
        this.hasBeenDestroyed = false;
        this.hasFullyRetreated = false;
        this.f1548a = builder.f1553a;
        this.b = builder.b;
        this.f1549c = builder.f1554c;
        this.f1550d = builder.f1555d;
        this.f1551e = builder.f1556e;
        this.f1552f = builder.f1557f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
        this.m = builder.m;
        this.n = builder.n;
        this.designNumber = builder.o;
        this.productionCost = builder.p;
        this.beenUsed = builder.q;
        this.initiative = builder.r;
        this.o = builder.s;
        this.aiTask = builder.t;
        this.isAuto = builder.u;
    }
}
