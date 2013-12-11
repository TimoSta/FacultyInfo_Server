package de.uni_passau.facultyinfo.server.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapMarker {
	public static final int TYPE_UNI = 1;
	public static final int TYPE_CITY = 2;
	
	private String id;
	private String description;
	private float latitude;
	private float longitude;
	private int category;

	public MapMarker(String id, String description, float latitude,
			float longitude, int category) {
		super();
		this.id = id;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
