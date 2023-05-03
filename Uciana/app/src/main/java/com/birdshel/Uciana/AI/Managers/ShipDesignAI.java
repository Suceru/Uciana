package com.birdshel.Uciana.AI.Managers;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Ship;
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
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipDesignAI {
    private Armor armor;
    private int availableSlots;
    private final Empire empire;
    private Shield shield;
    private List<ShipComponent> shipComponents;
    private ShipType shipType;
    private SublightEngine sublightEngine;
    private TargetingComputer targetingComputer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.Managers.ShipDesignAI$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1346a;

        static {
            int[] iArr = new int[ShipType.values().length];
            f1346a = iArr;
            try {
                iArr[ShipType.STAR_BASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1346a[ShipType.TORPEDO_TURRET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ShipDesignAI(Empire empire) {
        this.empire = empire;
    }

    private void addInWeapons(List<Weapon> list) {
        boolean z;
        boolean z2;
        boolean z3;
        int availableSpace = getAvailableSpace();
        Weapon bestWeaponOfType = getBestWeaponOfType(WeaponType.BEAM, list);
        boolean z4 = false;
        if (this.shipType == ShipType.TORPEDO_TURRET || this.availableSlots == 0 || bestWeaponOfType == null) {
            z = false;
        } else {
            availableSpace -= this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType);
            this.availableSlots--;
            z = true;
        }
        Weapon bestWeaponOfType2 = getBestWeaponOfType(WeaponType.BOMB, list);
        if (this.shipType.isStation() || this.availableSlots == 0 || bestWeaponOfType2 == null) {
            z2 = false;
        } else {
            availableSpace -= this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType2);
            this.availableSlots--;
            z2 = true;
        }
        Weapon bestWeaponOfType3 = getBestWeaponOfType(WeaponType.TORPEDO, list);
        if (this.availableSlots == 0 || bestWeaponOfType3 == null) {
            z3 = false;
        } else {
            availableSpace -= this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType3);
            this.availableSlots--;
            z3 = true;
        }
        Weapon bestWeaponOfType4 = getBestWeaponOfType(WeaponType.SPACIAL_CHARGE, list);
        if (this.availableSlots != 0 && bestWeaponOfType4 != null) {
            availableSpace -= this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType4);
            this.availableSlots--;
            z4 = true;
        }
        while (true) {
            if (z) {
                if (availableSpace - this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType) < 0) {
                    break;
                }
                bestWeaponOfType.setComponentCount(bestWeaponOfType.getComponentCount() + 1);
                availableSpace -= this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType);
            }
            if (z2) {
                if (availableSpace - this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType2) < 0) {
                    break;
                }
                bestWeaponOfType2.setComponentCount(bestWeaponOfType2.getComponentCount() + 1);
                availableSpace -= this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType2);
            }
            if (z3) {
                if (availableSpace - this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType3) < 0) {
                    break;
                }
                bestWeaponOfType3.setComponentCount(bestWeaponOfType3.getComponentCount() + 1);
                availableSpace -= this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType3);
            }
            if (z4) {
                if (availableSpace - this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType4) < 0) {
                    break;
                }
                bestWeaponOfType4.setComponentCount(bestWeaponOfType4.getComponentCount() + 1);
                availableSpace -= this.empire.getComponentSpaceAfterMiniaturization(bestWeaponOfType4);
            }
        }
        if (z) {
            this.shipComponents.add(bestWeaponOfType);
        }
        if (z2) {
            this.shipComponents.add(bestWeaponOfType2);
        }
        if (z3) {
            this.shipComponents.add(bestWeaponOfType3);
        }
        if (z4) {
            this.shipComponents.add(bestWeaponOfType4);
        }
    }

    private int getAvailableSpace() {
        int baseAvailableSpace = (((this.shipType.getBaseAvailableSpace() - this.empire.getComponentSpaceAfterMiniaturization(this.armor)) - this.empire.getComponentSpaceAfterMiniaturization(this.shield)) - this.empire.getComponentSpaceAfterMiniaturization(this.targetingComputer)) - this.empire.getComponentSpaceAfterMiniaturization(this.sublightEngine);
        for (ShipComponent shipComponent : this.shipComponents) {
            baseAvailableSpace -= this.empire.getComponentSpaceAfterMiniaturization(shipComponent) * shipComponent.getComponentCount();
            if (shipComponent.getID() == ShipComponentID.EXTENDED_HULL) {
                baseAvailableSpace = (int) (baseAvailableSpace + (this.shipType.getBaseAvailableSpace() * ((SpecialComponent) shipComponent).getEffectValue()));
            }
        }
        return baseAvailableSpace;
    }

    private Weapon getBestWeaponOfType(WeaponType weaponType, List<Weapon> list) {
        Weapon weapon = null;
        for (Weapon weapon2 : list) {
            if (weapon2.getType() == weaponType) {
                if (weapon == null) {
                    weapon = weapon2;
                }
                if (weapon.getMaxDamage() < weapon2.getMaxDamage()) {
                    weapon = weapon2;
                }
            }
        }
        if (weapon == null) {
            return null;
        }
        return (Weapon) ShipComponents.get(weapon.getID());
    }

    private int getProductionCost(ShipType shipType) {
        int baseProductionCost = shipType.getBaseProductionCost() + this.empire.getComponentCostAfterMiniaturization(this.armor) + this.empire.getComponentCostAfterMiniaturization(this.shield) + this.empire.getComponentCostAfterMiniaturization(this.targetingComputer) + this.empire.getComponentCostAfterMiniaturization(this.sublightEngine);
        float f2 = 1.0f;
        for (ShipComponent shipComponent : this.shipComponents) {
            baseProductionCost += this.empire.getComponentCostAfterMiniaturization(shipComponent) * shipComponent.getComponentCount();
            if (shipComponent.getID() == ShipComponentID.EXTENDED_HULL) {
                f2 = 2.0f;
            }
        }
        return (int) (baseProductionCost * f2);
    }

    public Armor getBestArmor() {
        List<Armor> availableArmor = this.empire.getAvailableArmor();
        Armor armor = availableArmor.get(0);
        for (Armor armor2 : availableArmor) {
            if (armor2.getArmorMultiplier() > armor.getArmorMultiplier()) {
                armor = armor2;
            }
        }
        return (Armor) ShipComponents.get(armor.getID());
    }

    public Shield getBestShield() {
        List<Shield> availableShields = this.empire.getAvailableShields();
        Shield shield = availableShields.get(0);
        for (Shield shield2 : availableShields) {
            if (shield2.getStrengthMultiplier() > shield.getStrengthMultiplier()) {
                shield = shield2;
            }
        }
        return (Shield) ShipComponents.get(shield.getID());
    }

    public SublightEngine getBestSublightEngine() {
        List<SublightEngine> availableSublightEngines = this.empire.getAvailableSublightEngines();
        SublightEngine sublightEngine = availableSublightEngines.get(0);
        for (SublightEngine sublightEngine2 : availableSublightEngines) {
            if (sublightEngine2.getCombatSpeed() > sublightEngine.getCombatSpeed()) {
                sublightEngine = sublightEngine2;
            }
        }
        return (SublightEngine) ShipComponents.get(sublightEngine.getID());
    }

    public TargetingComputer getBestTargetingComputer() {
        List<TargetingComputer> availableTargetingComputers = this.empire.getAvailableTargetingComputers();
        TargetingComputer targetingComputer = availableTargetingComputers.get(0);
        for (TargetingComputer targetingComputer2 : availableTargetingComputers) {
            if (targetingComputer2.getTargetingBonus() > targetingComputer.getTargetingBonus()) {
                targetingComputer = targetingComputer2;
            }
        }
        return (TargetingComputer) ShipComponents.get(targetingComputer.getID());
    }

    public Ship getShipDesign(ShipType shipType) {
        int i = AnonymousClass1.f1346a[shipType.ordinal()];
        if (i != 1 && i != 2) {
            return getShipDesign(shipType, Functions.random.nextInt(5));
        }
        return getShipDesign(shipType, 0);
    }

    public Ship getShipDesign(ShipType shipType, int i) {
        this.shipType = shipType;
        this.availableSlots = shipType.getNumberOfComponents();
        this.shipComponents = new ArrayList();
        List<Weapon> availableWeapons = this.empire.getAvailableWeapons();
        this.empire.getAvailableSpecialComponents();
        this.armor = getBestArmor();
        this.shield = getBestShield();
        this.targetingComputer = getBestTargetingComputer();
        if (shipType.isStation()) {
            this.sublightEngine = (SublightEngine) ShipComponents.get(ShipComponentID.NO_SUBLIGHT_ENGINES);
        } else {
            this.sublightEngine = getBestSublightEngine();
        }
        if (shipType != ShipType.TORPEDO_TURRET && this.empire.hasTech(TechID.EXTENDED_HULL)) {
            this.shipComponents.add(ShipComponents.get(ShipComponentID.EXTENDED_HULL));
            this.availableSlots--;
        }
        addInWeapons(availableWeapons);
        return new Ship.Builder().id("Design-" + UUID.randomUUID().toString()).shipType(shipType).name(shipType.getString(this.empire.id)).empireID(this.empire.id).productionCost(getProductionCost(shipType)).armor(this.armor).shield(this.shield).targetingComputer(this.targetingComputer).sublightEngine(this.sublightEngine).shipComponents(this.shipComponents).hullDesign(i).buildCombatShip();
    }
}
