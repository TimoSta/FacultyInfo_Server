package de.uni_passau.facultyinfo.server.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapMarkerCategory {
	private String id;
	private String title;
	@XmlTransient
	private MapMarkerCategory superCategory;
	private List<MapMarkerCategory> mapMarkerCategories;
	private List<MapMarker> mapMarkers;

	public MapMarkerCategory(String id, String title,
			MapMarkerCategory superCategory) {
		super();
		this.id = id;
		this.title = title;
		this.superCategory = superCategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MapMarkerCategory getSuperCategory() {
		return superCategory;
	}

	public void setSuperCategory(MapMarkerCategory superCategory) {
		this.superCategory = superCategory;
	}

	public List<MapMarkerCategory> getMapMarkerCategories() {
		return mapMarkerCategories;
	}

	public void setMapMarkerCategories(
			List<MapMarkerCategory> mapMarkerCategories) {
		this.mapMarkerCategories = mapMarkerCategories;
	}

	public void addMapMarkerCategory(MapMarkerCategory category) {
		if(mapMarkerCategories == null) {
			mapMarkerCategories = new ArrayList<MapMarkerCategory>();
		}
		mapMarkerCategories.add(category);
	}

	public List<MapMarker> getMapMarkers() {
		return mapMarkers;
	}

	public void setMapMarkers(List<MapMarker> mapMarkers) {
		this.mapMarkers = mapMarkers;
	}

}
