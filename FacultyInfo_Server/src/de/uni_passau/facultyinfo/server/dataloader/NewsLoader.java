package de.uni_passau.facultyinfo.server.dataloader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import de.uni_passau.facultyinfo.server.dao.NewsDAO;
import de.uni_passau.facultyinfo.server.dto.News;

public class NewsLoader {
	public String loadNews() {
		String result = "";
		NewsDAO newsDAO = new NewsDAO();
		result += "Deleting from Table news";
		newsDAO.deleteAllNews();
		result += " -- done\n";

		Connection connection = Jsoup
				.connect("http://neu.fs-wiwi.de/index.php?option=com_rd_rss&id=2");
		connection.ignoreContentType(true);
		connection.parser(Parser.xmlParser());
		result += "Loading news...\n";
		try {
			Document doc = connection.get();
			Elements newsElements = doc.select("item");
			for (Element element : newsElements) {
				String id = UUID.randomUUID().toString();
				String title = element.select("title").get(0).text();
				// String description = element.select("description").get(0)
				// .text();
				String url = element.select("link").get(0).text();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"E, d MMM yyyy H:m:s Z", Locale.ENGLISH);
				Element textElement = Jsoup.connect(url).get()
						.select("table.contentpaneopen").get(1).select("td")
						.get(0);
				textElement.select("table").get(0).html("");
				String text = Jsoup.parse(
						textElement.html().replace("<br>", "\n")
								.replace("<br />", "\n")).text();
				String description = text.length() >= 200 ? text.substring(0,
						199) + "..." : text;

				Date publicationDate = sdf.parse(element.select("pubDate")
						.get(0).text());

				News news = new News(id, title, description, url, text,
						publicationDate);
				result += news.getTitle();
				if (newsDAO.createNews(news)) {
					result += " -- done\n";
				} else {
					result += " -- error\n";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
