package com.mygdx.game.compnents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageView {

    int x;
    int y;

    int width;
    int height;

    Texture texture;

    public ImageView(int x, int y, String pathToImage) {
        this.x = x;
        this.y = y;
        texture = new Texture(pathToImage);
        width = texture.getWidth();
        height = texture.getHeight();
    }

    public ImageView(int x, int y, int width, int height, String pathToImage) {
        this(x, y, pathToImage);
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }

}
