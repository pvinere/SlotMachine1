package com.slotmachine1.slot_functions;

import com.slotmachine1.extras.Validate_account;

import java.sql.*;

public class UserVouchers {

    private static final String GET_USER_VOUCHERS = "SELECT vouchers FROM users WHERE username = ?";

    public static String getUserVouchers(String username) throws SQLException {
        String vouchers = null;
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(GET_USER_VOUCHERS)) {
            state.setString(1, username);
            ResultSet rs = state.executeQuery();

            while(rs.next()){
                vouchers = rs.getString("vouchers");
            }
        }
        return vouchers;
    }

}
