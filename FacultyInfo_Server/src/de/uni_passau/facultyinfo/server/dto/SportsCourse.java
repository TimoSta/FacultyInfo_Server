package de.uni_passau.facultyinfo.server.dto;

import java.sql.Time;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class SportsCourse {
	public static final int STATUS_NOT_AVAILABLE = 0;
	public static final int STATUS_OPEN = 1;
	public static final int STATUS_CLOSED = 2;
	public static final int STATUS_FULL = 3;
	public static final int STATUS_OFFICE_SIGNUP = 4;
	public static final int STATUS_STORNO = 5;
	public static final int STATUS_QUEUE = 6;
	public static final int STATUS_NO_SIGNUP_REQUIRED = 7;
	public static final int STATUS_NO_SIGNUP_POSSIBLE = 8;
	
	public static final int DATE_NOT_AVAILABLE = 0;
	public static final int MONDAY = 1;
	public static final int TUESDAY = 2;
	public static final int WEDNESDAY = 3;
	public static final int THURSDAY = 4;
	public static final int FRIDAY = 5;
	public static final int SATURDAY = 6;
	public static final int SUNDAY = 7;

	public static final double PRICE_NOT_AVAILABLE = -1.0;

	private String id;
	@XmlTransient
	private SportsCourseCategory category;
	private String number;
	private String details;
	private int dayOfWeek;
	private Time startTime;
	private Time endTime;
	private String location;
	private Date startDate;
	private Date endDate;
	private String host;
	private double price;
	private int status;

	public SportsCourse(String id, SportsCourseCategory category,
			String number, String details, int dayOfWeek, Time startTime,
			Time endTime, String location, Date startDate, Date endDate,
			String host, double price, int status) {
		super();
		this.id = id;
		this.category = category;
		this.number = number;
		this.details = details;
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.host = host;
		this.price = price;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SportsCourseCategory getCategory() {
		return category;
	}

	public void setCategory(SportsCourseCategory category) {
		this.category = category;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
