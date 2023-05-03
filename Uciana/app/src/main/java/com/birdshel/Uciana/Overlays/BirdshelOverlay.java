package com.birdshel.Uciana.Overlays;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BirdshelOverlay extends ExtendedChildScene {
    private final TiledSprite closeButton;
    private final Sprite discordButton;
    private final Sprite press;
    private final Sprite youtubeButton;

    public BirdshelOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.G.setAlpha(0.6f);
        Sprite t = t(0.0f, 150.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 440.0f);
        t.setAlpha(0.9f);
        u((getWidth() / 2.0f) - 200.0f, 225.0f, game.fonts.menuFont, "Social Links:", new TextOptions(), vertexBufferObjectManager);
        this.I = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        TiledSprite w = w(getWidth() - 120.0f, 150.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w;
        n(w);
        Sprite sprite = new Sprite((getWidth() / 2.0f) - 200.0f, 0.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
        this.press = sprite;
        sprite.setSize(400.0f, 80.0f);
        sprite.setVisible(false);
        attachChild(sprite);
        Sprite sprite2 = new Sprite((getWidth() / 2.0f) - 200.0f, 275.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        this.discordButton = sprite2;
        sprite2.setAlpha(0.8f);
        sprite2.setSize(400.0f, 80.0f);
        attachChild(sprite2);
        Sprite sprite3 = new Sprite(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        sprite3.setAlpha(0.4f);
        sprite3.setSize(400.0f, 80.0f);
        sprite2.attachChild(sprite3);
        Sprite sprite4 = new Sprite(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        sprite4.setAlpha(0.4f);
        sprite4.setSize(400.0f, 80.0f);
        sprite2.attachChild(sprite4);
        Text text = new Text(25.0f, 0.0f, game.fonts.menuFont, "DISCORD", new TextOptions(), vertexBufferObjectManager);
        text.setY(42.0f - (text.getHeightScaled() / 2.0f));
        sprite2.attachChild(text);
        Sprite sprite5 = new Sprite((getWidth() / 2.0f) - 200.0f, 375.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        this.youtubeButton = sprite5;
        sprite5.setAlpha(0.8f);
        sprite5.setSize(400.0f, 80.0f);
        attachChild(sprite5);
        Sprite sprite6 = new Sprite(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        sprite6.setAlpha(0.4f);
        sprite6.setSize(400.0f, 80.0f);
        sprite5.attachChild(sprite6);
        Sprite sprite7 = new Sprite(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        sprite7.setAlpha(0.4f);
        sprite7.setSize(400.0f, 80.0f);
        sprite5.attachChild(sprite7);
        Text text2 = new Text(25.0f, 0.0f, game.fonts.menuFont, "YouTube", new TextOptions(), vertexBufferObjectManager);
        text2.setY(42.0f - (text2.getHeightScaled() / 2.0f));
        sprite5.attachChild(text2);
        this.J = new MessageOverlay(game, vertexBufferObjectManager);
    }

    private void checkActionUp(Point point) {
        if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
        if (q(this.discordButton, point)) {
            discordPressed();
        }
        if (q(this.youtubeButton, point)) {
            youtubePressed();
        }
    }

    private void checkPress(Point point) {
        if (q(this.discordButton, point)) {
            this.press.setVisible(true);
            this.press.setY(this.discordButton.getY());
        }
        if (q(this.youtubeButton, point)) {
            this.press.setVisible(true);
            this.press.setY(this.youtubeButton.getY());
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void discordPressed() {
        openURL("https://discord.gg/fmv5uSr");
    }

    private void googlePlayStorePressed() {
        try {
            this.C.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:Birdshel")));
        } catch (ActivityNotFoundException unused) {
            this.C.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/search?q=pub:Birdshel")));
        }
    }

    private void openURL(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.C.getActivity().startActivity(intent);
    }

    private void youtubePressed() {
        openURL("https://www.youtube.com/channel/UCI4eKYRDAfvog9RVL6zTjRg");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i != 0) {
            if (i == 1) {
                this.press.setVisible(false);
                checkActionUp(point);
                return;
            } else if (i != 2) {
                return;
            } else {
                this.press.setVisible(false);
            }
        }
        checkPress(point);
    }

    public void setOverlay() {
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
