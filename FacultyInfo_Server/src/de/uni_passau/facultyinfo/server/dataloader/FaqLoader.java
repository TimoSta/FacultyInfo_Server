package de.uni_passau.facultyinfo.server.dataloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.uni_passau.facultyinfo.server.dao.FaqDAO;
import de.uni_passau.facultyinfo.server.dto.Faq;
import de.uni_passau.facultyinfo.server.dto.FaqCategory;

public class FaqLoader {
	public String loadFaqs() {
		String result = "";
		FaqDAO faqDAO = new FaqDAO();
		result += "Deleting from Table faqs";
		faqDAO.deleteAllFaqs();
		result += " -- done\n";
		result += "Deleting from Table faqcategories";
		faqDAO.deleteAllFaqCategories();
		result += " -- done\n";

		Connection connection = Jsoup
				.connect("http://www.neu.fs-wiwi.de/index.php/de/faq");
		connection.ignoreContentType(true);
		result += "Loading faqs...\n";

		try {
			Document doc = connection.get();
			Elements faqCategoryElements = doc.select("div.jwts_tabbertab");
			for (Element faqCategoryElement : faqCategoryElements) {
				String categoryId = UUID.randomUUID().toString();
				String title = faqCategoryElement.attr("title");
				FaqCategory faqCategory = new FaqCategory(categoryId, title);
				result += faqCategory.getTitle();
				if (faqDAO.createFaqCategory(faqCategory)) {
					result += " -- done\n";
				} else {
					result += " -- error\n";
				}

				for (Element faqElement : faqCategoryElement.select("li")) {
					String faqId = UUID.randomUUID().toString();
					String faqTitle = faqElement.text();
					String url = faqElement.select("a").get(0).attr("href");
					if (!url.startsWith("http://")) {
						url = "http://neu.fs-wiwi.de" + url;
					}
					Connection subConnection = Jsoup.connect(url);
					subConnection.ignoreContentType(true);
					Document subDoc = subConnection.get();
					subDoc.outputSettings(new Document.OutputSettings()
							.prettyPrint(false));
					subDoc.select("br").append("\\n");
					subDoc.select("p").prepend("\\n");
					subDoc.select("li").prepend("\\n");
					Element contentElement = subDoc
							.select("table.contentpaneopen").get(1)
							.select("tr").get(0);
					contentElement.select("table").html("");
					String content = contentElement.html();
					ArrayList<String> urls = new ArrayList<String>();
					for (Element element : contentElement.select("a")) {
						urls.add(element.absUrl("href"));
					}
					Pattern p = Pattern
							.compile("<a .*?href=\".*?\" .*?>(.*?)</a>");
					Matcher m = p.matcher(content);
					StringBuffer s = new StringBuffer();
					int i = 0;
					while (m.find()) {
						m.appendReplacement(s,
								urls.size() <= i
										|| Jsoup.parse(m.group(1)).text()
												.trim().isEmpty() ? m.group(1)
										: m.group(1) + " (" + urls.get(i)
												+ ") ");
						i++;
					}
					m.appendTail(s);

					content = Jsoup
							.parse(s.toString()
									.replaceAll("\\\\n", "[newline]")).text()
							.replace("[newline]", "\n").trim();

					Faq faq = new Faq(faqId, faqCategory, faqTitle, content);
					result += " -- " + faq.getTitle();
					if (faqDAO.createFaq(faq)) {
						result += " -- done\n";
					} else {
						result += " -- error\n";
					}
				}
			}
		} catch (IOException e) {
			result += "error";
			e.printStackTrace();
		}
		return result;
	}

}
