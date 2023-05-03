package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
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

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ResearchBreakthroughEvent implements RandomEvent {
    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean checkEvent(Game game) {
        return game.empires.get(game.gameSettings.currentRandomEventData1()).isAlive();
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public int execute(Game game) {
        game.empires.get(game.gameSettings.currentRandomEventData1()).getCurrentTech().addResearch(game.gameSettings.currentRandomEventData2());
        if (game.getCurrentScene() instanceof GalaxyScene) {
            game.galaxyScene.empireInfo.update();
        }
        game.randomEvents.a(new RandomEventData(RandomEventType.RESEARCH_BREAKTHROUGH, game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2(), game.gameSettings.currentRandomEventData3()));
        game.randomEvents.setRandomEvent(RandomEventType.NONE, -1, -1, -1);
        return 0;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public Entity getMessage(MessageOverlay messageOverlay, int i, int i2, int i3, int i4) {
        String string;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Entity entity = new Entity();
        boolean z = i2 == game.getCurrentPlayer();
        if (z) {
            game.graphics.setAmbassadorTexture(game.getCurrentEmpire().getRaceID(), game.getActivity());
            Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
            sprite.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite);
        } else {
            Sprite sprite2 = new Sprite(0.0f, 230.0f, game.graphics.spyTexture, buffer);
            sprite2.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite2);
        }
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.research_breakthrough), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 300.0f);
        entity.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        tiledSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text.getY() - 45.0f);
        entity.attachChild(tiledSprite);
        if (z) {
            string = game.getActivity().getString(Functions.getResId("research_breakthrough_them_" + game.getCurrentPlayer(), R.string.class), new Object[]{Integer.valueOf(i3)});
        } else {
            string = game.getActivity().getString(Functions.getResId("research_breakthrough_others_" + game.getCurrentPlayer(), R.string.class));
        }
        Text text2 = new Text(220.0f, 0.0f, game.fonts.infoFont, string, new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
        text2.setY(450.0f - text2.getHeightScaled());
        entity.attachChild(text2);
        return entity;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean initialize(Game game) {
        ArrayList arrayList = new ArrayList();
        for (Empire empire : game.empires.getEmpires()) {
            if (empire.isAlive()) {
                arrayList.add(Integer.valueOf(empire.id));
            }
        }
        int intValue = ((Integer) arrayList.get(Functions.random.nextInt(arrayList.size()))).intValue();
        int random = Functions.getRandom(100, 300);
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, Integer.valueOf(intValue));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, Integer.valueOf(random));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, -1);
        return true;
    }
}
