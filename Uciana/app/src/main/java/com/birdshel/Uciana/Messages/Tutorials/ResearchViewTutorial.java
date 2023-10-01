package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ResearchViewTutorial extends Tutorial {
    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        Game game = this.f1374a;
        Text text = new Text(50.0f, 220.0f, game.fonts.smallFont, game.getActivity().getString(R.string.research_tutorial_header), new TextOptions(AutoWrap.WORDS, 350.0f, HorizontalAlign.LEFT), this.b);
        messageOverlay.addElement(text);
        text.setY(230.0f - (text.getHeightScaled() / 2.0f));
        Game game2 = this.f1374a;
        messageOverlay.addElement(new Text(80.0f, 300.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.research_tutorial_icon), this.f1375c, this.b));
        Game game3 = this.f1374a;
        messageOverlay.addElement(new Text(80.0f, 325.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.research_tutorial_select), this.f1375c, this.b));
        Sprite sprite = new Sprite(150.0f, 360.0f, this.f1374a.graphics.scienceBarTexture, this.b);
        sprite.setAlpha(0.6f);
        sprite.setSize(300.0f, 75.0f);
        messageOverlay.addElement(sprite);
        TiledSprite tiledSprite = new TiledSprite(150.0f, 361.0f, this.f1374a.graphics.gameIconsTexture, this.b);
        tiledSprite.setSize(65.0f, 65.0f);
        tiledSprite.setCurrentTileIndex(GameIconEnum.SCANNING.ordinal());
        messageOverlay.addElement(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(220.0f, 361.0f, this.f1374a.graphics.empireColors, this.b);
        tiledSprite2.setSize(3.0f, 75.0f);
        tiledSprite2.setAlpha(0.9f);
        tiledSprite2.setCurrentTileIndex(6);
        messageOverlay.addElement(tiledSprite2);
        Game game4 = this.f1374a;
        Text text2 = new Text(230.0f, 0.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.tech_tachyon_scanning_short), this.f1375c, this.b);
        text2.setY(396.0f - (text2.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text2);
        messageOverlay.addElement(new Text(405.0f, 363.0f, this.f1374a.fonts.smallInfoFont, "50%", this.b));
        Game game5 = this.f1374a;
        messageOverlay.addElement(new Text(80.0f, 455.0f, game5.fonts.smallInfoFont, game5.getActivity().getString(R.string.research_tutorial_percent), this.f1375c, this.b));
        Game game6 = this.f1374a;
        Text text3 = new Text(0.0f, 260.0f, game6.fonts.smallInfoFont, game6.getActivity().getString(R.string.research_tutorial_background), this.f1375c, this.b);
        text3.setX(966.0f - (text3.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text3);
        Sprite sprite2 = new Sprite(753.0f, 290.0f, this.f1374a.graphics.scienceBarTexture, this.b);
        sprite2.setAlpha(0.6f);
        sprite2.setSize(35.0f, 35.0f);
        messageOverlay.addElement(sprite2);
        Game game7 = this.f1374a;
        messageOverlay.addElement(new Text(800.0f, 300.0f, game7.fonts.smallInfoFont, game7.getActivity().getString(R.string.research_tutorial_can), this.f1375c, this.b));
        Sprite sprite3 = new Sprite(753.0f, 340.0f, this.f1374a.graphics.farmingBarTexture, this.b);
        sprite3.setAlpha(0.9f);
        sprite3.setSize(35.0f, 35.0f);
        messageOverlay.addElement(sprite3);
        Game game8 = this.f1374a;
        messageOverlay.addElement(new Text(800.0f, 350.0f, game8.fonts.smallInfoFont, game8.getActivity().getString(R.string.research_tutorial_current), this.f1375c, this.b));
        Sprite sprite4 = new Sprite(753.0f, 390.0f, this.f1374a.graphics.popEmptyTexture, this.b);
        sprite4.setAlpha(0.6f);
        sprite4.setSize(35.0f, 35.0f);
        messageOverlay.addElement(sprite4);
        Game game9 = this.f1374a;
        messageOverlay.addElement(new Text(800.0f, 400.0f, game9.fonts.smallInfoFont, game9.getActivity().getString(R.string.research_tutorial_completed), this.f1375c, this.b));
        Sprite sprite5 = new Sprite(753.0f, 440.0f, this.f1374a.graphics.colonyBackgroundTexture, this.b);
        sprite5.setAlpha(1.0f);
        sprite5.setSize(35.0f, 35.0f);
        messageOverlay.addElement(sprite5);
        Sprite sprite6 = new Sprite(753.0f, 440.0f, this.f1374a.graphics.fadeBackgroundTexture, this.b);
        sprite6.setAlpha(0.3f);
        sprite6.setSize(35.0f, 35.0f);
        messageOverlay.addElement(sprite6);
        Game game10 = this.f1374a;
        messageOverlay.addElement(new Text(800.0f, 440.0f, game10.fonts.smallInfoFont, game10.getActivity().getString(R.string.research_tutorial_cant), this.f1375c, this.b));
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
