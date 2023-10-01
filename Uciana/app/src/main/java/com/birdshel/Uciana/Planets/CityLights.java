package com.birdshel.Uciana.Planets;

import android.util.SparseArray;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class CityLights {
    private static final int MAX_LIGHTS = 12;
    private static final int POP_PER_CITY = 25;
    private static final List<SparseArray<Point>> cityLights = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(int i, int i2, List<Entity> list, int i3, TiledSprite tiledSprite, float f2, float f3, float f4) {
        SparseArray<Point> lightSet = getLightSet(i);
        int i4 = 0;
        for (int i5 = 0; i5 < lightSet.size(); i5++) {
            int keyAt = lightSet.keyAt(i4);
            Point point = lightSet.get(keyAt);
            TiledSprite tiledSprite2 = (TiledSprite) list.get((i2 * 12) + i4);
            tiledSprite2.setCurrentTileIndex(keyAt);
            float f5 = (f4 * 250.0f) / 2.0f;
            float f6 = (250.0f * f2) / 2.0f;
            float y = (tiledSprite.getY() + f5) - f6;
            float x = ((tiledSprite.getX() + f5) - f6) + (point.getX() * f2);
            float y2 = y + (point.getY() * f2);
            tiledSprite2.setPosition(x, y2);
            tiledSprite2.setScale(f2);
            tiledSprite2.setAlpha(0.9f);
            tiledSprite2.setVisible(i3 > i4 * 25);
            tiledSprite2.setRotation(f3);
            if (f3 != 0.0f) {
                Point rotate = Functions.rotate(tiledSprite.getX() + (tiledSprite.getWidthScaled() / 2.0f), tiledSprite.getY() + (tiledSprite.getHeightScaled() / 2.0f), x + (tiledSprite2.getWidthScaled() / 2.0f), y2 + (tiledSprite2.getHeightScaled() / 2.0f), f3);
                tiledSprite2.setPosition(rotate.getX() - (tiledSprite2.getWidthScaled() / 2.0f), rotate.getY() - (tiledSprite2.getHeightScaled() / 2.0f));
                tiledSprite2.setRotationCenterX(tiledSprite2.getWidthScaled() / 2.0f);
                tiledSprite2.setRotationCenterY(tiledSprite2.getHeightScaled() / 2.0f);
            }
            i4++;
        }
    }

    private static SparseArray<Point> getLightSet(int i) {
        return cityLights.get(i);
    }

    public static void set() {
        SparseArray<Point> sparseArray = new SparseArray<>();
        sparseArray.put(0, new Point(170.0f, 145.0f));
        sparseArray.put(4, new Point(185.0f, 70.0f));
        sparseArray.put(9, new Point(160.0f, 40.0f));
        sparseArray.put(11, new Point(155.0f, 95.0f));
        sparseArray.put(1, new Point(150.0f, 30.0f));
        sparseArray.put(3, new Point(145.0f, 155.0f));
        sparseArray.put(6, new Point(160.0f, 55.0f));
        sparseArray.put(8, new Point(165.0f, 105.0f));
        sparseArray.put(2, new Point(150.0f, 45.0f));
        sparseArray.put(10, new Point(170.0f, 70.0f));
        sparseArray.put(7, new Point(150.0f, 155.0f));
        sparseArray.put(5, new Point(135.0f, 170.0f));
        List<SparseArray<Point>> list = cityLights;
        list.add(sparseArray);
        SparseArray<Point> sparseArray2 = new SparseArray<>();
        sparseArray2.put(0, new Point(165.0f, 125.0f));
        sparseArray2.put(1, new Point(175.0f, 80.0f));
        sparseArray2.put(2, new Point(170.0f, 50.0f));
        sparseArray2.put(3, new Point(180.0f, 60.0f));
        sparseArray2.put(4, new Point(125.0f, 20.0f));
        sparseArray2.put(5, new Point(175.0f, 105.0f));
        sparseArray2.put(6, new Point(135.0f, 50.0f));
        sparseArray2.put(7, new Point(160.0f, 155.0f));
        sparseArray2.put(8, new Point(125.0f, 160.0f));
        sparseArray2.put(9, new Point(155.0f, 85.0f));
        sparseArray2.put(10, new Point(140.0f, 135.0f));
        sparseArray2.put(11, new Point(145.0f, 35.0f));
        list.add(sparseArray2);
        SparseArray<Point> sparseArray3 = new SparseArray<>();
        sparseArray3.put(11, new Point(135.0f, 160.0f));
        sparseArray3.put(9, new Point(155.0f, 40.0f));
        sparseArray3.put(7, new Point(110.0f, 170.0f));
        sparseArray3.put(5, new Point(170.0f, 75.0f));
        sparseArray3.put(3, new Point(155.0f, 150.0f));
        sparseArray3.put(1, new Point(165.0f, 110.0f));
        sparseArray3.put(0, new Point(155.0f, 100.0f));
        sparseArray3.put(2, new Point(160.0f, 75.0f));
        sparseArray3.put(4, new Point(155.0f, 50.0f));
        sparseArray3.put(6, new Point(160.0f, 120.0f));
        sparseArray3.put(8, new Point(145.0f, 25.0f));
        sparseArray3.put(10, new Point(160.0f, 35.0f));
        list.add(sparseArray3);
    }
}
