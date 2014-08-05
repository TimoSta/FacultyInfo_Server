package de.uni_passau.facultyinfo.server.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapMarker {
	public static final int TYPE_UNI = 1;
	public static final int TYPE_CITY = 2;

	private String id;
	private String name;
	private String description;
	private double latitude;
	private double longitude;
	@XmlTransient
	private MapMarkerCategory category;

	public MapMarker(String id, String name, String description,
			double latitude, double longitude, MapMarkerCategory category) {
		super();
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public MapMarkerCategory getCategory() {
		return category;
	}

	public void setCategory(MapMarkerCategory category) {
		this.category = category;
	}
}
