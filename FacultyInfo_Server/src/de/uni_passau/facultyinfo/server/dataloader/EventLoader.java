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
import de.uni_passau.facultyinfo.server.dto.Event;

public class EventLoader {
	public String load() {
		String result = "";

		try {
			URL icalUrl = new URL(
					"http://www.uni-passau.de/universitaet/universitaet-im-ueberblick/neues-vom-campus/veranstaltungskalender/?type=150&tx_cal_controller%5Bcalendar%5D=9&tx_cal_controller%5Bview%5D=ics");
			URLConnection conn = icalUrl.openConnection();
			InputStream is = conn.getInputStream();
			ICalendar ical = Biweekly.parse(is).first();

			EventDAO eventDAO = new EventDAO();
			result += "Deleting from Table events";
			eventDAO.deleteAllEvents();
			result += " -- done\n";
			result += "Loading events...\n";

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
					Date endDate = eventElement == null ? null : eventElement
							.getDateEnd().getValue();
					String host = eventElement.getOrganizer() == null ? null
							: eventElement.getOrganizer().getCommonName();
					String url = null;
					Event event = new Event(id, title, subtitle, location,
							description, startDate, endDate, host, url);
					result += event.getTitle();
					if (eventDAO.createEvent(event)) {
						result += " -- done\n";
					} else {
						result += " -- error\n";
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		EventLoader el = new EventLoader();
		System.out.println(el.load());
	}
}
