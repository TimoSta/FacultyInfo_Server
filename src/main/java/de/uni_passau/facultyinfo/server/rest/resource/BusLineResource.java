package de.uni_passau.facultyinfo.server.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.uni_passau.facultyinfo.server.dao.BusLineDAO;
import de.uni_passau.facultyinfo.server.dataloader.BusLineLoader;
import de.uni_passau.facultyinfo.server.dto.BusLine;

@Path("/busline")
public class BusLineResource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BusLine> getEvents() {
		BusLineDAO busLineDAO = new BusLineDAO();
		return busLineDAO.getBusLines();
	}

	@Path("/load")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public int load() {
		BusLineLoader busLineLoader = new BusLineLoader();
		return busLineLoader.load();
	}
}