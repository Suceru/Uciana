package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Events.EventDataFields;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechID;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EventListElement extends Entity {
    private final TiledSprite asteroidBeltImage;
    private final TiledSprite empireBanner;
    private final TiledSprite empireColor;
    private final Game game;
    private final Text message;
    private final TiledSprite planetImage;
    private final TiledSprite ring;
    private AnimatedSprite star;
    private final TechIcon techIcon;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Elements.EventListElement$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1362a;

        static {
            int[] iArr = new int[EventType.values().length];
            f1362a = iArr;
            try {
                iArr[EventType.EMPIRE_DESTROYED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1362a[EventType.EMPIRE_CONTACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1362a[EventType.TERRAFORMING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1362a[EventType.OUTPOST_DESTROYED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1362a[EventType.COLONY_DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1362a[EventType.CAPITAL_DESTROYED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1362a[EventType.COLONY_INVADED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1362a[EventType.BUILDING_SCRAP.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public EventListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        this.game = game;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        sprite.setAlpha(0.7f);
        sprite.setSize(f2 - 145.0f, 86.0f);
        attachChild(sprite);
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, wrap, vertexBufferObjectManager);
        this.message = text;
        attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.empireColor = tiledSprite;
        tiledSprite.setSize(86.0f, 86.0f);
        tiledSprite.setAlpha(0.75f);
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.empireBanner = tiledSprite2;
        tiledSprite2.setSize(86.0f, 86.0f);
        attachChild(tiledSprite2);
        TechIcon techIcon = new TechIcon(game, vertexBufferObjectManager, 86);
        this.techIcon = techIcon;
        techIcon.setPosition(0.0f, 0.0f);
        attachChild(techIcon);
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.planetsTextureRegion[0], vertexBufferObjectManager);
        this.planetImage = tiledSprite3;
        tiledSprite3.setScaleCenter(0.0f, 0.0f);
        tiledSprite3.setScale(0.344f);
        attachChild(tiledSprite3);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, game.graphics.ringsTexture, vertexBufferObjectManager);
        this.ring = tiledSprite4;
        tiledSprite4.setScaleCenter(0.0f, 0.0f);
        tiledSprite4.setScale(0.344f);
        attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, game.graphics.asteroidBeltsTexture, vertexBufferObjectManager);
        this.asteroidBeltImage = tiledSprite5;
        tiledSprite5.setScaleCenter(0.0f, 0.0f);
        tiledSprite5.setScale(0.12f);
        attachChild(tiledSprite5);
        TiledSprite tiledSprite6 = new TiledSprite(f2 - 220.0f, 17.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite6.setCurrentTileIndex(GameIconEnum.CLOSE.ordinal());
        tiledSprite6.setScaleCenter(0.0f, 0.0f);
        tiledSprite6.setScale(0.5f);
        attachChild(tiledSprite6);
    }

    private void setEntitiesHidden() {
        this.message.setVisible(false);
        this.empireColor.setVisible(false);
        this.empireBanner.setVisible(false);
        this.star.setVisible(false);
        this.techIcon.setVisible(false);
        this.planetImage.setVisible(false);
        this.ring.setVisible(false);
        this.asteroidBeltImage.setVisible(false);
    }

    public void getPoolElements() {
        AnimatedSprite animatedSprite = this.game.starSpritePool.get();
        this.star = animatedSprite;
        animatedSprite.setPosition(0.0f, 0.0f);
        this.star.setSize(86.0f, 86.0f);
        attachChild(this.star);
    }

    public void releasePoolElements() {
        detachChild(this.star);
        this.game.starSpritePool.push(this.star);
    }

    public void setEmpireEvent(Event event) {
        String string;
        setVisible(true);
        setEntitiesHidden();
        int intValue = ((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue();
        Empire empire = this.game.empires.get(intValue);
        int i = AnonymousClass1.f1362a[event.getEventType().ordinal()];
        if (i != 1) {
            string = i != 2 ? "" : this.game.getActivity().getString(R.string.events_empire_contact, new Object[]{empire.getName()});
        } else {
            string = this.game.getActivity().getString(R.string.events_empire_destroyed, new Object[]{empire.getName()});
        }
        this.message.setText(string);
        this.message.setVisible(true);
        this.message.setPosition(96.0f, 43.0f - (this.message.getHeight() / 2.0f));
        this.empireColor.setVisible(true);
        this.empireColor.setCurrentTileIndex(intValue);
        this.empireBanner.setVisible(true);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(intValue));
    }

    public void setPlanetEvent(Event event) {
        String string;
        setVisible(true);
        setEntitiesHidden();
        SystemObject systemObject = this.game.galaxy.getStarSystem(((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue()).getSystemObject(((Integer) event.getEventData(EventDataFields.ORBIT)).intValue());
        String name = systemObject.getName();
        if (systemObject.hasColony()) {
            name = systemObject.getColony().getName();
        }
        switch (AnonymousClass1.f1362a[event.getEventType().ordinal()]) {
            case 3:
                string = this.game.getActivity().getString(R.string.events_planet_terraformed, new Object[]{name, ((Planet) systemObject).getTerraformedClimate().getDisplayName()});
                break;
            case 4:
                string = this.game.getActivity().getString(R.string.events_outpost_destroyed, new Object[]{name});
                break;
            case 5:
                string = this.game.getActivity().getString(R.string.events_colony_destroyed, new Object[]{name});
                break;
            case 6:
                string = this.game.getActivity().getString(R.string.events_capital_destroyed, new Object[]{name});
                break;
            case 7:
                string = this.game.getActivity().getString(R.string.events_colony_invaded, new Object[]{name});
                break;
            case 8:
                string = this.game.getActivity().getString(R.string.events_building_scrapped, new Object[]{Buildings.getBuilding(BuildingID.values()[((Integer) event.getEventData(EventDataFields.BUILDING_ID)).intValue()]).getName(), name});
                break;
            default:
                string = "";
                break;
        }
        this.message.setText(string);
        this.message.setVisible(true);
        this.message.setPosition(96.0f, 43.0f - (this.message.getHeight() / 2.0f));
        if (systemObject instanceof Planet) {
            this.planetImage.setVisible(true);
            Planet planet = (Planet) systemObject;
            int imageIndex = planet.getClimate().getImageIndex(planet.getImageIndex());
            this.planetImage.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.planetsTextureRegion[planet.getClimate().getID()]);
            this.planetImage.setCurrentTileIndex(imageIndex);
            if (planet.hasRing()) {
                this.ring.setVisible(true);
                this.ring.setPosition(-21.5f, this.planetImage.getY() - 21.5f);
                this.ring.setCurrentTileIndex(planet.getRingImageIndex());
            }
        } else if (systemObject instanceof GasGiant) {
            this.planetImage.setVisible(true);
            GasGiant gasGiant = (GasGiant) systemObject;
            int imageIndex2 = gasGiant.getClimate().getImageIndex(gasGiant.getImageIndex());
            this.planetImage.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.planetsTextureRegion[gasGiant.getClimate().getID()]);
            this.planetImage.setCurrentTileIndex(imageIndex2);
            if (gasGiant.hasRing()) {
                this.ring.setVisible(true);
                this.ring.setPosition(-21.5f, 21.5f);
                this.ring.setCurrentTileIndex(gasGiant.getRingImageIndex());
            }
        } else {
            this.asteroidBeltImage.setVisible(true);
            this.asteroidBeltImage.setCurrentTileIndex(((AsteroidBelt) systemObject).getImageIndex());
        }
    }

    public void setSystemExploredEvent(Event event) {
        setVisible(true);
        setEntitiesHidden();
        StarSystem starSystem = this.game.galaxy.getStarSystem(((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue());
        this.star.setVisible(true);
        int ordinal = (starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4);
        this.star.animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
        this.message.setText(this.game.getActivity().getString(R.string.events_system_explored, new Object[]{starSystem.getName()}));
        this.message.setVisible(true);
        this.message.setPosition(96.0f, 43.0f - (this.message.getHeight() / 2.0f));
    }

    public void setTechBreakthrough(Event event) {
        String string;
        setVisible(true);
        setEntitiesHidden();
        int intValue = ((Integer) event.getEventData(EventDataFields.TECH_ID)).intValue();
        int intValue2 = ((Integer) event.getEventData(EventDataFields.TECH_FROM)).intValue();
        Tech tech = this.game.getCurrentEmpire().getTech().getTech(TechID.getTechID(intValue));
        this.techIcon.setVisible(true);
        this.techIcon.set(this.game.getCurrentPlayer(), tech);
        if (intValue2 == 0) {
            string = this.game.getActivity().getString(R.string.events_tech_from_scientists, new Object[]{tech.getName()});
        } else if (intValue2 == 1) {
            string = this.game.getActivity().getString(R.string.events_tech_from_diplomats, new Object[]{tech.getName()});
        } else if (intValue2 != 2) {
            string = intValue2 != 3 ? "" : this.game.getActivity().getString(R.string.events_tech_from_explorers, new Object[]{tech.getName()});
        } else {
            string = this.game.getActivity().getString(R.string.events_tech_from_troops, new Object[]{tech.getName()});
        }
        this.message.setText(string);
        this.message.setVisible(true);
        this.message.setPosition(96.0f, 43.0f - (this.message.getHeight() / 2.0f));
    }
}
