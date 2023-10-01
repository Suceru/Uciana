package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EmpireButton extends TiledSprite {
    private final TiledSprite banner;
    private int empireID;

    public EmpireButton(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        setScaleCenter(0.0f, 0.0f);
        this.empireID = -1;
        setCurrentTileIndex(ButtonsEnum.EMPIRE.ordinal());
        TiledSprite tiledSprite = new TiledSprite(18.0f, 3.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.banner = tiledSprite;
        tiledSprite.setSize(80.0f, 80.0f);
        attachChild(tiledSprite);
    }

    public int getEmpireID() {
        return this.empireID;
    }

    public void set(int i) {
        this.empireID = i;
        setColor(Empires.getEmpireColor(i));
        this.banner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(i));
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        this.banner.setAlpha(f2);
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setVisible(boolean z) {
        super.setVisible(z);
        this.banner.setVisible(z);
    }
}
