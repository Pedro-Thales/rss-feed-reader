package com.pedro.crawler.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.crawler.model.FeedItem;
import com.pedro.crawler.service.FeedService;

@RestController
public class FeedController {

	@GetMapping("/showItems")
	public List<FeedItem> showItems(
			@RequestParam(value = "url", defaultValue = "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml") String url) {
		return FeedService.getFeed(url);
	}

}
