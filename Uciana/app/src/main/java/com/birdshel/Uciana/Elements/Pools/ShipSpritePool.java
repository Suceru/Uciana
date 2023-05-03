package com.birdshel.Uciana.Elements.Pools;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Ships.ShipSprite;
import java.util.Stack;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipSpritePool {
    private final VertexBufferObjectManager bufferManager;
    private final Game game;
    private final Stack<ShipSprite> shipSprites = new Stack<>();

    public ShipSpritePool(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        this.bufferManager = vertexBufferObjectManager;
        for (int i = 0; i < 50; i++) {
            this.shipSprites.push(new ShipSprite(game, vertexBufferObjectManager));
        }
    }

    public ShipSprite get() {
        if (this.shipSprites.isEmpty()) {
            return new ShipSprite(this.game, this.bufferManager);
        }
        ShipSprite pop = this.shipSprites.pop();
        pop.setVisible(true);
        pop.setAlpha(1.0f);
        pop.setScale(1.0f);
        return pop;
    }

    public void push(ShipSprite shipSprite) {
        shipSprite.reset();
        shipSprite.setVisible(false);
        this.shipSprites.push(shipSprite);
    }
}
