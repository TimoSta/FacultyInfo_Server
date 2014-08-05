package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.MenuItem;

public class MenuDAO {

	public List<MenuItem> getMenuItems() {
		return getMenuItems(null, null);
	}

	public List<MenuItem> getMenuItems(Integer type, Boolean today) {
		String query = "SELECT id, day, name, type, attributes, pricestudent, priceemployee, priceexternal FROM menuitems";
		String orderBy = "day, type, name";

		ArrayList<String> whereConditions = new ArrayList<String>();

		if (type != null) {
			String condition = "type = " + Integer.toString(type);
			whereConditions.add(condition);
		}

		if (today != null) {
			String condition = "day = CURDATE()";
			whereConditions.add(condition);
		}

		if (!whereConditions.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			boolean firstTime = true;
			for (String condition : whereConditions) {
				if (!firstTime) {
					builder.append(" AND ");
				}
				builder.append(condition);
				firstTime = false;
			}
			query += " WHERE " + builder.toString();
		}
		
		query += " ORDER BY " + orderBy;
		
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(query);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToMenuItems(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public MenuItem getMenuItem(String id) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, id);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, day, name, type, attributes, pricestudent, priceemployee, priceexternal FROM menuitems WHERE id = ?",
						attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToMenuItem(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean createMenuItem(MenuItem menuItem) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, menuItem.getId());
		attributes.add(2, menuItem.getDay());
		attributes.add(3, menuItem.getName());
		attributes.add(4, menuItem.getType());
		attributes.add(5, menuItem.getAttributes());
		attributes.add(6, menuItem.getPriceStudent());
		attributes.add(7, menuItem.getPriceEmployee());
		attributes.add(8, menuItem.getPriceExternal());
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO menuitems (id, day, name, type, attributes, pricestudent, priceemployee, priceexternal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
						attributes) == 1;
	}

	public void deleteAllMenuItems() {
		JDBCConnection.getInstance().executeStatement("DELETE FROM menuitems");
	}

	private ArrayList<MenuItem> mapResultSetToMenuItems(ResultSet resultSet)
			throws SQLException {
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		while (resultSet.next()) {
			MenuItem menuItem = new MenuItem(resultSet.getString("id"),
					resultSet.getDate("day"), resultSet.getString("name"),
					resultSet.getInt("type"), resultSet.getInt("attributes"),
					resultSet.getDouble("pricestudent"),
					resultSet.getDouble("priceemployee"),
					resultSet.getDouble("priceexternal"));
			menuItems.add(menuItem);
		}

		return menuItems;
	}

	private MenuItem mapResultSetToMenuItem(ResultSet resultSet)
			throws SQLException {
		if (resultSet.next()) {
			MenuItem menuItem = new MenuItem(resultSet.getString("id"),
					resultSet.getDate("day"), resultSet.getString("name"),
					resultSet.getInt("type"), resultSet.getInt("attributes"),
					resultSet.getDouble("pricestudent"),
					resultSet.getDouble("priceemployee"),
					resultSet.getDouble("priceexternal"));
			return menuItem;
		}
		return null;
	}
}
