package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.AudioControl;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.OptionID;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class VolumeControl extends Entity {
    private final AudioControl audioControl;
    private final Game game;
    private final TiledSprite muteButton;
    private final OptionID type;
    private final Sprite volumeBackground;
    private final Sprite volumeBarPress;
    private final int VOLUME_BARS = 10;
    private final TiledSprite[] volumeBars = new TiledSprite[10];

    public VolumeControl(AudioControl audioControl, OptionID optionID, Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        this.type = optionID;
        this.audioControl = audioControl;
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        this.volumeBackground = sprite;
        attachChild(sprite);
        sprite.setSize(620.0f, 150.0f);
        attachChild(new Text(135.0f, 5.0f, game.fonts.infoFont, optionID.getDescription(), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager));
        TiledSprite tiledSprite = new TiledSprite(0.0f, 47.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.muteButton = tiledSprite;
        tiledSprite.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        attachChild(tiledSprite);
        for (int i = 0; i < 10; i++) {
            this.volumeBars[i] = new TiledSprite((i * 45) + 135, 40.0f, game.graphics.empireColors, vertexBufferObjectManager);
            this.volumeBars[i].setCurrentTileIndex(2);
            this.volumeBars[i].setSize(35.0f, 86.0f);
            attachChild(this.volumeBars[i]);
        }
        Sprite sprite2 = new Sprite(0.0f, 40.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.volumeBarPress = sprite2;
        sprite2.setSize(35.0f, 86.0f);
        sprite2.setVisible(false);
        attachChild(sprite2);
    }

    private void audioOff(OptionID optionID) {
        if (optionID == OptionID.MUSIC) {
            this.game.music.pause();
        }
        Game.options.turnOff(optionID);
    }

    private void audioOn(OptionID optionID) {
        Game.options.turnOn(optionID);
        if (optionID == OptionID.MUSIC) {
            this.game.music.resume();
        }
    }

    private void checkActionDown(Point point) {
        checkPress(point);
    }

    private void checkActionMove(Point point) {
        this.volumeBarPress.setVisible(false);
        checkPress(point);
    }

    private void checkActionUp(Point point) {
        this.volumeBarPress.setVisible(false);
        if (isClicked(this.muteButton, point)) {
            muteButtonPressed();
        }
        for (int i = 0; i < 10; i++) {
            if (l(this.volumeBars[i], point, 20.0f, 0.0f)) {
                volumeBarPressed(i);
            }
        }
    }

    private void checkPress(Point point) {
        for (int i = 0; i < 10 && this.muteButton.getCurrentTileIndex() != ButtonsEnum.RADIO_OFF.ordinal(); i++) {
            if (l(this.volumeBars[i], point, 20.0f, 0.0f)) {
                this.volumeBarPress.setVisible(true);
                this.volumeBarPress.setX(this.volumeBars[i].getX());
            }
        }
    }

    private void muteButtonPressed() {
        int currentTileIndex = this.muteButton.getCurrentTileIndex();
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_ON;
        float f2 = 1.0f;
        if (currentTileIndex == buttonsEnum.ordinal()) {
            f2 = 0.3f;
            this.muteButton.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
            audioOff(this.type);
            this.volumeBackground.setAlpha(0.7f);
        } else {
            this.muteButton.setCurrentTileIndex(buttonsEnum.ordinal());
            audioOn(this.type);
            this.volumeBackground.setAlpha(1.0f);
        }
        for (TiledSprite tiledSprite : this.volumeBars) {
            tiledSprite.setAlpha(f2);
        }
        this.game.sounds.playButtonPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void volumeBarPressed(int i) {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i2 <= i) {
                this.volumeBars[i2].setCurrentTileIndex(1);
            } else {
                this.volumeBars[i2].setCurrentTileIndex(2);
            }
        }
        if (this.muteButton.getCurrentTileIndex() == ButtonsEnum.RADIO_OFF.ordinal()) {
            this.muteButton.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
            this.volumeBackground.setAlpha(1.0f);
            audioOn(this.type);
            for (TiledSprite tiledSprite : this.volumeBars) {
                tiledSprite.setAlpha(1.0f);
            }
        }
        this.audioControl.setVolume((i + 1) / 10.0f);
        this.game.sounds.playBoxPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public void checkInputOnControl(int i, Point point) {
        Point point2 = new Point(point.getX() - getX(), point.getY() - getY());
        if (i == 0) {
            checkActionDown(point2);
        } else if (i == 1) {
            checkActionUp(point2);
        } else if (i != 2) {
        } else {
            checkActionMove(point2);
        }
    }

    protected boolean isClicked(Entity entity, Point point) {
        return l(entity, point, 0.0f, 0.0f);
    }

    protected boolean l(Entity entity, Point point, float f2, float f3) {
        Sprite sprite = (Sprite) entity;
        if (sprite.getAlpha() == 0.4f) {
            return false;
        }
        float x = sprite.getX() - f2;
        float widthScaled = sprite.getWidthScaled() + x + f2;
        float y = sprite.getY() - f3;
        return sprite.isVisible() && ((point.getX() > x ? 1 : (point.getX() == x ? 0 : -1)) > 0 && (point.getX() > widthScaled ? 1 : (point.getX() == widthScaled ? 0 : -1)) < 0 && (point.getY() > y ? 1 : (point.getY() == y ? 0 : -1)) > 0 && (point.getY() > ((sprite.getHeightScaled() + y) + f3) ? 1 : (point.getY() == ((sprite.getHeightScaled() + y) + f3) ? 0 : -1)) < 0);
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setPosition(float f2, float f3) {
        setX(f2);
        setY(f3);
    }

    public void setVolumeBars(int i, boolean z) {
        if (z) {
            this.muteButton.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
            this.volumeBackground.setAlpha(0.7f);
            for (TiledSprite tiledSprite : this.volumeBars) {
                tiledSprite.setAlpha(0.3f);
            }
        } else {
            this.volumeBackground.setAlpha(1.0f);
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.volumeBars[i2].setCurrentTileIndex(1);
        }
    }
}
