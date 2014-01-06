package de.uni_passau.facultyinfo.server.dao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
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
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
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
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int executeStatement(String sql) {
		return executeStatement(sql, null);
	}

	public int executeStatement(String sql, AttributeContainer attributes) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			if (attributes != null) {
				if (attributes.getStringAttributes() != null) {
					Iterator<Entry<Integer, String>> iter = attributes
							.getStringAttributes().entrySet().iterator();
					while (iter.hasNext()) {
						Entry<Integer, String> entry = iter.next();
						statement.setString(entry.getKey(), entry.getValue());
					}
				}
				if (attributes.getDateTimeAttributes() != null) {
					Iterator<Entry<Integer, Timestamp>> iter = attributes
							.getDateTimeAttributes().entrySet().iterator();
					while (iter.hasNext()) {
						Entry<Integer, Timestamp> entry = iter.next();
						statement
								.setTimestamp(entry.getKey(), entry.getValue());
					}
				}
				if (attributes.getTimeAttributes() != null) {
					Iterator<Entry<Integer, Time>> iter = attributes
							.getTimeAttributes().entrySet().iterator();
					while (iter.hasNext()) {
						Entry<Integer, Time> entry = iter.next();
						statement.setTime(entry.getKey(), entry.getValue());
					}
				}
				if (attributes.getDoubleAttributes() != null) {
					Iterator<Entry<Integer, Double>> iter = attributes
							.getDoubleAttributes().entrySet().iterator();
					while (iter.hasNext()) {
						Entry<Integer, Double> entry = iter.next();
						statement.setDouble(entry.getKey(), entry.getValue());
					}
				}
				if (attributes.getIntegerAttributes() != null) {
					Iterator<Entry<Integer, Integer>> iter = attributes
							.getIntegerAttributes().entrySet().iterator();
					while (iter.hasNext()) {
						Entry<Integer, Integer> entry = iter.next();
						statement.setInt(entry.getKey(), entry.getValue());
					}
				}
			}
			return statement.executeUpdate();
		} catch (NamingException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

}
