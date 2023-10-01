package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.AIProposeTreatyMessage;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Messages.MessageAction;
import com.birdshel.Uciana.Messages.MessageActionData;
import com.birdshel.Uciana.Messages.MessageType;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.Resources.ButtonsEnum;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MessageOverlay extends ExtendedChildScene {
    private final List<Entity> actionButtons;
    private final List<MessageAction> actions;
    private final VertexBufferObjectManager bufferManager;
    private final List<Entity> elements;
    private Map<MessageActionData, Object> messageActionData;
    private final Sprite messageBackground;
    private MessageType messageType;
    private List<Message> messages;
    private final List<PlanetSprite> planetsToBePooled;
    private final List<AnimatedSprite> starsToBePooled;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Overlays.MessageOverlay$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1387a;
        static final /* synthetic */ int[] b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f1388c;

        static {
            int[] iArr = new int[ButtonsEnum.values().length];
            f1388c = iArr;
            try {
                iArr[ButtonsEnum.SYSTEM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1388c[ButtonsEnum.SCIENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1388c[ButtonsEnum.COLONIES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1388c[ButtonsEnum.FLEETS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1388c[ButtonsEnum.SHIP_YARD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1388c[ButtonsEnum.EMPIRE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1388c[ButtonsEnum.CLOSE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1388c[ButtonsEnum.OK.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1388c[ButtonsEnum.HISTORY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr2 = new int[MessageType.values().length];
            b = iArr2;
            try {
                iArr2[MessageType.CREDIT_ALERT.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[MessageType.DIPLOMATIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[MessageType.AI_PROPOSE_TREATY.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[MessageType.EMPIRE_DESTROYED.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[MessageType.MODDING_CHANGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[MessageType.CHANGE_LOCALE.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[MessageType.SYSTEM_DISCOVERY.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                b[MessageType.TERRAFORMING.ordinal()] = 8;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                b[MessageType.UNKNOWN_SYSTEM.ordinal()] = 9;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr3 = new int[MessageAction.values().length];
            f1387a = iArr3;
            try {
                iArr3[MessageAction.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f1387a[MessageAction.OPEN_SYSTEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f1387a[MessageAction.OPEN_RESEARCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f1387a[MessageAction.OPEN_EMPIRE_INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f1387a[MessageAction.OPEN_COLONIES.ordinal()] = 5;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f1387a[MessageAction.OPEN_FLEETS.ordinal()] = 6;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f1387a[MessageAction.OPEN_SHIP_DESIGN.ordinal()] = 7;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f1387a[MessageAction.OPEN_RACE_SCENE.ordinal()] = 8;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f1387a[MessageAction.OK.ordinal()] = 9;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f1387a[MessageAction.HISTORY.ordinal()] = 10;
            } catch (NoSuchFieldError unused28) {
            }
        }
    }

    public MessageOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.elements = new ArrayList();
        this.actionButtons = new ArrayList();
        this.planetsToBePooled = new ArrayList();
        this.starsToBePooled = new ArrayList();
        this.messages = new ArrayList();
        this.actions = new ArrayList();
        this.messageActionData = new HashMap<MessageActionData, Object>() { // from class: com.birdshel.Uciana.Overlays.MessageOverlay.1
        };
        this.bufferManager = vertexBufferObjectManager;
        this.G.setAlpha(0.6f);
        Sprite t = t(0.0f, 240.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        this.messageBackground = t;
        t.setSize(getWidth(), 240.0f);
        t.setAlpha(0.9f);
        this.I = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
    }

    private void acceptTreatyButtonPressed() {
        int intValue = ((Integer) this.messageActionData.get(MessageActionData.EMPIRE_ID)).intValue();
        int intValue2 = ((Integer) this.messageActionData.get(MessageActionData.OTHER_EMPIRE_ID)).intValue();
        Treaty treaty = (Treaty) this.messageActionData.get(MessageActionData.TREATY);
        if (treaty == Treaty.PEACE_TREATY) {
            this.C.empires.get(intValue2).getTreaties().makePeace(intValue);
            this.C.empires.get(intValue).getTreaties().makePeace(intValue2);
        } else {
            this.C.empires.get(intValue2).getTreaties().addTreaty(intValue, treaty);
            this.C.empires.get(intValue).getTreaties().addTreaty(intValue2, treaty);
        }
        this.C.galaxyScene.empireInfo.update();
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        ((AIProposeTreatyMessage) this.messageActionData.get(MessageActionData.CALLBACK)).setResponse(true);
    }

    private void actionButtonPressed(Entity entity) {
        switch (AnonymousClass3.f1388c[ButtonsEnum.values()[((TiledSprite) entity).getCurrentTileIndex()].ordinal()]) {
            case 1:
                if (this.messageType == MessageType.SYSTEM_DISCOVERY) {
                    systemDiscoveryButtonPressed();
                    return;
                }
                return;
            case 2:
                if (this.messageType == MessageType.TECH_DISCOVERY) {
                    techDiscoveryButtonPressed();
                    return;
                }
                return;
            case 3:
                if (this.messageType == MessageType.CREDIT_ALERT) {
                    coloniesButtonPressed();
                    return;
                }
                return;
            case 4:
                if (this.messageType == MessageType.CREDIT_ALERT) {
                    fleetsButtonPressed();
                    return;
                }
                return;
            case 5:
                if (this.messageType == MessageType.TECH_DISCOVERY) {
                    shipDesignButtonPressed();
                    return;
                }
                return;
            case 6:
                int i = AnonymousClass3.b[this.messageType.ordinal()];
                if (i == 1) {
                    empireButtonPressed();
                    return;
                } else if (i != 2) {
                    return;
                } else {
                    diplomaticButtonPressed();
                    return;
                }
            case 7:
                int i2 = AnonymousClass3.b[this.messageType.ordinal()];
                if (i2 == 1) {
                    this.C.galaxyScene.setTurnButtonToTurn();
                } else if (i2 == 3) {
                    aiProposeTreatyRejectButtonPressed();
                    return;
                } else if (i2 == 4) {
                    empireDestroyedCloseButtonPressed();
                } else if (i2 == 5 || i2 == 6) {
                    this.C.exit();
                }
                closeButtonPressed();
                return;
            case 8:
                if (this.messageType == MessageType.AI_PROPOSE_TREATY) {
                    acceptTreatyButtonPressed();
                    return;
                }
                return;
            case 9:
                historyGraphButtonPressed();
                return;
            default:
                return;
        }
    }

    private void addActionButton(float f2, int i) {
        TiledSprite tiledSprite = new TiledSprite(f2, this.messageBackground.getY() + this.messageBackground.getHeightScaled(), this.C.graphics.buttonsTexture, this.bufferManager);
        tiledSprite.setCurrentTileIndex(i);
        n(tiledSprite);
        this.actionButtons.add(tiledSprite);
        attachChild(tiledSprite);
    }

    private void addEmpireActionButton(float f2, int i) {
        EmpireButton empireButton = new EmpireButton(this.C, this.bufferManager);
        empireButton.setPosition(f2, this.messageBackground.getY() + this.messageBackground.getHeightScaled());
        empireButton.set(i);
        n(empireButton);
        this.actionButtons.add(empireButton);
        attachChild(empireButton);
    }

    private void aiProposeTreatyRejectButtonPressed() {
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        ((AIProposeTreatyMessage) this.messageActionData.get(MessageActionData.CALLBACK)).setResponse(false);
    }

    private void checkActionUp(Point point) {
        Map<MessageActionData, Object> map = this.messageActionData;
        MessageActionData messageActionData = MessageActionData.CLOSE_TO_RACE;
        if (map.containsKey(messageActionData)) {
            int intValue = ((Integer) this.messageActionData.get(messageActionData)).intValue();
            if (this.C.empires.get(intValue).isAlive()) {
                this.C.raceScene.set(intValue);
                this.C.getCurrentScene().changeScene(this.C.raceScene);
                this.C.sounds.playBoxPressSound();
                Game game = this.C;
                game.vibrate(game.BUTTON_VIBRATE);
            }
        }
        if (this.actions.isEmpty()) {
            this.C.sounds.playBoxPressSound();
            Game game2 = this.C;
            game2.vibrate(game2.BUTTON_VIBRATE);
            checkForNextMessage();
            return;
        }
        for (Entity entity : this.actionButtons) {
            if (q((Sprite) entity, point)) {
                actionButtonPressed(entity);
            }
        }
    }

    private void checkForNextMessage() {
        releasePoolElements();
        if (this.messages.isEmpty()) {
            back();
            this.C.galaxyScene.showButtons();
            return;
        }
        setOverlay();
    }

    private void clearOverlay() {
        for (Entity entity : this.elements) {
            entity.setVisible(false);
        }
        for (Entity entity2 : this.actionButtons) {
            entity2.setVisible(false);
        }
        p(this.elements, this);
        p(this.actionButtons, this);
        this.messageBackground.setY(240.0f);
        this.messageBackground.setHeight(240.0f);
        this.messageType = MessageType.NONE;
        this.actions.clear();
        this.messageActionData.clear();
        this.H.clear();
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        checkForNextMessage();
    }

    private void coloniesButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.C;
        game2.galaxyScene.changeScene(game2.coloniesScene);
        back();
    }

    private void diplomaticButtonPressed() {
        int intValue = ((Integer) this.messageActionData.get(MessageActionData.OPEN_TO_RACE)).intValue();
        if (this.C.empires.get(intValue).isAlive()) {
            back();
            this.C.getCurrentScene().changeScene(this.C.raceScene, Integer.valueOf(intValue));
            this.C.sounds.playBoxPressSound();
            Game game = this.C;
            game.vibrate(game.BUTTON_VIBRATE);
        }
    }

    private void empireButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.C;
        game2.galaxyScene.changeScene(game2.empireScene);
        back();
    }

    private void empireDestroyedCloseButtonPressed() {
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        if (this.C.getHumanPlayers().isEmpty()) {
            this.C.setCurrentPlayer(-1);
            this.C.menuScene.openMenu();
            this.C.getCurrentScene().changeScene(this.C.menuScene);
            return;
        }
        for (Colony colony : GameData.colonies.getColonies(this.C.getCurrentPlayer())) {
            GameData.colonies.remove(colony);
        }
        for (Outpost outpost : GameData.outposts.getOutposts(this.C.getCurrentPlayer())) {
            GameData.outposts.remove(outpost);
        }
        GameData.showTurnScene = true;
        this.C.turnDone();
    }

    private void fleetsButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.C;
        game2.galaxyScene.changeScene(game2.fleetsScene);
        back();
    }

    private void historyGraphButtonPressed() {
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.C;
        game2.statsScene.set(game2.galaxyScene, true);
        Game game3 = this.C;
        game3.setCurrentScene(game3.statsScene);
    }

    private void releasePoolElements() {
        int i = AnonymousClass3.b[this.messageType.ordinal()];
        if (i == 7 || i == 8) {
            for (int size = this.elements.size() - 1; size >= 0; size--) {
                if (this.elements.get(size) instanceof PlanetSprite) {
                    this.planetsToBePooled.add((PlanetSprite) this.elements.get(size));
                    this.elements.remove(size);
                }
            }
        } else if (i == 9) {
            for (int size2 = this.elements.size() - 1; size2 >= 0; size2--) {
                if (this.elements.get(size2) instanceof AnimatedSprite) {
                    this.starsToBePooled.add((AnimatedSprite) this.elements.get(size2));
                    this.elements.remove(size2);
                }
            }
        }
        this.C.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Overlays.MessageOverlay.2
            @Override // java.lang.Runnable
            public void run() {
                for (PlanetSprite planetSprite : MessageOverlay.this.planetsToBePooled) {
                    MessageOverlay.this.detachChild(planetSprite);
                    MessageOverlay.this.C.planetSpritePool.push(planetSprite);
                }
                MessageOverlay.this.planetsToBePooled.clear();
                for (AnimatedSprite animatedSprite : MessageOverlay.this.starsToBePooled) {
                    MessageOverlay.this.detachChild(animatedSprite);
                    MessageOverlay.this.C.starSpritePool.push(animatedSprite);
                }
                MessageOverlay.this.starsToBePooled.clear();
            }
        });
    }

    private void setActionButtons() {
        this.actionButtons.clear();
        float width = (getWidth() / 2.0f) - (this.actions.size() * 60);
        for (MessageAction messageAction : this.actions) {
            switch (AnonymousClass3.f1387a[messageAction.ordinal()]) {
                case 1:
                    addActionButton(width, ButtonsEnum.CLOSE.ordinal());
                    break;
                case 2:
                    addActionButton(width, ButtonsEnum.SYSTEM.ordinal());
                    break;
                case 3:
                    addActionButton(width, ButtonsEnum.SCIENCE.ordinal());
                    break;
                case 4:
                    addEmpireActionButton(width, this.C.getCurrentPlayer());
                    break;
                case 5:
                    addActionButton(width, ButtonsEnum.COLONIES.ordinal());
                    break;
                case 6:
                    addActionButton(width, ButtonsEnum.FLEETS.ordinal());
                    break;
                case 7:
                    addActionButton(width, ButtonsEnum.SHIP_YARD.ordinal());
                    break;
                case 8:
                    addEmpireActionButton(width, ((Integer) this.messageActionData.get(MessageActionData.OPEN_TO_RACE)).intValue());
                    break;
                case 9:
                    addActionButton(width, ButtonsEnum.OK.ordinal());
                    break;
                case 10:
                    addActionButton(width, ButtonsEnum.HISTORY.ordinal());
                    break;
            }
            width += 120.0f;
        }
    }

    private void shipDesignButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        this.C.shipDesignScene.set();
        Game game2 = this.C;
        game2.galaxyScene.changeScene(game2.shipDesignScene);
        back();
    }

    private void systemDiscoveryButtonPressed() {
        int intValue = ((Integer) this.messageActionData.get(MessageActionData.SYSTEM_ID)).intValue();
        Game game = this.C;
        game.fleets.removeColonyShipArrived(game.getCurrentPlayer(), intValue);
        Game game2 = this.C;
        game2.galaxyScene.changeScene(game2.systemScene, Integer.valueOf(intValue));
        this.C.sounds.playButtonPressSound();
        Game game3 = this.C;
        game3.vibrate(game3.BUTTON_VIBRATE);
        back();
    }

    private void techDiscoveryButtonPressed() {
        this.C.techScene.set();
        Game game = this.C;
        game.galaxyScene.changeScene(game.techScene);
        this.C.sounds.playButtonPressSound();
        Game game2 = this.C;
        game2.vibrate(game2.BUTTON_VIBRATE);
        back();
    }

    public void addAction(MessageAction messageAction) {
        this.actions.add(messageAction);
    }

    public void addElement(Entity entity) {
        this.elements.add(entity);
        attachChild(entity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i != 1) {
            return;
        }
        checkActionUp(point);
    }

    public VertexBufferObjectManager getBuffer() {
        return this.bufferManager;
    }

    public Game getGame() {
        return this.C;
    }

    public Sprite getMessageBackground() {
        return this.messageBackground;
    }

    public int getMessageCount() {
        return this.messages.size();
    }

    public void removeActions() {
        for (Entity entity : this.actionButtons) {
            entity.setVisible(false);
        }
        this.actions.clear();
    }

    public void setMessageActionData(Map<MessageActionData, Object> map) {
        this.messageActionData = map;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public void setOverlay(List<Message> list) {
        this.messages = list;
        setOverlay();
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }

    public void setOverlay(Message message) {
        ArrayList arrayList = new ArrayList();
        this.messages = arrayList;
        arrayList.add(message);
        setOverlay();
    }

    public void setOverlay() {
        clearOverlay();
        this.messages.get(0).set(this);
        this.messages.remove(0);
        setActionButtons();
    }
}
