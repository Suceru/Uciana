package com.birdshel.Uciana.Ships.ShipComponents;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Technology.TechID;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Weapon extends ShipComponent {
    private final int blastSize;
    private final int maxDamage;
    private final int minDamage;
    private final int shockwaveIndex;
    private final int soundEffectIndex;
    private final int speed;
    private final WeaponType type;
    private final Color weaponColor;
    private int weaponUsedCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Ships.ShipComponents.Weapon$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1564a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ShipComponentID.values().length];
            b = iArr;
            try {
                iArr[ShipComponentID.BIO_BOMB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[WeaponType.values().length];
            f1564a = iArr2;
            try {
                iArr2[WeaponType.BEAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1564a[WeaponType.TORPEDO.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1564a[WeaponType.SPACIAL_CHARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends ShipComponent.Builder {
        WeaponType j;
        int k;
        int l;
        Color m = Color.BLACK;
        int n;
        int o;
        int p;
        int q;

        public Builder blastSize(int i) {
            this.o = i;
            return this;
        }

        public Builder maxDamage(int i) {
            this.l = i;
            return this;
        }

        public Builder minDamage(int i) {
            this.k = i;
            return this;
        }

        public Builder shockwaveIndex(int i) {
            this.q = i;
            return this;
        }

        public Builder soundEffectIndex(int i) {
            this.n = i;
            return this;
        }

        public Builder speed(int i) {
            this.p = i;
            return this;
        }

        public Builder type(WeaponType weaponType) {
            this.j = weaponType;
            return this;
        }

        public Builder weaponColor(Color color) {
            this.m = color;
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Weapon build() {
            return new Weapon(this);
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder count(int i) {
            super.count(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder description(int i) {
            super.description(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder iconIndex(int i) {
            super.iconIndex(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder id(ShipComponentID shipComponentID) {
            super.id(shipComponentID);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder name(int i) {
            super.name(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder productionCost(int i) {
            super.productionCost(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder requiredTechID(TechID techID) {
            super.requiredTechID(techID);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder shortName(int i) {
            super.shortName(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder spaceRequired(int i) {
            super.spaceRequired(i);
            return this;
        }
    }

    public Weapon(Builder builder) {
        super(builder);
        this.weaponUsedCount = 0;
        this.type = builder.j;
        this.minDamage = builder.k;
        this.maxDamage = builder.l;
        this.weaponColor = builder.m;
        this.soundEffectIndex = builder.n;
        this.blastSize = builder.o;
        this.speed = builder.p;
        this.shockwaveIndex = builder.q;
    }

    private int bonusMaxDamage(int i) {
        return (!GameProperties.isNonNormalEmpire(i) && AnonymousClass1.b[getID().ordinal()] == 1 && GameData.empires.get(i).getResources().containsKey(ResourceID.BIO_TOXIN)) ? 25 : 0;
    }

    public int getAvailableCount() {
        return this.f1558a - this.weaponUsedCount;
    }

    public int getBlastSize() {
        return this.blastSize;
    }

    public int getDamage() {
        this.weaponUsedCount++;
        return Functions.random.nextInt((this.maxDamage - this.minDamage) + 1) + this.minDamage;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }

    public int getMinDamage() {
        return this.minDamage;
    }

    public int getShockwaveIndex() {
        return this.shockwaveIndex;
    }

    public int getSoundEffectIndex() {
        return this.soundEffectIndex;
    }

    public int getSpeed() {
        return this.speed;
    }

    public WeaponType getType() {
        return this.type;
    }

    public Color getWeaponColor() {
        return this.weaponColor;
    }

    public boolean hasUnfiredWeapons() {
        return getAvailableCount() != 0;
    }

    public boolean isShipToShipWeapon() {
        int i = AnonymousClass1.f1564a[this.type.ordinal()];
        return i == 1 || i == 2 || i == 3;
    }

    public void reset() {
        this.weaponUsedCount = 0;
    }

    public void setAllWeaponsUsed() {
        this.weaponUsedCount = this.f1558a;
    }

    public int getDamage(int i) {
        this.weaponUsedCount++;
        return Functions.random.nextInt(((this.maxDamage + bonusMaxDamage(i)) - this.minDamage) + 1) + this.minDamage;
    }

    public Weapon(Weapon weapon) {
        super(weapon);
        this.weaponUsedCount = 0;
        this.type = weapon.type;
        this.minDamage = weapon.minDamage;
        this.maxDamage = weapon.maxDamage;
        this.weaponColor = weapon.weaponColor;
        this.soundEffectIndex = weapon.soundEffectIndex;
        this.blastSize = weapon.blastSize;
        this.speed = weapon.speed;
        this.shockwaveIndex = weapon.shockwaveIndex;
    }
}
