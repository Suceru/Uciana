package com.birdshel.Uciana;

import com.birdshel.Uciana.RandomEvents.RandomEventType;
import com.birdshel.Uciana.Technology.TechProgressionType;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameSettings {
    private Map<GameSettingsEnum, Integer> settings = new HashMap();

    public int currentRandomEventData1() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_CURRENT_DATA1).intValue();
    }

    public int currentRandomEventData2() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_CURRENT_DATA2).intValue();
    }

    public int currentRandomEventData3() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_CURRENT_DATA3).intValue();
    }

    public int currentRandomEventStartTurn() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_CURRENT_START_TURN).intValue();
    }

    public RandomEventType currentRandomEventType() {
        return RandomEventType.values()[this.settings.get(GameSettingsEnum.RANDOM_EVENTS_CURRENT_TYPE).intValue()];
    }

    public Map<GameSettingsEnum, Integer> getSettings() {
        return this.settings;
    }

    public boolean isAlwaysAtWar() {
        Map<GameSettingsEnum, Integer> map = this.settings;
        GameSettingsEnum gameSettingsEnum = GameSettingsEnum.DIPLOMATIC_OPTIONS;
        if (!map.containsKey(gameSettingsEnum)) {
            setSetting(gameSettingsEnum, 0);
        }
        return this.settings.get(gameSettingsEnum).intValue() == 1;
    }

    public boolean isRandomEventsOn() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_ON).intValue() == 1;
    }

    public int nextRandomEventData1() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1).intValue();
    }

    public int nextRandomEventData2() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2).intValue();
    }

    public int nextRandomEventData3() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3).intValue();
    }

    public int nextRandomEventTurn() {
        return this.settings.get(GameSettingsEnum.RANDOM_EVENTS_NEXT_TURN).intValue();
    }

    public RandomEventType nextRandomEventType() {
        return RandomEventType.values()[this.settings.get(GameSettingsEnum.RANDOM_EVENTS_NEXT_TYPE).intValue()];
    }

    public boolean onlyAllowAutoAttack() {
        Map<GameSettingsEnum, Integer> map = this.settings;
        GameSettingsEnum gameSettingsEnum = GameSettingsEnum.ATTACK_OPTIONS;
        if (!map.containsKey(gameSettingsEnum)) {
            setSetting(gameSettingsEnum, 0);
        }
        return this.settings.get(gameSettingsEnum).intValue() == 1;
    }

    public void setSetting(GameSettingsEnum gameSettingsEnum, Integer num) {
        this.settings.put(gameSettingsEnum, num);
    }

    public void setSettings(Map<GameSettingsEnum, Integer> map) {
        this.settings = map;
    }

    public TechProgressionType techProgressionType() {
        Map<GameSettingsEnum, Integer> map = this.settings;
        GameSettingsEnum gameSettingsEnum = GameSettingsEnum.TECH_PROGRESSION_TYPE;
        if (map.containsKey(gameSettingsEnum)) {
            return TechProgressionType.values()[this.settings.get(gameSettingsEnum).intValue()];
        }
        return TechProgressionType.ONE_TECH_REQUIRED_PER_LEVEL;
    }
}
