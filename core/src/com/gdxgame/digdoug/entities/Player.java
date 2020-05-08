package com.gdxgame.digdoug.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.gdxgame.digdoug.tools.CollisionRect;
import com.gdxgame.digdoug.world.GameMap;
import com.gdxgame.digdoug.world.TileType;
import com.gdxgame.digdoug.world.custommap.CustomGameMapData;


public class Player extends Entity {

	private static final int JUMP_VELOCITY = 10;
	private static final int SPEED = 80;

	public CollisionRect rect;
	Texture image;

	public Player (float x, float y, GameMap map) {

		super(x, y, EntityType.PLAYER, map);
		image = new Texture("DOUG.gif");
	}
	
	@Override
	public void update(float deltaTime, float gravity) {
		if (Gdx.input.isKeyPressed(Keys.SPACE) && grounded)
			this.velocityY += JUMP_VELOCITY * getWeight();
		else if (Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY > 0)
			this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			moveY(SPEED * deltaTime);
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN))
		{
			moveY(SPEED * deltaTime);
		}

		super.update(deltaTime, gravity);//Apply gravity

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveX(-SPEED * deltaTime);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveX(SPEED * deltaTime);
		}
	}

	protected void moveX (float amount) {
		float newX = pos.x + amount;
		if (!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight())) {
			this.pos.x = newX;
		}
	}

	protected void moveY (float amount) {
		float newY = pos.y + amount;
		if (!map.doesRectCollideWithMap(newY, pos.x, getWidth(), getHeight()))
			this.pos.y = newY;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
	}

	public CollisionRect getCollisionRect(){
		return rect;
	}

}
