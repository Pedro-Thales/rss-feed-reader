package com.pedro.crawler;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class FeedReader {

	public static void main(String[] args) {
		try {
			String url = "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml";

			try (XmlReader reader = new XmlReader(new URL(url))) {
				SyndFeed feed = new SyndFeedInput().build(reader);
				System.out.println(feed.getTitle());
				System.out.println("***********************************");
				for (SyndEntry entry : feed.getEntries()) {
/*					System.out.println("LINK : ");
					System.out.println(entry.getLink());

					System.out.println("Título : ");
					System.out.println(entry.getTitle());*/

					System.out.println("Description : ");
					String html = entry.getDescription().getValue();
					System.out.println(html);
					
					Document doc = Jsoup.parse(html);
					System.out.println("TEXTO ");
					System.out.println(doc.body().text());
					
					Elements paragraphs = doc.select("p");
					for(Element ele : paragraphs) {
						
						if(!ele.text().isEmpty()) {
							System.out.println("======");
							System.out.println("TEXTO DO P");
							System.out.println(ele.text());
							System.out.println("======");
						}
					}
					
					
					Elements links = doc.select("div > ul > li > a");
					for(Element ele : links) {
						System.out.println("======");
						System.out.println("LINK DO ELEMENTO");
						System.out.println(ele.attr("href"));
						System.out.println("======");
					}

				}
				System.out.println("Done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
