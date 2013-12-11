package de.uni_passau.facultyinfo.server.dto;

import java.sql.Time;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessHours {
	public static final int TYPE_CAFETERIA = 1;
	public static final int TYPE_LIBRARY = 2;

	private String id;
	private Date day;
	private String facility;
	private int type;
	private Time openingTime;
	private Time closingTime;

	public BusinessHours(String id, Date day, String facility, int type,
			Time openingTime, Time closingTime) {
		super();
		this.id = id;
		this.day = day;
		this.facility = facility;
		this.type = type;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Time getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Time openingTime) {
		this.openingTime = openingTime;
	}

	public Time getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Time closingTime) {
		this.closingTime = closingTime;
	}
}
