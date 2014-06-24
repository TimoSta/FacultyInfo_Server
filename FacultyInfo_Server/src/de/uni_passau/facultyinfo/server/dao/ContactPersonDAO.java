package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public ContactPerson getContactPerson(String id) {
		AttributeContainer attributes = new AttributeContainer();
		attributes.add(1, id);
		ResultSet resultSet = JDBCConnection
				.getInstance()
				.executeSelect(
						"SELECT contactpersons.id, contactpersons.name, contactpersons.office, contactpersons.phone, contactpersons.email, contactpersons.description, contactgroups.title FROM contactpersons JOIN contactgroups ON contactpersons.contactgroup = contactgroups.id WHERE contactpersons.id = ?",
						attributes);
		if (resultSet == null) {
			return null;
		}

		try {
			return mapResultSetToContactPerson(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
						person.setGroupTitle(group.getTitle());
						response.add(person);
					}
				}
			}
		}

		return response;
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

	private ContactPerson mapResultSetToContactPerson(ResultSet resultSet)
			throws SQLException {
		if (resultSet.next()) {
			ContactPerson contactPerson = new ContactPerson(
					resultSet.getString("id"), resultSet.getString("name"),
					resultSet.getString("office"),
					resultSet.getString("phone"), resultSet.getString("email"),
					resultSet.getString("description"),
					resultSet.getString("title"));
			return contactPerson;
		}
		return null;
	}

	private String crop(String input, int start, int offset) {
		boolean cropStart = start - 50 >= 0;
		boolean cropEnd = start + offset + 50 < input.length();

		String croppedInput = input.substring(cropStart ? start - 50 : 0,
				cropEnd ? start + offset + 50 : input.length());

		croppedInput = (cropStart ? "..." : "") + croppedInput
				+ (cropEnd ? "..." : "");

		return croppedInput;
	}
}
