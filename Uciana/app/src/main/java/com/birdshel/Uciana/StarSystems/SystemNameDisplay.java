package com.birdshel.Uciana.StarSystems;

import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SystemNameDisplay {
    private final String name;
    private final List<Integer> occupiers;
    private final float x;
    private final float y;

    public SystemNameDisplay(float f2, float f3, String str, List<Integer> list) {
        this.x = f2;
        this.y = f3;
        this.name = str;
        this.occupiers = list;
    }

    public String getName() {
        return this.name;
    }

    public List<Integer> getOccupiers() {
        return this.occupiers;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
