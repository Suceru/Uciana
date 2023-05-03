package com.birdshel.Uciana.AI.AIObjects;

public enum SystemObjectAttackAction {
    BOMB,
    BOMBARD,
    INVADE,
    NO_ACTION,
    DESTROY;

    public static SystemObjectAttackAction getAction(int i) {
        return values()[i];
    }
}
