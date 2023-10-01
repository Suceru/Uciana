package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Elements.TechIcon;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechID;

import org.andengine.entity.Entity;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TechDiscoveryMessage implements Message {
    private final int techFrom;
    private final int techID;

    public TechDiscoveryMessage(int i, int i2) {
        this.techID = i;
        this.techFrom = i2;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        String string;
        StringBuilder sb;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Tech tech = game.getCurrentEmpire().getTech().getTech(TechID.getTechID(this.techID));
        Entity techIcon = new TechIcon(game.getCurrentPlayer(), tech, game, buffer, 100);
        techIcon.setPosition((messageOverlay.getWidth() / 2.0f) - 50.0f, 240.0f);
        messageOverlay.addElement(techIcon);
        int i = this.techFrom;
        if (i == 0) {
            string = game.getActivity().getString(R.string.tech_discovery_from_scientists, new Object[]{tech.getName()});
        } else if (i == 1) {
            string = game.getActivity().getString(R.string.tech_discovery_from_diplomats, new Object[]{tech.getName()});
        } else if (i != 2) {
            string = i != 3 ? "" : game.getActivity().getString(R.string.tech_discovery_from_explorers, new Object[]{tech.getName()});
        } else {
            string = game.getActivity().getString(R.string.tech_discovery_from_troops, new Object[]{tech.getName()});
        }
        String str = string;
        Font font = game.fonts.infoFont;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, str, new TextOptions(horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 390.0f, game.fonts.smallInfoFont, tech.getDescription(), new TextOptions(horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        if (tech.isResource()) {
            ResourceID resourceByTech = Resources.getResourceByTech(tech.getID());
            Empire empire = game.empires.get(game.getCurrentPlayer());
            ArrayList<String> arrayList = new ArrayList();
            for (StarSystem starSystem : game.galaxy.getStarSystems()) {
                if (empire.isDiscoveredSystem(starSystem.getID())) {
                    Iterator<SystemObject> it = starSystem.getSystemObjects().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            SystemObject next = it.next();
                            if (next.hasBeenExploredByEmpire(empire.id) && next.getResources().contains(resourceByTech)) {
                                arrayList.add(starSystem.getName());
                                break;
                            }
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                sb = new StringBuilder(game.getActivity().getString(R.string.tech_resource_deposits_not_found));
            } else {
                sb = new StringBuilder(game.getActivity().getString(R.string.tech_resource_deposits_found));
                int i2 = 1;
                for (String str2 : arrayList) {
                    sb.append(str2);
                    if (i2 != arrayList.size()) {
                        sb.append(", ");
                    }
                    i2++;
                    if (i2 > 10) {
                        break;
                    }
                }
                if (arrayList.size() > 10) {
                    sb.append(game.getActivity().getString(R.string.tech_resource_deposits_other_locations, new Object[]{Integer.valueOf(arrayList.size() - 10)}));
                }
            }
            Text text3 = new Text(0.0f, 435.0f, game.fonts.smallInfoFont, sb.toString(), new TextOptions(HorizontalAlign.CENTER), buffer);
            text3.setX((messageOverlay.getWidth() / 2.0f) - (text3.getWidthScaled() / 2.0f));
            messageOverlay.addElement(text3);
        }
        messageOverlay.setMessageType(MessageType.TECH_DISCOVERY);
        messageOverlay.addAction(MessageAction.OPEN_RESEARCH);
        if (tech.isShipComponent() || tech.getID() == TechID.DREADNOUGHT) {
            messageOverlay.addAction(MessageAction.OPEN_SHIP_DESIGN);
        }
        messageOverlay.addAction(MessageAction.CLOSE);
        messageOverlay.setMessageActionData(new HashMap<>());
    }
}
