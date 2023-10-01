package com.birdshel.Uciana.Elements.PlayerCreationScene;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Players.RaceAttribute;
import com.birdshel.Uciana.Players.RaceAttributeType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class RaceAttributesElement extends Entity {
    private final TiledSprite assimilationIcon;
    private final Entity assimilationRateBonus;
    private final Text assimilationText;
    private final Text[] attributeNames = new Text[2];
    private final Entity beamAccuracy;
    private final TiledSprite beamAccuracyIcon;
    private final Text beamAccuracyText;
    private final Text beamAccuracyValue;
    private final Entity beamEvasion;
    private final TiledSprite beamEvasionIcon;
    private final Text beamEvasionText;
    private final Text beamEvasionValue;
    private final Entity birthRate;
    private final TiledSprite birthRateIcon;
    private final Text birthRateText;
    private final Text birthRateValue;
    private final Entity commandPointsPerColony;
    private final TiledSprite commandPointsPerColonyIcon;
    private final Text commandPointsPerColonyText;
    private final Text commandPointsPerColonyValue;
    private final TiledSprite creditsIcon;
    private final Entity creditsIncrease;
    private final Text creditsValue;
    private final Entity defendingGroundCombat;
    private final TiledSprite defendingGroundCombatDefendIcon;
    private final Text defendingGroundCombatText;
    private final Text defendingGroundCombatValue;
    private final Entity foodPerFarmer;
    private final TiledSprite foodPerFarmerIcon;
    private final Text foodPerFarmerText;
    private final Text foodPerFarmerValue;
    private final Game game;
    private final Entity groundCombat;
    private final Entity groundCombat2;
    private final TiledSprite groundCombatAttackIcon;
    private final TiledSprite groundCombatAttackIcon2;
    private final TiledSprite groundCombatDefendIcon;
    private final TiledSprite groundCombatDefendIcon2;
    private final Text groundCombatText;
    private final Text groundCombatText2;
    private final Text groundCombatValue;
    private final Text groundCombatValue2;
    private final Entity happiness;
    private final TiledSprite happinessIcon;
    private final Text happinessValue;
    private final Entity maxPopulation;
    private final TiledSprite maxPopulationIcon;
    private final Text maxPopulationText;
    private final Text maxPopulationValue;
    private final Entity productionPerWorker;
    private final TiledSprite productionPerWorkerIcon;
    private final Text productionPerWorkerText;
    private final Text productionPerWorkerValue;
    private final Entity sciencePerScientist;
    private final TiledSprite sciencePerScientistIcon;
    private final Text sciencePerScientistText;
    private final Text sciencePerScientistValue;
    private final Entity startingTroopsForColony;
    private final TiledSprite startingTroopsForColonyIcon;
    private final Text startingTroopsForColonyText;
    private final Text startingTroopsForColonyValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Elements.PlayerCreationScene.RaceAttributesElement$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1364a;

        static {
            int[] iArr = new int[RaceAttributeType.values().length];
            f1364a = iArr;
            try {
                iArr[RaceAttributeType.FOOD_PER_FARMER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1364a[RaceAttributeType.PRODUCTION_PER_WORKER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1364a[RaceAttributeType.SCIENCE_PER_SCIENTIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1364a[RaceAttributeType.COMMAND_POINTS_PER_COLONY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1364a[RaceAttributeType.GROUND_COMBAT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1364a[RaceAttributeType.STARTING_TROOPS_FOR_COLONY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1364a[RaceAttributeType.BIRTHRATE_INCREASE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1364a[RaceAttributeType.MAX_POPULATION_INCREASE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1364a[RaceAttributeType.CREDITS_INCREASE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1364a[RaceAttributeType.BEAM_ACCURACY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1364a[RaceAttributeType.BEAM_EVASION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1364a[RaceAttributeType.HAPPINESS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1364a[RaceAttributeType.ASSIMILATION_RATE_BONUS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1364a[RaceAttributeType.DEFENDING_GROUND_COMBAT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    public RaceAttributesElement(float f2, float f3, Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        setX(f2);
        setY(f3);
        int i = 0;
        while (true) {
            Text[] textArr = this.attributeNames;
            if (i < textArr.length) {
                textArr[i] = new Text(0.0f, 0.0f, game.fonts.smallFont, CharBuffer.wrap(new char[255]), vertexBufferObjectManager);
                attachChild(this.attributeNames[i]);
                i++;
            } else {
                Entity entity = new Entity(40.0f, 0.0f);
                this.foodPerFarmer = entity;
                Text text = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.foodPerFarmerValue = text;
                entity.attachChild(text);
                TiledSprite tiledSprite = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.foodPerFarmerIcon = tiledSprite;
                tiledSprite.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
                entity.attachChild(tiledSprite);
                Text text2 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_per_farmer), vertexBufferObjectManager);
                this.foodPerFarmerText = text2;
                entity.attachChild(text2);
                attachChild(entity);
                Entity entity2 = new Entity(40.0f, 0.0f);
                this.productionPerWorker = entity2;
                Text text3 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.productionPerWorkerValue = text3;
                entity2.attachChild(text3);
                TiledSprite tiledSprite2 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.productionPerWorkerIcon = tiledSprite2;
                tiledSprite2.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
                entity2.attachChild(tiledSprite2);
                Text text4 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_per_worker), vertexBufferObjectManager);
                this.productionPerWorkerText = text4;
                entity2.attachChild(text4);
                attachChild(entity2);
                Entity entity3 = new Entity(40.0f, 0.0f);
                this.sciencePerScientist = entity3;
                Text text5 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.sciencePerScientistValue = text5;
                entity3.attachChild(text5);
                TiledSprite tiledSprite3 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.sciencePerScientistIcon = tiledSprite3;
                tiledSprite3.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
                entity3.attachChild(tiledSprite3);
                Text text6 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_per_scientist), vertexBufferObjectManager);
                this.sciencePerScientistText = text6;
                entity3.attachChild(text6);
                attachChild(entity3);
                Entity entity4 = new Entity(40.0f, 0.0f);
                this.commandPointsPerColony = entity4;
                Text text7 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.commandPointsPerColonyValue = text7;
                entity4.attachChild(text7);
                TiledSprite tiledSprite4 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.commandPointsPerColonyIcon = tiledSprite4;
                tiledSprite4.setCurrentTileIndex(InfoIconEnum.COMMAND_POINTS.ordinal());
                entity4.attachChild(tiledSprite4);
                Text text8 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_per_colony), vertexBufferObjectManager);
                this.commandPointsPerColonyText = text8;
                entity4.attachChild(text8);
                attachChild(entity4);
                Entity entity5 = new Entity(40.0f, 0.0f);
                this.groundCombat = entity5;
                Text text9 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.groundCombatValue = text9;
                entity5.attachChild(text9);
                TiledSprite tiledSprite5 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.groundCombatAttackIcon = tiledSprite5;
                InfoIconEnum infoIconEnum = InfoIconEnum.ATTACK;
                tiledSprite5.setCurrentTileIndex(infoIconEnum.ordinal());
                entity5.attachChild(tiledSprite5);
                TiledSprite tiledSprite6 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.groundCombatDefendIcon = tiledSprite6;
                InfoIconEnum infoIconEnum2 = InfoIconEnum.DEFENSE;
                tiledSprite6.setCurrentTileIndex(infoIconEnum2.ordinal());
                entity5.attachChild(tiledSprite6);
                Text text10 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_for_ground_combat), vertexBufferObjectManager);
                this.groundCombatText = text10;
                entity5.attachChild(text10);
                attachChild(entity5);
                Entity entity6 = new Entity(40.0f, 0.0f);
                this.groundCombat2 = entity6;
                Text text11 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.groundCombatValue2 = text11;
                entity6.attachChild(text11);
                TiledSprite tiledSprite7 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.groundCombatAttackIcon2 = tiledSprite7;
                tiledSprite7.setCurrentTileIndex(infoIconEnum.ordinal());
                entity6.attachChild(tiledSprite7);
                TiledSprite tiledSprite8 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.groundCombatDefendIcon2 = tiledSprite8;
                tiledSprite8.setCurrentTileIndex(infoIconEnum2.ordinal());
                entity6.attachChild(tiledSprite8);
                Text text12 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_for_ground_combat), vertexBufferObjectManager);
                this.groundCombatText2 = text12;
                entity6.attachChild(text12);
                attachChild(entity6);
                Entity entity7 = new Entity(40.0f, 0.0f);
                this.startingTroopsForColony = entity7;
                Text text13 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.startingTroopsForColonyValue = text13;
                entity7.attachChild(text13);
                TiledSprite tiledSprite9 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.startingTroopsForColonyIcon = tiledSprite9;
                tiledSprite9.setCurrentTileIndex(InfoIconEnum.INFANTRY.ordinal());
                entity7.attachChild(tiledSprite9);
                Text text14 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_for_new_colonies), vertexBufferObjectManager);
                this.startingTroopsForColonyText = text14;
                entity7.attachChild(text14);
                attachChild(entity7);
                Entity entity8 = new Entity(40.0f, 0.0f);
                this.birthRate = entity8;
                Text text15 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.birthRateValue = text15;
                entity8.attachChild(text15);
                TiledSprite tiledSprite10 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.birthRateIcon = tiledSprite10;
                tiledSprite10.setCurrentTileIndex(InfoIconEnum.POP_GROWTH.ordinal());
                entity8.attachChild(tiledSprite10);
                Text text16 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_population_growth), vertexBufferObjectManager);
                this.birthRateText = text16;
                entity8.attachChild(text16);
                attachChild(entity8);
                Entity entity9 = new Entity(40.0f, 0.0f);
                this.maxPopulation = entity9;
                Text text17 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.maxPopulationValue = text17;
                entity9.attachChild(text17);
                TiledSprite tiledSprite11 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.maxPopulationIcon = tiledSprite11;
                tiledSprite11.setCurrentTileIndex(InfoIconEnum.POPULATION.ordinal());
                entity9.attachChild(tiledSprite11);
                Text text18 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_max_population), vertexBufferObjectManager);
                this.maxPopulationText = text18;
                entity9.attachChild(text18);
                attachChild(entity9);
                Entity entity10 = new Entity(40.0f, 0.0f);
                this.creditsIncrease = entity10;
                Text text19 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.creditsValue = text19;
                entity10.attachChild(text19);
                TiledSprite tiledSprite12 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.creditsIcon = tiledSprite12;
                tiledSprite12.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
                entity10.attachChild(tiledSprite12);
                attachChild(entity10);
                Entity entity11 = new Entity(40.0f, 0.0f);
                this.beamAccuracy = entity11;
                Text text20 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.beamAccuracyValue = text20;
                entity11.attachChild(text20);
                TiledSprite tiledSprite13 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.beamAccuracyIcon = tiledSprite13;
                InfoIconEnum infoIconEnum3 = InfoIconEnum.BEAM_WEAPON;
                tiledSprite13.setCurrentTileIndex(infoIconEnum3.ordinal());
                entity11.attachChild(tiledSprite13);
                Text text21 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_accuracy), vertexBufferObjectManager);
                this.beamAccuracyText = text21;
                entity11.attachChild(text21);
                attachChild(entity11);
                Entity entity12 = new Entity(40.0f, 0.0f);
                this.beamEvasion = entity12;
                Text text22 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.beamEvasionValue = text22;
                entity12.attachChild(text22);
                TiledSprite tiledSprite14 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.beamEvasionIcon = tiledSprite14;
                tiledSprite14.setCurrentTileIndex(infoIconEnum3.ordinal());
                entity12.attachChild(tiledSprite14);
                Text text23 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_evasion), vertexBufferObjectManager);
                this.beamEvasionText = text23;
                entity12.attachChild(text23);
                attachChild(entity12);
                Entity entity13 = new Entity(40.0f, 0.0f);
                this.happiness = entity13;
                Text text24 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.happinessValue = text24;
                entity13.attachChild(text24);
                TiledSprite tiledSprite15 = new TiledSprite(0.0f, -7.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.happinessIcon = tiledSprite15;
                tiledSprite15.setCurrentTileIndex(InfoIconEnum.HAPPINESS.ordinal());
                entity13.attachChild(tiledSprite15);
                attachChild(entity13);
                Entity entity14 = new Entity(40.0f, 0.0f);
                this.assimilationRateBonus = entity14;
                TiledSprite tiledSprite16 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.assimilationIcon = tiledSprite16;
                tiledSprite16.setCurrentTileIndex(InfoIconEnum.RESISTANCE.ordinal());
                entity14.attachChild(tiledSprite16);
                Text text25 = new Text(5.0f + tiledSprite16.getWidthScaled(), 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_assimilation), vertexBufferObjectManager);
                this.assimilationText = text25;
                entity14.attachChild(text25);
                attachChild(entity14);
                Entity entity15 = new Entity(40.0f, 0.0f);
                this.defendingGroundCombat = entity15;
                Text text26 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, "####", vertexBufferObjectManager);
                this.defendingGroundCombatValue = text26;
                entity15.attachChild(text26);
                TiledSprite tiledSprite17 = new TiledSprite(0.0f, -5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                this.defendingGroundCombatDefendIcon = tiledSprite17;
                tiledSprite17.setCurrentTileIndex(infoIconEnum2.ordinal());
                entity15.attachChild(tiledSprite17);
                Text text27 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.perks_for_ground_combat), vertexBufferObjectManager);
                this.defendingGroundCombatText = text27;
                entity15.attachChild(text27);
                attachChild(entity15);
                return;
            }
        }
    }

    private void setAllAttributesInvisible() {
        for (Text text : this.attributeNames) {
            text.setVisible(false);
        }
        this.foodPerFarmer.setVisible(false);
        this.productionPerWorker.setVisible(false);
        this.sciencePerScientist.setVisible(false);
        this.commandPointsPerColony.setVisible(false);
        this.groundCombat.setVisible(false);
        this.groundCombat2.setVisible(false);
        this.startingTroopsForColony.setVisible(false);
        this.birthRate.setVisible(false);
        this.maxPopulation.setVisible(false);
        this.creditsIncrease.setVisible(false);
        this.beamAccuracy.setVisible(false);
        this.beamEvasion.setVisible(false);
        this.happiness.setVisible(false);
        this.assimilationRateBonus.setVisible(false);
        this.defendingGroundCombat.setVisible(false);
    }

    private void setAssimilationRateBonus(int i) {
        this.assimilationRateBonus.setVisible(true);
        this.assimilationRateBonus.setY(i);
    }

    private void setBeamAccuracy(int i, float f2) {
        this.beamAccuracy.setVisible(true);
        this.beamAccuracy.setY(i);
        int round = Math.round(f2);
        if (round > 0) {
            Text text = this.beamAccuracyValue;
            text.setText("+" + round);
        } else {
            this.beamAccuracyValue.setText(Integer.toString(round));
        }
        this.beamAccuracyIcon.setX(this.beamAccuracyValue.getWidthScaled() + 5.0f);
        this.beamAccuracyText.setX(this.beamAccuracyIcon.getX() + this.beamAccuracyIcon.getWidthScaled() + 5.0f);
    }

    private void setBeamEvasion(int i, float f2) {
        this.beamEvasion.setVisible(true);
        this.beamEvasion.setY(i);
        int round = Math.round(f2);
        if (round > 0) {
            Text text = this.beamEvasionValue;
            text.setText("+" + round);
        } else {
            this.beamEvasionValue.setText(Integer.toString(round));
        }
        this.beamEvasionIcon.setX(this.beamEvasionValue.getWidthScaled() + 5.0f);
        this.beamEvasionText.setX(this.beamEvasionIcon.getX() + this.beamEvasionIcon.getWidthScaled() + 5.0f);
    }

    private void setBirthRateIncrease(int i, float f2) {
        this.birthRate.setVisible(true);
        this.birthRate.setY(i);
        if (f2 > 0.0f) {
            Text text = this.birthRateValue;
            text.setText("+" + ((int) (f2 * 100.0f)) + "%");
        } else {
            Text text2 = this.birthRateValue;
            text2.setText(((int) (f2 * 100.0f)) + "%");
        }
        this.birthRateIcon.setX(this.birthRateValue.getWidthScaled() + 5.0f);
        this.birthRateText.setX(this.birthRateIcon.getX() + this.birthRateIcon.getWidthScaled() + 5.0f);
    }

    private void setCommandPointsPerColony(int i, float f2) {
        this.commandPointsPerColony.setVisible(true);
        this.commandPointsPerColony.setY(i);
        int round = Math.round(f2);
        if (round > 0) {
            Text text = this.commandPointsPerColonyValue;
            text.setText("+" + round);
        } else {
            this.commandPointsPerColonyValue.setText(Integer.toString(round));
        }
        this.commandPointsPerColonyIcon.setX(this.commandPointsPerColonyValue.getWidthScaled() + 5.0f);
        this.commandPointsPerColonyText.setX(this.commandPointsPerColonyIcon.getX() + this.commandPointsPerColonyIcon.getWidthScaled() + 5.0f);
    }

    private void setCreditsIncrease(int i, float f2) {
        this.creditsIncrease.setVisible(true);
        this.creditsIncrease.setY(i);
        if (f2 > 0.0f) {
            Text text = this.creditsValue;
            text.setText("+" + ((int) (f2 * 100.0f)) + "%");
        } else {
            Text text2 = this.creditsValue;
            text2.setText(((int) (f2 * 100.0f)) + "%");
        }
        this.creditsIcon.setX(this.creditsValue.getWidthScaled() + 5.0f);
    }

    private void setDefendingGroundCombat(int i, float f2) {
        this.defendingGroundCombat.setVisible(true);
        this.defendingGroundCombat.setY(i);
        int round = Math.round(f2);
        if (round > 0) {
            Text text = this.defendingGroundCombatValue;
            text.setText("+" + round);
        } else {
            this.defendingGroundCombatValue.setText(Integer.toString(round));
        }
        this.defendingGroundCombatDefendIcon.setX(this.defendingGroundCombatValue.getWidthScaled());
        this.defendingGroundCombatText.setX(this.defendingGroundCombatDefendIcon.getX() + this.defendingGroundCombatDefendIcon.getWidthScaled() + 5.0f);
    }

    private void setFoodPerFarmer(int i, float f2) {
        this.foodPerFarmer.setVisible(true);
        this.foodPerFarmer.setY(i);
        int round = Math.round(f2);
        if (round > 0) {
            Text text = this.foodPerFarmerValue;
            text.setText("+" + round);
        } else {
            this.foodPerFarmerValue.setText(Integer.toString(round));
        }
        this.foodPerFarmerIcon.setX(this.foodPerFarmerValue.getWidthScaled() + 5.0f);
        this.foodPerFarmerText.setX(this.foodPerFarmerIcon.getX() + this.foodPerFarmerIcon.getWidthScaled() + 5.0f);
    }

    private void setGroundCombat(int i, float f2, RaceAttribute raceAttribute) {
        if (raceAttribute == RaceAttribute.EXPERT_SOLDIERS) {
            this.groundCombat.setVisible(true);
            this.groundCombat.setY(i);
            int round = Math.round(f2);
            if (round > 0) {
                Text text = this.groundCombatValue;
                text.setText("+" + round);
            } else {
                this.groundCombatValue.setText(Integer.toString(round));
            }
            this.groundCombatAttackIcon.setX(this.groundCombatValue.getWidthScaled() + 5.0f);
            this.groundCombatDefendIcon.setX(this.groundCombatAttackIcon.getX() + this.groundCombatAttackIcon.getWidthScaled());
            this.groundCombatText.setX(this.groundCombatDefendIcon.getX() + this.groundCombatDefendIcon.getWidthScaled() + 5.0f);
            return;
        }
        this.groundCombat2.setVisible(true);
        this.groundCombat2.setY(i);
        int round2 = Math.round(f2);
        if (round2 > 0) {
            Text text2 = this.groundCombatValue2;
            text2.setText("+" + round2);
        } else {
            this.groundCombatValue2.setText(Integer.toString(round2));
        }
        this.groundCombatAttackIcon2.setX(this.groundCombatValue2.getWidthScaled() + 5.0f);
        this.groundCombatDefendIcon2.setX(this.groundCombatAttackIcon2.getX() + this.groundCombatAttackIcon2.getWidthScaled());
        this.groundCombatText2.setX(this.groundCombatDefendIcon2.getX() + this.groundCombatDefendIcon2.getWidthScaled() + 5.0f);
    }

    private void setHappiness(int i, float f2) {
        this.happiness.setVisible(true);
        this.happiness.setY(i);
        if (f2 > 0.0f) {
            Text text = this.happinessValue;
            text.setText("+" + ((int) (f2 * 100.0f)) + "%");
        } else {
            Text text2 = this.happinessValue;
            text2.setText(((int) (f2 * 100.0f)) + "%");
        }
        this.happinessIcon.setX(this.happinessValue.getWidthScaled() + 5.0f);
    }

    private void setMaxPopulationIncrease(int i, float f2) {
        this.maxPopulation.setVisible(true);
        this.maxPopulation.setY(i);
        if (f2 > 0.0f) {
            Text text = this.maxPopulationValue;
            text.setText("+" + ((int) (f2 * 100.0f)) + "%");
        } else {
            Text text2 = this.maxPopulationValue;
            text2.setText(((int) (f2 * 100.0f)) + "%");
        }
        this.maxPopulationIcon.setX(this.maxPopulationValue.getWidthScaled() + 5.0f);
        this.maxPopulationText.setX(this.maxPopulationIcon.getX() + this.maxPopulationIcon.getWidthScaled() + 5.0f);
    }

    private void setProductionPerWorker(int i, float f2) {
        this.productionPerWorker.setVisible(true);
        this.productionPerWorker.setY(i);
        int round = Math.round(f2);
        if (round > 0) {
            Text text = this.productionPerWorkerValue;
            text.setText("+" + round);
        } else {
            this.productionPerWorkerValue.setText(Integer.toString(round));
        }
        this.productionPerWorkerIcon.setX(this.productionPerWorkerValue.getWidthScaled() + 5.0f);
        this.productionPerWorkerText.setX(this.productionPerWorkerIcon.getX() + this.productionPerWorkerIcon.getWidthScaled() + 5.0f);
    }

    private void setSciencePerScientist(int i, float f2) {
        this.sciencePerScientist.setVisible(true);
        this.sciencePerScientist.setY(i);
        int round = Math.round(f2);
        if (round > 0) {
            Text text = this.sciencePerScientistValue;
            text.setText("+" + round);
        } else {
            this.sciencePerScientistValue.setText(Integer.toString(round));
        }
        this.sciencePerScientistIcon.setX(this.sciencePerScientistValue.getWidthScaled() + 5.0f);
        this.sciencePerScientistText.setX(this.sciencePerScientistIcon.getX() + this.sciencePerScientistIcon.getWidthScaled() + 5.0f);
    }

    private void setStartingTroopsForColony(int i, float f2) {
        this.startingTroopsForColony.setVisible(true);
        this.startingTroopsForColony.setY(i);
        this.startingTroopsForColonyValue.setText(Integer.toString(Math.round(f2)));
        this.startingTroopsForColonyIcon.setX(this.startingTroopsForColonyValue.getWidthScaled() + 5.0f);
        this.startingTroopsForColonyText.setX(this.startingTroopsForColonyIcon.getX() + this.startingTroopsForColonyIcon.getWidthScaled() + 5.0f);
    }

    public void set(List<RaceAttribute> list) {
        setAllAttributesInvisible();
        int i = 0;
        if (list.isEmpty()) {
            this.attributeNames[0].setVisible(true);
            this.attributeNames[0].setText(this.game.getActivity().getString(R.string.perks_no_perks));
            this.attributeNames[0].setY(0.0f);
            return;
        }
        int i2 = 0;
        for (RaceAttribute raceAttribute : list) {
            this.attributeNames[i].setVisible(true);
            this.attributeNames[i].setText(raceAttribute.getName());
            this.attributeNames[i].setY(i2);
            for (Map.Entry<RaceAttributeType, Float> entry : raceAttribute.getAttributeTypes().entrySet()) {
                i2 += 30;
                switch (AnonymousClass1.f1364a[entry.getKey().ordinal()]) {
                    case 1:
                        setFoodPerFarmer(i2, entry.getValue().floatValue());
                        break;
                    case 2:
                        setProductionPerWorker(i2, entry.getValue().floatValue());
                        break;
                    case 3:
                        setSciencePerScientist(i2, entry.getValue().floatValue());
                        break;
                    case 4:
                        setCommandPointsPerColony(i2, entry.getValue().floatValue());
                        break;
                    case 5:
                        setGroundCombat(i2, entry.getValue().floatValue(), raceAttribute);
                        break;
                    case 6:
                        setStartingTroopsForColony(i2, entry.getValue().floatValue());
                        break;
                    case 7:
                        setBirthRateIncrease(i2, entry.getValue().floatValue());
                        break;
                    case 8:
                        setMaxPopulationIncrease(i2, entry.getValue().floatValue());
                        break;
                    case 9:
                        setCreditsIncrease(i2, entry.getValue().floatValue());
                        break;
                    case 10:
                        setBeamAccuracy(i2, entry.getValue().floatValue());
                        break;
                    case 11:
                        setBeamEvasion(i2, entry.getValue().floatValue());
                        break;
                    case 12:
                        setHappiness(i2, entry.getValue().floatValue());
                        break;
                    case 13:
                        setAssimilationRateBonus(i2);
                        break;
                    case 14:
                        setDefendingGroundCombat(i2, entry.getValue().floatValue());
                        break;
                }
            }
            i2 += 40;
            i++;
        }
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setAlpha(float f2) {
        this.foodPerFarmerValue.setAlpha(f2);
        this.foodPerFarmerIcon.setAlpha(f2);
        this.foodPerFarmerText.setAlpha(f2);
        this.productionPerWorkerValue.setAlpha(f2);
        this.productionPerWorkerIcon.setAlpha(f2);
        this.productionPerWorkerText.setAlpha(f2);
        this.sciencePerScientistValue.setAlpha(f2);
        this.sciencePerScientistIcon.setAlpha(f2);
        this.sciencePerScientistText.setAlpha(f2);
        this.commandPointsPerColonyValue.setAlpha(f2);
        this.commandPointsPerColonyIcon.setAlpha(f2);
        this.commandPointsPerColonyText.setAlpha(f2);
        this.groundCombatValue.setAlpha(f2);
        this.groundCombatAttackIcon.setAlpha(f2);
        this.groundCombatDefendIcon.setAlpha(f2);
        this.groundCombatText.setAlpha(f2);
        this.groundCombatValue2.setAlpha(f2);
        this.groundCombatAttackIcon2.setAlpha(f2);
        this.groundCombatDefendIcon2.setAlpha(f2);
        this.groundCombatText2.setAlpha(f2);
        this.startingTroopsForColonyValue.setAlpha(f2);
        this.startingTroopsForColonyIcon.setAlpha(f2);
        this.startingTroopsForColonyText.setAlpha(f2);
        this.birthRateValue.setAlpha(f2);
        this.birthRateIcon.setAlpha(f2);
        this.birthRateText.setAlpha(f2);
        this.maxPopulationValue.setAlpha(f2);
        this.maxPopulationIcon.setAlpha(f2);
        this.maxPopulationText.setAlpha(f2);
        this.creditsValue.setAlpha(f2);
        this.creditsIcon.setAlpha(f2);
        this.beamAccuracyValue.setAlpha(f2);
        this.beamAccuracyIcon.setAlpha(f2);
        this.beamAccuracyText.setAlpha(f2);
        this.beamEvasionValue.setAlpha(f2);
        this.beamEvasionIcon.setAlpha(f2);
        this.beamEvasionText.setAlpha(f2);
        this.happinessValue.setAlpha(f2);
        this.happinessIcon.setAlpha(f2);
        this.assimilationIcon.setAlpha(f2);
        this.assimilationText.setAlpha(f2);
        this.defendingGroundCombatValue.setAlpha(f2);
        this.defendingGroundCombatDefendIcon.setAlpha(f2);
        this.defendingGroundCombatText.setAlpha(f2);
        for (Text text : this.attributeNames) {
            text.setAlpha(f2);
        }
    }
}
