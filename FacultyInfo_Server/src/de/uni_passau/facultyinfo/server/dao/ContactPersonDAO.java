package de.uni_passau.facultyinfo.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.uni_passau.facultyinfo.server.dao.connection.AttributeContainer;
import de.uni_passau.facultyinfo.server.dao.connection.JDBCConnection;
import de.uni_passau.facultyinfo.server.dto.ContactGroup;
import de.uni_passau.facultyinfo.server.dto.ContactPerson;

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
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add(id);
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
}
