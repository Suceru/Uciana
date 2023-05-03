package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetSprite extends Entity {
    private int blastIndex;
    private VertexBufferObjectManager bufferManager;
    private Game game;
    private TiledSprite moonBaseLights;
    private int moonRangeHeight;
    private int moonRangeWidth;
    private TiledSprite moonSprite;
    private TiledSprite planetSprite;
    private Sprite planetaryShield;
    private TiledSprite ringSprite;
    private float width;
    private final List<Entity> cityLights = new ArrayList();
    private final List<AnimatedSprite> explosionList = new ArrayList();
    private final List<Text> hpDamageList = new ArrayList();
    private final List<TiledSprite> blastDamageList = new ArrayList();

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Planets.PlanetSprite$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1395a;

        static {
            int[] iArr = new int[Climate.values().length];
            f1395a = iArr;
            try {
                iArr[Climate.POLLUTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1395a[Climate.RING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1395a[Climate.BROKEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public PlanetSprite(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        create(game, vertexBufferObjectManager, 0, 0);
    }

    private void rotatePlanet(float f2) {
        TiledSprite tiledSprite = this.planetSprite;
        tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, this.planetSprite.getHeightScaled() / 2.0f);
        this.planetSprite.setRotation(f2);
        TiledSprite tiledSprite2 = this.ringSprite;
        tiledSprite2.setRotationCenter(tiledSprite2.getWidthScaled() / 2.0f, this.ringSprite.getHeightScaled() / 2.0f);
        this.ringSprite.setRotation(f2);
        TiledSprite tiledSprite3 = this.moonSprite;
        tiledSprite3.setRotationCenter(tiledSprite3.getWidthScaled() / 2.0f, this.moonSprite.getHeightScaled() / 2.0f);
        this.moonSprite.setRotation(f2);
    }

    private void setMoon(Moon moon, float f2) {
        this.moonSprite.setScaleCenter(0.0f, 0.0f);
        float size = Moon.SPRITE_SIZE * f2 * moon.getSize();
        this.moonSprite.setSize(size, size);
        int i = this.moonRangeWidth;
        int i2 = (int) size;
        float nextInt = i - i2 > 0 ? Functions.random.nextInt(i - i2) - (this.moonRangeWidth / 2.0f) : 0.0f;
        int i3 = this.moonRangeHeight;
        this.moonSprite.setPosition(nextInt, i3 - i2 > 0 ? Functions.random.nextInt(i3 - i2) - (this.moonRangeHeight / 2.0f) : 0.0f);
        this.moonSprite.setCurrentTileIndex(moon.getImageIndex());
        this.moonSprite.setVisible(true);
        this.moonBaseLights.setPosition(30.0f * f2 * moon.getSize(), 20.0f * f2 * moon.getSize());
        this.moonBaseLights.setCurrentTileIndex(11);
        float size2 = f2 * 62.0f * moon.getSize();
        this.moonBaseLights.setSize(size2, size2);
    }

    private void setPlanetSprite(Climate climate, float f2, int i, boolean z, int i2, boolean z2, Moon moon, float f3, boolean z3) {
        setSpritesInvisible();
        this.blastIndex = 0;
        this.planetSprite.setScale(f2);
        this.width = this.planetSprite.getWidthScaled();
        int i3 = AnonymousClass1.f1395a[climate.ordinal()];
        if (i3 == 1) {
            this.planetSprite.setScale(1.56f * f2);
        } else if (i3 == 2) {
            this.planetSprite.setScale(2.88f * f2);
        } else if (i3 == 3) {
            this.planetSprite.setScale(2.5f * f2);
        }
        float widthScaled = (this.planetSprite.getWidthScaled() / 2.0f) * (-1.0f);
        float heightScaled = (this.planetSprite.getHeightScaled() / 2.0f) * (-1.0f);
        this.planetSprite.setPosition(widthScaled, heightScaled);
        this.planetSprite.setVisible(true);
        setClimate(climate, i);
        if (z) {
            setRing(widthScaled, heightScaled, f2, i2);
        }
        if (z2) {
            setMoon(moon, f3);
        }
        if (z3) {
            rotatePlanet(22.5f);
        } else {
            rotatePlanet(0.0f);
        }
    }

    private void setRing(float f2, float f3, float f4, int i) {
        this.ringSprite.setScaleCenter(0.0f, 0.0f);
        this.ringSprite.setScale(f4);
        float f5 = f4 * 62.5f;
        this.ringSprite.setPosition(f2 - f5, f3 - f5);
        this.ringSprite.setCurrentTileIndex(i);
        this.ringSprite.setVisible(true);
    }

    public void create(Game game, VertexBufferObjectManager vertexBufferObjectManager, int i, int i2) {
        this.game = game;
        this.bufferManager = vertexBufferObjectManager;
        this.moonRangeWidth = i;
        this.moonRangeHeight = i2;
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.planetsTextureRegion[0], vertexBufferObjectManager);
        this.planetSprite = tiledSprite;
        tiledSprite.setVisible(false);
        this.planetSprite.setScaleCenter(0.0f, 0.0f);
        this.planetSprite.setZIndex(0);
        attachChild(this.planetSprite);
        for (int i3 = 0; i3 < 12; i3++) {
            TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.cityLightsTexture, vertexBufferObjectManager);
            tiledSprite2.setScaleCenter(0.0f, 0.0f);
            tiledSprite2.setZIndex(1);
            tiledSprite2.setVisible(false);
            this.cityLights.add(tiledSprite2);
            attachChild(tiledSprite2);
        }
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.ringsTexture, vertexBufferObjectManager);
        this.ringSprite = tiledSprite3;
        tiledSprite3.setVisible(false);
        this.ringSprite.setZIndex(4);
        attachChild(this.ringSprite);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.shieldTexture, vertexBufferObjectManager);
        this.planetaryShield = sprite;
        sprite.setVisible(false);
        this.planetaryShield.setZIndex(5);
        attachChild(this.planetaryShield);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, game.graphics.moonsTexture, vertexBufferObjectManager);
        this.moonSprite = tiledSprite4;
        tiledSprite4.setVisible(false);
        this.moonSprite.setZIndex(5);
        attachChild(this.moonSprite);
        TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, game.graphics.cityLightsTexture, vertexBufferObjectManager);
        this.moonBaseLights = tiledSprite5;
        tiledSprite5.setScaleCenter(0.0f, 0.0f);
        this.moonBaseLights.setZIndex(5);
        this.moonBaseLights.setVisible(false);
        this.moonSprite.attachChild(this.moonBaseLights);
    }

    public float getHeightScaled() {
        return this.planetSprite.getHeightScaled();
    }

    public TiledSprite getMoonSprite() {
        return this.moonSprite;
    }

    public TiledSprite getPlanetSprite() {
        return this.planetSprite;
    }

    public float getWidthScaled() {
        return this.planetSprite.getWidthScaled();
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public float getX() {
        return super.getX() + this.planetSprite.getX();
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public float getY() {
        return super.getY() + this.planetSprite.getY();
    }

    public void hideMoon() {
        this.moonSprite.setVisible(false);
        this.moonBaseLights.setVisible(false);
    }

    public void setCityLights(int i, int i2, float f2, float f3, boolean z) {
        if (z) {
            CityLights.a(i2, 0, this.cityLights, i, this.planetSprite, f2, 22.5f, f3);
        } else {
            CityLights.a(i2, 0, this.cityLights, i, this.planetSprite, f2, 0.0f, f3);
        }
    }

    public void setClimate(Climate climate, int i) {
        this.planetSprite.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.planetsTextureRegion[climate.getID()]);
        this.planetSprite.setCurrentTileIndex(climate.getImageIndex(i));
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setColony(Colony colony, float f2, boolean z) {
        float f3;
        float f4;
        int i = AnonymousClass1.f1395a[colony.getPlanet().getClimate().ordinal()];
        if (i == 1) {
            f3 = 1.56f;
        } else if (i == 2) {
            f3 = 2.88f;
        } else if (i != 3) {
            f4 = f2;
            setCityLights(colony.getPopulation(), colony.getPlanet().getCityLightIndex(), f2, f4, z);
            if (colony.isShielded() && colony.isBlockaded()) {
                this.planetaryShield.setVisible(true);
                this.planetaryShield.setScaleCenter(0.0f, 0.0f);
                this.planetaryShield.setScale(f2 * 1.3f);
                this.planetaryShield.setPosition(((this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - (this.planetaryShield.getWidthScaled() / 2.0f)) - (this.planetaryShield.getWidthScaled() * 0.01f), (this.planetSprite.getY() + (this.planetSprite.getHeightScaled() / 2.0f)) - (this.planetaryShield.getHeightScaled() / 2.0f));
            }
            if (colony.hasBuilding(BuildingID.MOON_BASE)) {
                return;
            }
            this.moonBaseLights.setVisible(true);
            return;
        } else {
            f3 = 2.5f;
        }
        f4 = f3 * f2;
        setCityLights(colony.getPopulation(), colony.getPlanet().getCityLightIndex(), f2, f4, z);
        if (colony.isShielded()) {
            this.planetaryShield.setVisible(true);
            this.planetaryShield.setScaleCenter(0.0f, 0.0f);
            this.planetaryShield.setScale(f2 * 1.3f);
            this.planetaryShield.setPosition(((this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - (this.planetaryShield.getWidthScaled() / 2.0f)) - (this.planetaryShield.getWidthScaled() * 0.01f), (this.planetSprite.getY() + (this.planetSprite.getHeightScaled() / 2.0f)) - (this.planetaryShield.getHeightScaled() / 2.0f));
        }
        if (colony.hasBuilding(BuildingID.MOON_BASE)) {
        }
    }

    public void setGasGiant(GasGiant gasGiant, float f2, float f3) {
        setGasGiant(gasGiant, f2, f3, false);
    }

    public void setMoonRange(int i, int i2) {
        this.moonRangeWidth = i;
        this.moonRangeHeight = i2;
    }

    public void setPlanet(Planet planet, float f2, float f3) {
        setPlanet(planet, f2, f3, false);
    }

    public void setSpritesInvisible() {
        this.planetSprite.setVisible(false);
        this.moonSprite.setVisible(false);
        this.moonBaseLights.setVisible(false);
        this.ringSprite.setVisible(false);
        this.planetaryShield.setVisible(false);
        for (Entity entity : this.cityLights) {
            entity.setVisible(false);
        }
        for (AnimatedSprite animatedSprite : this.explosionList) {
            animatedSprite.setVisible(false);
        }
        for (Text text : this.hpDamageList) {
            text.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.blastDamageList) {
            tiledSprite.setVisible(false);
        }
    }

    public void showExplosion(ShipComponentID shipComponentID, String str, boolean z) {
        Weapon weapon = (Weapon) ShipComponents.get(shipComponentID);
        if (this.blastIndex >= this.explosionList.size()) {
            AnimatedSprite animatedSprite = new AnimatedSprite(0.0f, 0.0f, this.game.graphics.explosionTexture, this.bufferManager);
            animatedSprite.setZIndex(3);
            this.explosionList.add(animatedSprite);
            attachChild(animatedSprite);
            Text text = new Text(0.0f, animatedSprite.getY() - 10.0f, this.game.fonts.smallInfoFont, str, new TextOptions(HorizontalAlign.CENTER), this.bufferManager);
            text.setAlpha(0.0f);
            text.setColor(Color.RED);
            this.hpDamageList.add(text);
            attachChild(text);
            TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.game.graphics.bombDamageTexture, this.bufferManager);
            tiledSprite.setZIndex(2);
            this.blastDamageList.add(tiledSprite);
            attachChild(tiledSprite);
        }
        this.explosionList.get(this.blastIndex).setVisible(true);
        this.explosionList.get(this.blastIndex).setSize(weapon.getBlastSize(), weapon.getBlastSize());
        float f2 = this.width;
        Point randomPointInsideCircle = Functions.randomPointInsideCircle(((f2 - (0.08f * f2)) - weapon.getBlastSize()) - 25.0f);
        this.explosionList.get(this.blastIndex).setX(((this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) + randomPointInsideCircle.getX()) - (weapon.getBlastSize() / 2.0f));
        this.explosionList.get(this.blastIndex).setY(((this.planetSprite.getY() + (this.planetSprite.getHeightScaled() / 2.0f)) + randomPointInsideCircle.getY()) - (weapon.getBlastSize() / 2.0f));
        this.explosionList.get(this.blastIndex).animate(new long[]{65, 65, 65, 65, 65, 65, 65, 65, 65}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, false);
        if (z) {
            this.hpDamageList.get(this.blastIndex).setVisible(false);
        } else {
            this.hpDamageList.get(this.blastIndex).setVisible(true);
            this.hpDamageList.get(this.blastIndex).setX(this.explosionList.get(this.blastIndex).getX() + (this.hpDamageList.get(this.blastIndex).getWidthScaled() / 2.0f));
            this.hpDamageList.get(this.blastIndex).registerEntityModifier(new AlphaModifier(0.7f, 1.0f, 0.0f));
        }
        this.blastDamageList.get(this.blastIndex).setVisible(true);
        int nextInt = Functions.random.nextInt(this.game.graphics.bombDamageTexture.getTileCount());
        float blastSize = weapon.getBlastSize() + 10;
        float f3 = blastSize / 2.0f;
        this.blastDamageList.get(this.blastIndex).setPosition((this.explosionList.get(this.blastIndex).getX() + f3) - f3, (this.explosionList.get(this.blastIndex).getY() + f3) - f3);
        this.blastDamageList.get(this.blastIndex).setSize(blastSize, blastSize);
        this.blastDamageList.get(this.blastIndex).setCurrentTileIndex(nextInt);
        sortChildren();
        this.blastIndex++;
    }

    public void setGasGiant(GasGiant gasGiant, float f2, float f3, boolean z) {
        setPlanetSprite(Climate.GAS_GIANT, f2, gasGiant.getImageIndex(), gasGiant.hasRing(), gasGiant.getRingImageIndex(), gasGiant.hasMoon(), gasGiant.getMoon(), f3, z);
    }

    public void setPlanet(Planet planet, float f2, float f3, boolean z) {
        setPlanetSprite(planet.getClimate(), f2, planet.getImageIndex(), planet.hasRing(), planet.getRingImageIndex(), planet.hasMoon(), planet.getMoon(), f3, z);
        if (planet.hasColony()) {
            setColony(planet.getColony(), f2, z);
        }
    }

    public PlanetSprite(Game game, VertexBufferObjectManager vertexBufferObjectManager, int i, int i2) {
        create(game, vertexBufferObjectManager, i, i2);
    }
}
