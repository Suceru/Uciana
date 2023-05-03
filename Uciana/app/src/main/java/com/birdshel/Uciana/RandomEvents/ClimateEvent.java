package com.birdshel.Uciana.RandomEvents;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Planet;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ClimateEvent {
    private Point getClimatesFromData(int i) {
        String num = Integer.toString(i);
        return new Point(Integer.parseInt(num.substring(1, 3)), Integer.parseInt(num.substring(3)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(Game game, RandomEventType randomEventType) {
        Point d2 = d(game.gameSettings.currentRandomEventData1());
        Planet planet = (Planet) game.galaxy.getSystemObject((int) d2.getX(), (int) d2.getY());
        if (planet.isTerraformed()) {
            planet.setClimateChange(Climate.values()[game.gameSettings.currentRandomEventData3()]);
        } else {
            Point climatesFromData = getClimatesFromData(game.gameSettings.currentRandomEventData2());
            planet.setClimateChange(Climate.values()[(int) climatesFromData.getX()], Climate.values()[(int) climatesFromData.getY()]);
        }
        if (planet.hasColony() && planet.getFoodPerFarmer(planet.getOccupier()) == 0.0f) {
            Colony colony = planet.getColony();
            int farmersPercent = colony.getFarmersPercent();
            colony.setFarmersPercent(0);
            colony.setWorkersPercent(colony.getWorkersPercent() + farmersPercent);
        }
        game.randomEvents.a(new RandomEventData(randomEventType, game.gameSettings.currentRandomEventData1(), game.gameSettings.currentRandomEventData2(), game.gameSettings.currentRandomEventData3()));
        game.randomEvents.setRandomEvent(RandomEventType.NONE, -1, -1, -1);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(Climate climate, Climate climate2) {
        return climate2.ordinal() + 10000 + (climate.ordinal() * 100);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(int i, int i2) {
        return i2 + 10000 + (i * 10);
    }

    public boolean checkEvent(Game game) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Point d(int i) {
        String num = Integer.toString(i);
        return new Point(Integer.parseInt(num.substring(1, 4)), Integer.parseInt(num.substring(4)));
    }
}
