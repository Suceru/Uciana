package com.birdshel.Uciana.Scenes;

import android.os.AsyncTask;
import android.os.Environment;

import androidx.core.content.ContextCompat;

import com.birdshel.Uciana.BuildConfig;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.Messages.Tutorials.FirstTimeTutorial;
import com.birdshel.Uciana.Overlays.BirdshelOverlay;
import com.birdshel.Uciana.Overlays.LanguageSelectOverlay;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.Options;
import com.birdshel.Uciana.Resources.SupportedLocales;
import com.birdshel.Uciana.Resources.TutorialID;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarType;
import com.birdshel.Uciana.StarSystems.SunSprite;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.io.File;
import java.util.Locale;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MenuScene extends ExtendedScene {
    private TiledSprite achievementsButton;
    private TiledSprite birdshelButton;
    private BirdshelOverlay birdshelOverlay;
    private Text continueLabel;
    private Text exitLabel;
    private TiledSprite flag;
    private TiledSprite languageButton;
    private Text languageButtonText;
    private LanguageSelectOverlay languageSelectOverlay;
    private Text loadLabel;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private Text newLabel;
    private Text optionsLabel;
    private PlanetSprite planetSprite;
    private Text saveLabel;
    private SunSprite sunSprite;
    private Text versionNumber;
    private boolean scenePressed = false;
    private boolean finishedLoadingData = false;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.MenuScene$2 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1467a;

        static {
            int[] iArr = new int[Climate.values().length];
            f1467a = iArr;
            try {
                iArr[Climate.POLLUTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1467a[Climate.RING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1467a[Climate.BROKEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public MenuScene(Game game) {
        this.B = game;
    }

    private void achievementsButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.getActivity().runOnUiThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.t
            {
                MenuScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                MenuScene.this.lambda$achievementsButtonPressed$4();
            }
        });
    }

    private void birdshelButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        setChildScene(this.birdshelOverlay, false, false, true);
    }

    private void checkActionDown(Point point) {
        checkPress(point);
    }

    private void checkActionMove(Point point) {
        resetPress();
        checkPress(point);
    }

    private void checkActionUp(Point point) {
        resetPress();
        if (y(this.continueLabel, point) && !this.scenePressed) {
            continueButtonPressed();
        }
        if (y(this.newLabel, point) && !this.scenePressed) {
            newButtonPressed();
        }
        if (y(this.loadLabel, point) && !this.scenePressed) {
            loadButtonPressed();
        }
        if (y(this.saveLabel, point) && !this.scenePressed) {
            saveButtonPressed();
        }
        if (y(this.optionsLabel, point) && !this.scenePressed) {
            optionsButtonPressed();
        }
        if (y(this.exitLabel, point)) {
            exitButtonPressed();
        }
        if (isClicked(this.languageButton, point)) {
            languageButtonPressed();
        }
        if (isClicked(this.achievementsButton, point)) {
            achievementsButtonPressed();
        }
        if (y(this.versionNumber, point)) {
            versionNumberPressed();
        }
        if (isClicked(this.birdshelButton, point)) {
            birdshelButtonPressed();
        }
    }

    private void checkPress(Point point) {
        if (y(this.continueLabel, point) && !this.scenePressed) {
            this.continueLabel.setScale(1.3f);
        }
        if (y(this.newLabel, point) && !this.scenePressed) {
            this.newLabel.setScale(1.3f);
        }
        if (y(this.loadLabel, point) && !this.scenePressed) {
            this.loadLabel.setScale(1.3f);
        }
        if (y(this.saveLabel, point) && !this.scenePressed) {
            this.saveLabel.setScale(1.3f);
        }
        if (y(this.optionsLabel, point) && !this.scenePressed) {
            this.optionsLabel.setScale(1.3f);
        }
        if (y(this.exitLabel, point)) {
            this.exitLabel.setScale(1.3f);
        }
    }

    private void closeMenu() {
        this.planetSprite.registerEntityModifier(new MoveModifier(0.2f, getWidth() - 280.0f, getWidth() + 520.0f, 360.0f, 360.0f));
        this.sunSprite.registerEntityModifier(new MoveModifier(0.1f, 0.0f, -100.0f, 0.0f, 0.0f));
    }

    private void continueButtonPressed() {
        if (this.B.galaxy.getStarSystems().isEmpty()) {
            this.scenePressed = true;
            AsyncTask.execute(new Runnable() { // from class: com.birdshel.Uciana.Scenes.v
                {
                    MenuScene.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    MenuScene.this.lambda$continueButtonPressed$0();
                }
            });
        } else {
            setContinueScene();
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void exitButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.planetSprite.registerEntityModifier(new MoveModifier(0.2f, getWidth() - 280.0f, getWidth() + 520.0f, 360.0f, 360.0f) { // from class: com.birdshel.Uciana.Scenes.MenuScene.1
            {
                MenuScene.this = this;
            }

            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: m */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                MenuScene.this.B.exit();
            }
        });
        this.sunSprite.registerEntityModifier(new MoveModifier(0.1f, 0.0f, -100.0f, 0.0f, 0.0f));
    }

    public /* synthetic */ void lambda$achievementsButtonPressed$3() {
        if (this.B.getActivity().isSignedIn()) {
            GoogleApiClient googleApiClient = this.B.getActivity().getGoogleApiClient();
            if (googleApiClient.isConnected()) {
                this.B.getActivity().startActivityForResult(Games.Achievements.getAchievementsIntent(googleApiClient), 5001);
            } else {
                googleApiClient.connect();
            }
        }
    }

    public /* synthetic */ void lambda$achievementsButtonPressed$4() {
        this.B.getActivity().runOnUiThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.s
            {
                MenuScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                MenuScene.this.lambda$achievementsButtonPressed$3();
            }
        });
    }

    public /* synthetic */ void lambda$continueButtonPressed$0() {
        this.B.loadSaveScene.O(true);
        this.B.techScene.resetLists();
        int N = this.B.loadSaveScene.N(true);
        if (N != -1) {
            Game game = this.B;
            game.load(game.loadSaveScene.J[N]);
            closeMenu();
            this.B.setGameEnded(false);
            setContinueScene();
            this.B.startTime = System.currentTimeMillis();
            this.scenePressed = false;
        }
    }

    public /* synthetic */ void lambda$loadButtonPressed$1() {
        this.B.loadSaveScene.O(true);
        this.finishedLoadingData = true;
        closeMenu();
    }

    public /* synthetic */ void lambda$releasePoolElements$5(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        detachChild(this.planetSprite);
        this.B.planetSpritePool.push(this.planetSprite);
        extendedScene.getPoolElements();
        Q(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    public /* synthetic */ void lambda$saveButtonPressed$2() {
        this.B.loadSaveScene.O(false);
        this.finishedLoadingData = true;
        closeMenu();
    }

    private void languageButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        setChildScene(this.languageSelectOverlay, false, false, true);
    }

    private void loadButtonPressed() {
        this.scenePressed = true;
        AsyncTask.execute(new Runnable() { // from class: com.birdshel.Uciana.Scenes.u
            {
                MenuScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                MenuScene.this.lambda$loadButtonPressed$1();
            }
        });
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void newButtonPressed() {
        closeMenu();
        changeScene(this.B.setupScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void optionsButtonPressed() {
        closeMenu();
        changeScene(this.B.optionsScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void resetPress() {
        this.continueLabel.setScale(1.8f);
        this.newLabel.setScale(1.8f);
        this.loadLabel.setScale(1.8f);
        this.saveLabel.setScale(1.8f);
        this.optionsLabel.setScale(1.8f);
        this.exitLabel.setScale(1.8f);
    }

    private void saveButtonPressed() {
        this.scenePressed = true;
        AsyncTask.execute(new Runnable() { // from class: com.birdshel.Uciana.Scenes.w
            {
                MenuScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                MenuScene.this.lambda$saveButtonPressed$2();
            }
        });
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setContinueScene() {
        closeMenu();
        for (Empire empire : this.B.empires.getEmpires()) {
            this.B.graphics.setShipIconsTextures(empire.id, empire.getShipStyleID(), this.B.getActivity());
        }
        if (this.B.getCurrentPlayer() == -1) {
            changeScene(this.B.turnScene);
        } else {
            changeScene(this.B.galaxyScene);
        }
    }

    private void setLanguageButton() {
        String letters = this.B.getLocale().getLetters();
        Locale locale = new Locale(letters);
        this.flag.setCurrentTileIndex(InfoIconEnum.getLangIcon(letters));
        this.languageButtonText.setText(locale.getLanguage().split("_")[0]);
        Text text = this.languageButtonText;
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x005e, code lost:
        if (new java.io.File(r1.getAbsolutePath() + java.io.File.separator + "save").exists() != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void setTheLoadButton() {
        boolean z;
        boolean z2 = false;
        int i = 0;
        while (true) {
            z = true;
            if (i >= 6) {
                break;
            } else if (this.B.doesSaveExists(i)) {
                z2 = true;
                break;
            } else {
                i++;
            }
        }
        if (ContextCompat.checkSelfPermission(this.B.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            File file = new File(Environment.getExternalStorageDirectory() + "/uciana");
        }
        z = z2;
        if (z) {
            this.loadLabel.setAlpha(1.0f);
        } else {
            this.loadLabel.setAlpha(0.4f);
        }
    }

    private void versionNumberPressed() {
        showMessage(new TextMessage("28006"));
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void Q(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof TurnScene) {
            this.B.turnScene.set();
        } else if (extendedScene instanceof LoadSaveScene) {
            this.B.loadSaveScene.set();
        }
    }

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
        SunSprite sunSprite = new SunSprite(this.B, vertexBufferObjectManager, true);
        this.sunSprite = sunSprite;
        attachChild(sunSprite);
        Game game = this.B;
        Text F = F(250.0f, 119.0f, game.fonts.menuFont, game.getActivity().getString(R.string.menu_continue), this.E, vertexBufferObjectManager);
        this.continueLabel = F;
        F.setScaleCenter(0.0f, 0.0f);
        Game game2 = this.B;
        Text F2 = F(250.0f, 205.0f, game2.fonts.menuFont, game2.getActivity().getString(R.string.menu_new), this.E, vertexBufferObjectManager);
        this.newLabel = F2;
        F2.setScaleCenter(0.0f, 0.0f);
        Game game3 = this.B;
        Text F3 = F(250.0f, 291.0f, game3.fonts.menuFont, game3.getActivity().getString(R.string.menu_load), this.E, vertexBufferObjectManager);
        this.loadLabel = F3;
        F3.setScaleCenter(0.0f, 0.0f);
        Game game4 = this.B;
        Text F4 = F(250.0f, 377.0f, game4.fonts.menuFont, game4.getActivity().getString(R.string.menu_save), this.E, vertexBufferObjectManager);
        this.saveLabel = F4;
        F4.setScaleCenter(0.0f, 0.0f);
        Game game5 = this.B;
        Text F5 = F(250.0f, 463.0f, game5.fonts.menuFont, game5.getActivity().getString(R.string.menu_options), this.E, vertexBufferObjectManager);
        this.optionsLabel = F5;
        F5.setScaleCenter(0.0f, 0.0f);
        Game game6 = this.B;
        Text F6 = F(250.0f, 549.0f, game6.fonts.menuFont, game6.getActivity().getString(R.string.menu_exit), this.E, vertexBufferObjectManager);
        this.exitLabel = F6;
        F6.setScaleCenter(0.0f, 0.0f);
        resetPress();
        float width = getWidth() - 120.0f;
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK;
        TiledSprite H = H(width, 0.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.languageButton = H;
        s(H);
        Text text = new Text(20.0f, 0.0f, this.B.fonts.infoFont, "##", this.E, vertexBufferObjectManager);
        this.languageButtonText = text;
        this.languageButton.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(60.0f, 28.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.flag = tiledSprite;
        tiledSprite.setCurrentTileIndex(InfoIconEnum.ENGLISH.ordinal());
        this.languageButton.attachChild(this.flag);
        TiledSprite H2 = H(getWidth() - 120.0f, 86.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.achievementsButton = H2;
        s(H2);
        TiledSprite tiledSprite2 = new TiledSprite(35.0f, 18.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite2.setSize(50.0f, 50.0f);
        tiledSprite2.setCurrentTileIndex(GameIconEnum.ACHIEVEMENTS.ordinal());
        this.achievementsButton.attachChild(tiledSprite2);
        TiledSprite H3 = H(getWidth() - 120.0f, 172.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.BIRDSHEL.ordinal(), true);
        this.birdshelButton = H3;
        s(H3);
        this.birdshelButton.setVisible(this.B.getLocale() == SupportedLocales.ENGLISH);
        Game game7 = this.B;
        this.versionNumber = F(0.0f, 0.0f, game7.fonts.smallInfoFont, game7.getActivity().getString(R.string.menu_build, new Object[]{BuildConfig.VERSION_NAME}), this.E, vertexBufferObjectManager);
        this.versionNumber.setPosition((getWidth() - this.versionNumber.getWidthScaled()) - (getWidth() == 1480.0f ? 30.0f : 0.0f), 710.0f - this.versionNumber.getHeightScaled());
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
        this.languageSelectOverlay = new LanguageSelectOverlay(this.B, vertexBufferObjectManager);
        this.birdshelOverlay = new BirdshelOverlay(this.B, vertexBufferObjectManager);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void fadeIn() {
        super.fadeIn();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.planetSprite = planetSprite;
        planetSprite.setMoonRange(600, 600);
        this.nebulaBackground.attachChild(this.planetSprite);
    }

    public void openMenu() {
        float f2;
        float f3;
        setLanguageButton();
        this.nebulas.setRandomSystem();
        this.sunSprite.set(StarType.values()[Functions.random.nextInt(StarType.values().length)]);
        this.sunSprite.registerEntityModifier(new MoveModifier(0.2f, -100.0f, 0.0f, 0.0f, 0.0f));
        Planet buildRandomPlanet = new Planet.Builder().buildRandomPlanet();
        this.planetSprite.setSpritesInvisible();
        this.planetSprite.setPlanet(buildRandomPlanet, buildRandomPlanet.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene));
        if (buildRandomPlanet.getClimate() != Climate.GAS_GIANT) {
            float scale = buildRandomPlanet.getScale(this.B.planetScene);
            int i = AnonymousClass2.f1467a[buildRandomPlanet.getClimate().ordinal()];
            if (i == 1) {
                f2 = 1.56f;
            } else if (i == 2) {
                f2 = 2.88f;
            } else if (i != 3) {
                f3 = scale;
                this.planetSprite.setCityLights(Functions.random.nextInt(125) + 50, buildRandomPlanet.getCityLightIndex(), scale, f3, false);
            } else {
                f2 = 2.5f;
            }
            f3 = f2 * scale;
            this.planetSprite.setCityLights(Functions.random.nextInt(125) + 50, buildRandomPlanet.getCityLightIndex(), scale, f3, false);
        }
        this.planetSprite.registerEntityModifier(new MoveModifier(0.2f, 520.0f + getWidth(), getWidth() - 280.0f, 360.0f, 360.0f));
        if (!this.B.didGameEnd() && (!this.B.galaxy.getStarSystems().isEmpty() || this.B.doesSaveExists(0))) {
            this.continueLabel.setAlpha(1.0f);
        } else {
            this.continueLabel.setAlpha(0.4f);
        }
        if (this.B.galaxy.getStarSystems().isEmpty()) {
            this.saveLabel.setAlpha(0.4f);
        } else {
            this.saveLabel.setAlpha(1.0f);
        }
        setTheLoadButton();
        this.achievementsButton.setVisible(this.B.getActivity().isSignedIn());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.x
            {
                MenuScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                MenuScene.this.lambda$releasePoolElements$5(extendedScene, obj);
            }
        });
    }

    public void setAchievementsButtonVisibility(boolean z) {
        TiledSprite tiledSprite = this.achievementsButton;
        if (tiledSprite == null) {
            return;
        }
        tiledSprite.setVisible(z);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
        Options options = Game.options;
        TutorialID tutorialID = TutorialID.FIRST_TIME;
        if (options.shouldTutorialBeShown(tutorialID) && this.B.getLocale() == SupportedLocales.ENGLISH) {
            showMessage(new FirstTimeTutorial());
            Game.options.markSeen(tutorialID);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        if (this.finishedLoadingData) {
            this.finishedLoadingData = false;
            changeScene(this.B.loadSaveScene);
            this.scenePressed = false;
        }
    }
}
