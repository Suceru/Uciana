package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Resources.SupportedLocales;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class KeyboardButton extends TiledSprite {
    private final Game game;
    private final int id;
    private final Text letterKey;
    private final int shiftID;

    public KeyboardButton(int i, int i2, float f2, float f3, ITiledTextureRegion iTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, Game game) {
        super(f2, f3, iTiledTextureRegion, vertexBufferObjectManager);
        this.game = game;
        this.id = i;
        this.shiftID = i2;
        Text text = new Text(0.0f, 15.0f, game.fonts.infoFont, "###", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        this.letterKey = text;
        text.setScaleCenter(0.0f, 0.0f);
        text.setScale(1.2f);
        attachChild(text);
    }

    public String getChar() {
        return this.letterKey.getText().toString();
    }

    public void setKey(boolean z) {
        String string = this.game.getActivity().getString(this.id);
        if (z) {
            string = this.game.getActivity().getString(this.shiftID);
        }
        if (string.equals("hide")) {
            setVisible(false);
            return;
        }
        setVisible(true);
        this.letterKey.setText(string);
        float f2 = 0.0f;
        if (this.game.getLocale() == SupportedLocales.JAPANESE && !string.equals("1") && !string.equals("2") && !string.equals("3") && !string.equals("4") && !string.equals("5") && !string.equals("6") && !string.equals("7") && !string.equals("8") && !string.equals("9") && !string.equals("0") && !string.equals("-")) {
            f2 = (getWidth() - getWidthScaled()) / 2.0f;
        }
        this.letterKey.setX(((getWidthScaled() / 2.0f) - ((this.letterKey.getWidth() - this.letterKey.getWidthScaled()) / 2.0f)) - f2);
        this.letterKey.setY(((getHeightScaled() / 2.0f) - ((this.letterKey.getHeight() - this.letterKey.getHeightScaled()) / 2.0f)) - ((getHeight() - getHeightScaled()) / 2.0f));
    }
}
