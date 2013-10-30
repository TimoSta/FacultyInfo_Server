package de.uni_passau.facultyinfo.server.rest.ressource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.uni_passau.facultyinfo.server.dto.Article;
import de.uni_passau.facultyinfo.server.persistence.DataService;

@Path("/articles")
public class ArticleRessource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Article> getArticles() {
		return DataService.getInstance().getArticles();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Article getArticle(@PathParam("id") long id) {
		for (Article article : DataService.getInstance().getArticles()) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
}