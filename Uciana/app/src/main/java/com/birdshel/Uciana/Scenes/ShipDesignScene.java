package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Controls.ShipComponentSelector;
import com.birdshel.Uciana.Controls.ShipComponentSelectorType;
import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.SelectComponentOverlay;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.RaceAttributeType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.Armor;
import com.birdshel.Uciana.Ships.ShipComponents.Shield;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.SpecialComponent;
import com.birdshel.Uciana.Ships.ShipComponents.SublightEngine;
import com.birdshel.Uciana.Ships.ShipComponents.TargetingComputer;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipDesignScene extends ExtendedScene {
    private static final int ITEM_SIZE = 105;
    private Armor armor;
    private ShipComponentSelector armorSelector;
    private TiledSprite autoButton;
    private Sprite autoButtonPressed;
    private Text autoText;
    private Text availableSpace;
    private TiledSprite availableSpaceIcon;
    private Text beamAccuracy;
    private VertexBufferObjectManager bufferManager;
    private Sprite designBackground;
    private Text designName;
    private Text dreadnoughtCommandPointCost;
    private TiledSprite dreadnoughtCommandPointIcon;
    private TiledSprite dreadnoughtIcon;
    private TiledSprite dreadnoughtSizeSelector;
    private EmpireButton empireButton;
    private Text evasion;
    private TiledSprite galaxyButton;
    private TiledSprite level2Button;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private TiledSprite nextHullButton;
    private int orbit;
    private TiledSprite planetButton;
    private PlanetSprite planetSprite;
    private Sprite pressSprite;
    private TiledSprite previousHullButton;
    private TiledSprite productionButton;
    private Text productionCost;
    private TiledSprite productionCostIcon;
    private TiledSprite renameIcon;
    private TiledSprite saveButton;
    private Sprite saveButtonPressed;
    private Text saveText;
    private SelectComponentOverlay selectComponentOverlay;
    private int selectedDesignIndex;
    private Sprite selectedSize;
    private int selectedSizeIndex;
    private Shield shield;
    private ShipComponentSelector shieldSelector;
    private ShipSprite shipImage;
    private TiledSprite shipTypeIcon;
    private SublightEngine sublightEngine;
    private ShipComponentSelector sublightSelector;
    private int systemID;
    private TargetingComputer targetingComputer;
    private ShipComponentSelector targetingSelector;
    private final List<Entity> selectedDesignListBackground = new ArrayList();
    private final List<Entity> selectedList = new ArrayList();
    private final List<Entity> plusButtonList = new ArrayList();
    private final List<Entity> shipIcons = new ArrayList();
    private final List<Entity> shipNames = new ArrayList();
    private final List<Entity> productionCostList = new ArrayList();
    private final List<Entity> productionCostIconList = new ArrayList();
    private final List<Entity> shipTypeIcons = new ArrayList();
    private final List<Entity> removeButtonBackgrounds = new ArrayList();
    private final List<Entity> removeButtons = new ArrayList();
    private final List<Entity> shipDescriptions = new ArrayList();
    private final int[] designIndexes = new int[ShipType.values().length];
    private final ShipComponentSelector[] componentSelectors = new ShipComponentSelector[6];
    private List<Ship> shipDesigns = new ArrayList();
    private List<ShipComponent> shipComponents = new ArrayList();

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.ShipDesignScene$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1477a;

        static {
            int[] iArr = new int[ShipType.values().length];
            f1477a = iArr;
            try {
                iArr[ShipType.DREADNOUGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1477a[ShipType.BATTLESHIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1477a[ShipType.CRUISER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1477a[ShipType.DESTROYER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public ShipDesignScene(Game game) {
        this.B = game;
    }

    private void autoButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        setDesignName(ShipType.getShipType(this.selectedSizeIndex).getString(this.B.getCurrentPlayer()));
        this.renameIcon.setX(this.designName.getX() + this.designName.getWidthScaled() + 10.0f);
        setComponents(this.B.getCurrentEmpire().getShipDesignAI().getShipDesign(ShipType.getShipType(this.selectedSizeIndex)));
        setShip(getDesignIndex(ShipType.getShipType(this.selectedSizeIndex)));
    }

    private void checkActionDown(Point point) {
        checkPressed(point);
    }

    private void checkActionMove(Point point) {
        disablePress();
        checkPressed(point);
    }

    private void checkActionUp(Point point) {
        disablePress();
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.empireButton, point)) {
            empireButtonPressed();
        } else if (isClicked(this.level2Button, point)) {
            level2ButtonPressed();
        } else if (isClicked(this.planetButton, point)) {
            planetButtonPressed();
        } else if (isClicked(this.productionButton, point)) {
            productionButtonPressed();
        } else {
            if (point.getX() > getWidth() - 450.0f && point.getY() > 86.0f && point.getY() < 716.0f) {
                shipDesignPressed(((int) (point.getY() - 86.0f)) / 105, point);
            }
            for (int i = 0; i < 4; i++) {
                int i2 = i * 110;
                if (point.getX() > i2 + 10 && point.getX() < i2 + 110 && point.getY() < 110.0f) {
                    shipSizePressed(i);
                }
            }
            if (point.getX() > this.shipImage.getX() && point.getX() < this.shipImage.getX() + this.shipImage.getSize() && point.getY() > this.shipImage.getY() && point.getY() < this.shipImage.getY() + this.shipImage.getY()) {
                shipSpritePressed();
            }
            if (isClicked(this.previousHullButton, point)) {
                previousHullButtonPressed();
            }
            if (isClicked(this.nextHullButton, point)) {
                nextHullButtonPressed();
            }
            if (isClicked(this.autoButton, point)) {
                autoButtonPressed();
            }
            if (isClicked(this.saveButton, point)) {
                saveButtonPressed();
            }
            if (y(this.designName, point) || x(this.renameIcon, point, 20.0f, 20.0f)) {
                renamePressed();
            }
        }
    }

    private void checkPressed(Point point) {
        int i;
        if (point.getX() > getWidth() - 450.0f && point.getY() > 86.0f && point.getY() < 716.0f) {
            int y = ((int) (point.getY() - 86.0f)) / 105;
            if (this.selectedDesignListBackground.get(y).getAlpha() != 0.4f) {
                this.pressSprite.setVisible(true);
                this.pressSprite.setHeight(100.0f);
                if (this.plusButtonList.get(y).isVisible()) {
                    this.pressSprite.setX(this.selectedDesignListBackground.get(y).getX());
                    this.pressSprite.setWidth(450.0f);
                } else if (point.getX() > getWidth() - 50.0f) {
                    this.pressSprite.setX(this.removeButtonBackgrounds.get(y).getX());
                    this.pressSprite.setWidth(55.0f);
                } else {
                    this.pressSprite.setX(this.selectedDesignListBackground.get(y).getX());
                    this.pressSprite.setWidth(390.0f);
                }
                this.pressSprite.setY((y * 105) + 86);
            }
        }
        for (int i2 = 0; i2 < 4; i2++) {
            float f2 = (i2 * 110) + 10;
            if (point.getX() > f2 && point.getX() < i + 110 && point.getY() < 110.0f) {
                this.pressSprite.setPosition(f2, 10.0f);
                this.pressSprite.setSize(100.0f, 100.0f);
                this.pressSprite.setVisible(true);
            }
        }
        if (A(this.autoButton, point, 0.0f, 0.0f)) {
            this.autoButtonPressed.setVisible(true);
            this.autoText.setColor(Color.WHITE);
        }
        if (A(this.saveButton, point, 0.0f, 0.0f)) {
            this.saveButtonPressed.setVisible(true);
            this.saveText.setColor(Color.WHITE);
        }
    }

    private void disablePress() {
        this.pressSprite.setVisible(false);
        this.autoButtonPressed.setVisible(false);
        Text text = this.autoText;
        Color color = Color.BLACK;
        text.setColor(color);
        this.saveButtonPressed.setVisible(false);
        this.saveText.setColor(color);
    }

    private void empireButtonPressed() {
        changeScene(this.B.empireScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private int getDesignIndex(ShipType shipType) {
        return getDesignIndex(shipType.id);
    }

    private int getEvasionBonus() {
        return (int) (0 + GameData.empires.get(this.B.getCurrentPlayer()).getAttributeTypeBonus(RaceAttributeType.BEAM_EVASION));
    }

    private int getProductionCost() {
        int baseProductionCost = ShipType.getShipType(this.selectedSizeIndex).getBaseProductionCost() + this.B.getCurrentEmpire().getComponentCostAfterMiniaturization(this.armor) + this.B.getCurrentEmpire().getComponentCostAfterMiniaturization(this.shield) + this.B.getCurrentEmpire().getComponentCostAfterMiniaturization(this.targetingComputer) + this.B.getCurrentEmpire().getComponentCostAfterMiniaturization(this.sublightEngine);
        float f2 = 1.0f;
        for (ShipComponent shipComponent : this.shipComponents) {
            baseProductionCost += this.B.getCurrentEmpire().getComponentCostAfterMiniaturization(shipComponent) * shipComponent.getComponentCount();
            if (shipComponent.getID() == ShipComponentID.EXTENDED_HULL) {
                f2 = 2.0f;
            }
        }
        return (int) (baseProductionCost * f2);
    }

    private int getTargetAccuracyBonus() {
        return (int) (this.targetingComputer.getTargetingBonus() + GameData.empires.get(this.B.getCurrentPlayer()).getAttributeTypeBonus(RaceAttributeType.BEAM_ACCURACY));
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulas.setVisible(true);
        this.nebulaBackground.detachChild(this.nebulas);
        this.nebulaBackground.detachChild(this.planetSprite);
        this.B.planetSpritePool.push(this.planetSprite);
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void level2ButtonPressed() {
        if (this.level2Button.getCurrentTileIndex() == ButtonsEnum.FLEETS.ordinal()) {
            changeScene(this.B.fleetsScene);
        } else if (this.level2Button.getCurrentTileIndex() == ButtonsEnum.SYSTEM.ordinal()) {
            changeScene(this.B.systemScene, Integer.valueOf(this.systemID));
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void nextHullButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        ShipType shipType = ShipType.getShipType(this.selectedSizeIndex);
        updateHullDesign(shipType);
        setShip(getDesignIndex(shipType));
    }

    private void planetButtonPressed() {
        changeScene(this.B.planetScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void previousHullButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        ShipType shipType = ShipType.getShipType(this.selectedSizeIndex);
        int designIndex = getDesignIndex(shipType) - 1;
        if (designIndex == -1) {
            designIndex = 4;
        }
        this.designIndexes[shipType.id] = designIndex;
        setShip(getDesignIndex(shipType));
    }

    private void productionButtonPressed() {
        changeScene(this.B.productionScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void renamePressed() {
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.keyboardOverlay.setOverlay("renameShipDesign", this.B.getActivity().getString(R.string.ship_design_change_name), this.designName.getText().toString());
        setChildScene(this.B.keyboardOverlay, false, false, true);
    }

    private void saveButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        if (getAvailableSpace() < 0) {
            this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.ship_design_unable_to_save_negative_space)));
        } else {
            Game game2 = this.B;
            Empire empire = game2.empires.get(game2.getCurrentPlayer());
            ShipType shipType = ShipType.getShipType(this.selectedSizeIndex);
            Ship buildCombatShip = new Ship.Builder().id(UUID.randomUUID().toString()).shipType(shipType).name(this.designName.getText().toString()).designNumber(empire.getDesignVersion(shipType) + 1).empireID(this.B.getCurrentPlayer()).productionCost(getProductionCost()).armor(this.armor).shield(this.shield).targetingComputer(this.targetingComputer).sublightEngine(this.sublightEngine).shipComponents(this.shipComponents).hullDesign(getDesignIndex(ShipType.getShipType(this.selectedSizeIndex))).buildCombatShip();
            if (this.selectedDesignIndex == this.shipDesigns.size()) {
                empire.addShipDesign(buildCombatShip);
            } else {
                empire.replaceShipDesign(this.selectedDesignIndex, buildCombatShip);
            }
            setDesignList();
            this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.ship_design_saved)));
        }
        setChildScene(this.H, false, false, true);
    }

    private void setArmor() {
        this.armorSelector.set(this.armor, ShipType.getShipType(this.selectedSizeIndex).getSizeClass(), this.shipComponents);
        updateProductionCostAndSpace();
    }

    private void setComponents(Ship ship) {
        this.armor = ship.getArmor();
        this.shield = ship.getShield();
        this.targetingComputer = ship.getTargetingComputer();
        this.sublightEngine = ship.getSublightEngine();
        this.shipComponents = ship.getCopyOfShipComponents();
    }

    private void setComponentsForNewShip() {
        this.armor = this.B.getCurrentEmpire().getShipDesignAI().getBestArmor();
        this.shield = this.B.getCurrentEmpire().getShipDesignAI().getBestShield();
        this.targetingComputer = this.B.getCurrentEmpire().getShipDesignAI().getBestTargetingComputer();
        this.sublightEngine = this.B.getCurrentEmpire().getShipDesignAI().getBestSublightEngine();
        this.shipComponents = new ArrayList();
    }

    private void setDesignList() {
        C(this.selectedList);
        C(this.plusButtonList);
        C(this.shipIcons);
        C(this.shipNames);
        C(this.productionCostList);
        C(this.productionCostIconList);
        C(this.shipTypeIcons);
        C(this.removeButtons);
        C(this.removeButtonBackgrounds);
        w(this.shipDescriptions, this);
        this.shipDesigns = this.B.getCurrentEmpire().getShipDesigns();
        for (int i = 0; i < 6; i++) {
            if (this.shipDesigns.size() > i) {
                Ship ship = this.shipDesigns.get(i);
                ((Sprite) this.selectedDesignListBackground.get(i)).setWidth(390.0f);
                ((Sprite) this.selectedList.get(i)).setWidth(390.0f);
                ShipSprite shipSprite = (ShipSprite) this.shipIcons.get(i);
                shipSprite.setVisible(true);
                shipSprite.setShipIcon(this.B.getCurrentPlayer(), ship.getShipType(), ship.getHullDesign(), 100.0f, ship.getShipType().getSelectScreenSize(), 1, false);
                Text text = (Text) this.shipNames.get(i);
                text.setText(ship.getName());
                text.setVisible(true);
                Text text2 = (Text) this.productionCostList.get(i);
                text2.setText(Integer.toString(ship.getProductionCost()));
                text2.setX((((getWidth() - 60.0f) - text2.getWidthScaled()) - 5.0f) - this.productionCostIcon.getWidthScaled());
                text2.setVisible(true);
                TiledSprite tiledSprite = (TiledSprite) this.productionCostIconList.get(i);
                tiledSprite.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
                tiledSprite.setVisible(true);
                Entity armorShieldHpDescription = ship.getArmorShieldHpDescription(this.B, this.bufferManager);
                armorShieldHpDescription.setPosition(getWidth() - 340.0f, tiledSprite.getY());
                this.shipDescriptions.add(armorShieldHpDescription);
                attachChild(armorShieldHpDescription);
                Entity componentsDescription = ship.getComponentsDescription(this.B, this.bufferManager);
                componentsDescription.setPosition(getWidth() - 340.0f, (i * 105) + 86 + 70);
                this.shipDescriptions.add(componentsDescription);
                attachChild(componentsDescription);
                TiledSprite tiledSprite2 = (TiledSprite) this.shipTypeIcons.get(i);
                tiledSprite2.setCurrentTileIndex(InfoIconEnum.getShipIcon(ship.getShipType()));
                tiledSprite2.setVisible(true);
                this.removeButtonBackgrounds.get(i).setVisible(true);
                ((TiledSprite) this.removeButtons.get(i)).setVisible(true);
            } else if (this.shipDesigns.size() == i) {
                Sprite sprite = (Sprite) this.selectedDesignListBackground.get(i);
                sprite.setWidth(450.0f);
                ((Sprite) this.selectedList.get(i)).setWidth(450.0f);
                this.plusButtonList.get(i).setVisible(true);
                this.plusButtonList.get(i).setAlpha(1.0f);
                sprite.setAlpha(0.8f);
            } else {
                Sprite sprite2 = (Sprite) this.selectedDesignListBackground.get(i);
                sprite2.setWidth(450.0f);
                ((Sprite) this.selectedList.get(i)).setWidth(450.0f);
                this.plusButtonList.get(i).setVisible(true);
                this.plusButtonList.get(i).setAlpha(0.4f);
                sprite2.setAlpha(0.4f);
            }
        }
        setSelectedDesign(this.selectedDesignIndex);
    }

    private void setSelectedDesign(int i) {
        C(this.selectedList);
        this.selectedDesignIndex = i;
        this.selectedList.get(i).setVisible(true);
        if (this.selectedDesignIndex < this.shipDesigns.size()) {
            setDesignName(this.shipDesigns.get(this.selectedDesignIndex).getName());
            setSelectedSize(this.shipDesigns.get(this.selectedDesignIndex).getShipType());
            Ship ship = this.shipDesigns.get(this.selectedDesignIndex);
            setComponents(ship);
            setShip(ship.getHullDesign());
            this.designIndexes[ship.getShipType().id] = ship.getHullDesign();
        } else {
            setDesignName(ShipType.getShipType(this.selectedSizeIndex).getString(this.B.getCurrentPlayer()));
            setComponentsForNewShip();
            setShip(getDesignIndex(this.selectedSizeIndex));
        }
        this.renameIcon.setX(this.designName.getX() + this.designName.getWidthScaled() + 10.0f);
    }

    private void setSelectedSize(ShipType shipType) {
        int i = this.selectedSizeIndex;
        int i2 = shipType.id;
        this.selectedSizeIndex = i2;
        this.selectedSize.setX(((i2 - 4) * 110) + 10);
        if (this.designName.getText().equals(ShipType.getShipType(i).getString(this.B.getCurrentPlayer()))) {
            setDesignName(ShipType.getShipType(this.selectedSizeIndex).getString(this.B.getCurrentPlayer()));
        }
        int i3 = AnonymousClass1.f1477a[shipType.ordinal()];
        if (i3 == 1) {
            this.designBackground.setHeight(555.0f);
        } else if (i3 == 2 || i3 == 3) {
            this.designBackground.setHeight(475.0f);
        } else if (i3 == 4) {
            this.designBackground.setHeight(395.0f);
        }
        if (this.shipComponents.size() >= shipType.getNumberOfComponents()) {
            this.shipComponents = this.shipComponents.subList(0, shipType.getNumberOfComponents());
        }
    }

    private void setShield() {
        this.shieldSelector.set(this.shield, ShipType.getShipType(this.selectedSizeIndex).getSizeClass());
        updateProductionCostAndSpace();
    }

    private void setShip(int i) {
        ShipType shipType = ShipType.getShipType(this.selectedSizeIndex);
        this.shipImage.setShipIcon(this.B.getCurrentPlayer(), shipType, i, 150.0f, shipType.getSelectScreenSize() * 1.5f, 1, true);
        this.shipTypeIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
        setArmor();
        setShield();
        setTargetingComputer();
        setSublightEngine();
        setShipComponents();
        this.beamAccuracy.setText(this.B.getActivity().getString(R.string.ship_design_beam_accuracy, new Object[]{Integer.valueOf(getTargetAccuracyBonus())}));
        this.evasion.setText(this.B.getActivity().getString(R.string.ship_design_beam_evasion, new Object[]{Integer.valueOf(getEvasionBonus())}));
    }

    private void setShipComponents() {
        int numberOfComponents = ShipType.getShipType(this.selectedSizeIndex).getNumberOfComponents();
        int i = 0;
        for (ShipComponentSelector shipComponentSelector : this.componentSelectors) {
            shipComponentSelector.clear();
        }
        for (ShipComponent shipComponent : this.shipComponents) {
            setWeaponAndSpecialComponents(i, shipComponent);
            i++;
        }
        if (i < numberOfComponents) {
            this.componentSelectors[i].setForWeaponOrSpecialChoice();
            i++;
        }
        while (i < numberOfComponents) {
            this.componentSelectors[i].disable();
            i++;
        }
        updateProductionCostAndSpace();
    }

    private void setShipYards() {
        this.selectedDesignIndex = 0;
        ShipType shipType = ShipType.DESTROYER;
        int size = this.shipDesigns.size();
        int i = this.selectedDesignIndex;
        if (size > i) {
            shipType = this.shipDesigns.get(i).getShipType();
        }
        setSelectedSize(shipType);
        setDesignList();
        setSelectedDesign(this.selectedDesignIndex);
        float f2 = this.B.getCurrentEmpire().hasTech(TechID.DREADNOUGHT) ? 1.0f : 0.45f;
        this.dreadnoughtSizeSelector.setAlpha(f2);
        this.dreadnoughtIcon.setAlpha(f2);
        this.dreadnoughtCommandPointIcon.setAlpha(f2);
        this.dreadnoughtCommandPointCost.setAlpha(f2);
    }

    private void setSpritesInvisible() {
        this.planetButton.setVisible(false);
        this.productionButton.setVisible(false);
        this.pressSprite.setVisible(false);
        this.nebulas.setVisible(false);
    }

    private void setSublightEngine() {
        this.sublightSelector.set(this.sublightEngine);
        updateProductionCostAndSpace();
    }

    private void setTargetingComputer() {
        this.targetingSelector.set(this.targetingComputer);
        this.beamAccuracy.setText(this.B.getActivity().getString(R.string.ship_design_beam_accuracy, new Object[]{Integer.valueOf(getTargetAccuracyBonus())}));
        updateProductionCostAndSpace();
    }

    private void setWeaponAndSpecialComponents(int i, ShipComponent shipComponent) {
        if (shipComponent instanceof Weapon) {
            this.componentSelectors[i].set((Weapon) shipComponent);
        } else {
            this.componentSelectors[i].set((SpecialComponent) shipComponent);
        }
        updateProductionCostAndSpace();
        setArmor();
    }

    private void shipDesignPressed(int i, Point point) {
        if (this.selectedDesignListBackground.get(i).getAlpha() != 0.4f) {
            if (this.plusButtonList.get(i).isVisible()) {
                setSelectedDesign(i);
            } else if (point.getX() > getWidth() - 50.0f) {
                this.B.confirmOverlay.setOverlay(1, i);
                setChildScene(this.B.confirmOverlay, false, false, true);
            } else {
                setSelectedDesign(i);
            }
            this.B.sounds.playBoxPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        }
    }

    private void shipSizePressed(int i) {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        ShipType shipType = ShipType.getShipType(i + 4);
        if (shipType == ShipType.DREADNOUGHT && !this.B.getCurrentEmpire().hasTech(TechID.DREADNOUGHT)) {
            showMessage(new TextMessage(this.B.getActivity().getString(R.string.ship_design_dreadnought_tech_needed)));
            return;
        }
        setSelectedSize(shipType);
        setShip(getDesignIndex(shipType));
    }

    private void shipSpritePressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        ShipType shipType = ShipType.getShipType(this.selectedSizeIndex);
        updateHullDesign(shipType);
        setShip(getDesignIndex(shipType));
    }

    private void updateHullDesign(ShipType shipType) {
        int designIndex = getDesignIndex(shipType) + 1;
        if (designIndex == 5) {
            designIndex = 0;
        }
        this.designIndexes[shipType.id] = designIndex;
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.loadSystem(((Integer) obj).intValue());
        } else if (extendedScene instanceof PlanetScene) {
            Game game = this.B;
            game.planetScene.loadPlanet(this.systemID, this.orbit, game.productionScene);
        } else if (extendedScene instanceof ProductionScene) {
            this.B.productionScene.O();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (hasChildScene()) {
            this.B.keyboardOverlay.back();
            this.H.back();
        } else if (t()) {
        } else {
            if (this.productionButton.isVisible()) {
                changeScene(this.B.productionScene);
            } else {
                changeScene(this.B.fleetsScene);
            }
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        this.armorSelector.checkInput(i, point);
        this.shieldSelector.checkInput(i, point);
        this.targetingSelector.checkInput(i, point);
        this.sublightSelector.checkInput(i, point);
        for (ShipComponentSelector shipComponentSelector : this.componentSelectors) {
            shipComponentSelector.checkInput(i, point);
        }
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            checkActionUp(point);
        } else if (i == 2) {
            checkActionMove(point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.bufferManager = vertexBufferObjectManager;
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        H(500.0f, 550.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SHIP_YARDS.ordinal(), true).setSize(170.0f, 170.0f);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
        E.setSize(getWidth(), 720.0f);
        E.setAlpha(0.6f);
        this.pressSprite = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        boolean z = false;
        int i = 0;
        while (i < 6) {
            int i2 = (i * 105) + 86;
            float f2 = i2;
            Sprite E2 = E(getWidth() - 450.0f, f2, this.B.graphics.colonyBackgroundTexture, vertexBufferObjectManager, true);
            E2.setAlpha(0.8f);
            E2.setHeight(100.0f);
            this.selectedDesignListBackground.add(E2);
            Sprite E3 = E(getWidth() - 450.0f, f2, this.B.graphics.farmingBarTexture, vertexBufferObjectManager, true);
            E3.setAlpha(0.4f);
            E3.setHeight(100.0f);
            this.selectedList.add(E3);
            float f3 = i2 + 12;
            TiledSprite J = J(getWidth() - 263.0f, f3, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, false);
            J.setScaleCenter(0.0f, 0.0f);
            J.setCurrentTileIndex(GameIconEnum.ADD.ordinal());
            J.setScale(0.75f);
            this.plusButtonList.add(J);
            ShipSprite shipSprite = new ShipSprite(this.B, vertexBufferObjectManager);
            shipSprite.setPosition(getWidth() - 450.0f, f2);
            shipSprite.setVisible(z);
            attachChild(shipSprite);
            this.shipIcons.add(shipSprite);
            this.shipNames.add(G(getWidth() - 340.0f, f3, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager, false));
            Text G = G(getWidth() - 340.0f, i2 + 45, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false);
            this.productionCostList.add(G);
            TiledSprite H = H(0.0f, G.getY() - 7.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.PRODUCTION.ordinal(), false);
            H.setX((getWidth() - 60.0f) - H.getWidthScaled());
            this.productionCostIconList.add(H);
            this.shipTypeIcons.add(J(getWidth() - 450.0f, f2, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, false));
            Sprite E4 = E(getWidth() - 55.0f, f2, this.B.graphics.colonyBackgroundTexture, vertexBufferObjectManager, false);
            E4.setAlpha(0.8f);
            E4.setSize(55.0f, 100.0f);
            this.removeButtonBackgrounds.add(E4);
            TiledSprite H2 = H(getWidth() - 52.0f, i2 + 25, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.CLOSE.ordinal(), false);
            H2.setScaleCenter(0.0f, 0.0f);
            H2.setScale(0.5f);
            this.removeButtons.add(H2);
            i++;
            z = false;
        }
        Sprite E5 = E(0.0f, 0.0f, this.B.graphics.colonyBackgroundTexture, vertexBufferObjectManager, true);
        E5.setAlpha(0.8f);
        E5.setSize(450.0f, 120.0f);
        Sprite E6 = E(0.0f, 10.0f, this.B.graphics.farmingBarTexture, vertexBufferObjectManager, true);
        this.selectedSize = E6;
        E6.setAlpha(0.4f);
        this.selectedSize.setSize(100.0f, 100.0f);
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.gameIconsTexture;
        GameIconEnum gameIconEnum = GameIconEnum.SHIPS;
        TiledSprite H3 = H(-7.0f, -10.0f, iTiledTextureRegion, vertexBufferObjectManager, gameIconEnum.ordinal(), true);
        H3.setSize(130.0f, 130.0f);
        H3.setScaleCenter(65.0f, 65.0f);
        ShipType shipType = ShipType.DESTROYER;
        H3.setScale(shipType.getScale());
        H(10.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.DESTROYER_ICON.ordinal(), true);
        Text F = F(0.0f, 105.0f, this.B.fonts.smallInfoFont, Integer.toString(shipType.getCommandPointCost()), this.E, vertexBufferObjectManager);
        ITiledTextureRegion iTiledTextureRegion2 = this.B.graphics.infoIconsTexture;
        InfoIconEnum infoIconEnum = InfoIconEnum.COMMAND_POINTS;
        TiledSprite H4 = H(0.0f, 98.0f, iTiledTextureRegion2, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        F.setX(60.0f - (((F.getWidthScaled() + 5.0f) + 30.0f) / 2.0f));
        H4.setX(F.getX() + F.getWidthScaled() + 5.0f);
        TiledSprite H5 = H(103.0f, -10.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, gameIconEnum.ordinal(), true);
        H5.setSize(130.0f, 130.0f);
        H5.setScaleCenter(65.0f, 65.0f);
        ShipType shipType2 = ShipType.CRUISER;
        H5.setScale(shipType2.getScale());
        H(120.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.CRUISER_ICON.ordinal(), true);
        Text F2 = F(0.0f, 105.0f, this.B.fonts.smallInfoFont, Integer.toString(shipType2.getCommandPointCost()), this.E, vertexBufferObjectManager);
        TiledSprite H6 = H(0.0f, 98.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        F2.setX(170.0f - (((F2.getWidthScaled() + 5.0f) + 30.0f) / 2.0f));
        H6.setX(F2.getX() + F2.getWidthScaled() + 5.0f);
        TiledSprite H7 = H(213.0f, -10.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, gameIconEnum.ordinal(), true);
        H7.setSize(130.0f, 130.0f);
        H7.setScaleCenter(65.0f, 65.0f);
        ShipType shipType3 = ShipType.BATTLESHIP;
        H7.setScale(shipType3.getScale());
        H(230.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.BATTLESHIP_ICON.ordinal(), true);
        Text F3 = F(0.0f, 105.0f, this.B.fonts.smallInfoFont, Integer.toString(shipType3.getCommandPointCost()), this.E, vertexBufferObjectManager);
        TiledSprite H8 = H(0.0f, 98.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        F3.setX(280.0f - (((F3.getWidthScaled() + 5.0f) + 30.0f) / 2.0f));
        H8.setX(F3.getX() + F3.getWidthScaled() + 5.0f);
        TiledSprite H9 = H(323.0f, -10.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, gameIconEnum.ordinal(), true);
        this.dreadnoughtSizeSelector = H9;
        H9.setSize(130.0f, 130.0f);
        this.dreadnoughtSizeSelector.setScaleCenter(65.0f, 65.0f);
        TiledSprite tiledSprite = this.dreadnoughtSizeSelector;
        ShipType shipType4 = ShipType.DREADNOUGHT;
        tiledSprite.setScale(shipType4.getScale());
        this.dreadnoughtIcon = H(340.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.DREADNOUGHT_ICON.ordinal(), true);
        this.dreadnoughtCommandPointCost = F(0.0f, 105.0f, this.B.fonts.smallInfoFont, Integer.toString(shipType4.getCommandPointCost()), this.E, vertexBufferObjectManager);
        this.dreadnoughtCommandPointIcon = H(0.0f, 98.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        Text text = this.dreadnoughtCommandPointCost;
        text.setX(390.0f - (((text.getWidthScaled() + 5.0f) + 30.0f) / 2.0f));
        this.dreadnoughtCommandPointIcon.setX(this.dreadnoughtCommandPointCost.getX() + this.dreadnoughtCommandPointCost.getWidthScaled() + 5.0f);
        Sprite E7 = E(0.0f, 130.0f, this.B.graphics.colonyBackgroundTexture, vertexBufferObjectManager, true);
        this.designBackground = E7;
        E7.setAlpha(0.8f);
        this.designBackground.setSize(820.0f, 555.0f);
        ShipSprite shipSprite2 = new ShipSprite(this.B, vertexBufferObjectManager);
        this.shipImage = shipSprite2;
        shipSprite2.setPosition(0.0f, 130.0f);
        attachChild(this.shipImage);
        this.designName = F(200.0f, 155.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager);
        this.renameIcon = H(0.0f, 150.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.RENAME.ordinal(), true);
        this.shipTypeIcon = J(160.0f, 148.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, true);
        IFont iFont = this.B.fonts.smallInfoFont;
        CharSequence charSequence = this.D;
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        this.beamAccuracy = F(160.0f, 195.0f, iFont, charSequence, new TextOptions(autoWrap, 300.0f, horizontalAlign), vertexBufferObjectManager);
        this.evasion = F(160.0f, 235.0f, this.B.fonts.smallInfoFont, this.D, new TextOptions(autoWrap, 300.0f, horizontalAlign), vertexBufferObjectManager);
        Game game = this.B;
        Text F4 = F(0.0f, 140.0f, game.fonts.smallFont, game.getActivity().getString(R.string.ship_design_production_cost), this.E, vertexBufferObjectManager);
        F4.setX(680.0f - F4.getWidthScaled());
        this.productionCost = F(690.0f, 140.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager);
        this.productionCostIcon = H(0.0f, 135.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.PRODUCTION.ordinal(), true);
        Game game2 = this.B;
        Text F5 = F(0.0f, 170.0f, game2.fonts.smallFont, game2.getActivity().getString(R.string.ship_design_available_space), this.E, vertexBufferObjectManager);
        F5.setX(680.0f - F5.getWidthScaled());
        this.availableSpace = F(690.0f, 170.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager);
        this.availableSpaceIcon = H(0.0f, 165.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.SPACE.ordinal(), true);
        Game game3 = this.B;
        Text F6 = F(0.0f, 0.0f, game3.fonts.smallFont, game3.getActivity().getString(R.string.ship_design_hull_design), this.E, vertexBufferObjectManager);
        F6.setColor(new Color(0.7f, 0.7f, 0.7f));
        F6.setY(233.0f - (F6.getHeightScaled() / 2.0f));
        F6.setX(690.0f - F6.getWidthScaled());
        TiledSprite H10 = H((F6.getX() - 10.0f) - 120.0f, 190.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PREVIOUS.ordinal(), true);
        this.previousHullButton = H10;
        s(H10);
        TiledSprite H11 = H(700.0f, 190.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.NEXT.ordinal(), true);
        this.nextHullButton = H11;
        s(H11);
        ShipComponentSelector shipComponentSelector = new ShipComponentSelector(10.0f, 280.0f, this.B, vertexBufferObjectManager, ShipComponentSelectorType.ARMOR);
        this.armorSelector = shipComponentSelector;
        attachChild(shipComponentSelector);
        ShipComponentSelector shipComponentSelector2 = new ShipComponentSelector(415.0f, 280.0f, this.B, vertexBufferObjectManager, ShipComponentSelectorType.SHIELD);
        this.shieldSelector = shipComponentSelector2;
        attachChild(shipComponentSelector2);
        ShipComponentSelector shipComponentSelector3 = new ShipComponentSelector(10.0f, 360.0f, this.B, vertexBufferObjectManager, ShipComponentSelectorType.TARGETING_COMPUTER);
        this.targetingSelector = shipComponentSelector3;
        attachChild(shipComponentSelector3);
        ShipComponentSelector shipComponentSelector4 = new ShipComponentSelector(415.0f, 360.0f, this.B, vertexBufferObjectManager, ShipComponentSelectorType.ENGINE);
        this.sublightSelector = shipComponentSelector4;
        attachChild(shipComponentSelector4);
        ShipComponentSelector[] shipComponentSelectorArr = this.componentSelectors;
        Game game4 = this.B;
        ShipComponentSelectorType shipComponentSelectorType = ShipComponentSelectorType.WEAPON_OR_SPECIAL;
        shipComponentSelectorArr[0] = new ShipComponentSelector(10.0f, 440.0f, game4, vertexBufferObjectManager, shipComponentSelectorType, 0);
        attachChild(this.componentSelectors[0]);
        this.componentSelectors[1] = new ShipComponentSelector(415.0f, 440.0f, this.B, vertexBufferObjectManager, shipComponentSelectorType, 1);
        attachChild(this.componentSelectors[1]);
        this.componentSelectors[2] = new ShipComponentSelector(10.0f, 520.0f, this.B, vertexBufferObjectManager, shipComponentSelectorType, 2);
        attachChild(this.componentSelectors[2]);
        this.componentSelectors[3] = new ShipComponentSelector(415.0f, 520.0f, this.B, vertexBufferObjectManager, shipComponentSelectorType, 3);
        attachChild(this.componentSelectors[3]);
        this.componentSelectors[4] = new ShipComponentSelector(10.0f, 600.0f, this.B, vertexBufferObjectManager, shipComponentSelectorType, 4);
        attachChild(this.componentSelectors[4]);
        this.componentSelectors[5] = new ShipComponentSelector(415.0f, 600.0f, this.B, vertexBufferObjectManager, shipComponentSelectorType, 5);
        attachChild(this.componentSelectors[5]);
        TiledSprite H12 = H(635.0f, 17.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.saveButton = H12;
        H12.setAlpha(0.7f);
        this.saveButton.setSize(150.0f, 86.0f);
        this.saveButton.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(1.0f, 0.6f, 0.7f), new AlphaModifier(1.0f, 0.7f, 0.6f))));
        Sprite sprite = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.saveButtonPressed = sprite;
        sprite.setSize(this.saveButton.getWidthScaled() - 4.0f, this.saveButton.getHeightScaled() - 4.0f);
        this.saveButtonPressed.setVisible(false);
        this.saveButton.attachChild(this.saveButtonPressed);
        Game game5 = this.B;
        Text text2 = new Text(0.0f, 0.0f, game5.fonts.smallFont, game5.getActivity().getString(R.string.ship_design_save), this.E, vertexBufferObjectManager);
        this.saveText = text2;
        text2.setX(75.0f - (text2.getWidthScaled() / 2.0f));
        Text text3 = this.saveText;
        text3.setY(43.0f - (text3.getHeightScaled() / 2.0f));
        Text text4 = this.saveText;
        Color color = Color.BLACK;
        text4.setColor(color);
        this.saveButton.attachChild(this.saveText);
        TiledSprite H13 = H(465.0f, 17.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.autoButton = H13;
        H13.setAlpha(0.9f);
        this.autoButton.setSize(150.0f, 86.0f);
        Sprite sprite2 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.autoButtonPressed = sprite2;
        sprite2.setSize(this.autoButton.getWidthScaled() - 4.0f, this.autoButton.getHeightScaled() - 4.0f);
        this.autoButtonPressed.setVisible(false);
        this.autoButton.attachChild(this.autoButtonPressed);
        Game game6 = this.B;
        Text text5 = new Text(0.0f, 0.0f, game6.fonts.smallFont, game6.getActivity().getString(R.string.ship_design_auto), this.E, vertexBufferObjectManager);
        this.autoText = text5;
        text5.setX(75.0f - (text5.getWidthScaled() / 2.0f));
        Text text6 = this.autoText;
        text6.setY(43.0f - (text6.getHeightScaled() / 2.0f));
        this.autoText.setColor(color);
        this.autoButton.attachChild(this.autoText);
        TiledSprite H14 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H14;
        s(H14);
        TiledSprite H15 = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, 0, true);
        this.level2Button = H15;
        s(H15);
        EmpireButton empireButton = new EmpireButton(this.B, vertexBufferObjectManager);
        this.empireButton = empireButton;
        attachChild(empireButton);
        this.empireButton.setPosition(getWidth() - 240.0f, 0.0f);
        s(this.empireButton);
        TiledSprite H16 = H(getWidth() - 360.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PLANET.ordinal(), false);
        this.planetButton = H16;
        s(H16);
        TiledSprite H17 = H(getWidth() - 480.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRODUCTION.ordinal(), false);
        this.productionButton = H17;
        s(H17);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
        this.selectComponentOverlay = new SelectComponentOverlay(this.B, vertexBufferObjectManager);
    }

    public int getAvailableSpace() {
        ShipType shipType = ShipType.getShipType(this.selectedSizeIndex);
        int baseAvailableSpace = (((shipType.getBaseAvailableSpace() - this.B.getCurrentEmpire().getComponentSpaceAfterMiniaturization(this.armor)) - this.B.getCurrentEmpire().getComponentSpaceAfterMiniaturization(this.shield)) - this.B.getCurrentEmpire().getComponentSpaceAfterMiniaturization(this.targetingComputer)) - this.B.getCurrentEmpire().getComponentSpaceAfterMiniaturization(this.sublightEngine);
        for (ShipComponent shipComponent : this.shipComponents) {
            baseAvailableSpace -= this.B.getCurrentEmpire().getComponentSpaceAfterMiniaturization(shipComponent) * shipComponent.getComponentCount();
            if (shipComponent.getID() == ShipComponentID.EXTENDED_HULL) {
                baseAvailableSpace = (int) (baseAvailableSpace + (shipType.getBaseAvailableSpace() * ((SpecialComponent) shipComponent).getEffectValue()));
            }
        }
        return baseAvailableSpace;
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.planetSprite = planetSprite;
        planetSprite.setPosition(100.0f, 560.0f);
        this.planetSprite.setMoonRange(300, 300);
        this.nebulaBackground.attachChild(this.planetSprite);
    }

    public Armor getSelectedArmor() {
        return this.armor;
    }

    public Shield getSelectedShield() {
        return this.shield;
    }

    public SublightEngine getSelectedSublightEngine() {
        return this.sublightEngine;
    }

    public TargetingComputer getSelectedTargetingComputer() {
        return this.targetingComputer;
    }

    public ShipComponent getShipComponent(int i) {
        return this.shipComponents.get(i);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.j0
            {
                ShipDesignScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ShipDesignScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void removeComponent(int i) {
        if (this.shipComponents.size() > i) {
            this.shipComponents.remove(i);
            setShipComponents();
            setArmor();
        }
    }

    public void removeDesign(int i) {
        this.B.getCurrentEmpire().removeShipDesign(i);
        int i2 = this.selectedDesignIndex;
        if (i2 == i) {
            setSelectedDesign(0);
        } else if (i2 > i) {
            setSelectedDesign(i2 - 1);
        }
        setDesignList();
    }

    public void set() {
        setSpritesInvisible();
        this.level2Button.setVisible(false);
        this.empireButton.setVisible(false);
        setShipYards();
    }

    public void setDesignName(String str) {
        this.designName.setText(str);
        this.renameIcon.setX(this.designName.getX() + this.designName.getWidthScaled() + 10.0f);
    }

    public void setFromFleetScene() {
        setSpritesInvisible();
        this.empireButton.setVisible(false);
        this.level2Button.setVisible(true);
        this.level2Button.setCurrentTileIndex(ButtonsEnum.FLEETS.ordinal());
        this.planetSprite.setVisible(false);
        setShipYards();
    }

    public void setSelectedArmor(Armor armor) {
        this.armor = armor;
        setArmor();
    }

    public void setSelectedComponent(ShipComponent shipComponent, int i) {
        if (this.shipComponents.size() - 1 < i) {
            this.shipComponents.add(shipComponent);
        } else if (this.shipComponents.get(i).getID() != shipComponent.getID()) {
            this.shipComponents.set(i, shipComponent);
        }
        setShipComponents();
    }

    public void setSelectedEngine(SublightEngine sublightEngine) {
        this.sublightEngine = sublightEngine;
        setSublightEngine();
    }

    public void setSelectedShield(Shield shield) {
        this.shield = shield;
        setShield();
    }

    public void setSelectedTargeting(TargetingComputer targetingComputer) {
        this.targetingComputer = targetingComputer;
        setTargetingComputer();
    }

    public void showSelectComponentOverlay(ShipComponentSelectorType shipComponentSelectorType, int i, boolean z) {
        this.selectComponentOverlay.setOverlay(shipComponentSelectorType, ShipType.values()[this.selectedSizeIndex - 3], i, z, this.shipComponents, getAvailableSpace());
        setChildScene(this.selectComponentOverlay, false, false, true);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }

    public void updateProductionCostAndSpace() {
        this.productionCost.setText(Integer.toString(getProductionCost()));
        this.productionCostIcon.setX(this.productionCost.getX() + this.productionCost.getWidthScaled() + 5.0f);
        this.availableSpace.setText(Integer.toString(getAvailableSpace()));
        this.availableSpaceIcon.setX(this.availableSpace.getX() + this.availableSpace.getWidthScaled() + 2.0f);
    }

    private int getDesignIndex(int i) {
        return this.designIndexes[i];
    }

    public void set(int i) {
        setSpritesInvisible();
        this.level2Button.setVisible(false);
        this.empireButton.setVisible(true);
        this.empireButton.set(i);
        this.planetSprite.setVisible(false);
        setShipYards();
    }

    public void set(int i, int i2) {
        this.systemID = i;
        this.orbit = i2;
        setSpritesInvisible();
        this.nebulas.setVisible(true);
        this.nebulas.set(i);
        this.level2Button.setVisible(true);
        this.level2Button.setCurrentTileIndex(ButtonsEnum.SYSTEM.ordinal());
        this.empireButton.setVisible(false);
        this.planetButton.setVisible(true);
        this.productionButton.setVisible(true);
        this.planetSprite.setVisible(true);
        this.planetSprite.setPlanet((Planet) this.B.galaxy.getSystemObject(i, i2), 3.0f, 1.0f);
        setShipYards();
    }
}
