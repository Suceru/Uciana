package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Elements.ShipDamage;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ScaleAtModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipSpriteBattle extends ShipSprite {
    private int armorDamage;
    private final Line armorHpBar;
    private final TiledSprite beamIcon;
    private String damage;
    private final Line damageHpBar;
    private final Line damageShieldBar;
    private final ShipDamage[][] damages;
    private final AnimatedSprite hitExplosion;
    private final Text hitPercentText;
    private final Sprite shield;
    private int shieldDamage;
    private final Line shieldHpBar;
    private final List<Entity> shipStatuses;
    private final TiledSprite shockwave;
    private final Text totalDamageText;

    public ShipSpriteBattle(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager);
        this.damages = (ShipDamage[][]) Array.newInstance(ShipDamage.class, 5, 3);
        this.shipStatuses = new ArrayList();
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.damages[i][i2] = new ShipDamage(game, vertexBufferObjectManager);
                this.B.attachChild(this.damages[i][i2]);
            }
        }
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.shieldTexture, vertexBufferObjectManager);
        this.shield = sprite;
        sprite.setAlpha(0.0f);
        sprite.setZIndex(2);
        this.B.attachChild(sprite);
        AnimatedSprite animatedSprite = new AnimatedSprite(0.0f, 0.0f, game.graphics.explosionTexture, vertexBufferObjectManager);
        this.hitExplosion = animatedSprite;
        animatedSprite.setCurrentTileIndex(8);
        animatedSprite.setSize(50.0f, 50.0f);
        animatedSprite.setZIndex(3);
        this.B.attachChild(animatedSprite);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.shockwave = tiledSprite;
        tiledSprite.setCurrentTileIndex(GameIconEnum.SHOCK_WAVE.ordinal());
        tiledSprite.setVisible(false);
        this.B.attachChild(tiledSprite);
        Text text = new Text(100.0f, 25.0f, game.fonts.smallInfoFont, this.z, this.A, vertexBufferObjectManager);
        this.totalDamageText = text;
        text.setAlpha(0.0f);
        Color color = Color.RED;
        text.setColor(color);
        text.setZIndex(3);
        attachChild(text);
        Text text2 = new Text(100.0f, 45.0f, game.fonts.smallInfoFont, this.z, this.A, vertexBufferObjectManager);
        this.hitPercentText = text2;
        text2.setVisible(false);
        text2.setColor(Color.YELLOW);
        text2.setZIndex(3);
        attachChild(text2);
        TiledSprite tiledSprite2 = new TiledSprite(100.0f, 60.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.beamIcon = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.BEAM_WEAPON.ordinal());
        tiledSprite2.setVisible(false);
        tiledSprite2.setZIndex(3);
        attachChild(tiledSprite2);
        Line line = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
        this.armorHpBar = line;
        line.setColor(Color.GREEN);
        line.setLineWidth(8.0f);
        line.setAlpha(0.4f);
        line.setZIndex(3);
        attachChild(line);
        Line line2 = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
        this.shieldHpBar = line2;
        line2.setColor(new Color(0.16f, 0.78f, 0.98f));
        line2.setLineWidth(8.0f);
        line2.setAlpha(0.4f);
        line2.setZIndex(3);
        attachChild(line2);
        Line line3 = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
        this.damageShieldBar = line3;
        line3.setColor(color);
        line3.setLineWidth(8.0f);
        line3.setAlpha(0.0f);
        line3.setZIndex(3);
        attachChild(line3);
        Line line4 = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
        this.damageHpBar = line4;
        line4.setColor(color);
        line4.setLineWidth(8.0f);
        line4.setAlpha(0.0f);
        line4.setZIndex(3);
        attachChild(line4);
        for (int i3 = 0; i3 < 5; i3++) {
            TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite3.setVisible(false);
            tiledSprite3.setSize(20.0f, 20.0f);
            this.shipStatuses.add(tiledSprite3);
            attachChild(tiledSprite3);
        }
    }

    private void hideShipStatus() {
        for (Entity entity : this.shipStatuses) {
            entity.setVisible(false);
        }
    }

    private void setHitExplosion(TiledSprite tiledSprite) {
        this.hitExplosion.setPosition((tiledSprite.getX() + (tiledSprite.getWidthScaled() / 2.0f)) - (this.hitExplosion.getWidthScaled() / 2.0f), (tiledSprite.getY() + (tiledSprite.getHeightScaled() / 2.0f)) - (this.hitExplosion.getHeightScaled() / 2.0f));
    }

    private void setShipStatusPosition(Point point) {
        float x = point.getX();
        for (Entity entity : this.shipStatuses) {
            entity.setPosition(x, point.getY());
            x += 20.0f;
        }
    }

    private void showShipStatus(Ship ship) {
        hideShipStatus();
        int i = 0;
        for (ShipStatus shipStatus : ship.getStatuses()) {
            TiledSprite tiledSprite = (TiledSprite) this.shipStatuses.get(i);
            tiledSprite.setVisible(true);
            tiledSprite.setCurrentTileIndex(shipStatus.getImageIndex());
            i++;
        }
    }

    public void hideHitPercentage() {
        this.hitPercentText.setVisible(false);
        this.beamIcon.setVisible(false);
    }

    public void hideHpBars() {
        this.shieldHpBar.setVisible(false);
        this.armorHpBar.setVisible(false);
    }

    @Override // com.birdshel.Uciana.Ships.ShipSprite
    public void hideShipImage() {
        super.hideShipImage();
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.damages[i][i2].setVisible(false);
            }
        }
    }

    @Override // com.birdshel.Uciana.Ships.ShipSprite, org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.damages[i][i2].setAlpha(f2);
            }
        }
    }

    public void setDamageBar(int i, int i2) {
        this.shieldDamage = i;
        this.armorDamage = i2;
    }

    public void setDamageToShow(String str) {
        this.damage = str;
    }

    @Override // com.birdshel.Uciana.Ships.ShipSprite
    public void setShip(Ship ship, float f2, float f3, int i, boolean z) {
        setShipIcon(ship.getEmpireID(), ship.getShipType(), ship.getHullDesign(), f2, f3, i, z);
        if (ship.isCombatShip()) {
            updateShipDamage(ship);
        }
    }

    @Override // com.birdshel.Uciana.Ships.ShipSprite
    public void setShipIcon(int i, ShipType shipType, int i2, float f2, float f3, int i3, boolean z) {
        super.setShipIcon(i, shipType, i2, f2, f3, i3, z);
        float f4 = f3 / 145.0f;
        float f5 = f3 + (0.75f * f3);
        float f6 = (f2 / 2.0f) - (f5 / 2.0f);
        this.shield.setPosition(f6, f6);
        this.shield.setSize(f5, f5);
        this.shockwave.setSize(getSize(), getSize());
        for (int i4 = 0; i4 < 5; i4++) {
            for (int i5 = 0; i5 < 3; i5++) {
                this.damages[i4][i5].setShouldBeShown(false);
            }
        }
        int i6 = 0;
        for (List<Point> list : DamagePoints.a(i, shipType, i2)) {
            int i7 = 0;
            for (Point point : list) {
                float x = this.C.getX() + (point.getX() * f4);
                float y = this.C.getY() + (point.getY() * f4);
                this.damages[i6][i7].setShouldBeShown(true);
                this.damages[i6][i7].setPosition(x, y);
                this.damages[i6][i7].setScale(f4);
                i7++;
            }
            i6++;
        }
        if (shipType.isStation()) {
            setHitExplosion(this.D);
        } else {
            setHitExplosion(this.C);
        }
    }

    public void setShipStats(Ship ship, boolean z) {
        this.armorHpBar.setVisible(z);
        this.shieldHpBar.setVisible(z);
        this.damageShieldBar.setVisible(z);
        this.damageHpBar.setVisible(z);
        if (z) {
            showShipStatus(ship);
        } else {
            hideShipStatus();
        }
        float f2 = getY() + 18.0f > 720.0f ? 62.0f : 80.0f;
        float shieldHitPoints = (ship.getMaxShieldHitPoints() > 0 ? ship.getShieldHitPoints() / ship.getMaxShieldHitPoints() : 0.0f) * 70.0f;
        float f3 = 4.0f + f2;
        this.shieldHpBar.setPosition(10.0f, f3, shieldHitPoints + 10.0f, f3);
        float armorHitPoints = (ship.getArmorHitPoints() / ship.getMaxArmorHitPoints()) * 70.0f;
        float f4 = f2 + 10.0f;
        this.armorHpBar.setPosition(10.0f, f4, armorHitPoints + 10.0f, f4);
        if (ship.getMaxShieldHitPoints() > 0) {
            this.damageShieldBar.setPosition(this.shieldHpBar.getX1() + shieldHitPoints, f3, this.shieldHpBar.getX1() + shieldHitPoints + ((this.shieldDamage / ship.getMaxShieldHitPoints()) * 70.0f), f3);
        }
        this.damageHpBar.setPosition(this.armorHpBar.getX1() + armorHitPoints, f4, this.armorHpBar.getX1() + armorHitPoints + ((this.armorDamage / ship.getMaxArmorHitPoints()) * 70.0f), f4);
        setShipStatusPosition(new Point(10.0f, f3 - 20.0f));
    }

    public void setShipStatus(ShipStatus shipStatus) {
        for (Entity entity : this.shipStatuses) {
            if (!entity.isVisible()) {
                entity.setVisible(true);
                ((TiledSprite) entity).setCurrentTileIndex(shipStatus.getImageIndex());
                return;
            }
        }
    }

    protected void setSpritesInvisible() {
        super.hideShipImage();
        hideShipStatus();
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.damages[i][i2].setVisible(false);
            }
        }
    }

    public void showDamage() {
        this.totalDamageText.setColor(Color.RED);
        this.totalDamageText.setText(this.damage);
        this.totalDamageText.registerEntityModifier(new AlphaModifier(1.5f, 1.0f, 0.0f));
        AlphaModifier alphaModifier = new AlphaModifier(1.5f, 0.6f, 0.0f);
        this.damageShieldBar.registerEntityModifier(alphaModifier);
        this.damageHpBar.registerEntityModifier(alphaModifier);
        this.shieldDamage = 0;
        this.armorDamage = 0;
    }

    public void showHitExplosion() {
        this.hitExplosion.setAlpha(1.0f);
        this.hitExplosion.animate(new long[]{45, 45, 45, 45, 45, 45, 45, 45, 45}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, false, new AnimatedSprite.IAnimationListener() { // from class: com.birdshel.Uciana.Ships.ShipSpriteBattle.1
            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFinished(AnimatedSprite animatedSprite) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFrameChanged(AnimatedSprite animatedSprite, int i, int i2) {
                if (i == 6) {
                    ShipSpriteBattle.this.hitExplosion.setAlpha(0.0f);
                }
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationLoopFinished(AnimatedSprite animatedSprite, int i, int i2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationStarted(AnimatedSprite animatedSprite, int i) {
            }
        });
    }

    public void showHitPercentage(int i) {
        Text text = this.hitPercentText;
        text.setText(i + "%");
        this.hitPercentText.setVisible(true);
        this.beamIcon.setVisible(true);
    }

    public void showShieldHit() {
        this.shield.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        SequenceEntityModifier sequenceEntityModifier = new SequenceEntityModifier(new AlphaModifier(0.3f, 0.0f, 1.0f), new AlphaModifier(0.2f, 1.0f, 1.0f), new AlphaModifier(0.3f, 1.0f, 0.0f));
        sequenceEntityModifier.setAutoUnregisterWhenFinished(true);
        this.shield.registerEntityModifier(sequenceEntityModifier);
    }

    public void showShieldRegen(int i) {
        this.totalDamageText.setColor(Color.CYAN);
        this.totalDamageText.setText(Integer.toString(i));
        this.totalDamageText.registerEntityModifier(new AlphaModifier(1.5f, 1.0f, 0.0f));
    }

    public void showShockwave() {
        setSpritesInvisible();
        this.shockwave.setVisible(true);
        this.shockwave.registerEntityModifier(new AlphaModifier(1.0f, 1.0f, 0.0f));
        this.shockwave.registerEntityModifier(new ScaleAtModifier(1.0f, 1.0f, 6.0f, getSize() / 2.0f, getSize() / 2.0f));
    }

    public void updateShipDamage(Ship ship) {
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.damages[i][i2].setVisible(false);
            }
        }
        float armorHitPoints = ship.getArmorHitPoints() / ship.getMaxArmorHitPoints();
        if (armorHitPoints == 1.0f) {
            return;
        }
        if (armorHitPoints < 0.2f) {
            for (int i3 = 0; i3 < 3; i3++) {
                if (this.damages[0][i3].shouldBeShown()) {
                    this.damages[0][i3].setVisible(true);
                }
            }
        }
        if (armorHitPoints < 0.4f) {
            for (int i4 = 0; i4 < 3; i4++) {
                if (this.damages[1][i4].shouldBeShown()) {
                    this.damages[1][i4].setVisible(true);
                }
            }
        }
        if (armorHitPoints < 0.6f) {
            for (int i5 = 0; i5 < 3; i5++) {
                if (this.damages[2][i5].shouldBeShown()) {
                    this.damages[2][i5].setVisible(true);
                }
            }
        }
        if (armorHitPoints < 0.8f) {
            for (int i6 = 0; i6 < 3; i6++) {
                if (this.damages[3][i6].shouldBeShown()) {
                    this.damages[3][i6].setVisible(true);
                }
            }
        }
        for (int i7 = 0; i7 < 3; i7++) {
            if (this.damages[4][i7].shouldBeShown()) {
                this.damages[4][i7].setVisible(true);
            }
        }
    }
}
