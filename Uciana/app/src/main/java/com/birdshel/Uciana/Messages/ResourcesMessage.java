package com.birdshel.Uciana.Messages;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Resource;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import java.util.Iterator;
import java.util.List;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ResourcesMessage implements Message {
    private final List<ResourceID> resources;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Messages.ResourcesMessage$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1371a;

        static {
            int[] iArr = new int[ResourceID.values().length];
            f1371a = iArr;
            try {
                iArr[ResourceID.WHEAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1371a[ResourceID.SILVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1371a[ResourceID.GOLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1371a[ResourceID.PLATINUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1371a[ResourceID.CRYSTALS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1371a[ResourceID.NATIVES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1371a[ResourceID.BIO_TOXIN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1371a[ResourceID.ACID.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1371a[ResourceID.COZIURIUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1371a[ResourceID.ADVANCED_RUINS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1371a[ResourceID.WHISKEY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1371a[ResourceID.METALLIC_METHANE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1371a[ResourceID.RESORT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1371a[ResourceID.EXOTIC_WOOD.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f1371a[ResourceID.ANTIMATTER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f1371a[ResourceID.LESCITE.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public ResourcesMessage(List<ResourceID> list) {
        this.resources = list;
    }

    @Override // com.birdshel.Uciana.Messages.Message
    public void set(MessageOverlay messageOverlay) {
        Iterator<ResourceID> it;
        Game game = messageOverlay.getGame();
        VertexBufferObjectManager buffer = messageOverlay.getBuffer();
        int width = ((int) messageOverlay.getWidth()) / (this.resources.size() + 1);
        Iterator<ResourceID> it2 = this.resources.iterator();
        int i = width;
        while (it2.hasNext()) {
            ResourceID next = it2.next();
            Resource resource = Resources.get(next);
            TiledSprite tiledSprite = new TiledSprite(i - 25, 270.0f, game.graphics.resourceIconsTexture, buffer);
            tiledSprite.setSize(50.0f, 50.0f);
            tiledSprite.setCurrentTileIndex(resource.getImageIndex());
            messageOverlay.addElement(tiledSprite);
            Font font = game.fonts.infoFont;
            String name = resource.getName();
            HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
            Text text = new Text(0.0f, 0.0f, font, name, new TextOptions(AutoWrap.WORDS, width - 10, horizontalAlign), buffer);
            float widthScaled = text.getWidthScaled();
            float f2 = i;
            text.setX(f2 - (widthScaled / 2.0f));
            text.setY(360.0f - (text.getHeightScaled() / 2.0f));
            messageOverlay.addElement(text);
            switch (AnonymousClass1.f1371a[next.ordinal()]) {
                case 1:
                    it = it2;
                    Text text2 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+1", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text2);
                    TiledSprite tiledSprite2 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite2.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
                    messageOverlay.addElement(tiledSprite2);
                    Text text3 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_per_farmer), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text3);
                    float widthScaled2 = f2 - (((((text2.getWidthScaled() + 5.0f) + tiledSprite2.getWidthScaled()) + 5.0f) + text3.getWidthScaled()) / 2.0f);
                    text2.setX(widthScaled2);
                    float widthScaled3 = widthScaled2 + text2.getWidthScaled() + 5.0f;
                    tiledSprite2.setX(widthScaled3);
                    text3.setX(widthScaled3 + tiledSprite2.getWidthScaled() + 5.0f);
                    continue;
                    i += width;
                    it2 = it;
                case 2:
                    it = it2;
                    Text text4 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+5", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text4);
                    TiledSprite tiledSprite3 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite3.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
                    messageOverlay.addElement(tiledSprite3);
                    float widthScaled4 = f2 - (((text4.getWidthScaled() + 5.0f) + tiledSprite3.getWidthScaled()) / 2.0f);
                    text4.setX(widthScaled4);
                    tiledSprite3.setX(widthScaled4 + text4.getWidthScaled() + 5.0f);
                    continue;
                    i += width;
                    it2 = it;
                case 3:
                    it = it2;
                    Text text5 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+10", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text5);
                    TiledSprite tiledSprite4 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite4.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
                    messageOverlay.addElement(tiledSprite4);
                    float widthScaled5 = f2 - (((text5.getWidthScaled() + 5.0f) + tiledSprite4.getWidthScaled()) / 2.0f);
                    text5.setX(widthScaled5);
                    tiledSprite4.setX(widthScaled5 + text5.getWidthScaled() + 5.0f);
                    continue;
                    i += width;
                    it2 = it;
                case 4:
                    it = it2;
                    Text text6 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+15", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text6);
                    TiledSprite tiledSprite5 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite5.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
                    messageOverlay.addElement(tiledSprite5);
                    float widthScaled6 = f2 - (((text6.getWidthScaled() + 5.0f) + tiledSprite5.getWidthScaled()) / 2.0f);
                    text6.setX(widthScaled6);
                    tiledSprite5.setX(widthScaled6 + text6.getWidthScaled() + 5.0f);
                    continue;
                    i += width;
                    it2 = it;
                case 5:
                    it = it2;
                    Text text7 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+10%", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text7);
                    TiledSprite tiledSprite6 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite6.setCurrentTileIndex(InfoIconEnum.HAPPINESS.ordinal());
                    messageOverlay.addElement(tiledSprite6);
                    float widthScaled7 = f2 - (((text7.getWidthScaled() + 5.0f) + tiledSprite6.getWidthScaled()) / 2.0f);
                    text7.setX(widthScaled7);
                    tiledSprite6.setX(widthScaled7 + text7.getWidthScaled() + 5.0f);
                    continue;
                    i += width;
                    it2 = it;
                case 6:
                    it = it2;
                    Text text8 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+50", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text8);
                    TiledSprite tiledSprite7 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite7.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
                    messageOverlay.addElement(tiledSprite7);
                    Text text9 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+50", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text9);
                    TiledSprite tiledSprite8 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite8.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
                    messageOverlay.addElement(tiledSprite8);
                    float widthScaled8 = f2 - (((((((text8.getWidthScaled() + 5.0f) + tiledSprite7.getWidthScaled()) + 20.0f) + text9.getWidthScaled()) + 5.0f) + tiledSprite8.getWidthScaled()) / 2.0f);
                    text8.setX(widthScaled8);
                    float widthScaled9 = widthScaled8 + text8.getWidthScaled() + 5.0f;
                    tiledSprite7.setX(widthScaled9);
                    float widthScaled10 = widthScaled9 + tiledSprite7.getWidthScaled() + 20.0f;
                    text9.setX(widthScaled10);
                    tiledSprite8.setX(widthScaled10 + text9.getWidthScaled() + 5.0f);
                    continue;
                    i += width;
                    it2 = it;
                case 7:
                    Text text10 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+1", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text10);
                    TiledSprite tiledSprite9 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite9.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
                    messageOverlay.addElement(tiledSprite9);
                    it = it2;
                    Text text11 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_per_scientist), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text11);
                    Text text12 = new Text(0.0f, 440.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_bio_bomb, new Object[]{25}), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text12);
                    TiledSprite tiledSprite10 = new TiledSprite(0.0f, 425.0f, game.graphics.shipComponentIconsTexture, buffer);
                    tiledSprite10.setCurrentTileIndex(ShipComponents.get(ShipComponentID.BIO_BOMB).getIconIndex());
                    tiledSprite9.setSize(30.0f, 30.0f);
                    messageOverlay.addElement(tiledSprite10);
                    float widthScaled11 = f2 - (((((text10.getWidthScaled() + 5.0f) + tiledSprite9.getWidthScaled()) + 5.0f) + text11.getWidthScaled()) / 2.0f);
                    text10.setX(widthScaled11);
                    float widthScaled12 = widthScaled11 + text10.getWidthScaled() + 5.0f;
                    tiledSprite9.setX(widthScaled12);
                    text11.setX(widthScaled12 + tiledSprite9.getWidthScaled() + 5.0f);
                    float widthScaled13 = f2 - (((text12.getWidthScaled() + 5.0f) + tiledSprite10.getWidthScaled()) / 2.0f);
                    text12.setX(widthScaled13);
                    tiledSprite10.setX(widthScaled13 + text12.getWidthScaled() + 5.0f);
                    continue;
                    i += width;
                    it2 = it;
                case 8:
                    Text text13 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+1", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text13);
                    TiledSprite tiledSprite11 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite11.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
                    messageOverlay.addElement(tiledSprite11);
                    Text text14 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_per_worker_empire), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text14);
                    float widthScaled14 = f2 - (((((text13.getWidthScaled() + 5.0f) + tiledSprite11.getWidthScaled()) + 5.0f) + text14.getWidthScaled()) / 2.0f);
                    text13.setX(widthScaled14);
                    float widthScaled15 = widthScaled14 + text13.getWidthScaled() + 5.0f;
                    tiledSprite11.setX(widthScaled15);
                    text14.setX(widthScaled15 + tiledSprite11.getWidthScaled() + 5.0f);
                    break;
                case 9:
                    Text text15 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+1", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text15);
                    TiledSprite tiledSprite12 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite12.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
                    messageOverlay.addElement(tiledSprite12);
                    Text text16 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_per_worker), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text16);
                    float widthScaled16 = f2 - (((((text15.getWidthScaled() + 5.0f) + tiledSprite12.getWidthScaled()) + 5.0f) + text16.getWidthScaled()) / 2.0f);
                    text15.setX(widthScaled16);
                    float widthScaled17 = widthScaled16 + text15.getWidthScaled() + 5.0f;
                    tiledSprite12.setX(widthScaled17);
                    text16.setX(widthScaled17 + tiledSprite12.getWidthScaled() + 5.0f);
                    break;
                case 10:
                    Text text17 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+1", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text17);
                    TiledSprite tiledSprite13 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite13.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
                    messageOverlay.addElement(tiledSprite13);
                    Text text18 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_per_scientist), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text18);
                    float widthScaled18 = f2 - (((((text17.getWidthScaled() + 5.0f) + tiledSprite13.getWidthScaled()) + 5.0f) + text18.getWidthScaled()) / 2.0f);
                    text17.setX(widthScaled18);
                    float widthScaled19 = widthScaled18 + text17.getWidthScaled() + 5.0f;
                    tiledSprite13.setX(widthScaled19);
                    text18.setX(widthScaled19 + tiledSprite13.getWidthScaled() + 5.0f);
                    break;
                case 11:
                    Text text19 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+5%", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text19);
                    TiledSprite tiledSprite14 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite14.setCurrentTileIndex(InfoIconEnum.HAPPINESS.ordinal());
                    messageOverlay.addElement(tiledSprite14);
                    Text text20 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_empire_wide), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text20);
                    float widthScaled20 = f2 - (((((text19.getWidthScaled() + 5.0f) + tiledSprite14.getWidthScaled()) + 5.0f) + text20.getWidthScaled()) / 2.0f);
                    text19.setX(widthScaled20);
                    float widthScaled21 = widthScaled20 + text19.getWidthScaled() + 5.0f;
                    tiledSprite14.setX(widthScaled21);
                    text20.setX(widthScaled21 + tiledSprite14.getWidthScaled() + 5.0f);
                    break;
                case 12:
                    Text text21 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+5", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text21);
                    TiledSprite tiledSprite15 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite15.setCurrentTileIndex(InfoIconEnum.POWER.ordinal());
                    messageOverlay.addElement(tiledSprite15);
                    Text text22 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_empire_wide), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text22);
                    float widthScaled22 = f2 - (((((text21.getWidthScaled() + 5.0f) + tiledSprite15.getWidthScaled()) + 5.0f) + text22.getWidthScaled()) / 2.0f);
                    text21.setX(widthScaled22);
                    float widthScaled23 = widthScaled22 + text21.getWidthScaled() + 5.0f;
                    tiledSprite15.setX(widthScaled23);
                    text22.setX(widthScaled23 + tiledSprite15.getWidthScaled() + 5.0f);
                    break;
                case 13:
                    Text text23 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+10%", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text23);
                    TiledSprite tiledSprite16 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite16.setCurrentTileIndex(InfoIconEnum.HAPPINESS.ordinal());
                    messageOverlay.addElement(tiledSprite16);
                    Text text24 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+5%", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text24);
                    TiledSprite tiledSprite17 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite17.setCurrentTileIndex(InfoIconEnum.CREDITS.ordinal());
                    messageOverlay.addElement(tiledSprite17);
                    float widthScaled24 = f2 - (((((((text23.getWidthScaled() + 5.0f) + tiledSprite16.getWidthScaled()) + 20.0f) + text24.getWidthScaled()) + 5.0f) + tiledSprite17.getWidthScaled()) / 2.0f);
                    text23.setX(widthScaled24);
                    float widthScaled25 = widthScaled24 + text23.getWidthScaled() + 5.0f;
                    tiledSprite16.setX(widthScaled25);
                    float widthScaled26 = widthScaled25 + tiledSprite16.getWidthScaled() + 20.0f;
                    text24.setX(widthScaled26);
                    tiledSprite17.setX(widthScaled26 + text24.getWidthScaled() + 5.0f);
                    break;
                case 14:
                    Text text25 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+5%", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text25);
                    TiledSprite tiledSprite18 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite18.setCurrentTileIndex(InfoIconEnum.HAPPINESS.ordinal());
                    messageOverlay.addElement(tiledSprite18);
                    Text text26 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_empire_wide), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text26);
                    float widthScaled27 = f2 - (((((text25.getWidthScaled() + 5.0f) + tiledSprite18.getWidthScaled()) + 5.0f) + text26.getWidthScaled()) / 2.0f);
                    text25.setX(widthScaled27);
                    float widthScaled28 = widthScaled27 + text25.getWidthScaled() + 5.0f;
                    tiledSprite18.setX(widthScaled28);
                    text26.setX(widthScaled28 + tiledSprite18.getWidthScaled() + 5.0f);
                    break;
                case 15:
                    Text text27 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+10", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text27);
                    TiledSprite tiledSprite19 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite19.setCurrentTileIndex(InfoIconEnum.POWER.ordinal());
                    messageOverlay.addElement(tiledSprite19);
                    float widthScaled29 = f2 - (((text27.getWidthScaled() + 5.0f) + tiledSprite19.getWidthScaled()) / 2.0f);
                    text27.setX(widthScaled29);
                    tiledSprite19.setX(widthScaled29 + text27.getWidthScaled() + 5.0f);
                    break;
                case 16:
                    Text text28 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, "+1", new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text28);
                    TiledSprite tiledSprite20 = new TiledSprite(0.0f, 390.0f, game.graphics.infoIconsTexture, buffer);
                    tiledSprite20.setCurrentTileIndex(InfoIconEnum.FOOD.ordinal());
                    messageOverlay.addElement(tiledSprite20);
                    Text text29 = new Text(0.0f, 400.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.resources_per_farmer), new TextOptions(horizontalAlign), buffer);
                    messageOverlay.addElement(text29);
                    float widthScaled30 = f2 - (((((text28.getWidthScaled() + 5.0f) + tiledSprite20.getWidthScaled()) + 5.0f) + text29.getWidthScaled()) / 2.0f);
                    text28.setX(widthScaled30);
                    float widthScaled31 = widthScaled30 + text28.getWidthScaled() + 5.0f;
                    tiledSprite20.setX(widthScaled31);
                    text29.setX(widthScaled31 + tiledSprite20.getWidthScaled() + 5.0f);
                    break;
            }
            it = it2;
            i += width;
            it2 = it;
        }
    }
}
