package com.slotmachine1.slot_functions;

import com.slotmachine1.extras.Validate_account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersTotalWinLose {
    private static final String UPDATE_VOUCHERS_WIN_USERS = "UPDATE users SET wins=wins+? WHERE username = ?";

    private static final String UPDATE_VOUCHERS_LOSE_USERS ="UPDATE users SET loses=loses-? WHERE username = ?";


    public static void updateUserRecordWin(String username,int win) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(UPDATE_VOUCHERS_WIN_USERS)) {
            state.setInt(1, win);
            state.setString(2, username);
            state.executeUpdate();
        }
    }
    public static void updateUserRecordLose(String username,int lose) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(UPDATE_VOUCHERS_LOSE_USERS)) {
            state.setInt(1, lose);
            state.setString(2, username);
            state.executeUpdate();
        }
    }
}
