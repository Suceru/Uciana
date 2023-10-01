package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.ExplorationFind;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.R;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetExplorationMessage implements Message {
    private final ExplorationFind explorationFind;
    private final Planet planet;
    private final String who;

    public PlanetExplorationMessage(Planet planet, String str, boolean z) {
        this.planet = planet;
        this.who = str;
        if (z) {
            this.explorationFind = planet.getExplorationFindNoCheck();
        } else {
            this.explorationFind = planet.getExplorationFind();
        }
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        float f2;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Sprite sprite = new Sprite(0.0f, 150.0f, game.graphics.whiteTexture, buffer);
        sprite.setSize(messageOverlay.getWidth(), 50.0f);
        sprite.setAlpha(0.9f);
        messageOverlay.addElement(sprite);
        Object name = this.planet.getName();
        if (this.planet.hasColony()) {
            name = this.planet.getColony().getName();
        }
        int i = 0;
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.planet_exploration_header, new Object[]{name}), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(175.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        game.graphics.setSurfaceTexture("Surfaces/" + this.planet.getClimate().getID() + ".png", game.getActivity());
        Sprite sprite2 = new Sprite(0.0f, 200.0f, game.graphics.planetSurfaceTexture, buffer);
        sprite2.setSize(1280.0f, 320.0f);
        messageOverlay.addElement(sprite2);
        if (messageOverlay.getWidth() == 1480.0f) {
            sprite2.setSize(1480.0f, 370.0f);
            sprite2.setY(150.0f);
            sprite.setY(100.0f);
            text.setY(125.0f - (text.getHeightScaled() / 2.0f));
        }
        game.graphics.setExplorerTexture(game.getCurrentEmpire().getRaceID(), game.getActivity());
        Sprite sprite3 = new Sprite(messageOverlay.getWidth() - 380.0f, 170.0f, game.graphics.raceExplorerTexture, buffer);
        sprite3.setSize(280.0f, 350.0f);
        messageOverlay.addElement(sprite3);
        Sprite sprite4 = new Sprite(0.0f, 520.0f, game.graphics.whiteTexture, buffer);
        sprite4.setSize(messageOverlay.getWidth(), 50.0f);
        sprite4.setAlpha(0.9f);
        messageOverlay.addElement(sprite4);
        StringBuilder sb = new StringBuilder(game.getActivity().getString(R.string.planet_exploration_nothing_found, new Object[]{this.who}));
        List<ResourceID> visibleResources = this.planet.getVisibleResources(game.getCurrentPlayer());
        if (!visibleResources.isEmpty() || this.explorationFind != ExplorationFind.NOTHING) {
            sb = new StringBuilder(game.getActivity().getString(R.string.planet_exploration_found, new Object[]{this.who}));
            ExplorationFind explorationFind = this.explorationFind;
            if (explorationFind != ExplorationFind.NOTHING) {
                sb.append(explorationFind.getName());
                if (!visibleResources.isEmpty()) {
                    sb.append(", ");
                }
            }
            int i2 = 1;
            for (ResourceID resourceID : visibleResources) {
                sb.append(Resources.get(resourceID).getName());
                if (i2 != visibleResources.size()) {
                    sb.append(", ");
                }
                i2++;
            }
        }
        Font font = game.fonts.smallInfoFont;
        String sb2 = sb.toString();
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text2 = new Text(0.0f, 0.0f, font, sb2, new TextOptions(horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        text2.setY(545.0f - (text2.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text2);
        if (this.explorationFind != ExplorationFind.NOTHING) {
            Sprite sprite5 = new Sprite((messageOverlay.getWidth() / 2.0f) - 175.0f, 225.0f, game.graphics.whiteTexture, buffer);
            sprite5.setSize(350.0f, 150.0f);
            sprite5.setAlpha(0.8f);
            messageOverlay.addElement(sprite5);
            Font font2 = game.fonts.smallInfoFont;
            String name2 = this.explorationFind.getName();
            TextOptions textOptions = new TextOptions(horizontalAlign);
            f2 = 0.8f;
            Text text3 = new Text(0.0f, 0.0f, font2, name2, textOptions, buffer);
            text3.setX((messageOverlay.getWidth() / 2.0f) - (text3.getWidthScaled() / 2.0f));
            text3.setY(240.0f - (text3.getHeightScaled() / 2.0f));
            messageOverlay.addElement(text3);
            game.graphics.setDiscoveryTexture(this.explorationFind.ordinal(), game.getActivity());
            messageOverlay.addElement(new Sprite((messageOverlay.getWidth() / 2.0f) - 128.0f, 255.0f, game.graphics.discoveryTexture, buffer));
            Entity display = this.explorationFind.getDisplay(game, buffer);
            display.setX((messageOverlay.getWidth() / 2.0f) - (this.explorationFind.getDisplayWidth() / 2.0f));
            display.setY(318.0f);
            messageOverlay.addElement(display);
        } else {
            f2 = 0.8f;
        }
        if (visibleResources.isEmpty()) {
            return;
        }
        int width = ((int) (messageOverlay.getWidth() / 2.0f)) - (((visibleResources.size() * 100) + ((visibleResources.size() - 1) * 25)) / 2);
        for (ResourceID resourceID2 : visibleResources) {
            int i3 = (i * 125) + width;
            Sprite sprite6 = new Sprite(i3, 395.0f, game.graphics.blackenedBackgroundTexture, buffer);
            sprite6.setAlpha(f2);
            sprite6.setSize(100.0f, 100.0f);
            messageOverlay.addElement(sprite6);
            TiledSprite tiledSprite = new TiledSprite(i3 + 20, 415.0f, game.graphics.resourceIconsTexture, buffer);
            tiledSprite.setCurrentTileIndex(resourceID2.ordinal() - 1);
            tiledSprite.setScaleCenter(0.0f, 0.0f);
            tiledSprite.setSize(60.0f, 60.0f);
            messageOverlay.addElement(tiledSprite);
            i++;
        }
    }
}
