package com.birdshel.Uciana.Messages.ColonyInfo;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FoodMessage implements Message {
    private final Colony colony;

    public FoodMessage(Colony colony) {
        this.colony = colony;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        int totalFoodPerTurn = this.colony.getTotalFoodPerTurn();
        int importedFood = this.colony.getImportedFood();
        String num = Integer.toString(totalFoodPerTurn);
        if (this.colony.getTotalFoodPerTurn() >= 0) {
            num = "+" + num;
        }
        Font font = game.fonts.infoFont;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 270.0f, font, num, new TextOptions(horizontalAlign), buffer);
        messageOverlay.addElement(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 270.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
        messageOverlay.addElement(tiledSprite);
        Text text2 = new Text(0.0f, 270.0f, game.fonts.infoFont, "produced", new TextOptions(horizontalAlign), buffer);
        messageOverlay.addElement(text2);
        Text text3 = new Text(0.0f, 270.0f, game.fonts.infoFont, Integer.toString(importedFood), new TextOptions(horizontalAlign), buffer);
        messageOverlay.addElement(text3);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 270.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.IMPORTED_FOOD.ordinal());
        messageOverlay.addElement(tiledSprite2);
        Text text4 = new Text(0.0f, 270.0f, game.fonts.infoFont, "imported", new TextOptions(horizontalAlign), buffer);
        messageOverlay.addElement(text4);
        text3.setVisible(false);
        tiledSprite2.setVisible(false);
        text4.setVisible(false);
        text.setVisible(false);
        tiledSprite.setVisible(false);
        text2.setVisible(false);
        if (importedFood > 0) {
            text3.setVisible(true);
            tiledSprite2.setVisible(true);
            text4.setVisible(true);
            text3.setX((messageOverlay.getWidth() / 2.0f) - (((((text3.getWidthScaled() + 5.0f) + 30.0f) + 5.0f) + text4.getWidthScaled()) / 2.0f));
            tiledSprite2.setX(text3.getX() + text3.getWidthScaled() + 5.0f);
            text4.setX(tiledSprite2.getX() + 35.0f);
        } else {
            text.setVisible(true);
            tiledSprite.setVisible(true);
            text2.setVisible(true);
            text.setX((messageOverlay.getWidth() / 2.0f) - (((((text.getWidthScaled() + 5.0f) + 30.0f) + 5.0f) + text2.getWidthScaled()) / 2.0f));
            tiledSprite.setX(text.getX() + text.getWidthScaled() + 5.0f);
            text2.setX(tiledSprite.getX() + 35.0f);
        }
        Text text5 = new Text(0.0f, 340.0f, game.fonts.smallInfoFont, "+" + this.colony.getFoodPerTurn() + " from farmers", new TextOptions(horizontalAlign), buffer);
        text5.setColor(Color.GREEN);
        text5.setX((messageOverlay.getWidth() / 2.0f) - (text5.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text5);
        Text text6 = new Text(0.0f, 380.0f, game.fonts.smallInfoFont, this.colony.getImportedFood() + " imported", new TextOptions(horizontalAlign), buffer);
        text6.setColor(Color.YELLOW);
        text6.setX((messageOverlay.getWidth() / 2.0f) - (text6.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text6);
        Text text7 = new Text(0.0f, 420.0f, game.fonts.smallInfoFont, "-" + this.colony.getPopulation() + " consumed", new TextOptions(horizontalAlign), buffer);
        text7.setColor(Color.RED);
        text7.setX((messageOverlay.getWidth() / 2.0f) - (text7.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text7);
    }
}
