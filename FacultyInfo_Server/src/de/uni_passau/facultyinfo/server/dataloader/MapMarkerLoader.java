package de.uni_passau.facultyinfo.server.dataloader;

import java.util.UUID;

import de.uni_passau.facultyinfo.server.dao.MapMarkerDAO;
import de.uni_passau.facultyinfo.server.dto.MapMarker;
import de.uni_passau.facultyinfo.server.dto.MapMarkerCategory;

public class MapMarkerLoader {
	public String load() {
		String result = "";
		MapMarkerDAO mapMarkerDAO = new MapMarkerDAO();
		result += "Deleting from Table mapmarkers";
		mapMarkerDAO.deleteAllMapMarkers();
		result += " -- done\n";
		result += "Deleting from Table mapmarkercategories";
		mapMarkerDAO.deleteAllMapMarkerCategories();
		result += " -- done\n";

		result += "Loading map markers...\n";

		MapMarkerCategory category = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Hörsäle", null);
		mapMarkerDAO.createMapMarkerCategory(category);
		MapMarkerCategory categoryKT = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "KT", category);
		mapMarkerDAO.createMapMarkerCategory(categoryKT);
		MapMarker markerHs1HK = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 1", "", 48.57406950000001, 13.4710326, categoryKT);
		mapMarkerDAO.createMapMarker(markerHs1HK);
		MapMarker markerHs2HK = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 2", "", 48.57406950000001, 13.4710326, categoryKT);
		mapMarkerDAO.createMapMarker(markerHs2HK);
		MapMarker markerHs3HK = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 3", "", 48.57406950000001, 13.4710326, categoryKT);
		mapMarkerDAO.createMapMarker(markerHs3HK);
		MapMarkerCategory categoryPHIL = new MapMarkerCategory(UUID
				.randomUUID().toString(), "Phil", category);
		mapMarkerDAO.createMapMarkerCategory(categoryPHIL);
		MapMarker markerHs1 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 1", "", 48.5696145, 13.4561009, categoryPHIL);
		mapMarkerDAO.createMapMarker(markerHs1);
		MapMarker markerHs2 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 2", "", 48.5696145, 13.4561009, categoryPHIL);
		mapMarkerDAO.createMapMarker(markerHs2);
		MapMarker markerHs3 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 3", "", 48.5696145, 13.4561009, categoryPHIL);
		mapMarkerDAO.createMapMarker(markerHs3);
		MapMarker markerHs4 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 4", "", 48.5696145, 13.4561009, categoryPHIL);
		mapMarkerDAO.createMapMarker(markerHs4);
		MapMarkerCategory categoryWIWI = new MapMarkerCategory(UUID
				.randomUUID().toString(), "WiWi", category);
		mapMarkerDAO.createMapMarkerCategory(categoryWIWI);
		MapMarker markerHs5 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 5", "", 48.5687, 13.45472, categoryWIWI);
		mapMarkerDAO.createMapMarker(markerHs5);
		MapMarker markerHs6 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 6", "", 48.5687, 13.45472, categoryWIWI);
		mapMarkerDAO.createMapMarker(markerHs6);
		MapMarker markerHs7 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 7", "", 48.5687, 13.45472, categoryWIWI);
		mapMarkerDAO.createMapMarker(markerHs7);
		MapMarker markerHs8 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 8", "", 48.5687, 13.45472, categoryWIWI);
		mapMarkerDAO.createMapMarker(markerHs8);
		MapMarkerCategory categoryAM = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "AM", category);
		mapMarkerDAO.createMapMarkerCategory(categoryAM);
		MapMarker markerHs9 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 9", "", 48.5676983, 13.4518543, categoryAM);
		mapMarkerDAO.createMapMarker(markerHs9);
		MapMarker markerHs10 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 10", "", 48.5676983, 13.4518543, categoryAM);
		mapMarkerDAO.createMapMarker(markerHs10);
		MapMarkerCategory categoryIM = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "IM", category);
		mapMarkerDAO.createMapMarkerCategory(categoryIM);
		MapMarker markerHs11 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 11", "", 48.5668632, 13.4511949, categoryIM);
		mapMarkerDAO.createMapMarker(markerHs11);
		MapMarker markerHs12 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 12", "", 48.5668632, 13.4511949, categoryIM);
		mapMarkerDAO.createMapMarker(markerHs12);
		MapMarker markerHs13 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 13", "", 48.5668632, 13.4511949, categoryIM);
		mapMarkerDAO.createMapMarker(markerHs13);
		MapMarkerCategory categoryJUR = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Jur", category);
		mapMarkerDAO.createMapMarkerCategory(categoryJUR);
		MapMarker markerHs14 = new MapMarker(UUID.randomUUID().toString(),
				"Hörsaal 14", "", 48.56764949999999, 13.4514067, categoryJUR);
		mapMarkerDAO.createMapMarker(markerHs14);

		result += "done";
		return result;
	}

}
