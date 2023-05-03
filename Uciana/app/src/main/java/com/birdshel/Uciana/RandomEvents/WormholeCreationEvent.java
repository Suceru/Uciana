package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Scenes.GalaxyScene;
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
public class WormholeCreationEvent implements RandomEvent {
    private final int KNOW_BOTH_SYSTEMS = 0;
    private final int KNOW_ONLY_ONE_SYSTEM = 1;
    private final int KNOW_NONE_OF_THE_SYSTEMS = 2;

    private boolean check(Game game) {
        return Game.options.getOption(OptionID.WORMHOLES_LEVEL, 1.0f) != 0.0f && getNewWormholeCount(game) < game.galaxy.getSize().getMaxNumberOfNewWormholes();
    }

    private int getMessageType(Game game, int i, int i2) {
        Empire currentEmpire = game.getCurrentEmpire();
        if (currentEmpire.isDiscoveredSystem(i) && currentEmpire.isDiscoveredSystem(i2)) {
            return 0;
        }
        return (currentEmpire.isDiscoveredSystem(i) || currentEmpire.isDiscoveredSystem(i2)) ? 1 : 2;
    }

    private int getNewWormholeCount(Game game) {
        int i = 0;
        for (RandomEventData randomEventData : game.randomEvents.getHistory()) {
            if (randomEventData.getType() == RandomEventType.WORMHOLE_CREATION) {
                i++;
            }
        }
        return i;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean checkEvent(Game game) {
        return true;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public int execute(Game game) {
        int currentRandomEventData1 = game.gameSettings.currentRandomEventData1();
        int currentRandomEventData2 = game.gameSettings.currentRandomEventData2();
        game.galaxy.addWormhole(new Point(currentRandomEventData1, currentRandomEventData2));
        game.randomEvents.a(new RandomEventData(RandomEventType.WORMHOLE_CREATION, currentRandomEventData1, currentRandomEventData2, -1));
        game.randomEvents.setRandomEvent(RandomEventType.NONE, -1, -1, -1);
        if (game.getCurrentScene() instanceof GalaxyScene) {
            game.galaxyScene.setWormholes();
            game.galaxyScene.setEmpireDisplay();
            return 0;
        }
        return 0;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public Entity getMessage(MessageOverlay messageOverlay, int i, int i2, int i3, int i4) {
        String string;
        Object name;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Entity entity = new Entity();
        int messageType = getMessageType(game, i2, i3);
        if (messageType != 0 && messageType != 1) {
            Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.spyTexture, buffer);
            sprite.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite);
        } else {
            game.graphics.setAmbassadorTexture(game.getCurrentEmpire().getRaceID(), game.getActivity());
            Sprite sprite2 = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
            sprite2.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite2);
        }
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.wormhole_creation), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 300.0f);
        entity.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        tiledSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text.getY() - 45.0f);
        entity.attachChild(tiledSprite);
        if (messageType == 0) {
            Object name2 = game.galaxy.getStarSystem(i2).getName();
            Object name3 = game.galaxy.getStarSystem(i3).getName();
            string = game.getActivity().getString(Functions.getResId("wormhole_creation_both_" + game.getCurrentPlayer(), R.string.class), new Object[]{name2, name3});
        } else if (messageType == 1) {
            if (game.getCurrentEmpire().isDiscoveredSystem(i2)) {
                name = game.galaxy.getStarSystem(i2).getName();
            } else {
                name = game.galaxy.getStarSystem(i3).getName();
            }
            string = game.getActivity().getString(Functions.getResId("wormhole_creation_one_" + game.getCurrentPlayer(), R.string.class), new Object[]{name});
        } else if (messageType != 2) {
            string = "";
        } else {
            string = game.getActivity().getString(Functions.getResId("wormhole_creation_none_" + game.getCurrentPlayer(), R.string.class));
        }
        Text text2 = new Text(220.0f, 0.0f, game.fonts.infoFont, string, new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
        text2.setY(450.0f - text2.getHeightScaled());
        entity.attachChild(text2);
        return entity;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean initialize(Game game) {
        Point newWormholeEndpoints = game.galaxy.getNewWormholeEndpoints();
        if (check(game)) {
            game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, Integer.valueOf((int) newWormholeEndpoints.getX()));
            game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, Integer.valueOf((int) newWormholeEndpoints.getY()));
            game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, -1);
            return true;
        }
        return false;
    }
}
