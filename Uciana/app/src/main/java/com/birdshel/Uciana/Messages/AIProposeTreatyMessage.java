package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Players.EmpireMessages;
import com.birdshel.Uciana.Players.RelationStatus;
import com.birdshel.Uciana.Players.Treaty;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AIProposeTreatyMessage implements Message {
    private Text message;
    private MessageOverlay messageOverlay;
    private RelationStatus relationStatus;
    private final Treaty treaty;
    private final int treatyEmpireID;

    public AIProposeTreatyMessage(int i, Treaty treaty) {
        this.treaty = treaty;
        this.treatyEmpireID = i;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        this.messageOverlay = messageOverlay;
        game.graphics.setAmbassadorTexture(game.empires.get(this.treatyEmpireID).getRaceID(), game.getActivity());
        Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
        sprite.setSize(200.0f, 250.0f);
        messageOverlay.addElement(sprite);
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 50.0f, 240.0f, game.graphics.gameIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(this.treaty.getIconIndex());
        messageOverlay.addElement(tiledSprite);
        Text text = new Text(0.0f, 345.0f, game.fonts.smallFont, CharBuffer.wrap(new char[255]), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setText(this.treaty.getName());
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f));
        messageOverlay.addElement(text);
        this.relationStatus = RelationStatus.getRelationStatus(game.empires.get(this.treatyEmpireID).getRelationValue(game.getCurrentPlayer()));
        Text text2 = new Text(250.0f, 410.0f, game.fonts.infoFont, CharBuffer.wrap(new char[255]), new TextOptions(AutoWrap.WORDS, 1020.0f, HorizontalAlign.LEFT), buffer);
        this.message = text2;
        if (this.treaty == Treaty.PEACE_TREATY) {
            text2.setText(EmpireMessages.getAskForPeaceMessage(this.treatyEmpireID, this.relationStatus.getMessageIndex()));
        } else {
            text2.setText(EmpireMessages.getProposeTreatyMessage(this.treatyEmpireID, this.relationStatus.getMessageIndex(), this.treaty.getName()));
        }
        Text text3 = this.message;
        text3.setY(475.0f - text3.getHeightScaled());
        messageOverlay.addElement(this.message);
        messageOverlay.setMessageType(MessageType.AI_PROPOSE_TREATY);
        messageOverlay.addAction(MessageAction.OK);
        messageOverlay.addAction(MessageAction.CLOSE);
        Map<MessageActionData, Object> hashMap = new HashMap<>();
        hashMap.put(MessageActionData.EMPIRE_ID, Integer.valueOf(game.getCurrentPlayer()));
        hashMap.put(MessageActionData.OTHER_EMPIRE_ID, Integer.valueOf(this.treatyEmpireID));
        hashMap.put(MessageActionData.TREATY, this.treaty);
        hashMap.put(MessageActionData.CALLBACK, this);
        messageOverlay.setMessageActionData(hashMap);
    }

    public void setResponse(boolean z) {
        this.messageOverlay.removeActions();
        if (z) {
            this.message.setText(EmpireMessages.getProposalAcceptedResponseMessage(this.treatyEmpireID, this.relationStatus.getMessageIndex()));
        } else {
            this.message.setText(EmpireMessages.getProposalRejectedResponseMessage(this.treatyEmpireID, this.relationStatus.getMessageIndex()));
        }
    }
}
