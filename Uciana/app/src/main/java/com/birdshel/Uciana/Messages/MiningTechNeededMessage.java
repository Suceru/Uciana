package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MiningTechNeededMessage implements Message {
    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.mining_tech_needed), new TextOptions(HorizontalAlign.CENTER), messageOverlay.getBuffer());
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
    }
}
