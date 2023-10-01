package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Controls.FoodProductionScienceControl;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonyViewTutorial extends Tutorial {
    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        Game game = this.f1374a;
        Font font = game.fonts.smallFont;
        String string = game.getActivity().getString(R.string.colony_view_tutorial_header);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        Text text = new Text(50.0f, 220.0f, font, string, new TextOptions(autoWrap, 350.0f, horizontalAlign), this.b);
        messageOverlay.addElement(text);
        text.setY(230.0f - (text.getHeightScaled() / 2.0f));
        Game game2 = this.f1374a;
        messageOverlay.addElement(new Text(425.0f, 215.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.colony_view_tutorial_message), new TextOptions(autoWrap, 855.0f, horizontalAlign), this.b));
        FoodProductionScienceControl foodProductionScienceControl = new FoodProductionScienceControl(this.f1374a, this.b);
        Game game3 = this.f1374a;
        foodProductionScienceControl.set(game3.empires.get(game3.getCurrentPlayer()).getColonies().get(0));
        foodProductionScienceControl.setPosition(100.0f, 300.0f);
        messageOverlay.addElement(foodProductionScienceControl);
        Line line = new Line(600.0f, 315.0f, 700.0f, 315.0f, this.b);
        line.setLineWidth(2.0f);
        messageOverlay.addElement(line);
        Game game4 = this.f1374a;
        messageOverlay.addElement(new Text(720.0f, 295.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.colony_view_tutorial_output), new TextOptions(autoWrap, 560.0f, horizontalAlign), this.b));
        Line line2 = new Line(600.0f, 425.0f, 700.0f, 425.0f, this.b);
        line2.setLineWidth(2.0f);
        messageOverlay.addElement(line2);
        Game game5 = this.f1374a;
        messageOverlay.addElement(new Text(720.0f, 405.0f, game5.fonts.smallInfoFont, game5.getActivity().getString(R.string.colony_view_tutorial_slider), new TextOptions(autoWrap, 560.0f, horizontalAlign), this.b));
        Game game6 = this.f1374a;
        Text text2 = new Text(0.0f, 475.0f, game6.fonts.smallInfoFont, game6.getActivity().getString(R.string.colony_view_tutorial_slider_count), new TextOptions(autoWrap, 1200.0f, HorizontalAlign.CENTER), this.b);
        text2.setX(640.0f - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
