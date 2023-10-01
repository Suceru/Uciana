package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GalaxyViewButtonsTutorial extends Tutorial {
    private final int empireID;

    public GalaxyViewButtonsTutorial(int i) {
        this.empireID = i;
    }

    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Game game = this.f1374a;
        messageOverlay.addElement(new Text(50.0f, 220.0f, game.fonts.smallFont, game.getActivity().getString(R.string.galaxy_view_buttons_header), this.b));
        Sprite sprite = new Sprite(0.0f, 400.0f, this.f1374a.graphics.colonySeparatorTexture, this.b);
        sprite.setSize(messageOverlay.getWidth(), 120.0f);
        messageOverlay.addElement(sprite);
        TiledSprite tiledSprite = new TiledSprite(55.0f, 260.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite.setCurrentTileIndex(ButtonsEnum.MENU.ordinal());
        messageOverlay.addElement(tiledSprite);
        Game game2 = this.f1374a;
        Text text = new Text(0.0f, 350.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.galaxy_view_buttons_menu), this.b);
        text.setTextOptions(textOptions);
        text.setX(115.0f - (text.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text);
        TiledSprite tiledSprite2 = new TiledSprite(230.0f, 260.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite2.setCurrentTileIndex(ButtonsEnum.FLEETS.ordinal());
        messageOverlay.addElement(tiledSprite2);
        Game game3 = this.f1374a;
        Text text2 = new Text(0.0f, 350.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.galaxy_view_buttons_fleets), this.b);
        text2.setTextOptions(textOptions);
        text2.setX(290.0f - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        EmpireButton empireButton = new EmpireButton(this.f1374a, this.b);
        empireButton.setPosition(405.0f, 260.0f);
        empireButton.set(this.empireID);
        messageOverlay.addElement(empireButton);
        Game game4 = this.f1374a;
        Text text3 = new Text(0.0f, 350.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.galaxy_view_buttons_empire), this.b);
        text3.setTextOptions(textOptions);
        text3.setX(465.0f - (text3.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text3);
        TiledSprite tiledSprite3 = new TiledSprite(580.0f, 260.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite3.setCurrentTileIndex(ButtonsEnum.COLONIES.ordinal());
        messageOverlay.addElement(tiledSprite3);
        Game game5 = this.f1374a;
        Text text4 = new Text(0.0f, 350.0f, game5.fonts.smallInfoFont, game5.getActivity().getString(R.string.galaxy_view_buttons_colonies), this.b);
        text4.setTextOptions(textOptions);
        text4.setX(640.0f - (text4.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text4);
        TiledSprite tiledSprite4 = new TiledSprite(755.0f, 260.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite4.setCurrentTileIndex(ButtonsEnum.RACES.ordinal());
        messageOverlay.addElement(tiledSprite4);
        Game game6 = this.f1374a;
        Text text5 = new Text(0.0f, 350.0f, game6.fonts.smallInfoFont, game6.getActivity().getString(R.string.galaxy_view_buttons_diplomacy), this.b);
        text5.setTextOptions(textOptions);
        text5.setX(815.0f - (text5.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text5);
        TiledSprite tiledSprite5 = new TiledSprite(930.0f, 260.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite5.setCurrentTileIndex(ButtonsEnum.SCIENCE.ordinal());
        messageOverlay.addElement(tiledSprite5);
        Game game7 = this.f1374a;
        Text text6 = new Text(0.0f, 350.0f, game7.fonts.smallInfoFont, game7.getActivity().getString(R.string.galaxy_view_buttons_research), this.b);
        text6.setTextOptions(textOptions);
        text6.setX(990.0f - (text6.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text6);
        TiledSprite tiledSprite6 = new TiledSprite(1105.0f, 260.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite6.setCurrentTileIndex(ButtonsEnum.EVENTS.ordinal());
        messageOverlay.addElement(tiledSprite6);
        Game game8 = this.f1374a;
        Text text7 = new Text(0.0f, 350.0f, game8.fonts.smallInfoFont, game8.getActivity().getString(R.string.galaxy_view_buttons_events), this.b);
        text7.setTextOptions(textOptions);
        text7.setX(1165.0f - (text7.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text7);
        Game game9 = this.f1374a;
        Text text8 = new Text(10.0f, 0.0f, game9.fonts.smallInfoFont, game9.getActivity().getString(R.string.galaxy_view_buttons_turn_button), this.b);
        text8.setY(460.0f - (text8.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text8);
        TiledSprite tiledSprite7 = new TiledSprite(200.0f, 395.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite7.setCurrentTileIndex(ButtonsEnum.TURN.ordinal());
        messageOverlay.addElement(tiledSprite7);
        Game game10 = this.f1374a;
        Text text9 = new Text(0.0f, 480.0f, game10.fonts.smallInfoFont, game10.getActivity().getString(R.string.galaxy_view_buttons_next_turn), this.b);
        text9.setTextOptions(textOptions);
        text9.setX(260.0f - (text9.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text9);
        TiledSprite tiledSprite8 = new TiledSprite(450.0f, 395.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite8.setCurrentTileIndex(ButtonsEnum.PRODUCTION_WARN.ordinal());
        messageOverlay.addElement(tiledSprite8);
        Game game11 = this.f1374a;
        Text text10 = new Text(0.0f, 480.0f, game11.fonts.smallInfoFont, game11.getActivity().getString(R.string.galaxy_view_buttons_colony_needs_production), this.b);
        text10.setTextOptions(textOptions);
        text10.setX(510.0f - (text10.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text10);
        TiledSprite tiledSprite9 = new TiledSprite(800.0f, 395.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite9.setCurrentTileIndex(ButtonsEnum.TECH_WARN.ordinal());
        messageOverlay.addElement(tiledSprite9);
        Game game12 = this.f1374a;
        Text text11 = new Text(0.0f, 480.0f, game12.fonts.smallInfoFont, game12.getActivity().getString(R.string.galaxy_view_buttons_research_needs_set), this.b);
        text11.setTextOptions(textOptions);
        text11.setX(860.0f - (text11.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text11);
        TiledSprite tiledSprite10 = new TiledSprite(1090.0f, 395.0f, this.f1374a.graphics.buttonsTexture, this.b);
        tiledSprite10.setCurrentTileIndex(ButtonsEnum.CREDITS_WARN.ordinal());
        messageOverlay.addElement(tiledSprite10);
        Game game13 = this.f1374a;
        Text text12 = new Text(0.0f, 480.0f, game13.fonts.smallInfoFont, game13.getActivity().getString(R.string.galaxy_view_buttons_financial_issues), this.b);
        text12.setTextOptions(textOptions);
        text12.setX(1150.0f - (text12.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text12);
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
