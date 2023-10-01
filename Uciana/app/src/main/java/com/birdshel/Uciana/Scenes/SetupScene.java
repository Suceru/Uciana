package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Controls.Selector;
import com.birdshel.Uciana.Difficulty;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.StarSystems.GalaxySize;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarType;
import com.birdshel.Uciana.Technology.TechProgressionType;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SetupScene extends ExtendedScene {
    private Selector aiAppearanceSelector;
    private Selector attackOptionsSelector;
    private Selector blackholesSelector;
    private Selector difficultySelector;
    private Selector diplomaticOptionsSelector;
    private TiledSprite menuButton;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private TiledSprite nextButton;
    private Sprite nextButtonPressed;
    private Text nextText;
    private Selector numberOfEmpiresSelector;
    private Sprite pressSprite;
    private Selector randomEventsSelector;
    private Selector selectedSelector;
    private Sprite selectorBlackenedBackground;
    private Selector sizeSelector;
    private Selector spaceRiftsSelector;
    private Selector startingPositionsSelector;
    private Selector systemObjectPercentSelector;
    private Selector techProgressionSelector;
    private Selector wormholesSelector;

    public SetupScene(Game game) {
        this.game = game;
    }

    private void checkActionDown(Point point) {
        checkPressed(point);
    }

    private void checkActionMove(Point point) {
        this.pressSprite.setVisible(false);
        this.nextButtonPressed.setVisible(false);
        this.nextText.setColor(Color.BLACK);
        checkPressed(point);
    }

    private void checkActionUp(Point point) {
        this.pressSprite.setVisible(false);
        this.nextButtonPressed.setVisible(false);
        this.nextText.setColor(Color.BLACK);
        if (this.sizeSelector.isClicked(point)) {
            selectorPressed(this.sizeSelector);
        }
        if (this.numberOfEmpiresSelector.isClicked(point)) {
            selectorPressed(this.numberOfEmpiresSelector);
        }
        if (this.difficultySelector.isClicked(point)) {
            selectorPressed(this.difficultySelector);
        }
        if (this.blackholesSelector.isClicked(point)) {
            selectorPressed(this.blackholesSelector);
        }
        if (this.spaceRiftsSelector.isClicked(point)) {
            selectorPressed(this.spaceRiftsSelector);
        }
        if (this.wormholesSelector.isClicked(point)) {
            selectorPressed(this.wormholesSelector);
        }
        if (this.aiAppearanceSelector.isClicked(point)) {
            selectorPressed(this.aiAppearanceSelector);
        }
        if (this.randomEventsSelector.isClicked(point)) {
            selectorPressed(this.randomEventsSelector);
        }
        if (this.diplomaticOptionsSelector.isClicked(point)) {
            selectorPressed(this.diplomaticOptionsSelector);
        }
        if (this.attackOptionsSelector.isClicked(point)) {
            selectorPressed(this.attackOptionsSelector);
        }
        if (this.startingPositionsSelector.isClicked(point)) {
            selectorPressed(this.startingPositionsSelector);
        }
        if (this.techProgressionSelector.isClicked(point)) {
            selectorPressed(this.techProgressionSelector);
        }
        if (this.systemObjectPercentSelector.isClicked(point)) {
            selectorPressed(this.systemObjectPercentSelector);
        }
        if (isClicked(this.menuButton, point)) {
            menuButtonPressed();
        }
        if (isClicked(this.nextButton, point)) {
            nextButtonPressed();
        }
    }

    private void checkPressed(Point point) {
        if (isClicked(this.nextButton, point)) {
            this.nextButtonPressed.setVisible(true);
            this.nextText.setColor(Color.WHITE);
        }
        if (this.sizeSelector.isClicked(point)) {
            setPress(this.sizeSelector.getX(), this.sizeSelector.getY());
        }
        if (this.numberOfEmpiresSelector.isClicked(point)) {
            setPress(this.numberOfEmpiresSelector.getX(), this.numberOfEmpiresSelector.getY());
        }
        if (this.difficultySelector.isClicked(point)) {
            setPress(this.difficultySelector.getX(), this.difficultySelector.getY());
        }
        if (this.blackholesSelector.isClicked(point)) {
            setPress(this.blackholesSelector.getX(), this.blackholesSelector.getY());
        }
        if (this.spaceRiftsSelector.isClicked(point)) {
            setPress(this.spaceRiftsSelector.getX(), this.spaceRiftsSelector.getY());
        }
        if (this.wormholesSelector.isClicked(point)) {
            setPress(this.wormholesSelector.getX(), this.wormholesSelector.getY());
        }
        if (this.aiAppearanceSelector.isClicked(point)) {
            setPress(this.aiAppearanceSelector.getX(), this.aiAppearanceSelector.getY());
        }
        if (this.randomEventsSelector.isClicked(point)) {
            setPress(this.randomEventsSelector.getX(), this.randomEventsSelector.getY());
        }
        if (this.diplomaticOptionsSelector.isClicked(point)) {
            setPress(this.diplomaticOptionsSelector.getX(), this.diplomaticOptionsSelector.getY());
        }
        if (this.attackOptionsSelector.isClicked(point)) {
            setPress(this.attackOptionsSelector.getX(), this.attackOptionsSelector.getY());
        }
        if (this.startingPositionsSelector.isClicked(point)) {
            setPress(this.startingPositionsSelector.getX(), this.startingPositionsSelector.getY());
        }
        if (this.techProgressionSelector.isClicked(point)) {
            setPress(this.techProgressionSelector.getX(), this.techProgressionSelector.getY());
        }
        if (this.systemObjectPercentSelector.isClicked(point)) {
            setPress(this.systemObjectPercentSelector.getX(), this.systemObjectPercentSelector.getY());
        }
    }

    private void closeScene() {
    }

    private Map<GameSettingsEnum, Integer> getGameSettings() {
        boolean isOn = Game.options.isOn(OptionID.RANDOM_EVENTS, 1);
        HashMap hashMap = new HashMap();
        hashMap.put(GameSettingsEnum.RANDOM_EVENTS_ON, Integer.valueOf(isOn ? 1 : 0));
        hashMap.put(GameSettingsEnum.RANDOM_EVENTS_NEXT_TURN, Integer.valueOf(this.B.randomEvents.getInitialRandomEventTurn((GalaxySize) this.sizeSelector.getSelected())));
        hashMap.put(GameSettingsEnum.RANDOM_EVENTS_NEXT_TYPE, 0);
        hashMap.put(GameSettingsEnum.TECH_PROGRESSION_TYPE, Integer.valueOf(((Integer) this.techProgressionSelector.getSelected()).intValue()));
        hashMap.put(GameSettingsEnum.DIPLOMATIC_OPTIONS, Integer.valueOf(((Integer) this.diplomaticOptionsSelector.getSelected()).intValue()));
        hashMap.put(GameSettingsEnum.ATTACK_OPTIONS, Integer.valueOf(((Integer) this.attackOptionsSelector.getSelected()).intValue()));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void menuButtonPressed() {
        closeScene();
        changeScene(this.B.menuScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void nextButtonPressed() {
        closeScene();
        changeScene(this.B.playerCreationScene);
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void openScene() {
        this.sizeSelector.setSelected(GalaxySize.values()[(int) Game.options.getOption(OptionID.GALAXY_SIZE, GalaxySize.SMALL.ordinal())]);
        this.numberOfEmpiresSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.NUMBER_OF_PLAYERS, 4.0f)));
        this.difficultySelector.setSelected(Difficulty.values()[(int) Game.options.getOption(OptionID.DIFFICULTY, Difficulty.EASIER.ordinal())]);
        this.blackholesSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.BLACKHOLES_LEVEL, 10.0f)));
        this.spaceRiftsSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.SPACE_RIFTS_LEVEL, 10.0f)));
        this.wormholesSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.WORMHOLES_LEVEL, 1.0f)));
        this.aiAppearanceSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.AI_APPEARANCE, 0.0f)));
        this.randomEventsSelector.setSelected(Boolean.valueOf(Game.options.isOn(OptionID.RANDOM_EVENTS, 1)));
        this.diplomaticOptionsSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.DIPLOMATIC_OPTIONS, 0.0f)));
        this.attackOptionsSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.ATTACK_OPTIONS, 0.0f)));
        this.startingPositionsSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.STARTING_POSITIONS, 0.0f)));
        this.techProgressionSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.TECH_PROGRESSION, 0.0f)));
        this.systemObjectPercentSelector.setSelected(Integer.valueOf((int) Game.options.getOption(OptionID.SYSTEM_OBJECT_PERCENT, 1.0f)));
    }

    private void selectorPressed(Selector selector) {
        this.selectedSelector = selector;
        this.selectorBlackenedBackground.setVisible(true);
        selector.popOut();
        sortChildren();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setPress(float f2, float f3) {
        this.pressSprite.setVisible(true);
        this.pressSprite.setPosition(f2, f3);
    }

    private void setupScene() {
        this.nebulas.setRandomGalaxy();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof PlayerCreationScene) {
            int intValue = ((Integer) this.numberOfEmpiresSelector.getSelected()).intValue();
            this.B.playerCreationScene.set(intValue, (GalaxySize) this.sizeSelector.getSelected(), (Difficulty) this.difficultySelector.getSelected(), getGameSettings());
        } else if (extendedScene instanceof MenuScene) {
            this.B.menuScene.openMenu();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        Selector selector = this.selectedSelector;
        if (selector != null && selector.isVisible()) {
            closeSelector();
            return;
        }
        closeScene();
        changeScene(this.B.menuScene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        if (!this.selectorBlackenedBackground.isVisible()) {
            super.checkInput(i, point);
        }
        if (i == 0) {
            if (this.selectorBlackenedBackground.isVisible()) {
                this.selectedSelector.checkInput(0, point);
            } else {
                checkActionDown(point);
            }
        } else if (i == 1) {
            if (this.selectorBlackenedBackground.isVisible()) {
                this.selectedSelector.checkInput(1, point);
            } else {
                checkActionUp(point);
            }
        } else if (i != 2) {
        } else {
            if (this.selectorBlackenedBackground.isVisible()) {
                this.selectedSelector.checkInput(2, point);
            } else {
                checkActionMove(point);
            }
        }
    }

    public void closeSelector() {
        this.selectorBlackenedBackground.setVisible(false);
        this.selectedSelector.popBack();
        sortChildren();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        float f2;
        super.createScene(vertexBufferObjectManager);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true);
        E.setSize(getWidth(), 720.0f);
        E.setAlpha(0.6f);
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        E(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        Game game = this.B;
        Text G = G(0.0f, 0.0f, game.fonts.menuFont, game.getActivity().getString(R.string.new_new_game), this.E, vertexBufferObjectManager, true);
        G.setX((getWidth() / 2.0f) - (G.getWidth() / 2.0f));
        G.setY(43.0f - (G.getHeightScaled() / 2.0f));
        this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        Sprite E2 = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.pressSprite = E2;
        float f3 = 100.0f;
        E2.setSize(100.0f, 100.0f);
        Game game2 = this.B;
        Selector selector = new Selector(game2, "SETUP", OptionID.GALAXY_SIZE, vertexBufferObjectManager, game2.getActivity().getString(R.string.new_galaxy_size), this.B.getActivity().getString(R.string.new_size_selector_message));
        this.sizeSelector = selector;
        selector.setPosition(50.0f, 140.0f);
        attachChild(this.sizeSelector);
        GalaxySize[] values = GalaxySize.values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            GalaxySize galaxySize = values[i];
            TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
            tiledSprite.setCurrentTileIndex(2);
            tiledSprite.setSize(f3, f3);
            tiledSprite.setAlpha(0.7f);
            Font font = this.B.fonts.smallFont;
            int i2 = i;
            tiledSprite.attachChild(new Text(15.0f, 95.0f, font, "~" + galaxySize.getAverageNumberOfStars(), this.E, vertexBufferObjectManager));
            AnimatedSprite animatedSprite = new AnimatedSprite(50.0f, 85.0f, this.B.graphics.starsTexture, vertexBufferObjectManager);
            int ordinal = StarType.values()[Functions.random.nextInt(StarType.values().length)].ordinal() * 12;
            animatedSprite.animate(new long[]{125, 125, 125, (long) (Functions.random.nextInt(1000) + 2000)}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
            animatedSprite.setSize(40.0f, 40.0f);
            tiledSprite.attachChild(animatedSprite);
            if (galaxySize == GalaxySize.EXTRA_LARGE) {
                TiledSprite tiledSprite2 = new TiledSprite(-20.0f, -20.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
                tiledSprite2.setCurrentTileIndex(GameIconEnum.SMALL_GALAXY.ordinal());
                tiledSprite2.setSize(100.0f, 100.0f);
                tiledSprite.attachChild(tiledSprite2);
                TiledSprite tiledSprite3 = new TiledSprite(10.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
                tiledSprite3.setCurrentTileIndex(GameIconEnum.LARGE_GALAXY.ordinal());
                tiledSprite3.setSize(100.0f, 100.0f);
                tiledSprite.attachChild(tiledSprite3);
            } else {
                TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
                tiledSprite4.setCurrentTileIndex(GameIconEnum.getGalaxySizeIcon(galaxySize));
                tiledSprite4.setSize(100.0f, 100.0f);
                tiledSprite.attachChild(tiledSprite4);
            }
            Game game3 = this.B;
            Text text = new Text(0.0f, 130.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(galaxySize.getLabelID()), vertexBufferObjectManager);
            text.setX(50.0f - (text.getWidthScaled() / 2.0f));
            this.sizeSelector.addElement(galaxySize, tiledSprite, text);
            i = i2 + 1;
            f3 = 100.0f;
        }
        Game game4 = this.B;
        Selector selector2 = new Selector(game4, "SETUP", OptionID.NUMBER_OF_PLAYERS, vertexBufferObjectManager, game4.getActivity().getString(R.string.new_number_of_empires), this.B.getActivity().getString(R.string.new_number_empires_selector_message));
        this.numberOfEmpiresSelector = selector2;
        selector2.setPosition(210.0f, 140.0f);
        attachChild(this.numberOfEmpiresSelector);
        int i3 = 2;
        while (true) {
            f2 = 45.0f;
            if (i3 > 7) {
                break;
            }
            TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
            tiledSprite5.setCurrentTileIndex(2);
            tiledSprite5.setSize(100.0f, 100.0f);
            tiledSprite5.setAlpha(0.7f);
            Text text2 = new Text(0.0f, 0.0f, this.B.fonts.menuFont, Integer.toString(i3), this.E, vertexBufferObjectManager);
            text2.setScaleCenter(0.0f, 0.0f);
            text2.setScale(1.4f);
            text2.setX(45.0f - (text2.getWidthScaled() / 2.0f));
            text2.setY(55.0f - (text2.getHeightScaled() / 2.0f));
            tiledSprite5.attachChild(text2);
            this.numberOfEmpiresSelector.addElement(Integer.valueOf(i3), tiledSprite5, new Text(0.0f, 120.0f, this.B.fonts.smallInfoFont, "", vertexBufferObjectManager));
            i3++;
        }
        Game game5 = this.B;
        Selector selector3 = new Selector(game5, "SETUP", OptionID.DIFFICULTY, vertexBufferObjectManager, game5.getActivity().getString(R.string.new_difficulty), this.B.getActivity().getString(R.string.new_difficulty_selector_message));
        this.difficultySelector = selector3;
        selector3.setPosition(370.0f, 140.0f);
        attachChild(this.difficultySelector);
        Difficulty[] values2 = Difficulty.values();
        int length2 = values2.length;
        int i4 = 0;
        while (i4 < length2) {
            Difficulty difficulty = values2[i4];
            TiledSprite tiledSprite6 = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
            tiledSprite6.setCurrentTileIndex(2);
            tiledSprite6.setSize(100.0f, 100.0f);
            tiledSprite6.setAlpha(0.7f);
            TiledSprite tiledSprite7 = new TiledSprite(28.0f, 28.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite7.setCurrentTileIndex(difficulty.getInfoIconIndex());
            tiledSprite7.setSize(f2, f2);
            tiledSprite6.attachChild(tiledSprite7);
            Game game6 = this.B;
            Text text3 = new Text(0.0f, 120.0f, game6.fonts.smallInfoFont, game6.getActivity().getString(difficulty.getLabelID()), vertexBufferObjectManager);
            text3.setX(50.0f - (text3.getWidthScaled() / 2.0f));
            this.difficultySelector.addElement(difficulty, tiledSprite6, text3);
            i4++;
            f2 = 45.0f;
        }
        Game game7 = this.B;
        Selector selector4 = new Selector(game7, "SETUP", OptionID.BLACKHOLES_LEVEL, vertexBufferObjectManager, game7.getActivity().getString(R.string.new_blackholes), this.B.getActivity().getString(R.string.new_blackhole_selector_message));
        this.blackholesSelector = selector4;
        selector4.setPosition(530.0f, 140.0f);
        attachChild(this.blackholesSelector);
        TiledSprite tiledSprite8 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite8.setCurrentTileIndex(2);
        tiledSprite8.setSize(100.0f, 100.0f);
        tiledSprite8.setAlpha(0.7f);
        Sprite sprite = new Sprite(20.0f, 20.0f, this.B.graphics.blackholeTexture, vertexBufferObjectManager);
        sprite.setSize(60.0f, 60.0f);
        sprite.setRotationCenter(30.0f, 30.0f);
        sprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.5f, 0.0f, 360.0f)));
        tiledSprite8.attachChild(sprite);
        Game game8 = this.B;
        Text text4 = new Text(0.0f, 120.0f, game8.fonts.smallInfoFont, game8.getActivity().getString(R.string.blackhole_frequency_less), vertexBufferObjectManager);
        text4.setX(50.0f - (text4.getWidthScaled() / 2.0f));
        this.blackholesSelector.addElement(5, tiledSprite8, text4);
        TiledSprite tiledSprite9 = new TiledSprite(96.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite9.setCurrentTileIndex(2);
        tiledSprite9.setSize(100.0f, 100.0f);
        tiledSprite9.setAlpha(0.7f);
        Sprite sprite2 = new Sprite(5.0f, 5.0f, this.B.graphics.blackholeTexture, vertexBufferObjectManager);
        sprite2.setSize(60.0f, 60.0f);
        sprite2.setRotationCenter(30.0f, 30.0f);
        sprite2.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.5f, 0.0f, 360.0f)));
        tiledSprite9.attachChild(sprite2);
        Sprite sprite3 = new Sprite(30.0f, 30.0f, this.B.graphics.blackholeTexture, vertexBufferObjectManager);
        sprite3.setSize(60.0f, 60.0f);
        sprite3.setRotationCenter(30.0f, 30.0f);
        sprite3.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.5f, 0.0f, 360.0f)));
        tiledSprite9.attachChild(sprite3);
        Game game9 = this.B;
        Text text5 = new Text(0.0f, 120.0f, game9.fonts.smallInfoFont, game9.getActivity().getString(R.string.blackhole_frequency_normal), vertexBufferObjectManager);
        text5.setX(50.0f - (text5.getWidthScaled() / 2.0f));
        this.blackholesSelector.addElement(10, tiledSprite9, text5);
        TiledSprite tiledSprite10 = new TiledSprite(184.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite10.setCurrentTileIndex(2);
        tiledSprite10.setSize(100.0f, 100.0f);
        tiledSprite10.setAlpha(0.7f);
        Sprite sprite4 = new Sprite(0.0f, 5.0f, this.B.graphics.blackholeTexture, vertexBufferObjectManager);
        sprite4.setRotationCenter(30.0f, 30.0f);
        sprite4.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.5f, 0.0f, 360.0f)));
        sprite4.setSize(60.0f, 60.0f);
        tiledSprite10.attachChild(sprite4);
        Sprite sprite5 = new Sprite(20.0f, 35.0f, this.B.graphics.blackholeTexture, vertexBufferObjectManager);
        sprite5.setRotationCenter(30.0f, 30.0f);
        sprite5.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.5f, 0.0f, 360.0f)));
        sprite5.setSize(60.0f, 60.0f);
        tiledSprite10.attachChild(sprite5);
        Sprite sprite6 = new Sprite(40.0f, 10.0f, this.B.graphics.blackholeTexture, vertexBufferObjectManager);
        sprite6.setRotationCenter(30.0f, 30.0f);
        sprite6.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.5f, 0.0f, 360.0f)));
        sprite6.setSize(60.0f, 60.0f);
        tiledSprite10.attachChild(sprite6);
        Game game10 = this.B;
        Text text6 = new Text(0.0f, 120.0f, game10.fonts.smallInfoFont, game10.getActivity().getString(R.string.blackhole_frequency_more), vertexBufferObjectManager);
        text6.setX(50.0f - (text6.getWidthScaled() / 2.0f));
        this.blackholesSelector.addElement(15, tiledSprite10, text6);
        Game game11 = this.B;
        Selector selector5 = new Selector(game11, "SETUP", OptionID.SPACE_RIFTS_LEVEL, vertexBufferObjectManager, game11.getActivity().getString(R.string.new_space_rifts), this.B.getActivity().getString(R.string.new_space_rift_selector_message));
        this.spaceRiftsSelector = selector5;
        selector5.setPosition(690.0f, 140.0f);
        attachChild(this.spaceRiftsSelector);
        TiledSprite tiledSprite11 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite11.setCurrentTileIndex(2);
        tiledSprite11.setSize(100.0f, 100.0f);
        tiledSprite11.setAlpha(0.7f);
        TiledSprite tiledSprite12 = new TiledSprite(25.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        GameIconEnum gameIconEnum = GameIconEnum.SPACE_RIFT;
        tiledSprite12.setCurrentTileIndex(gameIconEnum.ordinal());
        tiledSprite12.setSize(50.0f, 50.0f);
        tiledSprite12.setRotationCenter(25.0f, 25.0f);
        tiledSprite12.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.0f, 0.0f, 360.0f)));
        tiledSprite11.attachChild(tiledSprite12);
        Game game12 = this.B;
        Text text7 = new Text(0.0f, 120.0f, game12.fonts.smallInfoFont, game12.getActivity().getString(R.string.space_rift_frequency_less), vertexBufferObjectManager);
        text7.setX(50.0f - (text7.getWidthScaled() / 2.0f));
        this.spaceRiftsSelector.addElement(5, tiledSprite11, text7);
        TiledSprite tiledSprite13 = new TiledSprite(96.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite13.setCurrentTileIndex(2);
        tiledSprite13.setSize(100.0f, 100.0f);
        tiledSprite13.setAlpha(0.7f);
        TiledSprite tiledSprite14 = new TiledSprite(40.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite14.setCurrentTileIndex(gameIconEnum.ordinal());
        tiledSprite14.setSize(50.0f, 50.0f);
        tiledSprite14.setRotationCenter(25.0f, 25.0f);
        tiledSprite14.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.0f, 0.0f, 360.0f)));
        tiledSprite13.attachChild(tiledSprite14);
        TiledSprite tiledSprite15 = new TiledSprite(5.0f, 45.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite15.setCurrentTileIndex(gameIconEnum.ordinal());
        tiledSprite15.setSize(50.0f, 50.0f);
        tiledSprite15.setRotationCenter(25.0f, 25.0f);
        tiledSprite15.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.0f, 0.0f, 360.0f)));
        tiledSprite13.attachChild(tiledSprite15);
        Game game13 = this.B;
        Text text8 = new Text(0.0f, 120.0f, game13.fonts.smallInfoFont, game13.getActivity().getString(R.string.space_rift_frequency_normal), vertexBufferObjectManager);
        text8.setX(50.0f - (text8.getWidthScaled() / 2.0f));
        this.spaceRiftsSelector.addElement(10, tiledSprite13, text8);
        TiledSprite tiledSprite16 = new TiledSprite(184.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite16.setCurrentTileIndex(2);
        tiledSprite16.setSize(100.0f, 100.0f);
        tiledSprite16.setAlpha(0.7f);
        TiledSprite tiledSprite17 = new TiledSprite(0.0f, 20.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite17.setCurrentTileIndex(gameIconEnum.ordinal());
        tiledSprite17.setSize(50.0f, 50.0f);
        tiledSprite17.setRotationCenter(25.0f, 25.0f);
        tiledSprite17.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.0f, 0.0f, 360.0f)));
        tiledSprite16.attachChild(tiledSprite17);
        TiledSprite tiledSprite18 = new TiledSprite(45.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite18.setCurrentTileIndex(gameIconEnum.ordinal());
        tiledSprite18.setSize(50.0f, 50.0f);
        tiledSprite18.setRotationCenter(25.0f, 25.0f);
        tiledSprite18.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.0f, 0.0f, 360.0f)));
        tiledSprite16.attachChild(tiledSprite18);
        TiledSprite tiledSprite19 = new TiledSprite(35.0f, 45.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite19.setCurrentTileIndex(gameIconEnum.ordinal());
        tiledSprite19.setSize(50.0f, 50.0f);
        tiledSprite19.setRotationCenter(25.0f, 25.0f);
        tiledSprite19.registerEntityModifier(new LoopEntityModifier(new RotationModifier(3.0f, 0.0f, 360.0f)));
        tiledSprite16.attachChild(tiledSprite19);
        Game game14 = this.B;
        Text text9 = new Text(0.0f, 120.0f, game14.fonts.smallInfoFont, game14.getActivity().getString(R.string.space_rift_frequency_more), vertexBufferObjectManager);
        text9.setX(50.0f - (text9.getWidthScaled() / 2.0f));
        this.spaceRiftsSelector.addElement(15, tiledSprite16, text9);
        Game game15 = this.B;
        Selector selector6 = new Selector(game15, "SETUP", OptionID.WORMHOLES_LEVEL, vertexBufferObjectManager, game15.getActivity().getString(R.string.new_wormholes), this.B.getActivity().getString(R.string.new_wormholes_selector_message));
        this.wormholesSelector = selector6;
        selector6.setPosition(850.0f, 140.0f);
        attachChild(this.wormholesSelector);
        TiledSprite tiledSprite20 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite20.setCurrentTileIndex(2);
        tiledSprite20.setSize(100.0f, 100.0f);
        tiledSprite20.setAlpha(0.7f);
        TiledSprite tiledSprite21 = new TiledSprite(25.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        GameIconEnum gameIconEnum2 = GameIconEnum.BOTTOM_WORMHOLE_LAYER;
        tiledSprite21.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite21.setSize(50.0f, 50.0f);
        tiledSprite21.setRotationCenter(25.0f, 25.0f);
        tiledSprite21.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        tiledSprite20.attachChild(tiledSprite21);
        TiledSprite tiledSprite22 = new TiledSprite(25.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        GameIconEnum gameIconEnum3 = GameIconEnum.TOP_WORMHOLE_LAYER;
        tiledSprite22.setCurrentTileIndex(gameIconEnum3.ordinal());
        tiledSprite22.setSize(50.0f, 50.0f);
        tiledSprite22.setRotationCenter(25.0f, 25.0f);
        tiledSprite22.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        tiledSprite20.attachChild(tiledSprite22);
        TiledSprite tiledSprite23 = new TiledSprite(20.0f, 20.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        InfoIconEnum infoIconEnum = InfoIconEnum.NOT_ALLOWED;
        tiledSprite23.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite23.setSize(60.0f, 60.0f);
        tiledSprite20.attachChild(tiledSprite23);
        Game game16 = this.B;
        Text text10 = new Text(0.0f, 120.0f, game16.fonts.smallInfoFont, game16.getActivity().getString(R.string.wormhole_frequency_none), vertexBufferObjectManager);
        text10.setX(50.0f - (text10.getWidthScaled() / 2.0f));
        this.wormholesSelector.addElement(0, tiledSprite20, text10);
        TiledSprite tiledSprite24 = new TiledSprite(96.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite24.setCurrentTileIndex(2);
        tiledSprite24.setSize(100.0f, 100.0f);
        tiledSprite24.setAlpha(0.7f);
        TiledSprite tiledSprite25 = new TiledSprite(25.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite25.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite25.setSize(50.0f, 50.0f);
        tiledSprite25.setRotationCenter(25.0f, 25.0f);
        tiledSprite25.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        tiledSprite24.attachChild(tiledSprite25);
        TiledSprite tiledSprite26 = new TiledSprite(25.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite26.setCurrentTileIndex(gameIconEnum3.ordinal());
        tiledSprite26.setSize(50.0f, 50.0f);
        tiledSprite26.setRotationCenter(25.0f, 25.0f);
        tiledSprite26.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        tiledSprite24.attachChild(tiledSprite26);
        Game game17 = this.B;
        Text text11 = new Text(0.0f, 120.0f, game17.fonts.smallInfoFont, game17.getActivity().getString(R.string.wormhole_frequency_low), vertexBufferObjectManager);
        text11.setX(50.0f - (text11.getWidthScaled() / 2.0f));
        this.wormholesSelector.addElement(3, tiledSprite24, text11);
        TiledSprite tiledSprite27 = new TiledSprite(96.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite27.setCurrentTileIndex(2);
        tiledSprite27.setSize(100.0f, 100.0f);
        tiledSprite27.setAlpha(0.7f);
        TiledSprite tiledSprite28 = new TiledSprite(40.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite28.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite28.setSize(50.0f, 50.0f);
        tiledSprite28.setRotationCenter(25.0f, 25.0f);
        tiledSprite28.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        tiledSprite27.attachChild(tiledSprite28);
        TiledSprite tiledSprite29 = new TiledSprite(40.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite29.setCurrentTileIndex(gameIconEnum3.ordinal());
        tiledSprite29.setSize(50.0f, 50.0f);
        tiledSprite29.setRotationCenter(25.0f, 25.0f);
        tiledSprite29.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        tiledSprite27.attachChild(tiledSprite29);
        TiledSprite tiledSprite30 = new TiledSprite(5.0f, 45.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite30.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite30.setSize(50.0f, 50.0f);
        tiledSprite30.setRotationCenter(25.0f, 25.0f);
        tiledSprite30.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        tiledSprite27.attachChild(tiledSprite30);
        TiledSprite tiledSprite31 = new TiledSprite(5.0f, 45.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite31.setCurrentTileIndex(gameIconEnum3.ordinal());
        tiledSprite31.setSize(50.0f, 50.0f);
        tiledSprite31.setRotationCenter(25.0f, 25.0f);
        tiledSprite31.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        tiledSprite27.attachChild(tiledSprite31);
        Game game18 = this.B;
        Text text12 = new Text(0.0f, 120.0f, game18.fonts.smallInfoFont, game18.getActivity().getString(R.string.wormhole_frequency_normal), vertexBufferObjectManager);
        text12.setX(50.0f - (text12.getWidthScaled() / 2.0f));
        this.wormholesSelector.addElement(1, tiledSprite27, text12);
        TiledSprite tiledSprite32 = new TiledSprite(96.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite32.setCurrentTileIndex(2);
        tiledSprite32.setSize(100.0f, 100.0f);
        tiledSprite32.setAlpha(0.7f);
        TiledSprite tiledSprite33 = new TiledSprite(0.0f, 20.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite33.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite33.setSize(50.0f, 50.0f);
        tiledSprite33.setRotationCenter(25.0f, 25.0f);
        tiledSprite33.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        tiledSprite32.attachChild(tiledSprite33);
        TiledSprite tiledSprite34 = new TiledSprite(0.0f, 20.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite34.setCurrentTileIndex(gameIconEnum3.ordinal());
        tiledSprite34.setSize(50.0f, 50.0f);
        tiledSprite34.setRotationCenter(25.0f, 25.0f);
        tiledSprite34.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        tiledSprite32.attachChild(tiledSprite34);
        TiledSprite tiledSprite35 = new TiledSprite(45.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite35.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite35.setSize(50.0f, 50.0f);
        tiledSprite35.setRotationCenter(25.0f, 25.0f);
        tiledSprite35.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        tiledSprite32.attachChild(tiledSprite35);
        TiledSprite tiledSprite36 = new TiledSprite(45.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite36.setCurrentTileIndex(gameIconEnum3.ordinal());
        tiledSprite36.setSize(50.0f, 50.0f);
        tiledSprite36.setRotationCenter(25.0f, 25.0f);
        tiledSprite36.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        tiledSprite32.attachChild(tiledSprite36);
        TiledSprite tiledSprite37 = new TiledSprite(35.0f, 45.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite37.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite37.setSize(50.0f, 50.0f);
        tiledSprite37.setRotationCenter(25.0f, 25.0f);
        tiledSprite37.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        tiledSprite32.attachChild(tiledSprite37);
        TiledSprite tiledSprite38 = new TiledSprite(35.0f, 45.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite38.setCurrentTileIndex(gameIconEnum3.ordinal());
        tiledSprite38.setSize(50.0f, 50.0f);
        tiledSprite38.setRotationCenter(25.0f, 25.0f);
        tiledSprite38.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        tiledSprite32.attachChild(tiledSprite38);
        Game game19 = this.B;
        Text text13 = new Text(0.0f, 120.0f, game19.fonts.smallInfoFont, game19.getActivity().getString(R.string.wormhole_frequency_more), vertexBufferObjectManager);
        text13.setX(50.0f - (text13.getWidthScaled() / 2.0f));
        this.wormholesSelector.addElement(2, tiledSprite32, text13);
        TiledSprite tiledSprite39 = new TiledSprite(96.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite39.setCurrentTileIndex(2);
        tiledSprite39.setSize(100.0f, 100.0f);
        tiledSprite39.setAlpha(0.7f);
        TiledSprite tiledSprite40 = new TiledSprite(5.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite40.setCurrentTileIndex(gameIconEnum2.ordinal());
        tiledSprite40.setSize(50.0f, 50.0f);
        tiledSprite40.setRotationCenter(25.0f, 25.0f);
        tiledSprite40.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.5f, 360.0f, 0.0f)));
        tiledSprite39.attachChild(tiledSprite40);
        TiledSprite tiledSprite41 = new TiledSprite(5.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite41.setCurrentTileIndex(gameIconEnum3.ordinal());
        tiledSprite41.setSize(50.0f, 50.0f);
        tiledSprite41.setRotationCenter(25.0f, 25.0f);
        tiledSprite41.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2.0f, 0.0f, 360.0f)));
        tiledSprite39.attachChild(tiledSprite41);
        TiledSprite tiledSprite42 = new TiledSprite(45.0f, 30.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        InfoIconEnum infoIconEnum2 = InfoIconEnum.RANDOM;
        tiledSprite42.setCurrentTileIndex(infoIconEnum2.ordinal());
        tiledSprite42.setSize(40.0f, 40.0f);
        tiledSprite39.attachChild(tiledSprite42);
        Game game20 = this.B;
        Text text14 = new Text(0.0f, 120.0f, game20.fonts.smallInfoFont, game20.getActivity().getString(R.string.wormhole_frequency_random), vertexBufferObjectManager);
        text14.setX(50.0f - (text14.getWidthScaled() / 2.0f));
        this.wormholesSelector.addElement(4, tiledSprite39, text14);
        Game game21 = this.B;
        Selector selector7 = new Selector(game21, "SETUP", OptionID.AI_APPEARANCE, vertexBufferObjectManager, game21.getActivity().getString(R.string.new_ai_appearance), this.B.getActivity().getString(R.string.new_ai_appearance_selector_message));
        this.aiAppearanceSelector = selector7;
        selector7.setPosition(1010.0f, 140.0f);
        attachChild(this.aiAppearanceSelector);
        TiledSprite tiledSprite43 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite43.setCurrentTileIndex(2);
        tiledSprite43.setSize(100.0f, 100.0f);
        tiledSprite43.setAlpha(0.7f);
        TiledSprite tiledSprite44 = new TiledSprite(10.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite44.setCurrentTileIndex(3);
        tiledSprite44.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite45 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite45.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(5));
        tiledSprite45.setSize(20.0f, 20.0f);
        tiledSprite44.attachChild(tiledSprite45);
        tiledSprite43.attachChild(tiledSprite44);
        TiledSprite tiledSprite46 = new TiledSprite(40.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite46.setCurrentTileIndex(5);
        tiledSprite46.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite47 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite47.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(2));
        tiledSprite47.setSize(20.0f, 20.0f);
        tiledSprite46.attachChild(tiledSprite47);
        tiledSprite43.attachChild(tiledSprite46);
        TiledSprite tiledSprite48 = new TiledSprite(70.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite48.setCurrentTileIndex(1);
        tiledSprite48.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite49 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite49.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(4));
        tiledSprite49.setSize(20.0f, 20.0f);
        tiledSprite48.attachChild(tiledSprite49);
        tiledSprite43.attachChild(tiledSprite48);
        TiledSprite tiledSprite50 = new TiledSprite(10.0f, 70.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite50.setCurrentTileIndex(2);
        tiledSprite50.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite51 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite51.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(1));
        tiledSprite51.setSize(20.0f, 20.0f);
        tiledSprite50.attachChild(tiledSprite51);
        tiledSprite43.attachChild(tiledSprite50);
        TiledSprite tiledSprite52 = new TiledSprite(10.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite52.setCurrentTileIndex(0);
        tiledSprite52.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite53 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite53.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(3));
        tiledSprite53.setSize(20.0f, 20.0f);
        tiledSprite52.attachChild(tiledSprite53);
        tiledSprite43.attachChild(tiledSprite52);
        TiledSprite tiledSprite54 = new TiledSprite(70.0f, 70.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite54.setCurrentTileIndex(4);
        tiledSprite54.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite55 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite55.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(6));
        tiledSprite55.setSize(20.0f, 20.0f);
        tiledSprite54.attachChild(tiledSprite55);
        tiledSprite43.attachChild(tiledSprite54);
        TiledSprite tiledSprite56 = new TiledSprite(70.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite56.setCurrentTileIndex(6);
        tiledSprite56.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite57 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite57.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(0));
        tiledSprite57.setSize(20.0f, 20.0f);
        tiledSprite56.attachChild(tiledSprite57);
        tiledSprite43.attachChild(tiledSprite56);
        TiledSprite tiledSprite58 = new TiledSprite(30.0f, 30.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite58.setCurrentTileIndex(infoIconEnum2.ordinal());
        tiledSprite58.setSize(40.0f, 40.0f);
        tiledSprite43.attachChild(tiledSprite58);
        Game game22 = this.B;
        Text text15 = new Text(0.0f, 120.0f, game22.fonts.smallInfoFont, game22.getActivity().getString(R.string.random_ai_yes), vertexBufferObjectManager);
        text15.setX(50.0f - (text15.getWidthScaled() / 2.0f));
        this.aiAppearanceSelector.addElement(1, tiledSprite43, text15);
        TiledSprite tiledSprite59 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite59.setCurrentTileIndex(2);
        tiledSprite59.setSize(100.0f, 100.0f);
        tiledSprite59.setAlpha(0.7f);
        TiledSprite tiledSprite60 = new TiledSprite(10.0f, 20.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite60.setCurrentTileIndex(0);
        tiledSprite60.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite61 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite61.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(0));
        tiledSprite61.setSize(20.0f, 20.0f);
        tiledSprite60.attachChild(tiledSprite61);
        tiledSprite59.attachChild(tiledSprite60);
        TiledSprite tiledSprite62 = new TiledSprite(40.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite62.setCurrentTileIndex(1);
        tiledSprite62.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite63 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite63.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(1));
        tiledSprite63.setSize(20.0f, 20.0f);
        tiledSprite62.attachChild(tiledSprite63);
        tiledSprite59.attachChild(tiledSprite62);
        TiledSprite tiledSprite64 = new TiledSprite(70.0f, 20.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite64.setCurrentTileIndex(2);
        tiledSprite64.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite65 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite65.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(2));
        tiledSprite65.setSize(20.0f, 20.0f);
        tiledSprite64.attachChild(tiledSprite65);
        tiledSprite59.attachChild(tiledSprite64);
        TiledSprite tiledSprite66 = new TiledSprite(10.0f, 60.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite66.setCurrentTileIndex(3);
        tiledSprite66.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite67 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite67.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(3));
        tiledSprite67.setSize(20.0f, 20.0f);
        tiledSprite66.attachChild(tiledSprite67);
        tiledSprite59.attachChild(tiledSprite66);
        TiledSprite tiledSprite68 = new TiledSprite(40.0f, 70.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite68.setCurrentTileIndex(4);
        tiledSprite68.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite69 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite69.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(0));
        tiledSprite69.setSize(20.0f, 20.0f);
        tiledSprite68.attachChild(tiledSprite69);
        tiledSprite59.attachChild(tiledSprite68);
        TiledSprite tiledSprite70 = new TiledSprite(70.0f, 60.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite70.setCurrentTileIndex(5);
        tiledSprite70.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite71 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite71.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(5));
        tiledSprite71.setSize(20.0f, 20.0f);
        tiledSprite70.attachChild(tiledSprite71);
        tiledSprite59.attachChild(tiledSprite70);
        TiledSprite tiledSprite72 = new TiledSprite(40.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite72.setCurrentTileIndex(6);
        tiledSprite72.setSize(20.0f, 20.0f);
        TiledSprite tiledSprite73 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite73.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(6));
        tiledSprite73.setSize(20.0f, 20.0f);
        tiledSprite72.attachChild(tiledSprite73);
        tiledSprite59.attachChild(tiledSprite72);
        Game game23 = this.B;
        Text text16 = new Text(0.0f, 120.0f, game23.fonts.smallInfoFont, game23.getActivity().getString(R.string.random_ai_no), vertexBufferObjectManager);
        text16.setX(50.0f - (text16.getWidthScaled() / 2.0f));
        this.aiAppearanceSelector.addElement(0, tiledSprite59, text16);
        Game game24 = this.B;
        Selector selector8 = new Selector(game24, "SETUP", OptionID.RANDOM_EVENTS, vertexBufferObjectManager, game24.getActivity().getString(R.string.new_random_events), this.B.getActivity().getString(R.string.new_random_events_selector_message));
        this.randomEventsSelector = selector8;
        selector8.setPosition(50.0f, 355.0f);
        attachChild(this.randomEventsSelector);
        TiledSprite tiledSprite74 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite74.setCurrentTileIndex(2);
        tiledSprite74.setSize(100.0f, 100.0f);
        tiledSprite74.setAlpha(0.7f);
        TiledSprite tiledSprite75 = new TiledSprite(25.0f, 25.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        InfoIconEnum infoIconEnum3 = InfoIconEnum.RANDOM_EVENT;
        tiledSprite75.setCurrentTileIndex(infoIconEnum3.ordinal());
        tiledSprite75.setSize(50.0f, 50.0f);
        tiledSprite74.attachChild(tiledSprite75);
        TiledSprite tiledSprite76 = new TiledSprite(20.0f, 20.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite76.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite76.setSize(60.0f, 60.0f);
        tiledSprite74.attachChild(tiledSprite76);
        Game game25 = this.B;
        Text text17 = new Text(0.0f, 120.0f, game25.fonts.smallInfoFont, game25.getActivity().getString(R.string.random_events_off), vertexBufferObjectManager);
        text17.setX(50.0f - (text17.getWidthScaled() / 2.0f));
        this.randomEventsSelector.addElement(Boolean.FALSE, tiledSprite74, text17);
        TiledSprite tiledSprite77 = new TiledSprite(96.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite77.setCurrentTileIndex(2);
        tiledSprite77.setSize(100.0f, 100.0f);
        tiledSprite77.setAlpha(0.7f);
        TiledSprite tiledSprite78 = new TiledSprite(25.0f, 25.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite78.setCurrentTileIndex(infoIconEnum3.ordinal());
        tiledSprite78.setSize(50.0f, 50.0f);
        tiledSprite77.attachChild(tiledSprite78);
        Game game26 = this.B;
        Text text18 = new Text(0.0f, 120.0f, game26.fonts.smallInfoFont, game26.getActivity().getString(R.string.random_events_on), vertexBufferObjectManager);
        text18.setX(50.0f - (text18.getWidthScaled() / 2.0f));
        this.randomEventsSelector.addElement(Boolean.TRUE, tiledSprite77, text18);
        Game game27 = this.B;
        Selector selector9 = new Selector(game27, "SETUP", OptionID.DIPLOMATIC_OPTIONS, vertexBufferObjectManager, game27.getActivity().getString(R.string.new_diplomatic_options), this.B.getActivity().getString(R.string.new_diplomatic_options_selector_message));
        this.diplomaticOptionsSelector = selector9;
        selector9.setPosition(210.0f, 355.0f);
        attachChild(this.diplomaticOptionsSelector);
        TiledSprite tiledSprite79 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite79.setCurrentTileIndex(2);
        tiledSprite79.setSize(100.0f, 100.0f);
        tiledSprite79.setAlpha(0.7f);
        TiledSprite tiledSprite80 = new TiledSprite(5.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite80.setCurrentTileIndex(GameIconEnum.PEACE.ordinal());
        tiledSprite80.setSize(50.0f, 50.0f);
        tiledSprite79.attachChild(tiledSprite80);
        TiledSprite tiledSprite81 = new TiledSprite(40.0f, 5.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite81.setCurrentTileIndex(GameIconEnum.TRADE_TREATY.ordinal());
        tiledSprite81.setSize(50.0f, 50.0f);
        tiledSprite79.attachChild(tiledSprite81);
        TiledSprite tiledSprite82 = new TiledSprite(5.0f, 45.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite82.setCurrentTileIndex(GameIconEnum.NON_AGGRESSION_TREATY.ordinal());
        tiledSprite82.setSize(50.0f, 50.0f);
        tiledSprite79.attachChild(tiledSprite82);
        TiledSprite tiledSprite83 = new TiledSprite(40.0f, 45.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite83.setCurrentTileIndex(GameIconEnum.ALLIANCE.ordinal());
        tiledSprite83.setSize(50.0f, 50.0f);
        tiledSprite79.attachChild(tiledSprite83);
        Game game28 = this.B;
        Text text19 = new Text(0.0f, 120.0f, game28.fonts.smallInfoFont, game28.getActivity().getString(R.string.diplomatic_options_all), vertexBufferObjectManager);
        text19.setX(50.0f - (text19.getWidthScaled() / 2.0f));
        this.diplomaticOptionsSelector.addElement(0, tiledSprite79, text19);
        TiledSprite tiledSprite84 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite84.setCurrentTileIndex(2);
        tiledSprite84.setSize(100.0f, 100.0f);
        tiledSprite84.setAlpha(0.7f);
        TiledSprite tiledSprite85 = new TiledSprite(25.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite85.setCurrentTileIndex(GameIconEnum.WAR.ordinal());
        tiledSprite85.setSize(50.0f, 50.0f);
        tiledSprite84.attachChild(tiledSprite85);
        Game game29 = this.B;
        Text text20 = new Text(0.0f, 120.0f, game29.fonts.smallInfoFont, game29.getActivity().getString(R.string.diplomatic_options_only_war), vertexBufferObjectManager);
        text20.setX(50.0f - (text20.getWidthScaled() / 2.0f));
        this.diplomaticOptionsSelector.addElement(1, tiledSprite84, text20);
        Game game30 = this.B;
        Selector selector10 = new Selector(game30, "SETUP", OptionID.ATTACK_OPTIONS, vertexBufferObjectManager, game30.getActivity().getString(R.string.new_attack_options), this.B.getActivity().getString(R.string.new_attack_options_descriptions));
        this.attackOptionsSelector = selector10;
        selector10.setPosition(370.0f, 355.0f);
        attachChild(this.attackOptionsSelector);
        TiledSprite tiledSprite86 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite86.setCurrentTileIndex(2);
        tiledSprite86.setSize(100.0f, 100.0f);
        tiledSprite86.setAlpha(0.7f);
        TiledSprite tiledSprite87 = new TiledSprite(20.0f, 5.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        tiledSprite87.setCurrentTileIndex(ButtonsEnum.ATTACK.ordinal());
        tiledSprite87.setSize(60.0f, 43.0f);
        tiledSprite86.attachChild(tiledSprite87);
        TiledSprite tiledSprite88 = new TiledSprite(20.0f, 49.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        ButtonsEnum buttonsEnum = ButtonsEnum.AUTO_ATTACK;
        tiledSprite88.setCurrentTileIndex(buttonsEnum.ordinal());
        tiledSprite88.setSize(60.0f, 43.0f);
        tiledSprite86.attachChild(tiledSprite88);
        Game game31 = this.B;
        Text text21 = new Text(0.0f, 120.0f, game31.fonts.smallInfoFont, game31.getActivity().getString(R.string.new_attack_options_both), vertexBufferObjectManager);
        text21.setX(50.0f - (text21.getWidthScaled() / 2.0f));
        this.attackOptionsSelector.addElement(0, tiledSprite86, text21);
        TiledSprite tiledSprite89 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite89.setCurrentTileIndex(2);
        tiledSprite89.setSize(100.0f, 100.0f);
        tiledSprite89.setAlpha(0.7f);
        TiledSprite tiledSprite90 = new TiledSprite(20.0f, 28.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        tiledSprite90.setCurrentTileIndex(buttonsEnum.ordinal());
        tiledSprite90.setSize(60.0f, 43.0f);
        tiledSprite89.attachChild(tiledSprite90);
        Game game32 = this.B;
        Font font2 = game32.fonts.smallInfoFont;
        String string = game32.getActivity().getString(R.string.new_attack_options_only_auto_battle);
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text22 = new Text(0.0f, 120.0f, font2, string, new TextOptions(horizontalAlign), vertexBufferObjectManager);
        text22.setX(50.0f - (text22.getWidthScaled() / 2.0f));
        this.attackOptionsSelector.addElement(1, tiledSprite89, text22);
        Game game33 = this.B;
        Selector selector11 = new Selector(game33, "SETUP", OptionID.STARTING_POSITIONS, vertexBufferObjectManager, game33.getActivity().getString(R.string.new_starting_positions), this.B.getActivity().getString(R.string.new_starting_positions_desc));
        this.startingPositionsSelector = selector11;
        selector11.setPosition(530.0f, 355.0f);
        attachChild(this.startingPositionsSelector);
        TiledSprite tiledSprite91 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite91.setCurrentTileIndex(2);
        tiledSprite91.setSize(100.0f, 100.0f);
        tiledSprite91.setAlpha(0.7f);
        TiledSprite tiledSprite92 = new TiledSprite(-10.0f, -10.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        GameIconEnum gameIconEnum4 = GameIconEnum.LARGE_GALAXY;
        tiledSprite92.setCurrentTileIndex(gameIconEnum4.ordinal());
        tiledSprite92.setSize(120.0f, 120.0f);
        tiledSprite92.setAlpha(0.4f);
        tiledSprite91.attachChild(tiledSprite92);
        TiledSprite tiledSprite93 = new TiledSprite(10.0f, 25.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite93.setSize(9.0f, 9.0f);
        tiledSprite93.setCurrentTileIndex(3);
        tiledSprite91.attachChild(tiledSprite93);
        TiledSprite tiledSprite94 = new TiledSprite(10.0f, 25.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite94.setSize(9.0f, 9.0f);
        tiledSprite94.setCurrentTileIndex(3);
        tiledSprite91.attachChild(tiledSprite94);
        TiledSprite tiledSprite95 = new TiledSprite(45.0f, 20.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite95.setSize(9.0f, 9.0f);
        tiledSprite95.setCurrentTileIndex(1);
        tiledSprite91.attachChild(tiledSprite95);
        TiledSprite tiledSprite96 = new TiledSprite(45.0f, 20.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite96.setSize(9.0f, 9.0f);
        tiledSprite96.setCurrentTileIndex(1);
        tiledSprite91.attachChild(tiledSprite96);
        TiledSprite tiledSprite97 = new TiledSprite(80.0f, 25.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite97.setSize(9.0f, 9.0f);
        tiledSprite97.setCurrentTileIndex(5);
        tiledSprite91.attachChild(tiledSprite97);
        TiledSprite tiledSprite98 = new TiledSprite(80.0f, 25.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite98.setSize(9.0f, 9.0f);
        tiledSprite98.setCurrentTileIndex(5);
        tiledSprite91.attachChild(tiledSprite98);
        TiledSprite tiledSprite99 = new TiledSprite(10.0f, 70.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite99.setSize(9.0f, 9.0f);
        tiledSprite99.setCurrentTileIndex(0);
        tiledSprite91.attachChild(tiledSprite99);
        TiledSprite tiledSprite100 = new TiledSprite(10.0f, 70.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite100.setSize(9.0f, 9.0f);
        tiledSprite100.setCurrentTileIndex(0);
        tiledSprite91.attachChild(tiledSprite100);
        TiledSprite tiledSprite101 = new TiledSprite(45.0f, 75.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite101.setSize(9.0f, 9.0f);
        tiledSprite101.setCurrentTileIndex(4);
        tiledSprite91.attachChild(tiledSprite101);
        TiledSprite tiledSprite102 = new TiledSprite(45.0f, 75.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite102.setSize(9.0f, 9.0f);
        tiledSprite102.setCurrentTileIndex(4);
        tiledSprite91.attachChild(tiledSprite102);
        TiledSprite tiledSprite103 = new TiledSprite(80.0f, 70.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite103.setSize(9.0f, 9.0f);
        tiledSprite103.setCurrentTileIndex(2);
        tiledSprite91.attachChild(tiledSprite103);
        TiledSprite tiledSprite104 = new TiledSprite(80.0f, 70.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite104.setSize(9.0f, 9.0f);
        tiledSprite104.setCurrentTileIndex(2);
        tiledSprite91.attachChild(tiledSprite104);
        TiledSprite tiledSprite105 = new TiledSprite(45.0f, 47.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite105.setSize(9.0f, 9.0f);
        tiledSprite105.setCurrentTileIndex(2);
        tiledSprite91.attachChild(tiledSprite105);
        TiledSprite tiledSprite106 = new TiledSprite(45.0f, 47.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite106.setSize(9.0f, 9.0f);
        tiledSprite106.setCurrentTileIndex(6);
        tiledSprite91.attachChild(tiledSprite106);
        Game game34 = this.B;
        Text text23 = new Text(0.0f, 120.0f, game34.fonts.smallInfoFont, game34.getActivity().getString(R.string.new_balanced), vertexBufferObjectManager);
        text23.setX(50.0f - (text23.getWidthScaled() / 2.0f));
        this.startingPositionsSelector.addElement(0, tiledSprite91, text23);
        TiledSprite tiledSprite107 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite107.setCurrentTileIndex(2);
        tiledSprite107.setSize(100.0f, 100.0f);
        tiledSprite107.setAlpha(0.7f);
        TiledSprite tiledSprite108 = new TiledSprite(-10.0f, -10.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite108.setCurrentTileIndex(gameIconEnum4.ordinal());
        tiledSprite108.setSize(120.0f, 120.0f);
        tiledSprite108.setAlpha(0.4f);
        tiledSprite107.attachChild(tiledSprite108);
        TiledSprite tiledSprite109 = new TiledSprite(40.0f, 60.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite109.setSize(9.0f, 9.0f);
        tiledSprite109.setCurrentTileIndex(3);
        tiledSprite107.attachChild(tiledSprite109);
        TiledSprite tiledSprite110 = new TiledSprite(40.0f, 60.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite110.setSize(9.0f, 9.0f);
        tiledSprite110.setCurrentTileIndex(3);
        tiledSprite107.attachChild(tiledSprite110);
        TiledSprite tiledSprite111 = new TiledSprite(15.0f, 80.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite111.setSize(9.0f, 9.0f);
        tiledSprite111.setCurrentTileIndex(1);
        tiledSprite107.attachChild(tiledSprite111);
        TiledSprite tiledSprite112 = new TiledSprite(15.0f, 80.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite112.setSize(9.0f, 9.0f);
        tiledSprite112.setCurrentTileIndex(1);
        tiledSprite107.attachChild(tiledSprite112);
        TiledSprite tiledSprite113 = new TiledSprite(10.0f, 25.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite113.setSize(9.0f, 9.0f);
        tiledSprite113.setCurrentTileIndex(5);
        tiledSprite107.attachChild(tiledSprite113);
        TiledSprite tiledSprite114 = new TiledSprite(10.0f, 25.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite114.setSize(9.0f, 9.0f);
        tiledSprite114.setCurrentTileIndex(5);
        tiledSprite107.attachChild(tiledSprite114);
        TiledSprite tiledSprite115 = new TiledSprite(90.0f, 20.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite115.setSize(9.0f, 9.0f);
        tiledSprite115.setCurrentTileIndex(0);
        tiledSprite107.attachChild(tiledSprite115);
        TiledSprite tiledSprite116 = new TiledSprite(90.0f, 20.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite116.setSize(9.0f, 9.0f);
        tiledSprite116.setCurrentTileIndex(0);
        tiledSprite107.attachChild(tiledSprite116);
        TiledSprite tiledSprite117 = new TiledSprite(20.0f, 20.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite117.setSize(9.0f, 9.0f);
        tiledSprite117.setCurrentTileIndex(4);
        tiledSprite107.attachChild(tiledSprite117);
        TiledSprite tiledSprite118 = new TiledSprite(20.0f, 20.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite118.setSize(9.0f, 9.0f);
        tiledSprite118.setCurrentTileIndex(4);
        tiledSprite107.attachChild(tiledSprite118);
        TiledSprite tiledSprite119 = new TiledSprite(50.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite119.setSize(9.0f, 9.0f);
        tiledSprite119.setCurrentTileIndex(2);
        tiledSprite107.attachChild(tiledSprite119);
        TiledSprite tiledSprite120 = new TiledSprite(50.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite120.setSize(9.0f, 9.0f);
        tiledSprite120.setCurrentTileIndex(2);
        tiledSprite107.attachChild(tiledSprite120);
        TiledSprite tiledSprite121 = new TiledSprite(85.0f, 50.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite121.setSize(9.0f, 9.0f);
        tiledSprite121.setCurrentTileIndex(6);
        tiledSprite107.attachChild(tiledSprite121);
        TiledSprite tiledSprite122 = new TiledSprite(85.0f, 50.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite122.setSize(9.0f, 9.0f);
        tiledSprite122.setCurrentTileIndex(6);
        tiledSprite107.attachChild(tiledSprite122);
        Game game35 = this.B;
        Text text24 = new Text(0.0f, 120.0f, game35.fonts.smallInfoFont, game35.getActivity().getString(R.string.new_random), vertexBufferObjectManager);
        text24.setX(50.0f - (text24.getWidthScaled() / 2.0f));
        this.startingPositionsSelector.addElement(1, tiledSprite107, text24);
        Game game36 = this.B;
        Selector selector12 = new Selector(game36, "SETUP", OptionID.TECH_PROGRESSION, vertexBufferObjectManager, game36.getActivity().getString(R.string.new_tech_progression), this.B.getActivity().getString(R.string.new_tech_progression_desc));
        this.techProgressionSelector = selector12;
        selector12.setPosition(690.0f, 355.0f);
        attachChild(this.techProgressionSelector);
        TiledSprite tiledSprite123 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite123.setCurrentTileIndex(2);
        tiledSprite123.setSize(100.0f, 100.0f);
        tiledSprite123.setAlpha(0.7f);
        TiledSprite tiledSprite124 = new TiledSprite(20.0f, 15.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite124.setCurrentTileIndex(2);
        tiledSprite124.setAlpha(0.8f);
        tiledSprite124.setSize(60.0f, 20.0f);
        tiledSprite123.attachChild(tiledSprite124);
        TiledSprite tiledSprite125 = new TiledSprite(20.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite125.setCurrentTileIndex(1);
        tiledSprite125.setAlpha(0.8f);
        tiledSprite125.setSize(60.0f, 20.0f);
        tiledSprite123.attachChild(tiledSprite125);
        TiledSprite tiledSprite126 = new TiledSprite(20.0f, 65.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite126.setCurrentTileIndex(2);
        tiledSprite126.setAlpha(0.8f);
        tiledSprite126.setSize(60.0f, 20.0f);
        tiledSprite123.attachChild(tiledSprite126);
        Game game37 = this.B;
        Font font3 = game37.fonts.smallInfoFont;
        String string2 = game37.getActivity().getString(R.string.new_one_tech);
        AutoWrap autoWrap = AutoWrap.WORDS;
        Text text25 = new Text(0.0f, 120.0f, font3, string2, new TextOptions(autoWrap, 110.0f, horizontalAlign), vertexBufferObjectManager);
        text25.setX(50.0f - (text25.getWidthScaled() / 2.0f));
        this.techProgressionSelector.addElement(Integer.valueOf(TechProgressionType.ONE_TECH_REQUIRED_PER_LEVEL.ordinal()), tiledSprite123, text25);
        TiledSprite tiledSprite127 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite127.setCurrentTileIndex(2);
        tiledSprite127.setSize(100.0f, 100.0f);
        tiledSprite127.setAlpha(0.7f);
        TiledSprite tiledSprite128 = new TiledSprite(20.0f, 15.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite128.setCurrentTileIndex(1);
        tiledSprite128.setAlpha(0.8f);
        tiledSprite128.setSize(60.0f, 20.0f);
        tiledSprite127.attachChild(tiledSprite128);
        TiledSprite tiledSprite129 = new TiledSprite(20.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite129.setCurrentTileIndex(1);
        tiledSprite129.setAlpha(0.8f);
        tiledSprite129.setSize(60.0f, 20.0f);
        tiledSprite127.attachChild(tiledSprite129);
        TiledSprite tiledSprite130 = new TiledSprite(20.0f, 65.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite130.setCurrentTileIndex(1);
        tiledSprite130.setAlpha(0.8f);
        tiledSprite130.setSize(60.0f, 20.0f);
        tiledSprite127.attachChild(tiledSprite130);
        Game game38 = this.B;
        Text text26 = new Text(0.0f, 120.0f, game38.fonts.smallInfoFont, game38.getActivity().getString(R.string.new_all_techs), new TextOptions(autoWrap, 110.0f, horizontalAlign), vertexBufferObjectManager);
        text26.setX(50.0f - (text26.getWidthScaled() / 2.0f));
        this.techProgressionSelector.addElement(Integer.valueOf(TechProgressionType.ALL_TECH_REQUIRED_PER_LEVEL.ordinal()), tiledSprite127, text26);
        TiledSprite tiledSprite131 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite131.setCurrentTileIndex(2);
        tiledSprite131.setSize(100.0f, 100.0f);
        tiledSprite131.setAlpha(0.7f);
        TiledSprite tiledSprite132 = new TiledSprite(20.0f, 15.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite132.setCurrentTileIndex(6);
        tiledSprite132.setAlpha(0.8f);
        tiledSprite132.setSize(60.0f, 20.0f);
        tiledSprite131.attachChild(tiledSprite132);
        TiledSprite tiledSprite133 = new TiledSprite(20.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite133.setCurrentTileIndex(1);
        tiledSprite133.setAlpha(0.8f);
        tiledSprite133.setSize(60.0f, 20.0f);
        tiledSprite131.attachChild(tiledSprite133);
        TiledSprite tiledSprite134 = new TiledSprite(20.0f, 65.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite134.setCurrentTileIndex(6);
        tiledSprite134.setAlpha(0.8f);
        tiledSprite134.setSize(60.0f, 20.0f);
        tiledSprite131.attachChild(tiledSprite134);
        Game game39 = this.B;
        Text text27 = new Text(0.0f, 120.0f, game39.fonts.smallInfoFont, game39.getActivity().getString(R.string.new_one_allowed_per_level), new TextOptions(autoWrap, 110.0f, horizontalAlign), vertexBufferObjectManager);
        text27.setX(50.0f - (text27.getWidthScaled() / 2.0f));
        this.techProgressionSelector.addElement(Integer.valueOf(TechProgressionType.ALLOW_ONLY_ONE_TECH_PER_LEVEL.ordinal()), tiledSprite131, text27);
        TiledSprite tiledSprite135 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite135.setCurrentTileIndex(2);
        tiledSprite135.setSize(100.0f, 100.0f);
        tiledSprite135.setAlpha(0.7f);
        TiledSprite tiledSprite136 = new TiledSprite(20.0f, 15.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite136.setCurrentTileIndex(6);
        tiledSprite136.setAlpha(0.8f);
        tiledSprite136.setSize(60.0f, 20.0f);
        tiledSprite135.attachChild(tiledSprite136);
        TiledSprite tiledSprite137 = new TiledSprite(20.0f, 40.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite137.setCurrentTileIndex(6);
        tiledSprite137.setAlpha(0.8f);
        tiledSprite137.setSize(60.0f, 20.0f);
        tiledSprite135.attachChild(tiledSprite137);
        TiledSprite tiledSprite138 = new TiledSprite(20.0f, 65.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite138.setCurrentTileIndex(1);
        tiledSprite138.setAlpha(0.8f);
        tiledSprite138.setSize(60.0f, 20.0f);
        tiledSprite135.attachChild(tiledSprite138);
        Text text28 = new Text(0.0f, 0.0f, this.B.fonts.menuFont, "?", new TextOptions(autoWrap, 110.0f, horizontalAlign), vertexBufferObjectManager);
        text28.setX(50.0f - (text28.getWidthScaled() / 2.0f));
        text28.setY(50.0f - (text28.getHeightScaled() / 2.0f));
        tiledSprite135.attachChild(text28);
        Game game40 = this.B;
        Text text29 = new Text(0.0f, 120.0f, game40.fonts.smallInfoFont, game40.getActivity().getString(R.string.new_one_random_allowed_per_level), new TextOptions(autoWrap, 110.0f, horizontalAlign), vertexBufferObjectManager);
        text29.setX(50.0f - (text29.getWidthScaled() / 2.0f));
        this.techProgressionSelector.addElement(Integer.valueOf(TechProgressionType.ALLOW_ONLY_ONE_RANDOM_TECH_PER_LEVEL.ordinal()), tiledSprite135, text29);
        Game game41 = this.B;
        Selector selector13 = new Selector(game41, "SETUP", OptionID.SYSTEM_OBJECT_PERCENT, vertexBufferObjectManager, game41.getActivity().getString(R.string.new_system_object_percent), this.B.getActivity().getString(R.string.new_system_object_percent_desc));
        this.systemObjectPercentSelector = selector13;
        selector13.setPosition(850.0f, 355.0f);
        attachChild(this.systemObjectPercentSelector);
        TiledSprite tiledSprite139 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite139.setCurrentTileIndex(2);
        tiledSprite139.setSize(100.0f, 100.0f);
        tiledSprite139.setAlpha(0.7f);
        TiledTextureRegion[] tiledTextureRegionArr = this.B.graphics.planetsTextureRegion;
        Climate climate = Climate.RADIATED;
        TiledSprite tiledSprite140 = new TiledSprite(30.0f, 30.0f, tiledTextureRegionArr[climate.getID()], vertexBufferObjectManager);
        tiledSprite140.setCurrentTileIndex(2);
        tiledSprite140.setSize(40.0f, 40.0f);
        tiledSprite139.attachChild(tiledSprite140);
        Game game42 = this.B;
        Text text30 = new Text(0.0f, 120.0f, game42.fonts.smallInfoFont, game42.getActivity().getString(R.string.percent_system_objects_less), vertexBufferObjectManager);
        text30.setX(50.0f - (text30.getWidthScaled() / 2.0f));
        this.systemObjectPercentSelector.addElement(0, tiledSprite139, text30);
        TiledSprite tiledSprite141 = new TiledSprite(8.0f, 10.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        tiledSprite141.setCurrentTileIndex(2);
        tiledSprite141.setSize(100.0f, 100.0f);
        tiledSprite141.setAlpha(0.7f);
        TiledSprite tiledSprite142 = new TiledSprite(50.0f, 15.0f, this.B.graphics.planetsTextureRegion[climate.getID()], vertexBufferObjectManager);
        tiledSprite142.setCurrentTileIndex(1);
        tiledSprite142.setSize(40.0f, 40.0f);
        tiledSprite141.attachChild(tiledSprite142);
        TiledSprite tiledSprite143 = new TiledSprite(15.0f, 50.0f, this.B.graphics.planetsTextureRegion[climate.getID()], vertexBufferObjectManager);
        tiledSprite143.setCurrentTileIndex(2);
        tiledSprite143.setSize(40.0f, 40.0f);
        tiledSprite141.attachChild(tiledSprite143);
        Game game43 = this.B;
        Text text31 = new Text(0.0f, 120.0f, game43.fonts.smallInfoFont, game43.getActivity().getString(R.string.percent_system_objects_normal), vertexBufferObjectManager);
        text31.setX(50.0f - (text31.getWidthScaled() / 2.0f));
        this.systemObjectPercentSelector.addElement(1, tiledSprite141, text31);
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.MENU.ordinal(), true);
        this.menuButton = H;
        s(H);
        TiledSprite H2 = H(getWidth() - 300.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.nextButton = H2;
        H2.setSize(300.0f, 75.0f);
        this.nextButton.setAlpha(0.7f);
        this.nextButton.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.nextButton.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(1.0f, 0.6f, 0.7f), new AlphaModifier(1.0f, 0.7f, 0.6f))));
        Sprite sprite7 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.nextButtonPressed = sprite7;
        sprite7.setVisible(false);
        this.nextButtonPressed.setSize(296.0f, 71.0f);
        this.nextButton.attachChild(this.nextButtonPressed);
        Game game44 = this.B;
        Text text32 = new Text(0.0f, 0.0f, game44.fonts.infoFont, game44.getActivity().getString(R.string.new_next_button), this.E, vertexBufferObjectManager);
        this.nextText = text32;
        text32.setColor(Color.BLACK);
        Text text33 = this.nextText;
        text33.setX(150.0f - (text33.getWidthScaled() / 2.0f));
        Text text34 = this.nextText;
        text34.setY(37.0f - (text34.getHeightScaled() / 2.0f));
        this.nextButton.attachChild(this.nextText);
        Sprite E3 = E(0.0f, 0.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, false);
        this.selectorBlackenedBackground = E3;
        E3.setSize(getWidth(), 720.0f);
        this.selectorBlackenedBackground.setAlpha(0.7f);
        this.selectorBlackenedBackground.setZIndex(100);
        Sprite sprite8 = new Sprite(0.0f, 160.0f, this.B.graphics.whiteTexture, vertexBufferObjectManager);
        sprite8.setSize(getWidth(), 400.0f);
        sprite8.setAlpha(0.95f);
        sprite8.setZIndex(100);
        this.selectorBlackenedBackground.attachChild(sprite8);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.i0
            @Override // java.lang.Runnable
            public final void run() {
                SetupScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
        setupScene();
        openScene();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
