package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechCategory;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TechListElement extends Entity {
    private final int ITEM_SIZE = 80;
    private final Sprite blackedBackground;
    private final Sprite canResearchOverlay;
    private final Sprite finishedOverlay;
    private final Sprite levelHeader;
    private final float listWidth;
    private final TiledSprite researchIcon;
    private final Sprite selectedOverlay;
    private final Sprite separator;
    private final Text techCost;
    private final TechIcon techIcon;
    private final TiledSprite techIconSenator;
    private final Text techName;
    public Text techPercentage;

    public TechListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        this.listWidth = f2;
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        this.separator = sprite;
        sprite.setAlpha(0.8f);
        float f3 = f2 - 20.0f;
        sprite.setSize(f3, 75.0f);
        attachChild(sprite);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        this.blackedBackground = sprite2;
        sprite2.setAlpha(0.4f);
        sprite2.setSize(f3, 75.0f);
        attachChild(sprite2);
        Sprite sprite3 = new Sprite(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        this.levelHeader = sprite3;
        sprite3.setAlpha(0.4f);
        sprite3.setSize(f3, 75.0f);
        attachChild(sprite3);
        Sprite sprite4 = new Sprite(0.0f, 0.0f, game.graphics.farmingBarTexture, vertexBufferObjectManager);
        this.selectedOverlay = sprite4;
        sprite4.setAlpha(0.9f);
        sprite4.setSize(f3, 75.0f);
        attachChild(sprite4);
        Sprite sprite5 = new Sprite(0.0f, 0.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
        this.canResearchOverlay = sprite5;
        sprite5.setAlpha(0.6f);
        sprite5.setSize(f3, 75.0f);
        attachChild(sprite5);
        Sprite sprite6 = new Sprite(0.0f, 0.0f, game.graphics.popEmptyTexture, vertexBufferObjectManager);
        this.finishedOverlay = sprite6;
        sprite6.setAlpha(0.8f);
        sprite6.setSize(f3, 75.0f);
        attachChild(sprite6);
        TechIcon techIcon = new TechIcon(game, vertexBufferObjectManager, 70);
        this.techIcon = techIcon;
        techIcon.setPosition(0.0f, 0.0f);
        attachChild(techIcon);
        TiledSprite tiledSprite = new TiledSprite(70.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.techIconSenator = tiledSprite;
        tiledSprite.setSize(3.0f, 75.0f);
        tiledSprite.setAlpha(0.9f);
        tiledSprite.setCurrentTileIndex(6);
        attachChild(tiledSprite);
        Text text = new Text(0.0f, 3.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[255]), vertexBufferObjectManager);
        this.techPercentage = text;
        text.setVisible(false);
        attachChild(this.techPercentage);
        Text text2 = new Text(10.0f, 0.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[255]), new TextOptions(), vertexBufferObjectManager);
        this.techName = text2;
        text2.setY(37.5f - (text2.getHeightScaled() / 2.0f));
        attachChild(text2);
        Text text3 = new Text(0.0f, 0.0f, game.fonts.smallFont, "######", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        this.techCost = text3;
        attachChild(text3);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.researchIcon = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
        attachChild(tiledSprite2);
    }

    private void setSpriteInvisible() {
        this.levelHeader.setVisible(false);
        this.selectedOverlay.setVisible(false);
        this.finishedOverlay.setVisible(false);
        this.techPercentage.setVisible(false);
        this.canResearchOverlay.setVisible(false);
        this.techCost.setVisible(false);
        this.researchIcon.setVisible(false);
        this.techName.setVisible(false);
    }

    public void set(TechCategory techCategory, int i, Tech tech) {
        setSpriteInvisible();
        this.techIcon.set(i, tech);
        if (tech.isHeader()) {
            this.techName.setVisible(true);
            this.techName.setText(tech.getName());
            this.techName.setX(10.0f);
            Text text = this.techName;
            text.setY(37.5f - (text.getHeightScaled() / 2.0f));
            this.techCost.setText(Integer.toString(techCategory.getTechCost(tech.getLevel())));
            this.techCost.setVisible(true);
            Text text2 = this.techCost;
            text2.setY(37.5f - (text2.getHeightScaled() / 2.0f));
            Text text3 = this.techCost;
            text3.setX((this.listWidth - 80.0f) - text3.getWidthScaled());
            this.researchIcon.setPosition(this.listWidth - 70.0f, this.techCost.getY() - 5.0f);
            this.researchIcon.setVisible(true);
        } else if (tech.isNormalTech()) {
            this.techName.setVisible(true);
            this.techName.setText(tech.getShortName());
            this.techName.setX(80.0f);
            Text text4 = this.techName;
            text4.setY(37.5f - (text4.getHeightScaled() / 2.0f));
        }
    }

    public void setCurrentTechElement() {
        this.separator.setVisible(true);
        this.blackedBackground.setVisible(true);
        this.canResearchOverlay.setVisible(false);
        this.selectedOverlay.setVisible(true);
        this.techIconSenator.setVisible(true);
    }

    public void setEmptyTechElement() {
        this.separator.setVisible(false);
        this.blackedBackground.setVisible(false);
        this.levelHeader.setVisible(false);
        this.canResearchOverlay.setVisible(false);
        this.selectedOverlay.setVisible(false);
        this.finishedOverlay.setVisible(false);
        this.techIconSenator.setVisible(false);
    }

    public void setResearchableTechElement() {
        this.separator.setVisible(true);
        this.blackedBackground.setVisible(true);
        this.canResearchOverlay.setVisible(true);
        this.selectedOverlay.setVisible(false);
        this.finishedOverlay.setVisible(false);
        this.techIconSenator.setVisible(true);
    }

    public void setTechDoneElement() {
        this.separator.setVisible(true);
        this.blackedBackground.setVisible(true);
        this.canResearchOverlay.setVisible(false);
        this.selectedOverlay.setVisible(false);
        this.finishedOverlay.setVisible(true);
        this.techIconSenator.setVisible(true);
    }

    public void setTechHeaderElement() {
        this.separator.setVisible(true);
        this.blackedBackground.setVisible(true);
        this.levelHeader.setVisible(true);
        this.canResearchOverlay.setVisible(false);
        this.selectedOverlay.setVisible(false);
        this.finishedOverlay.setVisible(false);
        this.techIconSenator.setVisible(false);
    }

    public void setUnavailableTechElement() {
        this.separator.setVisible(true);
        this.blackedBackground.setVisible(true);
        this.canResearchOverlay.setVisible(false);
        this.selectedOverlay.setVisible(false);
        this.finishedOverlay.setVisible(false);
        this.techIconSenator.setVisible(true);
    }
}
