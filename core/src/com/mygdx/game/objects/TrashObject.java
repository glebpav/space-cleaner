package com.mygdx.game.objects;

import com.mygdx.game.GameSettings;

import java.util.Random;

public class TrashObject extends SpaceObject{

    public TrashObject() {
        super((new Random()).nextInt(GameSettings.SCREEN_WIDTH - GameSettings.TRASH_WIDTH),
                GameSettings.SCREEN_HEIGHT,
                GameSettings.TRASH_WIDTH,
                GameSettings.TRASH_HEIGHT,
                "textures/trash.png");
        velocityX = 0;
        velocityY = -GameSettings.TRASH_VELOCITY;
    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }

}
