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

		MapMarkerCategory cat2 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Restaurants", null);
		mapMarkerDAO.createMapMarkerCategory(cat2);
		MapMarkerCategory cat21 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Asiatisch", cat2);
		mapMarkerDAO.createMapMarkerCategory(cat21);
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Bollywood", "", 48.576607,
				13.473845, cat21));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Chandi", "", 48.575812,
				13.469996, cat21));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Jento", "", 48.572511,
				13.455997, cat21));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Mandarin", "", 48.571784,
				13.454048, cat21));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "SensAsian", "", 48.575585,
				13.460888, cat21));

		MapMarkerCategory cat22 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Italienisch", cat2);
		mapMarkerDAO.createMapMarkerCategory(cat22);
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "DaFranco", "", 48.574666,
				13.471206, cat22));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Gallo Nero", "", 48.570928,
				13.467134, cat22));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "O. del Padrone", "", 48.575308,
				13.457262, cat22));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Padu Donaul.", "",
				48.575706, 13.465319, cat22));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Padu Fernseht.", "",
				48.573846, 13.460462, cat22));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Padu Innstr.", "", 48.57342,
				13.461673, cat22));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Va Bene", "", 48.574492,
				13.459115, cat22));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Venti Tre", "", 48.571013,
				13.468625, cat22));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Zi'Theresa", "", 48.57342,
				13.461673, cat22));

		MapMarkerCategory cat23 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Griechisch", cat2);
		mapMarkerDAO.createMapMarkerCategory(cat23);
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Akropolis", "", 48.567087,
				13.449174, cat23));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Korfou", "", 48.57392,
				13.474034, cat23));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Rhodos", "", 48.575321,
				13.457023, cat23));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Villans", "", 48.579162,
				13.447388, cat23));

		MapMarkerCategory cat24 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Bayrisch", cat2);
		mapMarkerDAO.createMapMarkerCategory(cat24);
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Bayr. Löwe", "", 48.572942,
				13.45687, cat24));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Bräuhaus", "", 48.574785,
				13.47155, cat24));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Das Oberhaus", "", 48.577672,
				13.470878, cat24));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Hl. Geist Stift", "", 48.573668,
				13.458844, cat24));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Kowalski", "", 48.572996,
				13.462586, cat24));

		MapMarkerCategory cat3 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Bars", null);
		mapMarkerDAO.createMapMarkerCategory(cat3);
		MapMarkerCategory cat31 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Bars", cat3);
		mapMarkerDAO.createMapMarkerCategory(cat31);
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Coconut", "", 48.57287,
				13.456943, cat31));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Colors", "", 48.570658,
				13.466376, cat31));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Funky Buddha", "",
				48.573473, 13.462052, cat31));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Hemingwey's", "", 48.575347,
				13.457836, cat31));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Journeys", "", 48.574286,
				13.468404, cat31));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Roots", "", 48.573477,
				13.461427, cat31));

		MapMarkerCategory cat4 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Clubs", null);
		mapMarkerDAO.createMapMarkerCategory(cat4);
		MapMarkerCategory cat41 = new MapMarkerCategory(UUID.randomUUID()
				.toString(), "Clubs", cat4);
		mapMarkerDAO.createMapMarkerCategory(cat41);
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Camera", "", 48.574069,
				13.457034, cat41));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Cubana", "", 48.575507,
				13.460169, cat41));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Frizz", "", 48.573384,
				13.457087, cat41));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "GO", "", 48.575269, 13.458729,
				cat41));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "GOA", "", 48.571922, 13.454257,
				cat41));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "LemonLounge", "", 48.571833,
				13.453158, cat41));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Rotwild", "", 48.571787,
				13.456873, cat41));
		mapMarkerDAO.createMapMarker(new MapMarker(
				UUID.randomUUID().toString(), "Soda Pur", "", 48.574437,
				13.456521, cat41));

		result += "done";
		return result;
	}

}
