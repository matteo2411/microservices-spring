package com.eureka.gallery.entities;

import java.util.List;

public class Gallery {
	
	int id;
	List<Object> images;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Object> getImages() {
		return images;
	}
	public void setImages(List<Object> images) {
		this.images = images;
	}
	
	public Gallery() {
		super();
	}
	public Gallery(int id) {
		super();
		this.id = id;
	}
	
}
