package com.birdshel.Uciana.Scenes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Parcelable;

import androidx.core.app.ActivityCompat;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.birdshel.Uciana.Elements.LoadSaveScene.GameSavePreviewImage;
import com.birdshel.Uciana.Elements.LoadSaveScene.GameSaveTile;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.ConfirmOverlay;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.PlayerSettings;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.SaveGameData.PreviewData;
import com.birdshel.Uciana.SaveGameData.SavedGameDetails;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.Utility.Log;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class LoadSaveScene extends ExtendedScene {
    private Sprite bottomBlackedBackground;
    private ConfirmOverlay confirmOverlay;
    private TiledSprite deleteButton;
    private Sprite deleteButtonPressed;
    private Text deleteText;
    private PreviewData externalPreviewData;
    private GameSavePreviewImage gameSavePreviewImage;
    private Sprite leftBlackedBackground;
    private boolean load;
    private TiledSprite loadSaveButton;
    private Sprite loadSaveButtonPressed;
    private Text loadSaveText;
    private TiledSprite menuButton;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private Sprite pressed;
    private Sprite rightBlackedBackground;
    private Sprite selected;
    private int selectedSaveIndex;
    private int selectedSlot;
    private Text title;
    private Sprite topBlackedBackground;
    private final GameSaveTile[] gameSaveTiles = new GameSaveTile[7];
    private final List<PreviewData> previewDataList = new ArrayList();
    final int[] J = {0, 4, 1, 2, 3, 5};
    private boolean loadPressed = false;
    private final int STORAGE_SLOT = 6;

    public LoadSaveScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(Point point) {
        GameSaveTile[] gameSaveTileArr;
        checkPress(point);
        this.selectedSaveIndex = -1;
        int i = 0;
        for (GameSaveTile gameSaveTile : this.gameSaveTiles) {
            if (gameSaveTile.getAlpha() == 0.8f && gameSaveTile.isPressed(point)) {
                this.selectedSaveIndex = i;
            }
            i++;
        }
    }

    private void checkActionMove(Point point) {
        this.loadSaveButtonPressed.setVisible(false);
        this.loadSaveText.setColor(Color.BLACK);
        this.deleteButtonPressed.setVisible(false);
        this.pressed.setVisible(false);
        checkPress(point);
    }

    private void checkActionUp(Point point) {
        this.loadSaveButtonPressed.setVisible(false);
        this.loadSaveText.setColor(Color.BLACK);
        this.pressed.setVisible(false);
        if (isClicked(this.menuButton, point)) {
            menuButtonPressed();
            return;
        }
        int i = 0;
        for (GameSaveTile gameSaveTile : this.gameSaveTiles) {
            if (gameSaveTile.isPressed(point)) {
                loadSaveBoxPressed(i);
            }
            i++;
        }
        if (isClicked(this.loadSaveButton, point)) {
            this.B.sounds.playBoxPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
            if (this.load) {
                loadButtonPressed();
            } else {
                saveButtonPressed();
            }
        }
        if (isClicked(this.deleteButton, point)) {
            deletePressed();
        }
    }

    private void checkPress(Point point) {
        GameSaveTile[] gameSaveTileArr;
        for (GameSaveTile gameSaveTile : this.gameSaveTiles) {
            if (gameSaveTile.getAlpha() == 0.8f && gameSaveTile.isPressed(point)) {
                this.pressed.setVisible(true);
                this.pressed.setPosition(gameSaveTile.getX(), gameSaveTile.getY());
            }
        }
        if (isClicked(this.loadSaveButton, point)) {
            this.loadSaveButtonPressed.setVisible(true);
            this.loadSaveText.setColor(Color.WHITE);
        }
        if (isClicked(this.deleteButton, point)) {
            this.deleteButtonPressed.setVisible(true);
        }
    }

    private void closeScene() {
    }

    private void deletePressed() {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.deleteButtonPressed.setVisible(false);
        this.confirmOverlay.setOverlay(0, this.J[this.selectedSlot]);
        setChildScene(this.confirmOverlay, false, false, true);
    }

    private void disableButtons() {
        this.loadSaveButton.setVisible(false);
        this.loadSaveButtonPressed.setVisible(false);
        this.loadSaveText.setVisible(false);
        this.deleteButton.setVisible(false);
        this.deleteButtonPressed.setVisible(false);
        this.deleteText.setVisible(false);
    }

    private void externalStorageGameSave() {
        boolean z = true;
        boolean z2 = false;
        if (ContextCompat.checkSelfPermission(this.B.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            File file = new File(Environment.getExternalStorageDirectory() + "/uciana");
            File file2 = new File(file.getAbsolutePath() + File.separator + "save");
            if (file2.exists()) {
                try {
                    this.gameSavePreviewImage.set(this.B.getSavedGameDetails().getNebulas(this.B.getActivity().openOrCreateDatabase(file2.getAbsolutePath(), 0, null)), this.externalPreviewData);
                } catch (Exception unused) {
                }
                z2 = z;
            }
            z = false;
            z2 = z;
        } else {
            this.loadSaveButton.setVisible(false);
            ActivityCompat.requestPermissions(this.B.getActivity(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2);
        }
        setPreviewImageVisible(z2);
    }

    private void finishLoading() {
        this.B.techScene.resetLists();
        this.loadPressed = false;
        this.B.setGameEnded(false);
        this.B.startTime = System.currentTimeMillis();
        goBackToGame();
    }

    private PreviewData getPreviewData(int i) {
        return this.previewDataList.get(i);
    }

    private void internalStorageGameSave(int i) {
        boolean z = false;
        if (this.B.doesSaveExists(this.J[i])) {
            try {
                this.gameSavePreviewImage.set(this.B.getSavedGameDetails().getNebulas(this.J[i]), this.previewDataList.get(i));
                z = true;
            } catch (Exception unused) {
            }
        }
        setPreviewImageVisible(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(int i) {
        this.B.load(i);
        finishLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFromStorage$1() {
        this.B.loadFromStorage();
        finishLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePoolElements$2(ExtendedScene extendedScene, Object obj) {
        this.gameSavePreviewImage.releasePoolElements();
        this.nebulaBackground.detachChild(this.nebulas);
        extendedScene.getPoolElements();
        P(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void load(final int i) {
        this.loadPressed = true;
        disableButtons();
        AsyncTask.execute(new Runnable() { // from class: com.birdshel.Uciana.Scenes.q
            @Override // java.lang.Runnable
            public final void run() {
                LoadSaveScene.this.lambda$load$0(i);
            }
        });
    }

    private void loadButtonPressed() {
        if (this.loadPressed) {
            return;
        }
        int i = this.selectedSlot;
        if (i != 6) {
            load(this.J[i]);
        } else {
            loadFromStorage();
        }
    }

    private void loadFromStorage() {
        this.loadPressed = true;
        disableButtons();
        AsyncTask.execute(new Runnable() { // from class: com.birdshel.Uciana.Scenes.p
            @Override // java.lang.Runnable
            public final void run() {
                LoadSaveScene.this.lambda$loadFromStorage$1();
            }
        });
    }

    private void loadSaveBoxPressed(int i) {
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        setSelected(i);
    }

    private void menuButtonPressed() {
        closeScene();
        changeScene(this.B.menuScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void openScene() {
    }

    private void save(int i) {
        this.B.save(i);
        goBackToGame();
    }

    private void saveButtonPressed() {
        int i = this.selectedSlot;
        if (i != 6) {
            save(this.J[i]);
        } else if (ContextCompat.checkSelfPermission(this.B.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this.B.getActivity(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {
            this.B.saveToStorage();
            goBackToGame();
        }
    }

    private void sendDB(int i) {
        File databasePath = this.B.getActivity().getApplicationContext().getDatabasePath("uciana" + this.J[i]);
        if (databasePath.exists()) {
            sendDbByEmail(databasePath);
        }
    }

    private void sendDbByEmail(File file) {
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.setType("text/plain");
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        arrayList.add(FileProvider.getUriForFile(this.B.getActivity().getApplicationContext(), "com.birdshel.Uciana.provider", file));
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        intent.addFlags(1);
        this.B.getActivity().startActivity(Intent.createChooser(intent, this.B.getActivity().getString(R.string.save_share)));
    }

    private void sendExternalStorageDB() {
        if (ContextCompat.checkSelfPermission(this.B.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            File file = new File(Environment.getExternalStorageDirectory() + "/uciana");
            File file2 = new File(file.getAbsolutePath() + File.separator + "save");
            if (file2.exists()) {
                sendDbByEmail(file2);
            }
        }
    }

    private void setExternalStorageSaveTile() {
        this.gameSaveTiles[6].enable();
        this.gameSaveTiles[6].hideInfo();
        if (ContextCompat.checkSelfPermission(this.B.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            File file = new File(Environment.getExternalStorageDirectory() + "/uciana");
            File file2 = new File(file.getAbsolutePath() + File.separator + "save");
            if (!file2.exists()) {
                if (this.load) {
                    this.gameSaveTiles[6].disable();
                    return;
                }
                return;
            }
            PreviewData previewData = this.B.getSavedGameDetails().getPreviewData(this.B.getActivity().openOrCreateDatabase(file2.getAbsolutePath(), 0, null));
            this.externalPreviewData = previewData;
            this.gameSaveTiles[6].setTimeStamp(previewData.saveTimeStamp);
            GameSaveTile gameSaveTile = this.gameSaveTiles[6];
            PreviewData previewData2 = this.externalPreviewData;
            gameSaveTile.setTurnCountAndTimePlayed(previewData2.turns, previewData2.playTime / 1000);
            this.gameSaveTiles[6].setDifficulty(this.externalPreviewData.difficulty);
            PreviewData previewData3 = this.externalPreviewData;
            int i = previewData3.currentPlayer;
            if (i != -1) {
                this.gameSaveTiles[6].setEmpireBanner(i, previewData3.bannerID);
                return;
            }
            return;
        }
        this.gameSaveTiles[6].showPermissionMessage();
    }

    private void setNewestSelected(boolean z) {
        int N = N(z);
        if (N == -1) {
            setSelected(0);
        } else {
            setSelected(N);
        }
    }

    private void setPreviewImageVisible(boolean z) {
        this.gameSavePreviewImage.setVisible(z);
        this.topBlackedBackground.setVisible(z);
        this.bottomBlackedBackground.setVisible(z);
        this.leftBlackedBackground.setVisible(z);
        this.rightBlackedBackground.setVisible(z);
    }

    private void setSelected(int i) {
        GameSaveTile[] gameSaveTileArr;
        this.loadSaveButton.setVisible(true);
        this.deleteButton.setVisible(false);
        this.deleteButtonPressed.setVisible(false);
        this.deleteText.setVisible(false);
        if (i != 0 && i != 1 && i != 6 && this.B.doesSaveExists(this.J[i])) {
            this.deleteButton.setVisible(true);
            this.deleteText.setVisible(true);
        }
        this.selectedSlot = i;
        int i2 = 0;
        for (GameSaveTile gameSaveTile : this.gameSaveTiles) {
            if (i2 == i) {
                gameSaveTile.registerEntityModifier(new MoveModifier(0.15f, gameSaveTile.getX(), 0.0f, gameSaveTile.getY(), gameSaveTile.getY()));
                this.selected.registerEntityModifier(new MoveModifier(0.15f, gameSaveTile.getX(), 0.0f, gameSaveTile.getY(), gameSaveTile.getY()));
                if (i2 == 6) {
                    externalStorageGameSave();
                } else {
                    internalStorageGameSave(i2);
                }
            } else {
                gameSaveTile.registerEntityModifier(new MoveModifier(0.15f, gameSaveTile.getX(), 50.0f, gameSaveTile.getY(), gameSaveTile.getY()));
            }
            i2++;
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
        GameSaveTile[] gameSaveTileArr;
        int i = 0;
        for (GameSaveTile gameSaveTile : this.gameSaveTiles) {
            if (i == this.selectedSaveIndex && gameSaveTile.isPressed(point)) {
                Game game = this.B;
                game.vibrate(game.BUTTON_VIBRATE);
                this.B.sounds.playBoxPressSound();
                if (i < 6) {
                    sendDB(i);
                } else {
                    sendExternalStorageDB();
                }
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int N(boolean z) {
        Date date = null;
        int i = -1;
        for (int i2 = 0; i2 < 6; i2++) {
            if (z || (i2 != 0 && i2 != 1)) {
                if (date == null) {
                    try {
                        date = new SimpleDateFormat("MMM dd yyyy hh:mm:ss a", Locale.US).parse(getPreviewData(i2).saveTimeStamp);
                        i = i2;
                    } catch (Exception unused) {
                        i = -1;
                    }
                } else {
                    try {
                        Date parse = new SimpleDateFormat("MMM dd yyyy hh:mm:ss a", Locale.US).parse(getPreviewData(i2).saveTimeStamp);
                        if (parse.compareTo(date) > 0) {
                            i = i2;
                            date = parse;
                        }
                    } catch (Exception unused2) {
                        Log.message("Load/Save", "Crash when comparing dates");
                    }
                }
            }
        }
        if (i == -1) {
            if (z) {
                for (int i3 = 0; i3 < 6; i3++) {
                    if (this.B.doesSaveExists(this.J[i3])) {
                        return i3;
                    }
                }
                return -1;
            }
            return 2;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void O(boolean z) {
        int[] iArr;
        this.load = z;
        SavedGameDetails savedGameDetails = this.B.getSavedGameDetails();
        this.previewDataList.clear();
        for (int i : this.J) {
            if (this.B.doesSaveExists(i)) {
                try {
                    this.previewDataList.add(savedGameDetails.getPreviewData(i));
                } catch (Exception unused) {
                    Log.message("LoadSave", "Crashed getting internal preview data");
                    this.previewDataList.add(new PreviewData());
                }
            } else {
                this.previewDataList.add(new PreviewData());
            }
        }
    }

    protected void P(ExtendedScene extendedScene, Object obj) {
        float f2;
        if (extendedScene instanceof MenuScene) {
            this.B.menuScene.openMenu();
        } else if (extendedScene instanceof GalaxyScene) {
            int i = 0;
            Game game = this.B;
            PlayerSettings playerSettings = game.playerSettings.get(Integer.valueOf(game.getCurrentPlayer()));
            float f3 = 0.0f;
            if (playerSettings != null) {
                f3 = playerSettings.getGalaxyX();
                int zoomLevel = playerSettings.getZoomLevel();
                f2 = playerSettings.getGalaxyY();
                i = zoomLevel;
            } else {
                f2 = 0.0f;
            }
            this.B.galaxyScene.setZoom(i);
            this.B.galaxyScene.L(f3, f2);
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof TurnScene) {
            this.B.turnScene.set();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        closeScene();
        changeScene(this.B.menuScene);
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
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        this.title = G(0.0f, 0.0f, this.B.fonts.menuFont, this.D, this.E, vertexBufferObjectManager, true);
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.MENU.ordinal(), true);
        this.menuButton = H;
        s(H);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, true);
        this.selected = E;
        E.setSize(550.0f, 80.0f);
        Sprite E2 = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.pressed = E2;
        E2.setSize(550.0f, 80.0f);
        this.gameSaveTiles[0] = new GameSaveTile(50, 86, vertexBufferObjectManager, this.B);
        this.gameSaveTiles[0].setBackground(1);
        this.gameSaveTiles[0].setSaveName(this.B.getActivity().getString(R.string.save_autosave));
        attachChild(this.gameSaveTiles[0]);
        this.gameSaveTiles[1] = new GameSaveTile(50, 171, vertexBufferObjectManager, this.B);
        this.gameSaveTiles[1].setBackground(1);
        this.gameSaveTiles[1].setSaveName(this.B.getActivity().getString(R.string.save_autosave2));
        attachChild(this.gameSaveTiles[1]);
        this.gameSaveTiles[2] = new GameSaveTile(50, 256, vertexBufferObjectManager, this.B);
        this.gameSaveTiles[2].setBackground(2);
        this.gameSaveTiles[2].setSaveName(this.B.getActivity().getString(R.string.save_save1));
        attachChild(this.gameSaveTiles[2]);
        this.gameSaveTiles[3] = new GameSaveTile(50, 341, vertexBufferObjectManager, this.B);
        this.gameSaveTiles[3].setBackground(2);
        this.gameSaveTiles[3].setSaveName(this.B.getActivity().getString(R.string.save_save2));
        attachChild(this.gameSaveTiles[3]);
        this.gameSaveTiles[4] = new GameSaveTile(50, 426, vertexBufferObjectManager, this.B);
        this.gameSaveTiles[4].setBackground(2);
        this.gameSaveTiles[4].setSaveName(this.B.getActivity().getString(R.string.save_save3));
        attachChild(this.gameSaveTiles[4]);
        this.gameSaveTiles[5] = new GameSaveTile(50, FrameMetricsAggregator.EVERY_DURATION, vertexBufferObjectManager, this.B);
        this.gameSaveTiles[5].setBackground(2);
        this.gameSaveTiles[5].setSaveName(this.B.getActivity().getString(R.string.save_save4));
        attachChild(this.gameSaveTiles[5]);
        this.gameSaveTiles[6] = new GameSaveTile(50, 596, vertexBufferObjectManager, this.B);
        this.gameSaveTiles[6].setBackground(5);
        this.gameSaveTiles[6].setSaveName(this.B.getActivity().getString(R.string.save_external));
        attachChild(this.gameSaveTiles[6]);
        GameSavePreviewImage gameSavePreviewImage = new GameSavePreviewImage(this.B, vertexBufferObjectManager);
        this.gameSavePreviewImage = gameSavePreviewImage;
        gameSavePreviewImage.setScaleCenter(0.0f, 0.0f);
        attachChild(this.gameSavePreviewImage);
        if (getWidth() == 1480.0f) {
            this.gameSavePreviewImage.setScale(0.65f);
            this.gameSavePreviewImage.setPosition(625.0f, 125.0f);
            Sprite E3 = E(605.0f, 105.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.topBlackedBackground = E3;
            E3.setSize(872.0f, 20.0f);
            Sprite E4 = E(605.0f, 593.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.bottomBlackedBackground = E4;
            E4.setSize(872.0f, 50.0f);
            Sprite E5 = E(605.0f, 125.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.leftBlackedBackground = E5;
            E5.setSize(20.0f, 468.0f);
            Sprite E6 = E(1457.0f, 125.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.rightBlackedBackground = E6;
            E6.setSize(20.0f, 468.0f);
        } else {
            this.gameSavePreviewImage.setScale(0.5f);
            this.gameSavePreviewImage.setPosition(625.0f, 200.0f);
            Sprite E7 = E(605.0f, 180.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.topBlackedBackground = E7;
            E7.setSize(680.0f, 20.0f);
            Sprite E8 = E(605.0f, 560.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.bottomBlackedBackground = E8;
            E8.setSize(680.0f, 50.0f);
            Sprite E9 = E(605.0f, 200.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.leftBlackedBackground = E9;
            E9.setSize(20.0f, 360.0f);
            Sprite E10 = E(1245.0f, 200.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.rightBlackedBackground = E10;
            E10.setSize(20.0f, 360.0f);
        }
        this.gameSavePreviewImage.setVisible(false);
        TiledSprite H2 = H(getWidth() - 300.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
        this.loadSaveButton = H2;
        H2.setSize(300.0f, 75.0f);
        this.loadSaveButton.setAlpha(0.7f);
        this.loadSaveButton.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.loadSaveButton.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(1.0f, 0.6f, 0.7f), new AlphaModifier(1.0f, 0.7f, 0.6f))));
        Sprite sprite = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.loadSaveButtonPressed = sprite;
        sprite.setVisible(false);
        this.loadSaveButtonPressed.setSize(296.0f, 71.0f);
        this.loadSaveButton.attachChild(this.loadSaveButtonPressed);
        Text text = new Text(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.loadSaveText = text;
        text.setColor(Color.BLACK);
        this.loadSaveButton.attachChild(this.loadSaveText);
        TiledSprite H3 = H(605.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 0, true);
        this.deleteButton = H3;
        H3.setSize(300.0f, 75.0f);
        Sprite sprite2 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.deleteButtonPressed = sprite2;
        sprite2.setVisible(false);
        this.deleteButtonPressed.setSize(296.0f, 71.0f);
        this.deleteButton.attachChild(this.deleteButtonPressed);
        Game game = this.B;
        Text text2 = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.save_delete), this.E, vertexBufferObjectManager);
        this.deleteText = text2;
        text2.setX(150.0f - (text2.getWidthScaled() / 2.0f));
        Text text3 = this.deleteText;
        text3.setY(37.0f - (text3.getHeightScaled() / 2.0f));
        this.deleteText.setColor(Color.WHITE);
        this.deleteButton.attachChild(this.deleteText);
        this.confirmOverlay = new ConfirmOverlay(this.B, vertexBufferObjectManager);
    }

    public void deleteSave(int i) {
        this.B.getDatabase().deleteSave(i);
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= 6) {
                break;
            } else if (this.B.doesSaveExists(this.J[i2])) {
                setSelected(i2);
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            set();
            return;
        }
        closeScene();
        changeScene(this.B.menuScene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
    }

    public void goBackToGame() {
        for (Empire empire : this.B.empires.getEmpires()) {
            this.B.graphics.setShipIconsTextures(empire.id, empire.getShipStyleID(), this.B.getActivity());
        }
        closeScene();
        if (this.B.getCurrentPlayer() == -1) {
            changeScene(this.B.turnScene);
        } else {
            changeScene(this.B.galaxyScene);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.r
            @Override // java.lang.Runnable
            public final void run() {
                LoadSaveScene.this.lambda$releasePoolElements$2(extendedScene, obj);
            }
        });
    }

    public void set() {
        GameSaveTile[] gameSaveTileArr;
        this.gameSavePreviewImage.getPoolElements();
        this.nebulas.setRandomSystem();
        if (this.load) {
            this.title.setText(this.B.getActivity().getString(R.string.save_load_game));
            this.loadSaveText.setText(this.B.getActivity().getString(R.string.menu_load));
        } else {
            this.title.setText(this.B.getActivity().getString(R.string.save_save_game));
            this.loadSaveText.setText(this.B.getActivity().getString(R.string.menu_save));
        }
        Text text = this.loadSaveText;
        text.setX(150.0f - (text.getWidthScaled() / 2.0f));
        Text text2 = this.loadSaveText;
        text2.setY(37.0f - (text2.getHeightScaled() / 2.0f));
        this.title.setX((getWidth() / 2.0f) - (this.title.getWidthScaled() / 2.0f));
        Text text3 = this.title;
        text3.setY(43.0f - (text3.getHeightScaled() / 2.0f));
        for (GameSaveTile gameSaveTile : this.gameSaveTiles) {
            gameSaveTile.hideInfo();
        }
        int i = 0;
        for (GameSaveTile gameSaveTile2 : this.gameSaveTiles) {
            gameSaveTile2.enable();
            if (i == 6) {
                try {
                    setExternalStorageSaveTile();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                try {
                    if (this.B.doesSaveExists(this.J[i])) {
                        gameSaveTile2.setTimeStamp(getPreviewData(i).saveTimeStamp);
                        gameSaveTile2.setTurnCountAndTimePlayed(getPreviewData(i).turns, getPreviewData(i).playTime / 1000);
                        gameSaveTile2.setDifficulty(getPreviewData(i).difficulty);
                        int i2 = getPreviewData(i).currentPlayer;
                        if (i2 != -1) {
                            gameSaveTile2.setEmpireBanner(i2, getPreviewData(i).bannerID);
                        }
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                if (this.load) {
                    if (!this.B.doesSaveExists(this.J[i])) {
                        this.gameSaveTiles[i].disable();
                    }
                } else if (i == 0 || i == 1) {
                    gameSaveTile2.disable();
                }
                i++;
            }
        }
        setNewestSelected(this.load);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
        this.loadPressed = false;
        this.loadSaveButton.setVisible(true);
        this.loadSaveText.setVisible(true);
        this.loadSaveButtonPressed.setVisible(false);
        this.loadSaveText.setColor(Color.BLACK);
        openScene();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        super.update();
    }
}
