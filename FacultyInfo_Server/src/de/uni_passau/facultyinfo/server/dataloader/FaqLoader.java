package de.uni_passau.facultyinfo.server.dataloader;

import java.io.IOException;
import java.util.UUID;

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
					String content = subDoc.select("table.contentpaneopen")
							.get(1).text();
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
