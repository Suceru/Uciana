package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.ShipDescription;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipDesignsOverlay extends ExtendedChildScene {
    private final int ITEM_SIZE;
    private boolean addToQueue;
    private final TiledSprite closeButton;
    private final List<TiledSprite> commandPointIcons;
    private final List<Text> commandPointsList;
    private final Sprite pressedSprite;
    private final Sprite pressedSprite2;
    private final List<TiledSprite> productionIcons;
    private final List<Text> productionTexts;
    private final List<Sprite> separators;
    private Ship ship;
    private final List<ShipDescription> shipDescriptions;
    private List<Ship> shipDesigns;
    private final List<TiledSprite> shipIcons;
    private final List<Text> shipNames;
    private final List<TiledSprite> shipTypeIcons;
    private final List<TiledSprite> turnIcons;
    private final List<Text> turnTexts;

    public ShipDesignsOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        int i;
        this.ITEM_SIZE = 105;
        this.separators = new ArrayList();
        this.shipIcons = new ArrayList();
        this.shipNames = new ArrayList();
        this.shipTypeIcons = new ArrayList();
        this.turnTexts = new ArrayList();
        this.turnIcons = new ArrayList();
        this.shipDescriptions = new ArrayList();
        this.productionTexts = new ArrayList();
        this.productionIcons = new ArrayList();
        this.commandPointsList = new ArrayList();
        this.commandPointIcons = new ArrayList();
        TiledSprite w = w(1160.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w;
        n(w);
        Text u = u(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.ship_design_select), this.F, vertexBufferObjectManager);
        u.setX(640.0f - (u.getWidthScaled() / 2.0f));
        u.setY(45.0f - (u.getHeightScaled() / 2.0f));
        Sprite t = t(300.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.pressedSprite = t;
        t.setSize(700.0f, 100.0f);
        Sprite t2 = t(300.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.pressedSprite2 = t2;
        t2.setSize(700.0f, 100.0f);
        for (int i2 = 0; i2 < 6; i2++) {
            int i3 = (i2 * 105) + 90;
            float f2 = i3;
            Sprite sprite = new Sprite(300.0f, f2, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
            sprite.setAlpha(0.6f);
            sprite.setSize(700.0f, 100.0f);
            Sprite t3 = t(300.0f, f2, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager, true);
            t3.setSize(700.0f, 100.0f);
            this.separators.add(t3);
            this.shipIcons.add(x(300.0f, f2, game.graphics.shipsTextures[0], vertexBufferObjectManager, true));
            float f3 = i3 + 30;
            this.shipNames.add(u(410.0f, f3, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager));
            this.shipTypeIcons.add(x(300.0f, f2, game.graphics.infoIconsTexture, vertexBufferObjectManager, true));
            Text u2 = u(0.0f, f3, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
            this.turnTexts.add(u2);
            this.turnIcons.add(w(965.0f, f3, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.TURN.ordinal(), true));
            this.productionIcons.add(w(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.PRODUCTION.ordinal(), true));
            Text u3 = u(0.0f, 0.0f, game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager);
            this.productionTexts.add(u3);
            this.commandPointIcons.add(w(u3.getX() - 40.0f, u3.getY() - 7.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.COMMAND_POINTS.ordinal(), true));
            this.commandPointsList.add(u(0.0f, u2.getY(), game.fonts.smallInfoFont, this.E, this.F, vertexBufferObjectManager));
            ShipDescription shipDescription = new ShipDescription(game, vertexBufferObjectManager);
            shipDescription.setPosition(410.0f, i + 100 + 50);
            attachChild(shipDescription);
            this.shipDescriptions.add(shipDescription);
        }
        this.J = new MessageOverlay(game, vertexBufferObjectManager);
    }

    private void checkPressed(Point point) {
        for (int i = 0; i < 6; i++) {
            if (q(this.separators.get(i), point)) {
                this.pressedSprite.setVisible(true);
                this.pressedSprite2.setVisible(true);
                this.pressedSprite.setY(this.separators.get(i).getY());
                this.pressedSprite2.setY(this.separators.get(i).getY());
                return;
            }
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void shipDesignPressed(int i) {
        this.C.productionScene.setRefitShip(this.shipDesigns.get(i), this.ship, this.addToQueue);
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 0) {
            checkPressed(point);
            return;
        }
        if (i != 1) {
            if (i != 2) {
                return;
            }
            this.pressedSprite.setVisible(false);
            this.pressedSprite2.setVisible(false);
            checkPressed(point);
            return;
        }
        this.pressedSprite.setVisible(false);
        this.pressedSprite2.setVisible(false);
        if (q(this.closeButton, point)) {
            closeButtonPressed();
            return;
        }
        for (int i2 = 0; i2 < 6; i2++) {
            if (q(this.separators.get(i2), point)) {
                shipDesignPressed(i2);
                return;
            }
            if (s(this.separators.get(i2), point, 0.0f, 0.0f) && this.shipIcons.get(i2).isVisible()) {
                showMessage(new TextMessage(this.C.getActivity().getString(R.string.ship_design_ship_yard_message)));
            }
        }
    }

    public void setOverlay(Colony colony, Ship ship, boolean z, boolean z2) {
        this.ship = ship;
        this.addToQueue = z;
        this.shipDesigns = this.C.getCurrentEmpire().getShipDesigns();
        int i = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            this.separators.get(i2).setAlpha(0.4f);
            this.shipIcons.get(i2).setVisible(false);
            this.shipIcons.get(i2).setPosition(300.0f, (i2 * 105) + 90);
            this.shipNames.get(i2).setVisible(false);
            this.turnTexts.get(i2).setVisible(false);
            this.turnIcons.get(i2).setVisible(false);
            this.shipTypeIcons.get(i2).setVisible(false);
            this.productionIcons.get(i2).setVisible(false);
            this.productionTexts.get(i2).setVisible(false);
            this.commandPointIcons.get(i2).setVisible(false);
            this.commandPointsList.get(i2).setVisible(false);
            this.shipDescriptions.get(i2).setVisible(false);
            this.shipIcons.get(i2).setAlpha(1.0f);
            this.shipNames.get(i2).setAlpha(1.0f);
            this.turnTexts.get(i2).setAlpha(1.0f);
            this.turnIcons.get(i2).setAlpha(1.0f);
            this.shipTypeIcons.get(i2).setAlpha(1.0f);
            this.productionIcons.get(i2).setAlpha(1.0f);
            this.productionTexts.get(i2).setAlpha(1.0f);
            this.commandPointIcons.get(i2).setAlpha(1.0f);
            this.commandPointsList.get(i2).setAlpha(1.0f);
            this.shipDescriptions.get(i2).setAlpha(1.0f);
        }
        for (Ship ship2 : this.shipDesigns) {
            this.separators.get(i).setAlpha(0.8f);
            int icon = ship2.getShipType().getIcon(this.C.getCurrentPlayer(), ship2.getHullDesign());
            Game game = this.C;
            this.shipIcons.get(i).setTiledTextureRegion((ITiledTextureRegion) game.graphics.shipsTextures[game.getCurrentPlayer()]);
            this.shipIcons.get(i).setCurrentTileIndex(icon);
            float selectScreenSize = ship2.getShipType().getSelectScreenSize();
            this.shipIcons.get(i).setSize(selectScreenSize, selectScreenSize);
            float f2 = 50.0f - (selectScreenSize / 2.0f);
            this.shipIcons.get(i).setX(this.shipIcons.get(i).getX() + f2);
            this.shipIcons.get(i).setY(this.shipIcons.get(i).getY() + f2);
            this.shipIcons.get(i).setVisible(true);
            this.shipNames.get(i).setText(ship2.getName());
            this.shipNames.get(i).setY(((i * 105) + 120) - (this.shipNames.get(i).getHeightScaled() / 2.0f));
            this.shipNames.get(i).setVisible(true);
            this.shipTypeIcons.get(i).setCurrentTileIndex(InfoIconEnum.getShipIcon(ship2.getShipType()));
            this.shipTypeIcons.get(i).setVisible(true);
            this.shipDescriptions.get(i).set(ship2);
            this.shipDescriptions.get(i).setVisible(true);
            this.turnTexts.get(i).setText(this.C.getActivity().getString(R.string.ship_design_never));
            if (colony.getProductionPerTurn() > 0) {
                double productionCost = ship2.getProductionCost();
                double productionPerTurn = colony.getProductionPerTurn();
                Double.isNaN(productionCost);
                Double.isNaN(productionPerTurn);
                int ceil = (int) Math.ceil(productionCost / productionPerTurn);
                if (ceil <= 1) {
                    this.turnTexts.get(i).setText("1");
                } else {
                    this.turnTexts.get(i).setText(Integer.toString(ceil));
                }
            }
            this.turnTexts.get(i).setX(955.0f - this.turnTexts.get(i).getWidthScaled());
            this.turnTexts.get(i).setY(this.shipNames.get(i).getY());
            this.turnTexts.get(i).setVisible(true);
            this.turnIcons.get(i).setY(this.shipNames.get(i).getY() - 5.0f);
            this.turnIcons.get(i).setVisible(true);
            this.productionIcons.get(i).setPosition(this.turnTexts.get(i).getX() - 35.0f, this.turnTexts.get(i).getY() - 7.0f);
            this.productionIcons.get(i).setVisible(true);
            this.productionTexts.get(i).setText(Integer.toString(ship2.getProductionCost()));
            this.productionTexts.get(i).setPosition((this.productionIcons.get(i).getX() - this.productionTexts.get(i).getWidthScaled()) - 5.0f, this.turnTexts.get(i).getY());
            this.productionTexts.get(i).setVisible(true);
            this.commandPointsList.get(i).setVisible(true);
            this.commandPointsList.get(i).setText(Integer.toString(ship.getShipType().getCommandPointCost()));
            this.commandPointsList.get(i).setX((this.commandPointIcons.get(i).getX() - this.commandPointsList.get(i).getWidthScaled()) - 4.0f);
            this.commandPointIcons.get(i).setVisible(true);
            if (!z2 && (ship2.getShipType() == ShipType.BATTLESHIP || ship2.getShipType() == ShipType.DREADNOUGHT)) {
                this.separators.get(i).setAlpha(0.4f);
                this.shipIcons.get(i).setAlpha(0.4f);
                this.shipNames.get(i).setAlpha(0.4f);
                this.turnTexts.get(i).setAlpha(0.4f);
                this.turnIcons.get(i).setAlpha(0.4f);
                this.shipTypeIcons.get(i).setAlpha(0.4f);
                this.productionIcons.get(i).setAlpha(0.4f);
                this.productionTexts.get(i).setAlpha(0.4f);
                this.commandPointIcons.get(i).setAlpha(0.4f);
                this.commandPointsList.get(i).setAlpha(0.4f);
                this.shipDescriptions.get(i).setAlpha(0.4f);
            }
            i++;
        }
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
