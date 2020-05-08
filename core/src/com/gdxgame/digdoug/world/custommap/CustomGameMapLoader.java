package com.gdxgame.digdoug.world.custommap;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.gdxgame.digdoug.world.CustomGameMap;
import com.gdxgame.digdoug.world.GameMap;
import com.gdxgame.digdoug.world.TileType;

import java.util.Random;


public class CustomGameMapLoader {

	private static final int SIZE = 50;
	
	public static CustomGameMapData generateRandomMap (String id, String name) {
		CustomGameMapData mapData = new CustomGameMapData();
		mapData.id = id;
		mapData.name = id;
		mapData.map = new int[2][SIZE][SIZE];
		
		Random random = new Random();
		
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				mapData.map[0][row][col] = TileType.SKY.getId();

				if (random.nextInt(50) == 0 && row > SIZE - 20) {
					if(random.nextInt(3) == 1) {
						mapData.map[1][row][col] = TileType.CRYSTAL.getId();
					}
					else if(random.nextInt(2) == 1)
					{
						mapData.map[1][row][col] = TileType.CRYSTAL2.getId();
					}
					else
					{
						mapData.map[1][row][col] = TileType.CRYSTAL3.getId();
					}
				} else if (row > SIZE - 2) {
					mapData.map[1][row][col] = TileType.BEDROCK.getId();
				} else if (row > SIZE - 20) {
					mapData.map[1][row][col] = TileType.DIRT.getId();
				} else if (row > SIZE - 21) {
					mapData.map[1][row][col] = TileType.GRASS.getId();
				} else {
					// 1/5 chance of being a cloud
					if (random.nextInt(25) == 0)
						mapData.map[0][row][col] = TileType.CLOUD.getId();
					if (TileType.GRASS.isCollidable()){
						mapData.map[0][row][col] = TileType.SKY.getId();
					}
				}
			}
		}
		
		return mapData;
	}
	
}
