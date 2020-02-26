package com.eureka.gallery.entities;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class Gallery {
	@NonNull private Integer id;
	private List<Object> images;
}
