package com.mygdx.game.objects;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.GameSession;
import com.mygdx.game.GameSettings;

import java.sql.Time;

public class ShipObject extends SpaceObject {

    long lastShootTime;

    public ShipObject(int x, int y) {
        super(x, y, GameSettings.SHIP_WIDTH, GameSettings.SHIP_HEIGHT, "textures/ship.png");
        lastShootTime = TimeUtils.millis();
    }

    public void move(int tx, int ty) {
        tx -= x + width / 2;
        ty -= y + height / 2;

        velocityX = (int) (0.1 * tx);
        velocityY = (int) (0.1 * ty);

        x += velocityX;
        y += velocityY;
    }

    public boolean hasToShoot() {
        if (TimeUtils.millis() - lastShootTime >= GameSettings.SHIP_SHOOTING_COOL_DOWN) {
            lastShootTime = TimeUtils.millis();
            return true;
        }
        return false;
    }
}
