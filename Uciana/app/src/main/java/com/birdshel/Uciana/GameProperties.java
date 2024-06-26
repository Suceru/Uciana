package com.birdshel.Uciana;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import com.birdshel.Uciana.Math.Point;
import com.google.android.gms.games.GamesStatusCodes;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameProperties {
    public static final int ASCENDED_EMPIRE_ID = 8;
    public static final int ASCENDED_FTL_SPEED = 6;
    public static final int BASE_COLONY_HIT_POINTS = 50;
    public static final int BASE_POWER_LEVEL = 5;
    public static final int BATTLE_CHANCE_FOR_CRITICAL = 25;
    public static final int BATTLE_CHANCE_FOR_SHOCKWAVE = 25;
    public static final float BATTLE_CRITICAL_BONUS = 0.5f;
    public static final int BATTLE_MAX_SHIP_LIMIT = 21;
    public static final int BATTLE_MAX_TURNS = 100;
    public static final int BLACKHOLE_DILATION_RANGE = 7;
    public static final int BLACKHOLE_MAX_SIZE = 110;
    public static final float BLACKHOLE_MAX_SPIN_SPEED = 4.5f;
    public static final int BLACKHOLE_MIN_SIZE = 75;
    public static final float BLACKHOLE_MIN_SPIN_SPEED = 1.5f;
    public static final int COMMAND_POINT_COST = 50;
    public static final int CORE_BREACH_DAMAGE_FACTOR_MAX = 40;
    public static final int CORE_BREACH_DAMAGE_FACTOR_MIN = 20;
    public static final int CUSTOM_EMPIRE_ID = -3;
    public static final int DISTANCE_CONST = 10;
    public static final int HEX_HIT_BOX_SIZE = 80;
    public static final int INITIAL_GROUND_COMBAT_STRENGTH = 10;
    public static final int INITIAL_STARTING_POPULATION = 10;
    public static final int INVASION_MAX_ROWS = 4;
    public static final int INVASION_MIN_PER_ROW = 6;
    public static final int MAX_ASTEROIDS_BATTLE_SCENE = 10;
    public static final int MAX_ASTEROIDS_PER_ASTEROID_BELT = 10;
    public static final int MAX_BATTLE_GRID_COLUMNS = 15;
    public static final int MAX_BATTLE_GRID_ROWS = 7;
    public static final int MAX_ION_STORMS_BATTLE_SCENE = 7;
    public static final int MAX_NEBULA_COUNT = 7;
    public static final int MAX_PLAYERS = 7;
    public static final int MAX_PRODUCTION_QUEUE_SIZE = 5;
    public static final int MAX_RESOURCES_PER_PLANET = 4;
    public static final int MAX_STAR_COUNT = 50;
    public static final int MAX_SYSTEM_OBJECTS_PER_SYSTEM = 5;
    public static final int MAX_WORMHOLES = 13;
    public static final int MAX_WORMHOLES_PER_SYSTEM = 4;
    public static final int MINIMUM_CREDITS_ALLOWED = -500;
    public static final int MIN_DISTANCE = 80;
    public static final int MIN_NEBULA_COUNT = 2;
    public static final int MIN_REFIT_COST = 50;
    public static final int MONSTER_ATTACK_TURNS = 3;
    public static final int MONSTER_EMPIRE_ID = 9;
    public static final int MONSTER_FTL_SPEED = 3;
    public static final int MONSTER_START_DISTANCE = 15;
    public static final int MONSTER_START_ETA = 5;
    public static final int MOVE_COST_PER_M = 1;
    public static final int NEBULA_SPRITE_SIZE = 300;
    public static final int NUMBER_OF_ASTEROIDS = 7;
    public static final int NUMBER_OF_STAR_NAMES = 60;
    public static final int PERCENT_CHANCE_FLUX_DESTROY_SHIP = 30;
    public static final int PERCENT_CHANCE_OF_COMETS_APPEARING = 33;
    public static final int PERCENT_CHANCE_OF_ION_STORM = 40;
    public static final int PERFECT_WORLD_PERCENT = 20;
    public static final int RANDOM_EMPIRE_ID = -2;
    public static final int REQUEST_READ_STORAGE = 2;
    public static final int REQUEST_WRITE_STORAGE = 1;
    public static final int SCIENCE_PER_SCIENTIST = 2;
    public static final int SPACE_RIFT_MAX_SIZE = 60;
    public static final float SPACE_RIFT_MAX_SPIN_SPEED = 40.0f;
    public static final int SPACE_RIFT_MIN_SIZE = 40;
    public static final float SPACE_RIFT_MIN_SPIN_SPEED = 15.0f;
    public static final int SPACE_RIFT_RANGE = 7;
    public static final int STARTING_COLONY_SCANNER_RANGE = 7;
    public static final int STARTING_CREDITS = 100;
    public static final int STARTING_FTL_SPEED = 3;
    public static final int STARTING_FUEL_RANGE = 10;
    public static final int STARTING_SHIP_COMMUNICATION_RANGE = 0;
    public static final int STARTING_SHIP_SCANNER_RANGE = 5;
    public static final float STARTING_TAX_RATE = 0.0f;
    public static final int STARTING_TROOP_RIFLE_STRENGTH = 5;
    public static final int TORPEDO_DESTRUCTION_CHANCE = 50;
    public static final float TRIBUTE_PERCENT = 0.1f;
    public static final int TROOPS_PER_TRANSPORT = 5;
    public static final int WORMHOLES_COUNT_HIGH = 2;
    public static final int WORMHOLES_COUNT_LOW = 3;
    public static final int WORMHOLES_COUNT_NONE = 0;
    public static final int WORMHOLES_COUNT_NORMAL = 1;
    public static final int WORMHOLES_COUNT_RANDOM = 4;
    public static final int WORMHOLE_MAX_SIZE = 130;
    public static final float WORMHOLE_MAX_SPIN_SPEED = 3.5f;
    public static final int WORMHOLE_MIN_SIZE = 70;
    public static final float WORMHOLE_MIN_SPIN_SPEED = 1.75f;
    public static final Point[] STARTING_ATTACK_POSITIONS = {new Point(0.0f, 3.0f), new Point(0.0f, 2.0f), new Point(0.0f, 4.0f), new Point(1.0f, 3.0f), new Point(1.0f, 2.0f), new Point(1.0f, 4.0f), new Point(0.0f, 5.0f), new Point(0.0f, 1.0f), new Point(1.0f, 1.0f), new Point(1.0f, 5.0f), new Point(2.0f, 3.0f), new Point(2.0f, 2.0f), new Point(2.0f, 4.0f), new Point(0.0f, 6.0f), new Point(0.0f, 0.0f), new Point(1.0f, 0.0f), new Point(1.0f, 6.0f), new Point(2.0f, 5.0f), new Point(2.0f, 1.0f), new Point(2.0f, 0.0f), new Point(2.0f, 6.0f)};
    public static final Point[] STARTING_DEFENSE_POSITIONS = {new Point(14.0f, 3.0f), new Point(14.0f, 2.0f), new Point(14.0f, 4.0f), new Point(13.0f, 3.0f), new Point(13.0f, 2.0f), new Point(13.0f, 4.0f), new Point(14.0f, 5.0f), new Point(14.0f, 1.0f), new Point(13.0f, 1.0f), new Point(13.0f, 5.0f), new Point(12.0f, 3.0f), new Point(12.0f, 2.0f), new Point(12.0f, 4.0f), new Point(14.0f, 6.0f), new Point(14.0f, 0.0f), new Point(13.0f, 0.0f), new Point(13.0f, 6.0f), new Point(12.0f, 5.0f), new Point(12.0f, 1.0f), new Point(12.0f, 0.0f), new Point(12.0f, 6.0f)};
    public static final Point BATTLE_TURRET_1_POSITION = new Point(11.0f, 1.0f);
    public static final Point BATTLE_TURRET_2_POSITION = new Point(11.0f, 4.0f);
    public static final int[][] ENGINEERING_RESEARCH_COST = {new int[]{400, TypedValues.Transition.TYPE_DURATION, 1100, 1600, 2200, 3000, GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND, 5200}, new int[]{600, 1000, 1600, 2400, 3200, 4200, GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED, GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY}};
    public static final int[][] PHYSICS_RESEARCH_COST = {new int[]{400, TypedValues.Transition.TYPE_DURATION, 1100, 1600, 2200, 3000, GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND, 5200}, new int[]{600, 1000, 1600, 2400, 3200, 4200, GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED, GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY}};
    public static final int[][] CHEMISTRY_RESEARCH_COST = {new int[]{400, TypedValues.Transition.TYPE_DURATION, 1100, 1600, 2200, 3000, GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND, 5200}, new int[]{600, 1000, 1600, 2400, 3200, 4200, GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED, GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY}};
    public static final int[][] ENERGY_RESEARCH_COST = {new int[]{400, TypedValues.Transition.TYPE_DURATION, 1100, 1600, 2200, 3000, GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND, 5200}, new int[]{600, 1000, 1600, 2400, 3200, 4200, GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED, GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY}};
    public static final float[][] MINIATURIZATION_SPACE = {new float[]{1.0f, 0.8f, 0.65f, 0.5f, 0.35f, 0.25f}, new float[]{1.0f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f}};
    public static final float[] MINIATURIZATION_COST = {1.0f, 0.75f, 0.55f, 0.4f, 0.3f, 0.25f};

    public static boolean isNonNormalEmpire(int i) {
        return i == 8 || i == 9;
    }
}
