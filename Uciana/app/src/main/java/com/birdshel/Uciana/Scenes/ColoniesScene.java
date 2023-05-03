package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.SortType;
import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.ColonyListElement;
import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.Tutorials.PlanetListTutorial;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.SortByOverlay;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.Options;
import com.birdshel.Uciana.Resources.TutorialID;
import com.birdshel.Uciana.StarSystems.Galaxy;
import com.birdshel.Uciana.StarSystems.Nebulas;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColoniesScene extends ExtendedScene {
    private Sprite buyNowPress;
    private List<Colony> colonies;
    private EmpireButton coloniesButton;
    private Scene coloniesList;
    private Sprite colonySelectPress;
    private int empireID;
    private TiledSprite galaxyButton;
    private TiledSprite inhabitedWorldsButton;
    private float lastY;
    private int listIndex;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private float pressedY;
    private Sprite productionSelectPress;
    private ScrollBarControl scrollBar;
    private TiledSprite selectMode;
    private TiledSprite sortButton;
    private SortType sortBy;
    private SortByOverlay sortByOverlay;
    private TiledSprite uninhabitedWorldsButton;
    private final ColonyListElement[] elements = new ColonyListElement[8];
    private List<Planet> planets = new ArrayList();
    private boolean isScroll = false;
    private final int SIZE = 105;

    public ColoniesScene(Game game) {
        this.B = game;
    }

    private void aWorldButtonWasPressed() {
        loadPlanets(this.empireID);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.sortButton.setAlpha(0.4f);
        this.scrollBar.setX(665.0f);
    }

    private void buyNowPressed(int i) {
        Colony colony = this.colonies.get(i);
        if (colony.getManufacturing().showGreyProgressBar() || colony.getManufacturing().getCostToFinish() == 0) {
            return;
        }
        Game game = this.B;
        game.confirmOverlay.setOverlay(colony, game.coloniesScene, "buy");
        setChildScene(this.B.confirmOverlay, false, false, true);
        this.B.sounds.playBoxPressSound();
        Game game2 = this.B;
        game2.vibrate(game2.BUTTON_VIBRATE);
    }

    private void checkActionDown(int i, Point point) {
        int size;
        if (this.selectMode.getY() == this.coloniesButton.getY()) {
            size = this.colonies.size();
        } else {
            size = this.planets.size();
        }
        if (point.getX() < 1160.0f) {
            this.pressedY = point.getY();
            this.isScroll = false;
            this.lastY = point.getY();
        }
        if (this.isScroll || size <= i) {
            return;
        }
        if (point.getX() < 660.0f) {
            this.colonySelectPress.setY(this.coloniesList.getY() + (i * 105));
            this.colonySelectPress.setVisible(true);
        } else if (point.getX() > 660.0f && point.getX() < 1030.0f) {
            if (this.selectMode.getY() == this.coloniesButton.getY()) {
                this.productionSelectPress.setY(this.coloniesList.getY() + (i * 105));
                this.productionSelectPress.setVisible(true);
            }
        } else if (point.getX() <= 1030.0f || point.getX() >= 1160.0f || this.selectMode.getY() != this.coloniesButton.getY() || !this.elements[i - this.listIndex].isBuyNowVisible()) {
        } else {
            this.buyNowPress.setY(this.coloniesList.getY() + (i * 105));
            this.buyNowPress.setVisible(true);
        }
    }

    private void checkActionMove(int i, Point point) {
        int size;
        int i2;
        this.colonySelectPress.setVisible(false);
        this.productionSelectPress.setVisible(false);
        this.buyNowPress.setVisible(false);
        if (this.selectMode.getY() == this.coloniesButton.getY()) {
            size = this.colonies.size();
        } else {
            size = this.planets.size();
        }
        if (point.getX() < 1160.0f && (i2 = size * 105) > 720) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y = this.coloniesList.getY() - (this.lastY - point.getY());
            if (y > 0.0f) {
                y = 0.0f;
            }
            float f2 = (i2 - 720) * (-1);
            if (y < f2) {
                y = f2;
            }
            this.coloniesList.setY(y);
            this.lastY = point.getY();
            this.scrollBar.update(y);
            int abs = Math.abs((int) (y / 105.0f));
            if (abs != this.listIndex) {
                if (this.selectMode.getY() == this.coloniesButton.getY()) {
                    setColonyList(abs);
                } else {
                    setPlanetList(abs);
                }
            }
        }
        if (this.isScroll || size <= i) {
            return;
        }
        if (point.getX() < 660.0f) {
            this.colonySelectPress.setY(this.coloniesList.getY() + (i * 105));
            this.colonySelectPress.setVisible(true);
        } else if (point.getX() > 660.0f && point.getX() < 1030.0f) {
            if (this.selectMode.getY() == this.coloniesButton.getY()) {
                this.productionSelectPress.setY(this.coloniesList.getY() + (i * 105));
                this.productionSelectPress.setVisible(true);
            }
        } else if (point.getX() <= 1030.0f || point.getX() >= 1160.0f || this.selectMode.getY() != this.coloniesButton.getY() || !this.elements[i - this.listIndex].isBuyNowVisible()) {
        } else {
            this.buyNowPress.setY(this.coloniesList.getY() + (i * 105));
            this.buyNowPress.setVisible(true);
        }
    }

    private void checkActionUp(int i, Point point) {
        this.colonySelectPress.setVisible(false);
        this.productionSelectPress.setVisible(false);
        this.buyNowPress.setVisible(false);
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
            return;
        }
        if (isClicked(this.coloniesButton, point)) {
            coloniesButtonPressed();
        }
        if (isClicked(this.uninhabitedWorldsButton, point)) {
            this.selectMode.setY(this.uninhabitedWorldsButton.getY());
            aWorldButtonWasPressed();
        }
        if (isClicked(this.inhabitedWorldsButton, point)) {
            this.selectMode.setY(this.inhabitedWorldsButton.getY());
            aWorldButtonWasPressed();
        }
        if (isClicked(this.sortButton, point)) {
            sortButtonPressed();
        }
        if (point.getX() >= 1160.0f || this.isScroll) {
            return;
        }
        if (this.selectMode.getY() == this.coloniesButton.getY()) {
            coloniesListPressed(i, point);
        } else {
            planetsListPressed(i, point);
        }
    }

    private void coloniesButtonPressed() {
        loadColonies(this.empireID, 0);
        this.selectMode.setY(this.coloniesButton.getY());
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.sortButton.setAlpha(1.0f);
        this.scrollBar.setX(1150.0f);
    }

    private void coloniesListPressed(int i, Point point) {
        if (this.colonies.size() > i) {
            if (point.getX() < 660.0f) {
                colonyPressed(i);
            } else if (point.getX() > 660.0f && point.getX() < 1040.0f) {
                productionPressed(i);
            } else {
                buyNowPressed(i);
            }
        }
    }

    private void colonyPressed(int i) {
        changeScene(this.B.planetScene, Integer.valueOf(i));
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (ColonyListElement colonyListElement : this.elements) {
            colonyListElement.releasePoolElements();
        }
        extendedScene.getPoolElements();
        M(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void loadColonies(int i) {
        loadColonies(i, 0);
    }

    private void loadPlanets(int i) {
        this.coloniesButton.set(i);
        setSpritesInvisible();
        this.nebulas.set();
        this.colonies = this.B.colonies.sort(i, this.sortBy);
        setColonyList(0);
        if (this.selectMode.getY() == this.uninhabitedWorldsButton.getY()) {
            Galaxy galaxy = this.B.galaxy;
            this.planets = galaxy.sortPlanets(galaxy.getKnownUninhabitedPlanets(i));
        } else {
            List<Colony> coloniesNotInEmpire = this.B.colonies.getColoniesNotInEmpire(i);
            this.colonies = coloniesNotInEmpire;
            this.B.colonies.sort(coloniesNotInEmpire, SortType.A_TO_Z);
            this.planets.clear();
            for (Colony colony : this.colonies) {
                this.planets.add(colony.getPlanet());
            }
        }
        setPlanetList(0);
        resetScrollList(this.planets.size());
    }

    private void planetPressed(int i) {
        changeScene(this.B.planetScene, Integer.valueOf(i));
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void planetsListPressed(int i, Point point) {
        if (this.planets.size() <= i || point.getX() >= 660.0f) {
            return;
        }
        planetPressed(i);
    }

    private void productionPressed(int i) {
        changeScene(this.B.productionScene, Integer.valueOf(i));
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void reloadColonies() {
        float y = this.coloniesList.getY();
        loadColonies(this.empireID, this.listIndex);
        this.coloniesList.setY(y);
        this.scrollBar.update(y, this.colonies.size());
    }

    private void resetScrollList(int i) {
        this.coloniesList.setY(0.0f);
        this.scrollBar.update(0.0f, i);
    }

    private void setColonyList(int i) {
        this.listIndex = i;
        int i2 = 0;
        while (true) {
            ColonyListElement[] colonyListElementArr = this.elements;
            if (i2 >= colonyListElementArr.length) {
                return;
            }
            colonyListElementArr[i2].setVisible(false);
            int i3 = i + i2;
            if (i3 < this.colonies.size()) {
                this.elements[i2].setVisible(true);
                this.elements[i2].set(this.colonies.get(i3));
                this.elements[i2].setY(i3 * 105);
            }
            i2++;
        }
    }

    private void setPlanetList(int i) {
        this.listIndex = i;
        int i2 = 0;
        while (true) {
            ColonyListElement[] colonyListElementArr = this.elements;
            if (i2 >= colonyListElementArr.length) {
                return;
            }
            colonyListElementArr[i2].setVisible(false);
            int i3 = i + i2;
            if (i3 < this.planets.size()) {
                this.elements[i2].setVisible(true);
                if (this.selectMode.getY() == this.uninhabitedWorldsButton.getY()) {
                    this.elements[i2].set(this.empireID, this.planets.get(i3));
                } else {
                    this.elements[i2].set(this.empireID, this.planets.get(i3), this.colonies.get(i3));
                }
                this.elements[i2].setY(i3 * 105);
            }
            i2++;
        }
    }

    private void setSpritesInvisible() {
        this.colonySelectPress.setVisible(false);
        this.productionSelectPress.setVisible(false);
    }

    private void sortButtonPressed() {
        this.sortByOverlay.setOverlay(this.sortBy);
        setChildScene(this.sortByOverlay, false, false, true);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public void L() {
        if (this.selectMode.getY() == this.coloniesButton.getY()) {
            reloadColonies();
        } else {
            loadPlanets(this.empireID);
        }
    }

    protected void M(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof PlanetScene) {
            if (this.selectMode.getY() == this.coloniesButton.getY()) {
                this.B.planetScene.L(((Integer) obj).intValue(), this.colonies);
            } else {
                this.B.planetScene.M(((Integer) obj).intValue(), this.planets);
            }
        } else if (extendedScene instanceof ProductionScene) {
            this.B.productionScene.set(((Integer) obj).intValue(), this.colonies);
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        if (hasChildScene()) {
            this.sortByOverlay.back();
            this.B.confirmOverlay.back();
            return;
        }
        changeScene(this.B.galaxyScene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        int y = ((int) (point.getY() - this.coloniesList.getY())) / 105;
        if (i == 0) {
            checkActionDown(y, point);
        } else if (i == 1) {
            checkActionUp(y, point);
        } else if (i != 2) {
        } else {
            checkActionMove(y, point);
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
        Sprite E = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.colonySelectPress = E;
        E.setSize(655.0f, 100.0f);
        Sprite E2 = E(660.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.productionSelectPress = E2;
        E2.setSize(375.0f, 100.0f);
        Sprite E3 = E(1040.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.buyNowPress = E3;
        E3.setSize(110.0f, 100.0f);
        this.selectMode = H(getWidth() - 120.0f, 231.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), true);
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H;
        s(H);
        EmpireButton empireButton = new EmpireButton(this.B, vertexBufferObjectManager);
        this.coloniesButton = empireButton;
        attachChild(empireButton);
        this.coloniesButton.setPosition(getWidth() - 120.0f, 231.0f);
        s(this.coloniesButton);
        TiledSprite H2 = H(getWidth() - 120.0f, 317.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PLANET.ordinal(), true);
        this.uninhabitedWorldsButton = H2;
        s(H2);
        TiledSprite H3 = H(getWidth() - 120.0f, 403.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.RACES.ordinal(), true);
        this.inhabitedWorldsButton = H3;
        s(H3);
        TiledSprite H4 = H(getWidth() - 120.0f, 634.0f, this.B.graphics.sortButtonsTexture, vertexBufferObjectManager, 0, true);
        this.sortButton = H4;
        s(H4);
        Scene scene = new Scene();
        this.coloniesList = scene;
        scene.setPosition(0.0f, 0.0f);
        int i = 0;
        this.coloniesList.setBackgroundEnabled(false);
        attachChild(this.coloniesList);
        while (true) {
            ColonyListElement[] colonyListElementArr = this.elements;
            if (i < colonyListElementArr.length) {
                colonyListElementArr[i] = new ColonyListElement(this.B, vertexBufferObjectManager);
                this.coloniesList.attachChild(this.elements[i]);
                i++;
            } else {
                ScrollBarControl scrollBarControl = new ScrollBarControl(1150.0f, 0.0f, 105, 720.0f, this.B, vertexBufferObjectManager);
                this.scrollBar = scrollBarControl;
                attachChild(scrollBarControl);
                Game game = this.B;
                this.sortByOverlay = new SortByOverlay(game, vertexBufferObjectManager, game.coloniesScene);
                this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
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
        for (ColonyListElement colonyListElement : this.elements) {
            colonyListElement.initialSet();
        }
    }

    public void load(int i) {
        if (this.selectMode.getY() == this.coloniesButton.getY()) {
            loadColonies(i, 0);
        } else {
            loadPlanets(i);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.g
            {
                ColoniesScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ColoniesScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void reloadColoniesFromOverlay() {
        float y = this.coloniesList.getY();
        loadColonies(this.empireID, this.listIndex);
        this.coloniesList.setY(y);
        this.scrollBar.update(y, this.colonies.size());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }

    private void loadColonies(int i, int i2) {
        this.empireID = i;
        this.coloniesButton.set(i);
        setSpritesInvisible();
        this.nebulas.set();
        SortType sortBy = this.B.empires.get(i).getSortBy();
        this.sortBy = sortBy;
        this.sortButton.setCurrentTileIndex(sortBy.ordinal());
        this.colonies = this.B.colonies.sort(i, this.sortBy);
        setColonyList(i2);
        resetScrollList(this.colonies.size());
        Options options = Game.options;
        TutorialID tutorialID = TutorialID.PLANET_LIST;
        if (options.shouldTutorialBeShown(tutorialID)) {
            showMessage(new PlanetListTutorial());
            Game.options.markSeen(tutorialID);
        }
    }

    public void reloadColonies(SortType sortType) {
        this.sortBy = sortType;
        this.B.empires.get(this.empireID).setSortBy(sortType);
        this.sortButton.setCurrentTileIndex(sortType.ordinal());
        loadColonies(this.empireID);
    }
}
