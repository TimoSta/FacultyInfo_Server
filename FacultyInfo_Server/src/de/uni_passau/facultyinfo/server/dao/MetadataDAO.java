package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.Metadata;

public class MetadataDAO {

	public Metadata getMetadata(String name) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, name);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT name, isThirdPartyData, lastStatuscode, sourceUrl FROM metadata WHERE name = ?",
						attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToMetadata(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateStatuscode(String name, int statuscode) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, statuscode);
		attributes.add(2, name);
		return JDBCConnection.getInstance().executeStatement(
				"UPDATE metadata SET lastStatuscode = ? WHERE name = ?",
				attributes) == 1;
	}

	private Metadata mapResultSetToMetadata(ResultSet resultSet)
			throws SQLException {
		if (resultSet.next()) {
			Metadata metadata = new Metadata(resultSet.getString("name"),
					resultSet.getBoolean("isThirdPartyData"),
					resultSet.getInt("lastStatuscode"),
					resultSet.getString("sourceUrl"));
			return metadata;
		}
		return null;
	}
}
