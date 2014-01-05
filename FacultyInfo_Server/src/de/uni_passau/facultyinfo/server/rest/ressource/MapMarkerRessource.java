package de.uni_passau.facultyinfo.server.rest.ressource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.uni_passau.facultyinfo.server.dao.MapMarkerDAO;
import de.uni_passau.facultyinfo.server.dataloader.MapMarkerLoader;
import de.uni_passau.facultyinfo.server.dto.MapMarkerCategory;

@Path("/mapmarker")
public class MapMarkerRessource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MapMarkerCategory> getMapMarkers() {
		MapMarkerDAO mapMarkerDAO = new MapMarkerDAO();
		return mapMarkerDAO.getMapMarkerList();
	}

	@Path("/load")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String load() {
		MapMarkerLoader mapMarkerLoader = new MapMarkerLoader();
		return mapMarkerLoader.load();
	}
}