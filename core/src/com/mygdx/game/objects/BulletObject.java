package com.mygdx.game.objects;

import com.mygdx.game.GameSettings;

public class BulletObject extends SpaceObject {

    public BulletObject(int x, int y) {
        super(x, y, 15, 50, "textures/bullet.png");

        velocityY = GameSettings.BULLET_VELOCITY;

    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }
}
