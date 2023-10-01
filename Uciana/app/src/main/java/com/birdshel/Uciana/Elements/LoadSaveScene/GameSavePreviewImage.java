package com.birdshel.Uciana.Elements.LoadSaveScene;

import android.util.SparseIntArray;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.SupportedLocales;
import com.birdshel.Uciana.SaveGameData.PreviewData;
import com.birdshel.Uciana.Ships.FleetIconData;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.GalaxySize;
import com.birdshel.Uciana.StarSystems.Nebula;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import com.birdshel.Uciana.StarSystems.Star;
import com.birdshel.Uciana.StarSystems.SystemNameDisplay;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameSavePreviewImage extends Entity {
    private final Game game;
    private final Nebulas nebulaBackground;
    private final Line[] wormholes = new Line[13];
    private final List<AnimatedSprite> stars = new ArrayList();
    private final Sprite[] blackholes = new Sprite[50];
    private final TiledSprite[] spaceRifts = new TiledSprite[50];
    private final TiledSprite[][] systemNameBackgrounds = (TiledSprite[][]) Array.newInstance(TiledSprite.class, 50, 5);
    private final Text[] systemNames = new Text[50];
    private final ArrayList<ShipSprite> fleetIcons = new ArrayList<>();

    public GameSavePreviewImage(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.backgroundTexture, vertexBufferObjectManager);
        sprite.setSize(1280.0f, 720.0f);
        attachChild(sprite);
        Nebulas nebulas = new Nebulas(game, vertexBufferObjectManager);
        this.nebulaBackground = nebulas;
        attachChild(nebulas);
        for (int i = 0; i < 13; i++) {
            this.wormholes[i] = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
            this.wormholes[i].setAlpha(0.4f);
            this.wormholes[i].setLineWidth(2.0f);
            attachChild(this.wormholes[i]);
        }
        for (int i2 = 0; i2 < 50; i2++) {
            this.blackholes[i2] = new Sprite(0.0f, 0.0f, game.graphics.blackholeTexture, vertexBufferObjectManager);
            this.blackholes[i2].setAlpha(0.8f);
            attachChild(this.blackholes[i2]);
            this.spaceRifts[i2] = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
            this.spaceRifts[i2].setCurrentTileIndex(GameIconEnum.SPACE_RIFT.ordinal());
            attachChild(this.spaceRifts[i2]);
            for (int i3 = 0; i3 < 5; i3++) {
                this.systemNameBackgrounds[i2][i3] = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
                attachChild(this.systemNameBackgrounds[i2][i3]);
            }
            this.systemNames[i2] = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####################", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
            if (game.getLocale() == SupportedLocales.JAPANESE) {
                this.systemNames[i2].setScale(0.8f);
            }
            attachChild(this.systemNames[i2]);
        }
    }

    public void getPoolElements() {
        for (int i = 0; i < 50; i++) {
            AnimatedSprite animatedSprite = this.game.starSpritePool.get();
            animatedSprite.setVisible(false);
            attachChild(animatedSprite);
            this.stars.add(animatedSprite);
        }
        for (int i2 = 0; i2 < 75; i2++) {
            ShipSprite shipSprite = this.game.shipSpritePool.get();
            this.fleetIcons.add(shipSprite);
            attachChild(shipSprite);
        }
    }

    public void releasePoolElements() {
        for (AnimatedSprite animatedSprite : this.stars) {
            detachChild(animatedSprite);
            this.game.starSpritePool.push(animatedSprite);
        }
        this.stars.clear();
        Iterator<ShipSprite> it = this.fleetIcons.iterator();
        while (it.hasNext()) {
            ShipSprite next = it.next();
            detachChild(next);
            this.game.shipSpritePool.push(next);
        }
        this.fleetIcons.clear();
    }

    public void set(List<Nebula> list, PreviewData previewData) {
        int i;
        int i2;
        boolean z;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int i3 = 0;
        for (int i4 = 0; i4 < 7; i4++) {
            this.game.graphics.setShipIconsTextures(i4, previewData.shipStyles.get(i4).intValue(), this.game.getActivity());
        }
        int i5 = 0;
        while (true) {
            i = 13;
            if (i5 >= 13) {
                break;
            }
            this.wormholes[i5].setVisible(false);
            i5++;
        }
        for (int i6 = 0; i6 < 50; i6++) {
            this.stars.get(i6).setVisible(false);
            this.blackholes[i6].setVisible(false);
            this.spaceRifts[i6].setVisible(false);
            this.systemNames[i6].setVisible(false);
            for (int i7 = 0; i7 < 5; i7++) {
                this.systemNameBackgrounds[i6][i7].setVisible(false);
            }
        }
        Iterator<ShipSprite> it = this.fleetIcons.iterator();
        while (it.hasNext()) {
            it.next().setVisible(false);
        }
        GalaxySize galaxySize = previewData.galaxySize;
        this.nebulaBackground.setFromSave(list);
        Iterator<Star> it2 = previewData.stars.iterator();
        int i8 = 0;
        while (true) {
            i2 = 3;
            z = true;
            if (!it2.hasNext()) {
                break;
            }
            Star next = it2.next();
            int ordinal = (next.getType().ordinal() * 12) + (next.getShape() * 4);
            AnimatedSprite animatedSprite = this.stars.get(i8);
            animatedSprite.setVisible(true);
            animatedSprite.setPosition(next.getPosition().getX(), next.getPosition().getY());
            animatedSprite.animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
            animatedSprite.setScaleCenter(0.0f, 0.0f);
            animatedSprite.setScale(next.getSize() * galaxySize.getSizeModifier());
            i8++;
        }
        Iterator<Blackhole> it3 = previewData.blackholes.iterator();
        int i9 = 0;
        while (true) {
            f2 = 2.0f;
            if (!it3.hasNext()) {
                break;
            }
            Blackhole next2 = it3.next();
            Point position = next2.getPosition();
            float x = position.getX() - (next2.getSize(galaxySize) * 0.25f);
            float y = position.getY() - (next2.getSize(galaxySize) * 0.25f);
            Sprite sprite = this.blackholes[i9];
            sprite.setVisible(true);
            sprite.setPosition(x, y);
            sprite.setSize(next2.getSize(galaxySize), next2.getSize(galaxySize));
            sprite.setVisible(true);
            sprite.setFlippedHorizontal(false);
            if (next2.hasAltSpinDirection()) {
                sprite.setFlippedHorizontal(true);
                f5 = 0.0f;
                f6 = 360.0f;
            } else {
                f5 = 360.0f;
                f6 = 0.0f;
            }
            sprite.setRotationCenter(sprite.getWidthScaled() / 2.0f, sprite.getHeightScaled() / 2.0f);
            sprite.clearEntityModifiers();
            sprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(next2.getSpinSpeed(), f5, f6)));
            i9++;
        }
        int i10 = 0;
        for (SpaceRift spaceRift : previewData.spaceRifts) {
            TiledSprite tiledSprite = this.spaceRifts[i10];
            tiledSprite.setPosition(spaceRift.getPosition().getX(), spaceRift.getPosition().getY());
            tiledSprite.setVisible(true);
            tiledSprite.setCurrentTileIndex(GameIconEnum.SPACE_RIFT.ordinal());
            tiledSprite.setSize(spaceRift.getSize(galaxySize), spaceRift.getSize(galaxySize));
            tiledSprite.setVisible(true);
            tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
            tiledSprite.setFlippedHorizontal(false);
            if (spaceRift.hasAltSpinDirection()) {
                tiledSprite.setFlippedHorizontal(true);
                f3 = 360.0f;
                f4 = 0.0f;
            } else {
                f3 = 0.0f;
                f4 = 360.0f;
            }
            tiledSprite.clearEntityModifiers();
            tiledSprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(spaceRift.getSpinSpeed(), f3, f4)));
            i10++;
        }
        List<Integer> list2 = previewData.discoveredLocations;
        int i11 = 0;
        for (Point point : previewData.wormholes) {
            if (list2.contains(Integer.valueOf((int) point.getX())) || list2.contains(Integer.valueOf((int) point.getY()))) {
                AnimatedSprite animatedSprite2 = this.stars.get((int) point.getX());
                float x2 = animatedSprite2.getX() + (animatedSprite2.getWidthScaled() / 2.0f);
                float y2 = animatedSprite2.getY() + (animatedSprite2.getWidthScaled() / 2.0f);
                AnimatedSprite animatedSprite3 = this.stars.get((int) point.getY());
                float x3 = animatedSprite3.getX() + (animatedSprite3.getWidthScaled() / 2.0f);
                float y3 = animatedSprite3.getY() + (animatedSprite3.getWidthScaled() / 2.0f);
                this.wormholes[i11].setVisible(true);
                this.wormholes[i11].setPosition(x2, y2, x3, y3);
            }
            i11++;
        }
        int i12 = 0;
        for (SystemNameDisplay systemNameDisplay : previewData.systemNameDisplays) {
            this.systemNames[i12].setVisible(z);
            this.systemNames[i12].setPosition(systemNameDisplay.getX(), systemNameDisplay.getY());
            this.systemNames[i12].setText(systemNameDisplay.getName());
            List<Integer> occupiers = systemNameDisplay.getOccupiers();
            int widthScaled = ((int) this.systemNames[i12].getWidthScaled()) + i;
            int heightScaled = ((int) this.systemNames[i12].getHeightScaled()) + i2;
            Point point2 = new Point(this.systemNames[i12].getX() - 5.0f, this.systemNames[i12].getY() - f2);
            SparseIntArray sparseIntArray = new SparseIntArray();
            for (Integer num : occupiers) {
                int intValue = num.intValue();
                sparseIntArray.put(intValue, sparseIntArray.get(intValue, i3) + 1);
                i3 = 0;
            }
            int i13 = 0;
            while (i13 < sparseIntArray.size()) {
                int keyAt = sparseIntArray.keyAt(i13);
                TiledSprite tiledSprite2 = this.systemNameBackgrounds[i12][i13];
                tiledSprite2.setVisible(z);
                tiledSprite2.setPosition(point2.getX(), point2.getY());
                tiledSprite2.setCurrentTileIndex(keyAt);
                float size = (int) (widthScaled * (sparseIntArray.get(keyAt) / occupiers.size()));
                tiledSprite2.setSize(size, heightScaled);
                point2.setX(point2.getX() + size);
                i13++;
                z = true;
            }
            i12++;
            i3 = 0;
            i = 13;
            i2 = 3;
            z = true;
            f2 = 2.0f;
        }
        int i14 = 0;
        for (FleetIconData fleetIconData : previewData.fleetIcons) {
            if (i14 >= 75) {
                return;
            }
            ShipSprite shipSprite = this.fleetIcons.get(i14);
            shipSprite.setVisible(true);
            shipSprite.setPosition(fleetIconData.getX(), fleetIconData.getY());
            shipSprite.setTextureRegion(this.game.graphics.shipsTextures[fleetIconData.getEmpireID()]);
            shipSprite.setCurrentTileIndex(fleetIconData.getIndex());
            shipSprite.hideThrusters();
            shipSprite.setSize(galaxySize.getShipSize());
            shipSprite.setRotationCenter(galaxySize.getShipSize() / 2.0f, galaxySize.getShipSize() / 2.0f);
            shipSprite.setRotation(fleetIconData.getRotation());
            i14++;
        }
    }
}
