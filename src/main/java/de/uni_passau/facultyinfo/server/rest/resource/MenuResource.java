package de.uni_passau.facultyinfo.server.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uni_passau.facultyinfo.server.dao.MenuDAO;
import de.uni_passau.facultyinfo.server.dataloader.MenuLoader;
import de.uni_passau.facultyinfo.server.dto.MenuItem;

@Path("/menu")
public class MenuResource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MenuItem> getMenuItems() {
		MenuDAO menuDAO = new MenuDAO();
		return menuDAO.getMenuItems();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuItem getMenuItem(@PathParam("id") String id) {
		MenuDAO menuDAO = new MenuDAO();
		MenuItem menuItem = menuDAO.getMenuItem(id);
		if (menuItem == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return menuItem;
	}

	@GET
	@Path("/load")
	@Produces(MediaType.TEXT_PLAIN)
	public int load() {
		MenuLoader menuLoader = new MenuLoader();
		return menuLoader.load();
	}
}