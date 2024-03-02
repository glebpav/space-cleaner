package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {

    int score;
    int kill;

    long timeOfTrashAppearance;

    public void beginSession() {
        score = 0;
        kill = 0;
        timeOfTrashAppearance = TimeUtils.millis();
    }

    public boolean hasToSpawnTrash() {
        if (TimeUtils.millis() - timeOfTrashAppearance >= GameSettings.TRASH_APPEARANCE_PERIOD) {
            timeOfTrashAppearance = TimeUtils.millis();
            return true;
        }
        return false;
    }


}
