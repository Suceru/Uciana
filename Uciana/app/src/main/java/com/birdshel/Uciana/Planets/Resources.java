package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.Resource;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Resources {
    private static final Map<ResourceID, Resource> resourceList = new HashMap();
    private static final Climate[] climateResourcesList = {Climate.PLAGUE, Climate.SUPER_ACIDIC, Climate.BOG, Climate.METHANE, Climate.BOREAL, Climate.TROPICAL_OCEAN, Climate.SENTIENT};

    public static boolean climateHasResource(Climate climate) {
        return Arrays.asList(climateResourcesList).contains(climate);
    }

    public static Resource get(ResourceID resourceID) {
        return resourceList.get(resourceID);
    }

    public static ResourceID getResourceByTech(TechID techID) {
        for (Resource resource : resourceList.values()) {
            if (resource.b() == techID) {
                return resource.getID();
            }
        }
        return ResourceID.NONE;
    }

    public static List<ResourceID> getResourcesForPlanet(Climate climate, Climate climate2) {
        ArrayList arrayList = new ArrayList();
        for (Resource resource : resourceList.values()) {
            if ((!resource.containsEffect(ResourceType.FOOD) && !resource.containsEffect(ResourceType.FOOD_PER_FARMER)) || (climate.getFoodPerFarmer() != 0.0f && climate2.getFoodPerFarmer() != 0.0f)) {
                if (Functions.percent(resource.a())) {
                    arrayList.add(resource.getID());
                    if (climateHasResource(climate2)) {
                        if (arrayList.size() == 3) {
                            return arrayList;
                        }
                    } else if (arrayList.size() == 4) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return arrayList;
    }

    public static void set() {
        Map<ResourceID, Resource> map = resourceList;
        map.clear();
        ResourceID resourceID = ResourceID.SILVER;
        map.put(resourceID, new Resource.Builder().id(resourceID).name(R.string.resource_silver).chancePercent(15).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.1
            {
                put(ResourceType.CREDITS, Float.valueOf(5.0f));
            }
        }).build());
        ResourceID resourceID2 = ResourceID.GOLD;
        map.put(resourceID2, new Resource.Builder().id(resourceID2).name(R.string.resource_gold).chancePercent(10).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.2
            {
                put(ResourceType.CREDITS, Float.valueOf(10.0f));
            }
        }).build());
        ResourceID resourceID3 = ResourceID.PLATINUM;
        map.put(resourceID3, new Resource.Builder().id(resourceID3).name(R.string.resource_platinum).chancePercent(5).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.3
            {
                put(ResourceType.CREDITS, Float.valueOf(15.0f));
            }
        }).build());
        ResourceID resourceID4 = ResourceID.WHEAT;
        map.put(resourceID4, new Resource.Builder().id(resourceID4).name(R.string.resource_fertile_soil).chancePercent(20).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.4
            {
                put(ResourceType.FOOD_PER_FARMER, Float.valueOf(1.0f));
            }
        }).build());
        ResourceID resourceID5 = ResourceID.BIO_TOXIN;
        map.put(resourceID5, new Resource.Builder().id(resourceID5).name(R.string.resource_bio_toxin).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.5
            {
                put(ResourceType.SCIENCE_PER_SCIENTIST, Float.valueOf(1.0f));
            }
        }).build());
        ResourceID resourceID6 = ResourceID.ACID;
        map.put(resourceID6, new Resource.Builder().id(resourceID6).name(R.string.resource_acid).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.6
            {
                put(ResourceType.PRODUCTION_PER_WORKER_EMPIRE_WIDE, Float.valueOf(1.0f));
            }
        }).build());
        ResourceID resourceID7 = ResourceID.CRYSTALS;
        map.put(resourceID7, new Resource.Builder().id(resourceID7).name(R.string.resource_crystals).chancePercent(15).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.7
            {
                put(ResourceType.HAPPINESS, Float.valueOf(0.1f));
            }
        }).build());
        ResourceID resourceID8 = ResourceID.NATIVES;
        map.put(resourceID8, new Resource.Builder().id(resourceID8).name(R.string.resource_natives).chancePercent(15).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.8
            {
                ResourceType resourceType = ResourceType.FOOD;
                Float valueOf = Float.valueOf(50.0f);
                put(resourceType, valueOf);
                put(ResourceType.PRODUCTION, valueOf);
            }
        }).build());
        ResourceID resourceID9 = ResourceID.COZIURIUM;
        map.put(resourceID9, new Resource.Builder().id(resourceID9).name(R.string.resource_coziurium).chancePercent(20).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.9
            {
                put(ResourceType.PRODUCTION_PER_WORKER, Float.valueOf(1.0f));
            }
        }).requiredTech(TechID.COZIURIUM).build());
        ResourceID resourceID10 = ResourceID.ADVANCED_RUINS;
        map.put(resourceID10, new Resource.Builder().id(resourceID10).name(R.string.resource_advanced_ruins).chancePercent(5).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.10
            {
                put(ResourceType.SCIENCE_PER_SCIENTIST, Float.valueOf(1.0f));
            }
        }).build());
        ResourceID resourceID11 = ResourceID.WHISKEY;
        map.put(resourceID11, new Resource.Builder().id(resourceID11).name(R.string.resource_whiskey).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.11
            {
                put(ResourceType.HAPPINESS_EMPIRE_WIDE, Float.valueOf(0.05f));
            }
        }).build());
        ResourceID resourceID12 = ResourceID.METALLIC_METHANE;
        map.put(resourceID12, new Resource.Builder().id(resourceID12).name(R.string.resource_metallic_methane).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.12
            {
                put(ResourceType.POWER_EMPIRE_WIDE, Float.valueOf(5.0f));
            }
        }).build());
        ResourceID resourceID13 = ResourceID.RESORT;
        map.put(resourceID13, new Resource.Builder().id(resourceID13).name(R.string.resource_resort).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.13
            {
                put(ResourceType.HAPPINESS, Float.valueOf(0.1f));
                put(ResourceType.CREDITS_PERCENT, Float.valueOf(0.05f));
            }
        }).build());
        ResourceID resourceID14 = ResourceID.EXOTIC_WOOD;
        map.put(resourceID14, new Resource.Builder().id(resourceID14).name(R.string.resource_exotic_wood).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.14
            {
                put(ResourceType.HAPPINESS_EMPIRE_WIDE, Float.valueOf(0.05f));
            }
        }).build());
        ResourceID resourceID15 = ResourceID.ANTIMATTER;
        map.put(resourceID15, new Resource.Builder().id(resourceID15).name(R.string.resource_antimatter).chancePercent(20).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.15
            {
                put(ResourceType.POWER, Float.valueOf(10.0f));
            }
        }).requiredTech(TechID.ANTIMATTER_DETECTION).build());
        ResourceID resourceID16 = ResourceID.LESCITE;
        map.put(resourceID16, new Resource.Builder().id(resourceID16).name(R.string.resource_lescite).chancePercent(15).effects(new HashMap<ResourceType, Float>() { // from class: com.birdshel.Uciana.Planets.Resources.16
            {
                put(ResourceType.FOOD_PER_FARMER, Float.valueOf(1.0f));
            }
        }).requiredTech(TechID.LESCITE_DETECTION).build());
    }
}
