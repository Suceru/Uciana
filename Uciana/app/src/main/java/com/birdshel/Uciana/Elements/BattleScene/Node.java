package com.birdshel.Uciana.Elements.BattleScene;

import com.birdshel.Uciana.Math.Point;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class Node {
    private final List<Point> connections;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Node(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        this.connections = arrayList;
        int i3 = i - 1;
        if (i3 >= 0) {
            arrayList.add(new Point(i3, i2));
        }
        int i4 = i2 - 1;
        if (i4 >= 0) {
            arrayList.add(new Point(i, i4));
        }
        int i5 = i + 1;
        if (i5 < 15) {
            arrayList.add(new Point(i5, i2));
        }
        int i6 = i2 + 1;
        if (i6 < 7) {
            arrayList.add(new Point(i, i6));
        }
        if ((i & 1) == 0) {
            if (i3 >= 0 && i4 >= 0) {
                arrayList.add(new Point(i3, i4));
            }
            if (i5 >= 15 || i4 < 0) {
                return;
            }
            arrayList.add(new Point(i5, i4));
            return;
        }
        if (i3 >= 0 && i6 < 7) {
            arrayList.add(new Point(i3, i6));
        }
        if (i5 >= 15 || i6 >= 7) {
            return;
        }
        arrayList.add(new Point(i5, i6));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Point> a() {
        return this.connections;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Point point) {
        for (Point point2 : this.connections) {
            if (point2.equals(point)) {
                this.connections.remove(point);
                return;
            }
        }
    }
}
