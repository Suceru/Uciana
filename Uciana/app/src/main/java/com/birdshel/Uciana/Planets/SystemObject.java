package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.GameData;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SystemObject {
    public static final int EXPLORED_BY_ALL = 254;
    public static final int EXPLORED_BY_NONE = 0;

    /* renamed from: a  reason: collision with root package name */
    protected int f1396a;
    protected int b;

    /* renamed from: c  reason: collision with root package name */
    protected List<ResourceID> f1397c;

    /* renamed from: d  reason: collision with root package name */
    protected int f1398d;

    /* renamed from: e  reason: collision with root package name */
    int f1399e;
    private final SystemObjectType systemObjectType;

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        protected String f1400a;
        SystemObjectType b;

        /* renamed from: c  reason: collision with root package name */
        protected int f1401c;

        /* renamed from: d  reason: collision with root package name */
        protected int f1402d;

        /* renamed from: e  reason: collision with root package name */
        protected List<ResourceID> f1403e = new ArrayList();

        /* renamed from: f  reason: collision with root package name */
        protected int f1404f = 0;
        int g;

        public SystemObject build() {
            return new SystemObject(this);
        }

        public Builder exploredValue(int i) {
            this.g = i;
            return this;
        }

        public Builder imageIndex(int i) {
            this.f1404f = i;
            return this;
        }

        public Builder name(String str) {
            this.f1400a = str;
            return this;
        }

        public Builder orbit(int i) {
            this.f1402d = i;
            return this;
        }

        public Builder resources(List<ResourceID> list) {
            this.f1403e = list;
            return this;
        }

        public Builder systemID(int i) {
            this.f1401c = i;
            return this;
        }
    }

    public SystemObject(Builder builder) {
        String str = builder.f1400a;
        this.systemObjectType = builder.b;
        this.f1396a = builder.f1401c;
        this.b = builder.f1402d;
        this.f1397c = builder.f1403e;
        this.f1398d = builder.f1404f;
        this.f1399e = builder.g;
    }

    public void beenExplored(int i) {
        this.f1399e += (int) Math.pow(2.0d, i + 1);
    }

    public Colony getColony() {
        return GameData.colonies.getColony(this.f1396a, this.b);
    }

    public int getExploredValue() {
        return this.f1399e;
    }

    public int getImageIndex() {
        return this.f1398d;
    }

    public String getName() {
        return GameData.galaxy.getStarSystem(this.f1396a).getName() + " " + (this.b + 1);
    }

    public int getOccupier() {
        if (hasColony()) {
            return getColony().getEmpireID();
        }
        if (hasOutpost()) {
            return getOutpost().getEmpireID();
        }
        throw new AssertionError("No Occupier at system " + this.f1396a + " in orbit " + this.b);
    }

    public int getOrbit() {
        return this.b;
    }

    public Outpost getOutpost() {
        return GameData.outposts.getOutpost(this.f1396a, this.b);
    }

    public List<ResourceID> getResources() {
        return this.f1397c;
    }

    public int getSystemID() {
        return this.f1396a;
    }

    public SystemObjectType getSystemObjectType() {
        return this.systemObjectType;
    }

    public List<ResourceID> getVisibleResources(int i) {
        ArrayList arrayList = new ArrayList();
        for (ResourceID resourceID : this.f1397c) {
            if (Resources.get(resourceID).isVisible(i)) {
                arrayList.add(resourceID);
            }
        }
        return arrayList;
    }

    public boolean hasBeenExploredByEmpire(int i) {
        int pow = (int) Math.pow(2.0d, i + 1);
        return (this.f1399e & pow) == pow;
    }

    public boolean hasColony() {
        return GameData.colonies.isColony(this.f1396a, this.b);
    }

    public boolean hasOutpost() {
        return GameData.outposts.isOutpost(this.f1396a, this.b);
    }

    public boolean isAsteroidBelt() {
        return this.systemObjectType == SystemObjectType.ASTEROID_BELT;
    }

    public boolean isGasGiant() {
        return this.systemObjectType == SystemObjectType.GAS_GIANT;
    }

    public boolean isNothing() {
        return this.systemObjectType == SystemObjectType.NONE;
    }

    public boolean isOccupied() {
        return hasColony() || hasOutpost();
    }

    public boolean isPlanet() {
        return this.systemObjectType == SystemObjectType.PLANET;
    }

    public boolean isUnexplored() {
        return this.f1399e == 0;
    }

    public List<ResourceID> getResources(int i, ResourceType resourceType) {
        ArrayList arrayList = new ArrayList();
        for (ResourceID resourceID : this.f1397c) {
            Resource resource = Resources.get(resourceID);
            if (resource.containsEffect(resourceType) && resource.isVisible(i)) {
                arrayList.add(resourceID);
            }
        }
        return arrayList;
    }
}
