package com.birdshel.Uciana.Resources;

import android.content.res.AssetManager;
import com.birdshel.Uciana.Uciana;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Fonts {
    public Font infoFont;
    public Font menuFont;
    public Font smallFont;
    public Font smallInfoFont;

    public Fonts(Uciana uciana) {
        FontManager fontManager = uciana.getFontManager();
        AssetManager assets = uciana.getAssets();
        TextureManager textureManager = uciana.getTextureManager();
        TextureOptions textureOptions = TextureOptions.BILINEAR;
        Font createFromAsset = FontFactory.createFromAsset(fontManager, new BitmapTextureAtlas(textureManager, 512, 1024, textureOptions), assets, "fonts/Carme_Regular.ttf", 32.0f, true, -1);
        this.infoFont = createFromAsset;
        createFromAsset.load();
        this.menuFont = this.infoFont;
        Font createFromAsset2 = FontFactory.createFromAsset(fontManager, new BitmapTextureAtlas(textureManager, 512, 1024, textureOptions), assets, "fonts/Carme_Regular.ttf", 21.0f, true, -1);
        this.smallInfoFont = createFromAsset2;
        createFromAsset2.load();
        this.smallFont = this.smallInfoFont;
    }
}
