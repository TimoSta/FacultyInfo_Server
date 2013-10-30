package de.uni_passau.facultyinfo.server.rest.ressource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uni_passau.facultyinfo.server.dao.ArticleDAO;
import de.uni_passau.facultyinfo.server.dto.Article;

@Path("/articles")
public class ArticleRessource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Article> getArticles() {
		ArticleDAO articleDAO = new ArticleDAO();
		return articleDAO.getArticles();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Article getArticle(@PathParam("id") long id) {
		ArticleDAO articleDAO = new ArticleDAO();
		Article article = articleDAO.getArticle(id);
		if(article == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return article;
	}
}