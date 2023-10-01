package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.StarSystem;

import org.andengine.entity.Entity;
import org.andengine.entity.particle.BatchedSpriteParticleSystem;
import org.andengine.entity.particle.emitter.RectangleParticleEmitter;
import org.andengine.entity.particle.initializer.ExpireParticleInitializer;
import org.andengine.entity.particle.initializer.ScaleParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FleetListElement extends Entity {
    private final Text fleetText;
    private final Game game;
    private final BatchedSpriteParticleSystem particleSystem;
    private final VelocityParticleInitializer particleVelocity;
    private final float screenWidth;
    private final TiledSprite shipListButtonPress;
    private AnimatedSprite star;
    private final Text unexplored;
    private final int ITEM_SIZE = WeaponStats.NOVA_BOMB_MAX_DAMAGE;
    private final List<ShipSprite> shipIcons = new ArrayList();
    private final Text[] shipCountTexts = new Text[8];
    private final TiledSprite[] shipTypeIcons = new TiledSprite[8];
    private final Text[] autoCountTexts = new Text[8];
    private final TiledSprite[] autoCountIcons = new TiledSprite[8];
    private final List<PlanetSprite> planetSprites = new ArrayList();
    private final TiledSprite[] asteroidBelts = new TiledSprite[5];
    private final TiledSprite[] empireBars = new TiledSprite[5];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Elements.FleetListElement$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1363a;

        static {
            int[] iArr = new int[SystemObjectType.values().length];
            f1363a = iArr;
            try {
                iArr[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1363a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1363a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public FleetListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        this.game = game;
        this.screenWidth = f2;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        sprite.setAlpha(0.7f);
        sprite.setSize(f2 - 135.0f, 145.0f);
        attachChild(sprite);
        RectangleParticleEmitter rectangleParticleEmitter = new RectangleParticleEmitter(640.0f, 75.0f, 1080.0f, 150.0f);
        rectangleParticleEmitter.setCenterX(250.0f);
        BatchedSpriteParticleSystem batchedSpriteParticleSystem = new BatchedSpriteParticleSystem(rectangleParticleEmitter, 50.0f, 100.0f, 400, game.graphics.particleTexture, vertexBufferObjectManager);
        this.particleSystem = batchedSpriteParticleSystem;
        VelocityParticleInitializer velocityParticleInitializer = new VelocityParticleInitializer(0.0f, 0.0f, 0.0f, 0.0f);
        this.particleVelocity = velocityParticleInitializer;
        batchedSpriteParticleSystem.addParticleInitializer(velocityParticleInitializer);
        batchedSpriteParticleSystem.addParticleInitializer(new ExpireParticleInitializer(2.0f));
        batchedSpriteParticleSystem.addParticleInitializer(new ScaleParticleInitializer(3.0f, 5.0f));
        batchedSpriteParticleSystem.addParticleModifier(new AlphaParticleModifier(1.0f, 3.0f, 0.9f, 0.0f));
        batchedSpriteParticleSystem.setContainerEnabled(true);
        batchedSpriteParticleSystem.setContainer(0.0f, 2.0f, 1125.0f, 141.0f);
        attachChild(batchedSpriteParticleSystem);
        Text text = new Text(0.0f, 10.0f, game.fonts.smallFont, wrap, new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);
        this.fleetText = text;
        attachChild(text);
        int i = 0;
        for (int i2 = 8; i < i2; i2 = 8) {
            int i3 = i;

            Text text2 = new Text(0.0f, 0.0f, game.fonts.smallFont, "##########", textOptions, vertexBufferObjectManager);
            this.shipCountTexts[i3] = text2;
            attachChild(text2);
            TiledSprite tiledSprite = new TiledSprite(0.0f, 45.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite.setZIndex(2);
            this.shipTypeIcons[i3] = tiledSprite;
            attachChild(tiledSprite);
            Text text3 = new Text(0.0f, 120.0f, game.fonts.smallFont, "##########", textOptions, vertexBufferObjectManager);
            text3.setZIndex(2);
            this.autoCountTexts[i3] = text3;
            attachChild(text3);
            TiledSprite tiledSprite2 = new TiledSprite(0.0f, 120.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.ON.ordinal());
            tiledSprite2.setSize(20.0f, 20.0f);
            tiledSprite2.setZIndex(2);
            this.autoCountIcons[i3] = tiledSprite2;
            attachChild(tiledSprite2);
            i = i3 + 1;
        }
        for (int i4 = 0; i4 < 5; i4++) {
            TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.asteroidBeltsTexture, vertexBufferObjectManager);
            tiledSprite3.setSize(15.0f, 50.0f);
            this.asteroidBelts[i4] = tiledSprite3;
            attachChild(tiledSprite3);
            TiledSprite tiledSprite4 = new TiledSprite(0.0f, 48.0f, game.graphics.empireColors, vertexBufferObjectManager);
            tiledSprite4.setSize(50.0f, 5.0f);
            tiledSprite4.setZIndex(2);
            this.empireBars[i4] = tiledSprite4;
            attachChild(tiledSprite4);
        }
        Text text4 = new Text(f2 - 385.0f, 0.0f, game.fonts.smallFont, game.getActivity().getString(R.string.fleets_unexplored), vertexBufferObjectManager);
        this.unexplored = text4;
        text4.setY((text4.getY() + 25.0f) - (text4.getHeightScaled() / 2.0f));
        attachChild(text4);
        float f3 = f2 - 255.0f;
        TiledSprite tiledSprite5 = new TiledSprite(f3, 59.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.shipListButtonPress = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(ButtonsEnum.PRESSED.ordinal());
        attachChild(tiledSprite5);
        TiledSprite tiledSprite6 = new TiledSprite(f3, 59.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        tiledSprite6.setCurrentTileIndex(ButtonsEnum.SHIP_SELECT.ordinal());
        attachChild(tiledSprite6);
    }

    public void getPoolElements() {
        for (int i = 0; i < 8; i++) {
            ShipSprite shipSprite = this.game.shipSpritePool.get();
            shipSprite.setY(45.0f);
            shipSprite.setZIndex(1);
            this.shipIcons.add(shipSprite);
            attachChild(shipSprite);
        }
        AnimatedSprite animatedSprite = this.game.starSpritePool.get();
        this.star = animatedSprite;
        animatedSprite.setPosition(this.screenWidth - 435.0f, 0.0f);
        this.star.setSize(50.0f, 50.0f);
        attachChild(this.star);
        sortChildren();
    }

    public void releasePoolElements() {
        for (ShipSprite shipSprite : this.shipIcons) {
            detachChild(shipSprite);
            this.game.shipSpritePool.push(shipSprite);
        }
        this.shipIcons.clear();
        for (PlanetSprite planetSprite : this.planetSprites) {
            detachChild(planetSprite);
            this.game.planetSpritePool.push(planetSprite);
        }
        this.planetSprites.clear();
        detachChild(this.star);
        this.game.starSpritePool.push(this.star);
    }

    public void set(Fleet fleet) {
        String string;
        int i;
        int i2;
        int i3 = 0;
        this.particleSystem.setVisible(false);
        for (int i4 = 0; i4 < 8; i4++) {
            this.shipCountTexts[i4].setVisible(false);
            this.shipIcons.get(i4).setVisible(false);
            this.shipTypeIcons[i4].setVisible(false);
            this.autoCountTexts[i4].setVisible(false);
            this.autoCountIcons[i4].setVisible(false);
        }
        for (int i5 = 0; i5 < 5; i5++) {
            this.asteroidBelts[i5].setVisible(false);
            this.empireBars[i5].setVisible(false);
        }
        this.unexplored.setVisible(false);
        this.shipListButtonPress.setVisible(false);
        float f2 = 0.0f;
        if (fleet.isMoving()) {
            this.particleSystem.setVisible(true);
            int engineSpeed = this.game.empires.get(fleet.empireID).getTech().getEngineSpeed() - 2;
            if (fleet.getDirection() == 1) {
                if (this.particleVelocity.getMaxVelocityX() > 0.0f) {
                    this.particleSystem.clearParticles();
                }
                this.particleVelocity.setVelocityX(engineSpeed * (-100), i2 - 100);
            } else {
                if (this.particleVelocity.getMaxVelocityX() < 0.0f) {
                    this.particleSystem.clearParticles();
                }
                this.particleVelocity.setVelocityX(engineSpeed * 100, i + 100);
            }
        }
        if (fleet.getShips().size() != 1) {
            string = this.game.getActivity().getString(R.string.fleets_ships_summary, new Object[]{Integer.valueOf(fleet.getShips().size()), fleet.getDestinationText()});
        } else {
            string = this.game.getActivity().getString(R.string.fleets_ship_summary, new Object[]{fleet.getDestinationText()});
        }
        this.fleetText.setText(string);
        int[] countOfEachType = fleet.getCountOfEachType(true);
        int i6 = 0;
        for (int i7 = 7; i7 > -1; i7--) {
            ShipType shipType = ShipType.getShipType(i7);
            if (countOfEachType[shipType.id] != 0) {
                this.shipCountTexts[i6].setVisible(true);
                this.shipCountTexts[i6].setText(Integer.toString(countOfEachType[shipType.id]));
                this.shipCountTexts[i6].setX(f2);
                Text[] textArr = this.shipCountTexts;
                textArr[i6].setY(95.0f - (textArr[i6].getHeightScaled() / 2.0f));
                float widthScaled = f2 + this.shipCountTexts[i6].getWidthScaled() + 5.0f;
                this.shipIcons.get(i6).setVisible(true);
                this.shipIcons.get(i6).setShipIcon(fleet.empireID, shipType, fleet.getHullDesignForShipType(shipType), 100.0f, shipType.getSelectScreenSize(), fleet.getDirection(), fleet.isMoving());
                this.shipIcons.get(i6).setX(widthScaled);
                this.shipTypeIcons[i6].setVisible(true);
                this.shipTypeIcons[i6].setX(widthScaled);
                this.shipTypeIcons[i6].setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
                int autoCount = fleet.getAutoCount(shipType);
                if (autoCount > 0) {
                    this.autoCountTexts[i6].setVisible(true);
                    this.autoCountTexts[i6].setText(Integer.toString(autoCount));
                    this.autoCountTexts[i6].setX((this.shipIcons.get(i6).getX() + 50.0f) - ((this.autoCountTexts[i6].getWidthScaled() + 25.0f) / 2.0f));
                    this.autoCountIcons[i6].setVisible(true);
                    this.autoCountIcons[i6].setX(this.autoCountTexts[i6].getX() + this.autoCountTexts[i6].getWidthScaled() + 5.0f);
                }
                f2 = widthScaled + 120.0f;
                i6++;
            }
        }
        StarSystem starSystem = this.game.galaxy.getStarSystem(fleet.getSystemID());
        int ordinal = (starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4);
        this.star.animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
        if (this.game.getCurrentEmpire().isDiscoveredSystem(starSystem.getID())) {
            for (SystemObject systemObject : starSystem.getSystemObjects()) {
                int i8 = AnonymousClass1.f1363a[systemObject.getSystemObjectType().ordinal()];
                if (i8 == 1 || i8 == 2) {
                    PlanetSprite planetSprite = this.game.planetSpritePool.get();
                    planetSprite.setZIndex(1);
                    this.planetSprites.add(planetSprite);
                    attachChild(planetSprite);
                    planetSprite.setPosition((this.screenWidth - 360.0f) + (systemObject.getOrbit() * 50), 25.0f);
                    if (systemObject.isGasGiant()) {
                        GasGiant gasGiant = (GasGiant) systemObject;
                        planetSprite.setGasGiant(gasGiant, gasGiant.getScale(this.game.fleetsScene), Moon.getScale(this.game.fleetsScene));
                    } else {
                        Planet planet = (Planet) systemObject;
                        planetSprite.setPlanet(planet, planet.getScale(this.game.fleetsScene), Moon.getScale(this.game.fleetsScene));
                    }
                } else if (i8 == 3) {
                    this.asteroidBelts[i3].setVisible(true);
                    this.asteroidBelts[i3].setX((this.screenWidth - 385.0f) + (systemObject.getOrbit() * 50) + 10.0f);
                }
                if (systemObject.hasColony()) {
                    this.empireBars[i3].setVisible(true);
                    this.empireBars[i3].setX((this.screenWidth - 385.0f) + (systemObject.getOrbit() * 50));
                    this.empireBars[i3].setCurrentTileIndex(this.game.colonies.getColony(systemObject.getSystemID(), systemObject.getOrbit()).getEmpireID());
                }
                i3++;
            }
            return;
        }
        this.unexplored.setVisible(true);
    }

    public void showShipsButtonPressInvisible(boolean z) {
        this.shipListButtonPress.setVisible(z);
    }
}
