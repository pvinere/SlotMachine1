package com.slotmachine1.slot_functions;

import com.slotmachine1.extras.Validate_account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserTimeCount {

    private static final String UPDATE_TIME_USER= "UPDATE users SET timer=timer+? WHERE username = ?";
    public static void updateUserTime(String username,int timer) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(UPDATE_TIME_USER)) {
            state.setInt(1, timer);
            state.setString(2, username);
            state.executeUpdate();
        }
    }
}
