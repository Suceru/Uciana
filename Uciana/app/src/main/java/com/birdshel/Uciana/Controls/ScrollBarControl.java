package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Game;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ScrollBarControl extends Entity {
    private float height;
    private int itemCount;
    private final int itemHeight;
    private final Sprite scrollBarBackground;
    private final TiledSprite scrollBarVisible1;
    private final TiledSprite scrollBarVisible2;

    public ScrollBarControl(float f2, float f3, int i, float f4, Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        setPosition(f2, f3);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.scrollBarBackground = sprite;
        sprite.setSize(10.0f, f4);
        attachChild(sprite);
        TiledSprite tiledSprite = new TiledSprite(2.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.scrollBarVisible1 = tiledSprite;
        tiledSprite.setCurrentTileIndex(3);
        tiledSprite.setWidth(6.0f);
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(2.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.scrollBarVisible2 = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(3);
        tiledSprite2.setWidth(6.0f);
        attachChild(tiledSprite2);
        this.itemHeight = i;
        this.height = f4;
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setAlpha(float f2) {
        this.scrollBarBackground.setAlpha(f2);
        this.scrollBarVisible1.setAlpha(f2);
        this.scrollBarVisible2.setAlpha(f2);
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void update(float f2, int i) {
        this.itemCount = i;
        update(f2);
    }

    public void update(float f2) {
        this.scrollBarVisible1.setVisible(false);
        this.scrollBarVisible2.setVisible(false);
        this.scrollBarBackground.setVisible(false);
        float f3 = this.itemCount * this.itemHeight;
        if (f3 <= this.height) {
            return;
        }
        this.scrollBarVisible1.setVisible(true);
        this.scrollBarVisible2.setVisible(true);
        this.scrollBarBackground.setVisible(true);
        float f4 = this.height;
        float f5 = f4 * (f4 / f3);
        this.scrollBarVisible1.setHeight(f5);
        this.scrollBarVisible2.setHeight(f5);
        float y = this.height * (((f2 - getY()) * (-1.0f)) / f3);
        this.scrollBarVisible1.setY(y);
        this.scrollBarVisible2.setY(y);
    }
}
