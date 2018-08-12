package com.pedro.crawler.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.pedro.crawler.model.DescriptionType;
import com.pedro.crawler.model.FeedItem;
import com.pedro.crawler.model.FeedItemDescription;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public final class FeedService {

	public static List<FeedItem> getFeed(final String url) {
		List<FeedItem> itens = new ArrayList<>();

		try (XmlReader reader = new XmlReader(new URL(url))) {

			SyndFeed feed = new SyndFeedInput().build(reader);

			for (SyndEntry entry : feed.getEntries()) {
				String title = entry.getTitle();
				String link = entry.getLink();
				String html = entry.getDescription().getValue();

				itens.add(extractFromHtml(title, link, html));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return itens;
	}

	private static FeedItem extractFromHtml(final String title, final String link, final String html) {
		Document doc = Jsoup.parse(html);

		List<FeedItemDescription<?>> listDescription = buildDescription(doc);

		return new FeedItem(title, link, listDescription);
	}

	private static List<FeedItemDescription<?>> buildDescription(Document document) {

		List<FeedItemDescription<?>> listDescription = new ArrayList<>();

		for (Element ele : document.getAllElements()) {
			parseElements(listDescription, ele);
		}

		return listDescription;
	}

	private static void parseElements(List<FeedItemDescription<?>> listDescription, Element element) {

		if (element.is("p")) {
			if (!element.text().isEmpty()) {
				listDescription.add(new FeedItemDescription<String>(DescriptionType.text, element.text()));
			}
		}

		if (element.is("div > ul")) {
			listDescription.add(new FeedItemDescription<List<String>>(DescriptionType.links, getInnerLinks(element)));
		}

		if (element.is("div > img")) {
			listDescription.add(new FeedItemDescription<String>(DescriptionType.image, element.attr("src")));
		}
	}

	private static List<String> getInnerLinks(Element element) {
		List<String> linksList = new ArrayList<>();
		for (Element innerLinkElement : element.select("li > a")) {
			linksList.add(innerLinkElement.attr("href"));
		}
		return linksList;
	}

}
