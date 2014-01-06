package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.SportsCourse;
import de.uni_passau.facultyinfo.server.dto.SportsCourseCategory;

public class SportsCourseDAO {

	public List<SportsCourseCategory> getSportsCourses() {
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, title FROM sportscoursecategories");
		if (resultSet == null) {
			return null;
		}

		try {
			ArrayList<SportsCourseCategory> sportsCourseCategories = mapResultSetToSportsCourseCategories(resultSet);

			for (SportsCourseCategory sportsCourseCategory : sportsCourseCategories) {
				ArrayList<String> attributes = new ArrayList<String>();
				attributes.add(sportsCourseCategory.getId());
				ResultSet sportsCourseResultSet = JDBCConnection
						.getInstance()
						.executeSelect(
								"SELECT id, details, number, dayofweek FROM sportscourses WHERE category = ?",
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

	public SportsCourse getSportsCourse(String id) {
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add(id);
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

	private ArrayList<SportsCourse> mapResultSetToSportsCourses(
			ResultSet resultSet, SportsCourseCategory sportsCourseCategory)
			throws SQLException {
		ArrayList<SportsCourse> sportsCourses = new ArrayList<SportsCourse>();
		while (resultSet.next()) {
			SportsCourse sportsCourse = new SportsCourse(
					resultSet.getString("id"), sportsCourseCategory,
					resultSet.getString("number"), resultSet.getString("details"),
					resultSet.getInt("dayofweek"), null, null, null, null,
					null, null, SportsCourse.PRICE_NOT_AVAILABLE,
					SportsCourse.STATUS_NOT_AVAILABLE);
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

	public void deleteAllSportsCourseCategories() {
		JDBCConnection.getInstance().executeStatement(
				"DELETE FROM sportscoursecategories");
	}

	public void deleteAllSportsCourses() {
		JDBCConnection.getInstance().executeStatement(
				"DELETE FROM sportscourses");
	}

}
