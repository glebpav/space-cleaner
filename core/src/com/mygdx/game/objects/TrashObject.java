package com.mygdx.game.objects;

import com.mygdx.game.GameSettings;

import java.util.Random;

public class TrashObject extends SpaceObject {

    public int lifeLeft;

    public TrashObject() {
        super((new Random()).nextInt(GameSettings.SCREEN_WIDTH - GameSettings.TRASH_WIDTH),
                GameSettings.SCREEN_HEIGHT,
                GameSettings.TRASH_WIDTH,
                GameSettings.TRASH_HEIGHT,
                "textures/trash.png");
        velocityX = 0;
        lifeLeft = 1;
        velocityY = -GameSettings.TRASH_VELOCITY;
    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }

    public void getDamage(int damage) {
        lifeLeft -= damage;
    }

    public boolean isAlive() {
        return lifeLeft > 0;
    }

    public boolean isHit(SpaceObject bullet) {
        int x1 = bullet.x + bullet.width / 2;
        int y1 = bullet.y + bullet.height / 2;
        int x2 = x + width / 2;
        int y2 = y + height / 2;
        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return distance <= width / 2f + bullet.height / 2f;
    }

}
