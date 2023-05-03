package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetListTutorial extends Tutorial {
    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        this.f1376d.setVisible(true);
        this.f1377e.setVisible(false);
        Game game = this.f1374a;
        messageOverlay.addElement(new Text(50.0f, 220.0f, game.fonts.smallFont, game.getActivity().getString(R.string.planet_list_tutorial_header), this.b));
        Game game2 = this.f1374a;
        Font font = game2.fonts.smallInfoFont;
        String string = game2.getActivity().getString(R.string.planet_list_tutorial_message);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        messageOverlay.addElement(new Text(425.0f, 215.0f, font, string, new TextOptions(autoWrap, 855.0f, horizontalAlign), this.b));
        Game game3 = this.f1374a;
        messageOverlay.addElement(new Text(20.0f, 300.0f, game3.fonts.smallFont, game3.getActivity().getString(R.string.planet_list_tutorial_lists), new TextOptions(autoWrap, 300.0f, horizontalAlign), this.b));
        EmpireButton empireButton = new EmpireButton(this.f1374a, this.b);
        empireButton.setPosition(200.0f, 270.0f);
        empireButton.set(this.f1374a.getCurrentPlayer());
        messageOverlay.addElement(empireButton);
        Game game4 = this.f1374a;
        Font font2 = game4.fonts.smallFont;
        String string2 = game4.getActivity().getString(R.string.planet_list_tutorial_colonies);
        HorizontalAlign horizontalAlign2 = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 360.0f, font2, string2, new TextOptions(autoWrap, 250.0f, horizontalAlign2), this.b);
        text.setX((empireButton.getX() + 60.0f) - (text.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text);
        TiledSprite tiledSprite = new TiledSprite(450.0f, 270.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite.setCurrentTileIndex(ButtonsEnum.PLANET.ordinal());
        messageOverlay.addElement(tiledSprite);
        Game game5 = this.f1374a;
        Text text2 = new Text(0.0f, 360.0f, game5.fonts.smallFont, game5.getActivity().getString(R.string.planet_list_tutorial_uninhabited_planets), new TextOptions(autoWrap, 250.0f, horizontalAlign2), this.b);
        text2.setX((tiledSprite.getX() + 60.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        TiledSprite tiledSprite2 = new TiledSprite(700.0f, 270.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite2.setCurrentTileIndex(ButtonsEnum.RACES.ordinal());
        messageOverlay.addElement(tiledSprite2);
        Game game6 = this.f1374a;
        Text text3 = new Text(0.0f, 360.0f, game6.fonts.smallFont, game6.getActivity().getString(R.string.planet_list_tutorial_inhabited_planets), new TextOptions(autoWrap, 250.0f, horizontalAlign2), this.b);
        text3.setX((tiledSprite2.getX() + 60.0f) - (text3.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text3);
        Sprite sprite = new Sprite(0.0f, 410.0f, this.f1374a.graphics.colonyBackgroundTexture, this.b);
        sprite.setAlpha(0.8f);
        sprite.setSize(655.0f, 100.0f);
        messageOverlay.addElement(sprite);
        Game game7 = this.f1374a;
        messageOverlay.addElement(new Text(10.0f, 420.0f, game7.fonts.smallFont, game7.getActivity().getString(R.string.planet_list_tutorial_colony_planet_info), new TextOptions(autoWrap, 640.0f, horizontalAlign), this.b));
        Sprite sprite2 = new Sprite(660.0f, 410.0f, this.f1374a.graphics.colonyBackgroundTexture, this.b);
        sprite2.setAlpha(0.8f);
        sprite2.setSize(375.0f, 100.0f);
        messageOverlay.addElement(sprite2);
        Game game8 = this.f1374a;
        messageOverlay.addElement(new Text(670.0f, 420.0f, game8.fonts.smallFont, game8.getActivity().getString(R.string.planet_list_tutorial_production), new TextOptions(autoWrap, 360.0f, horizontalAlign), this.b));
        Sprite sprite3 = new Sprite(1040.0f, 410.0f, this.f1374a.graphics.colonyBackgroundTexture, this.b);
        sprite3.setAlpha(0.8f);
        sprite3.setSize(110.0f, 100.0f);
        messageOverlay.addElement(sprite3);
        Game game9 = this.f1374a;
        messageOverlay.addElement(new Text(1050.0f, 420.0f, game9.fonts.smallFont, game9.getActivity().getString(R.string.planet_list_tutorial_buy_now), new TextOptions(autoWrap, 95.0f, horizontalAlign), this.b));
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
