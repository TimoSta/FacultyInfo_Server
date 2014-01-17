package de.uni_passau.facultyinfo.server.dataloader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
				String url = element.select("link").get(0).text();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"E, d MMM yyyy H:m:s Z", Locale.ENGLISH);
				Document subDoc = Jsoup.connect(url).get();
				subDoc.outputSettings(new Document.OutputSettings()
						.prettyPrint(false));
				subDoc.select("br").append("\\n");
				subDoc.select("p").prepend("\\n");
				subDoc.select("li").prepend("\\n");
				Element textElement = subDoc.select("table.contentpaneopen")
						.get(1).select("td").get(0);
				textElement.select("table").get(0).html("");
				String text = textElement.html();
				ArrayList<String> urls = new ArrayList<String>();
				for (Element linkElement : textElement.select("a")) {
					urls.add(linkElement.absUrl("href"));
				}
				Pattern p = Pattern.compile("<a .*?href=\".*?\" .*?>(.*?)</a>");
				Matcher m = p.matcher(text);
				StringBuffer s = new StringBuffer();
				int i = 0;
				while (m.find()) {
					m.appendReplacement(
							s,
							urls.size() <= i
									|| Jsoup.parse(m.group(1)).text().trim()
											.isEmpty() ? m.group(1) : m
									.group(1) + " (" + urls.get(i) + ") ");
					i++;
				}
				m.appendTail(s);

				text = Jsoup
						.parse(s.toString().replaceAll("\\\\n", "[newline]"))
						.text().replace("[newline]", "\n").trim();

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
