package com.gdxgame.digdoug.entities;

import java.util.HashMap;


@SuppressWarnings("rawtypes")
public enum EntityType {
	
	PLAYER("player", Player.class, 14, 16, 40);
	
	private String id;
	private Class loaderClass;
	private int width, height;
	private float weight;
	
	private EntityType(String id, Class loaderClass, int width, int height, float weight) {
		this.id = id;
		this.loaderClass = loaderClass;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public float getWeight() {
		return weight;
	}
	
	private static HashMap<String, EntityType> entityTypes;
	
	static {
		entityTypes = new HashMap<String, EntityType>();
		for (EntityType type : EntityType.values())
			entityTypes.put(type.id, type);
	}
	
}
