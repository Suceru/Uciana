package com.birdshel.Uciana.Math;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Point {
    private float x;
    private float y;

    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public Point copy() {
        return new Point(this.x, this.y);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Point) {
            Point point = (Point) obj;
            return point.x == this.x && point.y == this.y;
        }
        return false;
    }

    public int getDistance(Point point) {
        return (int) Math.hypot(this.x - point.getX(), this.y - point.getY());
    }

    public Point getPositionFromAngleAndDistance(float f2, float f3) {
        double d2 = f2;
        return new Point(this.x + (((float) Math.cos(Math.toRadians(d2))) * f3), this.y + (((float) Math.sin(Math.toRadians(d2))) * f3));
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int hashCode() {
        return (this.x + "," + this.y).hashCode();
    }

    public void setX(float f2) {
        this.x = f2;
    }

    public void setY(float f2) {
        this.y = f2;
    }

    public String toString() {
        return "x: " + this.x + " y: " + this.y;
    }

    public Point(float f2, float f3) {
        this.x = f2;
        this.y = f3;
    }
}
