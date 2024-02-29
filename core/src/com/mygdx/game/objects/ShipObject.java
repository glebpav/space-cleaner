package com.mygdx.game.objects;

import com.mygdx.game.GameSettings;

public class ShipObject extends SpaceObject {

    public ShipObject(int x, int y) {
        super(x, y, GameSettings.SHIP_WIDTH, GameSettings.SHIP_HEIGHT, "textures/ship.png");
    }

    public void move(int tx, int ty) {
        tx -= x + width / 2;
        ty -= y + height / 2;

        double sqrtT = Math.sqrt(tx * tx + ty * ty);
        velocityX = (int) (5 * tx / sqrtT);
        velocityY = (int) (5 * ty / sqrtT);

        System.out.println("sqrt - " + sqrtT);
        System.out.println("vx - " + tx);
        System.out.println("vy - " + ty);

        x += velocityX;
        y += velocityY;

    }
}
