package de.uni_passau.facultyinfo.server.rest.resource;

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
import de.uni_passau.facultyinfo.server.dto.SportsCourceSearchResponse;
import de.uni_passau.facultyinfo.server.dto.SportsCourse;
import de.uni_passau.facultyinfo.server.dto.SportsCourseCategory;

@Path("/sportscourse")
public class SportsCourseResource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SportsCourseCategory> getSportsCourseCategories() {
		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
		return sportsCourseDAO.getSportsCourseCategories();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SportsCourseCategory getSportsCourseCategory(
			@PathParam("id") String id) {
		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
		SportsCourseCategory sportsCourseCategory = sportsCourseDAO
				.getSportsCourseCategory(id);
		if (sportsCourseCategory == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return sportsCourseCategory;
	}

	@Path("/today")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SportsCourseCategory> getSportsCourseCategoriesToday() {
		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
		List<SportsCourseCategory> sportsCourseCategories = sportsCourseDAO
				.getSportsCourseCategoriesToday();
		return sportsCourseCategories;
	}

	@Path("/today/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SportsCourseCategory getSportsCourseCategoryToday(
			@PathParam("id") String id) {
		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
		SportsCourseCategory sportsCourseCategory = sportsCourseDAO
				.getSportsCourseCategoryToday(id);
		if (sportsCourseCategory == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return sportsCourseCategory;
	}

	@Path("/course/{id}")
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

	@Path("/find/{searchString}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SportsCourceSearchResponse findSportsCoursesV2(
			@PathParam("searchString") String searchString) {
		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
		SportsCourceSearchResponse response = sportsCourseDAO
				.find(searchString);
		return response;
	}

	@Path("/load")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String load() {
		SportsCourseLoader sportsCourseLoader = new SportsCourseLoader();
		return sportsCourseLoader.load();
	}
}