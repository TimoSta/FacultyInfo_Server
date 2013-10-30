package de.uni_passau.facultyinfo.server.persistence;

import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dto.Article;

public class DataService {

	private static DataService instance = null;

	public static DataService getInstance() {
		if (instance == null) {
			instance = new DataService();
		}

		return instance;
	}

	public List<Article> getArticles() {
		ArrayList<Article> articles = new ArrayList<Article>();
		articles.add(new Article(1, "Artikel 1", "Dies ist ein Testartikel"));
		articles.add(new Article(2, "Artikel 2", "Und noch ein Testartikel"));
		articles.add(new Article(3, "Artikel 3", "Und der letzte Testartikel"));
		return articles;
	}

}