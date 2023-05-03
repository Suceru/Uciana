package com.birdshel.Uciana.Resources;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Options {
    public static final int BLUE_FLEET_LINES_INDEX = 1;
    public static final int GREEN_FLEET_LINES_INDEX = 0;
    public static final int OFF = 0;
    public static final int ON = 1;
    public static final int RED_FLEET_LINES_INDEX = 2;
    private final boolean[] hideFleetLines;
    private final SharedPreferences options;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Autosave {
        public static final int EVERY_10_TURNS = 2;
        public static final int EVERY_5_TURNS = 3;
        public static final int EVERY_TURN = 4;
        public static final int NEVER = 0;
        public static final int ON_GAME_EXIT = 1;
    }

    public Options(Context context) {
        this.hideFleetLines = r0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("options", 0);
        this.options = sharedPreferences;
        boolean[] zArr = {sharedPreferences.getBoolean(OptionID.HIDE_GREEN_FLEET_LINES.getKey(), false), sharedPreferences.getBoolean(OptionID.HIDE_BLUE_FLEET_LINES.getKey(), false), sharedPreferences.getBoolean(OptionID.HIDE_RED_FLEET_LINES.getKey(), false)};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(OptionID optionID) {
        return this.options.getInt(optionID.getKey(), 0) == 0;
    }

    public float getOption(OptionID optionID, float f2) {
        return this.options.getFloat(optionID.getKey(), f2);
    }

    public boolean isOn(OptionID optionID, int i) {
        return this.options.getInt(optionID.getKey(), i) == 1;
    }

    public void markSeen(TutorialID tutorialID) {
        this.options.edit().putInt(tutorialID.getKey(), 1).apply();
    }

    public void markUnseen(TutorialID tutorialID) {
        this.options.edit().putInt(tutorialID.getKey(), 0).apply();
    }

    public void setFleetLinesVisibility(int i, boolean z) {
        this.hideFleetLines[i] = !z;
        if (i == 0) {
            this.options.edit().putBoolean(OptionID.HIDE_GREEN_FLEET_LINES.getKey(), !z).apply();
        } else if (i == 1) {
            this.options.edit().putBoolean(OptionID.HIDE_BLUE_FLEET_LINES.getKey(), !z).apply();
        } else if (i != 2) {
        } else {
            this.options.edit().putBoolean(OptionID.HIDE_RED_FLEET_LINES.getKey(), !z).apply();
        }
    }

    public void setOption(OptionID optionID, float f2) {
        this.options.edit().putFloat(optionID.getKey(), f2).apply();
    }

    public boolean shouldHideFleetLines(int i) {
        return this.hideFleetLines[i];
    }

    public boolean shouldTutorialBeShown(TutorialID tutorialID) {
        return this.options.getInt(tutorialID.getKey(), 0) == 0;
    }

    public void turnOff(OptionID optionID) {
        this.options.edit().putInt(optionID.getKey(), 0).apply();
    }

    public void turnOn(OptionID optionID) {
        this.options.edit().putInt(optionID.getKey(), 1).apply();
    }

    public boolean isOn(OptionID optionID) {
        return isOn(optionID, 0);
    }
}
