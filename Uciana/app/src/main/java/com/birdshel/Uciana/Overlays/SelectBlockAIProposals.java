package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SelectBlockAIProposals extends ExtendedChildScene {
    private final TiledSprite closeButton;
    private final Entity[] empireButtons;

    public SelectBlockAIProposals(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        this.empireButtons = new Entity[7];
        this.G.setAlpha(0.6f);
        Sprite t = t(0.0f, 240.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 240.0f);
        t.setAlpha(0.9f);
        this.I = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        Text u = u(0.0f, 300.0f, game.fonts.infoFont, game.getActivity().getString(R.string.select_block_ai_proposals), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        u.setX((getWidth() / 2.0f) - (u.getWidthScaled() / 2.0f));
        for (int i = 0; i < 7; i++) {
            TiledSprite empireButton = new EmpireButton(game, vertexBufferObjectManager);
            attachChild(empireButton);
            empireButton.setPosition(0.0f, 367.0f);
            empireButton.setVisible(false);
            n(empireButton);
            this.empireButtons[i] = empireButton;
        }
        TiledSprite w = w(getWidth() - 120.0f, 240.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w;
        n(w);
    }

    private void checkActionUp(Point point) {
        Entity[] entityArr;
        if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
        for (Entity entity : this.empireButtons) {
            if (isClicked(entity, point)) {
                empireButtonPressed((EmpireButton) entity);
            }
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void empireButtonPressed(EmpireButton empireButton) {
        int empireID = empireButton.getEmpireID();
        this.C.getCurrentEmpire().setHideAIProposals(empireID, !this.C.getCurrentEmpire().isAIProposalsHidden(empireID));
        empireButton.setAlpha(1.0f);
        if (this.C.getCurrentEmpire().isAIProposalsHidden(empireID)) {
            empireButton.setAlpha(0.45f);
        }
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            checkActionUp(point);
        }
    }

    public void setOverlay() {
        for (Entity entity : this.empireButtons) {
            entity.setVisible(false);
        }
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (this.C.empires.get(i2).isAlive() && !this.C.empires.get(i2).isHuman() && i2 != this.C.getCurrentPlayer() && this.C.getCurrentEmpire().isEmpireKnown(i2)) {
                EmpireButton empireButton = (EmpireButton) this.empireButtons[i];
                empireButton.setVisible(true);
                empireButton.setAlpha(1.0f);
                if (this.C.getCurrentEmpire().isAIProposalsHidden(i2)) {
                    empireButton.setAlpha(0.45f);
                }
                empireButton.set(i2);
                i++;
            }
        }
        int i3 = 0;
        for (Entity entity2 : this.empireButtons) {
            if (entity2.isVisible()) {
                i3++;
            }
        }
        int width = (((int) getWidth()) / 2) - ((i3 * 120) / 2);
        for (Entity entity3 : this.empireButtons) {
            entity3.setX(width);
            width += 120;
        }
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }
}
