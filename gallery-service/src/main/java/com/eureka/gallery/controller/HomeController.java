package com.eureka.gallery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eureka.gallery.entities.Gallery;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	public String home() {
		log.info("[START/END] home, /");
		return "Hello from Gallery Service running at port: " + env.getProperty("local.server.port");
	}
	
	@RequestMapping("/{id}")
	public Gallery getGallery(@PathVariable final int id) {
		
		log.info("[START] getGallery, /"+id);
		
		// create gallery object
		Gallery gallery = new Gallery(id);
		
		// get list of available images 
		List<Object> images = restTemplate.getForObject("http://image-service/images/", List.class);
		gallery.setImages(images);

		log.info("[END]  getGallery, /"+id);
	
		return gallery;
	}

	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping("/public/{id}")
	public Gallery getGalleryPublic(@PathVariable final int id) {
		
		log.info("[START] getGalleryPublic, /public/"+id);
		
		// create gallery object
		Gallery gallery = new Gallery(id);
		
		// get list of available images 
		List<Object> images = restTemplate.getForObject("http://image-service/images/", List.class);
		gallery.setImages(images);

		log.info("[END]  getGalleryPublic, /public/"+id);
	
		return gallery;
	}
	
	// -------- Admin Area --------
	// This method should only be accessed by users with role of 'admin'
	// We'll add the logic of role based auth later
	@RequestMapping("/admin")
	public String homeAdmin() {
		log.info("[START/END] homeAdmin, /admin");
		return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
	}
	
	// a fallback method to be called if failure happened
	public Gallery fallback(int galleryId, Throwable hystrixCommand) {
		log.info("[START/END] fallback");
		return new Gallery(galleryId);
	}
}
