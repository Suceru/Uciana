package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameMusic;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
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
public abstract class ExtendedScene extends Scene {
    protected Game game;//B
    protected Sprite C;
    protected TiledSprite G;
    protected MessageOverlay H;
    private Sprite fade;
    private AlphaModifier fadeIn;
    private AlphaModifier fadeOut;
    private long startTime;
    protected CharSequence D = CharBuffer.wrap(new char[255]);
    protected TextOptions E = new TextOptions(HorizontalAlign.CENTER);
    protected ArrayList<TiledSprite> F = new ArrayList<>();
    boolean I = true;
    private boolean pressStarted = false;
    private final Point pressLocation = new Point(0.0f, 0.0f);

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.ExtendedScene$1 */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements IUpdateHandler {
        AnonymousClass1() {
            ExtendedScene.this = r1;
        }

        public /* synthetic */ void lambda$onUpdate$0() {
            ExtendedScene.this.update();
        }

        @Override // org.andengine.engine.handler.IUpdateHandler
        public void onUpdate(float f2) {
            ExtendedScene.this.game.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.m
                {
                    ExtendedScene.AnonymousClass1.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    ExtendedScene.AnonymousClass1.this.lambda$onUpdate$0();
                }
            });
        }

        @Override // org.andengine.engine.handler.IUpdateHandler
        public void reset() {
        }
    }

    private void checkButtonPress(Point point) {
        Iterator<TiledSprite> it = this.F.iterator();
        while (it.hasNext()) {
            TiledSprite next = it.next();
            if (isClicked(next, point)) {
                this.G.setPosition(next.getX(), next.getY());
                this.G.setVisible(true);
            }
        }
    }

    private void fadeOut(final ExtendedScene extendedScene, final Object obj) {
        sortChildren();
        this.fade.setAlpha(0.0f);
        this.fade.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        AlphaModifier alphaModifier = new AlphaModifier(0.25f, 0.0f, 1.0f) { // from class: com.birdshel.Uciana.Scenes.ExtendedScene.2
            {
                ExtendedScene.this = this;
            }

            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: k */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                ExtendedScene.this.releasePoolElements(extendedScene, obj);
                ExtendedScene extendedScene2 = ExtendedScene.this;
                extendedScene2.unregisterEntityModifier(extendedScene2.fadeOut);
                ExtendedScene.this.fade.setAlpha(1.0f);
            }
        };
        this.fadeOut = alphaModifier;
        this.fade.registerEntityModifier(alphaModifier);
    }

    public static /* synthetic */ void lambda$detachListFromScene$1(List list, Entity entity) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            entity.detachChild((Entity) it.next());
        }
        System.gc();
    }

    public /* synthetic */ boolean lambda$setTouchListener$0(float f2, Scene scene, TouchEvent touchEvent) {
        float x = touchEvent.getMotionEvent().getX();
        float y = touchEvent.getMotionEvent().getY();
        float width = ((getWidth() / this.game.camera.getSurfaceWidth()) * x) - f2;
        float surfaceHeight = (720.0f / this.game.camera.getSurfaceHeight()) * y;
        if (this.fade.getAlpha() == 0.0f) {
            checkInput(touchEvent.getAction(), new Point(width, surfaceHeight));
            return true;
        }
        return true;
    }

    private void registerUpdate() {
        registerUpdateHandler(new AnonymousClass1());
    }

    private void setBackground(ITextureRegion iTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {
        Sprite sprite = new Sprite(0.0f, 0.0f, iTextureRegion, vertexBufferObjectManager);
        this.C = sprite;
        sprite.setSize(getWidth(), 720.0f);
        attachChild(this.C);
    }

    private void setTouchListener(final float f2) {
        setOnSceneTouchListener(new IOnSceneTouchListener() { // from class: com.birdshel.Uciana.Scenes.l
            {
                ExtendedScene.this = this;
            }

            @Override // org.andengine.entity.scene.IOnSceneTouchListener
            public final boolean onSceneTouchEvent(Scene scene, TouchEvent touchEvent) {
                boolean lambda$setTouchListener$0;
                lambda$setTouchListener$0 = ExtendedScene.this.lambda$setTouchListener$0(f2, scene, touchEvent);
                return lambda$setTouchListener$0;
            }
        });
    }

    public boolean A(Entity entity, Point point, float f2, float f3) {
        Sprite sprite = (Sprite) entity;
        float x = sprite.getX() - f2;
        float widthScaled = sprite.getWidthScaled() + x + f2;
        float y = sprite.getY() - f3;
        return sprite.isVisible() && ((point.getX() > x ? 1 : (point.getX() == x ? 0 : -1)) > 0 && (point.getX() > widthScaled ? 1 : (point.getX() == widthScaled ? 0 : -1)) < 0 && (point.getY() > y ? 1 : (point.getY() == y ? 0 : -1)) > 0 && (point.getY() > ((sprite.getHeightScaled() + y) + f3) ? 1 : (point.getY() == ((sprite.getHeightScaled() + y) + f3) ? 0 : -1)) < 0);
    }

    protected abstract void B(Point point);

    public void C(List<Entity> list) {
        for (Entity entity : list) {
            entity.setVisible(false);
        }
    }

    public void D() {
        this.G.setVisible(false);
    }

    public Sprite E(float f2, float f3, ITextureRegion iTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        Sprite sprite = new Sprite(f2, f3, iTextureRegion, vertexBufferObjectManager);
        sprite.setVisible(z);
        attachChild(sprite);
        return sprite;
    }

    public Text F(float f2, float f3, IFont iFont, CharSequence charSequence, TextOptions textOptions, VertexBufferObjectManager vertexBufferObjectManager) {
        Text text = new Text(f2, f3, iFont, charSequence, textOptions, vertexBufferObjectManager);
        text.setScaleCenter(0.0f, 0.0f);
        attachChild(text);
        return text;
    }

    public Text G(float f2, float f3, IFont iFont, CharSequence charSequence, TextOptions textOptions, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        Text text = new Text(f2, f3, iFont, charSequence, textOptions, vertexBufferObjectManager);
        text.setScaleCenter(0.0f, 0.0f);
        text.setVisible(z);
        attachChild(text);
        return text;
    }

    public TiledSprite H(float f2, float f3, ITiledTextureRegion iTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, int i, boolean z) {
        TiledSprite tiledSprite = new TiledSprite(f2, f3, iTiledTextureRegion, vertexBufferObjectManager);
        tiledSprite.setCurrentTileIndex(i);
        tiledSprite.setVisible(z);
        attachChild(tiledSprite);
        return tiledSprite;
    }

    public TiledSprite I(float f2, float f3, ITiledTextureRegion iTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, int i, boolean z, float f4) {
        TiledSprite tiledSprite = new TiledSprite(f2, f3, iTiledTextureRegion, vertexBufferObjectManager);
        tiledSprite.setCurrentTileIndex(i);
        tiledSprite.setScaleCenter(0.0f, 0.0f);
        tiledSprite.setScale(f4);
        tiledSprite.setVisible(z);
        attachChild(tiledSprite);
        return tiledSprite;
    }

    public TiledSprite J(float f2, float f3, ITiledTextureRegion iTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        TiledSprite tiledSprite = new TiledSprite(f2, f3, iTiledTextureRegion, vertexBufferObjectManager);
        tiledSprite.setVisible(z);
        tiledSprite.setScaleCenter(0.0f, 0.0f);
        attachChild(tiledSprite);
        return tiledSprite;
    }

    public void blinkSprite(Sprite sprite) {
        sprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        sprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.4f, 0.25f, 1.0f), new AlphaModifier(0.4f, 1.0f, 0.25f))));
    }

    public void changeScene(ExtendedScene extendedScene) {
        changeScene(extendedScene, null);
    }

    public void checkInput(int i, Point point) {
        if (i == 0) {
            this.pressStarted = true;
            this.startTime = System.currentTimeMillis();
            checkButtonPress(point);
        } else if (i == 1) {
            this.pressStarted = false;
            this.G.setVisible(false);
        } else if (i != 2) {
        } else {
            this.pressLocation.setX(point.getX());
            this.pressLocation.setY(point.getY());
            this.G.setVisible(false);
            checkButtonPress(point);
        }
    }

    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        createScene(vertexBufferObjectManager, 0.0f);
    }

    public void fadeIn() {
        sortChildren();
        this.fade.setAlpha(1.0f);
        this.fade.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        AlphaModifier alphaModifier = new AlphaModifier(0.25f, 1.0f, 0.0f) { // from class: com.birdshel.Uciana.Scenes.ExtendedScene.3
            {
                ExtendedScene.this = this;
            }

            @Override // org.andengine.util.modifier.BaseModifier
            /* renamed from: k */
            public void c(IEntity iEntity) {
                super.c(iEntity);
                ExtendedScene extendedScene = ExtendedScene.this;
                extendedScene.unregisterEntityModifier(extendedScene.fadeIn);
                ExtendedScene.this.fade.setAlpha(0.0f);
            }
        };
        this.fadeIn = alphaModifier;
        this.fade.registerEntityModifier(alphaModifier);
    }

    public abstract void getPoolElements();

    public float getWidth() {
        return this.game.getWidth();
    }

    public boolean isClicked(Entity entity, Point point) {
        return x(entity, point, 0.0f, 0.0f);
    }

    public abstract void refresh();

    protected abstract void releasePoolElements(ExtendedScene extendedScene, Object obj);

    public void s(TiledSprite tiledSprite) {
        if (this.F.contains(tiledSprite)) {
            return;
        }
        this.F.add(tiledSprite);
    }

    public void showMessage(Message message) {
        this.H.setOverlay(message);
        setChildScene(this.H, false, false, true);
    }

    public void switched() {
        GameMusic gameMusic;
        this.game.getActivity().setLocale();
        if (Game.options.isOn(OptionID.MUSIC, 1) && (gameMusic = this.game.music) != null) {
            gameMusic.resumeMainTheme();
        }
        fadeIn();
    }

    public boolean t() {
        return this.fade.getAlpha() != 0.0f;
    }

    public void u(Sprite sprite) {
        sprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        sprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.75f, 0.5f, 1.0f), new AlphaModifier(0.75f, 1.0f, 0.5f))));
    }

    public void update() {
        if (!this.pressStarted || System.currentTimeMillis() - this.startTime <= 1000) {
            return;
        }
        this.pressStarted = false;
        if (this.I) {
            B(this.pressLocation);
        }
    }

    public void v(Text text) {
        text.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        text.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.4f, 0.25f, 1.0f), new AlphaModifier(0.4f, 1.0f, 0.25f))));
    }

    public void w(List<Entity> list, final Entity entity) {
        final ArrayList arrayList = new ArrayList(list);
        list.clear();
        this.game.getActivity().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.k
            @Override // java.lang.Runnable
            public final void run() {
                ExtendedScene.lambda$detachListFromScene$1(arrayList, entity);
            }
        });
    }

    public boolean x(Entity entity, Point point, float f2, float f3) {
        Sprite sprite = (Sprite) entity;
        if (sprite.getAlpha() == 0.4f) {
            return false;
        }
        float x = sprite.getX() - f2;
        float widthScaled = sprite.getWidthScaled() + x + f2;
        float y = sprite.getY() - f3;
        return sprite.isVisible() && ((point.getX() > x ? 1 : (point.getX() == x ? 0 : -1)) > 0 && (point.getX() > widthScaled ? 1 : (point.getX() == widthScaled ? 0 : -1)) < 0 && (point.getY() > y ? 1 : (point.getY() == y ? 0 : -1)) > 0 && (point.getY() > ((sprite.getHeightScaled() + y) + f3) ? 1 : (point.getY() == ((sprite.getHeightScaled() + y) + f3) ? 0 : -1)) < 0);
    }

    public boolean y(Text text, Point point) {
        if (text.getAlpha() == 0.4f) {
            return false;
        }
        float x = text.getX();
        float widthScaled = text.getWidthScaled() + x;
        float y = text.getY();
        return text.isVisible() && ((point.getX() > x ? 1 : (point.getX() == x ? 0 : -1)) > 0 && (point.getX() > widthScaled ? 1 : (point.getX() == widthScaled ? 0 : -1)) < 0 && (point.getY() > y ? 1 : (point.getY() == y ? 0 : -1)) > 0 && (point.getY() > (text.getHeightScaled() + y) ? 1 : (point.getY() == (text.getHeightScaled() + y) ? 0 : -1)) < 0);
    }

    public boolean z(Text text, Point point, int i, int i2) {
        if (text.getAlpha() == 0.4f) {
            return false;
        }
        float x = text.getX();
        float widthScaled = text.getWidthScaled() + x + i;
        float y = text.getY();
        return text.isVisible() && ((point.getX() > x ? 1 : (point.getX() == x ? 0 : -1)) > 0 && (point.getX() > widthScaled ? 1 : (point.getX() == widthScaled ? 0 : -1)) < 0 && (point.getY() > y ? 1 : (point.getY() == y ? 0 : -1)) > 0 && (point.getY() > ((text.getHeightScaled() + y) + ((float) i2)) ? 1 : (point.getY() == ((text.getHeightScaled() + y) + ((float) i2)) ? 0 : -1)) < 0);
    }

    public void changeScene(ExtendedScene extendedScene, Object obj) {
        fadeOut(extendedScene, obj);
    }

    public void createScene(VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        setTouchListener(f2);
        registerUpdate();
        setBackground(this.game.graphics.backgroundTexture, vertexBufferObjectManager);
        this.G = H(0.0f, 0.0f, this.game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        Sprite sprite = new Sprite(0.0f, 0.0f, this.game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        this.fade = sprite;
        sprite.setSize(getWidth(), 720.0f);
        this.fade.setZIndex(20000);
        attachChild(this.fade);
    }
}
