package de.uni_passau.facultyinfo.server.dataloader;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.uni_passau.facultyinfo.server.dao.BusLineDAO;
import de.uni_passau.facultyinfo.server.dto.BusLine;

public class BusLineLoader {

	private final String BASE_URL = "http://reiseauskunft.bahn.de/bin/bhftafel.exe/dn?";
	HashMap<String, String> params = new HashMap<String, String>();
	ArrayList<String> timeTable = new ArrayList<String>();

	private String buildQueryURL() {
		String fields = "";
		Iterator<Entry<String, String>> iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			fields += entry.getKey() + "=" + entry.getValue() + "&";
		}
		fields.substring(0, fields.length() - 2);
		return BASE_URL + fields;
	}

	public String load() {
		String result = "";

		BusLineDAO busLineDAO = new BusLineDAO();
		result += "Deleting from Table buslines";
		busLineDAO.deleteAllBusLines();
		result += " -- done\n";
		result += "Loading buslines...\n";

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date current = cal.getTime();

		params.put("country", "DEU");
		params.put("rt", "1");
		params.put("GUIREQProduct_0", "");
		params.put("GUIREQProduct_1", "");
		params.put("GUIREQProduct_2", "");
		params.put("GUIREQProduct_3", "");
		params.put("GUIREQProduct_4", "");
		params.put("GUIREQProduct_5", "on");
		params.put("GUIREQProduct_6", "");
		params.put("GUIREQProduct_7", "");
		params.put("GUIREQProduct_8", "");
		params.put("REQTrain_name", "");
		params.put("REQTrain_name_filterSelf", "1");
		params.put("advancedProductMode", "");
		params.put("boardType", "dep");
		params.put("input", "Passau ZOB");
		params.put("start", "Suchen");
		for (int i = 0; i < 168; i++) {
			params.put("date",
					(new SimpleDateFormat("d.MM.yyyy")).format(current));
			params.put("time", (new SimpleDateFormat("H:m")).format(current));
			Connection connection = Jsoup.connect(buildQueryURL());
			connection.ignoreContentType(true);
			try {
				Document doc = connection.get();
				if (doc.select("div#sqResult p.errormsg").size() == 0) {
					Element resultElement = doc.select("table.result").get(0);

					List<Element> rows = resultElement.select("tr").subList(2,
							resultElement.select("tr").size() - 1);
					SimpleDateFormat hourFormat = new SimpleDateFormat("H");
					for (Element element : rows) {
						if (!element.attr("class").equals("current")) {
							String line = element.select("td.train").get(1)
									.text();
							String direction = element
									.select("td.route span.bold").get(0).text();
							if (line.contains("Bus")
									&& !direction.equals("Passau ZOB")) {
								String[] timeStrings = element
										.select("td.time").get(0).text()
										.split(":");
								GregorianCalendar calendar = new GregorianCalendar();
								calendar.setTime(current);
								calendar.set(Calendar.HOUR_OF_DAY,
										Integer.parseInt(timeStrings[0]));
								calendar.set(Calendar.MINUTE,
										Integer.parseInt(timeStrings[1]));
								Date departure = calendar.getTime();
								if (hourFormat.format(current).equals(
										hourFormat.format(departure))) {

									String id = UUID.randomUUID().toString();
									BusLine busLine = new BusLine(id, line,
											direction, departure);

									result += busLine.getDeparture().toString()
											+ " - " + busLine.getLine() + " - "
											+ busLine.getDirection();
									if (busLineDAO.createBusLine(busLine)) {
										result += " -- done\n";
									} else {
										result += " -- error\n";
									}
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			cal.setTime(current);
			cal.add(Calendar.HOUR_OF_DAY, 1);
			current = cal.getTime();
		}

		return result;
	}

}
