package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.OptionID;

import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetDiscoveryMessage implements Message {
    private final boolean discovered;
    private final Planet planet;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Messages.PlanetDiscoveryMessage$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1369a;

        static {
            int[] iArr = new int[Climate.values().length];
            f1369a = iArr;
            try {
                iArr[Climate.RED_HOMEWORLD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1369a[Climate.GREEN_HOMEWORLD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1369a[Climate.BLUE_HOMEWORLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1369a[Climate.ORANGE_HOMEWORLD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1369a[Climate.YELLOW_HOMEWORLD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1369a[Climate.PURPLE_HOMEWORLD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1369a[Climate.CYAN_HOMEWORLD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public PlanetDiscoveryMessage(Planet planet, boolean z) {
        this.planet = planet;
        this.discovered = z;
    }

    private int getEmpireIDFromHomeworld(Climate climate) {
        switch (AnonymousClass1.f1369a[climate.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            default:
                return -1;
        }
    }

    private String getHeader(Game game, Climate climate) {
        String string = game.getActivity().getString(R.string.planet_discovery_unique_world);
        String string2 = game.getActivity().getString(R.string.planet_discovery_homeworld);
        if (this.discovered) {
            string = game.getActivity().getString(R.string.planet_discovery_unique_world_found);
            string2 = game.getActivity().getString(R.string.planet_discovery_homeworld_found);
        }
        int empireIDFromHomeworld = getEmpireIDFromHomeworld(climate);
        return (empireIDFromHomeworld == -1 || game.empires.get(empireIDFromHomeworld).getType() == EmpireType.NONE) ? string : string2;
    }

    private String getMessage(Climate climate) {
        int empireIDFromHomeworld;
        String description = this.planet.getClimate().getDescription();
        return (!Game.options.isOn(OptionID.MODDING) || (empireIDFromHomeworld = getEmpireIDFromHomeworld(climate)) == -1 || Game.modValues.getHomeWorldDescription(empireIDFromHomeworld) == null) ? description : Game.modValues.getHomeWorldDescription(empireIDFromHomeworld);
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        PlanetSprite planetSprite = new PlanetSprite(game, buffer, 110, 110);
        planetSprite.setPlanet(this.planet, 0.44f, 0.35f, false);
        planetSprite.setPosition(messageOverlay.getWidth() / 2.0f, 290.0f);
        messageOverlay.addElement(planetSprite);
        Font font = game.fonts.infoFont;
        String header = getHeader(game, this.planet.getClimate());
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, header, new TextOptions(horizontalAlign), buffer);
        text.setX((messageOverlay.getWidth() / 2.0f) - (text.getWidthScaled() / 2.0f));
        text.setY(360.0f - (text.getHeightScaled() / 2.0f));
        messageOverlay.addElement(text);
        Text text2 = new Text(0.0f, 390.0f, game.fonts.smallInfoFont, getMessage(this.planet.getClimate()), new TextOptions(AutoWrap.WORDS, 1200.0f, horizontalAlign), buffer);
        text2.setX((messageOverlay.getWidth() / 2.0f) - (text2.getWidthScaled() / 2.0f));
        messageOverlay.addElement(text2);
    }
}
