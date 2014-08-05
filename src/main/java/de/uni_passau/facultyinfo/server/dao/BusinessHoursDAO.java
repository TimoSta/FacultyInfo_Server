package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.BusinessHours;
import de.uni_passau.facultyinfo.server.dto.BusinessHoursFacility;

public class BusinessHoursDAO {

	public List<BusinessHoursFacility> getLibraries() {
		return getFacilities(BusinessHoursFacility.TYPE_LIBRARY);
	}

	public List<BusinessHoursFacility> getCafeterias() {
		return getFacilities(BusinessHoursFacility.TYPE_CAFETERIA);
	}

	private List<BusinessHoursFacility> getFacilities(int type) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, type);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, name, type FROM businesshoursfacilities WHERE type = ? ORDER BY name",
						attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToFacilities(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BusinessHoursFacility getFacility(String id) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, id);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, name, type FROM businesshoursfacilities WHERE id = ?",
						attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			BusinessHoursFacility facility = mapResultSetToFacility(resultSet);

			ResultSet openingHoursResultSet = JDBCConnection
					.getInstance()
					.executeSelect(
							"SELECT id, facility, dayofweek, phase, status, openingtime, closingtime FROM businesshours WHERE facility = ?",
							attributes);
			facility.setBusinessHours(mapResultSetToBusinessHours(
					openingHoursResultSet, facility));

			return facility;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<BusinessHoursFacility> mapResultSetToFacilities(
			ResultSet resultSet) throws SQLException {
		ArrayList<BusinessHoursFacility> facilities = new ArrayList<BusinessHoursFacility>();
		while (resultSet.next()) {
			BusinessHoursFacility facility = new BusinessHoursFacility(
					resultSet.getString("id"), resultSet.getString("name"),
					resultSet.getInt("type"));
			facilities.add(facility);
		}
		return facilities;
	}

	private BusinessHoursFacility mapResultSetToFacility(ResultSet resultSet)
			throws SQLException {
		if (resultSet.next()) {
			BusinessHoursFacility facility = new BusinessHoursFacility(
					resultSet.getString("id"), resultSet.getString("name"),
					resultSet.getInt("type"));
			return facility;
		}
		return null;
	}

	private ArrayList<BusinessHours> mapResultSetToBusinessHours(
			ResultSet resultSet, BusinessHoursFacility facility)
			throws SQLException {
		ArrayList<BusinessHours> businessHoursList = new ArrayList<BusinessHours>();
		while (resultSet.next()) {
			BusinessHours businessHours = new BusinessHours(
					resultSet.getString("id"), facility,
					resultSet.getInt("dayofweek"), resultSet.getInt("phase"),
					resultSet.getInt("status"),
					resultSet.getTime("openingtime"),
					resultSet.getTime("closingtime"));
			businessHoursList.add(businessHours);
		}
		return businessHoursList;
	}
}
