package com.birdshel.Uciana.Messages.GalaxyMessages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Messages.MessageType;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.StarSystems.StarType;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class UnexploredMessage implements Message {
    private final int distance;
    private final int starImageIndex;
    private final StarType starType;

    public UnexploredMessage(StarType starType, int i, int i2) {
        this.starType = starType;
        this.starImageIndex = i;
        this.distance = i2;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        AnimatedSprite animatedSprite = game.starSpritePool.get();
        animatedSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 38.0f, 260.0f);
        long[] jArr = {125, 125, 125, Functions.random.nextInt(1000) + 2000};
        int i = this.starImageIndex;
        animatedSprite.animate(jArr, new int[]{i, i + 1, i + 2, i + 3}, true);
        animatedSprite.setSize(75.0f, 75.0f);
        messageOverlay.addElement(animatedSprite);
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.unexplored_distance, new Object[]{Integer.valueOf(this.distance)});
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, this.starType.getMessage(), new TextOptions(autoWrap, 1200.0f, horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        messageOverlay.setMessageType(MessageType.UNKNOWN_SYSTEM);
    }
}
