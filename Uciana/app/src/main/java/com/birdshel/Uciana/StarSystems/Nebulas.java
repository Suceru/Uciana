package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Nebulas extends Entity {
    private final Game game;
    private final List<TiledSprite> nebulas = new ArrayList();

    public Nebulas(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        for (int i = 0; i < 7; i++) {
            TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.nebulaTexture, vertexBufferObjectManager) { // from class: com.birdshel.Uciana.StarSystems.Nebulas.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // org.andengine.entity.sprite.Sprite, org.andengine.entity.shape.Shape, org.andengine.entity.Entity
                public void k(GLState gLState, Camera camera) {
                    super.k(gLState, camera);
                    gLState.enableDither();
                }
            };
            tiledSprite.setIgnoreUpdate(true);
            tiledSprite.setVisible(false);
            attachChild(tiledSprite);
            this.nebulas.add(tiledSprite);
        }
    }

    private void setNebula(TiledSprite tiledSprite, Nebula nebula) {
        tiledSprite.setCurrentTileIndex(nebula.getNebulaType().ordinal());
        tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
        tiledSprite.setRotation(nebula.getRotation());
        tiledSprite.setVisible(true);
    }

    public void resetSprites() {
        for (TiledSprite tiledSprite : this.nebulas) {
            tiledSprite.setVisible(false);
            tiledSprite.setSize(300.0f, 300.0f);
        }
    }

    public void set() {
        set(0.0f);
    }

    public void setFromSave(List<Nebula> list) {
        resetSprites();
        int i = 0;
        for (Nebula nebula : list) {
            Point position = nebula.getPosition();
            TiledSprite tiledSprite = this.nebulas.get(i);
            tiledSprite.setPosition(position.getX(), position.getY());
            tiledSprite.setScaleCenter(0.0f, 0.0f);
            tiledSprite.setScale(nebula.getSizeModifier());
            tiledSprite.setAlpha(1.0f);
            setNebula(tiledSprite, nebula);
            i++;
        }
    }

    public void setRandomGalaxy() {
        resetSprites();
        int nextInt = Functions.random.nextInt(6) + 2;
        for (int i = 0; i < nextInt; i++) {
            this.nebulas.get(i).setPosition(Functions.random.nextInt(1100), Functions.random.nextInt(600));
            this.nebulas.get(i).setCurrentTileIndex(NebulaType.getRandom().ordinal());
            this.nebulas.get(i).setScale(Functions.random.nextFloat() + 0.5f);
            this.nebulas.get(i).setRotationCenter(this.nebulas.get(i).getWidthScaled() / 2.0f, this.nebulas.get(i).getHeightScaled() / 2.0f);
            this.nebulas.get(i).setRotation(Functions.random.nextInt(4) * 90);
            this.nebulas.get(i).setVisible(true);
        }
    }

    public void setRandomSystem() {
        resetSprites();
        HashMap hashMap = new HashMap();
        hashMap.put(0, 15);
        hashMap.put(1, 55);
        hashMap.put(2, 30);
        int intValue = ((Integer) Functions.getItemByPercent(hashMap)).intValue();
        for (int i = 0; i < intValue; i++) {
            int nextInt = Functions.random.nextInt(NebulaType.values().length);
            TiledSprite tiledSprite = this.nebulas.get(i);
            tiledSprite.setPosition(50.0f, -150.0f);
            tiledSprite.setSize(1300.0f, 1200.0f);
            tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
            tiledSprite.setAlpha(0.9f);
            tiledSprite.setCurrentTileIndex(nextInt);
            tiledSprite.setRotation(Functions.random.nextInt(4) * 90);
            tiledSprite.setVisible(true);
        }
    }

    public void set(float f2) {
        resetSprites();
        int i = 0;
        for (Nebula nebula : this.game.galaxy.getNebulas()) {
            Point position = nebula.getPosition();
            TiledSprite tiledSprite = this.nebulas.get(i);
            tiledSprite.setPosition(position.getX() + f2, position.getY());
            tiledSprite.setScaleCenter(0.0f, 0.0f);
            tiledSprite.setScale(nebula.getSizeModifier());
            tiledSprite.setAlpha(1.0f);
            setNebula(tiledSprite, nebula);
            i++;
        }
    }

    public void set(int i) {
        resetSprites();
        for (Integer num : this.game.galaxy.getStarSystem(i).getNebulaIDs()) {
            int intValue = num.intValue();
            TiledSprite tiledSprite = this.nebulas.get(intValue);
            tiledSprite.setPosition(50.0f, -150.0f);
            tiledSprite.setSize(1300.0f, 1300.0f);
            tiledSprite.setAlpha(0.7f);
            setNebula(tiledSprite, this.game.galaxy.getNebulas().get(intValue));
        }
    }
}
