package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Elements.PlayerCreationScene.RaceAttributesElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.ResourcesMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.MigrantListOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.RaceAttribute;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Nebulas;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EmpireScene extends ExtendedScene {
    private static final int SCROLL_HEIGHT = 1460;
    private static final int VISIBLE_SCROLL_HEIGHT = 634;
    private TiledSprite availableCommandPointsIcon;
    private Text availableCountText;
    private VertexBufferObjectManager bufferManager;
    private Text buildingsCost;
    private Text buildingsCostValue;
    private TiledSprite coalition1Checkbox;
    private Text coalition1Condition;
    private TiledSprite coalition2Checkbox;
    private Text coalition2Condition;
    private TiledSprite coalitionBackground;
    private Text coalitionVictory;
    private TiledSprite coloniesButton;
    private Text coloniesCount;
    private Entity coloniesInfo;
    private Text colonyScannerRange;
    private TiledSprite creditsPerTurnIcon;
    private Text creditsPerTurnText;
    private Entity economicsInfo;
    private Empire empire;
    private TiledSprite empireBackground;
    private TiledSprite empireBanner;
    private Scene empireInfo;
    private Text empireName;
    private Text expense;
    private Line expenseLine;
    private float expenseValueWidth;
    private float expenseWidth;
    private Text extraFoodSale;
    private Text extraFoodSaleValue;
    private Text fleetMaintenance;
    private Text fleetMaintenanceValue;
    private TiledSprite fleetsButton;
    private Text foodShippingCosts;
    private Text foodShippingCostsValue;
    private Text ftlSpeed;
    private TiledSprite galaxyButton;
    private Text galaxyExplored;
    private TiledSprite groundCombatPointsIcon;
    private Text groundCombatPowerText;
    private TiledSprite historyGraphButton;
    private Text importedCountText;
    private TiledSprite importedFoodIcon;
    private TiledSprite infoButtonPress;
    private float lastY;
    private Text maxFuelRange;
    private MigrantListOverlay migrantListOverlay;
    private TiledSprite migrantsButton;
    private TiledSprite migrantsButtonIcon;
    private Entity militaryInfo;
    private Text militaryVictoryCondition;
    private Text miningOutpostCount;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private Text noneText;
    private Text outpostCount;
    private Text populationCount;
    private TiledSprite populationIcon;
    private Text populationInTransit;
    private Text populationTaxes;
    private Text populationTaxesValue;
    private float pressedY;
    private Text productionTaxes;
    private Text productionTaxesValue;
    private Entity raceInfo;
    private Text researchOutpostCount;
    private Entity resourcesInfo;
    private Text revenueFromResources;
    private Text revenueFromResourcesValue;
    private float revenueValueWidth;
    private float revenueWidth;
    private TiledSprite scrollBar1;
    private TiledSprite scrollBar2;
    private TiledSprite selectedTaxRate;
    private Text shipCommunicationRange;
    private Text shipCount;
    private Text shipScannerRange;
    private TiledSprite shippingCostIcon;
    private Text shippingCostText;
    private TiledSprite shipyardButton;
    private TiledSprite techButton;
    private Entity techInfo;
    private Text timePlayed;
    private TiledSprite totalCommandPointsIcon;
    private Text totalCountText;
    private TiledSprite totalCreditsIcon;
    private Text totalCreditsText;
    private Text tradegoods;
    private Text tradegoodsValue;
    private Text treatiesExpense;
    private Text treatiesExpenseValue;
    private Text treatiesRevenue;
    private Text treatiesRevenueValue;
    private Entity victoryInfo;
    private TiledSprite warningIcon;
    private Text warningText;
    private final TiledSprite[] shipTypeIcons = new TiledSprite[8];
    private final Text[] shipCounts = new Text[8];
    private final TiledSprite[] resourceIcons = new TiledSprite[ResourceID.values().length - 1];
    private final Text[] resourceCounts = new Text[ResourceID.values().length - 1];
    private final RaceAttributesElement[] raceInfoElements = new RaceAttributesElement[2];
    private final TiledSprite[] percentButtons = new TiledSprite[6];
    private boolean isScroll = false;

    public EmpireScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(Point point) {
        this.isScroll = false;
        if (point.getY() > 86.0f) {
            this.pressedY = point.getY();
            this.lastY = point.getY();
        }
        checkForPressed(point);
    }

    private void checkActionMove(Point point) {
        this.infoButtonPress.setVisible(false);
        if (point.getY() > 86.0f) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y = this.empireInfo.getY() - (this.lastY - point.getY());
            float f2 = y <= 86.0f ? y : 86.0f;
            if (f2 < -740.0f) {
                f2 = -740.0f;
            }
            this.empireInfo.setY(f2);
            this.lastY = point.getY();
            setScrollBar();
        }
        if (this.isScroll) {
            return;
        }
        checkForPressed(point);
    }

    private void checkActionUp(Point point) {
        TiledSprite[] tiledSpriteArr;
        this.infoButtonPress.setVisible(false);
        if (this.isScroll) {
            return;
        }
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isButtonOnInfoPressed(point, this.victoryInfo, this.historyGraphButton)) {
            historyGraphButtonPressed();
        } else {
            for (TiledSprite tiledSprite : this.resourceIcons) {
                if (isButtonOnInfoPressed(point, this.resourcesInfo, tiledSprite)) {
                    resourceIconPressed(tiledSprite);
                }
            }
            for (int i = 0; i < 6; i++) {
                if (isButtonOnInfoPressed(point, this.economicsInfo, this.percentButtons[i])) {
                    taxRateButtonPressed(i);
                }
            }
            if (isButtonOnInfoPressed(point, this.coloniesInfo, this.coloniesButton)) {
                coloniesButtonPressed();
            }
            if (isButtonOnInfoPressed(point, this.coloniesInfo, this.migrantsButton) && this.migrantsButton.getAlpha() == 1.0f) {
                migrantsButtonPressed();
            }
            if (isButtonOnInfoPressed(point, this.militaryInfo, this.fleetsButton)) {
                fleetsButtonPressed();
            }
            if (isButtonOnInfoPressed(point, this.militaryInfo, this.shipyardButton)) {
                shipyardButtonPressed();
            }
            if (isButtonOnInfoPressed(point, this.techInfo, this.techButton)) {
                techButtonPressed();
            }
        }
    }

    private void checkForPressed(Point point) {
        if (isButtonOnInfoPressed(point, this.victoryInfo, this.historyGraphButton)) {
            this.infoButtonPress.setX(this.historyGraphButton.getX());
            this.infoButtonPress.setY(this.empireInfo.getY() + this.victoryInfo.getY() + this.historyGraphButton.getY());
            this.infoButtonPress.setVisible(true);
        }
        for (int i = 0; i < 6; i++) {
            if (isButtonOnInfoPressed(point, this.economicsInfo, this.percentButtons[i])) {
                this.infoButtonPress.setX(this.percentButtons[i].getX());
                this.infoButtonPress.setY(this.empireInfo.getY() + this.economicsInfo.getY() + this.percentButtons[i].getY());
                this.infoButtonPress.setVisible(true);
            }
        }
        if (isButtonOnInfoPressed(point, this.coloniesInfo, this.coloniesButton)) {
            this.infoButtonPress.setX(this.coloniesButton.getX());
            this.infoButtonPress.setY(this.empireInfo.getY() + this.coloniesInfo.getY() + this.coloniesButton.getY());
            this.infoButtonPress.setVisible(true);
        }
        if (isButtonOnInfoPressed(point, this.coloniesInfo, this.migrantsButton) && this.migrantsButton.getAlpha() == 1.0f) {
            this.infoButtonPress.setX(this.migrantsButton.getX());
            this.infoButtonPress.setY(this.empireInfo.getY() + this.coloniesInfo.getY() + this.migrantsButton.getY());
            this.infoButtonPress.setVisible(true);
        }
        if (isButtonOnInfoPressed(point, this.militaryInfo, this.fleetsButton)) {
            this.infoButtonPress.setX(this.fleetsButton.getX());
            this.infoButtonPress.setY(this.empireInfo.getY() + this.militaryInfo.getY() + this.fleetsButton.getY());
            this.infoButtonPress.setVisible(true);
        }
        if (isButtonOnInfoPressed(point, this.militaryInfo, this.shipyardButton)) {
            this.infoButtonPress.setX(this.shipyardButton.getX());
            this.infoButtonPress.setY(this.empireInfo.getY() + this.militaryInfo.getY() + this.shipyardButton.getY());
            this.infoButtonPress.setVisible(true);
        }
        if (isButtonOnInfoPressed(point, this.techInfo, this.techButton)) {
            this.infoButtonPress.setX(this.techButton.getX());
            this.infoButtonPress.setY(this.empireInfo.getY() + this.techInfo.getY() + this.techButton.getY());
            this.infoButtonPress.setVisible(true);
        }
    }

    private void coloniesButtonPressed() {
        changeScene(this.B.coloniesScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void createColoniesInfo() {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager);
        tiledSprite.setCurrentTileIndex(1);
        tiledSprite.setSize(getWidth() - 10.0f, 200.0f);
        tiledSprite.setAlpha(0.2f);
        this.coloniesInfo.attachChild(tiledSprite);
        TiledTextureRegion[] tiledTextureRegionArr = this.B.graphics.planetsTextureRegion;
        Climate climate = Climate.TERRAN;
        TiledSprite tiledSprite2 = new TiledSprite(10.0f, 10.0f, tiledTextureRegionArr[climate.getID()], this.bufferManager);
        tiledSprite2.setCurrentTileIndex(0);
        tiledSprite2.setSize(50.0f, 50.0f);
        this.coloniesInfo.attachChild(tiledSprite2);
        Game game = this.B;
        this.coloniesInfo.attachChild(new Text(70.0f, 25.0f, game.fonts.infoFont, game.getActivity().getString(R.string.empire_colonies), this.bufferManager));
        Text text = new Text(0.0f, 14.0f, this.B.fonts.smallInfoFont, "##########", this.bufferManager);
        this.importedCountText = text;
        this.coloniesInfo.attachChild(text);
        TiledSprite tiledSprite3 = new TiledSprite(350.0f, 7.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.importedFoodIcon = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.IMPORTED_FOOD.ordinal());
        this.coloniesInfo.attachChild(this.importedFoodIcon);
        float x = this.importedFoodIcon.getX() + this.importedFoodIcon.getWidthScaled() + 10.0f;
        Game game2 = this.B;
        this.coloniesInfo.attachChild(new Text(x, 14.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.empire_imported_food), this.bufferManager));
        Text text2 = new Text(0.0f, 42.0f, this.B.fonts.smallInfoFont, "##########", this.bufferManager);
        this.shippingCostText = text2;
        this.coloniesInfo.attachChild(text2);
        TiledSprite tiledSprite4 = new TiledSprite(350.0f, 35.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.shippingCostIcon = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        this.coloniesInfo.attachChild(this.shippingCostIcon);
        Text text3 = new Text(0.0f, 14.0f, this.B.fonts.smallInfoFont, "##########", this.bufferManager);
        this.populationInTransit = text3;
        this.coloniesInfo.attachChild(text3);
        TiledSprite tiledSprite5 = new TiledSprite(650.0f, 7.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.populationIcon = tiledSprite5;
        InfoIconEnum infoIconEnum = InfoIconEnum.POPULATION;
        tiledSprite5.setCurrentTileIndex(infoIconEnum.ordinal());
        this.coloniesInfo.attachChild(this.populationIcon);
        Game game3 = this.B;
        this.coloniesInfo.attachChild(new Text(this.populationIcon.getX() + this.populationIcon.getWidthScaled() + 10.0f, 14.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.empire_in_transit), this.bufferManager));
        TiledSprite tiledSprite6 = new TiledSprite(25.0f, 90.0f, this.B.graphics.planetsTextureRegion[climate.getID()], this.bufferManager);
        tiledSprite6.setCurrentTileIndex(1);
        tiledSprite6.setSize(50.0f, 50.0f);
        this.coloniesInfo.attachChild(tiledSprite6);
        Text text4 = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.coloniesCount = text4;
        this.coloniesInfo.attachChild(text4);
        TiledSprite tiledSprite7 = new TiledSprite(125.0f, 90.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        tiledSprite7.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite7.setSize(50.0f, 50.0f);
        this.coloniesInfo.attachChild(tiledSprite7);
        Text text5 = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.populationCount = text5;
        this.coloniesInfo.attachChild(text5);
        TiledSprite tiledSprite8 = new TiledSprite(225.0f, 90.0f, this.B.graphics.gameIconsTexture, this.bufferManager);
        tiledSprite8.setCurrentTileIndex(GameIconEnum.OUTPOST.ordinal());
        tiledSprite8.setSize(50.0f, 50.0f);
        this.coloniesInfo.attachChild(tiledSprite8);
        Text text6 = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.outpostCount = text6;
        this.coloniesInfo.attachChild(text6);
        TiledSprite tiledSprite9 = new TiledSprite(325.0f, 90.0f, this.B.graphics.gameIconsTexture, this.bufferManager);
        tiledSprite9.setCurrentTileIndex(GameIconEnum.MINING_STATION.ordinal());
        tiledSprite9.setSize(50.0f, 50.0f);
        this.coloniesInfo.attachChild(tiledSprite9);
        Text text7 = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.miningOutpostCount = text7;
        this.coloniesInfo.attachChild(text7);
        TiledSprite tiledSprite10 = new TiledSprite(425.0f, 90.0f, this.B.graphics.gameIconsTexture, this.bufferManager);
        tiledSprite10.setCurrentTileIndex(GameIconEnum.SCIENCE_STATION.ordinal());
        tiledSprite10.setSize(50.0f, 50.0f);
        this.coloniesInfo.attachChild(tiledSprite10);
        Text text8 = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.researchOutpostCount = text8;
        this.coloniesInfo.attachChild(text8);
        TiledSprite tiledSprite11 = new TiledSprite(getWidth() - 130.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager);
        this.coloniesButton = tiledSprite11;
        tiledSprite11.setCurrentTileIndex(ButtonsEnum.COLONIES.ordinal());
        this.coloniesInfo.attachChild(this.coloniesButton);
        TiledSprite tiledSprite12 = new TiledSprite(getWidth() - 250.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager);
        this.migrantsButton = tiledSprite12;
        tiledSprite12.setCurrentTileIndex(ButtonsEnum.BLANK.ordinal());
        this.coloniesInfo.attachChild(this.migrantsButton);
        s(this.migrantsButton);
        TiledSprite tiledSprite13 = new TiledSprite(getWidth() - 240.0f, -7.0f, this.B.graphics.gameIconsTexture, this.bufferManager);
        this.migrantsButtonIcon = tiledSprite13;
        tiledSprite13.setCurrentTileIndex(GameIconEnum.MOVE_PEOPLE.ordinal());
        this.coloniesInfo.attachChild(this.migrantsButtonIcon);
    }

    private void createEconomicInfo() {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager);
        tiledSprite.setCurrentTileIndex(4);
        tiledSprite.setSize(getWidth() - 10.0f, 250.0f);
        tiledSprite.setAlpha(0.2f);
        this.economicsInfo.attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(10.0f, 10.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        InfoIconEnum infoIconEnum = InfoIconEnum.CREDITS;
        tiledSprite2.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite2.setSize(50.0f, 50.0f);
        this.economicsInfo.attachChild(tiledSprite2);
        Game game = this.B;
        this.economicsInfo.attachChild(new Text(70.0f, 25.0f, game.fonts.infoFont, game.getActivity().getString(R.string.empire_economy), this.bufferManager));
        Text text = new Text(0.0f, 14.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.creditsPerTurnText = text;
        this.economicsInfo.attachChild(text);
        TiledSprite tiledSprite3 = new TiledSprite(350.0f, 7.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.creditsPerTurnIcon = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(infoIconEnum.ordinal());
        this.economicsInfo.attachChild(this.creditsPerTurnIcon);
        float x = this.creditsPerTurnIcon.getX() + this.creditsPerTurnIcon.getWidthScaled() + 10.0f;
        Game game2 = this.B;
        this.economicsInfo.attachChild(new Text(x, 14.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.empire_per_turn), this.bufferManager));
        Text text2 = new Text(0.0f, 42.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.totalCreditsText = text2;
        this.economicsInfo.attachChild(text2);
        TiledSprite tiledSprite4 = new TiledSprite(350.0f, 35.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.totalCreditsIcon = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(infoIconEnum.ordinal());
        this.economicsInfo.attachChild(this.totalCreditsIcon);
        float x2 = this.creditsPerTurnIcon.getX() + this.creditsPerTurnIcon.getWidthScaled() + 10.0f;
        Game game3 = this.B;
        this.economicsInfo.attachChild(new Text(x2, 42.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.empire_credits_total), this.bufferManager));
        Game game4 = this.B;
        Text text3 = new Text(25.0f, 70.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.empire_economy_revenue), this.bufferManager);
        text3.setColor(new Color(0.7f, 1.0f, 0.7f));
        this.economicsInfo.attachChild(text3);
        Line line = new Line(text3.getX(), 90.0f, text3.getX() + text3.getWidthScaled(), 90.0f, this.bufferManager);
        line.setColor(new Color(0.8f, 0.8f, 0.8f));
        line.setLineWidth(3.0f);
        this.economicsInfo.attachChild(line);
        Game game5 = this.B;
        Text text4 = new Text(25.0f, 100.0f, game5.fonts.smallInfoFont, game5.getActivity().getString(R.string.empire_economy_revenue_population), this.E, this.bufferManager);
        this.populationTaxes = text4;
        this.economicsInfo.attachChild(text4);
        if (this.populationTaxes.getWidthScaled() > this.revenueWidth) {
            this.revenueWidth = this.populationTaxes.getWidthScaled();
        }
        Text text5 = new Text(0.0f, 100.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.populationTaxesValue = text5;
        this.economicsInfo.attachChild(text5);
        Game game6 = this.B;
        Text text6 = new Text(25.0f, 125.0f, game6.fonts.smallInfoFont, game6.getActivity().getString(R.string.empire_economy_revenue_industrial), this.E, this.bufferManager);
        this.productionTaxes = text6;
        this.economicsInfo.attachChild(text6);
        if (this.productionTaxes.getWidthScaled() > this.revenueWidth) {
            this.revenueWidth = this.productionTaxes.getWidthScaled();
        }
        Text text7 = new Text(0.0f, 125.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.productionTaxesValue = text7;
        this.economicsInfo.attachChild(text7);
        Game game7 = this.B;
        Text text8 = new Text(25.0f, 150.0f, game7.fonts.smallInfoFont, game7.getActivity().getString(R.string.empire_economy_revenue_tradegoods), this.E, this.bufferManager);
        this.tradegoods = text8;
        if (text8.getWidthScaled() > this.revenueWidth) {
            this.revenueWidth = this.tradegoods.getWidthScaled();
        }
        this.economicsInfo.attachChild(this.tradegoods);
        Text text9 = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.tradegoodsValue = text9;
        this.economicsInfo.attachChild(text9);
        Game game8 = this.B;
        Text text10 = new Text(25.0f, 175.0f, game8.fonts.smallInfoFont, game8.getActivity().getString(R.string.empire_economy_revenue_excess_food), this.E, this.bufferManager);
        this.extraFoodSale = text10;
        if (text10.getWidthScaled() > this.revenueWidth) {
            this.revenueWidth = this.extraFoodSale.getWidthScaled();
        }
        this.economicsInfo.attachChild(this.extraFoodSale);
        Text text11 = new Text(0.0f, 175.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.extraFoodSaleValue = text11;
        this.economicsInfo.attachChild(text11);
        Game game9 = this.B;
        Text text12 = new Text(25.0f, 200.0f, game9.fonts.smallInfoFont, game9.getActivity().getString(R.string.empire_economy_revenue_resources), this.E, this.bufferManager);
        this.revenueFromResources = text12;
        if (text12.getWidthScaled() > this.revenueWidth) {
            this.revenueWidth = this.revenueFromResources.getWidthScaled();
        }
        this.economicsInfo.attachChild(this.revenueFromResources);
        Text text13 = new Text(0.0f, 200.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.revenueFromResourcesValue = text13;
        this.economicsInfo.attachChild(text13);
        Game game10 = this.B;
        Text text14 = new Text(25.0f, 225.0f, game10.fonts.smallInfoFont, game10.getActivity().getString(R.string.empire_economy_revenue_treaties), this.E, this.bufferManager);
        this.treatiesRevenue = text14;
        if (text14.getWidthScaled() > this.revenueWidth) {
            this.revenueWidth = this.treatiesRevenue.getWidthScaled();
        }
        this.economicsInfo.attachChild(this.treatiesRevenue);
        Text text15 = new Text(0.0f, 225.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.treatiesRevenueValue = text15;
        this.economicsInfo.attachChild(text15);
        Game game11 = this.B;
        Text text16 = new Text(0.0f, 120.0f, game11.fonts.smallInfoFont, game11.getActivity().getString(R.string.empire_economy_expenses), this.bufferManager);
        this.expense = text16;
        text16.setColor(new Color(1.0f, 0.7f, 0.7f));
        this.economicsInfo.attachChild(this.expense);
        Line line2 = new Line(0.0f, 0.0f, 0.0f, 0.0f, this.bufferManager);
        this.expenseLine = line2;
        line2.setColor(new Color(0.8f, 0.8f, 0.8f));
        this.expenseLine.setLineWidth(3.0f);
        this.economicsInfo.attachChild(this.expenseLine);
        Game game12 = this.B;
        Text text17 = new Text(0.0f, 150.0f, game12.fonts.smallInfoFont, game12.getActivity().getString(R.string.empire_economy_expenses_buildings), this.E, this.bufferManager);
        this.buildingsCost = text17;
        if (text17.getWidthScaled() > this.expenseWidth) {
            this.expenseWidth = this.buildingsCost.getWidthScaled();
        }
        this.economicsInfo.attachChild(this.buildingsCost);
        Text text18 = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.buildingsCostValue = text18;
        this.economicsInfo.attachChild(text18);
        Game game13 = this.B;
        Text text19 = new Text(0.0f, 175.0f, game13.fonts.smallInfoFont, game13.getActivity().getString(R.string.empire_economy_expenses_import_costs), this.E, this.bufferManager);
        this.foodShippingCosts = text19;
        if (text19.getWidthScaled() > this.expenseWidth) {
            this.expenseWidth = this.foodShippingCosts.getWidthScaled();
        }
        this.economicsInfo.attachChild(this.foodShippingCosts);
        Text text20 = new Text(0.0f, 175.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.foodShippingCostsValue = text20;
        this.economicsInfo.attachChild(text20);
        Game game14 = this.B;
        Text text21 = new Text(0.0f, 200.0f, game14.fonts.smallInfoFont, game14.getActivity().getString(R.string.empire_economy_expenses_command_points), this.E, this.bufferManager);
        this.fleetMaintenance = text21;
        this.economicsInfo.attachChild(text21);
        if (this.fleetMaintenance.getWidthScaled() > this.expenseWidth) {
            this.expenseWidth = this.fleetMaintenance.getWidthScaled();
        }
        Text text22 = new Text(0.0f, 200.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.fleetMaintenanceValue = text22;
        this.economicsInfo.attachChild(text22);
        Game game15 = this.B;
        Text text23 = new Text(0.0f, 225.0f, game15.fonts.smallInfoFont, game15.getActivity().getString(R.string.empire_economy_expenses_treaties), this.E, this.bufferManager);
        this.treatiesExpense = text23;
        this.economicsInfo.attachChild(text23);
        if (this.treatiesExpense.getWidthScaled() > this.expenseWidth) {
            this.expenseWidth = this.treatiesExpense.getWidthScaled();
        }
        Text text24 = new Text(0.0f, 225.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
        this.treatiesExpenseValue = text24;
        this.economicsInfo.attachChild(text24);
        float width = getWidth() - 720.0f;
        Game game16 = this.B;
        Text text25 = new Text(width, 20.0f, game16.fonts.smallFont, game16.getActivity().getString(R.string.empire_tax_rate), this.E, this.bufferManager);
        this.economicsInfo.attachChild(text25);
        Game game17 = this.B;
        Text text26 = new Text(text25.getX() + text25.getWidthScaled() + 20.0f, 20.0f, game17.fonts.smallFont, game17.getActivity().getString(R.string.empire_tax_rate_desc), this.E, this.bufferManager);
        text26.setColor(0.6f, 0.6f, 0.6f);
        this.economicsInfo.attachChild(text26);
        TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager);
        this.selectedTaxRate = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(ButtonsEnum.PRESSED.ordinal());
        this.selectedTaxRate.setAlpha(0.5f);
        this.economicsInfo.attachChild(this.selectedTaxRate);
        int i = 0;
        while (i < 6) {
            this.percentButtons[i] = new TiledSprite((getWidth() - 730.0f) + (i * 120), 50.0f, this.B.graphics.buttonsTexture, this.bufferManager);
            this.percentButtons[i].setCurrentTileIndex(ButtonsEnum.BLANK.ordinal());
            this.economicsInfo.attachChild(this.percentButtons[i]);
            Font font = this.B.fonts.infoFont;
            Text text27 = new Text(0.0f, 0.0f, font, (i * 10) + "%", this.E, this.bufferManager);
            i++;
            text27.setX(((getWidth() - 790.0f) + ((float) (i * 120))) - (text27.getWidthScaled() / 2.0f));
            text27.setY(93.0f - (text27.getHeightScaled() / 2.0f));
            this.economicsInfo.attachChild(text27);
        }
    }

    private void createMilitaryInfo() {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager);
        tiledSprite.setSize(getWidth() - 10.0f, 200.0f);
        tiledSprite.setAlpha(0.2f);
        this.militaryInfo.attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(10.0f, 10.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        InfoIconEnum infoIconEnum = InfoIconEnum.COMMAND_POINTS;
        tiledSprite2.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite2.setSize(50.0f, 50.0f);
        this.militaryInfo.attachChild(tiledSprite2);
        Game game = this.B;
        this.militaryInfo.attachChild(new Text(70.0f, 25.0f, game.fonts.infoFont, game.getActivity().getString(R.string.empire_military), this.bufferManager));
        Text text = new Text(0.0f, 14.0f, this.B.fonts.smallInfoFont, "##########", this.bufferManager);
        this.availableCountText = text;
        this.militaryInfo.attachChild(text);
        TiledSprite tiledSprite3 = new TiledSprite(350.0f, 7.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.availableCommandPointsIcon = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(infoIconEnum.ordinal());
        this.militaryInfo.attachChild(this.availableCommandPointsIcon);
        float x = this.availableCommandPointsIcon.getX() + this.availableCommandPointsIcon.getWidthScaled() + 10.0f;
        Game game2 = this.B;
        this.militaryInfo.attachChild(new Text(x, 14.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.empire_command_points_available), this.bufferManager));
        Text text2 = new Text(0.0f, 42.0f, this.B.fonts.smallInfoFont, "##########", this.bufferManager);
        this.totalCountText = text2;
        this.militaryInfo.attachChild(text2);
        TiledSprite tiledSprite4 = new TiledSprite(350.0f, 35.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.totalCommandPointsIcon = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(infoIconEnum.ordinal());
        this.militaryInfo.attachChild(this.totalCommandPointsIcon);
        float x2 = this.availableCommandPointsIcon.getX() + this.availableCommandPointsIcon.getWidthScaled() + 10.0f;
        Game game3 = this.B;
        this.militaryInfo.attachChild(new Text(x2, 42.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.empire_command_points_total), this.bufferManager));
        Text text3 = new Text(0.0f, 14.0f, this.B.fonts.smallInfoFont, "##########", this.bufferManager);
        this.groundCombatPowerText = text3;
        this.militaryInfo.attachChild(text3);
        TiledSprite tiledSprite5 = new TiledSprite(650.0f, 7.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.groundCombatPointsIcon = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(InfoIconEnum.INFANTRY.ordinal());
        this.militaryInfo.attachChild(this.groundCombatPointsIcon);
        float x3 = this.groundCombatPointsIcon.getX() + this.groundCombatPointsIcon.getWidthScaled() + 10.0f;
        Game game4 = this.B;
        this.militaryInfo.attachChild(new Text(x3, 14.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.empire_troop_combat_strength), this.bufferManager));
        TiledSprite tiledSprite6 = new TiledSprite(getWidth() - 130.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager);
        this.fleetsButton = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(ButtonsEnum.FLEETS.ordinal());
        this.militaryInfo.attachChild(this.fleetsButton);
        TiledSprite tiledSprite7 = new TiledSprite(getWidth() - 250.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager);
        this.shipyardButton = tiledSprite7;
        tiledSprite7.setCurrentTileIndex(ButtonsEnum.SHIP_YARD.ordinal());
        this.militaryInfo.attachChild(this.shipyardButton);
        TiledSprite tiledSprite8 = new TiledSprite(25.0f, 90.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        tiledSprite8.setCurrentTileIndex(InfoIconEnum.SHIP.ordinal());
        tiledSprite8.setSize(50.0f, 50.0f);
        this.militaryInfo.attachChild(tiledSprite8);
        Text text4 = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.bufferManager);
        this.shipCount = text4;
        text4.setX(50.0f - (text4.getWidthScaled() / 2.0f));
        this.militaryInfo.attachChild(this.shipCount);
        for (int i = 0; i < 8; i++) {
            this.shipTypeIcons[i] = new TiledSprite((i * 100) + WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE, 90.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
            this.shipTypeIcons[i].setSize(50.0f, 50.0f);
            this.militaryInfo.attachChild(this.shipTypeIcons[i]);
            this.shipCounts[i] = new Text(0.0f, 150.0f, this.B.fonts.smallInfoFont, "##########", this.E, this.bufferManager);
            this.militaryInfo.attachChild(this.shipCounts[i]);
        }
    }

    private void createRaceInfo() {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager);
        tiledSprite.setCurrentTileIndex(5);
        tiledSprite.setSize(getWidth() - 10.0f, 200.0f);
        tiledSprite.setAlpha(0.2f);
        this.raceInfo.attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(10.0f, 10.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.POPULATION.ordinal());
        tiledSprite2.setSize(50.0f, 50.0f);
        this.raceInfo.attachChild(tiledSprite2);
        Game game = this.B;
        this.raceInfo.attachChild(new Text(70.0f, 25.0f, game.fonts.infoFont, game.getActivity().getString(R.string.empire_race_perks), this.bufferManager));
        RaceAttributesElement raceAttributesElement = new RaceAttributesElement(25.0f, 75.0f, this.B, this.bufferManager);
        this.raceInfo.attachChild(raceAttributesElement);
        this.raceInfoElements[0] = raceAttributesElement;
        RaceAttributesElement raceAttributesElement2 = new RaceAttributesElement(450.0f, 75.0f, this.B, this.bufferManager);
        this.raceInfo.attachChild(raceAttributesElement2);
        this.raceInfoElements[1] = raceAttributesElement2;
    }

    private void createResourcesInfo() {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager);
        tiledSprite.setCurrentTileIndex(3);
        tiledSprite.setSize(getWidth() - 10.0f, 150.0f);
        tiledSprite.setAlpha(0.3f);
        this.resourcesInfo.attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(10.0f, 10.0f, this.B.graphics.resourceIconsTexture, this.bufferManager);
        tiledSprite2.setCurrentTileIndex(ResourceID.WHEAT.ordinal() - 1);
        tiledSprite2.setSize(50.0f, 50.0f);
        this.resourcesInfo.attachChild(tiledSprite2);
        Game game = this.B;
        this.resourcesInfo.attachChild(new Text(70.0f, 25.0f, game.fonts.infoFont, game.getActivity().getString(R.string.empire_assets), this.bufferManager));
        Game game2 = this.B;
        Text text = new Text(25.0f, 90.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.empire_no_assets), this.bufferManager);
        this.noneText = text;
        this.resourcesInfo.attachChild(text);
        for (int i = 0; i < ResourceID.values().length - 1; i++) {
            this.resourceIcons[i] = new TiledSprite((i * 80) + 10, 65.0f, this.B.graphics.resourceIconsTexture, this.bufferManager);
            this.resourceIcons[i].setSize(50.0f, 50.0f);
            this.resourcesInfo.attachChild(this.resourceIcons[i]);
            this.resourceCounts[i] = new Text(0.0f, 125.0f, this.B.fonts.smallInfoFont, "########", this.E, this.bufferManager);
            this.resourcesInfo.attachChild(this.resourceCounts[i]);
        }
    }

    private void createTechInfo() {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager);
        tiledSprite.setCurrentTileIndex(2);
        tiledSprite.setSize(getWidth() - 10.0f, 200.0f);
        tiledSprite.setAlpha(0.2f);
        this.techInfo.attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(10.0f, 10.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
        tiledSprite2.setSize(50.0f, 50.0f);
        this.techInfo.attachChild(tiledSprite2);
        Game game = this.B;
        this.techInfo.attachChild(new Text(70.0f, 25.0f, game.fonts.infoFont, game.getActivity().getString(R.string.empire_tech), this.bufferManager));
        TiledSprite tiledSprite3 = new TiledSprite(getWidth() - 130.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager);
        this.techButton = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(ButtonsEnum.SCIENCE.ordinal());
        this.techInfo.attachChild(this.techButton);
        Text text = new Text(25.0f, 75.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.maxFuelRange = text;
        this.techInfo.attachChild(text);
        Text text2 = new Text(25.0f, 100.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.ftlSpeed = text2;
        this.techInfo.attachChild(text2);
        Text text3 = new Text(25.0f, 125.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.colonyScannerRange = text3;
        this.techInfo.attachChild(text3);
        Text text4 = new Text(625.0f, 75.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.shipScannerRange = text4;
        this.techInfo.attachChild(text4);
        Text text5 = new Text(625.0f, 100.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.shipCommunicationRange = text5;
        this.techInfo.attachChild(text5);
    }

    private void createVictoryInfo() {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager);
        tiledSprite.setCurrentTileIndex(6);
        tiledSprite.setSize(getWidth() - 10.0f, 150.0f);
        tiledSprite.setAlpha(0.3f);
        this.victoryInfo.attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(10.0f, 10.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.CAPITAL.ordinal());
        tiledSprite2.setSize(50.0f, 50.0f);
        this.victoryInfo.attachChild(tiledSprite2);
        Game game = this.B;
        this.victoryInfo.attachChild(new Text(70.0f, 25.0f, game.fonts.infoFont, game.getActivity().getString(R.string.empire_victory), this.bufferManager));
        Game game2 = this.B;
        this.victoryInfo.attachChild(new Text(70.0f, 80.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.empire_military_victory), this.bufferManager));
        TiledSprite tiledSprite3 = new TiledSprite(70.0f, 110.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.OFF.ordinal());
        tiledSprite3.setSize(20.0f, 20.0f);
        this.victoryInfo.attachChild(tiledSprite3);
        Text text = new Text(100.0f, 112.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.militaryVictoryCondition = text;
        this.victoryInfo.attachChild(text);
        TiledSprite tiledSprite4 = new TiledSprite(630.0f, 40.0f, this.B.graphics.empireColors, this.bufferManager);
        this.coalitionBackground = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(1);
        this.coalitionBackground.setSize(600.0f, 100.0f);
        this.coalitionBackground.setAlpha(0.3f);
        this.victoryInfo.attachChild(this.coalitionBackground);
        Game game3 = this.B;
        Text text2 = new Text(640.0f, 50.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.empire_coalition_victory), this.bufferManager);
        this.coalitionVictory = text2;
        this.victoryInfo.attachChild(text2);
        TiledSprite tiledSprite5 = new TiledSprite(640.0f, 80.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.coalition1Checkbox = tiledSprite5;
        tiledSprite5.setSize(20.0f, 20.0f);
        this.victoryInfo.attachChild(this.coalition1Checkbox);
        Game game4 = this.B;
        Text text3 = new Text(670.0f, 82.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.empire_coalition_victory_condition1), this.bufferManager);
        this.coalition1Condition = text3;
        this.victoryInfo.attachChild(text3);
        TiledSprite tiledSprite6 = new TiledSprite(640.0f, 105.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.coalition2Checkbox = tiledSprite6;
        tiledSprite6.setSize(20.0f, 20.0f);
        this.victoryInfo.attachChild(this.coalition2Checkbox);
        Game game5 = this.B;
        Text text4 = new Text(670.0f, 107.0f, game5.fonts.smallInfoFont, game5.getActivity().getString(R.string.empire_coalition_victory_condition2), this.bufferManager);
        this.coalition2Condition = text4;
        this.victoryInfo.attachChild(text4);
        TiledSprite tiledSprite7 = new TiledSprite(getWidth() - 130.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager);
        this.historyGraphButton = tiledSprite7;
        tiledSprite7.setCurrentTileIndex(ButtonsEnum.HISTORY.ordinal());
        this.victoryInfo.attachChild(this.historyGraphButton);
    }

    private void fleetsButtonPressed() {
        changeScene(this.B.fleetsScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void galaxyButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        changeScene(this.B.galaxyScene);
    }

    private void historyGraphButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.statsScene.set(this, false);
        changeScene(this.B.statsScene);
    }

    private boolean isButtonOnInfoPressed(Point point, Entity entity, TiledSprite tiledSprite) {
        float x = point.getX() - entity.getX();
        float y = (point.getY() - entity.getY()) - this.empireInfo.getY();
        return x > tiledSprite.getX() && x < tiledSprite.getX() + tiledSprite.getWidthScaled() && y > tiledSprite.getY() && y < tiledSprite.getY() + tiledSprite.getWidthScaled();
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void migrantsButtonPressed() {
        this.migrantListOverlay.setOverlay(this.empire.id);
        setChildScene(this.migrantListOverlay, false, false, true);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void resetScrollList() {
        this.empireInfo.setY(86.0f);
        setScrollBar();
    }

    private void resourceIconPressed(Entity entity) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ResourceID.values()[((TiledSprite) entity).getCurrentTileIndex() + 1]);
        showMessage(new ResourcesMessage(arrayList));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void set() {
        this.nebulas.set();
        Empire currentEmpire = this.B.getCurrentEmpire();
        this.empire = currentEmpire;
        this.empireBackground.setCurrentTileIndex(currentEmpire.id);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.empire.id));
        this.empireName.setText(this.empire.getName());
        Text text = this.empireName;
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
        this.warningIcon.setVisible(false);
        this.warningText.setVisible(false);
        if (!this.empire.hasCapital()) {
            this.warningIcon.setVisible(true);
            this.warningText.setVisible(true);
            this.warningText.setText(this.B.getActivity().getString(R.string.empire_no_capital_warning));
            this.warningText.setX((getWidth() - 140.0f) - this.warningText.getWidthScaled());
            Text text2 = this.warningText;
            text2.setY(43.0f - (text2.getHeightScaled() / 2.0f));
            this.warningIcon.setX((this.warningText.getX() - this.warningIcon.getWidthScaled()) - 10.0f);
        }
        setVictoryInfo();
        setResourcesInfo();
        setEconomicsInfo();
        setColoniesInfo();
        setMilitaryInfo();
        setTechInfo();
        setRaceInfo();
        this.galaxyExplored.setText(this.B.getActivity().getString(R.string.empire_explored_percent, new Object[]{Integer.valueOf(this.empire.getPercentOfGalaxyExplored())}));
        this.galaxyExplored.setX((getWidth() - this.galaxyExplored.getWidthScaled()) - 20.0f);
        resetScrollList();
    }

    private void setColoniesInfo() {
        this.importedCountText.setText(Integer.toString(this.empire.getTotalImportedFoodPerTurn()));
        this.importedCountText.setX((this.importedFoodIcon.getX() - this.importedCountText.getWidthScaled()) - 10.0f);
        this.shippingCostText.setText(Integer.toString(this.empire.getCostOfImportedFood()));
        this.shippingCostText.setX((this.shippingCostIcon.getX() - this.shippingCostText.getWidthScaled()) - 10.0f);
        float x = this.importedFoodIcon.getX() + this.importedFoodIcon.getWidthScaled() + 10.0f;
        Game game = this.B;
        this.coloniesInfo.attachChild(new Text(x, 42.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.empire_shipping_costs), this.bufferManager));
        Text text = this.populationInTransit;
        text.setText(this.empire.getPopulationInTransit() + "m");
        this.populationInTransit.setX((this.populationIcon.getX() - this.populationInTransit.getWidthScaled()) - 10.0f);
        this.coloniesCount.setText(Integer.toString(this.empire.getColonies().size()));
        Text text2 = this.coloniesCount;
        text2.setX(50.0f - (text2.getWidthScaled() / 2.0f));
        this.populationCount.setText(this.empire.getTotalPopulation() + "m");
        Text text3 = this.populationCount;
        text3.setX(150.0f - (text3.getWidthScaled() / 2.0f));
        Game game2 = this.B;
        this.outpostCount.setText(Integer.toString(game2.outposts.getCountOfOutpostAroundPlanets(game2.getCurrentPlayer())));
        Text text4 = this.outpostCount;
        text4.setX(250.0f - (text4.getWidthScaled() / 2.0f));
        Game game3 = this.B;
        this.miningOutpostCount.setText(Integer.toString(game3.outposts.getCountOfMiningStations(game3.getCurrentPlayer())));
        Text text5 = this.miningOutpostCount;
        text5.setX(350.0f - (text5.getWidthScaled() / 2.0f));
        Game game4 = this.B;
        this.researchOutpostCount.setText(Integer.toString(game4.outposts.getCountOfResearchStations(game4.getCurrentPlayer())));
        Text text6 = this.researchOutpostCount;
        text6.setX(450.0f - (text6.getWidthScaled() / 2.0f));
        float f2 = this.empire.getMigrants().isEmpty() ? 0.4f : 1.0f;
        this.migrantsButton.setAlpha(f2);
        this.migrantsButtonIcon.setAlpha(f2);
    }

    private void setEconomicsInfo() {
        Empire.FinancialInfo financialDetails = this.empire.getFinancialDetails();
        this.revenueValueWidth = 0.0f;
        String num = Integer.toString(financialDetails.populationTaxes);
        if (financialDetails.populationTaxes != 0) {
            num = "+" + num;
        }
        this.populationTaxesValue.setText(num);
        if (this.populationTaxesValue.getWidthScaled() > this.revenueValueWidth) {
            this.revenueValueWidth = this.populationTaxesValue.getWidthScaled();
        }
        float f2 = financialDetails.populationTaxes == 0 ? 0.4f : 1.0f;
        this.populationTaxes.setAlpha(f2);
        this.populationTaxesValue.setAlpha(f2);
        String num2 = Integer.toString(financialDetails.industrialTaxes);
        if (financialDetails.industrialTaxes != 0) {
            num2 = "+" + num2;
        }
        this.productionTaxesValue.setText(num2);
        if (this.productionTaxesValue.getWidthScaled() > this.revenueValueWidth) {
            this.revenueValueWidth = this.productionTaxesValue.getWidthScaled();
        }
        float f3 = financialDetails.industrialTaxes == 0 ? 0.4f : 1.0f;
        this.productionTaxes.setAlpha(f3);
        this.productionTaxesValue.setAlpha(f3);
        String num3 = Integer.toString(financialDetails.tradegoods);
        if (financialDetails.tradegoods != 0) {
            num3 = "+" + num3;
        }
        this.tradegoodsValue.setText(num3);
        if (this.tradegoodsValue.getWidthScaled() > this.revenueValueWidth) {
            this.revenueValueWidth = this.tradegoodsValue.getWidthScaled();
        }
        float f4 = financialDetails.tradegoods == 0 ? 0.4f : 1.0f;
        this.tradegoods.setAlpha(f4);
        this.tradegoodsValue.setAlpha(f4);
        String num4 = Integer.toString(financialDetails.extraFoodSale);
        if (financialDetails.extraFoodSale != 0) {
            num4 = "+" + num4;
        }
        this.extraFoodSaleValue.setText(num4);
        if (this.extraFoodSaleValue.getWidthScaled() > this.revenueValueWidth) {
            this.revenueValueWidth = this.extraFoodSaleValue.getWidthScaled();
        }
        float f5 = financialDetails.extraFoodSale == 0 ? 0.4f : 1.0f;
        this.extraFoodSale.setAlpha(f5);
        this.extraFoodSaleValue.setAlpha(f5);
        String num5 = Integer.toString(financialDetails.revenueFromResources);
        if (financialDetails.revenueFromResources != 0) {
            num5 = "+" + num5;
        }
        this.revenueFromResourcesValue.setText(num5);
        if (this.revenueFromResourcesValue.getWidthScaled() > this.revenueValueWidth) {
            this.revenueValueWidth = this.revenueFromResourcesValue.getWidthScaled();
        }
        float f6 = financialDetails.revenueFromResources == 0 ? 0.4f : 1.0f;
        this.revenueFromResources.setAlpha(f6);
        this.revenueFromResourcesValue.setAlpha(f6);
        String num6 = Integer.toString(financialDetails.treatiesRevenue);
        if (financialDetails.treatiesRevenue != 0) {
            num6 = "+" + num6;
        }
        this.treatiesRevenueValue.setText(num6);
        if (this.treatiesRevenueValue.getWidthScaled() > this.revenueValueWidth) {
            this.revenueValueWidth = this.treatiesRevenueValue.getWidthScaled();
        }
        float f7 = financialDetails.treatiesRevenue == 0 ? 0.4f : 1.0f;
        this.treatiesRevenue.setAlpha(f7);
        this.treatiesRevenueValue.setAlpha(f7);
        float f8 = this.revenueWidth + 25.0f + 40.0f + this.revenueValueWidth;
        Text text = this.populationTaxesValue;
        text.setX(f8 - text.getWidthScaled());
        Text text2 = this.productionTaxesValue;
        text2.setX(f8 - text2.getWidthScaled());
        Text text3 = this.tradegoodsValue;
        text3.setX(f8 - text3.getWidthScaled());
        Text text4 = this.extraFoodSaleValue;
        text4.setX(f8 - text4.getWidthScaled());
        Text text5 = this.revenueFromResourcesValue;
        text5.setX(f8 - text5.getWidthScaled());
        Text text6 = this.treatiesRevenueValue;
        text6.setX(f8 - text6.getWidthScaled());
        float f9 = f8 + 20.0f;
        this.expense.setX(f9);
        this.expenseLine.setPosition(this.expense.getX(), 140.0f, this.expense.getX() + this.expense.getWidthScaled(), 140.0f);
        this.buildingsCost.setX(f9);
        this.foodShippingCosts.setX(f9);
        this.fleetMaintenance.setX(f9);
        this.treatiesExpense.setX(f9);
        this.expenseValueWidth = 0.0f;
        String num7 = Integer.toString(financialDetails.buildingsMaintenanceCosts);
        if (financialDetails.buildingsMaintenanceCosts != 0) {
            num7 = "-" + num7;
        }
        this.buildingsCostValue.setText(num7);
        if (this.buildingsCostValue.getWidthScaled() > this.expenseValueWidth) {
            this.expenseValueWidth = this.buildingsCostValue.getWidthScaled();
        }
        float f10 = financialDetails.buildingsMaintenanceCosts == 0 ? 0.4f : 1.0f;
        this.buildingsCost.setAlpha(f10);
        this.buildingsCostValue.setAlpha(f10);
        String num8 = Integer.toString(financialDetails.importCosts);
        if (financialDetails.importCosts != 0) {
            num8 = "-" + num8;
        }
        this.foodShippingCostsValue.setText(num8);
        if (this.foodShippingCostsValue.getWidthScaled() > this.expenseValueWidth) {
            this.expenseValueWidth = this.foodShippingCostsValue.getWidthScaled();
        }
        float f11 = financialDetails.importCosts == 0 ? 0.4f : 1.0f;
        this.foodShippingCosts.setAlpha(f11);
        this.foodShippingCostsValue.setAlpha(f11);
        this.fleetMaintenanceValue.setText(Integer.toString(financialDetails.commandPointCost));
        if (this.fleetMaintenanceValue.getWidthScaled() > this.expenseValueWidth) {
            this.expenseValueWidth = this.fleetMaintenanceValue.getWidthScaled();
        }
        float f12 = financialDetails.commandPointCost == 0 ? 0.4f : 1.0f;
        this.fleetMaintenance.setAlpha(f12);
        this.fleetMaintenanceValue.setAlpha(f12);
        this.treatiesExpenseValue.setText(Integer.toString(financialDetails.treatiesExpense));
        if (this.treatiesExpenseValue.getWidthScaled() > this.expenseValueWidth) {
            this.expenseValueWidth = this.treatiesExpenseValue.getWidthScaled();
        }
        float f13 = financialDetails.treatiesExpense != 0 ? 1.0f : 0.4f;
        this.treatiesExpense.setAlpha(f13);
        this.treatiesExpenseValue.setAlpha(f13);
        float x = this.expense.getX() + this.expenseWidth + 20.0f + this.expenseValueWidth;
        Text text7 = this.buildingsCostValue;
        text7.setX(x - text7.getWidthScaled());
        Text text8 = this.foodShippingCostsValue;
        text8.setX(x - text8.getWidthScaled());
        Text text9 = this.fleetMaintenanceValue;
        text9.setX(x - text9.getWidthScaled());
        Text text10 = this.treatiesExpenseValue;
        text10.setX(x - text10.getWidthScaled());
        setSelectedTaxRate();
    }

    private void setMilitaryInfo() {
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            this.shipTypeIcons[i2].setVisible(false);
            this.shipCounts[i2].setVisible(false);
        }
        this.availableCountText.setText(Integer.toString(this.empire.getAvailableCommandPoints()));
        this.availableCountText.setX((this.availableCommandPointsIcon.getX() - this.availableCountText.getWidthScaled()) - 10.0f);
        this.totalCountText.setText(Integer.toString(this.empire.getCommandPoints()));
        this.totalCountText.setX((this.totalCommandPointsIcon.getX() - this.totalCountText.getWidthScaled()) - 10.0f);
        this.groundCombatPowerText.setText(Integer.toString(this.empire.getGroundCombatStrength()));
        this.groundCombatPowerText.setX((this.groundCombatPointsIcon.getX() - this.groundCombatPowerText.getWidthScaled()) - 10.0f);
        Game game = this.B;
        this.shipCount.setText(Integer.toString(game.fleets.getTotalShipCount(game.getCurrentPlayer())));
        Text text = this.shipCount;
        text.setX(50.0f - (text.getWidthScaled() / 2.0f));
        Game game2 = this.B;
        int[] countOfEachType = game2.fleets.getCountOfEachType(game2.getCurrentPlayer());
        for (int i3 = 7; i3 > -1; i3--) {
            ShipType shipType = ShipType.getShipType(i3);
            if (countOfEachType[shipType.id] != 0) {
                this.shipTypeIcons[i].setVisible(true);
                this.shipTypeIcons[i].setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
                this.shipCounts[i].setVisible(true);
                this.shipCounts[i].setText(Integer.toString(countOfEachType[shipType.id]));
                Text[] textArr = this.shipCounts;
                textArr[i].setX(((i * 100) + WeaponStats.SPACIAL_CHARGE_SPEED) - (textArr[i].getWidthScaled() / 2.0f));
                i++;
            }
        }
    }

    private void setRaceInfo() {
        List<RaceAttribute> raceAttributes = this.empire.getRaceAttributes();
        ArrayList arrayList = new ArrayList();
        if (!raceAttributes.isEmpty()) {
            arrayList.add(raceAttributes.get(0));
        }
        this.raceInfoElements[0].set(arrayList);
        ArrayList arrayList2 = new ArrayList();
        if (raceAttributes.size() > 1) {
            arrayList2.add(raceAttributes.get(1));
        }
        this.raceInfoElements[1].set(arrayList2);
    }

    private void setResourcesInfo() {
        this.noneText.setVisible(this.empire.getResources().isEmpty());
        int i = 0;
        for (int i2 = 0; i2 < ResourceID.values().length - 1; i2++) {
            this.resourceIcons[i2].setVisible(false);
            this.resourceCounts[i2].setVisible(false);
        }
        for (Map.Entry<ResourceID, Integer> entry : this.empire.getResources().entrySet()) {
            this.resourceIcons[i].setCurrentTileIndex(entry.getKey().ordinal() - 1);
            this.resourceIcons[i].setVisible(true);
            this.resourceCounts[i].setText(Integer.toString(entry.getValue().intValue()));
            Text[] textArr = this.resourceCounts;
            textArr[i].setX(((i * 80) + 35) - (textArr[i].getWidthScaled() / 2.0f));
            this.resourceCounts[i].setVisible(true);
            i++;
        }
    }

    private void setScrollBar() {
        this.scrollBar1.setHeight(275.31232f);
        this.scrollBar2.setHeight(275.31232f);
        float y = ((((this.empireInfo.getY() - 86.0f) * (-1.0f)) / 1460.0f) * 634.0f) + 86.0f;
        this.scrollBar1.setY(y);
        this.scrollBar2.setY(y);
    }

    private void setSelectedTaxRate() {
        int taxRate = (int) (this.empire.getTaxRate() * 10.0f);
        this.selectedTaxRate.setPosition(this.percentButtons[taxRate].getX(), this.percentButtons[taxRate].getY());
        int creditsPerTurn = this.empire.getCreditsPerTurn();
        String num = Integer.toString(creditsPerTurn);
        if (creditsPerTurn > -1) {
            num = "+" + num;
        }
        this.creditsPerTurnText.setText(num);
        this.creditsPerTurnText.setX((this.creditsPerTurnIcon.getX() - this.creditsPerTurnText.getWidthScaled()) - 10.0f);
        this.totalCreditsText.setText(Integer.toString(this.empire.getCredits()));
        this.totalCreditsText.setX((this.totalCreditsIcon.getX() - this.totalCreditsText.getWidthScaled()) - 10.0f);
    }

    private void setTechInfo() {
        String num = Integer.toString(this.empire.getTech().getFuelCellRange());
        if (this.empire.getTech().getFuelCellRange() == 1000) {
            num = this.B.getActivity().getString(R.string.empire_tech_fuel_unlimited);
        }
        this.maxFuelRange.setText(this.B.getActivity().getString(R.string.empire_tech_fuel_range, new Object[]{num}));
        this.ftlSpeed.setText(this.B.getActivity().getString(R.string.empire_tech_ftl_speed, new Object[]{Integer.valueOf(this.empire.getTech().getEngineSpeed())}));
        this.colonyScannerRange.setText(this.B.getActivity().getString(R.string.empire_tech_colony_scanning_range, new Object[]{Integer.valueOf(this.empire.getTech().getColonyScanningRange())}));
        this.shipScannerRange.setText(this.B.getActivity().getString(R.string.empire_tech_ship_scanning_range, new Object[]{Integer.valueOf(this.empire.getTech().getShipScanningRange())}));
        this.shipCommunicationRange.setText(this.B.getActivity().getString(R.string.empire_tech_ship_communication_range, new Object[]{Integer.valueOf(this.empire.getTech().getShipCommunicationRange())}));
    }

    private void setVictoryInfo() {
        int i = -1;
        for (Empire empire : this.B.empires.getEmpires()) {
            if (empire.isAlive()) {
                i++;
            }
        }
        boolean z = true;
        this.militaryVictoryCondition.setText(this.B.getActivity().getString(R.string.empire_military_victory_condition1, new Object[]{Integer.valueOf(i)}));
        this.coalitionBackground.setVisible(this.empire.getEmpireSetting("seen_coalition_victory") == 1);
        if (GameData.gameSettings.isAlwaysAtWar()) {
            this.coalitionBackground.setVisible(false);
            z = false;
        }
        this.coalitionVictory.setVisible(z);
        this.coalition1Condition.setVisible(z);
        this.coalition1Checkbox.setVisible(z);
        this.coalition2Condition.setVisible(z);
        this.coalition2Checkbox.setVisible(z);
        if (GameData.allEmpiresStillAlive()) {
            this.coalition1Checkbox.setCurrentTileIndex(InfoIconEnum.OFF.ordinal());
        } else {
            this.coalition1Checkbox.setCurrentTileIndex(InfoIconEnum.ON.ordinal());
        }
        if (GameData.allEmpiresAllied()) {
            this.coalition2Checkbox.setCurrentTileIndex(InfoIconEnum.ON.ordinal());
        } else {
            this.coalition2Checkbox.setCurrentTileIndex(InfoIconEnum.OFF.ordinal());
        }
    }

    private void shipyardButtonPressed() {
        changeScene(this.B.shipDesignScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void taxRateButtonPressed(int i) {
        Empire empire = this.empire;
        double d2 = i;
        Double.isNaN(d2);
        empire.setTaxRate((float) (d2 * 0.1d));
        setSelectedTaxRate();
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        Empire.FinancialInfo financialDetails = this.empire.getFinancialDetails();
        String num = Integer.toString(financialDetails.industrialTaxes);
        if (financialDetails.industrialTaxes != 0) {
            num = "+" + num;
        }
        float f2 = financialDetails.industrialTaxes == 0 ? 0.4f : 1.0f;
        this.productionTaxes.setAlpha(f2);
        this.productionTaxesValue.setAlpha(f2);
        this.productionTaxesValue.setText(num);
        Text text = this.productionTaxesValue;
        text.setX((((this.revenueWidth + 25.0f) + 40.0f) + this.revenueValueWidth) - text.getWidthScaled());
        String num2 = Integer.toString(financialDetails.tradegoods);
        if (financialDetails.tradegoods != 0) {
            num2 = "+" + num2;
        }
        this.tradegoodsValue.setText(num2);
        float f3 = financialDetails.tradegoods == 0 ? 0.4f : 1.0f;
        this.tradegoods.setAlpha(f3);
        this.tradegoodsValue.setAlpha(f3);
        String num3 = Integer.toString(financialDetails.treatiesExpense);
        float f4 = financialDetails.treatiesExpense != 0 ? 1.0f : 0.4f;
        this.treatiesExpense.setAlpha(f4);
        this.treatiesExpenseValue.setAlpha(f4);
        this.treatiesExpenseValue.setText(num3);
        this.treatiesExpenseValue.setX((((this.expense.getX() + this.expenseWidth) + 20.0f) + this.expenseValueWidth) - this.treatiesExpenseValue.getWidthScaled());
    }

    private void techButtonPressed() {
        this.B.techScene.set();
        changeScene(this.B.techScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof ColoniesScene) {
            Game game = this.B;
            game.coloniesScene.load(game.getCurrentPlayer());
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof ShipDesignScene) {
            Game game2 = this.B;
            game2.shipDesignScene.set(game2.getCurrentPlayer());
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        changeScene(this.B.galaxyScene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
        this.bufferManager = vertexBufferObjectManager;
        this.nebulas = this.B.nebulas;
        this.nebulaBackground = new Entity();
        if (getWidth() == 1480.0f) {
            this.nebulaBackground.setX(100.0f);
        }
        attachChild(this.nebulaBackground);
        Scene scene = new Scene();
        this.empireInfo = scene;
        scene.setPosition(0.0f, 86.0f);
        this.empireInfo.setBackgroundEnabled(false);
        attachChild(this.empireInfo);
        TiledSprite tiledSprite = new TiledSprite(getWidth() - 7.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.scrollBar1 = tiledSprite;
        tiledSprite.setCurrentTileIndex(3);
        this.scrollBar1.setWidth(6.0f);
        attachChild(this.scrollBar1);
        TiledSprite tiledSprite2 = new TiledSprite(getWidth() - 7.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.scrollBar2 = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(3);
        this.scrollBar2.setWidth(6.0f);
        attachChild(this.scrollBar2);
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.PRESSED;
        this.infoButtonPress = H(0.0f, 0.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        E(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        TiledSprite J = J(3.0f, 3.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireBackground = J;
        J.setSize(80.0f, 80.0f);
        TiledSprite J2 = J(3.0f, 3.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.empireBanner = J2;
        J2.setSize(80.0f, 80.0f);
        this.empireName = G(100.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, true);
        TiledSprite H = H(0.0f, 25.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.WARNING.ordinal(), false);
        this.warningIcon = H;
        blinkSprite(H);
        Text G = G(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, false);
        this.warningText = G;
        G.setColor(Color.RED);
        v(this.warningText);
        this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        this.victoryInfo = new Entity(0.0f, 10.0f);
        createVictoryInfo();
        this.empireInfo.attachChild(this.victoryInfo);
        this.economicsInfo = new Entity(0.0f, 170.0f);
        createEconomicInfo();
        this.empireInfo.attachChild(this.economicsInfo);
        this.resourcesInfo = new Entity(0.0f, 430.0f);
        createResourcesInfo();
        this.empireInfo.attachChild(this.resourcesInfo);
        this.coloniesInfo = new Entity(0.0f, 590.0f);
        createColoniesInfo();
        this.empireInfo.attachChild(this.coloniesInfo);
        this.militaryInfo = new Entity(0.0f, 800.0f);
        createMilitaryInfo();
        this.empireInfo.attachChild(this.militaryInfo);
        this.techInfo = new Entity(0.0f, 1010.0f);
        createTechInfo();
        this.empireInfo.attachChild(this.techInfo);
        this.raceInfo = new Entity(0.0f, 1220.0f);
        createRaceInfo();
        this.empireInfo.attachChild(this.raceInfo);
        Text text = new Text(5.0f, 1425.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        this.timePlayed = text;
        this.empireInfo.attachChild(text);
        Text text2 = new Text(0.0f, 1425.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        this.galaxyExplored = text2;
        this.empireInfo.attachChild(text2);
        TiledSprite H2 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H2;
        s(H2);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
        this.migrantListOverlay = new MigrantListOverlay(this.B, vertexBufferObjectManager, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.i
            {
                EmpireScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                EmpireScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
        set();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        this.timePlayed.setText(this.B.getActivity().getString(R.string.empire_time_played, new Object[]{Functions.convertSecondsIntoHMS(this.B.getTimePlayed() / 1000, true)}));
    }
}
