package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.Faq;
import de.uni_passau.facultyinfo.server.dto.FaqCategory;

public class FaqDAO {

	public List<FaqCategory> getFaqList() {
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, title FROM faqcategories");
		if (resultSet == null) {
			return null;
		}

		try {
			ArrayList<FaqCategory> faqCategories = mapResultSetToFaqCategories(resultSet);

			for (FaqCategory faqCategory : faqCategories) {
				ArrayList<String> attributes = new ArrayList<String>();
				attributes.add(faqCategory.getId());
				ResultSet faqResultSet = JDBCConnection
						.getInstance()
						.executeSelect(
								"SELECT id, title FROM faqs WHERE category = ?",
								attributes);
				if (faqResultSet == null) {
					continue;
				}

				faqCategory.setFaqs(mapResultSetToFaqs(faqResultSet,
						faqCategory));
			}

			return faqCategories;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Faq getFaq(String id) {
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add(id);
		ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
				"SELECT id, title, text FROM faqs WHERE id = ?", attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToFaq(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean createFaqCategory(FaqCategory faqCategory) {
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add(faqCategory.getId());
		attributes.add(faqCategory.getTitle());
		return JDBCConnection.getInstance().executeStatement(
				"INSERT INTO faqcategories (id, title) VALUES (?, ?)",
				attributes) == 1;
	}

	public boolean createFaq(Faq faq) {
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add(faq.getId());
		attributes.add(faq.getCategory().getId());
		attributes.add(faq.getTitle());
		attributes.add(faq.getText());
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO faqs (id, category, title, text) VALUES ( ?, ?, ?, ?)",
						attributes) == 1;
	}

	private ArrayList<FaqCategory> mapResultSetToFaqCategories(
			ResultSet resultSet) throws SQLException {
		ArrayList<FaqCategory> faqCategories = new ArrayList<FaqCategory>();
		while (resultSet.next()) {
			FaqCategory faqCategory = new FaqCategory(
					resultSet.getString("id"), resultSet.getString("title"));
			faqCategories.add(faqCategory);
		}

		return faqCategories;
	}

	private ArrayList<Faq> mapResultSetToFaqs(ResultSet resultSet,
			FaqCategory faqCategory) throws SQLException {
		ArrayList<Faq> faqs = new ArrayList<Faq>();
		while (resultSet.next()) {
			Faq faq = new Faq(resultSet.getString("id"), faqCategory,
					resultSet.getString("title"), null);
			faqs.add(faq);
		}
		return faqs;
	}

	private Faq mapResultSetToFaq(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			Faq faq = new Faq(resultSet.getString("id"), null,
					resultSet.getString("title"), resultSet.getString("text"));
			return faq;
		}
		return null;
	}

	public void deleteAllFaqCategories() {
		JDBCConnection.getInstance().executeStatement(
				"DELETE FROM faqcategories");
	}

	public void deleteAllFaqs() {
		JDBCConnection.getInstance().executeStatement("DELETE FROM faqs");
	}

}
