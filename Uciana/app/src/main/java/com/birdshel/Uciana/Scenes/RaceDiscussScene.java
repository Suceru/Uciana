package com.birdshel.Uciana.Scenes;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Elements.RaceDicussScene.TradeItemEntity;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.DiplomaticMessage;
import com.birdshel.Uciana.Messages.DiplomaticType;
import com.birdshel.Uciana.Messages.MessageActionData;
import com.birdshel.Uciana.Messages.SystemExploredMessage;
import com.birdshel.Uciana.Messages.TechInfoMessage;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.SelectCreditAmountOverlay;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.Players.TradeItem;
import com.birdshel.Uciana.Players.TradeItemIDs;
import com.birdshel.Uciana.Players.TradeType;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechID;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class RaceDiscussScene extends ExtendedScene {
    private final int ITEM_SIZE;
    private final int TRADE_ITEM_LIST_Y;
    private Sprite blackenedBackground3;
    private VertexBufferObjectManager bufferManager;
    private int contactID;
    private TiledSprite empire1CancelButton;
    private TiledSprite empire1ConfirmButton;
    private TiledSprite empire1Set;
    private Scene empire1TradeItemList;
    private TiledSprite empire2CancelButton;
    private TiledSprite empire2ConfirmButton;
    private TiledSprite empire2Set;
    private Scene empire2TradeItemList;
    private TiledSprite empireBanner;
    private TiledSprite empireBannerBackground;
    private EmpireButton empireButton;
    private TiledSprite empireConfirmBackground;
    private TiledSprite empireConfirmBackground2;
    private final List<List<TradeItem>> empireTradeItems;
    private final List<List<TradeItemEntity>> empireTradeItemsEntities;
    private int extraX;
    private TiledSprite galaxyButton;
    private boolean isScroll;
    private float lastY;
    private float pressedY;
    private TiledSprite racesButton;
    private ScrollBarControl scrollBarEmpire1;
    private ScrollBarControl scrollBarEmpire2;
    private SelectCreditAmountOverlay selectCreditAmountOverlay;
    private Sprite selectPress;
    private Sprite surface;
    private TiledSprite tradeBackground1;
    private TiledSprite tradeBackground2;
    private boolean tradeChanged;
    private Scene tradeItemList;
    private final List<TradeItem> tradeItems;
    private final List<TradeItemEntity> tradeItemsEntities;
    private ScrollBarControl tradeItemsScrollBar;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.RaceDiscussScene$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1475a;

        static {
            int[] iArr = new int[TradeType.values().length];
            f1475a = iArr;
            try {
                iArr[TradeType.TECH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1475a[TradeType.SYSTEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1475a[TradeType.TREATY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1475a[TradeType.MAPS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1475a[TradeType.CREDITS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public RaceDiscussScene(Game game) {
        ArrayList arrayList = new ArrayList();
        this.empireTradeItemsEntities = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.empireTradeItems = arrayList2;
        this.tradeItemsEntities = new ArrayList();
        this.tradeItems = new ArrayList();
        this.isScroll = false;
        this.TRADE_ITEM_LIST_Y = 86;
        this.ITEM_SIZE = 80;
        this.extraX = 0;
        this.B = game;
        arrayList.add(new ArrayList());
        arrayList.add(new ArrayList());
        arrayList2.add(new ArrayList());
        arrayList2.add(new ArrayList());
    }

    private void checkAIForDeal() {
        if (this.B.empires.get(this.contactID).getDiplomaticAI().isGift(this.tradeItems)) {
            deal();
            this.H.setOverlay(new DiplomaticMessage(this.contactID, DiplomaticType.GIFT));
            HashMap hashMap = new HashMap();
            hashMap.put(MessageActionData.CLOSE_TO_RACE, Integer.valueOf(this.contactID));
            this.H.setMessageActionData(hashMap);
            setChildScene(this.H, false, false, true);
        } else if (this.tradeChanged && this.B.empires.get(this.contactID).getDiplomaticAI().isDealAcceptable(this.tradeItems)) {
            deal();
            this.H.setOverlay(new DiplomaticMessage(this.contactID, DiplomaticType.ACCEPT));
            HashMap hashMap2 = new HashMap();
            hashMap2.put(MessageActionData.CLOSE_TO_RACE, Integer.valueOf(this.contactID));
            this.H.setMessageActionData(hashMap2);
            setChildScene(this.H, false, false, true);
        } else {
            this.H.setOverlay(new DiplomaticMessage(this.contactID, DiplomaticType.REJECT));
            setChildScene(this.H, false, false, true);
            this.tradeChanged = false;
        }
    }

    private void checkAcceptButton() {
        float f2 = this.tradeItems.isEmpty() ? 0.4f : 1.0f;
        this.empire1ConfirmButton.setAlpha(f2);
        this.empire2ConfirmButton.setAlpha(f2);
    }

    private void checkActionDown(Point point) {
        checkPressed(point);
        this.pressedY = point.getY();
        this.lastY = point.getY();
    }

    private void checkActionMove(Point point) {
        this.selectPress.setVisible(false);
        if (point.getX() <= 420.0f && point.getY() > 86.0f && point.getY() < 634.0f) {
            checkEmpire1ListMove(point);
        } else if (point.getX() > this.extraX + TypedValues.Cycle.TYPE_EASING && point.getX() < this.extraX + 860 && point.getY() > 86.0f) {
            checkTradeItemsListMove(point);
        } else if (point.getX() >= (this.extraX * 2) + 860 && point.getY() > 86.0f && point.getY() < this.tradeBackground2.getY() + this.tradeBackground2.getHeightScaled()) {
            checkEmpire2ListMove(point);
        } else {
            this.lastY = point.getY();
        }
        if (this.isScroll) {
            return;
        }
        checkPressed(point);
    }

    private void checkActionUp(Point point) {
        if (hasChildScene()) {
            this.H.back();
            empireButtonPressed();
            return;
        }
        this.selectPress.setVisible(false);
        if (this.isScroll) {
            this.isScroll = false;
            return;
        }
        float y = this.tradeBackground2.getY() + this.tradeBackground2.getHeightScaled();
        if (point.getX() < 340.0f && point.getY() > 86.0f && point.getY() < 634.0f) {
            if (this.empireTradeItems.get(0).size() > getPressedIndex(point)) {
                tradeItemPressed(0, getPressedIndex(point));
            }
        } else if (point.getX() > 340.0f && point.getX() < 420.0f && point.getY() > 86.0f && point.getY() < 634.0f) {
            if (this.empireTradeItems.get(0).size() > getPressedIndex(point)) {
                infoPressed(0, getPressedIndex(point));
            }
        } else if (point.getX() > this.extraX + TypedValues.Cycle.TYPE_EASING && point.getX() < this.extraX + 860 && point.getY() > 86.0f) {
            if (this.tradeItems.size() > getPressedIndex(point)) {
                itemInListPressed(getPressedIndex(point));
            }
        } else if (point.getX() > (this.extraX * 2) + 860 && point.getX() < (this.extraX * 2) + 1200 && point.getY() > 86.0f && point.getY() < y) {
            if (this.empireTradeItems.get(1).size() > getPressedIndex(point)) {
                tradeItemPressed(1, getPressedIndex(point));
            }
        } else if (point.getX() > (this.extraX * 2) + 1200 && point.getY() > 86.0f && point.getY() < y && this.empireTradeItems.get(1).size() > getPressedIndex(point)) {
            infoPressed(1, getPressedIndex(point));
        }
        checkButtons(point);
    }

    private void checkButtons(Point point) {
        if (isClicked(this.empireButton, point)) {
            empireButtonPressed();
        }
        if (isClicked(this.racesButton, point)) {
            racesButtonPressed();
        }
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        }
        if (isClicked(this.empire1ConfirmButton, point)) {
            empire1ConfirmButtonPressed();
        }
        if (isClicked(this.empire1CancelButton, point)) {
            empireButtonPressed();
        }
        if (isClicked(this.empire2ConfirmButton, point)) {
            empire2ConfirmButtonPressed();
        }
        if (isClicked(this.empire2CancelButton, point)) {
            empireButtonPressed();
        }
    }

    private void checkEmpire1ListMove(Point point) {
        if (this.empireTradeItems.get(0).size() * 80 > 548) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y = this.empire1TradeItemList.getY() - (this.lastY - point.getY());
            if (y > 86.0f) {
                y = 86.0f;
            }
            float size = ((this.empireTradeItems.get(0).size() * 80) - 634) * (-1);
            if (y < size) {
                y = size;
            }
            this.empire1TradeItemList.setY(y);
            this.lastY = point.getY();
            this.scrollBarEmpire1.update(y);
        }
    }

    private void checkEmpire2ListMove(Point point) {
        float y = this.tradeBackground2.getY() + this.tradeBackground2.getHeightScaled();
        if (this.empireTradeItems.get(1).size() * 80 > y - 86.0f) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y2 = this.empire2TradeItemList.getY() - (this.lastY - point.getY());
            float f2 = y2 <= 86.0f ? y2 : 86.0f;
            float size = ((this.empireTradeItems.get(1).size() * 80) - y) * (-1.0f);
            if (f2 < size) {
                f2 = size;
            }
            this.empire2TradeItemList.setY(f2);
            this.lastY = point.getY();
            this.scrollBarEmpire2.update(f2);
        }
    }

    private void checkPressed(Point point) {
        float y = this.tradeBackground2.getY() + this.tradeBackground2.getHeightScaled();
        if (point.getX() < 340.0f && point.getY() > 86.0f && point.getY() < 634.0f) {
            if (this.empireTradeItems.get(0).size() > getPressedIndex(point)) {
                showPress(0.0f, this.empire1TradeItemList.getY() + (getPressedIndex(point) * 80), 340);
            }
        } else if (point.getX() > 340.0f && point.getX() < 420.0f && point.getY() > 86.0f && point.getY() < 634.0f) {
            if (this.empireTradeItems.get(0).size() > getPressedIndex(point)) {
                showPress(340.0f, this.empire1TradeItemList.getY() + (getPressedIndex(point) * 80), 60);
            }
        } else if (point.getX() > this.extraX + TypedValues.Cycle.TYPE_EASING && point.getX() < this.extraX + 860 && point.getY() > 86.0f) {
            if (this.tradeItems.size() > getPressedIndex(point)) {
                showPress(this.tradeItemsEntities.get(getPressedIndex(point)).getX() + this.tradeItemList.getX(), this.tradeItemList.getY() + (getPressedIndex(point) * 80), this.tradeItems.get(getPressedIndex(point)).doesRequireBoth() ? 430 : 400);
            }
        } else if (point.getX() > (this.extraX * 2) + 860 && point.getX() < (this.extraX * 2) + 1200 && point.getY() > 86.0f && point.getY() < y) {
            if (this.empireTradeItems.get(1).size() > getPressedIndex(point)) {
                showPress((this.extraX * 2) + 860, this.empire2TradeItemList.getY() + (getPressedIndex(point) * 80), 340);
            }
        } else if (point.getX() <= (this.extraX * 2) + 1200 || point.getY() <= 86.0f || point.getY() >= y || this.empireTradeItems.get(1).size() <= getPressedIndex(point)) {
        } else {
            showPress((this.extraX * 2) + 1200, this.empire2TradeItemList.getY() + (getPressedIndex(point) * 80), 60);
        }
    }

    private void checkTradeItemsListMove(Point point) {
        if (this.tradeItems.size() * 80 > 634) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y = this.tradeItemList.getY() - (this.lastY - point.getY());
            if (y > 86.0f) {
                y = 86.0f;
            }
            float size = ((this.tradeItems.size() * 80) - 720) * (-1);
            if (y < size) {
                y = size;
            }
            this.tradeItemList.setY(y);
            this.lastY = point.getY();
            this.tradeItemsScrollBar.update(y);
        }
    }

    private void deal() {
        for (TradeItem tradeItem : this.tradeItems) {
            int i = AnonymousClass1.f1475a[tradeItem.getType().ordinal()];
            if (i == 1) {
                getTech(tradeItem);
            } else if (i == 2) {
                getSystem(tradeItem);
            } else if (i == 3) {
                setTreaty(tradeItem);
            } else if (i == 4) {
                getExplorationData(tradeItem);
            } else if (i == 5) {
                giveCredits(tradeItem);
            }
        }
    }

    private void empire1ConfirmButtonPressed() {
        if (this.B.empires.get(this.contactID).isAI()) {
            checkAIForDeal();
        } else if (this.empire1Set.isVisible()) {
            this.empire1Set.setVisible(false);
            return;
        } else if (this.empire2Set.isVisible()) {
            deal();
            this.B.raceScene.set(this.contactID);
            changeScene(this.B.raceScene);
        } else {
            this.empire1Set.setVisible(true);
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void empire2ConfirmButtonPressed() {
        if (this.empire2Set.isVisible()) {
            this.empire2Set.setVisible(false);
            return;
        }
        if (this.empire1Set.isVisible()) {
            deal();
            this.B.raceScene.set(this.contactID);
            changeScene(this.B.raceScene);
        } else {
            this.empire2Set.setVisible(true);
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void empireButtonPressed() {
        this.B.raceScene.set(this.contactID);
        changeScene(this.B.raceScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void getExplorationData(TradeItem tradeItem) {
        this.B.empires.get(tradeItem.getEmpire2ID()).getMapFromEmpire(tradeItem.getEmpire1ID());
    }

    private int getPressedIndex(Point point) {
        float y = this.tradeBackground2.getY() + this.tradeBackground2.getHeightScaled();
        if (point.getX() <= 420.0f && point.getY() > 86.0f && point.getY() < 634.0f) {
            return ((int) (point.getY() - this.empire1TradeItemList.getY())) / 80;
        }
        if (point.getX() > this.extraX + TypedValues.Cycle.TYPE_EASING && point.getX() < this.extraX + 860 && point.getY() > 86.0f) {
            return ((int) (point.getY() - this.tradeItemList.getY())) / 80;
        }
        if (point.getX() >= (this.extraX * 2) + 860 && point.getY() > 86.0f && point.getY() < y) {
            return ((int) (point.getY() - this.empire2TradeItemList.getY())) / 80;
        }
        throw new AssertionError("Index not found");
    }

    private void getSystem(TradeItem tradeItem) {
        int parseInt = Integer.parseInt(tradeItem.getID().replace(TradeItemIDs.SYSTEM.getID(), ""));
        this.B.colonies.transferColonies(parseInt, tradeItem.getEmpire1ID(), tradeItem.getEmpire2ID());
        this.B.outposts.transferOutposts(parseInt, tradeItem.getEmpire1ID(), tradeItem.getEmpire1ID());
    }

    private void getTech(TradeItem tradeItem) {
        TechID techID = TechID.values()[Integer.parseInt(tradeItem.getID().replace(TradeItemIDs.TECH.getID(), ""))];
        Tech tech = this.B.empires.get(tradeItem.getEmpire2ID()).getTech().getTech(techID);
        tech.addResearch(tech.getResearchCost());
        this.B.empires.get(tradeItem.getEmpire2ID()).checkForUpgrade(techID);
        this.B.events.addTechEvent(tradeItem.getEmpire2ID(), techID.ordinal(), 1);
    }

    private void giveCredits(TradeItem tradeItem) {
        this.B.empires.get(tradeItem.getEmpire1ID()).addRemoveCredits(tradeItem.getAmount() * (-1));
        this.B.empires.get(tradeItem.getEmpire2ID()).addRemoveCredits(tradeItem.getAmount());
    }

    private void infoPressed(int i, int i2) {
        TradeItem tradeItem = this.empireTradeItems.get(i).get(i2);
        int i3 = AnonymousClass1.f1475a[tradeItem.getType().ordinal()];
        if (i3 == 1) {
            this.H.setOverlay(new TechInfoMessage(TechID.values()[Integer.parseInt(tradeItem.getID().replace(TradeItemIDs.TECH.getID(), ""))]));
            setChildScene(this.H, false, false, true);
        } else if (i3 == 2) {
            this.H.setOverlay(new SystemExploredMessage(Integer.parseInt(tradeItem.getID().replace(TradeItemIDs.SYSTEM.getID(), "")), false));
            setChildScene(this.H, false, false, true);
        } else if (i3 == 3) {
            if (tradeItem.getID().equals(TradeItemIDs.PEACE_TREATY.getID())) {
                this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.race_discuss_peace_desc)));
            } else if (tradeItem.getID().equals(TradeItemIDs.NON_AGGRESSION_PACT.getID())) {
                this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.race_discuss_non_aggression_desc)));
            } else if (tradeItem.getID().equals(TradeItemIDs.ALLIANCE.getID())) {
                this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.race_discuss_alliance_desc)));
            } else if (tradeItem.getID().equals(TradeItemIDs.TRADE.getID())) {
                this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.race_discuss_trade_desc)));
            } else if (tradeItem.getID().equals(TradeItemIDs.RESEARCH.getID())) {
                this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.race_discuss_research_desc)));
            }
            setChildScene(this.H, false, false, true);
        } else if (i3 == 4) {
            this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.race_discuss_maps_desc)));
            setChildScene(this.H, false, false, true);
        } else if (i3 == 5) {
            this.H.setOverlay(new TextMessage(this.B.getActivity().getString(R.string.race_discuss_credits_desc)));
            setChildScene(this.H, false, false, true);
        }
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void itemInListPressed(int i) {
        this.tradeItems.remove(i);
        updateTradeItemList();
        updateSelected();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void racesButtonPressed() {
        changeScene(this.B.racesScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void removeItem(int i, int i2) {
        TradeItem tradeItem = this.empireTradeItems.get(i).get(i2);
        int i3 = this.contactID;
        if (i == 0) {
            i3 = this.B.getCurrentPlayer();
        }
        int i4 = 0;
        Iterator<TradeItem> it = this.tradeItems.iterator();
        while (true) {
            if (!it.hasNext()) {
                i4 = -1;
                break;
            }
            TradeItem next = it.next();
            if (next.doesRequireBoth()) {
                if (next.getID().equals(tradeItem.getID())) {
                    break;
                }
                i4++;
            } else {
                if (next.getEmpire1ID() == i3 && next.getID().equals(tradeItem.getID())) {
                    break;
                }
                i4++;
            }
        }
        if (i4 != -1) {
            this.tradeItems.remove(i4);
        }
        updateSelected();
    }

    private void setTradeItems(int i, int i2, int i3, Scene scene, boolean z) {
        List<TradeItem> list = this.empireTradeItems.get(i3);
        List<TradeItemEntity> list2 = this.empireTradeItemsEntities.get(i3);
        for (TradeItemEntity tradeItemEntity : list2) {
            tradeItemEntity.setVisible(false);
        }
        scene.setY(86.0f);
        int i4 = 0;
        for (TradeItem tradeItem : list) {
            if (i4 >= list2.size()) {
                TradeItemEntity tradeItemEntity2 = new TradeItemEntity(this.B, this.bufferManager);
                tradeItemEntity2.setPosition(0.0f, i4 * 80);
                list2.add(tradeItemEntity2);
                scene.attachChild(tradeItemEntity2);
            }
            tradeItem.setEmpire1ID(i);
            tradeItem.setEmpire2ID(i2);
            list2.get(i4).setVisible(true);
            list2.get(i4).set(i, tradeItem, z, false);
            i4++;
        }
    }

    private void setTreaty(TradeItem tradeItem) {
        Treaty treaty = Treaty.getTreaty(tradeItem.getID());
        if (Treaty.getTreaty(tradeItem.getID()) == Treaty.PEACE_TREATY) {
            this.B.empires.get(tradeItem.getEmpire1ID()).getTreaties().makePeace(tradeItem.getEmpire2ID());
            this.B.empires.get(tradeItem.getEmpire2ID()).getTreaties().makePeace(tradeItem.getEmpire1ID());
            return;
        }
        this.B.empires.get(tradeItem.getEmpire1ID()).getTreaties().addTreaty(tradeItem.getEmpire2ID(), treaty);
        if (tradeItem.doesRequireBoth()) {
            this.B.empires.get(tradeItem.getEmpire2ID()).getTreaties().addTreaty(tradeItem.getEmpire1ID(), treaty);
        }
    }

    private void showPress(float f2, float f3, int i) {
        this.selectPress.setVisible(true);
        this.selectPress.setX(f2);
        this.selectPress.setY(f3);
        this.selectPress.setWidth(i);
    }

    private void tradeItemPressed(int i, int i2) {
        if (this.empireTradeItemsEntities.get(i).get(i2).isSelected()) {
            removeItem(i, i2);
            updateTradeItemList();
        } else {
            TradeItem tradeItem = this.empireTradeItems.get(i).get(i2);
            if (tradeItem.getType() == TradeType.CREDITS) {
                int currentPlayer = this.B.getCurrentPlayer();
                if (i != 0) {
                    currentPlayer = this.contactID;
                }
                this.selectCreditAmountOverlay.setOverlay(tradeItem, i, currentPlayer);
                setChildScene(this.selectCreditAmountOverlay, false, false, true);
            } else {
                addItem(tradeItem, i);
            }
        }
        this.tradeChanged = true;
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void updateSelected() {
        for (TradeItemEntity tradeItemEntity : this.empireTradeItemsEntities.get(0)) {
            tradeItemEntity.unselected();
        }
        for (TradeItemEntity tradeItemEntity2 : this.empireTradeItemsEntities.get(1)) {
            tradeItemEntity2.unselected();
        }
        for (TradeItem tradeItem : this.tradeItems) {
            if (tradeItem.doesRequireBoth()) {
                Iterator<TradeItem> it = this.empireTradeItems.get(0).iterator();
                int i = 0;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (it.next().getID().equals(tradeItem.getID())) {
                        this.empireTradeItemsEntities.get(0).get(i).selected();
                        break;
                    } else {
                        i++;
                    }
                }
                Iterator<TradeItem> it2 = this.empireTradeItems.get(1).iterator();
                int i2 = 0;
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (it2.next().getID().equals(tradeItem.getID())) {
                        this.empireTradeItemsEntities.get(1).get(i2).selected();
                        break;
                    } else {
                        i2++;
                    }
                }
            } else {
                int i3 = this.B.getCurrentPlayer() == tradeItem.getEmpire1ID() ? 0 : 1;
                Iterator<TradeItem> it3 = this.empireTradeItems.get(i3).iterator();
                int i4 = 0;
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    } else if (it3.next().getID().equals(tradeItem.getID())) {
                        this.empireTradeItemsEntities.get(i3).get(i4).selected();
                        break;
                    } else {
                        i4++;
                    }
                }
            }
        }
        checkAcceptButton();
    }

    private void updateTradeItemList() {
        int i;
        boolean z;
        for (TradeItemEntity tradeItemEntity : this.tradeItemsEntities) {
            tradeItemEntity.setVisible(false);
        }
        int i2 = 0;
        for (TradeItem tradeItem : this.tradeItems) {
            if (tradeItem.getEmpire1ID() != this.B.getCurrentPlayer()) {
                i = 30;
                z = true;
            } else {
                i = 0;
                z = false;
            }
            if (i2 >= this.tradeItemsEntities.size()) {
                TradeItemEntity tradeItemEntity2 = new TradeItemEntity(this.B, this.bufferManager);
                this.tradeItemsEntities.add(tradeItemEntity2);
                this.tradeItemList.attachChild(tradeItemEntity2);
            }
            this.tradeItemsEntities.get(i2).setVisible(true);
            this.tradeItemsEntities.get(i2).set(tradeItem.getEmpire1ID(), tradeItem, z, true);
            this.tradeItemsEntities.get(i2).setPosition(i, i2 * 80);
            i2++;
        }
        if (this.tradeItems.size() * 80 > 634) {
            float size = ((this.tradeItems.size() * 80) - 720) * (-1);
            if (this.tradeItemList.getY() < size) {
                this.tradeItemList.setY(size);
            }
        }
        this.tradeItemsScrollBar.update(this.tradeItemList.getY(), this.tradeItems.size());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void K(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof RacesScene) {
            this.B.racesScene.set();
        } else if (extendedScene instanceof RaceScene) {
            this.B.raceScene.set(this.contactID);
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        }
    }

    public void addItem(TradeItem tradeItem, int i) {
        if (tradeItem.doesRequireBoth()) {
            tradeItem.setEmpire1ID(this.B.getCurrentPlayer());
            tradeItem.setEmpire2ID(this.contactID);
        } else if (i == 0) {
            tradeItem.setEmpire1ID(this.B.getCurrentPlayer());
        } else {
            tradeItem.setEmpire1ID(this.contactID);
        }
        this.tradeItems.add(tradeItem);
        updateSelected();
        updateTradeItemList();
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        changeScene(this.B.raceScene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            checkActionUp(point);
        } else if (i != 2) {
        } else {
            checkActionMove(point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.bufferManager = vertexBufferObjectManager;
        if (getWidth() == 1480.0f) {
            this.extraX = 100;
        }
        Sprite E = E(0.0f, 0.0f, this.B.graphics.planetSurfaceTexture, vertexBufferObjectManager, true);
        this.surface = E;
        E.setSize(2480.0f, 720.0f);
        Sprite E2 = E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true);
        E2.setSize(getWidth(), 720.0f);
        E2.setAlpha(0.6f);
        Sprite E3 = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = E3;
        E3.setSize(400.0f, 75.0f);
        Scene scene = new Scene();
        this.empire1TradeItemList = scene;
        scene.setPosition(0.0f, 86.0f);
        this.empire1TradeItemList.setBackgroundEnabled(false);
        attachChild(this.empire1TradeItemList);
        Scene scene2 = new Scene();
        this.empire2TradeItemList = scene2;
        scene2.setPosition((this.extraX * 2) + 860, 86.0f);
        this.empire2TradeItemList.setBackgroundEnabled(false);
        attachChild(this.empire2TradeItemList);
        Scene scene3 = new Scene();
        this.tradeItemList = scene3;
        scene3.setPosition(this.extraX + TypedValues.Cycle.TYPE_EASING, 86.0f);
        this.tradeItemList.setBackgroundEnabled(false);
        attachChild(this.tradeItemList);
        ScrollBarControl scrollBarControl = new ScrollBarControl(405.0f, 86.0f, 80, 548.0f, this.B, vertexBufferObjectManager);
        this.scrollBarEmpire1 = scrollBarControl;
        attachChild(scrollBarControl);
        ScrollBarControl scrollBarControl2 = new ScrollBarControl((this.extraX * 2) + 1265, 86.0f, 80, 548.0f, this.B, vertexBufferObjectManager);
        this.scrollBarEmpire2 = scrollBarControl2;
        attachChild(scrollBarControl2);
        ScrollBarControl scrollBarControl3 = new ScrollBarControl(this.extraX + 850, 86.0f, 80, 634.0f, this.B, vertexBufferObjectManager);
        this.tradeItemsScrollBar = scrollBarControl3;
        attachChild(scrollBarControl3);
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        E(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        TiledSprite J = J(3.0f, 3.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireBannerBackground = J;
        J.setSize(80.0f, 80.0f);
        TiledSprite J2 = J(3.0f, 3.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.empireBanner = J2;
        J2.setSize(80.0f, 80.0f);
        Game game = this.B;
        Text G = G(100.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.race_discuss_header), this.E, vertexBufferObjectManager, true);
        G.setY(43.0f - (G.getHeightScaled() / 2.0f));
        TiledSprite J3 = J(0.0f, 86.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.tradeBackground1 = J3;
        J3.setSize(420.0f, 548.0f);
        this.tradeBackground1.setAlpha(0.2f);
        TiledSprite J4 = J((this.extraX * 2) + 860, 86.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.tradeBackground2 = J4;
        J4.setSize(420.0f, 548.0f);
        this.tradeBackground2.setAlpha(0.2f);
        E(0.0f, 634.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(420.0f, 86.0f);
        TiledSprite J5 = J(0.0f, 634.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireConfirmBackground = J5;
        J5.setSize(420.0f, 86.0f);
        this.empireConfirmBackground.setAlpha(0.4f);
        Sprite E4 = E(getWidth() - 420.0f, 634.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true);
        this.blackenedBackground3 = E4;
        E4.setSize(420.0f, 86.0f);
        TiledSprite J6 = J(getWidth() - 420.0f, 634.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireConfirmBackground2 = J6;
        J6.setSize(420.0f, 86.0f);
        this.empireConfirmBackground2.setAlpha(0.4f);
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.PRESSED;
        this.G = H(0.0f, 0.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        EmpireButton empireButton = new EmpireButton(this.B, vertexBufferObjectManager);
        this.empireButton = empireButton;
        attachChild(empireButton);
        this.empireButton.setPosition(getWidth() - 360.0f, 0.0f);
        s(this.empireButton);
        TiledSprite H = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.RACES.ordinal(), true);
        this.racesButton = H;
        s(H);
        TiledSprite H2 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H2;
        s(H2);
        ITiledTextureRegion iTiledTextureRegion2 = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum2 = ButtonsEnum.OK;
        TiledSprite H3 = H(60.0f, 634.0f, iTiledTextureRegion2, vertexBufferObjectManager, buttonsEnum2.ordinal(), true);
        this.empire1ConfirmButton = H3;
        s(H3);
        this.empire1Set = H(60.0f, 634.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        ITiledTextureRegion iTiledTextureRegion3 = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum3 = ButtonsEnum.CLOSE;
        TiledSprite H4 = H(240.0f, 634.0f, iTiledTextureRegion3, vertexBufferObjectManager, buttonsEnum3.ordinal(), true);
        this.empire1CancelButton = H4;
        s(H4);
        TiledSprite H5 = H(getWidth() - 360.0f, 634.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum2.ordinal(), true);
        this.empire2ConfirmButton = H5;
        s(H5);
        this.empire2Set = H(getWidth() - 360.0f, 634.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        TiledSprite H6 = H(getWidth() - 180.0f, 634.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum3.ordinal(), true);
        this.empire2CancelButton = H6;
        s(H6);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
        this.selectCreditAmountOverlay = new SelectCreditAmountOverlay(this.B, vertexBufferObjectManager, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(ExtendedScene extendedScene, Object obj) {
        extendedScene.getPoolElements();
        K(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    public void set(int i) {
        boolean z;
        this.contactID = i;
        this.tradeChanged = true;
        Game game = this.B;
        this.surface.setTiledTextureRegion(game.graphics.setSurfaceTexture("Surfaces/" + Empires.getHomeworldClimate(i) + ".png", game.getActivity()));
        this.surface.setX(-Functions.random.nextInt(2480 - ((int) getWidth())));
        this.empire1Set.setVisible(false);
        this.empire2Set.setVisible(false);
        this.empire1ConfirmButton.setAlpha(0.4f);
        this.empire2ConfirmButton.setAlpha(0.4f);
        this.scrollBarEmpire2.setHeight(548);
        this.tradeBackground2.setHeight(548.0f);
        if (this.B.empires.get(i).isAI()) {
            this.scrollBarEmpire2.setHeight(643);
            this.tradeBackground2.setHeight(643.0f);
            z = false;
        } else {
            z = true;
        }
        this.blackenedBackground3.setVisible(z);
        this.empireConfirmBackground2.setVisible(z);
        this.empire2ConfirmButton.setVisible(z);
        this.empire2CancelButton.setVisible(z);
        this.empireBannerBackground.setCurrentTileIndex(this.B.getCurrentPlayer());
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.B.getCurrentPlayer()));
        this.empireButton.set(i);
        this.tradeBackground1.setCurrentTileIndex(this.B.getCurrentPlayer());
        this.empireConfirmBackground.setCurrentTileIndex(this.B.getCurrentPlayer());
        this.tradeBackground2.setCurrentTileIndex(i);
        this.empireConfirmBackground2.setCurrentTileIndex(i);
        List<List<TradeItem>> list = this.empireTradeItems;
        Game game2 = this.B;
        list.set(0, game2.empires.get(game2.getCurrentPlayer()).getTradeItems(i));
        setTradeItems(this.B.getCurrentPlayer(), i, 0, this.empire1TradeItemList, false);
        this.scrollBarEmpire1.update(86.0f, this.empireTradeItems.get(0).size());
        this.empireTradeItems.set(1, this.B.empires.get(i).getTradeItems(this.B.getCurrentPlayer()));
        setTradeItems(i, this.B.getCurrentPlayer(), 1, this.empire2TradeItemList, true);
        this.scrollBarEmpire2.update(86.0f, this.empireTradeItems.get(1).size());
        this.tradeItemList.setY(86.0f);
        this.tradeItems.clear();
        updateTradeItemList();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
