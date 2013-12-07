package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.News;

public class NewsDAO {

	public List<News> getNewsList() {
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, description, url, text, publishingdate FROM news");
		if (resultSet == null) {
			return null;
		}

		try {
			ArrayList<News> newsList = new ArrayList<News>();
			while (resultSet.next()) {
				newsList.add(mapResultSetToNews(resultSet));
			}

			return newsList;
		} catch (SQLException e) {
			return null;
		}
	}

	public News getNews(String id) {
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, description, url, text, publishingdate FROM news WHERE id = '"
								+ id + "'");
		if (resultSet == null) {
			return null;
		}

		try {
			if (resultSet.next()) {
				Logger.getAnonymousLogger().log(Level.WARNING,
						resultSet.getString("name"));
				return mapResultSetToNews(resultSet);
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean createNews(News news) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d H:m:s");
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO news (id, title, description, url, text, publishingdate) VALUES ('"
								+ news.getId()
								+ "', '"
								+ news.getTitle()
								+ "', '"
								+ news.getDescription()
								+ "', '"
								+ news.getUrl()
								+ "', '"
								+ news.getText()
								+ "', '"
								+ sdf.format(news.getPublicationDate()) + "')") == 1;
	}

	private News mapResultSetToNews(ResultSet resultSet) throws SQLException {
		News news = new News(resultSet.getString("id"),
				resultSet.getString("title"),
				resultSet.getString("description"), resultSet.getString("url"),
				resultSet.getString("text"),
				resultSet.getDate("publishingDate"));
		return news;
	}

	public void deleteAllNews() {
		JDBCConnection.getInstance().executeStatement("DELETE FROM news");
	}

}
