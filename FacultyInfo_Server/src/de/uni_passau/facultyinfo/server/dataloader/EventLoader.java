package de.uni_passau.facultyinfo.server.dataloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import de.uni_passau.facultyinfo.server.dao.EventDAO;
import de.uni_passau.facultyinfo.server.dao.MetadataDAO;
import de.uni_passau.facultyinfo.server.dto.Event;
import de.uni_passau.facultyinfo.server.dto.Metadata;

public class EventLoader {
	public int load() {
		int status = 0;

		MetadataDAO metadataDAO = new MetadataDAO();
		Metadata eventMetadata = metadataDAO.getMetadata(Metadata.NAME_EVENTS);

		if (eventMetadata != null && eventMetadata.getSourceUrl() != null
				&& !eventMetadata.getSourceUrl().isEmpty()) {
			boolean returnValue = true;

			try {
				URL icalUrl = new URL(eventMetadata.getSourceUrl());
				URLConnection conn = icalUrl.openConnection();
				InputStream is = conn.getInputStream();
				ICalendar ical = Biweekly.parse(is).first();

				EventDAO eventDAO = new EventDAO();
				eventDAO.deleteAllEvents();

				for (VEvent eventElement : ical.getEvents()) {
					if (eventElement.getDateStart() != null
							&& eventElement.getDateStart().getValue()
									.after(new Date())) {
						String id = UUID.randomUUID().toString();
						String title = eventElement.getSummary() == null ? null
								: eventElement.getSummary().getValue();
						String subtitle = null;
						String location = eventElement.getLocation() == null ? null
								: eventElement.getLocation().getValue();
						String description = eventElement.getDescription() == null ? null
								: eventElement.getDescription().getValue()
										.replaceAll("(^|\r\n)(r\r\n)+", "\r\n")
										.trim();
						if (description != null && description.isEmpty()) {
							description = null;
						}
						Date startDate = eventElement.getDateStart().getValue();
						Date endDate = eventElement == null ? null
								: eventElement.getDateEnd().getValue();
						String host = eventElement.getOrganizer() == null ? null
								: eventElement.getOrganizer().getCommonName();
						String url = null;
						Event event = new Event(id, title, subtitle, location,
								description, startDate, endDate, host, url);
						returnValue = returnValue
								&& eventDAO.createEvent(event);
					}
				}
			} catch (IOException e) {
				returnValue = false;
				e.printStackTrace();
			}

			status = returnValue ? 0 : 1;
		} else {
			status = 2;
		}

		metadataDAO.updateStatuscode(Metadata.NAME_EVENTS, status);

		return status;
	}

	public static void main(String[] args) {
		EventLoader el = new EventLoader();
		System.out.println(el.load());
	}
}
