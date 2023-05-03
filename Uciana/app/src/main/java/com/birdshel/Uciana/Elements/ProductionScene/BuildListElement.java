package com.birdshel.Uciana.Elements.ProductionScene;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.BuildListOverlay;
import com.birdshel.Uciana.Resources.GameIconEnum;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildListElement extends Entity {
    private final TiledSprite addButton;
    private final Sprite background;
    private final List<TiledSprite> buildingIcons = new ArrayList();
    private final List<Text> buildingNames = new ArrayList();

    public BuildListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2, float f3) {
        setPosition(f2, f3);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        this.background = sprite;
        sprite.setSize(400.0f, 350.0f);
        sprite.setAlpha(0.6f);
        attachChild(sprite);
        TiledSprite tiledSprite = new TiledSprite(150.0f, 125.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.addButton = tiledSprite;
        tiledSprite.setCurrentTileIndex(GameIconEnum.ADD.ordinal());
        attachChild(tiledSprite);
        for (int i = 0; i < 5; i++) {
            TiledSprite tiledSprite2 = new TiledSprite(0.0f, i * 70, game.graphics.gameIconsTexture, vertexBufferObjectManager);
            tiledSprite2.setSize(70.0f, 70.0f);
            this.buildingIcons.add(tiledSprite2);
            attachChild(tiledSprite2);
            Text text = new Text(80.0f, 0.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[255]), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
            this.buildingNames.add(text);
            attachChild(text);
        }
    }

    public float getHeight() {
        return this.background.getHeightScaled();
    }

    public float getWidth() {
        return this.background.getWidthScaled();
    }

    public void set(List<BuildingID> list, BuildListOverlay buildListOverlay) {
        int i;
        Iterator<TiledSprite> it = this.buildingIcons.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            it.next().setVisible(false);
        }
        for (Text text : this.buildingNames) {
            text.setVisible(false);
        }
        this.addButton.setVisible(false);
        if (list.isEmpty()) {
            this.addButton.setVisible(true);
        }
        for (BuildingID buildingID : list) {
            Building building = Buildings.getBuilding(buildingID);
            this.buildingIcons.get(i).setVisible(true);
            this.buildingIcons.get(i).setCurrentTileIndex(building.getImageIndex());
            this.buildingNames.get(i).setVisible(true);
            this.buildingNames.get(i).setText(building.getName());
            this.buildingNames.get(i).setY((this.buildingIcons.get(i).getY() + 35.0f) - (this.buildingNames.get(i).getHeightScaled() / 2.0f));
            float f2 = 0.4f;
            if (buildListOverlay.canBuildBuilding(buildingID)) {
                f2 = 1.0f;
            }
            this.buildingIcons.get(i).setAlpha(f2);
            this.buildingNames.get(i).setAlpha(f2);
            i++;
        }
    }
}
