package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Scenes.BuildingsScene;
import com.birdshel.Uciana.Scenes.ColoniesScene;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Scenes.FleetsScene;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import com.birdshel.Uciana.Scenes.PlanetScene;
import com.birdshel.Uciana.Scenes.SystemScene;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Moon {
    public static float SPRITE_SIZE = 100.0f;
    private final int imageIndex;
    private final float size;

    public Moon() {
        this.imageIndex = Functions.random.nextInt(6);
        this.size = (Functions.random.nextInt(7) + 8) * 0.1f;
    }

    public static float getScale(ExtendedScene extendedScene) {
        if (extendedScene instanceof PlanetScene) {
            return 1.125f;
        }
        if (extendedScene instanceof SystemScene) {
            return 0.425f;
        }
        if (extendedScene instanceof GalaxyScene) {
            return 0.3f;
        }
        if ((extendedScene instanceof ColoniesScene) || (extendedScene instanceof BuildingsScene)) {
            return 0.35f;
        }
        return extendedScene instanceof FleetsScene ? 0.2f : 1.0f;
    }

    public int getImageIndex() {
        return this.imageIndex;
    }

    public float getSize() {
        return this.size;
    }

    public Moon(int i, float f2) {
        this.imageIndex = i;
        this.size = f2;
    }
}
