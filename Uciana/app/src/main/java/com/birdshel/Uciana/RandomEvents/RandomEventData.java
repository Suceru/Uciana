package com.birdshel.Uciana.RandomEvents;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class RandomEventData {
    private int data1;
    private int data2;
    private int data3;
    private final RandomEventType type;

    public RandomEventData(RandomEventType randomEventType, int i, int i2, int i3) {
        this.type = randomEventType;
        this.data1 = i;
        this.data2 = i2;
        this.data3 = i3;
    }

    public int getData1() {
        return this.data1;
    }

    public int getData2() {
        return this.data2;
    }

    public int getData3() {
        return this.data3;
    }

    public RandomEventType getType() {
        return this.type;
    }

    public void setData1(int i) {
        this.data1 = i;
    }

    public void setData2(int i) {
        this.data2 = i;
    }

    public void setData3(int i) {
        this.data3 = i;
    }
}
