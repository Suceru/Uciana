package com.birdshel.Uciana.Scenes;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.birdshel.Uciana.Controls.VolumeControl;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.ChangeModdingSettingMessage;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Resources.SupportedLocales;
import com.birdshel.Uciana.Resources.TutorialID;
import com.birdshel.Uciana.Resources.XmlFile;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.Uciana;
import com.birdshel.Uciana.Utility.Log;
import com.google.android.gms.drive.DriveFile;
import java.io.File;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.level.constants.LevelConstants;
import org.w3c.dom.Element;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class OptionsScene extends ExtendedScene {
    private static final int SCROLL_HEIGHT = 950;
    private static final int VISIBLE_SCROLL_HEIGHT = 694;
    private TiledSprite autosave10;
    private TiledSprite autosave5;
    private TiledSprite autosaveBackground;
    private TiledSprite autosaveEveryTurn;
    private TiledSprite autosaveGameExit;
    private TiledSprite autosaveNever;
    private TiledSprite blueLinesCheckbox;
    private TiledSprite clearEventsBackground;
    private TiledSprite clearEventsCheckbox;
    private TiledSprite fleetLinesBackground;
    private TiledSprite gettingStartedVideo;
    private TiledSprite gettingStartedVideoBackground;
    private Text gettingStartedVideoText;
    private TiledSprite googlePlay;
    private TiledSprite googlePlayBackground;
    private Text googlePlayText;
    private TiledSprite greenLinesCheckbox;
    private boolean isScroll = false;
    private float lastY;
    private TiledSprite menuButton;
    private TiledSprite moddingBackground;
    private TiledSprite moddingCheckbox;
    private TiledSprite moddingInfoBackground;
    private Text moddingInfoPDFText;
    private Text moddingInfoText;
    private VolumeControl musicVolumeControl;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private Scene optionsList;
    private Sprite pressSprite;
    private float pressedY;
    private TiledSprite redLinesCheckbox;
    private TiledSprite resetTutorialMessages;
    private TiledSprite scrollBar1;
    private TiledSprite scrollBar2;
    private TiledSprite showSystemControlIndicatorsBackground;
    private TiledSprite showSystemControlIndicatorsCheckbox;
    private VolumeControl soundVolumeControl;
    private TiledSprite tradegoodsBackground;
    private TiledSprite tradegoodsCheckbox;
    private TiledSprite tutorialVideos;
    private TiledSprite tutorialVideosBackground;
    private Text tutorialVideosText;
    private TiledSprite vibrateBackground;
    private TiledSprite vibrateCheckbox;

    public OptionsScene(Game game) {
        this.B = game;
    }

    private void autoSavedPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        setAutosave();
    }

    private void checkActionDown(Point point) {
        this.isScroll = false;
        if (point.getY() > 86.0f) {
            this.pressedY = point.getY();
            this.lastY = point.getY();
        }
        checkPress(point);
    }

    private void checkActionMove(Point point) {
        this.pressSprite.setVisible(false);
        if (point.getY() > 86.0f) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y = this.optionsList.getY() - (this.lastY - point.getY());
            float f2 = y <= 86.0f ? y : 86.0f;
            if (f2 < -230.0f) {
                f2 = -230.0f;
            }
            this.optionsList.setY(f2);
            this.lastY = point.getY();
            setScrollBar();
        }
        if (this.isScroll) {
            return;
        }
        checkPress(point);
    }

    private void checkActionUp(Point point) {
        this.pressSprite.setVisible(false);
        if (this.isScroll) {
            return;
        }
        if (isClicked(this.menuButton, point)) {
            menuButtonPressed();
        } else if (point.getY() < 86.0f) {
        } else {
            point.setY(point.getY() - this.optionsList.getY());
            if (isClicked(this.vibrateBackground, point)) {
                checkboxPressed(OptionID.VIBRATE, this.vibrateCheckbox, this.vibrateBackground);
            }
            if (isClicked(this.tradegoodsBackground, point)) {
                checkboxPressed(OptionID.SET_TRADEGOODS, this.tradegoodsCheckbox, this.tradegoodsBackground);
            }
            if (isClicked(this.clearEventsBackground, point)) {
                checkboxPressed(OptionID.CLEAR_EVENTS_END_TURN, this.clearEventsCheckbox, this.clearEventsBackground);
            }
            if (isClicked(this.showSystemControlIndicatorsBackground, point)) {
                checkboxPressed(OptionID.CONTROL_INDICATOR, this.showSystemControlIndicatorsCheckbox, this.showSystemControlIndicatorsBackground);
            }
            if (isClicked(this.resetTutorialMessages, point)) {
                resetTutorialMessagesPressed();
            }
            if (isClicked(this.googlePlayBackground, point)) {
                googlePlayGamesButtonPressed();
            }
            if (isClicked(this.gettingStartedVideoBackground, point)) {
                gettingStartedPressed();
            }
            if (isClicked(this.tutorialVideosBackground, point)) {
                tutorialVideosPressed();
            }
            if (isClicked(this.autosaveBackground, point)) {
                Point point2 = new Point(point.getX(), point.getY() - this.autosaveBackground.getY());
                if (isClicked(this.autosaveNever, point2)) {
                    Game.options.setOption(OptionID.AUTOSAVE, 0.0f);
                    autoSavedPressed();
                }
                if (isClicked(this.autosaveGameExit, point2)) {
                    Game.options.setOption(OptionID.AUTOSAVE, 1.0f);
                    autoSavedPressed();
                }
                if (isClicked(this.autosave10, point2)) {
                    Game.options.setOption(OptionID.AUTOSAVE, 2.0f);
                    autoSavedPressed();
                }
                if (isClicked(this.autosave5, point2)) {
                    Game.options.setOption(OptionID.AUTOSAVE, 3.0f);
                    autoSavedPressed();
                }
                if (isClicked(this.autosaveEveryTurn, point2)) {
                    Game.options.setOption(OptionID.AUTOSAVE, 4.0f);
                    autoSavedPressed();
                }
            }
            if (isClicked(this.fleetLinesBackground, point)) {
                Point point3 = new Point(point.getX(), point.getY() - this.fleetLinesBackground.getY());
                if (isClicked(this.greenLinesCheckbox, point3)) {
                    fleetLinesPressed(0, this.greenLinesCheckbox);
                }
                if (isClicked(this.blueLinesCheckbox, point3)) {
                    fleetLinesPressed(1, this.blueLinesCheckbox);
                }
                if (isClicked(this.redLinesCheckbox, point3)) {
                    fleetLinesPressed(2, this.redLinesCheckbox);
                }
            }
            if (isClicked(this.moddingBackground, point)) {
                moddingPressed();
            }
            if (isClicked(this.moddingInfoBackground, point)) {
                moddingInfoPressed();
            }
        }
    }

    private void checkPress(Point point) {
        if (point.getY() < 86.0f) {
            return;
        }
        point.setY(point.getY() - this.optionsList.getY());
        if (isClicked(this.vibrateBackground, point)) {
            setPress(this.vibrateBackground);
        }
        if (isClicked(this.tradegoodsBackground, point)) {
            setPress(this.tradegoodsBackground);
        }
        if (isClicked(this.clearEventsBackground, point)) {
            setPress(this.clearEventsBackground);
        }
        if (isClicked(this.showSystemControlIndicatorsBackground, point)) {
            setPress(this.showSystemControlIndicatorsBackground);
        }
        if (isClicked(this.resetTutorialMessages, point)) {
            setPress(this.resetTutorialMessages);
        }
        if (isClicked(this.googlePlayBackground, point)) {
            setPress(this.googlePlayBackground);
        }
        if (isClicked(this.gettingStartedVideoBackground, point)) {
            setPress(this.gettingStartedVideoBackground);
        }
        if (isClicked(this.tutorialVideosBackground, point)) {
            setPress(this.tutorialVideosBackground);
        }
        if (isClicked(this.moddingBackground, point)) {
            setPress(this.moddingBackground);
        }
        if (isClicked(this.moddingInfoBackground, point)) {
            setPress(this.moddingInfoBackground);
        }
    }

    private void checkboxPressed(OptionID optionID, TiledSprite tiledSprite, Sprite sprite) {
        int currentTileIndex = tiledSprite.getCurrentTileIndex();
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_ON;
        if (currentTileIndex == buttonsEnum.ordinal()) {
            Game.options.turnOff(optionID);
            tiledSprite.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
            sprite.setAlpha(0.3f);
        } else {
            Game.options.turnOn(optionID);
            tiledSprite.setCurrentTileIndex(buttonsEnum.ordinal());
            sprite.setAlpha(0.7f);
        }
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void closeScene() {
        changeScene(this.B.menuScene);
    }

    private void createModdingFolders() {
        String[] strArr = {"mod", "mod/Backgrounds", "mod/Ambassadors", "mod/Ships", "mod/Planets", "mod/Troops"};
        for (int i = 0; i < 6; i++) {
            String str = strArr[i];
            File file = new File(Environment.getExternalStorageDirectory() + "/uciana/" + str);
            if (!file.exists() && !file.mkdirs()) {
                Log.message("MOD", "Failed to create folder: /uciana/" + str);
            }
        }
    }

    private void createValuesXmlFiles() {
        XmlFile xmlFile = new XmlFile("/uciana/mod", "values.xml");
        xmlFile.create("empires");
        XmlFile xmlFile2 = new XmlFile("/uciana/mod", "empireValues.xml");
        xmlFile2.create("empires");
        XmlFile xmlFile3 = new XmlFile("/uciana/mod", "systemValues.xml");
        xmlFile3.create("systems");
        if (!xmlFile2.exists() && !xmlFile.exists()) {
            for (int i = 0; i < 7; i++) {
                Element newItem = xmlFile2.newItem("empire", Integer.toString(i));
                String[] strArr = {LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, "description", "home_system_name", "home_world_name", "home_world_description", "default_perk_1", "default_perk_2", "scout_ship_name", "colony_ship_name", "construction_ship_name", "transport_ship_name", "destroyer_ship_name", "cruiser_ship_name", "battle_ship_name", "dreadnought_ship_name"};
                for (int i2 = 0; i2 < 15; i2++) {
                    xmlFile2.add(newItem, strArr[i2]);
                }
            }
            xmlFile2.save();
        }
        if (xmlFile3.exists()) {
            return;
        }
        for (int i3 = 0; i3 < 60; i3++) {
            xmlFile3.add(xmlFile3.newItem("system", Integer.toString(i3)), LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME);
        }
        xmlFile3.save();
    }

    private void fleetLinesPressed(int i, TiledSprite tiledSprite) {
        boolean z;
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        int currentTileIndex = tiledSprite.getCurrentTileIndex();
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_ON;
        if (currentTileIndex == buttonsEnum.ordinal()) {
            z = false;
            tiledSprite.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
        } else {
            z = true;
            tiledSprite.setCurrentTileIndex(buttonsEnum.ordinal());
        }
        Game.options.setFleetLinesVisibility(i, z);
    }

    private void gettingStartedPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://youtu.be/4FjGtQCZm9k"));
        this.B.getActivity().startActivity(intent);
    }

    private void googlePlayGamesButtonPressed() {
        if (this.googlePlay.getAlpha() == 1.0f) {
            this.B.getActivity().runOnUiThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.z
                @Override // java.lang.Runnable
                public final void run() {
                    OptionsScene.this.lambda$googlePlayGamesButtonPressed$0();
                }
            });
            this.B.sounds.playBoxPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$googlePlayGamesButtonPressed$0() {
        this.googlePlay.setAlpha(0.5f);
        this.googlePlayText.setAlpha(0.5f);
        if (this.B.getActivity().isSignedIn()) {
            this.B.getActivity().signOutOfGooglePlay();
        } else {
            this.B.getActivity().getGoogleApiClient().connect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePoolElements$1(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        extendedScene.getPoolElements();
        M(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void menuButtonPressed() {
        closeScene();
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void moddingInfoPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        if (!Functions.CopyPdfToExternalStorage(this.B.getActivity(), "Modding.pdf")) {
            showMessage(new TextMessage("Unable to open the modding guide"));
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/uciana/manuals/Modding.pdf");
        Intent intent = new Intent("android.intent.action.VIEW");
        Uciana activity = this.B.getActivity();
        intent.setDataAndType(FileProvider.getUriForFile(activity, this.B.getActivity().getApplicationContext().getPackageName() + ".provider", file), "application/pdf");
        intent.setFlags(67108864);
        intent.setFlags(DriveFile.MODE_READ_ONLY);
        intent.addFlags(1);
        try {
            this.B.getActivity().getApplicationContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            showMessage(new TextMessage("No PDF Viewer available to show the guide"));
        }
    }

    private void moddingPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        if (ContextCompat.checkSelfPermission(this.B.getActivity(), "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            changeModdingSetting();
        } else {
            ActivityCompat.requestPermissions(this.B.getActivity(), new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 2);
        }
    }

    private void openScene() {
    }

    private void resetScrollList() {
        this.optionsList.setY(86.0f);
        setScrollBar();
    }

    private void resetTutorialMessagesPressed() {
        TutorialID[] values;
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        for (TutorialID tutorialID : TutorialID.values()) {
            if (tutorialID != TutorialID.FIRST_TIME && tutorialID != TutorialID.HUGE_MAP) {
                Game.options.markUnseen(tutorialID);
            }
        }
        showMessage(new TextMessage(this.B.getActivity().getString(R.string.options_tutorial_message)));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void set() {
        float f2;
        SupportedLocales supportedLocales;
        this.nebulas.setRandomGalaxy();
        setCheckbox(OptionID.VIBRATE, 1, this.vibrateCheckbox, this.vibrateBackground);
        setCheckbox(OptionID.SET_TRADEGOODS, 0, this.tradegoodsCheckbox, this.tradegoodsBackground);
        setCheckbox(OptionID.CLEAR_EVENTS_END_TURN, 0, this.clearEventsCheckbox, this.clearEventsBackground);
        setCheckbox(OptionID.CONTROL_INDICATOR, 0, this.showSystemControlIndicatorsCheckbox, this.showSystemControlIndicatorsBackground);
        if (ContextCompat.checkSelfPermission(this.B.getActivity(), "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            OptionID optionID = OptionID.MODDING;
            setCheckbox(optionID, 0, this.moddingCheckbox, this.moddingBackground);
            if (Game.options.isOn(optionID)) {
                f2 = 1.0f;
                this.moddingInfoBackground.setAlpha(f2);
                this.moddingInfoPDFText.setAlpha(f2);
                this.moddingInfoText.setAlpha(f2);
                TiledSprite tiledSprite = this.moddingInfoBackground;
                SupportedLocales locale = this.B.getLocale();
                supportedLocales = SupportedLocales.ENGLISH;
                tiledSprite.setVisible(locale != supportedLocales);
                setAutosave();
                setFleetLines();
                this.musicVolumeControl.setVolumeBars((int) (this.B.music.getVolume() * 10.0f), !Game.options.isOn(OptionID.MUSIC, 1));
                this.soundVolumeControl.setVolumeBars((int) (this.B.sounds.getVolume() * 10.0f), true ^ Game.options.isOn(OptionID.SOUND, 1));
                changeGooglePlayGameButton(this.B.getActivity().isSignedIn());
                if (this.B.getLocale() != supportedLocales) {
                    this.gettingStartedVideoBackground.setVisible(false);
                    this.gettingStartedVideo.setVisible(false);
                    this.gettingStartedVideoText.setVisible(false);
                    this.tutorialVideosBackground.setVisible(false);
                    this.tutorialVideos.setVisible(false);
                    this.tutorialVideosText.setVisible(false);
                }
                resetScrollList();
            }
        } else {
            this.moddingCheckbox.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
            this.moddingBackground.setAlpha(0.3f);
        }
        f2 = 0.4f;
        this.moddingInfoBackground.setAlpha(f2);
        this.moddingInfoPDFText.setAlpha(f2);
        this.moddingInfoText.setAlpha(f2);
        TiledSprite tiledSprite2 = this.moddingInfoBackground;
        SupportedLocales locale2 = this.B.getLocale();
        supportedLocales = SupportedLocales.ENGLISH;
        tiledSprite2.setVisible(locale2 != supportedLocales);
        setAutosave();
        setFleetLines();
        this.musicVolumeControl.setVolumeBars((int) (this.B.music.getVolume() * 10.0f), !Game.options.isOn(OptionID.MUSIC, 1));
        this.soundVolumeControl.setVolumeBars((int) (this.B.sounds.getVolume() * 10.0f), true ^ Game.options.isOn(OptionID.SOUND, 1));
        changeGooglePlayGameButton(this.B.getActivity().isSignedIn());
        if (this.B.getLocale() != supportedLocales) {
        }
        resetScrollList();
    }

    private void setAutosave() {
        TiledSprite tiledSprite = this.autosaveNever;
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_OFF;
        tiledSprite.setCurrentTileIndex(buttonsEnum.ordinal());
        this.autosaveGameExit.setCurrentTileIndex(buttonsEnum.ordinal());
        this.autosave10.setCurrentTileIndex(buttonsEnum.ordinal());
        this.autosave5.setCurrentTileIndex(buttonsEnum.ordinal());
        this.autosaveEveryTurn.setCurrentTileIndex(buttonsEnum.ordinal());
        int option = (int) Game.options.getOption(OptionID.AUTOSAVE, 2.0f);
        if (option == 0) {
            this.autosaveNever.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        } else if (option == 1) {
            this.autosaveGameExit.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        } else if (option == 2) {
            this.autosave10.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        } else if (option == 3) {
            this.autosave5.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        } else if (option != 4) {
        } else {
            this.autosaveEveryTurn.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
        }
    }

    private void setCheckbox(OptionID optionID, int i, TiledSprite tiledSprite, Sprite sprite) {
        if (Game.options.isOn(optionID, i)) {
            tiledSprite.setCurrentTileIndex(ButtonsEnum.RADIO_ON.ordinal());
            sprite.setAlpha(0.7f);
            return;
        }
        tiledSprite.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
        sprite.setAlpha(0.3f);
    }

    private void setFleetLines() {
        TiledSprite tiledSprite = this.greenLinesCheckbox;
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_ON;
        tiledSprite.setCurrentTileIndex(buttonsEnum.ordinal());
        this.blueLinesCheckbox.setCurrentTileIndex(buttonsEnum.ordinal());
        this.redLinesCheckbox.setCurrentTileIndex(buttonsEnum.ordinal());
        if (Game.options.shouldHideFleetLines(0)) {
            this.greenLinesCheckbox.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
        }
        if (Game.options.shouldHideFleetLines(1)) {
            this.blueLinesCheckbox.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
        }
        if (Game.options.shouldHideFleetLines(2)) {
            this.redLinesCheckbox.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
        }
    }

    private void setPress(Sprite sprite) {
        this.pressSprite.setVisible(true);
        this.pressSprite.setSize(sprite.getWidthScaled(), sprite.getHeightScaled());
        this.pressSprite.setPosition(sprite.getX(), sprite.getY());
    }

    private void setScrollBar() {
        this.scrollBar1.setHeight(506.98526f);
        this.scrollBar2.setHeight(506.98526f);
        float y = ((((this.optionsList.getY() - 86.0f) * (-1.0f)) / 950.0f) * 694.0f) + 86.0f;
        this.scrollBar1.setY(y);
        this.scrollBar2.setY(y);
    }

    private void setupModding() {
        createModdingFolders();
        createValuesXmlFiles();
    }

    private void tutorialVideosPressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=hWCiq9loA5g&list=PLEIe-rUKX3sKQkxGq7v3JpqfILobM96At"));
        this.B.getActivity().startActivity(intent);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void M(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof MenuScene) {
            this.B.menuScene.openMenu();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        closeScene();
    }

    public void changeGooglePlayGameButton(boolean z) {
        TiledSprite tiledSprite = this.googlePlay;
        if (tiledSprite == null || this.googlePlayText == null) {
            return;
        }
        tiledSprite.setAlpha(1.0f);
        this.googlePlayText.setAlpha(1.0f);
        if (z) {
            this.googlePlayText.setText(this.B.getActivity().getString(R.string.options_google_play_signout));
        } else {
            this.googlePlayText.setText(this.B.getActivity().getString(R.string.options_google_play_signin));
        }
    }

    public void changeModdingSetting() {
        int currentTileIndex = this.moddingCheckbox.getCurrentTileIndex();
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_ON;
        if (currentTileIndex == buttonsEnum.ordinal()) {
            Game.options.turnOff(OptionID.MODDING);
            this.moddingCheckbox.setCurrentTileIndex(ButtonsEnum.RADIO_OFF.ordinal());
            this.moddingBackground.setAlpha(0.3f);
        } else {
            Game.options.turnOn(OptionID.MODDING);
            this.moddingCheckbox.setCurrentTileIndex(buttonsEnum.ordinal());
            this.moddingBackground.setAlpha(0.7f);
            setupModding();
        }
        showMessage(new ChangeModdingSettingMessage());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (!this.isScroll) {
            this.musicVolumeControl.checkInputOnControl(i, new Point(point.getX(), point.getY() - this.optionsList.getY()));
            this.soundVolumeControl.checkInputOnControl(i, new Point(point.getX(), point.getY() - this.optionsList.getY()));
        }
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
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        Scene scene = new Scene();
        this.optionsList = scene;
        scene.setPosition(0.0f, 86.0f);
        this.optionsList.setBackgroundEnabled(false);
        attachChild(this.optionsList);
        TiledSprite tiledSprite = new TiledSprite(getWidth() - 7.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.scrollBar1 = tiledSprite;
        tiledSprite.setCurrentTileIndex(3);
        this.scrollBar1.setWidth(6.0f);
        attachChild(this.scrollBar1);
        TiledSprite tiledSprite2 = new TiledSprite(getWidth() - 7.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.scrollBar2 = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(3);
        this.scrollBar2.setWidth(6.0f);
        attachChild(this.scrollBar2);
        Game game = this.B;
        VolumeControl volumeControl = new VolumeControl(game.music, OptionID.MUSIC, game, vertexBufferObjectManager);
        this.musicVolumeControl = volumeControl;
        volumeControl.setPosition(0.0f, 0.0f);
        this.optionsList.attachChild(this.musicVolumeControl);
        Game game2 = this.B;
        VolumeControl volumeControl2 = new VolumeControl(game2.sounds, OptionID.SOUND, game2, vertexBufferObjectManager);
        this.soundVolumeControl = volumeControl2;
        volumeControl2.setPosition(getWidth() - 640.0f, 0.0f);
        this.optionsList.attachChild(this.soundVolumeControl);
        Sprite sprite = new Sprite(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.pressSprite = sprite;
        sprite.setSize(620.0f, 86.0f);
        this.pressSprite.setVisible(false);
        this.optionsList.attachChild(this.pressSprite);
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 164.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.vibrateBackground = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(2);
        this.vibrateBackground.setSize(620.0f, 86.0f);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.vibrateCheckbox = tiledSprite4;
        ButtonsEnum buttonsEnum = ButtonsEnum.RADIO_OFF;
        tiledSprite4.setCurrentTileIndex(buttonsEnum.ordinal());
        this.vibrateBackground.attachChild(this.vibrateCheckbox);
        Game game3 = this.B;
        Font font = game3.fonts.smallFont;
        String string = game3.getActivity().getString(R.string.options_vibrate);
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        Text text = new Text(135.0f, 27.0f, font, string, new TextOptions(autoWrap, 475.0f, horizontalAlign), vertexBufferObjectManager);
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
        this.vibrateBackground.attachChild(text);
        this.optionsList.attachChild(this.vibrateBackground);
        TiledSprite tiledSprite5 = new TiledSprite(getWidth() - 640.0f, 164.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.tradegoodsBackground = tiledSprite5;
        tiledSprite5.setSize(620.0f, 86.0f);
        this.tradegoodsBackground.setCurrentTileIndex(2);
        TiledSprite tiledSprite6 = new TiledSprite(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.tradegoodsCheckbox = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(buttonsEnum.ordinal());
        this.tradegoodsBackground.attachChild(this.tradegoodsCheckbox);
        Game game4 = this.B;
        Text text2 = new Text(135.0f, 12.0f, game4.fonts.smallFont, game4.getActivity().getString(R.string.options_tradegoods), new TextOptions(autoWrap, 475.0f, horizontalAlign), vertexBufferObjectManager);
        text2.setY(43.0f - (text2.getHeightScaled() / 2.0f));
        this.tradegoodsBackground.attachChild(text2);
        this.optionsList.attachChild(this.tradegoodsBackground);
        TiledSprite tiledSprite7 = new TiledSprite(0.0f, 264.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.clearEventsBackground = tiledSprite7;
        tiledSprite7.setSize(620.0f, 86.0f);
        this.clearEventsBackground.setCurrentTileIndex(2);
        TiledSprite tiledSprite8 = new TiledSprite(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.clearEventsCheckbox = tiledSprite8;
        tiledSprite8.setCurrentTileIndex(buttonsEnum.ordinal());
        this.clearEventsBackground.attachChild(this.clearEventsCheckbox);
        Game game5 = this.B;
        Text text3 = new Text(135.0f, 27.0f, game5.fonts.smallFont, game5.getActivity().getString(R.string.options_clear_events), new TextOptions(autoWrap, 475.0f, horizontalAlign), vertexBufferObjectManager);
        text3.setY(43.0f - (text3.getHeightScaled() / 2.0f));
        this.clearEventsBackground.attachChild(text3);
        this.optionsList.attachChild(this.clearEventsBackground);
        TiledSprite tiledSprite9 = new TiledSprite(getWidth() - 640.0f, 264.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.googlePlayBackground = tiledSprite9;
        tiledSprite9.setSize(620.0f, 86.0f);
        this.googlePlayBackground.setCurrentTileIndex(2);
        this.googlePlayBackground.setAlpha(0.7f);
        Text text4 = new Text(135.0f, 0.0f, this.B.fonts.smallFont, this.D, new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.googlePlayText = text4;
        text4.setText(this.B.getActivity().getString(R.string.options_google_play_signin));
        Text text5 = this.googlePlayText;
        text5.setY(43.0f - (text5.getHeightScaled() / 2.0f));
        this.googlePlayBackground.attachChild(this.googlePlayText);
        TiledSprite tiledSprite10 = new TiledSprite(17.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.googlePlay = tiledSprite10;
        tiledSprite10.setCurrentTileIndex(GameIconEnum.GOOGLE_PLAY_LOGIN.ordinal());
        this.googlePlay.setSize(86.0f, 86.0f);
        this.googlePlayBackground.attachChild(this.googlePlay);
        this.optionsList.attachChild(this.googlePlayBackground);
        TiledSprite tiledSprite11 = new TiledSprite(0.0f, 364.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.showSystemControlIndicatorsBackground = tiledSprite11;
        tiledSprite11.setSize(620.0f, 86.0f);
        this.showSystemControlIndicatorsBackground.setCurrentTileIndex(2);
        TiledSprite tiledSprite12 = new TiledSprite(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.showSystemControlIndicatorsCheckbox = tiledSprite12;
        tiledSprite12.setCurrentTileIndex(buttonsEnum.ordinal());
        this.showSystemControlIndicatorsBackground.attachChild(this.showSystemControlIndicatorsCheckbox);
        Game game6 = this.B;
        Text text6 = new Text(135.0f, 27.0f, game6.fonts.smallFont, game6.getActivity().getString(R.string.options_system_control), new TextOptions(autoWrap, 475.0f, horizontalAlign), vertexBufferObjectManager);
        text6.setY(43.0f - (text6.getHeightScaled() / 2.0f));
        this.showSystemControlIndicatorsBackground.attachChild(text6);
        this.optionsList.attachChild(this.showSystemControlIndicatorsBackground);
        TiledSprite tiledSprite13 = new TiledSprite(0.0f, 464.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.resetTutorialMessages = tiledSprite13;
        tiledSprite13.setSize(620.0f, 86.0f);
        this.resetTutorialMessages.setCurrentTileIndex(2);
        this.resetTutorialMessages.setAlpha(0.7f);
        TiledSprite tiledSprite14 = new TiledSprite(17.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite14.setCurrentTileIndex(GameIconEnum.CLOSE.ordinal());
        tiledSprite14.setSize(86.0f, 86.0f);
        this.resetTutorialMessages.attachChild(tiledSprite14);
        Game game7 = this.B;
        Text text7 = new Text(135.0f, 27.0f, game7.fonts.smallFont, game7.getActivity().getString(R.string.options_reset_tutorial), new TextOptions(autoWrap, 475.0f, horizontalAlign), vertexBufferObjectManager);
        text7.setY(43.0f - (text7.getHeightScaled() / 2.0f));
        this.resetTutorialMessages.attachChild(text7);
        this.optionsList.attachChild(this.resetTutorialMessages);
        TiledSprite tiledSprite15 = new TiledSprite(getWidth() - 640.0f, 464.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.gettingStartedVideoBackground = tiledSprite15;
        tiledSprite15.setSize(300.0f, 86.0f);
        this.gettingStartedVideoBackground.setCurrentTileIndex(2);
        this.gettingStartedVideoBackground.setAlpha(0.7f);
        this.gettingStartedVideoText = new Text(135.0f, 27.0f, this.B.fonts.smallFont, this.D, new TextOptions(autoWrap, 175.0f, horizontalAlign), vertexBufferObjectManager);
        TiledSprite tiledSprite16 = new TiledSprite(17.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.gettingStartedVideo = tiledSprite16;
        GameIconEnum gameIconEnum = GameIconEnum.SYSTEM_SEARCH;
        tiledSprite16.setCurrentTileIndex(gameIconEnum.ordinal());
        this.gettingStartedVideo.setSize(86.0f, 86.0f);
        this.gettingStartedVideoBackground.attachChild(this.gettingStartedVideo);
        this.gettingStartedVideoText.setText("Getting Started Tutorial");
        Text text8 = this.gettingStartedVideoText;
        text8.setY(43.0f - (text8.getHeightScaled() / 2.0f));
        this.gettingStartedVideoBackground.attachChild(this.gettingStartedVideoText);
        this.optionsList.attachChild(this.gettingStartedVideoBackground);
        TiledSprite tiledSprite17 = new TiledSprite(getWidth() - 320.0f, 464.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.tutorialVideosBackground = tiledSprite17;
        tiledSprite17.setSize(300.0f, 86.0f);
        this.tutorialVideosBackground.setCurrentTileIndex(2);
        this.tutorialVideosBackground.setAlpha(0.7f);
        this.tutorialVideosText = new Text(135.0f, 27.0f, this.B.fonts.smallFont, this.D, new TextOptions(autoWrap, 175.0f, horizontalAlign), vertexBufferObjectManager);
        TiledSprite tiledSprite18 = new TiledSprite(17.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.tutorialVideos = tiledSprite18;
        tiledSprite18.setCurrentTileIndex(gameIconEnum.ordinal());
        this.tutorialVideos.setSize(86.0f, 86.0f);
        this.tutorialVideosBackground.attachChild(this.tutorialVideos);
        this.tutorialVideosText.setText("Other Tutorial Videos");
        Text text9 = this.tutorialVideosText;
        text9.setY(43.0f - (text9.getHeightScaled() / 2.0f));
        this.tutorialVideosBackground.attachChild(this.tutorialVideosText);
        this.optionsList.attachChild(this.tutorialVideosBackground);
        TiledSprite tiledSprite19 = new TiledSprite(0.0f, 564.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.fleetLinesBackground = tiledSprite19;
        tiledSprite19.setSize(getWidth() - 20.0f, 96.0f);
        this.fleetLinesBackground.setCurrentTileIndex(2);
        this.fleetLinesBackground.setAlpha(0.7f);
        Text text10 = new Text(135.0f, 27.0f, this.B.fonts.smallFont, this.D, new TextOptions(autoWrap, 175.0f, horizontalAlign), vertexBufferObjectManager);
        TiledSprite tiledSprite20 = new TiledSprite(17.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite20.setCurrentTileIndex(GameIconEnum.SHIPS.ordinal());
        tiledSprite20.setSize(86.0f, 86.0f);
        this.fleetLinesBackground.attachChild(tiledSprite20);
        text10.setText(this.B.getActivity().getString(R.string.options_fleet_destination_lines));
        text10.setY(48.0f - (text10.getHeightScaled() / 2.0f));
        this.fleetLinesBackground.attachChild(text10);
        TiledSprite tiledSprite21 = new TiledSprite(400.0f, -5.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.greenLinesCheckbox = tiledSprite21;
        this.fleetLinesBackground.attachChild(tiledSprite21);
        Game game8 = this.B;
        Font font2 = game8.fonts.smallFont;
        String string2 = game8.getActivity().getString(R.string.options_fleet_destination_lines_green);
        HorizontalAlign horizontalAlign2 = HorizontalAlign.CENTER;
        Text text11 = new Text(0.0f, 70.0f, font2, string2, new TextOptions(horizontalAlign2), vertexBufferObjectManager);
        text11.setX(460.0f - (text11.getWidthScaled() / 2.0f));
        this.fleetLinesBackground.attachChild(text11);
        TiledSprite tiledSprite22 = new TiledSprite(600.0f, -5.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.blueLinesCheckbox = tiledSprite22;
        this.fleetLinesBackground.attachChild(tiledSprite22);
        Game game9 = this.B;
        Text text12 = new Text(0.0f, 70.0f, game9.fonts.smallFont, game9.getActivity().getString(R.string.options_fleet_destination_lines_blue), new TextOptions(horizontalAlign2), vertexBufferObjectManager);
        text12.setX(660.0f - (text12.getWidthScaled() / 2.0f));
        this.fleetLinesBackground.attachChild(text12);
        TiledSprite tiledSprite23 = new TiledSprite(800.0f, -5.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.redLinesCheckbox = tiledSprite23;
        this.fleetLinesBackground.attachChild(tiledSprite23);
        Game game10 = this.B;
        Text text13 = new Text(0.0f, 70.0f, game10.fonts.smallFont, game10.getActivity().getString(R.string.options_fleet_destination_lines_red), new TextOptions(horizontalAlign2), vertexBufferObjectManager);
        text13.setX(860.0f - (text13.getWidthScaled() / 2.0f));
        this.fleetLinesBackground.attachChild(text13);
        this.optionsList.attachChild(this.fleetLinesBackground);
        TiledSprite tiledSprite24 = new TiledSprite(0.0f, 674.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.autosaveBackground = tiledSprite24;
        tiledSprite24.setAlpha(0.5f);
        this.autosaveBackground.setCurrentTileIndex(1);
        this.autosaveBackground.setSize(getWidth() - 20.0f, 160.0f);
        Game game11 = this.B;
        this.autosaveBackground.attachChild(new Text(5.0f, 5.0f, game11.fonts.smallFont, game11.getActivity().getString(R.string.options_autosave), vertexBufferObjectManager));
        TiledSprite tiledSprite25 = new TiledSprite(100.0f, 25.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.autosaveNever = tiledSprite25;
        this.autosaveBackground.attachChild(tiledSprite25);
        Game game12 = this.B;
        Text text14 = new Text(0.0f, 105.0f, game12.fonts.smallFont, game12.getActivity().getString(R.string.options_autosave_never), new TextOptions(horizontalAlign2), vertexBufferObjectManager);
        text14.setX(160.0f - (text14.getWidthScaled() / 2.0f));
        this.autosaveBackground.attachChild(text14);
        TiledSprite tiledSprite26 = new TiledSprite(300.0f, 25.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.autosaveGameExit = tiledSprite26;
        this.autosaveBackground.attachChild(tiledSprite26);
        Game game13 = this.B;
        Text text15 = new Text(0.0f, 105.0f, game13.fonts.smallFont, game13.getActivity().getString(R.string.options_autosave_on_game_exit), new TextOptions(horizontalAlign2), vertexBufferObjectManager);
        text15.setX(360.0f - (text15.getWidthScaled() / 2.0f));
        this.autosaveBackground.attachChild(text15);
        TiledSprite tiledSprite27 = new TiledSprite(500.0f, 25.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.autosave10 = tiledSprite27;
        this.autosaveBackground.attachChild(tiledSprite27);
        Game game14 = this.B;
        Text text16 = new Text(0.0f, 105.0f, game14.fonts.smallFont, game14.getActivity().getString(R.string.options_autosave_every_10_turns), new TextOptions(horizontalAlign2), vertexBufferObjectManager);
        text16.setX(560.0f - (text16.getWidthScaled() / 2.0f));
        this.autosaveBackground.attachChild(text16);
        TiledSprite tiledSprite28 = new TiledSprite(700.0f, 25.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.autosave5 = tiledSprite28;
        this.autosaveBackground.attachChild(tiledSprite28);
        Game game15 = this.B;
        Text text17 = new Text(0.0f, 105.0f, game15.fonts.smallFont, game15.getActivity().getString(R.string.options_autosave_every_5_turns), new TextOptions(horizontalAlign2), vertexBufferObjectManager);
        text17.setX(760.0f - (text17.getWidthScaled() / 2.0f));
        this.autosaveBackground.attachChild(text17);
        TiledSprite tiledSprite29 = new TiledSprite(900.0f, 25.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.autosaveEveryTurn = tiledSprite29;
        this.autosaveBackground.attachChild(tiledSprite29);
        Game game16 = this.B;
        Text text18 = new Text(0.0f, 105.0f, game16.fonts.smallFont, game16.getActivity().getString(R.string.options_autosave_every_turn), new TextOptions(horizontalAlign2), vertexBufferObjectManager);
        text18.setX(960.0f - (text18.getWidthScaled() / 2.0f));
        this.autosaveBackground.attachChild(text18);
        this.optionsList.attachChild(this.autosaveBackground);
        TiledSprite tiledSprite30 = new TiledSprite(0.0f, 845.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.moddingBackground = tiledSprite30;
        tiledSprite30.setSize(620.0f, 86.0f);
        this.moddingBackground.setCurrentTileIndex(2);
        TiledSprite tiledSprite31 = new TiledSprite(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.moddingCheckbox = tiledSprite31;
        tiledSprite31.setCurrentTileIndex(buttonsEnum.ordinal());
        this.moddingBackground.attachChild(this.moddingCheckbox);
        Game game17 = this.B;
        Text text19 = new Text(135.0f, 27.0f, game17.fonts.smallFont, game17.getActivity().getString(R.string.options_modding), new TextOptions(autoWrap, 475.0f, horizontalAlign), vertexBufferObjectManager);
        text19.setY(43.0f - (text19.getHeightScaled() / 2.0f));
        this.moddingBackground.attachChild(text19);
        this.optionsList.attachChild(this.moddingBackground);
        TiledSprite tiledSprite32 = new TiledSprite(getWidth() - 640.0f, 845.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.moddingInfoBackground = tiledSprite32;
        tiledSprite32.setSize(620.0f, 86.0f);
        this.moddingInfoBackground.setCurrentTileIndex(2);
        Text text20 = new Text(30.0f, 20.0f, this.B.fonts.menuFont, "PDF", new TextOptions(autoWrap, 475.0f, horizontalAlign), vertexBufferObjectManager);
        this.moddingInfoPDFText = text20;
        text20.setY(43.0f - (text20.getHeightScaled() / 2.0f));
        this.moddingInfoBackground.attachChild(this.moddingInfoPDFText);
        Text text21 = new Text(135.0f, 27.0f, this.B.fonts.smallFont, "Open Modding Guide", new TextOptions(autoWrap, 475.0f, horizontalAlign), vertexBufferObjectManager);
        this.moddingInfoText = text21;
        text21.setY(43.0f - (text21.getHeightScaled() / 2.0f));
        this.moddingInfoBackground.attachChild(this.moddingInfoText);
        this.optionsList.attachChild(this.moddingInfoBackground);
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        Game game18 = this.B;
        Text F = F(0.0f, 0.0f, game18.fonts.menuFont, game18.getActivity().getString(R.string.options_options), this.E, vertexBufferObjectManager);
        F.setX((getWidth() / 2.0f) - (F.getWidthScaled() / 2.0f));
        F.setY(43.0f - (F.getHeightScaled() / 2.0f));
        this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.MENU.ordinal(), true);
        this.menuButton = H;
        s(H);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.a0
            @Override // java.lang.Runnable
            public final void run() {
                OptionsScene.this.lambda$releasePoolElements$1(extendedScene, obj);
            }
        });
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        set();
        super.switched();
        openScene();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
