package com.birdshel.Uciana.Overlays;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.ComponentIconEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Nebulas;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TroopUnloadOverlay extends ExtendedChildScene {
    private final Text buildingCount;
    private final TiledSprite buildingIcon;
    private final TiledSprite closeButton;
    private Colony colony;
    private final TiledSprite colonyBackground;
    private final Text displayText;
    private final TiledSprite empireBanner;
    private Fleet fleet;
    private final Nebulas nebulaBackground;
    private final PlanetSprite planetSprite;
    private final Text populationCount;
    private final TiledSprite populationIcon;
    private final TiledSprite pressed;
    private final Text transportCount;
    private final TiledSprite transportIcon;
    private final TiledSprite transportUnloadButton;
    private final TiledSprite transportUnloadTroopsButtonIcon;
    private final Text troopCount;
    private final Sprite troopPercentBar;
    private final Entity troopUnloadControl;
    private final Text warshipCount;
    private final TiledSprite warshipIcon;
    private final TiledSprite warshipUnloadButton;
    private final TiledSprite warshipUnloadTroopsButtonIcon;

    public TroopUnloadOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, true);
        this.pressed = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        Nebulas nebulas = new Nebulas(game, vertexBufferObjectManager);
        this.nebulaBackground = nebulas;
        attachChild(nebulas);
        TiledSprite x = x(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.colonyBackground = x;
        x.setAlpha(0.6f);
        x.setSize(getWidth(), 86.0f);
        this.empireBanner = x(0.0f, -7.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.displayText = u(120.0f, 20.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        PlanetSprite planetSprite = new PlanetSprite(game, vertexBufferObjectManager, 665, TypedValues.Motion.TYPE_ANIMATE_RELATIVE_TO);
        this.planetSprite = planetSprite;
        planetSprite.setPosition(360.0f, 390.0f);
        attachChild(planetSprite);
        this.populationCount = v(0.0f, 620.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager, true);
        TiledSprite w = w(0.0f, 610.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.POPULATION.ordinal(), true);
        this.populationIcon = w;
        w.setSize(40.0f, 40.0f);
        this.buildingCount = v(0.0f, 670.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager, true);
        TiledSprite w2 = w(0.0f, 655.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.BUILDINGS.ordinal(), true);
        this.buildingIcon = w2;
        w2.setSize(50.0f, 50.0f);
        Entity entity = new Entity();
        this.troopUnloadControl = entity;
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        sprite.setSize(500.0f, 500.0f);
        entity.attachChild(sprite);
        TiledSprite tiledSprite = new TiledSprite(50.0f, 50.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.INFANTRY.ordinal());
        entity.attachChild(tiledSprite);
        entity.attachChild(new Text(85.0f, 50.0f, game.fonts.infoFont, game.getActivity().getString(R.string.troop_unload_troops), vertexBufferObjectManager));
        Text text = new Text(0.0f, 50.0f, game.fonts.infoFont, this.E, vertexBufferObjectManager);
        this.troopCount = text;
        entity.attachChild(text);
        Sprite sprite2 = new Sprite(50.0f, 85.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
        sprite2.setSize(400.0f, 75.0f);
        entity.attachChild(sprite2);
        Sprite sprite3 = new Sprite(50.0f, 85.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        sprite3.setAlpha(0.7f);
        sprite3.setSize(400.0f, 75.0f);
        entity.attachChild(sprite3);
        Sprite sprite4 = new Sprite(50.0f, 85.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
        this.troopPercentBar = sprite4;
        sprite4.setHeight(75.0f);
        entity.attachChild(sprite4);
        TiledSprite tiledSprite2 = new TiledSprite(50.0f, 264.0f, game.graphics.shipsTextures[0], vertexBufferObjectManager);
        this.transportIcon = tiledSprite2;
        tiledSprite2.setSize(86.0f, 86.0f);
        entity.attachChild(tiledSprite2);
        Text text2 = new Text(145.0f, 0.0f, game.fonts.infoFont, this.E, vertexBufferObjectManager);
        this.transportCount = text2;
        entity.attachChild(text2);
        TiledSprite tiledSprite3 = new TiledSprite(330.0f, 264.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.transportUnloadButton = tiledSprite3;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK;
        tiledSprite3.setCurrentTileIndex(buttonsEnum.ordinal());
        entity.attachChild(tiledSprite3);
        TiledSprite tiledSprite4 = new TiledSprite(tiledSprite3.getX() + 10.0f, tiledSprite3.getY() - 7.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.transportUnloadTroopsButtonIcon = tiledSprite4;
        GameIconEnum gameIconEnum = GameIconEnum.UNLOAD;
        tiledSprite4.setCurrentTileIndex(gameIconEnum.ordinal());
        entity.attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(50.0f, 364.0f, game.graphics.shipsTextures[0], vertexBufferObjectManager);
        this.warshipIcon = tiledSprite5;
        tiledSprite5.setSize(86.0f, 86.0f);
        TiledSprite tiledSprite6 = new TiledSprite(85.0f, 55.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager);
        tiledSprite6.setCurrentTileIndex(ComponentIconEnum.TROOP_PODS.ordinal());
        tiledSprite6.setSize(40.0f, 40.0f);
        tiledSprite5.attachChild(tiledSprite6);
        entity.attachChild(tiledSprite5);
        Text text3 = new Text(145.0f, 0.0f, game.fonts.infoFont, this.E, vertexBufferObjectManager);
        this.warshipCount = text3;
        entity.attachChild(text3);
        TiledSprite tiledSprite7 = new TiledSprite(330.0f, 364.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.warshipUnloadButton = tiledSprite7;
        tiledSprite7.setCurrentTileIndex(buttonsEnum.ordinal());
        entity.attachChild(tiledSprite7);
        TiledSprite tiledSprite8 = new TiledSprite(tiledSprite7.getX() + 10.0f, tiledSprite7.getY() - 7.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.warshipUnloadTroopsButtonIcon = tiledSprite8;
        tiledSprite8.setCurrentTileIndex(gameIconEnum.ordinal());
        entity.attachChild(tiledSprite8);
        entity.setPosition(700.0f, 200.0f);
        attachChild(entity);
        TiledSprite w3 = w(getWidth() - 120.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w3;
        n(w3);
    }

    private void addTroopsAndRefresh() {
        this.colony.infLanded(5);
        setUnloadControl();
        this.C.systemScene.setFleetIcons(this.colony.getSystemID());
        this.C.systemScene.setActionButtons();
    }

    private void checkActionDownUnload(Point point) {
        checkPressed(point);
    }

    private void checkActionMoveUnload(Point point) {
        this.pressed.setVisible(false);
        checkPressed(point);
    }

    private void checkActionUpUnload(Point point) {
        this.pressed.setVisible(false);
        if (q(this.transportUnloadButton, point)) {
            transportUnloadButtonPressed();
        }
        if (q(this.warshipUnloadButton, point)) {
            warshipUnloadButtonPressed();
        }
    }

    private void checkPressed(Point point) {
        if (q(this.transportUnloadButton, point)) {
            this.pressed.setPosition(this.troopUnloadControl.getX() + this.transportUnloadButton.getX(), this.troopUnloadControl.getY() + this.transportUnloadButton.getY());
            this.pressed.setVisible(true);
        }
        if (q(this.warshipUnloadButton, point)) {
            this.pressed.setPosition(this.troopUnloadControl.getX() + this.warshipUnloadButton.getX(), this.troopUnloadControl.getY() + this.warshipUnloadButton.getY());
            this.pressed.setVisible(true);
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
        Game game2 = this.C;
        if (game2.colonies.getColonies(game2.getCurrentPlayer(), this.colony.getSystemID()).size() <= 1 || this.fleet.getCountOfEachType(true)[ShipType.TRANSPORT.id] == 0) {
            return;
        }
        this.C.systemScene.showSelectUnloadTroopsOverlay();
    }

    private void setHeader() {
        this.colonyBackground.setCurrentTileIndex(this.colony.getEmpireID());
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.colony.getEmpireID()));
        this.displayText.setText(this.C.getActivity().getString(R.string.troop_unload_onto, new Object[]{this.colony.getName()}));
        Text text = this.displayText;
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
    }

    private void setPlanet() {
        Planet planet = this.colony.getPlanet();
        this.planetSprite.setPlanet(planet, planet.getScale(this.C.planetScene), Moon.getScale(this.C.planetScene), true);
        Text text = this.populationCount;
        text.setText(this.colony.getPopulation() + " / " + this.colony.getPlanet().getMaxPopulation());
        float x = (this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - ((this.populationCount.getWidthScaled() / 2.0f) + 22.0f);
        this.populationCount.setX(x);
        this.populationIcon.setX(x + this.populationCount.getWidthScaled() + 5.0f);
        this.buildingCount.setText(Integer.toString(this.colony.getBuildings().size()));
        float x2 = (this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - ((this.buildingCount.getWidthScaled() / 2.0f) + 27.0f);
        this.buildingCount.setX(x2);
        this.buildingIcon.setX(x2 + this.buildingCount.getWidthScaled() + 5.0f);
    }

    private void setSpritesInvisible() {
        this.planetSprite.setSpritesInvisible();
    }

    private void setUnloadControl() {
        int[] countOfEachType = this.fleet.getCountOfEachType(true);
        ShipType shipType = ShipType.TRANSPORT;
        int i = countOfEachType[shipType.id];
        this.transportIcon.setTiledTextureRegion((ITiledTextureRegion) this.C.graphics.shipsTextures[this.colony.getEmpireID()]);
        this.transportIcon.setCurrentTileIndex(shipType.getIcon(this.colony.getEmpireID()));
        this.transportCount.setText(Integer.toString(i));
        this.transportCount.setY((this.transportIcon.getY() + 43.0f) - (this.transportCount.getHeightScaled() / 2.0f));
        float f2 = 0.4f;
        float f3 = (i == 0 || this.colony.getInfDivisions() == this.colony.getInfantryCapacity()) ? 0.4f : 1.0f;
        this.transportUnloadButton.setAlpha(f3);
        this.transportUnloadTroopsButtonIcon.setAlpha(f3);
        int i2 = 0;
        for (Ship ship : this.fleet.getShips()) {
            if (ship.isCombatShip()) {
                for (ShipComponent shipComponent : ship.getShipComponents()) {
                    if (shipComponent.getID() == ShipComponentID.TROOP_PODS) {
                        i2++;
                    }
                }
            }
        }
        this.warshipIcon.setTiledTextureRegion((ITiledTextureRegion) this.C.graphics.shipsTextures[this.colony.getEmpireID()]);
        this.warshipIcon.setCurrentTileIndex(ShipType.BATTLESHIP.getIcon(this.colony.getEmpireID(), 0));
        this.warshipCount.setText(Integer.toString(i2));
        this.warshipCount.setY((this.warshipIcon.getY() + 43.0f) - (this.warshipCount.getHeightScaled() / 2.0f));
        if (i2 != 0 && this.colony.getInfDivisions() != this.colony.getInfantryCapacity()) {
            f2 = 1.0f;
        }
        this.warshipUnloadButton.setAlpha(f2);
        this.warshipUnloadTroopsButtonIcon.setAlpha(f2);
        Text text = this.troopCount;
        text.setText(this.colony.getInfDivisions() + " / " + this.colony.getInfantryCapacity());
        Text text2 = this.troopCount;
        text2.setX(450.0f - text2.getWidthScaled());
        this.troopPercentBar.setWidth((((float) this.colony.getInfDivisions()) / ((float) this.colony.getInfantryCapacity())) * 400.0f);
    }

    private void transportUnloadButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        this.C.fleets.removeTransportShip(this.colony.getSystemID(), this.colony.getEmpireID());
        addTroopsAndRefresh();
    }

    private void warshipUnloadButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        for (Ship ship : this.fleet.getShips()) {
            if (ship.isCombatShip()) {
                for (ShipComponent shipComponent : ship.getShipComponents()) {
                    ShipComponentID id = shipComponent.getID();
                    ShipComponentID shipComponentID = ShipComponentID.TROOP_PODS;
                    if (id == shipComponentID) {
                        ship.removeShipComponent(shipComponentID);
                        addTroopsAndRefresh();
                        return;
                    }
                }
                continue;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        Point point2 = new Point(point.getX() - this.troopUnloadControl.getX(), point.getY() - this.troopUnloadControl.getY());
        if (i == 0) {
            checkActionDownUnload(point2);
        } else if (i != 1) {
            if (i != 2) {
                return;
            }
            checkActionMoveUnload(point2);
        } else {
            checkActionUpUnload(point2);
            if (q(this.closeButton, point)) {
                closeButtonPressed();
            }
        }
    }

    public void setOverlay(int i, int i2) {
        Colony colony = this.C.colonies.getColony(i, i2);
        this.colony = colony;
        this.fleet = this.C.fleets.getFleetInSystem(colony.getEmpireID(), this.colony.getSystemID());
        setSpritesInvisible();
        this.nebulaBackground.set(i);
        setHeader();
        setPlanet();
        setUnloadControl();
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
