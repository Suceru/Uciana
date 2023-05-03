package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import java.util.ArrayList;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.align.HorizontalAlign;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class CreditsEvent {
    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(Game game, RandomEventType randomEventType) {
        game.empires.get(game.gameSettings.currentRandomEventData1()).addRemoveCredits(game.gameSettings.currentRandomEventData2());
        if (game.getCurrentScene() instanceof GalaxyScene) {
            game.galaxyScene.empireInfo.update();
        }
        game.randomEvents.a(new RandomEventData(randomEventType, game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2(), game.gameSettings.currentRandomEventData3()));
        game.randomEvents.setRandomEvent(RandomEventType.NONE, -1, -1, -1);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(Game game, int i) {
        ArrayList arrayList = new ArrayList();
        for (Empire empire : game.empires.getEmpires()) {
            if (empire.isAlive()) {
                arrayList.add(Integer.valueOf(empire.id));
            }
        }
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, Integer.valueOf(((Integer) arrayList.get(Functions.random.nextInt(arrayList.size()))).intValue()));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, Integer.valueOf(i));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, -1);
        return true;
    }

    public boolean checkEvent(Game game) {
        return game.empires.get(game.gameSettings.currentRandomEventData1()).isAlive();
    }

    public Entity getMessage(MessageOverlay messageOverlay, String str, String str2, int i) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Entity entity = new Entity();
        if (i == game.getCurrentPlayer()) {
            game.graphics.setAmbassadorTexture(game.getCurrentEmpire().getRaceID(), game.getActivity());
            Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
            sprite.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite);
        } else {
            Sprite sprite2 = new Sprite(0.0f, 230.0f, game.graphics.spyTexture, buffer);
            sprite2.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite2);
        }
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, str, new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 300.0f);
        entity.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        tiledSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text.getY() - 45.0f);
        entity.attachChild(tiledSprite);
        Text text2 = new Text(220.0f, 0.0f, game.fonts.infoFont, str2, new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
        text2.setY(450.0f - text2.getHeightScaled());
        entity.attachChild(text2);
        return entity;
    }
}
