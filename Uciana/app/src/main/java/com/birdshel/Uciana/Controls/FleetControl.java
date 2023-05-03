package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Elements.FleetControlElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ScaleAtModifier;
import org.andengine.entity.particle.BatchedSpriteParticleSystem;
import org.andengine.entity.particle.emitter.RectangleParticleEmitter;
import org.andengine.entity.particle.initializer.ScaleParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ExpireParticleInitializer;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.sprite.UncoloredSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FleetControl extends ExtendedScene {
    private final VertexBufferObjectManager bufferManager;
    private TiledSprite close;
    private Text destination;
    private Text eta;
    private Sprite fleetBlackBackground;
    private TiledSprite fleetEmpireColorBackground;
    private String fleetID;
    private RectangleParticleEmitter particleEmitter;
    private BatchedSpriteParticleSystem particleSystem;
    private Text selectedShipCount;
    private TiledSprite shipSelectButton;
    private int shipTypeCount;
    private Text status;
    private Text totalShipCount;
    private TiledSprite turnsIcon;
    private final float xOffset;
    private final FleetControlElement[] fleetControlElements = new FleetControlElement[8];
    private final VelocityParticleInitializer<UncoloredSprite> warp = new VelocityParticleInitializer<>(0.0f, 0.0f, 0.0f, 0.0f);
    private boolean isOpen = false;
    private Point positionWhenTouched = new Point(0.0f, 0.0f);
    private Point currentPosition = new Point(0.0f, 0.0f);

    public FleetControl(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        setBackgroundEnabled(false);
        this.B = game;
        this.bufferManager = vertexBufferObjectManager;
        this.xOffset = f2;
        createControlBackground();
        createWarpEffect();
        createMoveIcon();
        createShipCounts();
        createFleetStatuses();
        createShipsAndCounts();
        createButtons();
        setScale(0.0f);
    }

    private boolean checkActionUp(Point point) {
        if (isClicked(this.shipSelectButton, point)) {
            shipSelectButtonPressed();
            return true;
        } else if (this.B.getCurrentPlayer() == this.B.fleets.get(this.fleetID).empireID && point.getX() > 70.0f && point.getX() < getWidth() - 180.0f) {
            shipTypePressed(point);
            return true;
        } else if (isClicked(this.close, point)) {
            closeButtonPressed();
            return true;
        } else {
            return false;
        }
    }

    private void closeButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.getCurrentEmpire().setSelectedFleetID("none");
        this.B.galaxyScene.selectFleet(this.fleetID);
    }

    private void createButtons() {
        this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.PRESSED.ordinal(), false);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 32.0f, this.B.graphics.buttonsTexture, this.bufferManager);
        this.shipSelectButton = tiledSprite;
        tiledSprite.setCurrentTileIndex(ButtonsEnum.SHIP_SELECT.ordinal());
        attachChild(this.shipSelectButton);
        this.F.add(this.shipSelectButton);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 50.0f, this.B.graphics.gameIconsTexture, this.bufferManager);
        this.close = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(GameIconEnum.CLOSE.ordinal());
        this.close.setScaleCenter(0.0f, 0.0f);
        this.close.setScale(0.5f);
        attachChild(this.close);
    }

    private void createControlBackground() {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager);
        this.fleetEmpireColorBackground = tiledSprite;
        tiledSprite.setHeight(150.0f);
        attachChild(this.fleetEmpireColorBackground);
        Sprite sprite = new Sprite(0.0f, 0.0f, this.B.graphics.blackenedBackgroundTexture, this.bufferManager);
        this.fleetBlackBackground = sprite;
        sprite.setHeight(150.0f);
        this.fleetBlackBackground.setAlpha(0.8f);
        attachChild(this.fleetBlackBackground);
    }

    private void createFleetStatuses() {
        Text text = new Text(5.0f, 8.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.eta = text;
        attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 5.0f, this.B.graphics.infoIconsTexture, this.bufferManager);
        this.turnsIcon = tiledSprite;
        tiledSprite.setSize(25.0f, 25.0f);
        this.turnsIcon.setCurrentTileIndex(InfoIconEnum.TURN.ordinal());
        attachChild(this.turnsIcon);
        Text text2 = new Text(0.0f, 8.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.destination = text2;
        attachChild(text2);
        Text text3 = new Text(5.0f, 107.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.status = text3;
        text3.setColor(Color.RED);
        this.status.setAlpha(0.8f);
        attachChild(this.status);
    }

    private void createMoveIcon() {
        TiledSprite tiledSprite = new TiledSprite(10.0f, 50.0f, this.B.graphics.gameIconsTexture, this.bufferManager);
        tiledSprite.setCurrentTileIndex(GameIconEnum.MOVE.ordinal());
        tiledSprite.setScaleCenter(0.0f, 0.0f);
        tiledSprite.setSize(25.0f, 50.0f);
        tiledSprite.setAlpha(0.5f);
        attachChild(tiledSprite);
    }

    private void createShipCounts() {
        Text text = new Text(70.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.selectedShipCount = text;
        attachChild(text);
        Text text2 = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.bufferManager);
        this.totalShipCount = text2;
        attachChild(text2);
    }

    private void createShipsAndCounts() {
        for (int i = 0; i < 8; i++) {
            this.fleetControlElements[i] = new FleetControlElement(this.B, this.bufferManager);
            this.fleetControlElements[i].setX((i * 100) + 45);
            attachChild(this.fleetControlElements[i]);
        }
    }

    private void createWarpEffect() {
        this.particleEmitter = new RectangleParticleEmitter(100.0f, 100.0f, 200.0f, 200.0f);
        BatchedSpriteParticleSystem batchedSpriteParticleSystem = new BatchedSpriteParticleSystem(this.particleEmitter, 50.0f, 100.0f, 400, this.B.graphics.particleTexture, this.bufferManager);
        this.particleSystem = batchedSpriteParticleSystem;
        batchedSpriteParticleSystem.addParticleInitializer(new ExpireParticleInitializer(2.0f));
        this.particleSystem.addParticleInitializer(new ScaleParticleInitializer(3.0f, 5.0f));
        this.particleSystem.addParticleModifier(new AlphaParticleModifier(1.0f, 3.0f, 0.9f, 0.0f));
        this.particleSystem.addParticleInitializer(this.warp);
        attachChild(this.particleSystem);
    }

    private boolean hasMoved() {
        if (this.fleetBlackBackground.isVisible()) {
            return false;
        }
        float x = this.currentPosition.getX() - this.positionWhenTouched.getX();
        if (x < -20.0f || x > 20.0f) {
            return true;
        }
        float y = this.currentPosition.getY() - this.positionWhenTouched.getY();
        return y < -20.0f || y > 20.0f;
    }

    private void setControlPlacement(Fleet fleet, boolean z, float f2) {
        if (!z) {
            float f3 = f2 / 2.0f;
            setX(((this.B.getWidth() - 120) / 2.0f) - f3);
            this.currentPosition.setX(((this.B.getWidth() - 120) / 2.0f) - f3);
            setY(0.0f);
            this.currentPosition.setY(0.0f);
            if (fleet.getPosition().getY() < 360.0f) {
                setY(525.0f);
                this.currentPosition.setY(525.0f);
            }
        } else if (this.B.galaxyScene.fleetControl.getX() + getWidth() > this.B.getWidth() - 120) {
            setX((this.B.getWidth() - 120) - getWidth());
            this.currentPosition.setX((this.B.getWidth() - 120) - getWidth());
        }
    }

    private void setElementsInvisible() {
        this.particleSystem.reset();
        this.particleSystem.setVisible(false);
        this.particleSystem.setContainerEnabled(false);
        this.eta.setVisible(false);
        this.turnsIcon.setVisible(false);
        this.destination.setVisible(false);
        this.status.setVisible(false);
        this.selectedShipCount.setVisible(false);
    }

    private void setEta(Fleet fleet) {
        this.eta.setText(Integer.toString(fleet.getETA()));
        this.eta.setVisible(true);
        this.turnsIcon.setX(this.eta.getX() + this.eta.getWidthScaled() + 5.0f);
        this.turnsIcon.setVisible(true);
        this.destination.setX(this.turnsIcon.getX() + this.turnsIcon.getWidthScaled() + 5.0f);
        String string = GameData.activity.getString(R.string.galaxy_select_unknown);
        if (GameData.getCurrentEmpire().isDiscoveredSystem(fleet.getSystemID())) {
            string = GameData.galaxy.getStarSystem(fleet.getSystemID()).getName();
        }
        this.destination.setText(string);
        this.destination.setVisible(true);
    }

    private void setShipTypeIcons(Fleet fleet, List<String> list) {
        int i = 0;
        for (FleetControlElement fleetControlElement : this.fleetControlElements) {
            fleetControlElement.setVisible(false);
        }
        this.shipTypeCount = 0;
        int[] countOfEachType = fleet.getCountOfEachType(list, true);
        for (int i2 = 7; i2 > -1; i2--) {
            ShipType shipType = ShipType.getShipType(i2);
            if (countOfEachType[shipType.id] != 0) {
                this.shipTypeCount++;
                this.fleetControlElements[i].setVisible(true);
                this.fleetControlElements[i].set(fleet, shipType, countOfEachType[shipType.id]);
                i++;
            }
        }
    }

    private void setStatus(String str) {
        this.status.setVisible(true);
        this.status.setText(str);
    }

    private void shipSelectButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.galaxyScene.showShipSelectMenu();
    }

    private void shipTypePressed(Point point) {
        int x = ((int) (point.getX() - 70.0f)) / 100;
        Game game = this.B;
        List<String> list = game.galaxyScene.selectedShipIDs;
        ShipType shipType = ShipType.SCOUT;
        Fleet fleet = game.fleets.get(this.fleetID);
        int[] countOfEachType = fleet.getCountOfEachType(list, true);
        int i = 0;
        int i2 = 7;
        while (true) {
            if (i2 <= -1) {
                break;
            }
            ShipType shipType2 = ShipType.getShipType(i2);
            if (countOfEachType[shipType2.id] != 0) {
                if (x == i) {
                    shipType = shipType2;
                    break;
                }
                i++;
            }
            i2--;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (fleet.getShip(str).getShipType() == shipType) {
                arrayList.add(str);
            }
        }
        GalaxyScene galaxyScene = this.B.galaxyScene;
        galaxyScene.selectedShipIDs = arrayList;
        galaxyScene.showShipCount();
        setControl(this.fleetID, true);
        this.B.sounds.playBoxPressSound();
        Game game2 = this.B;
        game2.vibrate(game2.BUTTON_VIBRATE);
    }

    private void showSelectedShipCount(int i) {
        this.selectedShipCount.setVisible(true);
        this.selectedShipCount.setText(this.B.getActivity().getString(R.string.fleet_control_ship_selected_count, new Object[]{Integer.valueOf(i)}));
        this.selectedShipCount.setX((this.shipSelectButton.getX() + 60.0f) - (this.selectedShipCount.getWidthScaled() / 2.0f));
        Text text = this.selectedShipCount;
        text.setY(134.0f - (text.getHeightScaled() / 2.0f));
    }

    private void showWarpBackgroundEffect(Fleet fleet) {
        int engineSpeed;
        this.particleEmitter.setHeight(this.fleetBlackBackground.getHeightScaled());
        this.particleEmitter.setWidth(getWidth());
        this.particleEmitter.setCenterX(getWidth() / 2.0f);
        this.particleEmitter.setCenterY(this.fleetBlackBackground.getHeightScaled() / 2.0f);
        this.particleSystem.setVisible(true);
        this.particleSystem.setContainerEnabled(true);
        this.particleSystem.setContainer(10.0f, 0.0f, getWidth() - 20.0f, this.fleetBlackBackground.getHeightScaled() - 10.0f);
        int i = fleet.empireID;
        if (i == 9) {
            engineSpeed = 1;
        } else {
            engineSpeed = i == 8 ? 4 : this.B.empires.get(i).getTech().getEngineSpeed() - 2;
        }
        if (fleet.getDirection() == 1) {
            int i2 = engineSpeed * (-100);
            this.warp.setVelocityX(i2, i2 - 100);
            return;
        }
        int i3 = engineSpeed * 100;
        this.warp.setVelocityX(i3, i3 + 100);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public boolean checkInputOnControl(int i, Point point) {
        Point point2 = new Point((point.getX() - getX()) + this.xOffset, point.getY() - getY());
        super.checkInput(i, point2);
        if (!hasMoved() && i == 1) {
            return checkActionUp(point2);
        }
        return false;
    }

    public void close() {
        if (this.isOpen) {
            this.isOpen = false;
            unPressed();
            registerEntityModifier(new ScaleAtModifier(0.3f, 1.0f, 0.0f, getWidth() / 2.0f, getHeight() / 2.0f) { // from class: com.birdshel.Uciana.Controls.FleetControl.1
                {
                    FleetControl.this = this;
                }

                @Override // org.andengine.util.modifier.BaseModifier
                /* renamed from: n */
                public void c(IEntity iEntity) {
                    super.c(iEntity);
                    FleetControl.this.setScale(0.0f);
                }
            });
            registerEntityModifier(new AlphaModifier(0.3f, 1.0f, 0.0f));
        }
    }

    public float getHeight() {
        return this.fleetBlackBackground.getHeightScaled();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public float getWidth() {
        return this.fleetBlackBackground.getWidthScaled();
    }

    public void instantClose() {
        setScale(0.0f);
        this.isOpen = false;
    }

    public void open() {
        setScale(1.0f);
        setAlpha(1.0f);
        this.isOpen = true;
    }

    public void pressed() {
        this.fleetBlackBackground.setAlpha(0.5f);
        this.positionWhenTouched = new Point(this.currentPosition.getX(), this.currentPosition.getY());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void releasePoolElements(ExtendedScene extendedScene, Object obj) {
    }

    public void setControl(String str, boolean z) {
        int i;
        this.fleetID = str;
        Fleet fleet = this.B.fleets.get(str);
        List<String> list = this.B.galaxyScene.selectedShipIDs;
        setElementsInvisible();
        setShipTypeIcons(fleet, list);
        float f2 = (this.shipTypeCount * 100) + 70 + 180;
        this.fleetBlackBackground.setWidth(f2);
        this.fleetEmpireColorBackground.setWidth(i - 1);
        this.fleetEmpireColorBackground.setCurrentTileIndex(fleet.empireID);
        this.shipSelectButton.setX(i - 180);
        if (fleet.empireID == this.B.getCurrentPlayer()) {
            if (fleet.getSize() != list.size()) {
                showSelectedShipCount(list.size());
            }
            if (!fleet.canCommunicate()) {
                setStatus(this.B.getActivity().getString(R.string.fleet_control_comm_range));
            }
        }
        this.totalShipCount.setText(this.B.getActivity().getString(R.string.fleet_control_ship_total_count, new Object[]{Integer.valueOf(fleet.getSize())}));
        this.totalShipCount.setX((this.shipSelectButton.getX() + 60.0f) - (this.totalShipCount.getWidthScaled() / 2.0f));
        Text text = this.totalShipCount;
        text.setY(20.0f - (text.getHeightScaled() / 2.0f));
        this.close.setX(i - 55);
        setControlPlacement(fleet, z, f2);
        if (fleet.isMoving()) {
            showWarpBackgroundEffect(fleet);
            setEta(fleet);
        }
        open();
    }

    public void setControlPosition(float f2, float f3) {
        setPosition(f2, f3);
        this.currentPosition = new Point(f2, f3);
    }

    public void unPressed() {
        this.fleetBlackBackground.setAlpha(0.8f);
    }
}
