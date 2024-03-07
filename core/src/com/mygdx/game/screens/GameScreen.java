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
import com.mygdx.game.objects.BulletObject;
import com.mygdx.game.objects.ShipObject;
import com.mygdx.game.objects.TrashObject;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;
    GameSession session;

    ArrayList<TrashObject> trashArray;
    ArrayList<BulletObject> bulletArray;

    ShipObject ship;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        session = new GameSession();
        trashArray = new ArrayList<>();
        bulletArray = new ArrayList<>();
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

        if (ship.hasToShoot()) {
            BulletObject bullet = new BulletObject(ship.getX() + ship.getWidth() / 2, ship.getY());
            bulletArray.add(bullet);
        }

        for (int i = 0; i < trashArray.size(); i++) {
            for (int j = 0; j < bulletArray.size(); j++) {
                if (trashArray.get(i).isHit(bulletArray.get(j))) {

                    trashArray.get(i).getDamage(1);
                    if (!trashArray.get(i).isAlive()) {
                        trashArray.remove(i);
                        i--;
                    }

                    bulletArray.remove(j);
                    j--;

                }
            }

            if (trashArray.get(i).isHit(ship)) {
                ship.getDamage(1);
                if (!ship.isAlive()) {
                    System.out.println("end of game");
                }

                trashArray.remove(i);
                i--;
            }
        }

        for (TrashObject trash : trashArray) trash.move();
        for (BulletObject bullet : bulletArray) bullet.move();

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
        for (BulletObject bullet : bulletArray) bullet.draw(myGdxGame.batch);
        for (TrashObject trash : trashArray) trash.draw(myGdxGame.batch);
        ship.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }


}
