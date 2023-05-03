package com.birdshel.Uciana.Resources;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum SupportedLocales {
    ENGLISH("en"),
    GERMAN("de"),
    RUSSIAN("ru"),
    JAPANESE("ja"),
    SPANISH("es"),
    FRENCH("fr"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ITALIAN("it"),
    TURKISH("tr"),
    KOREAN("ko");
    
    private final String letters;

    SupportedLocales(String str) {
        this.letters = str;
    }

    public static boolean contains(String str) {
        for (SupportedLocales supportedLocales : values()) {
            if (str.equals(supportedLocales.letters)) {
                return true;
            }
        }
        return false;
    }

    public static SupportedLocales convertFromString(String str) {
        SupportedLocales[] values;
        for (SupportedLocales supportedLocales : values()) {
            if (str.equals(supportedLocales.letters)) {
                return supportedLocales;
            }
        }
        return ENGLISH;
    }

    public String getLetters() {
        return this.letters;
    }
}
