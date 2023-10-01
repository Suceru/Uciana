package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.ArrayList;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonyRevoltEvent implements RandomEvent {
    private final int OWN_COLONY = 0;
    private final int OTHER_COLONY = 1;

    private int getMessageType(Game game, int i, int i2) {
        return (game.galaxy.getSystemObject(i, i2).hasColony() && game.galaxy.getSystemObject(i, i2).getOccupier() == game.getCurrentPlayer()) ? 0 : 1;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean checkEvent(Game game) {
        return game.colonies.isColony(game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2());
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public int execute(Game game) {
        game.colonies.getColony(game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2()).setPopulationToRevolt();
        game.randomEvents.a(new RandomEventData(RandomEventType.COLONY_REVOLT, game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2(), game.gameSettings.currentRandomEventData3()));
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
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.colony_revolt), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 300.0f);
        entity.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        tiledSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text.getY() - 45.0f);
        entity.attachChild(tiledSprite);
        if (messageType == 1) {
            string = game.getActivity().getString(Functions.getResId("colony_revolt_others_" + game.getCurrentPlayer(), R.string.class));
        } else {
            string = game.getActivity().getString(Functions.getResId("colony_revolt_them_" + game.getCurrentPlayer(), R.string.class), new Object[]{game.colonies.getColony(i2, i3).getName()});
        }
        Text text2 = new Text(220.0f, 0.0f, game.fonts.infoFont, string, new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
        text2.setY(450.0f - text2.getHeightScaled());
        entity.attachChild(text2);
        return entity;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean initialize(Game game) {
        ArrayList arrayList = new ArrayList();
        for (Colony colony : game.colonies.getColonies()) {
            if (!colony.hasBuilding(BuildingID.CAPITAL)) {
                arrayList.add(colony);
            }
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        Colony colony2 = (Colony) arrayList.get(Functions.random.nextInt(arrayList.size()));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, Integer.valueOf(colony2.getSystemID()));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, Integer.valueOf(colony2.getOrbit()));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, -1);
        return true;
    }
}
