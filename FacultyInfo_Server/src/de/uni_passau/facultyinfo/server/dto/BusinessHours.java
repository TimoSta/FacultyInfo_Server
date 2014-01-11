package de.uni_passau.facultyinfo.server.dto;

import java.sql.Time;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessHours {
	public static final int MONDAY = 1;
	public static final int TUESDAY = 2;
	public static final int WEDNESDAY = 3;
	public static final int THURSDAY = 4;
	public static final int FRIDAY = 5;
	public static final int SATURDAY = 6;
	public static final int SUNDAY = 7;

	public static final int PHASE_SEMESTER = 1;
	public static final int PHASE_BREAK = 2;

	public static final int STATUS_OPEN = 1;
	public static final int STATUS_CLOSED = 2;

	private String id;
	@XmlTransient
	private BusinessHoursFacility associatedFacility;
	private int dayOfWeek;
	private int phase;
	private int status;
	private Time openingTime;
	private Time closingTime;

	public BusinessHours(String id, BusinessHoursFacility associatedFacility,
			int dayOfWeek, int phase, int status, Time openingTime,
			Time closingTime) {
		super();
		this.id = id;
		this.associatedFacility = associatedFacility;
		this.dayOfWeek = dayOfWeek;
		this.phase = phase;
		this.status = status;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BusinessHoursFacility getAssociatedFacility() {
		return associatedFacility;
	}

	public void setAssociatedFacility(BusinessHoursFacility associatedFacility) {
		this.associatedFacility = associatedFacility;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
