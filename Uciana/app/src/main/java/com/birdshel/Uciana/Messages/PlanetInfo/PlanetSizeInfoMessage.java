package com.birdshel.Uciana.Messages.PlanetInfo;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.PlanetSize;
import com.birdshel.Uciana.R;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetSizeInfoMessage implements Message {
    private final PlanetSize planetSize;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Messages.PlanetInfo.PlanetSizeInfoMessage$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1370a;

        static {
            int[] iArr = new int[PlanetSize.values().length];
            f1370a = iArr;
            try {
                iArr[PlanetSize.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1370a[PlanetSize.MED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1370a[PlanetSize.LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1370a[PlanetSize.EXTRA_LARGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public PlanetSizeInfoMessage(PlanetSize planetSize) {
        this.planetSize = planetSize;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        String string;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 25.0f, 270.0f, game.graphics.planetInfoTexture, buffer);
        tiledSprite.setCurrentTileIndex(this.planetSize.getInfoIconIndex());
        tiledSprite.setSize(50.0f, 50.0f);
        messageOverlay.addElement(tiledSprite);
        Font font = game.fonts.infoFont;
        String string2 = game.getActivity().getString(R.string.planet_size_info_header, new Object[]{this.planetSize.getDisplayName()});
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string2, new TextOptions(horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        int i = AnonymousClass1.f1370a[this.planetSize.ordinal()];
        if (i == 1) {
            string = game.getActivity().getString(R.string.planet_size_info_small);
        } else if (i == 2) {
            string = game.getActivity().getString(R.string.planet_size_info_med);
        } else if (i != 3) {
            string = i != 4 ? "" : game.getActivity().getString(R.string.planet_size_info_extra_large);
        } else {
            string = game.getActivity().getString(R.string.planet_size_info_large);
        }
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, string, new TextOptions(AutoWrap.WORDS, 1200.0f, horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
