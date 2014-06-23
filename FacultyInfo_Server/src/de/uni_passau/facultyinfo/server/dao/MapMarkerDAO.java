package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.MapMarker;
import de.uni_passau.facultyinfo.server.dto.MapMarkerCategory;

public class MapMarkerDAO {

	public List<MapMarkerCategory> getMapMarkerList() {
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, supercategory FROM mapmarkercategories ORDER BY title");
		if (resultSet == null) {
			return null;
		}

		try {
			HashMap<MapMarkerCategory, String> subCategories = new HashMap<MapMarkerCategory, String>();
			ArrayList<MapMarkerCategory> superCategories = new ArrayList<MapMarkerCategory>();
			while (resultSet.next()) {
				MapMarkerCategory mapMarkerCategory = new MapMarkerCategory(
						resultSet.getString("id"),
						resultSet.getString("title"), null);
				if (resultSet.getString("supercategory") != null) {
					subCategories.put(mapMarkerCategory,
							resultSet.getString("supercategory"));
				} else {
					superCategories.add(mapMarkerCategory);
				}
			}

			buildCategoryTreeAndLoadMapMarkers(superCategories, subCategories);

			return superCategories;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<MapMarker> mapResultSetToMapMarkers(ResultSet resultSet,
			MapMarkerCategory mapMarkerCategory) throws SQLException {
		if (resultSet != null) {
			ArrayList<MapMarker> mapMarkers = new ArrayList<MapMarker>();
			while (resultSet.next()) {
				MapMarker mapMarker = new MapMarker(resultSet.getString("id"),
						resultSet.getString("name"),
						resultSet.getString("description"),
						resultSet.getDouble("latitude"),
						resultSet.getDouble("longitude"), mapMarkerCategory);
				mapMarkers.add(mapMarker);
			}
			return mapMarkers;
		}
		return null;
	}

	private void buildCategoryTreeAndLoadMapMarkers(
			List<MapMarkerCategory> superCategories,
			HashMap<MapMarkerCategory, String> subCategories)
			throws SQLException {
		if (superCategories != null) {
			for (MapMarkerCategory mapMarkerCategory : superCategories) {
				Iterator<Entry<MapMarkerCategory, String>> iterator = subCategories
						.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<MapMarkerCategory, String> entry = iterator.next();
					if (mapMarkerCategory.getId().equals(entry.getValue())) {
						mapMarkerCategory.addMapMarkerCategory(entry.getKey());
						entry.getKey().setSuperCategory(mapMarkerCategory);
					}
				}

				AttributeContainer attributes = new AttributeContainer();
				attributes.add(1, mapMarkerCategory.getId());
				ResultSet mapMarkerResultSet = JDBCConnection
						.getInstance()
						.executeSelect(
								"SELECT id, name, description, latitude, longitude, category FROM mapmarkers WHERE category = ?",
								attributes);
				mapMarkerCategory.setMapMarkers(mapResultSetToMapMarkers(
						mapMarkerResultSet, mapMarkerCategory));

				buildCategoryTreeAndLoadMapMarkers(
						mapMarkerCategory.getMapMarkerCategories(),
						subCategories);
			}
		}
	}
}
