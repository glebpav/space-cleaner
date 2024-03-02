package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameSession;
import com.mygdx.game.GameSettings;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.objects.ShipObject;
import com.mygdx.game.objects.TrashObject;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;
    GameSession session;

    ArrayList<TrashObject> trashArray;

    ShipObject ship;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        session = new GameSession();
        trashArray = new ArrayList<>();
        ship = new ShipObject(GameSettings.SCREEN_WIDTH / 2 - GameSettings.SHIP_WIDTH / 2, 100);
    }

    @Override
    public void show() {
        session.beginSession();
    }

    @Override
    public void render(float delta) {
        handleInput();

        if (session.hasToSpawnTrash()) {
            TrashObject trash = new TrashObject();
            trashArray.add(trash);
        }

        for (int i = 0; i < trashArray.size(); i++) trashArray.get(i).move();

        draw();
    }

    private void handleInput() {

        if (Gdx.input.isTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            ship.move((int) touch.x, (int) touch.y);
        }

    }

    public void draw() {
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.BLACK);

        myGdxGame.batch.begin();
        for (int i = 0; i < trashArray.size(); i++) trashArray.get(i).draw(myGdxGame.batch);
        ship.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }


}
