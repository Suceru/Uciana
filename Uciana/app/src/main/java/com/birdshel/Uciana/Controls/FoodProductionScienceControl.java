package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FoodProductionScienceControl extends ExtendedScene {
    private Colony colony;
    private final Sprite farmingBar;
    private final Text perTurnFood;
    private final TiledSprite perTurnFoodIcon;
    private final Text perTurnProduction;
    private final TiledSprite perTurnProductionIcon;
    private final Text perTurnScience;
    private final TiledSprite perTurnScienceIcon;
    private final Text percentFarming;
    private final Text percentProduction;
    private final Text percentScience;
    private int pressedSlider;
    private float pressedX;
    private final Sprite productionBar;
    private final Sprite scienceBar;
    private final List<Entity> sliderButtons;

    public FoodProductionScienceControl(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        ArrayList arrayList = new ArrayList();
        this.sliderButtons = arrayList;
        this.pressedSlider = -1;
        setBackgroundEnabled(false);
        this.B = game;
        Sprite E = E(80.0f, 35.0f, game.graphics.farmingBarTexture, vertexBufferObjectManager, true);
        this.farmingBar = E;
        E.setHeight(60.0f);
        Sprite E2 = E(80.0f, 35.0f, game.graphics.productionBarTexture, vertexBufferObjectManager, true);
        this.productionBar = E2;
        E2.setHeight(60.0f);
        Sprite E3 = E(80.0f, 35.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager, true);
        this.scienceBar = E3;
        E3.setHeight(60.0f);
        Sprite E4 = E(0.0f, 95.0f, game.graphics.sliderTexture, vertexBufferObjectManager, true);
        arrayList.add(E4);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.sliderTexture, vertexBufferObjectManager);
        blinkSprite(sprite);
        E4.attachChild(sprite);
        Sprite E5 = E(100.0f, 95.0f, game.graphics.sliderTexture, vertexBufferObjectManager, true);
        arrayList.add(E5);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, game.graphics.sliderTexture, vertexBufferObjectManager);
        blinkSprite(sprite2);
        E5.attachChild(sprite2);
        this.percentFarming = F(240.0f, 50.0f, game.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.percentProduction = F(340.0f, 50.0f, game.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.percentScience = F(440.0f, 50.0f, game.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.perTurnFoodIcon = H(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.FOOD.ordinal(), false);
        this.perTurnProductionIcon = H(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.PRODUCTION.ordinal(), false);
        this.perTurnScienceIcon = H(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.SCIENCE.ordinal(), false);
        this.perTurnFood = F(0.0f, 2.0f, game.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.perTurnProduction = F(0.0f, 2.0f, game.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.perTurnScience = F(0.0f, 2.0f, game.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
    }

    private boolean checkActionDown(Point point) {
        if (isClicked(this.sliderButtons.get(0), point)) {
            this.pressedSlider = 0;
            this.pressedX = point.getX();
            return true;
        } else if (isClicked(this.sliderButtons.get(1), point)) {
            this.pressedSlider = 1;
            this.pressedX = point.getX();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkActionMove(Point point) {
        if (this.pressedSlider != -1) {
            if (moveSlider(point)) {
                float x = this.pressedX - point.getX();
                if (x > 5.0f || x < -5.0f) {
                    Game game = this.B;
                    game.vibrate(game.SLIDER_VIBRATE);
                }
            }
            this.pressedX = point.getX();
            return true;
        }
        return false;
    }

    private boolean checkActionUp() {
        if (this.pressedSlider != -1) {
            this.B.planetScene.updateColonyInfo();
            this.B.planetScene.updatePopulationInfo();
            this.pressedSlider = -1;
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0057, code lost:
        if (r2 < 20.0f) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean moveSlider(Point point) {
        boolean z;
        Sprite sprite = (Sprite) this.sliderButtons.get(0);
        Sprite sprite2 = (Sprite) this.sliderButtons.get(1);
        float f2 = 20.0f;
        if (point.getX() < this.pressedX) {
            float x = this.sliderButtons.get(this.pressedSlider).getX() - (this.pressedX - point.getX());
            if (this.pressedSlider == 1) {
                if (x < sprite.getX() + sprite.getWidth()) {
                    f2 = sprite.getX() + sprite.getWidth() + 1.0f;
                    z = false;
                }
                f2 = x;
                z = true;
            }
        } else {
            f2 = this.sliderButtons.get(this.pressedSlider).getX() + (point.getX() - this.pressedX);
            if (this.pressedSlider == 0) {
                if (sprite.getWidth() + f2 > sprite2.getX()) {
                    f2 = sprite2.getX() - sprite.getWidth();
                    z = false;
                }
            } else if (f2 > 480.0f) {
                z = false;
                f2 = 480.0f;
            }
            z = true;
        }
        this.sliderButtons.get(this.pressedSlider).setX(f2);
        this.colony.setFarmersPercent(((((int) this.sliderButtons.get(0).getX()) + 60) - 80) / 4);
        this.colony.setWorkersPercent((((int) this.sliderButtons.get(1).getX()) - ((int) (this.sliderButtons.get(0).getX() + 60.0f))) / 4);
        this.colony.setScientistPercent((480 - ((int) this.sliderButtons.get(1).getX())) / 4);
        int farmersPercent = ((100 - this.colony.getFarmersPercent()) - this.colony.getWorkersPercent()) - this.colony.getScientistPercent();
        if (farmersPercent != 0) {
            if (this.colony.getFarmersPercent() > this.colony.getWorkersPercent() && this.colony.getFarmersPercent() > this.colony.getScientistPercent()) {
                Colony colony = this.colony;
                colony.setFarmersPercent(colony.getFarmersPercent() + farmersPercent);
            } else if (this.colony.getWorkersPercent() > this.colony.getFarmersPercent() && this.colony.getWorkersPercent() > this.colony.getScientistPercent()) {
                Colony colony2 = this.colony;
                colony2.setWorkersPercent(colony2.getWorkersPercent() + farmersPercent);
            } else {
                Colony colony3 = this.colony;
                colony3.setScientistPercent(colony3.getScientistPercent() + farmersPercent);
            }
        }
        updateSlider();
        return z;
    }

    private void setSliderText(Text text, float f2, int i, float f3) {
        text.setText(i + "%");
        if (text.getWidth() > f3) {
            text.setText(Integer.toString(i));
            if (text.getWidth() > f3) {
                text.setVisible(false);
                return;
            }
        }
        text.setPosition(f2 + ((f3 - text.getWidth()) / 2.0f), text.getY());
    }

    private void updateSlider() {
        this.farmingBar.setWidth(this.colony.getFarmersPercent() * 4);
        this.productionBar.setPosition(this.farmingBar.getX() + this.farmingBar.getWidth(), this.productionBar.getY());
        this.productionBar.setWidth(this.colony.getWorkersPercent() * 4);
        this.scienceBar.setPosition(this.productionBar.getX() + this.productionBar.getWidth(), this.scienceBar.getY());
        this.scienceBar.setWidth(this.colony.getScientistPercent() * 4);
        float x = this.farmingBar.getX();
        float x2 = this.farmingBar.getX() + this.farmingBar.getWidth();
        float x3 = this.productionBar.getX() + this.productionBar.getWidth();
        this.percentFarming.setVisible(true);
        this.percentProduction.setVisible(true);
        this.percentScience.setVisible(true);
        setSliderText(this.percentFarming, x, this.colony.getFarmersPercent(), this.farmingBar.getWidth());
        setSliderText(this.percentProduction, x2, this.colony.getWorkersPercent(), this.productionBar.getWidth());
        setSliderText(this.percentScience, x3, this.colony.getScientistPercent(), this.scienceBar.getWidth());
        this.perTurnFood.setText(Integer.toString(this.colony.getFoodPerTurn()));
        this.perTurnFood.setPosition(this.farmingBar.getX(), this.perTurnFood.getY());
        this.perTurnFoodIcon.setPosition(this.perTurnFood.getX() + this.perTurnFood.getWidth() + 5.0f, this.perTurnFoodIcon.getY());
        this.perTurnProduction.setText(Integer.toString(this.colony.getProductionPerTurn()));
        float width = (200.0f - (((this.perTurnProduction.getWidth() + 5.0f) + this.perTurnProductionIcon.getWidth()) / 2.0f)) + this.farmingBar.getX();
        Text text = this.perTurnProduction;
        text.setPosition(width, text.getY());
        this.perTurnProductionIcon.setPosition(width + this.perTurnProduction.getWidth() + 5.0f, this.perTurnProductionIcon.getY());
        this.perTurnScience.setText(Integer.toString(this.colony.getSciencePerTurn()));
        float x4 = (this.scienceBar.getX() + this.scienceBar.getWidth()) - this.perTurnScienceIcon.getWidth();
        Text text2 = this.perTurnScience;
        text2.setPosition((x4 - text2.getWidth()) - 5.0f, this.perTurnScience.getY());
        TiledSprite tiledSprite = this.perTurnScienceIcon;
        tiledSprite.setPosition(x4, tiledSprite.getY());
        this.B.planetScene.updateColony();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public boolean checkInputOnControl(int i, Point point) {
        Point point2 = new Point(point.getX() - getX(), point.getY() - getY());
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return false;
                }
                return checkActionMove(point2);
            }
            return checkActionUp();
        }
        return checkActionDown(point2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
    }

    public void hideControl() {
        C(this.sliderButtons);
        this.farmingBar.setVisible(false);
        this.productionBar.setVisible(false);
        this.scienceBar.setVisible(false);
        this.percentFarming.setVisible(false);
        this.percentProduction.setVisible(false);
        this.percentScience.setVisible(false);
        this.perTurnFood.setVisible(false);
        this.perTurnProduction.setVisible(false);
        this.perTurnScience.setVisible(false);
        this.perTurnFoodIcon.setVisible(false);
        this.perTurnProductionIcon.setVisible(false);
        this.perTurnScienceIcon.setVisible(false);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        this.sliderButtons.get(0).setPosition((this.colony.getFarmersPercent() * 4) + 20, this.sliderButtons.get(0).getY());
        if (this.colony.getPlanet().getFoodPerFarmer(this.colony.getEmpireID()) != 0.0f) {
            this.sliderButtons.get(0).setVisible(true);
        }
        this.sliderButtons.get(1).setPosition(480 - (this.colony.getScientistPercent() * 4), this.sliderButtons.get(1).getY());
        this.sliderButtons.get(1).setVisible(true);
        this.farmingBar.setVisible(true);
        this.productionBar.setVisible(true);
        this.scienceBar.setVisible(true);
        this.perTurnFoodIcon.setVisible(true);
        this.perTurnProductionIcon.setVisible(true);
        this.perTurnScienceIcon.setVisible(true);
        this.perTurnFood.setVisible(true);
        this.perTurnProduction.setVisible(true);
        this.perTurnScience.setVisible(true);
        updateSlider();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(ExtendedScene extendedScene, Object obj) {
    }

    public void set(Colony colony) {
        this.colony = colony;
        refresh();
    }
}
