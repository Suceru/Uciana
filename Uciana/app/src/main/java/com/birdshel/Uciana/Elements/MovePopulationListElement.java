package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MovePopulationListElement extends Entity {
    private final Text costText;
    private final Sprite emptyPopulationBar;
    private final Game game;
    private final PlanetInfo planetInfo;
    private final Text planetName;
    private PlanetSprite planetSprite;
    private final Text populationString;
    private final TiledSprite[] resources = new TiledSprite[4];
    private final float screenWidth;
    private final TiledSprite turnIcon;
    private final Text turnsToText;
    private final TiledSprite warning;

    public MovePopulationListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager, float f2) {
        this.game = game;
        this.screenWidth = f2;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        sprite.setAlpha(0.8f);
        sprite.setSize(f2 - 10.0f, 100.0f);
        attachChild(sprite);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.warning = tiledSprite;
        tiledSprite.setCurrentTileIndex(InfoIconEnum.BLOCKADE.ordinal());
        tiledSprite.setZIndex(2);
        blinkSprite(tiledSprite);
        attachChild(tiledSprite);
        Text text = new Text(145.0f, 10.0f, game.fonts.smallFont, wrap, textOptions, vertexBufferObjectManager);
        this.planetName = text;
        text.setZIndex(2);
        attachChild(text);
        PlanetInfo planetInfo = new PlanetInfo(game, vertexBufferObjectManager);
        this.planetInfo = planetInfo;
        planetInfo.setPosition(135.0f, 40.0f);
        attachChild(planetInfo);
        Sprite sprite2 = new Sprite(390.0f, 10.0f, game.graphics.popTexture, vertexBufferObjectManager);
        sprite2.setSize(400.0f, 80.0f);
        attachChild(sprite2);
        Sprite sprite3 = new Sprite(0.0f, 25.0f, game.graphics.popEmptyTexture, vertexBufferObjectManager);
        this.emptyPopulationBar = sprite3;
        attachChild(sprite3);
        Text text2 = new Text(0.0f, 40.0f, game.fonts.smallFont, wrap, textOptions, vertexBufferObjectManager);
        this.populationString = text2;
        attachChild(text2);
        for (int i = 0; i < 4; i++) {
            this.resources[i] = new TiledSprite(0.0f, 70.0f, game.graphics.resourceIconsTexture, vertexBufferObjectManager);
            this.resources[i].setScaleCenter(0.0f, 0.0f);
            this.resources[i].setSize(30.0f, 30.0f);
            this.resources[i].setZIndex(2);
            attachChild(this.resources[i]);
        }
        Text text3 = new Text(850.0f, 35.0f, game.fonts.infoFont, wrap, textOptions, vertexBufferObjectManager);
        this.turnsToText = text3;
        attachChild(text3);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 32.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.turnIcon = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.TURN.ordinal());
        attachChild(tiledSprite2);
        Text text4 = new Text(0.0f, 35.0f, game.fonts.infoFont, "##########", textOptions, vertexBufferObjectManager);
        this.costText = text4;
        attachChild(text4);
        float f3 = f2 - 180.0f;
        TiledSprite tiledSprite3 = new TiledSprite(f3, 32.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
        attachChild(tiledSprite3);
        Font font = game.fonts.smallFont;
        Text text5 = new Text(f3 + 40.0f, 32.0f, font, game.getActivity().getString(R.string.move_population_per) + " m", textOptions, vertexBufferObjectManager);
        attachChild(text5);
        TiledSprite tiledSprite4 = new TiledSprite(text5.getX() + text5.getWidthScaled() + 3.0f, 32.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.POPULATION.ordinal());
        attachChild(tiledSprite4);
    }

    private void blinkSprite(Sprite sprite) {
        sprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        sprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.4f, 0.25f, 1.0f), new AlphaModifier(0.4f, 1.0f, 0.25f))));
    }

    public void initialSet() {
        PlanetSprite planetSprite = this.game.planetSpritePool.get();
        this.planetSprite = planetSprite;
        planetSprite.setMoonRange(100, 100);
        this.planetSprite.setPosition(60.0f, 48.0f);
        this.planetSprite.setZIndex(1);
        attachChild(this.planetSprite);
        sortChildren();
    }

    public void releasePoolElements() {
        detachChild(this.planetSprite);
        this.game.planetSpritePool.push(this.planetSprite);
    }

    public void set(Colony colony) {
        Planet planet = colony.getPlanet();
        this.planetSprite.setPlanet(planet, planet.getScale(this.game.coloniesScene), Moon.getScale(this.game.coloniesScene));
        this.planetSprite.getMoonSprite().setPosition(15.0f, -5.0f);
        this.warning.setVisible(colony.isBlockaded());
        this.planetName.setText(colony.getName());
        this.planetInfo.set(planet);
        float population = colony.getPopulation() / colony.getPlanet().getMaxPopulation();
        int i = 0;
        this.emptyPopulationBar.setVisible(false);
        if (population != 1.0f) {
            float f2 = population * 398.0f;
            this.emptyPopulationBar.setX(391.0f + f2);
            this.emptyPopulationBar.setSize(398.0f - f2, 47.0f);
            this.emptyPopulationBar.setVisible(true);
        }
        this.populationString.setText(this.game.getActivity().getString(R.string.system_colony_pop, new Object[]{Integer.valueOf(colony.getPopulation()), Integer.valueOf(colony.getPlanet().getMaxPopulation())}));
        this.populationString.setX(590.0f - (this.populationString.getWidth() / 2.0f));
        for (int i2 = 0; i2 < 4; i2++) {
            this.resources[i2].setVisible(false);
        }
        int size = colony.getResources().size() * 30;
        for (ResourceID resourceID : colony.getResources()) {
            this.resources[i].setVisible(true);
            this.resources[i].setCurrentTileIndex(Resources.get(resourceID).getImageIndex());
            this.resources[i].setX((60.0f - (size / 2.0f)) + (i * 30));
            i++;
        }
        int turnsToSystem = this.game.movePopulationScene.getTurnsToSystem(colony.getSystemID());
        this.turnsToText.setText(Integer.toString(turnsToSystem));
        this.turnIcon.setX(this.turnsToText.getX() + this.turnsToText.getWidthScaled() + 8.0f);
        this.costText.setText(Integer.toString(turnsToSystem * 1));
        Text text = this.costText;
        text.setX(((this.screenWidth - 180.0f) - text.getWidthScaled()) - 5.0f);
    }
}
