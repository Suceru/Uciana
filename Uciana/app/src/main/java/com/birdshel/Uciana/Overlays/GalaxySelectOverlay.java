package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.GalaxySelectListElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.StarSystems.StarSystem;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GalaxySelectOverlay extends ExtendedChildScene {
    private final int ITEM_SIZE;
    private final VertexBufferObjectManager bufferManager;
    private final TiledSprite closeButton;
    private final List<GalaxySelectListElement> galaxySelectListElements;
    private boolean isScroll;
    private float lastY;
    private float pressedY;
    private final ScrollBarControl scrollBar;
    private final Text selectHeader;
    private final Scene selectList;
    private final Sprite selectPress;
    private List<Fleet> selectedFleets;
    private List<StarSystem> selectedStarSystem;

    public GalaxySelectOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.galaxySelectListElements = new ArrayList();
        this.isScroll = false;
        this.ITEM_SIZE = 100;
        this.bufferManager = vertexBufferObjectManager;
        Sprite t = t((getWidth() - 700.0f) / 2.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = t;
        t.setSize(700.0f, 100.0f);
        Scene scene = new Scene();
        this.selectList = scene;
        scene.setPosition(0.0f, 70.0f);
        scene.setBackgroundEnabled(false);
        attachChild(scene);
        ScrollBarControl scrollBarControl = new ScrollBarControl(((getWidth() - 700.0f) / 2.0f) + 705.0f, 40.0f, 100, 680.0f, game, vertexBufferObjectManager);
        this.scrollBar = scrollBarControl;
        attachChild(scrollBarControl);
        Text v = v((getWidth() - 700.0f) / 2.0f, 20.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager, true);
        this.selectHeader = v;
        v.setColor(Color.CYAN);
        TiledSprite w = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w;
        n(w);
    }

    private void checkActionDown(int i, Point point) {
        if (point.getX() <= (getWidth() - 700.0f) / 2.0f || point.getX() >= ((getWidth() - 700.0f) / 2.0f) + 700.0f || point.getY() <= 70.0f) {
            return;
        }
        this.pressedY = point.getY();
        this.isScroll = false;
        this.lastY = point.getY();
        if (this.selectedStarSystem.size() + this.selectedFleets.size() > i) {
            this.selectPress.setY(this.selectList.getY() + (i * 100));
            this.selectPress.setVisible(true);
        }
    }

    private void checkActionMove(int i, Point point) {
        this.selectPress.setVisible(false);
        if (point.getX() <= (getWidth() - 700.0f) / 2.0f || point.getX() >= ((getWidth() - 700.0f) / 2.0f) + 700.0f) {
            return;
        }
        if (point.getY() > 70.0f) {
            int size = this.selectedStarSystem.size() + this.selectedFleets.size();
            if (size > 6) {
                if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                    this.isScroll = true;
                }
                float y = this.selectList.getY() - (this.lastY - point.getY());
                float f2 = y <= 70.0f ? y : 70.0f;
                float f3 = ((size * 100) - 720) * (-1);
                if (f2 < f3) {
                    f2 = f3;
                }
                this.selectList.setY(f2);
                this.lastY = point.getY();
                this.scrollBar.update(f2);
            }
            if (size <= i || this.isScroll) {
                return;
            }
            this.selectPress.setY(this.selectList.getY() + (i * 100));
            this.selectPress.setVisible(true);
        }
    }

    private void checkActionUp(int i, Point point) {
        this.selectPress.setVisible(false);
        if (point.getX() > (getWidth() - 700.0f) / 2.0f && point.getX() < ((getWidth() - 700.0f) / 2.0f) + 700.0f && point.getY() > 70.0f && !this.isScroll) {
            galaxyItemPressed(i);
        }
        if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void galaxyItemPressed(int i) {
        if (this.selectedStarSystem.size() + this.selectedFleets.size() > i) {
            back();
            if (i < this.selectedStarSystem.size()) {
                this.C.galaxyScene.selectSystem(this.selectedStarSystem.get(i).getID());
                return;
            }
            this.C.galaxyScene.selectFleet(this.selectedFleets.get(i - this.selectedStarSystem.size()));
            this.C.sounds.playFleetPressSound();
            Game game = this.C;
            game.vibrate(game.BUTTON_VIBRATE);
        }
    }

    private void moveListToTop() {
        this.selectList.setPosition(0.0f, 70.0f);
    }

    private void setSelectHeader() {
        this.selectHeader.setText(this.C.getActivity().getString(R.string.galaxy_select_fleet));
        if (this.selectedStarSystem.size() > 0) {
            this.selectHeader.setText(this.C.getActivity().getString(R.string.galaxy_select_system_or_fleet));
        }
        Text text = this.selectHeader;
        text.setX(640.0f - (text.getWidthScaled() / 2.0f));
    }

    private void showTheFleets() {
        int size = this.selectedStarSystem.size();
        for (Fleet fleet : this.selectedFleets) {
            if (size >= this.galaxySelectListElements.size()) {
                GalaxySelectListElement galaxySelectListElement = new GalaxySelectListElement(this.C, this.bufferManager, getWidth());
                galaxySelectListElement.setPosition(0.0f, size * 100);
                this.selectList.attachChild(galaxySelectListElement);
                this.galaxySelectListElements.add(galaxySelectListElement);
            }
            this.galaxySelectListElements.get(size).setVisible(true);
            this.galaxySelectListElements.get(size).set(fleet);
            size++;
        }
    }

    private void showTheSystems() {
        int i = 0;
        for (StarSystem starSystem : this.selectedStarSystem) {
            if (i >= this.galaxySelectListElements.size()) {
                GalaxySelectListElement galaxySelectListElement = new GalaxySelectListElement(this.C, this.bufferManager, getWidth());
                galaxySelectListElement.setPosition(0.0f, i * 100);
                this.selectList.attachChild(galaxySelectListElement);
                this.galaxySelectListElements.add(galaxySelectListElement);
            }
            this.galaxySelectListElements.get(i).setVisible(true);
            this.galaxySelectListElements.get(i).set(starSystem);
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        int y = ((int) (point.getY() - this.selectList.getY())) / 100;
        if (i == 0) {
            checkActionDown(y, point);
        } else if (i == 1) {
            checkActionUp(y, point);
        } else if (i != 2) {
        } else {
            checkActionMove(y, point);
        }
    }

    public void setOverlay(List<StarSystem> list, List<Fleet> list2) {
        this.selectedStarSystem = list;
        this.selectedFleets = list2;
        moveListToTop();
        setSelectHeader();
        for (GalaxySelectListElement galaxySelectListElement : this.galaxySelectListElements) {
            galaxySelectListElement.setVisible(false);
        }
        showTheSystems();
        showTheFleets();
        this.scrollBar.update(40.0f, list.size() + list2.size());
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
