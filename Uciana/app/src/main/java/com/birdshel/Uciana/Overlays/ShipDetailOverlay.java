package com.birdshel.Uciana.Overlays;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.Armor;
import com.birdshel.Uciana.Ships.ShipComponents.Shield;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.SublightEngine;
import com.birdshel.Uciana.Ships.ShipComponents.TargetingComputer;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipSpriteBattle;
import com.birdshel.Uciana.Ships.ShipStatus;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipDetailOverlay extends ExtendedChildScene {
    private final Text armorDescription;
    private final TiledSprite armorIcon;
    private final Text armorName;
    private final Text beamAccuracy;
    private final Text[] componentDescriptions;
    private final TiledSprite[] componentIcons;
    private final Text[] componentNames;
    private final Sprite designBackground;
    private final Text designName;
    private final Text evasion;
    private int extraX;
    private final Text shieldDescription;
    private final TiledSprite shieldIcon;
    private final Text shieldName;
    private final TiledSprite[] shipComponentBackgrounds;
    private final Text shipID;
    private ShipSpriteBattle shipImage;
    private final TiledSprite shipTypeIcon;
    private final List<TiledSprite> statusIcons;
    private final Text sublightEngineDescription;
    private final TiledSprite sublightEngineIcon;
    private final Text sublightEngineName;
    private final Text targetingDescription;
    private final TiledSprite targetingIcon;
    private final Text targetingName;

    public ShipDetailOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.shipComponentBackgrounds = new TiledSprite[6];
        this.componentNames = new Text[6];
        this.componentIcons = new TiledSprite[6];
        this.componentDescriptions = new Text[6];
        this.statusIcons = new ArrayList();
        this.extraX = 0;
        if (getWidth() == 1480.0f) {
            this.extraX = 100;
        }
        Sprite t = t(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 720.0f);
        t.setAlpha(0.5f);
        Sprite t2 = t(this.extraX + 230, 90.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager, true);
        this.designBackground = t2;
        t2.setAlpha(0.8f);
        t2.setSize(820.0f, 555.0f);
        this.designName = u(this.extraX + 390, 110.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        Text u = u(0.0f, 110.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
        this.shipID = u;
        u.setColor(new Color(0.6f, 0.6f, 0.6f));
        this.shipTypeIcon = x(this.extraX + 390, 145.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, true);
        this.beamAccuracy = u(this.extraX + 390, 185.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
        this.evasion = u(this.extraX + 390, 215.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
        TiledSprite w = w(this.extraX + 240, 240.0f, game.graphics.empireColors, vertexBufferObjectManager, 2, true);
        w.setSize(385.0f, 75.0f);
        w.setAlpha(0.5f);
        this.armorIcon = x(this.extraX + 245, 252.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager, true);
        this.armorName = v(this.extraX + 300, 252.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager, true);
        this.armorDescription = v(this.extraX + 300, 282.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager, true);
        TiledSprite w2 = w(this.extraX + 645, 240.0f, game.graphics.empireColors, vertexBufferObjectManager, 2, true);
        w2.setSize(385.0f, 75.0f);
        w2.setAlpha(0.5f);
        this.shieldIcon = x(this.extraX + 650, 252.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager, true);
        this.shieldName = v(this.extraX + TypedValues.Transition.TYPE_INTERPOLATOR, 252.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager, true);
        this.shieldDescription = v(this.extraX + TypedValues.Transition.TYPE_INTERPOLATOR, 282.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager, true);
        TiledSprite w3 = w(this.extraX + 240, 320.0f, game.graphics.empireColors, vertexBufferObjectManager, 2, true);
        w3.setSize(385.0f, 75.0f);
        w3.setAlpha(0.5f);
        this.targetingIcon = x(this.extraX + 245, 332.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager, true);
        this.targetingName = v(this.extraX + 300, 332.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager, true);
        this.targetingDescription = v(this.extraX + 300, 358.0f, game.fonts.smallInfoFont, this.E, new TextOptions(AutoWrap.WORDS, 320.0f, HorizontalAlign.LEFT), vertexBufferObjectManager, true);
        TiledSprite w4 = w(this.extraX + 645, 320.0f, game.graphics.empireColors, vertexBufferObjectManager, 2, true);
        w4.setSize(385.0f, 75.0f);
        w4.setAlpha(0.5f);
        this.sublightEngineIcon = x(this.extraX + 650, 332.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager, true);
        this.sublightEngineName = v(this.extraX + TypedValues.Transition.TYPE_INTERPOLATOR, 332.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager, true);
        this.sublightEngineDescription = v(this.extraX + TypedValues.Transition.TYPE_INTERPOLATOR, 362.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager, true);
        setComponents(this.extraX + 240, 400.0f, 0, vertexBufferObjectManager);
        setComponents(this.extraX + 645, 400.0f, 1, vertexBufferObjectManager);
        setComponents(this.extraX + 240, 480.0f, 2, vertexBufferObjectManager);
        setComponents(this.extraX + 645, 480.0f, 3, vertexBufferObjectManager);
        setComponents(this.extraX + 240, 560.0f, 4, vertexBufferObjectManager);
        setComponents(this.extraX + 645, 560.0f, 5, vertexBufferObjectManager);
        for (int i = 1; i < 5; i++) {
            TiledSprite x = x((getWidth() - 230.0f) - (i * 40), 90.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, false);
            x.setSize(40.0f, 40.0f);
            this.statusIcons.add(x);
        }
    }

    private void setComponents(float f2, float f3, int i, VertexBufferObjectManager vertexBufferObjectManager) {
        this.shipComponentBackgrounds[i] = w(f2, f3, this.C.graphics.empireColors, vertexBufferObjectManager, 2, true);
        this.shipComponentBackgrounds[i].setSize(385.0f, 75.0f);
        this.shipComponentBackgrounds[i].setAlpha(0.5f);
        float f4 = 60.0f + f2;
        this.componentNames[i] = v(f4, f3 + 12.0f, this.C.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager, true);
        this.componentIcons[i] = w(f2 + 5.0f, f3 + 14.0f, this.C.graphics.shipComponentIconsTexture, vertexBufferObjectManager, 0, true);
        this.componentIcons[i].setSize(45.0f, 45.0f);
        this.componentDescriptions[i] = v(f4, f3 + 40.0f, this.C.fonts.smallInfoFont, this.E, new TextOptions(AutoWrap.WORDS, 320.0f, HorizontalAlign.LEFT), vertexBufferObjectManager, true);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            this.C.sounds.playSystemObjectPressSound();
            Game game = this.C;
            game.vibrate(game.BUTTON_VIBRATE);
            back();
        }
    }

    public void getPoolElements() {
        ShipSpriteBattle shipSpriteBattle = this.C.shipSpriteBattlePool.get();
        this.shipImage = shipSpriteBattle;
        shipSpriteBattle.setPosition(this.extraX + 230, 90.0f);
        attachChild(this.shipImage);
    }

    public void releasePoolElements() {
        detachChild(this.shipImage);
        this.C.shipSpriteBattlePool.push(this.shipImage);
    }

    public void setOverlay(Ship ship) {
        TiledSprite[] tiledSpriteArr;
        int size = ship.getShipComponents().size();
        if (size > 4) {
            this.designBackground.setHeight(555.0f);
        } else if (size > 2) {
            this.designBackground.setHeight(475.0f);
        } else {
            this.designBackground.setHeight(395.0f);
        }
        ShipType shipType = ship.getShipType();
        this.shipImage.setShip(ship, 150.0f, 1.5f * shipType.getSelectScreenSize(), 1, true);
        this.shipTypeIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
        if (ship.isCombatShip()) {
            this.shipImage.updateShipDamage(ship);
        }
        this.designName.setText(ship.getName());
        this.shipID.setText(ship.getID());
        this.shipID.setX(this.shipTypeIcon.getX() + this.shipTypeIcon.getWidthScaled() + 10.0f);
        this.shipID.setY((this.shipTypeIcon.getY() + (this.shipTypeIcon.getHeightScaled() / 2.0f)) - (this.shipID.getHeightScaled() / 2.0f));
        int i = 0;
        this.beamAccuracy.setText(this.C.getActivity().getString(R.string.ship_design_beam_accuracy, new Object[]{Integer.valueOf(ship.getTargetAccuracyBonus())}));
        this.evasion.setText(this.C.getActivity().getString(R.string.ship_design_beam_evasion, new Object[]{Integer.valueOf(ship.getEvasionBonus())}));
        Armor armor = ship.getArmor();
        this.armorIcon.setCurrentTileIndex(armor.getIconIndex());
        this.armorName.setText(armor.getName());
        if (ship.getArmorHitPoints() != ship.getMaxArmorHitPoints()) {
            this.armorDescription.setText(ship.getArmorHitPoints() + "/" + ship.getMaxArmorHitPoints() + " hp");
        } else {
            this.armorDescription.setText(ship.getArmorHitPoints() + " hp");
        }
        float armorHitPoints = ship.getArmorHitPoints() / ship.getMaxArmorHitPoints();
        if (armorHitPoints >= 0.8f) {
            this.armorDescription.setColor(Color.GREEN);
        } else if (armorHitPoints < 0.8f && armorHitPoints > 0.4d) {
            this.armorDescription.setColor(Color.YELLOW);
        } else {
            this.armorDescription.setColor(Color.RED);
        }
        Shield shield = ship.getShield();
        this.shieldIcon.setCurrentTileIndex(shield.getIconIndex());
        this.shieldName.setText(shield.getName());
        this.shieldDescription.setText(this.C.getActivity().getString(R.string.component_selector_shield_description, new Object[]{Integer.valueOf(shield.getStrengthMultiplier() * ship.getShipType().getSizeClass()), Integer.valueOf(shield.getAbsorption()), Integer.valueOf((int) (shield.getRechargeRate() * 100.0f))}));
        TargetingComputer targetingComputer = ship.getTargetingComputer();
        this.targetingIcon.setCurrentTileIndex(targetingComputer.getIconIndex());
        this.targetingName.setText(targetingComputer.getName());
        this.targetingDescription.setText(this.C.getActivity().getString(R.string.component_selector_targeting_description, new Object[]{Integer.valueOf(targetingComputer.getTargetingBonus()), Integer.valueOf(targetingComputer.getDamageBonus())}));
        SublightEngine sublightEngine = ship.getSublightEngine();
        this.sublightEngineIcon.setCurrentTileIndex(sublightEngine.getIconIndex());
        this.sublightEngineName.setText(sublightEngine.getName());
        this.sublightEngineDescription.setText(this.C.getActivity().getString(R.string.component_selector_engine_description, new Object[]{Integer.valueOf(sublightEngine.getCombatSpeed())}));
        if (ship.getShipType().isStation()) {
            this.sublightEngineDescription.setText(this.C.getActivity().getString(R.string.ship_detail_no_movement));
        }
        int i2 = 0;
        for (TiledSprite tiledSprite : this.shipComponentBackgrounds) {
            tiledSprite.setVisible(false);
            this.componentNames[i2].setVisible(false);
            this.componentIcons[i2].setVisible(false);
            this.componentDescriptions[i2].setVisible(false);
            if (size > i2) {
                tiledSprite.setVisible(true);
                this.componentNames[i2].setVisible(true);
                this.componentIcons[i2].setVisible(true);
                this.componentDescriptions[i2].setVisible(true);
                ShipComponent shipComponent = ship.getShipComponent(i2);
                if (shipComponent instanceof Weapon) {
                    tiledSprite.setCurrentTileIndex(0);
                    if (shipComponent.getComponentCount() > 1) {
                        this.componentNames[i2].setText(this.C.getActivity().getString(R.string.ship_design_component_multiple, new Object[]{Integer.valueOf(shipComponent.getComponentCount()), shipComponent.getName()}));
                    } else {
                        this.componentNames[i2].setText(this.C.getActivity().getString(R.string.ship_design_component_single, new Object[]{shipComponent.getName()}));
                    }
                } else {
                    tiledSprite.setCurrentTileIndex(1);
                    this.componentNames[i2].setText(shipComponent.getName());
                }
                this.componentIcons[i2].setCurrentTileIndex(shipComponent.getIconIndex());
                this.componentDescriptions[i2].setText(shipComponent.getDescription());
            }
            i2++;
        }
        for (TiledSprite tiledSprite2 : this.statusIcons) {
            tiledSprite2.setVisible(false);
        }
        for (ShipStatus shipStatus : ship.getStatuses()) {
            this.statusIcons.get(i).setVisible(true);
            this.statusIcons.get(i).setCurrentTileIndex(shipStatus.getImageIndex());
            i++;
        }
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
