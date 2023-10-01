package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class HyperSpaceFluctuations implements RandomEvent {
    public static final int MESSAGE_SHIP_DESTROYED = 1;
    private final int MESSAGE_EVENT_START = 0;
    private final int MESSAGE_EVENT_END = 2;

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean checkEvent(Game game) {
        return true;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public int execute(Game game) {
        if (game.randomEvents.getCurrentRandomEventData1() == GameData.turn) {
            game.galaxyScene.setGalaxyState(1);
            return 0;
        } else if (game.randomEvents.getCurrentRandomEventData2() <= GameData.turn) {
            game.galaxyScene.setGalaxyState(0);
            game.randomEvents.a(new RandomEventData(RandomEventType.HYPER_SPACE_FLUCTUATIONS, game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2(), game.gameSettings.currentRandomEventData3()));
            game.randomEvents.setRandomEvent(RandomEventType.NONE, -1, -1, -1);
            return 2;
        } else {
            game.galaxyScene.setGalaxyState(1);
            if (Functions.percent(70)) {
                return -1;
            }
            List<Fleet> movingFleets = game.fleets.getMovingFleets();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Fleet fleet : movingFleets) {
                for (Ship ship : fleet.getShips()) {
                    linkedHashMap.put(ship.getID(), fleet.id);
                }
            }
            if (linkedHashMap.isEmpty()) {
                return -1;
            }
            String str = (String) linkedHashMap.keySet().toArray()[Functions.random.nextInt(linkedHashMap.size())];
            String str2 = (String) linkedHashMap.get(str);
            game.getRandomEventData().setData3(game.fleets.get(str2).getShip(str).getEmpireID());
            Fleet fleet2 = game.fleets.get(str2);
            fleet2.removeShip(str);
            if (fleet2.isEmpty()) {
                game.fleets.remove(fleet2);
            }
            return 1;
        }
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public Entity getMessage(MessageOverlay messageOverlay, int i, int i2, int i3, int i4) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Entity entity = new Entity();
        game.graphics.setAmbassadorTexture(game.getCurrentEmpire().getRaceID(), game.getActivity());
        Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
        sprite.setSize(200.0f, 250.0f);
        messageOverlay.addElement(sprite);
        if (i == 0) {
            Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.hyperspace_flux_started), new TextOptions(HorizontalAlign.CENTER), buffer);
            text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 275.0f);
            entity.attachChild(text);
            TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
            tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
            tiledSprite.setSize(40.0f, 40.0f);
            tiledSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text.getY() - 40.0f);
            entity.attachChild(tiledSprite);
            Text text2 = new Text(220.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(Functions.getResId("hyperspace_flux_started_" + game.getCurrentPlayer(), R.string.class)), new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
            text2.setY(475.0f - text2.getHeightScaled());
            entity.attachChild(text2);
            return entity;
        } else if (i == 1) {
            Text text3 = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.hyperspace_flux_ship_lost), new TextOptions(HorizontalAlign.CENTER), buffer);
            text3.setPosition((messageOverlay.getWidth() / 2.0f) - (text3.getWidth() / 2.0f), 300.0f);
            entity.attachChild(text3);
            TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
            tiledSprite2.setSize(40.0f, 40.0f);
            tiledSprite2.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text3.getY() - 45.0f);
            entity.attachChild(tiledSprite2);
            Text text4 = new Text(220.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(Functions.getResId("hyperspace_flux_ship_lost_" + game.getCurrentPlayer(), R.string.class)), new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
            text4.setY(450.0f - text4.getHeightScaled());
            entity.attachChild(text4);
            return entity;
        } else if (i != 2) {
            return entity;
        } else {
            Text text5 = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.hyperspace_flux_ended), new TextOptions(HorizontalAlign.CENTER), buffer);
            text5.setPosition((messageOverlay.getWidth() / 2.0f) - (text5.getWidth() / 2.0f), 300.0f);
            entity.attachChild(text5);
            TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
            tiledSprite3.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
            tiledSprite3.setSize(40.0f, 40.0f);
            tiledSprite3.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text5.getY() - 45.0f);
            entity.attachChild(tiledSprite3);
            Text text6 = new Text(220.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(Functions.getResId("hyperspace_flux_ended_" + game.getCurrentPlayer(), R.string.class)), new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
            text6.setY(450.0f - text6.getHeightScaled());
            entity.attachChild(text6);
            return entity;
        }
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean initialize(Game game) {
        int nextRandomEventTurn = game.gameSettings.nextRandomEventTurn();
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, Integer.valueOf(nextRandomEventTurn));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, Integer.valueOf(nextRandomEventTurn + 10));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, -1);
        return true;
    }
}
