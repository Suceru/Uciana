package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.StarSystem;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.particle.BatchedSpriteParticleSystem;
import org.andengine.entity.particle.emitter.RectangleParticleEmitter;
import org.andengine.entity.particle.initializer.ExpireParticleInitializer;
import org.andengine.entity.particle.initializer.ScaleParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.nio.CharBuffer;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GalaxySelectListElement extends Entity {
    private final ShipSprite fleetIcon;
    private final Text fleetText;
    private final Game game;
    private final BatchedSpriteParticleSystem particleSystem;
    private final VelocityParticleInitializer particleVelocity;
    private final float screenWidth;
    private final Text shipCountText;
    private final TiledSprite shipIcon;
    private final AnimatedSprite star;
    private final Text systemName;
    private final int ITEM_SIZE = 100;
    private final Text[] shipCounts = new Text[8];
    private final TiledSprite[] shipTypeIcons = new TiledSprite[8];

    public GalaxySelectListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        this.game = game;
        this.screenWidth = f2;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        float f3 = (f2 - 700.0f) / 2.0f;
        Sprite sprite = new Sprite(f3, 2.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        sprite.setSize(700.0f, 96.0f);
        sprite.setAlpha(0.6f);
        attachChild(sprite);
        RectangleParticleEmitter rectangleParticleEmitter = new RectangleParticleEmitter(350.0f, 50.0f, 700.0f, 100.0f);
        rectangleParticleEmitter.setCenterX(250.0f);
        BatchedSpriteParticleSystem batchedSpriteParticleSystem = new BatchedSpriteParticleSystem(rectangleParticleEmitter, 50.0f, 100.0f, 400, game.graphics.particleTexture, vertexBufferObjectManager);
        this.particleSystem = batchedSpriteParticleSystem;
        VelocityParticleInitializer velocityParticleInitializer = new VelocityParticleInitializer(0.0f, 0.0f, 0.0f, 0.0f);
        this.particleVelocity = velocityParticleInitializer;
        batchedSpriteParticleSystem.addParticleInitializer(velocityParticleInitializer);
        batchedSpriteParticleSystem.addParticleInitializer(new ExpireParticleInitializer(2.0f));
        batchedSpriteParticleSystem.addParticleInitializer(new ScaleParticleInitializer(3.0f, 5.0f));
        batchedSpriteParticleSystem.addParticleModifier(new AlphaParticleModifier(1.0f, 3.0f, 0.9f, 0.0f));
        batchedSpriteParticleSystem.setContainerEnabled(true);
        batchedSpriteParticleSystem.setContainer(0.0f, 2.0f, 700.0f, 91.0f);
        sprite.attachChild(batchedSpriteParticleSystem);
        AnimatedSprite animatedSprite = new AnimatedSprite(0.0f, 0.0f, game.graphics.starsTexture, vertexBufferObjectManager);
        this.star = animatedSprite;
        attachChild(animatedSprite);
        float f4 = f3 + 100.0f + 10.0f;
        Text text = new Text(f4, 0.0f, game.fonts.infoFont, wrap, textOptions, vertexBufferObjectManager);
        this.systemName = text;
        attachChild(text);
        ShipSprite shipSprite = new ShipSprite(game, vertexBufferObjectManager);
        this.fleetIcon = shipSprite;
        shipSprite.setPosition(f3, 0.0f);
        attachChild(shipSprite);
        Font font = game.fonts.infoFont;
        HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;
        Text text2 = new Text(f4, 12.0f, font, "##########", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.shipCountText = text2;
        attachChild(text2);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.shipIcon = tiledSprite;
        tiledSprite.setCurrentTileIndex(InfoIconEnum.SHIP.ordinal());
        attachChild(tiledSprite);
        Text text3 = new Text(f4, 0.0f, game.fonts.smallInfoFont, wrap, new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.fleetText = text3;
        attachChild(text3);
        for (int i = 0; i < 8; i++) {
            IEntity text4 = new Text(0.0f, 12.0f, game.fonts.infoFont, "##########", vertexBufferObjectManager);
            this.shipCounts[i] = text4;
            attachChild(text4);
            IEntity tiledSprite2 = new TiledSprite(0.0f, 10.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
            this.shipTypeIcons[i] = tiledSprite2;
            attachChild(tiledSprite2);
        }
    }

    private void setElementsInvisible() {
        this.particleSystem.clearParticles();
        this.star.setVisible(false);
        this.particleSystem.setVisible(false);
        this.systemName.setVisible(false);
        this.fleetIcon.setVisible(false);
        this.shipCountText.setVisible(false);
        this.shipIcon.setVisible(false);
        this.fleetText.setVisible(false);
        for (int i = 0; i < 8; i++) {
            this.shipCounts[i].setVisible(false);
            this.shipTypeIcons[i].setVisible(false);
        }
    }

    public void set(StarSystem starSystem) {
        setElementsInvisible();
        int ordinal = (starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4);
        this.star.setVisible(true);
        this.star.setPosition((this.screenWidth - 700.0f) / 2.0f, 0.0f);
        this.star.setSize(100.0f, 100.0f);
        this.star.animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
        this.systemName.setVisible(true);
        if (this.game.getCurrentEmpire().isDiscoveredSystem(starSystem.getID())) {
            this.systemName.setText(starSystem.getName());
        } else {
            this.systemName.setText(this.game.getActivity().getString(R.string.galaxy_select_unknown));
        }
        this.systemName.setY(50.0f - (this.systemName.getHeight() / 2.0f));
    }

    public void set(Fleet fleet) {
        int i;
        int i2;
        setElementsInvisible();
        int i3 = 0;
        int i4 = 4;
        if (!fleet.isMoving()) {
            this.star.setVisible(true);
            this.star.setPosition(((this.screenWidth - 700.0f) / 2.0f) - 20.0f, -10.0f);
            this.star.setSize(66.666664f, 66.666664f);
            StarSystem starSystem = this.game.galaxy.getStarSystem(fleet.getSystemID());
            int ordinal = (starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4);
            this.star.animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
        } else {
            this.particleSystem.setVisible(true);
            int i5 = fleet.empireID;
            if (i5 == 9) {
                i4 = 1;
            } else if (i5 != 8) {
                i4 = this.game.empires.get(i5).getTech().getEngineSpeed() - 2;
            }
            if (fleet.getDirection() == 1) {
                this.particleVelocity.setVelocityX(i4 * (-100), i2 - 100);
            } else {
                this.particleVelocity.setVelocityX(i4 * 100, i + 100);
            }
        }
        this.fleetIcon.setVisible(true);
        Object[] largestShipTypeAndDesign = fleet.getLargestShipTypeAndDesign();
        this.fleetIcon.setShipIcon(fleet.empireID, (ShipType) largestShipTypeAndDesign[0], ((Integer) largestShipTypeAndDesign[1]).intValue(), 100.0f, fleet.getLargestShip().getSelectScreenSize(), fleet.getDirection(), fleet.isMoving());
        this.shipCountText.setVisible(true);
        this.shipCountText.setText(Integer.toString(fleet.getSize()));
        this.shipIcon.setVisible(true);
        this.shipIcon.setPosition(this.shipCountText.getX() + this.shipCountText.getWidthScaled() + 7.0f, this.shipCountText.getY() - 2.0f);
        this.fleetText.setVisible(true);
        this.fleetText.setText(fleet.getDestinationText());
        Text text = this.fleetText;
        text.setY(90.0f - text.getHeight());
        float x = this.shipIcon.getX() + this.shipIcon.getWidthScaled() + 20.0f;
        int[] countOfEachType = fleet.getCountOfEachType(fleet.getShipIDs(), true);
        for (int i6 = 7; i6 > -1; i6--) {
            ShipType shipType = ShipType.getShipType(i6);
            if (countOfEachType[shipType.id] != 0) {
                this.shipCounts[i3].setVisible(true);
                this.shipCounts[i3].setText(Integer.toString(countOfEachType[shipType.id]));
                this.shipCounts[i3].setX(x);
                float widthScaled = x + this.shipCounts[i3].getWidthScaled() + 8.0f;
                this.shipTypeIcons[i3].setVisible(true);
                this.shipTypeIcons[i3].setX(widthScaled);
                this.shipTypeIcons[i3].setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
                x = widthScaled + this.shipTypeIcons[i3].getWidthScaled() + 10.0f;
                i3++;
            }
        }
    }
}
