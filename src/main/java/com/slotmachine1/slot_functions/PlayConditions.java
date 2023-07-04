package com.slotmachine1.slot_functions;

import com.slotmachine1.extras.Validate_account;

import java.sql.*;

public class PlayConditions {

    private static final String SELECT_USER_LIMIT = "SELECT vouchers FROM users WHERE username = ?";

    private static final String UPDATE_USER_LIMIT = "UPDATE users SET limits = 0 WHERE username = ?";
    private static final String UPDATE_USER_VOUC = "UPDATE users SET vouchers = 500 WHERE username = ?";

    public static boolean countUserVouchers(String username) throws SQLException {

        int vouchers = 0;

        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(SELECT_USER_LIMIT)) {
            state.setString(1, username);
            ResultSet rs = state.executeQuery();
            while(rs.next()){

                vouchers = rs.getInt("vouchers");
            }
        }

        if(vouchers > 0)
        {
            return true;
        }
        else
            return false;
    }

    public static void updateLimit(String username) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement dl = connection.prepareStatement(UPDATE_USER_LIMIT)) {
            dl.setString(1, username);
            dl.executeUpdate();
        }
    }

    public static void updateVouchers(String username) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement dl = connection.prepareStatement(UPDATE_USER_VOUC)) {
            dl.setString(1, username);
            dl.executeUpdate();
        }
    }

}
