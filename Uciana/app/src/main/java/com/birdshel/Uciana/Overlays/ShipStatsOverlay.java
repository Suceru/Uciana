package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipStatus;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipStatsOverlay extends ExtendedChildScene {
    private static final int HEIGHT = 106;
    private static final int WIDTH = 300;
    private final Text armorHitPoints;
    private final TiledSprite armorIcon;
    private final TiledSprite empireColorBackground;
    private final Text shieldHitPoints;
    private final TiledSprite shieldIcon;
    private final TiledSprite shipClassIcon;
    private final Text shipClassName;
    private final ShipSprite shipSprite;
    private final List<TiledSprite> statusIcons;

    public ShipStatsOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.statusIcons = new ArrayList();
        this.G.setVisible(false);
        TiledSprite x = x(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireColorBackground = x;
        x.setSize(300.0f, 106.0f);
        Sprite t = t(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
        t.setSize(300.0f, 106.0f);
        t.setAlpha(0.8f);
        blinkSprite(x);
        TiledSprite x2 = x(2.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, true);
        this.shipClassIcon = x2;
        x2.setSize(20.0f, 20.0f);
        this.shipClassName = u(24.0f, 2.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
        ShipSprite shipSprite = new ShipSprite(game, vertexBufferObjectManager);
        this.shipSprite = shipSprite;
        shipSprite.setPosition(0.0f, 20.0f);
        attachChild(shipSprite);
        TiledSprite w = w(100.0f, 30.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.SHIELD.ordinal(), true);
        this.shieldIcon = w;
        w.setSize(20.0f, 20.0f);
        this.shieldHitPoints = u(122.0f, 31.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
        TiledSprite w2 = w(100.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.ARMOR.ordinal(), true);
        this.armorIcon = w2;
        w2.setSize(20.0f, 20.0f);
        this.armorHitPoints = u(122.0f, 0.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
        for (int i = 1; i < 5; i++) {
            TiledSprite x3 = x(300 - (i * 20), 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, false);
            x3.setSize(20.0f, 20.0f);
            this.statusIcons.add(x3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            this.C.sounds.playBoxPressSound();
            Game game = this.C;
            game.vibrate(game.BUTTON_VIBRATE);
            back();
        }
    }

    public void setOverlay(int i, Ship ship) {
        this.empireColorBackground.setCurrentTileIndex(i);
        this.shipClassIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(ship.getShipType()));
        this.shipClassName.setText(ship.getName());
        this.shipSprite.setShip(ship, 86.0f, ship.getShipType().getScale() * 86.0f, 1, true);
        Text text = this.armorHitPoints;
        text.setText(ship.getArmorHitPoints() + "/" + ship.getMaxArmorHitPoints() + " hp");
        int i2 = 0;
        if (ship.getShield().getID() != ShipComponentID.NO_SHIELDS) {
            this.armorIcon.setY(60.0f);
            this.armorHitPoints.setY(61.0f);
            this.shieldIcon.setVisible(true);
            Text text2 = this.shieldHitPoints;
            text2.setText(ship.getShieldHitPoints() + "/" + ship.getMaxShieldHitPoints() + " hp");
            float shieldHitPoints = ((float) ship.getShieldHitPoints()) / ((float) ship.getMaxShieldHitPoints());
            if (shieldHitPoints >= 0.8f) {
                this.shieldHitPoints.setColor(Color.GREEN);
            } else if (shieldHitPoints < 0.8f && shieldHitPoints > 0.4d) {
                this.shieldHitPoints.setColor(Color.YELLOW);
            } else {
                this.shieldHitPoints.setColor(Color.RED);
            }
        } else {
            this.shieldIcon.setVisible(false);
            this.shieldHitPoints.setText("");
            this.armorIcon.setY(60.0f);
            this.armorHitPoints.setY(61.0f);
        }
        if (ship.getArmorPercent() >= 80) {
            this.armorHitPoints.setColor(Color.GREEN);
        } else if (ship.getArmorPercent() < 80 && ship.getArmorPercent() > 40) {
            this.armorHitPoints.setColor(Color.YELLOW);
        } else {
            this.armorHitPoints.setColor(Color.RED);
        }
        for (TiledSprite tiledSprite : this.statusIcons) {
            tiledSprite.setVisible(false);
        }
        for (ShipStatus shipStatus : ship.getStatuses()) {
            this.statusIcons.get(i2).setVisible(true);
            this.statusIcons.get(i2).setCurrentTileIndex(shipStatus.getImageIndex());
            i2++;
        }
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setPosition(float f2, float f3) {
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f3 > 614.0f) {
            f3 = 614.0f;
        }
        if (f2 > getWidth() - 300.0f) {
            f2 = getWidth() - 300.0f;
        }
        super.setPosition(f2, f3);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
