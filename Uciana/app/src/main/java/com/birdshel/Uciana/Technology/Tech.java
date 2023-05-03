package com.birdshel.Uciana.Technology;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Tech {
    public static final int FROM_EXPLORERS = 3;
    public static final int FROM_SCIENTIST = 0;
    public static final int FROM_TRADE = 1;
    public static final int FROM_TROOPS = 2;
    private final TechCategory category;
    private int currentResearch;
    private final String description;
    private final int iconIndex;
    private final TechID id;
    private boolean isDone;
    private final int level;
    private final String name;
    private final int researchCost;
    private final String shortName;
    private final Technology techLink;
    private final TechType type;
    private final int value;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Technology.Tech$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1573a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[TechType.values().length];
            b = iArr;
            try {
                iArr[TechType.POWER_CORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[TechType.FASTER_THEN_LIGHT_DRIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[TechType.COLONY_SCANNER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[TechType.SHIP_SCANNER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[TechType.SHIP_COMMUNICATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[TechType.TROOP_IMPROVEMENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[TechType.PLANET_ENHANCEMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[TechType.BUILDING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[TechProgressionType.values().length];
            f1573a = iArr2;
            try {
                iArr2[TechProgressionType.ONE_TECH_REQUIRED_PER_LEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1573a[TechProgressionType.ALL_TECH_REQUIRED_PER_LEVEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1573a[TechProgressionType.ALLOW_ONLY_ONE_TECH_PER_LEVEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1573a[TechProgressionType.ALLOW_ONLY_ONE_RANDOM_TECH_PER_LEVEL.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private TechCategory category;
        private TechID id;
        private int level;
        private String name;
        private int researchCost;
        private String shortName;
        private final Technology techLink;
        private TechType type;
        private final int currentResearch = 0;
        private int value = 0;
        private String description = "";
        private boolean isDone = false;
        private int iconIndex = GameIconEnum.NONE.ordinal();

        public Builder(Technology technology) {
            this.techLink = technology;
        }

        public Tech build() {
            this.researchCost = this.category.getTechCost(this.level);
            if (this.shortName == null) {
                this.shortName = this.name;
            }
            return new Tech(this);
        }

        public Builder category(TechCategory techCategory) {
            this.category = techCategory;
            return this;
        }

        public Builder description(String str) {
            this.description = str;
            return this;
        }

        public Builder iconIndex(int i) {
            this.iconIndex = i;
            return this;
        }

        public Builder id(TechID techID) {
            this.id = techID;
            return this;
        }

        public Builder isDone(boolean z) {
            this.isDone = z;
            return this;
        }

        public Builder level(int i) {
            this.level = i;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder shortName(String str) {
            this.shortName = str;
            return this;
        }

        public Builder type(TechType techType) {
            this.type = techType;
            return this;
        }

        public Builder value(int i) {
            this.value = i;
            return this;
        }
    }

    public Tech(Builder builder) {
        this.techLink = builder.techLink;
        this.id = builder.id;
        this.type = builder.type;
        this.name = builder.name;
        this.shortName = builder.shortName;
        this.description = builder.description;
        this.category = builder.category;
        this.level = builder.level;
        this.researchCost = builder.researchCost;
        this.currentResearch = builder.currentResearch;
        this.value = builder.value;
        this.iconIndex = builder.iconIndex;
        this.isDone = builder.isDone;
    }

    private boolean checkIfDone() {
        int i;
        if (this.type == TechType.NONE) {
            return false;
        }
        double researchCost = getResearchCost();
        Double.isNaN(researchCost);
        int i2 = (int) (researchCost * 0.66d);
        if (this.currentResearch >= getResearchCost()) {
            done();
            return true;
        }
        if (this.currentResearch < i2 || !Functions.percent((int) (((i - i2) / (getResearchCost() - i2)) * 100.0f))) {
            return false;
        }
        done();
        return true;
    }

    private void done() {
        switch (AnonymousClass1.b[this.type.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                int b = this.techLink.b(this.type);
                int i = this.value;
                if (b < i) {
                    this.techLink.f(this.type, i);
                    break;
                }
                break;
        }
        this.isDone = true;
        this.currentResearch += this.researchCost + 10000;
        List<TechID> d2 = this.techLink.d();
        try {
            if (GameData.empires.get(this.techLink.getEmpireID()).isHuman()) {
                Game.gameAchievements.checkForTechAchievements(this.techLink.getEmpireID(), this.category, this.type, d2);
            }
        } catch (Exception unused) {
        }
        int currentTechLevel = this.techLink.getCurrentTechLevel(this.category);
        int i2 = this.level;
        if (currentTechLevel < i2) {
            this.techLink.e(this.category, i2);
        }
        if (d2.isEmpty()) {
            this.techLink.setTech(TechID.MINIATURIZATION);
        }
    }

    private int getTechTurnsLeft(int i) {
        double neededResearchPoints = neededResearchPoints();
        double d2 = i;
        Double.isNaN(neededResearchPoints);
        Double.isNaN(d2);
        return (int) Math.ceil(neededResearchPoints / d2);
    }

    private int neededResearchPoints() {
        return getResearchCost() - this.currentResearch;
    }

    public void a(int i) {
        this.currentResearch = i;
        checkIfDone();
    }

    public boolean addResearch(int i) {
        int i2 = this.currentResearch + i;
        this.currentResearch = i2;
        if (i2 < 0) {
            this.currentResearch = 0;
        }
        return checkIfDone();
    }

    public boolean canBeResearched() {
        Empire empire = GameData.empires.get(this.techLink.getEmpireID());
        int i = AnonymousClass1.f1573a[GameData.gameSettings.techProgressionType().ordinal()];
        if (i == 1) {
            return empire.getTech().getCurrentTechLevel(this.category) + 1 >= this.level;
        } else if (i == 2) {
            if (this.level == 1) {
                return true;
            }
            for (Tech tech : empire.getTech().c(this.category, this.level - 1)) {
                if (!tech.isDone) {
                    return false;
                }
            }
            return true;
        } else if (i == 3) {
            int currentTechLevel = empire.getTech().getCurrentTechLevel(this.category) + 1;
            return empire.isAI() ? currentTechLevel >= this.level : this.level == currentTechLevel;
        } else if (i != 4) {
            return false;
        } else {
            int currentTechLevel2 = empire.getTech().getCurrentTechLevel(this.category) + 1;
            if (empire.isAI()) {
                return currentTechLevel2 >= this.level;
            }
            return this.level == currentTechLevel2 && empire.getTech().getAllowedTechsForRandomProgression().contains(this.id);
        }
    }

    public TechCategory getCategory() {
        return this.category;
    }

    public int getCurrentResearch() {
        return this.currentResearch;
    }

    public String getDescription() {
        return this.description;
    }

    public TechID getID() {
        return this.id;
    }

    public int getIconIndex() {
        return this.iconIndex;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    public int getPercentDone() {
        float researchCost = this.currentResearch / getResearchCost();
        if (researchCost > 1.0f) {
            researchCost = 1.0f;
        }
        return (int) (researchCost * 100.0f);
    }

    public int getResearchCost() {
        if (this.techLink.a() == EmpireType.AI) {
            return (int) (this.researchCost * Game.getResearchDifficultyModifier());
        }
        return this.researchCost;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getTechTurnsLeftString(int i) {
        int i2;
        String str;
        if (i == 0) {
            return GameData.activity.getString(R.string.research_status_never);
        }
        if (!this.isDone && this.type != TechType.NONE) {
            int techTurnsLeft = getTechTurnsLeft(i);
            if (techTurnsLeft <= 1) {
                return GameData.activity.getString(R.string.research_status_turn);
            }
            double researchCost = getResearchCost();
            Double.isNaN(researchCost);
            int i3 = (int) (researchCost * 0.66d);
            if (this.currentResearch >= i3) {
                str = " (" + ((int) ((((i2 - i3) + i) / (getResearchCost() - i3)) * 100.0f)) + "%)";
            } else {
                str = "";
            }
            return GameData.activity.getString(R.string.research_status_turns, new Object[]{Integer.valueOf(techTurnsLeft), str});
        }
        return GameData.activity.getString(R.string.research_status_done);
    }

    public TechType getType() {
        return this.type;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public boolean isHeader() {
        return this.type == TechType.NONE && this.id != TechID.BLANK;
    }

    public boolean isNormalTech() {
        return this.type != TechType.NONE;
    }

    public boolean isPerk() {
        return this.type == TechType.PERK;
    }

    public boolean isResource() {
        return this.type == TechType.RESOURCE;
    }

    public boolean isShipComponent() {
        return this.type.isShipComponent();
    }
}
