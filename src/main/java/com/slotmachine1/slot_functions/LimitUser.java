package com.slotmachine1.slot_functions;

import com.slotmachine1.extras.Validate_account;

import java.sql.*;

public class LimitUser {
    private static final String SELECT_USER_LIMIT = "SELECT limits FROM users WHERE username = ?";

    private static final String UPDATE_USER_LIMIT = "UPDATE users SET limits = limits + 1 WHERE username = ?";

    public static void countUserSpins(String username) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement dl = connection.prepareStatement(UPDATE_USER_LIMIT)) {
            dl.setString(1, username);
            dl.executeUpdate();
        }
    }

    public static boolean LimitUserSpins(String username) throws SQLException{

            int limit = 0;

            try (Connection connection = DriverManager
                    .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
                 PreparedStatement state = connection.prepareStatement(SELECT_USER_LIMIT)) {
                state.setString(1,username);
                ResultSet rs = state.executeQuery();
                while(rs.next()){

                    limit = rs.getInt("limits");
                }

        }
            if(limit < 100)
        {
            return true;
        }
        else
            return false;

    }





}
