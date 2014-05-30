package de.uni_passau.facultyinfo.server.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

import de.uni_passau.facultyinfo.server.rest.resource.BusLineResource;
import de.uni_passau.facultyinfo.server.rest.resource.BusinessHoursResource;
import de.uni_passau.facultyinfo.server.rest.resource.ContactPersonResource;
import de.uni_passau.facultyinfo.server.rest.resource.EventResource;
import de.uni_passau.facultyinfo.server.rest.resource.FaqResource;
import de.uni_passau.facultyinfo.server.rest.resource.MapMarkerResource;
import de.uni_passau.facultyinfo.server.rest.resource.MenuResource;
import de.uni_passau.facultyinfo.server.rest.resource.NewsResource;
import de.uni_passau.facultyinfo.server.rest.resource.SportsCourseResource;

@ApplicationPath("/")
public class FacultiyInfoApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(NewsResource.class);
		classes.add(FaqResource.class);
		classes.add(EventResource.class);
		classes.add(BusLineResource.class);
		classes.add(MapMarkerResource.class);
		classes.add(SportsCourseResource.class);
		classes.add(ContactPersonResource.class);
		classes.add(BusinessHoursResource.class);
		classes.add(MenuResource.class);
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		final Set<Object> instances = new HashSet<Object>();
		instances.add(new JacksonFeature());
		instances.add(new LoggingFilter());
		return instances;
	}
}