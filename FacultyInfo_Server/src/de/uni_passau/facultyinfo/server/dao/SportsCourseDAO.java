//package de.uni_passau.facultyinfo.server.dao;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//
//import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
//import de.uni_passau.facultyinfo.server.dto.SportsCourse;
//import de.uni_passau.facultyinfo.server.dto.SportsCourseCategory;
//
//public class SportsCourseDAO {
//
//	// public List<FaqCategory> getFaqList() {
//	// ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
//	// "SELECT id, title FROM faqcategories");
//	// if (resultSet == null) {
//	// return null;
//	// }
//	//
//	// try {
//	// ArrayList<FaqCategory> faqCategories =
//	// mapResultSetToFaqCategories(resultSet);
//	//
//	// for (FaqCategory faqCategory : faqCategories) {
//	// ArrayList<String> attributes = new ArrayList<String>();
//	// attributes.add(faqCategory.getId());
//	// ResultSet faqResultSet = JDBCConnection
//	// .getInstance()
//	// .executeSelect(
//	// "SELECT id, title FROM faqs WHERE category = ?",
//	// attributes);
//	// if (faqResultSet == null) {
//	// continue;
//	// }
//	//
//	// faqCategory.setFaqs(mapResultSetToFaqs(faqResultSet,
//	// faqCategory));
//	// }
//	//
//	// return faqCategories;
//	// } catch (SQLException e) {
//	// e.printStackTrace();
//	// return null;
//	// }
//	// }
//	//
//	// public Faq getFaq(String id) {
//	// ArrayList<String> attributes = new ArrayList<String>();
//	// attributes.add(id);
//	// ResultSet resultSet = JDBCConnection.getInstance().executeSelect(
//	// "SELECT id, title, text FROM faqs WHERE id = ?", attributes);
//	// if (resultSet == null) {
//	// return null;
//	// }
//	//
//	// try {
//	// return mapResultSetToFaq(resultSet);
//	// } catch (SQLException e) {
//	// e.printStackTrace();
//	// return null;
//	// }
//	// }
//
//	public boolean createSportsCourseCategory(
//			SportsCourseCategory sportsCourseCategory) {
//		ArrayList<String> attributes = new ArrayList<String>();
//		attributes.add(sportsCourseCategory.getId());
//		attributes.add(sportsCourseCategory.getTitle());
//		return JDBCConnection.getInstance().executeStatement(
//				"INSERT INTO sportscoursecategories (id, title) VALUES (?, ?)",
//				attributes) == 1;
//	}
//
//	public boolean createSportsCourse(SportsCourse sportsCourse) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
//		ArrayList<String> attributes = new ArrayList<String>();
//		attributes.add(sportsCourse.getId());
//		attributes.add(sportsCourse.getCategory().getId());
//		attributes.add(sportsCourse.getNumber());
//		attributes.add(sportsCourse.getDetails());
//		attributes.add(Integer.toString(sportsCourse.getDayOfWeek()));
//		attributes.add(sportsCourse.getStartTime().toString());
//		attributes.add(sportsCourse.getEndTime().toString());
//		attributes.add(sportsCourse.getLocation());
//		attributes.add(sdf.format(sportsCourse.getStartDate()));
//		attributes.add(sdf.format(sportsCourse.getEndDate()));
//		attributes.add(sportsCourse.getHost());
//		attributes.add(Float.toString(sportsCourse.getPrice()));
//		attributes.add(Integer.toString(sportsCourse.getStatus()));
//		return JDBCConnection
//				.getInstance()
//				.executeStatement(
//						"INSERT INTO sportscourses (id, category, number, details, dayofweek, starttime, endtime, location, startdate, enddate, host, price, status) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
//						attributes) == 1;
//	}
//
//	// private ArrayList<FaqCategory> mapResultSetToFaqCategories(
//	// ResultSet resultSet) throws SQLException {
//	// ArrayList<FaqCategory> faqCategories = new ArrayList<FaqCategory>();
//	// while (resultSet.next()) {
//	// FaqCategory faqCategory = new FaqCategory(
//	// resultSet.getString("id"), resultSet.getString("title"));
//	// faqCategories.add(faqCategory);
//	// }
//	//
//	// return faqCategories;
//	// }
//	//
//	// private ArrayList<Faq> mapResultSetToFaqs(ResultSet resultSet,
//	// FaqCategory faqCategory) throws SQLException {
//	// ArrayList<Faq> faqs = new ArrayList<Faq>();
//	// while (resultSet.next()) {
//	// Faq faq = new Faq(resultSet.getString("id"), faqCategory,
//	// resultSet.getString("title"), null);
//	// faqs.add(faq);
//	// }
//	// return faqs;
//	// }
//	//
//	// private Faq mapResultSetToFaq(ResultSet resultSet) throws SQLException {
//	// if (resultSet.next()) {
//	// Faq faq = new Faq(resultSet.getString("id"), null,
//	// resultSet.getString("title"), resultSet.getString("text"));
//	// return faq;
//	// }
//	// return null;
//	// }
//
//	public void deleteAllSportsCourseCategories() {
//		JDBCConnection.getInstance().executeStatement(
//				"DELETE FROM sportscoursecategories");
//	}
//
//	public void deleteAllSportsCourses() {
//		JDBCConnection.getInstance().executeStatement(
//				"DELETE FROM sportscourses");
//	}
//
//}
