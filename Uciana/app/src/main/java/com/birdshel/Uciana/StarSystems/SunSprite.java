package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import org.andengine.entity.Entity;
import org.andengine.entity.particle.BatchedPseudoSpriteParticleSystem;
import org.andengine.entity.particle.emitter.CircleOutlineParticleEmitter;
import org.andengine.entity.particle.emitter.RectangleParticleEmitter;
import org.andengine.entity.particle.initializer.AccelerationParticleInitializer;
import org.andengine.entity.particle.initializer.ExpireParticleInitializer;
import org.andengine.entity.particle.initializer.ScaleParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ColorParticleModifier;
//import org.andengine.entity.particle.modifier.ExpireParticleInitializer;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SunSprite extends Entity {
    private ColorParticleModifier<Entity> colorParticleModifier = new ColorParticleModifier<>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    private final BatchedPseudoSpriteParticleSystem particleSystem;
    private final Sprite sun;

    public SunSprite(Game game, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        if (z) {
            BatchedPseudoSpriteParticleSystem batchedPseudoSpriteParticleSystem = new BatchedPseudoSpriteParticleSystem(new CircleOutlineParticleEmitter(0.0f, 360.0f, 90.0f, 380.0f), 50.0f, 100.0f, 500, game.graphics.particleTexture, vertexBufferObjectManager);
            this.particleSystem = batchedPseudoSpriteParticleSystem;
            batchedPseudoSpriteParticleSystem.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 1);
            batchedPseudoSpriteParticleSystem.addParticleInitializer(new VelocityParticleInitializer(20.0f, 50.0f, -20.0f, 20.0f));
            batchedPseudoSpriteParticleSystem.addParticleInitializer(new AccelerationParticleInitializer(20.0f, 20.0f, -20.0f, 20.0f));
        } else {
            BatchedPseudoSpriteParticleSystem batchedPseudoSpriteParticleSystem2 = new BatchedPseudoSpriteParticleSystem(new RectangleParticleEmitter(100.0f, 100.0f, 200.0f, 200.0f), 25.0f, 50.0f, WeaponStats.SUBSPACE_CHARGE_SPEED, game.graphics.particleTexture, vertexBufferObjectManager);
            this.particleSystem = batchedPseudoSpriteParticleSystem2;
            batchedPseudoSpriteParticleSystem2.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 1);
            batchedPseudoSpriteParticleSystem2.addParticleInitializer(new VelocityParticleInitializer(1.0f, 1.0f, -1.0f, -1.0f));
            batchedPseudoSpriteParticleSystem2.addParticleInitializer(new AccelerationParticleInitializer(20.0f, 20.0f, 20.0f, 20.0f));
        }
        this.particleSystem.addParticleInitializer(new ExpireParticleInitializer(3.0f));
        this.particleSystem.addParticleInitializer(new ScaleParticleInitializer(2.0f, 4.0f));
        this.particleSystem.addParticleModifier(this.colorParticleModifier);
        this.particleSystem.addParticleModifier(new AlphaParticleModifier(1.0f, 3.0f, 0.9f, 0.0f));
        attachChild(this.particleSystem);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.sunTextureRegion, vertexBufferObjectManager);
        this.sun = sprite;
        if (!z) {
            sprite.setPosition(20.0f, -305.0f);
            sprite.setScale(1.5f);
            sprite.setRotation(45.0f);
        }
        attachChild(sprite);
    }

    public void set(StarType starType) {
        this.sun.setColor(starType.getColor());
        float red = starType.getColor().getRed();
        float green = starType.getColor().getGreen();
        float blue = starType.getColor().getBlue();
        this.particleSystem.removeParticleModifier(this.colorParticleModifier);
        ColorParticleModifier<Entity> colorParticleModifier = new ColorParticleModifier<>(0.0f, 0.0f, red, red, green, green, blue, blue);
        this.colorParticleModifier = colorParticleModifier;
        this.particleSystem.addParticleModifier(colorParticleModifier);
        this.particleSystem.setParticlesColor(red, green, blue, 1.0f);
    }
}
