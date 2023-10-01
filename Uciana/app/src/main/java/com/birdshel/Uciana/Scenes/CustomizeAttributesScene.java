package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Elements.PlayerCreationScene.RaceAttributesElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.Players.RaceAttribute;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.StarSystems.Nebulas;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class CustomizeAttributesScene extends ExtendedScene {
    private TiledSprite doneButton;
    private Sprite doneButtonPressed;
    private Text doneText;
    private PlanetSprite homeworldImage;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private Sprite pressSprite;
    private TiledSprite randomButton;
    private Sprite randomButtonPressed;
    private Text randomText;
    private final List<Sprite> raceAttributeBoxes = new ArrayList();
    private final List<TiledSprite> checkboxes = new ArrayList();
    private final List<RaceAttributesElement> raceAttributesElements = new ArrayList();

    public CustomizeAttributesScene(Game game) {
        this.B = game;
    }

    private void attributePressed(int i) {
        if (this.checkboxes.get(i).getAlpha() == 0.4f) {
            return;
        }
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        int currentTileIndex = this.checkboxes.get(i).getCurrentTileIndex();
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_ON;
        if (currentTileIndex == buttonsEnum.ordinal()) {
            this.checkboxes.get(i).setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
        } else {
            this.checkboxes.get(i).setCurrentTileIndex(buttonsEnum.ordinal());
        }
        disableEnableCheckBoxes();
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
        if (isClicked(this.randomButton, point)) {
            randomButtonPressed();
        }
        if (isClicked(this.doneButton, point)) {
            doneButtonPressed();
        }
        int i = 0;
        for (Sprite sprite : this.raceAttributeBoxes) {
            if (isClicked(sprite, point)) {
                attributePressed(i);
            }
            i++;
        }
    }

    private void checkPressed(Point point) {
        if (isClicked(this.randomButton, point)) {
            this.randomButtonPressed.setVisible(true);
            this.randomText.setColor(Color.WHITE);
        }
        if (isClicked(this.doneButton, point)) {
            this.doneButtonPressed.setVisible(true);
            this.doneText.setColor(Color.WHITE);
        }
        int i = 0;
        for (Sprite sprite : this.raceAttributeBoxes) {
            if (isClicked(sprite, point) && this.checkboxes.get(i).getAlpha() != 0.4f) {
                setPress(sprite);
            }
            i++;
        }
    }

    private void disableEnableCheckBoxes() {
        int i = 0;
        int i2 = 0;
        for (TiledSprite tiledSprite : this.checkboxes) {
            if (tiledSprite.getCurrentTileIndex() == ButtonsEnum.RADIO_ON.ordinal()) {
                i2++;
            }
        }
        float f2 = i2 >= 2 ? 0.4f : 1.0f;
        for (TiledSprite tiledSprite2 : this.checkboxes) {
            if (tiledSprite2.getCurrentTileIndex() == ButtonsEnum.RADIO_OFF.ordinal()) {
                tiledSprite2.setAlpha(f2);
                this.raceAttributesElements.get(i).setAlpha(f2);
                this.raceAttributeBoxes.get(i).setAlpha(f2);
            } else {
                tiledSprite2.setAlpha(1.0f);
                this.raceAttributesElements.get(i).setAlpha(1.0f);
                this.raceAttributeBoxes.get(i).setAlpha(1.0f);
            }
            i++;
        }
    }

    private void disablePress() {
        this.pressSprite.setVisible(false);
        this.randomButtonPressed.setVisible(false);
        Text text = this.randomText;
        Color color = Color.BLACK;
        text.setColor(color);
        this.doneButtonPressed.setVisible(false);
        this.doneText.setColor(color);
    }

    private void doneButtonPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.playerCreationScene.V(getSelectedAttributes());
        changeScene(this.B.playerCreationScene);
    }

    private List<RaceAttribute> getSelectedAttributes() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (TiledSprite tiledSprite : this.checkboxes) {
            if (tiledSprite.getCurrentTileIndex() == ButtonsEnum.RADIO_ON.ordinal()) {
                arrayList.add(RaceAttribute.values()[i]);
            }
            i++;
        }
        return arrayList;
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        detachChild(this.homeworldImage);
        this.B.planetSpritePool.push(this.homeworldImage);
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void randomButtonPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.playerCreationScene.W();
        changeScene(this.B.playerCreationScene);
    }

    private void setPress(Sprite sprite) {
        this.pressSprite.setVisible(true);
        this.pressSprite.setX(sprite.getX());
        this.pressSprite.setY(sprite.getY());
        this.pressSprite.setSize(sprite.getWidthScaled(), sprite.getHeightScaled());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof PlayerCreationScene) {
            this.B.playerCreationScene.U();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        changeScene(this.B.playerCreationScene);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
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

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        RaceAttribute[] values;
        super.createScene(vertexBufferObjectManager);
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
        E.setSize(getWidth(), 720.0f);
        E.setAlpha(0.7f);
        E.setZIndex(2);
        this.pressSprite = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        Game game = this.B;
        Text F = F(0.0f, 15.0f, game.fonts.menuFont, game.getActivity().getString(R.string.customize_header), this.E, vertexBufferObjectManager);
        F.setX((getWidth() / 2.0f) - (F.getWidthScaled() / 2.0f));
        F.setZIndex(2);
        float heightScaled = F.getHeightScaled() + 10.0f;
        Game game2 = this.B;
        Text F2 = F(0.0f, heightScaled, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.customize_description), this.E, vertexBufferObjectManager);
        F2.setX((getWidth() / 2.0f) - (F2.getWidthScaled() / 2.0f));
        F2.setZIndex(2);
        int i = 0;
        int i2 = 0;
        for (RaceAttribute raceAttribute : RaceAttribute.values()) {
            if (i == 2) {
                i2++;
                i = 0;
            }
            int i3 = 20;
            if (i == 1) {
                i3 = ((int) getWidth()) - 620;
            }
            float f2 = i3;
            int i4 = i2 * 110;
            Sprite sprite = new Sprite(f2, i4 + 86, this.B.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
            sprite.setSize(600.0f, 100.0f);
            sprite.setZIndex(2);
            attachChild(sprite);
            this.raceAttributeBoxes.add(sprite);
            TiledSprite tiledSprite = new TiledSprite(f2, i4 + 7 + 86, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
            tiledSprite.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
            tiledSprite.setZIndex(2);
            attachChild(tiledSprite);
            this.checkboxes.add(tiledSprite);
            RaceAttributesElement raceAttributesElement = new RaceAttributesElement(i3 + 120, i4 + 10 + 86, this.B, vertexBufferObjectManager);
            ArrayList arrayList = new ArrayList();
            arrayList.add(raceAttribute);
            raceAttributesElement.set(arrayList);
            raceAttributesElement.setZIndex(2);
            attachChild(raceAttributesElement);
            this.raceAttributesElements.add(raceAttributesElement);
            i++;
        }
        TiledSprite H = H(0.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.randomButton = H;
        H.setSize(400.0f, 75.0f);
        this.randomButton.setAlpha(0.7f);
        this.randomButton.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.randomButton.setZIndex(2);
        Sprite sprite2 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.randomButtonPressed = sprite2;
        sprite2.setSize(this.randomButton.getWidthScaled() - 4.0f, this.randomButton.getHeightScaled() - 4.0f);
        this.randomButtonPressed.setVisible(false);
        this.randomButtonPressed.setZIndex(2);
        this.randomButton.attachChild(this.randomButtonPressed);
        Game game3 = this.B;
        Text text = new Text(0.0f, 0.0f, game3.fonts.infoFont, game3.getActivity().getString(R.string.customize_random), this.E, vertexBufferObjectManager);
        this.randomText = text;
        Color color = Color.BLACK;
        text.setColor(color);
        this.randomText.setZIndex(2);
        Text text2 = this.randomText;
        text2.setX(200.0f - (text2.getWidthScaled() / 2.0f));
        Text text3 = this.randomText;
        text3.setY(37.0f - (text3.getHeightScaled() / 2.0f));
        this.randomButton.attachChild(this.randomText);
        TiledSprite H2 = H(getWidth() - 400.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.doneButton = H2;
        H2.setSize(400.0f, 75.0f);
        this.doneButton.setAlpha(0.7f);
        this.doneButton.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.doneButton.setZIndex(2);
        this.doneButton.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(1.0f, 0.6f, 0.7f), new AlphaModifier(1.0f, 0.7f, 0.6f))));
        Sprite sprite3 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.doneButtonPressed = sprite3;
        sprite3.setSize(this.doneButton.getWidthScaled() - 4.0f, this.doneButton.getHeightScaled() - 4.0f);
        this.doneButtonPressed.setVisible(false);
        this.doneButtonPressed.setZIndex(2);
        this.doneButton.attachChild(this.doneButtonPressed);
        Game game4 = this.B;
        Text text4 = new Text(0.0f, 0.0f, game4.fonts.infoFont, game4.getActivity().getString(R.string.customize_done), this.E, vertexBufferObjectManager);
        this.doneText = text4;
        text4.setColor(color);
        this.doneText.setZIndex(2);
        Text text5 = this.doneText;
        text5.setX(200.0f - (text5.getWidthScaled() / 2.0f));
        Text text6 = this.doneText;
        text6.setY(37.0f - (text6.getHeightScaled() / 2.0f));
        this.doneButton.attachChild(this.doneText);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.homeworldImage = planetSprite;
        planetSprite.setMoonRange(500, 500);
        this.homeworldImage.setPosition(getWidth() - 180.0f, 390.0f);
        this.homeworldImage.setZIndex(1);
        attachChild(this.homeworldImage);
        sortChildren();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.h
            {
                CustomizeAttributesScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                CustomizeAttributesScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void set(int i, List<RaceAttribute> list) {
        for (TiledSprite tiledSprite : this.checkboxes) {
            tiledSprite.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
        }
        for (RaceAttribute raceAttribute : list) {
            this.checkboxes.get(raceAttribute.ordinal()).setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        }
        disableEnableCheckBoxes();
        this.nebulas.setRandomSystem();
        boolean homeworldHasRings = Empires.homeworldHasRings(i);
        Planet buildHomeworld = new Planet.Builder().hasRing(homeworldHasRings).hasMoon(Empires.homeworldHasMoon(i)).moon(new Moon(Empires.getHomeworldMoonImage(i), Empires.getHomeworldMoonSize(i))).climate(Climate.values()[Empires.getHomeworldClimate(i)]).terraformedClimate(Climate.values()[Empires.getHomeworldClimate(i)]).buildHomeworld(EmpireType.HUMAN);
        this.homeworldImage.setSpritesInvisible();
        this.homeworldImage.setPlanet(buildHomeworld, buildHomeworld.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene));
        this.homeworldImage.setCityLights(Functions.random.nextInt(80) + 50, buildHomeworld.getCityLightIndex(), buildHomeworld.getScale(this.B.planetScene), buildHomeworld.getScale(this.B.planetScene), false);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
