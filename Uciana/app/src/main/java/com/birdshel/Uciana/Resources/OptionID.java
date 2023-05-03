package com.birdshel.Uciana.Resources;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum OptionID {
    DEBUG("debug", 0),
    MUSIC("music", R.string.options_music),
    MUSIC_VOLUME("musicVolume", R.string.options_music_volume),
    SOUND("sound", R.string.options_sound),
    SOUND_VOLUME("soundVolume", R.string.options_sound_volume),
    VIBRATE("vibrate", 0),
    SET_TRADEGOODS("setTradegoods", 0),
    GALAXY_SIZE("galaxySize", 0),
    NUMBER_OF_PLAYERS("numberOfPlayers", 0),
    DIFFICULTY("difficulty", 0),
    LAST_SELECTED_EMPIRE("lastSelectedEmpire", 0),
    AMBIENT_SOUND("ambientSound", R.string.options_ambient_sound),
    AMBIENT_SOUND_VOLUME("ambientSoundVolume", R.string.options_ambient_sound_volume),
    CLEAR_EVENTS_END_TURN("clearEventsEndTurn", 0),
    SHOW_GRID("showGrid", 0),
    STARTING_POSITIONS("startingPosition", 0),
    HIDE_SURFACE_IMAGE_SM("showSurfaceImageSmallMed", 0),
    HIDE_SURFACE_IMAGE_LH("showSurfaceImageLargeHuge", 0),
    AUTOSAVE("autosave", 0),
    BLACKHOLES_LEVEL("blackholesLevel", 0),
    SPACE_RIFTS_LEVEL("spaceRiftsLevel", 0),
    WORMHOLES_LEVEL("wormholesLevel", 0),
    LAST_AUTOSAVE_SLOT("lastAutosaveSlot", 0),
    RANDOM_EMPIRE_PERK("randomEmpirePerkOption", 0),
    HIDE_RED_FLEET_LINES("hideRedFleetLines", 0),
    HIDE_BLUE_FLEET_LINES("hideBlueFleetLines", 0),
    HIDE_GREEN_FLEET_LINES("hideGreenFleetLines", 0),
    RANDOM_EVENTS("randomEvents", 0),
    TECH_PROGRESSION("techProgression", 0),
    DIPLOMATIC_OPTIONS("diplomaticOptions", 0),
    ATTACK_OPTIONS("attackOptions", 0),
    SYSTEM_OBJECT_PERCENT("systemObjectPercent", 0),
    MODDING("modding", 0),
    CUSTOM_EMPIRE_COLOR("customEmpireColor", 0),
    CUSTOM_EMPIRE_BANNER("customEmpireBanner", 0),
    CUSTOM_EMPIRE_RACE("customEmpireRace", 0),
    CUSTOM_EMPIRE_SHIP_STYLE("customEmpireShipStyle", 0),
    AI_APPEARANCE("aiAppearance", 0),
    CONTROL_INDICATOR("controlIndicator", 0);
    
    private final int description;
    private final String key;

    OptionID(String str, int i) {
        this.key = str;
        this.description = i;
    }

    public String getDescription() {
        int i = this.description;
        return i == 0 ? "" : GameData.activity.getString(i);
    }

    public String getKey() {
        return this.key;
    }
}
