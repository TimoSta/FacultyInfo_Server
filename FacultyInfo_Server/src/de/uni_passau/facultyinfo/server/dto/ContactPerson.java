package de.uni_passau.facultyinfo.server.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContactPerson {
	private String id;
	private String name;
	private String office;
	private String phone;
	private String email;
	private String description;
	@XmlTransient
	private ContactGroup contactGroup;

	public ContactPerson(String id, String name, String office, String phone,
			String email, String description, ContactGroup contactGroup) {
		super();
		this.id = id;
		this.name = name;
		this.office = office;
		this.phone = phone;
		this.email = email;
		this.description = description;
		this.contactGroup = contactGroup;
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

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}
}
