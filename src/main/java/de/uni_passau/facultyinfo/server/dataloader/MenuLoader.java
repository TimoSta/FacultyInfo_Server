package de.uni_passau.facultyinfo.server.dataloader;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.uni_passau.facultyinfo.server.dao.MenuDAO;
import de.uni_passau.facultyinfo.server.dao.MetadataDAO;
import de.uni_passau.facultyinfo.server.dto.MenuItem;
import de.uni_passau.facultyinfo.server.dto.Metadata;

public class MenuLoader {

	private MenuDAO menuDAO = new MenuDAO();

	public int load() {
		int status = 0;

		MetadataDAO metadataDAO = new MetadataDAO();
		Metadata menuMetadata = metadataDAO.getMetadata(Metadata.NAME_MENU);

		if (menuMetadata != null && menuMetadata.getSourceUrl() != null
				&& !menuMetadata.getSourceUrl().isEmpty()) {

			boolean responseValue = true;

			menuDAO.deleteAllMenuItems();

			GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Date current = cal.getTime();

			responseValue = responseValue
					&& processWeek(current, "dw", menuMetadata.getSourceUrl());

			cal.setTime(current);
			cal.add(Calendar.DAY_OF_WEEK, 7);
			current = cal.getTime();

			responseValue = responseValue
					&& processWeek(current, "nw", menuMetadata.getSourceUrl());

			status = responseValue ? 0 : 1;
		} else {
			status = 2;
		}

		metadataDAO.updateStatuscode(Metadata.NAME_MENU, status);

		return status;
	}

	private boolean processWeek(Date currentDate, String weekString, String url) {
		SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");
		boolean responseValue = true;

		for (int i = 0; i < 5; i++) {
			try {
				Document document = Jsoup.connect(url).data("func", "make_spl")
						.data("locId", "UNI-P").data("lang", "de")
						.data("w", weekString)
						.data("date", sdf.format(currentDate)).post();

				Elements rowsRaw = document.select("table.speiseplan tbody tr");
				int currentType = MenuItem.TYPE_NOT_AVAILABLE;
				List<Element> rows = rowsRaw.subList(1, rowsRaw.size());
				for (Element element : rows) {
					String firstLineText = element.select("td.cell1").get(0)
							.text();
					if (firstLineText.equals("Geschlossen")
							|| firstLineText.equals("keine Daten vorhanden")) {
						break;
					}
					Elements typeElement = element.select("td.cell0");
					if (typeElement.size() > 0) {
						String typeString = typeElement.get(0).text();
						if (typeString.equals("Suppen")) {
							currentType = MenuItem.TYPE_SOUP;
						} else if (typeString.equals("Hauptgerichte")) {
							currentType = MenuItem.TYPE_MAIN;
						} else if (typeString.equals("Beilagen")) {
							currentType = MenuItem.TYPE_APPETIZER;
						} else if (typeString.equals("Nachspeisen")) {
							currentType = MenuItem.TYPE_DESSERT;
						} else {
							currentType = MenuItem.TYPE_NOT_AVAILABLE;
						}
					}

					String id = UUID.randomUUID().toString();

					String name = firstLineText.replaceAll("\\(.*?\\)", "")
							.trim();
					String[] priceStrings = element.select("td.cell3").get(0)
							.text().trim().split(" / ");

					double priceStudent = MenuItem.PRICE_NOT_AVAILABLE;
					double priceEmployee = MenuItem.PRICE_NOT_AVAILABLE;
					double priceExternal = MenuItem.PRICE_NOT_AVAILABLE;
					try {
						NumberFormat format = NumberFormat
								.getInstance(Locale.GERMANY);
						priceStudent = format.parse(priceStrings[0])
								.doubleValue();
						priceEmployee = format.parse(priceStrings[1])
								.doubleValue();
						priceExternal = format.parse(priceStrings[2])
								.doubleValue();
					} catch (ParseException e) {
						e.printStackTrace();
					}

					MenuItem menuItem = new MenuItem(id, currentDate, name,
							currentType, 0, priceStudent, priceEmployee,
							priceExternal);
					responseValue = responseValue
							&& menuDAO.createMenuItem(menuItem);
				}

				Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);
				cal.add(Calendar.DAY_OF_WEEK, 1);
				currentDate = cal.getTime();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseValue;
	}
}
