package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {

    public Sound explosionSound;
    public Sound shootSound;
    public Music backgoundMusic;

    public AudioManager() {
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/destroy.mp3"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.mp3"));
        backgoundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/background_music.mp3"));
    }

    public void dispose() {
        explosionSound.dispose();
        shootSound.dispose();
        backgoundMusic.dispose();
    }

}
