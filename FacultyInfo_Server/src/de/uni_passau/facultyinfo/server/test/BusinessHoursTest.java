//package de.uni_passau.facultyinfo.server.test;
//
//import java.sql.Time;
//import java.util.Date;
//import java.util.UUID;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//import de.uni_passau.facultyinfo.server.dto.BusinessHours;
//
//@RunWith(JUnit4.class)
//public class BusinessHoursTest {
//
//	@Test
//	public final void testBusinessHours() {
//		String id = UUID.randomUUID().toString();
//		Date day = new Date();
//		String facility = "Location";
//		int type = BusinessHours.TYPE_CAFETERIA;
//		Time openingTime = new Time(8, 0, 0);
//		Time closingTime = new Time(18, 0, 0);
//
//		BusinessHours businessHours = new BusinessHours(id, day, facility,
//				type, openingTime, closingTime);
//
//		Assert.assertEquals("Getter for Id not working correctly.",
//				businessHours.getId(), id);
//		Assert.assertEquals("Getter for Day not working correctly.",
//				businessHours.getDay(), day);
//		Assert.assertEquals("Getter for Facility not working correctly.",
//				businessHours.getFacility(), facility);
//		Assert.assertEquals("Getter for Type not working correctly.",
//				businessHours.getType(), type);
//		Assert.assertEquals("Getter for Opening Time not working correctly.",
//				businessHours.getOpeningTime(), openingTime);
//		Assert.assertEquals("Getter for Closing Time not working correctly.",
//				businessHours.getClosingTime(), closingTime);
//	}
//
//}
