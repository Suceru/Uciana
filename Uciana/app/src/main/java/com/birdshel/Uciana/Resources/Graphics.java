package com.birdshel.Uciana.Resources;

import android.content.res.AssetManager;
import android.os.Environment;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import androidx.core.view.PointerIconCompat;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.Uciana;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.FileBitmapTextureAtlasSource;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.adt.io.in.IInputStreamOpener;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Graphics {
    public TiledTextureRegion asteroidBeltsTexture;
    public ITextureRegion backgroundTexture;
    public ITextureRegion blackenedBackgroundTexture;
    public ITextureRegion blackholeTexture;
    public TiledTextureRegion bombDamageTexture;
    public TiledTextureRegion buttonsTexture;
    public TiledTextureRegion cityLightsTexture;
    public ITextureRegion colonyBackgroundTexture;
    public ITextureRegion colonySeparatorTexture;
    public TiledTextureRegion cometsTexture;
    public ITextureRegion discoveryTexture;
    public TiledTextureRegion empireColors;
    public TiledTextureRegion explosionTexture;
    public ITextureRegion fadeBackgroundTexture;
    public ITextureRegion farmingBarTexture;
    public TiledTextureRegion gameIconsTexture;
    public ITextureRegion gradientTexture;
    public TiledTextureRegion infoIconsTexture;
    public TiledTextureRegion ionCloudsTexture;
    public TiledTextureRegion ionFlashesTexture;
    private final boolean[] isShipsModded;
    public ITextureRegion labBackgroundTexture;
    public TiledTextureRegion moonsTexture;
    public TiledTextureRegion nebulaTexture;
    public TiledTextureRegion outOfRangeStarTexture;
    public ITextureRegion particleTexture;
    public TiledTextureRegion planetInfoTexture;
    public ITextureRegion planetSurfaceTexture;
    public ITextureRegion popEmptyTexture;
    public ITextureRegion popTexture;
    public ITextureRegion productionBarTexture;
    public ITextureRegion productionButton;
    public TiledTextureRegion productionPercentBarTexture;
    public ITextureRegion raceAmbassadorTexture;
    public ITextureRegion raceExplorerTexture;
    public TiledTextureRegion resourceIconsTexture;
    public TiledTextureRegion ringsTexture;
    public ITextureRegion scienceBarTexture;
    public ITextureRegion selectColonyTexture;
    public ITextureRegion selectedFleetTexture;
    public ITextureRegion shieldTexture;
    public TiledTextureRegion shipComponentIconsTexture;
    public TiledTextureRegion shipDebrisTexture;
    public ITextureRegion sliderTexture;
    public TiledTextureRegion sortButtonsTexture;
    public ITextureRegion spyTexture;
    public TiledTextureRegion starsTexture;
    public ITextureRegion sunTextureRegion;
    public ITextureRegion whiteTexture;
    public TiledTextureRegion[] planetsTextureRegion = new TiledTextureRegion[37];
    public TiledTextureRegion[] shipsTextures = new TiledTextureRegion[10];
    public TiledTextureRegion[] troops = new TiledTextureRegion[7];
    public TextureRegion[] ambassadorIcons = new TextureRegion[7];
    private String surfaceFileName = "";
    private String exploreFileName = "";
    private String discoveryFileName = "";
    private String ambassadorFileName = "";
    private final String[] shipTextureFileNames = new String[8];

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class TiledTextureRegionObject {

        /* renamed from: a */
        TiledTextureRegion f1417a;

        private TiledTextureRegionObject() {
        }
    }

    public Graphics(Uciana uciana) {
        boolean[] zArr = new boolean[10];
        this.isShipsModded = zArr;
        TextureManager textureManager = uciana.getTextureManager();
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx_hd/");
        Arrays.fill(zArr, false);
        if (isModdingAssetAvailable(uciana)) {
            for (int i = 0; i < 10; i++) {
                if (getModdedFile("Ships/" + i + ".png").exists()) {
                    this.isShipsModded[i] = true;
                }
            }
        }
        for (int i2 = 0; i2 < 7; i2++) {
            String str = "Ships/" + i2 + "-" + i2 + ".png";
            if (this.isShipsModded[i2]) {
                str = "Ships/" + i2 + ".png";
            }
            this.shipsTextures[i2] = getTiledTextureFromFileOrAsset(uciana, str, 9, 1);
            this.shipTextureFileNames[i2] = str;
        }
        this.shipsTextures[7] = getTiledTextureFromFileOrAsset(uciana, "Ships/7.png", 9, 1);
        this.shipTextureFileNames[7] = "Ships/7.png";
        this.shipsTextures[8] = getTiledTextureFromFileOrAsset(uciana, "Ships/8.png", 1, 1);
        this.shipsTextures[9] = getTiledTextureFromFileOrAsset(uciana, "Ships/9.png", 1, 1);
        TextureOptions textureOptions = TextureOptions.BILINEAR;
        BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(textureManager, 1024, 1024, textureOptions);
        bitmapTextureAtlas.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        this.buttonsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, uciana, "Buttons.png", 0, 0, 4, 11);
        this.sortButtonsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, uciana, "SortButtons.png", 481, 0, 4, 6);
        this.planetInfoTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, uciana, "PlanetInfo.png", 481, 533, 7, 6);
        this.cometsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, uciana, "Comets.png", 481, 834, 2, 1);
        this.sliderTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, uciana, "Slider.png", 962, 0);
        this.selectedFleetTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, uciana, "SelectedFleet.png", 962, 71);
        this.popTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, uciana, "Pop.png", 962, 110);
        this.particleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, uciana, "WhiteParticle.png", (int) PointerIconCompat.TYPE_GRAB, 51);
        this.spyTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, uciana, "Agent.png", 682, 834);
        bitmapTextureAtlas.load();
        BitmapTextureAtlas bitmapTextureAtlas2 = new BitmapTextureAtlas(textureManager, 512, 1024, textureOptions);
        bitmapTextureAtlas2.addEmptyTextureAtlasSource(0, 0, 512, 1024);
        this.starsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas2, uciana, "Stars.png", 0, 0, 4, 18);
        this.outOfRangeStarTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas2, uciana, "OutOfRangeStar.png", 141, 201, 3, 1);
        this.moonsTexture = setTiledTextureIntoTextureAtlas(uciana, "Moons.png", bitmapTextureAtlas2, 141, 0, 3, 2);
        this.resourceIconsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas2, uciana, "Resources.png", 141, 237, 6, 4);
        this.shipComponentIconsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas2, uciana, "ShipComponentIcons.png", 141, 488, 7, 9);
        this.shipDebrisTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas2, uciana, "ShipDebris.png", 141, 438, 3, 1);
        this.infoIconsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas2, uciana, "InfoIcons.png", 0, 844, 14, 6);
        bitmapTextureAtlas2.load();
        BitmapTextureAtlas bitmapTextureAtlas3 = new BitmapTextureAtlas(textureManager, 1024, 1024, textureOptions);
        bitmapTextureAtlas3.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        this.gameIconsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas3, uciana, "GameIcons.png", 0, 0, 10, 8);
        this.troops[0] = setTiledTextureIntoTextureAtlas(uciana, "Troops/0.png", bitmapTextureAtlas3, 0, 801, 1, 1);
        this.troops[1] = setTiledTextureIntoTextureAtlas(uciana, "Troops/1.png", bitmapTextureAtlas3, 100, 801, 1, 1);
        this.troops[2] = setTiledTextureIntoTextureAtlas(uciana, "Troops/2.png", bitmapTextureAtlas3, WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE, 801, 1, 1);
        this.troops[3] = setTiledTextureIntoTextureAtlas(uciana, "Troops/3.png", bitmapTextureAtlas3, 300, 801, 1, 1);
        this.troops[4] = setTiledTextureIntoTextureAtlas(uciana, "Troops/4.png", bitmapTextureAtlas3, 400, 801, 1, 1);
        this.troops[5] = setTiledTextureIntoTextureAtlas(uciana, "Troops/5.png", bitmapTextureAtlas3, 500, 801, 1, 1);
        this.troops[6] = setTiledTextureIntoTextureAtlas(uciana, "Troops/6.png", bitmapTextureAtlas3, 600, 801, 1, 1);
        this.ambassadorIcons[0] = setTextureIntoTextureAtlas(uciana, "AmbassadorIcons/0.png", bitmapTextureAtlas3, 0, TypedValues.Custom.TYPE_FLOAT);
        this.ambassadorIcons[1] = setTextureIntoTextureAtlas(uciana, "AmbassadorIcons/1.png", bitmapTextureAtlas3, 100, TypedValues.Custom.TYPE_COLOR);
        this.ambassadorIcons[2] = setTextureIntoTextureAtlas(uciana, "AmbassadorIcons/2.png", bitmapTextureAtlas3, WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE, TypedValues.Custom.TYPE_COLOR);
        this.ambassadorIcons[3] = setTextureIntoTextureAtlas(uciana, "AmbassadorIcons/3.png", bitmapTextureAtlas3, 300, TypedValues.Custom.TYPE_COLOR);
        this.ambassadorIcons[4] = setTextureIntoTextureAtlas(uciana, "AmbassadorIcons/4.png", bitmapTextureAtlas3, 400, TypedValues.Custom.TYPE_COLOR);
        this.ambassadorIcons[5] = setTextureIntoTextureAtlas(uciana, "AmbassadorIcons/5.png", bitmapTextureAtlas3, 500, TypedValues.Custom.TYPE_COLOR);
        this.ambassadorIcons[6] = setTextureIntoTextureAtlas(uciana, "AmbassadorIcons/6.png", bitmapTextureAtlas3, 600, TypedValues.Custom.TYPE_COLOR);
        bitmapTextureAtlas3.load();
        BitmapTextureAtlas bitmapTextureAtlas4 = new BitmapTextureAtlas(textureManager, 1024, 1024, textureOptions);
        bitmapTextureAtlas4.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        this.asteroidBeltsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas4, uciana, "AsteroidBelts.png", 0, 0, 3, 1);
        this.productionButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas4, uciana, "ProductionButton.png", 0, 721);
        this.explosionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas4, uciana, "Explosion.png", 0, 822, 9, 1);
        this.shieldTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas4, uciana, "Shield.png", 641, 0);
        this.ionCloudsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas4, uciana, "IonClouds.png", 641, 251, 2, 2);
        this.ionFlashesTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas4, uciana, "IonFlashes.png", 641, 452, 3, 2);
        this.blackholeTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas4, uciana, "Blackhole.png", 892, 0);
        this.gradientTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas4, uciana, "Gradient.png", 0, 923);
        bitmapTextureAtlas4.load();
        BitmapTextureAtlas bitmapTextureAtlas5 = new BitmapTextureAtlas(textureManager, 1024, 1024, textureOptions);
        bitmapTextureAtlas5.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        this.ringsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas5, uciana, "Rings.png", 0, 0, 2, 1);
        this.nebulaTexture = setTiledTextureIntoTextureAtlas(uciana, "Nebula.png", bitmapTextureAtlas5, 0, 376, 3, 2);
        this.cityLightsTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas5, uciana, "LightsSheet.png", 751, 0, 4, 3);
        this.bombDamageTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas5, uciana, "PlanetDamageSheet.png", 751, 187, 4, 2);
        bitmapTextureAtlas5.load();
        BitmapTextureAtlas bitmapTextureAtlas6 = new BitmapTextureAtlas(textureManager, 256, 256, TextureOptions.DEFAULT);
        bitmapTextureAtlas6.addEmptyTextureAtlasSource(0, 0, 256, 256);
        this.empireColors = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas6, uciana, "EmpireColors.png", 0, 0, 11, 1);
        this.farmingBarTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "FarmingBar.png", 0, 92);
        this.productionBarTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "ProductionBar.png", 6, 92);
        this.scienceBarTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "ScienceBar.png", 12, 92);
        this.popEmptyTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "PopEmpty.png", 20, 92);
        this.colonySeparatorTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "ColoniesSeperator.png", 26, 92);
        this.blackenedBackgroundTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "BlackenedBackground.png", 0, 153);
        this.fadeBackgroundTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "FadeBackground.png", 0, 164);
        this.colonyBackgroundTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "ColonyBackground.png", (int) WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE, (int) WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE);
        this.productionPercentBarTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas6, uciana, "ProductionProgressBar.png", 211, 211, 4, 1);
        this.selectColonyTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "ColoniesSelect.png", (int) WeaponStats.SUBSPACE_CHARGE_SPEED, 0);
        this.whiteTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas6, uciana, "MessageBackground.png", 240, 0);
        bitmapTextureAtlas6.load();
        BitmapTextureAtlas bitmapTextureAtlas7 = new BitmapTextureAtlas(textureManager, 1024, 512, textureOptions);
        bitmapTextureAtlas7.addEmptyTextureAtlasSource(0, 0, 1024, 512);
        this.backgroundTexture = setTextureIntoTextureAtlas(uciana, "Backgrounds/Background.png", bitmapTextureAtlas7, 0, 0);
        bitmapTextureAtlas7.load();
        BitmapTextureAtlas bitmapTextureAtlas8 = new BitmapTextureAtlas(textureManager, 1024, 512, textureOptions);
        bitmapTextureAtlas8.addEmptyTextureAtlasSource(0, 0, 1024, 512);
        this.labBackgroundTexture = setTextureIntoTextureAtlas(uciana, "Backgrounds/Lab.png", bitmapTextureAtlas8, 0, 0);
        bitmapTextureAtlas8.load();
        BitmapTextureAtlas bitmapTextureAtlas9 = new BitmapTextureAtlas(textureManager, 256, 1024, textureOptions);
        bitmapTextureAtlas9.addEmptyTextureAtlasSource(0, 0, 256, 1024);
        this.sunTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas9, uciana, "Sun.png", 0, 0);
        bitmapTextureAtlas9.load();
        this.planetSurfaceTexture = getTextureFromFileOrAsset(uciana, "Surfaces/1.png");
        this.raceExplorerTexture = getTextureFromFileOrAsset(uciana, "Explorers/0.png");
        this.discoveryTexture = getTextureFromFileOrAsset(uciana, "Discoveries/1.png");
        this.raceAmbassadorTexture = getTextureFromFileOrAsset(uciana, "Ambassadors/0.png");
        BitmapTextureAtlas bitmapTextureAtlas10 = new BitmapTextureAtlas(textureManager, 1024, 1024, textureOptions);
        bitmapTextureAtlas10.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        this.planetsTextureRegion[0] = setTiledTextureIntoTextureAtlas(uciana, "Planets/0.png", bitmapTextureAtlas10, 0, 0, 3, 1);
        this.planetsTextureRegion[2] = setTiledTextureIntoTextureAtlas(uciana, "Planets/2.png", bitmapTextureAtlas10, 750, 0, 1, 1);
        this.planetsTextureRegion[1] = setTiledTextureIntoTextureAtlas(uciana, "Planets/1.png", bitmapTextureAtlas10, 0, WeaponStats.SUBSPACE_CHARGE_SPEED, 3, 1);
        this.planetsTextureRegion[4] = setTiledTextureIntoTextureAtlas(uciana, "Planets/4.png", bitmapTextureAtlas10, 750, WeaponStats.SUBSPACE_CHARGE_SPEED, 1, 1);
        this.planetsTextureRegion[3] = setTiledTextureIntoTextureAtlas(uciana, "Planets/3.png", bitmapTextureAtlas10, 0, 500, 3, 1);
        this.planetsTextureRegion[6] = setTiledTextureIntoTextureAtlas(uciana, "Planets/6.png", bitmapTextureAtlas10, 750, 500, 1, 1);
        this.planetsTextureRegion[5] = setTiledTextureIntoTextureAtlas(uciana, "Planets/5.png", bitmapTextureAtlas10, 0, 750, 3, 1);
        this.planetsTextureRegion[10] = setTiledTextureIntoTextureAtlas(uciana, "Planets/10.png", bitmapTextureAtlas10, 750, 750, 1, 1);
        bitmapTextureAtlas10.load();
        BitmapTextureAtlas bitmapTextureAtlas11 = new BitmapTextureAtlas(textureManager, 1024, 1024, textureOptions);
        bitmapTextureAtlas11.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        this.planetsTextureRegion[7] = setTiledTextureIntoTextureAtlas(uciana, "Planets/7.png", bitmapTextureAtlas11, 0, 0, 3, 1);
        this.planetsTextureRegion[13] = setTiledTextureIntoTextureAtlas(uciana, "Planets/13.png", bitmapTextureAtlas11, 750, 0, 1, 1);
        this.planetsTextureRegion[8] = setTiledTextureIntoTextureAtlas(uciana, "Planets/8.png", bitmapTextureAtlas11, 0, WeaponStats.SUBSPACE_CHARGE_SPEED, 3, 1);
        this.planetsTextureRegion[14] = setTiledTextureIntoTextureAtlas(uciana, "Planets/14.png", bitmapTextureAtlas11, 750, WeaponStats.SUBSPACE_CHARGE_SPEED, 1, 1);
        this.planetsTextureRegion[9] = setTiledTextureIntoTextureAtlas(uciana, "Planets/9.png", bitmapTextureAtlas11, 0, 500, 3, 1);
        this.planetsTextureRegion[16] = setTiledTextureIntoTextureAtlas(uciana, "Planets/16.png", bitmapTextureAtlas11, 750, 500, 1, 1);
        this.planetsTextureRegion[11] = setTiledTextureIntoTextureAtlas(uciana, "Planets/11.png", bitmapTextureAtlas11, 0, 750, 3, 1);
        this.planetsTextureRegion[17] = setTiledTextureIntoTextureAtlas(uciana, "Planets/17.png", bitmapTextureAtlas11, 750, 750, 1, 1);
        bitmapTextureAtlas11.load();
        BitmapTextureAtlas bitmapTextureAtlas12 = new BitmapTextureAtlas(textureManager, 1024, 1024, textureOptions);
        bitmapTextureAtlas12.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        this.planetsTextureRegion[12] = setTiledTextureIntoTextureAtlas(uciana, "Planets/12.png", bitmapTextureAtlas12, 0, 0, 3, 1);
        this.planetsTextureRegion[19] = setTiledTextureIntoTextureAtlas(uciana, "Planets/19.png", bitmapTextureAtlas12, 750, 0, 1, 1);
        this.planetsTextureRegion[15] = setTiledTextureIntoTextureAtlas(uciana, "Planets/15.png", bitmapTextureAtlas12, 0, WeaponStats.SUBSPACE_CHARGE_SPEED, 3, 1);
        this.planetsTextureRegion[21] = setTiledTextureIntoTextureAtlas(uciana, "Planets/21.png", bitmapTextureAtlas12, 750, WeaponStats.SUBSPACE_CHARGE_SPEED, 1, 1);
        this.planetsTextureRegion[18] = setTiledTextureIntoTextureAtlas(uciana, "Planets/18.png", bitmapTextureAtlas12, 0, 500, 3, 1);
        this.planetsTextureRegion[22] = setTiledTextureIntoTextureAtlas(uciana, "Planets/22.png", bitmapTextureAtlas12, 750, 500, 1, 1);
        this.planetsTextureRegion[20] = setTiledTextureIntoTextureAtlas(uciana, "Planets/20.png", bitmapTextureAtlas12, 0, 750, 3, 1);
        this.planetsTextureRegion[24] = setTiledTextureIntoTextureAtlas(uciana, "Planets/24.png", bitmapTextureAtlas12, 750, 750, 1, 1);
        bitmapTextureAtlas12.load();
        BitmapTextureAtlas bitmapTextureAtlas13 = new BitmapTextureAtlas(textureManager, 1024, 1024, textureOptions);
        bitmapTextureAtlas13.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        this.planetsTextureRegion[23] = setTiledTextureIntoTextureAtlas(uciana, "Planets/23.png", bitmapTextureAtlas13, 0, 0, 3, 1);
        this.planetsTextureRegion[25] = setTiledTextureIntoTextureAtlas(uciana, "Planets/25.png", bitmapTextureAtlas13, 750, 0, 1, 1);
        this.planetsTextureRegion[26] = setTiledTextureIntoTextureAtlas(uciana, "Planets/26.png", bitmapTextureAtlas13, 0, WeaponStats.SUBSPACE_CHARGE_SPEED, 1, 1);
        this.planetsTextureRegion[27] = setTiledTextureIntoTextureAtlas(uciana, "Planets/27.png", bitmapTextureAtlas13, WeaponStats.SUBSPACE_CHARGE_SPEED, WeaponStats.SUBSPACE_CHARGE_SPEED, 1, 1);
        this.planetsTextureRegion[28] = setTiledTextureIntoTextureAtlas(uciana, "Planets/28.png", bitmapTextureAtlas13, 500, WeaponStats.SUBSPACE_CHARGE_SPEED, 1, 1);
        this.planetsTextureRegion[29] = setTiledTextureIntoTextureAtlas(uciana, "Planets/29.png", bitmapTextureAtlas13, 750, WeaponStats.SUBSPACE_CHARGE_SPEED, 1, 1);
        this.planetsTextureRegion[30] = setTiledTextureIntoTextureAtlas(uciana, "Planets/30.png", bitmapTextureAtlas13, 0, 500, 1, 1);
        this.planetsTextureRegion[31] = setTiledTextureIntoTextureAtlas(uciana, "Planets/31.png", bitmapTextureAtlas13, WeaponStats.SUBSPACE_CHARGE_SPEED, 500, 1, 1);
        this.planetsTextureRegion[32] = setTiledTextureIntoTextureAtlas(uciana, "Planets/32.png", bitmapTextureAtlas13, 500, 500, 1, 1);
        this.planetsTextureRegion[33] = setTiledTextureIntoTextureAtlas(uciana, "Planets/33.png", bitmapTextureAtlas13, 750, 500, 1, 1);
        this.planetsTextureRegion[34] = setTiledTextureIntoTextureAtlas(uciana, "Planets/34.png", bitmapTextureAtlas13, 0, 750, 1, 1);
        this.planetsTextureRegion[35] = setTiledTextureIntoTextureAtlas(uciana, "Planets/35.png", bitmapTextureAtlas13, WeaponStats.SUBSPACE_CHARGE_SPEED, 750, 1, 1);
        this.planetsTextureRegion[36] = setTiledTextureIntoTextureAtlas(uciana, "Planets/36.png", bitmapTextureAtlas13, 500, 750, 1, 1);
        bitmapTextureAtlas13.load();
    }

    private File getModdedFile(String str) {
        File file = new File(Environment.getExternalStorageDirectory() + "/uciana/mod");
        return new File(file.getAbsolutePath() + File.separator + str);
    }

    private TextureRegion getTextureFromFileOrAsset(final Uciana uciana, final String str) {
        if (isModdingAssetAvailable(uciana)) {
            final File moddedFile = getModdedFile(str);
            if (moddedFile.exists()) {
                try {
                    BitmapTexture bitmapTexture = new BitmapTexture(uciana.getEngine().getTextureManager(), new IInputStreamOpener(this) { // from class: com.birdshel.Uciana.Resources.Graphics.1
                        @Override // org.andengine.util.adt.io.in.IInputStreamOpener
                        public InputStream open() {
                            return new FileInputStream(moddedFile);
                        }
                    }, TextureOptions.BILINEAR);
                    bitmapTexture.load();
                    return TextureRegionFactory.extractFromTexture(bitmapTexture);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        try {
            BitmapTexture bitmapTexture2 = new BitmapTexture(uciana.getEngine().getTextureManager(), new IInputStreamOpener(this) { // from class: com.birdshel.Uciana.Resources.Graphics.2
                @Override // org.andengine.util.adt.io.in.IInputStreamOpener
                public InputStream open() {
                    AssetManager assets = uciana.getAssets();
                    return assets.open("gfx_hd/" + str);
                }
            }, TextureOptions.BILINEAR);
            bitmapTexture2.load();
            return TextureRegionFactory.extractFromTexture(bitmapTexture2);
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private TiledTextureRegion getTiledTextureFromFileOrAsset(final Uciana uciana, final String str, int i, int i2) {
        if (isModdingAssetAvailable(uciana)) {
            final File moddedFile = getModdedFile(str);
            if (moddedFile.exists()) {
                try {
                    BitmapTexture bitmapTexture = new BitmapTexture(uciana.getEngine().getTextureManager(), new IInputStreamOpener(this) { // from class: com.birdshel.Uciana.Resources.Graphics.3
                        @Override // org.andengine.util.adt.io.in.IInputStreamOpener
                        public InputStream open() {
                            return new FileInputStream(moddedFile);
                        }
                    }, TextureOptions.BILINEAR);
                    bitmapTexture.load();
                    return TextureRegionFactory.extractTiledFromTexture(bitmapTexture, i, i2);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        try {
            BitmapTexture bitmapTexture2 = new BitmapTexture(uciana.getEngine().getTextureManager(), new IInputStreamOpener(this) { // from class: com.birdshel.Uciana.Resources.Graphics.4
                @Override // org.andengine.util.adt.io.in.IInputStreamOpener
                public InputStream open() {
                    AssetManager assets = uciana.getAssets();
                    return assets.open("gfx_hd/" + str);
                }
            }, TextureOptions.BILINEAR);
            bitmapTexture2.load();
            return TextureRegionFactory.extractTiledFromTexture(bitmapTexture2, i, i2);
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private boolean isModdingAssetAvailable(Uciana uciana) {
        return Game.options.isOn(OptionID.MODDING) && ContextCompat.checkSelfPermission(uciana, "android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    private TextureRegion setTextureIntoTextureAtlas(Uciana uciana, String str, BitmapTextureAtlas bitmapTextureAtlas, int i, int i2) {
        if (isModdingAssetAvailable(uciana)) {
            File moddedFile = getModdedFile(str);
            if (moddedFile.exists()) {
                FileBitmapTextureAtlasSource create = FileBitmapTextureAtlasSource.create(moddedFile);
                uciana.getTextureManager().loadTexture(bitmapTextureAtlas);
                return TextureRegionFactory.createFromSource(bitmapTextureAtlas, create, i, i2, false);
            }
        }
        return BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, uciana, str, i, i2);
    }

    private TiledTextureRegion setTiledTextureIntoTextureAtlas(Uciana uciana, String str, BitmapTextureAtlas bitmapTextureAtlas, int i, int i2, int i3, int i4) {
        return setTiledTextureIntoTextureAtlasKnowIfModded(uciana, str, bitmapTextureAtlas, i, i2, i3, i4).f1417a;
    }

    private TiledTextureRegionObject setTiledTextureIntoTextureAtlasKnowIfModded(Uciana uciana, String str, BitmapTextureAtlas bitmapTextureAtlas, int i, int i2, int i3, int i4) {
        TiledTextureRegionObject tiledTextureRegionObject = new TiledTextureRegionObject();
        if (isModdingAssetAvailable(uciana)) {
            File moddedFile = getModdedFile(str);
            if (moddedFile.exists()) {
                FileBitmapTextureAtlasSource create = FileBitmapTextureAtlasSource.create(moddedFile);
                uciana.getTextureManager().loadTexture(bitmapTextureAtlas);
                tiledTextureRegionObject.f1417a = TextureRegionFactory.createTiledFromSource(bitmapTextureAtlas, create, i, i2, i3, i4, false);
                return tiledTextureRegionObject;
            }
        }
        tiledTextureRegionObject.f1417a = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, uciana, str, i, i2, i3, i4);
        return tiledTextureRegionObject;
    }

    public boolean isAnyShipsModded() {
        for (boolean z : this.isShipsModded) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public boolean isShipsModded(int i) {
        return this.isShipsModded[i];
    }

    public ITextureRegion setAmbassadorTexture(int i, Uciana uciana) {
        String str = "Ambassadors/" + i + ".png";
        if (!str.equals(this.ambassadorFileName)) {
            this.raceAmbassadorTexture = getTextureFromFileOrAsset(uciana, str);
            this.ambassadorFileName = str;
        }
        return this.raceAmbassadorTexture;
    }

    public void setDiscoveryTexture(int i, Uciana uciana) {
        String str = "Discoveries/" + i + ".png";
        if (str.equals(this.discoveryFileName)) {
            return;
        }
        this.discoveryTexture = getTextureFromFileOrAsset(uciana, str);
        this.discoveryFileName = str;
    }

    public void setExplorerTexture(int i, Uciana uciana) {
        String str = "Explorers/" + i + ".png";
        if (str.equals(this.exploreFileName)) {
            return;
        }
        this.raceExplorerTexture = getTextureFromFileOrAsset(uciana, str);
        this.exploreFileName = str;
    }

    public void setShipIconsTextures(int i, int i2, Uciana uciana) {
        setShipIconsTextures(i, i, i2, uciana);
    }

    public ITextureRegion setSurfaceTexture(String str, Uciana uciana) {
        if (!str.equals(this.surfaceFileName)) {
            this.planetSurfaceTexture = getTextureFromFileOrAsset(uciana, str);
            this.surfaceFileName = str;
        }
        return this.planetSurfaceTexture;
    }

    public void setShipIconsTextures(int i, int i2, int i3, Uciana uciana) {
        String str = "Ships/" + i2 + "-" + i3 + ".png";
        if (this.isShipsModded[i]) {
            str = "Ships/" + i + ".png";
        }
        if (this.shipTextureFileNames[i].equals(str)) {
            return;
        }
        this.shipsTextures[i] = getTiledTextureFromFileOrAsset(uciana, str, 9, 1);
        this.shipTextureFileNames[i] = str;
    }
}
