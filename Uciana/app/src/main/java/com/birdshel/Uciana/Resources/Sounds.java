package com.birdshel.Uciana.Resources;

import android.os.Environment;

import androidx.core.content.ContextCompat;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Uciana;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;

import java.io.File;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Sounds implements AudioControl {
    private Sound armorHit;
    private Sound beepSound;
    private Sound bombExplosionSound;
    private Sound boxPressSound;
    private Sound explosionSound;
    private Sound fleetPressSound;
    private final Sound[] infantryKilledSounds;
    private Sound selectPressSound;
    private Sound shieldHit;
    private Sound shipMoveSound;
    private Sound shockwaveSound;
    private Sound starPressSound;
    private Sound warpSound;
    private final Sound[] weaponsSounds;

    public Sounds(Uciana uciana) {
        Sound[] soundArr = new Sound[11];
        this.weaponsSounds = soundArr;
        Sound[] soundArr2 = new Sound[3];
        this.infantryKilledSounds = soundArr2;
        SoundFactory.setAssetBasePath("Sounds/");
        try {
            this.beepSound = setSound(uciana, "Beep.mp3");
            this.boxPressSound = setSound(uciana, "BoxPress.mp3");
            this.fleetPressSound = setSound(uciana, "FleetPress.mp3");
            this.starPressSound = setSound(uciana, "StarPress.mp3");
            this.explosionSound = setSound(uciana, "Blast.mp3");
            this.selectPressSound = setSound(uciana, "SelectPress.mp3");
            this.shipMoveSound = setSound(uciana, "ShipMoving.mp3");
            this.warpSound = setSound(uciana, "Warp.mp3");
            this.shockwaveSound = setSound(uciana, "Shockwave.mp3");
            this.shieldHit = setSound(uciana, "ShieldHit.mp3");
            this.armorHit = setSound(uciana, "ArmorHit.mp3");
            this.bombExplosionSound = setSound(uciana, "BombExplosion.mp3");
            soundArr[0] = setSound(uciana, "BeamWeapon1.mp3");
            soundArr[1] = setSound(uciana, "BeamWeapon2.mp3");
            soundArr[2] = setSound(uciana, "BeamWeapon3.mp3");
            soundArr[3] = setSound(uciana, "BeamWeapon4.mp3");
            soundArr[4] = setSound(uciana, "TorpedoWeapon1.mp3");
            soundArr[5] = setSound(uciana, "TorpedoWeapon2.mp3");
            soundArr[6] = setSound(uciana, "TorpedoWeapon3.mp3");
            soundArr[7] = setSound(uciana, "TorpedoWeapon4.mp3");
            soundArr[8] = setSound(uciana, "ChargeWeapon1.mp3");
            soundArr[9] = setSound(uciana, "ChargeWeapon2.mp3");
            soundArr[10] = setSound(uciana, "ChargeWeapon3.mp3");
            soundArr2[0] = this.armorHit;
            soundArr2[1] = this.bombExplosionSound;
            soundArr2[2] = this.explosionSound;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        setVolume(getVolume());
    }

    private void play(Sound sound) {
        if (Game.options.isOn(OptionID.SOUND, 1)) {
            sound.play();
        }
    }

    private Sound setSound(Uciana uciana, String str) {
        if (ContextCompat.checkSelfPermission(uciana, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            File file = new File(Environment.getExternalStorageDirectory() + "/uciana/mod");
            File file2 = new File(file.getAbsolutePath() + File.separator + str);
            if (file2.exists()) {
                return SoundFactory.createSoundFromFile(uciana.getEngine().getSoundManager(), file2);
            }
        }
        return SoundFactory.createSoundFromAsset(uciana.getEngine().getSoundManager(), uciana, str);
    }

    public float getVolume() {
        return Game.options.getOption(OptionID.SOUND_VOLUME, 0.7f);
    }

    public void playArmorHit() {
        play(this.armorHit);
    }

    public void playBombExplosionSound() {
        play(this.bombExplosionSound);
    }

    public void playBoxPressSound() {
        play(this.boxPressSound);
    }

    public void playButtonPressSound() {
        play(this.beepSound);
    }

    public void playExplosionSound() {
        play(this.explosionSound);
    }

    public void playFleetPressSound() {
        play(this.fleetPressSound);
    }

    public void playInfantryKilledSound() {
        Sound[] soundArr = this.infantryKilledSounds;
        play(soundArr[Functions.random.nextInt(soundArr.length)]);
    }

    public void playMoveFleetSound() {
        play(this.warpSound);
    }

    public void playSelectPressSound() {
        play(this.selectPressSound);
    }

    public void playShieldHit() {
        play(this.shieldHit);
    }

    public void playShipMoveSound() {
        play(this.shipMoveSound);
    }

    public void playShockwaveSound() {
        play(this.shockwaveSound);
    }

    public void playStarPressSound() {
        play(this.starPressSound);
    }

    public void playSystemObjectPressSound() {
        play(this.starPressSound);
    }

    public void playWeaponSound(int i) {
        play(this.weaponsSounds[i]);
    }

    @Override // com.birdshel.Uciana.Resources.AudioControl
    public void setVolume(float f2) {
        Game.options.setOption(OptionID.SOUND_VOLUME, f2);
        this.beepSound.setVolume(f2);
        this.boxPressSound.setVolume(f2);
        this.fleetPressSound.setVolume(f2);
        this.starPressSound.setVolume(f2);
        this.explosionSound.setVolume(f2);
        this.selectPressSound.setVolume(f2);
        this.shipMoveSound.setVolume(f2);
        this.warpSound.setVolume(f2);
        this.shockwaveSound.setVolume(f2);
        this.shieldHit.setVolume(f2);
        this.armorHit.setVolume(f2);
        this.bombExplosionSound.setVolume(f2);
        for (Sound sound : this.weaponsSounds) {
            sound.setVolume(f2);
        }
    }
}
