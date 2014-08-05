package de.uni_passau.facultyinfo.server.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItem {
	public static final int TYPE_NOT_AVAILABLE = 0;
	public static final int TYPE_SOUP = 1;
	public static final int TYPE_APPETIZER = 2;
	public static final int TYPE_MAIN = 3;
	public static final int TYPE_DESSERT = 4;
	
	public static final double PRICE_NOT_AVAILABLE = -1.0;

	private String id;
	private Date day;
	private String name;
	private int type;
	private int attributes;
	private double priceStudent;
	private double priceEmployee;
	private double priceExternal;

	public MenuItem(String id, Date day, String name, int type, int attributes,
			double priceStudent, double priceEmployee, double priceExternal) {
		super();
		this.id = id;
		this.day = day;
		this.name = name;
		this.type = type;
		this.attributes = attributes;
		this.priceStudent = priceStudent;
		this.priceEmployee = priceEmployee;
		this.priceExternal = priceExternal;
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

	public int getAttributes() {
		return attributes;
	}

	public void setAttributes(int attributes) {
		this.attributes = attributes;
	}

	public double getPriceStudent() {
		return priceStudent;
	}

	public void setPriceStudent(double priceStudent) {
		this.priceStudent = priceStudent;
	}

	public double getPriceEmployee() {
		return priceEmployee;
	}

	public void setPriceEmployee(double priceEmployee) {
		this.priceEmployee = priceEmployee;
	}

	public double getPriceExternal() {
		return priceExternal;
	}

	public void setPriceExternal(double priceExternal) {
		this.priceExternal = priceExternal;
	}

}
