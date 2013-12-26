package de.uni_passau.facultyinfo.server.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class Faq {
	private String id;
	@XmlTransient
	private FaqCategory category;
	private String title;
	private String text;

	public Faq(String id, FaqCategory category, String title, String text) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FaqCategory getCategory() {
		return category;
	}

	public void setCategory(FaqCategory category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
