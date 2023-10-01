package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;

import org.andengine.entity.Entity;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public interface RandomEvent {
    boolean checkEvent(Game game);

    int execute(Game game);

    Entity getMessage(MessageOverlay messageOverlay, int i, int i2, int i3, int i4);

    boolean initialize(Game game);
}
