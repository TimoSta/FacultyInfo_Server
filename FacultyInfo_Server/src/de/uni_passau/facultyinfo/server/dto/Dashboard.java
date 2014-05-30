package de.uni_passau.facultyinfo.server.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dashboard {
	private News news;
	private List<BusLine> busLines;
	private List<MenuItem> menuItems;

	public Dashboard(News news, List<BusLine> busLines, List<MenuItem> menuItems) {
		super();
		this.news = news;
		this.busLines = busLines;
		this.menuItems = menuItems;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<BusLine> getBusLines() {
		return busLines;
	}

	public void setBusLines(List<BusLine> busLines) {
		this.busLines = busLines;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

}
