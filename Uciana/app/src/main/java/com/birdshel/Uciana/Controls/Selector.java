package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Difficulty;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.StarSystems.GalaxySize;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Selector extends Entity {
    private final TiledSprite blueSelectedElement;
    private final Map<Object, Text> elementLabels = new LinkedHashMap();
    private final Map<Object, Entity> elements = new LinkedHashMap();
    private final Game game;
    private final TiledSprite greenSelectedElement;
    private final Text header;
    private final OptionID optionID;
    private final Sprite pressSprite;
    private final Text selectText;
    private Object selected;
    private final String source;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Controls.Selector$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1359a;

        static {
            int[] iArr = new int[OptionID.values().length];
            f1359a = iArr;
            try {
                iArr[OptionID.GALAXY_SIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1359a[OptionID.NUMBER_OF_PLAYERS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1359a[OptionID.DIFFICULTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1359a[OptionID.BLACKHOLES_LEVEL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1359a[OptionID.SPACE_RIFTS_LEVEL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1359a[OptionID.WORMHOLES_LEVEL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1359a[OptionID.RANDOM_EVENTS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1359a[OptionID.DIPLOMATIC_OPTIONS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1359a[OptionID.ATTACK_OPTIONS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1359a[OptionID.STARTING_POSITIONS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1359a[OptionID.TECH_PROGRESSION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1359a[OptionID.SYSTEM_OBJECT_PERCENT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1359a[OptionID.AI_APPEARANCE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public Selector(Game game, String str, OptionID optionID, VertexBufferObjectManager vertexBufferObjectManager, String str2, String str3) {
        this.game = game;
        this.source = str;
        this.optionID = optionID;
        Text text = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, str2, new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        this.header = text;
        text.setX(50.0f - (text.getWidthScaled() / 2.0f));
        text.setY((-5.0f) - text.getHeightScaled());
        attachChild(text);
        Text text2 = new Text(0.0f, 0.0f, game.fonts.infoFont, str3, new TextOptions(AutoWrap.WORDS, 1200.0f, HorizontalAlign.LEFT), vertexBufferObjectManager);
        this.selectText = text2;
        text2.setVisible(false);
        attachChild(text2);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.pressSprite = sprite;
        sprite.setSize(100.0f, 100.0f);
        sprite.setVisible(false);
        attachChild(sprite);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 120.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.greenSelectedElement = tiledSprite;
        tiledSprite.setCurrentTileIndex(1);
        tiledSprite.setSize(100.0f, 100.0f);
        tiledSprite.setAlpha(0.7f);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.blueSelectedElement = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(2);
        tiledSprite2.setSize(100.0f, 100.0f);
        tiledSprite2.setAlpha(0.7f);
        tiledSprite.attachChild(tiledSprite2);
        attachChild(tiledSprite);
    }

    private void checkActionDown(Point point) {
        checkPressed(point);
    }

    private void checkActionMove(Point point) {
        this.pressSprite.setVisible(false);
        checkPressed(point);
    }

    private void checkActionUp(Point point) {
        this.pressSprite.setVisible(false);
        for (Map.Entry<Object, Entity> entry : this.elements.entrySet()) {
            if (point.getX() > entry.getValue().getX() && point.getX() < entry.getValue().getX() + 100.0f && point.getY() > entry.getValue().getY() && point.getY() < entry.getValue().getY() + 100.0f) {
                setSelected(entry.getKey());
                if (this.source.equals("SETUP")) {
                    this.game.setupScene.closeSelector();
                }
                saveOption(entry.getKey());
                this.game.sounds.playBoxPressSound();
                Game game = this.game;
                game.vibrate(game.BUTTON_VIBRATE);
                return;
            }
        }
    }

    private void checkPressed(Point point) {
        for (Map.Entry<Object, Entity> entry : this.elements.entrySet()) {
            if (point.getX() > entry.getValue().getX() && point.getX() < entry.getValue().getX() + 100.0f && point.getY() > entry.getValue().getY() && point.getY() < entry.getValue().getY() + 100.0f) {
                this.pressSprite.setVisible(true);
                this.pressSprite.setPosition(entry.getValue().getX(), entry.getValue().getY());
            }
        }
    }

    private void saveOption(Object obj) {
        switch (AnonymousClass1.f1359a[this.optionID.ordinal()]) {
            case 1:
                Game.options.setOption(OptionID.GALAXY_SIZE, ((GalaxySize) obj).ordinal());
                return;
            case 2:
                Game.options.setOption(OptionID.NUMBER_OF_PLAYERS, ((Integer) obj).intValue());
                return;
            case 3:
                Game.options.setOption(OptionID.DIFFICULTY, ((Difficulty) obj).ordinal());
                return;
            case 4:
                Game.options.setOption(OptionID.BLACKHOLES_LEVEL, ((Integer) obj).intValue());
                return;
            case 5:
                Game.options.setOption(OptionID.SPACE_RIFTS_LEVEL, ((Integer) obj).intValue());
                return;
            case 6:
                Game.options.setOption(OptionID.WORMHOLES_LEVEL, ((Integer) obj).intValue());
                return;
            case 7:
                if (((Boolean) obj).booleanValue()) {
                    Game.options.turnOn(OptionID.RANDOM_EVENTS);
                    return;
                } else {
                    Game.options.turnOff(OptionID.RANDOM_EVENTS);
                    return;
                }
            case 8:
                Game.options.setOption(OptionID.DIPLOMATIC_OPTIONS, ((Integer) obj).intValue());
                return;
            case 9:
                Game.options.setOption(OptionID.ATTACK_OPTIONS, ((Integer) obj).intValue());
                return;
            case 10:
                Game.options.setOption(OptionID.STARTING_POSITIONS, ((Integer) obj).intValue());
                return;
            case 11:
                Game.options.setOption(OptionID.TECH_PROGRESSION, ((Integer) obj).intValue());
                return;
            case 12:
                Game.options.setOption(OptionID.SYSTEM_OBJECT_PERCENT, ((Integer) obj).intValue());
                return;
            case 13:
                Game.options.setOption(OptionID.AI_APPEARANCE, ((Integer) obj).intValue());
                return;
            default:
                throw new AssertionError("Selector - Invalid Option");
        }
    }

    public void addElement(Object obj, Entity entity, Text text) {
        attachChild(entity);
        this.elements.put(obj, entity);
        entity.attachChild(text);
        this.elementLabels.put(obj, text);
        text.setVisible(false);
    }

    public void checkInput(int i, Point point) {
        Point point2 = new Point(point.getX() - getX(), point.getY() - getY());
        if (i == 0) {
            checkActionDown(point2);
        } else if (i == 1) {
            checkActionUp(point2);
        } else if (i != 2) {
        } else {
            checkActionMove(point2);
        }
    }

    public Object getSelected() {
        return this.selected;
    }

    public boolean isClicked(Point point) {
        return point.getX() > getX() && point.getX() < getX() + 100.0f && point.getY() > getY() && point.getY() < getY() + 100.0f;
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public boolean isVisible() {
        return this.selectText.isVisible();
    }

    public void popBack() {
        setZIndex(99);
        this.header.setVisible(true);
        this.selectText.setVisible(false);
        setSelected(this.selected);
        for (Map.Entry<Object, Text> entry : this.elementLabels.entrySet()) {
            entry.getValue().setVisible(false);
        }
    }

    public void popOut() {
        this.header.setVisible(false);
        this.selectText.setVisible(true);
        this.selectText.setPosition((getX() * (-1.0f)) + 50.0f, (((getY() * (-1.0f)) + 310.0f) - this.selectText.getHeightScaled()) - 50.0f);
        setZIndex(101);
        int i = 0;
        for (Map.Entry<Object, Entity> entry : this.elements.entrySet()) {
            entry.getValue().setScale(1.0f);
            float f2 = i * WeaponStats.NOVA_BOMB_MAX_DAMAGE;
            entry.getValue().setPosition((getX() * (-1.0f)) + f2 + 50.0f, (getY() * (-1.0f)) + 310.0f);
            if (entry.getKey() == this.selected) {
                this.greenSelectedElement.setPosition((getX() * (-1.0f)) + f2 + 50.0f, (getY() * (-1.0f)) + 310.0f);
                this.greenSelectedElement.setScale(1.0f);
                this.blueSelectedElement.setVisible(false);
            }
            i++;
        }
        for (Map.Entry<Object, Text> entry2 : this.elementLabels.entrySet()) {
            entry2.getValue().setVisible(true);
        }
    }

    public void setSelected(Object obj) {
        this.selected = obj;
        int size = 50 - ((this.elements.size() * 25) / 2);
        boolean z = false;
        int i = 0;
        for (Map.Entry<Object, Entity> entry : this.elements.entrySet()) {
            if (entry.getKey() == this.selected) {
                entry.getValue().setScale(1.0f);
                entry.getValue().setPosition(0.0f, 0.0f);
                this.greenSelectedElement.setPosition((i * 25) + size, 120.0f);
                this.greenSelectedElement.setScale(0.2f);
                this.blueSelectedElement.setVisible(true);
                z = true;
            } else {
                entry.getValue().setScale(0.2f);
                entry.getValue().setPosition((i * 25) + size, 120.0f);
            }
            i++;
        }
        if (z) {
            return;
        }
        setSelected(this.elements.entrySet().iterator().next().getKey());
    }
}
