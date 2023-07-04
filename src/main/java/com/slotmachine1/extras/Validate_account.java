package com.slotmachine1.extras;

import java.sql.*;

public class Validate_account {

    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/slot_machine_db";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "1234";

    private static final String SELECT_QUERY_USERS = "SELECT * FROM users WHERE username = ? and password = ?";

    private static final String SELECT_QUERY_ADMINS = "SELECT * FROM administrators WHERE username = ? and password = ?";


    private boolean getConnection(String username, String password, String selectQuery) {
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             PreparedStatement state = connection.prepareStatement(selectQuery)) {
            state.setString(1, username);
            state.setString(2, password);

            System.out.println(state);

            ResultSet resultSet = state.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public boolean validate_user(String username,String password)
    {
        return getConnection(username,password,SELECT_QUERY_USERS);
    }

    public boolean validate_admins(String username,String password)
    {
        return getConnection(username,password,SELECT_QUERY_ADMINS);
    }

}
