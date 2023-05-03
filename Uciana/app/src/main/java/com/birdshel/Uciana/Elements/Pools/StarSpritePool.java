package com.birdshel.Uciana.Elements.Pools;

import com.birdshel.Uciana.Game;
import java.util.Stack;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class StarSpritePool {
    private final VertexBufferObjectManager bufferManager;
    private final Game game;
    private final Stack<AnimatedSprite> stars = new Stack<>();

    public StarSpritePool(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        this.bufferManager = vertexBufferObjectManager;
        for (int i = 0; i < 52; i++) {
            AnimatedSprite animatedSprite = new AnimatedSprite(0.0f, 0.0f, game.graphics.starsTexture, vertexBufferObjectManager);
            animatedSprite.setScaleCenter(0.0f, 0.0f);
            this.stars.push(animatedSprite);
        }
    }

    public AnimatedSprite get() {
        if (this.stars.isEmpty()) {
            AnimatedSprite animatedSprite = new AnimatedSprite(0.0f, 0.0f, this.game.graphics.starsTexture, this.bufferManager);
            animatedSprite.setScaleCenter(0.0f, 0.0f);
            return animatedSprite;
        }
        AnimatedSprite pop = this.stars.pop();
        pop.setVisible(true);
        return pop;
    }

    public void push(AnimatedSprite animatedSprite) {
        animatedSprite.setVisible(false);
        animatedSprite.setSize(35.0f, 35.0f);
        animatedSprite.setScale(1.0f);
        animatedSprite.setAlpha(1.0f);
        this.stars.push(animatedSprite);
    }
}
