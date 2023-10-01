package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.TradeItem;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.RaceDiscussScene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SelectCreditAmountOverlay extends ExtendedChildScene {
    private final int[] amounts;
    private final TiledSprite[] backgrounds;
    private final TiledSprite closeButton;
    private final Text[] creditAmounts;
    private final TiledSprite[] creditIcons;
    private final Scene scene;
    private final Sprite selectPress;
    private TradeItem tradeItem;
    private int tradeListIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectCreditAmountOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager, Scene scene) {
        super(game, vertexBufferObjectManager, false);
        this.amounts = new int[5];
        this.backgrounds = new TiledSprite[5];
        this.creditAmounts = new Text[5];
        this.creditIcons = new TiledSprite[5];
        this.scene = scene;
        this.G.setAlpha(0.6f);
        Sprite t = t(0.0f, 190.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 340.0f);
        t.setAlpha(0.9f);
        this.I = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        Sprite t2 = t(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = t2;
        t2.setSize(200.0f, 100.0f);
        Text u = u(0.0f, 230.0f, game.fonts.infoFont, game.getActivity().getString(R.string.select_credit_amount), this.F, vertexBufferObjectManager);
        u.setX((getWidth() / 2.0f) - (u.getWidthScaled() / 2.0f));
        int[] iArr = {10, 25, 50, 75, 100};
        for (int i = 0; i < 5; i++) {
            TiledSprite w = w((i * 210) + ((getWidth() - 1050.0f) / 2.0f), 300.0f, game.graphics.empireColors, vertexBufferObjectManager, 4, true);
            w.setAlpha(0.5f);
            w.setSize(200.0f, 100.0f);
            this.backgrounds[i] = w;
            Font font = game.fonts.infoFont;
            Text u2 = u(0.0f, 340.0f, font, iArr[i] + "%", this.F, vertexBufferObjectManager);
            u2.setX((w.getX() + 100.0f) - (u2.getWidthScaled() / 2.0f));
            this.creditAmounts[i] = u(0.0f, 420.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
            this.creditIcons[i] = w(0.0f, 415.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.CREDITS.ordinal(), true);
        }
        TiledSprite w2 = w(getWidth() - 120.0f, 190.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w2;
        n(w2);
    }

    private void checkActionUp(Point point) {
        if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
        int i = 0;
        for (TiledSprite tiledSprite : this.backgrounds) {
            if (q(tiledSprite, point)) {
                creditBoxPressed(i);
            }
            i++;
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void creditBoxPressed(int i) {
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        this.tradeItem.setAmount(this.amounts[i]);
        Scene scene = this.scene;
        RaceDiscussScene raceDiscussScene = this.C.raceDiscussScene;
        if (scene == raceDiscussScene) {
            raceDiscussScene.addItem(this.tradeItem, this.tradeListIndex);
        }
        back();
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        TiledSprite[] tiledSpriteArr;
        super.checkInput(i, point);
        if (i != 0) {
            if (i == 1) {
                this.selectPress.setVisible(false);
                checkActionUp(point);
                return;
            } else if (i != 2) {
                return;
            } else {
                this.selectPress.setVisible(false);
            }
        }
        for (TiledSprite tiledSprite : this.backgrounds) {
            if (q(tiledSprite, point)) {
                this.selectPress.setVisible(true);
                this.selectPress.setPosition(tiledSprite.getX(), tiledSprite.getY());
            }
        }
    }

    public void setOverlay(TradeItem tradeItem, int i, int i2) {
        this.tradeItem = tradeItem;
        this.tradeListIndex = i;
        Empire empire = this.C.empires.get(i2);
        float[] fArr = {0.1f, 0.25f, 0.5f, 0.75f, 1.0f};
        int i3 = 0;
        for (int i4 = 0; i4 < 5; i4++) {
            this.amounts[i3] = (int) (empire.getCredits() * fArr[i4]);
            this.creditAmounts[i3].setText(Integer.toString(this.amounts[i3]));
            this.creditAmounts[i3].setX((this.backgrounds[i3].getX() + 100.0f) - ((this.creditAmounts[i3].getWidthScaled() + 35.0f) / 2.0f));
            this.creditIcons[i3].setX(this.creditAmounts[i3].getX() + this.creditAmounts[i3].getWidthScaled() + 5.0f);
            i3++;
        }
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
