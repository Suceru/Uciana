package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.andengine.engine.Engine;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public abstract class ExtendedChildScene extends CameraScene {
    protected Game C;
    protected Engine D;
    protected CharSequence E;
    protected TextOptions F;
    protected Sprite G;
    protected ArrayList<TiledSprite> H;
    protected TiledSprite I;
    protected MessageOverlay J;

    public ExtendedChildScene(Game game, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        super(game.camera);
        this.E = CharBuffer.wrap(new char[255]);
        this.F = new TextOptions(HorizontalAlign.CENTER);
        this.H = new ArrayList<>();
        setScene(game, vertexBufferObjectManager, z, 0.0f);
    }

    private void registerUpdate() {
        registerUpdateHandler(new IUpdateHandler() { // from class: com.birdshel.Uciana.Overlays.ExtendedChildScene.1
            {
                ExtendedChildScene.this = this;
            }

            @Override // org.andengine.engine.handler.IUpdateHandler
            public void onUpdate(float f2) {
                ExtendedChildScene.this.D.runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Overlays.ExtendedChildScene.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ExtendedChildScene.this.update();
                    }
                });
            }

            @Override // org.andengine.engine.handler.IUpdateHandler
            public void reset() {
            }
        });
    }

    private void setScene(Game game, VertexBufferObjectManager vertexBufferObjectManager, boolean z, float f2) {
        this.C = game;
        this.D = game.getEngine();
        setTouchListener(f2);
        registerUpdate();
        if (z) {
            Sprite t = t(0.0f, 0.0f, game.graphics.backgroundTexture, vertexBufferObjectManager, true);
            setBackgroundEnabled(true);
            t.setSize(getWidth(), 720.0f);
        } else {
            setBackgroundEnabled(false);
            Sprite t2 = t(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
            this.G = t2;
            t2.setSize(getWidth(), 720.0f);
        }
        TiledSprite w = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        this.I = w;
        w.setScaleCenter(0.0f, 0.0f);
    }

    private void setTouchListener(final float f2) {
        setOnSceneTouchListener(new IOnSceneTouchListener() { // from class: com.birdshel.Uciana.Overlays.ExtendedChildScene.2
            {
                ExtendedChildScene.this = this;
            }

            @Override // org.andengine.entity.scene.IOnSceneTouchListener
            public boolean onSceneTouchEvent(Scene scene, TouchEvent touchEvent) {
                float x = touchEvent.getMotionEvent().getX();
                float y = touchEvent.getMotionEvent().getY();
                ExtendedChildScene.this.checkInput(touchEvent.getAction(), new Point(((ExtendedChildScene.this.getWidth() / ExtendedChildScene.this.C.camera.getSurfaceWidth()) * x) - f2, (720.0f / ExtendedChildScene.this.C.camera.getSurfaceHeight()) * y));
                return true;
            }
        });
    }

    public void blinkSprite(Sprite sprite) {
        sprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        sprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(1.0f, 0.25f, 1.0f), new AlphaModifier(1.0f, 1.0f, 0.25f))));
    }

    public void checkInput(int i, Point point) {
        if (i != 0) {
            if (i == 1) {
                this.I.setVisible(false);
                return;
            } else if (i != 2) {
                return;
            } else {
                this.I.setVisible(false);
            }
        }
        Iterator<TiledSprite> it = this.H.iterator();
        while (it.hasNext()) {
            TiledSprite next = it.next();
            if (q(next, point)) {
                this.I.setPosition(next.getX(), next.getY());
                this.I.setSize(next.getWidthScaled(), next.getHeightScaled());
                this.I.setVisible(true);
                return;
            }
        }
    }

    public float getWidth() {
        return this.C.getWidth();
    }

    public boolean isClicked(Entity entity, Point point) {
        return r((Sprite) entity, point, 0.0f, 0.0f);
    }

    public void n(TiledSprite tiledSprite) {
        if (this.H.contains(tiledSprite)) {
            return;
        }
        this.H.add(tiledSprite);
    }

    public void o(Text text) {
        text.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        text.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.4f, 0.25f, 1.0f), new AlphaModifier(0.4f, 1.0f, 0.25f))));
    }

    public void p(List<Entity> list, final Scene scene) {
        final ArrayList arrayList = new ArrayList(list);
        list.clear();
        this.C.getActivity().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Overlays.ExtendedChildScene.3
            {
                ExtendedChildScene.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (Entity entity : arrayList) {
                    scene.detachChild(entity);
                }
                System.gc();
            }
        });
    }

    public boolean q(Sprite sprite, Point point) {
        return r(sprite, point, 0.0f, 0.0f);
    }

    protected boolean r(Sprite sprite, Point point, float f2, float f3) {
        if (sprite.getAlpha() == 0.4f) {
            return false;
        }
        float x = sprite.getX() - f2;
        float widthScaled = sprite.getWidthScaled() + x + f2;
        float y = sprite.getY() - f3;
        return sprite.isVisible() && ((point.getX() > x ? 1 : (point.getX() == x ? 0 : -1)) > 0 && (point.getX() > widthScaled ? 1 : (point.getX() == widthScaled ? 0 : -1)) < 0 && (point.getY() > y ? 1 : (point.getY() == y ? 0 : -1)) > 0 && (point.getY() > ((sprite.getHeightScaled() + y) + f3) ? 1 : (point.getY() == ((sprite.getHeightScaled() + y) + f3) ? 0 : -1)) < 0);
    }

    public boolean s(Entity entity, Point point, float f2, float f3) {
        Sprite sprite = (Sprite) entity;
        float x = sprite.getX() - f2;
        float widthScaled = sprite.getWidthScaled() + x + f2;
        float y = sprite.getY() - f3;
        return sprite.isVisible() && ((point.getX() > x ? 1 : (point.getX() == x ? 0 : -1)) > 0 && (point.getX() > widthScaled ? 1 : (point.getX() == widthScaled ? 0 : -1)) < 0 && (point.getY() > y ? 1 : (point.getY() == y ? 0 : -1)) > 0 && (point.getY() > ((sprite.getHeightScaled() + y) + f3) ? 1 : (point.getY() == ((sprite.getHeightScaled() + y) + f3) ? 0 : -1)) < 0);
    }

    public void showMessage(Message message) {
        this.J.setOverlay(message);
        setChildScene(this.J, false, false, true);
    }

    public Sprite t(float f2, float f3, ITextureRegion iTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        Sprite sprite = new Sprite(f2, f3, iTextureRegion, vertexBufferObjectManager);
        sprite.setVisible(z);
        attachChild(sprite);
        return sprite;
    }

    public Text u(float f2, float f3, IFont iFont, CharSequence charSequence, TextOptions textOptions, VertexBufferObjectManager vertexBufferObjectManager) {
        Text text = new Text(f2, f3, iFont, charSequence, textOptions, vertexBufferObjectManager);
        attachChild(text);
        return text;
    }

    protected abstract void update();

    public Text v(float f2, float f3, IFont iFont, CharSequence charSequence, TextOptions textOptions, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        Text text = new Text(f2, f3, iFont, charSequence, textOptions, vertexBufferObjectManager);
        text.setVisible(z);
        attachChild(text);
        return text;
    }

    public TiledSprite w(float f2, float f3, ITiledTextureRegion iTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, int i, boolean z) {
        TiledSprite tiledSprite = new TiledSprite(f2, f3, iTiledTextureRegion, vertexBufferObjectManager);
        tiledSprite.setCurrentTileIndex(i);
        tiledSprite.setVisible(z);
        attachChild(tiledSprite);
        return tiledSprite;
    }

    public TiledSprite x(float f2, float f3, ITiledTextureRegion iTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        TiledSprite tiledSprite = new TiledSprite(f2, f3, iTiledTextureRegion, vertexBufferObjectManager);
        tiledSprite.setVisible(z);
        tiledSprite.setScaleCenter(0.0f, 0.0f);
        attachChild(tiledSprite);
        return tiledSprite;
    }

    public ExtendedChildScene(Game game, VertexBufferObjectManager vertexBufferObjectManager, boolean z, float f2) {
        super(game.camera);
        this.E = CharBuffer.wrap(new char[255]);
        this.F = new TextOptions(HorizontalAlign.CENTER);
        this.H = new ArrayList<>();
        setScene(game, vertexBufferObjectManager, z, f2);
    }
}
