package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FirstTimeTutorial extends Tutorial {
    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        messageOverlay.addElement(new Text(50.0f, 220.0f, this.f1374a.fonts.smallFont, "Tutorial Videos", this.b));
        Text text = new Text(0.0f, 0.0f, this.f1374a.fonts.smallFont, "If you have any problems figuring out the game or would like more info\n\nPress the Tutorial Videos button in Options", new TextOptions(HorizontalAlign.CENTER), this.b);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
