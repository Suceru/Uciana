package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.SelectAutoAttackSettingOverlay;
import com.birdshel.Uciana.Overlays.SelectBlockAIProposals;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.RelationStatus;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.StarSystems.Nebulas;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
//import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class RacesScene extends ExtendedScene {
    private TiledSprite empireBackground;
    private TiledSprite empireBanner;
    private Text empireName;
    private TiledSprite galaxyButton;
    private PlanetSprite homeworld;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private TiledSprite selectAIProposalsButton;
    private TiledSprite selectAttackSettingsButton;
    private SelectAutoAttackSettingOverlay selectAutoAttackSettingOverlay;
    private SelectBlockAIProposals selectBlockAIProposals;
    private Sprite selectPress;
    private final List<Entity> empireBackgrounds = new ArrayList();
    private final List<Entity> empireBanners = new ArrayList();
    private final List<Entity> ambassadors = new ArrayList();
    private final List<Entity> empireNames = new ArrayList();
    private final List<Entity> empireRelations = new ArrayList();
    private final List<Entity> empireRelationLevels = new ArrayList();
    private final List<Entity> creditsAmounts = new ArrayList();
    private final List<Entity> creditsIcons = new ArrayList();
    private final List<Entity> researchPointsAmounts = new ArrayList();
    private final List<Entity> scienceIcons = new ArrayList();
    private final List<Entity> allianceIcons = new ArrayList();
    private final List<Entity> warIcons = new ArrayList();
    private final List<List<Entity>> empireIconBackgrounds = new ArrayList();
    private final List<List<Entity>> empireIcons = new ArrayList();

    public RacesScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(Point point) {
        checkPress(point);
    }

    private void checkActionMove(Point point) {
        this.selectPress.setVisible(false);
        checkPress(point);
    }

    private void checkActionUp(Point point) {
        this.selectPress.setVisible(false);
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.selectAIProposalsButton, point)) {
            selectAIProposalsButtonPressed();
        } else if (isClicked(this.selectAttackSettingsButton, point)) {
            selectAttackSettingsButtonPressed();
        } else {
            for (Entity entity : this.empireBackgrounds) {
                if (isClicked(entity, point)) {
                    empirePressed(entity);
                    return;
                }
            }
        }
    }

    private void checkPress(Point point) {
        for (Entity entity : this.empireBackgrounds) {
            if (isClicked(entity, point)) {
                if (this.B.empires.get(((TiledSprite) entity).getCurrentTileIndex()).isAlive()) {
                    this.selectPress.setPosition(entity.getX(), entity.getY());
                    this.selectPress.setVisible(true);
                }
            }
        }
    }

    private void empirePressed(Entity entity) {
        int currentTileIndex = ((TiledSprite) entity).getCurrentTileIndex();
        if (this.B.empires.get(currentTileIndex).isAlive()) {
            changeScene(this.B.raceScene, Integer.valueOf(currentTileIndex));
            this.B.sounds.playBoxPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        }
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        detachChild(this.homeworld);
        this.B.planetSpritePool.push(this.homeworld);
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void selectAIProposalsButtonPressed() {
        this.selectBlockAIProposals.setOverlay();
        setChildScene(this.selectBlockAIProposals, false, false, true);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void selectAttackSettingsButtonPressed() {
        this.selectAutoAttackSettingOverlay.setOverlay();
        setChildScene(this.selectAutoAttackSettingOverlay, false, false, true);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setEmpireBanners(VertexBufferObjectManager vertexBufferObjectManager, int i, int i2) {
        float f2 = i;
        int i3 = i2 * 125;
        TiledSprite H = H(f2, i3 + 90, this.B.graphics.empireColors, vertexBufferObjectManager, 0, false);
        H.setSize(710.0f, 120.0f);
        this.empireBackgrounds.add(H);
        float f3 = i3 + 100;
        this.empireBanners.add(H(i + 15, f3, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, 0, false));
        float f4 = i + 140;
        this.empireNames.add(G(f4, i3 + 110, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, false));
        float f5 = i3 + 145;
        this.empireRelations.add(G(f4, f5, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager, false));
        this.empireRelationLevels.add(G(f4, f5, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false));
        this.creditsAmounts.add(G(f2, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false));
        float f6 = i + 550;
        this.creditsIcons.add(H(f6, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.CREDITS.ordinal(), false));
        this.researchPointsAmounts.add(G(f2, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false));
        this.scienceIcons.add(H(f6, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.SCIENCE.ordinal(), false));
        this.ambassadors.add(E(i + 600, f3, this.B.graphics.ambassadorIcons[0], vertexBufferObjectManager, false));
        float f7 = i3 + 170;
        this.allianceIcons.add(H(f4, f7, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.ALLIANCE.ordinal(), false));
        this.warIcons.add(H(f2, f7, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.WAR.ordinal(), false));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i4 = 0; i4 < 7; i4++) {
            TiledSprite H2 = H(f2, f7, this.B.graphics.empireColors, vertexBufferObjectManager, 0, false);
            H2.setSize(30.0f, 30.0f);
            arrayList2.add(H2);
            arrayList.add(H(f2, f7, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, 0, false));
        }
        this.empireIconBackgrounds.add(arrayList2);
        this.empireIcons.add(arrayList);
    }

    private void setEmpireIcon(int i, int i2, Empire empire, int i3, int i4) {
        TiledSprite tiledSprite = (TiledSprite) this.empireIconBackgrounds.get(i).get(i2);
        TiledSprite tiledSprite2 = (TiledSprite) this.empireIcons.get(i).get(i2);
        tiledSprite2.setVisible(true);
        float f2 = i4;
        tiledSprite2.setX(f2);
        if (empire.id != i3 && !empire.isEmpireKnown(i3)) {
            tiledSprite.setVisible(false);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.UNKNOWN_EMPIRE.ordinal());
            return;
        }
        tiledSprite.setVisible(true);
        tiledSprite.setX(f2);
        tiledSprite.setCurrentTileIndex(i3);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.getEmpireIcon(this.B.empires.get(i3).getBannerID()));
    }

    private void setSpritesInvisible() {
        C(this.empireBackgrounds);
        C(this.empireBanners);
        C(this.empireNames);
        C(this.empireRelations);
        C(this.empireRelationLevels);
        C(this.ambassadors);
        C(this.creditsAmounts);
        C(this.creditsIcons);
        C(this.researchPointsAmounts);
        C(this.scienceIcons);
        C(this.allianceIcons);
        C(this.warIcons);
        for (List<Entity> list : this.empireIconBackgrounds) {
            C(list);
        }
        for (List<Entity> list2 : this.empireIcons) {
            C(list2);
        }
        this.selectPress.setVisible(false);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof RaceScene) {
            this.B.raceScene.set(((Integer) obj).intValue());
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
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
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        E(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        TiledSprite J = J(3.0f, 3.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.empireBackground = J;
        J.setSize(80.0f, 80.0f);
        this.empireBackground.setAlpha(0.75f);
        TiledSprite J2 = J(3.0f, 3.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.empireBanner = J2;
        J2.setSize(80.0f, 80.0f);
        this.empireName = G(100.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager, true);
        this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H;
        s(H);
        float width = getWidth() - 120.0f;
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.SETTINGS;
        TiledSprite H2 = H(width, 554.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.selectAIProposalsButton = H2;
        s(H2);
        Game game = this.B;
        Text F = F(0.0f, 0.0f, game.fonts.smallFont, game.getActivity().getString(R.string.races_block_ai), this.E, vertexBufferObjectManager);
        F.setX((getWidth() - 125.0f) - F.getWidthScaled());
        F.setY(597.0f - (F.getHeightScaled() / 2.0f));
        TiledSprite H3 = H(getWidth() - 120.0f, 634.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.selectAttackSettingsButton = H3;
        s(H3);
        Game game2 = this.B;
        Text F2 = F(0.0f, 0.0f, game2.fonts.smallFont, game2.getActivity().getString(R.string.races_auto_select_attack), this.E, vertexBufferObjectManager);
        F2.setX((getWidth() - 125.0f) - F2.getWidthScaled());
        F2.setY(677.0f - (F2.getHeightScaled() / 2.0f));
        Sprite E = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = E;
        E.setSize(710.0f, 120.0f);
        for (int i = 0; i < 5; i++) {
            setEmpireBanners(vertexBufferObjectManager, 0, i);
        }
        setEmpireBanners(vertexBufferObjectManager, this.B.getWidth() - 710, 0);
        this.selectAutoAttackSettingOverlay = new SelectAutoAttackSettingOverlay(this.B, vertexBufferObjectManager);
        this.selectBlockAIProposals = new SelectBlockAIProposals(this.B, vertexBufferObjectManager);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.homeworld = planetSprite;
        planetSprite.setMoonRange(600, 600);
        this.homeworld.setPosition(getWidth() - 280.0f, 390.0f);
        this.nebulaBackground.attachChild(this.homeworld);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.g0
            {
                RacesScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                RacesScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void set() {
        int i;
        int i2;
        int i3;
        int currentPlayer = this.B.getCurrentPlayer();
        this.B.getActivity().setLocale();
        setSpritesInvisible();
        Empire empire = this.B.empires.get(currentPlayer);
        this.nebulas.set(empire.getHomeSystem());
        this.empireBackground.setCurrentTileIndex(currentPlayer);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(currentPlayer));
        this.empireName.setText(empire.getName());
        Text text = this.empireName;
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
        this.homeworld.setSpritesInvisible();
        Planet planet = (Planet) this.B.galaxy.getSystemObject(empire.getHomeSystem(), empire.getHomeWorldOrbit());
        this.homeworld.setPlanet(planet, planet.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene));
        int i4 = 0;
        for (Integer num : empire.getKnownEmpires()) {
            int intValue = num.intValue();
            Empire empire2 = this.B.empires.get(intValue);
            TiledSprite tiledSprite = (TiledSprite) this.empireBackgrounds.get(i4);
            tiledSprite.setVisible(true);
            tiledSprite.setCurrentTileIndex(intValue);
            tiledSprite.setAlpha(0.5f);
            TiledSprite tiledSprite2 = (TiledSprite) this.empireBanners.get(i4);
            tiledSprite2.setCurrentTileIndex(GameIconEnum.getEmpireBanner(intValue));
            tiledSprite2.setVisible(true);
            tiledSprite2.setAlpha(1.0f);
            Text text2 = (Text) this.empireNames.get(i4);
            text2.setText(empire2.getName());
            text2.setColor(new Color(0.65f, 0.65f, 0.65f));
            text2.setVisible(true);
            text2.setAlpha(1.0f);
            Sprite sprite = (Sprite) this.ambassadors.get(i4);
            sprite.setTiledTextureRegion(this.B.graphics.ambassadorIcons[empire2.getRaceID()]);
            sprite.setVisible(true);
            sprite.setAlpha(1.0f);
            Text text3 = (Text) this.empireRelationLevels.get(i4);
            Text text4 = (Text) this.empireRelations.get(i4);
            text4.setVisible(true);
            text4.setAlpha(1.0f);
            if (!this.B.empires.get(intValue).isAlive()) {
                text4.setColor(Color.WHITE);
                text4.setText(this.B.getActivity().getString(R.string.races_status_destroyed));
                text4.setAlpha(0.5f);
                text2.setAlpha(0.5f);
                tiledSprite2.setAlpha(0.4f);
                tiledSprite.setAlpha(0.2f);
                sprite.setAlpha(0.4f);
                text3.setVisible(false);
            } else if (empire.isAtWar(intValue)) {
                text4.setColor(Color.RED);
                text4.setText(this.B.getActivity().getString(R.string.races_status_war));
            } else if (empire.areAllies(intValue)) {
                text4.setColor(Color.GREEN);
                text4.setText(this.B.getActivity().getString(R.string.races_status_alliance));
            } else if (empire.getTreaties().hasTreaty(intValue, Treaty.NON_AGGRESSION_PACT)) {
                text4.setColor(Color.CYAN);
                text4.setText(this.B.getActivity().getString(R.string.races_status_non_aggression));
            } else {
                text4.setColor(Color.WHITE);
                text4.setText(this.B.getActivity().getString(R.string.races_status_peace));
            }
            if (empire2.isAI() && this.B.empires.get(intValue).isAlive() && !this.B.gameSettings.isAlwaysAtWar()) {
                RelationStatus relationStatus = RelationStatus.getRelationStatus(this.B.empires.get(intValue).getRelationValue(this.B.getCurrentPlayer()));
                text3.setVisible(true);
                text3.setText("(" + relationStatus.getName() + ")");
                text3.setX(text4.getX() + text4.getWidthScaled() + 20.0f);
            }
            int incomeFromTreaties = empire.getTreaties().getIncomeFromTreaties(intValue);
            int researchPointsFromTreaties = empire.getTreaties().getResearchPointsFromTreaties(intValue);
            if (incomeFromTreaties != 0) {
                this.creditsIcons.get(i4).setVisible(true);
                Text text5 = (Text) this.creditsAmounts.get(i4);
                text5.setVisible(true);
                text5.setText((incomeFromTreaties > 0 ? "+" : "") + Integer.toString(incomeFromTreaties));
                text5.setY((tiledSprite.getY() + (researchPointsFromTreaties > 0 ? 35.0f : 60.0f)) - (text5.getHeightScaled() / 2.0f));
                this.creditsIcons.get(i4).setY(text5.getY() - 7.0f);
                text5.setX((this.creditsIcons.get(i4).getX() - text5.getWidthScaled()) - 5.0f);
            }
            if (researchPointsFromTreaties != 0) {
                this.scienceIcons.get(i4).setVisible(true);
                Text text6 = (Text) this.researchPointsAmounts.get(i4);
                text6.setVisible(true);
                text6.setText("+" + researchPointsFromTreaties);
                text6.setY((tiledSprite.getY() + 85.0f) - (text6.getHeightScaled() / 2.0f));
                this.scienceIcons.get(i4).setY(text6.getY() - 7.0f);
                text6.setX((this.scienceIcons.get(i4).getX() - text6.getWidthScaled()) - 5.0f);
            }
            if (this.B.empires.get(intValue).isAlive()) {
                List<Integer> alliancesWith = empire2.getAlliancesWith();
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (true) {
                    if (i5 >= 7) {
                        break;
                    }
                    if (alliancesWith.contains(Integer.valueOf(i5)) && this.B.empires.get(i5).isAlive()) {
                        if (i6 == 0) {
                            this.allianceIcons.get(i4).setVisible(true);
                            i6 += 30;
                        }
                        setEmpireIcon(i4, i7, empire, i5, i6 + 140);
                        i6 += 30;
                        i7++;
                    }
                    i5++;
                }
                List<Integer> atWarWith = empire2.getAtWarWith();
                int i8 = 0;
                int i9 = 0;
                for (i = 7; i9 < i; i = 7) {
                    if (atWarWith.contains(Integer.valueOf(i9)) && this.B.empires.get(i9).isAlive()) {
                        if (i6 == 0 || i8 != 0) {
                            if (i6 == 0 && i8 == 0) {
                                this.warIcons.get(i4).setVisible(true);
                                this.warIcons.get(i4).setX(i6 + 140 + i8);
                                i8 += 30;
                            }
                            i2 = i8;
                        } else {
                            this.warIcons.get(i4).setVisible(true);
                            this.warIcons.get(i4).setX(i6 + 140 + i3);
                            i2 = i8 + 30 + 30;
                        }
                        setEmpireIcon(i4, i7, empire, i9, i6 + 140 + i2);
                        i8 = i2 + 30;
                        i7++;
                    }
                    i9++;
                }
            }
            i4++;
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
