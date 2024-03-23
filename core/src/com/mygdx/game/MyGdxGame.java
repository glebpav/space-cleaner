package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.SettingsScreen;

public class MyGdxGame extends Game {

	public OrthographicCamera camera;
	public SpriteBatch batch;

	public AudioManager audioManager;

	public GameScreen gameScreen;
	public MenuScreen menuScreen;
	public SettingsScreen settingsScreen;

	public BitmapFont largeWhiteFont;
	public BitmapFont commonWhiteFont;
	public BitmapFont commonBlackFont;


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);

		audioManager = new AudioManager();

		largeWhiteFont = FontBuilder.generate(48, Color.WHITE, "fonts/Montserrat-Bold.ttf");
		commonWhiteFont = FontBuilder.generate(24, Color.WHITE, "fonts/Montserrat-Bold.ttf");
		commonBlackFont = FontBuilder.generate(24, Color.BLACK, "fonts/Montserrat-Bold.ttf");

		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		settingsScreen = new SettingsScreen(this);

		setScreen(menuScreen);

		audioManager.backgoundMusic.setVolume(0.3f);
		audioManager.backgoundMusic.play();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
