package de.uni_passau.facultyinfo.server.rest.ressource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uni_passau.facultyinfo.server.dao.SportsCourseDAO;
import de.uni_passau.facultyinfo.server.dataloader.SportsCourseLoader;
import de.uni_passau.facultyinfo.server.dto.SportsCourse;
import de.uni_passau.facultyinfo.server.dto.SportsCourseCategory;

@Path("/sportscourse")
public class SportsCourseRessource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SportsCourseCategory> getSportsCourses() {
		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
		return sportsCourseDAO.getSportsCourses();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SportsCourse getSportsCourse(@PathParam("id") String id) {
		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
		SportsCourse sportsCourse = sportsCourseDAO.getSportsCourse(id);
		if (sportsCourse == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return sportsCourse;
	}

	@Path("/load")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String load() {
		SportsCourseLoader sportsCourseLoader = new SportsCourseLoader();
		return sportsCourseLoader.load();
	}
}