package com.birdshel.Uciana.Messages.GalaxyMessages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SpaceRiftMessage implements Message {
    private final SpaceRift spaceRiftObject;

    public SpaceRiftMessage(SpaceRift spaceRift) {
        this.spaceRiftObject = spaceRift;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 50.0f, 245.0f, game.graphics.gameIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(GameIconEnum.SPACE_RIFT.ordinal());
        tiledSprite.setSize(100.0f, 100.0f);
        tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
        tiledSprite.setFlippedHorizontal(false);
        float f2 = 360.0f;
        float f3 = 0.0f;
        if (this.spaceRiftObject.hasAltSpinDirection()) {
            tiledSprite.setFlippedHorizontal(true);
        } else {
            f2 = 0.0f;
            f3 = 360.0f;
        }
        tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
        tiledSprite.clearEntityModifiers();
        tiledSprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(this.spaceRiftObject.getSpinSpeed(), f2, f3)));
        messageOverlay.addElement(tiledSprite);
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.spacial_rift_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(370.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.spacial_rift_description, new Object[]{7}), new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
