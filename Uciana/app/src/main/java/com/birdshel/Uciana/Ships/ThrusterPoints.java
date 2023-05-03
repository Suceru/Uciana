package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Math.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ThrusterPoints {

    /* renamed from: a  reason: collision with root package name */
    static int[][] f1567a = {new int[]{0, 1, 2, 3, 4}, new int[]{1, 2, 0, 3, 4}, new int[]{2, 3, 1, 0, 4}, new int[]{3, 2, 1, 0, 4}};
    static Point[][][] b = {new Point[][]{new Point[]{new Point(-23.0f, 20.0f)}, new Point[]{new Point(-50.0f, 22.0f)}, new Point[]{new Point(-40.0f, 13.0f), new Point(-40.0f, 32.0f)}, new Point[]{new Point(-19.0f, -9.0f), new Point(-22.0f, 22.0f), new Point(-19.0f, 53.0f)}, new Point[]{new Point(-45.0f, -1.0f), new Point(-38.0f, 22.0f), new Point(-45.0f, 45.0f)}, new Point[]{new Point(-39.0f, -14.0f), new Point(-50.0f, 22.0f), new Point(-39.0f, 59.0f)}, new Point[]{new Point(-37.0f, -37.0f), new Point(-38.0f, 22.0f), new Point(-37.0f, 81.0f)}, new Point[]{new Point(-44.0f, -5.0f), new Point(-41.0f, 22.0f), new Point(-44.0f, 50.0f)}, new Point[]{new Point(-41.0f, 22.0f)}}, new Point[][]{new Point[]{new Point(-29.0f, 22.0f)}, new Point[]{new Point(-37.0f, 12.0f), new Point(-37.0f, 36.0f)}, new Point[]{new Point(-41.0f, 22.0f)}, new Point[]{new Point(-39.0f, 22.0f)}, new Point[]{new Point(-32.0f, 23.0f)}, new Point[]{new Point(-49.0f, 0.0f), new Point(-49.0f, 44.0f)}, new Point[]{new Point(-46.0f, 0.0f), new Point(-46.0f, 44.0f)}, new Point[]{new Point(-12.0f, 22.0f)}, new Point[]{new Point(-37.0f, 23.0f)}}, new Point[][]{new Point[]{new Point(-10.0f, 22.0f)}, new Point[]{new Point(-20.0f, 6.0f), new Point(-20.0f, 37.0f)}, new Point[]{new Point(-27.0f, 22.0f)}, new Point[]{new Point(-21.0f, 22.0f)}, new Point[]{new Point(-13.0f, 22.0f)}, new Point[]{new Point(-27.0f, 22.0f)}, new Point[]{new Point(-16.0f, 22.0f)}, new Point[]{new Point(-10.0f, 22.0f)}, new Point[]{new Point(-23.0f, 23.0f)}}, new Point[][]{new Point[]{new Point(-50.0f, 24.0f)}, new Point[]{new Point(-50.0f, 24.0f)}, new Point[]{new Point(-50.0f, 22.0f)}, new Point[]{new Point(-52.0f, 22.0f)}, new Point[]{new Point(-49.0f, -13.0f), new Point(-49.0f, 57.0f)}, new Point[]{new Point(-48.0f, -24.0f), new Point(-48.0f, 68.0f)}, new Point[]{new Point(-39.0f, -21.0f), new Point(-47.0f, 22.0f), new Point(-39.0f, 65.0f)}, new Point[]{new Point(-39.0f, -10.0f), new Point(-43.0f, 21.0f), new Point(-39.0f, 54.0f)}, new Point[]{new Point(-39.0f, -30.0f), new Point(-47.0f, 22.0f), new Point(-39.0f, 75.0f)}}, new Point[][]{new Point[]{new Point(-15.0f, 22.0f)}, new Point[]{new Point(-45.0f, 22.0f)}, new Point[]{new Point(-40.0f, 12.0f), new Point(-40.0f, 33.0f)}, new Point[]{new Point(-48.0f, 22.0f)}, new Point[]{new Point(-42.0f, 6.0f), new Point(-42.0f, 22.0f), new Point(-42.0f, 38.0f)}, new Point[]{new Point(-32.0f, -5.0f), new Point(-32.0f, 22.0f), new Point(-32.0f, 49.0f)}, new Point[]{new Point(-39.0f, -8.0f), new Point(-39.0f, 53.0f)}, new Point[]{new Point(-44.0f, -4.0f), new Point(-44.0f, 49.0f)}, new Point[]{new Point(-36.0f, 10.0f), new Point(-40.0f, 22.0f), new Point(-36.0f, 34.0f)}}, new Point[][]{new Point[]{new Point(-32.0f, 22.5f)}, new Point[]{new Point(-42.0f, 22.0f)}, new Point[]{new Point(-34.0f, 22.5f)}, new Point[]{new Point(-43.0f, 8.0f), new Point(-43.0f, 36.0f)}, new Point[]{new Point(-34.0f, -4.0f), new Point(-34.0f, 51.0f)}, new Point[]{new Point(-41.0f, 15.0f), new Point(-41.0f, 31.0f)}, new Point[]{new Point(-39.0f, 24.0f)}, new Point[]{new Point(-40.0f, 14.0f), new Point(-40.0f, 31.0f)}, new Point[]{new Point(-45.0f, 23.0f)}}, new Point[][]{new Point[]{new Point(-42.0f, 24.0f)}, new Point[]{new Point(-38.0f, 10.0f), new Point(-38.0f, 36.0f)}, new Point[]{new Point(-8.0f, -4.0f), new Point(-8.0f, 51.0f)}, new Point[]{new Point(-36.0f, 2.0f), new Point(-36.0f, 44.0f)}, new Point[]{new Point(-20.0f, 6.0f), new Point(-20.0f, 41.0f)}, new Point[]{new Point(-36.0f, 24.0f)}, new Point[]{new Point(-25.0f, -18.0f), new Point(-25.0f, 62.0f)}, new Point[]{new Point(-10.0f, -20.0f), new Point(-25.0f, 23.0f), new Point(-10.0f, 65.0f)}, new Point[]{new Point(-20.0f, 2.0f), new Point(-20.0f, 45.0f)}}};

    public static List<Point> getThrusters(int i, ShipType shipType, int i2) {
        if (i < 7) {
            if (shipType.isStation()) {
                return new ArrayList();
            }
            if (shipType.isCombatShip()) {
                return Arrays.asList(b[i][f1567a[shipType.id - 4][i2] + 4]);
            }
            return Arrays.asList(b[i][shipType.id]);
        }
        return new ArrayList();
    }
}
