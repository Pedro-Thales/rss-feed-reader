package com.pedro.crawler.model;

public class FeedItemDescription<T> {

	private final DescriptionType type;
	private final T content;

	public FeedItemDescription(DescriptionType type, T content) {
		this.type = type;
		this.content = content;
	}

	public DescriptionType getType() {
		return type;
	}

	public T getContent() {
		return content;
	}

}
