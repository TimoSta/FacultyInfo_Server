package de.uni_passau.facultyinfo.server.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uni_passau.facultyinfo.server.dao.EventDAO;
import de.uni_passau.facultyinfo.server.dataloader.EventLoader;
import de.uni_passau.facultyinfo.server.dto.Event;

@Path("/event")
public class EventResource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEvents() {
		EventDAO eventDAO = new EventDAO();
		return eventDAO.getEvents();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Event getEvent(@PathParam("id") String id) {
		EventDAO eventDAO = new EventDAO();
		Event event = eventDAO.getEvent(id);
		if (event == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return event;
	}

	@GET
	@Path("/find/{searchString}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> find(@PathParam("searchString") String searchString) {
		EventDAO eventDAO = new EventDAO();
		List<Event> events = eventDAO.find(searchString);
		return events;
	}

	@GET
	@Path("/load")
	@Produces(MediaType.TEXT_PLAIN)
	public int load() {
		EventLoader eventLoader = new EventLoader();
		return eventLoader.load();
	}
}