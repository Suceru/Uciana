package com.birdshel.Uciana.Messages.PlanetInfo;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.MineralRichness;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.R;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.text.DecimalFormat;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class RichnessInfoMessage implements Message {
    private final boolean isOnPlanet = false;
    private final MineralRichness mineralRichness;

    public RichnessInfoMessage(Planet planet) {
        this.mineralRichness = planet.getMineralRichness();
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        DecimalFormat decimalFormat = new DecimalFormat("##.#");
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 25.0f, 270.0f, game.graphics.planetInfoTexture, buffer);
        tiledSprite.setCurrentTileIndex(this.mineralRichness.getInfoIconIndex());
        tiledSprite.setSize(50.0f, 50.0f);
        messageOverlay.addElement(tiledSprite);
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.richness_info_header, new Object[]{this.mineralRichness.getDisplayName()});
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, this.isOnPlanet ? game.getActivity().getString(R.string.richness_info_description, new Object[]{decimalFormat.format(this.mineralRichness.getProductionPerWorker())}) : "", new TextOptions(AutoWrap.WORDS, 1200.0f, horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }

    public RichnessInfoMessage(AsteroidBelt asteroidBelt) {
        this.mineralRichness = asteroidBelt.getMineralRichness();
    }
}
