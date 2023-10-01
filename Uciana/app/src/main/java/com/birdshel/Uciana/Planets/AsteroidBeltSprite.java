package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.GameIconEnum;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AsteroidBeltSprite extends Entity {
    private final TiledSprite asteroidBeltSprite;
    private final List<Entity> asteroidSprites = new ArrayList();

    public AsteroidBeltSprite(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        Object obj;
        TiledSprite tiledSprite = new TiledSprite(105.0f, 0.0f, game.graphics.asteroidBeltsTexture, vertexBufferObjectManager);
        this.asteroidBeltSprite = tiledSprite;
        attachChild(tiledSprite);
        for (int i = 0; i < 10; i++) {
            TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
            tiledSprite2.setCurrentTileIndex(GameIconEnum.ASTEROID1.ordinal() + Functions.random.nextInt(7));
            tiledSprite2.setScale((Functions.random.nextFloat() * 0.10000001f) + 0.2f);
            attachChild(tiledSprite2);
            float nextFloat = (Functions.random.nextFloat() * 10.0f) + 15.0f;
            float nextInt = Functions.random.nextInt(60) + 125;
            float f2 = nextInt + 25.0f;
            MoveModifier moveModifier = new MoveModifier(nextFloat, nextInt, f2, -100.0f, 180.0f);
            float f3 = nextInt + 50.0f;
            MoveModifier moveModifier2 = new MoveModifier(nextFloat, f2, f3, 180.0f, 360.0f);
            MoveModifier moveModifier3 = new MoveModifier(nextFloat, f3, f2, 360.0f, 540.0f);
            MoveModifier moveModifier4 = new MoveModifier(nextFloat, f2, nextInt, 540.0f, 720.0f);
            MoveModifier moveModifier5 = new MoveModifier(nextFloat, nextInt, f2, 720.0f, 540.0f);
            MoveModifier moveModifier6 = new MoveModifier(nextFloat, f2, f3, 540.0f, 360.0f);
            MoveModifier moveModifier7 = new MoveModifier(nextFloat, f3, f2, 360.0f, 180.0f);
            MoveModifier moveModifier8 = new MoveModifier(nextFloat, f2, nextInt, 180.0f, -100.0f);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SequenceEntityModifier(moveModifier, moveModifier2, moveModifier3, moveModifier4, moveModifier5, moveModifier6, moveModifier7, moveModifier8));
            arrayList.add(new SequenceEntityModifier(moveModifier2, moveModifier3, moveModifier4, moveModifier5, moveModifier6, moveModifier7, moveModifier8, moveModifier));
            arrayList.add(new SequenceEntityModifier(moveModifier3, moveModifier4, moveModifier5, moveModifier6, moveModifier7, moveModifier8, moveModifier, moveModifier2));
            arrayList.add(new SequenceEntityModifier(moveModifier4, moveModifier5, moveModifier6, moveModifier7, moveModifier8, moveModifier, moveModifier2, moveModifier3));
            arrayList.add(new SequenceEntityModifier(moveModifier5, moveModifier6, moveModifier7, moveModifier8, moveModifier, moveModifier2, moveModifier3, moveModifier4));
            arrayList.add(new SequenceEntityModifier(moveModifier6, moveModifier7, moveModifier8, moveModifier, moveModifier2, moveModifier3, moveModifier4, moveModifier5));
            arrayList.add(new SequenceEntityModifier(moveModifier7, moveModifier8, moveModifier, moveModifier2, moveModifier3, moveModifier4, moveModifier5, moveModifier6));
            arrayList.add(new SequenceEntityModifier(moveModifier8, moveModifier, moveModifier2, moveModifier3, moveModifier4, moveModifier5, moveModifier6, moveModifier7));
            if (i >= arrayList.size()) {
                obj = arrayList.get(Functions.random.nextInt(arrayList.size()));
            } else {
                obj = arrayList.get(i);
            }
            tiledSprite2.registerEntityModifier(new LoopEntityModifier((SequenceEntityModifier) obj));
            this.asteroidSprites.add(tiledSprite2);
        }
    }

    public boolean isPressed(Point point) {
        return isVisible() && point.getX() > getX() + this.asteroidBeltSprite.getX() && point.getX() < (getX() + this.asteroidBeltSprite.getX()) + this.asteroidBeltSprite.getWidthScaled() && point.getY() > 100.0f && point.getY() < 1180.0f;
    }

    public void set(AsteroidBelt asteroidBelt) {
        this.asteroidBeltSprite.setCurrentTileIndex(asteroidBelt.getImageIndex());
    }
}
