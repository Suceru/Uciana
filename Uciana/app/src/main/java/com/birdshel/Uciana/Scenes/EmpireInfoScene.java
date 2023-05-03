package com.birdshel.Uciana.Scenes;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Elements.PlayerCreationScene.RaceAttributesElement;
import com.birdshel.Uciana.Elements.TechIcon;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.TechInfoMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EmpireInfoScene extends ExtendedScene {
    private VertexBufferObjectManager bufferManager;
    private Empire empire;
    private TiledSprite empireBackground;
    private TiledSprite empireBanner;
    private EmpireButton empireButton;
    private Text empireName;
    private TiledSprite galaxyButton;
    private PlanetSprite homeworld;
    private float lastY;
    private Nebulas nebulaBackground;
    private float pressedY;
    private RaceAttributesElement raceAttributes;
    private TiledSprite racesButton;
    private ScrollBarControl scrollBar;
    private Sprite selectPress;
    private Entity techList;
    private List<TechID> techs;
    private final List<Entity> techListElements = new ArrayList();
    private final ShipSprite[] shipIcons = new ShipSprite[ShipType.values().length];
    private final Text[] shipCountsText = new Text[ShipType.values().length];
    private boolean isScroll = false;
    private final int ITEM_SIZE = 55;
    private final ShipType[] shipTypes = {ShipType.DREADNOUGHT, ShipType.BATTLESHIP, ShipType.CRUISER, ShipType.DESTROYER, ShipType.COLONY, ShipType.CONSTRUCTION, ShipType.SCOUT, ShipType.TRANSPORT};

    public EmpireInfoScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(Point point, int i) {
        this.isScroll = false;
        if (point.getX() >= 400.0f || point.getY() <= 136.0f) {
            return;
        }
        this.pressedY = point.getY();
        this.lastY = point.getY();
        if (this.techs.size() > i) {
            setPress(i);
        }
    }

    private void checkActionMove(Point point, int i) {
        this.selectPress.setVisible(false);
        if (point.getX() < 400.0f) {
            if (point.getY() <= 136.0f || this.techs.size() <= i) {
                return;
            }
            if (this.techs.size() * 55 > 584) {
                if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                    this.isScroll = true;
                }
                float y = this.techList.getY() - (this.lastY - point.getY());
                float f2 = y <= 136.0f ? y : 136.0f;
                float size = ((this.techs.size() * 55) - 720) * (-1);
                if (f2 < size) {
                    f2 = size;
                }
                this.techList.setY(f2);
                this.lastY = point.getY();
                this.scrollBar.update(this.techList.getY());
            }
            if (this.isScroll) {
                return;
            }
            setPress(i);
        }
    }

    private void checkActionUp(Point point, int i) {
        this.selectPress.setVisible(false);
        if (this.isScroll) {
            return;
        }
        if (isClicked(this.empireButton, point)) {
            empireButtonPressed();
        }
        if (isClicked(this.racesButton, point)) {
            racesButtonPressed();
        }
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        }
        if (point.getX() >= 400.0f || point.getY() <= 136.0f) {
            return;
        }
        techPressed(i);
    }

    private void empireButtonPressed() {
        changeScene(this.B.raceScene);
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

    private void racesButtonPressed() {
        changeScene(this.B.racesScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setContactEmpireBanner() {
        this.empireBackground.setCurrentTileIndex(this.empire.id);
        this.empireName.setText(this.B.getActivity().getString(R.string.empire_info_report, new Object[]{this.empire.getName()}));
        this.empireName.setPosition(this.empireBanner.getX() + this.empireBanner.getWidthScaled() + 30.0f, 43.0f - (this.empireName.getHeightScaled() / 2.0f));
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.empire.id));
    }

    private void setFleetStrength() {
        ShipType[] shipTypeArr;
        ShipType[] shipTypeArr2;
        for (ShipType shipType : this.shipTypes) {
            this.shipIcons[shipType.id].setShipIcon(this.empire.id, shipType, 100.0f, 100.0f * shipType.getScale(), 1, true);
        }
        int[] iArr = new int[ShipType.values().length];
        for (Fleet fleet : this.B.fleets.getList()) {
            if (fleet.empireID == this.empire.id && fleet.isVisible(this.B.getCurrentEmpire())) {
                for (Ship ship : fleet.getShips()) {
                    int i = ship.getShipType().id;
                    iArr[i] = iArr[i] + 1;
                }
            }
        }
        for (ShipType shipType2 : this.shipTypes) {
            int i2 = shipType2.id;
            int i3 = iArr[i2];
            this.shipCountsText[i2].setText(Integer.toString(i3));
            Text[] textArr = this.shipCountsText;
            int i4 = shipType2.id;
            textArr[i4].setX((this.shipIcons[i4].getX() + 50.0f) - (this.shipCountsText[shipType2.id].getWidthScaled() / 2.0f));
            float f2 = 1.0f;
            if (i3 == 0) {
                f2 = 0.4f;
            }
            this.shipIcons[shipType2.id].setAlpha(f2);
            this.shipCountsText[shipType2.id].setAlpha(f2);
        }
    }

    private void setPress(int i) {
        this.selectPress.setY(this.techList.getY() + (i * 55));
        this.selectPress.setVisible(true);
    }

    private void setTechList() {
        int i;
        Empire currentEmpire = this.B.getCurrentEmpire();
        this.techs = this.empire.getTech().getFinishedTechs();
        w(this.techListElements, this.techList);
        int i2 = 0;
        for (TechID techID : this.techs) {
            Tech tech = this.empire.getTech().getTech(techID);
            float f2 = i2 * 55;
            Sprite sprite = new Sprite(0.0f, f2, this.B.graphics.colonyBackgroundTexture, this.bufferManager);
            sprite.setAlpha(0.8f);
            sprite.setSize(400.0f, 50.0f);
            this.techList.attachChild(sprite);
            this.techListElements.add(sprite);
            Sprite sprite2 = new Sprite(0.0f, f2, this.B.graphics.blackenedBackgroundTexture, this.bufferManager);
            sprite2.setSize(400.0f, 50.0f);
            sprite2.setAlpha(0.4f);
            this.techList.attachChild(sprite2);
            this.techListElements.add(sprite2);
            if (currentEmpire.hasTech(techID)) {
                Sprite sprite3 = new Sprite(0.0f, f2, this.B.graphics.popEmptyTexture, this.bufferManager);
                sprite3.setAlpha(0.6f);
                sprite3.setSize(400.0f, 50.0f);
                this.techList.attachChild(sprite3);
                this.techListElements.add(sprite3);
            }
            TechIcon techIcon = new TechIcon(currentEmpire.id, tech, this.B, this.bufferManager, 50);
            techIcon.setPosition(0.0f, f2);
            this.techList.attachChild(techIcon);
            this.techListElements.add(techIcon);
            Text text = new Text(50.0f, 0.0f, this.B.fonts.smallFont, tech.getName(), this.E, this.bufferManager);
            text.setY((i + 25) - (text.getHeightScaled() / 2.0f));
            this.techList.attachChild(text);
            this.techListElements.add(text);
            i2++;
        }
    }

    private void techPressed(int i) {
        if (this.techs.size() <= i) {
            return;
        }
        showMessage(new TechInfoMessage(this.techs.get(i)));
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void K(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof RaceScene) {
            this.B.raceScene.set(this.empire.id);
        } else if (extendedScene instanceof RacesScene) {
            this.B.racesScene.set();
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        changeScene(this.B.raceScene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        int y = ((int) (point.getY() - this.techList.getY())) / 55;
        if (i == 0) {
            checkActionDown(point, y);
        } else if (i == 1) {
            checkActionUp(point, y);
        } else if (i != 2) {
        } else {
            checkActionMove(point, y);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        ShipType[] shipTypeArr;
        float f2;
        super.createScene(vertexBufferObjectManager);
        this.bufferManager = vertexBufferObjectManager;
        Nebulas nebulas = new Nebulas(this.B, vertexBufferObjectManager);
        this.nebulaBackground = nebulas;
        attachChild(nebulas);
        PlanetSprite planetSprite = new PlanetSprite(this.B, vertexBufferObjectManager, 600, 600);
        this.homeworld = planetSprite;
        planetSprite.setPosition(1000.0f, 390.0f);
        attachChild(this.homeworld);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = E;
        E.setSize(400.0f, 50.0f);
        Entity entity = new Entity();
        this.techList = entity;
        attachChild(entity);
        this.techList.setPosition(0.0f, 136.0f);
        ScrollBarControl scrollBarControl = new ScrollBarControl(400.0f, 136.0f, 55, 584.0f, this.B, vertexBufferObjectManager);
        this.scrollBar = scrollBarControl;
        attachChild(scrollBarControl);
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        E(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        TiledSprite J = J(5.0f, 3.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireBackground = J;
        J.setSize(80.0f, 80.0f);
        this.empireBackground.setAlpha(0.75f);
        TiledSprite J2 = J(5.0f, 3.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.empireBanner = J2;
        J2.setSize(80.0f, 80.0f);
        this.empireName = G(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, true);
        this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        E(0.0f, 86.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(400.0f, 50.0f);
        H(0.0f, 86.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 2, true).setSize(400.0f, 50.0f);
        Game game = this.B;
        Text F = F(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.empire_info_technologies), this.E, vertexBufferObjectManager);
        F.setX(200.0f - (F.getWidthScaled() / 2.0f));
        F.setY(111.0f - (F.getHeightScaled() / 2.0f));
        E(420.0f, 86.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(400.0f, 50.0f);
        H(420.0f, 86.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 0, true).setSize(400.0f, 50.0f);
        Game game2 = this.B;
        Text F2 = F(0.0f, 0.0f, game2.fonts.infoFont, game2.getActivity().getString(R.string.empire_info_fleet), this.E, vertexBufferObjectManager);
        F2.setX(620.0f - (F2.getWidthScaled() / 2.0f));
        F2.setY(111.0f - (F2.getHeightScaled() / 2.0f));
        E(420.0f, 450.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(400.0f, 50.0f);
        H(420.0f, 450.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 5, true).setSize(400.0f, 50.0f);
        Game game3 = this.B;
        Text F3 = F(0.0f, 0.0f, game3.fonts.infoFont, game3.getActivity().getString(R.string.empire_info_perks), this.E, vertexBufferObjectManager);
        F3.setX(620.0f - (F3.getWidthScaled() / 2.0f));
        F3.setY(475.0f - (F3.getHeightScaled() / 2.0f));
        int i = 0;
        for (ShipType shipType : this.shipTypes) {
            float f3 = (i * 100) + TypedValues.Cycle.TYPE_EASING;
            if (i > 3) {
                f3 -= 400.0f;
                f2 = 286.0f;
            } else {
                f2 = 136.0f;
            }
            this.shipIcons[shipType.id] = new ShipSprite(this.B, vertexBufferObjectManager);
            this.shipIcons[shipType.id].setPosition(f3, f2);
            attachChild(this.shipIcons[shipType.id]);
            this.shipCountsText[shipType.id] = F(0.0f, f2 + 100.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
            i++;
        }
        RaceAttributesElement raceAttributesElement = new RaceAttributesElement(420.0f, 510.0f, this.B, vertexBufferObjectManager);
        this.raceAttributes = raceAttributesElement;
        attachChild(raceAttributesElement);
        EmpireButton empireButton = new EmpireButton(this.B, vertexBufferObjectManager);
        this.empireButton = empireButton;
        attachChild(empireButton);
        this.empireButton.setPosition(getWidth() - 360.0f, 0.0f);
        s(this.empireButton);
        TiledSprite H = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.RACES.ordinal(), true);
        this.racesButton = H;
        s(H);
        TiledSprite H2 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H2;
        s(H2);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(ExtendedScene extendedScene, Object obj) {
        extendedScene.getPoolElements();
        K(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    public void set(int i) {
        this.empire = this.B.empires.get(i);
        this.empireButton.set(i);
        this.nebulaBackground.set(this.empire.getHomeSystem());
        this.homeworld.setSpritesInvisible();
        Planet planet = (Planet) this.B.galaxy.getSystemObject(this.empire.getHomeSystem(), this.empire.getHomeWorldOrbit());
        this.homeworld.setPlanet(planet, planet.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene));
        setContactEmpireBanner();
        setTechList();
        setFleetStrength();
        this.raceAttributes.set(this.B.empires.get(i).getRaceAttributes());
        this.scrollBar.update(136.0f, this.techs.size());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
