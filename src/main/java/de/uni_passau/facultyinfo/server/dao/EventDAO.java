package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.Event;

public class EventDAO {

	private static final int CROP_OFFSET = 30;

	public List<Event> getEvents() {
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, startdate, enddate, host FROM events WHERE startdate >= NOW() ORDER BY startdate, title ");
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

	private List<Event> getEventsForSearch() {
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, startdate, subtitle, location, host, description FROM events WHERE startdate >= NOW() ORDER BY startdate, title ");
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToEventsForSearch(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Event getEvent(String id) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, id);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, startdate, enddate, subtitle, location, description, host, url FROM events WHERE id = ?",
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

	public List<Event> find(String searchString) {
		ArrayList<Event> searchResults = new ArrayList<Event>();

		if (searchString != null && !searchString.isEmpty()) {

			List<Event> events = getEventsForSearch();
			Pattern pattern = Pattern.compile(searchString,
					Pattern.CASE_INSENSITIVE + Pattern.LITERAL);

			for (Event event : events) {
				boolean found = false;

				if (event.getTitle() != null
						&& pattern.matcher(event.getTitle()).find()) {
					found = true;
				}

				if (event.getSubtitle() != null
						&& pattern.matcher(event.getSubtitle()).find()) {
					found = true;
				} else {
					event.setSubtitle(null);
				}

				if (event.getHost() != null
						&& pattern.matcher(event.getHost()).find()) {
					found = true;
				} else {
					event.setHost(null);
				}

				if (event.getLocation() != null
						&& pattern.matcher(event.getLocation()).find()) {
					found = true;
				} else {
					event.setLocation(null);
				}

				if (event.getDescription() != null) {
					Matcher descriptionMatcher = pattern.matcher(event
							.getDescription());
					if (descriptionMatcher.find()) {
						event.setDescription(crop(
								event.getDescription(),
								descriptionMatcher.start(),
								descriptionMatcher.end()
										- descriptionMatcher.start()));
						found = true;
					} else {
						event.setDescription(null);
					}
				}

				if (found) {
					searchResults.add(event);
				}
			}
		}
		return Collections.unmodifiableList(searchResults);
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

	private ArrayList<Event> mapResultSetToEventsForSearch(ResultSet resultSet)
			throws SQLException {
		ArrayList<Event> events = new ArrayList<Event>();
		while (resultSet.next()) {
			Event event = new Event(resultSet.getString("id"),
					resultSet.getString("title"),
					resultSet.getString("subtitle"),
					resultSet.getString("location"),
					resultSet.getString("description"),
					resultSet.getTimestamp("startdate"), null,
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

	private String crop(String input, int start, int offset) {
		boolean cropStart = start - CROP_OFFSET >= 0;
		boolean cropEnd = start + offset + CROP_OFFSET < input.length();

		String croppedInput = input.substring(cropStart ? start - CROP_OFFSET
				: 0, cropEnd ? start + offset + CROP_OFFSET
				: input.length() - 1);

		croppedInput = (cropStart ? "..." : "") + croppedInput
				+ (cropEnd ? "..." : "");

		return croppedInput;
	}
}
