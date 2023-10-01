package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.TechIcon;
import com.birdshel.Uciana.Elements.TechListElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.TechInfoMessage;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.Messages.Tutorials.ResearchViewTutorial;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.Options;
import com.birdshel.Uciana.Resources.TutorialID;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechCategory;
import com.birdshel.Uciana.Technology.TechID;
import com.birdshel.Uciana.Technology.TechProgressionType;
import com.birdshel.Uciana.Technology.TechType;
import com.birdshel.Uciana.Technology.Technology;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TechScene extends ExtendedScene {
    private TiledSprite addedResearchBar;
    private TiledSprite autoButton;
    private TiledSprite autoButtonHighlight;
    private Text chemistryCount;
    private TechIcon currentResearchIcon;
    private TiledSprite currentTechBackground;
    private TiledSprite empireBackground;
    private TiledSprite empireBanner;
    private TiledSprite emptyResearchBar;
    private Text energyCount;
    private Text engineeringCount;
    private TiledSprite finishedResearchBar;
    private TiledSprite galaxyButton;
    private float lastY;
    private Text physicsCount;
    private float pressedY;
    private Text researchAndTotalResearch;
    private TiledSprite researchIcon;
    private Text researchPerTurn;
    private Text researchTurnsLeft;
    private Sprite selectPress;
    private Text techName;
    private Technology technology;
    private TiledSprite totalResearchIcon;
    private final Map<TechCategory, TechID[]> techsInCategory = new HashMap();
    private final Map<TechCategory, Scene> techList = new HashMap();
    private final Map<TechCategory, ScrollBarControl> scrollBar = new HashMap();
    private boolean isScroll = false;
    private final Map<TechCategory, TechListElement[]> techListElements = new HashMap();
    private final Map<TechCategory, Integer> techListIndexes = new HashMap();
    private final int ITEM_SIZE = 80;
    private int listWidth = 320;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.TechScene$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1479a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[TechCategory.values().length];
            b = iArr;
            try {
                iArr[TechCategory.ENGINEERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[TechCategory.PHYSICS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[TechCategory.CHEMISTRY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[TechCategory.ENERGY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[TechProgressionType.values().length];
            f1479a = iArr2;
            try {
                iArr2[TechProgressionType.ONE_TECH_REQUIRED_PER_LEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1479a[TechProgressionType.ALL_TECH_REQUIRED_PER_LEVEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1479a[TechProgressionType.ALLOW_ONLY_ONE_TECH_PER_LEVEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1479a[TechProgressionType.ALLOW_ONLY_ONE_RANDOM_TECH_PER_LEVEL.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public TechScene(Game game) {
        this.B = game;
    }

    private void autoPressed() {
        Empire currentEmpire = this.B.getCurrentEmpire();
        TiledSprite tiledSprite = this.autoButtonHighlight;
        tiledSprite.setVisible(!tiledSprite.isVisible());
        currentEmpire.setEmpireSetting("auto_research", this.autoButtonHighlight.isVisible() ? 1 : 0);
        if (currentEmpire.getCurrentTech().getID() == TechID.NONE) {
            currentEmpire.getResearchAI().manage();
            refresh();
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void checkActionDown(Point point) {
        this.isScroll = false;
        if (point.getY() > 166.0f) {
            this.pressedY = point.getY();
            this.lastY = point.getY();
            checkToShowPress(point);
        }
    }

    private void checkActionMove(Point point) {
        this.selectPress.setVisible(false);
        if (point.getY() > 166.0f) {
            if (point.getX() <= this.listWidth) {
                checkTechCategoryActionMove(TechCategory.ENGINEERING, point);
            } else if (point.getX() > this.listWidth && point.getX() <= this.listWidth * 2) {
                checkTechCategoryActionMove(TechCategory.PHYSICS, point);
            } else if (point.getX() > this.listWidth * 2 && point.getX() <= this.listWidth * 3) {
                checkTechCategoryActionMove(TechCategory.CHEMISTRY, point);
            } else {
                checkTechCategoryActionMove(TechCategory.ENERGY, point);
            }
        }
    }

    private void checkActionUp(Point point) {
        this.selectPress.setVisible(false);
        if (this.isScroll) {
            return;
        }
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.autoButton, point)) {
            autoPressed();
        } else if (point.getY() > 166.0f) {
            if (point.getX() <= this.listWidth) {
                techPressed(TechCategory.ENGINEERING, point.getX(), point.getY());
            } else if (point.getX() > this.listWidth && point.getX() <= this.listWidth * 2) {
                techPressed(TechCategory.PHYSICS, point.getX() - this.listWidth, point.getY());
            } else if (point.getX() > this.listWidth * 2 && point.getX() <= this.listWidth * 3) {
                techPressed(TechCategory.CHEMISTRY, point.getX() - (this.listWidth * 2), point.getY());
            } else {
                techPressed(TechCategory.ENERGY, point.getX() - (this.listWidth * 3), point.getY());
            }
        }
    }

    private void checkTechCategoryActionMove(TechCategory techCategory, Point point) {
        int y = ((int) (point.getY() - getTechList(techCategory).getY())) / 80;
        int length = getAllTechsInCategory(techCategory).length;
        if (length > y) {
            int i = length * 80;
            if (i > 559) {
                if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                    this.isScroll = true;
                }
                float y2 = getTechList(techCategory).getY() - (this.lastY - point.getY());
                if (y2 > 166.0f) {
                    y2 = 166.0f;
                }
                float f2 = (i - 720) * (-1);
                if (y2 < f2) {
                    y2 = f2;
                }
                getTechList(techCategory).setY(y2);
                this.lastY = point.getY();
                getScrollBar(techCategory).update(getTechList(techCategory).getY());
                int abs = Math.abs((int) ((y2 - 166.0f) / 80.0f));
                if (abs != getTechListIndexes(techCategory)) {
                    setTechList(techCategory, abs);
                }
            }
            if (this.isScroll) {
                return;
            }
            checkToShowPress(point);
        }
    }

    private void checkTechPress(TechCategory techCategory, int i, int i2) {
        if (getAllTechsInCategory(techCategory).length > i && this.technology.getTech(getTechInCategoryByIndex(techCategory, i)).getType() != TechType.NONE) {
            this.selectPress.setPosition(i2, getTechList(techCategory).getY() + (i * 80));
            this.selectPress.setVisible(true);
        }
    }

    private void checkToShowPress(Point point) {
        if (point.getX() <= this.listWidth) {
            float y = point.getY();
            TechCategory techCategory = TechCategory.ENGINEERING;
            int y2 = ((int) (y - getTechList(techCategory).getY())) / 80;
            if (point.getX() < 70.0f) {
                this.selectPress.setWidth(70.0f);
                checkTechPress(techCategory, y2, 0);
                return;
            }
            this.selectPress.setWidth(this.listWidth - 90);
            checkTechPress(techCategory, y2, 70);
        } else if (point.getX() > this.listWidth && point.getX() <= this.listWidth * 2) {
            float y3 = point.getY();
            TechCategory techCategory2 = TechCategory.PHYSICS;
            int y4 = ((int) (y3 - getTechList(techCategory2).getY())) / 80;
            float x = point.getX();
            int i = this.listWidth;
            if (x < i + 70) {
                this.selectPress.setWidth(70.0f);
                checkTechPress(techCategory2, y4, this.listWidth);
                return;
            }
            this.selectPress.setWidth(i - 90);
            checkTechPress(techCategory2, y4, this.listWidth + 70);
        } else if (point.getX() > this.listWidth * 2 && point.getX() <= this.listWidth * 3) {
            float y5 = point.getY();
            TechCategory techCategory3 = TechCategory.CHEMISTRY;
            int y6 = ((int) (y5 - getTechList(techCategory3).getY())) / 80;
            float x2 = point.getX();
            int i2 = this.listWidth;
            if (x2 < (i2 * 2) + 70) {
                this.selectPress.setWidth(70.0f);
                checkTechPress(techCategory3, y6, this.listWidth * 2);
                return;
            }
            this.selectPress.setWidth(i2 - 90);
            checkTechPress(techCategory3, y6, (this.listWidth * 2) + 70);
        } else {
            float y7 = point.getY();
            TechCategory techCategory4 = TechCategory.ENERGY;
            int y8 = ((int) (y7 - getTechList(techCategory4).getY())) / 80;
            float x3 = point.getX();
            int i3 = this.listWidth;
            if (x3 < (i3 * 3) + 70) {
                this.selectPress.setWidth(70.0f);
                checkTechPress(techCategory4, y8, this.listWidth * 3);
                return;
            }
            this.selectPress.setWidth(i3 - 90);
            checkTechPress(techCategory4, y8, (this.listWidth * 3) + 70);
        }
    }

    private void galaxyButtonPressed() {
        setTechListYs();
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private TechID[] getAllTechsInCategory(TechCategory techCategory) {
        return this.techsInCategory.get(techCategory);
    }

    private ScrollBarControl getScrollBar(TechCategory techCategory) {
        return this.scrollBar.get(techCategory);
    }

    private TechID getTechInCategoryByIndex(TechCategory techCategory, int i) {
        return getAllTechsInCategory(techCategory)[i];
    }

    private Scene getTechList(TechCategory techCategory) {
        return this.techList.get(techCategory);
    }

    private TechListElement getTechListElement(TechCategory techCategory, int i) {
        return getTechListElements(techCategory)[i];
    }

    private TechListElement[] getTechListElements(TechCategory techCategory) {
        return this.techListElements.get(techCategory);
    }

    private int getTechListIndexes(TechCategory techCategory) {
        return this.techListIndexes.get(techCategory).intValue();
    }

    private void setTech(TechCategory techCategory, int i) {
        this.B.getCurrentEmpire().setTech(getTechInCategoryByIndex(techCategory, i));
        updateCurrentTech();
        setTechListForEmpire();
    }

    private void setTechCount(Text text, TechCategory techCategory, int i) {
        Empire currentEmpire = this.B.getCurrentEmpire();
        int countOfUnfinishedTechsFromCategory = currentEmpire.getTech().getCountOfUnfinishedTechsFromCategory(techCategory);
        int countOfTechsFromCategory = currentEmpire.getTech().getCountOfTechsFromCategory(techCategory);
        text.setText(countOfUnfinishedTechsFromCategory + " / " + countOfTechsFromCategory);
        text.setX(((float) i) - (text.getWidthScaled() / 2.0f));
    }

    private void setTechElementBackgrounds(Tech tech, TechCategory techCategory, int i) {
        if (tech.getType() == TechType.NONE) {
            if (tech.getID() == TechID.BLANK) {
                getTechListElement(techCategory, i).setEmptyTechElement();
            } else {
                getTechListElement(techCategory, i).setTechHeaderElement();
            }
        } else if (tech.getID() == this.B.getCurrentEmpire().getCurrentTech().getID()) {
            getTechListElement(techCategory, i).setCurrentTechElement();
        } else if (tech.isDone()) {
            getTechListElement(techCategory, i).setTechDoneElement();
        } else if (!tech.canBeResearched()) {
            getTechListElement(techCategory, i).setUnavailableTechElement();
        } else {
            getTechListElement(techCategory, i).setResearchableTechElement();
        }
    }

    private void setTechElementPercent(Tech tech, TechCategory techCategory, int i) {
        Text text = getTechListElement(techCategory, i).techPercentage;
        if (tech.isNormalTech() && tech.getPercentDone() != 0 && !tech.isDone()) {
            text.setVisible(true);
            text.setText(tech.getPercentDone() + "%");
            text.setX(((float) (this.listWidth + (-25))) - text.getWidthScaled());
            return;
        }
        text.setVisible(false);
    }

    private void setTechList(TechCategory techCategory, int i) {
        this.techListIndexes.put(techCategory, Integer.valueOf(i));
        for (int i2 = 0; i2 < getTechListElements(techCategory).length; i2++) {
            getTechListElement(techCategory, i2).setVisible(false);
            int i3 = i + i2;
            if (i3 < getAllTechsInCategory(techCategory).length) {
                Tech tech = this.technology.getTech(getTechInCategoryByIndex(techCategory, i3));
                getTechListElement(techCategory, i2).setVisible(true);
                getTechListElement(techCategory, i2).set(techCategory, this.B.getCurrentPlayer(), tech);
                getTechListElement(techCategory, i2).setY(i3 * 80);
                setTechElementBackgrounds(tech, techCategory, i2);
                setTechElementPercent(tech, techCategory, i2);
            }
        }
    }

    private void setTechListForEmpire() {
        TechCategory[] techCategoryArr = {TechCategory.ENGINEERING, TechCategory.PHYSICS, TechCategory.CHEMISTRY, TechCategory.ENERGY};
        for (int i = 0; i < 4; i++) {
            TechCategory techCategory = techCategoryArr[i];
            setTechList(techCategory, getTechListIndexes(techCategory));
        }
    }

    private void setTechListPosition(TechCategory techCategory) {
        float techListY = this.B.getCurrentEmpire().getTechListY(techCategory);
        getTechList(techCategory).setY(techListY);
        getScrollBar(techCategory).update(techListY, getAllTechsInCategory(techCategory).length);
    }

    private void setTechListYs() {
        Empire currentEmpire = this.B.getCurrentEmpire();
        TechCategory techCategory = TechCategory.ENGINEERING;
        currentEmpire.setTechListY(techCategory, getTechList(techCategory).getY());
        Empire currentEmpire2 = this.B.getCurrentEmpire();
        TechCategory techCategory2 = TechCategory.PHYSICS;
        currentEmpire2.setTechListY(techCategory2, getTechList(techCategory2).getY());
        Empire currentEmpire3 = this.B.getCurrentEmpire();
        TechCategory techCategory3 = TechCategory.CHEMISTRY;
        currentEmpire3.setTechListY(techCategory3, getTechList(techCategory3).getY());
        Empire currentEmpire4 = this.B.getCurrentEmpire();
        TechCategory techCategory4 = TechCategory.ENERGY;
        currentEmpire4.setTechListY(techCategory4, getTechList(techCategory4).getY());
    }

    private void techPressed(TechCategory techCategory, float f2, float f3) {
        String string;
        String string2;
        int y = ((int) (f3 - getTechList(techCategory).getY())) / 80;
        if (getAllTechsInCategory(techCategory).length <= y) {
            return;
        }
        TechID techInCategoryByIndex = getTechInCategoryByIndex(techCategory, y);
        if (this.technology.getTech(techInCategoryByIndex).getType() == TechType.NONE) {
            return;
        }
        Tech tech = this.technology.getTech(techInCategoryByIndex);
        if (f2 >= 70.0f && !tech.isDone() && tech.getID() != this.B.getCurrentEmpire().getCurrentTech().getID()) {
            if (!tech.canBeResearched()) {
                int currentTechLevel = this.technology.getCurrentTechLevel(techCategory);
                int i = AnonymousClass1.f1479a[this.B.gameSettings.techProgressionType().ordinal()];
                if (i == 1) {
                    showMessage(new TextMessage(this.B.getActivity().getString(R.string.tech_level_needed, new Object[]{tech.getCategory().getName(), Integer.valueOf(tech.getLevel() - 1)})));
                } else if (i == 2) {
                    showMessage(new TextMessage(this.B.getActivity().getString(R.string.tech_level_needed_all, new Object[]{tech.getCategory().getName(), Integer.valueOf(tech.getLevel() - 1)})));
                } else if (i == 3) {
                    if (tech.getLevel() <= currentTechLevel) {
                        string = this.B.getActivity().getString(R.string.tech_level_only_one);
                    } else {
                        string = this.B.getActivity().getString(R.string.tech_level_needed, new Object[]{tech.getCategory().getName(), Integer.valueOf(tech.getLevel() - 1)});
                    }
                    showMessage(new TextMessage(string));
                } else if (i == 4) {
                    if (tech.getLevel() <= currentTechLevel + 1) {
                        string2 = this.B.getActivity().getString(R.string.tech_level_random);
                    } else {
                        string2 = this.B.getActivity().getString(R.string.tech_level_needed_random, new Object[]{tech.getCategory().getName(), Integer.valueOf(tech.getLevel() - 1), Integer.valueOf(tech.getLevel())});
                    }
                    showMessage(new TextMessage(string2));
                }
            } else {
                setTech(techCategory, y);
            }
        } else {
            showMessage(new TechInfoMessage(techInCategoryByIndex));
        }
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void updateCurrentTech() {
        Game game = this.B;
        Empire empire = game.empires.get(game.getCurrentPlayer());
        Tech currentTech = empire.getCurrentTech();
        this.currentResearchIcon.set(currentTech);
        this.researchPerTurn.setText("+" + empire.getResearchPoints());
        this.researchIcon.setX(this.researchPerTurn.getX() + this.researchPerTurn.getWidth() + 5.0f);
        this.techName.setText(currentTech.getName());
        this.researchTurnsLeft.setText(empire.getTechTurnsLeftString());
        float width = this.techName.getWidth();
        float width2 = this.researchTurnsLeft.getWidth();
        this.techName.setX(585.0f - width);
        this.researchTurnsLeft.setX(585.0f - width2);
        this.totalResearchIcon.setVisible(true);
        this.researchAndTotalResearch.setVisible(true);
        int researchCost = currentTech.getResearchCost();
        this.researchAndTotalResearch.setText(currentTech.getCurrentResearch() + " / " + researchCost);
        this.totalResearchIcon.setX(this.researchAndTotalResearch.getX() + this.researchAndTotalResearch.getWidth() + 5.0f);
        if (currentTech.getType() == TechType.NONE) {
            this.currentTechBackground.setVisible(false);
            this.emptyResearchBar.setCurrentTileIndex(3);
            this.finishedResearchBar.setVisible(false);
            this.addedResearchBar.setVisible(false);
            return;
        }
        this.currentTechBackground.setVisible(true);
        int i = AnonymousClass1.b[currentTech.getCategory().ordinal()];
        if (i == 1) {
            this.currentTechBackground.setCurrentTileIndex(2);
        } else if (i == 2) {
            this.currentTechBackground.setCurrentTileIndex(0);
        } else if (i == 3) {
            this.currentTechBackground.setCurrentTileIndex(1);
        } else if (i == 4) {
            this.currentTechBackground.setCurrentTileIndex(3);
        }
        this.emptyResearchBar.setCurrentTileIndex(2);
        this.finishedResearchBar.setVisible(true);
        this.addedResearchBar.setVisible(true);
        int percentDone = currentTech.getPercentDone();
        float f2 = percentDone * 5;
        this.finishedResearchBar.setWidth(f2);
        this.addedResearchBar.setX(this.emptyResearchBar.getX() + f2);
        int researchPoints = (int) ((empire.getResearchPoints() / currentTech.getResearchCost()) * 100.0f);
        if (percentDone + researchPoints > 100) {
            researchPoints = 100 - percentDone;
        }
        this.addedResearchBar.setWidth(researchPoints * 5);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void K(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        setTechListYs();
        changeScene(this.B.galaxyScene);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            checkActionUp(point);
        } else if (i != 2) {
        } else {
            checkActionMove(point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        if (getWidth() == 1480.0f) {
            this.listWidth = 370;
        }
        E(0.0f, 0.0f, this.B.graphics.labBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 720.0f);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, true);
        E.setSize(getWidth(), 720.0f);
        E.setAlpha(0.5f);
        TechCategory[] techCategoryArr = {TechCategory.ENGINEERING, TechCategory.PHYSICS, TechCategory.CHEMISTRY, TechCategory.ENERGY};
        int i = 0;
        int i2 = 0;
        for (int i3 = 4; i < i3; i3 = 4) {
            TechCategory techCategory = techCategoryArr[i];
            this.techList.put(techCategory, new Scene());
            getTechList(techCategory).setPosition(this.listWidth * i2, 166.0f);
            getTechList(techCategory).setBackgroundEnabled(false);
            attachChild(getTechList(techCategory));
            Map<TechCategory, ScrollBarControl> map = this.scrollBar;
            int i4 = this.listWidth;
            map.put(techCategory, new ScrollBarControl(((i2 * i4) + i4) - 15, 161.0f, 80, 559.0f, this.B, vertexBufferObjectManager));
            attachChild(getScrollBar(techCategory));
            TechListElement[] techListElementArr = new TechListElement[8];
            for (int i5 = 0; i5 < 8; i5++) {
                techListElementArr[i5] = new TechListElement(this.B, vertexBufferObjectManager, this.listWidth);
                getTechList(techCategory).attachChild(techListElementArr[i5]);
            }
            this.techListElements.put(techCategory, techListElementArr);
            i2++;
            i++;
        }
        Sprite E2 = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = E2;
        E2.setSize(300.0f, 75.0f);
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        E(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        TiledSprite J = J(3.0f, 3.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireBackground = J;
        J.setSize(80.0f, 80.0f);
        this.empireBackground.setAlpha(0.75f);
        TiledSprite J2 = J(3.0f, 3.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.empireBanner = J2;
        J2.setSize(80.0f, 80.0f);
        TiledSprite J3 = J((getWidth() / 2.0f) - 300.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.currentTechBackground = J3;
        J3.setSize(600.0f, 80.0f);
        this.currentTechBackground.setAlpha(0.3f);
        TechIcon techIcon = new TechIcon(this.B, vertexBufferObjectManager, 75);
        this.currentResearchIcon = techIcon;
        techIcon.setPosition(0.0f, 2.0f);
        this.currentTechBackground.attachChild(this.currentResearchIcon);
        Text text = new Text(80.0f, 10.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager);
        this.researchPerTurn = text;
        this.currentTechBackground.attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 2.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.researchIcon = tiledSprite;
        InfoIconEnum infoIconEnum = InfoIconEnum.SCIENCE;
        tiledSprite.setCurrentTileIndex(infoIconEnum.ordinal());
        this.currentTechBackground.attachChild(this.researchIcon);
        Text text2 = new Text(80.0f, 52.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager);
        this.researchAndTotalResearch = text2;
        this.currentTechBackground.attachChild(text2);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 47.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.totalResearchIcon = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(infoIconEnum.ordinal());
        this.currentTechBackground.attachChild(this.totalResearchIcon);
        Text text3 = new Text(0.0f, 10.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager);
        this.techName = text3;
        this.currentTechBackground.attachChild(text3);
        Text text4 = new Text(0.0f, 52.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager);
        this.researchTurnsLeft = text4;
        this.currentTechBackground.attachChild(text4);
        TiledSprite tiledSprite3 = new TiledSprite(80.0f, 38.0f, this.B.graphics.productionPercentBarTexture, vertexBufferObjectManager);
        this.emptyResearchBar = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(2);
        this.emptyResearchBar.setWidth(500.0f);
        this.currentTechBackground.attachChild(this.emptyResearchBar);
        TiledSprite tiledSprite4 = new TiledSprite(80.0f, 38.0f, this.B.graphics.productionPercentBarTexture, vertexBufferObjectManager);
        this.finishedResearchBar = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(0);
        this.currentTechBackground.attachChild(this.finishedResearchBar);
        TiledSprite tiledSprite5 = new TiledSprite(0.0f, 38.0f, this.B.graphics.productionPercentBarTexture, vertexBufferObjectManager);
        this.addedResearchBar = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(1);
        this.currentTechBackground.attachChild(this.addedResearchBar);
        TiledTextureRegion tiledTextureRegion = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.PRESSED;
        this.G = H(0.0f, 0.0f, tiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H;
        s(H);
        TiledSprite H2 = H(100.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        this.autoButtonHighlight = H2;
        H2.setAlpha(0.4f);
        this.autoButtonHighlight.setIgnoreUpdate(true);
        TiledSprite H3 = H(100.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.BLANK.ordinal(), true);
        this.autoButton = H3;
        H3.setIgnoreUpdate(true);
        H(140.0f, 15.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true).setSize(40.0f, 40.0f);
        Game game = this.B;
        Text F = F(0.0f, 57.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.production_auto), this.E, vertexBufferObjectManager);
        F.setIgnoreUpdate(true);
        F.setX(160.0f - (F.getWidthScaled() / 2.0f));
        s(this.autoButton);
        E(0.0f, 86.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 75.0f);
        TiledSprite H4 = H(0.0f, 86.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 2, true);
        H4.setSize(this.listWidth, 75.0f);
        H4.setAlpha(0.4f);
        H(0.0f, 86.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.BUILDINGS.ordinal(), true).setSize(75.0f, 75.0f);
        Game game2 = this.B;
        Text F2 = F(0.0f, 100.0f, game2.fonts.smallInfoFont, game2.getActivity().getString(R.string.tech_category_engineering), this.E, vertexBufferObjectManager);
        F2.setX((this.listWidth / 2.0f) - (F2.getWidthScaled() / 2.0f));
        this.engineeringCount = F(0.0f, 126.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        TiledSprite H5 = H(this.listWidth, 86.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 0, true);
        H5.setSize(this.listWidth, 75.0f);
        H5.setAlpha(0.4f);
        H(this.listWidth, 86.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.PHYSICS.ordinal(), true).setSize(75.0f, 75.0f);
        Game game3 = this.B;
        Text F3 = F(0.0f, 100.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.tech_category_physics), this.E, vertexBufferObjectManager);
        int i6 = this.listWidth;
        F3.setX((i6 + (i6 / 2.0f)) - (F3.getWidthScaled() / 2.0f));
        this.physicsCount = F(0.0f, 126.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        TiledSprite H6 = H(this.listWidth * 2, 86.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 1, true);
        H6.setSize(this.listWidth, 75.0f);
        H6.setAlpha(0.4f);
        H(this.listWidth * 2, 86.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.CHEMISTRY.ordinal(), true).setSize(75.0f, 75.0f);
        Game game4 = this.B;
        Text F4 = F(0.0f, 100.0f, game4.fonts.smallInfoFont, game4.getActivity().getString(R.string.tech_category_chemistry), this.E, vertexBufferObjectManager);
        int i7 = this.listWidth;
        F4.setX(((i7 * 2) + (i7 / 2.0f)) - (F4.getWidthScaled() / 2.0f));
        this.chemistryCount = F(0.0f, 126.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        TiledSprite H7 = H(this.listWidth * 3, 86.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 3, true);
        H7.setSize(this.listWidth, 75.0f);
        H7.setAlpha(0.4f);
        H(this.listWidth * 3, 86.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.BOTTOM_WORMHOLE_LAYER.ordinal(), true).setSize(75.0f, 75.0f);
        Game game5 = this.B;
        Text F5 = F(0.0f, 100.0f, game5.fonts.smallInfoFont, game5.getActivity().getString(R.string.tech_category_energy), this.E, vertexBufferObjectManager);
        int i8 = this.listWidth;
        F5.setX(((i8 * 3) + (i8 / 2.0f)) - (F5.getWidthScaled() / 2.0f));
        this.energyCount = F(0.0f, 126.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        Map<TechCategory, TechID[]> map2 = this.techsInCategory;
        TechCategory techCategory2 = TechCategory.ENGINEERING;
        map2.put(techCategory2, techCategory2.getTechLevelList());
        Map<TechCategory, TechID[]> map3 = this.techsInCategory;
        TechCategory techCategory3 = TechCategory.PHYSICS;
        map3.put(techCategory3, techCategory3.getTechLevelList());
        Map<TechCategory, TechID[]> map4 = this.techsInCategory;
        TechCategory techCategory4 = TechCategory.CHEMISTRY;
        map4.put(techCategory4, techCategory4.getTechLevelList());
        Map<TechCategory, TechID[]> map5 = this.techsInCategory;
        TechCategory techCategory5 = TechCategory.ENERGY;
        map5.put(techCategory5, techCategory5.getTechLevelList());
        this.techListIndexes.put(techCategory2, 0);
        this.techListIndexes.put(techCategory3, 0);
        this.techListIndexes.put(techCategory4, 0);
        this.techListIndexes.put(techCategory5, 0);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        updateCurrentTech();
        setTechListForEmpire();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(ExtendedScene extendedScene, Object obj) {
        extendedScene.getPoolElements();
        K(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    public void resetLists() {
        TechCategory[] techCategoryArr = {TechCategory.ENGINEERING, TechCategory.PHYSICS, TechCategory.CHEMISTRY, TechCategory.ENERGY};
        for (int i = 0; i < 4; i++) {
            TechCategory techCategory = techCategoryArr[i];
            this.techListIndexes.put(techCategory, 0);
            getTechList(techCategory).setY(0.0f);
            this.lastY = 0.0f;
            this.pressedY = 0.0f;
        }
    }

    public void set() {
        this.B.getActivity().setLocale();
        this.empireBackground.setCurrentTileIndex(this.B.getCurrentPlayer());
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.B.getCurrentPlayer()));
        this.technology = this.B.getCurrentEmpire().getTech();
        Text text = this.engineeringCount;
        TechCategory techCategory = TechCategory.ENGINEERING;
        setTechCount(text, techCategory, this.listWidth / 2);
        Text text2 = this.physicsCount;
        TechCategory techCategory2 = TechCategory.PHYSICS;
        int i = this.listWidth;
        setTechCount(text2, techCategory2, i + (i / 2));
        Text text3 = this.chemistryCount;
        TechCategory techCategory3 = TechCategory.CHEMISTRY;
        int i2 = this.listWidth;
        setTechCount(text3, techCategory3, (i2 * 2) + (i2 / 2));
        Text text4 = this.energyCount;
        TechCategory techCategory4 = TechCategory.ENERGY;
        int i3 = this.listWidth;
        setTechCount(text4, techCategory4, (i3 * 3) + (i3 / 2));
        setTechList(techCategory, getTechListIndexes(techCategory));
        setTechList(techCategory2, getTechListIndexes(techCategory2));
        setTechList(techCategory3, getTechListIndexes(techCategory3));
        setTechList(techCategory4, getTechListIndexes(techCategory4));
        setTechListPosition(techCategory);
        setTechListPosition(techCategory2);
        setTechListPosition(techCategory3);
        setTechListPosition(techCategory4);
        updateCurrentTech();
        Options options = Game.options;
        TutorialID tutorialID = TutorialID.RESEARCH;
        if (options.shouldTutorialBeShown(tutorialID)) {
            showMessage(new ResearchViewTutorial());
            Game.options.markSeen(tutorialID);
        }
        this.autoButtonHighlight.setVisible(this.B.getCurrentEmpire().getEmpireSetting("auto_research") == 1);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
