package com.birdshel.Uciana.Elements.BattleScene;

import com.birdshel.Uciana.Math.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Grid {
    private final Node[][] grid = (Node[][]) Array.newInstance(Node.class, 15, 7);
    private final Point[][] gridPositions = (Point[][]) Array.newInstance(Point.class, 15, 7);

    public Grid() {
        for (int i = 0; i < 15; i++) {
            for (int i2 = 0; i2 < 7; i2++) {
                this.grid[i][i2] = new Node(i, i2);
                float f2 = i * 75;
                float f3 = (i2 * 100) - 15;
                if (i % 2 != 0) {
                    f3 += 50.0f;
                }
                this.gridPositions[i][i2] = new Point(f2, f3);
            }
        }
    }

    private Node getNode(int i, int i2) {
        return this.grid[i][i2];
    }

    public Set<Point> getNodesInRange(Point point, int i) {
        return getNodesInRange(point, 0, i);
    }

    public Point getPosition(int i, int i2) {
        return new Point(this.gridPositions[i][i2].getX(), this.gridPositions[i][i2].getY());
    }

    public void removeNode(int i, int i2) {
        for (Point point : getNode(i, i2).a()) {
            getNode((int) point.getX(), (int) point.getY()).b(new Point(i, i2));
        }
    }

    private Set<Point> getNodesInRange(Point point, int i, int i2) {
        ArrayList<Set> arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        if (i2 == 0) {
            return new HashSet();
        }
        arrayList.add(new HashSet(getNode((int) point.getX(), (int) point.getY()).a()));
        for (int i3 = 0; i3 < i2 - 1; i3++) {
            HashSet hashSet2 = new HashSet();
            for (Point point2 : (Set) arrayList.get(i3)) {
                if (!hashSet.contains(point2)) {
                    hashSet.add(point2);
                    hashSet2.addAll(getNode((int) point2.getX(), (int) point2.getY()).a());
                }
            }
            arrayList.add(hashSet2);
        }
        HashSet hashSet3 = new HashSet();
        for (Set set : arrayList) {
            if (1 >= i) {
                hashSet3.addAll(set);
            }
        }
        return hashSet3;
    }
}
