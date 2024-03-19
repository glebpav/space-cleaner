package com.mygdx.game.compnents;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class ButtonView {

    TextView textView;
    ImageView imageView;

    String text;

    public ButtonView(int x, int y, int width, int height, String texturePath, BitmapFont bitmapFont, String text) {

        imageView = new ImageView(x, y, width, height, texturePath);
        textView = new TextView(0, 0, bitmapFont);

        this.text = text;
        GlyphLayout glyphLayout = new GlyphLayout(bitmapFont, text);
        textView.height = (int) glyphLayout.height;
        textView.width = (int) glyphLayout.width;

        textView.x = imageView.x + imageView.width / 2 - textView.width / 2;
        textView.y = imageView.y + imageView.height / 2 - textView.height / 2;
    }

    public ButtonView(int x, int y, int width, int height, String texturePath) {
        imageView = new ImageView(x, y, width, height, texturePath);
        textView = null;
    }

    public void draw(SpriteBatch batch) {
        imageView.draw(batch);

        if (textView != null) textView.draw(batch, text);
    }

    public void dispose() {
        imageView.dispose();
        textView.dispose();
    }

    public boolean isHit(float tx, float ty) {
        return (tx > imageView.x && tx < imageView.x + imageView.width
                && ty > imageView.y && ty < imageView.y + imageView.height);
    }

}
