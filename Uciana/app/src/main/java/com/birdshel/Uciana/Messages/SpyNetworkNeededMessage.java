package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SpyNetworkNeededMessage implements Message {
    private final int empireID;

    public SpyNetworkNeededMessage(int i) {
        this.empireID = i;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.spy_network_needed), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setX(((messageOverlay.getWidth() / 2.0f) - 15.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        TiledSprite tiledSprite = new TiledSprite(text.getX() + text.getWidthScaled() + 10.0f, text.getY() - 5.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.EMPIRE_BACKGROUND.ordinal());
        tiledSprite.setColor(Empires.getEmpireColor(this.empireID));
        tiledSprite.setSize(40.0f, 40.0f);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(game.empires.get(this.empireID).getBannerID()));
        tiledSprite2.setSize(40.0f, 40.0f);
        tiledSprite.attachChild(tiledSprite2);
        messageOverlay.addElement(tiledSprite);
    }
}
