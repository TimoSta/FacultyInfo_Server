package de.uni_passau.facultyinfo.server.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.uni_passau.facultyinfo.server.dao.MapMarkerDAO;
import de.uni_passau.facultyinfo.server.dto.MapMarkerCategory;

@Path("/mapmarker")
public class MapMarkerResource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MapMarkerCategory> getMapMarkers() {
		MapMarkerDAO mapMarkerDAO = new MapMarkerDAO();
		return mapMarkerDAO.getMapMarkerList();
	}
}