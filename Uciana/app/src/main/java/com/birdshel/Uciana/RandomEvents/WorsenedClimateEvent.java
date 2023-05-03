package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.StarSystems.StarSystem;
import java.util.ArrayList;
import java.util.Iterator;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class WorsenedClimateEvent extends ClimateEvent implements RandomEvent {
    private final int OWN_COLONY = 0;
    private final int KNOWN_WORLD = 1;
    private final int UNKNOWN_WORLD = 2;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.RandomEvents.WorsenedClimateEvent$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1411a;

        static {
            int[] iArr = new int[Climate.values().length];
            f1411a = iArr;
            try {
                iArr[Climate.SUPER_ACIDIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1411a[Climate.BOG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1411a[Climate.METHANE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1411a[Climate.BOREAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1411a[Climate.PLAGUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1411a[Climate.TROPICAL_OCEAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1411a[Climate.SENTIENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1411a[Climate.CORROSIVE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1411a[Climate.BARREN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1411a[Climate.DESERT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1411a[Climate.TUNDRA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1411a[Climate.OCEAN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1411a[Climate.ARID.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1411a[Climate.SWAMP.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f1411a[Climate.JUNGLE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f1411a[Climate.GARDEN.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f1411a[Climate.TERRAN.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private int getMessageType(Game game, int i, int i2) {
        Planet planet = (Planet) game.galaxy.getSystemObject(i, i2);
        if (planet.hasColony() && planet.getOccupier() == game.getCurrentPlayer()) {
            return 0;
        }
        return game.getCurrentEmpire().isDiscoveredSystem(i) ? 1 : 2;
    }

    private Climate getWorseClimate(Climate climate) {
        switch (AnonymousClass1.f1411a[climate.ordinal()]) {
            case 8:
                return Climate.MOLTEN;
            case 9:
                return Climate.RADIATED;
            case 10:
                return Climate.BARREN;
            case 11:
            case 12:
                return Climate.ICE;
            case 13:
                return Climate.DESERT;
            case 14:
                return Climate.TUNDRA;
            case 15:
                return Climate.SWAMP;
            case 16:
                return Climate.TERRAN;
            case 17:
                if (Functions.percent(50)) {
                    return Climate.JUNGLE;
                }
                return Climate.OCEAN;
            default:
                throw new AssertionError("Unsupported Climate: " + climate.getDisplayName());
        }
    }

    @Override // com.birdshel.Uciana.RandomEvents.ClimateEvent, com.birdshel.Uciana.RandomEvents.RandomEvent
    public /* bridge */ /* synthetic */ boolean checkEvent(Game game) {
        return super.checkEvent(game);
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public int execute(Game game) {
        return a(game, RandomEventType.WORSENED_CLIMATE);
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public Entity getMessage(MessageOverlay messageOverlay, int i, int i2, int i3, int i4) {
        String string;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Entity entity = new Entity();
        Point d2 = d(i2);
        int messageType = getMessageType(game, (int) d2.getX(), (int) d2.getY());
        if (messageType != 1 && messageType != 0) {
            Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.spyTexture, buffer);
            sprite.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite);
        } else {
            game.graphics.setAmbassadorTexture(game.getCurrentEmpire().getRaceID(), game.getActivity());
            Sprite sprite2 = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
            sprite2.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite2);
        }
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.worsened_climate), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 300.0f);
        entity.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        tiledSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text.getY() - 45.0f);
        entity.attachChild(tiledSprite);
        Object name = game.galaxy.getSystemObject((int) d2.getX(), (int) d2.getY()).getName();
        if (messageType == 0) {
            string = game.getActivity().getString(Functions.getResId("worsened_climate_own_" + game.getCurrentPlayer(), R.string.class), new Object[]{name});
        } else if (messageType == 1) {
            string = game.getActivity().getString(Functions.getResId("worsened_climate_known_" + game.getCurrentPlayer(), R.string.class), new Object[]{name});
        } else if (messageType != 2) {
            string = "";
        } else {
            string = game.getActivity().getString(Functions.getResId("worsened_climate_unknown_" + game.getCurrentPlayer(), R.string.class));
        }
        Text text2 = new Text(220.0f, 0.0f, game.fonts.infoFont, string, new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
        text2.setY(450.0f - text2.getHeightScaled());
        entity.attachChild(text2);
        return entity;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean initialize(Game game) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (RandomEventData randomEventData : game.randomEvents.b(RandomEventType.WORSENED_CLIMATE)) {
            arrayList.add(d(randomEventData.getData1()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (StarSystem starSystem : game.galaxy.getStarSystems()) {
            for (SystemObject systemObject : starSystem.getSystemObjects()) {
                if (systemObject.isPlanet()) {
                    Planet planet = (Planet) systemObject;
                    if (!planet.getClimate().isSpecial() && planet.getClimate() != Climate.MOLTEN && planet.getClimate() != Climate.RADIATED && planet.getClimate() != Climate.ICE && !planet.getTerraformedClimate().isRareClimate()) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                            Point point = (Point) it.next();
                            int x = (int) point.getX();
                            int y = (int) point.getY();
                            if (planet.getSystemID() == x && planet.getOrbit() == y) {
                                z = true;
                                break;
                            }
                        }
                        if (!z) {
                            arrayList2.add(new Point(planet.getSystemID(), planet.getOrbit()));
                        }
                    }
                }
            }
        }
        if (arrayList2.isEmpty()) {
            return false;
        }
        try {
            Point point2 = (Point) arrayList2.get(Functions.random.nextInt(arrayList2.size()));
            Planet planet2 = (Planet) game.galaxy.getSystemObject((int) point2.getX(), (int) point2.getY());
            Climate worseClimate = getWorseClimate(planet2.getPreTerraformedClimate());
            Climate terraformedClimate = worseClimate.getTerraformedClimate();
            if (planet2.getResources().size() == 4 && Resources.climateHasResource(terraformedClimate)) {
                switch (AnonymousClass1.f1411a[terraformedClimate.ordinal()]) {
                    case 1:
                        terraformedClimate = Climate.DESERT;
                        break;
                    case 2:
                    case 3:
                        terraformedClimate = Climate.JUNGLE;
                        break;
                    case 4:
                    case 5:
                        terraformedClimate = Climate.TERRAN;
                        break;
                    case 6:
                        terraformedClimate = Climate.GARDEN;
                        break;
                    case 7:
                        terraformedClimate = Climate.GAIA;
                        break;
                }
            }
            Climate worseClimate2 = getWorseClimate(planet2.getClimate());
            game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, Integer.valueOf(c((int) point2.getX(), (int) point2.getY())));
            game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, Integer.valueOf(b(worseClimate, terraformedClimate)));
            game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, Integer.valueOf(worseClimate2.ordinal()));
            return true;
        } catch (AssertionError unused) {
            return false;
        }
    }
}
