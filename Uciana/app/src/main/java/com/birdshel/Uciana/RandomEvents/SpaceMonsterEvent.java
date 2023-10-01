package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.Armor;
import com.birdshel.Uciana.Ships.ShipComponents.Shield;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.SublightEngine;
import com.birdshel.Uciana.Ships.ShipComponents.TargetingComputer;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponType;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.StarSystem;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.ArrayList;
import java.util.Collections;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SpaceMonsterEvent implements RandomEvent {
    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean checkEvent(Game game) {
        return true;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public int execute(Game game) {
        Fleet fleet = new Fleet(9, game.gameSettings.currentRandomEventData2());
        fleet.setData(0);
        game.fleets.add(fleet);
        ArrayList<Armor> arrayList = new ArrayList();
        ArrayList<Weapon> arrayList2 = new ArrayList();
        ArrayList<TargetingComputer> arrayList3 = new ArrayList();
        ArrayList<SublightEngine> arrayList4 = new ArrayList();
        for (Empire empire : game.empires.getEmpires()) {
            arrayList.addAll(empire.getAvailableArmor());
            arrayList2.addAll(empire.getAvailableWeapons());
            arrayList3.addAll(empire.getAvailableTargetingComputers());
            arrayList4.addAll(empire.getAvailableSublightEngines());
        }
        Armor armor = (Armor) arrayList.get(0);
        for (Armor armor2 : arrayList) {
            if (armor2.getArmorMultiplier() > armor.getArmorMultiplier()) {
                armor = armor2;
            }
        }
        Shield shield = (Shield) ShipComponents.get(ShipComponentID.NO_SHIELDS);
        TargetingComputer targetingComputer = (TargetingComputer) arrayList3.get(0);
        for (TargetingComputer targetingComputer2 : arrayList3) {
            if (targetingComputer2.getTargetingBonus() > targetingComputer.getTargetingBonus()) {
                targetingComputer = targetingComputer2;
            }
        }
        TargetingComputer targetingComputer3 = (TargetingComputer) ShipComponents.get(targetingComputer.getID());
        SublightEngine sublightEngine = (SublightEngine) arrayList4.get(0);
        for (SublightEngine sublightEngine2 : arrayList4) {
            if (sublightEngine2.getCombatSpeed() > sublightEngine.getCombatSpeed()) {
                sublightEngine = sublightEngine2;
            }
        }
        SublightEngine sublightEngine3 = (SublightEngine) ShipComponents.get(sublightEngine.getID());
        Weapon weapon = null;
        Weapon weapon2 = null;
        for (Weapon weapon3 : arrayList2) {
            if (weapon3.getType() == WeaponType.BEAM) {
                if (weapon2 == null) {
                    weapon2 = weapon3;
                }
                if (weapon2.getMaxDamage() < weapon3.getMaxDamage()) {
                    weapon2 = weapon3;
                }
            }
        }
        weapon2.setComponentCount(8);
        for (Weapon weapon4 : arrayList2) {
            if (weapon4.getType() == WeaponType.BOMB) {
                if (weapon == null) {
                    weapon = weapon4;
                }
                if (weapon.getMaxDamage() < weapon4.getMaxDamage()) {
                    weapon = weapon4;
                }
            }
        }
        weapon.setComponentCount(6);
        int i = 0;
        while (i < game.gameSettings.currentRandomEventData3()) {
            ArrayList arrayList5 = new ArrayList();
            arrayList5.add(weapon2);
            arrayList5.add(weapon);
            Ship.Builder builder = new Ship.Builder();
            StringBuilder sb = new StringBuilder();
            sb.append("MO-");
            sb.append(GameData.turn);
            sb.append("-");
            i++;
            sb.append(i);
            Ship.Builder id = builder.id(sb.toString());
            ShipType shipType = ShipType.BATTLESHIP;
            fleet.addShip(id.shipType(shipType).name(shipType.getString(9)).empireID(9).designNumber(0).productionCost(0).armor(armor).targetingComputer(targetingComputer3).sublightEngine(sublightEngine3).shipComponents(arrayList5).shield(shield).buildCombatShip());
        }
        fleet.move(game.gameSettings.currentRandomEventData1());
        while (fleet.getETA() > 5) {
            fleet.update();
        }
        game.randomEvents.a(new RandomEventData(RandomEventType.SPACE_MONSTER, game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2(), game.gameSettings.currentRandomEventData3()));
        game.randomEvents.setRandomEvent(RandomEventType.NONE, -1, -1, -1);
        return 0;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public Entity getMessage(MessageOverlay messageOverlay, int i, int i2, int i3, int i4) {
        String string;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Entity entity = new Entity();
        Empire currentEmpire = game.getCurrentEmpire();
        if (currentEmpire.getSystemIDs().contains(Integer.valueOf(i2))) {
            game.graphics.setAmbassadorTexture(game.getCurrentEmpire().getRaceID(), game.getActivity());
            Sprite sprite = new Sprite(0.0f, 230.0f, game.graphics.raceAmbassadorTexture, buffer);
            sprite.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite);
        } else {
            Sprite sprite2 = new Sprite(0.0f, 230.0f, game.graphics.spyTexture, buffer);
            sprite2.setSize(200.0f, 250.0f);
            messageOverlay.addElement(sprite2);
        }
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.space_monster), new TextOptions(HorizontalAlign.CENTER), buffer);
        text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 300.0f);
        entity.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.RANDOM_EVENT.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        tiledSprite.setPosition((messageOverlay.getWidth() / 2.0f) - 20.0f, text.getY() - 45.0f);
        entity.attachChild(tiledSprite);
        Object name = game.galaxy.getStarSystem(i2).getName();
        if (currentEmpire.getSystemIDs().contains(Integer.valueOf(i2))) {
            string = game.getActivity().getString(Functions.getResId("space_monster_in_" + game.getCurrentPlayer(), R.string.class), new Object[]{name});
        } else if (currentEmpire.isDiscoveredSystem(i2)) {
            string = game.getActivity().getString(Functions.getResId("space_monster_know_" + game.getCurrentPlayer(), R.string.class), new Object[]{name});
        } else {
            string = game.getActivity().getString(Functions.getResId("space_monster_unknown_" + game.getCurrentPlayer(), R.string.class));
        }
        Text text2 = new Text(220.0f, 0.0f, game.fonts.infoFont, string, new TextOptions(AutoWrap.WORDS, 1020.0f), buffer);
        text2.setY(450.0f - text2.getHeightScaled());
        entity.attachChild(text2);
        return entity;
    }

    @Override // com.birdshel.Uciana.RandomEvents.RandomEvent
    public boolean initialize(Game game) {
        ArrayList arrayList = new ArrayList();
        for (Colony colony : game.colonies.getColonies()) {
            arrayList.add(Integer.valueOf(colony.getSystemID()));
        }
        Collections.shuffle(arrayList);
        int intValue = ((Integer) arrayList.get(Functions.random.nextInt(arrayList.size()))).intValue();
        int i = -1;
        for (StarSystem starSystem : game.galaxy.getStarSystems()) {
            if (game.galaxy.getDistance(intValue, starSystem.getID()) > 15) {
                i = starSystem.getID();
            }
        }
        if (i == -1) {
            return false;
        }
        int i2 = 1;
        for (SystemObject systemObject : game.galaxy.getStarSystem(intValue).getSystemObjects()) {
            if (systemObject.hasColony()) {
                Colony colony2 = systemObject.getColony();
                if (colony2.hasBuilding(BuildingID.STAR_BASE)) {
                    i2++;
                }
                if (colony2.hasBuilding(BuildingID.TORPEDO_TURRET)) {
                    i2++;
                }
            }
        }
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA1, Integer.valueOf(intValue));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA2, Integer.valueOf(i));
        game.gameSettings.setSetting(GameSettingsEnum.RANDOM_EVENTS_NEXT_DATA3, Integer.valueOf(i2));
        return true;
    }
}
