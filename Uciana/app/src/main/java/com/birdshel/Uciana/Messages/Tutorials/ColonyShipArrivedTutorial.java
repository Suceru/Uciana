package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonyShipArrivedTutorial extends Tutorial {
    protected void a(Sprite sprite) {
        sprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        sprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.4f, 0.25f, 1.0f), new AlphaModifier(0.4f, 1.0f, 0.25f))));
    }

    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        Game game = this.f1374a;
        Font font = game.fonts.smallFont;
        String string = game.getActivity().getString(R.string.colony_ship_arrived_tutorial_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        Text text = new Text(50.0f, 220.0f, font, string, new TextOptions(autoWrap, 350.0f, HorizontalAlign.LEFT), this.b);
        messageOverlay.addElement(text);
        text.setY(230.0f - (text.getHeightScaled() / 2.0f));
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 25.0f, 220.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite.setSize(50.0f, 50.0f);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.COLONY_SHIP_ARRIVED.ordinal());
        messageOverlay.addElement(tiledSprite);
        a(tiledSprite);
        Game game2 = this.f1374a;
        Font font2 = game2.fonts.infoFont;
        String string2 = game2.getActivity().getString(R.string.colony_ship_arrived_tutorial_message);
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text2 = new Text(0.0f, 320.0f, font2, string2, new TextOptions(horizontalAlign), this.b);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        Text text3 = new Text(0.0f, 390.0f, this.f1374a.fonts.smallInfoFont, this.f1374a.getActivity().getString(R.string.colony_ship_arrived_tutorial_description), new TextOptions(autoWrap, 1200.0f, horizontalAlign), this.b);
        text3.setX((messageOverlay.getWidth() / 2.0f) - (text3.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text3);
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
