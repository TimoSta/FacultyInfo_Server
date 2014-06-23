package de.uni_passau.facultyinfo.server.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uni_passau.facultyinfo.server.dao.BusinessHoursDAO;
import de.uni_passau.facultyinfo.server.dto.BusinessHoursFacility;

@Path("/businesshours")
public class BusinessHoursResource {
	@GET
	@Path("/cafeteria")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BusinessHoursFacility> getCafeterias() {
		BusinessHoursDAO businessHoursDAO = new BusinessHoursDAO();
		return businessHoursDAO.getCafeterias();
	}

	@GET
	@Path("/library")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BusinessHoursFacility> getLibraries() {
		BusinessHoursDAO businessHoursDAO = new BusinessHoursDAO();
		return businessHoursDAO.getLibraries();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BusinessHoursFacility getFacility(@PathParam("id") String id) {
		BusinessHoursDAO businessHoursDAO = new BusinessHoursDAO();
		BusinessHoursFacility facility = businessHoursDAO.getFacility(id);
		if (facility == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return facility;
	}
}