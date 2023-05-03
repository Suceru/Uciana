package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Manufacturing;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.ShipType;
import java.nio.CharBuffer;
import java.text.DecimalFormat;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonyListElement extends Entity {
    private final TiledSprite blockadeWarning;
    private final TiledSprite buildingIcon;
    private final Sprite buyNowBackground;
    private final TiledSprite buyNowButton;
    private final TiledSprite capitalIcon;
    private final TiledSprite creditsIcon;
    private final Text creditsPerTurn;
    private final DecimalFormat decimalFormat;
    private final TiledSprite empireBanner;
    private final TiledSprite empireBannerBackground;
    private final TiledSprite emptyColonyWarning;
    private final Sprite emptyPopulationBar;
    private final Sprite farmingBar;
    private final TiledSprite foodIcon;
    private final Text foodPerFarmerAmount;
    private final Text foodText;
    private final Game game;
    private final Text happiness;
    private final TiledSprite happinessIcon;
    private final TiledSprite lowPowerWarning;
    private final PlanetInfo planetInfo;
    private final Text planetName;
    private PlanetSprite planetSprite;
    private final Sprite populationBar;
    private final Text populationString;
    private final Text power;
    private final TiledSprite powerIcon;
    private final Sprite productionBackground;
    private final Sprite productionBar;
    private final TiledSprite productionIcon;
    private final Text productionName;
    private final Text productionPerWorkerAmount;
    private final ProductionPercentBar productionPercentBar;
    private final Text productionText;
    private final TiledSprite repeatIcon;
    private final TiledSprite[] resourceSprites = new TiledSprite[4];
    private final Sprite scienceBar;
    private final TiledSprite scienceIcon;
    private final Text sciencePerScientistAmount;
    private final Text scienceText;
    private final TiledSprite shipIcon;
    private final TiledSprite shipTypeIcon;
    private final TiledSprite starvingWarning;
    private final Text turnsLeft;

    public ColonyListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        DecimalFormat decimalFormat = new DecimalFormat();
        this.decimalFormat = decimalFormat;
        this.game = game;
        decimalFormat.setMaximumFractionDigits(1);
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        sprite.setAlpha(0.8f);
        sprite.setSize(655.0f, 100.0f);
        attachChild(sprite);
        Sprite sprite2 = new Sprite(660.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        this.productionBackground = sprite2;
        sprite2.setAlpha(0.8f);
        sprite2.setSize(375.0f, 100.0f);
        attachChild(sprite2);
        Sprite sprite3 = new Sprite(1040.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        this.buyNowBackground = sprite3;
        sprite3.setAlpha(0.8f);
        sprite3.setSize(110.0f, 100.0f);
        attachChild(sprite3);
        for (int i = 0; i < 4; i++) {
            TiledSprite tiledSprite = new TiledSprite(0.0f, 70.0f, game.graphics.resourceIconsTexture, vertexBufferObjectManager);
            tiledSprite.setScaleCenter(0.0f, 0.0f);
            tiledSprite.setSize(30.0f, 30.0f);
            tiledSprite.setZIndex(2);
            attachChild(tiledSprite);
            this.resourceSprites[i] = tiledSprite;
        }
        TiledSprite tiledSprite2 = new TiledSprite(105.0f, 5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.capitalIcon = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.CAPITAL.ordinal());
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.starvingWarning = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.STARVING.ordinal());
        tiledSprite3.setZIndex(2);
        blinkSprite(tiledSprite3);
        attachChild(tiledSprite3);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.blockadeWarning = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.BLOCKADE.ordinal());
        tiledSprite4.setZIndex(2);
        blinkSprite(tiledSprite4);
        attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.lowPowerWarning = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(InfoIconEnum.POWER_WARNING.ordinal());
        tiledSprite5.setZIndex(2);
        blinkSprite(tiledSprite5);
        attachChild(tiledSprite5);
        TiledSprite tiledSprite6 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.emptyColonyWarning = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(InfoIconEnum.EMPTY_COLONY_WARNING.ordinal());
        tiledSprite6.setZIndex(2);
        blinkSprite(tiledSprite6);
        attachChild(tiledSprite6);
        Text text = new Text(135.0f, 10.0f, game.fonts.smallFont, "###############", textOptions, vertexBufferObjectManager);
        this.planetName = text;
        text.setZIndex(2);
        attachChild(text);
        PlanetInfo planetInfo = new PlanetInfo(game, vertexBufferObjectManager);
        this.planetInfo = planetInfo;
        planetInfo.setPosition(135.0f, 35.0f);
        planetInfo.setScale(0.75f);
        planetInfo.setZIndex(2);
        attachChild(planetInfo);
        Text text2 = new Text(135.0f, 80.0f, game.fonts.smallInfoFont, "##########", textOptions, vertexBufferObjectManager);
        this.foodPerFarmerAmount = text2;
        attachChild(text2);
        TiledSprite tiledSprite7 = new TiledSprite(0.0f, 75.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.foodIcon = tiledSprite7;
        tiledSprite7.setScaleCenter(0.0f, 0.0f);
        tiledSprite7.setScale(0.8f);
        tiledSprite7.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
        attachChild(tiledSprite7);
        Text text3 = new Text(0.0f, 80.0f, game.fonts.smallInfoFont, "##########", textOptions, vertexBufferObjectManager);
        this.productionPerWorkerAmount = text3;
        attachChild(text3);
        TiledSprite tiledSprite8 = new TiledSprite(0.0f, 75.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.productionIcon = tiledSprite8;
        tiledSprite8.setScaleCenter(0.0f, 0.0f);
        tiledSprite8.setScale(0.8f);
        tiledSprite8.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
        attachChild(tiledSprite8);
        Text text4 = new Text(0.0f, 80.0f, game.fonts.smallInfoFont, "##########", textOptions, vertexBufferObjectManager);
        this.sciencePerScientistAmount = text4;
        attachChild(text4);
        TiledSprite tiledSprite9 = new TiledSprite(0.0f, 75.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.scienceIcon = tiledSprite9;
        tiledSprite9.setScaleCenter(0.0f, 0.0f);
        tiledSprite9.setScale(0.8f);
        tiledSprite9.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
        attachChild(tiledSprite9);
        TiledSprite tiledSprite10 = new TiledSprite(350.0f, 25.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.empireBannerBackground = tiledSprite10;
        tiledSprite10.setSize(50.0f, 50.0f);
        attachChild(tiledSprite10);
        TiledSprite tiledSprite11 = new TiledSprite(350.0f, 25.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.empireBanner = tiledSprite11;
        tiledSprite11.setSize(50.0f, 50.0f);
        attachChild(tiledSprite11);
        Sprite sprite4 = new Sprite(350.0f, 5.0f, game.graphics.popTexture, vertexBufferObjectManager);
        this.populationBar = sprite4;
        sprite4.setSize(200.0f, 40.0f);
        attachChild(sprite4);
        Sprite sprite5 = new Sprite(0.0f, 0.0f, game.graphics.popEmptyTexture, vertexBufferObjectManager);
        this.emptyPopulationBar = sprite5;
        attachChild(sprite5);
        Text text5 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.populationString = text5;
        attachChild(text5);
        Sprite sprite6 = new Sprite(350.0f, 50.0f, game.graphics.farmingBarTexture, vertexBufferObjectManager);
        this.farmingBar = sprite6;
        attachChild(sprite6);
        Sprite sprite7 = new Sprite(0.0f, 50.0f, game.graphics.productionBarTexture, vertexBufferObjectManager);
        this.productionBar = sprite7;
        attachChild(sprite7);
        Sprite sprite8 = new Sprite(0.0f, 50.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
        this.scienceBar = sprite8;
        attachChild(sprite8);
        Text text6 = new Text(355.0f, 60.0f, game.fonts.smallInfoFont, "###############", textOptions, vertexBufferObjectManager);
        this.foodText = text6;
        attachChild(text6);
        Text text7 = new Text(0.0f, 60.0f, game.fonts.smallInfoFont, "###############", textOptions, vertexBufferObjectManager);
        this.productionText = text7;
        attachChild(text7);
        Text text8 = new Text(0.0f, 60.0f, game.fonts.smallInfoFont, "###############", textOptions, vertexBufferObjectManager);
        this.scienceText = text8;
        attachChild(text8);
        Text text9 = new Text(0.0f, 7.0f, game.fonts.smallInfoFont, "###############", textOptions, vertexBufferObjectManager);
        this.happiness = text9;
        attachChild(text9);
        TiledSprite tiledSprite12 = new TiledSprite(625.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.happinessIcon = tiledSprite12;
        tiledSprite12.setCurrentTileIndex(InfoIconEnum.HAPPINESS.ordinal());
        attachChild(tiledSprite12);
        Text text10 = new Text(560.0f, 40.0f, game.fonts.smallInfoFont, "###############", textOptions, vertexBufferObjectManager);
        this.power = text10;
        attachChild(text10);
        TiledSprite tiledSprite13 = new TiledSprite(625.0f, 34.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.powerIcon = tiledSprite13;
        tiledSprite13.setCurrentTileIndex(InfoIconEnum.POWER.ordinal());
        attachChild(tiledSprite13);
        Text text11 = new Text(560.0f, 73.0f, game.fonts.smallInfoFont, "###############", textOptions, vertexBufferObjectManager);
        this.creditsPerTurn = text11;
        attachChild(text11);
        TiledSprite tiledSprite14 = new TiledSprite(625.0f, 68.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.creditsIcon = tiledSprite14;
        tiledSprite14.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        attachChild(tiledSprite14);
        TiledSprite tiledSprite15 = new TiledSprite(1045.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.buyNowButton = tiledSprite15;
        tiledSprite15.setCurrentTileIndex(GameIconEnum.BUY_PRODUCTION.ordinal());
        attachChild(tiledSprite15);
        Text text12 = new Text(800.0f, 13.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.productionName = text12;
        attachChild(text12);
        Text text13 = new Text(800.0f, 63.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.turnsLeft = text13;
        attachChild(text13);
        ProductionPercentBar productionPercentBar = new ProductionPercentBar(game, vertexBufferObjectManager, 250.0f);
        this.productionPercentBar = productionPercentBar;
        attachChild(productionPercentBar);
        TiledSprite tiledSprite16 = new TiledSprite(665.0f, 0.0f, game.graphics.shipsTextures[0], vertexBufferObjectManager);
        this.shipIcon = tiledSprite16;
        attachChild(tiledSprite16);
        TiledSprite tiledSprite17 = new TiledSprite(665.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.shipTypeIcon = tiledSprite17;
        attachChild(tiledSprite17);
        TiledSprite tiledSprite18 = new TiledSprite(665.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.buildingIcon = tiledSprite18;
        attachChild(tiledSprite18);
        TiledSprite tiledSprite19 = new TiledSprite(1000.0f, 5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.repeatIcon = tiledSprite19;
        tiledSprite19.setCurrentTileIndex(InfoIconEnum.REPEAT.ordinal());
        attachChild(tiledSprite19);
    }

    private void blinkSprite(Sprite sprite) {
        sprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        sprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.4f, 0.25f, 1.0f), new AlphaModifier(0.4f, 1.0f, 0.25f))));
    }

    private void setCapitalIcon(Colony colony) {
        this.capitalIcon.setVisible(false);
        if (colony.hasBuilding(BuildingID.CAPITAL)) {
            this.capitalIcon.setVisible(true);
        }
    }

    private void setColonyWarnings(Colony colony) {
        int i = 0;
        this.starvingWarning.setVisible(false);
        this.blockadeWarning.setVisible(false);
        this.lowPowerWarning.setVisible(false);
        this.emptyColonyWarning.setVisible(false);
        if (colony.isStarving()) {
            this.starvingWarning.setVisible(true);
            this.starvingWarning.setX(0);
            i = 30;
        }
        if (colony.isBlockaded()) {
            this.blockadeWarning.setVisible(true);
            this.blockadeWarning.setX(i);
            i += 30;
        }
        if (colony.isLowPower()) {
            this.lowPowerWarning.setVisible(true);
            this.lowPowerWarning.setX(i);
            i += 30;
        }
        if (colony.getPopulation() == 0) {
            this.emptyColonyWarning.setVisible(true);
            this.emptyColonyWarning.setX(i);
        }
    }

    private void setCredits(Colony colony) {
        if (colony.getCreditsPerTurn() > 0) {
            Text text = this.creditsPerTurn;
            text.setText("+" + colony.getCreditsPerTurn());
        } else {
            this.creditsPerTurn.setText(Integer.toString(colony.getCreditsPerTurn()));
        }
        Text text2 = this.creditsPerTurn;
        text2.setX(622.0f - text2.getWidthScaled());
    }

    private void setFoodProductionScienceBar(Colony colony) {
        this.farmingBar.setSize(colony.getFarmersPercent() * 2, 35.0f);
        this.productionBar.setSize(colony.getWorkersPercent() * 2, 35.0f);
        this.productionBar.setX(this.farmingBar.getX() + this.farmingBar.getWidth());
        this.scienceBar.setSize(colony.getScientistPercent() * 2, 35.0f);
        this.scienceBar.setX(this.productionBar.getX() + this.productionBar.getWidth());
        this.foodText.setText(Integer.toString(colony.getFoodPerTurn()));
        this.productionText.setText(Integer.toString(colony.getProductionPerTurn()));
        this.productionText.setX(450.0f - (this.productionText.getWidth() / 2.0f));
        this.scienceText.setText(Integer.toString(colony.getSciencePerTurn()));
        this.scienceText.setX(545.0f - this.scienceText.getWidth());
    }

    private void setFoodProductionScienceInfo(float f2, float f3, float f4) {
        this.foodPerFarmerAmount.setText(this.decimalFormat.format(f2));
        this.foodIcon.setX(this.foodPerFarmerAmount.getX() + this.foodPerFarmerAmount.getWidthScaled() + 5.0f);
        this.productionPerWorkerAmount.setX(this.foodIcon.getX() + this.foodIcon.getWidthScaled() + 10.0f);
        this.productionPerWorkerAmount.setText(this.decimalFormat.format(f3));
        this.productionIcon.setX(this.productionPerWorkerAmount.getX() + this.productionPerWorkerAmount.getWidthScaled() + 3.0f);
        this.sciencePerScientistAmount.setX(this.productionIcon.getX() + this.productionIcon.getWidthScaled() + 10.0f);
        this.sciencePerScientistAmount.setText(this.decimalFormat.format(f4));
        this.scienceIcon.setX(this.sciencePerScientistAmount.getX() + this.sciencePerScientistAmount.getWidthScaled() + 3.0f);
    }

    private void setHappiness(Colony colony) {
        Text text = this.happiness;
        text.setText(Math.round(colony.getHappiness() * 100.0f) + "%");
        Text text2 = this.happiness;
        text2.setX(622.0f - text2.getWidthScaled());
    }

    private void setPlanet(int i, Planet planet) {
        this.planetSprite.setPlanet(planet, planet.getScale(this.game.coloniesScene), Moon.getScale(this.game.coloniesScene));
        this.planetSprite.getMoonSprite().setPosition(15.0f, -5.0f);
        this.starvingWarning.setVisible(false);
        this.blockadeWarning.setVisible(false);
        this.lowPowerWarning.setVisible(false);
        this.emptyColonyWarning.setVisible(false);
        for (int i2 = 0; i2 < 4; i2++) {
            this.resourceSprites[i2].setVisible(false);
        }
        if (planet.hasBeenExploredByEmpire(i)) {
            setPlanetResource(planet.getVisibleResources(i));
        }
        this.planetName.setText(planet.getName());
        this.planetInfo.set(planet);
        setFoodProductionScienceInfo(planet.getFoodPerFarmer(i), planet.getProductionPerWorker(i), planet.getSciencePerScientist(i));
        this.populationString.setVisible(true);
        if (planet.hasBeenExploredByEmpire(i)) {
            this.populationString.setColor(Color.GREEN);
            this.populationString.setText(this.game.getActivity().getString(R.string.planet_explored));
        } else if (planet.isUnexplored()) {
            this.populationString.setColor(Color.WHITE);
            this.populationString.setText(this.game.getActivity().getString(R.string.planet_unexplored));
        } else {
            this.populationString.setColor(new Color(0.6f, 0.6f, 0.6f));
            this.populationString.setText(this.game.getActivity().getString(R.string.planet_unknown));
        }
        Text text = this.populationString;
        text.setPosition(450.0f, 50.0f - (text.getHeightScaled() / 2.0f));
    }

    private void setPlanetResource(List<ResourceID> list) {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            this.resourceSprites[i2].setVisible(false);
        }
        int size = list.size() * 30;
        for (ResourceID resourceID : list) {
            if (Resources.get(resourceID).isVisible(this.game.getCurrentPlayer())) {
                this.resourceSprites[i].setCurrentTileIndex(Resources.get(resourceID).getImageIndex());
                this.resourceSprites[i].setX((60.0f - (size / 2.0f)) + (i * 30));
                this.resourceSprites[i].setVisible(true);
                i++;
                if (i == 4) {
                    return;
                }
            }
        }
    }

    private void setPopulationBar(Colony colony) {
        float population = colony.getPopulation() / colony.getPlanet().getMaxPopulation();
        this.emptyPopulationBar.setVisible(false);
        if (population != 1.0f) {
            float f2 = population * 198.0f;
            this.emptyPopulationBar.setPosition(351.0f + f2, 11.0f);
            this.emptyPopulationBar.setSize(198.0f - f2, 27.0f);
            this.emptyPopulationBar.setVisible(true);
        }
        this.populationString.setColor(Color.WHITE);
        this.populationString.setText(this.game.getActivity().getString(R.string.system_colony_pop, new Object[]{Integer.valueOf(colony.getPopulation()), Integer.valueOf(colony.getPlanet().getMaxPopulation())}));
        this.populationString.setPosition(450.0f - (this.populationString.getWidth() / 2.0f), 16.0f);
    }

    private void setPower(Colony colony) {
        if (colony.getPowerLevel() > 0) {
            Text text = this.power;
            text.setText("+" + colony.getPowerLevel());
        } else {
            this.power.setText(Integer.toString(colony.getPowerLevel()));
        }
        Text text2 = this.power;
        text2.setX(622.0f - text2.getWidthScaled());
    }

    private void setProductionInfo(Colony colony) {
        this.shipIcon.setVisible(false);
        this.shipTypeIcon.setVisible(false);
        this.buildingIcon.setVisible(false);
        Manufacturing manufacturing = colony.getManufacturing();
        this.repeatIcon.setVisible(manufacturing.getCurrentItem().isRepeatOn());
        this.buyNowButton.setCurrentTileIndex(GameIconEnum.BUY_PRODUCTION.ordinal());
        this.buyNowButton.setAlpha(1.0f);
        this.buyNowButton.setVisible(true);
        if (manufacturing.getCostToFinish() > this.game.getCurrentEmpire().getCredits()) {
            this.buyNowButton.setAlpha(0.4f);
        }
        if (manufacturing.getCostToFinish() == 0) {
            this.buyNowButton.setVisible(false);
        }
        this.productionName.setText(colony.getNameOfProduction());
        Text text = this.productionName;
        text.setX(895.0f - (text.getWidthScaled() / 2.0f));
        this.turnsLeft.setText(colony.getTurnsLeftOnProductionString());
        Text text2 = this.turnsLeft;
        text2.setX(895.0f - (text2.getWidthScaled() / 2.0f));
        this.productionPercentBar.setPosition(770.0f, 45.0f);
        this.productionPercentBar.set(colony);
        if (manufacturing.getType() == ManufacturingType.SHIP) {
            ShipType shipType = colony.getManufacturing().getShipType();
            this.shipIcon.setVisible(true);
            this.shipIcon.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.shipsTextures[colony.getEmpireID()]);
            this.shipIcon.setCurrentTileIndex(colony.getManufacturing().getShipIconIndex());
            float selectScreenSize = shipType.getSelectScreenSize();
            this.shipIcon.setSize(selectScreenSize, selectScreenSize);
            float f2 = 50.0f - (selectScreenSize / 2.0f);
            this.shipIcon.setPosition(665.0f + f2, f2);
            this.shipTypeIcon.setVisible(true);
            this.shipTypeIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
            return;
        }
        this.buildingIcon.setVisible(true);
        this.buildingIcon.setCurrentTileIndex(manufacturing.getImageIndex());
    }

    private void setVisibilityOfColonyOnlyElement(boolean z) {
        this.productionBackground.setVisible(z);
        this.buyNowBackground.setVisible(z);
        this.capitalIcon.setVisible(z);
        this.populationBar.setVisible(z);
        this.emptyPopulationBar.setVisible(z);
        this.farmingBar.setVisible(z);
        this.productionBar.setVisible(z);
        this.scienceBar.setVisible(z);
        this.foodText.setVisible(z);
        this.productionText.setVisible(z);
        this.scienceText.setVisible(z);
        this.happiness.setVisible(z);
        this.happinessIcon.setVisible(z);
        this.power.setVisible(z);
        this.powerIcon.setVisible(z);
        this.creditsPerTurn.setVisible(z);
        this.creditsPerTurn.setVisible(z);
        this.creditsIcon.setVisible(z);
        this.buyNowButton.setVisible(z);
        this.productionName.setVisible(z);
        this.turnsLeft.setVisible(z);
        this.productionPercentBar.setVisible(z);
        this.shipIcon.setVisible(z);
        this.shipTypeIcon.setVisible(z);
        this.buildingIcon.setVisible(z);
        this.repeatIcon.setVisible(z);
    }

    public void initialSet() {
        PlanetSprite planetSprite = this.game.planetSpritePool.get();
        this.planetSprite = planetSprite;
        planetSprite.setMoonRange(100, 100);
        this.planetSprite.setPosition(60.0f, 48.0f);
        this.planetSprite.setZIndex(1);
        attachChild(this.planetSprite);
        sortChildren();
    }

    public boolean isBuyNowVisible() {
        return this.buyNowButton.isVisible();
    }

    public void releasePoolElements() {
        detachChild(this.planetSprite);
        this.game.planetSpritePool.push(this.planetSprite);
    }

    public void set(Colony colony) {
        Planet planet = colony.getPlanet();
        setVisibilityOfColonyOnlyElement(true);
        this.empireBannerBackground.setVisible(false);
        this.empireBanner.setVisible(false);
        this.planetSprite.setPlanet(planet, planet.getScale(this.game.coloniesScene), Moon.getScale(this.game.coloniesScene));
        this.planetSprite.getMoonSprite().setPosition(15.0f, -5.0f);
        setPlanetResource(colony.getPlanet().getVisibleResources(colony.getEmpireID()));
        setCapitalIcon(colony);
        setColonyWarnings(colony);
        this.planetName.setText(colony.getName());
        this.planetInfo.set(planet);
        setFoodProductionScienceInfo(colony.getFoodPerFarmer(), colony.getProductionPerWorker(), colony.getSciencePerScientist());
        setPopulationBar(colony);
        setFoodProductionScienceBar(colony);
        setHappiness(colony);
        setPower(colony);
        setCredits(colony);
        setProductionInfo(colony);
    }

    public void set(int i, Planet planet) {
        setVisibilityOfColonyOnlyElement(false);
        this.empireBannerBackground.setVisible(false);
        this.empireBanner.setVisible(false);
        setPlanet(i, planet);
    }

    public void set(int i, Planet planet, Colony colony) {
        setVisibilityOfColonyOnlyElement(false);
        setPlanet(i, planet);
        this.empireBannerBackground.setVisible(true);
        this.empireBannerBackground.setCurrentTileIndex(colony.getEmpireID());
        this.empireBanner.setVisible(true);
        this.empireBanner.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(this.game.empires.get(colony.getEmpireID()).getBannerID()));
        this.capitalIcon.setVisible(colony.hasBuilding(BuildingID.CAPITAL));
        this.planetName.setText(colony.getName());
    }
}
