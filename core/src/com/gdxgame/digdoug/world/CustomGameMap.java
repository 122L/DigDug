package com.gdxgame.digdoug.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdxgame.digdoug.DigDoug;
import com.gdxgame.digdoug.world.custommap.CustomGameMapData;
import com.gdxgame.digdoug.world.custommap.CustomGameMapLoader;
import com.gdxgame.digdoug.screens.Hud;



public class CustomGameMap extends GameMap {
	
	String id;
	String name;
	int[][][] map;

	public DigDoug game;
	
	private TextureRegion[][] tiles;

	private Hud hud;

	public CustomGameMap (DigDoug game) {

		CustomGameMapData data = CustomGameMapLoader.generateRandomMap("bruh", "DigDoug");
		this.id = data.id;
		this.name = data.name;
		this.map = data.map;

		this.game = game;
		hud = new Hud(game.batch);


		tiles = TextureRegion.split(new Texture("newerTiles.png"), TileType.TILE_SIZE, TileType.TILE_SIZE);
	}
	
	public void render(OrthographicCamera camera, SpriteBatch batch) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();


		for (int layer = 0; layer < getLayers(); layer++) {
			for (int row = 0; row < getHeight(); row++) {
				for (int col = 0; col < getWidth(); col++) {
					TileType type = this.getTileTypeByCoordinate(layer, col, row);
					if (type != null) {
						batch.draw(tiles[0][type.getId() - 1], col * TileType.TILE_SIZE, row * TileType.TILE_SIZE);
					}
				}
			}
		}


		super.render(camera, batch);
		batch.end();

		batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	public void update(float delta) {
		super.update(delta);
		hud.update(delta);
	}

	public void dispose() {
		hud.dispose();
	}
	
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), getHeight() - (int) (y / TileType.TILE_SIZE) - 1);
	}
	
	public TileType getTileTypeByCoordinate(int layer, int col, int row) {
		if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight())
			return null;
		
		return TileType.getTileTypeById(map[layer][getHeight() - row - 1][col]);
	}

	public int getWidth() {
		return map[0][0].length;
	}
	public int getHeight() {
		return map[0].length;
	}
	public int getLayers() {
		return map.length;
	}

}
