package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.AI.PlanetAttack;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetAttackSummaryOverlay extends ExtendedChildScene {
    private final TiledSprite alertBackground;
    private final TiledSprite asteroidBeltSprite;
    private final TiledSprite buildingIcon;
    private final Text buildingLosses;
    private final Sprite infantryIcon;
    private final Text militaryLosses;
    private final Text name;
    private final Text noLosses;
    private final TiledSprite outpostIcon;
    private final PlanetSprite planetSprite;
    private final TiledSprite populationIcon;
    private final Text populationLosses;

    public PlanetAttackSummaryOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.G.setAlpha(0.6f);
        Sprite t = t(0.0f, 190.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 340.0f);
        t.setAlpha(0.9f);
        TiledSprite x = x(0.0f, 190.0f, game.graphics.empireColors, vertexBufferObjectManager, false);
        this.alertBackground = x;
        x.setSize(getWidth(), 340.0f);
        x.setAlpha(0.15f);
        PlanetSprite planetSprite = new PlanetSprite(game, vertexBufferObjectManager, WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE, WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE);
        this.planetSprite = planetSprite;
        planetSprite.setPosition(getWidth() / 2.0f, 225.0f);
        attachChild(planetSprite);
        TiledSprite x2 = x((getWidth() / 2.0f) - 20.0f, 180.0f, game.graphics.asteroidBeltsTexture, vertexBufferObjectManager, false);
        this.asteroidBeltSprite = x2;
        x2.setSize(50.0f, 130.0f);
        TiledSprite w = w((getWidth() / 2.0f) - 50.0f, 210.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.OUTPOST.ordinal(), false);
        this.outpostIcon = w;
        w.setSize(30.0f, 30.0f);
        this.name = u(0.0f, 300.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.populationIcon = w(0.0f, 380.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.POPULATION.ordinal(), true);
        this.populationLosses = u(0.0f, 480.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager);
        this.buildingIcon = w(0.0f, 380.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.BUILDINGS.ordinal(), true);
        this.buildingLosses = u(0.0f, 480.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager);
        this.infantryIcon = t(0.0f, 380.0f, game.graphics.troops[0], vertexBufferObjectManager, true);
        this.militaryLosses = u(0.0f, 480.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager);
        Text u = u(0.0f, 385.0f, game.fonts.infoFont, game.getActivity().getString(R.string.planet_attack_no_losses), this.F, vertexBufferObjectManager);
        this.noLosses = u;
        u.setX((getWidth() / 2.0f) - (u.getWidthScaled() / 2.0f));
    }

    private void checkActionUp() {
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
        this.C.attackScene.bombingSummaryClosed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp();
        }
    }

    public void setOverlay(PlanetAttack planetAttack) {
        int i;
        this.planetSprite.setVisible(false);
        this.alertBackground.setVisible(false);
        this.asteroidBeltSprite.setVisible(false);
        this.outpostIcon.setVisible(false);
        SystemObject systemObject = this.C.galaxy.getSystemObject(planetAttack.getSystemID(), planetAttack.getOrbit());
        if (systemObject.isPlanet()) {
            this.planetSprite.setPlanet((Planet) systemObject, 0.6f, 0.3f);
            this.planetSprite.setVisible(true);
        } else if (systemObject.isAsteroidBelt()) {
            this.asteroidBeltSprite.setCurrentTileIndex(((AsteroidBelt) systemObject).getImageIndex());
            this.asteroidBeltSprite.setVisible(true);
            this.outpostIcon.setVisible(true);
        } else if (systemObject.isGasGiant()) {
            this.planetSprite.setGasGiant((GasGiant) systemObject, 0.6f, 0.3f);
            this.planetSprite.setVisible(true);
        }
        if (planetAttack.hasColonyBeenDestroyed() || planetAttack.hasOutpostBeenDestroyed() || planetAttack.hasColonyBeenTaken()) {
            this.alertBackground.setVisible(true);
        }
        if (!planetAttack.hasColonyBeenDestroyed() && !systemObject.hasColony()) {
            this.name.setText(this.C.getActivity().getString(R.string.planet_attack_outpost, new Object[]{systemObject.getName(), planetAttack.getActionString()}));
        } else {
            String name = systemObject.getName();
            if (systemObject.hasColony()) {
                name = systemObject.getColony().getName();
            }
            this.name.setText(this.C.getActivity().getString(R.string.planet_attack_colony, new Object[]{name, planetAttack.getActionString()}));
        }
        this.name.setX((getWidth() / 2.0f) - (this.name.getWidthScaled() / 2.0f));
        int i2 = planetAttack.getPopulationLosses() > 0 ? 1 : 0;
        if (planetAttack.getBuildingLosses() > 0) {
            i2++;
        }
        if (planetAttack.getMilitaryLosses() > 0) {
            i2++;
        }
        this.populationIcon.setVisible(false);
        this.buildingIcon.setVisible(false);
        this.infantryIcon.setVisible(false);
        this.populationLosses.setVisible(false);
        this.buildingLosses.setVisible(false);
        this.militaryLosses.setVisible(false);
        this.noLosses.setVisible(false);
        if (i2 > 0) {
            int width = (int) (getWidth() / 2.0f);
            int[] iArr = {width};
            if (i2 == 2) {
                iArr = new int[]{width - 214, width + 212};
            }
            if (i2 == 3) {
                iArr = new int[]{width - 320, width, width + 320};
            }
            if (planetAttack.getPopulationLosses() > 0) {
                this.populationLosses.setText(this.C.getActivity().getString(R.string.planet_attack_population_loss, new Object[]{Integer.valueOf(planetAttack.getPopulationLosses())}));
                Text text = this.populationLosses;
                text.setX(iArr[0] - (text.getWidthScaled() / 2.0f));
                this.populationLosses.setVisible(true);
                this.populationIcon.setVisible(true);
                TiledSprite tiledSprite = this.populationIcon;
                tiledSprite.setX(iArr[0] - (tiledSprite.getWidthScaled() / 2.0f));
                i = 1;
            } else {
                i = 0;
            }
            if (planetAttack.getBuildingLosses() > 0) {
                this.buildingLosses.setText(this.C.getActivity().getString(R.string.planet_attack_buildings_destroyed, new Object[]{Integer.valueOf(planetAttack.getBuildingLosses())}));
                Text text2 = this.buildingLosses;
                text2.setX(iArr[i] - (text2.getWidthScaled() / 2.0f));
                this.buildingLosses.setVisible(true);
                this.buildingIcon.setVisible(true);
                TiledSprite tiledSprite2 = this.buildingIcon;
                tiledSprite2.setX(iArr[i] - (tiledSprite2.getWidthScaled() / 2.0f));
                i++;
            }
            if (planetAttack.getMilitaryLosses() > 0) {
                this.militaryLosses.setText(this.C.getActivity().getString(R.string.planet_attack_troops_lost, new Object[]{Integer.valueOf(planetAttack.getMilitaryLosses())}));
                Text text3 = this.militaryLosses;
                text3.setX(iArr[i] - (text3.getWidthScaled() / 2.0f));
                this.militaryLosses.setVisible(true);
                this.infantryIcon.setVisible(true);
                this.infantryIcon.setTiledTextureRegion(this.C.graphics.troops[planetAttack.getDefenderID()]);
                this.infantryIcon.setX(iArr[i] - (this.buildingIcon.getWidthScaled() / 2.0f));
            }
        } else if (planetAttack.hasColonyBeenTaken()) {
            this.militaryLosses.setText(this.C.getActivity().getString(R.string.planet_attack_no_troops_defending));
            this.militaryLosses.setX((getWidth() / 2.0f) - (this.militaryLosses.getWidthScaled() / 2.0f));
            this.militaryLosses.setVisible(true);
            this.infantryIcon.setVisible(true);
            this.infantryIcon.setTiledTextureRegion(this.C.graphics.troops[planetAttack.getDefenderID()]);
            this.infantryIcon.setX((getWidth() / 2.0f) - (this.buildingIcon.getWidthScaled() / 2.0f));
        } else if (planetAttack.hasOutpostBeenDestroyed() || planetAttack.hasColonyBeenDestroyed()) {
        } else {
            this.noLosses.setVisible(true);
        }
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
