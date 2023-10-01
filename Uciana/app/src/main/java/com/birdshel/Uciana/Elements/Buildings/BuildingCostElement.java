package com.birdshel.Uciana.Elements.Buildings;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.text.DecimalFormat;
import java.util.TreeSet;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildingCostElement extends Entity {
    private float costWidth;
    private final Text[] costTexts = new Text[2];
    private final TiledSprite[] costIcons = new TiledSprite[2];

    public BuildingCostElement(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        for (int i = 0; i < 2; i++) {
            this.costTexts[i] = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, "##########", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
            attachChild(this.costTexts[i]);
            this.costIcons[i] = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
            attachChild(this.costIcons[i]);
        }
    }

    public float getCostWidth() {
        return this.costWidth;
    }

    public void set(Building building, float f2) {
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            this.costTexts[i2].setVisible(false);
            this.costIcons[i2].setVisible(false);
        }
        this.costWidth = 0.0f;
        DecimalFormat decimalFormat = new DecimalFormat();
        int i3 = 0;
        float f3 = 0.0f;
        for (String str : new TreeSet(building.getCostInfo().keySet())) {
            if (str.endsWith(TypedValues.Custom.S_STRING)) {
                String str2 = (String) building.getCostInfo().get(str);
                if (str.endsWith("cost-string")) {
                    str2 = decimalFormat.format(Integer.parseInt(str2) * f2);
                }
                this.costTexts[i3].setVisible(true);
                this.costTexts[i3].setText(str2);
                this.costTexts[i3].setX(f3);
                f3 += this.costTexts[i3].getWidthScaled() + 5.0f;
                i3++;
            } else if (str.endsWith("icon")) {
                this.costIcons[i].setVisible(true);
                this.costIcons[i].setCurrentTileIndex(((InfoIconEnum) building.getCostInfo().get(str)).ordinal());
                this.costIcons[i].setX(f3);
                f3 += this.costIcons[i].getWidthScaled() + 5.0f;
                i++;
            }
        }
        this.costWidth = ((int) f3) - 5;
    }
}
