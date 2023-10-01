package com.birdshel.Uciana.Elements.ProductionScene;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonyInfoEntity extends Entity {
    private final TiledSprite creditsIcon;
    private final Text creditsText;
    private final Text foodAmountText;
    private final TiledSprite foodIcon;
    private final TiledSprite powerIcon;
    private final Text powerText;
    private final TiledSprite productionIcon;
    private final Text productionText;
    private final TiledSprite scienceIcon;
    private final Text scienceText;

    public ColonyInfoEntity(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        Font font = game.fonts.smallInfoFont;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 7.0f, font, "####################", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.foodAmountText = text;
        attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.foodIcon = tiledSprite;
        tiledSprite.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
        attachChild(tiledSprite);
        Text text2 = new Text(0.0f, 7.0f, game.fonts.smallInfoFont, "####################", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.productionText = text2;
        attachChild(text2);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.productionIcon = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
        attachChild(tiledSprite2);
        Text text3 = new Text(0.0f, 7.0f, game.fonts.smallInfoFont, "####################", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.scienceText = text3;
        attachChild(text3);
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.scienceIcon = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
        attachChild(tiledSprite3);
        Text text4 = new Text(0.0f, 7.0f, game.fonts.smallInfoFont, "####################", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.creditsText = text4;
        attachChild(text4);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.creditsIcon = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        attachChild(tiledSprite4);
        Text text5 = new Text(0.0f, 7.0f, game.fonts.smallInfoFont, "####################", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.powerText = text5;
        attachChild(text5);
        TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.powerIcon = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(InfoIconEnum.POWER.ordinal());
        attachChild(tiledSprite5);
    }

    public void set(Colony colony) {
        int netFoodPerTurn = colony.getNetFoodPerTurn();
        String num = Integer.toString(netFoodPerTurn);
        if (netFoodPerTurn > 0) {
            num = "+" + num;
        }
        this.foodAmountText.setText(num);
        this.foodIcon.setX(this.foodAmountText.getWidthScaled() + 2.0f);
        int productionPerTurn = colony.getProductionPerTurn();
        String num2 = Integer.toString(productionPerTurn);
        if (productionPerTurn > 0) {
            num2 = "+" + num2;
        }
        this.productionText.setText(num2);
        this.productionText.setX(this.foodIcon.getX() + 45.0f);
        this.productionIcon.setX(this.productionText.getX() + this.productionText.getWidthScaled() + 2.0f);
        int sciencePerTurn = colony.getSciencePerTurn();
        String num3 = Integer.toString(sciencePerTurn);
        if (sciencePerTurn > 0) {
            num3 = "+" + num3;
        }
        this.scienceText.setText(num3);
        this.scienceText.setX(this.productionIcon.getX() + 45.0f);
        this.scienceIcon.setX(this.scienceText.getX() + this.scienceText.getWidthScaled() + 2.0f);
        int creditsPerTurn = colony.getCreditsPerTurn();
        String num4 = Integer.toString(creditsPerTurn);
        if (creditsPerTurn > 0) {
            num4 = "+" + num4;
        }
        this.creditsText.setText(num4);
        this.creditsText.setX(this.scienceIcon.getX() + 45.0f);
        this.creditsIcon.setX(this.creditsText.getX() + this.creditsText.getWidthScaled() + 2.0f);
        int powerLevel = colony.getPowerLevel();
        String num5 = Integer.toString(powerLevel);
        if (powerLevel > 0) {
            num5 = "+" + num5;
        }
        this.powerText.setText(num5);
        this.powerText.setX(this.creditsIcon.getX() + 45.0f);
        this.powerIcon.setX(this.powerText.getX() + this.powerText.getWidthScaled() + 2.0f);
    }
}
