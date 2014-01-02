//package de.uni_passau.facultyinfo.server.dataloader;
//
//import java.io.IOException;
//import java.sql.Time;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.UUID;
//
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import de.uni_passau.facultyinfo.server.dao.SportsCourseDAO;
//import de.uni_passau.facultyinfo.server.dto.SportsCourse;
//import de.uni_passau.facultyinfo.server.dto.SportsCourseCategory;
//
//public class SportsCourseLoader {
//	private static final String BASE_URL = "http://online.sportz.uni-passau.de/angebote/aktueller_zeitraum/";
//
//	public String load() {
//		String result = "";
//		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
//		result += "Deleting from Table sportscourses";
//		sportsCourseDAO.deleteAllFaqs();
//		result += " -- done\n";
//		result += "Deleting from Table sportscoursecategories";
//		sportsCourseDAO.deleteAllFaqCategories();
//		result += " -- done\n";
//
//		Connection connection = Jsoup.connect(BASE_URL + "index.html");
//		connection.ignoreContentType(true);
//		result += "Loading Sports Courses...\n";
//
//		try {
//			Document doc = connection.get();
//			Elements sportsCourseCategoryElements = doc.select("dl.bs_menu dd");
//			for (Element sportsCourseCategoryElement : sportsCourseCategoryElements) {
//				sportsCourseCategoryElement = sportsCourseCategoryElement
//						.select("a").get(0);
//				String categoryId = UUID.randomUUID().toString();
//				String categoryTitle = sportsCourseCategoryElement.text();
//				String categoryUrl = BASE_URL
//						+ sportsCourseCategoryElement.attr("href");
//				SportsCourseCategory sportsCourseCategory = new SportsCourseCategory(
//						categoryId, categoryTitle);
//				result += sportsCourseCategory.getTitle();
//				if (sportsCourseDAO
//						.createSportsCourseCategory(sportsCourseCategory)) {
//					result += " -- done\n";
//				} else {
//					result += " -- error\n";
//				}
//
//				Connection subConnection = Jsoup.connect(categoryUrl);
//				Document subDocument = subConnection.get();
//				Elements sportsCourseElements = subDocument
//						.select("table.bs_kurse tr");
//				for (Element sportsCourseElement : sportsCourseElements) {
//					String sportsCourseId = UUID.randomUUID().toString();
//					String sportsCourseDetails = sportsCourseElement
//							.select("td.bs_sdet").get(0).text();
//					String sportsCourseNumber = sportsCourseElement
//							.select("td.bs_sknr").get(0).text();
//					String sportsCourseDayOfWeekString = sportsCourseElement
//							.select("bs_stag").get(0).text();
//					int sportsCourseDayOfWeek = 0;
//					if (sportsCourseDayOfWeekString.equals("Mo")) {
//						sportsCourseDayOfWeek = 1;
//					} else if (sportsCourseDayOfWeekString.equals("Di")) {
//						sportsCourseDayOfWeek = 2;
//					} else if (sportsCourseDayOfWeekString.equals("Mi")) {
//						sportsCourseDayOfWeek = 3;
//					} else if (sportsCourseDayOfWeekString.equals("Do")) {
//						sportsCourseDayOfWeek = 4;
//					} else if (sportsCourseDayOfWeekString.equals("Fr")) {
//						sportsCourseDayOfWeek = 5;
//					} else if (sportsCourseDayOfWeekString.equals("Sa")) {
//						sportsCourseDayOfWeek = 6;
//					} else if (sportsCourseDayOfWeekString.equals("So")) {
//						sportsCourseDayOfWeek = 7;
//					}
//					String[] timeStrings = sportsCourseElement
//							.select("td.bs_szeit").get(0).text().split("-");
//					SimpleDateFormat timeSdf = new SimpleDateFormat("H:m");
//					Time sportsCourseStartTime = new Time(timeSdf.parse(
//							timeStrings[0]).getTime());
//					Time sportsCourseEndTime = new Time(timeSdf.parse(
//							timeStrings[1]).getTime());
//					String sportsCourseLocation = sportsCourseElement
//							.select("td.bs_sort").get(0).text();
//					String[] dateStrings = sportsCourseElement
//							.select("td.bs_szr").get(0).text().split("-");
//					SimpleDateFormat dateSdf = new SimpleDateFormat("d.MM.");
//					Date sportsCourseStartDate = dateSdf.parse(dateStrings[0]);
//					Date sportsCourseEndDate = dateSdf.parse(dateStrings[1]);
//					Calendar cal = Calendar.getInstance();
//					cal.setTime(new Date());
//					int currentYear = cal.get(Calendar.YEAR);
//					cal.setTime(dateSdf.parse(dateStrings[0]));
//					cal.set(Calendar.YEAR, currentYear);
//					if(sportsCourseEndDate.before(sportsCourseStartDate))
//					
//					SportsCourse sportsCourse = new SportsCourse(
//							sportsCourseId, sportsCourseCategory,
//							sportsCourseNumber, sportsCourseDetails,
//							sportsCourseDayOfWeek, sportsCourseStartTime,
//							sportsCourseEndTime, sportsCourseLocation,
//							startDate, endDate, host, price, status);
//					result += " -- " + faq.getTitle();
//					if (sportsCourseDAO.createFaq(faq)) {
//						result += " -- done\n";
//					} else {
//						result += " -- error\n";
//					}
//				}
//			}
//		} catch (IOException e) {
//			result += "error";
//			e.printStackTrace();
//		}
//		return result;
//	}
//}
