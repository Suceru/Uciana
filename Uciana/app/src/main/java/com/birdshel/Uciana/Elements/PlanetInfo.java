package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetInfo extends Entity {
    private final TiledSprite planetClimate;
    private final TiledSprite planetClimateTerraformed;
    private final TiledSprite planetGravity;
    private final TiledSprite planetGravityAdjusted;
    private final TiledSprite planetRichness;
    private final TiledSprite planetSize;

    public PlanetInfo(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        setScaleCenter(0.0f, 0.0f);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.planetInfoTexture, vertexBufferObjectManager);
        this.planetSize = tiledSprite;
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(50.0f, 0.0f, game.graphics.planetInfoTexture, vertexBufferObjectManager);
        this.planetClimate = tiledSprite2;
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(75.0f, 32.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.planetClimateTerraformed = tiledSprite3;
        InfoIconEnum infoIconEnum = InfoIconEnum.ADJUSTED;
        tiledSprite3.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite3.setScaleCenter(0.0f, 0.0f);
        tiledSprite3.setScale(0.9f);
        attachChild(tiledSprite3);
        TiledSprite tiledSprite4 = new TiledSprite(100.0f, 0.0f, game.graphics.planetInfoTexture, vertexBufferObjectManager);
        this.planetRichness = tiledSprite4;
        attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(150.0f, 0.0f, game.graphics.planetInfoTexture, vertexBufferObjectManager);
        this.planetGravity = tiledSprite5;
        attachChild(tiledSprite5);
        TiledSprite tiledSprite6 = new TiledSprite(175.0f, 32.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.planetGravityAdjusted = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite6.setScaleCenter(0.0f, 0.0f);
        tiledSprite6.setScale(0.9f);
        attachChild(tiledSprite6);
    }

    private void setIconsInvisible() {
        this.planetSize.setVisible(false);
        this.planetClimate.setVisible(false);
        this.planetClimateTerraformed.setVisible(false);
        this.planetRichness.setVisible(false);
        this.planetGravity.setVisible(false);
        this.planetGravityAdjusted.setVisible(false);
    }

    public Sprite getClimateSprite() {
        return this.planetClimate;
    }

    public Sprite getGravitySprite() {
        return this.planetGravity;
    }

    public Sprite getRichnessSprite() {
        return this.planetRichness;
    }

    public Sprite getSizeSprite() {
        return this.planetSize;
    }

    public void set(Planet planet) {
        setIconsInvisible();
        this.planetSize.setVisible(true);
        this.planetSize.setCurrentTileIndex(planet.getSize().getInfoIconIndex());
        this.planetClimate.setVisible(true);
        this.planetClimate.setCurrentTileIndex(planet.getClimate().getInfoIconIndex());
        if (planet.isTerraformed()) {
            this.planetClimateTerraformed.setVisible(true);
        }
        this.planetRichness.setVisible(true);
        this.planetRichness.setCurrentTileIndex(planet.getMineralRichness().getInfoIconIndex());
        this.planetGravity.setVisible(true);
        this.planetGravity.setCurrentTileIndex(planet.getGravity().getInfoIconIndex());
        if (planet.hasAdjustedGravity()) {
            this.planetGravityAdjusted.setVisible(true);
        }
    }

    public void set(AsteroidBelt asteroidBelt) {
        setIconsInvisible();
        this.planetRichness.setCurrentTileIndex(asteroidBelt.getMineralRichness().ordinal() + 29);
        this.planetRichness.setVisible(true);
    }

    public void set() {
        setIconsInvisible();
    }
}
