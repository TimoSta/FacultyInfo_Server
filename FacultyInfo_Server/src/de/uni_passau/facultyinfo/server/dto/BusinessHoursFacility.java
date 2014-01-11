package de.uni_passau.facultyinfo.server.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessHoursFacility {
	public static final int TYPE_CAFETERIA = 1;
	public static final int TYPE_LIBRARY = 2;

	public static final int MONDAY = 1;
	public static final int TUESDAY = 2;
	public static final int WEDNESDAY = 3;
	public static final int THURSDAY = 4;
	public static final int FRIDAY = 5;
	public static final int SATURDAY = 6;
	public static final int SUNDAY = 7;

	private String id;
	private String name;
	private int type;

	private List<BusinessHours> businessHours;

	public BusinessHoursFacility(String id, String name, int type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<BusinessHours> getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(List<BusinessHours> businessHours) {
		this.businessHours = businessHours;
	}

}
