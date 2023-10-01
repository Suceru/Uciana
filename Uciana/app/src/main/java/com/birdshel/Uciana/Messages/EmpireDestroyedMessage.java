package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.nio.CharBuffer;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EmpireDestroyedMessage implements Message {
    public static final int STARVED = 1;
    public static final int WIPED_OUT = 0;
    private final int empireID;
    private final int why;

    public EmpireDestroyedMessage(int i, int i2) {
        this.empireID = i;
        this.why = i2;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        TiledSprite tiledSprite;
        String string;
        String str;
        String string2;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        messageOverlay.setMessageType(MessageType.EMPIRE_DESTROYED);
        TiledSprite tiledSprite2 = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 50.0f, 240.0f, game.graphics.gameIconsTexture, buffer);
        if (!game.getCurrentEmpire().isEmpireKnown(this.empireID) && game.getCurrentPlayer() != this.empireID) {
            tiledSprite = new TiledSprite(0.0f, 250.0f, game.graphics.productionPercentBarTexture, buffer);
            tiledSprite.setCurrentTileIndex(3);
            tiledSprite2.setCurrentTileIndex(GameIconEnum.MED_GALAXY.ordinal());
        } else {
            tiledSprite = new TiledSprite(0.0f, 250.0f, game.graphics.empireColors, buffer);
            tiledSprite.setCurrentTileIndex(this.empireID);
            tiledSprite2.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.empireID));
        }
        tiledSprite.setSize(messageOverlay.getWidth(), 80.0f);
        messageOverlay.addElement(tiledSprite);
        tiledSprite2.setSize(100.0f, 100.0f);
        messageOverlay.addElement(tiledSprite2);
        if (game.getCurrentPlayer() == this.empireID) {
            String string3 = game.getActivity().getString(R.string.empire_destroyed_player);
            messageOverlay.addAction(MessageAction.HISTORY);
            Object convertSecondsIntoHMS = Functions.convertSecondsIntoHMS(game.getTimePlayed() / 1000, true);
            Text text = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[255]), new TextOptions(HorizontalAlign.CENTER), buffer);
            text.setText(game.getActivity().getString(R.string.empire_time_played, new Object[]{convertSecondsIntoHMS}));
            text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
            text.setY(475.0f - text.getHeightScaled());
            messageOverlay.addElement(text);
            str = string3;
        } else {
            if (!game.getCurrentEmpire().isEmpireKnown(this.empireID) && game.getCurrentPlayer() != this.empireID) {
                string = game.getActivity().getString(R.string.empire_destroyed_unknown);
            } else {
                string = game.getActivity().getString(R.string.empire_destroyed_known, new Object[]{game.empires.get(this.empireID).getName()});
            }
            str = string;
        }
        Font font = game.fonts.infoFont;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text2 = new Text(0.0f, 0.0f, font, str, new TextOptions(horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        text2.setY(360.0f - (text2.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text2);
        int i = this.why;
        if (i == 1) {
            if (game.getCurrentPlayer() == this.empireID) {
                string2 = game.getActivity().getString(R.string.empire_destroyed_player_starve);
            } else {
                string2 = game.getActivity().getString(R.string.empire_destroyed_other_starve);
            }
        } else {
            string2 = i == 0 ? game.getActivity().getString(R.string.empire_destroyed_colonies_wiped_out) : "";
        }
        Text text3 = new Text(0.0f, 390.0f, game.fonts.smallInfoFont, string2, new TextOptions(horizontalAlign), buffer);
        text3.setX((messageOverlay.getWidth() / 2.0f) - (text3.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text3);
        if (game.getCurrentPlayer() == this.empireID) {
            messageOverlay.addAction(MessageAction.CLOSE);
        }
        if (game.getHumanPlayers().isEmpty()) {
            game.setGameEnded(true);
            Game.gameAchievements.gameOver();
        }
    }
}
