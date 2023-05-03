package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.PlanetScene;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechType;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EmpireInfo extends Entity {
    private final Text availableCommandPoints;
    private final int centerX;
    private final TiledSprite commandPointsIcon;
    private final TiledSprite creditsIcon;
    private final Text creditsPerTurn;
    private final TiledSprite discoveryIcon;
    private final TiledSprite foodIcon;
    private final Text foodPerTurn;
    private final Game game;
    private final Scene scene;
    private final TiledSprite scienceIcon;
    private final Text sciencePerTurn;
    private final Text taxRate;
    private final TiledSprite taxRateIcon;
    private final Text tech;
    private final Text totalCredits;

    public EmpireInfo(Game game, VertexBufferObjectManager vertexBufferObjectManager, int i, Scene scene) {
        this.game = game;
        this.centerX = i;
        this.scene = scene;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.foodIcon = tiledSprite;
        tiledSprite.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.commandPointsIcon = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.COMMAND_POINTS.ordinal());
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.creditsIcon = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        attachChild(tiledSprite3);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.taxRateIcon = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.TAX_RATE.ordinal());
        attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.scienceIcon = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
        attachChild(tiledSprite5);
        TiledSprite tiledSprite6 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.discoveryIcon = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(InfoIconEnum.DISCOVERY.ordinal());
        attachChild(tiledSprite6);
        Text text = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.foodPerTurn = text;
        attachChild(text);
        Text text2 = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.availableCommandPoints = text2;
        attachChild(text2);
        Text text3 = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.creditsPerTurn = text3;
        attachChild(text3);
        Text text4 = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.totalCredits = text4;
        attachChild(text4);
        Text text5 = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.taxRate = text5;
        attachChild(text5);
        Text text6 = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.sciencePerTurn = text6;
        attachChild(text6);
        Text text7 = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.tech = text7;
        attachChild(text7);
        if (scene instanceof PlanetScene) {
            text5.setVisible(false);
            tiledSprite4.setVisible(false);
            text2.setVisible(false);
            tiledSprite2.setVisible(false);
        }
    }

    private void centerInfo(float f2) {
        float f3 = this.centerX - (f2 / 2.0f);
        this.foodPerTurn.setX(f3);
        this.foodIcon.setX(f3 + this.foodPerTurn.getWidth());
        float x = this.foodIcon.getX() + this.foodIcon.getWidthScaled() + 30.0f;
        if (this.availableCommandPoints.isVisible()) {
            this.availableCommandPoints.setX(x);
            this.commandPointsIcon.setX(x + this.availableCommandPoints.getWidthScaled());
            x = this.commandPointsIcon.getX() + this.commandPointsIcon.getWidthScaled() + 30.0f;
        }
        this.creditsPerTurn.setX(x);
        float x2 = this.creditsPerTurn.getX() + this.creditsPerTurn.getWidthScaled() + 4.0f;
        this.totalCredits.setX(x2);
        this.creditsIcon.setX(x2 + this.totalCredits.getWidthScaled());
        float x3 = this.creditsIcon.getX() + this.creditsIcon.getWidthScaled() + 30.0f;
        if (this.taxRate.isVisible()) {
            this.taxRate.setX(x3);
            this.taxRateIcon.setX(this.taxRate.getX() + this.taxRate.getWidthScaled());
            x3 = this.taxRateIcon.getX() + this.taxRateIcon.getWidthScaled() + 20.0f;
        }
        this.sciencePerTurn.setX(x3);
        this.scienceIcon.setX(this.sciencePerTurn.getX() + this.sciencePerTurn.getWidthScaled());
        this.tech.setX(this.scienceIcon.getX() + this.scienceIcon.getWidthScaled() + 30.0f);
        this.discoveryIcon.setX(this.tech.getX() + this.tech.getWidthScaled());
    }

    public void update() {
        Tech currentTech;
        if (this.game.getCurrentPlayer() == -1) {
            return;
        }
        Empire currentEmpire = this.game.getCurrentEmpire();
        String techTurnsLeftString = currentEmpire.getTechTurnsLeftString();
        if (this.scene instanceof PlanetScene) {
            this.tech.setText(techTurnsLeftString);
        } else {
            if (currentEmpire.getTech().getCurrentTech().getType() == TechType.NONE) {
                this.tech.setText(techTurnsLeftString);
            } else {
                this.tech.setText("[" + currentTech.getName() + "]  " + techTurnsLeftString);
            }
        }
        float width = this.tech.getWidth() + 60.0f + 0.0f;
        String num = Integer.toString(currentEmpire.getResearchPoints());
        if (currentEmpire.getResearchPoints() > -1) {
            num = "+" + num;
        }
        this.sciencePerTurn.setText(num);
        float width2 = width + this.sciencePerTurn.getWidth() + 50.0f;
        if (this.taxRate.isVisible()) {
            String num2 = Integer.toString((int) (currentEmpire.getTaxRate() * 100.0f));
            this.taxRate.setText(num2 + "%");
            width2 += this.taxRate.getWidth() + 60.0f;
        }
        String num3 = Integer.toString(currentEmpire.getCreditsPerTurn());
        String num4 = Integer.toString(currentEmpire.getCredits());
        if (currentEmpire.getCreditsPerTurn() > -1) {
            num3 = "+" + num3;
            this.creditsPerTurn.setColor(Color.WHITE);
        } else {
            this.creditsPerTurn.setColor(Color.RED);
        }
        this.totalCredits.setText(" (" + num4 + ")");
        if (currentEmpire.getCredits() < 0) {
            this.totalCredits.setColor(Color.RED);
        } else {
            this.totalCredits.setColor(Color.WHITE);
        }
        this.creditsPerTurn.setText(num3);
        float width3 = width2 + (((this.totalCredits.getWidth() - 4.0f) - this.creditsPerTurn.getWidth()) - 60.0f);
        if (this.availableCommandPoints.isVisible()) {
            String num5 = Integer.toString(currentEmpire.getAvailableCommandPoints());
            if (currentEmpire.getAvailableCommandPoints() > -1) {
                this.availableCommandPoints.setColor(Color.WHITE);
            } else {
                this.availableCommandPoints.setColor(Color.RED);
            }
            this.availableCommandPoints.setText(num5);
            width3 += this.availableCommandPoints.getWidth() + 60.0f;
        }
        String num6 = Integer.toString(currentEmpire.getNetFoodPerTurn());
        if (currentEmpire.getNetFoodPerTurn() > -1) {
            num6 = "+" + num6;
            this.foodPerTurn.setColor(Color.WHITE);
        } else {
            this.foodPerTurn.setColor(Color.RED);
        }
        this.foodPerTurn.setText(num6);
        centerInfo(width3 + this.foodPerTurn.getWidth());
    }
}
