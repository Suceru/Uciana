package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechType;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TechIcon extends Entity {
    private final Game game;
    private int size;
    private final TiledSprite techIcon;

    public TechIcon(Game game, VertexBufferObjectManager vertexBufferObjectManager, int i) {
        this.game = game;
        this.size = i;
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.techIcon = tiledSprite;
        attachChild(tiledSprite);
    }

    private void setTechIcon(Tech tech, Game game, int i) {
        TiledTextureRegion tiledTextureRegion = game.graphics.gameIconsTexture;
        if (tech.isShipComponent()) {
            tiledTextureRegion = game.graphics.shipComponentIconsTexture;
        } else if (tech.isResource() || tech.isPerk()) {
            tiledTextureRegion = game.graphics.resourceIconsTexture;
        }
        float f2 = i;
        this.techIcon.setSize(f2, f2);
        this.techIcon.setPosition(0.0f, 0.0f);
        this.techIcon.setTiledTextureRegion((ITiledTextureRegion) tiledTextureRegion);
        this.techIcon.setCurrentTileIndex(tech.getIconIndex());
    }

    private void setTroopTechIcon(int i) {
        this.techIcon.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.troops[this.game.empires.get(i).getRaceID()]);
        this.techIcon.setCurrentTileIndex(0);
    }

    public void set(Tech tech) {
        setTechIcon(tech, this.game, this.size);
    }

    public void set(int i, Tech tech) {
        setTechIcon(tech, this.game, this.size);
        if (tech.getType() == TechType.TROOP_IMPROVEMENT) {
            setTroopTechIcon(i);
        }
    }

    public TechIcon(int i, Tech tech, Game game, VertexBufferObjectManager vertexBufferObjectManager, int i2) {
        this.game = game;
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.techIcon = tiledSprite;
        attachChild(tiledSprite);
        setTechIcon(tech, game, i2);
        if (tech.getType() == TechType.TROOP_IMPROVEMENT) {
            setTroopTechIcon(i);
        }
    }
}
