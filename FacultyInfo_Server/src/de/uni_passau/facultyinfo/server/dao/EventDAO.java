package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.Event;

public class EventDAO {

	public List<Event> getEvents() {
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, title, startdate, enddate, host FROM events");
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d H:m:s");
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add(event.getId());
		attributes.add(event.getTitle());
		attributes.add(event.getSubtitle());
		attributes.add(event.getLocation());
		attributes.add(event.getDescription());
		attributes.add(sdf.format(event.getStartDate()));
		attributes.add(sdf.format(event.getEndDate()));
		attributes.add(event.getHost());
		attributes.add(event.getUrl());
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO events (id, title, subtitle, location, description, startdate, enddate, host, url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
						attributes) == 1;
	}

	private ArrayList<Event> mapResultSetToEvents(ResultSet resultSet)
			throws SQLException {
		ArrayList<Event> events = new ArrayList<Event>();
		while (resultSet.next()) {
			Event event = new Event(resultSet.getString("id"),
					resultSet.getString("title"), null, null, null,
					resultSet.getTimestamp("startdate"),
					resultSet.getTimestamp("enddate"), resultSet.getString("host"),
					null);
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
					resultSet.getTimestamp("enddate"), resultSet.getString("host"),
					resultSet.getString("url"));
			return event;
		}
		return null;
	}

	public void deleteAllEvents() {
		JDBCConnection.getInstance().executeStatement("DELETE FROM events");
	}

}
