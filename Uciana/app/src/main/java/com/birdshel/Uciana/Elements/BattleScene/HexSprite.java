package com.birdshel.Uciana.Elements.BattleScene;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.GameIconEnum;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class HexSprite {
    private final TiledSprite attackGrid;
    private final TiledSprite attackHighlightedGrid;
    private final TiledSprite displayGrid;
    private final TiledSprite moveGrid;
    private final TiledSprite specialGrid;
    private final TiledSprite specialHighlightedGrid;

    public HexSprite(Game game, Scene scene, VertexBufferObjectManager vertexBufferObjectManager, float f2, float f3) {
        TiledSprite tiledSprite = new TiledSprite(f2, f3, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.displayGrid = tiledSprite;
        tiledSprite.setCurrentTileIndex(GameIconEnum.HEX_GRID.ordinal());
        tiledSprite.setAlpha(0.5f);
        tiledSprite.setVisible(false);
        tiledSprite.setZIndex(2);
        tiledSprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier((Functions.random.nextFloat() * 1.5f) + 0.5f, 0.6f, 0.1f), new AlphaModifier((Functions.random.nextFloat() * 1.5f) + 0.5f, 0.1f, 0.1f), new AlphaModifier((Functions.random.nextFloat() * 1.5f) + 0.5f, 0.1f, 0.6f), new AlphaModifier((Functions.random.nextFloat() * 1.5f) + 0.5f, 0.6f, 0.6f))));
        scene.attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(f2, f3, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.moveGrid = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(GameIconEnum.MOVE_HEX_GRID.ordinal());
        tiledSprite2.setAlpha(0.75f);
        tiledSprite2.setVisible(false);
        tiledSprite2.setZIndex(2);
        scene.attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(f2, f3, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.attackGrid = tiledSprite3;
        GameIconEnum gameIconEnum = GameIconEnum.ATTACK_HEX_GRID;
        tiledSprite3.setCurrentTileIndex(gameIconEnum.ordinal());
        tiledSprite3.setAlpha(0.75f);
        tiledSprite3.setVisible(false);
        tiledSprite3.setZIndex(2);
        scene.attachChild(tiledSprite3);
        AlphaModifier alphaModifier = new AlphaModifier(0.8f, 0.4f, 1.0f);
        AlphaModifier alphaModifier2 = new AlphaModifier(0.8f, 1.0f, 0.4f);
        TiledSprite tiledSprite4 = new TiledSprite(f2, f3, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.attackHighlightedGrid = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(gameIconEnum.ordinal());
        tiledSprite4.setAlpha(0.25f);
        tiledSprite4.setVisible(false);
        tiledSprite4.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(alphaModifier, alphaModifier2)));
        tiledSprite4.setZIndex(2);
        scene.attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(f2, f3, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.specialGrid = tiledSprite5;
        GameIconEnum gameIconEnum2 = GameIconEnum.SPECIAL_HEX_GRID;
        tiledSprite5.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite5.setAlpha(0.75f);
        tiledSprite5.setVisible(false);
        tiledSprite5.setZIndex(2);
        scene.attachChild(tiledSprite5);
        TiledSprite tiledSprite6 = new TiledSprite(f2, f3, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.specialHighlightedGrid = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite6.setAlpha(0.25f);
        tiledSprite6.setVisible(false);
        tiledSprite6.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(alphaModifier, alphaModifier2)));
        tiledSprite6.setZIndex(2);
        scene.attachChild(tiledSprite6);
    }

    public Point getPosition() {
        return new Point(this.displayGrid.getX(), this.displayGrid.getY());
    }

    public float getX() {
        return this.displayGrid.getX();
    }

    public float getY() {
        return this.displayGrid.getY();
    }

    public void setAttackGrid(boolean z) {
        this.attackGrid.setVisible(z);
    }

    public void setAttackHighlightedGrid(boolean z) {
        this.attackHighlightedGrid.setVisible(z);
    }

    public void setDisplayGrid(boolean z) {
        this.displayGrid.setVisible(z);
    }

    public void setMoveGrid(boolean z) {
        this.moveGrid.setVisible(z);
    }

    public void setSpecialGrid(boolean z) {
        this.specialGrid.setVisible(z);
    }

    public void setSpecialHighlightedGrid(boolean z) {
        this.specialHighlightedGrid.setVisible(z);
    }
}
