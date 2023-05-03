package com.birdshel.Uciana.Messages.PlanetInfo;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import java.text.DecimalFormat;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ClimateInfoMessage implements Message {
    private final Climate climate;
    private final boolean hasTerraformedClimate;

    public ClimateInfoMessage(Climate climate, boolean z) {
        this.climate = climate;
        this.hasTerraformedClimate = z;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        String str;
        String str2;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        DecimalFormat decimalFormat = new DecimalFormat("##.#");
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 25.0f, 270.0f, game.graphics.planetInfoTexture, buffer);
        tiledSprite.setCurrentTileIndex(this.climate.getInfoIconIndex());
        tiledSprite.setSize(50.0f, 50.0f);
        messageOverlay.addElement(tiledSprite);
        if (this.hasTerraformedClimate) {
            TiledSprite tiledSprite2 = new TiledSprite(messageOverlay.getWidth() / 2.0f, 302.0f, game.graphics.infoIconsTexture, buffer);
            tiledSprite2.setCurrentTileIndex(InfoIconEnum.ADJUSTED.ordinal());
            tiledSprite2.setScaleCenter(0.0f, 0.0f);
            tiledSprite2.setScale(0.9f);
            messageOverlay.addElement(tiledSprite2);
        }
        Font font = game.fonts.infoFont;
        String string = game.getActivity().getString(R.string.climate_info_header, new Object[]{this.climate.getDisplayName()});
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, string, new TextOptions(horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        if (this.climate.getPopulationLimitModifier() >= 0.9d) {
            str = "" + game.getActivity().getString(R.string.climate_info_pop_very_high);
        } else if (this.climate.getPopulationLimitModifier() >= 0.65d) {
            str = "" + game.getActivity().getString(R.string.climate_info_pop_high);
        } else if (this.climate.getPopulationLimitModifier() >= 0.46d) {
            str = "" + game.getActivity().getString(R.string.climate_info_pop_moderate);
        } else if (this.climate.getPopulationLimitModifier() >= 0.26d) {
            str = "" + game.getActivity().getString(R.string.climate_info_pop_low);
        } else {
            str = "" + game.getActivity().getString(R.string.climate_info_pop_very_low);
        }
        if (this.climate.getFoodPerFarmer() == 0.0f) {
            str2 = str + game.getActivity().getString(R.string.climate_info_unable_to_farm);
        } else {
            str2 = str + game.getActivity().getString(R.string.climate_info_food_per_farmer, new Object[]{decimalFormat.format(this.climate.getFoodPerFarmer())});
        }
        if (this.climate.getResearchBonus() != 0) {
            str2 = str2 + game.getActivity().getString(R.string.climate_info_science_per_scientist, new Object[]{Integer.valueOf(this.climate.getResearchBonus())});
        }
        int round = Math.round(this.climate.getPopulationGrowthModifier() * 100.0f);
        String str3 = str2 + game.getActivity().getString(R.string.climate_info_pop_growth, new Object[]{Integer.valueOf(round)});
        if (this.climate.getMaintenanceCostModifier() > 1.0f) {
            int round2 = Math.round((this.climate.getMaintenanceCostModifier() - 1.0f) * 100.0f);
            str3 = str3 + game.getActivity().getString(R.string.climate_info_maintenance, new Object[]{Integer.valueOf(round2)});
        }
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, str3 + game.getActivity().getString(R.string.climate_info_defense, new Object[]{Integer.valueOf(this.climate.getDefenceBonus())}), new TextOptions(horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
