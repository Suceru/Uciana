package com.birdshel.Uciana.Elements.LoadSaveScene;

import com.birdshel.Uciana.Difficulty;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameSaveTile extends Entity {
    private final TiledSprite background;
    private final TiledSprite difficulty;
    private final TiledSprite empireBackground;
    private final TiledSprite empireBanner;
    private final Game game;
    private final Text saveName;
    private final Text timePlayed;
    private final Text timeStamp;
    private final Text turnCount;

    public GameSaveTile(int i, int i2, VertexBufferObjectManager vertexBufferObjectManager, Game game) {
        this.game = game;
        setX(i);
        setY(i2);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.background = tiledSprite;
        tiledSprite.setAlpha(0.8f);
        tiledSprite.setSize(550.0f, 80.0f);
        attachChild(tiledSprite);
        Font font = game.fonts.smallInfoFont;
        CharBuffer wrap = CharBuffer.wrap(new char[50]);
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(5.0f, 2.0f, font, wrap, new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.saveName = text;
        attachChild(text);
        Text text2 = new Text(0.0f, 2.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[50]), new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.timeStamp = text2;
        text2.setColor(new Color(0.75f, 0.75f, 0.75f));
        attachChild(text2);
        Text text3 = new Text(125.0f, 42.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[WeaponStats.SUBSPACE_CHARGE_SPEED]), new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.turnCount = text3;
        attachChild(text3);
        Text text4 = new Text(0.0f, 42.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[50]), new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.timePlayed = text4;
        attachChild(text4);
        TiledSprite tiledSprite2 = new TiledSprite(3.0f, 27.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.empireBackground = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(0);
        tiledSprite2.setVisible(false);
        tiledSprite2.setSize(50.0f, 50.0f);
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(3.0f, 27.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.empireBanner = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(0);
        tiledSprite3.setVisible(false);
        tiledSprite3.setSize(50.0f, 50.0f);
        attachChild(tiledSprite3);
        TiledSprite tiledSprite4 = new TiledSprite(65.0f, 27.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.difficulty = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(0);
        tiledSprite4.setVisible(false);
        tiledSprite4.setSize(50.0f, 50.0f);
        attachChild(tiledSprite4);
    }

    public void disable() {
        this.background.setAlpha(0.4f);
        this.turnCount.setAlpha(0.4f);
        this.timeStamp.setAlpha(0.4f);
        this.timePlayed.setAlpha(0.4f);
        this.empireBackground.setAlpha(0.4f);
        this.empireBanner.setAlpha(0.4f);
        this.difficulty.setAlpha(0.4f);
    }

    public void enable() {
        this.background.setAlpha(0.8f);
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public float getAlpha() {
        return this.background.getAlpha();
    }

    public void hideInfo() {
        this.timeStamp.setVisible(false);
        this.turnCount.setVisible(false);
        this.timePlayed.setVisible(false);
        this.empireBackground.setVisible(false);
        this.empireBanner.setVisible(false);
        this.difficulty.setVisible(false);
    }

    public boolean isPressed(Point point) {
        return this.background.getAlpha() != 0.4f && point.getX() > getX() && point.getX() < getX() + this.background.getWidthScaled() && point.getY() > getY() && point.getY() < getY() + this.background.getHeightScaled();
    }

    public void setBackground(int i) {
        this.background.setCurrentTileIndex(i);
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty.setCurrentTileIndex(difficulty.getInfoIconIndex());
        this.difficulty.setVisible(true);
        this.difficulty.setAlpha(1.0f);
    }

    public void setEmpireBanner(int i, int i2) {
        this.empireBackground.setVisible(true);
        this.empireBackground.setCurrentTileIndex(i);
        this.empireBackground.setAlpha(1.0f);
        this.empireBanner.setVisible(true);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBannerNonEmpireLookup(i2));
        this.empireBanner.setAlpha(1.0f);
    }

    public void setSaveName(String str) {
        this.saveName.setText(str);
    }

    public void setTimeStamp(String str) {
        this.timeStamp.setText(str);
        Text text = this.timeStamp;
        text.setX(540.0f - text.getWidthScaled());
        this.timeStamp.setVisible(true);
        this.timeStamp.setAlpha(1.0f);
    }

    public void setTurnCountAndTimePlayed(String str, long j) {
        Text text = this.turnCount;
        text.setText(str + " " + this.game.getActivity().getString(R.string.save_turns));
        this.turnCount.setVisible(true);
        this.turnCount.setAlpha(1.0f);
        this.timePlayed.setText(Functions.convertSecondsIntoHMS(j, false));
        this.timePlayed.setVisible(true);
        this.timePlayed.setAlpha(1.0f);
        this.timePlayed.setX(this.turnCount.getX() + this.turnCount.getWidthScaled() + 25.0f);
    }

    public void showPermissionMessage() {
        this.turnCount.setVisible(true);
        this.turnCount.setAlpha(1.0f);
        this.turnCount.setText(this.game.getActivity().getString(R.string.save_permission_needed));
    }
}
