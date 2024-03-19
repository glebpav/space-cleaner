package com.mygdx.game.objects;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.GameSettings;


public class ShipObject extends SpaceObject {

    int lifeLeft;
    long lastShootTime;

    public ShipObject(int x, int y) {
        super(x, y, GameSettings.SHIP_WIDTH, GameSettings.SHIP_HEIGHT, "textures/ship.png");
        lastShootTime = TimeUtils.millis();
        lifeLeft = 3;
    }

    public void move(int tx, int ty) {
        tx -= x + width / 2;
        ty -= y + height / 2;

        velocityX = (int) (0.1 * tx);
        velocityY = (int) (0.1 * ty);

        x += velocityX;
        y += velocityY;
    }

    public void getDamage(int damage) {
        lifeLeft -= damage;
    }

    public boolean isAlive() {
        return lifeLeft > 0;
    }

    public int getLifeLeft() {
        return lifeLeft;
    }

    public boolean hasToShoot() {
        if (TimeUtils.millis() - lastShootTime >= GameSettings.SHIP_SHOOTING_COOL_DOWN) {
            lastShootTime = TimeUtils.millis();
            return true;
        }
        return false;
    }
}
