package de.uni_passau.facultyinfo.server.dataloader;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.uni_passau.facultyinfo.server.dao.SportsCourseDAO;
import de.uni_passau.facultyinfo.server.dto.SportsCourse;
import de.uni_passau.facultyinfo.server.dto.SportsCourseCategory;

public class SportsCourseLoader {
	private static final String BASE_URL = "http://online.sportz.uni-passau.de/angebote/aktueller_zeitraum/";

	public String load() {
		String result = "";
		SportsCourseDAO sportsCourseDAO = new SportsCourseDAO();
		result += "Deleting from Table sportscourses";
		sportsCourseDAO.deleteAllSportsCourses();
		result += " -- done\n";
		result += "Deleting from Table sportscoursecategories";
		sportsCourseDAO.deleteAllSportsCourseCategories();
		result += " -- done\n";

		Connection connection = Jsoup.connect(BASE_URL + "index.html");
		connection.ignoreContentType(true);
		result += "Loading Sports Courses...\n";

		try {
			Document doc = connection.get();
			Elements sportsCourseCategoryElements = doc.select("dl.bs_menu dd");
			for (Element sportsCourseCategoryElement : sportsCourseCategoryElements) {
				sportsCourseCategoryElement = sportsCourseCategoryElement
						.select("a").get(0);
				String categoryId = UUID.randomUUID().toString();
				String categoryTitle = sportsCourseCategoryElement.text();
				String categoryUrl = BASE_URL
						+ sportsCourseCategoryElement.attr("href");
				SportsCourseCategory category = new SportsCourseCategory(
						categoryId, categoryTitle);
				result += category.getTitle();
				if (sportsCourseDAO.createSportsCourseCategory(category)) {
					result += " -- done\n";
				} else {
					result += " -- error\n";
				}

				System.out.println(category.getTitle());

				Connection subConnection = Jsoup.connect(categoryUrl);
				Document subDocument = subConnection.get();
				Elements sportsCourseElements = subDocument
						.select("table.bs_kurse tbody tr");
				for (Element sportsCourseElement : sportsCourseElements) {
					System.out.println(sportsCourseElement.select("td.bs_sknr")
							.get(0).text());
					// Details
					String details = sportsCourseElement.select("td.bs_sdet")
							.get(0).text();
					if (details.isEmpty()) {
						details = null;
					}

					// Number
					String number = sportsCourseElement.select("td.bs_sknr")
							.get(0).text();

					// Day of Week
					String[] dayOfWeekStrings = sportsCourseElement
							.select("td.bs_stag").get(0).html().split("<br />");
					ArrayList<ArrayList<Integer>> daysOfWeek = new ArrayList<ArrayList<Integer>>();
					for (String dayOfWeekString : dayOfWeekStrings) {
						dayOfWeekString = Jsoup.parse(dayOfWeekString).text();
						if (!dayOfWeekString.isEmpty()) {
							daysOfWeek
									.add(parseDaysOfWeekString(dayOfWeekString));
						}
					}

					// Time
					ArrayList<Time> startTimes = new ArrayList<Time>();
					ArrayList<Time> endTimes = new ArrayList<Time>();
					if (!sportsCourseElement.select("td.bs_szeit").get(0)
							.text().isEmpty()) {
						String[] timeStrings = sportsCourseElement
								.select("td.bs_szeit").get(0).html()
								.split("<br />");

						for (String timeString : timeStrings) {
							timeString = Jsoup.parse(timeString).text();
							if (!timeString.isEmpty()) {
								String[] subStrings = timeString.split("-");
								startTimes.add(parseTime(subStrings[0]));
								endTimes.add(parseTime(subStrings[1]));
							}
						}
					}

					// Location
					ArrayList<String> locations = new ArrayList<String>();
					if (!sportsCourseElement.select("td.bs_sort").get(0).text()
							.isEmpty()) {
						String[] locationStrings = sportsCourseElement
								.select("td.bs_sort").get(0).html()
								.split("<br />");
						for (String location : locationStrings) {
							location = Jsoup.parse(location).text();
							if (!location.isEmpty()) {
								locations.add(location);
							}
						}
					}

					// Date
					String[] dateStrings = sportsCourseElement
							.select("td.bs_szr").get(0).text().split("-");
					SimpleDateFormat dateSdf = new SimpleDateFormat("d.MM.");
					Date startDate = null;
					Date endDate = null;
					try {
						startDate = dateSdf.parse(dateStrings[0]);
						endDate = dateStrings.length < 2 ? startDate : dateSdf
								.parse(dateStrings[1]);

						Calendar cal = Calendar.getInstance();
						cal.setTime(new Date());
						boolean winter = cal.get(Calendar.MONTH) >= 8
								|| cal.get(Calendar.MONTH) <= 2;
						int currentYear = cal.get(Calendar.YEAR);
						currentYear = winter && cal.get(Calendar.MONTH) < 8 ? currentYear - 1
								: currentYear;
						cal.setTime(startDate);
						cal.set(Calendar.YEAR,
								!winter || cal.get(Calendar.MONTH) >= 8 ? currentYear
										: currentYear + 1);
						startDate = cal.getTime();
						cal.setTime(endDate);
						cal.set(Calendar.YEAR,
								!winter || cal.get(Calendar.MONTH) >= 8 ? currentYear
										: currentYear + 1);
						endDate = cal.getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}

					// Host
					String host = sportsCourseElement.select("td.bs_skl")
							.get(0).text();
					if (host.isEmpty()) {
						host = null;
					}

					// Price
					String priceString = sportsCourseElement
							.select("td.bs_spreis").get(0).text();
					Double price = 0.0;
					if (priceString.matches(".+/.+")) {
						priceString = priceString.split("/")[0];
						price = Double.valueOf(priceString);
					}

					// Status
					String statusString = sportsCourseElement
							.select("td.bs_sbuch input,td.bs_sbuch span")
							.get(0).attr("class");
					int status = SportsCourse.STATUS_NOT_AVAILABLE;
					if (statusString.equals("bs_btn_buchen")) {
						status = SportsCourse.STATUS_OPEN;
					} else if (statusString.equals("bs_btn_ausgebucht")) {
						status = SportsCourse.STATUS_FULL;
					} else if (statusString.equals("bs_btn_ohne_anmeldung")) {
						status = SportsCourse.STATUS_NO_SIGNUP_REQUIRED;
					} else if (statusString.equals("bs_btn_abgelaufen")) {
						status = SportsCourse.STATUS_CLOSED;
					} else if (statusString.equals("bs_btn_warteliste")) {
						status = SportsCourse.STATUS_QUEUE;
					} else if (statusString.equals("bs_btn_nur_buero")) {
						status = SportsCourse.STATUS_OFFICE_SIGNUP;
					} else if (statusString.equals("bs_btn_storniert")) {
						status = SportsCourse.STATUS_STORNO;
					} else if (statusString.equals("bs_btn_keine_buchung")) {
						status = SportsCourse.STATUS_NO_SIGNUP_POSSIBLE;
					}

					// Create Sports Course
					int index = 0;
					if (daysOfWeek.isEmpty()) {
						ArrayList<Integer> dummyList = new ArrayList<Integer>();
						dummyList.add(SportsCourse.DATE_NOT_AVAILABLE);
						daysOfWeek.add(dummyList);
					}
					for (ArrayList<Integer> subDaysOfWeek : daysOfWeek) {
						Time startTime = startTimes.size() >= index + 1 ? startTimes
								.get(index) : null;
						Time endTime = endTimes.size() >= index + 1 ? endTimes
								.get(index) : null;
						String location = locations.size() >= index + 1 ? locations
								.get(0) : null;
						for (Integer dayOfWeek : subDaysOfWeek) {
							// Id
							String id = UUID.randomUUID().toString();

							SportsCourse sportsCourse = new SportsCourse(id,
									category, number, details, dayOfWeek,
									startTime, endTime, location, startDate,
									endDate, host, price, status);
							result += " -- " + sportsCourse.getDetails();
							if (sportsCourseDAO
									.createSportsCourse(sportsCourse)) {
								result += " -- done\n";
							} else {
								result += " -- error\n";
							}
						}
						index++;
					}
				}
			}
		} catch (IOException e) {
			result += "error";
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	private ArrayList<Integer> parseDaysOfWeekString(String dayOfWeekString) {
		ArrayList<Integer> daysOfWeek = new ArrayList<Integer>();
		String[] subStrings = dayOfWeekString.split("-");
		if (subStrings.length == 2) {
			int firstDay = parseDayOfWeek(subStrings[0]);
			int lastDay = parseDayOfWeek(subStrings[1]);
			for (int i = firstDay; i <= lastDay; i++) {
				daysOfWeek.add(i);
			}
		} else if (subStrings.length == 1) {
			daysOfWeek.add(parseDayOfWeek(subStrings[0]));
		}
		return daysOfWeek;
	}

	private int parseDayOfWeek(String dayOfWeekString) {
		int dayOfWeek = SportsCourse.DATE_NOT_AVAILABLE;
		if (dayOfWeekString.equals("Mo")) {
			dayOfWeek = SportsCourse.MONDAY;
		} else if (dayOfWeekString.equals("Di")) {
			dayOfWeek = SportsCourse.TUESDAY;
		} else if (dayOfWeekString.equals("Mi")) {
			dayOfWeek = SportsCourse.WEDNESDAY;
		} else if (dayOfWeekString.equals("Do")) {
			dayOfWeek = SportsCourse.THURSDAY;
		} else if (dayOfWeekString.equals("Fr")) {
			dayOfWeek = SportsCourse.FRIDAY;
		} else if (dayOfWeekString.equals("Sa")) {
			dayOfWeek = SportsCourse.SATURDAY;
		} else if (dayOfWeekString.equals("So")) {
			dayOfWeek = SportsCourse.SUNDAY;
		}
		return dayOfWeek;
	}

	private Time parseTime(String timeString) throws ParseException {
		SimpleDateFormat timeSdf = new SimpleDateFormat("H:m");
		Time time = new Time(timeSdf.parse(timeString).getTime());
		return time;
	}
}
