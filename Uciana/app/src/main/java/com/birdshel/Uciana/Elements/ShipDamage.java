package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipDamage extends TiledSprite {
    private boolean shouldBeShown;

    public ShipDamage(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(0.0f, 0.0f, game.graphics.bombDamageTexture, vertexBufferObjectManager);
        setCurrentTileIndex(Functions.random.nextInt(8));
        setScaleCenter(0.0f, 0.0f);
        setZIndex(2);
        setVisible(false);
        setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        float nextFloat = (Functions.random.nextFloat() * 0.7f) + 0.3f;
        registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(nextFloat, 0.6f, 1.0f), new AlphaModifier(nextFloat, 1.0f, 0.6f))));
    }

    public void setShouldBeShown(boolean z) {
        this.shouldBeShown = z;
        if (z) {
            return;
        }
        setVisible(false);
    }

    public boolean shouldBeShown() {
        return this.shouldBeShown;
    }
}
