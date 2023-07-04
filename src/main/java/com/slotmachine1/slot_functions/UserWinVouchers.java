package com.slotmachine1.slot_functions;

import com.slotmachine1.extras.Validate_account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserWinVouchers {

    private static final String UPDATE_VOUCHERS_WIN = "UPDATE users SET vouchers=vouchers+? WHERE username = ?";

    private static final String UPDATE_VOUCHERS_LOSE ="UPDATE users SET vouchers=vouchers-? WHERE username = ?";


    public static void updateVocuhersWin(String username,int voucher_amt) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(UPDATE_VOUCHERS_WIN)) {
            state.setInt(1,voucher_amt);
            state.setString(2,username);
            state.executeUpdate();

        }

    }
    public static void updateVocuhersLose(String username,int voucher_amt) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(UPDATE_VOUCHERS_LOSE)) {
            state.setInt(1,voucher_amt);
            state.setString(2,username);
            state.executeUpdate();

        }

    }
}