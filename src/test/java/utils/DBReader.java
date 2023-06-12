package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Mans;
import models.Womans;

public class DBReader {
    private final static String URL = "jdbc:postgresql://localhost:4567/postgres";
    private final static String USER_NAME = "myUser";
    private final static String USER_PASSWORD = "Test123!!!";
    private final static String QUERY_SELECT_MANS = "select * from mans m";

    public static List<Mans> getMansFromDB() {
        List<Mans> mans = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement =  connection.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_MANS);


            while (resultSet.next()){
                Mans man = new Mans(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                mans.add(man);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return mans;
    }

    public static void main(String[] args){
        getMansFromDB();
    }
}
