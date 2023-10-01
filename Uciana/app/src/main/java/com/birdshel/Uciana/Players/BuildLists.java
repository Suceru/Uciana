package com.birdshel.Uciana.Players;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildLists {
    private final Map<Integer, Map<Integer, BuildingID>> itemLists;
    private final Map<Integer, String> names;

    public BuildLists() {
        HashMap hashMap = new HashMap();
        this.names = hashMap;
        HashMap hashMap2 = new HashMap();
        this.itemLists = hashMap2;
        hashMap.put(0, "");
        hashMap.put(1, "");
        hashMap.put(2, "");
        hashMap2.put(0, new HashMap());
        hashMap2.put(1, new HashMap());
        hashMap2.put(2, new HashMap());
    }

    public boolean containsList(int i) {
        return this.itemLists.containsKey(Integer.valueOf(i));
    }

    public Map<Integer, BuildingID> getItems(int i) {
        return this.itemLists.get(Integer.valueOf(i));
    }

    public String getName(int i) {
        return this.names.get(Integer.valueOf(i));
    }

    public boolean isBlank(int i) {
        return !this.itemLists.containsKey(Integer.valueOf(i));
    }

    public void setItems(int i, Map<Integer, BuildingID> map) {
        this.itemLists.put(Integer.valueOf(i), map);
    }

    public void setName(int i, String str) {
        this.names.put(Integer.valueOf(i), str);
    }
}
