package com.birdshel.Uciana.Players;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TradeItem {
    private int amount = 0;
    private int empire1ID;
    private int empire2ID;
    private final String id;
    private final String name;
    private final boolean requireBoth;
    private final TradeType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TradeItem(TradeType tradeType, String str, String str2, boolean z) {
        this.type = tradeType;
        this.id = str;
        this.name = str2;
        this.requireBoth = z;
    }

    public boolean doesRequireBoth() {
        return this.requireBoth;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getEmpire1ID() {
        return this.empire1ID;
    }

    public int getEmpire2ID() {
        return this.empire2ID;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public TradeType getType() {
        return this.type;
    }

    public void setAmount(int i) {
        this.amount = i;
    }

    public void setEmpire1ID(int i) {
        this.empire1ID = i;
    }

    public void setEmpire2ID(int i) {
        this.empire2ID = i;
    }
}
