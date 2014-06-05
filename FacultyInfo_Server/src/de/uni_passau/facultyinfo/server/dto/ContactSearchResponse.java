package de.uni_passau.facultyinfo.server.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContactSearchResponse {
	private List<ContactGroup> groups = null;
	private List<ContactPerson> persons = null;

	public ContactSearchResponse() {
		groups = new ArrayList<ContactGroup>();
		persons = new ArrayList<ContactPerson>();
	}

	public void add(ContactGroup group) {
		groups.add(group);
	}

	public void add(ContactPerson person) {
		persons.add(person);
	}

}
