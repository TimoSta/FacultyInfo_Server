package de.uni_passau.facultyinfo.server.dao.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCConnection {

	public static JDBCConnection instance = null;

	public static JDBCConnection getInstance() {
		if (instance == null) {
			instance = new JDBCConnection();
		}

		return instance;
	}
	
	private JDBCConnection() {
	}

	private Connection connection = null;

	private Connection getConnection() throws NamingException, SQLException {
		if (connection == null) {
			InitialContext ctx = new InitialContext();
			DataSource ds = (javax.sql.DataSource) ctx
					.lookup("jdbc/FacultyInfoDB");
			connection = ds.getConnection();
		}

		return connection;
	}

	public ResultSet executeSelect(String sql) {
		try {
			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			return resultSet;
		} catch (NamingException e) {
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public int executeStatement(String sql) {
		try {
			Statement statement = getConnection().createStatement();
			return statement.executeUpdate(sql);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
