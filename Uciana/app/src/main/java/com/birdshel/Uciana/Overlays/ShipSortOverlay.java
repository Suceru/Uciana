package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.ShipSort;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipSortOverlay extends ExtendedChildScene {
    private final TiledSprite sortButton;
    private final List<TiledSprite> sortButtons;

    public ShipSortOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        ArrayList arrayList = new ArrayList();
        this.sortButtons = arrayList;
        TiledSprite w = w(getWidth() - 240.0f, 0.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, 14, true);
        arrayList.add(w);
        n(w);
        TiledSprite w2 = w(getWidth() - 120.0f, 0.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, 15, true);
        arrayList.add(w2);
        n(w2);
        TiledSprite w3 = w(getWidth() - 240.0f, 86.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, 2, true);
        arrayList.add(w3);
        n(w3);
        TiledSprite w4 = w(getWidth() - 120.0f, 86.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, 3, true);
        arrayList.add(w4);
        n(w4);
        TiledSprite w5 = w(getWidth() - 120.0f, 632.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, 0, true);
        this.sortButton = w5;
        n(w5);
    }

    private void checkActionUp(Point point) {
        for (TiledSprite tiledSprite : this.sortButtons) {
            if (q(tiledSprite, point)) {
                sortItemPressed(tiledSprite);
            }
        }
        if (q(this.sortButton, point)) {
            sortButtonPressed();
        }
    }

    private void sortButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void sortItemPressed(TiledSprite tiledSprite) {
        this.C.fleetsScene.reload(ShipSort.getShipShort(tiledSprite.getCurrentTileIndex()));
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp(point);
        }
    }

    public void setOverlay(ShipSort shipSort) {
        this.sortButton.setCurrentTileIndex(shipSort.getValue());
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
