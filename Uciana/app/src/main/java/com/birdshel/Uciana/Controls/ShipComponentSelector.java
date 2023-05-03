package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.Armor;
import com.birdshel.Uciana.Ships.ShipComponents.Shield;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.SpecialComponent;
import com.birdshel.Uciana.Ships.ShipComponents.SublightEngine;
import com.birdshel.Uciana.Ships.ShipComponents.TargetingComputer;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import java.nio.CharBuffer;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipComponentSelector extends Entity {
    private TiledSprite background;
    private TiledSprite componentIcon;
    private Text description;
    private Game game;
    private int index;
    private boolean isDisabled;
    private final CharSequence maxTextSize;
    private TiledSprite minusBackground;
    private TiledSprite minusIcon;
    private Text name;
    private TiledSprite plusBackground;
    private TiledSprite plusIcon;
    private Sprite pressSprite;
    private TiledSprite selectedWeaponBackground;
    private ShipComponent shipComponent;
    private ShipComponentSelectorType shipComponentSelectorType;
    private TiledSprite specialBackground;
    private TiledSprite specialPlusIcon;
    private final TextOptions textOptions;
    private TiledSprite weaponBackground;
    private TiledSprite weaponPlusIcon;

    public ShipComponentSelector(float f2, float f3, Game game, VertexBufferObjectManager vertexBufferObjectManager, ShipComponentSelectorType shipComponentSelectorType) {
        this.isDisabled = false;
        this.maxTextSize = CharBuffer.wrap(new char[255]);
        this.textOptions = new TextOptions(HorizontalAlign.CENTER);
        setComponentSelector(f2, f3, game, vertexBufferObjectManager, shipComponentSelectorType, 0);
    }

    private void checkPress(Point point) {
        ShipComponentSelectorType shipComponentSelectorType = this.shipComponentSelectorType;
        if (shipComponentSelectorType == ShipComponentSelectorType.WEAPON) {
            if (isPressed(this.selectedWeaponBackground, point)) {
                setPress(this.selectedWeaponBackground);
            }
            if (isPressed(this.plusBackground, point)) {
                setPress(this.plusBackground);
            }
            if (isPressed(this.minusBackground, point)) {
                setPress(this.minusBackground);
            }
        } else if (shipComponentSelectorType == ShipComponentSelectorType.WEAPON_OR_SPECIAL) {
            if (isPressed(this.weaponBackground, point)) {
                setPress(this.weaponBackground);
            }
            if (isPressed(this.specialBackground, point)) {
                setPress(this.specialBackground);
            }
        } else if (isPressed(this.background, point)) {
            setPress(this.background);
        }
    }

    private void checkRelease(Point point) {
        this.pressSprite.setVisible(false);
        ShipComponentSelectorType shipComponentSelectorType = this.shipComponentSelectorType;
        ShipComponentSelectorType shipComponentSelectorType2 = ShipComponentSelectorType.WEAPON;
        if (shipComponentSelectorType == shipComponentSelectorType2) {
            if (isPressed(this.selectedWeaponBackground, point)) {
                this.game.sounds.playBoxPressSound();
                Game game = this.game;
                game.vibrate(game.BUTTON_VIBRATE);
                this.game.shipDesignScene.showSelectComponentOverlay(this.shipComponentSelectorType, this.index, true);
            }
            if (isPressed(this.plusBackground, point) && this.game.shipDesignScene.getAvailableSpace() >= this.game.getCurrentEmpire().getComponentSpaceAfterMiniaturization(this.shipComponent)) {
                ShipComponent shipComponent = this.shipComponent;
                shipComponent.setComponentCount(shipComponent.getComponentCount() + 1);
                set((Weapon) this.shipComponent);
                this.game.shipDesignScene.updateProductionCostAndSpace();
                this.game.sounds.playBoxPressSound();
                Game game2 = this.game;
                game2.vibrate(game2.BUTTON_VIBRATE);
            }
            if (!isPressed(this.minusBackground, point) || this.shipComponent.getComponentCount() <= 1) {
                return;
            }
            ShipComponent shipComponent2 = this.shipComponent;
            shipComponent2.setComponentCount(shipComponent2.getComponentCount() - 1);
            set((Weapon) this.shipComponent);
            this.game.shipDesignScene.updateProductionCostAndSpace();
            this.game.sounds.playBoxPressSound();
            Game game3 = this.game;
            game3.vibrate(game3.BUTTON_VIBRATE);
        } else if (shipComponentSelectorType == ShipComponentSelectorType.WEAPON_OR_SPECIAL) {
            if (isPressed(this.weaponBackground, point)) {
                this.game.sounds.playBoxPressSound();
                Game game4 = this.game;
                game4.vibrate(game4.BUTTON_VIBRATE);
                this.game.shipDesignScene.showSelectComponentOverlay(shipComponentSelectorType2, this.index, false);
            } else if (isPressed(this.specialBackground, point)) {
                this.game.sounds.playBoxPressSound();
                Game game5 = this.game;
                game5.vibrate(game5.BUTTON_VIBRATE);
                this.game.shipDesignScene.showSelectComponentOverlay(ShipComponentSelectorType.SPECIAL, this.index, false);
            }
        } else if (isPressed(this.background, point)) {
            this.game.sounds.playBoxPressSound();
            Game game6 = this.game;
            game6.vibrate(game6.BUTTON_VIBRATE);
            this.game.shipDesignScene.showSelectComponentOverlay(this.shipComponentSelectorType, this.index, true);
        }
    }

    private boolean isPressed(Sprite sprite, Point point) {
        return point.getX() > getX() + sprite.getX() && point.getX() < (getX() + sprite.getX()) + sprite.getWidthScaled() && point.getY() > getY() + sprite.getY() && point.getY() < (getY() + sprite.getY()) + sprite.getHeightScaled();
    }

    private void setCommon() {
        this.componentIcon.setVisible(true);
        this.componentIcon.setCurrentTileIndex(this.shipComponent.getIconIndex());
        this.name.setVisible(true);
        this.name.setText(this.shipComponent.getName());
        this.description.setVisible(true);
        this.isDisabled = false;
    }

    private void setComponentSelector(float f2, float f3, Game game, VertexBufferObjectManager vertexBufferObjectManager, ShipComponentSelectorType shipComponentSelectorType, int i) {
        setPosition(f2, f3);
        this.game = game;
        this.shipComponentSelectorType = shipComponentSelectorType;
        this.index = i;
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.pressSprite = sprite;
        sprite.setVisible(false);
        attachChild(this.pressSprite);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.background = tiledSprite;
        tiledSprite.setSize(395.0f, 75.0f);
        this.background.setAlpha(0.5f);
        attachChild(this.background);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.weaponBackground = tiledSprite2;
        tiledSprite2.setSize(195.0f, 75.0f);
        this.weaponBackground.setAlpha(0.5f);
        this.weaponBackground.setCurrentTileIndex(0);
        attachChild(this.weaponBackground);
        TiledSprite tiledSprite3 = new TiledSprite(72.0f, 12.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.weaponPlusIcon = tiledSprite3;
        tiledSprite3.setScaleCenter(0.0f, 0.0f);
        this.weaponPlusIcon.setSize(50.0f, 50.0f);
        TiledSprite tiledSprite4 = this.weaponPlusIcon;
        GameIconEnum gameIconEnum = GameIconEnum.ADD;
        tiledSprite4.setCurrentTileIndex(gameIconEnum.ordinal());
        attachChild(this.weaponPlusIcon);
        TiledSprite tiledSprite5 = new TiledSprite(200.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.specialBackground = tiledSprite5;
        tiledSprite5.setSize(195.0f, 75.0f);
        this.specialBackground.setAlpha(0.5f);
        this.specialBackground.setCurrentTileIndex(1);
        attachChild(this.specialBackground);
        TiledSprite tiledSprite6 = new TiledSprite(272.0f, 12.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.specialPlusIcon = tiledSprite6;
        tiledSprite6.setScaleCenter(0.0f, 0.0f);
        this.specialPlusIcon.setSize(50.0f, 50.0f);
        this.specialPlusIcon.setCurrentTileIndex(gameIconEnum.ordinal());
        attachChild(this.specialPlusIcon);
        TiledSprite tiledSprite7 = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.selectedWeaponBackground = tiledSprite7;
        tiledSprite7.setSize(295.0f, 75.0f);
        this.selectedWeaponBackground.setAlpha(0.5f);
        this.selectedWeaponBackground.setCurrentTileIndex(0);
        attachChild(this.selectedWeaponBackground);
        TiledSprite tiledSprite8 = new TiledSprite(300.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.plusBackground = tiledSprite8;
        tiledSprite8.setSize(45.0f, 75.0f);
        this.plusBackground.setAlpha(0.5f);
        this.plusBackground.setCurrentTileIndex(0);
        attachChild(this.plusBackground);
        TiledSprite tiledSprite9 = new TiledSprite(297.0f, 12.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.plusIcon = tiledSprite9;
        tiledSprite9.setScaleCenter(0.0f, 0.0f);
        this.plusIcon.setSize(50.0f, 50.0f);
        this.plusIcon.setCurrentTileIndex(gameIconEnum.ordinal());
        attachChild(this.plusIcon);
        TiledSprite tiledSprite10 = new TiledSprite(350.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.minusBackground = tiledSprite10;
        tiledSprite10.setSize(45.0f, 75.0f);
        this.minusBackground.setAlpha(0.5f);
        this.minusBackground.setCurrentTileIndex(0);
        attachChild(this.minusBackground);
        TiledSprite tiledSprite11 = new TiledSprite(347.0f, 12.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.minusIcon = tiledSprite11;
        tiledSprite11.setScaleCenter(0.0f, 0.0f);
        this.minusIcon.setSize(50.0f, 50.0f);
        this.minusIcon.setCurrentTileIndex(GameIconEnum.MINUS.ordinal());
        attachChild(this.minusIcon);
        TiledSprite tiledSprite12 = new TiledSprite(5.0f, 17.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager);
        this.componentIcon = tiledSprite12;
        tiledSprite12.setSize(40.0f, 40.0f);
        attachChild(this.componentIcon);
        Text text = new Text(60.0f, 10.0f, game.fonts.smallInfoFont, this.maxTextSize, this.textOptions, vertexBufferObjectManager);
        this.name = text;
        attachChild(text);
        Text text2 = new Text(60.0f, 38.0f, game.fonts.smallInfoFont, this.maxTextSize, new TextOptions(AutoWrap.WORDS, 335.0f, HorizontalAlign.LEFT), vertexBufferObjectManager);
        this.description = text2;
        attachChild(text2);
    }

    private void setPress(Sprite sprite) {
        this.pressSprite.setX(sprite.getX());
        this.pressSprite.setY(sprite.getY());
        this.pressSprite.setSize(sprite.getWidthScaled(), sprite.getHeightScaled());
        this.pressSprite.setVisible(true);
    }

    private void setSpritesInvisible() {
        this.background.setVisible(false);
        this.componentIcon.setVisible(false);
        this.name.setVisible(false);
        this.description.setVisible(false);
        this.weaponBackground.setVisible(false);
        this.specialBackground.setVisible(false);
        this.selectedWeaponBackground.setVisible(false);
        this.plusBackground.setVisible(false);
        this.plusIcon.setVisible(false);
        this.minusBackground.setVisible(false);
        this.minusIcon.setVisible(false);
        this.weaponPlusIcon.setVisible(false);
        this.specialPlusIcon.setVisible(false);
        this.background.setAlpha(0.5f);
    }

    public void checkInput(int i, Point point) {
        if (this.isDisabled) {
            return;
        }
        if (i == 0) {
            checkPress(point);
        } else if (i == 1) {
            checkRelease(point);
        } else if (i != 2) {
        } else {
            this.pressSprite.setVisible(false);
            checkPress(point);
        }
    }

    public void clear() {
        setSpritesInvisible();
        this.shipComponent = null;
        this.isDisabled = true;
        this.shipComponentSelectorType = ShipComponentSelectorType.WEAPON_OR_SPECIAL;
    }

    public void disable() {
        clear();
        this.background.setCurrentTileIndex(2);
        this.background.setAlpha(0.25f);
        this.background.setVisible(true);
    }

    public void set(Armor armor, int i, List<ShipComponent> list) {
        setSpritesInvisible();
        this.background.setVisible(true);
        this.background.setCurrentTileIndex(2);
        this.shipComponent = armor;
        setCommon();
        int armorMultiplier = (int) (i * 100 * armor.getArmorMultiplier());
        for (ShipComponent shipComponent : list) {
            ShipComponentID id = shipComponent.getID();
            ShipComponentID shipComponentID = ShipComponentID.HARDENED_ALLOY;
            if (id == shipComponentID) {
                float f2 = armorMultiplier;
                armorMultiplier = (int) (f2 + (((SpecialComponent) ShipComponents.get(shipComponentID)).getEffectValue() * f2));
            }
        }
        this.description.setText(this.game.getActivity().getString(R.string.component_selector_armor_description, new Object[]{Integer.valueOf(armorMultiplier)}));
    }

    public void setForWeaponOrSpecialChoice() {
        setSpritesInvisible();
        this.weaponBackground.setVisible(true);
        this.specialBackground.setVisible(true);
        this.weaponPlusIcon.setVisible(true);
        this.specialPlusIcon.setVisible(true);
        this.isDisabled = false;
    }

    public ShipComponentSelector(float f2, float f3, Game game, VertexBufferObjectManager vertexBufferObjectManager, ShipComponentSelectorType shipComponentSelectorType, int i) {
        this.isDisabled = false;
        this.maxTextSize = CharBuffer.wrap(new char[255]);
        this.textOptions = new TextOptions(HorizontalAlign.CENTER);
        setComponentSelector(f2, f3, game, vertexBufferObjectManager, shipComponentSelectorType, i);
    }

    public void set(Shield shield, int i) {
        setSpritesInvisible();
        this.background.setVisible(true);
        this.background.setCurrentTileIndex(2);
        this.shipComponent = shield;
        setCommon();
        this.description.setText(this.game.getActivity().getString(R.string.component_selector_shield_description, new Object[]{Integer.valueOf(shield.getStrengthMultiplier() * i), Integer.valueOf(shield.getAbsorption()), Integer.valueOf((int) (shield.getRechargeRate() * 100.0f))}));
    }

    public void set(TargetingComputer targetingComputer) {
        setSpritesInvisible();
        this.background.setVisible(true);
        this.background.setCurrentTileIndex(2);
        this.shipComponent = targetingComputer;
        setCommon();
        this.description.setText(this.game.getActivity().getString(R.string.component_selector_targeting_description, new Object[]{Integer.valueOf(targetingComputer.getTargetingBonus()), Integer.valueOf(targetingComputer.getDamageBonus())}));
    }

    public void set(SublightEngine sublightEngine) {
        setSpritesInvisible();
        this.background.setVisible(true);
        this.background.setCurrentTileIndex(2);
        this.shipComponent = sublightEngine;
        setCommon();
        this.description.setText(this.game.getActivity().getString(R.string.component_selector_engine_description, new Object[]{Integer.valueOf(sublightEngine.getCombatSpeed())}));
    }

    public void set(Weapon weapon) {
        setSpritesInvisible();
        this.selectedWeaponBackground.setVisible(true);
        this.plusBackground.setVisible(true);
        this.plusIcon.setVisible(true);
        this.minusBackground.setVisible(true);
        this.minusIcon.setVisible(true);
        this.shipComponent = weapon;
        setCommon();
        String name = weapon.getName();
        if (weapon.getComponentCount() > 1) {
            this.name.setText(this.game.getActivity().getString(R.string.component_selector_weapon_count, new Object[]{Integer.valueOf(weapon.getComponentCount()), name}));
        } else {
            this.name.setText(this.game.getActivity().getString(R.string.component_selector_weapon_single, new Object[]{name}));
        }
        this.description.setText(this.game.getActivity().getString(R.string.component_selector_weapon_description, new Object[]{Integer.valueOf(weapon.getMinDamage()), Integer.valueOf(weapon.getMaxDamage())}));
        this.shipComponentSelectorType = ShipComponentSelectorType.WEAPON;
    }

    public void set(SpecialComponent specialComponent) {
        setSpritesInvisible();
        this.background.setVisible(true);
        this.background.setCurrentTileIndex(1);
        this.shipComponent = specialComponent;
        setCommon();
        this.description.setText(specialComponent.getDescription());
        this.shipComponentSelectorType = ShipComponentSelectorType.SPECIAL;
    }
}
