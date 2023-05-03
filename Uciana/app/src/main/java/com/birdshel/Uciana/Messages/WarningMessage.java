package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class WarningMessage implements Message {
    private final int warning;

    public WarningMessage(int i) {
        this.warning = i;
    }

    private void blinkSprite(Sprite sprite) {
        sprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        sprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.4f, 0.25f, 1.0f), new AlphaModifier(0.4f, 1.0f, 0.25f))));
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        String str;
        String str2;
        String string;
        String string2;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 23.0f, 270.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(this.warning);
        tiledSprite.setSize(45.0f, 45.0f);
        blinkSprite(tiledSprite);
        messageOverlay.addElement(tiledSprite);
        if (this.warning == InfoIconEnum.STARVING.ordinal()) {
            string = game.getActivity().getString(R.string.warning_starving_title);
            string2 = game.getActivity().getString(R.string.warning_starving_description);
        } else if (this.warning == InfoIconEnum.BLOCKADE.ordinal()) {
            string = game.getActivity().getString(R.string.warning_blockade_title);
            string2 = game.getActivity().getString(R.string.warning_blockade_description);
        } else if (this.warning == InfoIconEnum.POWER_WARNING.ordinal()) {
            string = game.getActivity().getString(R.string.warning_power_title);
            string2 = game.getActivity().getString(R.string.warning_power_description);
        } else if (this.warning == InfoIconEnum.EMPTY_COLONY_WARNING.ordinal()) {
            string = game.getActivity().getString(R.string.warning_empty_colony_title);
            string2 = game.getActivity().getString(R.string.warning_empty_colony_description);
        } else {
            str = "";
            str2 = str;
            Font font = game.fonts.infoFont;
            HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
            Text text = new Text(0.0f, 0.0f, font, str, new TextOptions(horizontalAlign), buffer);
            text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
            text.setY(360.0f - (text.getHeightScaled() / 2.0f));
            messageOverlay.addElement(text);
            Text text2 = new Text(0.0f, 390.0f, game.fonts.smallInfoFont, str2, new TextOptions(horizontalAlign), buffer);
            text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
            messageOverlay.addElement(text2);
        }
        str = string;
        str2 = string2;
        Font font2 = game.fonts.infoFont;
        HorizontalAlign horizontalAlign2 = HorizontalAlign.CENTER;
        Text text3 = new Text(0.0f, 0.0f, font2, str, new TextOptions(horizontalAlign2), buffer);
        text3.setX((messageOverlay.getWidth() / 2.0f) - (text3.getWidthScaled() / 2.0f));
        text3.setY(360.0f - (text3.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text3);
        Text text22 = new Text(0.0f, 390.0f, game.fonts.smallInfoFont, str2, new TextOptions(horizontalAlign2), buffer);
        text22.setX((messageOverlay.getWidth() / 2.0f) - (text22.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text22);
    }
}
