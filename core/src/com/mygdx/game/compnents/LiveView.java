package com.mygdx.game.compnents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LiveView {

    private final int HEART_PADDING = 6;

    Texture texture;
    int x;
    int y;

    int liveLeft;

    public LiveView(int x, int y) {
        this.texture = new Texture("textures/life.png");
        this.x = x;
        this.y = y;

        liveLeft = 0;
    }

    public void setLiveLeft(int liveLeft) {
        this.liveLeft = liveLeft;
    }

    public void draw(SpriteBatch batch) {
        if (liveLeft > 0) batch.draw(texture, x + texture.getWidth() + HEART_PADDING, y);
        if (liveLeft > 1) batch.draw(texture, x, y);
        if (liveLeft > 2) batch.draw(texture, x + 2 * (texture.getWidth() + HEART_PADDING), y);
    }

    public void dispose() {
        texture.dispose();
    }
}
