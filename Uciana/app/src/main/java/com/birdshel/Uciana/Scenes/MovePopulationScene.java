package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.AI.AI;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.FilterType;
import com.birdshel.Uciana.Colonies.SortType;
import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.MovePopulationListElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.SortByOverlay;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarSystem;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MovePopulationScene extends ExtendedScene {
    private static final int ITEM_SIZE = 105;
    private static final int LIST_BOTTOM = 720;
    private static final int LIST_TOP = 180;
    private static final int MAX_LIST_SIZE = 540;
    private static final int sliderPosition = 500;
    private List<Colony> colonies;
    private Scene coloniesList;
    private TiledSprite colonyBackground;
    private int colonyListIndex;
    private Text colonyName;
    private Sprite colonySelectPress;
    private Sprite colonySelectSprite;
    private int empireID;
    private TiledSprite filterButton;
    private TiledSprite filterListIcon;
    private TiledSprite galaxyButton;
    private float lastY;
    private int listSize;
    private TiledSprite moveButton;
    private Text moveCostText;
    private TiledSprite moveCreditsIcon;
    private TiledSprite movePeopleButtonIcon;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private int orbit;
    private TiledSprite planetButton;
    private PlanetSprite planetSprite;
    private TiledSprite populationIcon;
    private Text populationText;
    private float pressedX;
    private float pressedY;
    private ScrollBarControl scrollBar;
    private Text selectColonyMessage;
    private int selectedColonyIndex;
    private Sprite sendBar;
    private Sprite sliderButton;
    private Sprite sliderGlowButton;
    private TiledSprite sliderPopulationIcon;
    private Text sliderText;
    private TiledSprite sortButton;
    private SortByOverlay sortByOverlay;
    private TiledSprite systemButton;
    private int systemID;
    private boolean sliderPressed = false;
    private final MovePopulationListElement[] elements = new MovePopulationListElement[7];
    private boolean isScroll = false;
    private SortType sortBy = SortType.OLDEST_TO_NEWEST;
    private FilterType filterType = FilterType.NONE;

    public MovePopulationScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(Point point) {
        if (isClicked(this.sliderButton, point)) {
            this.sliderPressed = true;
            this.pressedX = point.getX();
            return;
        }
        int y = ((int) (point.getY() - this.coloniesList.getY())) / 105;
        if (point.getY() > 180.0f) {
            this.pressedY = point.getY();
            this.lastY = point.getY();
        }
        if (this.isScroll || point.getY() <= 180.0f) {
            return;
        }
        checkPressed(y);
    }

    private void checkActionMove(Point point) {
        if (this.sliderPressed) {
            moveSlider(point);
            float x = this.pressedX - point.getX();
            if (x > 5.0f || x < -5.0f) {
                Game game = this.B;
                game.vibrate(game.SLIDER_VIBRATE);
            }
            this.pressedX = point.getX();
            return;
        }
        this.colonySelectPress.setVisible(false);
        int y = ((int) (point.getY() - this.coloniesList.getY())) / 105;
        if (point.getY() > 180.0f && (this.listSize * 105) + LIST_TOP > LIST_BOTTOM) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y2 = this.coloniesList.getY() - (this.lastY - point.getY());
            if (y2 > 180.0f) {
                y2 = 180.0f;
            }
            float f2 = ((this.listSize * 105) - LIST_BOTTOM) * (-1);
            if (y2 < f2) {
                y2 = f2;
            }
            this.coloniesList.setY(y2);
            this.lastY = point.getY();
            this.scrollBar.update(y2);
            int abs = Math.abs((int) ((y2 - 180.0f) / 105.0f));
            if (abs != this.colonyListIndex) {
                setColonyList(abs);
            }
        }
        if (this.isScroll || point.getY() <= 180.0f) {
            return;
        }
        checkPressed(y);
    }

    private void checkActionUp(Point point) {
        if (this.sliderPressed) {
            this.sliderPressed = false;
            return;
        }
        int y = ((int) (point.getY() - this.coloniesList.getY())) / 105;
        this.colonySelectPress.setVisible(false);
        if (this.isScroll) {
            this.isScroll = false;
            return;
        }
        if (point.getY() > 180.0f && y < this.listSize) {
            colonyPressed(y);
        }
        checkButtons(point);
    }

    private void checkButtons(Point point) {
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.systemButton, point)) {
            systemButtonPressed();
        } else if (isClicked(this.planetButton, point)) {
            planetButtonPressed();
        } else {
            if (isClicked(this.sortButton, point)) {
                sortButtonPressed();
            }
            if (isClicked(this.filterButton, point)) {
                filterButtonPressed();
            }
            if (isClicked(this.moveButton, point)) {
                movePopulationButtonPressed();
            }
        }
    }

    private void checkPressed(int i) {
        if (i < this.listSize) {
            this.colonySelectPress.setVisible(true);
            this.colonySelectPress.setY(i * 105);
        }
    }

    private void colonyPressed(int i) {
        this.selectedColonyIndex = i;
        setSelected();
        setSlider();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void filterButtonPressed() {
        int currentTileIndex = this.filterButton.getCurrentTileIndex();
        ButtonsEnum buttonsEnum = ButtonsEnum.GALAXY;
        if (currentTileIndex == buttonsEnum.ordinal()) {
            this.filterButton.setCurrentTileIndex(ButtonsEnum.SYSTEM.ordinal());
            this.filterType = FilterType.SAME_SYSTEM;
        } else {
            this.filterButton.setCurrentTileIndex(buttonsEnum.ordinal());
            this.filterType = FilterType.NONE;
        }
        this.selectedColonyIndex = -1;
        reloadColonies(this.sortBy);
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

    private int getMovePopulation() {
        return (int) ((this.B.colonies.getColony(this.systemID, this.orbit).getPopulation() - 1) * ((this.sendBar.getWidthScaled() / 4.0f) / 100.0f));
    }

    private Point getNextJump(Point point, Point point2, Point point3) {
        int distance = point2.getDistance(point3);
        int engineSpeed = GameData.empires.get(this.B.getCurrentPlayer()).getTech().getEngineSpeed() * GameData.galaxy.getDistanceConst();
        for (Blackhole blackhole : GameData.galaxy.getBlackholes()) {
            if (blackhole.isAffectedByTimeDilation(point2)) {
                engineSpeed /= 2;
            }
        }
        if (engineSpeed > distance) {
            return new Point(point3.getX(), point3.getY());
        }
        double atan2 = (float) Math.atan2(point.getY() - point3.getY(), point.getX() - point3.getX());
        float f2 = engineSpeed;
        return new Point(point2.getX() - (((float) Math.cos(atan2)) * f2), point2.getY() - (((float) Math.sin(atan2)) * f2));
    }

    private int getTurnsTo(Point point, Point point2) {
        Point point3 = new Point(point.getX(), point.getY());
        int i = 0;
        while (!point2.equals(point3)) {
            point3 = getNextJump(point, point3, point2);
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        detachChild(this.planetSprite);
        this.B.planetSpritePool.push(this.planetSprite);
        for (MovePopulationListElement movePopulationListElement : this.elements) {
            movePopulationListElement.releasePoolElements();
        }
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void loadColonies(boolean z) {
        this.colonySelectPress.setVisible(false);
        List<Colony> filter = this.B.colonies.filter(this.B.colonies.sort(this.empireID, this.sortBy), this.filterType, Integer.valueOf(this.systemID));
        this.colonies = new ArrayList();
        for (Colony colony : filter) {
            if (colony.getSystemID() != this.systemID || colony.getOrbit() != this.orbit) {
                this.colonies.add(colony);
            }
        }
        if (this.selectedColonyIndex == -1) {
            this.selectedColonyIndex = 0;
        }
        this.listSize = this.colonies.size();
        setColonyList(this.colonyListIndex);
        if (z) {
            resetScrollList();
        }
    }

    private void movePopulationButtonPressed() {
        Colony colony = this.colonies.get(this.selectedColonyIndex);
        int movePopulation = getMovePopulation();
        Colony colony2 = this.B.colonies.getColony(this.systemID, this.orbit);
        colony2.removePopulation(movePopulation);
        int turnsToSystem = getTurnsToSystem(colony.getSystemID());
        if (turnsToSystem == 0) {
            colony.addPopulation(movePopulation);
        } else {
            this.B.empires.get(this.empireID).addMigrants(colony.getSystemID(), colony.getOrbit(), movePopulation, turnsToSystem);
        }
        if (colony2.getPopulation() <= 2) {
            this.B.planetScene.refresh();
            changeScene(this.B.planetScene);
        } else {
            setColonyInfo();
            loadColonies(false);
            setSlider();
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void moveSlider(Point point) {
        float x = this.sliderButton.getX() - (this.pressedX - point.getX());
        if (x < 440.0f) {
            x = 440.0f;
        }
        if (x > 840.0f) {
            x = 840.0f;
        }
        this.sliderButton.setX(x);
        this.sliderGlowButton.setX(x);
        setSlider();
    }

    private void planetButtonPressed() {
        changeScene(this.B.planetScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void resetScrollList() {
        this.coloniesList.setY(180.0f);
        this.scrollBar.update(180.0f, this.listSize);
    }

    private void setColonyInfo() {
        Colony colony = this.B.colonies.getColony(this.systemID, this.orbit);
        this.colonyBackground.setCurrentTileIndex(colony.getEmpireID());
        Planet planet = colony.getPlanet();
        this.planetSprite.setPlanet(planet, planet.getScale(this.B.buildingsScene), Moon.getScale(this.B.buildingsScene));
        this.planetSprite.setPosition(50.0f, 43.0f);
        this.colonyName.setText(colony.getName());
        this.populationText.setText(this.B.getActivity().getString(R.string.system_colony_pop, new Object[]{Integer.valueOf(colony.getPopulation()), Integer.valueOf(planet.getMaxPopulation())}));
        this.populationIcon.setX(this.populationText.getX() + this.populationText.getWidthScaled() + 5.0f);
    }

    private void setColonyList(int i) {
        this.colonyListIndex = i;
        int i2 = 0;
        while (true) {
            MovePopulationListElement[] movePopulationListElementArr = this.elements;
            if (i2 >= movePopulationListElementArr.length) {
                return;
            }
            movePopulationListElementArr[i2].setVisible(false);
            int i3 = i + i2;
            if (i3 < this.colonies.size()) {
                this.elements[i2].setVisible(true);
                this.elements[i2].set(this.colonies.get(i3));
                this.elements[i2].setY(i3 * 105);
            }
            i2++;
        }
    }

    private void setFilterButton() {
        if (this.B.colonies.getColonies(this.empireID, this.systemID).size() > 1) {
            this.filterListIcon.setVisible(true);
            this.filterButton.setVisible(true);
            this.selectColonyMessage.setX(240.0f);
            return;
        }
        this.filterListIcon.setVisible(false);
        this.filterButton.setVisible(false);
        this.selectColonyMessage.setX(120.0f);
        this.filterType = FilterType.NONE;
    }

    private void setSelected() {
        this.colonySelectSprite.setVisible(true);
        this.colonySelectSprite.setY(this.selectedColonyIndex * 105);
        setSlider();
    }

    private void setSlider() {
        this.sendBar.setWidth((this.sliderButton.getX() + this.sliderButton.getWidthScaled()) - 500.0f);
        int movePopulation = getMovePopulation();
        this.sliderText.setText(this.B.getActivity().getString(R.string.move_population_move, new Object[]{Integer.valueOf(movePopulation)}));
        Text text = this.sliderText;
        text.setX((700.0f - (text.getWidthScaled() / 2.0f)) - 15.0f);
        this.sliderPopulationIcon.setX(this.sliderText.getX() + this.sliderText.getWidthScaled() + 5.0f);
        this.moveCostText.setText(Integer.toString(getTurnsToSystem(this.colonies.get(this.selectedColonyIndex).getSystemID()) * movePopulation * 1));
        this.moveCostText.setX((this.moveCreditsIcon.getX() - this.moveCostText.getWidthScaled()) - 5.0f);
        float f2 = movePopulation == 0 ? 0.4f : 1.0f;
        this.moveCreditsIcon.setAlpha(f2);
        this.moveButton.setAlpha(f2);
        this.movePeopleButtonIcon.setAlpha(f2);
        this.moveCostText.setAlpha(f2);
    }

    private void sortButtonPressed() {
        this.sortByOverlay.setOverlay(this.sortBy);
        setChildScene(this.sortByOverlay, false, false, true);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void systemButtonPressed() {
        changeScene(this.B.systemScene, Integer.valueOf(this.systemID));
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof PlanetScene) {
            this.B.planetScene.refresh();
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.loadSystem(((Integer) obj).intValue());
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        if (hasChildScene()) {
            this.sortByOverlay.back();
        } else {
            changeScene(this.B.planetScene);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        if (!this.sliderPressed) {
            super.checkInput(i, point);
        }
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
        this.nebulaBackground = new Entity();
        if (getWidth() == 1480.0f) {
            this.nebulaBackground.setX(100.0f);
        }
        attachChild(this.nebulaBackground);
        Scene scene = new Scene();
        this.coloniesList = scene;
        scene.setPosition(0.0f, 180.0f);
        int i = 0;
        this.coloniesList.setBackgroundEnabled(false);
        attachChild(this.coloniesList);
        Sprite sprite = new Sprite(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.colonySelectPress = sprite;
        sprite.setVisible(false);
        this.colonySelectPress.setSize(getWidth() - 10.0f, 100.0f);
        this.colonySelectPress.setAlpha(0.6f);
        this.coloniesList.attachChild(this.colonySelectPress);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.colonySelectSprite = sprite2;
        sprite2.setVisible(true);
        this.colonySelectSprite.setSize(getWidth() - 10.0f, 100.0f);
        this.coloniesList.attachChild(this.colonySelectSprite);
        while (true) {
            MovePopulationListElement[] movePopulationListElementArr = this.elements;
            if (i < movePopulationListElementArr.length) {
                movePopulationListElementArr[i] = new MovePopulationListElement(this.B, vertexBufferObjectManager, getWidth());
                this.coloniesList.attachChild(this.elements[i]);
                i++;
            } else {
                ScrollBarControl scrollBarControl = new ScrollBarControl(getWidth() - 10.0f, 180.0f, 105, 540.0f, this.B, vertexBufferObjectManager);
                this.scrollBar = scrollBarControl;
                attachChild(scrollBarControl);
                E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 175.0f);
                TiledSprite J = J(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
                this.colonyBackground = J;
                J.setAlpha(0.6f);
                this.colonyBackground.setSize(getWidth(), 175.0f);
                Text F = F(100.0f, 10.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
                this.colonyName = F;
                F.setZIndex(2);
                this.populationText = F(100.0f, 50.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
                ITiledTextureRegion iTiledTextureRegion = this.B.graphics.infoIconsTexture;
                InfoIconEnum infoIconEnum = InfoIconEnum.POPULATION;
                this.populationIcon = H(0.0f, 50.0f, iTiledTextureRegion, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
                this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
                float width = getWidth() - 120.0f;
                ITiledTextureRegion iTiledTextureRegion2 = this.B.graphics.buttonsTexture;
                ButtonsEnum buttonsEnum = ButtonsEnum.GALAXY;
                TiledSprite H = H(width, 0.0f, iTiledTextureRegion2, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
                this.galaxyButton = H;
                s(H);
                TiledSprite H2 = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.SYSTEM.ordinal(), true);
                this.systemButton = H2;
                s(H2);
                TiledSprite H3 = H(getWidth() - 360.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PLANET.ordinal(), true);
                this.planetButton = H3;
                s(H3);
                TiledSprite H4 = H(getWidth() - 120.0f, 86.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.BLANK.ordinal(), true);
                this.moveButton = H4;
                s(H4);
                this.movePeopleButtonIcon = H(getWidth() - 110.0f, 79.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.MOVE_PEOPLE.ordinal(), true);
                TiledSprite H5 = H(0.0f, 89.0f, this.B.graphics.sortButtonsTexture, vertexBufferObjectManager, 0, true);
                this.sortButton = H5;
                s(H5);
                TiledSprite H6 = H(120.0f, 89.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
                this.filterButton = H6;
                s(H6);
                TiledSprite H7 = H(135.0f, 110.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.MOVE.ordinal(), true);
                this.filterListIcon = H7;
                H7.setSize(25.0f, 25.0f);
                Game game = this.B;
                this.selectColonyMessage = F(240.0f, 145.0f, game.fonts.smallFont, game.getActivity().getString(R.string.move_population_select), this.E, vertexBufferObjectManager);
                E(500.0f, 15.0f, this.B.graphics.popEmptyTexture, vertexBufferObjectManager, true).setSize(400.0f, 60.0f);
                Sprite E = E(500.0f, 15.0f, this.B.graphics.farmingBarTexture, vertexBufferObjectManager, true);
                this.sendBar = E;
                E.setHeight(60.0f);
                this.sliderButton = E(440.0f, 75.0f, this.B.graphics.sliderTexture, vertexBufferObjectManager, true);
                Sprite E2 = E(440.0f, 75.0f, this.B.graphics.sliderTexture, vertexBufferObjectManager, true);
                this.sliderGlowButton = E2;
                blinkSprite(E2);
                this.sliderText = F(0.0f, 30.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
                this.sliderPopulationIcon = H(0.0f, 30.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
                this.moveCreditsIcon = H(getWidth() - 155.0f, 114.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.CREDITS.ordinal(), true);
                this.moveCostText = F(0.0f, 114.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
                Game game2 = this.B;
                this.sortByOverlay = new SortByOverlay(game2, vertexBufferObjectManager, game2.movePopulationScene);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.planetSprite = planetSprite;
        planetSprite.setMoonRange(100, 86);
        this.planetSprite.setZIndex(1);
        attachChild(this.planetSprite);
        for (MovePopulationListElement movePopulationListElement : this.elements) {
            movePopulationListElement.initialSet();
        }
        sortChildren();
    }

    public int getTurnsToSystem(int i) {
        List<Integer> route = AI.getRoute(this.B.getCurrentPlayer(), this.systemID, i);
        int i2 = this.systemID;
        int i3 = 0;
        for (Integer num : route) {
            i3 += getTurnsToSystem(i2, num.intValue());
            i2 = num.intValue();
        }
        return i3;
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.y
            @Override // java.lang.Runnable
            public final void run() {
                MovePopulationScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void reloadColonies(SortType sortType) {
        this.sortBy = sortType;
        this.B.empires.get(this.empireID).setSortBy(sortType);
        this.sortButton.setCurrentTileIndex(sortType.ordinal());
        this.colonyListIndex = 0;
        this.selectedColonyIndex = 0;
        setSelected();
        loadColonies(true);
    }

    public void set(int i, int i2) {
        this.systemID = i;
        this.orbit = i2;
        this.empireID = this.B.colonies.getColony(i, i2).getEmpireID();
        this.nebulas.set();
        setColonyInfo();
        this.selectedColonyIndex = -1;
        this.colonyListIndex = 0;
        setFilterButton();
        loadColonies(true);
        setSlider();
        setSelected();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }

    private int getTurnsToSystem(int i, int i2) {
        StarSystem starSystem = GameData.galaxy.getStarSystem(i);
        int turnsTo = getTurnsTo(starSystem.getPosition(), GameData.galaxy.getStarSystem(i2).getPosition());
        if (starSystem.hasWormholes()) {
            for (Point point : GameData.galaxy.getWormholes()) {
                if (point.getX() == starSystem.getID() && point.getY() == i2) {
                    return 1;
                }
                if (point.getY() == starSystem.getID() && point.getX() == i2) {
                    return 1;
                }
            }
        }
        return turnsTo;
    }
}
