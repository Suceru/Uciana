package com.birdshel.Uciana.Messages.Tutorials;

import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Overlays.MessageOverlay;

import org.andengine.entity.text.Text;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FleetControlTutorial extends Tutorial {
    @Override // com.birdshel.Uciana.Messages.Tutorials.Tutorial, com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        super.set(messageOverlay);
        messageOverlay.addElement(new Text(50.0f, 220.0f, this.f1374a.fonts.smallFont, "Fleet Control Tutorial", this.b));
        messageOverlay.addAction(MessageAction.CLOSE);
    }
}
