package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.ComponentIconEnum;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipSprite extends Entity {
    protected Entity entity;
    protected TiledSprite tiledSprite;
    protected TiledSprite tiledSprite2;
    private float maxSize;
    protected Game y;
    protected CharSequence z = CharBuffer.wrap(new char[255]);
    protected TextOptions A = new TextOptions(HorizontalAlign.CENTER);
    private final TiledSprite[] thrusters = new TiledSprite[3];

    public ShipSprite(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.y = game;
        Entity entity = new Entity();
        this.entity = entity;
        attachChild(entity);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.shipsTextures[0], vertexBufferObjectManager);
        this.tiledSprite = tiledSprite;
        tiledSprite.setZIndex(1);
        this.tiledSprite.setVisible(false);
        this.entity.attachChild(this.tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.tiledSprite2 = tiledSprite2;
        tiledSprite2.setZIndex(1);
        this.tiledSprite2.setVisible(false);
        this.entity.attachChild(this.tiledSprite2);
        for (int i = 0; i < 3; i++) {
            TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager);
            tiledSprite3.setScaleCenter(0.0f, 0.0f);
            tiledSprite3.setCurrentTileIndex(ComponentIconEnum.TRANSPHASIC_TORPEDO.ordinal());
            tiledSprite3.setZIndex(0);
            tiledSprite3.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
            tiledSprite3.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.2f, 0.6f, 1.0f), new AlphaModifier(0.2f, 1.0f, 0.6f))));
            this.thrusters[i] = tiledSprite3;
            this.entity.attachChild(tiledSprite3);
        }
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public float getAlpha() {
        return this.tiledSprite.getAlpha();
    }

    public int getCurrentTileIndex() {
        return this.tiledSprite.getCurrentTileIndex();
    }

    public float getPlacementX() {
        if (this.tiledSprite.isVisible()) {
            return this.tiledSprite.getX();
        }
        return this.tiledSprite2.getX();
    }

    public float getPlacementY() {
        if (this.tiledSprite.isVisible()) {
            return this.tiledSprite.getY();
        }
        return this.tiledSprite2.getY();
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public float getRotation() {
        return this.entity.getRotation();
    }

    public float getSize() {
        return this.maxSize;
    }

    public void hideShipImage() {
        this.tiledSprite.setVisible(false);
        this.tiledSprite2.setVisible(false);
        hideThrusters();
    }

    public void hideThrusters() {
        for (TiledSprite tiledSprite : this.thrusters) {
            tiledSprite.setVisible(false);
        }
    }

    @Override // org.andengine.entity.Entity, org.andengine.engine.handler.IUpdateHandler
    public void reset() {
        this.maxSize = 145.0f;
        this.tiledSprite.setSize(145.0f, 145.0f);
        Entity entity = this.entity;
        float f2 = this.maxSize;
        entity.setRotationCenter(f2 / 2.0f, f2 / 2.0f);
        setScale(1.0f);
        setVisible(true);
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setAlpha(float f2) {
        this.tiledSprite.setAlpha(f2);
        for (TiledSprite tiledSprite : this.thrusters) {
            tiledSprite.setAlpha(f2);
        }
    }

    public void setCurrentTileIndex(int i) {
        this.tiledSprite.setVisible(true);
        this.tiledSprite.setCurrentTileIndex(i);
    }

    public int setFleetIconForGalaxy(Fleet fleet) {
        float shipSize = this.y.galaxy.getSize().getShipSize();
        Object[] largestShipTypeAndDesign = fleet.getLargestShipTypeAndDesign();
        int intValue = ((Integer) largestShipTypeAndDesign[1]).intValue();
        setShipIcon(fleet.empireID, (ShipType) largestShipTypeAndDesign[0], intValue, shipSize, shipSize, fleet.getDirection(), fleet.isMoving());
        return intValue;
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setRotation(float f2) {
        this.entity.setRotation(f2);
    }

    public void setShip(Fleet fleet, Ship ship, float f2, float f3) {
        setShip(ship, f2, f3, fleet.getDirection(), fleet.isMoving());
    }

    public void setShipIcon(int i, ShipType shipType, float f2, float f3, int i2, boolean z) {
        setShipIcon(i, shipType, 0, f2, f3, i2, z);
    }

    public void setSize(float f2) {
        setScale(f2 / 145.0f);
    }

    public void setTextureRegion(ITiledTextureRegion iTiledTextureRegion) {
        this.tiledSprite.setTiledTextureRegion(iTiledTextureRegion);
    }

    public void setShip(Ship ship, float f2, float f3, int i, boolean z) {
        setShipIcon(ship.getEmpireID(), ship.getShipType(), ship.getHullDesign(), f2, f3, i, z);
    }

    public void setShipIcon(int i, ShipType shipType, int i2, float f2, float f3, int i3, boolean z) {
        setShipIcon(i, (i == 8 || i == 9 || this.y.empires.getEmpires().isEmpty()) ? i : this.y.empires.get(i).getShipStyleID(), shipType, i2, f2, f3, i3, z);
    }

    public void setShipIcon(int i, int i2, ShipType shipType, int i3, float f2, float f3, int i4, boolean z) {
        this.maxSize = f2;
        float f4 = f2 / 2.0f;
        this.entity.setRotationCenter(f4, f4);
        float f5 = f3 / 145.0f;
        int i5 = 0;
        this.tiledSprite2.setVisible(false);
        this.tiledSprite.setVisible(false);
        if (shipType.isStation()) {
            this.tiledSprite2.setVisible(true);
            this.tiledSprite2.setCurrentTileIndex(shipType.getIcon(i));
            this.tiledSprite2.setSize(f3, f3);
            float f6 = f4 - (f3 / 2.0f);
            this.tiledSprite2.setPosition(f6, f6);
        } else {
            this.tiledSprite.setVisible(true);
            this.tiledSprite.setTiledTextureRegion((ITiledTextureRegion) this.y.graphics.shipsTextures[i]);
            this.tiledSprite.setCurrentTileIndex(shipType.getIcon(i, i3));
            this.tiledSprite.setSize(f3, f3);
            float f7 = f4 - (f3 / 2.0f);
            this.tiledSprite.setPosition(f7, f7);
        }
        for (TiledSprite tiledSprite : this.thrusters) {
            tiledSprite.setVisible(false);
        }
        if (z && !this.y.graphics.isShipsModded(i)) {
            for (Point point : ThrusterPoints.getThrusters(i2, shipType, i3)) {
                float x = this.tiledSprite.getX() + ((point.getX() + 20.0f) * f5);
                float y = this.tiledSprite.getY() + ((point.getY() + 25.0f) * f5);
                this.thrusters[i5].setVisible(true);
                this.thrusters[i5].setPosition(x, y);
                this.thrusters[i5].setScale(f5);
                i5++;
            }
        }
        this.entity.setRotation(0.0f);
        if (i4 == 0) {
            this.entity.setRotation(180.0f);
        }
        this.entity.sortChildren();
    }
}
