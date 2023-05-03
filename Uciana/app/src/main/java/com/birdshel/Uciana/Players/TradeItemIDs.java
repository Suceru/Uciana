package com.birdshel.Uciana.Players;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum TradeItemIDs {
    PEACE_TREATY(Integer.toString(Treaty.PEACE_TREATY.getID())),
    ALLIANCE(Integer.toString(Treaty.ALLIANCE.getID())),
    SHARE_MAPS("share_maps"),
    TECH("tech"),
    SYSTEM("system"),
    NON_AGGRESSION_PACT(Integer.toString(Treaty.NON_AGGRESSION_PACT.getID())),
    TRADE(Integer.toString(Treaty.TRADE.getID())),
    RESEARCH(Integer.toString(Treaty.RESEARCH.getID())),
    TRIBUTE(Integer.toString(Treaty.TRIBUTE.getID())),
    CREDITS("credits");
    
    private final String id;

    TradeItemIDs(String str) {
        this.id = str;
    }

    public String getID() {
        return this.id;
    }
}
