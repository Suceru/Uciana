package com.birdshel.Uciana.Overlays;

import android.util.SparseArray;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.KeyboardButton;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.SupportedLocales;
import com.birdshel.Uciana.Scenes.SystemScene;
import com.birdshel.Uciana.StarSystems.StarSystem;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.Iterator;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class KeyboardOverlay extends ExtendedChildScene {
    private final TiledSprite backspaceButton;
    private final TiledSprite closeButton;
    private final Sprite cursor;
    private final Sprite cursor2;
    private final TiledSprite enterButton;
    private final Text enteredInText;
    private int extra_string_length;
    private final Entity keyboard;
    private final Text keyboardMessage;
    private String keyboardTask;
    private final Text keyboardWarning;
    private final SparseArray<KeyboardButton> letterButtons;
    private int max_string_length;
    private final TiledSprite resetButton;
    private final TiledSprite shiftButton;
    private final Text shiftKeyText;
    private final TiledSprite shiftSelected;
    private final TiledSprite spaceButton;

    public KeyboardOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.letterButtons = new SparseArray<>();
        this.max_string_length = 10;
        this.extra_string_length = 0;
        if (game.getLocale() == SupportedLocales.JAPANESE) {
            this.max_string_length = 6;
        } else if (game.getLocale() == SupportedLocales.KOREAN) {
            this.max_string_length = 8;
        }
        Entity entity = new Entity();
        this.keyboard = entity;
        if (getWidth() == 1480.0f) {
            entity.setX(100.0f);
        } else {
            entity.setX(0.0f);
        }
        attachChild(entity);
        Sprite sprite = new Sprite(470.0f, 75.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        sprite.setSize(340.0f, 75.0f);
        entity.attachChild(sprite);
        Sprite sprite2 = new Sprite(470.0f, 75.0f, game.graphics.colonySeparatorTexture, vertexBufferObjectManager);
        sprite2.setSize(340.0f, 75.0f);
        entity.attachChild(sprite2);
        Sprite sprite3 = new Sprite(638.0f, 90.0f, game.graphics.particleTexture, vertexBufferObjectManager);
        this.cursor = sprite3;
        sprite3.setSize(3.0f, 45.0f);
        blinkSprite(sprite3);
        entity.attachChild(sprite3);
        Sprite sprite4 = new Sprite(638.0f, 90.0f, game.graphics.particleTexture, vertexBufferObjectManager);
        this.cursor2 = sprite4;
        sprite4.setSize(3.0f, 45.0f);
        blinkSprite(sprite4);
        entity.attachChild(sprite4);
        Text text = new Text(0.0f, 5.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.keyboardMessage = text;
        entity.attachChild(text);
        Text text2 = new Text(0.0f, 205.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.keyboardWarning = text2;
        text2.setColor(Color.RED);
        entity.attachChild(text2);
        Text text3 = new Text(0.0f, 105.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.enteredInText = text3;
        text3.setText("");
        entity.attachChild(text3);
        setupKeyBoardRow(new int[][]{new int[]{R.string.key_r1k1, R.string.key_r1k1u}, new int[]{R.string.key_r1k2, R.string.key_r1k2u}, new int[]{R.string.key_r1k3, R.string.key_r1k3u}, new int[]{R.string.key_r1k4, R.string.key_r1k4u}, new int[]{R.string.key_r1k5, R.string.key_r1k5u}, new int[]{R.string.key_r1k6, R.string.key_r1k6u}, new int[]{R.string.key_r1k7, R.string.key_r1k7u}, new int[]{R.string.key_r1k8, R.string.key_r1k8u}, new int[]{R.string.key_r1k9, R.string.key_r1k9u}, new int[]{R.string.key_r1k10, R.string.key_r1k10u}, new int[]{R.string.key_r1k11, R.string.key_r1k11u}, new int[]{R.string.key_r1k12, R.string.key_r1k12u}, new int[]{R.string.key_r1k13, R.string.key_r1k13u}}, 365.0f, vertexBufferObjectManager);
        setupKeyBoardRow(new int[][]{new int[]{R.string.key_r2k1, R.string.key_r2k1u}, new int[]{R.string.key_r2k2, R.string.key_r2k2u}, new int[]{R.string.key_r2k3, R.string.key_r2k3u}, new int[]{R.string.key_r2k4, R.string.key_r2k4u}, new int[]{R.string.key_r2k5, R.string.key_r2k5u}, new int[]{R.string.key_r2k6, R.string.key_r2k6u}, new int[]{R.string.key_r2k7, R.string.key_r2k7u}, new int[]{R.string.key_r2k8, R.string.key_r2k8u}, new int[]{R.string.key_r2k9, R.string.key_r2k9u}, new int[]{R.string.key_r2k10, R.string.key_r2k10u}, new int[]{R.string.key_r2k11, R.string.key_r2k11u}, new int[]{R.string.key_r2k12, R.string.key_r2k12u}, new int[]{R.string.key_r2k13, R.string.key_r2k13u}}, 436.0f, vertexBufferObjectManager);
        setupKeyBoardRow(new int[][]{new int[]{R.string.key_r3k1, R.string.key_r3k1u}, new int[]{R.string.key_r3k2, R.string.key_r3k2u}, new int[]{R.string.key_r3k3, R.string.key_r3k3u}, new int[]{R.string.key_r3k4, R.string.key_r3k4u}, new int[]{R.string.key_r3k5, R.string.key_r3k5u}, new int[]{R.string.key_r3k6, R.string.key_r3k6u}, new int[]{R.string.key_r3k7, R.string.key_r3k7u}, new int[]{R.string.key_r3k8, R.string.key_r3k8u}, new int[]{R.string.key_r3k9, R.string.key_r3k9u}, new int[]{R.string.key_r3k10, R.string.key_r3k10u}, new int[]{R.string.key_r3k11, R.string.key_r3k11u}, new int[]{R.string.key_r3k12, R.string.key_r3k12u}, new int[]{R.string.key_r3k13, R.string.key_r3k13u}}, 507.0f, vertexBufferObjectManager);
        setupKeyBoardRow(new int[][]{new int[]{R.string.key_r4k1, R.string.key_r4k1u}, new int[]{R.string.key_r4k2, R.string.key_r4k2u}, new int[]{R.string.key_r4k3, R.string.key_r4k3u}, new int[]{R.string.key_r4k4, R.string.key_r4k4u}, new int[]{R.string.key_r4k5, R.string.key_r4k5u}, new int[]{R.string.key_r4k6, R.string.key_r4k6u}, new int[]{R.string.key_r4k7, R.string.key_r4k7u}, new int[]{R.string.key_r4k8, R.string.key_r4k8u}, new int[]{R.string.key_r4k9, R.string.key_r4k9u}, new int[]{R.string.key_r4k10, R.string.key_r4k10u}, new int[]{R.string.key_r4k11, R.string.key_r4k11u}, new int[]{R.string.key_r4k12, R.string.key_r4k12u}, new int[]{R.string.key_r4k13, R.string.key_r4k13u}}, 578.0f, vertexBufferObjectManager);
        setupKeyBoardRow(new int[][]{new int[]{R.string.key_r5k1, R.string.key_r5k1u}, new int[]{R.string.key_r5k2, R.string.key_r5k2u}, new int[]{R.string.key_r5k3, R.string.key_r5k3u}, new int[]{R.string.key_r5k4, R.string.key_r5k4u}, new int[]{R.string.key_r5k5, R.string.key_r5k5u}, new int[]{R.string.key_r5k6, R.string.key_r5k6u}, new int[]{R.string.key_r5k7, R.string.key_r5k7u}, new int[]{R.string.key_r5k8, R.string.key_r5k8u}, new int[]{R.string.key_r5k9, R.string.key_r5k9u}, new int[]{R.string.key_r5k10, R.string.key_r5k10u}, new int[]{R.string.key_r5k11, R.string.key_r5k11u}, new int[]{R.string.key_r5k12, R.string.key_r5k12u}, new int[]{R.string.key_r5k13, R.string.key_r5k13u}}, 649.0f, vertexBufferObjectManager);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.closeButton = tiledSprite;
        tiledSprite.setCurrentTileIndex(ButtonsEnum.CLOSE.ordinal());
        entity.attachChild(tiledSprite);
        n(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(40.0f, 235.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.shiftSelected = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(ButtonsEnum.PRESSED.ordinal());
        tiledSprite2.setAlpha(0.7f);
        entity.attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(40.0f, 235.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.shiftButton = tiledSprite3;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK_BLUE;
        tiledSprite3.setCurrentTileIndex(buttonsEnum.ordinal());
        entity.attachChild(tiledSprite3);
        n(tiledSprite3);
        Text text4 = new Text(45.0f, 267.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager);
        this.shiftKeyText = text4;
        entity.attachChild(text4);
        TiledSprite tiledSprite4 = new TiledSprite(160.0f, 235.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.spaceButton = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(buttonsEnum.ordinal());
        entity.attachChild(tiledSprite4);
        n(tiledSprite4);
        Text text5 = new Text(45.0f, 267.0f, game.fonts.smallFont, game.getActivity().getString(R.string.key_space), this.F, vertexBufferObjectManager);
        entity.attachChild(text5);
        text5.setX((tiledSprite4.getX() + (tiledSprite4.getWidthScaled() / 2.0f)) - (text5.getWidthScaled() / 2.0f));
        TiledSprite tiledSprite5 = new TiledSprite(1000.0f, 245.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.backspaceButton = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(ButtonsEnum.PREVIOUS.ordinal());
        entity.attachChild(tiledSprite5);
        n(tiledSprite5);
        TiledSprite tiledSprite6 = new TiledSprite(1120.0f, 245.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.enterButton = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(ButtonsEnum.TURN.ordinal());
        entity.attachChild(tiledSprite6);
        n(tiledSprite6);
        TiledSprite tiledSprite7 = new TiledSprite(350.0f, 68.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.resetButton = tiledSprite7;
        tiledSprite7.setCurrentTileIndex(ButtonsEnum.BLANK.ordinal());
        entity.attachChild(tiledSprite7);
        n(tiledSprite7);
        Text text6 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.keyboard_reset), this.F, vertexBufferObjectManager);
        text6.setX(60.0f - (text6.getWidthScaled() / 2.0f));
        text6.setY(43.0f - (text6.getHeightScaled() / 2.0f));
        tiledSprite7.attachChild(text6);
    }

    private void alphanumericKeyPressed(KeyboardButton keyboardButton) {
        if (this.enteredInText.getText().length() < this.max_string_length + this.extra_string_length) {
            this.keyboardWarning.setText("");
            Text text = this.enteredInText;
            text.setText(((Object) this.enteredInText.getText()) + keyboardButton.getChar());
            Text text2 = this.enteredInText;
            text2.setX(640.0f - (text2.getWidth() / 2.0f));
            updateCursorPosition();
        }
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        if (this.C.getLocale() == SupportedLocales.JAPANESE || this.C.getLocale() == SupportedLocales.KOREAN || !this.shiftSelected.isVisible()) {
            return;
        }
        shiftButtonPressed(false);
    }

    private void backspaceButtonPressed() {
        int length = this.enteredInText.getText().length();
        if (length != 0) {
            if (this.C.getLocale() != SupportedLocales.JAPANESE && this.C.getLocale() != SupportedLocales.KOREAN && length == 1 && !this.shiftSelected.isVisible()) {
                shiftButtonPressed(true);
            }
            this.keyboardWarning.setText("");
            this.enteredInText.setText(this.enteredInText.getText().subSequence(0, length - 1));
            Text text = this.enteredInText;
            text.setX(640.0f - (text.getWidth() / 2.0f));
            this.cursor.setX(this.enteredInText.getX() + this.enteredInText.getWidthScaled() + 5.0f);
            this.cursor2.setX(this.enteredInText.getX() + this.enteredInText.getWidthScaled() + 5.0f);
        }
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void checkActionUp(Point point) {
        if (q(this.shiftButton, point)) {
            shiftButtonPressed(!this.shiftSelected.isVisible());
            this.C.sounds.playButtonPressSound();
            Game game = this.C;
            game.vibrate(game.BUTTON_VIBRATE);
        } else if (q(this.spaceButton, point)) {
            spaceButtonPressed();
        } else if (q(this.closeButton, point)) {
            closeButtonPressed();
        } else if (q(this.backspaceButton, point)) {
            backspaceButtonPressed();
        } else if (q(this.enterButton, point)) {
            enterButtonPressed();
        } else if (q(this.resetButton, point)) {
            resetButtonPressed();
        } else {
            int size = this.letterButtons.size();
            for (int i = 0; i < size; i++) {
                KeyboardButton valueAt = this.letterButtons.valueAt(i);
                if (q(valueAt, point)) {
                    alphanumericKeyPressed(valueAt);
                }
            }
        }
    }

    private void checkRenameColony() {
        if (this.enteredInText.getText().length() < 4) {
            setKeyboardWarning(this.C.getActivity().getString(R.string.keyboard_colony_length_warning));
            return;
        }
        boolean z = false;
        String charSequence = this.enteredInText.getText().toString();
        Iterator<Colony> it = this.C.colonies.getColonies().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (charSequence.equals(it.next().getName())) {
                z = true;
                setKeyboardWarning(this.C.getActivity().getString(R.string.keyboard_system_name_already_in_use));
                break;
            }
        }
        if (z) {
            return;
        }
        this.C.planetScene.setColonyName(charSequence);
        this.C.planetScene.refresh();
        back();
    }

    private void checkRenameShipDesign() {
        if (this.enteredInText.getText().length() < 3) {
            setKeyboardWarning(this.C.getActivity().getString(R.string.keyboard_ship_length_warning));
            return;
        }
        this.C.shipDesignScene.setDesignName(this.enteredInText.getText().toString());
        back();
    }

    private void checkRenameSystem() {
        if (this.enteredInText.getText().length() < 4) {
            setKeyboardWarning(this.C.getActivity().getString(R.string.keyboard_system_length_warning));
            return;
        }
        boolean z = false;
        String charSequence = this.enteredInText.getText().toString();
        Iterator<StarSystem> it = this.C.galaxy.getStarSystems().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().getName().equalsIgnoreCase(charSequence)) {
                setKeyboardWarning(this.C.getActivity().getString(R.string.keyboard_system_name_already_in_use));
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        this.C.systemScene.starSystem.setName(charSequence);
        SystemScene systemScene = this.C.systemScene;
        systemScene.loadSystem(systemScene.starSystem.getID());
        back();
    }

    private void closeButtonPressed() {
        back();
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void enterButtonPressed() {
        Text text = this.enteredInText;
        text.setText(text.getText().toString().trim());
        this.cursor.setX(this.enteredInText.getX() + this.enteredInText.getWidthScaled() + 5.0f);
        this.cursor2.setX(this.enteredInText.getX() + this.enteredInText.getWidthScaled() + 5.0f);
        String str = this.keyboardTask;
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -483657256:
                if (str.equals("renameColony")) {
                    c2 = 0;
                    break;
                }
                break;
            case -16142579:
                if (str.equals("renameSystem")) {
                    c2 = 1;
                    break;
                }
                break;
            case 30836984:
                if (str.equals("renameShipDesign")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                checkRenameColony();
                break;
            case 1:
                checkRenameSystem();
                break;
            case 2:
                checkRenameShipDesign();
                break;
        }
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void resetButtonPressed() {
        if ("renameColony".equals(this.keyboardTask)) {
            this.enteredInText.setText(this.C.planetScene.getSystemObject().getName());
            Text text = this.enteredInText;
            text.setX(640.0f - (text.getWidth() / 2.0f));
            updateCursorPosition();
        }
    }

    private void setKeyboardWarning(String str) {
        this.keyboardWarning.setText(str);
        Text text = this.keyboardWarning;
        text.setX(640.0f - (text.getWidth() / 2.0f));
    }

    private void setKeys() {
        if (this.shiftSelected.isVisible()) {
            this.shiftKeyText.setText(this.C.getActivity().getString(R.string.key_shift_to_lower));
        } else {
            this.shiftKeyText.setText(this.C.getActivity().getString(R.string.key_shift_to_upper));
        }
        this.shiftKeyText.setX((this.shiftButton.getX() + (this.shiftButton.getWidthScaled() / 2.0f)) - (this.shiftKeyText.getWidthScaled() / 2.0f));
        this.shiftKeyText.setY((this.shiftButton.getY() + (this.shiftButton.getHeightScaled() / 2.0f)) - (this.shiftKeyText.getHeightScaled() / 2.0f));
        int size = this.letterButtons.size();
        for (int i = 0; i < size; i++) {
            this.letterButtons.valueAt(i).setKey(this.shiftSelected.isVisible());
        }
    }

    private void setupKeyBoardRow(int[][] iArr, float f2, VertexBufferObjectManager vertexBufferObjectManager) {
        int i = 0;
        for (int[] iArr2 : iArr) {
            Game game = this.C;
            KeyboardButton keyboardButton = new KeyboardButton(iArr2[0], iArr2[1], i * 99, f2, game.graphics.buttonsTexture, vertexBufferObjectManager, game);
            keyboardButton.setScaleCenter(0.0f, 0.0f);
            keyboardButton.setScale(0.82f);
            keyboardButton.setCurrentTileIndex(ButtonsEnum.BLANK.ordinal());
            this.keyboard.attachChild(keyboardButton);
            n(keyboardButton);
            this.letterButtons.put(iArr2[0], keyboardButton);
            i++;
        }
    }

    private void shiftButtonPressed(boolean z) {
        this.shiftSelected.setVisible(z);
        setKeys();
    }

    private boolean shouldShowResetButton(String str) {
        return str.equals("renameColony");
    }

    private void spaceButtonPressed() {
        CharSequence text = this.enteredInText.getText();
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        if (text.length() == 0 || this.enteredInText.getText().length() >= this.max_string_length + this.extra_string_length || text.charAt(text.length() - 1) == ' ') {
            return;
        }
        Text text2 = this.enteredInText;
        text2.setText(((Object) this.enteredInText.getText()) + " ");
        Text text3 = this.enteredInText;
        text3.setX(640.0f - (text3.getWidth() / 2.0f));
        updateCursorPosition();
    }

    private void updateCursorPosition() {
        this.cursor.setX(this.enteredInText.getX() + this.enteredInText.getWidthScaled() + 5.0f);
        this.cursor2.setX(this.enteredInText.getX() + this.enteredInText.getWidthScaled() + 5.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        Point point2 = new Point(point.getX() - this.keyboard.getX(), point.getY());
        if (i != 0) {
            if (i == 1) {
                this.I.setVisible(false);
                checkActionUp(point2);
                return;
            } else if (i != 2) {
                return;
            } else {
                this.I.setVisible(false);
            }
        }
        Iterator<TiledSprite> it = this.H.iterator();
        while (it.hasNext()) {
            TiledSprite next = it.next();
            if (q(next, point2)) {
                this.I.setPosition(next.getX() + this.keyboard.getX(), next.getY());
                this.I.setScale(next.getScaleX(), next.getScaleY());
                this.I.setVisible(true);
                return;
            }
        }
    }

    public void setOverlay(String str, String str2) {
        this.keyboardTask = str;
        boolean z = false;
        this.extra_string_length = 0;
        if (this.C.getLocale() != SupportedLocales.JAPANESE && this.C.getLocale() != SupportedLocales.KOREAN) {
            z = true;
        }
        shiftButtonPressed(z);
        this.enteredInText.setText("");
        this.keyboardWarning.setText("");
        this.keyboardMessage.setText(str2);
        Text text = this.keyboardMessage;
        text.setX(640.0f - (text.getWidth() / 2.0f));
        this.cursor.setX(638.0f);
        this.cursor2.setX(638.0f);
        setKeys();
        this.resetButton.setVisible(shouldShowResetButton(str));
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }

    public void setOverlay(String str, String str2, String str3) {
        setOverlay(str, str2);
        this.extra_string_length = 4;
        this.enteredInText.setText(str3);
        Text text = this.enteredInText;
        text.setX(640.0f - (text.getWidth() / 2.0f));
        this.cursor.setX(this.enteredInText.getX() + this.enteredInText.getWidthScaled() + 5.0f);
        this.cursor2.setX(this.enteredInText.getX() + this.enteredInText.getWidthScaled() + 5.0f);
    }
}
