package com.pedro.crawler.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.crawler.model.DescriptionType;
import com.pedro.crawler.model.FeedItem;
import com.pedro.crawler.model.FeedItemDescription;

@RestController
public class FeedController {
	
	@GetMapping("/showItems")
	public FeedItem showItems(){
		List<FeedItemDescription<?>> list = new ArrayList<>();
		FeedItemDescription<String> itemDescr = new FeedItemDescription<String>(DescriptionType.text, "Conteudo");
		list.add(itemDescr);
		list.add(itemDescr);

		FeedItem feedItem = new FeedItem("Titulo", "link ", list);
		//return new ResponseEntity<FeedItem>(feedItem, HttpStatus.OK);
		return feedItem;
	}
	

}
