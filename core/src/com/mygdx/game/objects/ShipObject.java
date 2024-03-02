package com.mygdx.game.objects;

import com.mygdx.game.GameSettings;

public class ShipObject extends SpaceObject {

    public ShipObject(int x, int y) {
        super(x, y, GameSettings.SHIP_WIDTH, GameSettings.SHIP_HEIGHT, "textures/ship.png");
    }

    public void move(int tx, int ty) {
        tx -= x + width / 2;
        ty -= y + height / 2;

        velocityX = (int) (0.1 * tx);
        velocityY = (int) (0.1 * ty);

        x += velocityX;
        y += velocityY;
    }
}
