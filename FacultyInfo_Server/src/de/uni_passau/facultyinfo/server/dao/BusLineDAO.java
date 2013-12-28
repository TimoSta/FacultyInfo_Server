package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.BusLine;

public class BusLineDAO {
	public List<BusLine> getBusLines() {
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, line, direction, departure FROM buslines");
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d H:m:s");
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add(busLine.getId());
		attributes.add(busLine.getLine());
		attributes.add(busLine.getDirection());
		attributes.add(sdf.format(busLine.getDeparture()));
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO buslines (id, line, direction, departure) VALUES (?, ?, ?, ?)",
						attributes) == 1;
	}

	private ArrayList<BusLine> mapResultSetToBusLines(ResultSet resultSet)
			throws SQLException {
		ArrayList<BusLine> busLines = new ArrayList<BusLine>();
		while (resultSet.next()) {
			BusLine busLine = new BusLine(resultSet.getString("id"),
					resultSet.getString("line"),
					resultSet.getString("direction"),
					resultSet.getTimestamp("departure"));
			busLines.add(busLine);
		}

		return busLines;
	}

	public void deleteAllBusLines() {
		JDBCConnection.getInstance().executeStatement("DELETE FROM buslines");
	}
}
