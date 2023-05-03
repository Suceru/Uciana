package com.birdshel.Uciana.Elements.ProductionScene;

import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildListContainer extends Entity {
    private int itemIndex;
    private float oldY;
    private LinkedList<ProductionItem> queue;
    private float queueItemPressedY;
    private BuildListQueueItem selectedQueueItem;
    private final List<BuildListQueueItem> queueItems = new ArrayList();
    private boolean queueItemMoved = false;
    private boolean queueItemPressed = false;
    private final int ITEM_SIZE = 105;

    public BuildListContainer(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        for (int i = 0; i < 5; i++) {
            BuildListQueueItem buildListQueueItem = new BuildListQueueItem(game, vertexBufferObjectManager, this);
            buildListQueueItem.setY(i * 105);
            this.queueItems.add(buildListQueueItem);
            attachChild(buildListQueueItem);
        }
    }

    private void checkActionDown(Point point) {
        for (BuildListQueueItem buildListQueueItem : this.queueItems) {
            if (buildListQueueItem.isPressed(point)) {
                this.queueItemPressed = true;
                this.selectedQueueItem = buildListQueueItem;
                this.oldY = point.getY();
                this.queueItemPressedY = this.selectedQueueItem.getY();
                for (BuildListQueueItem buildListQueueItem2 : this.queueItems) {
                    buildListQueueItem2.setZIndex(1);
                }
                this.selectedQueueItem.setZIndex(2);
                this.selectedQueueItem.l(true);
                this.itemIndex = (int) (point.getY() / 105.0f);
                sortChildren();
            }
        }
    }

    private void checkActionMove(Point point) {
        if (this.queueItemPressed) {
            float y = this.selectedQueueItem.getY() + (point.getY() - this.oldY);
            if (y < 0.0f) {
                y = 0.0f;
            }
            if (y + 105.0f > this.queue.size() * 105) {
                y = (this.queue.size() * 105) - 105;
            }
            this.selectedQueueItem.setY(y);
            this.oldY = point.getY();
            float y2 = this.queueItemPressedY - this.selectedQueueItem.getY();
            if (y2 > 10.0f || y2 < -10.0f) {
                this.queueItemMoved = true;
            }
            int i = (int) ((y + 10.0f) / 105.0f);
            if (i >= this.queue.size() || i == this.itemIndex) {
                return;
            }
            moveItemToIndex(this.queueItems.get(i), this.itemIndex);
            Collections.swap(this.queueItems, i, this.itemIndex);
            Collections.swap(this.queue, i, this.itemIndex);
            this.itemIndex = i;
        }
    }

    private void moveItemToIndex(BuildListQueueItem buildListQueueItem, int i) {
        buildListQueueItem.registerEntityModifier(new MoveModifier(0.2f, buildListQueueItem.getX(), buildListQueueItem.getX(), buildListQueueItem.getY(), i * 105));
        buildListQueueItem.n(i);
        refresh();
    }

    public void checkInput(int i, Point point) {
        Point point2 = new Point(point.getX() - getX(), point.getY() - getY());
        if (i != 0) {
            boolean z = true;
            if (i == 1) {
                if (this.queueItemMoved) {
                    this.queueItemMoved = false;
                } else {
                    z = false;
                }
                resetIfPressed();
                if (z) {
                    return;
                }
            } else if (i == 2) {
                checkActionMove(point2);
            }
        } else {
            checkActionDown(point2);
        }
        if (this.queueItemMoved) {
            return;
        }
        for (BuildListQueueItem buildListQueueItem : this.queueItems) {
            if (buildListQueueItem.isVisible()) {
                buildListQueueItem.checkInput(i, point2);
            }
        }
    }

    public boolean isQueueItemMoved() {
        return this.queueItemMoved;
    }

    public void refresh() {
        int i;
        Iterator<BuildListQueueItem> it = this.queueItems.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            it.next().setVisible(false);
        }
        Iterator<ProductionItem> it2 = this.queue.iterator();
        while (it2.hasNext()) {
            BuildListQueueItem buildListQueueItem = this.queueItems.get(i);
            buildListQueueItem.m(it2.next(), i);
            buildListQueueItem.setVisible(true);
            i++;
        }
    }

    public void remove(int i) {
        this.queue.remove(i);
        refresh();
    }

    public void resetIfPressed() {
        if (this.queueItemPressed) {
            moveItemToIndex(this.selectedQueueItem, this.itemIndex);
            this.selectedQueueItem.l(false);
        }
        this.queueItemPressed = false;
    }

    public void resetMoved() {
        this.queueItemMoved = false;
    }

    public void set(LinkedList<ProductionItem> linkedList) {
        this.queue = linkedList;
        refresh();
    }

    public void upPress() {
        this.queueItemMoved = false;
    }
}
