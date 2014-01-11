package de.uni_passau.facultyinfo.server.dataloader;

import java.sql.Time;
import java.util.Calendar;
import java.util.UUID;

import de.uni_passau.facultyinfo.server.dao.BusinessHoursDAO;
import de.uni_passau.facultyinfo.server.dto.BusinessHours;
import de.uni_passau.facultyinfo.server.dto.BusinessHoursFacility;

public class BusinessHoursLoader {
	public String load() {
		String result = "";
		BusinessHoursDAO businesshoursDAO = new BusinessHoursDAO();
		result += "Deleting from Table businesshours";
		businesshoursDAO.deleteAllBusinessHours();
		result += " -- done\n";
		result += "Deleting from Table businesshoursfacilities";
		businesshoursDAO.deleteAllFacilities();;
		result += " -- done\n";

		result += "Loading business hours...\n";

		BusinessHoursFacility facility = null;
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Zentralbibliothek", BusinessHoursFacility.TYPE_LIBRARY);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(9), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(11), time(22)));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Nikolakloster", BusinessHoursFacility.TYPE_LIBRARY);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(9), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(11), time(19)));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "WiWi", BusinessHoursFacility.TYPE_LIBRARY);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(9), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(11), time(19)));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Juridikum", BusinessHoursFacility.TYPE_LIBRARY);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(24)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(9), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(11), time(19)));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "FIM", BusinessHoursFacility.TYPE_LIBRARY);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(18)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(18)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(18)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(18)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(18)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Cafeteria Audimax", BusinessHoursFacility.TYPE_CAFETERIA);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(7, 45), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(7, 45), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(7, 45), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(7, 45), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(7, 45), time(14, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Cafeteria Nikola", BusinessHoursFacility.TYPE_CAFETERIA);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(17)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(14, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(14, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(14, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(14, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(14, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Kulturcafé", BusinessHoursFacility.TYPE_CAFETERIA);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(22)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Cafeteria Mensa", BusinessHoursFacility.TYPE_CAFETERIA);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(7, 30), time(19)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(7, 30), time(19)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(7, 30), time(19)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(7, 30), time(19)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(7, 30), time(15, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(9, 30), time(15)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(17, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(17, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(17, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(17, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(8), time(15, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Café UniCa", BusinessHoursFacility.TYPE_CAFETERIA);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(16)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(16)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(16)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(8), time(16)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(8), time(13, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		
		facility = new BusinessHoursFacility(UUID.randomUUID().toString(), "Mensa", BusinessHoursFacility.TYPE_CAFETERIA);
		businesshoursDAO.createFacility(facility);
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(11), time(14)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(11), time(14)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(11), time(14)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN,  time(11), time(14)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_OPEN, time(11), time(14)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_SEMESTER, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.MONDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(11, 15), time(13, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.TUESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(11, 15), time(13, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.WEDNESDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(11, 15), time(13, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.THURSDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(11, 15), time(13, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.FRIDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_OPEN, time(11, 15), time(13, 30)));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SATURDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));
		businesshoursDAO.createBusinessHours(new BusinessHours(UUID.randomUUID().toString(), facility, BusinessHours.SUNDAY, BusinessHours.PHASE_BREAK, BusinessHours.STATUS_CLOSED, null, null));

		result += "done";
		return result;
	}

	private Time time(int hour) {
		return time(hour, 0);
	}

	private Time time(int hour, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		return new Time(calendar.getTimeInMillis());
	}

}