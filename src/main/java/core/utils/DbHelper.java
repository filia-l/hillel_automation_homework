package core.utils;

import core.dto.ProductDetailsDTO;

import java.sql.*;

public class DbHelper {

    private static Connection connection;

    private static final String USERNAME = UrlBuilder.getPropertyValue("database.username");
    private static final String PASSWORD = UrlBuilder.getPropertyValue("database.password");
    private static final String CONNECTION_STRING = UrlBuilder.getPropertyValue("database.connection.string");

    private DbHelper() {
    }

    public static ProductDetailsDTO executeQuery(final String sqlQuery) throws SQLException {
        final ProductDetailsDTO detailsDTO = new ProductDetailsDTO();
        if ( connection == null || connection.isClosed()) {
            connectToDb();
            final PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                detailsDTO.setTitle(resultSet.getString("Title"));
                detailsDTO.setQty(resultSet.getString("Quantity"));
                detailsDTO.setPrice(resultSet.getString("Price"));
                detailsDTO.setTotal(resultSet.getString("Total"));
                detailsDTO.setColor(resultSet.getString("Color"));
                detailsDTO.setSize(resultSet.getString("Size"));
            }
        }
        return detailsDTO;
    }

    private static void connectToDb() throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
    }
}
