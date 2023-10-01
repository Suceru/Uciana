package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.Battle.Invasion;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.PlanetExplorationMessage;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Technology.TechID;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class InvasionOverlay extends ExtendedChildScene {
    private final TiledSprite attackerBackground;
    private final Sprite attackerIcon;
    private final TiledSprite attackerStrengthIcon;
    private final Text attackerStrengthText;
    private final Stack<Entity> attackingInfantry;
    private final Text attackingInfantryCount;
    private final Sprite blackenedBackground;
    private final VertexBufferObjectManager bufferManager;
    private final TiledSprite colonyBackground;
    private final Text colonyName;
    private final TiledSprite defenderBackground;
    private final Sprite defenderIcon;
    private final TiledSprite defenderStrengthIcon;
    private final Text defenderStrengthText;
    private final Stack<Entity> defendingInfantry;
    private final Text defendingInfantryCount;
    private final TiledSprite empireBanner;
    private final List<Entity> explosionList;
    private final TiledSprite finishButton;
    private Invasion invasion;
    private final Sprite messageBackground;
    private final MessageOverlay messageOverlay;
    private final Text messageText;
    private boolean startInvasion;
    private long startTime;
    private final Sprite surface;

    public InvasionOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, true);
        this.attackingInfantry = new Stack<>();
        this.defendingInfantry = new Stack<>();
        this.explosionList = new ArrayList();
        this.bufferManager = vertexBufferObjectManager;
        Sprite t = t(0.0f, 0.0f, game.graphics.planetSurfaceTexture, vertexBufferObjectManager, true);
        this.surface = t;
        t.setSize(2480.0f, 720.0f);
        Sprite t2 = t(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, false);
        t2.setSize(getWidth(), 720.0f);
        t2.setAlpha(0.7f);
        t(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        TiledSprite x = x(0.0f, 0.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.colonyBackground = x;
        x.setAlpha(0.6f);
        x.setSize(getWidth(), 86.0f);
        this.empireBanner = x(0.0f, -7.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.colonyName = u(120.0f, 20.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        TiledSprite x2 = x(0.0f, 645.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.attackerBackground = x2;
        x2.setSize(getWidth() / 2.0f, 75.0f);
        TiledSprite x3 = x(getWidth() / 2.0f, 645.0f, game.graphics.empireColors, vertexBufferObjectManager, true);
        this.defenderBackground = x3;
        x3.setSize(getWidth() / 2.0f, 75.0f);
        TiledSprite x4 = x(300.0f, 645.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.attackerIcon = x4;
        x4.setSize(75.0f, 75.0f);
        TiledSprite x5 = x((getWidth() / 2.0f) + 260.0f, 645.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.defenderIcon = x5;
        x5.setSize(75.0f, 75.0f);
        this.attackingInfantryCount = u(400.0f, 670.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.defendingInfantryCount = u((getWidth() / 2.0f) + 360.0f, 670.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.attackerStrengthText = u(50.0f, 670.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.attackerStrengthIcon = w(0.0f, 670.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.ATTACK.ordinal(), true);
        this.defenderStrengthText = u(0.0f, 670.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager);
        this.defenderStrengthIcon = w((getWidth() / 2.0f) + 560.0f, 670.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.DEFENSE.ordinal(), true);
        this.finishButton = w((getWidth() / 2.0f) - 60.0f, 640.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.OK.ordinal(), true);
        Sprite t3 = t(0.0f, 0.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager, false);
        this.blackenedBackground = t3;
        t3.setSize(getWidth(), 720.0f);
        t3.setAlpha(0.5f);
        t3.setZIndex(11);
        Sprite t4 = t(0.0f, 240.0f, game.graphics.whiteTexture, vertexBufferObjectManager, false);
        this.messageBackground = t4;
        t4.setSize(getWidth(), 240.0f);
        t4.setAlpha(0.9f);
        t4.setZIndex(11);
        Text v = v(0.0f, 0.0f, game.fonts.infoFont, this.E, this.F, vertexBufferObjectManager, false);
        this.messageText = v;
        v.setZIndex(11);
        MessageOverlay messageOverlay = new MessageOverlay(game, vertexBufferObjectManager);
        this.messageOverlay = messageOverlay;
        messageOverlay.setZIndex(12);
    }

    private void close() {
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        if (this.invasion.hasPlanetBeenTaken()) {
            this.C.beginTurn();
        } else {
            this.C.attackScene.setFleets();
            this.C.attackScene.setColony();
            this.C.attackScene.setActionBar();
        }
        back();
    }

    private void finishButtonPressed() {
        this.finishButton.setAlpha(0.4f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invade() {
        this.invasion.check();
        if (this.invasion.isDone()) {
            setHeader();
            showResult();
            return;
        }
        if (Functions.random.nextInt(this.invasion.getDefenderStrength()) >= Functions.random.nextInt(this.invasion.getAttackerStrength())) {
            this.invasion.removeAttacker();
            removeTroop(this.attackingInfantry.pop());
            return;
        }
        this.invasion.removeDefender();
        removeTroop(this.defendingInfantry.pop());
    }

    private void removeTroop(Entity entity) {
        if (this.finishButton.getAlpha() == 1.0f) {
            showExplosion(entity);
            return;
        }
        entity.setVisible(false);
        updateTroopCounts();
        invade();
    }

    private void setHeader() {
        this.colonyBackground.setCurrentTileIndex(this.invasion.getDefenderID());
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.invasion.getDefenderID()));
        this.colonyName.setText(this.invasion.getPlanet().getColony().getName());
        Text text = this.colonyName;
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
    }

    private void setSpriteInvisible() {
        this.blackenedBackground.setVisible(false);
        this.messageBackground.setVisible(false);
        this.messageText.setVisible(false);
        p(this.attackingInfantry, this);
        p(this.defendingInfantry, this);
        p(this.explosionList, this);
    }

    private void setTroops() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        this.attackerBackground.setCurrentTileIndex(this.invasion.getAttackerID());
        this.defenderBackground.setCurrentTileIndex(this.invasion.getDefenderID());
        int raceID = this.C.empires.get(this.invasion.getAttackerID()).getRaceID();
        int raceID2 = this.C.empires.get(this.invasion.getDefenderID()).getRaceID();
        this.attackerIcon.setTiledTextureRegion(this.C.graphics.troops[raceID]);
        this.defenderIcon.setTiledTextureRegion(this.C.graphics.troops[raceID2]);
        this.attackingInfantryCount.setVisible(true);
        this.defendingInfantryCount.setVisible(true);
        updateTroopCounts();
        this.attackerStrengthText.setText(Integer.toString(this.C.empires.get(this.invasion.getAttackerID()).getGroundCombatStrength()));
        this.attackerStrengthIcon.setX(this.attackerStrengthText.getX() + this.attackerStrengthText.getWidthScaled() + 5.0f);
        this.defenderStrengthText.setText(Integer.toString(this.C.empires.get(this.invasion.getDefenderID()).getGroundCombatStrength() + this.invasion.getColony().getDefenseBonus()));
        this.defenderStrengthText.setX((this.defenderStrengthIcon.getX() - this.defenderStrengthText.getWidthScaled()) - 5.0f);
        int ceil = this.invasion.getCountOfAttackingInfantry() > 24 ? (int) Math.ceil(this.invasion.getCountOfAttackingInfantry() / 4.0f) : 6;
        int i7 = 640 / ceil;
        float f2 = 2.0f;
        int width = (((int) (getWidth() / 2.0f)) - 100) + i7;
        int y = ((int) this.attackerBackground.getY()) - 120;
        int i8 = 0;
        int i9 = 0;
        while (i9 < this.invasion.getCountOfAttackingInfantry()) {
            if (i8 >= ceil) {
                i4 = ((int) (getWidth() / f2)) - 100;
                i6 = y - 120;
                i5 = 0;
            } else {
                i4 = width - i7;
                i5 = i8;
                i6 = y;
            }
            int i10 = i6;
            Sprite t = t(i4, i6, this.C.graphics.troops[this.invasion.getAttackerID()], this.bufferManager, true);
            t.setSize(120.0f, 120.0f);
            t.setZIndex(10);
            this.attackingInfantry.add(t);
            i8 = i5 + 1;
            i9++;
            width = i4;
            y = i10;
            f2 = 2.0f;
        }
        int ceil2 = this.invasion.getDefendingInfantryCount() > 24 ? (int) Math.ceil(this.invasion.getDefendingInfantryCount() / 4.0f) : 6;
        int i11 = 640 / ceil2;
        int width2 = ((int) (getWidth() / 2.0f)) - i11;
        int y2 = ((int) this.attackerBackground.getY()) - 120;
        int i12 = 0;
        int i13 = 0;
        while (i13 < this.invasion.getDefendingInfantryCount()) {
            if (i12 >= ceil2) {
                i = (int) (getWidth() / 2.0f);
                i2 = y2 - 120;
                i3 = 0;
            } else {
                i = width2 + i11;
                i2 = y2;
                i3 = i12;
            }
            Sprite t2 = t(i, i2, this.C.graphics.troops[this.invasion.getDefenderID()], this.bufferManager, true);
            t2.setSize(120.0f, 120.0f);
            t2.setZIndex(10);
            this.defendingInfantry.add(t2);
            i12 = i3 + 1;
            i13++;
            width2 = i;
            y2 = i2;
        }
        sortChildren();
    }

    private void showExplosion(Entity entity) {
        entity.setVisible(false);
        updateTroopCounts();
        this.C.sounds.playInfantryKilledSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        AnimatedSprite animatedSprite = new AnimatedSprite(entity.getX() - 15.0f, entity.getY() - 15.0f, this.C.graphics.explosionTexture, this.bufferManager);
        animatedSprite.setSize(130.0f, 130.0f);
        animatedSprite.animate(new long[]{65, 65, 65, 65, 65, 65, 65, 65}, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, false, new AnimatedSprite.IAnimationListener() { // from class: com.birdshel.Uciana.Overlays.InvasionOverlay.1
            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFinished(AnimatedSprite animatedSprite2) {
                animatedSprite2.setVisible(false);
                InvasionOverlay.this.invade();
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationFrameChanged(AnimatedSprite animatedSprite2, int i, int i2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationLoopFinished(AnimatedSprite animatedSprite2, int i, int i2) {
            }

            @Override // org.andengine.entity.sprite.AnimatedSprite.IAnimationListener
            public void onAnimationStarted(AnimatedSprite animatedSprite2, int i) {
            }
        });
        this.explosionList.add(animatedSprite);
        attachChild(animatedSprite);
    }

    private void showResult() {
        TechID techFromDefender;
        p(this.explosionList, this);
        this.blackenedBackground.setVisible(true);
        this.messageBackground.setVisible(true);
        this.messageText.setVisible(true);
        String name = this.invasion.getPlanet().getName();
        if (this.invasion.getPlanet().hasColony()) {
            name = this.invasion.getPlanet().getName();
        }
        if (this.invasion.hasPlanetBeenTaken()) {
            String string = this.C.getActivity().getString(R.string.invasion_planet_taken, new Object[]{name, this.C.empires.get(this.invasion.getAttackerID()).getName()});
            if (this.invasion.didInvasionForceRecoverTech() && (techFromDefender = this.invasion.getTechFromDefender()) != TechID.NONE) {
                string = string + this.C.getActivity().getString(R.string.invasion_tech, new Object[]{this.C.empires.get(this.invasion.getAttackerID()).getTech().getTech(techFromDefender).getName()});
            }
            this.messageText.setText(string);
            if (this.invasion.showExploredMessage()) {
                this.messageOverlay.setOverlay(new PlanetExplorationMessage(this.invasion.getPlanet(), this.C.getActivity().getString(R.string.exploration_troops), false));
                setChildScene(this.messageOverlay, false, false, true);
            }
        } else {
            this.messageText.setText(this.C.getActivity().getString(R.string.invasion_defended, new Object[]{this.C.empires.get(this.invasion.getDefenderID()).getName(), name}));
        }
        this.messageText.setX((getWidth() / 2.0f) - (this.messageText.getWidthScaled() / 2.0f));
        this.messageText.setY(360.0f - (this.messageText.getHeightScaled() / 2.0f));
    }

    private void updateTroopCounts() {
        this.attackingInfantryCount.setText(Integer.toString(this.invasion.getCountOfAttackingInfantry()));
        this.defendingInfantryCount.setText(Integer.toString(this.invasion.getDefendingInfantryCount()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 1) {
            if (this.invasion.isDone()) {
                close();
            }
            if (q(this.finishButton, point)) {
                finishButtonPressed();
            }
        }
    }

    public void set(Colony colony, int i) {
        this.invasion = new Invasion(colony, i);
        setSpriteInvisible();
        setHeader();
        this.finishButton.setAlpha(1.0f);
        Game game = this.C;
        this.surface.setTiledTextureRegion(game.graphics.setSurfaceTexture("Surfaces/" + colony.getPlanet().getClimate().getID() + ".png", game.getActivity()));
        this.surface.setX(-Functions.random.nextInt(2480 - ((int) getWidth())));
        setTroops();
    }

    public void startInvasion() {
        this.startInvasion = true;
        this.startTime = System.currentTimeMillis();
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
        if (!this.startInvasion || System.currentTimeMillis() - this.startTime <= 750) {
            return;
        }
        invade();
        this.startInvasion = false;
    }
}
