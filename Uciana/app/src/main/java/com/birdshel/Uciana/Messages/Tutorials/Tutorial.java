package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Tutorial implements Message {

    /* renamed from: a  reason: collision with root package name */
    protected Game f1374a;
    protected VertexBufferObjectManager b;

    /* renamed from: c  reason: collision with root package name */
    protected TextOptions f1375c = new TextOptions(HorizontalAlign.CENTER);

    /* renamed from: d  reason: collision with root package name */
    protected Sprite f1376d;

    /* renamed from: e  reason: collision with root package name */
    protected Sprite f1377e;

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        this.f1374a = messageOverlay.getGame();
        this.b = messageOverlay.getBuffer();
        Sprite messageBackground = messageOverlay.getMessageBackground();
        messageBackground.setY(200.0f);
        messageBackground.setHeight(320.0f);
        Sprite sprite = new Sprite(0.0f, 200.0f, this.f1374a.graphics.popEmptyTexture, this.b);
        this.f1376d = sprite;
        sprite.setSize(400.0f, 60.0f);
        this.f1376d.setAlpha(0.6f);
        messageOverlay.addElement(this.f1376d);
        Sprite sprite2 = new Sprite(0.0f, 200.0f, this.f1374a.graphics.productionBarTexture, this.b);
        this.f1377e = sprite2;
        sprite2.setSize(400.0f, 60.0f);
        this.f1377e.setAlpha(0.6f);
        this.f1377e.setVisible(false);
        messageOverlay.addElement(this.f1377e);
        TiledSprite tiledSprite = new TiledSprite(10.0f, 215.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.INFO.ordinal());
        messageOverlay.addElement(tiledSprite);
    }
}
