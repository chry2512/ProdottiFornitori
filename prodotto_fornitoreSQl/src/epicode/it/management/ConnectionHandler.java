package epicode.it.management;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ConnectionHandler extends AutoCloseable {

	Connection getConnection() throws SQLException;

	void closeConnection() throws SQLException;

	PreparedStatement getPreparedStatement(String commandText) throws SQLException;

}
