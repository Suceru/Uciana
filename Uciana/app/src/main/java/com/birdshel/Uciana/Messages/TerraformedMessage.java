package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.Resource;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.ResourceType;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import java.nio.CharBuffer;
import java.text.DecimalFormat;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TerraformedMessage implements Message {
    private final DecimalFormat decalFormat = new DecimalFormat();
    private final Planet planet;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Messages.TerraformedMessage$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1373a;

        static {
            int[] iArr = new int[Climate.values().length];
            f1373a = iArr;
            try {
                iArr[Climate.PLAGUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1373a[Climate.SUPER_ACIDIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1373a[Climate.BOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1373a[Climate.METHANE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1373a[Climate.BOREAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1373a[Climate.TROPICAL_OCEAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1373a[Climate.SENTIENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public TerraformedMessage(Planet planet) {
        this.planet = planet;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        String string;
        VertexBufferObjectManager vertexBufferObjectManager;
        TextOptions textOptions;
        String string2;
        String string3;
        String string4;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        Sprite messageBackground = messageOverlay.getMessageBackground();
        TextOptions textOptions2 = new TextOptions(HorizontalAlign.CENTER);
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        messageBackground.setY(200.0f);
        messageBackground.setHeight(320.0f);
        Object name = this.planet.getName();
        if (game.colonies.isColony(this.planet.getSystemID(), this.planet.getOrbit())) {
            name = game.colonies.getColony(this.planet.getSystemID(), this.planet.getOrbit()).getName();
        }
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.terraformed_header, new Object[]{name}), textOptions2, buffer);
        text.setPosition((messageOverlay.getWidth() / 2.0f) - (text.getWidth() / 2.0f), 340.0f);
        messageOverlay.addElement(text);
        PlanetSprite planetSprite = game.planetSpritePool.get();
        planetSprite.setMoonRange(1000, 1000);
        messageOverlay.addElement(planetSprite);
        planetSprite.setPlanet(this.planet, 0.6f, 0.3f, true);
        planetSprite.setClimate(this.planet.getPreTerraformedClimate(), this.planet.getImageIndex());
        planetSprite.hideMoon();
        float width = ((messageOverlay.getWidth() / 2.0f) - planetSprite.getWidthScaled()) + (planetSprite.getWidthScaled() * 0.3f);
        planetSprite.setPosition((planetSprite.getWidthScaled() / 2.0f) + width, (planetSprite.getWidthScaled() / 2.0f) + 195.0f);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.planetInfoTexture, buffer);
        messageOverlay.addElement(tiledSprite);
        tiledSprite.setX(width - tiledSprite.getWidthScaled());
        tiledSprite.setY(((planetSprite.getHeightScaled() / 2.0f) + 200.0f) - (tiledSprite.getHeightScaled() / 2.0f));
        tiledSprite.setCurrentTileIndex(this.planet.getPreTerraformedClimate().getInfoIconIndex());
        PlanetSprite planetSprite2 = game.planetSpritePool.get();
        planetSprite2.setMoonRange(1000, 1000);
        messageOverlay.addElement(planetSprite2);
        planetSprite2.setPlanet(this.planet, 0.6f, 0.3f, true);
        planetSprite2.hideMoon();
        float width2 = (messageOverlay.getWidth() / 2.0f) - (planetSprite2.getWidthScaled() * 0.3f);
        planetSprite2.setPosition((planetSprite2.getWidthScaled() / 2.0f) + width2, (planetSprite2.getWidthScaled() / 2.0f) + 195.0f);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.planetInfoTexture, buffer);
        messageOverlay.addElement(tiledSprite2);
        tiledSprite2.setX(width2 + planetSprite2.getWidthScaled());
        tiledSprite2.setY(((planetSprite2.getHeightScaled() / 2.0f) + 200.0f) - (tiledSprite2.getHeightScaled() / 2.0f));
        tiledSprite2.setCurrentTileIndex(this.planet.getTerraformedClimate().getInfoIconIndex());
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.ADJUSTED.ordinal());
        tiledSprite3.setScaleCenter(0.0f, 0.0f);
        tiledSprite3.setScale(0.9f);
        messageOverlay.addElement(tiledSprite3);
        tiledSprite3.setPosition(tiledSprite2.getX() + 27.0f, tiledSprite2.getY() + 30.0f);
        Text text2 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions2, buffer);
        messageOverlay.addElement(text2);
        int maxPopulationPreTerraforming = this.planet.getMaxPopulationPreTerraforming();
        int maxPopulation = this.planet.getMaxPopulation();
        int i = maxPopulation - maxPopulationPreTerraforming;
        if (i > 0) {
            string = game.getActivity().getString(R.string.terraformed_max_pop_increase, new Object[]{Integer.valueOf(i), Integer.valueOf(maxPopulationPreTerraforming), Integer.valueOf(maxPopulation)});
            text2.setColor(Color.GREEN);
        } else {
            string = game.getActivity().getString(R.string.terraformed_max_pop_decrease, new Object[]{Integer.valueOf(i), Integer.valueOf(maxPopulationPreTerraforming), Integer.valueOf(maxPopulation)});
            text2.setColor(Color.RED);
        }
        text2.setText(string);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        text2.setY(380.0f);
        float y = text2.getY() + text2.getHeightScaled();
        Text text3 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions2, buffer);
        text3.setVisible(false);
        messageOverlay.addElement(text3);
        int round = Math.round(this.planet.getPopulationGrowthModifierPreTerraforming() * 100.0f);
        int round2 = Math.round(this.planet.getPopulationGrowthModifier() * 100.0f);
        int i2 = round2 - round;
        if (i2 != 0) {
            if (i2 > 0) {
                string4 = game.getActivity().getString(R.string.terraformed_pop_growth_increase, new Object[]{Integer.valueOf(i2), Integer.valueOf(round), Integer.valueOf(round2)});
                text3.setColor(Color.GREEN);
            } else {
                string4 = game.getActivity().getString(R.string.terraformed_pop_growth_decrease, new Object[]{Integer.valueOf(i2), Integer.valueOf(round), Integer.valueOf(round2)});
                text3.setColor(Color.RED);
            }
            text3.setText(string4);
            text3.setPosition((messageOverlay.getWidth() / 2.0f) - (text3.getWidthScaled() / 2.0f), y);
            text3.setVisible(true);
            y = text3.getY() + text3.getHeightScaled();
        }
        Text text4 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions2, buffer);
        text4.setVisible(false);
        messageOverlay.addElement(text4);
        int round3 = Math.round((this.planet.getClimateCostModifierPreTerraforming() - 1.0f) * 100.0f);
        int round4 = Math.round((this.planet.getClimateCostModifier() - 1.0f) * 100.0f);
        int i3 = round4 - round3;
        if (i3 != 0) {
            if (i3 > 0) {
                string3 = game.getActivity().getString(R.string.terraformed_maintenance_increase, new Object[]{Integer.valueOf(i3), Integer.valueOf(round3), Integer.valueOf(round4)});
                text4.setColor(Color.RED);
            } else {
                string3 = game.getActivity().getString(R.string.terraformed_maintenance_decrease, new Object[]{Integer.valueOf(i3), Integer.valueOf(round3), Integer.valueOf(round4)});
                text4.setColor(Color.GREEN);
            }
            text4.setText(string3);
            text4.setPosition((messageOverlay.getWidth() / 2.0f) - (text4.getWidthScaled() / 2.0f), y);
            text4.setVisible(true);
            y = text4.getY() + text4.getHeightScaled();
        }
        Text text5 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions2, buffer);
        text5.setVisible(false);
        messageOverlay.addElement(text5);
        float f2 = 0.0f;
        for (ResourceID resourceID : this.planet.getVisibleResources(game.getCurrentPlayer())) {
            Resource resource = Resources.get(resourceID);
            ResourceType resourceType = ResourceType.FOOD_PER_FARMER;
            if (resource.containsEffect(resourceType)) {
                f2 += Resources.get(resourceID).getValue(resourceType);
            }
        }
        float foodPerFarmer = (this.planet.getPreTerraformedClimate().getFoodPerFarmer() + f2) * this.planet.getGravity().getModifier();
        float foodPerFarmer2 = (f2 + this.planet.getTerraformedClimate().getFoodPerFarmer()) * this.planet.getGravity().getModifier();
        float f3 = foodPerFarmer2 - foodPerFarmer;
        if (f3 != 0.0f) {
            if (f3 > 0.0f) {
                vertexBufferObjectManager = buffer;
                textOptions = textOptions2;
                string2 = game.getActivity().getString(R.string.terraformed_food_per_turn_increase, new Object[]{this.decalFormat.format(f3), this.decalFormat.format(foodPerFarmer), this.decalFormat.format(foodPerFarmer2)});
                text5.setColor(Color.GREEN);
            } else {
                vertexBufferObjectManager = buffer;
                textOptions = textOptions2;
                string2 = game.getActivity().getString(R.string.terraformed_food_per_turn_decreased, new Object[]{this.decalFormat.format(f3), this.decalFormat.format(foodPerFarmer), this.decalFormat.format(foodPerFarmer2)});
                text5.setColor(Color.RED);
            }
            text5.setText(string2);
            text5.setPosition((messageOverlay.getWidth() / 2.0f) - (text5.getWidthScaled() / 2.0f), y);
            text5.setVisible(true);
            y = text5.getY() + text5.getHeightScaled();
        } else {
            vertexBufferObjectManager = buffer;
            textOptions = textOptions2;
        }
        Text text6 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        text6.setVisible(false);
        messageOverlay.addElement(text6);
        if (this.planet.getTerraformedClimate() == Climate.METALLIC) {
            float productionPerWorker = this.planet.getMineralRichness().getProductionPerWorker() * this.planet.getGravity().getModifier();
            float productionPerWorker2 = this.planet.getMineralRichness().getPrevious().getProductionPerWorker() * this.planet.getGravity().getModifier();
            text6.setText(game.getActivity().getString(R.string.terraformed_production_per_turn_increase, new Object[]{this.decalFormat.format(productionPerWorker - productionPerWorker2), this.decalFormat.format(productionPerWorker2), this.decalFormat.format(productionPerWorker)}));
            text6.setColor(Color.GREEN);
            text6.setPosition((messageOverlay.getWidth() / 2.0f) - (text6.getWidthScaled() / 2.0f), y);
            text6.setVisible(true);
            y = text6.getY() + text6.getHeightScaled();
        }
        Text text7 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        text7.setVisible(false);
        messageOverlay.addElement(text7);
        float sciencePerScientistPreTerraforming = this.planet.getSciencePerScientistPreTerraforming();
        float sciencePerScientist = this.planet.getSciencePerScientist(game.getCurrentPlayer());
        float f4 = sciencePerScientist - sciencePerScientistPreTerraforming;
        if (f4 != 0.0f) {
            text7.setText(game.getActivity().getString(R.string.terraformed_science_per_turn_increase, new Object[]{this.decalFormat.format(f4), this.decalFormat.format(sciencePerScientistPreTerraforming), this.decalFormat.format(sciencePerScientist)}));
            text7.setColor(Color.GREEN);
            text7.setPosition((messageOverlay.getWidth() / 2.0f) - (text7.getWidthScaled() / 2.0f), y);
            text7.setVisible(true);
            y = text7.getY() + text7.getHeightScaled() + 5.0f;
        }
        Text text8 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.terraformed_new_resource), textOptions, vertexBufferObjectManager);
        text8.setVisible(false);
        messageOverlay.addElement(text8);
        text8.setColor(Color.GREEN);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, game.graphics.resourceIconsTexture, vertexBufferObjectManager);
        tiledSprite4.setScaleCenter(0.0f, 0.0f);
        tiledSprite4.setSize(30.0f, 30.0f);
        tiledSprite4.setVisible(false);
        messageOverlay.addElement(tiledSprite4);
        if (Resources.climateHasResource(this.planet.getTerraformedClimate())) {
            text8.setPosition((messageOverlay.getWidth() / 2.0f) - ((text8.getWidthScaled() / 2.0f) + 35.0f), y);
            text8.setVisible(true);
            tiledSprite4.setVisible(true);
            switch (AnonymousClass1.f1373a[this.planet.getTerraformedClimate().ordinal()]) {
                case 1:
                    tiledSprite4.setCurrentTileIndex(Resources.get(ResourceID.BIO_TOXIN).getImageIndex());
                    break;
                case 2:
                    tiledSprite4.setCurrentTileIndex(Resources.get(ResourceID.ACID).getImageIndex());
                    break;
                case 3:
                    tiledSprite4.setCurrentTileIndex(Resources.get(ResourceID.WHISKEY).getImageIndex());
                    break;
                case 4:
                    tiledSprite4.setCurrentTileIndex(Resources.get(ResourceID.METALLIC_METHANE).getImageIndex());
                    break;
                case 5:
                    tiledSprite4.setCurrentTileIndex(Resources.get(ResourceID.EXOTIC_WOOD).getImageIndex());
                    break;
                case 6:
                case 7:
                    tiledSprite4.setCurrentTileIndex(Resources.get(ResourceID.RESORT).getImageIndex());
                    break;
            }
            tiledSprite4.setPosition(text8.getX() + text8.getWidthScaled() + 5.0f, y - 5.0f);
        }
        messageOverlay.setMessageType(MessageType.TERRAFORMING);
    }
}
