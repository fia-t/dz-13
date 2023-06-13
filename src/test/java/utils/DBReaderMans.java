package utils;

import java.sql.*;

public class DBReaderMans {
    private final static String URL = "jdbc:postgresql://localhost:4567/postgres";
    private final static String USER_NAME = "myUser";
    private final static String USER_PASSWORD = "Test123!!!";
    private final static String QUERY_SELECT_MANS = "select * from mans m";
    private final static String QUERY_SELECT_MAN = "select * from mans m where id=?";
    private final static String QUERY_INSERT_MAN = "insert into mans values(?,?,?,?,?)";
    private final static String QUERY_UPDATE_MAN = "update mans set firstname=? where id=?";
    private final static String QUERY_DELETE_MAN = "delete from mans where id=?";

    public static void getAllMansFromDB()throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD))  {
            Statement sqlStatement =  connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_MANS);

            resultSet.first();
            for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                System.out.print(resultSet.getMetaData().getColumnName(j) + "\t\t");
            }
            System.out.println();

            resultSet.beforeFirst();
            while (resultSet.next()) {
//                System.out.print(resultSet.getArray("id")+ "\n\n");
                for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                    System.out.print(resultSet.getString(j) + "\t\t");
                }
                System.out.println();
            }

            if (resultSet != null) { resultSet.close(); }
            if (sqlStatement != null) { sqlStatement.close(); }
            if (connection != null) { resultSet.close(); }


        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static boolean getManFromDB(int id) throws SQLException{

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement =  connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_MAN);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
//                System.out.print(resultSet.getArray("id")+ "\n\n");
                for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                    System.out.print(resultSet.getString(j) + "\t\t");
                }
                System.out.println();
            }

            if (resultSet != null) { resultSet.close(); }
            if (sqlStatement != null) { sqlStatement.close(); }
            if (connection != null) { resultSet.close(); }


        } catch (SQLException e) {
            printSQLException(e);
        }
        return true;
    }

    public static void insertMan(int id, String firstname, String lastname, int age, int partner_id) throws SQLException{
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_MAN);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setInt(4, age);
            preparedStatement.setInt(5, partner_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void updateMan(int id, String firstname) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_MAN);
            preparedStatement.setString(1, firstname);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void deleteMan(int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_MAN);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
//public static List<Mans> getMansFromDB() {
//    List<Mans> mans = new ArrayList<>();
//
//    try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
//        Statement sqlStatement = connection.createStatement();
//        ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_MANS);
//
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        int columnCount = metaData.getColumnCount();
//
//        while (resultSet.next()) {
//            String[] rowData = new String[columnCount];
//            for (int i = 1; i <= columnCount; i++) {
//                rowData[i - 1] = resultSet.getString(i);
//            }
//            mans.add(new Mans(rowData));
//        }
//
//        resultSet.close();
//        sqlStatement.close();
//    } catch (SQLException exception) {
//        throw new RuntimeException(String.format("Please check connection string" +
//                ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD), exception);
//    }
//    return mans;
//}
    public static void main(String[] args) throws SQLException{
        System.out.println("Вибираємо всі значення таблиці Mans, 3 записи" + "\t\t");
        getAllMansFromDB();
        System.out.println("Додаємо четвертий запис в таблицю Mans " + "\t\t");
        insertMan(4, "Test", "Test2", 60, 1);
        System.out.println("Вибираємо четвертий запис з таблиці Mans " + "\t\t");
        if (getManFromDB(4)){
            System.out.println("У четвертого запису змінюємо firstname Mans " + "\t\t");
            updateMan(4, "Roberto");
            System.out.println("Вибираємо всі значення таблиці Mans, 4 записи" + "\t\t");
            getAllMansFromDB();
            System.out.println("Видаляємо четвертий запис з таблиці Mans " + "\t\t");
            deleteMan(4);
            System.out.println("Вибираємо всі значення таблиці Mans, 3 записи" + "\t\t");
            getAllMansFromDB();
        }
    }
}
