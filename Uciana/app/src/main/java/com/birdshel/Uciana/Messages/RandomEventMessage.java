package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.RandomEvents.RandomEvent;
import com.birdshel.Uciana.RandomEvents.RandomEventType;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class RandomEventMessage implements Message {
    private final int data1;
    private final int data2;
    private final int data3;
    private final int messageType;
    private final RandomEventType type;

    public RandomEventMessage(RandomEventType randomEventType, int i, int i2, int i3, int i4) {
        this.type = randomEventType;
        this.messageType = i;
        this.data1 = i2;
        this.data2 = i3;
        this.data3 = i4;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        RandomEvent randomEvent = game.randomEvents.getRandomEvent(this.type);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 240.0f, game.graphics.empireColors, buffer);
        tiledSprite.setCurrentTileIndex(3);
        tiledSprite.setSize(messageOverlay.getWidth(), 240.0f);
        tiledSprite.setAlpha(0.15f);
        messageOverlay.addElement(tiledSprite);
        messageOverlay.addElement(randomEvent.getMessage(messageOverlay, this.messageType, this.data1, this.data2, this.data3));
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
