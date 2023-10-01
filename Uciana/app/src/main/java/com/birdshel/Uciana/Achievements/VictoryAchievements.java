package com.birdshel.Uciana.Achievements;

import android.app.Activity;

import com.birdshel.Uciana.Difficulty;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.Players.RaceAttribute;
import com.birdshel.Uciana.Technology.TechProgressionType;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class VictoryAchievements extends BaseAchievements {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Achievements.VictoryAchievements$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1352a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[TechProgressionType.values().length];
            b = iArr;
            try {
                iArr[TechProgressionType.ALLOW_ONLY_ONE_TECH_PER_LEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[TechProgressionType.ALLOW_ONLY_ONE_RANDOM_TECH_PER_LEVEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[Difficulty.values().length];
            f1352a = iArr2;
            try {
                iArr2[Difficulty.HARDEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1352a[Difficulty.HARDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1352a[Difficulty.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1352a[Difficulty.EASIER.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1352a[Difficulty.EASIEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VictoryAchievements(Game game, Activity activity) {
        super(game, activity);
    }

    private void checkEmpireForDefaults(int i, AchievementID achievementID) {
        if (c(achievementID)) {
            return;
        }
        Empire empire = this.game.empires.get(i);
        List<RaceAttribute> asList = Arrays.asList(Empires.getDefaultRaceAttributes(i));
        List<RaceAttribute> raceAttributes = empire.getRaceAttributes();
        if (raceAttributes.size() != asList.size()) {
            return;
        }
        for (RaceAttribute raceAttribute : asList) {
            if (!raceAttributes.contains(raceAttribute)) {
                return;
            }
        }
        a(achievementID);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0073, code lost:
        if (r3 != 5) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void d(int i, int i2) {
        int i3;
        if (i2 == 0) {
            b(AchievementID.MILITARY_VICTORY);
        } else if (i2 == 1) {
            b(AchievementID.COALITION_VICTORY);
        }
        AchievementID achievementID = AchievementID.NO_PERK_VICTORY;
        if (!c(achievementID) && this.game.empires.get(i).getRaceAttributes().isEmpty()) {
            a(achievementID);
        }
        switch (i) {
            case 0:
                checkEmpireForDefaults(i, AchievementID.TARLISH_VICTORY);
                break;
            case 1:
                checkEmpireForDefaults(i, AchievementID.HUMAN_VICTORY);
                break;
            case 2:
                checkEmpireForDefaults(i, AchievementID.SOTHREN_VICTORY);
                break;
            case 3:
                checkEmpireForDefaults(i, AchievementID.DARGATHI_VICTORY);
                break;
            case 4:
                checkEmpireForDefaults(i, AchievementID.BYLON_VICTORY);
                break;
            case 5:
                checkEmpireForDefaults(i, AchievementID.AMEOLI_VICTORY);
                break;
            case 6:
                checkEmpireForDefaults(i, AchievementID.MARRENAREE_VICTORY);
                break;
        }
        int i4 = AnonymousClass1.f1352a[Game.getDifficulty().ordinal()];
        if (i4 == 1) {
            b(AchievementID.HARDEST_VICTORY);
        } else if (i4 != 2) {
            if (i4 != 3) {
                if (i4 != 4) {
                }
                b(AchievementID.EASY_VICTORY);
                b(AchievementID.EASIEST_VICTORY);
                i3 = AnonymousClass1.b[GameData.gameSettings.techProgressionType().ordinal()];
                if (i3 == 1) {
                    b(AchievementID.ONE_TECH_PER_LEVEL);
                    return;
                } else if (i3 != 2) {
                    return;
                } else {
                    b(AchievementID.ONE_RANDOM_TECH_PER_LEVEL);
                    return;
                }
            }
            b(AchievementID.NORMAL_VICTORY);
            b(AchievementID.EASY_VICTORY);
            b(AchievementID.EASIEST_VICTORY);
            i3 = AnonymousClass1.b[GameData.gameSettings.techProgressionType().ordinal()];
            if (i3 == 1) {
            }
        }
        b(AchievementID.HARD_VICTORY);
        b(AchievementID.NORMAL_VICTORY);
        b(AchievementID.EASY_VICTORY);
        b(AchievementID.EASIEST_VICTORY);
        i3 = AnonymousClass1.b[GameData.gameSettings.techProgressionType().ordinal()];
        if (i3 == 1) {
        }
    }
}
