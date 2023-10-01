package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class WormholeMessage implements Message {
    private final boolean discovered;

    public WormholeMessage(boolean z) {
        this.discovered = z;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 55.0f, 240.0f, game.graphics.gameIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(GameIconEnum.BOTTOM_WORMHOLE_LAYER.ordinal());
        tiledSprite.setSize(110.0f, 110.0f);
        tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
        tiledSprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        messageOverlay.addElement(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 55.0f, 240.0f, game.graphics.gameIconsTexture, buffer);
        tiledSprite2.setCurrentTileIndex(GameIconEnum.TOP_WORMHOLE_LAYER.ordinal());
        tiledSprite2.setAlpha(0.9f);
        tiledSprite2.setSize(110.0f, 110.0f);
        tiledSprite2.setRotationCenter(tiledSprite2.getWidthScaled() / 2.0f, tiledSprite2.getHeightScaled() / 2.0f);
        tiledSprite2.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        messageOverlay.addElement(tiledSprite2);
        String string = game.getActivity().getString(R.string.wormhole_header);
        if (this.discovered) {
            string = game.getActivity().getString(R.string.wormhole_header_found);
        }
        Font font = game.fonts.infoFont;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.wormhole_description), new TextOptions(horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
