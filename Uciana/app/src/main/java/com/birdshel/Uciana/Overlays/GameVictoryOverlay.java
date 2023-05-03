package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameVictoryOverlay extends ExtendedChildScene {
    public static final int COALITION = 1;
    public static final int MILITARY = 0;
    private final Game game;
    private final TiledSprite headerBar;
    private final TiledSprite historyGraphButton;
    private final Sprite historyGraphButtonPress;
    private final Text historyGraphText;
    private final TiledSprite keepPlayingButton;
    private final Sprite keepPlayingButtonPress;
    private final Text keepPlayingText;
    private final TiledSprite menuButton;
    private final Sprite menuButtonPress;
    private final Text menuText;
    private final Sprite surface;
    private final Text timePlayed;
    private final Text victoryText;
    private final Text victoryTypeText;

    public GameVictoryOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, true);
        this.game = game;
        Sprite t = t(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 720.0f);
        t.setAlpha(1.0f);
        TiledSprite x = x(0.0f, 75.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.headerBar = x;
        x.setSize(getWidth(), 75.0f);
        Font font = game.fonts.menuFont;
        String string = game.getActivity().getString(R.string.victory_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        Text u = u(0.0f, 0.0f, font, string, new TextOptions(autoWrap, 1200.0f, HorizontalAlign.CENTER), vertexBufferObjectManager);
        u.setX((getWidth() / 2.0f) - (u.getWidthScaled() / 2.0f));
        u.setY(112.0f - (u.getHeightScaled() / 2.0f));
        float x2 = u.getX() - 60.0f;
        TiledTextureRegion tiledTextureRegion = game.graphics.infoIconsTexture;
        InfoIconEnum infoIconEnum = InfoIconEnum.CAPITAL;
        w(x2, 97.0f, tiledTextureRegion, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        w(u.getX() - 90.0f, 97.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        w(u.getX() - 120.0f, 97.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        w(30.0f + u.getX() + u.getWidthScaled(), 97.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        w(u.getX() + u.getWidthScaled() + 60.0f, 97.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        w(u.getX() + u.getWidthScaled() + 90.0f, 97.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        Sprite t2 = t(0.0f, 150.0f, game.graphics.planetSurfaceTexture, vertexBufferObjectManager, true);
        this.surface = t2;
        t2.setSize(getWidth(), 320.0f);
        this.victoryTypeText = u(0.0f, 480.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.victoryText = u(0.0f, 525.0f, game.fonts.smallFont, this.E, new TextOptions(autoWrap, 810.0f), vertexBufferObjectManager);
        this.timePlayed = u(0.0f, 0.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
        TiledSprite w = w(getWidth() - 225.0f, 560.0f, game.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.historyGraphButton = w;
        w.setSize(225.0f, 75.0f);
        w.setAlpha(0.7f);
        Sprite sprite = new Sprite(2.0f, 2.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.historyGraphButtonPress = sprite;
        sprite.setSize(w.getWidthScaled() - 4.0f, w.getHeightScaled() - 4.0f);
        sprite.setVisible(false);
        w.attachChild(sprite);
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.victory_history_graph), this.F, vertexBufferObjectManager);
        this.historyGraphText = text;
        text.setX(112.0f - (text.getWidthScaled() / 2.0f));
        text.setY(37.0f - (text.getHeightScaled() / 2.0f));
        Color color = Color.BLACK;
        text.setColor(color);
        w.attachChild(text);
        TiledSprite w2 = w(0.0f, 645.0f, game.graphics.empireColors, vertexBufferObjectManager, 10, false);
        this.keepPlayingButton = w2;
        w2.setSize(225.0f, 75.0f);
        w2.setAlpha(0.7f);
        Sprite sprite2 = new Sprite(2.0f, 2.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.keepPlayingButtonPress = sprite2;
        sprite2.setSize(w2.getWidthScaled() - 4.0f, w2.getHeightScaled() - 4.0f);
        sprite2.setVisible(false);
        w2.attachChild(sprite2);
        Text text2 = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.victory_keep_playing), this.F, vertexBufferObjectManager);
        this.keepPlayingText = text2;
        text2.setX(112.0f - (text2.getWidthScaled() / 2.0f));
        text2.setY(37.0f - (text2.getHeightScaled() / 2.0f));
        text2.setColor(color);
        w2.attachChild(text2);
        TiledSprite w3 = w(getWidth() - 225.0f, 645.0f, game.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.menuButton = w3;
        w3.setSize(225.0f, 75.0f);
        w3.setAlpha(0.7f);
        Sprite sprite3 = new Sprite(2.0f, 2.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.menuButtonPress = sprite3;
        sprite3.setSize(w3.getWidthScaled() - 4.0f, w3.getHeightScaled() - 4.0f);
        sprite3.setVisible(false);
        w3.attachChild(sprite3);
        Text text3 = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.victory_done), this.F, vertexBufferObjectManager);
        this.menuText = text3;
        text3.setX(112.0f - (text3.getWidthScaled() / 2.0f));
        text3.setY(37.0f - (text3.getHeightScaled() / 2.0f));
        text3.setColor(color);
        w3.attachChild(text3);
    }

    private void checkActionDown(Point point) {
        checkPressed(point);
    }

    private void checkActionMove(Point point) {
        disablePress();
        checkPressed(point);
    }

    private void checkActionUp(Point point) {
        disablePress();
        if (q(this.menuButton, point)) {
            menuButtonPressed();
        }
        if (q(this.keepPlayingButton, point)) {
            keepPlayingButtonPressed();
        }
        if (q(this.historyGraphButton, point)) {
            historyGraphButtonPressed();
        }
    }

    private void checkPressed(Point point) {
        if (q(this.menuButton, point)) {
            this.menuButtonPress.setVisible(true);
            this.menuText.setColor(Color.WHITE);
        }
        if (q(this.keepPlayingButton, point)) {
            this.keepPlayingButtonPress.setVisible(true);
            this.keepPlayingText.setColor(Color.WHITE);
        }
        if (q(this.historyGraphButton, point)) {
            this.historyGraphButtonPress.setVisible(true);
            this.historyGraphText.setColor(Color.WHITE);
        }
    }

    private void disablePress() {
        this.menuButtonPress.setVisible(false);
        Text text = this.menuText;
        Color color = Color.BLACK;
        text.setColor(color);
        this.keepPlayingButtonPress.setVisible(false);
        this.keepPlayingText.setColor(color);
        this.historyGraphButtonPress.setVisible(false);
        this.historyGraphText.setColor(color);
    }

    private void historyGraphButtonPressed() {
        this.game.sounds.playBoxPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.game;
        game2.statsScene.set(game2.galaxyScene, true);
        Game game3 = this.game;
        game3.setCurrentScene(game3.statsScene);
    }

    private void keepPlayingButtonPressed() {
        this.game.sounds.playBoxPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void menuButtonPressed() {
        this.game.sounds.playBoxPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
        this.game.menuScene.openMenu();
        Game game2 = this.game;
        game2.galaxyScene.changeScene(game2.menuScene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
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

    public void setOverlay(int i, int i2) {
        String str = "Victories/MilitaryVictory.png";
        if (i2 == 0) {
            this.headerBar.setCurrentTileIndex(i);
            this.victoryTypeText.setText(this.game.getActivity().getString(R.string.victory_military));
            this.victoryText.setText(Empires.getVictoryText(this.game.getActivity(), i));
            this.keepPlayingButton.setVisible(false);
        } else if (i2 == 1) {
            this.headerBar.setCurrentTileIndex(6);
            this.victoryTypeText.setText(this.game.getActivity().getString(R.string.victory_coalition));
            this.victoryText.setText(this.game.getActivity().getString(R.string.victory_coalition_message));
            this.keepPlayingButton.setVisible(true);
            str = "Victories/CoalitionVictory.png";
        }
        Game game = this.game;
        this.surface.setTiledTextureRegion(game.graphics.setSurfaceTexture(str, game.getActivity()));
        this.victoryText.setX((getWidth() / 2.0f) - (this.victoryText.getWidthScaled() / 2.0f));
        this.victoryTypeText.setX((getWidth() / 2.0f) - (this.victoryTypeText.getWidthScaled() / 2.0f));
        this.timePlayed.setText(this.game.getActivity().getString(R.string.empire_time_played, new Object[]{Functions.convertSecondsIntoHMS(this.game.getTimePlayed() / 1000, true)}));
        this.timePlayed.setX((getWidth() / 2.0f) - (this.timePlayed.getWidthScaled() / 2.0f));
        Text text = this.timePlayed;
        text.setY(715.0f - text.getHeightScaled());
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
