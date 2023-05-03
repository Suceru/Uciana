package com.birdshel.Uciana.Elements.Pools;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Ships.ShipSpriteBattle;
import java.util.Stack;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipSpriteBattlePool {
    private final VertexBufferObjectManager bufferManager;
    private final Game game;
    private final Stack<ShipSpriteBattle> shipSprites = new Stack<>();

    public ShipSpriteBattlePool(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        this.bufferManager = vertexBufferObjectManager;
        for (int i = 0; i < 47; i++) {
            this.shipSprites.push(new ShipSpriteBattle(game, vertexBufferObjectManager));
        }
    }

    public ShipSpriteBattle get() {
        if (this.shipSprites.isEmpty()) {
            return new ShipSpriteBattle(this.game, this.bufferManager);
        }
        ShipSpriteBattle pop = this.shipSprites.pop();
        pop.setVisible(true);
        pop.setAlpha(1.0f);
        pop.setScale(1.0f);
        return pop;
    }

    public void push(ShipSpriteBattle shipSpriteBattle) {
        shipSpriteBattle.reset();
        shipSpriteBattle.setVisible(false);
        this.shipSprites.push(shipSpriteBattle);
    }
}
