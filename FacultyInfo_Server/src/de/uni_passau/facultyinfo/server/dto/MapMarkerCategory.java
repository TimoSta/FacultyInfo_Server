package de.uni_passau.facultyinfo.server.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapMarkerCategory {
	private String id;
	private String title;
	private MapMarkerCategory superCategory;

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

}
