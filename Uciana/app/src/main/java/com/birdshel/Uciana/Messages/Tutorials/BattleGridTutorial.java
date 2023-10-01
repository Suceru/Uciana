package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Elements.IonStormSprite;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.IonStorm;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BattleGridTutorial extends Tutorial {
    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        Game game = this.f1374a;
        Font font = game.fonts.smallFont;
        String string = game.getActivity().getString(R.string.battle_grid_tutorial_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        Text text = new Text(50.0f, 220.0f, font, string, new TextOptions(autoWrap, 350.0f, horizontalAlign), this.b);
        text.setY(230.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Game game2 = this.f1374a;
        messageOverlay.addElement(new Text(425.0f, 215.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.battle_grid_tutorial_message), new TextOptions(autoWrap, 855.0f, horizontalAlign), this.b));
        Game game3 = this.f1374a;
        messageOverlay.addElement(new Text(20.0f, 295.0f, game3.fonts.smallFont, game3.getActivity().getString(R.string.battle_grid_tutorial_obstacles), this.b));
        TiledSprite tiledSprite = new TiledSprite(20.0f, 320.0f, this.f1374a.graphics.gameIconsTexture, this.b);
        tiledSprite.setCurrentTileIndex(GameIconEnum.ASTEROID1.ordinal());
        messageOverlay.addElement(tiledSprite);
        Game game4 = this.f1374a;
        messageOverlay.addElement(new Text(130.0f, 360.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.battle_grid_tutorial_asteroids), new TextOptions(autoWrap, 505.0f, horizontalAlign), this.b));
        IonStorm ionStorm = new IonStorm(new Point(10.0f, 400.0f));
        IonStormSprite ionStormSprite = new IonStormSprite(this.f1374a, this.b);
        ionStormSprite.set(ionStorm);
        messageOverlay.addElement(ionStormSprite);
        Game game5 = this.f1374a;
        messageOverlay.addElement(new Text(130.0f, 440.0f, game5.fonts.smallInfoFont, game5.getActivity().getString(R.string.battle_grid_tutorial_ion), new TextOptions(autoWrap, 505.0f, horizontalAlign), this.b));
        Game game6 = this.f1374a;
        messageOverlay.addElement(new Text(640.0f, 295.0f, game6.fonts.smallFont, game6.getActivity().getString(R.string.battle_grid_tutorial_ship_status), this.b));
        TiledSprite tiledSprite2 = new TiledSprite(640.0f, 325.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.LEFT_ARROW.ordinal());
        tiledSprite2.setSize(50.0f, 50.0f);
        messageOverlay.addElement(tiledSprite2);
        Game game7 = this.f1374a;
        messageOverlay.addElement(new Text(700.0f, 340.0f, game7.fonts.smallInfoFont, game7.getActivity().getString(R.string.battle_grid_tutorial_retreat), new TextOptions(horizontalAlign), this.b));
        TiledSprite tiledSprite3 = new TiledSprite(640.0f, 375.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.SUBLIGHT_DISABLED.ordinal());
        tiledSprite3.setSize(50.0f, 50.0f);
        messageOverlay.addElement(tiledSprite3);
        Game game8 = this.f1374a;
        messageOverlay.addElement(new Text(700.0f, 390.0f, game8.fonts.smallInfoFont, game8.getActivity().getString(R.string.battle_grid_tutorial_sublight), new TextOptions(horizontalAlign), this.b));
        TiledSprite tiledSprite4 = new TiledSprite(640.0f, 425.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.FTL_DISABLED.ordinal());
        tiledSprite4.setSize(50.0f, 50.0f);
        messageOverlay.addElement(tiledSprite4);
        Game game9 = this.f1374a;
        messageOverlay.addElement(new Text(700.0f, 440.0f, game9.fonts.smallInfoFont, game9.getActivity().getString(R.string.battle_grid_tutorial_ftl), new TextOptions(horizontalAlign), this.b));
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
