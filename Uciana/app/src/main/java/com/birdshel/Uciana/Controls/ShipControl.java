package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.SpecialComponent;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponType;
import com.birdshel.Uciana.Ships.ShipSpriteBattle;
import com.birdshel.Uciana.Ships.StarBase;
import com.birdshel.Uciana.Ships.TorpedoTurret;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleAtModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipControl extends ExtendedScene {
    private static final int CONFIRM_COLOR = 0;
    private static final int DESTRUCT_COLOR = 3;
    private final Text armorHitPoints;
    private final TiledSprite armorIcon;
    private final Sprite blackBackground;
    private final Sprite doneButton;
    private final TiledSprite doneIcon;
    private final Text doneText;
    private final TiledSprite empireColorBackground;
    private final Sprite retreatButton;
    private final TiledSprite retreatIcon;
    private final Text retreatText;
    private final Sprite selectedAction;
    private final TiledSprite selfDestructButton;
    private final TiledSprite selfDestructIcon;
    private final Text selfDestructText;
    private final Text shieldHitPoints;
    private final TiledSprite shieldIcon;
    private Ship ship;
    private final Sprite shipButton;
    private final TiledSprite shipIcon;
    private ShipSpriteBattle shipSprite;
    private final Text shipText;
    private final TiledSprite thruster1;
    private final TiledSprite thruster2;
    private final Sprite waitButton;
    private final Text waitIcon1;
    private final Text waitIcon2;
    private final Text waitIcon3;
    private final Text waitText;
    private final ShipControlButton[] shipControlButtons = new ShipControlButton[6];
    private boolean isDisabled = false;
    private boolean isOpen = false;
    private Point positionWhenTouched = new Point(0.0f, 0.0f);
    private Point currentPosition = new Point(0.0f, 0.0f);

    public ShipControl(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        int i = 0;
        setBackgroundEnabled(false);
        this.B = game;
        TiledSprite J = J(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireColorBackground = J;
        J.setHeight(106.0f);
        Sprite E = E(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
        this.blackBackground = E;
        E.setHeight(106.0f);
        E.setAlpha(0.8f);
        TiledSprite H = H(10.0f, 28.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.MOVE.ordinal(), true);
        H.setSize(25.0f, 50.0f);
        H.setAlpha(0.5f);
        TiledSprite H2 = H(106.0f, 86.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.INFO.ordinal(), true);
        H2.setSize(20.0f, 20.0f);
        H2.setAlpha(0.5f);
        H2.setZIndex(2);
        this.G = H(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        Sprite E2 = E(131.0f, 20.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, true);
        this.selectedAction = E2;
        E2.setSize(86.0f, 86.0f);
        blinkSprite(E2);
        TiledSprite H3 = H(131.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.SHIELD.ordinal(), true);
        this.shieldIcon = H3;
        H3.setSize(20.0f, 20.0f);
        this.shieldHitPoints = F(151.0f, 1.0f, game.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        TiledSprite H4 = H(131.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.ARMOR.ordinal(), true);
        this.armorIcon = H4;
        H4.setSize(20.0f, 20.0f);
        this.armorHitPoints = F(151.0f, 1.0f, game.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        Sprite E3 = E(131.0f, 20.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager, true);
        this.shipButton = E3;
        E3.setSize(86.0f, 86.0f);
        E3.setAlpha(0.6f);
        TiledTextureRegion tiledTextureRegion = game.graphics.gameIconsTexture;
        GameIconEnum gameIconEnum = GameIconEnum.THRUSTER;
        TiledSprite H5 = H(140.0f, 30.0f, tiledTextureRegion, vertexBufferObjectManager, gameIconEnum.ordinal(), true);
        this.thruster1 = H5;
        H5.setSize(40.0f, 40.0f);
        H5.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.2f, 0.6f, 1.0f), new AlphaModifier(0.2f, 1.0f, 0.6f))));
        TiledSprite H6 = H(140.0f, 45.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, gameIconEnum.ordinal(), true);
        this.thruster2 = H6;
        H6.setSize(40.0f, 40.0f);
        H6.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.2f, 0.6f, 1.0f), new AlphaModifier(0.2f, 1.0f, 0.6f))));
        TiledSprite H7 = H(139.0f, 20.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SHIPS.ordinal(), true);
        this.shipIcon = H7;
        H7.setSize(70.0f, 70.0f);
        this.shipText = F(0.0f, 0.0f, game.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        while (true) {
            ShipControlButton[] shipControlButtonArr = this.shipControlButtons;
            if (i < shipControlButtonArr.length) {
                shipControlButtonArr[i] = new ShipControlButton(game, vertexBufferObjectManager);
                attachChild(this.shipControlButtons[i]);
                i++;
            } else {
                TiledSprite H8 = H(0.0f, 20.0f, game.graphics.empireColors, vertexBufferObjectManager, 3, true);
                this.selfDestructButton = H8;
                H8.setSize(86.0f, 86.0f);
                H8.setAlpha(0.6f);
                TiledSprite H9 = H(0.0f, 17.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SHOCK_WAVE.ordinal(), true);
                this.selfDestructIcon = H9;
                H9.setSize(70.0f, 70.0f);
                Text F = F(0.0f, 0.0f, game.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
                this.selfDestructText = F;
                F.setText(game.getActivity().getString(R.string.ship_control_destruct));
                Sprite E4 = E(0.0f, 20.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, true);
                this.waitButton = E4;
                E4.setSize(86.0f, 86.0f);
                E4.setAlpha(0.6f);
                this.waitIcon1 = F(0.0f, 30.0f, game.fonts.infoFont, "z", this.E, vertexBufferObjectManager);
                this.waitIcon2 = F(0.0f, 45.0f, game.fonts.smallFont, "z", this.E, vertexBufferObjectManager);
                this.waitIcon3 = F(0.0f, 60.0f, game.fonts.smallInfoFont, "z", this.E, vertexBufferObjectManager);
                this.waitText = F(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.ship_control_wait), this.E, vertexBufferObjectManager);
                Sprite E5 = E(0.0f, 20.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, true);
                this.retreatButton = E5;
                E5.setSize(86.0f, 86.0f);
                E5.setAlpha(0.6f);
                TiledSprite H10 = H(0.0f, 17.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.DOWN_ARROW.ordinal(), true);
                this.retreatIcon = H10;
                H10.setSize(70.0f, 70.0f);
                H10.setRotationCenter(35.0f, 35.0f);
                H10.setRotation(90.0f);
                this.retreatText = F(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.ship_control_retreat), this.E, vertexBufferObjectManager);
                Sprite E6 = E(0.0f, 20.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, true);
                this.doneButton = E6;
                E6.setSize(86.0f, 86.0f);
                E6.setAlpha(0.6f);
                TiledSprite H11 = H(0.0f, 20.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.UP_ARROW.ordinal(), true);
                this.doneIcon = H11;
                H11.setSize(70.0f, 70.0f);
                H11.setRotationCenter(35.0f, 35.0f);
                H11.setRotation(90.0f);
                this.doneText = F(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.ship_control_done), this.E, vertexBufferObjectManager);
                setScale(0.0f);
                return;
            }
        }
    }

    private void doneButtonPressed() {
        if (this.B.battleScene.anyShipsMoving()) {
            return;
        }
        this.B.battleScene.shipDone();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private boolean hasMoved() {
        if (this.blackBackground.isVisible()) {
            return false;
        }
        float x = this.currentPosition.getX() - this.positionWhenTouched.getX();
        if (x < -20.0f || x > 20.0f) {
            return true;
        }
        float y = this.currentPosition.getY() - this.positionWhenTouched.getY();
        return y < -20.0f || y > 20.0f;
    }

    private void resetDestructButton() {
        this.selfDestructButton.setCurrentTileIndex(3);
        this.selfDestructText.setText(this.B.getActivity().getString(R.string.ship_control_destruct));
        this.selfDestructButton.clearEntityModifiers();
        this.selfDestructText.setX((this.selfDestructButton.getX() + 43.0f) - (this.selfDestructText.getWidthScaled() / 2.0f));
    }

    private void retreatButtonPressed() {
        if (this.B.battleScene.anyShipsMoving() || !this.ship.canGoFTL()) {
            return;
        }
        this.B.battleScene.shipRetreat(this.ship);
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void selfDestructButtonPressed() {
        if (this.B.battleScene.anyShipsMoving()) {
            return;
        }
        if (this.selfDestructButton.getCurrentTileIndex() == 0) {
            this.B.battleScene.selfDestruct(this.ship);
        } else {
            this.selfDestructButton.setCurrentTileIndex(0);
            this.selfDestructText.setText(this.B.getActivity().getString(R.string.ship_control_confirm));
            this.selfDestructText.setX((this.selfDestructButton.getX() + 43.0f) - (this.selfDestructText.getWidthScaled() / 2.0f));
            blinkSprite(this.selfDestructButton);
        }
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setControlButtons() {
        for (ShipControlButton shipControlButton : this.shipControlButtons) {
            shipControlButton.setVisible(false);
        }
        Ship ship = this.ship;
        if (ship instanceof StarBase) {
            this.shipIcon.setCurrentTileIndex(GameIconEnum.STAR_BASE.ordinal());
            this.shipText.setText(this.B.getActivity().getString(R.string.ship_control_base));
            this.thruster1.setVisible(false);
            this.thruster2.setVisible(false);
        } else if (ship instanceof TorpedoTurret) {
            this.shipIcon.setCurrentTileIndex(GameIconEnum.TORPEDO_TURRET.ordinal());
            this.shipText.setText(this.B.getActivity().getString(R.string.ship_control_turret));
            this.thruster1.setVisible(false);
            this.thruster2.setVisible(false);
        } else {
            this.shipIcon.setCurrentTileIndex(GameIconEnum.SHIPS.ordinal());
            this.shipText.setText(this.B.getActivity().getString(R.string.ship_control_ship));
            this.thruster1.setVisible(true);
            this.thruster2.setVisible(true);
        }
        Text text = this.shipText;
        text.setY(106.0f - text.getHeightScaled());
        Text text2 = this.shipText;
        text2.setX(174.0f - (text2.getWidthScaled() / 2.0f));
        float f2 = 221.0f;
        int i = 0;
        for (ShipComponent shipComponent : this.ship.getShipComponents()) {
            if (shipComponent instanceof SpecialComponent) {
                if (((SpecialComponent) shipComponent).showOnShipControl()) {
                    this.shipControlButtons[i].setVisible(true);
                    this.shipControlButtons[i].set(shipComponent);
                    this.shipControlButtons[i].setX(f2);
                    i++;
                    f2 += 90.0f;
                }
            } else {
                if ((shipComponent instanceof Weapon) && ((Weapon) shipComponent).getType() == WeaponType.BOMB) {
                }
                this.shipControlButtons[i].setVisible(true);
                this.shipControlButtons[i].set(shipComponent);
                this.shipControlButtons[i].setX(f2);
                i++;
                f2 += 90.0f;
            }
        }
        this.selfDestructButton.setX(f2);
        this.selfDestructIcon.setX(f2 + 8.0f);
        Text text3 = this.selfDestructText;
        text3.setX((f2 + 43.0f) - (text3.getWidthScaled() / 2.0f));
        Text text4 = this.selfDestructText;
        text4.setY(106.0f - text4.getHeightScaled());
        float f3 = f2 + 90.0f;
        this.waitButton.setX(f3);
        Text text5 = this.waitText;
        text5.setX((f3 + 43.0f) - (text5.getWidthScaled() / 2.0f));
        Text text6 = this.waitText;
        text6.setY(106.0f - text6.getHeightScaled());
        this.waitIcon1.setX(20.0f + f3);
        this.waitIcon2.setX(40.0f + f3);
        this.waitIcon3.setX(60.0f + f3);
        float f4 = f3 + 90.0f;
        Ship ship2 = this.ship;
        if (ship2 instanceof StarBase) {
            this.retreatButton.setVisible(false);
            this.retreatIcon.setVisible(false);
            this.retreatText.setVisible(false);
        } else {
            float f5 = ship2.canGoFTL() ? 1.0f : 0.4f;
            this.retreatButton.setVisible(true);
            this.retreatButton.setAlpha(f5);
            this.retreatIcon.setVisible(true);
            this.retreatIcon.setAlpha(f5);
            this.retreatText.setVisible(true);
            this.retreatText.setAlpha(f5);
            this.retreatButton.setX(f4);
            Text text7 = this.retreatText;
            text7.setX((f4 + 43.0f) - (text7.getWidthScaled() / 2.0f));
            Text text8 = this.retreatText;
            text8.setY(106.0f - text8.getHeightScaled());
            this.retreatIcon.setX(f4 + 8.0f);
            f4 += 90.0f;
        }
        this.doneButton.setX(f4);
        Text text9 = this.doneText;
        text9.setX((43.0f + f4) - (text9.getWidthScaled() / 2.0f));
        Text text10 = this.doneText;
        text10.setY(106.0f - text10.getHeightScaled());
        this.doneIcon.setX(8.0f + f4);
        float f6 = f4 + 86.0f;
        this.blackBackground.setWidth(f6);
        this.empireColorBackground.setWidth(f6);
    }

    private void setHpColor(Text text, float f2) {
        if (f2 >= 0.8f) {
            text.setColor(Color.GREEN);
        } else if (f2 < 0.8f && f2 > 0.4d) {
            text.setColor(Color.YELLOW);
        } else {
            text.setColor(Color.RED);
        }
    }

    private void setShipArmorAndShieldHitPoints() {
        Text text = this.armorHitPoints;
        text.setText(this.ship.getArmorHitPoints() + "/" + this.ship.getMaxArmorHitPoints() + " hp");
        if (this.ship.getShield().getID() != ShipComponentID.NO_SHIELDS) {
            this.shieldIcon.setVisible(true);
            Text text2 = this.shieldHitPoints;
            text2.setText(this.ship.getShieldHitPoints() + "/" + this.ship.getMaxShieldHitPoints() + " hp");
            setHpColor(this.shieldHitPoints, ((float) this.ship.getShieldHitPoints()) / ((float) this.ship.getMaxShieldHitPoints()));
            this.armorIcon.setX(this.shieldHitPoints.getX() + this.shieldHitPoints.getWidthScaled() + 10.0f);
        } else {
            this.shieldIcon.setVisible(false);
            this.shieldHitPoints.setText("");
            this.armorIcon.setX(131.0f);
        }
        this.armorHitPoints.setX(this.armorIcon.getX() + 20.0f);
        setHpColor(this.armorHitPoints, this.ship.getArmorHitPoints() / this.ship.getMaxArmorHitPoints());
    }

    private void shipButtonPressed() {
        this.selectedAction.setX(this.shipButton.getX());
        this.B.battleScene.selectMove();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void shipControlButtonPressed(ShipControlButton shipControlButton) {
        this.selectedAction.setX(shipControlButton.getX());
        this.B.battleScene.selectComponent(shipControlButton.l());
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void showShipDetailsPressed() {
        this.B.battleScene.showShipDetails(this.ship);
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void waitButtonPressed() {
        if (this.B.battleScene.anyShipsMoving()) {
            return;
        }
        this.B.battleScene.shipWait();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public boolean checkInputOnControl(int i, Point point) {
        ShipControlButton[] shipControlButtonArr;
        Point point2 = new Point(point.getX() - getX(), point.getY() - getY());
        super.checkInput(i, point2);
        if (hasMoved() || i != 1 || this.isDisabled) {
            return false;
        }
        if (point2.getX() > this.shipSprite.getX() && point2.getX() < this.shipSprite.getX() + this.shipSprite.getSize() && point2.getY() > this.shipSprite.getY() && point2.getY() < this.shipSprite.getY() + this.shipSprite.getSize()) {
            showShipDetailsPressed();
            resetDestructButton();
            return false;
        } else if (isClicked(this.shipButton, point2)) {
            shipButtonPressed();
            resetDestructButton();
            return false;
        } else {
            for (ShipControlButton shipControlButton : this.shipControlButtons) {
                if (shipControlButton.isVisible() && shipControlButton.isClicked(point2)) {
                    shipControlButtonPressed(shipControlButton);
                    resetDestructButton();
                    return false;
                }
            }
            if (isClicked(this.selfDestructButton, point2)) {
                selfDestructButtonPressed();
                return false;
            } else if (isClicked(this.waitButton, point2)) {
                waitButtonPressed();
                return false;
            } else if (isClicked(this.retreatButton, point2)) {
                retreatButtonPressed();
                return true;
            } else {
                if (isClicked(this.doneButton, point2)) {
                    doneButtonPressed();
                    return true;
                }
                return false;
            }
        }
    }

    public void close() {
        if (this.isOpen) {
            this.isOpen = false;
            unPressed();
            registerEntityModifier(new ScaleAtModifier(0.3f, 1.0f, 0.0f, getWidth() / 2.0f, getHeight() / 2.0f) { // from class: com.birdshel.Uciana.Controls.ShipControl.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: n */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    ShipControl.this.setScale(0.0f);
                }
            });
            registerEntityModifier(new AlphaModifier(0.3f, 1.0f, 0.0f));
        }
    }

    public float getHeight() {
        return this.blackBackground.getHeightScaled();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        ShipSpriteBattle shipSpriteBattle = this.B.shipSpriteBattlePool.get();
        this.shipSprite = shipSpriteBattle;
        shipSpriteBattle.setPosition(40.0f, 10.0f);
        this.shipSprite.setZIndex(1);
        attachChild(this.shipSprite);
        sortChildren();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public float getWidth() {
        return this.blackBackground.getWidthScaled();
    }

    public boolean hasWeaponSelected() {
        return this.selectedAction.getX() != this.shipButton.getX();
    }

    public void open() {
        setScale(1.0f);
        setAlpha(1.0f);
        this.isOpen = true;
    }

    public void pressed() {
        this.blackBackground.setAlpha(0.5f);
        this.positionWhenTouched = new Point(this.currentPosition.getX(), this.currentPosition.getY());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void releasePoolElements(ExtendedScene extendedScene, Object obj) {
        detachChild(this.shipSprite);
        this.B.shipSpriteBattlePool.push(this.shipSprite);
    }

    public void setControl(Ship ship, Point point) {
        this.ship = ship;
        resetDestructButton();
        this.selectedAction.setX(this.shipButton.getX());
        this.empireColorBackground.setCurrentTileIndex(ship.getEmpireID());
        this.shipSprite.setShip(ship, 86.0f, ship.getShipType().getScale() * 86.0f, 1, true);
        setShipArmorAndShieldHitPoints();
        setControlButtons();
        setX(580.0f - (getWidth() / 2.0f));
        this.currentPosition.setX(580.0f - (getWidth() / 2.0f));
        setY(0.0f);
        this.currentPosition.setY(0.0f);
        if (point.getY() < 360.0f) {
            setY(720.0f - getHeight());
            this.currentPosition.setY(720.0f - getHeight());
        }
        open();
        this.selectedAction.setX(this.shipButton.getX());
        this.B.battleScene.selectMove();
    }

    public void setControlPosition(float f2, float f3) {
        setPosition(f2, f3);
        this.currentPosition = new Point(f2, f3);
    }

    public void setDisabled(boolean z) {
        this.isDisabled = z;
    }

    public void unPressed() {
        this.blackBackground.setAlpha(0.8f);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        if (isVisible()) {
            setShipArmorAndShieldHitPoints();
            setControlButtons();
            this.shipSprite.updateShipDamage(this.ship);
        }
    }
}
