package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.Migrants;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.StarSystems.Nebulas;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MigrantListOverlay extends ExtendedChildScene {
    private final int ITEM_SIZE;
    private final int LIST_BOTTOM;
    private final int LIST_TOP;
    private final VertexBufferObjectManager bufferManager;
    private final TiledSprite closeButton;
    private boolean isScroll;
    private float lastY;
    private final List<Entity> listElements;
    private final Scene listScene;
    private int listSize;
    private final Nebulas nebulaBackground;
    private float pressedY;
    private final ScrollBarControl scrollBar;

    public MigrantListOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        super(game, vertexBufferObjectManager, z);
        this.listElements = new ArrayList();
        this.isScroll = false;
        this.ITEM_SIZE = 105;
        this.LIST_TOP = 0;
        this.LIST_BOTTOM = 720;
        this.bufferManager = vertexBufferObjectManager;
        Nebulas nebulas = new Nebulas(game, vertexBufferObjectManager);
        this.nebulaBackground = nebulas;
        if (getWidth() == 1480.0f) {
            nebulas.setX(100.0f);
        }
        attachChild(nebulas);
        Scene scene = new Scene();
        this.listScene = scene;
        scene.setPosition(0.0f, 0.0f);
        scene.setBackgroundEnabled(false);
        attachChild(scene);
        ScrollBarControl scrollBarControl = new ScrollBarControl(getWidth() - 130.0f, 0.0f, 105, 720.0f, game, vertexBufferObjectManager);
        this.scrollBar = scrollBarControl;
        attachChild(scrollBarControl);
        TiledSprite w = w(getWidth() - 120.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w;
        n(w);
    }

    private void checkActionDown(Point point) {
        if (point.getY() > 0.0f) {
            this.pressedY = point.getY();
            this.lastY = point.getY();
        }
    }

    private void checkActionMove(Point point) {
        if (point.getY() <= 0.0f || this.listSize * 105 <= 720) {
            return;
        }
        if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
            this.isScroll = true;
        }
        float y = this.listScene.getY() - (this.lastY - point.getY());
        float f2 = y <= 0.0f ? y : 0.0f;
        float f3 = ((this.listSize * 105) - 720) * (-1);
        if (f2 < f3) {
            f2 = f3;
        }
        this.listScene.setY(f2);
        this.lastY = point.getY();
        this.scrollBar.update(f2);
    }

    private void checkActionUp(Point point) {
        if (this.isScroll) {
            this.isScroll = false;
        } else if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
    }

    private void closeButtonPressed() {
        this.C.galaxyScene.showButtons();
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
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

    public void setOverlay(int i) {
        List<Migrants> migrants = this.C.empires.get(i).getMigrants();
        this.nebulaBackground.set();
        setOverlay(migrants);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }

    public void setOverlay(int i, int i2) {
        List<Migrants> migrantsForPlanet = this.C.empires.get(this.C.galaxy.getSystemObject(i, i2).getOccupier()).getMigrantsForPlanet(i, i2);
        this.nebulaBackground.resetSprites();
        setOverlay(migrantsForPlanet);
    }

    private void setOverlay(List<Migrants> list) {
        p(this.listElements, this.listScene);
        this.listScene.setY(0.0f);
        this.listSize = list.size();
        int i = 0;
        for (Migrants migrants : list) {
            int i2 = i * 105;
            Sprite sprite = new Sprite(0.0f, i2, this.C.graphics.colonyBackgroundTexture, this.bufferManager);
            sprite.setAlpha(0.8f);
            sprite.setSize(getWidth() - 130.0f, 100.0f);
            this.listScene.attachChild(sprite);
            this.listElements.add(sprite);
            PlanetSprite planetSprite = new PlanetSprite(this.C, this.bufferManager, 100, 100);
            planetSprite.setPosition(100.0f, (i2 - 2) + 50);
            Planet planet = (Planet) this.C.galaxy.getSystemObject(migrants.getSystemID(), migrants.getOrbit());
            planetSprite.setPlanet(planet, planet.getScale(this.C.coloniesScene), Moon.getScale(this.C.coloniesScene));
            this.listScene.attachChild(planetSprite);
            this.listElements.add(planetSprite);
            String name = planet.getName();
            if (planet.hasColony()) {
                name = planet.getColony().getName();
            }
            float f2 = i2 + 40;
            Text text = new Text(300.0f, f2, this.C.fonts.infoFont, name, this.bufferManager);
            this.listScene.attachChild(text);
            this.listElements.add(text);
            Text text2 = new Text(600.0f, f2, this.C.fonts.infoFont, Integer.toString(migrants.getPopulationCount()), this.bufferManager);
            this.listScene.attachChild(text2);
            this.listElements.add(text2);
            TiledSprite tiledSprite = new TiledSprite(text2.getX() + text2.getWidthScaled() + 5.0f, f2, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite.setCurrentTileIndex(InfoIconEnum.POPULATION.ordinal());
            this.listScene.attachChild(tiledSprite);
            this.listElements.add(tiledSprite);
            Text text3 = new Text(900.0f, f2, this.C.fonts.infoFont, Integer.toString(migrants.getTurns()), this.bufferManager);
            this.listScene.attachChild(text3);
            this.listElements.add(text3);
            TiledSprite tiledSprite2 = new TiledSprite(text3.getX() + text3.getWidthScaled() + 5.0f, f2, this.C.graphics.infoIconsTexture, this.bufferManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.TURN.ordinal());
            this.listScene.attachChild(tiledSprite2);
            this.listElements.add(tiledSprite2);
            i++;
        }
        this.scrollBar.update(0.0f, list.size());
    }
}
