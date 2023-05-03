package com.birdshel.Uciana.AI.AIObjects;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum FleetSize {
    TINY,
    SMALL,
    MEDIUM,
    LARGE,
    HUGE,
    MAXED;

    public static FleetSize getFleetSize(int i, int i2) {
        if (i <= 0) {
            return MAXED;
        }
        if (i <= 4) {
            return HUGE;
        }
        int i3 = i / i2;
        if (i3 < 0 || i3 >= 0.2f) {
            float f2 = i3;
            if (f2 < 0.2f || f2 >= 0.4f) {
                if (f2 < 0.4f || f2 >= 0.6f) {
                    if (f2 < 0.6f || f2 >= 0.8f) {
                        if (f2 >= 0.8f && f2 < 0.95f) {
                            return HUGE;
                        }
                        return MAXED;
                    }
                    return LARGE;
                }
                return MEDIUM;
            }
            return SMALL;
        }
        return TINY;
    }
}
