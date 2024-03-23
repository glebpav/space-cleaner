package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.compnents.ButtonView;
import com.mygdx.game.compnents.MovingBackgroundView;
import com.mygdx.game.compnents.TextView;

public class MenuScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;

    MovingBackgroundView backgroundView;
    TextView titleView;
    ButtonView startButtonView;
    ButtonView settingsButtonView;
    ButtonView exitButtonView;

    public MenuScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        backgroundView = new MovingBackgroundView("textures/background.png");
        titleView = new TextView(180, 955, myGdxGame.largeWhiteFont);
        startButtonView = new ButtonView(
                140, 645,
                440, 70,
                "textures/button_background_2.png",
                myGdxGame.commonBlackFont,
                "start"
        );
        settingsButtonView = new ButtonView(
                140, 550,
                440, 70,
                "textures/button_background_2.png",
                myGdxGame.commonBlackFont,
                "settings"
        );
        exitButtonView = new ButtonView(
                140, 450,
                440, 70,
                "textures/button_background_2.png",
                myGdxGame.commonBlackFont,
                "exit"
        );

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (exitButtonView.isHit(touch.x, touch.y)) {
                Gdx.app.exit();
            }
            if (settingsButtonView.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.settingsScreen);
            }
            if (startButtonView.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.gameScreen);
            }

        }

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.BLACK);

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        titleView.draw(myGdxGame.batch, "Space Cleaner");
        settingsButtonView.draw(myGdxGame.batch);
        startButtonView.draw(myGdxGame.batch);
        exitButtonView.draw(myGdxGame.batch);

        myGdxGame.batch.end();

    }

    @Override
    public void dispose() {
        backgroundView.dispose();
        titleView.dispose();
        settingsButtonView.dispose();
        startButtonView.dispose();
        exitButtonView.dispose();
    }
}



