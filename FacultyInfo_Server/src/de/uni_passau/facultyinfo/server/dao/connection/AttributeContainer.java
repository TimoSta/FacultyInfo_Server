package de.uni_passau.facultyinfo.server.dao.connection;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;

public class AttributeContainer {
	private HashMap<Integer, String> stringContainer = null;
	private HashMap<Integer, Timestamp> dateTimeContainer = null;
	private HashMap<Integer, Time> timeContainer = null;

	private HashMap<Integer, String> getStringContainer() {
		if (stringContainer == null) {
			stringContainer = new HashMap<>();
		}
		return stringContainer;
	}

	private HashMap<Integer, Timestamp> getDateTimeContainer() {
		if (dateTimeContainer == null) {
			dateTimeContainer = new HashMap<>();
		}
		return dateTimeContainer;
	}

	private HashMap<Integer, Time> getTimeContainer() {
		if (timeContainer == null) {
			timeContainer = new HashMap<>();
		}
		return timeContainer;
	}

	public AttributeContainer addString(Integer position, String value) {
		getStringContainer().put(position, value);
		return this;
	}

	public AttributeContainer addDateTime(Integer position, java.util.Date value) {
		getDateTimeContainer().put(position, new Timestamp(value.getTime()));
		return this;
	}

	public AttributeContainer addTime(Integer position, Time value) {
		getTimeContainer().put(position, value);
		return this;
	}

	protected HashMap<Integer, String> getStringAttributes() {
		return stringContainer;
	}

	protected HashMap<Integer, Timestamp> getDateTimeAttributes() {
		return dateTimeContainer;
	}

	protected HashMap<Integer, Time> getTimeAttributes() {
		return timeContainer;
	}

}
