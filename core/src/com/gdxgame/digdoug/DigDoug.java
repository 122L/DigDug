package com.gdxgame.digdoug;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdxgame.digdoug.world.CustomGameMap;
import com.gdxgame.digdoug.world.GameMap;



public class DigDoug extends Game {

    public SpriteBatch batch;
    private Viewport viewport;


    GameMap gameMap;
	Music music;
    OrthographicCamera cam;
    float deltaX, deltaY;


	@Override
	public void create() {

		batch = new SpriteBatch();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, w, h);
        cam.update();

        music = Gdx.audio.newMusic(Gdx.files.internal("D_O_U_G.mp3"));
        music.setLooping(true);
        music.play();

        gameMap = new CustomGameMap(this);

	}

    @Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        gameMap.update(Gdx.graphics.getDeltaTime());
        gameMap.render(cam, batch);


        super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		gameMap.dispose();
		music.dispose();

	}
}