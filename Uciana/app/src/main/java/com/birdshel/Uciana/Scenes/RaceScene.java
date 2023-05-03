package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Events.EventDataFields;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.DiplomaticMessage;
import com.birdshel.Uciana.Messages.DiplomaticType;
import com.birdshel.Uciana.Messages.SpyNetworkNeededMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireMessages;
import com.birdshel.Uciana.Players.RelationStatus;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.StarSystems.Nebulas;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class RaceScene extends ExtendedScene {
    private Empire contactEmpire;
    private TiledSprite declareWarButton;
    private TiledSprite declareWarButton2;
    private Sprite declareWarButtonPressed;
    private Text declareWarText;
    private TiledSprite discussButton;
    private Sprite discussButtonPressed;
    private Text discussText;
    private TiledSprite empireBackground;
    private TiledSprite empireBanner;
    private TiledSprite empireInfoButton;
    private Sprite empireInfoButtonPressed;
    private Text empireName;
    private TiledSprite galaxyButton;
    private Text greetingsText;
    private PlanetSprite homeworld;
    private Text infoText;
    private Sprite levelIndicator;
    private Sprite levelIndicator2;
    private Text levelText;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private Sprite press;
    private Sprite raceAmbassador;
    private TiledSprite racesButton;
    private Sprite relationshipBar;
    private final List<Entity> treatyBackgrounds = new ArrayList();
    private final List<Entity> treatyIcons = new ArrayList();
    private final List<Entity> treatyNames = new ArrayList();
    private final List<Entity> removeTreatyButtons = new ArrayList();
    private final List<Entity> amountTexts = new ArrayList();
    private final List<Entity> amountIcons = new ArrayList();
    private final List<Treaty> treaties = new ArrayList();

    public RaceScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(Point point) {
        checkPresses(point);
    }

    private void checkActionMove(Point point) {
        disablePress();
        checkPresses(point);
    }

    private void checkActionUp(Point point) {
        disablePress();
        if (isClicked(this.discussButton, point)) {
            discussButtonPressed();
        }
        if (isClicked(this.declareWarButton, point)) {
            declareWarButtonPressed();
        }
        if (isClicked(this.empireInfoButton, point)) {
            empireInfoButtonPressed();
        } else if (A(this.empireInfoButton, point, 0.0f, 0.0f)) {
            showMessage(new SpyNetworkNeededMessage(this.contactEmpire.id));
        } else {
            if (isClicked(this.racesButton, point)) {
                racesButtonPressed();
            }
            if (isClicked(this.galaxyButton, point)) {
                galaxyButtonPressed();
            }
            int i = 0;
            for (Entity entity : this.removeTreatyButtons) {
                if (isClicked(entity, point)) {
                    removeTreatyButtonPressed(i);
                }
                i++;
            }
        }
    }

    private void checkPresses(Point point) {
        if (isClicked(this.discussButton, point)) {
            this.discussButtonPressed.setVisible(true);
            this.discussText.setColor(Color.WHITE);
        }
        if (isClicked(this.declareWarButton, point)) {
            this.declareWarButtonPressed.setVisible(true);
            this.declareWarText.setColor(Color.WHITE);
        }
        if (isClicked(this.empireInfoButton, point) && this.B.getCurrentEmpire().hasSpyNetwork(this.contactEmpire.id)) {
            this.empireInfoButtonPressed.setVisible(true);
            this.infoText.setColor(Color.WHITE);
        }
    }

    private void declareWarButtonPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.getCurrentEmpire().declareWar(this.contactEmpire.id);
        this.B.raceScene.set(this.contactEmpire.id);
    }

    private void disablePress() {
        this.press.setVisible(false);
        this.discussButtonPressed.setVisible(false);
        Text text = this.discussText;
        Color color = Color.BLACK;
        text.setColor(color);
        this.declareWarButtonPressed.setVisible(false);
        this.declareWarText.setColor(color);
        if (this.B.getCurrentEmpire().hasSpyNetwork(this.contactEmpire.id)) {
            this.empireInfoButtonPressed.setVisible(false);
            this.infoText.setColor(color);
        }
    }

    private void discussButtonPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.raceDiscussScene.set(this.contactEmpire.id);
        changeScene(this.B.raceDiscussScene);
    }

    private void empireInfoButtonPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.empireInfoScene.set(this.contactEmpire.id);
        changeScene(this.B.empireInfoScene);
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        detachChild(this.homeworld);
        this.B.planetSpritePool.push(this.homeworld);
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void racesButtonPressed() {
        changeScene(this.B.racesScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void removeTreatyButtonPressed(int i) {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.confirmOverlay.setOverlay(this.treaties.get(i), this.contactEmpire.id);
        setChildScene(this.B.confirmOverlay, false, false, true);
    }

    private void setContactEmpireBanner() {
        this.empireBackground.setCurrentTileIndex(this.contactEmpire.id);
        this.empireName.setText(this.contactEmpire.getName());
        Text text = this.empireName;
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.contactEmpire.id));
    }

    private void setTreaties(Empire empire) {
        C(this.treatyBackgrounds);
        C(this.treatyIcons);
        C(this.treatyNames);
        C(this.removeTreatyButtons);
        C(this.amountTexts);
        C(this.amountIcons);
        this.treaties.clear();
        int i = 0;
        for (Treaty treaty : empire.getTreaties().getTreaties(this.contactEmpire.id)) {
            if (treaty.showOnRaceScene()) {
                this.treaties.add(treaty);
                this.treatyBackgrounds.get(i).setVisible(true);
                TiledSprite tiledSprite = (TiledSprite) this.treatyIcons.get(i);
                tiledSprite.setCurrentTileIndex(treaty.getIconIndex());
                tiledSprite.setVisible(true);
                Text text = (Text) this.treatyNames.get(i);
                text.setText(treaty.getName());
                text.setVisible(true);
                this.removeTreatyButtons.get(i).setVisible(true);
                this.removeTreatyButtons.get(i).setAlpha(1.0f);
                Treaty treaty2 = Treaty.NON_AGGRESSION_PACT;
                if (treaty == treaty2 && empire.getTreaties().hasTreaty(this.contactEmpire.id, Treaty.ALLIANCE)) {
                    this.removeTreatyButtons.get(i).setAlpha(0.4f);
                }
                if (treaty != Treaty.ALLIANCE && treaty != treaty2) {
                    text.setY((this.treatyBackgrounds.get(i).getY() + 21.0f) - (text.getHeightScaled() / 2.0f));
                    this.amountTexts.get(i).setVisible(true);
                    TiledSprite tiledSprite2 = (TiledSprite) this.amountIcons.get(i);
                    tiledSprite2.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
                    tiledSprite2.setVisible(true);
                    int incomeFromTreaty = empire.getTreaties().getIncomeFromTreaty(this.contactEmpire.id, treaty);
                    if (treaty == Treaty.RESEARCH && incomeFromTreaty == 0) {
                        incomeFromTreaty = empire.getTreaties().getResearchPointsFromTreaties(this.contactEmpire.id);
                        tiledSprite2.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
                    }
                    String str = (incomeFromTreaty > 0 ? "+" : "") + Integer.toString(incomeFromTreaty);
                    Text text2 = (Text) this.amountTexts.get(i);
                    text2.setText(str);
                    text2.setX(this.treatyNames.get(i).getX());
                    text2.setY((this.treatyBackgrounds.get(i).getY() + 63.0f) - (text2.getHeightScaled() / 2.0f));
                    tiledSprite2.setX(text2.getX() + text2.getWidthScaled() + 10.0f);
                    tiledSprite2.setY(text2.getY() - 2.0f);
                } else {
                    text.setY((this.treatyBackgrounds.get(i).getY() + 43.0f) - (text.getHeightScaled() / 2.0f));
                }
                i++;
            }
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof RacesScene) {
            this.B.racesScene.set();
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (hasChildScene()) {
            this.B.confirmOverlay.back();
        } else if (t()) {
        } else {
            changeScene(this.B.racesScene);
        }
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
        super.createScene(vertexBufferObjectManager);
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        float f2 = 86.0f;
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        E(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        TiledSprite J = J(3.0f, 3.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireBackground = J;
        J.setSize(80.0f, 80.0f);
        this.empireBackground.setAlpha(0.75f);
        TiledSprite J2 = J(3.0f, 3.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.empireBanner = J2;
        J2.setSize(80.0f, 80.0f);
        this.empireName = G(100.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, true);
        this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        TiledSprite H = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.RACES.ordinal(), true);
        this.racesButton = H;
        s(H);
        TiledSprite H2 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H2;
        s(H2);
        Sprite E = E(0.0f, 30.0f, this.B.graphics.gradientTexture, vertexBufferObjectManager, true);
        this.relationshipBar = E;
        E.setSize(300.0f, 10.0f);
        Sprite E2 = E(0.0f, 15.0f, this.B.graphics.particleTexture, vertexBufferObjectManager, true);
        this.levelIndicator = E2;
        E2.setSize(10.0f, 40.0f);
        Sprite E3 = E(0.0f, 15.0f, this.B.graphics.particleTexture, vertexBufferObjectManager, true);
        this.levelIndicator2 = E3;
        E3.setSize(10.0f, 40.0f);
        this.levelText = G(0.0f, 60.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, true);
        int i = 0;
        while (i < 6) {
            int i2 = i > 2 ? 460 : 5;
            int i3 = (i * 90) + 120;
            if (i > 2) {
                i3 = ((i - 3) * 90) + 120;
            }
            float f3 = i3;
            Sprite E4 = E(i2, f3, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, false);
            E4.setSize(450.0f, f2);
            this.treatyBackgrounds.add(E4);
            TiledSprite J3 = J(i2 + 5, 5.0f + E4.getY(), this.B.graphics.gameIconsTexture, vertexBufferObjectManager, false);
            J3.setSize(75.0f, 75.0f);
            this.treatyIcons.add(J3);
            this.treatyNames.add(G(i2 + 85, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false));
            this.amountTexts.add(G(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false));
            TiledSprite J4 = J(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, false);
            J4.setSize(20.0f, 20.0f);
            this.amountIcons.add(J4);
            TiledSprite H3 = H(i2 + 325, f3, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), false);
            s(H3);
            this.removeTreatyButtons.add(H3);
            i++;
            f2 = 86.0f;
        }
        this.greetingsText = G(250.0f, 525.0f, this.B.fonts.infoFont, this.D, new TextOptions(AutoWrap.WORDS, 1020.0f), vertexBufferObjectManager, true);
        Sprite E5 = E(0.0f, 570.0f, this.B.graphics.whiteTexture, vertexBufferObjectManager, true);
        E5.setSize(980.0f, 150.0f);
        E5.setAlpha(0.5f);
        Sprite E6 = E(0.0f, 420.0f, this.B.graphics.raceAmbassadorTexture, vertexBufferObjectManager, true);
        this.raceAmbassador = E6;
        E6.setSize(240.0f, 300.0f);
        Sprite E7 = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.press = E7;
        E7.setSize(200.0f, 80.0f);
        TiledSprite H4 = H(250.0f, 610.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.discussButton = H4;
        H4.setSize(200.0f, 80.0f);
        this.discussButton.setAlpha(0.7f);
        Sprite sprite = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.discussButtonPressed = sprite;
        sprite.setVisible(false);
        this.discussButtonPressed.setSize(this.discussButton.getWidthScaled() - 4.0f, this.discussButton.getHeightScaled() - 4.0f);
        this.discussButton.attachChild(this.discussButtonPressed);
        Game game = this.B;
        Text F = F(0.0f, 0.0f, game.fonts.smallFont, game.getActivity().getString(R.string.race_discuss), this.E, vertexBufferObjectManager);
        this.discussText = F;
        F.setX(350.0f - (F.getWidthScaled() / 2.0f));
        Text text = this.discussText;
        text.setY(650.0f - (text.getHeightScaled() / 2.0f));
        Text text2 = this.discussText;
        Color color = Color.BLACK;
        text2.setColor(color);
        TiledSprite H5 = H(730.0f, 610.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.declareWarButton = H5;
        H5.setSize(200.0f, 80.0f);
        this.declareWarButton.setAlpha(0.7f);
        TiledSprite H6 = H(730.0f, 610.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 0, true);
        this.declareWarButton2 = H6;
        H6.setSize(200.0f, 80.0f);
        this.declareWarButton2.setAlpha(0.7f);
        Sprite sprite2 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.declareWarButtonPressed = sprite2;
        sprite2.setVisible(false);
        this.declareWarButtonPressed.setSize(this.declareWarButton2.getWidthScaled() - 4.0f, this.declareWarButton2.getHeightScaled() - 4.0f);
        this.declareWarButton2.attachChild(this.declareWarButtonPressed);
        Game game2 = this.B;
        Text F2 = F(0.0f, 0.0f, game2.fonts.smallFont, game2.getActivity().getString(R.string.race_declare_war), this.E, vertexBufferObjectManager);
        this.declareWarText = F2;
        F2.setX(830.0f - (F2.getWidthScaled() / 2.0f));
        Text text3 = this.declareWarText;
        text3.setY(650.0f - (text3.getHeightScaled() / 2.0f));
        this.declareWarText.setColor(color);
        TiledSprite H7 = H(490.0f, 610.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.empireInfoButton = H7;
        H7.setSize(200.0f, 80.0f);
        this.empireInfoButton.setAlpha(0.7f);
        Sprite sprite3 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.empireInfoButtonPressed = sprite3;
        sprite3.setVisible(false);
        this.empireInfoButtonPressed.setSize(this.empireInfoButton.getWidthScaled() - 4.0f, this.empireInfoButton.getHeightScaled() - 4.0f);
        this.empireInfoButton.attachChild(this.empireInfoButtonPressed);
        Game game3 = this.B;
        Text F3 = F(0.0f, 0.0f, game3.fonts.smallFont, game3.getActivity().getString(R.string.race_intel), this.E, vertexBufferObjectManager);
        this.infoText = F3;
        F3.setX(590.0f - (F3.getWidthScaled() / 2.0f));
        Text text4 = this.infoText;
        text4.setY(650.0f - (text4.getHeightScaled() / 2.0f));
        this.infoText.setColor(color);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.homeworld = planetSprite;
        planetSprite.setMoonRange(600, 600);
        this.homeworld.setPosition(1100.0f, 350.0f);
        attachChild(this.homeworld);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.f0
            {
                RaceScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                RaceScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void removeTreaty(Treaty treaty, int i) {
        this.B.getCurrentEmpire().getTreaties().removeTreaty(i, treaty);
        if (treaty.requiresBoth()) {
            this.contactEmpire.getTreaties().removeTreaty(this.B.getCurrentPlayer(), treaty);
        }
        set(this.contactEmpire.id);
    }

    public void set(int i) {
        Empire currentEmpire = this.B.getCurrentEmpire();
        this.contactEmpire = this.B.empires.get(i);
        this.B.getActivity().setLocale();
        this.raceAmbassador.setTiledTextureRegion(this.B.graphics.setAmbassadorTexture(this.contactEmpire.getRaceID(), this.B.getActivity()));
        this.nebulas.set(this.contactEmpire.getHomeSystem());
        this.homeworld.setSpritesInvisible();
        Planet planet = (Planet) this.B.galaxy.getSystemObject(this.contactEmpire.getHomeSystem(), this.contactEmpire.getHomeWorldOrbit());
        this.homeworld.setPlanet(planet, planet.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene));
        setContactEmpireBanner();
        boolean z = true;
        this.discussButton.setVisible(true);
        this.discussText.setVisible(true);
        if (this.B.gameSettings.isAlwaysAtWar()) {
            this.discussButton.setVisible(false);
            this.discussText.setVisible(false);
        }
        this.declareWarButton.setAlpha(0.7f);
        this.declareWarButton2.setAlpha(0.7f);
        this.declareWarText.setAlpha(1.0f);
        if (currentEmpire.isAtWar(i)) {
            this.declareWarButton.setAlpha(0.4f);
            this.declareWarButton2.setAlpha(0.3f);
            this.declareWarText.setAlpha(0.6f);
        }
        this.declareWarButton.setVisible(true);
        this.declareWarButton2.setVisible(true);
        this.declareWarText.setVisible(true);
        if (this.B.gameSettings.isAlwaysAtWar()) {
            this.declareWarButton.setVisible(false);
            this.declareWarButton2.setVisible(false);
            this.declareWarText.setVisible(false);
        }
        this.empireInfoButton.setAlpha(0.4f);
        this.infoText.setAlpha(0.6f);
        if (currentEmpire.hasSpyNetwork(i)) {
            this.empireInfoButton.setAlpha(0.7f);
            this.infoText.setAlpha(1.0f);
        }
        Game game = this.B;
        List<Event> events = game.events.getEvents(game.getCurrentPlayer(), EventType.DIPLOMATIC);
        if (!events.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Event event : events) {
                arrayList.add(new DiplomaticMessage(((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue(), DiplomaticType.values()[((Integer) event.getEventData(EventDataFields.DIPLOMATIC_TYPE)).intValue()]));
                this.B.events.removeEvent(event);
            }
            this.H.setOverlay(arrayList);
            setChildScene(this.H, false, false, true);
        }
        if (!this.contactEmpire.isHuman() && !this.B.gameSettings.isAlwaysAtWar()) {
            this.relationshipBar.setX(this.empireName.getX() + this.empireName.getWidthScaled() + 100.0f);
            int relationValue = this.contactEmpire.getRelationValue(currentEmpire.id);
            RelationStatus relationStatus = RelationStatus.getRelationStatus(relationValue);
            this.levelText.setText(relationStatus.getName());
            this.levelText.setX((this.relationshipBar.getX() + (this.relationshipBar.getWidthScaled() / 2.0f)) - (this.levelText.getWidthScaled() / 2.0f));
            float f2 = relationValue * 3;
            this.levelIndicator.setX(this.relationshipBar.getX() + f2);
            this.levelIndicator2.setX(this.relationshipBar.getX() + f2);
            this.greetingsText.setText(EmpireMessages.getDiplomaticSceneGreeting(this.contactEmpire.getRaceID(), relationStatus.getMessageIndex()));
        } else {
            if (this.B.gameSettings.isAlwaysAtWar()) {
                this.greetingsText.setText(EmpireMessages.getDiplomaticSceneGreeting(this.contactEmpire.getRaceID(), 0));
            } else {
                this.greetingsText.setText(this.B.getActivity().getString(R.string.human_greeting_message));
            }
            z = false;
        }
        Text text = this.greetingsText;
        text.setY(565.0f - text.getHeightScaled());
        this.relationshipBar.setVisible(z);
        this.levelText.setVisible(z);
        this.levelIndicator.setVisible(z);
        this.levelIndicator2.setVisible(z);
        setTreaties(currentEmpire);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
