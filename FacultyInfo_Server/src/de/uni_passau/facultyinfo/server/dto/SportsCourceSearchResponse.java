package de.uni_passau.facultyinfo.server.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SportsCourceSearchResponse {
	List<SportsCourseCategory> categories = null;
	List<SportsCourse> courses = null;

	public SportsCourceSearchResponse() {
		categories = new ArrayList<SportsCourseCategory>();
		courses = new ArrayList<SportsCourse>();
	}

	public void add(SportsCourseCategory category) {
		this.categories.add(category);
	}

	public void add(SportsCourse course) {
		this.courses.add(course);
	}

}
