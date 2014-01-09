package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.Event;

public class EventDAO {

	public List<Event> getEvents() {
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, startdate, enddate, host FROM events ORDER BY startdate, title");
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToEvents(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Event getEvent(String id) {
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add(id);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, subtitle, location, description, startdate, enddate, host, url FROM events WHERE id = ?",
						attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToEvent(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean createEvent(Event event) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, event.getId());
		attributes.add(2, event.getTitle());
		attributes.add(3, event.getSubtitle());
		attributes.add(4, event.getLocation());
		attributes.add(5, event.getDescription());
		attributes.add(6, event.getStartDate());
		attributes.add(7, event.getEndDate());
		attributes.add(8, event.getHost());
		attributes.add(9, event.getUrl());
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO events (id, title, subtitle, location, description, startdate, enddate, host, url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
						attributes) == 1;
	}

	public void deleteAllEvents() {
		JDBCConnection.getInstance().executeStatement("DELETE FROM events");
	}

	private ArrayList<Event> mapResultSetToEvents(ResultSet resultSet)
			throws SQLException {
		ArrayList<Event> events = new ArrayList<Event>();
		while (resultSet.next()) {
			Event event = new Event(resultSet.getString("id"),
					resultSet.getString("title"), null, null, null,
					resultSet.getTimestamp("startdate"),
					resultSet.getTimestamp("enddate"),
					resultSet.getString("host"), null);
			events.add(event);
		}

		return events;
	}

	private Event mapResultSetToEvent(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			Event event = new Event(resultSet.getString("id"),
					resultSet.getString("title"),
					resultSet.getString("subtitle"),
					resultSet.getString("location"),
					resultSet.getString("description"),
					resultSet.getTimestamp("startdate"),
					resultSet.getTimestamp("enddate"),
					resultSet.getString("host"), resultSet.getString("url"));
			return event;
		}
		return null;
	}
}
