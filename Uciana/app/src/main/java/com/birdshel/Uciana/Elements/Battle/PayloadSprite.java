package com.birdshel.Uciana.Elements.Battle;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponType;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.PointParticleEmitter;
import org.andengine.entity.particle.initializer.ExpireParticleInitializer;
import org.andengine.entity.particle.initializer.ScaleParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ColorParticleModifier;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PayloadSprite extends Entity {
    private ColorParticleModifier colorParticleModifier;
    private final AlphaModifier fadeIn;
    private final AlphaModifier fadeOut;
    private String id = "";
    private final SpriteParticleSystem particleSystem;
    private final TiledSprite payloadSprite;

    public PayloadSprite(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager);
        this.payloadSprite = tiledSprite;
        tiledSprite.setZIndex(3);
        attachChild(tiledSprite);
        AlphaModifier alphaModifier = new AlphaModifier(0.0f, 0.0f, 0.0f);
        this.fadeIn = alphaModifier;
        AlphaModifier alphaModifier2 = new AlphaModifier(0.0f, 0.0f, 0.0f);
        this.fadeOut = alphaModifier2;
        tiledSprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(alphaModifier, alphaModifier2)));
        SpriteParticleSystem spriteParticleSystem = new SpriteParticleSystem(new PointParticleEmitter(0.0f, 0.0f) { // from class: com.birdshel.Uciana.Elements.Battle.PayloadSprite.1
            @Override // org.andengine.entity.particle.emitter.BaseParticleEmitter, org.andengine.engine.handler.IUpdateHandler
            public void onUpdate(float f2) {
                super.onUpdate(f2);
                setCenterX(PayloadSprite.this.payloadSprite.getX() + (PayloadSprite.this.payloadSprite.getWidthScaled() / 2.0f));
                setCenterY(PayloadSprite.this.payloadSprite.getY() + (PayloadSprite.this.payloadSprite.getHeightScaled() / 2.0f));
            }
        }, 10.0f, 25.0f, 100, game.graphics.particleTexture, vertexBufferObjectManager);
        this.particleSystem = spriteParticleSystem;
        spriteParticleSystem.addParticleInitializer(new ScaleParticleInitializer(3.0f));
        spriteParticleSystem.addParticleInitializer(new VelocityParticleInitializer(-40.0f, 40.0f, -40.0f, 40.0f));
        spriteParticleSystem.addParticleInitializer(new ExpireParticleInitializer(0.5f, 0.7f));
        ColorParticleModifier colorParticleModifier = new ColorParticleModifier(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.colorParticleModifier = colorParticleModifier;
        spriteParticleSystem.addParticleModifier(colorParticleModifier);
        spriteParticleSystem.addParticleModifier(new AlphaParticleModifier(0.1f, 0.7f, 1.0f, 0.0f));
        spriteParticleSystem.setZIndex(2);
        attachChild(spriteParticleSystem);
    }

    public void clear() {
        this.id = "";
        setVisible(false);
    }

    public String getID() {
        return this.id;
    }

    public TiledSprite getSprite() {
        return this.payloadSprite;
    }

    public void setPayload(String str, Weapon weapon, WeaponType weaponType, Point point) {
        float f2;
        float f3;
        this.id = str;
        if (weaponType == WeaponType.TORPEDO) {
            f2 = 0.1f;
            f3 = 0.6f;
        } else {
            f2 = 0.2f;
            f3 = 0.8f;
        }
        this.fadeIn.reset(f2, f3, 1.0f);
        this.fadeOut.reset(f2, 1.0f, f3);
        this.payloadSprite.setPosition(point.getX(), point.getY());
        this.payloadSprite.setCurrentTileIndex(weapon.getIconIndex());
        float red = weapon.getWeaponColor().getRed();
        float green = weapon.getWeaponColor().getGreen();
        float blue = weapon.getWeaponColor().getBlue();
        this.particleSystem.removeParticleModifier(this.colorParticleModifier);
        ColorParticleModifier colorParticleModifier = new ColorParticleModifier(0.0f, 0.0f, red, red, green, green, blue, blue);
        this.colorParticleModifier = colorParticleModifier;
        this.particleSystem.addParticleModifier(colorParticleModifier);
        setVisible(true);
    }
}
