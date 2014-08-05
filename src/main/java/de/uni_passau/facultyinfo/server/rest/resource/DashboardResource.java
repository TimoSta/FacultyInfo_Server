package de.uni_passau.facultyinfo.server.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.uni_passau.facultyinfo.server.dao.BusLineDAO;
import de.uni_passau.facultyinfo.server.dao.MenuDAO;
import de.uni_passau.facultyinfo.server.dao.NewsDAO;
import de.uni_passau.facultyinfo.server.dto.BusLine;
import de.uni_passau.facultyinfo.server.dto.Dashboard;
import de.uni_passau.facultyinfo.server.dto.MenuItem;
import de.uni_passau.facultyinfo.server.dto.News;

@Path("/dashboard")
public class DashboardResource {
	private static final int NEWS_LIMIT = 1;
	private static final int BUSLINE_LIMIT = 2;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Dashboard getDashboard() {
		NewsDAO newsDAO = new NewsDAO();
		List<News> newsList = newsDAO.getNewsList(NEWS_LIMIT);
		News news = newsList != null && newsList.size() > 0 ? newsList.get(0)
				: null;

		BusLineDAO busLineDAO = new BusLineDAO();
		List<BusLine> busLines = busLineDAO.getBusLines(BUSLINE_LIMIT);

		MenuDAO menuDAO = new MenuDAO();
		List<MenuItem> menuItems = menuDAO.getMenuItems(MenuItem.TYPE_MAIN,
				true);

		Dashboard dashboard = new Dashboard(news, busLines, menuItems);

		return dashboard;
	}
}