package com.birdshel.Uciana.Resources;

import android.os.Environment;
import androidx.core.content.ContextCompat;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Scenes.BattleScene;
import java.io.File;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameMusic implements AudioControl {
    private Music battleTheme;
    private final Game game;
    private Music mainTheme;

    public GameMusic(Game game) {
        this.game = game;
        MusicFactory.setAssetBasePath("Music/");
        try {
            this.mainTheme = setMusic("Main.mp3");
            this.battleTheme = setMusic("Battle.mp3");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Music setMusic(String str) {
        Music music;
        if (ContextCompat.checkSelfPermission(this.game.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            File file = new File(Environment.getExternalStorageDirectory() + "/uciana/mod");
            File file2 = new File(file.getAbsolutePath() + File.separator + str);
            if (file2.exists()) {
                music = MusicFactory.createMusicFromFile(this.game.getActivity().getEngine().getMusicManager(), file2);
                if (music == null) {
                    music = MusicFactory.createMusicFromAsset(this.game.getEngine().getMusicManager(), this.game.getActivity(), str);
                }
                music.setVolume(getVolume());
                music.setLooping(true);
                music.play();
                music.pause();
                return music;
            }
        }
        music = null;
        if (music == null) {
        }
        music.setVolume(getVolume());
        music.setLooping(true);
        music.play();
        music.pause();
        return music;
    }

    public float getVolume() {
        return Game.options.getOption(OptionID.MUSIC_VOLUME, 0.5f);
    }

    public void pause() {
        Music music = this.mainTheme;
        if (music != null && music.isPlaying()) {
            this.mainTheme.pause();
        }
        Music music2 = this.battleTheme;
        if (music2 == null || !music2.isPlaying()) {
            return;
        }
        this.battleTheme.pause();
    }

    public void resume() {
        if (!Game.options.isOn(OptionID.MUSIC, 1) || this.mainTheme == null || this.battleTheme == null) {
            return;
        }
        if (this.game.getCurrentScene() instanceof BattleScene) {
            resumeBattleTheme();
        } else {
            resumeMainTheme();
        }
    }

    public void resumeBattleTheme() {
        this.game.music.mainTheme.pause();
        this.game.music.battleTheme.resume();
    }

    public void resumeMainTheme() {
        this.game.music.battleTheme.pause();
        this.game.music.mainTheme.resume();
    }

    @Override // com.birdshel.Uciana.Resources.AudioControl
    public void setVolume(float f2) {
        this.mainTheme.setVolume(f2);
        this.battleTheme.setVolume(f2);
        Game.options.setOption(OptionID.MUSIC_VOLUME, f2);
    }
}
