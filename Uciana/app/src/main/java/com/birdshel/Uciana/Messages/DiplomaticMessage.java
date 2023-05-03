package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Players.Empire;
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

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class DiplomaticMessage implements Message {
    private final DiplomaticType diplomaticType;
    private final int empireID;
    private Treaty treaty;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Messages.DiplomaticMessage$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1368a;

        static {
            int[] iArr = new int[DiplomaticType.values().length];
            f1368a = iArr;
            try {
                iArr[DiplomaticType.DECLARED_WAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1368a[DiplomaticType.RESPONSE_TO_WAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1368a[DiplomaticType.BREAK_TREATY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1368a[DiplomaticType.FIRST_CONTACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1368a[DiplomaticType.ACCEPT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1368a[DiplomaticType.REJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1368a[DiplomaticType.GIFT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public DiplomaticMessage(int i, DiplomaticType diplomaticType) {
        this.empireID = i;
        this.diplomaticType = diplomaticType;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite(0.0f, 240.0f, game.graphics.empireColors, buffer);
        tiledSprite.setSize(messageOverlay.getWidth(), 240.0f);
        tiledSprite.setAlpha(0.15f);
        tiledSprite.setVisible(false);
        messageOverlay.addElement(tiledSprite);
        Empire empire = game.empires.get(this.empireID);
        game.graphics.setAmbassadorTexture(empire.getRaceID(), game.getActivity());
        Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
        sprite.setSize(200.0f, 250.0f);
        messageOverlay.addElement(sprite);
        Text text = new Text(220.0f, 0.0f, game.fonts.infoFont, CharBuffer.wrap(new char[255]), new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
        RelationStatus relationStatus = RelationStatus.getRelationStatus(empire.getRelationValue(game.getCurrentPlayer()));
        switch (AnonymousClass1.f1368a[this.diplomaticType.ordinal()]) {
            case 1:
                text.setText(EmpireMessages.getDeclareWarMessage(empire.getRaceID()));
                tiledSprite.setVisible(true);
                tiledSprite.setCurrentTileIndex(0);
                messageOverlay.addAction(MessageAction.CLOSE);
                break;
            case 2:
                text.setText(EmpireMessages.getDeclaredWarOnMessage(empire.getRaceID()));
                break;
            case 3:
                text.setText(EmpireMessages.getBreakTreatyMessage(empire.getRaceID(), relationStatus.getMessageIndex(), this.treaty.getName()));
                tiledSprite.setVisible(true);
                tiledSprite.setCurrentTileIndex(0);
                messageOverlay.addAction(MessageAction.CLOSE);
                break;
            case 4:
                text.setText(EmpireMessages.getFirstContactMessage(empire.getRaceID(), empire.getDisposition(game.getCurrentPlayer())));
                messageOverlay.setMessageType(MessageType.DIPLOMATIC);
                messageOverlay.addAction(MessageAction.OPEN_RACE_SCENE);
                messageOverlay.addAction(MessageAction.CLOSE);
                Map<MessageActionData, Object> hashMap = new HashMap<>();
                hashMap.put(MessageActionData.OPEN_TO_RACE, Integer.valueOf(this.empireID));
                messageOverlay.setMessageActionData(hashMap);
                break;
            case 5:
                text.setText(EmpireMessages.getProposalAcceptedMessage(empire.getRaceID(), relationStatus.getMessageIndex()));
                tiledSprite.setVisible(true);
                tiledSprite.setCurrentTileIndex(1);
                break;
            case 6:
                text.setText(EmpireMessages.getProposalRejectedMessage(empire.getRaceID(), relationStatus.getMessageIndex()));
                tiledSprite.setVisible(true);
                tiledSprite.setCurrentTileIndex(0);
                break;
            case 7:
                text.setText(EmpireMessages.getGiftMessage(empire.getRaceID(), relationStatus.getMessageIndex()));
                break;
        }
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
    }

    public DiplomaticMessage(int i, Treaty treaty) {
        this.empireID = i;
        this.diplomaticType = DiplomaticType.BREAK_TREATY;
        this.treaty = treaty;
    }
}
