package com.mygdx.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.compnents.ButtonView;
import com.mygdx.game.compnents.ImageView;
import com.mygdx.game.compnents.MovingBackgroundView;
import com.mygdx.game.compnents.TextView;

public class SettingsScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;

    MovingBackgroundView backgroundView;
    TextView titleView;
    ImageView blackoutView;
    ButtonView buttonSoundView;
    ButtonView buttonMusicView;
    ButtonView buttonReturn;


    public SettingsScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        backgroundView = new MovingBackgroundView("textures/background.png");
        titleView = new TextView(256, 950, myGdxGame.largeWhiteFont);
        blackoutView = new ImageView(85, 365, "textures/blackout_settings.png");
        buttonMusicView = new ButtonView(173, 717, myGdxGame.commonWhiteFont, "music: " + "off");
        buttonSoundView = new ButtonView(173, 658, myGdxGame.commonWhiteFont, "sound: " + "off");
        buttonReturn = new ButtonView(
                280, 447,
                160, 70,
                "textures/button_background_1.png",
                myGdxGame.commonBlackFont,
                "return"
        );

    }

    @Override
    public void render(float delta) {

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.BLACK);

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        blackoutView.draw(myGdxGame.batch);
        titleView.draw(myGdxGame.batch, "Settings");
        buttonReturn.draw(myGdxGame.batch);
        buttonSoundView.draw(myGdxGame.batch);
        buttonMusicView.draw(myGdxGame.batch);

        myGdxGame.batch.end();

    }
}
