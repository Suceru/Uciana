package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.EventListElement;
import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Events.EventDataFields;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.StarSystems.Nebulas;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EventsScene extends ExtendedScene {
    private TiledSprite dismissAllButton;
    private Sprite dismissPress;
    private int empireID;
    private Scene eventList;
    private Sprite eventPress;
    private List<Event> events;
    private int eventsListIndex;
    private TiledSprite galaxyButton;
    private float lastY;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private float pressedY;
    private ScrollBarControl scrollBar;
    private final EventListElement[] elements = new EventListElement[9];
    private boolean isScroll = false;
    private final int ITEM_SIZE = 90;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.EventsScene$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1459a;

        static {
            int[] iArr = new int[EventType.values().length];
            f1459a = iArr;
            try {
                iArr[EventType.EMPIRE_CONTACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1459a[EventType.EMPIRE_DESTROYED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1459a[EventType.SYSTEM_DISCOVERY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1459a[EventType.TERRAFORMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1459a[EventType.COLONY_DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1459a[EventType.CAPITAL_DESTROYED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1459a[EventType.OUTPOST_DESTROYED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1459a[EventType.COLONY_INVADED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1459a[EventType.BUILDING_SCRAP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1459a[EventType.TECH_BREAKTHROUGH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public EventsScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(int i, Point point) {
        if (point.getX() < getWidth() - 120.0f) {
            this.pressedY = point.getY();
            this.isScroll = false;
            this.lastY = point.getY();
        }
        if (this.isScroll || this.events.size() <= i) {
            return;
        }
        if (point.getX() < getWidth() - 245.0f) {
            this.eventPress.setY(this.eventList.getY() + (i * 90));
            this.eventPress.setVisible(true);
        } else if (point.getX() <= getWidth() - 245.0f || point.getX() >= getWidth() - 145.0f) {
        } else {
            this.dismissPress.setY(this.eventList.getY() + (i * 90));
            this.dismissPress.setVisible(true);
        }
    }

    private void checkActionMove(int i, Point point) {
        this.eventPress.setVisible(false);
        this.dismissPress.setVisible(false);
        if (point.getX() < 1160.0f && this.events.size() * 90 > 720) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y = this.eventList.getY() - (this.lastY - point.getY());
            if (y > 0.0f) {
                y = 0.0f;
            }
            float size = ((this.events.size() * 90) - 720) * (-1);
            if (y < size) {
                y = size;
            }
            this.eventList.setY(y);
            this.lastY = point.getY();
            this.scrollBar.update(y);
            int abs = Math.abs((int) (y / 90.0f));
            if (abs != this.eventsListIndex) {
                setEventList(abs);
            }
        }
        if (this.isScroll || this.events.size() <= i) {
            return;
        }
        if (point.getX() < getWidth() - 245.0f) {
            this.eventPress.setY(this.eventList.getY() + (i * 90));
            this.eventPress.setVisible(true);
        } else if (point.getX() <= getWidth() - 245.0f || point.getX() >= getWidth() - 145.0f) {
        } else {
            this.dismissPress.setY(this.eventList.getY() + (i * 90));
            this.dismissPress.setVisible(true);
        }
    }

    private void checkActionUp(int i, Point point) {
        this.eventPress.setVisible(false);
        this.dismissPress.setVisible(false);
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.dismissAllButton, point)) {
            dismissAllButtonPressed();
        } else if (this.events.size() <= i || this.isScroll) {
        } else {
            if (point.getX() < getWidth() - 245.0f) {
                eventPressed(i);
            } else if (point.getX() <= getWidth() - 245.0f || point.getX() >= getWidth() - 145.0f) {
            } else {
                dismissButtonPressed(i);
            }
        }
    }

    private void dismissAllButtonPressed() {
        this.B.events.clear();
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void dismissButtonPressed(int i) {
        this.B.events.removeEvent(this.events.get(i));
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        refresh();
    }

    private void eventPressed(int i) {
        switch (AnonymousClass1.f1459a[this.events.get(i).getEventType().ordinal()]) {
            case 1:
                int intValue = ((Integer) this.events.get(i).getEventData(EventDataFields.EMPIRE_ID)).intValue();
                if (this.B.empires.get(intValue).isAlive()) {
                    changeScene(this.B.raceScene, Integer.valueOf(intValue));
                    break;
                } else {
                    changeScene(this.B.racesScene);
                    break;
                }
            case 3:
                int intValue2 = ((Integer) this.events.get(i).getEventData(EventDataFields.SYSTEM_ID)).intValue();
                Game game = this.B;
                game.fleets.removeColonyShipArrived(game.getCurrentPlayer(), intValue2);
                changeScene(this.B.systemScene, Integer.valueOf(intValue2));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                changeScene(this.B.planetScene, new Point(((Integer) this.events.get(i).getEventData(EventDataFields.SYSTEM_ID)).intValue(), ((Integer) this.events.get(i).getEventData(EventDataFields.ORBIT)).intValue()));
                this.B.sounds.playBoxPressSound();
                Game game2 = this.B;
                game2.vibrate(game2.BUTTON_VIBRATE);
                return;
        }
        this.B.events.removeEvent(this.events.get(i));
        refresh();
        this.B.sounds.playBoxPressSound();
        Game game3 = this.B;
        game3.vibrate(game3.BUTTON_VIBRATE);
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (EventListElement eventListElement : this.elements) {
            eventListElement.releasePoolElements();
        }
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void setEventList(int i) {
        this.eventsListIndex = i;
        int i2 = 0;
        while (true) {
            EventListElement[] eventListElementArr = this.elements;
            if (i2 >= eventListElementArr.length) {
                return;
            }
            eventListElementArr[i2].setVisible(false);
            int i3 = i + i2;
            if (i3 < this.events.size()) {
                this.elements[i2].setVisible(true);
                this.elements[i2].setY(i3 * 90);
                Event event = this.events.get(i3);
                switch (AnonymousClass1.f1459a[event.getEventType().ordinal()]) {
                    case 1:
                    case 2:
                        this.elements[i2].setEmpireEvent(event);
                        continue;
                    case 3:
                        this.elements[i2].setSystemExploredEvent(event);
                        continue;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        this.elements[i2].setPlanetEvent(event);
                        continue;
                    case 10:
                        this.elements[i2].setTechBreakthrough(event);
                        continue;
                }
            }
            i2++;
        }
    }

    private void setEventListPosition() {
        float size = ((this.events.size() * 90) - 720) * (-1);
        if (this.eventList.getY() < size) {
            this.eventList.setY(size);
        }
        if (this.events.size() * 90 <= 720.0f) {
            this.eventList.setY(0.0f);
        }
    }

    private void setSpritesInvisible() {
        this.eventPress.setVisible(false);
        this.dismissPress.setVisible(false);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.L(((Integer) obj).intValue());
        } else if (extendedScene instanceof PlanetScene) {
            Point point = (Point) obj;
            Game game = this.B;
            game.planetScene.loadPlanet((int) point.getX(), (int) point.getY(), game.eventsScene);
        } else if (extendedScene instanceof RaceScene) {
            this.B.raceScene.set(((Integer) obj).intValue());
        } else if (extendedScene instanceof RacesScene) {
            this.B.racesScene.set();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        changeScene(this.B.galaxyScene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        int y = ((int) (point.getY() - this.eventList.getY())) / 90;
        super.checkInput(i, point);
        if (i == 0) {
            checkActionDown(y, point);
        } else if (i == 1) {
            checkActionUp(y, point);
        } else if (i != 2) {
        } else {
            checkActionMove(y, point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.nebulas = this.B.nebulas;
        this.nebulaBackground = new Entity();
        if (getWidth() == 1480.0f) {
            this.nebulaBackground.setX(100.0f);
        }
        attachChild(this.nebulaBackground);
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H;
        s(H);
        TiledSprite H2 = H(getWidth() - 120.0f, 634.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.BLANK.ordinal(), true);
        this.dismissAllButton = H2;
        s(H2);
        TiledSprite H3 = H(getWidth() - 100.0f, 657.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.MOVE.ordinal(), true);
        H3.setScaleCenter(0.0f, 0.0f);
        H3.setScale(0.4f);
        TiledSprite H4 = H(getWidth() - 65.0f, 652.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.CLOSE.ordinal(), true);
        H4.setScaleCenter(0.0f, 0.0f);
        H4.setScale(0.5f);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.eventPress = E;
        E.setSize(getWidth() - 245.0f, 86.0f);
        Sprite E2 = E(getWidth() - 245.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.dismissPress = E2;
        E2.setSize(100.0f, 86.0f);
        Scene scene = new Scene();
        this.eventList = scene;
        scene.setPosition(0.0f, 0.0f);
        int i = 0;
        this.eventList.setBackgroundEnabled(false);
        attachChild(this.eventList);
        while (true) {
            EventListElement[] eventListElementArr = this.elements;
            if (i < eventListElementArr.length) {
                eventListElementArr[i] = new EventListElement(this.B, vertexBufferObjectManager, getWidth());
                this.eventList.attachChild(this.elements[i]);
                i++;
            } else {
                ScrollBarControl scrollBarControl = new ScrollBarControl(getWidth() - 135.0f, 0.0f, 90, 720.0f, this.B, vertexBufferObjectManager);
                this.scrollBar = scrollBarControl;
                attachChild(scrollBarControl);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        for (EventListElement eventListElement : this.elements) {
            eventListElement.getPoolElements();
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        set(this.empireID);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.j
            @Override // java.lang.Runnable
            public final void run() {
                EventsScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void set(int i) {
        this.empireID = i;
        this.B.getActivity().setLocale();
        setSpritesInvisible();
        this.nebulas.set();
        this.events = this.B.events.getEventsForEventsScene(i);
        setEventList(0);
        this.scrollBar.update(0.0f, this.events.size());
        this.eventList.setY(0.0f);
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
