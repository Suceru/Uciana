package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SystemViewTutorial extends Tutorial {
    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        Game game = this.f1374a;
        Font font = game.fonts.smallFont;
        String string = game.getActivity().getString(R.string.system_view_tutorial_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        Text text = new Text(50.0f, 220.0f, font, string, new TextOptions(autoWrap, 350.0f, horizontalAlign), this.b);
        messageOverlay.addElement(text);
        text.setY(230.0f - (text.getHeightScaled() / 2.0f));
        Game game2 = this.f1374a;
        messageOverlay.addElement(new Text(75.0f, 300.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.system_view_tutorial_press), new TextOptions(autoWrap, 620.0f, horizontalAlign), this.b));
        TiledSprite tiledSprite = new TiledSprite(200.0f, 350.0f, this.f1374a.graphics.planetsTextureRegion[Climate.SUPER_ACIDIC.getID()], this.b);
        tiledSprite.setCurrentTileIndex(0);
        tiledSprite.setSize(100.0f, 100.0f);
        messageOverlay.addElement(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(400.0f, 350.0f, this.f1374a.graphics.asteroidBeltsTexture, this.b);
        tiledSprite2.setSize(40.0f, 100.0f);
        messageOverlay.addElement(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(400.0f, 350.0f, this.f1374a.graphics.asteroidBeltsTexture, this.b);
        tiledSprite3.setSize(40.0f, 100.0f);
        messageOverlay.addElement(tiledSprite3);
        Game game3 = this.f1374a;
        Text text2 = new Text(0.0f, 220.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.system_view_tutorial_buttons), this.f1375c, this.b);
        text2.setX(956.0f - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        TiledSprite tiledSprite4 = new TiledSprite(695.0f, 250.0f, this.f1374a.graphics.buttonsTexture, this.b);
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK_BLUE;
        tiledSprite4.setCurrentTileIndex(buttonsEnum.ordinal());
        messageOverlay.addElement(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(695.0f, 336.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite5.setCurrentTileIndex(buttonsEnum.ordinal());
        messageOverlay.addElement(tiledSprite5);
        TiledSprite tiledSprite6 = new TiledSprite(695.0f, 422.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite6.setCurrentTileIndex(buttonsEnum.ordinal());
        messageOverlay.addElement(tiledSprite6);
        TiledSprite tiledSprite7 = new TiledSprite(735.0f, 273.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite7.setCurrentTileIndex(InfoIconEnum.SCOUT_ICON.ordinal());
        tiledSprite7.setSize(40.0f, 40.0f);
        messageOverlay.addElement(tiledSprite7);
        TiledSprite tiledSprite8 = new TiledSprite(735.0f, 359.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite8.setCurrentTileIndex(InfoIconEnum.COLONY_ICON.ordinal());
        tiledSprite8.setSize(40.0f, 40.0f);
        messageOverlay.addElement(tiledSprite8);
        TiledSprite tiledSprite9 = new TiledSprite(735.0f, 445.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite9.setCurrentTileIndex(InfoIconEnum.WORKER_ICON.ordinal());
        tiledSprite9.setSize(40.0f, 40.0f);
        messageOverlay.addElement(tiledSprite9);
        Game game4 = this.f1374a;
        messageOverlay.addElement(new Text(815.0f, 260.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.system_view_tutorial_scout), new TextOptions(autoWrap, 465.0f, horizontalAlign), this.b));
        Game game5 = this.f1374a;
        messageOverlay.addElement(new Text(815.0f, 346.0f, game5.fonts.smallInfoFont, game5.getActivity().getString(R.string.system_view_tutorial_colonize), new TextOptions(autoWrap, 465.0f, horizontalAlign), this.b));
        Game game6 = this.f1374a;
        messageOverlay.addElement(new Text(815.0f, 432.0f, game6.fonts.smallInfoFont, game6.getActivity().getString(R.string.system_view_tutorial_outpost), new TextOptions(autoWrap, 465.0f, horizontalAlign), this.b));
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
