package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.HashMap;
import java.util.Map;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipClassSelectOverlay extends ExtendedChildScene {
    private final Map<ShipType, TiledSprite> highlights;
    private final Map<ShipType, TiledSprite> selectButtons;
    private final ShipSelectOverlay shipSelectOverlay;
    private final ShipType[] shipTypes;
    private final Map<ShipType, TiledSprite> typeIcons;
    private final Map<ShipType, TiledSprite> typeIcons2;
    private final Map<ShipType, TiledSprite> unselectButtons;

    public ShipClassSelectOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager, ShipSelectOverlay shipSelectOverlay) {
        super(game, vertexBufferObjectManager, false);
        this.selectButtons = new HashMap();
        this.highlights = new HashMap();
        this.typeIcons = new HashMap();
        this.unselectButtons = new HashMap();
        this.typeIcons2 = new HashMap();
        ShipType[] shipTypeArr = {ShipType.SCOUT, ShipType.COLONY, ShipType.CONSTRUCTION, ShipType.TRANSPORT, ShipType.DESTROYER, ShipType.CRUISER, ShipType.BATTLESHIP, ShipType.DREADNOUGHT};
        this.shipTypes = shipTypeArr;
        this.shipSelectOverlay = shipSelectOverlay;
        for (ShipType shipType : shipTypeArr) {
            float width = getWidth() - 360.0f;
            TiledTextureRegion tiledTextureRegion = game.graphics.buttonsTexture;
            ButtonsEnum buttonsEnum = ButtonsEnum.BLANK;
            TiledSprite w = w(width, 0.0f, tiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
            n(w);
            this.selectButtons.put(shipType, w);
            TiledSprite w2 = w(getWidth() - 360.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), true);
            w2.setAlpha(0.4f);
            this.highlights.put(shipType, w2);
            TiledSprite w3 = w(getWidth() - 320.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.getShipIcon(shipType), true);
            w3.setSize(40.0f, 40.0f);
            this.typeIcons.put(shipType, w3);
            TiledSprite w4 = w(getWidth() - 240.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
            n(w4);
            this.unselectButtons.put(shipType, w4);
            TiledSprite w5 = w(getWidth() - 200.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.getShipIcon(shipType), true);
            w5.setSize(40.0f, 40.0f);
            this.typeIcons2.put(shipType, w5);
        }
    }

    private void checkActionUp(Point point) {
        ShipType[] shipTypeArr;
        for (ShipType shipType : this.shipTypes) {
            if (q(this.selectButtons.get(shipType), point)) {
                shipTypeSelectPressed(shipType, true);
            } else if (q(this.unselectButtons.get(shipType), point)) {
                shipTypeSelectPressed(shipType, false);
            }
        }
        back();
    }

    private void shipTypeSelectPressed(ShipType shipType, boolean z) {
        this.shipSelectOverlay.z(shipType, z);
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp(point);
        }
    }

    public void setOverlay(int[] iArr) {
        ShipType[] shipTypeArr;
        ShipType[] shipTypeArr2;
        int i = 0;
        for (ShipType shipType : this.shipTypes) {
            boolean z = iArr[shipType.id] != 0;
            this.selectButtons.get(shipType).setVisible(z);
            this.highlights.get(shipType).setVisible(z);
            this.typeIcons.get(shipType).setVisible(z);
            this.unselectButtons.get(shipType).setVisible(z);
            this.typeIcons2.get(shipType).setVisible(z);
            if (z) {
                i++;
            }
        }
        float f2 = 360.0f - ((i * 86) / 2.0f);
        for (ShipType shipType2 : this.shipTypes) {
            if (this.selectButtons.get(shipType2).isVisible()) {
                this.selectButtons.get(shipType2).setY(f2);
                this.highlights.get(shipType2).setY(f2);
                float f3 = 23.0f + f2;
                this.typeIcons.get(shipType2).setY(f3);
                this.unselectButtons.get(shipType2).setY(f2);
                this.typeIcons2.get(shipType2).setY(f3);
                f2 += 86.0f;
            }
        }
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
