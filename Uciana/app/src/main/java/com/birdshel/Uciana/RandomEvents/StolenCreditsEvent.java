package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;

import org.andengine.entity.Entity;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class StolenCreditsEvent extends CreditsEvent implements RandomEvent {
    @Override // com.birdshel.Uciana.RandomEvents.CreditsEvent, com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean checkEvent(Game game) {
        return super.checkEvent(game);
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public int execute(Game game) {
        return super.a(game, RandomEventType.STOLEN_CREDITS);
    }

    @Override // com.birdshel.Uciana.RandomEvents.CreditsEvent
    public /* bridge */ /* synthetic */ Entity getMessage(MessageOverlay messageOverlay, String str, String str2, int i) {
        return super.getMessage(messageOverlay, str, str2, i);
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean initialize(Game game) {
        int[] iArr = {-100, -150, -200, -250};
        int[] iArr2 = {-200, -300, -400, GameProperties.MINIMUM_CREDITS_ALLOWED};
        if (GameData.turn < 100) {
            return super.b(game, iArr[Functions.random.nextInt(4)]);
        }
        return super.b(game, iArr2[Functions.random.nextInt(4)]);
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public Entity getMessage(MessageOverlay messageOverlay, int i, int i2, int i3, int i4) {
        String string;
        Game game = messageOverlay.getGame();
        if (i2 == game.getCurrentPlayer()) {
            string = game.getActivity().getString(Functions.getResId("credits_stolen_them_" + game.getCurrentPlayer(), R.string.class), new Object[]{Integer.valueOf(i3 * (-1))});
        } else {
            string = game.getActivity().getString(Functions.getResId("credits_stolen_others_" + game.getCurrentPlayer(), R.string.class));
        }
        return super.getMessage(messageOverlay, game.getActivity().getString(R.string.credits_stolen), string, i2);
    }
}
