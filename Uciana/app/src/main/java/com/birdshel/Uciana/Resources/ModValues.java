package com.birdshel.Uciana.Resources;

import androidx.core.content.ContextCompat;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Players.RaceAttribute;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.Uciana;

import org.andengine.util.level.constants.LevelConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Array;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ModValues {
    private final String[] empireNames = new String[7];
    private final String[] empireDescriptions = new String[7];
    private final String[] homeSystemNames = new String[7];
    private final String[] homeWorldNames = new String[7];
    private final String[] homeWorldDescriptions = new String[7];
    private final String[][] shipNames = (String[][]) Array.newInstance(String.class, 7, 8);
    private final RaceAttribute[][] defaultPerks = (RaceAttribute[][]) Array.newInstance(RaceAttribute.class, 7, 2);
    private final String[] systemNames = new String[60];

    public ModValues(Uciana uciana) {
        char c2 = 7;
        if (!Game.options.a(OptionID.MODDING) && ContextCompat.checkSelfPermission(uciana, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            XmlFile xmlFile = new XmlFile("/uciana/mod", "values.xml");
            XmlFile xmlFile2 = new XmlFile("/uciana/mod", "empireValues.xml");
            XmlFile xmlFile3 = new XmlFile("/uciana/mod", "systemValues.xml");
            if (xmlFile2.exists() || xmlFile.exists()) {
                xmlFile = xmlFile2.exists() ? xmlFile2 : xmlFile;
                xmlFile.open();
                NodeList items = xmlFile.getItems("empire");
                int i = 0;
                while (i < items.getLength()) {
                    Node item = items.item(i);
                    if (item.getNodeType() == 1) {
                        Element element = (Element) item;
                        int parseInt = Integer.parseInt(element.getAttribute("id"));
                        setArrayValue(this.empireNames, parseInt, element.getElementsByTagName(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME));
                        setArrayValue(this.empireDescriptions, parseInt, element.getElementsByTagName("description"));
                        setArrayValue(this.homeSystemNames, parseInt, element.getElementsByTagName("home_system_name"));
                        setArrayValue(this.homeWorldNames, parseInt, element.getElementsByTagName("home_world_name"));
                        setArrayValue(this.homeWorldDescriptions, parseInt, element.getElementsByTagName("home_world_description"));
                        setRaceAttributes(parseInt, 0, element.getElementsByTagName("default_perk_1"));
                        setRaceAttributes(parseInt, 1, element.getElementsByTagName("default_perk_2"));
                        String[] strArr = new String[8];
                        strArr[0] = "scout_ship_name";
                        strArr[1] = "destroyer_ship_name";
                        strArr[2] = "cruiser_ship_name";
                        strArr[3] = "battle_ship_name";
                        strArr[4] = "dreadnought_ship_name";
                        strArr[5] = "colony_ship_name";
                        strArr[6] = "construction_ship_name";
                        strArr[c2] = "transport_ship_name";
                        for (int i2 = 0; i2 < 8; i2++) {
                            setArrayValue(this.shipNames[parseInt], i2, element.getElementsByTagName(strArr[i2]));
                        }
                    }
                    i++;
                    c2 = 7;
                }
            }
            if (xmlFile3.exists()) {
                xmlFile3.open();
                NodeList items2 = xmlFile3.getItems("system");
                for (int i3 = 0; i3 < items2.getLength(); i3++) {
                    Node item2 = items2.item(i3);
                    if (item2.getNodeType() == 1) {
                        Element element2 = (Element) item2;
                        setArrayValue(this.systemNames, Integer.parseInt(element2.getAttribute("id")), element2.getElementsByTagName(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME));
                    }
                }
            }
        }
    }

    private void setArrayValue(String[] strArr, int i, NodeList nodeList) {
        if (nodeList.getLength() > 0) {
            String textContent = nodeList.item(0).getTextContent();
            if (textContent.equals("") || textContent.equals("\u200b")) {
                return;
            }
            strArr[i] = textContent;
        }
    }

    private void setRaceAttributes(int i, int i2, NodeList nodeList) {
        if (nodeList.getLength() > 0) {
            String textContent = nodeList.item(0).getTextContent();
            if (textContent.equals("") || textContent.equals("\u200b")) {
                return;
            }
            try {
                this.defaultPerks[i][i2] = RaceAttribute.values()[Integer.parseInt(textContent.replace("\u200b", "").trim())];
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public String getEmpireDescription(int i) {
        return this.empireDescriptions[i];
    }

    public String getEmpireName(int i) {
        return this.empireNames[i];
    }

    public String getHomeSystemName(int i) {
        return this.homeSystemNames[i];
    }

    public String getHomeWorldDescription(int i) {
        return this.homeWorldDescriptions[i];
    }

    public String getHomeWorldName(int i) {
        return this.homeWorldNames[i];
    }

    public RaceAttribute getRaceAttribute(int i, int i2) {
        return this.defaultPerks[i][i2];
    }

    public String getShipName(int i, ShipType shipType) {
        return this.shipNames[i][shipType.ordinal()];
    }

    public String getSystemName(int i) {
        return this.systemNames[i];
    }
}
