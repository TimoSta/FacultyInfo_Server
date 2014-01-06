package de.uni_passau.facultyinfo.server.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SportsCourseCategory {
	private String id;
	private String title;
	private List<SportsCourse> sportsCourses;

	public SportsCourseCategory(String id, String title) {
		super();
		this.id = id;
		this.title = title;
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

	public List<SportsCourse> getSportsCourses() {
		return sportsCourses;
	}

	public void setSportsCourses(List<SportsCourse> sportsCourses) {
		this.sportsCourses = sportsCourses;
	}

}
