package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.ChangeLocaleMessage;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.SupportedLocales;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class LanguageSelectOverlay extends ExtendedChildScene {
    private final TiledSprite closeButton;
    private final TiledSprite english;
    private final TiledSprite french;
    private final TiledSprite german;
    private final TiledSprite italian;
    private final TiledSprite japanese;
    private final TiledSprite korean;
    private final TiledSprite polish;
    private final TiledSprite portuguese;
    private final TiledSprite russian;
    private final TiledSprite spanish;
    private final TiledSprite turkish;

    public LanguageSelectOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.G.setAlpha(0.6f);
        Sprite t = t(0.0f, 150.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 440.0f);
        t.setAlpha(0.9f);
        this.I = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        SupportedLocales locale = game.getLocale();
        this.english = setLanguageButton(10, 170, "English", InfoIconEnum.ENGLISH.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.ENGLISH);
        this.german = setLanguageButton(410, 170, "Deutsch", InfoIconEnum.GERMAN.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.GERMAN);
        this.russian = setLanguageButton(810, 170, "\u0440\u0443\u0441\u0441\u043a\u0438\u0439", InfoIconEnum.RUSSIAN.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.RUSSIAN);
        this.spanish = setLanguageButton(10, 270, "Espa\u00f1ol", InfoIconEnum.SPANISH.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.SPANISH);
        this.japanese = setLanguageButton(410, 270, "\u65e5\u672c\u8a9e", InfoIconEnum.JAPANESE.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.JAPANESE);
        this.polish = setLanguageButton(810, 270, "Polskie", InfoIconEnum.POLISH.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.POLISH);
        this.french = setLanguageButton(10, 370, "fran\u00e7ais", InfoIconEnum.FRENCH.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.FRENCH);
        this.portuguese = setLanguageButton(410, 370, "Portugu\u00eas", InfoIconEnum.PORTUGUESE.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.PORTUGUESE);
        this.italian = setLanguageButton(810, 370, "italiano", InfoIconEnum.ITALIAN.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.ITALIAN);
        this.turkish = setLanguageButton(10, 470, "T\u00fcrk", InfoIconEnum.TURKISH.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.TURKISH);
        this.korean = setLanguageButton(410, 470, "\ud55c\uad6d\uc5b4", InfoIconEnum.KOREAN.ordinal(), vertexBufferObjectManager, locale == SupportedLocales.KOREAN);
        TiledSprite w = w(getWidth() - 120.0f, 150.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w;
        n(w);
        this.J = new MessageOverlay(game, vertexBufferObjectManager);
    }

    private void changeLocalePressed(String str) {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        this.C.getActivity().setLocale(str);
        showMessage(new ChangeLocaleMessage());
    }

    private void checkActionUp(Point point) {
        if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
        if (q(this.english, point)) {
            changeLocalePressed(SupportedLocales.ENGLISH.getLetters());
        }
        if (q(this.german, point)) {
            changeLocalePressed(SupportedLocales.GERMAN.getLetters());
        }
        if (q(this.russian, point)) {
            changeLocalePressed(SupportedLocales.RUSSIAN.getLetters());
        }
        if (q(this.japanese, point)) {
            changeLocalePressed(SupportedLocales.JAPANESE.getLetters());
        }
        if (q(this.spanish, point)) {
            changeLocalePressed(SupportedLocales.SPANISH.getLetters());
        }
        if (q(this.french, point)) {
            changeLocalePressed(SupportedLocales.FRENCH.getLetters());
        }
        if (q(this.polish, point)) {
            changeLocalePressed(SupportedLocales.POLISH.getLetters());
        }
        if (q(this.portuguese, point)) {
            changeLocalePressed(SupportedLocales.PORTUGUESE.getLetters());
        }
        if (q(this.italian, point)) {
            changeLocalePressed(SupportedLocales.ITALIAN.getLetters());
        }
        if (q(this.turkish, point)) {
            changeLocalePressed(SupportedLocales.TURKISH.getLetters());
        }
        if (q(this.korean, point)) {
            changeLocalePressed(SupportedLocales.KOREAN.getLetters());
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private TiledSprite setLanguageButton(int i, int i2, String str, int i3, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        TiledSprite w = w(i, i2, this.C.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.BLANK.ordinal(), true);
        n(w);
        TiledSprite tiledSprite = new TiledSprite(40.0f, 23.0f, this.C.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite.setCurrentTileIndex(i3);
        tiledSprite.setSize(40.0f, 40.0f);
        w.attachChild(tiledSprite);
        Text text = new Text(120.0f, 43.0f, this.C.fonts.infoFont, str, vertexBufferObjectManager);
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
        w.attachChild(text);
        if (z) {
            w.setAlpha(0.4f);
            tiledSprite.setAlpha(0.4f);
            text.setAlpha(0.4f);
        }
        return w;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp(point);
        }
    }

    public void setOverlay() {
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
