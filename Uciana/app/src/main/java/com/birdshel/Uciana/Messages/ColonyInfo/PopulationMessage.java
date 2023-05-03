package com.birdshel.Uciana.Messages.ColonyInfo;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.Message;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import java.text.DecimalFormat;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PopulationMessage implements Message {
    private final Colony colony;

    public PopulationMessage(Colony colony) {
        this.colony = colony;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        TiledSprite tiledSprite = new TiledSprite((messageOverlay.getWidth() / 2.0f) - 25.0f, 270.0f, game.graphics.infoIconsTexture, buffer);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.POPULATION.ordinal());
        tiledSprite.setSize(50.0f, 50.0f);
        messageOverlay.addElement(tiledSprite);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String format = decimalFormat.format(this.colony.getRealPopulation());
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, game.fonts.infoFont, format + "k", new TextOptions(horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        int populationGrowth = this.colony.getPopulationGrowth();
        Object format2 = decimalFormat.format(populationGrowth);
        if (populationGrowth > 0) {
            format2 = "+" + populationGrowth;
        }
        Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.population_info_growth, new Object[]{format2}), new TextOptions(horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
