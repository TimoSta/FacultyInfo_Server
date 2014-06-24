package de.uni_passau.facultyinfo.server.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uni_passau.facultyinfo.server.dao.ContactPersonDAO;
import de.uni_passau.facultyinfo.server.dto.ContactGroup;
import de.uni_passau.facultyinfo.server.dto.ContactPerson;
import de.uni_passau.facultyinfo.server.dto.ContactSearchResponse;

@Path("/contactperson")
public class ContactPersonResource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContactGroup> getContactGroups() {
		ContactPersonDAO contactPersonDAO = new ContactPersonDAO();
		return contactPersonDAO.getContactGroups();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ContactGroup getContactGroup(@PathParam("id") String id) {
		ContactPersonDAO contactPersonDAO = new ContactPersonDAO();
		ContactGroup contactGroup = contactPersonDAO.getContactGroup(id);
		if (contactGroup == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return contactGroup;
	}

	@Path("/person/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ContactPerson getContactPerson(@PathParam("id") String id) {
		ContactPersonDAO contactPersonDAO = new ContactPersonDAO();
		ContactPerson contactPerson = contactPersonDAO.getContactPerson(id);
		if (contactPerson == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return contactPerson;
	}

	@Path("/find/{searchString}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ContactSearchResponse find(
			@PathParam("searchString") String searchString) {
		ContactPersonDAO contactPersonDAO = new ContactPersonDAO();
		ContactSearchResponse response = contactPersonDAO.find(searchString);
		return response;
	}
}