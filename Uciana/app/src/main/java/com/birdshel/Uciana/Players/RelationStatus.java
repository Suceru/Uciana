package com.birdshel.Uciana.Players;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum RelationStatus {
    HATE(R.string.relation_status_hate, 0, 10, 0),
    DISLIKE(R.string.relation_status_dislike, 11, 20, 0),
    DISTRUST(R.string.relation_status_distrust, 21, 30, 0),
    UNFRIENDLY(R.string.relation_status_unfriendly, 31, 40, 1),
    NEUTRAL(R.string.relation_status_neutral, 41, 60, 1),
    FRIENDLY(R.string.relation_status_friendly, 61, 70, 1),
    TRUSTING(R.string.relation_status_trusting, 71, 80, 2),
    LIKE(R.string.relation_status_like, 81, 90, 2),
    LOVE(R.string.relation_status_love, 91, 100, 2);
    
    private final int max;
    private final int messageIndex;
    private final int min;
    private final int name;

    RelationStatus(int i, int i2, int i3, int i4) {
        this.name = i;
        this.min = i2;
        this.max = i3;
        this.messageIndex = i4;
    }

    public static RelationStatus getRelationStatus(int i) {
        RelationStatus[] values;
        for (RelationStatus relationStatus : values()) {
            if (i >= relationStatus.min && i <= relationStatus.max) {
                return relationStatus;
            }
        }
        throw new AssertionError("Error in the RelationStatus");
    }

    public int getMessageIndex() {
        return this.messageIndex;
    }

    public String getName() {
        return GameData.activity.getString(this.name);
    }
}
