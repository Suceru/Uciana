package com.birdshel.Uciana.Messages.GalaxyMessages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.StarSystems.Blackhole;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BlackholeMessage implements Message {
    private final Blackhole blackholeObject;

    public BlackholeMessage(Blackhole blackhole) {
        this.blackholeObject = blackhole;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Sprite sprite = new Sprite((messageOverlay.getWidth() / 2.0f) - 55.0f, 240.0f, game.graphics.blackholeTexture, buffer);
        sprite.setSize(110.0f, 110.0f);
        sprite.setFlippedHorizontal(false);
        float f2 = 0.0f;
        float f3 = 360.0f;
        if (this.blackholeObject.hasAltSpinDirection()) {
            sprite.setFlippedHorizontal(true);
        } else {
            f2 = 360.0f;
            f3 = 0.0f;
        }
        sprite.setRotationCenter(sprite.getWidthScaled() / 2.0f, sprite.getHeightScaled() / 2.0f);
        sprite.clearEntityModifiers();
        sprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(this.blackholeObject.getSpinSpeed(), f2, f3)));
        messageOverlay.addElement(sprite);
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.blackhole_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(370.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.blackhole_description, new Object[]{7}), new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
