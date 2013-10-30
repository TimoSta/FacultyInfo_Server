package de.uni_passau.facultyinfo.server.dao.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCConnection {
	public ResultSet executeSelect(String sql) {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (javax.sql.DataSource) ctx
					.lookup("jdbc/FacultyInfoDB");
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery(sql);
			return resultSet;
		} catch (NamingException e) {
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

}
