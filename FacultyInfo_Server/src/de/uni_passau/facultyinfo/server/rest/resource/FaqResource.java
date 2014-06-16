package de.uni_passau.facultyinfo.server.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uni_passau.facultyinfo.server.dao.FaqDAO;
import de.uni_passau.facultyinfo.server.dataloader.FaqLoader;
import de.uni_passau.facultyinfo.server.dto.Faq;
import de.uni_passau.facultyinfo.server.dto.FaqCategory;

@Path("/faq")
public class FaqResource {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FaqCategory> getFaqs() {
		FaqDAO faqDAO = new FaqDAO();
		return faqDAO.getFaqList();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Faq getFaq(@PathParam("id") String id) {
		FaqDAO faqDAO = new FaqDAO();
		Faq faq = faqDAO.getFaq(id);
		if (faq == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return faq;
	}

	@Path("/load")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public int loadNews() {
		FaqLoader faqLoader = new FaqLoader();
		return faqLoader.loadFaqs();
	}
}