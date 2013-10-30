package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.Article;

public class ArticleDAO {

	public List<Article> getArticles() {
		JDBCConnection jdbcConnection = new JDBCConnection();
		ResultSet resultSet = jdbcConnection
				.executeSelect("SELECT id, name FROM Article");
		if (resultSet == null) {
			return null;
		}

		try {
			ArrayList<Article> articles = new ArrayList<Article>();
			while (resultSet.next()) {
				Article article = new Article(resultSet.getLong("id"),
						resultSet.getString("name"), null);
				articles.add(article);
			}

			return articles;
		} catch (SQLException e) {
			return null;
		}
	}

	public Article getArticle(long id) {
		JDBCConnection jdbcConnection = new JDBCConnection();
		ResultSet resultSet = jdbcConnection
				.executeSelect("SELECT name, content FROM Article WHERE id = "
						+ Long.toString(id));
		if (resultSet == null) {
			return null;
		}

		try {
			if (resultSet.next()) {
				Logger.getAnonymousLogger().log(Level.WARNING,
						resultSet.getString("name"));
				Article article = new Article(id, resultSet.getString("name"),
						resultSet.getString("content"));
				return article;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

}
