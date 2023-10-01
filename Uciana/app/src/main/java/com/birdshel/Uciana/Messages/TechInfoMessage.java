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
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TechInfoMessage implements Message {
    private Game game;
    private final TechID techID;

    public TechInfoMessage(TechID techID) {
        this.techID = techID;
    }

    private void addResourceLocations(MessageOverlay messageOverlay, TechID techID) {
        ResourceID resourceByTech = Resources.getResourceByTech(techID);
        Empire empire = messageOverlay.getGame().empires.get(messageOverlay.getGame().getCurrentPlayer());
        ArrayList<String> arrayList = new ArrayList();
        for (StarSystem starSystem : messageOverlay.getGame().galaxy.getStarSystems()) {
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
        StringBuilder sb = new StringBuilder();
        if (arrayList.isEmpty()) {
            sb.append(this.game.getActivity().getString(R.string.tech_resource_deposits_not_found));
        } else {
            sb.append(this.game.getActivity().getString(R.string.tech_resource_deposits_found));
            int i = 1;
            for (String str : arrayList) {
                sb.append(str);
                if (i != arrayList.size()) {
                    sb.append(", ");
                }
                i++;
                if (i > 10) {
                    break;
                }
            }
            if (arrayList.size() > 10) {
                sb.append(this.game.getActivity().getString(R.string.tech_resource_deposits_other_locations, new Object[]{Integer.valueOf(arrayList.size() - 10)}));
            }
        }
        Text text = new Text(0.0f, 435.0f, messageOverlay.getGame().fonts.smallInfoFont, sb, new TextOptions(HorizontalAlign.CENTER), messageOverlay.getBuffer());
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text);
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        this.game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Tech tech = this.game.getCurrentEmpire().getTech().getTech(this.techID);
        Font font = this.game.fonts.infoFont;
        String name = tech.getName();
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 260.0f, font, name, new TextOptions(horizontalAlign), buffer);
        messageOverlay.addElement(text);
        Entity techIcon = new TechIcon(this.game.getCurrentPlayer(), tech, this.game, buffer, 100);
        messageOverlay.addElement(techIcon);
        float width = (messageOverlay.getWidth() / 2.0f) - ((text.getWidthScaled() + 120.0f) / 2.0f);
        techIcon.setPosition(width, 245.0f);
        float f2 = width + 120.0f;
        text.setX(f2);
        String name2 = tech.getType().getName();
        if (this.techID == TechID.SOIL_ENRICHMENT) {
            name2 = this.game.getActivity().getString(R.string.tech_type_planet_enhancement);
        }
        messageOverlay.addElement(new Text(f2, 305.0f, this.game.fonts.smallInfoFont, name2, buffer));
        TiledSprite tiledSprite = new TiledSprite(220.0f, 350.0f, this.game.graphics.productionPercentBarTexture, buffer);
        tiledSprite.setSize(messageOverlay.getWidth() - 440.0f, 5.0f);
        tiledSprite.setCurrentTileIndex(3);
        tiledSprite.setAlpha(0.5f);
        messageOverlay.addElement(tiledSprite);
        Text text2 = new Text(0.0f, 390.0f, this.game.fonts.smallInfoFont, tech.getDescription(), new TextOptions(horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
        if (tech.isResource() && tech.isDone()) {
            text2.setY(370.0f);
            addResourceLocations(messageOverlay, this.techID);
        }
    }
}
