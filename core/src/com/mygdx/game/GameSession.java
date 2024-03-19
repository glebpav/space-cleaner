package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {

    private GameScreenState state;

    int score;
    int kill;

    long timeOfTrashAppearance;

    public GameScreenState getState() {
        return state;
    }

    public void beginSession() {
        score = 0;
        kill = 0;
        state = GameScreenState.PLAYING;
        timeOfTrashAppearance = TimeUtils.millis();
    }

    public void pauseSession() {
        state = GameScreenState.PAUSED;
    }

    public void resumeSession() {
        state = GameScreenState.PLAYING;
    }

    public boolean hasToSpawnTrash() {
        if (TimeUtils.millis() - timeOfTrashAppearance >= GameSettings.TRASH_APPEARANCE_PERIOD) {
            timeOfTrashAppearance = TimeUtils.millis();
            return true;
        }
        return false;
    }


}
