package com.slotmachine1.slot_functions;

import com.slotmachine1.extras.Validate_account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Casinos_record_win_loses {
    private static final String UPDATE_VOUCHERS_WIN_CASINO = "UPDATE casinos SET casino_total_win=casino_total_win+? WHERE casino_name = ?";

    private static final String UPDATE_VOUCHERS_LOSE_CASINO ="UPDATE casinos SET casino_total_lose=casino_total_lose-? WHERE casino_name = ?";


    public static void updateCasinoRecordWin(String casino,int win) throws SQLException {

        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(UPDATE_VOUCHERS_WIN_CASINO)) {
            state.setInt(1, win);
            state.setString(2, casino);
            state.executeUpdate();
        }
    }

    public static void updateCasinoRecordLose(String casino,int lose) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(UPDATE_VOUCHERS_LOSE_CASINO)) {
            state.setInt(1, lose);
            state.setString(2, casino);
            state.executeUpdate();
        }
    }
}

