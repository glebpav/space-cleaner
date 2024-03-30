package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameScreenState;
import com.mygdx.game.GameSession;
import com.mygdx.game.GameSettings;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.compnents.*;
import com.mygdx.game.objects.BulletObject;
import com.mygdx.game.objects.ShipObject;
import com.mygdx.game.objects.TrashObject;

import java.awt.event.TextEvent;
import java.util.ArrayList;

public class GameScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;
    GameSession session;

    ArrayList<TrashObject> trashArray;
    ArrayList<BulletObject> bulletArray;

    ShipObject ship;

    // playing state ui
    MovingBackgroundView backgroundView;
    ImageView topBlackOutView;
    TextView scoreCounterView;
    ButtonView pauseButton;
    LiveView liveView;

    // paused state ui
    ImageView blackoutView;
    TextView pauseTitleView;
    ButtonView homeButtonView;
    ButtonView resumeButtonView;


    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        session = new GameSession();
        trashArray = new ArrayList<>();
        bulletArray = new ArrayList<>();
        ship = new ShipObject(GameSettings.SCREEN_WIDTH / 2 - GameSettings.SHIP_WIDTH / 2, 100);

        backgroundView = new MovingBackgroundView("textures/background.png");
        topBlackOutView = new ImageView(0, 1180, "textures/top_blackout.png");
        scoreCounterView = new TextView(50, 1215, myGdxGame.commonWhiteFont);
        pauseButton = new ButtonView(605, 1200, 46, 54, "textures/pause_icon.png");
        liveView = new LiveView(305, 1215);

        pauseTitleView = new TextView(284, 842, myGdxGame.largeWhiteFont);
        blackoutView = new ImageView(
                0, 0,
                GameSettings.SCREEN_WIDTH,
                GameSettings.SCREEN_HEIGHT,
                "textures/blackout_full.png"
        );
        resumeButtonView = new ButtonView(
                185, 680,
                160, 70,
                "textures/button_background_1.png",
                myGdxGame.commonBlackFont,
                "Continue"
        );
        homeButtonView = new ButtonView(
                375, 680,
                160, 70,
                "textures/button_background_1.png",
                myGdxGame.commonBlackFont,
                "Home"
        );
    }

    @Override
    public void show() {
        session.beginSession();
        restartGame();
    }

    private void restartGame() {
        ship = new ShipObject(GameSettings.SCREEN_WIDTH / 2 - GameSettings.SHIP_WIDTH / 2, 100);
        trashArray.clear();
        bulletArray.clear();
        liveView.setLiveLeft(ship.getLifeLeft());
    }

    @Override
    public void render(float delta) {
        handleInput();

        if (session.getState() == GameScreenState.PLAYING) {

            if (session.hasToSpawnTrash()) {
                TrashObject trash = new TrashObject();
                trashArray.add(trash);
            }

            if (ship.hasToShoot()) {
                BulletObject bullet = new BulletObject(ship.getX() + ship.getWidth() / 2, ship.getY());
                bulletArray.add(bullet);
                if (myGdxGame.audioManager.isSoundOn) myGdxGame.audioManager.shootSound.play();
            }

        }

        for (int i = 0; i < trashArray.size(); i++) {

            for (int j = 0; j < bulletArray.size(); j++) {
                if (trashArray.get(i).isHit(bulletArray.get(j))) {

                    trashArray.get(i).getDamage(1);
                    if (!trashArray.get(i).isAlive()) {
                        trashArray.remove(i);
                        i--;
                    }

                    if (myGdxGame.audioManager.isSoundOn) myGdxGame.audioManager.explosionSound.play();
                    bulletArray.remove(j);
                    j--;
                    break;

                }
            }

            if (i >= 0 && trashArray.get(i).isHit(ship)) {
                ship.getDamage(1);
                liveView.setLiveLeft(ship.getLifeLeft());
                if (!ship.isAlive()) {
                    System.out.println("end of game");
                }

                trashArray.remove(i);
                i--;
            }
        }

        if (session.getState() == GameScreenState.PLAYING) {
            for (TrashObject trash : trashArray) trash.move();
            for (BulletObject bullet : bulletArray) bullet.move();
            backgroundView.move();
        }


        draw();
    }

    private void handleInput() {

        if (Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (pauseButton.isHit(touch.x, touch.y)) {
                session.pauseSession();
                return;
            }
            if (resumeButtonView.isHit(touch.x, touch.y)) {
                session.resumeSession();
            }
            if (homeButtonView.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
        }

        if (session.getState() == GameScreenState.PLAYING && Gdx.input.isTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            ship.move((int) touch.x, (int) touch.y);
        }

    }

    public void draw() {
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.BLACK);

        myGdxGame.batch.begin();
        backgroundView.draw(myGdxGame.batch);
        for (BulletObject bullet : bulletArray) bullet.draw(myGdxGame.batch);
        for (TrashObject trash : trashArray) trash.draw(myGdxGame.batch);
        ship.draw(myGdxGame.batch);
        topBlackOutView.draw(myGdxGame.batch);
        scoreCounterView.draw(myGdxGame.batch, "score: " + 100);
        pauseButton.draw(myGdxGame.batch);
        liveView.draw(myGdxGame.batch);

        if (session.getState() == GameScreenState.PAUSED) {
            blackoutView.draw(myGdxGame.batch);
            pauseTitleView.draw(myGdxGame.batch, "Pause");
            homeButtonView.draw(myGdxGame.batch);
            resumeButtonView.draw(myGdxGame.batch);
        }

        myGdxGame.batch.end();
    }


}
