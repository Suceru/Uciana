package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class LowCreditsMessage implements Message {
    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite((game.getWidth() / 2.0f) - 30.0f, 270.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.WARNING.ordinal());
        messageOverlay.addElement(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(game.getWidth() / 2.0f, 270.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        messageOverlay.addElement(tiledSprite2);
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.low_credits_header);
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(horizontalAlign), buffer);
        text.setX((game.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(350.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.low_credits_description), new TextOptions(horizontalAlign), buffer);
        text2.setX((game.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        text2.setY(420.0f - (text2.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text2);
        messageOverlay.setMessageType(MessageType.CREDIT_ALERT);
        messageOverlay.addAction(MessageAction.OPEN_EMPIRE_INFO);
        messageOverlay.addAction(MessageAction.OPEN_FLEETS);
        messageOverlay.addAction(MessageAction.OPEN_COLONIES);
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
