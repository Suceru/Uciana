package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.SystemScene;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TroopUnloadSelectOverlay extends ExtendedChildScene {
    private final TiledSprite closeButton;
    private final Text displayText;
    private final TiledSprite empireBackground;
    private final TiledSprite empireBanner;
    private int empireID;
    private final List<PlanetSprite> planetSprites;
    private int systemID;
    private final List<TiledSprite> troopIcons;
    private final List<Text> troopTotals;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TroopUnloadSelectOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.planetSprites = new ArrayList();
        this.troopTotals = new ArrayList();
        this.troopIcons = new ArrayList();
        TiledSprite x = x(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireBackground = x;
        x.setAlpha(0.6f);
        x.setSize(getWidth(), 86.0f);
        this.empireBanner = x(0.0f, -7.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.displayText = u(120.0f, 20.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        for (int i = 0; i < 5; i++) {
            PlanetSprite planetSprite = new PlanetSprite(game, vertexBufferObjectManager, SystemScene.ORBIT_SIZE, 180);
            planetSprite.setPosition((SystemScene.ORBIT_SIZE * i) + 135 + 106, 330.0f);
            this.planetSprites.add(planetSprite);
            attachChild(planetSprite);
            this.troopTotals.add(v(0.0f, 205.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager, false));
            this.troopIcons.add(w(0.0f, 200.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.INFANTRY.ordinal(), false));
        }
        TiledSprite w = w(getWidth() - 120.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w;
        n(w);
    }

    private void checkActionUp(Point point) {
        int i = 0;
        for (PlanetSprite planetSprite : this.planetSprites) {
            if (planetSprite.getPlanetSprite().isVisible() && point.getX() > planetSprite.getX() && point.getX() < planetSprite.getX() + planetSprite.getWidthScaled() && point.getY() > planetSprite.getY() && point.getY() < planetSprite.getY() + planetSprite.getWidthScaled()) {
                planetPressed(i);
            }
            i++;
        }
        if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void planetPressed(int i) {
        this.C.sounds.playSystemObjectPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
        this.C.systemScene.showUnloadTroopsOverlay(i);
    }

    private void setHeader() {
        this.empireBackground.setCurrentTileIndex(this.empireID);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.empireID));
        this.displayText.setText(this.C.getActivity().getString(R.string.troop_select_unload));
        Text text = this.displayText;
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
    }

    private void setPlanets(List<Entity> list) {
        for (SystemObject systemObject : this.C.galaxy.getStarSystem(this.systemID).getSystemObjects()) {
            int orbit = systemObject.getOrbit();
            if (systemObject.hasColony() && systemObject.getOccupier() == this.empireID) {
                Planet planet = (Planet) systemObject;
                this.planetSprites.get(orbit).setPlanet(planet, planet.getScale(this.C.systemScene), Moon.getScale(this.C.systemScene));
                if (planet.hasMoon()) {
                    TiledSprite moonSprite = this.planetSprites.get(orbit).getMoonSprite();
                    moonSprite.setX(list.get(orbit).getX());
                    moonSprite.setY(list.get(orbit).getY());
                }
                Colony colony = planet.getColony();
                this.troopTotals.get(orbit).setText(colony.getInfDivisions() + " / " + colony.getInfantryCapacity());
                this.troopTotals.get(orbit).setVisible(true);
                float x = ((this.planetSprites.get(orbit).getX() + (this.planetSprites.get(orbit).getWidthScaled() / 2.0f)) - 17.0f) - (this.troopTotals.get(orbit).getWidthScaled() / 2.0f);
                this.troopTotals.get(orbit).setX(x);
                this.troopIcons.get(orbit).setX(x + this.troopTotals.get(orbit).getWidthScaled() + 5.0f);
                this.troopIcons.get(orbit).setVisible(true);
            }
        }
    }

    private void setSpritesInvisible() {
        for (PlanetSprite planetSprite : this.planetSprites) {
            planetSprite.setSpritesInvisible();
        }
        for (Text text : this.troopTotals) {
            text.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.troopIcons) {
            tiledSprite.setVisible(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp(point);
        }
    }

    public void setOverlay(int i, int i2, List<Entity> list) {
        this.empireID = i;
        this.systemID = i2;
        setSpritesInvisible();
        setHeader();
        setPlanets(list);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
