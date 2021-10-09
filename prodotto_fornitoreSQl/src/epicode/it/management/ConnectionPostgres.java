package epicode.it.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionPostgres implements ConnectionHandler {

	private Connection connect;
	private String connectionUrl;
	private static final Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);

	public ConnectionPostgres(String dbName, String schema, String username, String password) {
		this("localhost",5432, dbName, schema, username, password);
	}

	public ConnectionPostgres(String host, int port, String dbName, String schema, String username, String password) {
		try {
			Class.forName("org.postgresql.Driver");
			connectionUrl = new StringBuilder()
					.append("jdbc:postgresql://")
					.append(host).append(":").append(port).append("/")
					.append(dbName).append("?")
					.append("currentSchema=").append(schema)
					.append("&user=").append(username)
					.append("&password=").append(password)
					.toString();

			connect = DriverManager.getConnection(connectionUrl);
			logger.info("Correct");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void close() throws Exception {
		closeConnection();

	}

	@Override
	public void closeConnection() throws SQLException {
		if (connect != null) {
			connect.close();
			connect = null;

		}

	}

	@Override
	public Connection getConnection() throws SQLException {
		if (connect != null) {
			connect = DriverManager.getConnection(connectionUrl);
			return connect;

		}

		return null;
	}

	@Override
	public PreparedStatement getPreparedStatement(String commandText) throws SQLException {

		return getConnection().prepareStatement(commandText);

	}

}
