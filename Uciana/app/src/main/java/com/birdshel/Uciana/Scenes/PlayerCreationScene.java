package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Controls.EmpireSelectControl;
import com.birdshel.Uciana.Difficulty;
import com.birdshel.Uciana.Elements.PlayerCreationScene.RaceAttributesElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.CreateEmpireInfo;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.Players.RaceAttribute;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.GalaxySize;
import com.birdshel.Uciana.StarSystems.Nebulas;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlayerCreationScene extends ExtendedScene {
    private TiledSprite addPlayerButton;
    private Sprite addPlayerButtonPressed;
    private Text addPlayerSummary;
    private Text addPlayerText;
    private Sprite ambassador;
    private List<RaceAttribute> attributes;
    private TiledSprite backButton;
    private Sprite background;
    private TiledSprite bannerSelectedSprite;
    private TiledSprite colorSelectedSprite;
    private int customBannerID;
    private TiledSprite customButton;
    private int customEmpireID;
    private TiledSprite customEmpirePress;
    private int customRaceID;
    private int customShipStyleID;
    private TiledSprite customizeButton;
    private Sprite customizeButtonPressed;
    private Text customizeText;
    private TiledSprite defaultPerksRadioButton;
    private Text defaultPerksText;
    private Difficulty difficulty;
    private TiledSprite empireBanner;
    private TiledSprite empireBannerBackground;
    private Entity empireBar;
    private Text empireName;
    private EmpireSelectControl empireSelectControl;
    private Entity fleet;
    private GalaxySize galaxySize;
    private Map<GameSettingsEnum, Integer> gameSettings;
    private PlanetSprite homeworldImage;
    private Sprite infantryIcon;
    private TiledSprite menuButton;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private TiledSprite noPerksRadioButton;
    private Text noPerksText;
    private int numberOfEmpires;
    private RaceAttributesElement raceAttributes;
    private Text raceInfo;
    private TiledSprite raceSelectedSprite;
    private TiledSprite randomEmperor;
    private Entity randomEmpirePerks;
    private TiledSprite randomPerksRadioButton;
    private Text randomPerksText;
    private List<Integer> randomPlayersChoice;
    private Text selectEmpireText;
    private int selectedEmpireID;
    private TiledSprite shipStyleSelectedSprite;
    private TiledSprite startButton;
    private Sprite startButtonPressed;
    private Text startGameSummary;
    private Text startText;
    private final List<ShipSprite> ships = new ArrayList();
    private final CreateEmpireInfo[] empires = new CreateEmpireInfo[7];
    private final TiledSprite[] empireColorSelect = new TiledSprite[7];
    private final TiledSprite[] empireBannerSelect = new TiledSprite[7];
    private final Sprite[] empireRaceSelect = new Sprite[7];
    private final List<ShipSprite> empireShipStyleSelect = new ArrayList();
    private List<Integer> availableColors = new ArrayList();
    private List<Integer> availableBanners = new ArrayList();
    private List<Integer> availableRaces = new ArrayList();
    private List<Integer> availableShipStyles = new ArrayList();
    private final int MENU = 1;
    private final int GALAXY = 2;
    private final int SETUP = 3;

    public PlayerCreationScene(Game game) {
        this.B = game;
    }

    private void addPlayerButtonPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        assignPlayer();
        this.fleet.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 670.0f, getWidth() + 100.0f, 190.0f, 190.0f) { // from class: com.birdshel.Uciana.Scenes.PlayerCreationScene.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: m */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                PlayerCreationScene.this.setPlayer(-1);
            }
        });
        swipeContentsOff();
    }

    private void assignPlayer() {
        int i = this.selectedEmpireID;
        if (i == -2) {
            this.randomPlayersChoice.add(Integer.valueOf((int) Game.options.getOption(OptionID.RANDOM_EMPIRE_PERK, 0.0f)));
        } else if (i == -3) {
            int raceID = this.empires[this.customEmpireID].getRaceID();
            int bannerID = this.empires[this.customEmpireID].getBannerID();
            int shipStyleID = this.empires[this.customEmpireID].getShipStyleID();
            this.empires[this.customEmpireID].setEmpireType(EmpireType.HUMAN);
            this.empires[this.customEmpireID].setRaceAttributes(this.attributes);
            this.empires[this.customEmpireID].setBannerID(this.customBannerID);
            if (this.B.graphics.isAnyShipsModded()) {
                this.empires[this.customEmpireID].setShipStyleID(this.customRaceID);
            } else {
                this.empires[this.customEmpireID].setShipStyleID(this.customShipStyleID);
            }
            this.empires[this.customEmpireID].setRaceID(this.customRaceID);
            this.empires[this.customRaceID].setRaceID(raceID);
            this.empires[this.customBannerID].setBannerID(bannerID);
            this.empires[this.customShipStyleID].setShipStyleID(shipStyleID);
            Game game = this.B;
            game.graphics.setShipIconsTextures(this.customShipStyleID, shipStyleID, game.getActivity());
        } else {
            this.empires[i].setEmpireType(EmpireType.HUMAN);
            this.empires[this.selectedEmpireID].setRaceAttributes(this.attributes);
            CreateEmpireInfo[] createEmpireInfoArr = this.empires;
            int i2 = this.selectedEmpireID;
            createEmpireInfoArr[i2].setBannerID(i2);
            CreateEmpireInfo[] createEmpireInfoArr2 = this.empires;
            int i3 = this.selectedEmpireID;
            createEmpireInfoArr2[i3].setShipStyleID(i3);
            CreateEmpireInfo[] createEmpireInfoArr3 = this.empires;
            int i4 = this.selectedEmpireID;
            createEmpireInfoArr3[i4].setRaceID(i4);
        }
    }

    private void backButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        closeScene(3);
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
        if (isClicked(this.menuButton, point)) {
            menuButtonPressed();
        }
        if (isClicked(this.backButton, point)) {
            backButtonPressed();
        }
        if (isClicked(this.customButton, point)) {
            customButtonPressed();
        }
        if (isClicked(this.customizeButton, point)) {
            customizeButtonPressed();
        }
        if (this.selectedEmpireID == -2 && point.getX() > this.randomEmpirePerks.getX() && point.getX() < this.randomEmpirePerks.getX() + 400.0f) {
            Point point2 = new Point(point.getX() - this.randomEmpirePerks.getX(), point.getY() - this.randomEmpirePerks.getY());
            if (isClicked(this.defaultPerksRadioButton, point2) || z(this.defaultPerksText, point2, 0, 15)) {
                selectPerkChoice(0);
            }
            if (isClicked(this.randomPerksRadioButton, point2) || z(this.randomPerksText, point2, 0, 15)) {
                selectPerkChoice(1);
            }
            if (isClicked(this.noPerksRadioButton, point2) || z(this.noPerksText, point2, 0, 15)) {
                selectPerkChoice(2);
            }
        }
        if (isClicked(this.addPlayerButton, point)) {
            addPlayerButtonPressed();
        }
        if (isClicked(this.startButton, point)) {
            startButtonPressed();
        }
        if (this.selectedEmpireID == -3) {
            for (int i = 0; i < 7; i++) {
                if (this.empireColorSelect[i].isVisible() && isClicked(this.empireColorSelect[i], point)) {
                    empireColorSelectPressed(i);
                }
                if (this.empireBannerSelect[i].isVisible() && isClicked(this.empireBannerSelect[i], point)) {
                    empireBannerSelectPressed(i);
                }
                if (this.empireRaceSelect[i].isVisible() && isClicked(this.empireRaceSelect[i], point)) {
                    empireRaceSelectPressed(i);
                }
                if (this.empireShipStyleSelect.get(i).isVisible() && point.getX() > this.empireShipStyleSelect.get(i).getX() && point.getX() < this.empireShipStyleSelect.get(i).getX() + this.empireShipStyleSelect.get(i).getSize() && point.getY() > this.empireShipStyleSelect.get(i).getY() && point.getY() < this.empireShipStyleSelect.get(i).getY() + this.empireShipStyleSelect.get(i).getSize()) {
                    empireShipStyleSelectPressed(i);
                }
            }
        }
    }

    private void checkPressed(Point point) {
        if (isClicked(this.addPlayerButton, point)) {
            this.addPlayerButtonPressed.setVisible(true);
            Text text = this.addPlayerText;
            Color color = Color.WHITE;
            text.setColor(color);
            this.addPlayerSummary.setColor(color);
        }
        if (isClicked(this.startButton, point)) {
            this.startButtonPressed.setVisible(true);
            Text text2 = this.startText;
            Color color2 = Color.WHITE;
            text2.setColor(color2);
            this.startGameSummary.setColor(color2);
        }
        if (isClicked(this.customizeButton, point)) {
            this.customizeButtonPressed.setVisible(true);
            this.customizeText.setColor(Color.WHITE);
        }
        if (this.selectedEmpireID == -3) {
            for (int i = 0; i < 7; i++) {
                if (this.empireColorSelect[i].isVisible() && isClicked(this.empireColorSelect[i], point)) {
                    this.customEmpirePress.setVisible(true);
                    this.customEmpirePress.setX(this.empireColorSelect[i].getX() - 5.0f);
                    this.customEmpirePress.setY(this.empireColorSelect[i].getY() - 5.0f);
                    this.customEmpirePress.setSize(110.0f, 85.0f);
                }
                if (this.empireBannerSelect[i].isVisible() && isClicked(this.empireBannerSelect[i], point)) {
                    this.customEmpirePress.setVisible(true);
                    this.customEmpirePress.setX(this.empireBannerSelect[i].getX() - 5.0f);
                    this.customEmpirePress.setY(this.empireBannerSelect[i].getY() + 10.0f);
                    this.customEmpirePress.setSize(110.0f, 80.0f);
                }
                if (this.empireRaceSelect[i].isVisible() && isClicked(this.empireRaceSelect[i], point)) {
                    this.customEmpirePress.setVisible(true);
                    this.customEmpirePress.setX(this.empireRaceSelect[i].getX() - 5.0f);
                    this.customEmpirePress.setY(this.empireRaceSelect[i].getY() - 5.0f);
                    this.customEmpirePress.setSize(110.0f, 130.0f);
                }
                if (this.empireShipStyleSelect.get(i).isVisible() && point.getX() > this.empireShipStyleSelect.get(i).getX() && point.getX() < this.empireShipStyleSelect.get(i).getX() + this.empireShipStyleSelect.get(i).getSize() && point.getY() > this.empireShipStyleSelect.get(i).getY() && point.getY() < this.empireShipStyleSelect.get(i).getY() + this.empireShipStyleSelect.get(i).getSize()) {
                    this.customEmpirePress.setVisible(true);
                    this.customEmpirePress.setPosition(this.empireShipStyleSelect.get(i).getX() - 15.0f, 535.0f);
                    this.customEmpirePress.setSize(110.0f, 90.0f);
                }
            }
        }
    }

    private void clearPerkRadioButtons() {
        TiledSprite tiledSprite = this.defaultPerksRadioButton;
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_OFF;
        tiledSprite.setCurrentTileIndex(buttonsEnum.ordinal());
        this.randomPerksRadioButton.setCurrentTileIndex(buttonsEnum.ordinal());
        this.noPerksRadioButton.setCurrentTileIndex(buttonsEnum.ordinal());
    }

    private void closeScene(final int i) {
        if (this.selectedEmpireID == -3) {
            swipeCustomContentsOff();
        }
        this.background.registerEntityModifier(new AlphaModifier(0.25f, 0.6f, 0.0f));
        this.fleet.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 670.0f, 100.0f + getWidth(), 190.0f, 190.0f));
        this.homeworldImage.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 180.0f, 320.0f + getWidth(), 220.0f, 220.0f));
        this.ambassador.registerEntityModifier(new MoveModifier(0.15f, 0.0f, -300.0f, 350.0f, 350.0f));
        this.randomEmperor.registerEntityModifier(new MoveModifier(0.15f, 0.0f, -300.0f, 350.0f, 350.0f));
        this.raceAttributes.registerEntityModifier(new MoveModifier(0.15f, 265.0f, -600.0f, 425.0f, 425.0f));
        this.randomEmpirePerks.registerEntityModifier(new MoveModifier(0.15f, 255.0f, -600.0f, 375.0f, 375.0f));
        this.customizeButton.registerEntityModifier(new MoveModifier(0.15f, 265.0f, -600.0f, 355.0f, 355.0f));
        this.infantryIcon.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 440.0f, 200.0f + getWidth(), 525.0f, 525.0f));
        this.raceInfo.registerEntityModifier(new MoveModifier(0.15f, 5.0f, -720.0f, 223.0f, 223.0f));
        this.empireBar.registerEntityModifier(new MoveModifier(0.15f, 0.0f, -700.0f, 170.0f, 170.0f));
        this.empireSelectControl.registerEntityModifier(new MoveModifier(0.15f, (getWidth() / 2.0f) - (this.empireSelectControl.getControlWidth() / 2.0f), (getWidth() / 2.0f) - (this.empireSelectControl.getControlWidth() / 2.0f), 0.0f, -200.0f));
        this.addPlayerButton.registerEntityModifier(new MoveModifier(0.15f, 0.0f, 0.0f, 645.0f, 750.0f));
        Text text = this.selectEmpireText;
        text.registerEntityModifier(new MoveModifier(0.15f, text.getX(), this.selectEmpireText.getX(), 650.0f, 750.0f));
        this.startButton.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 450.0f, getWidth() - 450.0f, 645.0f, 750.0f) { // from class: com.birdshel.Uciana.Scenes.PlayerCreationScene.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: m */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                int i2 = i;
                if (i2 == 2) {
                    PlayerCreationScene playerCreationScene = PlayerCreationScene.this;
                    playerCreationScene.B.newGame(playerCreationScene.galaxySize, PlayerCreationScene.this.numberOfEmpires, PlayerCreationScene.this.empires, PlayerCreationScene.this.difficulty, PlayerCreationScene.this.gameSettings);
                } else if (i2 == 1) {
                    PlayerCreationScene playerCreationScene2 = PlayerCreationScene.this;
                    playerCreationScene2.changeScene(playerCreationScene2.B.menuScene);
                } else {
                    PlayerCreationScene playerCreationScene3 = PlayerCreationScene.this;
                    playerCreationScene3.changeScene(playerCreationScene3.B.setupScene);
                }
            }
        });
    }

    private void customButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        setCustomEmpire(this.selectedEmpireID);
    }

    private void customizeButtonPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        changeScene(this.B.customizeAttributesScene);
    }

    private void disablePress() {
        this.customizeButtonPressed.setVisible(false);
        Text text = this.customizeText;
        Color color = Color.BLACK;
        text.setColor(color);
        this.addPlayerButtonPressed.setVisible(false);
        this.addPlayerText.setColor(color);
        this.addPlayerSummary.setColor(color);
        this.startButtonPressed.setVisible(false);
        this.startText.setColor(color);
        this.startGameSummary.setColor(color);
        this.customEmpirePress.setVisible(false);
    }

    private void empireBannerSelectPressed(int i) {
        this.bannerSelectedSprite.setX(this.empireBannerSelect[i].getX() - 5.0f);
        int intValue = this.availableBanners.get(i).intValue();
        this.customBannerID = intValue;
        Game.options.setOption(OptionID.CUSTOM_EMPIRE_BANNER, intValue);
        setCustomEmpireNameAndBanner();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void empireColorSelectPressed(int i) {
        this.colorSelectedSprite.setX(this.empireColorSelect[i].getX() - 5.0f);
        int intValue = this.availableColors.get(i).intValue();
        this.customEmpireID = intValue;
        Game.options.setOption(OptionID.CUSTOM_EMPIRE_COLOR, intValue);
        setCustomEmpireNameAndBanner();
        for (int i2 = 0; i2 < 7; i2++) {
            Game game = this.B;
            game.graphics.setShipIconsTextures(i2, this.customEmpireID, i2, game.getActivity());
        }
        for (int i3 = 0; i3 < this.availableShipStyles.size(); i3++) {
            this.empireShipStyleSelect.get(i3).setShipIcon(this.availableShipStyles.get(i3).intValue(), this.availableShipStyles.get(i3).intValue(), ShipType.CRUISER, 0, 100.0f, 100.0f, 0, true);
        }
        this.B.sounds.playBoxPressSound();
        Game game2 = this.B;
        game2.vibrate(game2.BUTTON_VIBRATE);
    }

    private void empireRaceSelectPressed(int i) {
        this.raceSelectedSprite.setX(this.empireRaceSelect[i].getX() - 5.0f);
        int intValue = this.availableRaces.get(i).intValue();
        this.customRaceID = intValue;
        Game.options.setOption(OptionID.CUSTOM_EMPIRE_RACE, intValue);
        List<RaceAttribute> asList = Arrays.asList(Empires.getDefaultRaceAttributes(this.customRaceID));
        this.attributes = asList;
        this.raceAttributes.set(asList);
        setCustomEmpireNameAndBanner();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void empireShipStyleSelectPressed(int i) {
        this.shipStyleSelectedSprite.setX(this.empireShipStyleSelect.get(i).getX() - 15.0f);
        int intValue = this.availableShipStyles.get(i).intValue();
        this.customShipStyleID = intValue;
        Game.options.setOption(OptionID.CUSTOM_EMPIRE_SHIP_STYLE, intValue);
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private int getSelectedIndex(int i, List<Integer> list) {
        int i2 = 0;
        for (Integer num : list) {
            if (num.intValue() == i) {
                return i2;
            }
            i2++;
        }
        return 0;
    }

    private void hideCustomEmpireControls() {
        for (int i = 0; i < 7; i++) {
            this.empireColorSelect[i].setVisible(false);
            this.empireBannerSelect[i].setVisible(false);
            this.empireRaceSelect[i].setVisible(false);
            this.empireShipStyleSelect.get(i).setVisible(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (ShipSprite shipSprite : this.ships) {
            this.fleet.detachChild(shipSprite);
            this.B.shipSpritePool.push(shipSprite);
        }
        this.ships.clear();
        for (ShipSprite shipSprite2 : this.empireShipStyleSelect) {
            detachChild(shipSprite2);
            this.B.shipSpritePool.push(shipSprite2);
        }
        this.empireShipStyleSelect.clear();
        this.background.detachChild(this.homeworldImage);
        this.B.planetSpritePool.push(this.homeworldImage);
        extendedScene.getPoolElements();
        X(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void menuButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        closeScene(1);
    }

    private void moveEmpireContentIn() {
        this.raceAttributes.registerEntityModifier(new MoveModifier(0.15f, -600.0f, 265.0f, 425.0f, 425.0f));
        this.customizeButton.registerEntityModifier(new MoveModifier(0.15f, -600.0f, 265.0f, 360.0f, 360.0f));
        moveEmpireContentInAll();
    }

    private void moveEmpireContentInAll() {
        this.fleet.registerEntityModifier(new MoveModifier(0.15f, getWidth() + 100.0f, getWidth() - 670.0f, 190.0f, 190.0f));
        this.homeworldImage.registerEntityModifier(new MoveModifier(0.15f, getWidth() + 320.0f, getWidth() - 180.0f, 220.0f, 220.0f));
        this.ambassador.registerEntityModifier(new MoveModifier(0.15f, -250.0f, 0.0f, 350.0f, 350.0f));
        this.randomEmperor.registerEntityModifier(new MoveModifier(0.15f, -250.0f, 0.0f, 350.0f, 350.0f));
        this.randomEmpirePerks.registerEntityModifier(new MoveModifier(0.15f, -600.0f, 255.0f, 375.0f, 375.0f));
        this.infantryIcon.registerEntityModifier(new MoveModifier(0.15f, getWidth() + 200.0f, getWidth() - 440.0f, 525.0f, 525.0f));
        this.raceInfo.registerEntityModifier(new MoveModifier(0.15f, -720.0f, 5.0f, 223.0f, 223.0f));
        this.empireBar.registerEntityModifier(new MoveModifier(0.15f, -680.0f, 0.0f, 170.0f, 170.0f));
    }

    private void moveEmpireContentInCustom() {
        int i;
        this.raceAttributes.registerEntityModifier(new MoveModifier(0.15f, getWidth() + 400.0f, getWidth() - 400.0f, 425.0f, 425.0f));
        this.customizeButton.registerEntityModifier(new MoveModifier(0.2f, getWidth() + 400.0f, getWidth() - 400.0f, 360.0f, 360.0f));
        for (int i2 = 0; i2 < 7; i2++) {
            float f2 = (i2 * 110) + 10;
            this.empireColorSelect[i2].registerEntityModifier(new MoveModifier(0.15f, -200.0f, f2, 230.0f, 230.0f));
            this.empireBannerSelect[i2].registerEntityModifier(new MoveModifier(0.15f, -200.0f, f2, 305.0f, 305.0f));
            this.empireRaceSelect[i2].registerEntityModifier(new MoveModifier(0.15f, -200.0f, f2, 405.0f, 405.0f));
            this.empireShipStyleSelect.get(i2).registerEntityModifier(new MoveModifier(0.15f, -200.0f, i + 20, 545.0f, 545.0f));
        }
        int selectedIndex = getSelectedIndex(this.customEmpireID, this.availableColors);
        int selectedIndex2 = getSelectedIndex(this.customBannerID, this.availableBanners);
        int selectedIndex3 = getSelectedIndex(this.customRaceID, this.availableRaces);
        int selectedIndex4 = getSelectedIndex(this.customShipStyleID, this.availableShipStyles);
        this.colorSelectedSprite.registerEntityModifier(new MoveModifier(0.15f, -200.0f, ((selectedIndex * 110) + 10) - 5, 225.0f, 225.0f));
        this.bannerSelectedSprite.registerEntityModifier(new MoveModifier(0.15f, -200.0f, ((selectedIndex2 * 110) + 10) - 5, 315.0f, 315.0f));
        this.raceSelectedSprite.registerEntityModifier(new MoveModifier(0.15f, -200.0f, ((selectedIndex3 * 110) + 10) - 5, 400.0f, 400.0f));
        this.shipStyleSelectedSprite.registerEntityModifier(new MoveModifier(0.15f, -200.0f, ((selectedIndex4 * 110) + 20) - 15, 535.0f, 535.0f));
        moveEmpireContentInAll();
    }

    private void selectPerkChoice(int i) {
        clearPerkRadioButtons();
        if (i == 0) {
            this.defaultPerksRadioButton.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
            Game.options.setOption(OptionID.RANDOM_EMPIRE_PERK, 0.0f);
        } else if (i == 1) {
            this.randomPerksRadioButton.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
            Game.options.setOption(OptionID.RANDOM_EMPIRE_PERK, 1.0f);
        } else if (i == 2) {
            this.noPerksRadioButton.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
            Game.options.setOption(OptionID.RANDOM_EMPIRE_PERK, 2.0f);
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setAvailableItems() {
        this.availableColors = new ArrayList();
        this.availableBanners = new ArrayList();
        this.availableRaces = new ArrayList();
        this.availableShipStyles = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (this.empires[i].getEmpireType() != EmpireType.HUMAN) {
                this.availableColors.add(Integer.valueOf(i));
                this.availableBanners.add(Integer.valueOf(this.empires[i].getBannerID()));
                this.availableRaces.add(Integer.valueOf(this.empires[i].getRaceID()));
                this.availableShipStyles.add(Integer.valueOf(this.empires[i].getShipStyleID()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustomEmpire() {
        setAvailableItems();
        this.customEmpireID = this.availableColors.get(0).intValue();
        this.customRaceID = this.availableRaces.get(0).intValue();
        this.customBannerID = this.availableBanners.get(0).intValue();
        this.customShipStyleID = this.availableShipStyles.get(0).intValue();
        int option = (int) Game.options.getOption(OptionID.CUSTOM_EMPIRE_COLOR, 0.0f);
        int option2 = (int) Game.options.getOption(OptionID.CUSTOM_EMPIRE_RACE, 0.0f);
        int option3 = (int) Game.options.getOption(OptionID.CUSTOM_EMPIRE_BANNER, 0.0f);
        int option4 = (int) Game.options.getOption(OptionID.CUSTOM_EMPIRE_SHIP_STYLE, 0.0f);
        if (this.availableColors.contains(Integer.valueOf(option))) {
            this.customEmpireID = option;
        }
        if (this.availableRaces.contains(Integer.valueOf(option2))) {
            this.customRaceID = option2;
        }
        if (this.availableBanners.contains(Integer.valueOf(option3))) {
            this.customBannerID = option3;
        }
        if (this.availableShipStyles.contains(Integer.valueOf(option4))) {
            this.customShipStyleID = option4;
        }
        setCustomEmpireItems();
    }

    private void setCustomEmpireItems() {
        this.B.getActivity().setLocale();
        setStartSummaryText();
        setSelectEmpireText();
        this.raceAttributes.setVisible(true);
        this.customizeButton.setVisible(true);
        this.customizeText.setVisible(true);
        this.ambassador.setVisible(false);
        this.randomEmperor.setVisible(false);
        this.randomEmpirePerks.setVisible(false);
        this.raceInfo.setVisible(false);
        this.customButton.setVisible(false);
        this.homeworldImage.setVisible(false);
        for (ShipSprite shipSprite : this.ships) {
            shipSprite.setVisible(false);
        }
        this.infantryIcon.setVisible(false);
        for (int i = 0; i < 7; i++) {
            Game game = this.B;
            game.graphics.setShipIconsTextures(i, this.customEmpireID, i, game.getActivity());
        }
        for (int i2 = 0; i2 < 7; i2++) {
            if (i2 < this.availableColors.size()) {
                this.empireColorSelect[i2].setVisible(true);
                this.empireColorSelect[i2].setCurrentTileIndex(this.availableColors.get(i2).intValue());
                this.empireBannerSelect[i2].setVisible(true);
                this.empireBannerSelect[i2].setCurrentTileIndex(GameIconEnum.getEmpireBannerNonEmpireLookup(this.availableBanners.get(i2).intValue()));
                this.empireRaceSelect[i2].setVisible(true);
                this.empireRaceSelect[i2].setTiledTextureRegion(this.B.graphics.ambassadorIcons[this.availableRaces.get(i2).intValue()]);
                this.shipStyleSelectedSprite.setVisible(false);
                if (!this.B.graphics.isAnyShipsModded()) {
                    this.shipStyleSelectedSprite.setVisible(true);
                    this.empireShipStyleSelect.get(i2).setVisible(true);
                    this.empireShipStyleSelect.get(i2).setShipIcon(this.availableShipStyles.get(i2).intValue(), this.availableShipStyles.get(i2).intValue(), ShipType.CRUISER, 0, 100.0f, 100.0f, 0, true);
                }
            } else {
                this.empireColorSelect[i2].setVisible(false);
                this.empireBannerSelect[i2].setVisible(false);
                this.empireRaceSelect[i2].setVisible(false);
                this.empireShipStyleSelect.get(i2).setVisible(false);
            }
        }
        List<RaceAttribute> asList = Arrays.asList(Empires.getDefaultRaceAttributes(this.customRaceID));
        this.attributes = asList;
        this.raceAttributes.set(asList);
        setCustomEmpireNameAndBanner();
        moveEmpireContentInCustom();
    }

    private void setCustomEmpireNameAndBanner() {
        this.empireBannerBackground.setCurrentTileIndex(this.customEmpireID);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBannerNonEmpireLookup(this.customBannerID));
        this.empireBanner.setY(-10.0f);
        this.empireName.setText(this.B.empires.getDefaultName(this.customRaceID));
        Text text = this.empireName;
        text.setY(26.0f - (text.getHeightScaled() / 2.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEmpire() {
        this.B.getActivity().setLocale();
        this.raceAttributes.setVisible(true);
        this.customizeButton.setVisible(true);
        this.customizeText.setVisible(true);
        this.ambassador.setVisible(true);
        this.randomEmperor.setVisible(false);
        this.randomEmpirePerks.setVisible(false);
        this.raceInfo.setVisible(true);
        this.customButton.setVisible(true);
        this.homeworldImage.setVisible(true);
        for (ShipSprite shipSprite : this.ships) {
            shipSprite.setVisible(true);
        }
        this.infantryIcon.setVisible(true);
        hideCustomEmpireControls();
        int raceID = this.empires[this.selectedEmpireID].getRaceID();
        this.empireBannerBackground.setCurrentTileIndex(this.selectedEmpireID);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBannerNonEmpireLookup(this.empires[this.selectedEmpireID].getBannerID()));
        this.empireBanner.setY(-10.0f);
        this.empireName.setText(this.B.empires.getDefaultName(raceID));
        Text text = this.empireName;
        text.setY(26.0f - (text.getHeightScaled() / 2.0f));
        this.raceInfo.setText(this.B.empires.getEmpireDescription(raceID));
        Game game = this.B;
        this.ambassador.setTiledTextureRegion(game.graphics.setAmbassadorTexture(raceID, game.getActivity()));
        boolean homeworldHasRings = Empires.homeworldHasRings(raceID);
        boolean homeworldHasMoon = Empires.homeworldHasMoon(raceID);
        int homeworldMoonImage = Empires.getHomeworldMoonImage(raceID);
        float homeworldMoonSize = Empires.getHomeworldMoonSize(raceID);
        Climate climate = Climate.values()[Empires.getHomeworldClimate(raceID)];
        Planet buildHomeworld = new Planet.Builder().hasRing(homeworldHasRings).hasMoon(homeworldHasMoon).moon(new Moon(homeworldMoonImage, homeworldMoonSize)).climate(climate).terraformedClimate(climate).buildHomeworld(EmpireType.HUMAN);
        this.homeworldImage.setSpritesInvisible();
        this.homeworldImage.setPlanet(buildHomeworld, buildHomeworld.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene));
        this.homeworldImage.setCityLights(Functions.random.nextInt(80) + 50, buildHomeworld.getCityLightIndex(), buildHomeworld.getScale(this.B.planetScene), buildHomeworld.getScale(this.B.planetScene), false);
        U();
        this.infantryIcon.setTiledTextureRegion(this.B.graphics.troops[raceID]);
        List<RaceAttribute> asList = Arrays.asList(Empires.getDefaultRaceAttributes(raceID));
        this.attributes = asList;
        this.raceAttributes.set(asList);
        moveEmpireContentIn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayer(int i) {
        int i2 = 0;
        while (true) {
            if (i2 >= 7) {
                i2 = 0;
                break;
            } else if (this.empires[i2].getEmpireType() == EmpireType.NONE) {
                break;
            } else {
                i2++;
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 7; i4++) {
            if (this.empires[i4].getEmpireType() == EmpireType.HUMAN) {
                i3++;
            }
        }
        boolean z = i3 + this.randomPlayersChoice.size() != this.numberOfEmpires - 1;
        this.addPlayerButton.setVisible(z);
        this.addPlayerText.setVisible(z);
        this.addPlayerSummary.setVisible(z);
        setStartSummaryText();
        setSelectEmpireText();
        if (i != -1) {
            this.selectedEmpireID = i;
        } else {
            this.selectedEmpireID = i2;
        }
        this.empireSelectControl.set(this.selectedEmpireID, this.empires);
        setEmpire();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRandomEmpire() {
        this.B.getActivity().setLocale();
        setStartSummaryText();
        setSelectEmpireText();
        this.raceAttributes.setVisible(false);
        this.customizeButton.setVisible(false);
        this.customizeText.setVisible(false);
        this.ambassador.setVisible(false);
        this.randomEmperor.setVisible(true);
        this.randomEmpirePerks.setVisible(true);
        this.raceInfo.setVisible(true);
        this.customButton.setVisible(false);
        this.homeworldImage.setVisible(true);
        for (ShipSprite shipSprite : this.ships) {
            shipSprite.setVisible(true);
        }
        this.infantryIcon.setVisible(true);
        hideCustomEmpireControls();
        this.empireBannerBackground.setCurrentTileIndex(8);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.SMALL_GALAXY.ordinal());
        this.empireBanner.setY(-15.0f);
        this.empireName.setText(this.B.getActivity().getString(R.string.select_empire_random_empire));
        Text text = this.empireName;
        text.setY(26.0f - (text.getHeightScaled() / 2.0f));
        this.raceInfo.setText(this.B.getActivity().getString(R.string.select_empire_random_empire_info));
        boolean homeworldHasRings = Empires.homeworldHasRings(Functions.random.nextInt(6));
        Planet buildHomeworld = new Planet.Builder().hasRing(homeworldHasRings).hasMoon(Empires.homeworldHasMoon(Functions.random.nextInt(6))).moon(new Moon(Empires.getHomeworldMoonImage(Functions.random.nextInt(6)), Empires.getHomeworldMoonSize(Functions.random.nextInt(6)))).climate(Climate.values()[Functions.random.nextInt(24)]).terraformedClimate(Climate.values()[Functions.random.nextInt(24)]).buildHomeworld(EmpireType.HUMAN);
        this.homeworldImage.setSpritesInvisible();
        this.homeworldImage.setPlanet(buildHomeworld, buildHomeworld.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene));
        this.homeworldImage.setCityLights(Functions.random.nextInt(80) + 50, buildHomeworld.getCityLightIndex(), buildHomeworld.getScale(this.B.planetScene), buildHomeworld.getScale(this.B.planetScene), false);
        ArrayList arrayList = new ArrayList();
        if (Game.options.getOption(OptionID.AI_APPEARANCE, 0.0f) == 1.0f) {
            for (int i = 0; i < 7; i++) {
                int random = Functions.getRandom(0, 7);
                this.B.graphics.setShipIconsTextures(i, Functions.getRandom(0, 7), random, this.B.getActivity());
                arrayList.add(Integer.valueOf(random));
            }
        } else {
            for (int i2 = 0; i2 < 7; i2++) {
                Game game = this.B;
                game.graphics.setShipIconsTextures(i2, i2, i2, game.getActivity());
                arrayList.add(Integer.valueOf(i2));
            }
        }
        int nextInt = Functions.random.nextInt(7);
        ShipSprite shipSprite2 = this.ships.get(0);
        int intValue = ((Integer) arrayList.get(nextInt)).intValue();
        ShipType shipType = ShipType.DREADNOUGHT;
        shipSprite2.setShipIcon(nextInt, intValue, shipType, 0, 100.0f, shipType.getSelectScreenSize(), 0, true);
        int nextInt2 = Functions.random.nextInt(7);
        ShipSprite shipSprite3 = this.ships.get(1);
        int intValue2 = ((Integer) arrayList.get(nextInt2)).intValue();
        ShipType shipType2 = ShipType.BATTLESHIP;
        shipSprite3.setShipIcon(nextInt2, intValue2, shipType2, 0, 100.0f, shipType2.getSelectScreenSize(), 0, true);
        int nextInt3 = Functions.random.nextInt(7);
        ShipSprite shipSprite4 = this.ships.get(2);
        int intValue3 = ((Integer) arrayList.get(nextInt3)).intValue();
        ShipType shipType3 = ShipType.CRUISER;
        shipSprite4.setShipIcon(nextInt3, intValue3, shipType3, 0, 100.0f, shipType3.getSelectScreenSize(), 0, true);
        int nextInt4 = Functions.random.nextInt(7);
        ShipSprite shipSprite5 = this.ships.get(3);
        int intValue4 = ((Integer) arrayList.get(nextInt4)).intValue();
        ShipType shipType4 = ShipType.DESTROYER;
        shipSprite5.setShipIcon(nextInt4, intValue4, shipType4, 0, 100.0f, shipType4.getSelectScreenSize(), 0, true);
        int nextInt5 = Functions.random.nextInt(7);
        ShipSprite shipSprite6 = this.ships.get(4);
        int intValue5 = ((Integer) arrayList.get(nextInt5)).intValue();
        ShipType shipType5 = ShipType.SCOUT;
        shipSprite6.setShipIcon(nextInt5, intValue5, shipType5, 0, 100.0f, shipType5.getSelectScreenSize(), 0, true);
        int nextInt6 = Functions.random.nextInt(7);
        ShipSprite shipSprite7 = this.ships.get(5);
        int intValue6 = ((Integer) arrayList.get(nextInt6)).intValue();
        ShipType shipType6 = ShipType.COLONY;
        shipSprite7.setShipIcon(nextInt6, intValue6, shipType6, 0, 100.0f, shipType6.getSelectScreenSize(), 0, true);
        int nextInt7 = Functions.random.nextInt(7);
        ShipSprite shipSprite8 = this.ships.get(6);
        int intValue7 = ((Integer) arrayList.get(nextInt7)).intValue();
        ShipType shipType7 = ShipType.CONSTRUCTION;
        shipSprite8.setShipIcon(nextInt7, intValue7, shipType7, 0, 100.0f, shipType7.getSelectScreenSize(), 0, true);
        int nextInt8 = Functions.random.nextInt(7);
        ShipSprite shipSprite9 = this.ships.get(7);
        int intValue8 = ((Integer) arrayList.get(nextInt8)).intValue();
        ShipType shipType8 = ShipType.TRANSPORT;
        shipSprite9.setShipIcon(nextInt8, intValue8, shipType8, 0, 100.0f, shipType8.getSelectScreenSize(), 0, true);
        int nextInt9 = Functions.random.nextInt(7);
        this.ships.get(8).setShipIcon(nextInt9, ((Integer) arrayList.get(nextInt9)).intValue(), shipType3, 4, 100.0f, shipType3.getSelectScreenSize(), 0, true);
        this.infantryIcon.setTiledTextureRegion(this.B.graphics.troops[Functions.random.nextInt(7)]);
        moveEmpireContentIn();
    }

    private void setSelectEmpireText() {
        int i = 1;
        for (CreateEmpireInfo createEmpireInfo : this.empires) {
            if (createEmpireInfo.getEmpireType() == EmpireType.HUMAN) {
                i++;
            }
        }
        int size = i + this.randomPlayersChoice.size();
        String string = this.B.getActivity().getString(R.string.select_select_an_empire);
        if (size > 1) {
            string = string + this.B.getActivity().getString(R.string.select_select_an_empire_for_player, new Object[]{Integer.valueOf(size)});
        }
        this.selectEmpireText.setText(string);
        this.selectEmpireText.setX((getWidth() / 2.0f) - (this.selectEmpireText.getWidthScaled() / 2.0f));
    }

    private void setStartSummaryText() {
        int i = 1;
        for (CreateEmpireInfo createEmpireInfo : this.empires) {
            if (createEmpireInfo.getEmpireType() == EmpireType.HUMAN) {
                i++;
            }
        }
        int size = i + this.randomPlayersChoice.size();
        String num = Integer.toString(size);
        String str = size == 1 ? num + " " + this.B.getActivity().getString(R.string.select_start_player) : num + " " + this.B.getActivity().getString(R.string.select_start_players);
        int i2 = this.numberOfEmpires - size;
        if (i2 == 1) {
            str = str + " " + this.B.getActivity().getString(R.string.select_start_and) + " " + i2 + " " + this.B.getActivity().getString(R.string.select_start_ai);
        } else if (i2 > 1) {
            str = str + " " + this.B.getActivity().getString(R.string.select_start_and) + " " + i2 + " " + this.B.getActivity().getString(R.string.select_start_ais);
        }
        this.startGameSummary.setText(str);
        Text text = this.startGameSummary;
        text.setX(225.0f - (text.getWidthScaled() / 2.0f));
    }

    private void startButtonPressed() {
        CreateEmpireInfo[] createEmpireInfoArr;
        CreateEmpireInfo[] createEmpireInfoArr2;
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        assignPlayer();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (this.empires[i].getEmpireType() != EmpireType.HUMAN) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Integer num : this.randomPlayersChoice) {
            int nextInt = Functions.random.nextInt(arrayList.size());
            int intValue = ((Integer) arrayList.get(nextInt)).intValue();
            arrayList.remove(nextInt);
            if (Game.options.getOption(OptionID.AI_APPEARANCE, 0.0f) == 1.0f) {
                arrayList2.add(Integer.valueOf(nextInt));
            }
            this.empires[intValue].setEmpireType(EmpireType.HUMAN);
            this.empires[intValue].setBannerID(intValue);
            this.empires[intValue].setShipStyleID(intValue);
            this.empires[intValue].setRaceID(intValue);
            int intValue2 = num.intValue();
            if (intValue2 == 0) {
                this.empires[intValue].setRaceAttributes(Arrays.asList(Empires.getDefaultRaceAttributes(intValue)));
            } else if (intValue2 == 1) {
                Stack stack = new Stack();
                stack.addAll(Arrays.asList(RaceAttribute.values()));
                Collections.shuffle(stack);
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add((RaceAttribute) stack.pop());
                arrayList3.add((RaceAttribute) stack.pop());
                this.empires[intValue].setRaceAttributes(arrayList3);
            } else if (intValue2 == 2) {
                this.empires[intValue].setRaceAttributes(new ArrayList());
            }
        }
        if (Game.options.getOption(OptionID.AI_APPEARANCE, 0.0f) == 1.0f) {
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            int i2 = 0;
            for (CreateEmpireInfo createEmpireInfo : this.empires) {
                if (createEmpireInfo.getEmpireType() != EmpireType.HUMAN || arrayList2.contains(Integer.valueOf(i2))) {
                    arrayList4.add(Integer.valueOf(createEmpireInfo.getRaceID()));
                    arrayList5.add(Integer.valueOf(createEmpireInfo.getBannerID()));
                    arrayList6.add(Integer.valueOf(createEmpireInfo.getShipStyleID()));
                }
                i2++;
            }
            int i3 = 0;
            for (CreateEmpireInfo createEmpireInfo2 : this.empires) {
                if (createEmpireInfo2.getEmpireType() != EmpireType.HUMAN || arrayList2.contains(Integer.valueOf(i3))) {
                    Integer num2 = (Integer) arrayList4.get(Functions.random.nextInt(arrayList4.size()));
                    createEmpireInfo2.setRaceID(num2.intValue());
                    arrayList4.remove(num2);
                    Integer num3 = (Integer) arrayList5.get(Functions.random.nextInt(arrayList5.size()));
                    createEmpireInfo2.setBannerID(num3.intValue());
                    arrayList5.remove(num3);
                    Integer num4 = (Integer) arrayList6.get(Functions.random.nextInt(arrayList6.size()));
                    createEmpireInfo2.setShipStyleID(num4.intValue());
                    arrayList6.remove(num4);
                }
                i3++;
            }
        }
        closeScene(2);
    }

    private void swipeAllContentsOff() {
        this.homeworldImage.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 180.0f, getWidth() + 320.0f, 220.0f, 220.0f));
        this.ambassador.registerEntityModifier(new MoveModifier(0.15f, 0.0f, -250.0f, 350.0f, 350.0f));
        this.randomEmperor.registerEntityModifier(new MoveModifier(0.15f, 0.0f, -250.0f, 350.0f, 350.0f));
        this.randomEmpirePerks.registerEntityModifier(new MoveModifier(0.15f, 255.0f, -600.0f, 375.0f, 375.0f));
        this.infantryIcon.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 440.0f, getWidth() + 200.0f, 525.0f, 525.0f));
        this.raceInfo.registerEntityModifier(new MoveModifier(0.15f, 5.0f, -720.0f, 223.0f, 223.0f));
        this.empireBar.registerEntityModifier(new MoveModifier(0.15f, 0.0f, -680.0f, 170.0f, 170.0f));
    }

    private void swipeContentsOff() {
        if (this.customizeButton.getX() == 265.0f) {
            swipeEmpireContentsOff();
        } else {
            swipeCustomContentsOff();
        }
    }

    private void swipeCustomContentsOff() {
        int i;
        this.customizeButton.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 400.0f, getWidth() + 400.0f, 355.0f, 355.0f));
        this.raceAttributes.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 400.0f, getWidth() + 400.0f, 425.0f, 425.0f));
        for (int i2 = 0; i2 < 7; i2++) {
            float f2 = (i2 * 110) + 10;
            this.empireColorSelect[i2].registerEntityModifier(new MoveModifier(0.15f, f2, -200.0f, 230.0f, 230.0f));
            this.empireBannerSelect[i2].registerEntityModifier(new MoveModifier(0.15f, f2, -200.0f, 305.0f, 305.0f));
            this.empireRaceSelect[i2].registerEntityModifier(new MoveModifier(0.15f, f2, -200.0f, 405.0f, 405.0f));
            this.empireShipStyleSelect.get(i2).registerEntityModifier(new MoveModifier(0.15f, i + 20, -200.0f, 545.0f, 545.0f));
        }
        TiledSprite tiledSprite = this.colorSelectedSprite;
        tiledSprite.registerEntityModifier(new MoveModifier(0.15f, tiledSprite.getX(), -200.0f, 225.0f, 225.0f));
        TiledSprite tiledSprite2 = this.bannerSelectedSprite;
        tiledSprite2.registerEntityModifier(new MoveModifier(0.15f, tiledSprite2.getX(), -200.0f, 315.0f, 315.0f));
        TiledSprite tiledSprite3 = this.raceSelectedSprite;
        tiledSprite3.registerEntityModifier(new MoveModifier(0.15f, tiledSprite3.getX(), -200.0f, 405.0f, 405.0f));
        TiledSprite tiledSprite4 = this.shipStyleSelectedSprite;
        tiledSprite4.registerEntityModifier(new MoveModifier(0.15f, tiledSprite4.getX(), -200.0f, 545.0f, 545.0f));
        swipeAllContentsOff();
    }

    private void swipeEmpireContentsOff() {
        this.customizeButton.registerEntityModifier(new MoveModifier(0.15f, 265.0f, -600.0f, 355.0f, 355.0f));
        this.raceAttributes.registerEntityModifier(new MoveModifier(0.15f, 265.0f, -600.0f, 425.0f, 425.0f));
        swipeAllContentsOff();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void U() {
        int i = this.selectedEmpireID;
        if (i == -3) {
            return;
        }
        int shipStyleID = this.empires[i].getShipStyleID();
        Game game = this.B;
        game.graphics.setShipIconsTextures(this.selectedEmpireID, shipStyleID, game.getActivity());
        int i2 = this.selectedEmpireID;
        ShipType shipType = ShipType.DREADNOUGHT;
        this.ships.get(0).setShipIcon(i2, shipStyleID, shipType, 0, 100.0f, shipType.getSelectScreenSize(), 0, true);
        int i3 = this.selectedEmpireID;
        ShipType shipType2 = ShipType.BATTLESHIP;
        this.ships.get(1).setShipIcon(i3, shipStyleID, shipType2, 0, 100.0f, shipType2.getSelectScreenSize(), 0, true);
        int i4 = this.selectedEmpireID;
        ShipType shipType3 = ShipType.CRUISER;
        this.ships.get(2).setShipIcon(i4, shipStyleID, shipType3, 0, 100.0f, shipType3.getSelectScreenSize(), 0, true);
        int i5 = this.selectedEmpireID;
        ShipType shipType4 = ShipType.DESTROYER;
        this.ships.get(3).setShipIcon(i5, shipStyleID, shipType4, 0, 100.0f, shipType4.getSelectScreenSize(), 0, true);
        int i6 = this.selectedEmpireID;
        ShipType shipType5 = ShipType.SCOUT;
        this.ships.get(4).setShipIcon(i6, shipStyleID, shipType5, 0, 100.0f, shipType5.getSelectScreenSize(), 0, true);
        int i7 = this.selectedEmpireID;
        ShipType shipType6 = ShipType.COLONY;
        this.ships.get(5).setShipIcon(i7, shipStyleID, shipType6, 0, 100.0f, shipType6.getSelectScreenSize(), 0, true);
        int i8 = this.selectedEmpireID;
        ShipType shipType7 = ShipType.CONSTRUCTION;
        this.ships.get(6).setShipIcon(i8, shipStyleID, shipType7, 0, 100.0f, shipType7.getSelectScreenSize(), 0, true);
        int i9 = this.selectedEmpireID;
        ShipType shipType8 = ShipType.TRANSPORT;
        this.ships.get(7).setShipIcon(i9, shipStyleID, shipType8, 0, 100.0f, shipType8.getSelectScreenSize(), 0, true);
        this.ships.get(8).setShipIcon(this.selectedEmpireID, shipStyleID, shipType3, 4, 100.0f, shipType3.getSelectScreenSize(), 0, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void V(List<RaceAttribute> list) {
        this.B.getActivity().setLocale();
        this.attributes = list;
        this.raceAttributes.set(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void W() {
        Stack stack = new Stack();
        stack.addAll(Arrays.asList(RaceAttribute.values()));
        Collections.shuffle(stack);
        ArrayList arrayList = new ArrayList();
        arrayList.add((RaceAttribute) stack.pop());
        arrayList.add((RaceAttribute) stack.pop());
        V(arrayList);
    }

    protected void X(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof CustomizeAttributesScene) {
            int i = this.selectedEmpireID;
            if (i == -3) {
                i = this.customRaceID;
            }
            this.B.customizeAttributesScene.set(i, this.attributes);
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof TurnScene) {
            this.B.turnScene.set();
        }
        if (extendedScene instanceof MenuScene) {
            this.B.menuScene.openMenu();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        if (hasChildScene()) {
            this.H.back();
        } else {
            closeScene(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        this.empireSelectControl.checkInputOnControl(i, point);
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            checkActionUp(point);
        } else if (i != 2) {
        } else {
            checkActionMove(point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        Sprite E = E(0.0f, 170.0f, this.B.graphics.whiteTexture, vertexBufferObjectManager, true);
        this.background = E;
        E.setSize(getWidth(), 460.0f);
        this.background.setAlpha(0.6f);
        Entity entity2 = new Entity(0.0f, 170.0f);
        this.empireBar = entity2;
        attachChild(entity2);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.empireBannerBackground = tiledSprite;
        tiledSprite.setSize(600.0f, 50.0f);
        this.empireBar.attachChild(this.empireBannerBackground);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, -10.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.empireBanner = tiledSprite2;
        tiledSprite2.setSize(75.0f, 75.0f);
        this.empireBar.attachChild(this.empireBanner);
        Text text = new Text(100.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.empireName = text;
        this.empireBar.attachChild(text);
        IFont iFont = this.B.fonts.smallInfoFont;
        CharSequence wrap = CharBuffer.wrap(new char[512]);
        AutoWrap autoWrap = AutoWrap.WORDS;
        this.raceInfo = F(5.0f, 223.0f, iFont, wrap, new TextOptions(autoWrap, 640.0f), vertexBufferObjectManager);
        Sprite E2 = E(0.0f, 350.0f, this.B.graphics.raceAmbassadorTexture, vertexBufferObjectManager, true);
        this.ambassador = E2;
        E2.setSize(240.0f, 275.0f);
        TiledSprite H = H(0.0f, 350.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SPY.ordinal(), false);
        this.randomEmperor = H;
        H.setSize(240.0f, 275.0f);
        RaceAttributesElement raceAttributesElement = new RaceAttributesElement(265.0f, 425.0f, this.B, vertexBufferObjectManager);
        this.raceAttributes = raceAttributesElement;
        attachChild(raceAttributesElement);
        Entity entity3 = new Entity(getWidth() - 670.0f, 190.0f);
        this.fleet = entity3;
        attachChild(entity3);
        Sprite E3 = E(getWidth() - 440.0f, 525.0f, this.B.graphics.troops[0], vertexBufferObjectManager, true);
        this.infantryIcon = E3;
        E3.setZIndex(2);
        EmpireSelectControl empireSelectControl = new EmpireSelectControl(this.B, vertexBufferObjectManager);
        this.empireSelectControl = empireSelectControl;
        empireSelectControl.setPosition((getWidth() / 2.0f) - (this.empireSelectControl.getControlWidth() / 2.0f), 0.0f);
        attachChild(this.empireSelectControl);
        TiledSprite H2 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.MENU.ordinal(), true);
        this.menuButton = H2;
        s(H2);
        TiledSprite H3 = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PREVIOUS.ordinal(), true);
        this.backButton = H3;
        s(H3);
        this.customButton = H(getWidth() - 120.0f, 544.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.BLANK.ordinal(), true);
        TiledSprite tiledSprite3 = new TiledSprite(34.0f, 18.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.CUSTOM.ordinal());
        tiledSprite3.setSize(50.0f, 50.0f);
        this.customButton.attachChild(tiledSprite3);
        s(this.customButton);
        TiledSprite H4 = H(0.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.addPlayerButton = H4;
        H4.setSize(450.0f, 75.0f);
        this.addPlayerButton.setAlpha(0.7f);
        Sprite sprite = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.addPlayerButtonPressed = sprite;
        sprite.setSize(this.addPlayerButton.getWidthScaled() - 4.0f, this.addPlayerButton.getHeightScaled() - 4.0f);
        this.addPlayerButtonPressed.setVisible(false);
        this.addPlayerButton.attachChild(this.addPlayerButtonPressed);
        Game game = this.B;
        Text text2 = new Text(0.0f, 12.0f, game.fonts.infoFont, game.getActivity().getString(R.string.select_add_player), this.E, vertexBufferObjectManager);
        this.addPlayerText = text2;
        text2.setX(225.0f - (text2.getWidthScaled() / 2.0f));
        Text text3 = this.addPlayerText;
        Color color = Color.BLACK;
        text3.setColor(color);
        this.addPlayerButton.attachChild(this.addPlayerText);
        Game game2 = this.B;
        Text text4 = new Text(0.0f, 48.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.select_add_player_description), this.E, vertexBufferObjectManager);
        this.addPlayerSummary = text4;
        text4.setX(225.0f - (text4.getWidthScaled() / 2.0f));
        this.addPlayerSummary.setColor(color);
        this.addPlayerButton.attachChild(this.addPlayerSummary);
        TiledSprite H5 = H(getWidth() - 450.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.startButton = H5;
        H5.setSize(450.0f, 75.0f);
        this.startButton.setAlpha(0.7f);
        this.startButton.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.startButton.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(1.0f, 0.6f, 0.7f), new AlphaModifier(1.0f, 0.7f, 0.6f))));
        Sprite sprite2 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.startButtonPressed = sprite2;
        sprite2.setSize(this.startButton.getWidthScaled() - 4.0f, this.startButton.getHeightScaled() - 4.0f);
        this.startButtonPressed.setVisible(false);
        this.startButton.attachChild(this.startButtonPressed);
        Game game3 = this.B;
        Text text5 = new Text(0.0f, 12.0f, game3.fonts.infoFont, game3.getActivity().getString(R.string.select_start_game), this.E, vertexBufferObjectManager);
        this.startText = text5;
        text5.setX(225.0f - (text5.getWidthScaled() / 2.0f));
        this.startText.setColor(color);
        this.startButton.attachChild(this.startText);
        Text text6 = new Text(0.0f, 48.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        this.startGameSummary = text6;
        text6.setColor(color);
        this.startButton.attachChild(this.startGameSummary);
        TiledSprite H6 = H(265.0f, 360.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.customizeButton = H6;
        H6.setSize(310.0f, 60.0f);
        this.customizeButton.setAlpha(0.7f);
        Sprite sprite3 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.customizeButtonPressed = sprite3;
        sprite3.setSize(306.0f, 56.0f);
        this.customizeButtonPressed.setVisible(false);
        this.customizeButton.attachChild(this.customizeButtonPressed);
        Game game4 = this.B;
        Text text7 = new Text(0.0f, 0.0f, game4.fonts.smallFont, game4.getActivity().getString(R.string.select_customize_perks), vertexBufferObjectManager);
        this.customizeText = text7;
        text7.setColor(color);
        Text text8 = this.customizeText;
        text8.setX(155.0f - (text8.getWidthScaled() / 2.0f));
        Text text9 = this.customizeText;
        text9.setY(30.0f - (text9.getHeightScaled() / 2.0f));
        this.customizeButton.attachChild(this.customizeText);
        Entity entity4 = new Entity();
        this.randomEmpirePerks = entity4;
        entity4.setPosition(255.0f, 375.0f);
        attachChild(this.randomEmpirePerks);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite4.setCurrentTileIndex(8);
        tiledSprite4.setSize(325.0f, 50.0f);
        this.randomEmpirePerks.attachChild(tiledSprite4);
        Game game5 = this.B;
        Text text10 = new Text(10.0f, 0.0f, game5.fonts.smallFont, game5.getActivity().getString(R.string.select_empire_random_perks), this.E, vertexBufferObjectManager);
        text10.setY(25.0f - (text10.getHeightScaled() / 2.0f));
        this.randomEmpirePerks.attachChild(text10);
        TiledSprite tiledSprite5 = new TiledSprite(-20.0f, 65.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.defaultPerksRadioButton = tiledSprite5;
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_OFF;
        tiledSprite5.setCurrentTileIndex(buttonsEnum.ordinal());
        this.defaultPerksRadioButton.setSize(84.0f, 60.0f);
        this.randomEmpirePerks.attachChild(this.defaultPerksRadioButton);
        Game game6 = this.B;
        Font font = game6.fonts.smallInfoFont;
        String string = game6.getActivity().getString(R.string.select_empire_random_perks_select_default);
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        Text text11 = new Text(60.0f, 0.0f, font, string, new TextOptions(autoWrap, 310.0f, horizontalAlign), vertexBufferObjectManager);
        this.defaultPerksText = text11;
        text11.setY(95.0f - (text11.getHeightScaled() / 2.0f));
        this.randomEmpirePerks.attachChild(this.defaultPerksText);
        TiledSprite tiledSprite6 = new TiledSprite(-20.0f, 125.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.randomPerksRadioButton = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(buttonsEnum.ordinal());
        this.randomPerksRadioButton.setSize(84.0f, 60.0f);
        this.randomEmpirePerks.attachChild(this.randomPerksRadioButton);
        Game game7 = this.B;
        Text text12 = new Text(60.0f, 0.0f, game7.fonts.smallInfoFont, game7.getActivity().getString(R.string.select_empire_random_perks_select_random), new TextOptions(autoWrap, 310.0f, horizontalAlign), vertexBufferObjectManager);
        this.randomPerksText = text12;
        text12.setY(155.0f - (text12.getHeightScaled() / 2.0f));
        this.randomEmpirePerks.attachChild(this.randomPerksText);
        TiledSprite tiledSprite7 = new TiledSprite(-20.0f, 185.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.noPerksRadioButton = tiledSprite7;
        tiledSprite7.setCurrentTileIndex(buttonsEnum.ordinal());
        this.noPerksRadioButton.setSize(84.0f, 60.0f);
        this.randomEmpirePerks.attachChild(this.noPerksRadioButton);
        Game game8 = this.B;
        Text text13 = new Text(60.0f, 0.0f, game8.fonts.smallInfoFont, game8.getActivity().getString(R.string.select_empire_random_perks_select_none), new TextOptions(autoWrap, 310.0f, horizontalAlign), vertexBufferObjectManager);
        this.noPerksText = text13;
        text13.setY(215.0f - (text13.getHeightScaled() / 2.0f));
        this.randomEmpirePerks.attachChild(this.noPerksText);
        TiledSprite H7 = H(-200.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, false);
        this.customEmpirePress = H7;
        H7.setAlpha(0.7f);
        TiledSprite H8 = H(-200.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.colorSelectedSprite = H8;
        H8.setSize(110.0f, 85.0f);
        this.colorSelectedSprite.setAlpha(0.75f);
        u(this.colorSelectedSprite);
        TiledSprite H9 = H(-200.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.bannerSelectedSprite = H9;
        H9.setSize(110.0f, 80.0f);
        this.bannerSelectedSprite.setAlpha(0.75f);
        u(this.bannerSelectedSprite);
        TiledSprite H10 = H(-200.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.raceSelectedSprite = H10;
        H10.setSize(110.0f, 130.0f);
        this.raceSelectedSprite.setAlpha(0.75f);
        u(this.raceSelectedSprite);
        TiledSprite H11 = H(-200.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.shipStyleSelectedSprite = H11;
        H11.setSize(110.0f, 90.0f);
        this.shipStyleSelectedSprite.setAlpha(0.75f);
        u(this.shipStyleSelectedSprite);
        for (int i = 0; i < 7; i++) {
            float f2 = (i * 110) + 10;
            this.empireColorSelect[i] = J(f2, 230.0f, this.B.graphics.empireColors, vertexBufferObjectManager, false);
            this.empireColorSelect[i].setSize(100.0f, 75.0f);
            this.empireBannerSelect[i] = J(f2, 305.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, false);
            this.empireRaceSelect[i] = E(f2, 405.0f, this.B.graphics.ambassadorIcons[0], vertexBufferObjectManager, false);
            this.empireRaceSelect[i].setSize(100.0f, 120.0f);
        }
        this.selectEmpireText = F(0.0f, 650.0f, this.B.fonts.smallInfoFont, this.D, new TextOptions(AutoWrap.WORDS, 340.0f, HorizontalAlign.CENTER), vertexBufferObjectManager);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
    }

    public void customEmpireSelected() {
        Game.options.setOption(OptionID.LAST_SELECTED_EMPIRE, -3.0f);
        this.empireSelectControl.set(-3, this.empires);
        this.selectedEmpireID = -3;
        this.fleet.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 670.0f, getWidth() + 100.0f, 190.0f, 190.0f) { // from class: com.birdshel.Uciana.Scenes.PlayerCreationScene.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: m */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                PlayerCreationScene.this.setCustomEmpire();
            }
        });
        swipeContentsOff();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.homeworldImage = planetSprite;
        planetSprite.setMoonRange(500, 500);
        this.homeworldImage.setPosition(getWidth() - 180.0f, 220.0f);
        this.homeworldImage.setVisible(false);
        this.background.attachChild(this.homeworldImage);
        ShipSprite shipSprite = this.B.shipSpritePool.get();
        shipSprite.setPosition(60.0f, 210.0f);
        this.fleet.attachChild(shipSprite);
        this.ships.add(shipSprite);
        ShipSprite shipSprite2 = this.B.shipSpritePool.get();
        shipSprite2.setPosition(50.0f, 110.0f);
        this.fleet.attachChild(shipSprite2);
        this.ships.add(shipSprite2);
        ShipSprite shipSprite3 = this.B.shipSpritePool.get();
        shipSprite3.setPosition(40.0f, 0.0f);
        this.fleet.attachChild(shipSprite3);
        this.ships.add(shipSprite3);
        ShipSprite shipSprite4 = this.B.shipSpritePool.get();
        shipSprite4.setPosition(50.0f, 310.0f);
        this.fleet.attachChild(shipSprite4);
        this.ships.add(shipSprite4);
        ShipSprite shipSprite5 = this.B.shipSpritePool.get();
        shipSprite5.setPosition(130.0f, 350.0f);
        this.fleet.attachChild(shipSprite5);
        this.ships.add(shipSprite5);
        ShipSprite shipSprite6 = this.B.shipSpritePool.get();
        shipSprite6.setPosition(170.0f, 110.0f);
        this.fleet.attachChild(shipSprite6);
        this.ships.add(shipSprite6);
        ShipSprite shipSprite7 = this.B.shipSpritePool.get();
        shipSprite7.setPosition(160.0f, 190.0f);
        this.fleet.attachChild(shipSprite7);
        this.ships.add(shipSprite7);
        ShipSprite shipSprite8 = this.B.shipSpritePool.get();
        shipSprite8.setPosition(160.0f, 270.0f);
        this.fleet.attachChild(shipSprite8);
        this.ships.add(shipSprite8);
        ShipSprite shipSprite9 = this.B.shipSpritePool.get();
        shipSprite9.setPosition(150.0f, 10.0f);
        this.fleet.attachChild(shipSprite9);
        this.ships.add(shipSprite9);
        for (ShipSprite shipSprite10 : this.ships) {
            shipSprite10.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            ShipSprite shipSprite11 = this.B.shipSpritePool.get();
            shipSprite11.setPosition((i * 110) + 20, 545.0f);
            shipSprite11.setVisible(false);
            if (this.selectedEmpireID == -3 && !this.B.graphics.isAnyShipsModded() && i < this.availableShipStyles.size()) {
                shipSprite11.setVisible(true);
                shipSprite11.setShipIcon(this.availableShipStyles.get(i).intValue(), this.availableShipStyles.get(i).intValue(), ShipType.CRUISER, 0, 100.0f, 100.0f, 0, true);
            }
            shipSprite11.setSize(100.0f);
            attachChild(shipSprite11);
            this.empireShipStyleSelect.add(shipSprite11);
        }
        sortChildren();
    }

    public void randomEmpireSelected() {
        Game.options.setOption(OptionID.LAST_SELECTED_EMPIRE, -2.0f);
        this.empireSelectControl.set(-2, this.empires);
        this.selectedEmpireID = -2;
        this.fleet.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 670.0f, getWidth() + 100.0f, 190.0f, 190.0f) { // from class: com.birdshel.Uciana.Scenes.PlayerCreationScene.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: m */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                PlayerCreationScene.this.setRandomEmpire();
            }
        });
        swipeContentsOff();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.c0
            @Override // java.lang.Runnable
            public final void run() {
                PlayerCreationScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void set(int i, GalaxySize galaxySize, Difficulty difficulty, Map<GameSettingsEnum, Integer> map) {
        this.numberOfEmpires = i;
        this.galaxySize = galaxySize;
        this.difficulty = difficulty;
        this.gameSettings = map;
        this.randomPlayersChoice = new ArrayList();
        this.addPlayerButton.setVisible(true);
        this.addPlayerText.setVisible(true);
        this.addPlayerSummary.setVisible(true);
        for (int i2 = 0; i2 < 7; i2++) {
            this.empires[i2] = new CreateEmpireInfo();
            this.empires[i2].setEmpireType(EmpireType.NONE);
            this.empires[i2].setRaceID(i2);
            this.empires[i2].setBannerID(i2);
            this.empires[i2].setShipStyleID(i2);
        }
        int option = (int) Game.options.getOption(OptionID.LAST_SELECTED_EMPIRE, 0.0f);
        for (int i3 = 0; i3 < 7; i3++) {
            Game game = this.B;
            game.graphics.setShipIconsTextures(i3, i3, game.getActivity());
        }
        this.nebulas.setRandomSystem();
        this.selectedEmpireID = option;
        if (option >= 0 && option < 7) {
            setPlayer(option);
        } else if (option == -3) {
            this.empireSelectControl.set(option, this.empires);
            setCustomEmpire();
        } else {
            this.empireSelectControl.set(option, this.empires);
            setRandomEmpire();
        }
        clearPerkRadioButtons();
        int option2 = (int) Game.options.getOption(OptionID.RANDOM_EMPIRE_PERK, 0.0f);
        if (option2 == 0) {
            this.defaultPerksRadioButton.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        } else if (option2 == 1) {
            this.randomPerksRadioButton.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        } else if (option2 == 2) {
            this.noPerksRadioButton.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        }
    }

    public void setSelected(int i) {
        Game.options.setOption(OptionID.LAST_SELECTED_EMPIRE, i);
        this.empireSelectControl.set(i, this.empires);
        this.selectedEmpireID = i;
        this.fleet.registerEntityModifier(new MoveModifier(0.15f, getWidth() - 670.0f, getWidth() + 100.0f, 190.0f, 190.0f) { // from class: com.birdshel.Uciana.Scenes.PlayerCreationScene.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: m */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                PlayerCreationScene.this.setEmpire();
            }
        });
        swipeContentsOff();
    }

    public void showPlayerAlreadySelectedMessage() {
        showMessage(new TextMessage(this.B.getActivity().getString(R.string.select_empire_already_selected)));
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
        this.empireSelectControl.setPosition((getWidth() / 2.0f) - (this.empireSelectControl.getControlWidth() / 2.0f), 0.0f);
        this.addPlayerButton.setPosition(0.0f, 645.0f);
        this.selectEmpireText.setY(650.0f);
        this.startButton.setPosition(getWidth() - 450.0f, 645.0f);
        this.background.setAlpha(0.6f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }

    private void setCustomEmpire(int i) {
        setAvailableItems();
        this.selectedEmpireID = -3;
        this.empireSelectControl.setSelected(-3);
        Game.options.setOption(OptionID.LAST_SELECTED_EMPIRE, this.selectedEmpireID);
        this.customEmpireID = i;
        this.customRaceID = this.empires[i].getRaceID();
        this.customBannerID = this.empires[i].getBannerID();
        this.customShipStyleID = this.empires[i].getShipStyleID();
        setCustomEmpireItems();
    }
}
