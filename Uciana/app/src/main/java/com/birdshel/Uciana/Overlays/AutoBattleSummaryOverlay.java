package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Elements.Battle.BattleLosses;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AutoBattleSummaryOverlay extends ExtendedChildScene {
    private final TiledSprite attackerBackground;
    private int attackerID;
    private final TiledSprite defenderBackground;
    private final Text noLossesAttacker;
    private final Text noLossesDefender;
    private final Text resultText;
    private final Text[] shipCounts;
    private final List<ShipSprite> shipIcons;
    private final TiledSprite[] shipTypeIcons;
    private int winner;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoBattleSummaryOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.shipIcons = new ArrayList();
        this.shipTypeIcons = new TiledSprite[20];
        this.shipCounts = new Text[20];
        this.G.setAlpha(0.5f);
        Sprite t = t(0.0f, 185.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 350.0f);
        t.setAlpha(0.9f);
        TiledSprite x = x(0.0f, 185.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.attackerBackground = x;
        x.setSize(getWidth() / 2.0f, 350.0f);
        x.setAlpha(0.08f);
        TiledSprite x2 = x(getWidth() / 2.0f, 185.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.defenderBackground = x2;
        x2.setSize(getWidth() / 2.0f, 350.0f);
        x2.setAlpha(0.08f);
        this.resultText = u(0.0f, 190.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        Text u = u(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.auto_battle_no_losses), this.F, vertexBufferObjectManager);
        this.noLossesAttacker = u;
        u.setX((((getWidth() / 2.0f) + 320.0f) - 610.0f) - (u.getWidthScaled() / 2.0f));
        u.setY(360.0f - (u.getHeightScaled() / 2.0f));
        Text u2 = u(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.auto_battle_no_losses), this.F, vertexBufferObjectManager);
        this.noLossesDefender = u2;
        u2.setX(((getWidth() / 2.0f) + 320.0f) - (u2.getWidthScaled() / 2.0f));
        u2.setY(360.0f - (u2.getHeightScaled() / 2.0f));
        Text u3 = u(0.0f, 220.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.auto_battle_ships_lost), this.F, vertexBufferObjectManager);
        u3.setX((getWidth() / 2.0f) - (u3.getWidthScaled() / 2.0f));
        for (int i = 0; i < 20; i++) {
            this.shipTypeIcons[i] = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
            attachChild(this.shipTypeIcons[i]);
            this.shipCounts[i] = new Text(0.0f, 0.0f, game.fonts.smallFont, "######", vertexBufferObjectManager);
            attachChild(this.shipCounts[i]);
        }
    }

    private void checkActionUp() {
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        this.C.attackScene.autoResultClosed(this.winner == this.attackerID);
    }

    private void placeShipIcon(BattleLosses battleLosses, int i, int i2, int i3, int i4, int i5) {
        float f2;
        float f3 = 230.0f;
        int i6 = 0;
        for (Map.Entry<ShipType, Integer> entry : battleLosses.getShipTypeAndCount().entrySet()) {
            float f4 = (i6 * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + i3 + i2;
            if (i6 > i4) {
                f2 = ((i6 - (i4 + 1)) * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + i5 + i2;
                f3 = 380.0f;
            } else {
                f2 = f4;
            }
            setShipIcon(battleLosses, i6, f2, f3, entry.getKey(), i, entry.getValue().intValue());
            i6++;
        }
    }

    private void setLosses(BattleLosses battleLosses, int i, int i2, Text text) {
        text.setVisible(battleLosses.isEmpty());
        if (battleLosses.numberOfShipTypes() < 5) {
            float numberOfShipTypes = 320 - (battleLosses.numberOfShipTypes() * 75);
            int i3 = 0;
            for (Map.Entry<ShipType, Integer> entry : battleLosses.getShipTypeAndCount().entrySet()) {
                setShipIcon(battleLosses, i3, (i3 * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + numberOfShipTypes + i2, 305.0f, entry.getKey(), i, entry.getValue().intValue());
                i3++;
            }
        } else if (battleLosses.numberOfShipTypes() == 5) {
            placeShipIcon(battleLosses, i, i2, 170, 1, 95);
        } else if (battleLosses.numberOfShipTypes() == 6) {
            placeShipIcon(battleLosses, i, i2, 95, 2, 95);
        } else if (battleLosses.numberOfShipTypes() == 7) {
            placeShipIcon(battleLosses, i, i2, 95, 3, 20);
        } else if (battleLosses.numberOfShipTypes() == 8) {
            placeShipIcon(battleLosses, i, i2, 0, 4, 0);
        }
    }

    private void setShipIcon(BattleLosses battleLosses, int i, float f2, float f3, ShipType shipType, int i2, int i3) {
        boolean z = i2 != this.attackerID;
        ShipSprite shipSprite = this.C.shipSpritePool.get();
        shipSprite.setTextureRegion(this.C.graphics.shipsTextures[i2]);
        shipSprite.setCurrentTileIndex(shipType.getIcon(i2));
        float scale = shipType.getScale() * 150.0f;
        List<Integer> hullDesignsForShipType = battleLosses.getHullDesignsForShipType(shipType);
        shipSprite.setShipIcon(i2, shipType, hullDesignsForShipType.get(Functions.random.nextInt(hullDesignsForShipType.size())).intValue(), 150.0f, scale, 1, false);
        shipSprite.setX(f2);
        shipSprite.setY(f3 + ((150.0f - scale) / 2.0f));
        shipSprite.setZIndex(7);
        if (z && shipType != ShipType.STAR_BASE) {
            float f4 = scale / 2.0f;
            shipSprite.setRotationCenter(f4, f4);
            shipSprite.setRotation(180.0f);
        }
        attachChild(shipSprite);
        this.shipIcons.add(shipSprite);
        this.shipTypeIcons[i].setY(shipSprite.getY() + shipSprite.getPlacementY());
        float x = (shipSprite.getX() + shipSprite.getPlacementX()) - this.shipTypeIcons[i].getWidthScaled();
        if (z) {
            x = shipSprite.getX() + shipSprite.getPlacementX() + scale;
        }
        this.shipTypeIcons[i].setX(x);
        this.shipTypeIcons[i].setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
        this.shipTypeIcons[i].setZIndex(8);
        this.shipTypeIcons[i].setVisible(true);
        this.shipCounts[i].setText(Integer.toString(i3));
        this.shipCounts[i].setY(this.shipTypeIcons[i].getY() + this.shipTypeIcons[i].getHeightScaled());
        this.shipCounts[i].setX((this.shipTypeIcons[i].getX() + (this.shipTypeIcons[i].getWidthScaled() / 2.0f)) - (this.shipCounts[i].getWidthScaled() / 2.0f));
        this.shipCounts[i].setZIndex(8);
        this.shipCounts[i].setVisible(true);
        sortChildren();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp();
        }
    }

    public void releasePoolElements() {
        this.C.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Overlays.AutoBattleSummaryOverlay.1
            @Override // java.lang.Runnable
            public void run() {
                for (ShipSprite shipSprite : AutoBattleSummaryOverlay.this.shipIcons) {
                    AutoBattleSummaryOverlay.this.detachChild(shipSprite);
                    AutoBattleSummaryOverlay.this.C.shipSpritePool.push(shipSprite);
                }
                AutoBattleSummaryOverlay.this.shipIcons.clear();
            }
        });
    }

    public void setOverlay(int i, int i2, int i3, BattleLosses battleLosses, BattleLosses battleLosses2) {
        this.winner = i;
        this.attackerID = i2;
        for (TiledSprite tiledSprite : this.shipTypeIcons) {
            tiledSprite.setVisible(false);
        }
        for (Text text : this.shipCounts) {
            text.setVisible(false);
        }
        if (GameProperties.isNonNormalEmpire(i2) || !this.C.empires.get(i2).isHuman()) {
            if (i == -1) {
                this.resultText.setText(this.C.getActivity().getString(R.string.auto_battle_draw));
            } else if (i == i2) {
                this.resultText.setText(this.C.getActivity().getString(R.string.auto_battle_defeat));
            } else {
                this.resultText.setText(this.C.getActivity().getString(R.string.auto_battle_victory));
            }
        } else if (i == -1) {
            this.resultText.setText(this.C.getActivity().getString(R.string.auto_battle_draw));
        } else if (i == i2) {
            this.resultText.setText(this.C.getActivity().getString(R.string.auto_battle_victory));
        } else {
            this.resultText.setText(this.C.getActivity().getString(R.string.auto_battle_defeat));
        }
        this.resultText.setX((getWidth() / 2.0f) - (this.resultText.getWidthScaled() / 2.0f));
        this.attackerBackground.setCurrentTileIndex(i2);
        this.defenderBackground.setCurrentTileIndex(i3);
        setLosses(battleLosses, i2, ((int) (getWidth() / 2.0f)) - 610, this.noLossesAttacker);
        setLosses(battleLosses2, i3, (int) (getWidth() / 2.0f), this.noLossesDefender);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
