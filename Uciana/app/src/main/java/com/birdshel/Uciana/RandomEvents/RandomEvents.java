package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameSettings;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.StarSystems.GalaxySize;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class RandomEvents {
    private RandomEventType currentRandomEvent;
    private int currentRandomEventStartTurn;
    private final Game game;
    private List<RandomEventData> history = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.RandomEvents.RandomEvents$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1410a;

        static {
            int[] iArr = new int[RandomEventType.values().length];
            f1410a = iArr;
            try {
                iArr[RandomEventType.WORMHOLE_CREATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1410a[RandomEventType.IMPROVED_CLIMATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1410a[RandomEventType.WORSENED_CLIMATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1410a[RandomEventType.COMPUTER_VIRUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1410a[RandomEventType.RESEARCH_BREAKTHROUGH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1410a[RandomEventType.NEW_DEPOSITS_FOUND.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1410a[RandomEventType.MINERAL_DEPOSITS_DEPLETED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1410a[RandomEventType.STOLEN_CREDITS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1410a[RandomEventType.CREDITS_DONATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1410a[RandomEventType.COLONY_REVOLT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1410a[RandomEventType.HYPER_SPACE_FLUCTUATIONS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1410a[RandomEventType.SPACE_MONSTER.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1410a[RandomEventType.WORMHOLE_COLLAPSE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1410a[RandomEventType.NONE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    public RandomEvents(Game game) {
        this.game = game;
    }

    private void figureOutNextEvent(int i) {
        int nextInt = GameData.turn + Functions.random.nextInt(10) + i;
        GameSettings gameSettings = this.game.gameSettings;
        GameSettingsEnum gameSettingsEnum = GameSettingsEnum.RANDOM_EVENTS_NEXT_TURN;
        gameSettings.setSetting(gameSettingsEnum, Integer.valueOf(nextInt));
        RandomEventType generateRandomEventType = generateRandomEventType();
        if (!getRandomEvent(generateRandomEventType).initialize(this.game)) {
            generateRandomEventType = RandomEventType.NONE;
            this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, -1);
            this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, -1);
            this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, -1);
        }
        this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_TYPE, Integer.valueOf(generateRandomEventType.ordinal()));
        if (generateRandomEventType == RandomEventType.NONE) {
            this.game.gameSettings.setSetting(gameSettingsEnum, Integer.valueOf(GameData.turn + 2));
        }
    }

    private RandomEventType generateRandomEventType() {
        RandomEventType[] values;
        ArrayList arrayList = new ArrayList();
        for (RandomEventType randomEventType : RandomEventType.values()) {
            for (int i = 0; i < randomEventType.getRarity().getOccurrence(); i++) {
                arrayList.add(randomEventType);
            }
        }
        return (RandomEventType) arrayList.get(Functions.random.nextInt(arrayList.size()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RandomEventData randomEventData) {
        this.history.add(randomEventData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<RandomEventData> b(RandomEventType randomEventType) {
        ArrayList arrayList = new ArrayList();
        for (RandomEventData randomEventData : this.history) {
            if (randomEventData.getType() == randomEventType) {
                arrayList.add(randomEventData);
            }
        }
        return arrayList;
    }

    public boolean canCurrentRandomEventStillHappen() {
        return getRandomEvent(this.currentRandomEvent).checkEvent(this.game);
    }

    public void clear() {
        this.history = new ArrayList();
        setRandomEvent(RandomEventType.NONE, -1, -1, -1);
        setCurrentRandomEventStartTurn(0);
        figureOutNextEvent(15);
    }

    public int executeCurrentRandomEvent() {
        return getRandomEvent(this.currentRandomEvent).execute(this.game);
    }

    public RandomEventType getCurrentRandomEvent() {
        return this.currentRandomEvent;
    }

    public int getCurrentRandomEventData1() {
        return this.game.gameSettings.currentRandomEventData1();
    }

    public int getCurrentRandomEventData2() {
        return this.game.gameSettings.currentRandomEventData2();
    }

    public int getCurrentRandomEventData3() {
        return this.game.gameSettings.currentRandomEventData3();
    }

    public int getCurrentRandomEventStartTurn() {
        return this.currentRandomEventStartTurn;
    }

    public List<RandomEventData> getHistory() {
        return this.history;
    }

    public int getInitialRandomEventTurn(GalaxySize galaxySize) {
        return galaxySize.getAverageNumberOfStars() + (galaxySize.getAverageNumberOfStars() / 2);
    }

    public int getNextRandomEventTurn() {
        return this.game.gameSettings.nextRandomEventTurn();
    }

    public RandomEvent getRandomEvent(RandomEventType randomEventType) {
        switch (AnonymousClass1.f1410a[randomEventType.ordinal()]) {
            case 1:
                return new WormholeCreationEvent();
            case 2:
                return new ImprovedClimateEvent();
            case 3:
                return new WorsenedClimateEvent();
            case 4:
                return new ComputerVirusEvent();
            case 5:
                return new ResearchBreakthroughEvent();
            case 6:
                return new NewDepositsFoundEvent();
            case 7:
                return new DepletedMineralResourcesEvent();
            case 8:
                return new StolenCreditsEvent();
            case 9:
                return new CreditDonationEvent();
            case 10:
                return new ColonyRevoltEvent();
            case 11:
                return new HyperSpaceFluctuations();
            case 12:
                return new SpaceMonsterEvent();
            case 13:
                return new WormholeCollapseEvent();
            default:
                return new NoEvent();
        }
    }

    public void setCurrentRandomEventStartTurn(int i) {
        this.currentRandomEventStartTurn = i;
        this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_CURRENT_START_TURN, Integer.valueOf(i));
    }

    public void setHistory(List<RandomEventData> list) {
        this.history = list;
    }

    public void setNextToBeCurrentRandomEvent() {
        setRandomEvent(this.game.gameSettings.nextRandomEventType(), this.game.gameSettings.nextRandomEventData1(), this.game.gameSettings.nextRandomEventData2(), this.game.gameSettings.nextRandomEventData3());
        setCurrentRandomEventStartTurn(GameData.turn);
        figureOutNextEvent(this.currentRandomEvent == RandomEventType.HYPER_SPACE_FLUCTUATIONS ? 5 + (getCurrentRandomEventData2() - GameData.turn) : 5);
    }

    public void setRandomEvent(RandomEventType randomEventType, int i, int i2, int i3) {
        this.currentRandomEvent = randomEventType;
        this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_CURRENT_TYPE, Integer.valueOf(randomEventType.ordinal()));
        this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_CURRENT_DATA1, Integer.valueOf(i));
        this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_CURRENT_DATA2, Integer.valueOf(i2));
        this.game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_CURRENT_DATA3, Integer.valueOf(i3));
    }
}
