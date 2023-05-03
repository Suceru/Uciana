package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Colonies.SortType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Scenes.ColoniesScene;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Scenes.MovePopulationScene;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SortByOverlay extends ExtendedChildScene {
    private final ExtendedScene fromScreen;
    private final TiledSprite sortButton;
    private final List<TiledSprite> sortButtons;

    public SortByOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager, ExtendedScene extendedScene) {
        super(game, vertexBufferObjectManager, false);
        ArrayList arrayList = new ArrayList();
        this.sortButtons = arrayList;
        this.fromScreen = extendedScene;
        TiledSprite w = w(getWidth() - 240.0f, 0.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.OLDEST_TO_NEWEST.ordinal(), true);
        arrayList.add(w);
        n(w);
        TiledSprite w2 = w(getWidth() - 120.0f, 0.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.NEWEST_TO_OLDEST.ordinal(), true);
        arrayList.add(w2);
        n(w2);
        TiledSprite w3 = w(getWidth() - 240.0f, 86.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.A_TO_Z.ordinal(), true);
        arrayList.add(w3);
        n(w3);
        TiledSprite w4 = w(getWidth() - 120.0f, 86.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.Z_TO_A.ordinal(), true);
        arrayList.add(w4);
        n(w4);
        TiledSprite w5 = w(getWidth() - 240.0f, 172.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.POPULATION_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w5);
        n(w5);
        TiledSprite w6 = w(getWidth() - 120.0f, 172.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.POPULATION_LOWEST_TO_HIGHEST.ordinal(), true);
        arrayList.add(w6);
        n(w6);
        TiledSprite w7 = w(getWidth() - 240.0f, 258.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.FOOD_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w7);
        n(w7);
        TiledSprite w8 = w(getWidth() - 120.0f, 258.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.FOOD_LOWEST_TO_HIGHEST.ordinal(), true);
        arrayList.add(w8);
        n(w8);
        TiledSprite w9 = w(getWidth() - 240.0f, 344.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.PRODUCTION_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w9);
        n(w9);
        TiledSprite w10 = w(getWidth() - 120.0f, 344.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.PRODUCTION_LOWEST_TO_HIGHEST.ordinal(), true);
        arrayList.add(w10);
        n(w10);
        TiledSprite w11 = w(getWidth() - 240.0f, 430.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.SCIENCE_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w11);
        n(w11);
        TiledSprite w12 = w(getWidth() - 120.0f, 430.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.SCIENCE_LOWEST_TO_HIGHEST.ordinal(), true);
        arrayList.add(w12);
        n(w12);
        TiledSprite w13 = w(getWidth() - 240.0f, 516.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.CREDITS_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w13);
        n(w13);
        TiledSprite w14 = w(getWidth() - 120.0f, 516.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.CREDITS_LOWEST_TO_HIGHEST.ordinal(), true);
        arrayList.add(w14);
        n(w14);
        TiledSprite w15 = w(getWidth() - 360.0f, 172.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.ALERTS.ordinal(), true);
        arrayList.add(w15);
        n(w15);
        TiledSprite w16 = w(getWidth() - 360.0f, 258.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.FOOD_PER_FARMER_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w16);
        n(w16);
        TiledSprite w17 = w(getWidth() - 360.0f, 344.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.PRODUCTION_PER_WORKER_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w17);
        n(w17);
        TiledSprite w18 = w(getWidth() - 360.0f, 430.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.SCIENCE_PER_SCIENTISTS_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w18);
        n(w18);
        TiledSprite w19 = w(getWidth() - 480.0f, 0.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.BUILDINGS_HIGHEST_TO_LOWEST.ordinal(), true);
        arrayList.add(w19);
        n(w19);
        TiledSprite w20 = w(getWidth() - 360.0f, 0.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, SortType.BUILDINGS_LOWEST_TO_HIGHEST.ordinal(), true);
        arrayList.add(w20);
        n(w20);
        TiledSprite w21 = w(getWidth() - 120.0f, 632.0f, game.graphics.sortButtonsTexture, vertexBufferObjectManager, 0, true);
        this.sortButton = w21;
        n(w21);
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
        SortType sortType = SortType.values()[tiledSprite.getCurrentTileIndex()];
        ExtendedScene extendedScene = this.fromScreen;
        if (extendedScene instanceof ColoniesScene) {
            this.C.coloniesScene.reloadColonies(sortType);
        } else if (extendedScene instanceof MovePopulationScene) {
            this.C.movePopulationScene.reloadColonies(sortType);
        }
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp(point);
        }
    }

    public void setOverlay(SortType sortType) {
        this.sortButton.setCurrentTileIndex(sortType.ordinal());
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
