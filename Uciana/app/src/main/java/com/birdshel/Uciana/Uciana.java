package com.birdshel.Uciana;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Planets.CityLights;
import com.birdshel.Uciana.Resources.SupportedLocales;
import com.birdshel.Uciana.Scenes.LoadSaveScene;
import com.birdshel.Uciana.Scenes.OptionsScene;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Utility.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;

import java.util.Locale;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Uciana extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private Camera camera;
    private Sprite fade;
    public ITextureRegion fadeBackgroundTexture;
    private Game game;
    private SharedPreferences googleAPI;
    private boolean isSignedIn = false;
    private GoogleApiClient mGoogleApiClient;
    private int width;

    private ScreenOrientation getDesiredOrientation() {
        if (getSystemLanguage().equals(getLocale())) {
            return ScreenOrientation.LANDSCAPE_SENSOR;
        }
        if (((WindowManager) getSystemService("window")).getDefaultDisplay().getOrientation() != 1) {
         /*   *//** The app will be fixed in its default Landscape mode. *//*
            LANDSCAPE_FIXED,
                    *//** The app will automatically rotate between the Landscape modes, depending on the orientation of the device. *//*
                    LANDSCAPE_SENSOR,
                    *//** The app will be fixed in its default Portrait mode. *//*
                    PORTRAIT_FIXED,
                    *//** The app will automatically rotate between the Portrait modes, depending on the orientation of the device. *//*
                    PORTRAIT_SENSOR;*/
            return ScreenOrientation.LANDSCAPE_FIXED;
        }
        return ScreenOrientation.LANDSCAPE_FIXED;
    }

    private String getLocale() {
        String string = this.googleAPI.getString("locale", "default");
        if (!string.equals("jp")) {
            return string.equals("default") ? getSystemLanguage() : string;
        }
        setLocale("ja");
        return "ja";
    }

    private void requestReadPermission(int[] iArr) {
        if (iArr.length <= 0 || iArr[0] != 0) {
            return;
        }
        if (this.game.getCurrentScene() instanceof LoadSaveScene) {
            this.game.loadSaveScene.set();
        } else if (this.game.getCurrentScene() instanceof OptionsScene) {
            this.game.optionsScene.changeModdingSetting();
        }
    }

    private void requestWritePermission(int[] iArr) {
        if (iArr.length <= 0 || iArr[0] != 0) {
            return;
        }
        this.game.saveToStorage();
        this.game.loadSaveScene.goBackToGame();
    }

    private void setImmersiveMode() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    private void setWidthBasedOnScreenRatio() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.width = 1280;
        if (point.x / point.y >= 1.86d) {
            this.width = 1480;
        }
    }

    @Override // org.andengine.ui.activity.BaseGameActivity
    protected void d() {
        RenderSurfaceView renderSurfaceView = new RenderSurfaceView(this);
        this.mRenderSurfaceView = renderSurfaceView;
        renderSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 24, 0);
        this.mRenderSurfaceView.setRenderer(this.getEngine(), this);
        this.mRenderSurfaceView.getHolder().setFormat(1);
        setContentView(this.mRenderSurfaceView, BaseGameActivity.b());
    }

    public GoogleApiClient getGoogleApiClient() {
        return this.mGoogleApiClient;
    }

    public String getSystemLanguage() {
        return Resources.getSystem().getConfiguration().locale.getLanguage();
    }

    public boolean isSignedIn() {
        return this.isSignedIn;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 5001 && i2 == 10001) {
            signOutOfGooglePlay();
        } else if (i == 1 && i2 == -1) {
            this.mGoogleApiClient.connect();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getWindow().setFormat(1);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        this.isSignedIn = true;
        this.googleAPI.edit().putInt("connect", 1).apply();
        Game game = this.game;
        if (game != null) {
            game.menuScene.setAchievementsButtonVisibility(true);
            this.game.optionsScene.changeGooglePlayGameButton(true);
            return;
        }
        Log.message("Play Services", "Connected but Game object not ready");
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.isSignedIn = false;
        Game game = this.game;
        if (game != null) {
            game.menuScene.setAchievementsButtonVisibility(false);
        }
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, 1);
            } catch (IntentSender.SendIntentException unused) {
                Log.message("Play Services", "Connection Failed");
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
    }

    @Override // org.andengine.ui.activity.BaseGameActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setLocale();
        super.onCreate(bundle);
        this.mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Games.API).addScope(Games.SCOPE_GAMES).build();
    }

    @Override // org.andengine.ui.IGameInterface
    public EngineOptions onCreateEngineOptions() {
        setWidthBasedOnScreenRatio();
        this.camera = new Camera(0.0f, 0.0f, this.width, 720.0f);
        EngineOptions engineOptions = new EngineOptions(true, getDesiredOrientation(), new RatioResolutionPolicy(this.width, 720.0f), this.camera);
        engineOptions.getRenderOptions().setDithering(true);
        engineOptions.getRenderOptions().setMultiSampling(true);
        engineOptions.getAudioOptions().setNeedsSound(true);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        return engineOptions;
    }

    @Override // org.andengine.ui.IGameInterface
    public void onCreateResources(IGameInterface.OnCreateResourcesCallback onCreateResourcesCallback) {
        Game game = new Game(this, this.getEngine(), this.camera, this.width, SupportedLocales.convertFromString(getLocale()));
        this.game = game;
        game.e();
        onCreateResourcesCallback.onCreateResourcesFinished();
        this.game.empires.setValues();
    }

    @Override // org.andengine.ui.IGameInterface
    public void onCreateScene(IGameInterface.OnCreateSceneCallback onCreateSceneCallback) {
        BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 16, 16, TextureOptions.DEFAULT);
        bitmapTextureAtlas.addEmptyTextureAtlasSource(0, 0, 16, 16);
        this.fadeBackgroundTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this, "FadeBackground.png", 0, 0);
        bitmapTextureAtlas.load();
        onCreateSceneCallback.onCreateSceneFinished(this.game.c());
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            Game game = this.game;
            if (game == null || !game.back()) {
                return true;
            }
            this.game.exit();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.andengine.ui.activity.BaseGameActivity, android.app.Activity
    public void onPause() {
        if (Integer.parseInt(Character.toString(Build.VERSION.RELEASE.charAt(0))) > 2) {
            this.b.setPreserveEGLContextOnPause(true);
        }
        Game game = this.game;
        if (game != null) {
            game.f();
        }
        this.getEngine().stop();
        super.onPause();
    }

    @Override // org.andengine.ui.IGameInterface
    public void onPopulateScene(Scene scene, IGameInterface.OnPopulateSceneCallback onPopulateSceneCallback) {
        this.getEngine().registerUpdateHandler(new TimerHandler(1.0f, new ITimerCallback() { // from class: com.birdshel.Uciana.Uciana.1
            {
                Uciana.this = this;
            }

            @Override // org.andengine.engine.handler.timer.ITimerCallback
            public void onTimePassed(TimerHandler timerHandler) {
                ((BaseGameActivity) Uciana.this).getEngine().unregisterUpdateHandler(timerHandler);
                com.birdshel.Uciana.Planets.Resources.set();
                Buildings.set();
                ShipComponents.set();
                CityLights.set();
                Uciana.this.game.d();
                Uciana.this.game.b();
                Uciana.this.game.menuScene.getPoolElements();
                Uciana.this.game.menuScene.openMenu();
                Uciana.this.game.music.resume();
                Uciana uciana = Uciana.this;
                Uciana uciana2 = Uciana.this;
                uciana.fade = new Sprite(0.0f, 0.0f, uciana2.fadeBackgroundTexture, uciana2.getVertexBufferObjectManager());
                Uciana.this.fade.setAlpha(0.0f);
                Uciana.this.fade.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
                ((BaseGameActivity) Uciana.this).engine.getScene().attachChild(Uciana.this.fade);
                Uciana.this.fade.registerEntityModifier(new AlphaModifier(0.25f, 0.0f, 1.0f) { // from class: com.birdshel.Uciana.Uciana.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // org.andengine.util.modifier.BaseModifier
                    /* renamed from: k */
                    public void c(IEntity iEntity) {
                        super.c(iEntity);
                        Uciana.this.fade.setAlpha(1.0f);
                        Uciana.this.game.setCurrentScene(Uciana.this.game.menuScene);
                        Uciana.this.game.menuScene.fadeIn();
                        if (Uciana.this.googleAPI.getInt("connect", 2) == 2) {
                            Uciana.this.googleAPI.edit().putInt("connect", 0).apply();
                            Uciana.this.mGoogleApiClient.connect();
                        }
                    }
                });
            }
        }));
        onPopulateSceneCallback.onPopulateSceneFinished();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            requestWritePermission(iArr);
        } else if (i != 2) {
        } else {
            requestReadPermission(iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.andengine.ui.activity.BaseGameActivity, android.app.Activity
    public void onResume() {
        setLocale();
        Game game = this.game;
        if (game != null) {
            game.g();
        }
        this.engine.start();
        setImmersiveMode();
        super.onResume();
    }

    @Override // org.andengine.ui.activity.BaseGameActivity, org.andengine.ui.IGameInterface
    public synchronized void onResumeGame() {
        if (this.engine != null) {
            super.onResumeGame();
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("googleAPI", 0);
        this.googleAPI = sharedPreferences;
        if (sharedPreferences.getInt("connect", 0) == 1) {
            this.mGoogleApiClient.connect();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        finishActivity(5001);
        super.onStop();
        if (this.mGoogleApiClient.isConnected()) {
            this.mGoogleApiClient.disconnect();
            this.isSignedIn = false;
        }
    }

    @Override // org.andengine.ui.activity.BaseGameActivity, android.app.Activity, android.view.Window.Callback
    public synchronized void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            setImmersiveMode();
        }
    }

    public void setLocale() {
        this.googleAPI = getApplicationContext().getSharedPreferences("googleAPI", 0);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        String locale = getLocale();
        if (SupportedLocales.contains(locale)) {
            Locale locale2 = new Locale(locale);
            configuration.locale = locale2;
            Locale.setDefault(locale2);
            if (Build.VERSION.SDK_INT >= 17) {
                configuration.setLayoutDirection(configuration.locale);
            }
            getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    public void signOutOfGooglePlay() {
        this.isSignedIn = false;
        this.mGoogleApiClient.disconnect();
        this.googleAPI.edit().putInt("connect", 0).apply();
        this.game.menuScene.setAchievementsButtonVisibility(false);
        this.game.optionsScene.changeGooglePlayGameButton(false);
    }

    public void setLocale(String str) {
        this.googleAPI.edit().putString("locale", str).apply();
    }
}
