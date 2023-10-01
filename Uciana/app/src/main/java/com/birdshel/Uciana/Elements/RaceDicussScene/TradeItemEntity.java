package com.birdshel.Uciana.Elements.RaceDicussScene;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.TechIcon;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Players.TradeItem;
import com.birdshel.Uciana.Players.TradeItemIDs;
import com.birdshel.Uciana.Players.TradeType;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechID;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.nio.CharBuffer;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TradeItemEntity extends Entity {
    private final TiledSprite buildingsIcon;
    private final Text buildingsText;
    private final TiledSprite creditsIcon;
    private final Text creditsText;
    private final TiledSprite empire1Background;
    private final TiledSprite empire2Background;
    private final TiledSprite empireBackground;
    private final Game game;
    private final TiledSprite gameIcon;
    private final TiledSprite getInfoIcon;
    private final TiledSprite infoIcon;
    private final TiledSprite populationIcon;
    private final Text populationText;
    private final Text researchCostText;
    private final TiledSprite researchIcon;
    private final Sprite selectedBackground;
    private final AnimatedSprite star;
    private final TechIcon techIcon;
    private final Text titleText;
    private final Sprite unselectedBackground;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Elements.RaceDicussScene.TradeItemEntity$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1365a;

        static {
            int[] iArr = new int[TradeType.values().length];
            f1365a = iArr;
            try {
                iArr[TradeType.TREATY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1365a[TradeType.MAPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1365a[TradeType.TECH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1365a[TradeType.SYSTEM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1365a[TradeType.CREDITS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public TradeItemEntity(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonySeparatorTexture, vertexBufferObjectManager);
        this.unselectedBackground = sprite;
        sprite.setSize(400.0f, 75.0f);
        attachChild(sprite);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.selectedBackground = sprite2;
        sprite2.setSize(400.0f, 75.0f);
        attachChild(sprite2);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.empireBackground = tiledSprite;
        tiledSprite.setSize(10.0f, 75.0f);
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.empire1Background = tiledSprite2;
        tiledSprite2.setSize(10.0f, 75.0f);
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(420.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.empire2Background = tiledSprite3;
        tiledSprite3.setSize(10.0f, 75.0f);
        attachChild(tiledSprite3);
        TiledSprite tiledSprite4 = new TiledSprite(15.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.gameIcon = tiledSprite4;
        tiledSprite4.setSize(75.0f, 75.0f);
        attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(32.0f, 17.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.infoIcon = tiledSprite5;
        tiledSprite5.setSize(40.0f, 40.0f);
        attachChild(tiledSprite5);
        Text text = new Text(95.0f, 0.0f, game.fonts.smallInfoFont, wrap, new TextOptions(AutoWrap.WORDS, 250.0f, HorizontalAlign.LEFT), vertexBufferObjectManager);
        this.titleText = text;
        attachChild(text);
        TechIcon techIcon = new TechIcon(game, vertexBufferObjectManager, 75);
        this.techIcon = techIcon;
        techIcon.setX(15.0f);
        attachChild(techIcon);
        Font font = game.fonts.smallInfoFont;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text2 = new Text(95.0f, 48.0f, font, wrap, new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.researchCostText = text2;
        attachChild(text2);
        TiledSprite tiledSprite6 = new TiledSprite(0.0f, 42.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.researchIcon = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
        tiledSprite6.setSize(25.0f, 25.0f);
        attachChild(tiledSprite6);
        AnimatedSprite animatedSprite = new AnimatedSprite(15.0f, 0.0f, game.graphics.starsTexture, vertexBufferObjectManager);
        this.star = animatedSprite;
        animatedSprite.setSize(75.0f, 75.0f);
        attachChild(animatedSprite);
        Text text3 = new Text(95.0f, 48.0f, game.fonts.smallInfoFont, "##########", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.populationText = text3;
        attachChild(text3);
        TiledSprite tiledSprite7 = new TiledSprite(0.0f, 42.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.populationIcon = tiledSprite7;
        tiledSprite7.setCurrentTileIndex(InfoIconEnum.POPULATION.ordinal());
        tiledSprite7.setSize(25.0f, 25.0f);
        attachChild(tiledSprite7);
        Text text4 = new Text(0.0f, 48.0f, game.fonts.smallInfoFont, "##########", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.buildingsText = text4;
        attachChild(text4);
        TiledSprite tiledSprite8 = new TiledSprite(0.0f, 42.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.buildingsIcon = tiledSprite8;
        tiledSprite8.setCurrentTileIndex(GameIconEnum.BUILDINGS.ordinal());
        tiledSprite8.setSize(25.0f, 25.0f);
        attachChild(tiledSprite8);
        Text text5 = new Text(95.0f, 48.0f, game.fonts.smallInfoFont, "##########", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.creditsText = text5;
        attachChild(text5);
        TiledSprite tiledSprite9 = new TiledSprite(0.0f, 42.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.creditsIcon = tiledSprite9;
        tiledSprite9.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        tiledSprite9.setSize(25.0f, 25.0f);
        attachChild(tiledSprite9);
        TiledSprite tiledSprite10 = new TiledSprite(355.0f, 22.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.getInfoIcon = tiledSprite10;
        tiledSprite10.setCurrentTileIndex(InfoIconEnum.INFO.ordinal());
        attachChild(tiledSprite10);
    }

    private void setCredits(TradeItem tradeItem) {
        this.infoIcon.setVisible(true);
        this.infoIcon.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        this.titleText.setText(tradeItem.getName());
        this.titleText.setY(15.0f);
        if (tradeItem.getAmount() != 0) {
            this.creditsText.setVisible(true);
            this.creditsText.setText(Integer.toString(tradeItem.getAmount()));
            this.creditsIcon.setVisible(true);
            this.creditsIcon.setX(this.creditsText.getX() + this.creditsText.getWidthScaled() + 5.0f);
            return;
        }
        Text text = this.titleText;
        text.setY(37.0f - (text.getHeightScaled() / 2.0f));
    }

    private void setElementsInvisible() {
        this.empireBackground.setVisible(false);
        this.empire1Background.setVisible(false);
        this.empire2Background.setVisible(false);
        this.gameIcon.setVisible(false);
        this.infoIcon.setVisible(false);
        this.techIcon.setVisible(false);
        this.researchCostText.setVisible(false);
        this.researchIcon.setVisible(false);
        this.star.setVisible(false);
        this.populationText.setVisible(false);
        this.populationIcon.setVisible(false);
        this.buildingsText.setVisible(false);
        this.buildingsIcon.setVisible(false);
        this.creditsText.setVisible(false);
        this.creditsIcon.setVisible(false);
        this.getInfoIcon.setVisible(false);
    }

    private void setMaps(TradeItem tradeItem) {
        this.infoIcon.setVisible(true);
        this.infoIcon.setCurrentTileIndex(InfoIconEnum.SCOUT_ICON.ordinal());
        this.titleText.setText(tradeItem.getName());
        Text text = this.titleText;
        text.setY(37.0f - (text.getHeightScaled() / 2.0f));
    }

    private void setSystem(TradeItem tradeItem) {
        int parseInt = Integer.parseInt(tradeItem.getID().replace(TradeItemIDs.SYSTEM.getID(), ""));
        StarSystem starSystem = this.game.galaxy.getStarSystem(parseInt);
        int ordinal = (starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4);
        int i = 0;
        this.star.setVisible(true);
        this.star.animate(new long[]{125, 125, 125, Functions.random.nextInt(1000) + 2000}, new int[]{ordinal, ordinal + 1, ordinal + 2, ordinal + 3}, true);
        this.titleText.setText(tradeItem.getName());
        Text text = this.titleText;
        text.setY(37.0f - (text.getHeightScaled() / 2.0f));
        int i2 = 0;
        for (Colony colony : this.game.empires.get(tradeItem.getEmpire1ID()).getColonies()) {
            if (colony.getSystemID() == parseInt) {
                i += colony.getPopulation();
                i2 += colony.getBuildings().size();
            }
        }
        this.populationText.setVisible(true);
        this.populationText.setText(Integer.toString(i));
        this.populationIcon.setVisible(true);
        this.populationIcon.setX(this.populationText.getX() + this.populationText.getWidthScaled() + 5.0f);
        this.buildingsText.setVisible(true);
        this.buildingsText.setText(Integer.toString(i2));
        this.buildingsText.setX(this.populationIcon.getX() + 50.0f);
        this.buildingsIcon.setVisible(true);
        this.buildingsIcon.setX(this.buildingsText.getX() + this.buildingsText.getWidthScaled() + 5.0f);
    }

    private void setTech(int i, TradeItem tradeItem) {
        Tech tech = this.game.getCurrentEmpire().getTech().getTech(TechID.values()[Integer.parseInt(tradeItem.getID().replace(TradeItemIDs.TECH.getID(), ""))]);
        this.techIcon.setVisible(true);
        this.techIcon.set(i, tech);
        this.titleText.setText(tradeItem.getName());
        Text text = this.titleText;
        text.setY(20.0f - (text.getHeightScaled() / 2.0f));
        this.researchCostText.setVisible(true);
        this.researchCostText.setText(Integer.toString(tech.getResearchCost()));
        this.researchIcon.setVisible(true);
        this.researchIcon.setX(this.researchCostText.getX() + this.researchCostText.getWidthScaled() + 5.0f);
    }

    private void setTreaty(TradeItem tradeItem) {
        Treaty treaty = Treaty.getTreaty(tradeItem.getID());
        this.gameIcon.setVisible(true);
        this.gameIcon.setCurrentTileIndex(treaty.getIconIndex());
        this.titleText.setText(tradeItem.getName());
        Text text = this.titleText;
        text.setY(37.0f - (text.getHeightScaled() / 2.0f));
    }

    public boolean isSelected() {
        return this.selectedBackground.isVisible();
    }

    public void selected() {
        this.unselectedBackground.setVisible(false);
        this.selectedBackground.setVisible(true);
    }

    public void set(int i, TradeItem tradeItem, boolean z, boolean z2) {
        setElementsInvisible();
        this.unselectedBackground.setWidth(400.0f);
        this.selectedBackground.setWidth(400.0f);
        if (z) {
            this.empireBackground.setX(390.0f);
        } else {
            this.empireBackground.setX(0.0f);
        }
        if (z2 && tradeItem.doesRequireBoth()) {
            this.unselectedBackground.setWidth(430.0f);
            this.selectedBackground.setWidth(430.0f);
            this.empire1Background.setVisible(true);
            this.empire2Background.setVisible(true);
            this.empire1Background.setCurrentTileIndex(tradeItem.getEmpire1ID());
            this.empire2Background.setCurrentTileIndex(tradeItem.getEmpire2ID());
        } else {
            this.empireBackground.setVisible(true);
            this.empireBackground.setCurrentTileIndex(tradeItem.getEmpire1ID());
        }
        int i2 = AnonymousClass1.f1365a[tradeItem.getType().ordinal()];
        if (i2 == 1) {
            setTreaty(tradeItem);
        } else if (i2 == 2) {
            setMaps(tradeItem);
        } else if (i2 == 3) {
            setTech(i, tradeItem);
        } else if (i2 == 4) {
            setSystem(tradeItem);
        } else if (i2 == 5) {
            setCredits(tradeItem);
        }
        if (!z2) {
            this.getInfoIcon.setVisible(true);
        }
        unselected();
    }

    public void unselected() {
        this.unselectedBackground.setVisible(true);
        this.selectedBackground.setVisible(false);
    }
}
