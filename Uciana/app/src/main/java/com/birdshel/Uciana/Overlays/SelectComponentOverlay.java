package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Controls.ShipComponentSelectorType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.ComponentIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.Armor;
import com.birdshel.Uciana.Ships.ShipComponents.Shield;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.SpecialComponent;
import com.birdshel.Uciana.Ships.ShipComponents.SublightEngine;
import com.birdshel.Uciana.Ships.ShipComponents.TargetingComputer;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponType;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.Uciana;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SelectComponentOverlay extends ExtendedChildScene {
    private static final int ITEM_HEIGHT = 105;
    private static final int ITEM_WIDTH = 600;
    private static final int LIST_BOTTOM = 720;
    private static final int LIST_TOP = 0;
    private static final int MAX_LIST_SIZE = 720;
    private final TiledSprite allWeaponTypesButton;
    private final TiledSprite allWeaponTypesIcon1;
    private final TiledSprite allWeaponTypesIcon2;
    private final TiledSprite allWeaponTypesIcon3;
    private final TiledSprite allWeaponTypesIcon4;
    private List<Armor> armors;
    private int availableSpace;
    private final List<Entity> backgrounds;
    private final TiledSprite beamWeaponTypeButton;
    private final TiledSprite beamWeaponTypeIcon;
    private final TiledSprite bombWeaponTypeButton;
    private final TiledSprite bombWeaponTypeIcon;
    private final VertexBufferObjectManager bufferManager;
    private final TiledSprite chargeWeaponTypeButton;
    private final TiledSprite chargeWeaponTypeIcon;
    private final TiledSprite closeButton;
    private final List<Entity> elements;
    private boolean hasSelected;
    private boolean isScroll;
    private float lastY;
    private final Scene list;
    private int listSize;
    private final MessageOverlay messageOverlay;
    private float pressedY;
    private final ScrollBarControl scrollBar;
    private final Sprite selectPress;
    private final TiledSprite selectType;
    private ShipComponentSelectorType selectedComponent;
    private int selectorIndex;
    private List<Shield> shields;
    private ShipType shipType;
    private List<SpecialComponent> specialComponents;
    private List<SublightEngine> sublightEngines;
    private List<TargetingComputer> targetingComputers;
    private final TiledSprite torpedoWeaponTypeButton;
    private final TiledSprite torpedoWeaponTypeIcon;
    private List<Weapon> weapons;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Overlays.SelectComponentOverlay$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1389a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[WeaponType.values().length];
            b = iArr;
            try {
                iArr[WeaponType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[WeaponType.BOMB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[WeaponType.BEAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[WeaponType.TORPEDO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ShipComponentSelectorType.values().length];
            f1389a = iArr2;
            try {
                iArr2[ShipComponentSelectorType.ARMOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1389a[ShipComponentSelectorType.SHIELD.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1389a[ShipComponentSelectorType.TARGETING_COMPUTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1389a[ShipComponentSelectorType.ENGINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1389a[ShipComponentSelectorType.WEAPON.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1389a[ShipComponentSelectorType.SPECIAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public SelectComponentOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.elements = new ArrayList();
        this.backgrounds = new ArrayList();
        this.isScroll = false;
        this.bufferManager = vertexBufferObjectManager;
        Scene scene = new Scene();
        this.list = scene;
        scene.setPosition(340.0f, 0.0f);
        scene.setBackgroundEnabled(false);
        attachChild(scene);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.selectPress = sprite;
        sprite.setVisible(false);
        sprite.setSize(600.0f, 100.0f);
        sprite.setAlpha(0.6f);
        scene.attachChild(sprite);
        ScrollBarControl scrollBarControl = new ScrollBarControl(950.0f, 0.0f, 105, 720.0f, game, vertexBufferObjectManager);
        this.scrollBar = scrollBarControl;
        attachChild(scrollBarControl);
        TiledSprite w = w(getWidth() - 120.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w;
        n(w);
        this.selectType = w(0.0f, 145.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), true);
        ITiledTextureRegion iTiledTextureRegion = game.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK;
        TiledSprite w2 = w(0.0f, 145.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.allWeaponTypesButton = w2;
        n(w2);
        ITiledTextureRegion iTiledTextureRegion2 = game.graphics.shipComponentIconsTexture;
        ComponentIconEnum componentIconEnum = ComponentIconEnum.SPACIAL_CHARGE;
        TiledSprite w3 = w(70.0f, 163.0f, iTiledTextureRegion2, vertexBufferObjectManager, componentIconEnum.ordinal(), true);
        this.allWeaponTypesIcon1 = w3;
        w3.setSize(50.0f, 50.0f);
        ITiledTextureRegion iTiledTextureRegion3 = game.graphics.shipComponentIconsTexture;
        ComponentIconEnum componentIconEnum2 = ComponentIconEnum.TORPEDO;
        TiledSprite w4 = w(50.0f, 163.0f, iTiledTextureRegion3, vertexBufferObjectManager, componentIconEnum2.ordinal(), true);
        this.allWeaponTypesIcon2 = w4;
        w4.setSize(50.0f, 50.0f);
        ITiledTextureRegion iTiledTextureRegion4 = game.graphics.shipComponentIconsTexture;
        ComponentIconEnum componentIconEnum3 = ComponentIconEnum.NUCLUEAR_BOMB;
        TiledSprite w5 = w(30.0f, 163.0f, iTiledTextureRegion4, vertexBufferObjectManager, componentIconEnum3.ordinal(), true);
        this.allWeaponTypesIcon3 = w5;
        w5.setSize(50.0f, 50.0f);
        ITiledTextureRegion iTiledTextureRegion5 = game.graphics.shipComponentIconsTexture;
        ComponentIconEnum componentIconEnum4 = ComponentIconEnum.LAZER;
        TiledSprite w6 = w(10.0f, 163.0f, iTiledTextureRegion5, vertexBufferObjectManager, componentIconEnum4.ordinal(), true);
        this.allWeaponTypesIcon4 = w6;
        w6.setSize(50.0f, 50.0f);
        TiledSprite w7 = w(0.0f, 231.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.beamWeaponTypeButton = w7;
        n(w7);
        TiledSprite w8 = w(22.0f, 236.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager, componentIconEnum4.ordinal(), true);
        this.beamWeaponTypeIcon = w8;
        w8.setSize(75.0f, 75.0f);
        TiledSprite w9 = w(0.0f, 317.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.bombWeaponTypeButton = w9;
        n(w9);
        TiledSprite w10 = w(22.0f, 322.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager, componentIconEnum3.ordinal(), true);
        this.bombWeaponTypeIcon = w10;
        w10.setSize(75.0f, 75.0f);
        TiledSprite w11 = w(0.0f, 403.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.torpedoWeaponTypeButton = w11;
        n(w11);
        TiledSprite w12 = w(22.0f, 408.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager, componentIconEnum2.ordinal(), true);
        this.torpedoWeaponTypeIcon = w12;
        w12.setSize(75.0f, 75.0f);
        TiledSprite w13 = w(0.0f, 489.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.chargeWeaponTypeButton = w13;
        n(w13);
        TiledSprite w14 = w(22.0f, 494.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager, componentIconEnum.ordinal(), true);
        this.chargeWeaponTypeIcon = w14;
        w14.setSize(75.0f, 75.0f);
        this.messageOverlay = new MessageOverlay(game, vertexBufferObjectManager);
    }

    private void allWeaponTypesButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        setWeaponType(this.allWeaponTypesButton.getY(), WeaponType.NONE);
    }

    private void beamWeaponTypeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        setWeaponType(this.beamWeaponTypeButton.getY(), WeaponType.BEAM);
    }

    private void bombWeaponTypeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        setWeaponType(this.bombWeaponTypeButton.getY(), WeaponType.BOMB);
    }

    private void chargeWeaponTypeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        setWeaponType(this.chargeWeaponTypeButton.getY(), WeaponType.SPACIAL_CHARGE);
    }

    private void checkActionDown(Point point) {
        if (point.getX() <= this.list.getX() || point.getX() >= this.list.getX() + 600.0f) {
            return;
        }
        int y = ((int) (point.getY() - this.list.getY())) / 105;
        if (point.getY() > 0.0f) {
            this.pressedY = point.getY();
            this.lastY = point.getY();
        }
        if (this.isScroll || point.getY() <= 0.0f) {
            return;
        }
        checkPressed(y);
    }

    private void checkActionMove(Point point) {
        int i;
        this.selectPress.setVisible(false);
        if (point.getX() <= this.list.getX() || point.getX() >= this.list.getX() + 600.0f) {
            return;
        }
        int y = ((int) (point.getY() - this.list.getY())) / 105;
        int i2 = this.listSize;
        ShipComponentSelectorType shipComponentSelectorType = this.selectedComponent;
        if (shipComponentSelectorType == ShipComponentSelectorType.WEAPON || shipComponentSelectorType == ShipComponentSelectorType.SPECIAL) {
            i2++;
        }
        if (point.getY() > 0.0f && (i = i2 * 105) > 720) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y2 = this.list.getY() - (this.lastY - point.getY());
            if (y2 > 0.0f) {
                y2 = 0.0f;
            }
            float f2 = (i - 720) * (-1);
            if (y2 < f2) {
                y2 = f2;
            }
            this.list.setY(y2);
            this.lastY = point.getY();
            this.scrollBar.update(y2);
        }
        if (this.isScroll || point.getY() <= 0.0f) {
            return;
        }
        checkPressed(y);
    }

    private void checkActionUp(Point point) {
        this.selectPress.setVisible(false);
        if (this.isScroll) {
            this.isScroll = false;
            return;
        }
        if (point.getX() > this.list.getX() && point.getX() < this.list.getX() + 600.0f) {
            componentItemPressed(((int) (point.getY() - this.list.getY())) / 105);
        }
        if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
        if (q(this.allWeaponTypesButton, point)) {
            allWeaponTypesButtonPressed();
        }
        if (q(this.beamWeaponTypeButton, point)) {
            beamWeaponTypeButtonPressed();
        }
        if (q(this.bombWeaponTypeButton, point)) {
            bombWeaponTypeButtonPressed();
        }
        if (q(this.torpedoWeaponTypeButton, point)) {
            torpedoWeaponTypeButtonPressed();
        }
        if (q(this.chargeWeaponTypeButton, point)) {
            chargeWeaponTypeButtonPressed();
        }
    }

    private void checkPressed(int i) {
        int i2 = this.listSize;
        ShipComponentSelectorType shipComponentSelectorType = this.selectedComponent;
        if (shipComponentSelectorType == ShipComponentSelectorType.WEAPON || shipComponentSelectorType == ShipComponentSelectorType.SPECIAL) {
            i2++;
        }
        if (i >= i2 || this.backgrounds.get(i).getAlpha() == 0.4f) {
            return;
        }
        this.selectPress.setVisible(true);
        this.selectPress.setY(i * 105);
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void componentItemPressed(int i) {
        int i2 = this.listSize;
        ShipComponentSelectorType shipComponentSelectorType = this.selectedComponent;
        if (shipComponentSelectorType == ShipComponentSelectorType.WEAPON || shipComponentSelectorType == ShipComponentSelectorType.SPECIAL) {
            i2++;
        }
        if (i < i2) {
            if (this.backgrounds.get(i).getAlpha() == 0.41f) {
                this.messageOverlay.setOverlay(new TextMessage(this.C.getActivity().getString(R.string.select_component_one_per_ship)));
                setChildScene(this.messageOverlay, false, false, true);
            } else if (this.backgrounds.get(i).getAlpha() == 0.4f) {
                this.messageOverlay.setOverlay(new TextMessage(this.C.getActivity().getString(R.string.select_component_not_enough_space)));
                setChildScene(this.messageOverlay, false, false, true);
            } else {
                switch (AnonymousClass1.f1389a[this.selectedComponent.ordinal()]) {
                    case 1:
                        this.C.shipDesignScene.setSelectedArmor(this.armors.get(i));
                        break;
                    case 2:
                        this.C.shipDesignScene.setSelectedShield(this.shields.get(i));
                        break;
                    case 3:
                        this.C.shipDesignScene.setSelectedTargeting(this.targetingComputers.get(i));
                        break;
                    case 4:
                        this.C.shipDesignScene.setSelectedEngine(this.sublightEngines.get(i));
                        break;
                    case 5:
                        if (i == 0) {
                            this.C.shipDesignScene.removeComponent(this.selectorIndex);
                            break;
                        } else {
                            this.C.shipDesignScene.setSelectedComponent(new Weapon(this.weapons.get(i - 1)), this.selectorIndex);
                            break;
                        }
                    case 6:
                        if (i == 0) {
                            this.C.shipDesignScene.removeComponent(this.selectorIndex);
                            break;
                        } else {
                            this.C.shipDesignScene.setSelectedComponent(new SpecialComponent(this.specialComponents.get(i - 1)), this.selectorIndex);
                            break;
                        }
                }
                this.C.sounds.playBoxPressSound();
                Game game = this.C;
                game.vibrate(game.BUTTON_VIBRATE);
                back();
            }
        }
    }

    private void resetOverlay() {
        this.list.setPosition(340.0f, 0.0f);
        p(this.elements, this.list);
        p(this.backgrounds, this.list);
    }

    private void setArmors(List<ShipComponent> list) {
        List<Armor> availableArmor = this.C.getCurrentEmpire().getAvailableArmor();
        this.armors = availableArmor;
        this.listSize = availableArmor.size();
        Armor selectedArmor = this.C.shipDesignScene.getSelectedArmor();
        int componentSpaceAfterMiniaturization = this.availableSpace + this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(ShipComponents.get(selectedArmor.getID()));
        char c2 = 0;
        int i = 0;
        for (Armor armor : this.armors) {
            float f2 = 1.0f;
            if (this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(armor) > componentSpaceAfterMiniaturization && this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(armor) >= this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(selectedArmor) && armor.getID() != selectedArmor.getID()) {
                f2 = 0.4f;
            }
            int i2 = i * 105;
            float f3 = i2;
            Sprite sprite = new Sprite(0.0f, f3, this.C.graphics.colonyBackgroundTexture, this.bufferManager);
            sprite.setAlpha(0.8f);
            sprite.setSize(600.0f, 100.0f);
            sprite.setAlpha(f2);
            this.list.attachChild(sprite);
            this.backgrounds.add(sprite);
            if (armor.getID() == selectedArmor.getID()) {
                Sprite sprite2 = new Sprite(0.0f, f3, this.C.graphics.farmingBarTexture, this.bufferManager);
                sprite2.setAlpha(0.6f);
                sprite2.setSize(600.0f, 100.0f);
                this.list.attachChild(sprite2);
                this.elements.add(sprite2);
            }
            float f4 = i2 + 15;
            Text text = new Text(65.0f, f4, this.C.fonts.smallFont, armor.getName(), this.bufferManager);
            text.setAlpha(f2);
            this.list.attachChild(text);
            this.elements.add(text);
            TiledSprite tiledSprite = new TiledSprite(10.0f, i2 + 25, this.C.graphics.shipComponentIconsTexture, this.bufferManager);
            tiledSprite.setCurrentTileIndex(armor.getIconIndex());
            tiledSprite.setAlpha(f2);
            this.list.attachChild(tiledSprite);
            this.elements.add(tiledSprite);
            int sizeClass = (int) (this.shipType.getSizeClass() * 100 * armor.getArmorMultiplier());
            for (ShipComponent shipComponent : list) {
                ShipComponentID id = shipComponent.getID();
                ShipComponentID shipComponentID = ShipComponentID.HARDENED_ALLOY;
                if (id == shipComponentID) {
                    float f5 = sizeClass;
                    sizeClass = (int) (f5 + (((SpecialComponent) ShipComponents.get(shipComponentID)).getEffectValue() * f5));
                }
            }
            Uciana activity = this.C.getActivity();
            Object[] objArr = new Object[1];
            objArr[c2] = Integer.valueOf(sizeClass);
            String string = activity.getString(R.string.component_selector_armor_description, objArr);
            float f6 = i2 + 60;
            Text text2 = new Text(65.0f, f6, this.C.fonts.smallInfoFont, string, this.bufferManager);
            text2.setAlpha(f2);
            this.list.attachChild(text2);
            this.elements.add(text2);
            TiledSprite tiledSprite2 = new TiledSprite(560.0f, i2 + 10, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
            tiledSprite2.setAlpha(f2);
            this.list.attachChild(tiledSprite2);
            this.elements.add(tiledSprite2);
            Text text3 = new Text(0.0f, f4, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentCostAfterMiniaturization(armor)), this.bufferManager);
            text3.setX((tiledSprite2.getX() - text3.getWidthScaled()) - 10.0f);
            text3.setAlpha(f2);
            this.list.attachChild(text3);
            this.elements.add(text3);
            TiledSprite tiledSprite3 = new TiledSprite(560.0f, i2 + 55, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite3.setCurrentTileIndex(InfoIconEnum.SPACE.ordinal());
            tiledSprite3.setAlpha(f2);
            this.list.attachChild(tiledSprite3);
            this.elements.add(tiledSprite3);
            Text text4 = new Text(0.0f, f6, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(armor)), this.bufferManager);
            text4.setX((tiledSprite3.getX() - text4.getWidthScaled()) - 10.0f);
            text4.setAlpha(f2);
            this.list.attachChild(text4);
            this.elements.add(text4);
            i++;
            c2 = 0;
        }
    }

    private void setEngines() {
        List<SublightEngine> availableSublightEngines = this.C.getCurrentEmpire().getAvailableSublightEngines();
        this.sublightEngines = availableSublightEngines;
        this.listSize = availableSublightEngines.size();
        SublightEngine selectedSublightEngine = this.C.shipDesignScene.getSelectedSublightEngine();
        int componentSpaceAfterMiniaturization = this.availableSpace + this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(ShipComponents.get(selectedSublightEngine.getID()));
        char c2 = 0;
        int i = 0;
        for (SublightEngine sublightEngine : this.sublightEngines) {
            float f2 = 1.0f;
            if (this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(sublightEngine) > componentSpaceAfterMiniaturization && this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(sublightEngine) >= this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(selectedSublightEngine) && sublightEngine.getID() != selectedSublightEngine.getID()) {
                f2 = 0.4f;
            }
            int i2 = i * 105;
            float f3 = i2;
            Sprite sprite = new Sprite(0.0f, f3, this.C.graphics.colonyBackgroundTexture, this.bufferManager);
            sprite.setAlpha(0.8f);
            sprite.setSize(600.0f, 100.0f);
            sprite.setAlpha(f2);
            this.list.attachChild(sprite);
            this.backgrounds.add(sprite);
            if (sublightEngine.getID() == selectedSublightEngine.getID()) {
                Sprite sprite2 = new Sprite(0.0f, f3, this.C.graphics.farmingBarTexture, this.bufferManager);
                sprite2.setAlpha(0.6f);
                sprite2.setSize(600.0f, 100.0f);
                this.list.attachChild(sprite2);
                this.elements.add(sprite2);
            }
            float f4 = i2 + 15;
            Text text = new Text(65.0f, f4, this.C.fonts.smallFont, sublightEngine.getName(), this.bufferManager);
            text.setAlpha(f2);
            this.list.attachChild(text);
            this.elements.add(text);
            TiledSprite tiledSprite = new TiledSprite(10.0f, i2 + 25, this.C.graphics.shipComponentIconsTexture, this.bufferManager);
            tiledSprite.setCurrentTileIndex(sublightEngine.getIconIndex());
            tiledSprite.setAlpha(f2);
            this.list.attachChild(tiledSprite);
            this.elements.add(tiledSprite);
            float f5 = i2 + 60;
            Game game = this.C;
            Font font = game.fonts.smallInfoFont;
            Uciana activity = game.getActivity();
            Object[] objArr = new Object[1];
            objArr[c2] = Integer.valueOf(sublightEngine.getCombatSpeed());
            Text text2 = new Text(65.0f, f5, font, activity.getString(R.string.component_selector_engine_description, objArr), this.bufferManager);
            text2.setAlpha(f2);
            this.list.attachChild(text2);
            this.elements.add(text2);
            TiledSprite tiledSprite2 = new TiledSprite(560.0f, i2 + 10, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
            tiledSprite2.setAlpha(f2);
            this.list.attachChild(tiledSprite2);
            this.elements.add(tiledSprite2);
            Text text3 = new Text(0.0f, f4, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentCostAfterMiniaturization(sublightEngine)), this.bufferManager);
            text3.setX((tiledSprite2.getX() - text3.getWidthScaled()) - 10.0f);
            text3.setAlpha(f2);
            this.list.attachChild(text3);
            this.elements.add(text3);
            TiledSprite tiledSprite3 = new TiledSprite(560.0f, i2 + 55, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite3.setCurrentTileIndex(InfoIconEnum.SPACE.ordinal());
            tiledSprite3.setAlpha(f2);
            this.list.attachChild(tiledSprite3);
            this.elements.add(tiledSprite3);
            Text text4 = new Text(0.0f, f5, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(sublightEngine)), this.bufferManager);
            text4.setX((tiledSprite3.getX() - text4.getWidthScaled()) - 10.0f);
            text4.setAlpha(f2);
            this.list.attachChild(text4);
            this.elements.add(text4);
            i++;
            c2 = 0;
        }
    }

    private void setShields() {
        List<Shield> availableShields = this.C.getCurrentEmpire().getAvailableShields();
        this.shields = availableShields;
        this.listSize = availableShields.size();
        Shield selectedShield = this.C.shipDesignScene.getSelectedShield();
        int componentSpaceAfterMiniaturization = this.availableSpace + this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(ShipComponents.get(selectedShield.getID()));
        char c2 = 0;
        int i = 0;
        for (Shield shield : this.shields) {
            float f2 = 1.0f;
            if (this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(shield) > componentSpaceAfterMiniaturization && this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(shield) >= this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(selectedShield) && shield.getID() != selectedShield.getID()) {
                f2 = 0.4f;
            }
            int i2 = i * 105;
            float f3 = i2;
            Sprite sprite = new Sprite(0.0f, f3, this.C.graphics.colonyBackgroundTexture, this.bufferManager);
            sprite.setAlpha(0.8f);
            sprite.setSize(600.0f, 100.0f);
            sprite.setAlpha(f2);
            this.list.attachChild(sprite);
            this.backgrounds.add(sprite);
            if (shield.getID() == selectedShield.getID()) {
                Sprite sprite2 = new Sprite(0.0f, f3, this.C.graphics.farmingBarTexture, this.bufferManager);
                sprite2.setAlpha(0.6f);
                sprite2.setSize(600.0f, 100.0f);
                this.list.attachChild(sprite2);
                this.elements.add(sprite2);
            }
            float f4 = i2 + 15;
            Text text = new Text(65.0f, f4, this.C.fonts.smallFont, shield.getName(), this.bufferManager);
            text.setAlpha(f2);
            this.list.attachChild(text);
            this.elements.add(text);
            TiledSprite tiledSprite = new TiledSprite(10.0f, i2 + 25, this.C.graphics.shipComponentIconsTexture, this.bufferManager);
            tiledSprite.setCurrentTileIndex(shield.getIconIndex());
            tiledSprite.setAlpha(f2);
            this.list.attachChild(tiledSprite);
            this.elements.add(tiledSprite);
            Uciana activity = this.C.getActivity();
            Object[] objArr = new Object[3];
            objArr[c2] = Integer.valueOf(shield.getStrengthMultiplier() * this.shipType.getSizeClass());
            objArr[1] = Integer.valueOf(shield.getAbsorption());
            objArr[2] = Integer.valueOf((int) (shield.getRechargeRate() * 100.0f));
            float f5 = i2 + 60;
            Text text2 = new Text(65.0f, f5, this.C.fonts.smallInfoFont, activity.getString(R.string.component_selector_shield_description, objArr), this.bufferManager);
            text2.setAlpha(f2);
            this.list.attachChild(text2);
            this.elements.add(text2);
            TiledSprite tiledSprite2 = new TiledSprite(560.0f, i2 + 10, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
            tiledSprite2.setAlpha(f2);
            this.list.attachChild(tiledSprite2);
            this.elements.add(tiledSprite2);
            Shield shield2 = selectedShield;
            Text text3 = new Text(0.0f, f4, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentCostAfterMiniaturization(shield)), this.bufferManager);
            text3.setX((tiledSprite2.getX() - text3.getWidthScaled()) - 10.0f);
            text3.setAlpha(f2);
            this.list.attachChild(text3);
            this.elements.add(text3);
            TiledSprite tiledSprite3 = new TiledSprite(560.0f, i2 + 55, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite3.setCurrentTileIndex(InfoIconEnum.SPACE.ordinal());
            tiledSprite3.setAlpha(f2);
            this.list.attachChild(tiledSprite3);
            this.elements.add(tiledSprite3);
            Text text4 = new Text(0.0f, f5, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(shield)), this.bufferManager);
            text4.setX((tiledSprite3.getX() - text4.getWidthScaled()) - 10.0f);
            text4.setAlpha(f2);
            this.list.attachChild(text4);
            this.elements.add(text4);
            i++;
            selectedShield = shield2;
            c2 = 0;
        }
    }

    private void setSpecial(int i, boolean z, List<ShipComponent> list) {
        SpecialComponent specialComponent;
        boolean z2;
        List<SpecialComponent> availableSpecialComponents = this.C.getCurrentEmpire().getAvailableSpecialComponents();
        this.specialComponents = availableSpecialComponents;
        this.listSize = availableSpecialComponents.size();
        int i2 = this.availableSpace;
        if (z) {
            specialComponent = (SpecialComponent) this.C.shipDesignScene.getShipComponent(i);
            i2 += this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(specialComponent);
        } else {
            specialComponent = null;
        }
        float f2 = 0.0f;
        Sprite sprite = new Sprite(0.0f, 0.0f, this.C.graphics.popEmptyTexture, this.bufferManager);
        sprite.setAlpha(0.5f);
        float f3 = 600.0f;
        float f4 = 100.0f;
        sprite.setSize(600.0f, 100.0f);
        this.list.attachChild(sprite);
        this.backgrounds.add(sprite);
        String string = this.C.getActivity().getString(R.string.component_selector_cancel);
        if (z) {
            string = this.C.getActivity().getString(R.string.component_selector_remove);
        }
        Text text = new Text(0.0f, 0.0f, this.C.fonts.smallFont, string, this.bufferManager);
        text.setX(300.0f - (text.getWidthScaled() / 2.0f));
        text.setY(52.5f - (text.getHeightScaled() / 2.0f));
        this.list.attachChild(text);
        this.elements.add(text);
        int i3 = 1;
        for (SpecialComponent specialComponent2 : this.specialComponents) {
            float f5 = (this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(specialComponent2) <= i2 || !(specialComponent == null || specialComponent2.getID() == specialComponent.getID() || this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(specialComponent2) > this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(specialComponent))) ? 1.0f : 0.4f;
            if (f5 != 0.4f) {
                while (true) {
                    z2 = false;
                    for (ShipComponent shipComponent : list) {
                        if (shipComponent.getID() == specialComponent2.getID() && !specialComponent2.allowMoreThenOne()) {
                            if (!z || shipComponent.getID() != specialComponent.getID()) {
                                z2 = true;
                            }
                        }
                    }
                }
                if (z2) {
                    f5 = 0.41f;
                }
            }
            int i4 = i3 * 105;
            float f6 = i4;
            Sprite sprite2 = new Sprite(f2, f6, this.C.graphics.colonyBackgroundTexture, this.bufferManager);
            sprite2.setAlpha(f5);
            if (f5 == 1.0f) {
                sprite2.setAlpha(0.8f);
            }
            sprite2.setSize(f3, f4);
            this.list.attachChild(sprite2);
            this.backgrounds.add(sprite2);
            if (z && specialComponent2.getID() == specialComponent.getID()) {
                Sprite sprite3 = new Sprite(f2, f6, this.C.graphics.farmingBarTexture, this.bufferManager);
                sprite3.setAlpha(0.6f);
                sprite3.setSize(f3, f4);
                this.list.attachChild(sprite3);
                this.elements.add(sprite3);
            }
            float f7 = i4 + 15;
            Text text2 = new Text(50.0f, f7, this.C.fonts.smallFont, specialComponent2.getName(), this.bufferManager);
            text2.setAlpha(f5);
            this.list.attachChild(text2);
            this.elements.add(text2);
            TiledSprite tiledSprite = new TiledSprite(5.0f, i4 + 30, this.C.graphics.shipComponentIconsTexture, this.bufferManager);
            tiledSprite.setCurrentTileIndex(specialComponent2.getIconIndex());
            tiledSprite.setScaleCenter(f2, f2);
            tiledSprite.setSize(40.0f, 40.0f);
            tiledSprite.setAlpha(f5);
            this.list.attachChild(tiledSprite);
            this.elements.add(tiledSprite);
            Text text3 = new Text(50.0f, i4 + 58, this.C.fonts.smallInfoFont, specialComponent2.getDescription(), new TextOptions(AutoWrap.WORDS, 450.0f, HorizontalAlign.LEFT), this.bufferManager);
            text3.setAlpha(f5);
            this.list.attachChild(text3);
            this.elements.add(text3);
            TiledSprite tiledSprite2 = new TiledSprite(560.0f, i4 + 10, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
            tiledSprite2.setAlpha(f5);
            this.list.attachChild(tiledSprite2);
            this.elements.add(tiledSprite2);
            String num = Integer.toString(this.C.getCurrentEmpire().getComponentCostAfterMiniaturization(specialComponent2));
            ShipComponentID id = specialComponent2.getID();
            ShipComponentID shipComponentID = ShipComponentID.EXTENDED_HULL;
            if (id == shipComponentID) {
                num = "+100%";
            }
            Text text4 = new Text(0.0f, f7, this.C.fonts.smallFont, num, this.bufferManager);
            text4.setX((tiledSprite2.getX() - text4.getWidthScaled()) - 10.0f);
            text4.setAlpha(f5);
            this.list.attachChild(text4);
            this.elements.add(text4);
            TiledSprite tiledSprite3 = new TiledSprite(560.0f, i4 + 55, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite3.setCurrentTileIndex(InfoIconEnum.SPACE.ordinal());
            tiledSprite3.setAlpha(f5);
            this.list.attachChild(tiledSprite3);
            this.elements.add(tiledSprite3);
            String num2 = Integer.toString(this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(specialComponent2));
            if (specialComponent2.getID() == shipComponentID) {
                num2 = "+50%";
            }
            Text text5 = new Text(0.0f, i4 + 60, this.C.fonts.smallFont, num2, this.bufferManager);
            text5.setX((tiledSprite3.getX() - text5.getWidthScaled()) - 10.0f);
            text5.setAlpha(f5);
            this.list.attachChild(text5);
            this.elements.add(text5);
            i3++;
            f3 = 600.0f;
            f4 = 100.0f;
            f2 = 0.0f;
        }
    }

    private void setSpritesVisible(boolean z) {
        this.selectType.setVisible(z);
        this.allWeaponTypesButton.setVisible(z);
        this.allWeaponTypesIcon1.setVisible(z);
        this.allWeaponTypesIcon2.setVisible(z);
        this.allWeaponTypesIcon3.setVisible(z);
        this.allWeaponTypesIcon4.setVisible(z);
        this.beamWeaponTypeButton.setVisible(z);
        this.beamWeaponTypeIcon.setVisible(z);
        this.bombWeaponTypeButton.setVisible(z);
        this.bombWeaponTypeIcon.setVisible(z);
        this.torpedoWeaponTypeButton.setVisible(z);
        this.torpedoWeaponTypeIcon.setVisible(z);
        this.chargeWeaponTypeButton.setVisible(z);
        this.chargeWeaponTypeIcon.setVisible(z);
    }

    private void setTargeting() {
        List<TargetingComputer> availableTargetingComputers = this.C.getCurrentEmpire().getAvailableTargetingComputers();
        this.targetingComputers = availableTargetingComputers;
        this.listSize = availableTargetingComputers.size();
        TargetingComputer selectedTargetingComputer = this.C.shipDesignScene.getSelectedTargetingComputer();
        int componentSpaceAfterMiniaturization = this.availableSpace + this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(ShipComponents.get(selectedTargetingComputer.getID()));
        char c2 = 0;
        int i = 0;
        for (TargetingComputer targetingComputer : this.targetingComputers) {
            float f2 = 1.0f;
            if (this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(targetingComputer) > componentSpaceAfterMiniaturization && this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(targetingComputer) >= this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(selectedTargetingComputer) && targetingComputer.getID() != selectedTargetingComputer.getID()) {
                f2 = 0.4f;
            }
            int i2 = i * 105;
            float f3 = i2;
            Sprite sprite = new Sprite(0.0f, f3, this.C.graphics.colonyBackgroundTexture, this.bufferManager);
            sprite.setAlpha(0.8f);
            sprite.setSize(600.0f, 100.0f);
            sprite.setAlpha(f2);
            this.list.attachChild(sprite);
            this.backgrounds.add(sprite);
            if (targetingComputer.getID() == selectedTargetingComputer.getID()) {
                Sprite sprite2 = new Sprite(0.0f, f3, this.C.graphics.farmingBarTexture, this.bufferManager);
                sprite2.setAlpha(0.6f);
                sprite2.setSize(600.0f, 100.0f);
                this.list.attachChild(sprite2);
                this.elements.add(sprite2);
            }
            float f4 = i2 + 15;
            Text text = new Text(65.0f, f4, this.C.fonts.smallFont, targetingComputer.getName(), this.bufferManager);
            text.setAlpha(f2);
            this.list.attachChild(text);
            this.elements.add(text);
            TiledSprite tiledSprite = new TiledSprite(10.0f, i2 + 25, this.C.graphics.shipComponentIconsTexture, this.bufferManager);
            tiledSprite.setCurrentTileIndex(targetingComputer.getIconIndex());
            tiledSprite.setAlpha(f2);
            this.list.attachChild(tiledSprite);
            this.elements.add(tiledSprite);
            Uciana activity = this.C.getActivity();
            Object[] objArr = new Object[2];
            objArr[c2] = Integer.valueOf(targetingComputer.getTargetingBonus());
            objArr[1] = Integer.valueOf(targetingComputer.getDamageBonus());
            float f5 = i2 + 60;
            Text text2 = new Text(65.0f, f5, this.C.fonts.smallInfoFont, activity.getString(R.string.component_selector_targeting_description, objArr), this.bufferManager);
            text2.setAlpha(f2);
            this.list.attachChild(text2);
            this.elements.add(text2);
            TiledSprite tiledSprite2 = new TiledSprite(560.0f, i2 + 10, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
            tiledSprite2.setAlpha(f2);
            this.list.attachChild(tiledSprite2);
            this.elements.add(tiledSprite2);
            Text text3 = new Text(0.0f, f4, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentCostAfterMiniaturization(targetingComputer)), this.bufferManager);
            text3.setX((tiledSprite2.getX() - text3.getWidthScaled()) - 10.0f);
            text3.setAlpha(f2);
            this.list.attachChild(text3);
            this.elements.add(text3);
            TiledSprite tiledSprite3 = new TiledSprite(560.0f, i2 + 55, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite3.setCurrentTileIndex(InfoIconEnum.SPACE.ordinal());
            tiledSprite3.setAlpha(f2);
            this.list.attachChild(tiledSprite3);
            this.elements.add(tiledSprite3);
            Text text4 = new Text(0.0f, f5, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(targetingComputer)), this.bufferManager);
            text4.setX((tiledSprite3.getX() - text4.getWidthScaled()) - 10.0f);
            text4.setAlpha(f2);
            this.list.attachChild(text4);
            this.elements.add(text4);
            i++;
            c2 = 0;
        }
    }

    private void setWeaponType(float f2, WeaponType weaponType) {
        resetOverlay();
        this.selectType.setY(f2);
        setWeapons(this.selectorIndex, this.hasSelected, weaponType);
        this.scrollBar.update(0.0f, this.listSize);
    }

    private void setWeaponTypeFilters() {
        setSpritesVisible(true);
        this.beamWeaponTypeButton.setAlpha(0.4f);
        this.beamWeaponTypeIcon.setAlpha(0.4f);
        this.bombWeaponTypeButton.setAlpha(0.4f);
        this.bombWeaponTypeIcon.setAlpha(0.4f);
        this.torpedoWeaponTypeButton.setAlpha(0.4f);
        this.torpedoWeaponTypeIcon.setAlpha(0.4f);
        for (Weapon weapon : this.C.getCurrentEmpire().getAvailableWeapons()) {
            int i = AnonymousClass1.b[weapon.getType().ordinal()];
            if (i == 2) {
                this.bombWeaponTypeButton.setAlpha(1.0f);
                this.bombWeaponTypeIcon.setAlpha(1.0f);
            } else if (i == 3) {
                this.beamWeaponTypeButton.setAlpha(1.0f);
                this.beamWeaponTypeIcon.setAlpha(1.0f);
            } else if (i == 4) {
                this.torpedoWeaponTypeButton.setAlpha(1.0f);
                this.torpedoWeaponTypeIcon.setAlpha(1.0f);
            }
        }
    }

    private void setWeapons(int i, boolean z, WeaponType weaponType) {
        this.weapons = this.C.getCurrentEmpire().getAvailableWeapons();
        if (weaponType != WeaponType.NONE) {
            ArrayList arrayList = new ArrayList();
            for (Weapon weapon : this.weapons) {
                if (weapon.getType() == weaponType) {
                    arrayList.add(weapon);
                }
            }
            this.weapons = arrayList;
        }
        this.listSize = this.weapons.size();
        int i2 = this.availableSpace;
        Weapon weapon2 = null;
        if (z) {
            weapon2 = (Weapon) this.C.shipDesignScene.getShipComponent(i);
            i2 += this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(weapon2);
        }
        float f2 = 0.0f;
        Sprite sprite = new Sprite(0.0f, 0.0f, this.C.graphics.popEmptyTexture, this.bufferManager);
        sprite.setAlpha(0.5f);
        float f3 = 600.0f;
        float f4 = 100.0f;
        sprite.setSize(600.0f, 100.0f);
        this.list.attachChild(sprite);
        this.backgrounds.add(sprite);
        String string = this.C.getActivity().getString(R.string.select_component_cancel);
        if (z) {
            string = this.C.getActivity().getString(R.string.select_component_remove);
        }
        Text text = new Text(0.0f, 0.0f, this.C.fonts.smallFont, string, this.bufferManager);
        text.setX(300.0f - (text.getWidthScaled() / 2.0f));
        text.setY(52.5f - (text.getHeightScaled() / 2.0f));
        this.list.attachChild(text);
        this.elements.add(text);
        int i3 = 1;
        for (Weapon weapon3 : this.weapons) {
            float f5 = 1.0f;
            if (this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(weapon3) > i2 && (weapon2 == null || weapon3.getID() == weapon2.getID() || this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(weapon3) > this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(weapon2))) {
                f5 = 0.4f;
            }
            int i4 = i3 * 105;
            float f6 = i4;
            Sprite sprite2 = new Sprite(f2, f6, this.C.graphics.colonyBackgroundTexture, this.bufferManager);
            sprite2.setAlpha(0.8f);
            sprite2.setSize(f3, f4);
            sprite2.setAlpha(f5);
            this.list.attachChild(sprite2);
            this.backgrounds.add(sprite2);
            if (z && weapon3.getID() == weapon2.getID()) {
                Sprite sprite3 = new Sprite(f2, f6, this.C.graphics.farmingBarTexture, this.bufferManager);
                sprite3.setAlpha(0.6f);
                sprite3.setSize(f3, f4);
                this.list.attachChild(sprite3);
                this.elements.add(sprite3);
            }
            float f7 = i4 + 15;
            Text text2 = new Text(50.0f, f7, this.C.fonts.smallFont, weapon3.getName(), this.bufferManager);
            text2.setAlpha(f5);
            this.list.attachChild(text2);
            this.elements.add(text2);
            TiledSprite tiledSprite = new TiledSprite(5.0f, i4 + 30, this.C.graphics.shipComponentIconsTexture, this.bufferManager);
            tiledSprite.setCurrentTileIndex(weapon3.getIconIndex());
            tiledSprite.setScaleCenter(f2, f2);
            tiledSprite.setSize(40.0f, 40.0f);
            tiledSprite.setAlpha(f5);
            this.list.attachChild(tiledSprite);
            this.elements.add(tiledSprite);
            String string2 = this.C.getActivity().getString(R.string.component_selector_damage, new Object[]{Integer.valueOf(weapon3.getMinDamage()), Integer.valueOf(weapon3.getMaxDamage())});
            int i5 = AnonymousClass1.b[weapon3.getType().ordinal()];
            if (i5 == 1) {
                string2 = "";
            } else if (i5 == 2) {
                string2 = this.C.getActivity().getString(R.string.component_selector_bomb_damage, new Object[]{Integer.valueOf(weapon3.getMinDamage()), Integer.valueOf(weapon3.getMaxDamage())});
            }
            float f8 = i4 + 60;
            Text text3 = new Text(50.0f, f8, this.C.fonts.smallInfoFont, string2, this.bufferManager);
            text3.setAlpha(f5);
            this.list.attachChild(text3);
            this.elements.add(text3);
            TiledSprite tiledSprite2 = new TiledSprite(560.0f, i4 + 10, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
            tiledSprite2.setAlpha(f5);
            this.list.attachChild(tiledSprite2);
            this.elements.add(tiledSprite2);
            Text text4 = new Text(0.0f, f7, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentCostAfterMiniaturization(weapon3)), this.bufferManager);
            text4.setX((tiledSprite2.getX() - text4.getWidthScaled()) - 10.0f);
            text4.setAlpha(f5);
            this.list.attachChild(text4);
            this.elements.add(text4);
            TiledSprite tiledSprite3 = new TiledSprite(560.0f, i4 + 55, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite3.setCurrentTileIndex(InfoIconEnum.SPACE.ordinal());
            tiledSprite3.setAlpha(f5);
            this.list.attachChild(tiledSprite3);
            this.elements.add(tiledSprite3);
            Text text5 = new Text(0.0f, f8, this.C.fonts.smallFont, Integer.toString(this.C.getCurrentEmpire().getComponentSpaceAfterMiniaturization(weapon3)), this.bufferManager);
            text5.setX((tiledSprite3.getX() - text5.getWidthScaled()) - 10.0f);
            text5.setAlpha(f5);
            this.list.attachChild(text5);
            this.elements.add(text5);
            i3++;
            f3 = 600.0f;
            f4 = 100.0f;
            f2 = 0.0f;
        }
    }

    private void torpedoWeaponTypeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        setWeaponType(this.torpedoWeaponTypeButton.getY(), WeaponType.TORPEDO);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            checkActionUp(point);
        } else if (i != 2) {
        } else {
            checkActionMove(point);
        }
    }

    public void setOverlay(ShipComponentSelectorType shipComponentSelectorType, ShipType shipType, int i) {
        setOverlay(shipComponentSelectorType, shipType, -1, true, new ArrayList(), i);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }

    public void setOverlay(ShipComponentSelectorType shipComponentSelectorType, ShipType shipType, int i, boolean z, List<ShipComponent> list, int i2) {
        this.shipType = shipType;
        this.selectorIndex = i;
        this.hasSelected = z;
        this.availableSpace = i2;
        setSpritesVisible(false);
        resetOverlay();
        this.selectedComponent = shipComponentSelectorType;
        switch (AnonymousClass1.f1389a[shipComponentSelectorType.ordinal()]) {
            case 1:
                setArmors(list);
                break;
            case 2:
                setShields();
                break;
            case 3:
                setTargeting();
                break;
            case 4:
                setEngines();
                break;
            case 5:
                WeaponType weaponType = WeaponType.NONE;
                if (this.selectType.getY() == this.beamWeaponTypeButton.getY()) {
                    weaponType = WeaponType.BEAM;
                } else if (this.selectType.getY() == this.bombWeaponTypeButton.getY()) {
                    weaponType = WeaponType.BOMB;
                } else if (this.selectType.getY() == this.torpedoWeaponTypeButton.getY()) {
                    weaponType = WeaponType.TORPEDO;
                } else if (this.selectType.getY() == this.chargeWeaponTypeButton.getY()) {
                    weaponType = WeaponType.SPACIAL_CHARGE;
                }
                setWeapons(i, z, weaponType);
                setWeaponTypeFilters();
                break;
            case 6:
                setSpecial(i, z, list);
                break;
        }
        this.scrollBar.update(0.0f, this.listSize);
    }
}
