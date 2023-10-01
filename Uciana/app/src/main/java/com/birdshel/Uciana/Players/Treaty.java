package com.birdshel.Uciana.Players;

import android.util.SparseArray;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum Treaty {
    PEACE_TREATY(1, false, false, GameIconEnum.PEACE.ordinal(), R.string.treaty_peace, true),
    NON_AGGRESSION_PACT(2, false, true, GameIconEnum.NON_AGGRESSION_TREATY.ordinal(), R.string.treaty_non_aggression_pact, true),
    ALLIANCE(4, false, true, GameIconEnum.ALLIANCE.ordinal(), R.string.treaty_alliance, true),
    TRADE(8, true, true, GameIconEnum.TRADE_TREATY.ordinal(), R.string.treaty_trade, true),
    RESEARCH(16, true, true, GameIconEnum.RESEARCH_TREATY.ordinal(), R.string.treaty_research, true),
    TRIBUTE(32, false, true, GameIconEnum.TRIBUTE_TREATY.ordinal(), R.string.treaty_tribute, false),
    DECLARATION_OF_WAR(64, false, false, GameIconEnum.WAR.ordinal(), R.string.treaty_war, true);
    
    private static final SparseArray<Treaty> lookup = new SparseArray<>();
    private final int iconIndex;
    private final int id;
    private final boolean keepStartDate;
    private final int name;
    private final boolean requireBoth;
    private final boolean showOnRaceScene;

    static {
        Treaty[] values;
        for (Treaty treaty : values()) {
            lookup.put(treaty.getID(), treaty);
        }
    }

    Treaty(int i, boolean z, boolean z2, int i2, int i3, boolean z3) {
        this.id = i;
        this.keepStartDate = z;
        this.showOnRaceScene = z2;
        this.iconIndex = i2;
        this.name = i3;
        this.requireBoth = z3;
    }

    public static Treaty getTreaty(String str) {
        return lookup.get(Integer.parseInt(str));
    }

    public int getID() {
        return this.id;
    }

    public int getIconIndex() {
        return this.iconIndex;
    }

    public String getName() {
        return GameData.activity.getString(this.name);
    }

    public boolean keepStartDate() {
        return this.keepStartDate;
    }

    public boolean requiresBoth() {
        return this.requireBoth;
    }

    public boolean showOnRaceScene() {
        return this.showOnRaceScene;
    }

    public static Treaty getTreaty(int i) {
        return lookup.get(i);
    }
}
