package com.birdshel.Uciana.SaveGameData;

import com.birdshel.Uciana.Difficulty;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.FleetIconData;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.GalaxySize;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import com.birdshel.Uciana.StarSystems.Star;
import com.birdshel.Uciana.StarSystems.SystemNameDisplay;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PreviewData {
    public String saveTimeStamp = "";
    public String turns = "";
    public long playTime = 0;
    public int currentPlayer = -1;
    public int bannerID = -1;
    public Difficulty difficulty = Difficulty.NORMAL;
    public GalaxySize galaxySize = GalaxySize.MEDIUM;
    public List<Star> stars = new ArrayList();
    public List<Blackhole> blackholes = new ArrayList();
    public List<SpaceRift> spaceRifts = new ArrayList();
    public List<Point> wormholes = new ArrayList();
    public List<Integer> discoveredLocations = new ArrayList();
    public List<SystemNameDisplay> systemNameDisplays = new ArrayList();
    public List<FleetIconData> fleetIcons = new ArrayList();
    public List<Integer> shipStyles = new ArrayList();
}
