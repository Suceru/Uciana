package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Manufacturing;
import com.birdshel.Uciana.Game;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ProductionPercentBar extends Entity {
    private final TiledSprite addedProductionBar;
    private Colony colony;
    private final TiledSprite emptyProductionBar;
    private final TiledSprite finishedProductionBar;
    private float width;

    public ProductionPercentBar(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        this.width = f2;
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.productionPercentBarTexture, vertexBufferObjectManager);
        this.emptyProductionBar = tiledSprite;
        tiledSprite.setCurrentTileIndex(2);
        tiledSprite.setWidth(f2);
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.productionPercentBarTexture, vertexBufferObjectManager);
        this.finishedProductionBar = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(0);
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.productionPercentBarTexture, vertexBufferObjectManager);
        this.addedProductionBar = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(1);
        tiledSprite3.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.8f, 0.7f, 1.0f), new AlphaModifier(0.8f, 1.0f, 0.7f))));
        attachChild(tiledSprite3);
    }

    public void hide() {
        this.emptyProductionBar.setVisible(false);
        this.finishedProductionBar.setVisible(false);
        this.addedProductionBar.setVisible(false);
    }

    public void set(Colony colony) {
        this.colony = colony;
        update();
    }

    public void setWidth(float f2) {
        this.width = f2;
        this.emptyProductionBar.setWidth(f2);
        update();
    }

    public void update() {
        Manufacturing manufacturing = this.colony.getManufacturing();
        this.emptyProductionBar.setVisible(true);
        if (manufacturing.showGreyProgressBar()) {
            this.emptyProductionBar.setCurrentTileIndex(3);
            this.addedProductionBar.setVisible(false);
            this.finishedProductionBar.setVisible(false);
            return;
        }
        this.finishedProductionBar.setVisible(true);
        this.addedProductionBar.setVisible(true);
        this.emptyProductionBar.setCurrentTileIndex(2);
        int percentDone = manufacturing.getPercentDone();
        float f2 = this.width * (percentDone / 100.0f);
        this.finishedProductionBar.setWidth(f2);
        this.addedProductionBar.setX(this.emptyProductionBar.getX() + f2);
        int productionPerTurn = (int) ((this.colony.getProductionPerTurn() / manufacturing.getRequiredProduction()) * 100.0f);
        if (percentDone + productionPerTurn > 100) {
            productionPerTurn = 100 - percentDone;
        }
        this.addedProductionBar.setWidth(this.width * (productionPerTurn / 100.0f));
    }
}
