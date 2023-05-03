package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import com.birdshel.Uciana.StarSystems.StarSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SystemPreviewOverlay extends ExtendedChildScene {
    private static final int COLONY_BANNER_WIDTH = 150;
    private static final int ORBIT_SIZE = 150;
    private static final int OUTPOST_BANNER_WIDTH = 75;
    private static final int SUN_WIDTH = 28;
    private final List<TiledSprite> asteroidBelts;
    private final Sprite blackhole;
    private int blackholeID;
    private final List<TiledSprite> empireBars;
    private final List<Text> exploredStatuses;
    private final List<PlanetSprite> planetSprites;
    private final TiledSprite spaceRift;
    private int spaceRiftID;
    private final Sprite sun;
    private int systemID;
    private final Text systemName;
    private final Entity systemPreview;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Overlays.SystemPreviewOverlay$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1390a;

        static {
            int[] iArr = new int[SystemObjectType.values().length];
            f1390a = iArr;
            try {
                iArr[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1390a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1390a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemPreviewOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        super(game, vertexBufferObjectManager, false, f2);
        this.planetSprites = new ArrayList();
        this.asteroidBelts = new ArrayList();
        this.empireBars = new ArrayList();
        this.exploredStatuses = new ArrayList();
        this.G.setVisible(false);
        Entity entity = new Entity();
        this.systemPreview = entity;
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        sprite.setSize(778.0f, 150.0f);
        entity.attachChild(sprite);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        sprite2.setSize(778.0f, 150.0f);
        entity.attachChild(sprite2);
        Sprite sprite3 = new Sprite(0.0f, 0.0f, game.graphics.sunTextureRegion, vertexBufferObjectManager);
        this.sun = sprite3;
        sprite3.setSize(28.0f, 150.0f);
        entity.attachChild(sprite3);
        for (int i = 0; i < 5; i++) {
            int i2 = i * WeaponStats.NOVA_BOMB_MAX_DAMAGE;
            TiledSprite tiledSprite = new TiledSprite(i2 + 44 + 28, 0.0f, game.graphics.asteroidBeltsTexture, vertexBufferObjectManager);
            tiledSprite.setSize(44.0f, 150.0f);
            this.systemPreview.attachChild(tiledSprite);
            this.asteroidBelts.add(tiledSprite);
            Text text = new Text(0.0f, 120.0f, game.fonts.smallInfoFont, this.E, vertexBufferObjectManager);
            text.setZIndex(2);
            this.systemPreview.attachChild(text);
            this.exploredStatuses.add(text);
            TiledSprite tiledSprite2 = new TiledSprite(i2 + 28, 140.0f, game.graphics.empireColors, vertexBufferObjectManager);
            tiledSprite2.setHeight(10.0f);
            tiledSprite2.setZIndex(2);
            this.systemPreview.attachChild(tiledSprite2);
            this.empireBars.add(tiledSprite2);
        }
        Text text2 = new Text(265.0f, -10.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.systemName = text2;
        text2.setZIndex(2);
        this.systemPreview.attachChild(text2);
        Sprite sprite4 = new Sprite(289.0f, -20.0f, game.graphics.blackholeTexture, vertexBufferObjectManager);
        this.blackhole = sprite4;
        sprite4.setSize(200.0f, 200.0f);
        this.systemPreview.attachChild(sprite4);
        TiledSprite tiledSprite3 = new TiledSprite(299.0f, -10.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.spaceRift = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(GameIconEnum.SPACE_RIFT.ordinal());
        tiledSprite3.setSize(180.0f, 180.0f);
        this.systemPreview.attachChild(tiledSprite3);
        attachChild(this.systemPreview);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0112, code lost:
        r1 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkPressLocation(Point point) {
        boolean z;
        boolean z2;
        Point point2 = new Point(point.getX() - this.C.galaxyScene.getGalaxyPosition().getX(), point.getY() - this.C.galaxyScene.getGalaxyPosition().getY());
        int i = 0;
        while (true) {
            z = true;
            if (i >= this.C.galaxyScene.stars.size()) {
                z2 = false;
                break;
            }
            float sizeModifier = this.C.galaxyScene.getZoom() == 1.0f ? (this.C.galaxy.getSize().getSizeModifier() * 50.0f) - this.C.galaxyScene.stars.get(i).getWidthScaled() : 0.0f;
            float x = this.C.galaxyScene.stars.get(i).getX() * this.C.galaxyScene.getZoom();
            float y = this.C.galaxyScene.stars.get(i).getY() * this.C.galaxyScene.getZoom();
            float widthScaled = (this.C.galaxyScene.stars.get(i).getWidthScaled() * this.C.galaxyScene.getZoom()) + sizeModifier;
            float heightScaled = (this.C.galaxyScene.stars.get(i).getHeightScaled() * this.C.galaxyScene.getZoom()) + sizeModifier;
            if (this.C.galaxyScene.stars.get(i).isVisible() && point2.getX() > x && point2.getX() < x + widthScaled && point2.getY() > y && point2.getY() < y + heightScaled) {
                if (i == this.systemID) {
                    return;
                }
                setSystem(i);
                this.blackholeID = -1;
                this.spaceRiftID = -1;
            } else {
                float x2 = this.C.galaxyScene.outOfRangeStars.get(i).getX() * this.C.galaxyScene.getZoom();
                float y2 = this.C.galaxyScene.outOfRangeStars.get(i).getY() * this.C.galaxyScene.getZoom();
                float widthScaled2 = (this.C.galaxyScene.outOfRangeStars.get(i).getWidthScaled() * this.C.galaxyScene.getZoom()) + sizeModifier;
                float heightScaled2 = (this.C.galaxyScene.outOfRangeStars.get(i).getHeightScaled() * this.C.galaxyScene.getZoom()) + sizeModifier;
                if (!this.C.galaxyScene.outOfRangeStars.get(i).isVisible() || point2.getX() <= x2 || point2.getX() >= x2 + widthScaled2 || point2.getY() <= y2 || point2.getY() >= y2 + heightScaled2) {
                    i++;
                } else if (i == this.systemID) {
                    return;
                } else {
                    setSystem(i);
                    this.blackholeID = -1;
                    this.spaceRiftID = -1;
                }
            }
        }
        Iterator<Blackhole> it = this.C.galaxy.getBlackholes().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Blackhole next = it.next();
            Sprite sprite = this.C.galaxyScene.blackholes.get(next.getID());
            float x3 = (sprite.getX() + (sprite.getWidthScaled() * 0.25f)) * this.C.galaxyScene.getZoom();
            float y3 = (sprite.getY() + (sprite.getHeightScaled() * 0.25f)) * this.C.galaxyScene.getZoom();
            float x4 = ((sprite.getX() * this.C.galaxyScene.getZoom()) + (sprite.getWidthScaled() * this.C.galaxyScene.getZoom())) - ((sprite.getWidthScaled() * 0.25f) * this.C.galaxyScene.getZoom());
            float y4 = ((sprite.getY() * this.C.galaxyScene.getZoom()) + (sprite.getHeightScaled() * this.C.galaxyScene.getZoom())) - ((sprite.getHeightScaled() * 0.25f) * this.C.galaxyScene.getZoom());
            if (point2.getX() > x3 && point2.getX() < x4 && point2.getY() > y3 && point2.getY() < y4) {
                if (next.getID() == this.blackholeID) {
                    return;
                }
                setBlackhole(next.getID());
                this.systemID = -1;
                this.spaceRiftID = -1;
                z2 = true;
            }
        }
        Iterator<SpaceRift> it2 = this.C.galaxy.getSpaceRifts().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = z2;
                break;
            }
            SpaceRift next2 = it2.next();
            TiledSprite tiledSprite = this.C.galaxyScene.spaceRifts.get(next2.getID());
            if (point2.getX() > tiledSprite.getX() * this.C.galaxyScene.getZoom() && point2.getX() < (tiledSprite.getX() * this.C.galaxyScene.getZoom()) + (tiledSprite.getWidthScaled() * this.C.galaxyScene.getZoom()) && point2.getY() > tiledSprite.getY() * this.C.galaxyScene.getZoom() && point2.getY() < (tiledSprite.getY() * this.C.galaxyScene.getZoom()) + (tiledSprite.getHeightScaled() * this.C.galaxyScene.getZoom())) {
                if (next2.getID() == this.spaceRiftID) {
                    return;
                }
                setSpaceRift(next2.getID());
                this.systemID = -1;
                this.blackholeID = -1;
            }
        }
        if (z) {
            return;
        }
        this.systemPreview.setVisible(false);
        this.systemID = -1;
        this.blackholeID = -1;
        this.spaceRiftID = -1;
    }

    private void setBlackhole(int i) {
        this.blackholeID = i;
        Blackhole blackhole = this.C.galaxy.getBlackhole(i);
        this.systemPreview.setVisible(true);
        setSpiresInvisible();
        this.systemName.setText(this.C.getActivity().getString(R.string.system_preview_blackhole));
        Text text = this.systemName;
        text.setPosition(389.0f - (text.getWidthScaled() / 2.0f), -10.0f);
        this.blackhole.setVisible(true);
        this.blackhole.setFlippedHorizontal(false);
        float f2 = 0.0f;
        float f3 = 360.0f;
        if (blackhole.hasAltSpinDirection()) {
            this.blackhole.setFlippedHorizontal(true);
        } else {
            f2 = 360.0f;
            f3 = 0.0f;
        }
        Sprite sprite = this.blackhole;
        sprite.setRotationCenter(sprite.getWidthScaled() / 2.0f, this.blackhole.getHeightScaled() / 2.0f);
        this.blackhole.clearEntityModifiers();
        this.blackhole.registerEntityModifier(new LoopEntityModifier(new RotationModifier(blackhole.getSpinSpeed(), f2, f3)));
        setPreviewPosition(blackhole.getPosition().getY());
    }

    private void setEmpireBars(SystemObject systemObject, int i) {
        if (systemObject.hasColony()) {
            Colony colony = systemObject.getColony();
            TiledSprite tiledSprite = this.empireBars.get(i);
            tiledSprite.setCurrentTileIndex(colony.getEmpireID());
            tiledSprite.setX((i * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + 28);
            tiledSprite.setWidth(150.0f);
            tiledSprite.setVisible(true);
        } else if (systemObject.hasOutpost()) {
            Outpost outpost = systemObject.getOutpost();
            TiledSprite tiledSprite2 = this.empireBars.get(i);
            tiledSprite2.setCurrentTileIndex(outpost.getEmpireID());
            tiledSprite2.setX((i * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + 28 + 37.5f);
            tiledSprite2.setWidth(75.0f);
            tiledSprite2.setVisible(true);
        }
    }

    private void setExploredStatus(SystemObject systemObject, int i) {
        this.exploredStatuses.get(i).setVisible(false);
        if (systemObject.getSystemObjectType() == SystemObjectType.PLANET && !systemObject.hasBeenExploredByEmpire(this.C.getCurrentPlayer())) {
            Text text = this.exploredStatuses.get(i);
            text.setVisible(true);
            if (systemObject.isUnexplored()) {
                text.setColor(Color.WHITE);
                text.setText(this.C.getActivity().getString(R.string.system_preview_planet_unexplored));
            } else {
                text.setColor(new Color(0.6f, 0.6f, 0.6f));
                text.setText(this.C.getActivity().getString(R.string.system_preview_planet_unknown));
            }
            text.setX((((i * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + 28) + 75.0f) - (text.getWidthScaled() / 2.0f));
        }
    }

    private void setPreviewPosition(float f2) {
        setY(20.0f);
        if (f2 < 200.0f) {
            setY(540.0f);
        }
    }

    private void setSpaceRift(int i) {
        this.spaceRiftID = i;
        SpaceRift spaceRift = this.C.galaxy.getSpaceRifts().get(i);
        this.systemPreview.setVisible(true);
        setSpiresInvisible();
        this.systemName.setText(this.C.getActivity().getString(R.string.system_preview_space_rift));
        Text text = this.systemName;
        text.setPosition(389.0f - (text.getWidthScaled() / 2.0f), -10.0f);
        this.spaceRift.setVisible(true);
        TiledSprite tiledSprite = this.spaceRift;
        tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, this.spaceRift.getHeightScaled() / 2.0f);
        this.spaceRift.setFlippedHorizontal(false);
        float f2 = 360.0f;
        float f3 = 0.0f;
        if (spaceRift.hasAltSpinDirection()) {
            this.spaceRift.setFlippedHorizontal(true);
        } else {
            f2 = 0.0f;
            f3 = 360.0f;
        }
        TiledSprite tiledSprite2 = this.spaceRift;
        tiledSprite2.setRotationCenter(tiledSprite2.getWidthScaled() / 2.0f, this.spaceRift.getHeightScaled() / 2.0f);
        this.spaceRift.clearEntityModifiers();
        this.spaceRift.registerEntityModifier(new LoopEntityModifier(new RotationModifier(spaceRift.getSpinSpeed(), f2, f3)));
        setPreviewPosition(spaceRift.getPosition().getY());
    }

    private void setSpiresInvisible() {
        for (PlanetSprite planetSprite : this.planetSprites) {
            planetSprite.setSpritesInvisible();
        }
        for (Text text : this.exploredStatuses) {
            text.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.asteroidBelts) {
            tiledSprite.setVisible(false);
        }
        for (TiledSprite tiledSprite2 : this.empireBars) {
            tiledSprite2.setVisible(false);
        }
        this.sun.setVisible(false);
        this.blackhole.setVisible(false);
        this.spaceRift.setVisible(false);
    }

    private void setSystem(int i) {
        this.systemID = i;
        StarSystem starSystem = this.C.galaxy.getStarSystem(i);
        this.systemPreview.setVisible(true);
        setSpiresInvisible();
        if (!this.C.getCurrentEmpire().getDiscoveredSystems().contains(Integer.valueOf(i)) && !Game.options.isOn(OptionID.DEBUG)) {
            this.systemName.setText(this.C.getActivity().getString(R.string.system_preview_unexplored_system_name));
            Text text = this.systemName;
            text.setY(75.0f - (text.getHeightScaled() / 2.0f));
        } else {
            this.systemName.setText(starSystem.getName());
            this.systemName.setY(-10.0f);
        }
        Text text2 = this.systemName;
        text2.setX(389.0f - (text2.getWidthScaled() / 2.0f));
        this.sun.setVisible(true);
        this.sun.setColor(starSystem.getStarType().getColor());
        if (this.C.getCurrentEmpire().getDiscoveredSystems().contains(Integer.valueOf(i)) || Game.options.isOn(OptionID.DEBUG)) {
            for (int i2 = 0; i2 < 5; i2++) {
                SystemObject systemObject = starSystem.getSystemObject(i2);
                int i3 = AnonymousClass1.f1390a[systemObject.getSystemObjectType().ordinal()];
                if (i3 == 1) {
                    Planet planet = (Planet) systemObject;
                    this.planetSprites.get(i2).setPlanet(planet, planet.getScale(this.C.galaxyScene), Moon.getScale(this.C.galaxyScene));
                } else if (i3 == 2) {
                    GasGiant gasGiant = (GasGiant) systemObject;
                    this.planetSprites.get(i2).setGasGiant(gasGiant, gasGiant.getScale(this.C.galaxyScene), Moon.getScale(this.C.galaxyScene));
                } else if (i3 == 3) {
                    this.asteroidBelts.get(i2).setCurrentTileIndex(((AsteroidBelt) systemObject).getImageIndex());
                    this.asteroidBelts.get(i2).setVisible(true);
                }
                setExploredStatus(systemObject, i2);
                setEmpireBars(systemObject, i2);
            }
        }
        setPreviewPosition(starSystem.getPosition().getY());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            this.C.galaxyScene.showButtons();
            back();
        } else if (i != 2) {
        } else {
            checkPressLocation(point);
        }
    }

    public void getPoolElements() {
        for (int i = 0; i < 5; i++) {
            PlanetSprite planetSprite = this.C.planetSpritePool.get();
            planetSprite.setPosition((i * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + 28 + 75.0f, 75.0f);
            planetSprite.setMoonRange(WeaponStats.NOVA_BOMB_MAX_DAMAGE, WeaponStats.NOVA_BOMB_MAX_DAMAGE);
            planetSprite.setZIndex(1);
            this.systemPreview.attachChild(planetSprite);
            this.planetSprites.add(planetSprite);
        }
        this.systemPreview.sortChildren();
    }

    public void releasePoolElements() {
        for (PlanetSprite planetSprite : this.planetSprites) {
            this.systemPreview.detachChild(planetSprite);
            this.C.planetSpritePool.push(planetSprite);
        }
        this.planetSprites.clear();
    }

    public void setOverlay(Point point) {
        this.systemPreview.setVisible(false);
        this.systemID = -1;
        this.blackholeID = -1;
        this.spaceRiftID = -1;
        checkPressLocation(point);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
