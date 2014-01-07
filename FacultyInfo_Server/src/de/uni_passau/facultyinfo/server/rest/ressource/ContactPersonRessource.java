package de.uni_passau.facultyinfo.server.rest.ressource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uni_passau.facultyinfo.server.dao.ContactPersonDAO;
import de.uni_passau.facultyinfo.server.dataloader.ContactPersonLoader;
import de.uni_passau.facultyinfo.server.dto.ContactGroup;

@Path("/contactperson")
public class ContactPersonRessource {
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

	@Path("/load")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadNews() {
		ContactPersonLoader contactPersonLoader = new ContactPersonLoader();
		return contactPersonLoader.load();
	}
}