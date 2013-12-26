package de.uni_passau.facultyinfo.server.dao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private DataSource dataSource = null;

	private Connection getConnection() throws NamingException, SQLException {
		if (dataSource == null) {
			InitialContext ctx = new InitialContext();
			dataSource = (javax.sql.DataSource) ctx
					.lookup("jdbc/FacultyInfoDB");
		}

		return dataSource.getConnection();
	}

	public ResultSet executeSelect(String sql) {
		return executeSelect(sql, new ArrayList<String>());
	}

	public ResultSet executeSelect(String sql, List<String> attributes) {
		try {
			PreparedStatement statement = getConnection().prepareStatement(sql);
			int argumentNumber = 1;
			for (String attribute : attributes) {
				statement.setString(argumentNumber++, attribute);
			}
			ResultSet resultSet = statement.executeQuery();
			return resultSet;
		} catch (NamingException e) {
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public int executeStatement(String sql) {
		return executeStatement(sql, new ArrayList<String>());
	}

	public int executeStatement(String sql, List<String> attributes) {
		try {
			PreparedStatement statement = getConnection().prepareStatement(sql);
			int argumentNumber = 1;
			for (String attribute : attributes) {
				statement.setString(argumentNumber++, attribute);
			}
			return statement.executeUpdate();
		} catch (NamingException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

}
