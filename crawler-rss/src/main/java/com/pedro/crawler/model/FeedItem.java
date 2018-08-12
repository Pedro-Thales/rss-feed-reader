package com.pedro.crawler.model;

import java.util.List;

public class FeedItem {

	private final String title;
	private final String link;
	private final List<FeedItemDescription<?>> description;

	public FeedItem(String title, String link, List<FeedItemDescription<?>> description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public List<FeedItemDescription<?>> getDescription() {
		return description;
	}

}
