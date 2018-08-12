package com.pedro.crawler.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.crawler.model.FeedItem;
import com.pedro.crawler.service.FeedService;

@RestController
public class FeedController {
	
	@GetMapping("/showItems")
	public List<FeedItem> showItems(){
		//return new ResponseEntity<FeedItem>(feedItem, HttpStatus.OK);
		return FeedService.getFeed();
	}
	

}
