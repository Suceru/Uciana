package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;

import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class UnableToRenameMessage implements Message {
    private final boolean hasPlayers;

    public UnableToRenameMessage(boolean z) {
        this.hasPlayers = z;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        String string = game.getActivity().getString(R.string.unable_to_rename_no_only_one);
        if (!this.hasPlayers) {
            string = game.getActivity().getString(R.string.unable_to_rename_no_presence);
        }
        Text text = new Text(0.0f, 0.0f, game.fonts.smallFont, string, new TextOptions(AutoWrap.WORDS, 1200.0f, HorizontalAlign.CENTER), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
    }
}
