package com.birdshel.Uciana.Messages.GalaxyMessages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class OutOfRangeMessage implements Message {
    private final int distance;
    private final int fuelRange;
    private final int starImageIndex;

    public OutOfRangeMessage(int i, int i2, int i3) {
        this.starImageIndex = i;
        this.distance = i2;
        this.fuelRange = i3;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        AnimatedSprite animatedSprite = new AnimatedSprite((messageOverlay.getWidth() / 2.0f) - 38.0f, 260.0f, game.graphics.starsTexture, buffer);
        long[] jArr = {125, 125, 125, Functions.random.nextInt(1000) + 2000};
        int i = this.starImageIndex;
        animatedSprite.animate(jArr, new int[]{i, i + 1, i + 2, i + 3}, true);
        animatedSprite.setSize(75.0f, 75.0f);
        messageOverlay.addElement(animatedSprite);
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.out_of_range_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.out_of_range_description, new Object[]{Integer.valueOf(this.distance), Integer.valueOf(this.fuelRange)}), new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
