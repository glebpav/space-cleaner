package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceObject {

    int x;
    int y;
    int width;
    int height;
    float velocityX;
    float velocityY;

    Texture texture;

    public SpaceObject(int x, int y, int width, int height, String texturePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        velocityX = 0;
        velocityY = 0;

        texture = new Texture(texturePath);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }
}
