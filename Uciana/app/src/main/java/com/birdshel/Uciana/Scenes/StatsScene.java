package com.birdshel.Uciana.Scenes;

import android.util.SparseArray;
import android.util.SparseIntArray;

import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.SpyNetworkNeededMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.StarSystems.StarType;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class StatsScene extends ExtendedScene {
    private TiledSprite backButton;
    private TiledSprite chartButtonSelected;
    private Text chartTitle;
    private TiledSprite coloniesButton;
    private TiledSprite commandPointsButton;
    private TiledSprite creditsPerTurnButton;
    private Text endTurnLabel;
    private TiledSprite foodButton;
    private Text largestAmountLabel;
    private Text lowestAmountLabel;
    private TiledSprite populationButton;
    private TiledSprite productionButton;
    private TiledSprite scienceButton;
    private ExtendedScene source;
    private TiledSprite systemsButton;
    private TiledSprite techButton;
    private Line zeroLine;
    private ArrayList<ArrayList<Line>> empireLinesList = new ArrayList<>();
    private int selectedChart = 0;
    private final EmpireButton[] empireButtons = new EmpireButton[7];
    private final TiledSprite[] selectedEmpires = new TiledSprite[7];

    public StatsScene(Game game) {
        this.B = game;
    }

    private void backButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void chartButtonPressed(TiledSprite tiledSprite, int i) {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.chartButtonSelected.setX(tiledSprite.getX());
        setChart(i);
    }

    private void checkActionUp(Point point) {
        EmpireButton[] empireButtonArr;
        TiledSprite[] tiledSpriteArr;
        if (isClicked(this.backButton, point)) {
            backButtonPressed();
        }
        if (isClicked(this.populationButton, point)) {
            chartButtonPressed(this.populationButton, 0);
        }
        if (isClicked(this.systemsButton, point)) {
            chartButtonPressed(this.systemsButton, 2);
        }
        if (isClicked(this.coloniesButton, point)) {
            chartButtonPressed(this.coloniesButton, 1);
        }
        if (isClicked(this.commandPointsButton, point)) {
            chartButtonPressed(this.commandPointsButton, 3);
        }
        if (isClicked(this.foodButton, point)) {
            chartButtonPressed(this.foodButton, 4);
        }
        if (isClicked(this.productionButton, point)) {
            chartButtonPressed(this.productionButton, 5);
        }
        if (isClicked(this.scienceButton, point)) {
            chartButtonPressed(this.scienceButton, 6);
        }
        if (isClicked(this.techButton, point)) {
            chartButtonPressed(this.techButton, 7);
        }
        if (isClicked(this.creditsPerTurnButton, point)) {
            chartButtonPressed(this.creditsPerTurnButton, 8);
        }
        int i = 0;
        for (EmpireButton empireButton : this.empireButtons) {
            if (isClicked(empireButton, point)) {
                this.selectedEmpires[i].setVisible(!tiledSpriteArr[i].isVisible());
                setChart(this.selectedChart);
                return;
            } else if (A(empireButton, point, 0.0f, 0.0f)) {
                showMessage(new SpyNetworkNeededMessage(empireButton.getEmpireID()));
                return;
            } else {
                i++;
            }
        }
    }

    private void clearChart() {
        Iterator<ArrayList<Line>> it = this.empireLinesList.iterator();
        while (it.hasNext()) {
            Iterator<Line> it2 = it.next().iterator();
            while (it2.hasNext()) {
                it2.next().setVisible(false);
            }
        }
    }

    private SparseIntArray getChartHistory(int i) {
        Empire empire = this.B.empires.get(i);
        switch (this.selectedChart) {
            case 0:
                return empire.getPopulationHistory();
            case 1:
                return empire.getColoniesCountHistory();
            case 2:
                return empire.getSystemsCountHistory();
            case 3:
                return empire.getCommandPointUsageHistory();
            case 4:
                return empire.getFoodHistory();
            case 5:
                return empire.getProductionHistory();
            case 6:
                return empire.getScienceHistory();
            case 7:
                return empire.getTechHistory();
            case 8:
                return empire.getCreditsPerTurnHistory();
            default:
                return new SparseIntArray();
        }
    }

    private void setChart(int i) {
        EmpireButton[] empireButtonArr;
        this.selectedChart = i;
        clearChart();
        ArrayList<Integer> arrayList = new ArrayList();
        int i2 = 0;
        for (EmpireButton empireButton : this.empireButtons) {
            if (empireButton.isVisible() && this.selectedEmpires[i2].isVisible()) {
                arrayList.add(Integer.valueOf(empireButton.getEmpireID()));
            }
            i2++;
        }
        int i3 = R.string.history_chart_0;
        switch (i) {
            case 1:
                i3 = R.string.history_chart_1;
                break;
            case 2:
                i3 = R.string.history_chart_2;
                break;
            case 3:
                i3 = R.string.history_chart_3;
                break;
            case 4:
                i3 = R.string.history_chart_4;
                break;
            case 5:
                i3 = R.string.history_chart_5;
                break;
            case 6:
                i3 = R.string.history_chart_6;
                break;
            case 7:
                i3 = R.string.history_chart_7;
                break;
            case 8:
                i3 = R.string.history_chart_8;
                break;
        }
        this.chartTitle.setText(this.B.getActivity().getString(i3));
        this.chartTitle.setX((getWidth() / 2.0f) - (this.chartTitle.getWidthScaled() / 2.0f));
        if (arrayList.isEmpty()) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (Integer num : arrayList) {
            int intValue = num.intValue();
            SparseIntArray chartHistory = getChartHistory(intValue);
            sparseArray.put(intValue, chartHistory);
            if (chartHistory.size() != 0) {
                int keyAt = chartHistory.keyAt(chartHistory.size() - 1);
                if (keyAt > i4) {
                    i4 = keyAt;
                }
                for (int i7 = 0; i7 < chartHistory.size(); i7++) {
                    if (chartHistory.get(chartHistory.keyAt(i7)) > i5) {
                        i5 = chartHistory.get(chartHistory.keyAt(i7));
                    }
                    if (chartHistory.get(chartHistory.keyAt(i7)) < i6) {
                        i6 = chartHistory.get(chartHistory.keyAt(i7));
                    }
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (i4 > 100) {
            float f2 = i4 / 100.0f;
            for (int i8 = 0; i8 < 100; i8++) {
                arrayList2.add(Integer.valueOf((int) Math.floor(i8 * f2)));
            }
        } else {
            for (int i9 = 1; i9 <= i4; i9++) {
                arrayList2.add(Integer.valueOf(i9));
            }
        }
        for (Integer num2 : arrayList) {
            int intValue2 = num2.intValue();
            setChartForEmpire(intValue2, (SparseIntArray) sparseArray.get(intValue2), i6, i5, arrayList2);
        }
        if (i5 == 0) {
            this.largestAmountLabel.setVisible(false);
        } else {
            this.largestAmountLabel.setVisible(true);
            this.largestAmountLabel.setText(Integer.toString(i5));
        }
        this.lowestAmountLabel.setText(Integer.toString(i6));
        Text text = this.lowestAmountLabel;
        text.setY(600.0f - text.getHeightScaled());
    }

    private void setChartForEmpire(int i, SparseIntArray sparseIntArray, int i2, int i3, List<Integer> list) {
        if (sparseIntArray.size() == 0) {
            return;
        }
        float width = (getWidth() - 40.0f) / list.size();
        float f2 = 0.0f;
        float f3 = 450.0f;
        float f4 = i3 != 0 ? 450.0f / (i3 - i2) : 0.0f;
        float f5 = i2 * f4;
        this.zeroLine.setY(f5 + 450.0f);
        int i4 = 0;
        float f6 = -1.0f;
        while (i4 < list.size()) {
            int intValue = list.get(i4).intValue();
            int i5 = sparseIntArray.indexOfKey(intValue) > -1 ? sparseIntArray.get(intValue) : 0;
            this.empireLinesList.get(i).get(i4).setVisible(true);
            int i6 = i4 + 1;
            float f7 = i6 * width;
            float f8 = (f3 - (i5 * f4)) + f5;
            if (f6 == -1.0f) {
                f6 = f8;
            }
            this.empireLinesList.get(i).get(i4).setPosition(f2 + 40.0f, f6, f7 + 40.0f, f8);
            f6 = f8;
            i4 = i6;
            f2 = f7;
            f3 = 450.0f;
        }
    }

    private void setEmpireButtons(boolean z) {
        for (EmpireButton empireButton : this.empireButtons) {
            empireButton.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.selectedEmpires) {
            tiledSprite.setVisible(false);
        }
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (showEmpireChartButton(i2, z)) {
                EmpireButton empireButton2 = this.empireButtons[i];
                empireButton2.setVisible(true);
                empireButton2.setAlpha(1.0f);
                this.selectedEmpires[i].setVisible(true);
                empireButton2.set(i2);
                if (shouldEmpireChartButtonBeDisabled(i2, z)) {
                    empireButton2.setAlpha(0.4f);
                    this.selectedEmpires[i].setVisible(false);
                }
                i++;
            }
        }
        int i3 = 0;
        for (EmpireButton empireButton3 : this.empireButtons) {
            if (empireButton3.isVisible()) {
                i3++;
            }
        }
        int width = (((int) getWidth()) / 2) - ((i3 * 120) / 2);
        int i4 = 0;
        for (EmpireButton empireButton4 : this.empireButtons) {
            float f2 = width;
            empireButton4.setX(f2);
            this.selectedEmpires[i4].setX(f2);
            width += 120;
            i4++;
        }
    }

    private boolean shouldEmpireChartButtonBeDisabled(int i, boolean z) {
        if (z || i == this.B.getCurrentPlayer() || !this.B.empires.get(i).isAlive()) {
            return false;
        }
        return !this.B.getCurrentEmpire().hasSpyNetwork(i);
    }

    private boolean showEmpireChartButton(int i, boolean z) {
        if (this.B.empires.get(i).getType() == EmpireType.NONE) {
            return false;
        }
        return z || this.B.getCurrentEmpire().isEmpireKnown(i) || this.B.getCurrentPlayer() == i || !this.B.empires.get(i).isAlive();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void K(ExtendedScene extendedScene, Object obj) {
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        ExtendedScene extendedScene = this.source;
        if (extendedScene instanceof EmpireScene) {
            changeScene(this.B.empireScene);
        } else {
            this.B.setCurrentSceneNoSwitch(extendedScene);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp(point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        IEntity entity = new Entity(0.0f, 147.0f);
        attachChild(entity);
        Sprite sprite = new Sprite(0.0f, 0.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        sprite.setAlpha(0.4f);
        sprite.setSize(getWidth(), 450.0f);
        entity.attachChild(sprite);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager);
        sprite2.setSize(getWidth(), 450.0f);
        entity.attachChild(sprite2);
        Line line = new Line(38.0f, 0.0f, getWidth(), 0.0f, vertexBufferObjectManager);
        this.zeroLine = line;
        line.setColor(Color.WHITE);
        entity.attachChild(this.zeroLine);
        for (int i = 0; i < 7; i++) {
            ArrayList<Line> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < 100; i2++) {
                Line line2 = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
                line2.setLineWidth(4.0f);
                line2.setAlpha(0.1f);
                line2.setColor(Empires.getEmpireColor(i));
                line2.setVisible(false);
                entity.attachChild(line2);
                arrayList.add(line2);
            }
            this.empireLinesList.add(arrayList);
        }
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.backButton = H;
        s(H);
        this.chartTitle = F(0.0f, 25.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.chartButtonSelected = H(0.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), true);
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK;
        TiledSprite H2 = H(0.0f, 60.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.populationButton = H2;
        s(H2);
        TiledSprite tiledSprite = new TiledSprite(40.0f, 23.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.POPULATION.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        this.populationButton.attachChild(tiledSprite);
        TiledSprite H3 = H(120.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.systemsButton = H3;
        s(H3);
        AnimatedSprite animatedSprite = new AnimatedSprite(30.0f, 13.0f, this.B.graphics.starsTexture, vertexBufferObjectManager);
        int ordinal = StarType.values()[Functions.random.nextInt(StarType.values().length)].ordinal() * 12;
        animatedSprite.animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
        animatedSprite.setSize(60.0f, 60.0f);
        this.systemsButton.attachChild(animatedSprite);
        TiledSprite H4 = H(240.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.coloniesButton = H4;
        s(H4);
        TiledSprite tiledSprite2 = new TiledSprite(35.0f, 18.0f, this.B.graphics.planetsTextureRegion[Climate.TERRAN.getID()], vertexBufferObjectManager);
        tiledSprite2.setCurrentTileIndex(1);
        tiledSprite2.setSize(50.0f, 50.0f);
        this.coloniesButton.attachChild(tiledSprite2);
        TiledSprite H5 = H(360.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.commandPointsButton = H5;
        s(H5);
        TiledSprite tiledSprite3 = new TiledSprite(40.0f, 23.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.COMMAND_POINTS.ordinal());
        tiledSprite3.setSize(40.0f, 40.0f);
        this.commandPointsButton.attachChild(tiledSprite3);
        TiledSprite H6 = H(480.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.foodButton = H6;
        s(H6);
        TiledSprite tiledSprite4 = new TiledSprite(40.0f, 23.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
        tiledSprite4.setSize(40.0f, 40.0f);
        this.foodButton.attachChild(tiledSprite4);
        TiledSprite H7 = H(600.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.productionButton = H7;
        s(H7);
        TiledSprite tiledSprite5 = new TiledSprite(40.0f, 23.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite5.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
        tiledSprite5.setSize(40.0f, 40.0f);
        this.productionButton.attachChild(tiledSprite5);
        TiledSprite H8 = H(720.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.scienceButton = H8;
        s(H8);
        TiledSprite tiledSprite6 = new TiledSprite(40.0f, 23.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite6.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
        tiledSprite6.setSize(40.0f, 40.0f);
        this.scienceButton.attachChild(tiledSprite6);
        TiledSprite H9 = H(840.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.techButton = H9;
        s(H9);
        TiledSprite tiledSprite7 = new TiledSprite(40.0f, 23.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite7.setCurrentTileIndex(InfoIconEnum.DISCOVERY.ordinal());
        tiledSprite7.setSize(40.0f, 40.0f);
        this.techButton.attachChild(tiledSprite7);
        TiledSprite H10 = H(960.0f, 60.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.creditsPerTurnButton = H10;
        s(H10);
        TiledSprite tiledSprite8 = new TiledSprite(40.0f, 23.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite8.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        tiledSprite8.setSize(40.0f, 40.0f);
        this.creditsPerTurnButton.attachChild(tiledSprite8);
        for (int i3 = 0; i3 < 7; i3++) {
            this.selectedEmpires[i3] = H(0.0f, 620.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
            TiledSprite empireButton = new EmpireButton(this.B, vertexBufferObjectManager);
            attachChild(empireButton);
            empireButton.setPosition(0.0f, 620.0f);
            empireButton.setVisible(false);
            s(empireButton);
            this.empireButtons[i3] = empireButton;
        }
        attachChild(new Line(0.0f, 598.0f, getWidth(), 598.0f, vertexBufferObjectManager));
        attachChild(new Line(38.0f, 172.0f, 38.0f, 597.0f, vertexBufferObjectManager));
        Game game = this.B;
        Text F = F(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.history_turns), this.E, vertexBufferObjectManager);
        F.setX((getWidth() / 2.0f) - (F.getWidthScaled() / 2.0f));
        F.setY(625.0f - F.getHeightScaled());
        Text F2 = F(45.0f, 0.0f, this.B.fonts.smallInfoFont, "0", this.E, vertexBufferObjectManager);
        F2.setY(625.0f - F2.getHeightScaled());
        this.endTurnLabel = F(0.0f, 0.0f, this.B.fonts.smallInfoFont, "#########", this.E, vertexBufferObjectManager);
        this.largestAmountLabel = F(0.0f, entity.getY(), this.B.fonts.smallInfoFont, "###########", this.E, vertexBufferObjectManager);
        this.lowestAmountLabel = F(0.0f, entity.getY(), this.B.fonts.smallInfoFont, "###########", this.E, vertexBufferObjectManager);
        Game game2 = this.B;
        Text F3 = F(0.0f, 0.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.history_amount), this.E, vertexBufferObjectManager);
        F3.setRotationCenter(F3.getWidthScaled() / 2.0f, F3.getHeightScaled() / 2.0f);
        F3.setRotation(270.0f);
        F3.setY(390.0f - (F3.getHeightScaled() / 2.0f));
        F3.setX(20.0f - (F3.getWidthScaled() / 2.0f));
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
    }

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

    public void set(ExtendedScene extendedScene, boolean z) {
        this.source = extendedScene;
        setEmpireButtons(z);
        setChart(this.selectedChart);
        this.endTurnLabel.setText(Integer.toString(GameData.turn));
        this.endTurnLabel.setX((getWidth() - this.endTurnLabel.getWidthScaled()) - 2.0f);
        Text text = this.endTurnLabel;
        text.setY(625.0f - text.getHeightScaled());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
