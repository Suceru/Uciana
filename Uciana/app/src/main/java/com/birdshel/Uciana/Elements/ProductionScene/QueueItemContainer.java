package com.birdshel.Uciana.Elements.ProductionScene;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class QueueItemContainer extends Entity {
    private Colony colony;
    private int itemIndex;
    private float oldY;
    private float queueItemPressedY;
    private QueueItem selectedQueueItem;
    private final List<QueueItem> queueItems = new ArrayList();
    private boolean queueItemMoved = false;
    private boolean queueItemPressed = false;
    private final int ITEM_SIZE = 105;

    public QueueItemContainer(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        for (int i = 0; i < 5; i++) {
            QueueItem queueItem = new QueueItem(game, vertexBufferObjectManager);
            queueItem.setY(i * 105);
            this.queueItems.add(queueItem);
            attachChild(queueItem);
        }
    }

    private void checkActionDown(Point point) {
        for (QueueItem queueItem : this.queueItems) {
            if (queueItem.isPressed(point)) {
                this.queueItemPressed = true;
                this.selectedQueueItem = queueItem;
                this.oldY = point.getY();
                this.queueItemPressedY = this.selectedQueueItem.getY();
                for (QueueItem queueItem2 : this.queueItems) {
                    queueItem2.setZIndex(1);
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
            if (y + 105.0f > this.colony.getQueue().size() * 105) {
                y = (this.colony.getQueue().size() * 105) - 105;
            }
            this.selectedQueueItem.setY(y);
            this.oldY = point.getY();
            float y2 = this.queueItemPressedY - this.selectedQueueItem.getY();
            if (y2 > 10.0f || y2 < -10.0f) {
                this.queueItemMoved = true;
            }
            int i = (int) ((y + 10.0f) / 105.0f);
            if (i >= this.colony.getQueue().size() || i == this.itemIndex) {
                return;
            }
            moveItemToIndex(this.queueItems.get(i), this.itemIndex);
            Collections.swap(this.queueItems, i, this.itemIndex);
            Collections.swap(this.colony.getQueue(), i, this.itemIndex);
            this.itemIndex = i;
        }
    }

    private void moveItemToIndex(QueueItem queueItem, int i) {
        queueItem.registerEntityModifier(new MoveModifier(0.2f, queueItem.getX(), queueItem.getX(), queueItem.getY(), i * 105));
        queueItem.n(i);
        refresh();
    }

    public void checkInput(int i, Point point) {
        Point point2 = new Point(point.getX(), point.getY() - getY());
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
        for (QueueItem queueItem : this.queueItems) {
            if (queueItem.isVisible()) {
                queueItem.checkInput(i, point2);
            }
        }
    }

    public float getBottomY() {
        return getY() + (this.colony.getQueue().size() * 105);
    }

    public void refresh() {
        int i;
        Iterator<QueueItem> it = this.queueItems.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            it.next().setVisible(false);
        }
        ProductionItem currentItem = this.colony.getManufacturing().getCurrentItem();
        boolean z = currentItem.isRepeatOn() || currentItem.getID().equals(Buildings.getBuilding(BuildingID.TRADEGOODS).getStringID());
        Iterator<ProductionItem> it2 = this.colony.getQueue().iterator();
        while (it2.hasNext()) {
            ProductionItem next = it2.next();
            QueueItem queueItem = this.queueItems.get(i);
            queueItem.m(next, i, this.colony, z);
            if (next.isRepeatOn() || next.getID().equals(Buildings.getBuilding(BuildingID.TRADEGOODS).getStringID())) {
                z = true;
            }
            queueItem.setVisible(true);
            i++;
        }
    }

    public void resetIfPressed() {
        if (this.queueItemPressed) {
            moveItemToIndex(this.selectedQueueItem, this.itemIndex);
            this.selectedQueueItem.l(false);
        }
        this.queueItemPressed = false;
    }

    public void set(Colony colony) {
        this.colony = colony;
        refresh();
    }

    public void upPress() {
        this.queueItemMoved = false;
    }
}
