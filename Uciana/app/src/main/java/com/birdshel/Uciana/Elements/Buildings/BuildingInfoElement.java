package com.birdshel.Uciana.Elements.Buildings;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import java.nio.CharBuffer;
import java.util.TreeSet;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildingInfoElement extends Entity {
    private final Text[] texts = new Text[5];
    private final TiledSprite[] icons = new TiledSprite[5];

    public BuildingInfoElement(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        for (int i = 0; i < 5; i++) {
            this.texts[i] = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[255]), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
            attachChild(this.texts[i]);
            this.icons[i] = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
            attachChild(this.icons[i]);
        }
    }

    public void set(Building building) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            this.texts[i2].setVisible(false);
            this.icons[i2].setVisible(false);
        }
        int i3 = 0;
        float f2 = 0.0f;
        for (String str : new TreeSet(building.getInfo().keySet())) {
            if (str.endsWith(TypedValues.Custom.S_STRING)) {
                this.texts[i].setVisible(true);
                this.texts[i].setText((String) building.getInfo().get(str));
                this.texts[i].setX(f2);
                f2 += this.texts[i].getWidthScaled() + 5.0f;
                i++;
            } else if (str.endsWith("banner")) {
                int intValue = ((Integer) building.getInfo().get(str)).intValue();
                this.icons[i3].setVisible(true);
                this.icons[i3].setCurrentTileIndex(InfoIconEnum.EMPIRE_BACKGROUND.ordinal());
                this.icons[i3].setColor(Empires.getEmpireColor(intValue));
                this.icons[i3].setX(f2);
                int i4 = i3 + 1;
                this.icons[i4].setVisible(true);
                this.icons[i4].setCurrentTileIndex(InfoIconEnum.getEmpireIcon(GameData.empires.get(intValue).getBannerID()));
                this.icons[i4].setX(f2);
                f2 += this.icons[i4].getWidthScaled() + 5.0f;
                i3 = i4 + 1;
            } else if (str.endsWith("icon")) {
                this.icons[i3].setVisible(true);
                this.icons[i3].setColor(Color.WHITE);
                this.icons[i3].setCurrentTileIndex(((InfoIconEnum) building.getInfo().get(str)).ordinal());
                this.icons[i3].setX(f2);
                f2 += this.icons[i3].getWidthScaled() + 5.0f;
                i3++;
            }
        }
    }
}
