package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import com.birdshel.Uciana.StarSystems.StarSystem;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TurnScene extends ExtendedScene {
    private TiledSprite menuButton;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private Sprite selectPress;
    public List<AnimatedSprite> stars = new ArrayList();
    private final List<Sprite> blackholes = new ArrayList();
    private final List<TiledSprite> spaceRifts = new ArrayList();
    private final List<Entity> empireBlackBackgrounds = new ArrayList();
    private final List<Entity> empireBackgrounds = new ArrayList();
    private final List<Entity> empireBanners = new ArrayList();
    private final List<Entity> empireNames = new ArrayList();
    private float extraX = 0.0f;

    public TurnScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(Point point) {
        checkEmpireSelectors(point);
    }

    private void checkActionMove(Point point) {
        this.selectPress.setVisible(false);
        checkEmpireSelectors(point);
    }

    private void checkActionUp(Point point) {
        this.selectPress.setVisible(false);
        if (isClicked(this.menuButton, point)) {
            menuButtonPressed();
            return;
        }
        for (Entity entity : this.empireBackgrounds) {
            if (isClicked(entity, point)) {
                empireSelectorPressed(entity);
                return;
            }
        }
    }

    private void checkEmpireSelectors(Point point) {
        for (Entity entity : this.empireBackgrounds) {
            if (isClicked(entity, point)) {
                this.selectPress.setVisible(true);
                this.selectPress.setPosition(entity.getX(), entity.getY());
            }
        }
    }

    private void empireSelectorPressed(Entity entity) {
        this.B.setCurrentPlayer(((TiledSprite) entity).getCurrentTileIndex());
        changeScene(this.B.galaxyScene);
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (AnimatedSprite animatedSprite : this.stars) {
            detachChild(animatedSprite);
            this.B.starSpritePool.push(animatedSprite);
        }
        this.stars.clear();
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void menuButtonPressed() {
        changeScene(this.B.menuScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setBlackholes() {
        for (Blackhole blackhole : this.B.galaxy.getBlackholes()) {
            Point position = blackhole.getPosition();
            Sprite sprite = this.blackholes.get(blackhole.getID());
            sprite.setPosition((position.getX() + this.extraX) - (blackhole.getSize() * 0.25f), position.getY() - (blackhole.getSize() * 0.25f));
            sprite.setSize(blackhole.getSize(), blackhole.getSize());
            sprite.setVisible(true);
            sprite.setFlippedHorizontal(false);
            float f2 = 0.0f;
            float f3 = 360.0f;
            if (blackhole.hasAltSpinDirection()) {
                sprite.setFlippedHorizontal(true);
            } else {
                f2 = 360.0f;
                f3 = 0.0f;
            }
            sprite.setRotationCenter(sprite.getWidthScaled() / 2.0f, sprite.getHeightScaled() / 2.0f);
            sprite.clearEntityModifiers();
            sprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(blackhole.getSpinSpeed(), f2, f3)));
        }
    }

    private void setSpaceRifts() {
        for (SpaceRift spaceRift : this.B.galaxy.getSpaceRifts()) {
            TiledSprite tiledSprite = this.spaceRifts.get(spaceRift.getID());
            tiledSprite.setVisible(true);
            tiledSprite.setPosition(spaceRift.getPosition().getX() + this.extraX, spaceRift.getPosition().getY());
            tiledSprite.setSize(spaceRift.getSize(), spaceRift.getSize());
            tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
            tiledSprite.setFlippedHorizontal(false);
            float f2 = 360.0f;
            float f3 = 0.0f;
            if (spaceRift.hasAltSpinDirection()) {
                tiledSprite.setFlippedHorizontal(true);
            } else {
                f2 = 0.0f;
                f3 = 360.0f;
            }
            tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
            tiledSprite.clearEntityModifiers();
            tiledSprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(spaceRift.getSpinSpeed(), f2, f3)));
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof GalaxyScene) {
            Game game = this.B;
            Game game2 = this.B;
            Game game3 = this.B;
            game3.galaxyScene.setZoom(game3.playerSettings.get(Integer.valueOf(game3.getCurrentPlayer())).getZoomLevel());
            this.B.galaxyScene.L(game.playerSettings.get(Integer.valueOf(game.getCurrentPlayer())).getGalaxyX(), game2.playerSettings.get(Integer.valueOf(game2.getCurrentPlayer())).getGalaxyY());
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof MenuScene) {
            this.B.menuScene.openMenu();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        changeScene(this.B.menuScene);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            checkActionUp(point);
        } else if (i != 2) {
        } else {
            checkActionMove(point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        int i;
        super.createScene(vertexBufferObjectManager);
        if (getWidth() == 1480.0f) {
            this.extraX = 100.0f;
        }
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        for (int i2 = 0; i2 < 50; i2++) {
            Sprite E = E(0.0f, 0.0f, this.B.graphics.blackholeTexture, vertexBufferObjectManager, false);
            E.setAlpha(0.8f);
            this.blackholes.add(E);
            this.spaceRifts.add(H(0.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SPACE_RIFT.ordinal(), false));
        }
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.MENU.ordinal(), true);
        this.menuButton = H;
        s(H);
        for (int i3 = 0; i3 < 7; i3++) {
            float f2 = (i3 * 105) + 50;
            Sprite E2 = E((getWidth() / 2.0f) - 300.0f, f2, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, false);
            E2.setSize(600.0f, 100.0f);
            this.empireBlackBackgrounds.add(E2);
            TiledSprite H2 = H((getWidth() / 2.0f) - 300.0f, f2, this.B.graphics.empireColors, vertexBufferObjectManager, 0, false);
            H2.setSize(600.0f, 100.0f);
            this.empireBackgrounds.add(H2);
            this.empireBanners.add(H(5.0f + ((getWidth() / 2.0f) - 300.0f), f2, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, 0, false));
            this.empireNames.add(G(130.0f + ((getWidth() / 2.0f) - 300.0f), i + 85, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, false));
        }
        Sprite E3 = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = E3;
        E3.setSize(600.0f, 100.0f);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        for (int i = 0; i < 50; i++) {
            AnimatedSprite animatedSprite = this.B.starSpritePool.get();
            animatedSprite.setVisible(false);
            attachChild(animatedSprite);
            this.stars.add(animatedSprite);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.l0
            {
                TurnScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                TurnScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void set() {
        int i;
        C(this.empireBlackBackgrounds);
        C(this.empireBackgrounds);
        C(this.empireBanners);
        C(this.empireNames);
        int i2 = 0;
        this.selectPress.setVisible(false);
        for (Sprite sprite : this.blackholes) {
            sprite.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.spaceRifts) {
            tiledSprite.setVisible(false);
        }
        this.nebulas.set(this.extraX);
        for (StarSystem starSystem : this.B.galaxy.getStarSystems()) {
            int ordinal = (starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4);
            int[] iArr = {ordinal, ordinal + 1, ordinal + 2, ordinal + 3};
            Point position = starSystem.getPosition();
            int id = starSystem.getID();
            this.stars.get(id).setPosition(position.getX() + this.extraX, position.getY());
            this.stars.get(id).setVisible(true);
            this.stars.get(id).setAlpha(0.7f);
            this.stars.get(id).animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, iArr, true);
            this.stars.get(id).setScale(starSystem.getStarSize());
        }
        setBlackholes();
        setSpaceRifts();
        int humanPlayerCount = (720 - (this.B.getHumanPlayerCount() * 105)) / 2;
        for (int i3 = 0; i3 < 7; i3++) {
            float f2 = (i3 * 105) + humanPlayerCount;
            this.empireBlackBackgrounds.get(i3).setY(f2);
            this.empireBackgrounds.get(i3).setY(f2);
            this.empireBanners.get(i3).setY(f2);
            this.empireNames.get(i3).setY(i + 35);
        }
        for (Integer num : this.B.getHumanPlayers()) {
            int intValue = num.intValue();
            ((Sprite) this.empireBlackBackgrounds.get(i2)).setVisible(true);
            TiledSprite tiledSprite2 = (TiledSprite) this.empireBackgrounds.get(i2);
            tiledSprite2.setVisible(true);
            tiledSprite2.setCurrentTileIndex(intValue);
            TiledSprite tiledSprite3 = (TiledSprite) this.empireBanners.get(i2);
            tiledSprite3.setVisible(true);
            tiledSprite3.setCurrentTileIndex(GameIconEnum.getEmpireBanner(intValue));
            Text text = (Text) this.empireNames.get(i2);
            text.setVisible(true);
            text.setText(this.B.empires.get(intValue).getName());
            float f3 = 1.0f;
            if (this.B.getPlayerStatus(intValue) == 1) {
                f3 = 0.4f;
            }
            tiledSprite2.setAlpha(f3);
            tiledSprite3.setAlpha(f3);
            text.setAlpha(f3);
            i2++;
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
