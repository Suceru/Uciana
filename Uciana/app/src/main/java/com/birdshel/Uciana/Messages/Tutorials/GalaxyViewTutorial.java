package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.GalaxySize;
import com.birdshel.Uciana.StarSystems.StarType;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GalaxyViewTutorial extends Tutorial {
    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        Game game = this.f1374a;
        Font font = game.fonts.smallFont;
        String string = game.getActivity().getString(R.string.galaxy_view_tutorial_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        Text text = new Text(50.0f, 0.0f, font, string, new TextOptions(autoWrap, 350.0f, horizontalAlign), this.b);
        messageOverlay.addElement(text);
        text.setY(230.0f - (text.getHeightScaled() / 2.0f));
        AnimatedSprite animatedSprite = new AnimatedSprite(500.0f, 300.0f, this.f1374a.graphics.starsTexture, this.b);
        int ordinal = (StarType.values()[Functions.random.nextInt(StarType.values().length)].ordinal() * 12) + (Functions.random.nextInt(3) * 4);
        animatedSprite.animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
        animatedSprite.setSize(75.0f, 75.0f);
        messageOverlay.addElement(animatedSprite);
        Text text2 = new Text(0.0f, animatedSprite.getHeightScaled() + 300.0f, this.f1374a.fonts.smallInfoFont, "Carina", this.b);
        text2.setX(((animatedSprite.getWidthScaled() / 2.0f) + 500.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        TiledSprite tiledSprite = new TiledSprite(495.0f, 310.0f, this.f1374a.graphics.infoIconsTexture, this.b);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.SCOUT_ICON.ordinal());
        tiledSprite.setSize(15.0f, 15.0f);
        messageOverlay.addElement(tiledSprite);
        Text text3 = new Text(0.0f, 310.0f, this.f1374a.fonts.smallInfoFont, "2", this.f1375c, this.b);
        text3.setX((tiledSprite.getX() - text3.getWidthScaled()) - 5.0f);
        messageOverlay.addElement(text3);
        Game game2 = this.f1374a;
        Entity text4 = new Text(275.0f, 450.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.galaxy_view_tutorial_system), new TextOptions(autoWrap, 600.0f, horizontalAlign), this.b);
        messageOverlay.addElement(text4);
        messageOverlay.addElement(new Line(text2.getX() + (text2.getWidthScaled() / 2.0f), text4.getY() - 10.0f, text2.getX() + (text2.getWidthScaled() / 2.0f), text2.getY() + text2.getHeightScaled() + 10.0f, this.b));
        Game game3 = this.f1374a;
        Entity text5 = new Text(30.0f, 310.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.galaxy_view_tutorial_unexplored), new TextOptions(autoWrap, 440.0f, horizontalAlign), this.b);
        messageOverlay.addElement(text5);
        messageOverlay.addElement(new Line(text3.getX() - 50.0f, text5.getY() + 7.0f, text3.getX() - 20.0f, text3.getY() + 7.0f, this.b));
        ShipSprite shipSprite = new ShipSprite(this.f1374a, this.b);
        int currentPlayer = this.f1374a.getCurrentPlayer();
        ShipType shipType = ShipType.COLONY;
        GalaxySize galaxySize = GalaxySize.SMALL;
        shipSprite.setShipIcon(currentPlayer, shipType, galaxySize.getShipSize(), galaxySize.getShipSize(), 1, false);
        shipSprite.setPosition(animatedSprite.getWidthScaled() + 500.0f, 300.0f);
        messageOverlay.addElement(shipSprite);
        Game game4 = this.f1374a;
        Entity text6 = new Text(705.0f, 310.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.galaxy_view_tutorial_fleet), new TextOptions(autoWrap, 460.0f, horizontalAlign), this.b);
        messageOverlay.addElement(text6);
        messageOverlay.addElement(new Line(shipSprite.getX() + shipSprite.getSize() + 20.0f, text5.getY() + 7.0f, text6.getX() - 20.0f, text5.getY() + 7.0f, this.b));
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
