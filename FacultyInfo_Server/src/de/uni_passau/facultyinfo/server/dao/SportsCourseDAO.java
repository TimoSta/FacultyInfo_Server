package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.SportsCourse;
import de.uni_passau.facultyinfo.server.dto.SportsCourseCategory;

public class SportsCourseDAO {

	public List<SportsCourseCategory> getSportsCourseCategories() {
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, title FROM sportscoursecategories ORDER BY title");
		if (resultSet == null) {
			return null;
		}

		try {
			ArrayList<SportsCourseCategory> sportsCourseCategories = mapResultSetToSportsCourseCategories(resultSet);
			return sportsCourseCategories;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public SportsCourseCategory getSportsCourseCategory(String id) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, id);
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, title FROM sportscoursecategories WHERE id = ?",
				attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			SportsCourseCategory sportsCourseCategory = mapResultSetToSportsCourseCategory(resultSet);

			ResultSet sportsCourseResultSet = JDBCConnection
					.getInstance()
					.executeSelect(
							"SELECT id, category, number, details, dayofweek, starttime, endtime, location, startdate, enddate, host, price, status FROM sportscourses WHERE category = ? ORDER BY details, dayofweek, starttime",
							attributes);
			sportsCourseCategory.setSportsCourses(mapResultSetToSportsCourses(
					sportsCourseResultSet, sportsCourseCategory));

			return sportsCourseCategory;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SportsCourseCategory> getSportsCourseCategoriesToday() {
		List<SportsCourseCategory> sportsCourseCategories = getFullSportsCourseCategories();
		List<SportsCourseCategory> todaysSportsCourseCategories = new ArrayList<SportsCourseCategory>();
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		for (SportsCourseCategory sportsCourseCategory : sportsCourseCategories) {
			boolean hasSportsCourseToday = false;
			for (SportsCourse sportsCourse : sportsCourseCategory
					.getSportsCourses()) {
				sportsCourse.setCategory(null);
				if ((sportsCourse.getStartDate() == null || sportsCourse
						.getStartDate().before(today))
						&& (sportsCourse.getEndDate() == null || sportsCourse
								.getEndDate().after(today))
						&& sportsCourse.getDayOfWeek() == dayOfWeek) {
					hasSportsCourseToday = true;
					break;
				}
			}
			if (hasSportsCourseToday) {
				sportsCourseCategory.setSportsCourses(null);
				todaysSportsCourseCategories.add(sportsCourseCategory);
			}
		}

		return Collections.unmodifiableList(todaysSportsCourseCategories);
	}

	public SportsCourseCategory getSportsCourseCategoryToday(String categoryId) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, categoryId);
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, title FROM sportscoursecategories WHERE id = ?",
				attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			SportsCourseCategory sportsCourseCategory = mapResultSetToSportsCourseCategory(resultSet);

			ResultSet sportsCourseResultSet = JDBCConnection
					.getInstance()
					.executeSelect(
							"SELECT id, category, number, details, dayofweek, starttime, endtime, location, startdate, enddate, host, price, status FROM sportscourses WHERE category = ? ORDER BY details, dayofweek, starttime",
							attributes);

			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

			List<SportsCourse> allSportsCourses = mapResultSetToSportsCourses(
					sportsCourseResultSet, sportsCourseCategory);

			List<SportsCourse> todaysSportsCourses = new ArrayList<SportsCourse>();
			for (SportsCourse sportsCourse : allSportsCourses) {
				sportsCourse.setCategory(null);
				if ((sportsCourse.getStartDate() == null || sportsCourse
						.getStartDate().before(today))
						&& (sportsCourse.getEndDate() == null || sportsCourse
								.getEndDate().after(today))
						&& sportsCourse.getDayOfWeek() == dayOfWeek) {
					todaysSportsCourses.add(sportsCourse);
					sportsCourse.setCategory(sportsCourseCategory);
				}
			}
			if (!todaysSportsCourses.isEmpty()) {
				sportsCourseCategory.setSportsCourses(Collections
						.unmodifiableList(todaysSportsCourses));
			}

			return sportsCourseCategory;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public SportsCourse getSportsCourse(String id) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, id);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, category, number, details, dayofweek, starttime, endtime, location, startdate, enddate, host, price, status FROM sportscourses WHERE id = ?",
						attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToSportsCourse(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SportsCourseCategory> findSportsCourseCategories(
			String searchString) {
		ArrayList<SportsCourseCategory> searchResults = new ArrayList<SportsCourseCategory>();

		if (searchString != null && !searchString.isEmpty()) {

			List<SportsCourseCategory> sportsCourseCategories = getFullSportsCourseCategories();
			Pattern pattern = Pattern.compile(searchString,
					Pattern.CASE_INSENSITIVE + Pattern.LITERAL);

			for (SportsCourseCategory sportsCourseCategory : sportsCourseCategories) {
				if (sportsCourseCategory.getTitle() != null
						&& pattern.matcher(sportsCourseCategory.getTitle())
								.find()) {
					searchResults.add(sportsCourseCategory);
				} else {
					ArrayList<SportsCourse> matchingSportsCourses = new ArrayList<SportsCourse>();
					for (SportsCourse sportsCourse : sportsCourseCategory
							.getSportsCourses()) {
						sportsCourse.setCategory(null);
						if ((sportsCourse.getDetails() != null && pattern
								.matcher(sportsCourse.getDetails()).find())
								|| (sportsCourse.getHost() != null && pattern
										.matcher(sportsCourse.getHost()).find())) {
							matchingSportsCourses.add(sportsCourse);
							sportsCourse.setCategory(sportsCourseCategory);
						}
					}
					if (!matchingSportsCourses.isEmpty()) {
						sportsCourseCategory.setSportsCourses(Collections
								.unmodifiableList(matchingSportsCourses));
						searchResults.add(sportsCourseCategory);
					}
				}
			}

			return Collections.unmodifiableList(searchResults);
		}

		return searchResults;
	}

	public void deleteAllSportsCourseCategories() {
		JDBCConnection.getInstance().executeStatement(
				"DELETE FROM sportscoursecategories");
	}

	public void deleteAllSportsCourses() {
		JDBCConnection.getInstance().executeStatement(
				"DELETE FROM sportscourses");
	}

	public boolean createSportsCourseCategory(
			SportsCourseCategory sportsCourseCategory) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, sportsCourseCategory.getId());
		attributes.add(2, sportsCourseCategory.getTitle());
		return JDBCConnection.getInstance().executeStatement(
				"INSERT INTO sportscoursecategories (id, title) VALUES (?, ?)",
				attributes) == 1;
	}

	public boolean createSportsCourse(SportsCourse sportsCourse) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, sportsCourse.getId());
		attributes.add(2, sportsCourse.getCategory().getId());
		attributes.add(3, sportsCourse.getNumber());
		attributes.add(4, sportsCourse.getDetails());
		attributes.add(5, sportsCourse.getDayOfWeek());
		attributes.add(6, sportsCourse.getStartTime());
		attributes.add(7, sportsCourse.getEndTime());
		attributes.add(8, sportsCourse.getLocation());
		attributes.add(9, sportsCourse.getStartDate());
		attributes.add(10, sportsCourse.getEndDate());
		attributes.add(11, sportsCourse.getHost());
		attributes.add(12, sportsCourse.getPrice());
		attributes.add(13, sportsCourse.getStatus());
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO sportscourses (id, category, number, details, dayofweek, starttime, endtime, location, startdate, enddate, host, price, status) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
						attributes) == 1;
	}

	private List<SportsCourseCategory> getFullSportsCourseCategories() {
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, title FROM sportscoursecategories ORDER BY title");
		if (resultSet == null) {
			return null;
		}

		try {
			ArrayList<SportsCourseCategory> sportsCourseCategories = mapResultSetToSportsCourseCategories(resultSet);

			for (SportsCourseCategory sportsCourseCategory : sportsCourseCategories) {
				AttributeContainer attributes = new AttributeContainer();
				attributes.add(1, sportsCourseCategory.getId());
				ResultSet sportsCourseResultSet = JDBCConnection
						.getInstance()
						.executeSelect(
								"SELECT id, category, number, details, dayofweek, starttime, endtime, location, startdate, enddate, host, price, status FROM sportscourses WHERE category = ? ORDER BY details, dayofweek, starttime",
								attributes);
				if (sportsCourseResultSet == null) {
					continue;
				}

				sportsCourseCategory
						.setSportsCourses(mapResultSetToSportsCourses(
								sportsCourseResultSet, sportsCourseCategory));
			}

			return sportsCourseCategories;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<SportsCourseCategory> mapResultSetToSportsCourseCategories(
			ResultSet resultSet) throws SQLException {
		ArrayList<SportsCourseCategory> sportsCourseCategories = new ArrayList<SportsCourseCategory>();
		while (resultSet.next()) {
			SportsCourseCategory sportsCourseCategory = new SportsCourseCategory(
					resultSet.getString("id"), resultSet.getString("title"));
			sportsCourseCategories.add(sportsCourseCategory);
		}

		return sportsCourseCategories;
	}

	private SportsCourseCategory mapResultSetToSportsCourseCategory(
			ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			SportsCourseCategory sportsCourseCategory = new SportsCourseCategory(
					resultSet.getString("id"), resultSet.getString("title"));
			return sportsCourseCategory;
		}

		return null;
	}

	private ArrayList<SportsCourse> mapResultSetToSportsCourses(
			ResultSet resultSet, SportsCourseCategory sportsCourseCategory)
			throws SQLException {
		ArrayList<SportsCourse> sportsCourses = new ArrayList<SportsCourse>();
		while (resultSet.next()) {
			SportsCourse sportsCourse = new SportsCourse(
					resultSet.getString("id"), sportsCourseCategory,
					resultSet.getString("number"),
					resultSet.getString("details"),
					resultSet.getInt("dayofweek"),
					resultSet.getTime("starttime"),
					resultSet.getTime("endTime"),
					resultSet.getString("location"),
					resultSet.getDate("startdate"),
					resultSet.getDate("enddate"), resultSet.getString("host"),
					resultSet.getDouble("price"), resultSet.getInt("status"));
			sportsCourses.add(sportsCourse);
		}
		return sportsCourses;
	}

	private SportsCourse mapResultSetToSportsCourse(ResultSet resultSet)
			throws SQLException {
		if (resultSet.next()) {
			SportsCourse sportsCourse = new SportsCourse(
					resultSet.getString("id"), null,
					resultSet.getString("number"),
					resultSet.getString("details"),
					resultSet.getInt("dayofweek"),
					resultSet.getTime("starttime"),
					resultSet.getTime("endTime"),
					resultSet.getString("location"),
					resultSet.getDate("startdate"),
					resultSet.getDate("enddate"), resultSet.getString("host"),
					resultSet.getDouble("price"), resultSet.getInt("status"));
			return sportsCourse;
		}
		return null;
	}
}
