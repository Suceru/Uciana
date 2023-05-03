package com.birdshel.Uciana.Messages.PlanetInfo;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Gravity;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GravityInfoMessage implements Message {
    private final Gravity gravity;
    private final boolean hasAdjustedGravity;

    public GravityInfoMessage(Gravity gravity, boolean z) {
        this.gravity = gravity;
        this.hasAdjustedGravity = z;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 25.0f, 270.0f, game.graphics.planetInfoTexture, buffer);
        tiledSprite.setCurrentTileIndex(this.gravity.getInfoIconIndex());
        tiledSprite.setSize(50.0f, 50.0f);
        messageOverlay.addElement(tiledSprite);
        if (this.hasAdjustedGravity) {
            TiledSprite tiledSprite2 = new TiledSprite(messageOverlay.getWidth() / 2.0f, 302.0f, game.graphics.infoIconsTexture, buffer);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.ADJUSTED.ordinal());
            tiledSprite2.setScaleCenter(0.0f, 0.0f);
            tiledSprite2.setScale(0.9f);
            messageOverlay.addElement(tiledSprite2);
        }
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.gravity_info_header, new Object[]{this.gravity.getDisplayName()});
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        String str = "";
        if (this.hasAdjustedGravity) {
            str = "" + game.getActivity().getString(R.string.gravity_info_normalized);
        }
        Gravity gravity = this.gravity;
        if (gravity == Gravity.NORMAL) {
            str = str + game.getActivity().getString(R.string.gravity_info_normal);
        } else if (gravity == Gravity.LOW) {
            str = str + game.getActivity().getString(R.string.gravity_info_low);
        } else if (gravity == Gravity.HIGH) {
            str = str + game.getActivity().getString(R.string.gravity_info_high);
        }
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, str, new TextOptions(horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
