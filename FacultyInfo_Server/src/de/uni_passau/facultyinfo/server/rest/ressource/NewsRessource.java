package de.uni_passau.facultyinfo.server.rest.ressource;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.uni_passau.facultyinfo.server.dao.NewsDAO;
import de.uni_passau.facultyinfo.server.dto.News;

@Path("/news")
public class NewsRessource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getNewsList() {
		NewsDAO newsDAO = new NewsDAO();
		return newsDAO.getNewsList();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public News getArticle(@PathParam("id") String id) {
		NewsDAO newsDAO = new NewsDAO();
		News news = newsDAO.getNews(id);
		if (news == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return news;
	}

	@Path("/load")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadNews() {
		String result = "";
		NewsDAO newsDAO = new NewsDAO();

		result += "Deleting from Table news";
		newsDAO.deleteAllNews();
		result += " -- done\n";
		
		Connection connection = Jsoup
				.connect("http://neu.fs-wiwi.de/index.php?option=com_rd_rss&id=2");
		connection.ignoreContentType(true);
		result += "Loading news...\n";
		try {
			Document doc = connection.get();
			Elements newsElements = doc.select("item");
			for (Element element : newsElements) {
				String id = UUID.randomUUID().toString();
				String title = element.select("title").get(0).text();
				String description = element.select("description").get(0)
						.text();
				String url = element.select("link").get(0).text();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"E, d MMM yyyy H:m:s Z", Locale.ENGLISH);
				String text = "";
				Date publicationDate = sdf.parse(element.select("pubDate")
						.get(0).text());

				News news = new News(id, title, description, url, text,
						publicationDate);
				result += news.getTitle();
				if (newsDAO.createNews(news)) {
					result += " -- done -- "+element.html()+"\n";
				} else {
					result += " -- error\n";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		result += "Finished loading news!";
		return result;
	}
}