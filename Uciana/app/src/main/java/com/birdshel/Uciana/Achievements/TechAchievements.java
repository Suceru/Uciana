package com.birdshel.Uciana.Achievements;

import android.app.Activity;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Technology.TechCategory;
import com.birdshel.Uciana.Technology.TechID;
import com.birdshel.Uciana.Technology.TechType;
import com.birdshel.Uciana.Technology.Technology;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TechAchievements extends BaseAchievements {



    /* JADX INFO: Access modifiers changed from: package-private */
    public TechAchievements(Game game, Activity activity) {
        super(game, activity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i, TechCategory techCategory, TechType techType, List<TechID> list) {
        boolean z;
        Technology tech = this.game.empires.get(i).getTech();
        Iterator<TechID> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (tech.getTech(it.next()).getCategory() == techCategory) {
                z = true;
                break;
            }
        }

        if (!z) {
            switch (techCategory){

                case NONE:
                    break;
                case ENGINEERING:
                    b(AchievementID.ENGINEERING_MASTERS);
                    break;
                case PHYSICS:
                    b(AchievementID.PHYSICS_MASTERS);
                    break;
                case CHEMISTRY:
                    b(AchievementID.CHEMISTRY_MASTERS);
                    break;
                case ENERGY:
                    b(AchievementID.ENERGY_MASTERS);
                    break;
            }

        }
        if (list.isEmpty()) {
            b(AchievementID.TECHNOLOGY_MASTERS);
        }
        if (techType == TechType.RESOURCE) {
            Game.gameAchievements.a(i);
        }
    }
}
/*    *//* JADX INFO: Access modifiers changed from: package-private *//*
 *//* compiled from: MyApplication *//*
 *//* renamed from: com.birdshel.Uciana.Achievements.TechAchievements$1  reason: invalid class name *//*
 *//* loaded from: classes.dex *//*
    public static *//* synthetic *//* class AnonymousClass1 {

 *//* renamed from: a  reason: collision with root package name *//*
        static final *//* synthetic *//* int[] f1351a;

        static {
            int[] iArr = new int[TechCategory.values().length];
            f1351a = iArr;
            try {
                iArr[TechCategory.ENGINEERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1351a[TechCategory.PHYSICS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1351a[TechCategory.CHEMISTRY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1351a[TechCategory.ENERGY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }*/
        /*int i2 = AnonymousClass1.f1351a[techCategory.ordinal()];
            if (i2 == 1) {
                b(AchievementID.ENGINEERING_MASTERS);
            } else if (i2 == 2) {
                b(AchievementID.PHYSICS_MASTERS);
            } else if (i2 == 3) {
                b(AchievementID.CHEMISTRY_MASTERS);
            } else if (i2 == 4) {
                b(AchievementID.ENERGY_MASTERS);
            }*/
