package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Resources.InfoIconEnum;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum ShipStatus {
    NONE(-1),
    RETREAT(InfoIconEnum.LEFT_ARROW.ordinal()),
    FTL_DISABLED(InfoIconEnum.FTL_DISABLED.ordinal()),
    SUBLIGHT_DISABLED(InfoIconEnum.SUBLIGHT_DISABLED.ordinal());
    
    private final int imageIndex;

    ShipStatus(int i) {
        this.imageIndex = i;
    }

    public int getImageIndex() {
        return this.imageIndex;
    }
}
