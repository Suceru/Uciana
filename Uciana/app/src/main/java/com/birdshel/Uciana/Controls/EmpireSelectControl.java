package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Players.CreateEmpireInfo;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class EmpireSelectControl extends ExtendedScene {
    private final TiledSprite customEmpireBackground;
    private final List<Sprite> emperorImages = new ArrayList();
    private final List<TiledSprite> empireBackgrounds = new ArrayList();
    private final List<TiledSprite> empireBanners = new ArrayList();
    private final Sprite pressSprite;
    private final TiledSprite randomEmpireBackground;
    private final Sprite selectedSprite;
    private final Sprite selectedSprite2;

    public EmpireSelectControl(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        setBackgroundEnabled(false);
        this.game = game;
        Sprite E = E(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.pressSprite = E;
        E.setSize(125.0f, 150.0f);
        Sprite E2 = E(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, true);
        this.selectedSprite = E2;
        E2.setSize(125.0f, 150.0f);
        blinkSprite(E2);
        Sprite E3 = E(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, true);
        this.selectedSprite2 = E3;
        E3.setSize(125.0f, 150.0f);
        blinkSprite(E3);
        for (int i = 0; i < 7; i++) {
            int i2 = i * GameProperties.WORMHOLE_MAX_SIZE;
            TiledSprite H = H(i2, 0.0f, game.graphics.empireColors, vertexBufferObjectManager, 0, true);
            H.setAlpha(0.8f);
            H.setSize(125.0f, 150.0f);
            this.empireBackgrounds.add(H);
            Sprite E4 = E(i2 + 15, 12.0f, game.graphics.ambassadorIcons[0], vertexBufferObjectManager, true);
            E4.setSize(96.0f, 120.0f);
            this.emperorImages.add(E4);
            TiledSprite H2 = H(i2 - 10, -15.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, 0, true);
            H2.setSize(75.0f, 75.0f);
            this.empireBanners.add(H2);
        }
        TiledSprite H3 = H(910.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager, 8, true);
        this.randomEmpireBackground = H3;
        H3.setAlpha(0.8f);
        H3.setSize(125.0f, 72.0f);
        TiledSprite tiledSprite = new TiledSprite(37.0f, 11.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM.ordinal());
        tiledSprite.setSize(50.0f, 50.0f);
        H3.attachChild(tiledSprite);
        TiledSprite H4 = H(910.0f, 78.0f, game.graphics.empireColors, vertexBufferObjectManager, 8, true);
        this.customEmpireBackground = H4;
        H4.setAlpha(0.8f);
        H4.setSize(125.0f, 72.0f);
        TiledSprite tiledSprite2 = new TiledSprite(37.0f, 11.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.CUSTOM.ordinal());
        tiledSprite2.setSize(50.0f, 50.0f);
        H4.attachChild(tiledSprite2);
    }

    private void checkActionDown(Point point) {
        checkPress(point);
    }

    private void checkActionMove(Point point) {
        checkPress(point);
    }

    private void checkActionUp(Point point) {
        for (int i = 0; i < 7; i++) {
            if (isClicked(this.empireBackgrounds.get(i), point)) {
                this.game.playerCreationScene.setSelected(this.empireBackgrounds.get(i).getCurrentTileIndex());
                this.game.sounds.playBoxPressSound();
                Game game = this.game;
                game.vibrate(game.BUTTON_VIBRATE);
            } else if (A(this.empireBackgrounds.get(i), point, 0.0f, 0.0f)) {
                this.game.playerCreationScene.showPlayerAlreadySelectedMessage();
                this.game.sounds.playBoxPressSound();
                Game game2 = this.game;
                game2.vibrate(game2.BUTTON_VIBRATE);
            }
        }
        if (isClicked(this.randomEmpireBackground, point)) {
            this.game.playerCreationScene.randomEmpireSelected();
            this.game.sounds.playBoxPressSound();
            Game game3 = this.game;
            game3.vibrate(game3.BUTTON_VIBRATE);
        }
        if (isClicked(this.customEmpireBackground, point)) {
            this.game.playerCreationScene.customEmpireSelected();
            this.game.sounds.playBoxPressSound();
            Game game4 = this.game;
            game4.vibrate(game4.BUTTON_VIBRATE);
        }
    }

    private void checkPress(Point point) {
        for (int i = 0; i < 7; i++) {
            if (isClicked(this.empireBackgrounds.get(i), point)) {
                this.pressSprite.setX(this.empireBackgrounds.get(i).getX());
                this.pressSprite.setY(this.empireBackgrounds.get(i).getY());
                this.pressSprite.setHeight(150.0f);
                this.pressSprite.setVisible(true);
            }
        }
        if (isClicked(this.randomEmpireBackground, point)) {
            this.pressSprite.setX(this.randomEmpireBackground.getX());
            this.pressSprite.setY(this.randomEmpireBackground.getY());
            this.pressSprite.setHeight(72.0f);
            this.pressSprite.setVisible(true);
        }
        if (isClicked(this.customEmpireBackground, point)) {
            this.pressSprite.setX(this.customEmpireBackground.getX());
            this.pressSprite.setY(this.customEmpireBackground.getY());
            this.pressSprite.setHeight(72.0f);
            this.pressSprite.setVisible(true);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public void checkInputOnControl(int i, Point point) {
        Point point2 = new Point(point.getX() - getX(), point.getY() - getY());
        if (i == 0) {
            checkActionDown(point2);
        } else if (i == 1) {
            this.pressSprite.setVisible(false);
            checkActionUp(point2);
        } else if (i != 2) {
        } else {
            this.pressSprite.setVisible(false);
            checkActionMove(point2);
        }
    }

    public float getControlWidth() {
        return 1040.0f;
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(ExtendedScene extendedScene, Object obj) {
    }

    public void set(int i, CreateEmpireInfo[] createEmpireInfoArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 7; i3++) {
            if (i3 == i) {
                Sprite sprite = this.selectedSprite;
                float f2 = i3 * GameProperties.WORMHOLE_MAX_SIZE;
                sprite.setX(f2);
                this.selectedSprite2.setX(f2);
                this.selectedSprite.setY(0.0f);
                this.selectedSprite2.setY(0.0f);
                this.selectedSprite.setHeight(150.0f);
                this.selectedSprite2.setHeight(150.0f);
            }
            this.emperorImages.set(i2, this.game.graphics.ambassadorIcons[createEmpireInfoArr[i3].getRaceID()]);//.get(i2).setTiledTextureRegion(this.game.graphics.ambassadorIcons[createEmpireInfoArr[i3].getRaceID()]);

            //this.emperorImages.get(i2).setTiledTextureRegion(this.game.graphics.ambassadorIcons[createEmpireInfoArr[i3].getRaceID()]);
            this.empireBackgrounds.set(i2,CurrentTileIndex(i3));//.get(i2).setCurrentTileIndex(i3);
           // this.empireBackgrounds.get(i2).setCurrentTileIndex(i3);

            this.empireBanners.get(i2).setCurrentTileIndex(GameIconEnum.getEmpireBannerNonEmpireLookup(createEmpireInfoArr[i3].getBannerID()));
            float f3 = 1.0f;
            if (createEmpireInfoArr[i3].getEmpireType() == EmpireType.HUMAN) {
                f3 = 0.4f;
            }
            this.emperorImages.get(i2).setAlpha(f3);
            this.empireBackgrounds.get(i2).setAlpha(f3);
            this.empireBanners.get(i2).setAlpha(f3);
            i2++;
        }
        setSelected(i);
    }

    public void setSelected(int i) {
        if (i == -2) {
            this.selectedSprite.setX(this.randomEmpireBackground.getX());
            this.selectedSprite2.setX(this.randomEmpireBackground.getX());
            this.selectedSprite.setY(0.0f);
            this.selectedSprite2.setY(0.0f);
            this.selectedSprite.setHeight(72.0f);
            this.selectedSprite2.setHeight(72.0f);
        }
        if (i == -3) {
            this.selectedSprite.setX(this.customEmpireBackground.getX());
            this.selectedSprite2.setX(this.customEmpireBackground.getX());
            this.selectedSprite.setY(this.customEmpireBackground.getY());
            this.selectedSprite2.setY(this.customEmpireBackground.getY());
            this.selectedSprite.setHeight(72.0f);
            this.selectedSprite2.setHeight(72.0f);
        }
    }
}
