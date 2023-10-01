package com.birdshel.Uciana.AI.Managers;

import com.birdshel.Uciana.Difficulty;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Messages.DiplomaticType;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.RelationStatus;
import com.birdshel.Uciana.Players.TradeItem;
import com.birdshel.Uciana.Players.TradeItemIDs;
import com.birdshel.Uciana.Players.TradeType;
import com.birdshel.Uciana.Players.Treaties;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.Technology.TechID;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class DiplomaticAI {
    private final Empire empire;
    private int[] turnsToAskAgain = new int[7];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.Managers.DiplomaticAI$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1342a;
        static final /* synthetic */ int[] b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f1343c;

        static {
            int[] iArr = new int[RelationStatus.values().length];
            f1343c = iArr;
            try {
                iArr[RelationStatus.HATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1343c[RelationStatus.DISLIKE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1343c[RelationStatus.DISTRUST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1343c[RelationStatus.TRUSTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1343c[RelationStatus.LIKE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1343c[RelationStatus.LOVE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[TradeType.values().length];
            b = iArr2;
            try {
                iArr2[TradeType.TREATY.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[TradeType.MAPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[TradeType.TECH.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[TradeType.SYSTEM.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[TradeType.CREDITS.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[TradeType.CREDITS_PER_TURN.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr3 = new int[Treaty.values().length];
            f1342a = iArr3;
            try {
                iArr3[Treaty.ALLIANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1342a[Treaty.NON_AGGRESSION_PACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    public DiplomaticAI(Empire empire) {
        this.empire = empire;
        for (int i = 0; i < 7; i++) {
            this.turnsToAskAgain[i] = 0;
        }
    }

    private void checkForPeace(Empire empire) {
        if (this.turnsToAskAgain[empire.id] > 0) {
            return;
        }
        int startDate = this.empire.getTreaties().getStartDate(empire.id, Treaty.DECLARATION_OF_WAR);
        if (GameData.turn - startDate > this.empire.getPersonality().getMinTurnsAskForPeace() && Functions.percent(50)) {
            if (empire.isHuman()) {
                GameData.events.addAIProposeTreatyEvent(empire.id, this.empire.id, Treaty.PEACE_TREATY.ordinal());
            } else {
                int i = GameData.turn - startDate;
                if (i > empire.getPersonality().getMinTurnsAskForPeace() && Functions.percent(i)) {
                    empire.getTreaties().makePeace(this.empire.id);
                    this.empire.getTreaties().makePeace(empire.id);
                }
            }
        }
        this.turnsToAskAgain[empire.id] = Functions.random.nextInt(5) + 10;
    }

    private int checkRelationShipOfContactWithOthers(Empire empire) {
        int i = 0;
        for (Empire empire2 : GameData.empires.getEmpires()) {
            if (empire.getTreaties().hasTreaty(empire2.id, Treaty.DECLARATION_OF_WAR)) {
                switch (AnonymousClass3.f1343c[RelationStatus.getRelationStatus(this.empire.getRelationValue(empire2.id)).ordinal()]) {
                    case 1:
                        i += 3;
                        break;
                    case 2:
                        i += 2;
                        break;
                    case 3:
                        i++;
                        break;
                    case 4:
                        i--;
                        break;
                    case 5:
                        i -= 2;
                        break;
                    case 6:
                        i -= 3;
                        break;
                }
            }
            if (empire.getTreaties().hasTreaty(empire2.id, Treaty.ALLIANCE)) {
                switch (AnonymousClass3.f1343c[RelationStatus.getRelationStatus(this.empire.getRelationValue(empire2.id)).ordinal()]) {
                    case 1:
                        i -= 2;
                        continue;
                    case 2:
                    case 3:
                        i--;
                        continue;
                    case 4:
                    case 5:
                        i++;
                        continue;
                    case 6:
                        i += 2;
                        continue;
                }
            }
        }
        return i;
    }

    private void checkToSeeIfProposeOrBreakTreaty(Treaty treaty, Empire empire, int i, int i2) {
        if (treaty != Treaty.PEACE_TREATY && this.empire.getTreaties().hasTreaty(empire.id, treaty)) {
            if (this.empire.getRelationValue(empire.id) > i2 || !Functions.percent(50)) {
                return;
            }
            if (empire.isHuman()) {
                GameData.events.addDiplomaticBreakTreatyEvent(DiplomaticType.BREAK_TREATY.ordinal(), empire.id, this.empire.id, treaty);
            }
            this.empire.getTreaties().removeTreaty(empire.id, treaty);
            if (treaty.requiresBoth()) {
                empire.getTreaties().removeTreaty(this.empire.id, treaty);
                return;
            }
            return;
        }
        int[] iArr = this.turnsToAskAgain;
        int i3 = empire.id;
        if (iArr[i3] <= 0 && this.empire.getRelationValue(i3) >= i && Functions.percent(50)) {
            if (empire.isHuman()) {
                GameData.events.addAIProposeTreatyEvent(empire.id, this.empire.id, treaty.ordinal());
            } else if (empire.getRelationValue(this.empire.id) >= i && Functions.percent(50)) {
                empire.getTreaties().addTreaty(this.empire.id, treaty);
                this.empire.getTreaties().addTreaty(empire.id, treaty);
            }
            this.turnsToAskAgain[empire.id] = Functions.random.nextInt(5) + 10;
        }
    }

    public void checkForRelationChanges() {
        if (GameData.gameSettings.isAlwaysAtWar()) {
            return;
        }
        for (Empire empire : GameData.empires.getEmpires()) {
            int i = empire.id;
            Empire empire2 = this.empire;
            if (i != empire2.id && empire2.isEmpireKnown(i)) {
                if (this.empire.isAtWar(empire.id)) {
                    checkForPeace(empire);
                } else if (this.empire.getRelationValue(empire.id) < this.empire.getPersonality().getMinGoToWar() && GameData.turn - this.empire.getTreaties().getStartDate(empire.id, Treaty.PEACE_TREATY) > this.empire.getPersonality().getMinTurnsAfterPeaceToGoToWar() && Functions.percent(50)) {
                    this.empire.declareWar(empire.id);
                    return;
                } else {
                    checkToSeeIfProposeOrBreakTreaty(Treaty.TRADE, empire, 60, 40);
                    checkToSeeIfProposeOrBreakTreaty(Treaty.RESEARCH, empire, 70, 50);
                    Treaties treaties = this.empire.getTreaties();
                    int i2 = empire.id;
                    Treaty treaty = Treaty.ALLIANCE;
                    if (!treaties.hasTreaty(i2, treaty)) {
                        checkToSeeIfProposeOrBreakTreaty(Treaty.NON_AGGRESSION_PACT, empire, 85, 55);
                    }
                    if (this.empire.getTreaties().hasTreaty(empire.id, Treaty.NON_AGGRESSION_PACT)) {
                        checkToSeeIfProposeOrBreakTreaty(treaty, empire, 100, 70);
                    }
                }
                int[] iArr = this.turnsToAskAgain;
                int i3 = empire.id;
                if (iArr[i3] > 0) {
                    iArr[i3] = iArr[i3] - 1;
                }
            }
        }
    }

    public int[] getTurnsToAsk() {
        return this.turnsToAskAgain;
    }

    public boolean isDealAcceptable(List<TradeItem> list) {
        TradeType[] values;
        HashMap<TradeType, Integer> hashMap = new HashMap<TradeType, Integer>(this) { // from class: com.birdshel.Uciana.AI.Managers.DiplomaticAI.1
            {
                for (TradeType tradeType : TradeType.values()) {
                    put(tradeType, 0);
                }
            }
        };
        HashMap<TradeType, Integer> hashMap2 = new HashMap<TradeType, Integer>(this) { // from class: com.birdshel.Uciana.AI.Managers.DiplomaticAI.2
            {
                for (TradeType tradeType : TradeType.values()) {
                    put(tradeType, 0);
                }
            }
        };
        Iterator<TradeItem> it = list.iterator();
        int i = -1;
        while (true) {
            int i2 = 1;
            if (it.hasNext()) {
                TradeItem next = it.next();
                if (next.doesRequireBoth()) {
                    if (next.getEmpire1ID() != this.empire.id) {
                        i = next.getEmpire1ID();
                    }
                    if (next.getType() != TradeType.TREATY) {
                        continue;
                    } else {
                        int i3 = AnonymousClass3.f1342a[Treaty.getTreaty(next.getID()).ordinal()];
                        if (i3 != 1) {
                            if (i3 == 2 && this.empire.getRelationValue(i) < 70) {
                                return false;
                            }
                        } else if (this.empire.getRelationValue(i) < 90) {
                            return false;
                        }
                    }
                } else {
                    int i4 = AnonymousClass3.b[next.getType().ordinal()];
                    if (i4 != 2) {
                        if (i4 != 3) {
                            i2 = i4 != 5 ? 0 : next.getAmount();
                        } else {
                            i2 = GameData.empires.get(next.getEmpire2ID()).getTech().getTech(TechID.values()[Integer.parseInt(next.getID().replace(TradeItemIDs.TECH.getID(), ""))]).getResearchCost();
                        }
                    }
                    if (next.getEmpire1ID() == this.empire.id) {
                        hashMap.put(next.getType(), Integer.valueOf(hashMap.get(next.getType()).intValue() + i2));
                    } else {
                        hashMap2.put(next.getType(), Integer.valueOf(hashMap2.get(next.getType()).intValue() + i2));
                        i = next.getEmpire1ID();
                    }
                }
            } else {
                for (TradeType tradeType : TradeType.values()) {
                    Integer num = hashMap.get(tradeType);
                    if (num == null) {
                        num = 0;
                    }
                    Integer num2 = hashMap2.get(tradeType);
                    Integer valueOf = Integer.valueOf(num2 == null ? 0 : num2.intValue());
                    if (!(num.intValue() == 0 && valueOf.intValue() == 0) && num.intValue() > valueOf.intValue()) {
                        return false;
                    }
                }
                if (i == -1) {
                    return false;
                }
                return !Functions.percent(100 - this.empire.getRelationValue(i));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean isGift(List<TradeItem> list) {
        for (TradeItem tradeItem : list) {
            if (tradeItem.getEmpire1ID() == this.empire.id || tradeItem.doesRequireBoth()) {
                return false;
            }
            while (r5.hasNext()) {
            }
        }
        return true;
    }

    public void setTurnsToAsk(int[] iArr) {
        this.turnsToAskAgain = iArr;
    }

    public void updateRelations() {
        for (Empire empire : GameData.empires.getEmpires()) {
            int i = empire.id;
            Empire empire2 = this.empire;
            if (i != empire2.id && empire2.isEmpireKnown(i)) {
                int i2 = 0;
                int disposition = this.empire.getDisposition(empire.id);
                if (disposition == 0) {
                    i2 = -1;
                } else if (disposition == 2) {
                    i2 = 1;
                }
                if (Game.getDifficulty() == Difficulty.HARDER && Functions.percent(50)) {
                    i2--;
                }
                if (Game.getDifficulty() == Difficulty.HARDEST) {
                    i2--;
                }
                this.empire.updateRelationValue(empire.id, i2 + (Functions.random.nextInt(2) - 1) + checkRelationShipOfContactWithOthers(empire));
            }
        }
    }
}
