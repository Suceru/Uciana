package com.birdshel.Uciana.Elements.Pools;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Planets.PlanetSprite;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Stack;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetSpritePool {
    private final VertexBufferObjectManager bufferManager;
    private final Game game;
    private final Stack<PlanetSprite> planetSprites = new Stack<>();

    public PlanetSpritePool(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        this.bufferManager = vertexBufferObjectManager;
        for (int i = 0; i < 20; i++) {
            this.planetSprites.push(new PlanetSprite(game, vertexBufferObjectManager));
        }
    }

    public PlanetSprite get() {
        if (this.planetSprites.isEmpty()) {
            return new PlanetSprite(this.game, this.bufferManager);
        }
        PlanetSprite pop = this.planetSprites.pop();
        if (pop.hasParent()) {
            pop.detachSelf();
        }
        pop.setVisible(true);
        return pop;
    }

    public void push(PlanetSprite planetSprite) {
        planetSprite.setVisible(false);
        planetSprite.detachSelf();
        this.planetSprites.push(planetSprite);
    }
}
