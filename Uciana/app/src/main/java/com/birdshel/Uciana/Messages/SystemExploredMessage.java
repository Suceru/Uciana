package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.StarSystems.StarSystem;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SystemExploredMessage implements Message {
    private final boolean isExplored;
    private final int systemID;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Messages.SystemExploredMessage$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1372a;

        static {
            int[] iArr = new int[SystemObjectType.values().length];
            f1372a = iArr;
            try {
                iArr[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1372a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1372a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public SystemExploredMessage(int i, boolean z) {
        this.systemID = i;
        this.isExplored = z;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        StarSystem starSystem = game.galaxy.getStarSystem(this.systemID);
        Entity sprite = new Sprite(0.0f, 240.0f, game.graphics.sunTextureRegion, buffer);
        sprite.setColor(starSystem.getStarType().getColor());
        sprite.setScaleCenter(0.0f, 0.0f);
        sprite.setScale(0.333f);
        messageOverlay.addElement(sprite);
        int i = 0;
        for (SystemObject systemObject : starSystem.getSystemObjects()) {
            int i2 = AnonymousClass1.f1372a[systemObject.getSystemObjectType().ordinal()];
            if (i2 == 1) {
                Planet planet = (Planet) systemObject;
                PlanetSprite planetSprite = game.planetSpritePool.get();
                planetSprite.setMoonRange(WeaponStats.NOVA_BOMB_MAX_DAMAGE, 180);
                planetSprite.setPlanet(planet, planet.getScale(game.systemScene), Moon.getScale(game.systemScene));
                planetSprite.setPosition((i * 175) + 135 + 106, 360.0f);
                messageOverlay.addElement(planetSprite);
            } else if (i2 == 2) {
                GasGiant gasGiant = (GasGiant) systemObject;
                PlanetSprite planetSprite2 = game.planetSpritePool.get();
                planetSprite2.setMoonRange(WeaponStats.NOVA_BOMB_MAX_DAMAGE, 180);
                planetSprite2.setGasGiant(gasGiant, gasGiant.getScale(game.systemScene), Moon.getScale(game.systemScene));
                planetSprite2.setPosition((i * 175) + 135 + 106, 360.0f);
                messageOverlay.addElement(planetSprite2);
            } else if (i2 == 3) {
                TiledSprite tiledSprite = new TiledSprite((i * 175) + 135 + 50, 240.0f, game.graphics.asteroidBeltsTexture, buffer);
                tiledSprite.setScaleCenter(0.0f, 0.0f);
                tiledSprite.setScale(0.333f);
                tiledSprite.setCurrentTileIndex(((AsteroidBelt) systemObject).getImageIndex());
                messageOverlay.addElement(tiledSprite);
            }
            if (systemObject.isOccupied()) {
                TiledSprite tiledSprite2 = new TiledSprite(0.0f, 460.0f, game.graphics.empireColors, buffer);
                tiledSprite2.setHeight(10.0f);
                tiledSprite2.setCurrentTileIndex(systemObject.getOccupier());
                if (systemObject.hasColony()) {
                    tiledSprite2.setX((i * 175) + 135 + 15 + 15);
                    tiledSprite2.setWidth(145.0f);
                } else {
                    tiledSprite2.setX((i * 175) + 135 + 51 + 15);
                    tiledSprite2.setWidth(72.0f);
                }
                messageOverlay.addElement(tiledSprite2);
            }
            i++;
        }
        Text text = new Text(0.0f, 250.0f, game.fonts.smallFont, this.isExplored ? game.getActivity().getString(R.string.system_explored_have, new Object[]{starSystem.getName()}) : game.getActivity().getString(R.string.system_explored_name, new Object[]{starSystem.getName()}), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text);
        messageOverlay.setMessageType(MessageType.SYSTEM_DISCOVERY);
        if (this.isExplored) {
            messageOverlay.addAction(MessageAction.OPEN_SYSTEM);
            messageOverlay.addAction(MessageAction.CLOSE);
        }
        Map<MessageActionData, Object> hashMap = new HashMap<>();
        hashMap.put(MessageActionData.SYSTEM_ID, Integer.valueOf(this.systemID));
        messageOverlay.setMessageActionData(hashMap);
    }
}
