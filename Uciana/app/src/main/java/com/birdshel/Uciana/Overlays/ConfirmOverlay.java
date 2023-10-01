package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Manufacturing;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Scenes.ColoniesScene;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Scenes.FleetsScene;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import com.birdshel.Uciana.Scenes.PlanetScene;
import com.birdshel.Uciana.Scenes.ProductionScene;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ConfirmOverlay extends ExtendedChildScene {
    public static final int GAME_SAVE = 0;
    public static final int SHIP_DESIGN = 1;
    private final TiledSprite cancelButton;
    private Colony colony;
    private final TiledSprite creditsIcon;
    private final Map<String, Object> data;
    private final Sprite messageBackground;
    private final Text messageLine1Text;
    private final Text messageLine2Text;
    private final Text messageLine3Text;
    private final TiledSprite okButton;
    private ExtendedScene screen;
    private final TiledSprite shortCreditsIcon;
    private boolean showButtons;
    private String type;

    public ConfirmOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.data = new HashMap();
        this.G.setAlpha(0.3f);
        Sprite t = t(0.0f, 240.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        this.messageBackground = t;
        t.setSize(getWidth(), 240.0f);
        t.setAlpha(0.9f);
        this.I = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        this.messageLine1Text = v(0.0f, 325.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager, true);
        this.messageLine2Text = v(0.0f, 360.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager, true);
        this.messageLine3Text = v(0.0f, 395.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager, true);
        TiledTextureRegion tiledTextureRegion = game.graphics.infoIconsTexture;
        InfoIconEnum infoIconEnum = InfoIconEnum.CREDITS;
        this.creditsIcon = w(0.0f, 325.0f, tiledTextureRegion, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        this.shortCreditsIcon = w(0.0f, 360.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        TiledSprite w = w((getWidth() / 2.0f) - 180.0f, 500.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.cancelButton = w;
        n(w);
        TiledSprite w2 = w((getWidth() / 2.0f) + 60.0f, 500.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.OK.ordinal(), true);
        this.okButton = w2;
        n(w2);
    }

    private void attack() {
        int intValue = ((Integer) this.data.get("attackerID")).intValue();
        this.C.empires.get(intValue).declareWar(((Integer) this.data.get("defenderID")).intValue());
        if (this.data.get("attackType").equals("bomb")) {
            ShipComponentID shipComponentID = (ShipComponentID) this.data.get("shipComponentID");
            this.C.attackScene.bombPlanet(shipComponentID);
            if (shipComponentID == ShipComponentID.BIO_BOMB) {
                Game.gameAchievements.usedBioWeapon();
            }
        } else if (this.data.get("attackType").equals("bombard")) {
            this.C.attackScene.bombardPlanet();
        } else if (this.data.get("attackType").equals("attack")) {
            this.C.attackScene.attackFleet();
        } else if (this.data.get("attackType").equals("autoAttack")) {
            this.C.sounds.playButtonPressSound();
            Game game = this.C;
            game.vibrate(game.BUTTON_VIBRATE);
            back();
            this.C.attackScene.autoAttackFleet();
        } else if (this.data.get("attackType").equals("destroyNonCombat")) {
            this.C.attackScene.destroyNonCombat();
        } else if (this.data.get("attackType").equals("invade")) {
            this.C.sounds.playButtonPressSound();
            Game game2 = this.C;
            game2.vibrate(game2.BUTTON_VIBRATE);
            back();
            this.C.attackScene.invadePlanet();
        }
    }

    private void autoOff() {
        this.colony.setAutoBuild(false);
        this.C.productionScene.refresh();
    }

    private void buy() {
        this.C.getCurrentEmpire().addRemoveCredits(-this.colony.getManufacturing().getCostToFinish());
        this.colony.getManufacturing().finishProject();
        ExtendedScene extendedScene = this.screen;
        if (extendedScene instanceof ColoniesScene) {
            this.C.coloniesScene.reloadColoniesFromOverlay();
        } else if (extendedScene instanceof PlanetScene) {
            this.C.planetScene.refresh();
        } else if (extendedScene instanceof ProductionScene) {
            this.C.productionScene.updateProduction();
        }
    }

    private void cancelButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void checkActionUp(Point point) {
        if (!this.showButtons) {
            this.C.sounds.playBoxPressSound();
            Game game = this.C;
            game.vibrate(game.BUTTON_VIBRATE);
            back();
        }
        if (q(this.okButton, point)) {
            okButtonPressed();
        }
        if (q(this.cancelButton, point)) {
            cancelButtonPressed();
        }
    }

    private void deleteSave() {
        this.C.loadSaveScene.deleteSave(((Integer) this.data.get("save")).intValue());
    }

    private void endTreaty() {
        int intValue = ((Integer) this.data.get("contactEmpireID")).intValue();
        this.C.raceScene.removeTreaty((Treaty) this.data.get("treaty"), intValue);
    }

    private void okButtonPressed() {
        String str = this.type;
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1407259064:
                if (str.equals("attack")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1335458389:
                if (str.equals("delete")) {
                    c2 = 1;
                    break;
                }
                break;
            case -646331072:
                if (str.equals("autoOff")) {
                    c2 = 2;
                    break;
                }
                break;
            case 97926:
                if (str.equals("buy")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3526482:
                if (str.equals("sell")) {
                    c2 = 4;
                    break;
                }
                break;
            case 109266897:
                if (str.equals("scrap")) {
                    c2 = 5;
                    break;
                }
                break;
            case 117426446:
                if (str.equals("refitRemoval")) {
                    c2 = 6;
                    break;
                }
                break;
            case 120087510:
                if (str.equals("refitReplace")) {
                    c2 = 7;
                    break;
                }
                break;
            case 850324250:
                if (str.equals("shipDesign")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1635592282:
                if (str.equals("endTreaty")) {
                    c2 = '\t';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                attack();
                break;
            case 1:
                deleteSave();
                break;
            case 2:
                autoOff();
                break;
            case 3:
                buy();
                break;
            case 4:
                sell();
                break;
            case 5:
                scrap();
                break;
            case 6:
                refitRemoval();
                break;
            case 7:
                refitReplace();
                return;
            case '\b':
                removeShipDesign();
                break;
            case '\t':
                endTreaty();
                break;
        }
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void refitRemoval() {
        ((Manufacturing) this.data.get("manufacturing")).removeFromQueue(((Integer) this.data.get("index")).intValue());
        this.C.productionScene.refreshQueue();
        this.C.productionScene.setQueuedOverlays();
    }

    private void refitReplace() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        Manufacturing manufacturing = (Manufacturing) this.data.get("manufacturing");
        if (this.data.get("type") == "ship") {
            manufacturing.setShip((Ship) this.data.get("productionItem"));
            this.C.productionScene.updateDisplay();
            back();
        } else if (this.data.get("type") == "building") {
            manufacturing.setBuilding((Building) this.data.get("productionItem"));
            this.C.productionScene.updateDisplay();
            back();
        } else {
            back();
            this.C.productionScene.showShipSelectOverlayForRefit();
        }
    }

    private void removeShipDesign() {
        this.C.shipDesignScene.removeDesign(((Integer) this.data.get("design")).intValue());
    }

    private void scrap() {
        if (this.C.getCurrentScene() instanceof GalaxyScene) {
            this.C.shipSelectOverlay.scrap();
        } else if (this.C.getCurrentScene() instanceof FleetsScene) {
            this.C.shipSelectOverlay.scrap();
        }
    }

    private void sell() {
        this.C.buildingsScene.sellBuilding();
    }

    private void setForAutoOff() {
        setMessages(this.C.getActivity().getString(R.string.confirm_auto_off_message), "", true, false, this.C.getActivity().getString(R.string.confirm_auto_off));
        this.shortCreditsIcon.setVisible(false);
    }

    private void setForBuying() {
        String string = this.C.getActivity().getString(R.string.confirm_finishing, new Object[]{this.colony.getManufacturing().getName(), Integer.valueOf(this.colony.getManufacturing().getCostToFinish())});
        if (this.colony.getManufacturing().getCostToFinish() > this.C.getCurrentEmpire().getCredits()) {
            setMessages(string, this.C.getActivity().getString(R.string.confirm_finishing_short, new Object[]{Integer.valueOf(this.colony.getManufacturing().getCostToFinish() - this.C.getCurrentEmpire().getCredits())}), false, true);
        } else {
            setMessages(string, this.C.getActivity().getString(R.string.confirm_finishing_not_short, new Object[]{Integer.valueOf(this.C.getCurrentEmpire().getCredits())}), true, true);
        }
    }

    private void setForSelling() {
        Building building = Buildings.getBuilding(this.C.buildingsScene.selectedBuilding);
        setMessages(this.C.getActivity().getString(R.string.confirm_building_sale, new Object[]{building.getName(), Integer.valueOf(building.getSellValue())}), "", true, true);
        this.shortCreditsIcon.setVisible(false);
    }

    private void setMessages(String str, String str2, boolean z, boolean z2) {
        setMessages(str, str2, z, z2, this.C.getActivity().getString(R.string.confirm_are_you_sure));
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp(point);
        }
    }

    public void setOverlay(Colony colony, ExtendedScene extendedScene, String str) {
        this.colony = colony;
        this.screen = extendedScene;
        this.type = str;
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -646331072:
                if (str.equals("autoOff")) {
                    c2 = 0;
                    break;
                }
                break;
            case 97926:
                if (str.equals("buy")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3526482:
                if (str.equals("sell")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setForAutoOff();
                break;
            case 1:
                setForBuying();
                break;
            case 2:
                setForSelling();
                break;
        }
        this.okButton.setVisible(this.showButtons);
        this.cancelButton.setVisible(this.showButtons);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }

    private void setMessages(String str, String str2, boolean z, boolean z2, String str3) {
        float y;
        float heightScaled;
        this.messageLine1Text.setY(325.0f);
        this.messageLine2Text.setY(360.0f);
        this.messageLine3Text.setY(395.0f);
        this.creditsIcon.setY(325.0f);
        this.shortCreditsIcon.setY(360.0f);
        this.okButton.setY(450.0f);
        this.messageLine1Text.setText(str);
        this.messageLine1Text.setVisible(true);
        this.messageLine1Text.setX((getWidth() / 2.0f) - ((this.messageLine1Text.getWidthScaled() + 30.0f) / 2.0f));
        this.creditsIcon.setX(this.messageLine1Text.getX() + this.messageLine1Text.getWidthScaled() + 5.0f);
        this.messageLine2Text.setText(str2);
        this.messageLine2Text.setVisible(!str2.equals(""));
        this.messageLine2Text.setX((getWidth() / 2.0f) - ((this.messageLine2Text.getWidthScaled() + 30.0f) / 2.0f));
        this.shortCreditsIcon.setX(this.messageLine2Text.getX() + this.messageLine2Text.getWidthScaled() + 5.0f);
        this.shortCreditsIcon.setVisible(!str2.equals(""));
        this.messageLine3Text.setText(str3);
        this.messageLine3Text.setVisible(z);
        this.messageLine3Text.setX((getWidth() / 2.0f) - (this.messageLine3Text.getWidthScaled() / 2.0f));
        this.showButtons = z;
        float y2 = this.messageLine1Text.getY() - 50.0f;
        if (z) {
            y = this.okButton.getY();
            heightScaled = this.okButton.getHeightScaled();
        } else {
            y = this.messageLine2Text.getY();
            heightScaled = this.messageLine2Text.getHeightScaled();
        }
        float f2 = ((y + heightScaled) + 50.0f) - y2;
        float f3 = y2 - (360.0f - (f2 / 2.0f));
        this.messageBackground.setY(y2 - f3);
        this.messageBackground.setHeight(f2);
        Text text = this.messageLine1Text;
        text.setY(text.getY() - f3);
        Text text2 = this.messageLine2Text;
        text2.setY(text2.getY() - f3);
        Text text3 = this.messageLine3Text;
        text3.setY(text3.getY() - f3);
        TiledSprite tiledSprite = this.creditsIcon;
        tiledSprite.setY(tiledSprite.getY() - f3);
        TiledSprite tiledSprite2 = this.shortCreditsIcon;
        tiledSprite2.setY(tiledSprite2.getY() - f3);
        this.okButton.setY(450.0f - f3);
        this.cancelButton.setY(this.okButton.getY());
        if (z2) {
            this.creditsIcon.setVisible(true);
            this.shortCreditsIcon.setVisible(true);
        } else {
            this.creditsIcon.setVisible(false);
            this.shortCreditsIcon.setVisible(false);
        }
        this.okButton.setVisible(this.showButtons);
        this.cancelButton.setVisible(this.showButtons);
    }

    public void setOverlay(String str, List<String> list) {
        String string;
        this.type = "scrap";
        int scrapValue = this.C.fleets.get(str).getScrapValue(list);
        if (list.size() == 1) {
            if (scrapValue == 0) {
                string = this.C.getActivity().getString(R.string.confirm_scrapping_one_ship_out_of_system);
            } else {
                string = this.C.getActivity().getString(R.string.confirm_scrapping_one_ship, new Object[]{Integer.valueOf(scrapValue)});
            }
        } else if (scrapValue == 0) {
            string = this.C.getActivity().getString(R.string.confirm_scrapping_many_ships_out_of_system, new Object[]{Integer.valueOf(list.size())});
        } else {
            string = this.C.getActivity().getString(R.string.confirm_scrapping_many_ships, new Object[]{Integer.valueOf(list.size()), Integer.valueOf(scrapValue)});
        }
        setMessages(string, "", true, true);
        this.shortCreditsIcon.setVisible(false);
        this.okButton.setVisible(this.showButtons);
        this.cancelButton.setVisible(this.showButtons);
    }

    public void setOverlay(int i, int i2, String str, ShipComponentID shipComponentID) {
        this.type = "attack";
        this.data.clear();
        this.data.put("attackType", str);
        this.data.put("attackerID", Integer.valueOf(i));
        this.data.put("defenderID", Integer.valueOf(i2));
        this.data.put("shipComponentID", shipComponentID);
        setMessages(this.C.getActivity().getString(R.string.confirm_attack), this.C.empires.get(i2).getName(), true, false);
        this.okButton.setVisible(this.showButtons);
        this.cancelButton.setVisible(this.showButtons);
    }

    public void setOverlay(int i, int i2) {
        if (i == 0) {
            this.type = "delete";
            this.data.clear();
            this.data.put("save", Integer.valueOf(i2));
            setMessages(this.C.getActivity().getString(R.string.confirm_delete), "", true, true);
        } else if (i == 1) {
            this.type = "shipDesign";
            this.data.clear();
            this.data.put("design", Integer.valueOf(i2));
            setMessages(this.C.getActivity().getString(R.string.confirm_remove_ship_design), "", true, true);
        }
        this.creditsIcon.setVisible(false);
        this.shortCreditsIcon.setVisible(false);
    }

    public void setOverlay(String str, Manufacturing manufacturing, String str2, Object obj) {
        this.type = "refitReplace";
        this.data.clear();
        this.data.put("manufacturing", manufacturing);
        this.data.put("type", str2);
        this.data.put("productionItem", obj);
        setMessages(this.C.getActivity().getString(R.string.confirm_scrap_of_refit, new Object[]{str}), "", true, true);
        this.creditsIcon.setVisible(false);
        this.shortCreditsIcon.setVisible(false);
    }

    public void setOverlay(String str, Manufacturing manufacturing, int i) {
        this.type = "refitRemoval";
        this.data.clear();
        this.data.put("manufacturing", manufacturing);
        this.data.put("index", Integer.valueOf(i));
        setMessages(this.C.getActivity().getString(R.string.confirm_scrap_of_refit, new Object[]{str}), "", true, true);
        this.creditsIcon.setVisible(false);
        this.shortCreditsIcon.setVisible(false);
    }

    public void setOverlay(Treaty treaty, int i) {
        this.type = "endTreaty";
        this.data.clear();
        this.data.put("treaty", treaty);
        this.data.put("contactEmpireID", Integer.valueOf(i));
        setMessages("", "", true, true);
        this.creditsIcon.setVisible(false);
        this.shortCreditsIcon.setVisible(false);
    }
}
