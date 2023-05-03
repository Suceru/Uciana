package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Ships.ShipType;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AscendedEncounterMessage implements Message {
    private final int systemID;

    public AscendedEncounterMessage(int i) {
        this.systemID = i;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 50.0f, 245.0f, game.graphics.shipsTextures[8], buffer);
        tiledSprite.setSize(100.0f, 100.0f);
        tiledSprite.setCurrentTileIndex(ShipType.BATTLESHIP.getIcon(8));
        messageOverlay.addElement(tiledSprite);
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.ascended_encounter_header);
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(370.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.ascended_encounter_description, new Object[]{game.galaxy.getStarSystem(this.systemID).getName()}), new TextOptions(AutoWrap.WORDS, 1200.0f, horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
