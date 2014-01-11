package de.uni_passau.facultyinfo.server.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

import de.uni_passau.facultyinfo.server.rest.ressource.BusLineRessource;
import de.uni_passau.facultyinfo.server.rest.ressource.BusinessHoursRessource;
import de.uni_passau.facultyinfo.server.rest.ressource.ContactPersonRessource;
import de.uni_passau.facultyinfo.server.rest.ressource.EventRessource;
import de.uni_passau.facultyinfo.server.rest.ressource.FaqRessource;
import de.uni_passau.facultyinfo.server.rest.ressource.MapMarkerRessource;
import de.uni_passau.facultyinfo.server.rest.ressource.NewsRessource;
import de.uni_passau.facultyinfo.server.rest.ressource.SportsCourseRessource;

@ApplicationPath("/")
public class FacultiyInfoApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(NewsRessource.class);
		classes.add(FaqRessource.class);
		classes.add(EventRessource.class);
		classes.add(BusLineRessource.class);
		classes.add(MapMarkerRessource.class);
		classes.add(SportsCourseRessource.class);
		classes.add(ContactPersonRessource.class);
		classes.add(BusinessHoursRessource.class);
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