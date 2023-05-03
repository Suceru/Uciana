package com.birdshel.Uciana.Players;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Treaties {
    private final int empireID;
    private List<Map<Treaty, Integer>> startDates = new ArrayList();
    private final int[] treaties = new int[7];

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Players.Treaties$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1407a;

        static {
            int[] iArr = new int[Treaty.values().length];
            f1407a = iArr;
            try {
                iArr[Treaty.RESEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1407a[Treaty.TRADE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1407a[Treaty.TRIBUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public Treaties(int i) {
        this.empireID = i;
        for (int i2 = 0; i2 < 7; i2++) {
            int[] iArr = this.treaties;
            Treaty treaty = Treaty.PEACE_TREATY;
            iArr[i2] = treaty.getID();
            this.startDates.add(new HashMap());
            this.startDates.get(i2).put(treaty, 0);
        }
    }

    private int getResearchCost(int i) {
        int intValue;
        Empire empire = GameData.empires.get(this.empireID);
        Treaty treaty = Treaty.RESEARCH;
        if (!hasTreaty(i, treaty) || (intValue = GameData.turn - this.startDates.get(i).get(treaty).intValue()) >= 10) {
            return 0;
        }
        return (int) (0 - ((empire.b() / 2) * ((10 - intValue) * 0.1f)));
    }

    private int getTradeIncome(int i) {
        Empire empire = GameData.empires.get(this.empireID);
        Empire empire2 = GameData.empires.get(i);
        Treaty treaty = Treaty.TRADE;
        if (hasTreaty(i, treaty)) {
            int intValue = GameData.turn - this.startDates.get(i).get(treaty).intValue();
            if (intValue < 10) {
                return (int) (0 - ((empire.b() / 2) * ((10 - intValue) * 0.1f)));
            }
            return (int) (0 + ((empire2.b() / 2) * (intValue < 20 ? (intValue - 10) * 0.01f : 0.1f)));
        }
        return 0;
    }

    private int getTributeIncome(int i) {
        Empire empire = GameData.empires.get(this.empireID);
        Empire empire2 = GameData.empires.get(i);
        Treaty treaty = Treaty.TRIBUTE;
        int b = hasTreaty(i, treaty) ? (int) (0 - (empire.b() * 0.1f)) : 0;
        return empire2.getTreaties().hasTreaty(this.empireID, treaty) ? (int) (b + (empire2.b() * 0.1f)) : b;
    }

    public int a() {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            int incomeFromTreaty = getIncomeFromTreaty(i2, Treaty.RESEARCH);
            if (incomeFromTreaty < 0) {
                i += incomeFromTreaty;
            }
            int incomeFromTreaty2 = getIncomeFromTreaty(i2, Treaty.TRADE);
            if (incomeFromTreaty2 < 0) {
                i += incomeFromTreaty2;
            }
            int incomeFromTreaty3 = getIncomeFromTreaty(i2, Treaty.TRIBUTE);
            if (incomeFromTreaty3 < 0) {
                i += incomeFromTreaty3;
            }
        }
        return i;
    }

    public void addTreaty(int i, Treaty treaty) {
        if (hasTreaty(i, treaty)) {
            return;
        }
        Empire empire = GameData.empires.get(this.empireID);
        if (empire.isHuman()) {
            Game.gameAchievements.checkForTreatyAchievements(treaty);
        }
        int[] iArr = this.treaties;
        iArr[i] = iArr[i] + treaty.getID();
        empire.updateRelationValue(i, RelationEvent.NEW_TREATY);
        if (treaty.keepStartDate()) {
            this.startDates.get(i).put(treaty, Integer.valueOf(GameData.turn));
        }
    }

    public int b() {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            i += getIncomeFromTreaties(i2);
        }
        return i;
    }

    public int c() {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            i += getResearchPointsFromTreaties(i2);
        }
        return i;
    }

    public int d() {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            int incomeFromTreaty = getIncomeFromTreaty(i2, Treaty.RESEARCH);
            if (incomeFromTreaty > 0) {
                i += incomeFromTreaty;
            }
            int incomeFromTreaty2 = getIncomeFromTreaty(i2, Treaty.TRADE);
            if (incomeFromTreaty2 > 0) {
                i += incomeFromTreaty2;
            }
            int incomeFromTreaty3 = getIncomeFromTreaty(i2, Treaty.TRIBUTE);
            if (incomeFromTreaty3 > 0) {
                i += incomeFromTreaty3;
            }
        }
        return i;
    }

    public void declareWar(int i) {
        int[] iArr = this.treaties;
        Treaty treaty = Treaty.DECLARATION_OF_WAR;
        iArr[i] = treaty.getID();
        this.startDates.get(i).clear();
        this.startDates.get(i).put(treaty, Integer.valueOf(GameData.turn));
    }

    public int[] e() {
        return this.treaties;
    }

    public int getIncomeFromTreaties(int i) {
        return getIncomeFromTreaty(i, Treaty.RESEARCH) + 0 + getIncomeFromTreaty(i, Treaty.TRADE) + getIncomeFromTreaty(i, Treaty.TRIBUTE);
    }

    public int getIncomeFromTreaty(int i, Treaty treaty) {
        int i2 = AnonymousClass1.f1407a[treaty.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return getTributeIncome(i);
                }
                throw new AssertionError("Unknown treaty");
            }
            return getTradeIncome(i);
        }
        return getResearchCost(i);
    }

    public int getResearchPointsFromTreaties(int i) {
        int intValue;
        Treaty treaty = Treaty.RESEARCH;
        if (!hasTreaty(i, treaty) || (intValue = GameData.turn - this.startDates.get(i).get(treaty).intValue()) < 10) {
            return 0;
        }
        return (int) (0 + (GameData.empires.get(i).c() * (intValue < 20 ? (intValue - 10) * 0.01f : 0.1f)));
    }

    public int getStartDate(int i, Treaty treaty) {
        Map<Treaty, Integer> map = this.startDates.get(i);
        if (map.containsKey(treaty)) {
            return map.get(treaty).intValue();
        }
        map.put(treaty, Integer.valueOf(GameData.turn));
        return GameData.turn;
    }

    public List<Map<Treaty, Integer>> getStartDates() {
        return this.startDates;
    }

    public List<Treaty> getTreaties(int i) {
        Treaty[] values;
        ArrayList arrayList = new ArrayList();
        for (Treaty treaty : Treaty.values()) {
            if (hasTreaty(i, treaty)) {
                arrayList.add(treaty);
            }
        }
        return arrayList;
    }

    public boolean hasTreaty(int i, Treaty treaty) {
        return (this.treaties[i] & treaty.getID()) == treaty.getID();
    }

    public void makePeace(int i) {
        int[] iArr = this.treaties;
        Treaty treaty = Treaty.PEACE_TREATY;
        iArr[i] = treaty.getID();
        this.startDates.get(i).put(treaty, Integer.valueOf(GameData.turn));
        GameData.empires.get(this.empireID).updateRelationValue(i, RelationEvent.PEACE);
    }

    public void removeTreaty(int i, Treaty treaty) {
        if (hasTreaty(i, treaty)) {
            int[] iArr = this.treaties;
            iArr[i] = iArr[i] - treaty.getID();
            if (treaty.keepStartDate()) {
                this.startDates.get(i).remove(treaty);
            }
        }
    }

    public void setStartDates(List<Map<Treaty, Integer>> list) {
        this.startDates = list;
    }

    public void setTreatiesValue(int i, int i2) {
        this.treaties[i] = i2;
    }
}
