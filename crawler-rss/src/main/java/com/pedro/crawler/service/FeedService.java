package com.pedro.crawler.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pedro.crawler.model.DescriptionType;
import com.pedro.crawler.model.FeedItem;
import com.pedro.crawler.model.FeedItemDescription;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public final class FeedService {

	public final static List<FeedItem> getFeed() {
		List<FeedItem> itens = new ArrayList<>();
		try {
			String url = "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml";

			try (XmlReader reader = new XmlReader(new URL(url))) {
				SyndFeed feed = new SyndFeedInput().build(reader);
				System.out.println(feed.getTitle());
				System.out.println("***********************************");
				for (SyndEntry entry : feed.getEntries()) {

					String title = entry.getTitle();

					String link = entry.getLink();

					String html = entry.getDescription().getValue();
					Document doc = Jsoup.parse(html);
					List<FeedItemDescription<?>> listDescription = new ArrayList<>();
					Elements paragraphs = doc.select("p");
					for (Element ele : paragraphs) {
						if (!ele.text().isEmpty()) {
							listDescription.add(new FeedItemDescription<String>(DescriptionType.text, ele.text()));
						}
					}

					List<String> list = new ArrayList<>();

					Elements links = doc.select("div > ul > li > a");
					for (Element ele : links) {
						list.add(ele.attr("href"));
					}

					listDescription.add(new FeedItemDescription<List<String>>(DescriptionType.links, list));

					itens.add(new FeedItem(title, link, listDescription));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itens;
	}

}
