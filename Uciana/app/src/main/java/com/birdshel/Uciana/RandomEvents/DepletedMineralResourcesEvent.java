package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.MineralRichness;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.StarSystems.StarSystem;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class DepletedMineralResourcesEvent implements RandomEvent {
    private final int OWN_COLONY = 0;
    private final int COLONY_IS_KNOWN = 1;
    private final int COLONY_IS_UNKNOWN = 2;

    private int getMessageType(Game game, int i, int i2) {
        if (game.getCurrentEmpire().isDiscoveredSystem(i)) {
            return (game.galaxy.getSystemObject(i, i2).hasColony() && game.galaxy.getSystemObject(i, i2).getOccupier() == game.getCurrentPlayer()) ? 0 : 1;
        }
        return 2;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean checkEvent(Game game) {
        return game.galaxy.getSystemObject(game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2()).hasColony();
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public int execute(Game game) {
        ((Planet) game.galaxy.getSystemObject(game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2())).decreaseMineralRichness();
        game.randomEvents.a(new RandomEventData(RandomEventType.MINERAL_DEPOSITS_DEPLETED, game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2(), game.gameSettings.currentRandomEventData3()));
        game.randomEvents.setRandomEvent(RandomEventType.NONE, -1, -1, -1);
        return 0;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public Entity getMessage(MessageOverlay messageOverlay, int i, int i2, int i3, int i4) {
        String string;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Entity entity = new Entity();
        int messageType = getMessageType(game, i2, i3);
        if (messageType == 0) {
            game.graphics.setAmbassadorTexture(game.getCurrentEmpire().getRaceID(), game.getActivity());
            Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
            sprite.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite);
        } else {
            Sprite sprite2 = new Sprite(0.0f, 230.0f, game.graphics.spyTexture, buffer);
            sprite2.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite2);
        }
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.depleted_mineral_deposits), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 300.0f);
        entity.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        tiledSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text.getY() - 45.0f);
        entity.attachChild(tiledSprite);
        Object name = game.galaxy.getSystemObject(i2, i3).getName();
        if (messageType == 0) {
            string = game.getActivity().getString(Functions.getResId("depleted_mineral_deposits_own_" + game.getCurrentPlayer(), R.string.class), new Object[]{name});
        } else if (messageType == 1) {
            string = game.getActivity().getString(Functions.getResId("depleted_mineral_deposits_known_" + game.getCurrentPlayer(), R.string.class), new Object[]{name});
        } else if (messageType != 2) {
            string = "";
        } else {
            string = game.getActivity().getString(Functions.getResId("depleted_mineral_deposits_unknown_" + game.getCurrentPlayer(), R.string.class));
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
        for (RandomEventData randomEventData : game.randomEvents.b(RandomEventType.MINERAL_DEPOSITS_DEPLETED)) {
            arrayList.add(new Point(randomEventData.getData1(), randomEventData.getData2()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (StarSystem starSystem : game.galaxy.getStarSystems()) {
            for (SystemObject systemObject : starSystem.getSystemObjects()) {
                if (systemObject.isPlanet()) {
                    Planet planet = (Planet) systemObject;
                    if (planet.getMineralRichness() != MineralRichness.VERY_POOR && planet.hasColony()) {
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
        Point point2 = (Point) arrayList2.get(Functions.random.nextInt(arrayList2.size()));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, Integer.valueOf((int) point2.getX()));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, Integer.valueOf((int) point2.getY()));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, -1);
        return true;
    }
}
