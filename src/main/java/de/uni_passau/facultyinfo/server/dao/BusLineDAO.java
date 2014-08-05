package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.BusLine;

public class BusLineDAO {
	public List<BusLine> getBusLines() {
		return getBusLines(null);
	}

	public List<BusLine> getBusLines(Integer limit) {
		String query = "SELECT id, line, direction, departure FROM buslines WHERE departure BETWEEN NOW() AND (NOW() + INTERVAL 60 MINUTE) ORDER BY departure, line, direction";

		if (limit != null) {
			query += " LIMIT " + Integer.toString(limit);
		}

		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(query);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToBusLines(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean createBusLine(BusLine busLine) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, busLine.getId());
		attributes.add(2, busLine.getLine());
		attributes.add(3, busLine.getDirection());
		attributes.add(4, busLine.getDeparture());
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO buslines (id, line, direction, departure) VALUES (?, ?, ?, ?)",
						attributes) == 1;
	}

	public void deleteAllBusLines() {
		JDBCConnection.getInstance().executeStatement("DELETE FROM buslines");
	}

	private ArrayList<BusLine> mapResultSetToBusLines(ResultSet resultSet)
			throws SQLException {
		ArrayList<BusLine> busLines = new ArrayList<BusLine>();
		while (resultSet.next()) {
			Date departure = resultSet
					.getTimestamp(
							"departure",
							new GregorianCalendar(TimeZone
									.getTimeZone("Europe/Berlin")));
			BusLine busLine = new BusLine(resultSet.getString("id"),
					resultSet.getString("line"),
					resultSet.getString("direction"), departure);
			busLines.add(busLine);
		}

		return busLines;
	}
}
