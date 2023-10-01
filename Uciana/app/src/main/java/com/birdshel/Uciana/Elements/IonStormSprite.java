package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.IonStorm;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class IonStormSprite extends Entity {
    private final TiledSprite[] ionClouds = new TiledSprite[2];
    private final TiledSprite[] ionFlashes = new TiledSprite[4];

    public IonStormSprite(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        for (int i = 0; i < 2; i++) {
            this.ionClouds[i] = new TiledSprite(0.0f, 0.0f, game.graphics.ionCloudsTexture, vertexBufferObjectManager);
            this.ionClouds[i].setIgnoreUpdate(true);
            TiledSprite[] tiledSpriteArr = this.ionClouds;
            tiledSpriteArr[i].setRotationCenter(tiledSpriteArr[i].getWidthScaled() / 2.0f, this.ionClouds[i].getHeightScaled() / 2.0f);
            attachChild(this.ionClouds[i]);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.ionFlashes[i2] = new TiledSprite(0.0f, 0.0f, game.graphics.ionFlashesTexture, vertexBufferObjectManager);
            attachChild(this.ionFlashes[i2]);
            startIonFlashEffect(this.ionFlashes[i2], (Functions.random.nextFloat() * 3.0f) + 2.0f);
        }
    }

    private void startIonFlashEffect(TiledSprite tiledSprite, float f2) {
        tiledSprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        tiledSprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.25f, 0.0f, 1.0f), new AlphaModifier(0.25f, 1.0f, 0.0f), new AlphaModifier(f2, 0.0f, 0.0f))));
    }

    public void set(IonStorm ionStorm) {
        for (int i = 0; i < 2; i++) {
            this.ionClouds[i].setCurrentTileIndex(ionStorm.getLayer(i));
            this.ionClouds[i].setPosition(ionStorm.getPosition().getX() + ionStorm.getPositionOfCloud(i).getX(), ionStorm.getPosition().getY() + ionStorm.getPositionOfCloud(i).getY());
            this.ionClouds[i].setAlpha(ionStorm.getAlpha(i));
            this.ionClouds[i].setRotation(ionStorm.getRotation(i));
        }
        for (int i2 = 0; i2 < 2; i2++) {
            for (int i3 = 0; i3 < 2; i3++) {
                float x = ionStorm.getPosition().getX() + ionStorm.getPositionOfCloud(i2).getX();
                float y = ionStorm.getPosition().getY() + ionStorm.getPositionOfCloud(i2).getY();
                int i4 = (i2 * 2) + i3;
                this.ionFlashes[i4].setX(Functions.random.nextInt(75) + x);
                this.ionFlashes[i4].setY(Functions.random.nextInt(75) + y);
                this.ionFlashes[i4].setCurrentTileIndex(Functions.random.nextInt(6));
                this.ionFlashes[i4].setX(x + Functions.random.nextInt(75));
                this.ionFlashes[i4].setY(y + Functions.random.nextInt(75));
                this.ionFlashes[i4].setCurrentTileIndex(Functions.random.nextInt(6));
            }
        }
    }
}
