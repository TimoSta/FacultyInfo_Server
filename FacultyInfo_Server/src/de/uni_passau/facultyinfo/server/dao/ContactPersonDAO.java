package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.ContactGroup;
import de.uni_passau.facultyinfo.server.dto.ContactPerson;
import de.uni_passau.facultyinfo.server.dto.ContactSearchResponse;

public class ContactPersonDAO {

	public List<ContactGroup> getContactGroups() {
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, description FROM contactgroups ORDER BY title");
		if (resultSet == null) {
			return null;
		}

		try {
			ArrayList<ContactGroup> contactGroups = mapResultSetToContactGroups(resultSet);

			// for (ContactGroup contactGroup : contactGroups) {
			// ArrayList<String> attributes = new ArrayList<String>();
			// attributes.add(contactGroup.getId());
			// ResultSet contactPersonResultSet = JDBCConnection
			// .getInstance()
			// .executeSelect(
			// "SELECT id, name, office, phone, email, description FROM contactpersons WHERE contactgroup = ?",
			// attributes);
			// if (contactPersonResultSet == null) {
			// continue;
			// }
			//
			// contactGroup.setContactPersons(mapResultSetToContactPersons(
			// contactPersonResultSet, contactGroup));
			// }

			return contactGroups;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ContactGroup getContactGroup(String id) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, id);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, description FROM contactgroups WHERE id = ?",
						attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			ContactGroup contactGroup = mapResultSetToContactGroup(resultSet);

			ResultSet contactPersonResultSet = JDBCConnection
					.getInstance()
					.executeSelect(
							"SELECT id, name, office, phone, email, description FROM contactpersons WHERE contactgroup = ?",
							attributes);
			contactGroup.setContactPersons(mapResultSetToContactPersons(
					contactPersonResultSet, contactGroup));

			return contactGroup;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ContactGroup> findContactGroups(String searchString) {
		ArrayList<ContactGroup> searchResults = new ArrayList<ContactGroup>();

		if (searchString != null && !searchString.isEmpty()) {

			List<ContactGroup> contactGroups = getFullContactGroups();
			Pattern pattern = Pattern.compile(searchString,
					Pattern.CASE_INSENSITIVE + Pattern.LITERAL);

			for (ContactGroup contactGroup : contactGroups) {
				if (contactGroup.getTitle() != null
						&& pattern.matcher(contactGroup.getTitle()).find()) {
					searchResults.add(contactGroup);
				} else {
					ArrayList<ContactPerson> matchingContactPersons = new ArrayList<ContactPerson>();
					for (ContactPerson contactPerson : contactGroup
							.getContactPersons()) {
						contactPerson.setContactGroup(null);
						if ((contactPerson.getName() != null && pattern
								.matcher(contactPerson.getName()).find())) {
							matchingContactPersons.add(contactPerson);
							contactPerson.setContactGroup(contactGroup);
						}
					}
					if (!matchingContactPersons.isEmpty()) {
						contactGroup.setContactPersons(Collections
								.unmodifiableList(matchingContactPersons));
						searchResults.add(contactGroup);
					}
				}
			}

			return Collections.unmodifiableList(searchResults);
		}

		return searchResults;
	}

	public ContactSearchResponse find(String searchString) {
		ContactSearchResponse response = new ContactSearchResponse();

		if (searchString != null && !searchString.isEmpty()) {

			List<ContactGroup> groups = getFullContactGroups();
			Pattern pattern = Pattern.compile(searchString,
					Pattern.CASE_INSENSITIVE + Pattern.LITERAL);

			for (ContactGroup group : groups) {
				List<ContactPerson> persons = group.getContactPersons();
				group.setContactPersons(null);

				boolean found = false;

				if (group.getTitle() != null
						&& pattern.matcher(group.getTitle()).find()) {
					found = true;
				}

				if (group.getDescription() != null) {
					Matcher descriptionMatcher = pattern.matcher(group
							.getDescription());
					if (descriptionMatcher.find()) {
						group.setDescription(crop(group.getDescription(),
								descriptionMatcher.start(),
								descriptionMatcher.end()));
						found = true;
					} else {
						group.setDescription(null);
					}
				}

				if (found) {
					response.add(group);
				}

				for (ContactPerson person : persons) {
					person.setContactGroup(null);

					found = false;

					if (person.getName() != null
							&& pattern.matcher(person.getName()).find()) {
						found = true;
					}

					if (person.getOffice() != null
							&& pattern.matcher(person.getOffice()).find()) {
						found = true;
					} else {
						person.setOffice(null);
					}

					if (person.getPhone() != null
							&& pattern.matcher(person.getPhone()).find()) {
						found = true;
					} else {
						person.setPhone(null);
					}

					if (person.getEmail() != null
							&& pattern.matcher(person.getEmail()).find()) {
						found = true;
					} else {
						person.setEmail(null);
					}

					if (person.getDescription() != null) {
						Matcher descriptionMatcher = pattern.matcher(person
								.getDescription());
						if (descriptionMatcher.find()) {
							person.setDescription(crop(person.getDescription(),
									descriptionMatcher.start(),
									descriptionMatcher.end()));
							found = true;
						} else {
							person.setDescription(null);
						}
					}

					if (found) {
						response.add(person);
					}
				}
			}
		}

		return response;
	}

	public boolean createContactGroup(ContactGroup contactGroup) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, contactGroup.getId());
		attributes.add(2, contactGroup.getTitle());
		attributes.add(3, contactGroup.getDescription());
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO contactgroups (id, title, description) VALUES (?, ?, ?)",
						attributes) == 1;
	}

	public boolean createContactPerson(ContactPerson contactPerson) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, contactPerson.getId());
		attributes.add(2, contactPerson.getContactGroup().getId());
		attributes.add(3, contactPerson.getName());
		attributes.add(4, contactPerson.getOffice());
		attributes.add(5, contactPerson.getPhone());
		attributes.add(6, contactPerson.getEmail());
		attributes.add(7, contactPerson.getDescription());
		return JDBCConnection
				.getInstance()
				.executeStatement(
						"INSERT INTO contactpersons (id, contactgroup, name, office, phone, email, description) VALUES ( ?, ?, ?, ?, ?, ?, ?)",
						attributes) == 1;
	}

	public void deleteAllContactGroups() {
		JDBCConnection.getInstance().executeStatement(
				"DELETE FROM contactgroups");
	}

	public void deleteAllContactPersons() {
		JDBCConnection.getInstance().executeStatement(
				"DELETE FROM contactpersons");
	}

	private List<ContactGroup> getFullContactGroups() {
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT id, title, description FROM contactgroups ORDER BY title");
		if (resultSet == null) {
			return null;
		}

		try {
			ArrayList<ContactGroup> contactGroups = mapResultSetToContactGroups(resultSet);

			for (ContactGroup contactGroup : contactGroups) {
				AttributeContainer attributes = new AttributeContainer();
				attributes.add(1, contactGroup.getId());
				ResultSet contactPersonResultSet = JDBCConnection
						.getInstance()
						.executeSelect(
								"SELECT id, name, office, phone, email, description FROM contactpersons WHERE contactgroup = ?",
								attributes);
				if (contactPersonResultSet == null) {
					continue;
				}

				contactGroup.setContactPersons(mapResultSetToContactPersons(
						contactPersonResultSet, contactGroup));
			}

			return contactGroups;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<ContactGroup> mapResultSetToContactGroups(
			ResultSet resultSet) throws SQLException {
		ArrayList<ContactGroup> contactGroups = new ArrayList<ContactGroup>();
		while (resultSet.next()) {
			ContactGroup contactGroup = new ContactGroup(
					resultSet.getString("id"), resultSet.getString("title"),
					resultSet.getString("description"));
			contactGroups.add(contactGroup);
		}
		return contactGroups;
	}

	private ContactGroup mapResultSetToContactGroup(ResultSet resultSet)
			throws SQLException {
		if (resultSet.next()) {
			ContactGroup contactGroup = new ContactGroup(
					resultSet.getString("id"), resultSet.getString("title"),
					resultSet.getString("description"));
			return contactGroup;
		}
		return null;
	}

	private ArrayList<ContactPerson> mapResultSetToContactPersons(
			ResultSet resultSet, ContactGroup contactGroup) throws SQLException {
		ArrayList<ContactPerson> contactPersons = new ArrayList<ContactPerson>();
		while (resultSet.next()) {
			ContactPerson contactPerson = new ContactPerson(
					resultSet.getString("id"), resultSet.getString("name"),
					resultSet.getString("office"),
					resultSet.getString("phone"), resultSet.getString("email"),
					resultSet.getString("description"), contactGroup);
			contactPersons.add(contactPerson);
		}
		return contactPersons;
	}

	private String crop(String input, int start, int offset) {
		boolean cropStart = start - 50 >= 0;
		boolean cropEnd = start + offset + 50 < input.length();

		String croppedInput = input.substring(cropStart ? start - 50 : 0,
				cropEnd ? start + offset + 50 : input.length() - 1);

		croppedInput = (cropStart ? "..." : "") + croppedInput
				+ (cropEnd ? "..." : "");

		return croppedInput;
	}
}
