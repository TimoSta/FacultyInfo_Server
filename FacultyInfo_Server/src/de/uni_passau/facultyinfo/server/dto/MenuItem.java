package de.uni_passau.facultyinfo.server.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItem {
	public static final int TYPE_SOUP = 1;
	public static final int TYPE_APPETIZER = 2;
	public static final int TYPE_MAIN = 3;
	public static final int TYPE_DESSERT = 4;

	private String id;
	private Date day;
	private String name;
	private int type;
	private int attributes;
	private float priceStudent;
	private float priceEmployee;
	private float priceExternal;

	public MenuItem(String id, Date day, String name, int type, int attributes,
			float priceStudent, float priceEmployee, float priceExternal) {
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

	public float getPriceStudent() {
		return priceStudent;
	}

	public void setPriceStudent(float priceStudent) {
		this.priceStudent = priceStudent;
	}

	public float getPriceEmployee() {
		return priceEmployee;
	}

	public void setPriceEmployee(float priceEmployee) {
		this.priceEmployee = priceEmployee;
	}

	public float getPriceExternal() {
		return priceExternal;
	}

	public void setPriceExternal(float priceExternal) {
		this.priceExternal = priceExternal;
	}

}
