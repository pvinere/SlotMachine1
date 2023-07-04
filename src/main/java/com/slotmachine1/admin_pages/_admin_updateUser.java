package com.slotmachine1.admin_pages;

import com.slotmachine1.extras.Alerts_infos;
import com.slotmachine1.extras.Validate_account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class _admin_updateUser {

    private Stage stage;
    private Scene scene;
    private Parent root;

    //variabile care tin datele de conexiune la baza de date

    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/slot_machine_db";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "1234";

    //quarry-uri cu baza de date

    private static final String UPDATE_USERNAME = "UPDATE users SET username = ? WHERE username = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE password = ?";

    private static final String DELETE_USER = "DELETE FROM users WHERE username = ?";

    private static final String UPDATE_USER_VOUCHER = "UPDATE users SET vouchers = ? WHERE username = ?";

    private static final String UPDATE_USER_LIMIT ="UPDATE users SET limits = ? WHERE username = ?";

    @FXML
    private TextField username_user;
    @FXML
    private TextField password_user;

    @FXML
    private TextField update_username;
    @FXML
    private TextField update_password;


    @FXML
    private TextField set_voucher;

    @FXML
    private TextField set_limit;

    boolean data_verification = false;

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

            throw new RuntimeException(e);
        }
        return false;
    }

    @FXML
    public void backButton(ActionEvent event) throws IOException
    {
        URL url = new File("src/main/resources/fxml_files/admin_page.fxml").toURI().toURL();
        root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void checkUsernameAndPassword(ActionEvent event) throws SQLException {
        String username = username_user.getText();
        String password = password_user.getText();

        Validate_account connectionDB = new Validate_account();
        boolean flag = connectionDB.validate_user(username, password);

        if (!flag) {
            Alerts_infos.infoBox("Please enter correct Username and Password", null, "Failed");
            data_verification = false;
        } else {
            Alerts_infos.infoBox("Data's are correct, you can now change the username and password", null, "Failed");
            //update la username sau password
            data_verification = true;
        }

    }

    public void updateUserData(ActionEvent event) throws SQLException {

        String new_username = update_username.getText();
        String new_password = update_password.getText();
        String old_username = username_user.getText();
        String old_password = password_user.getText();

        if(data_verification == true)
        {
            if(update_password.getText().isEmpty())
            {
                try (Connection connection = DriverManager
                        .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                     PreparedStatement dl = connection.prepareStatement(UPDATE_USERNAME)) {
                    dl.setString(1, new_username);
                    dl.setString(2, old_username);
                    dl.executeUpdate();
                    Alerts_infos.infoBox("The new username is: " + new_username , null, "Failed");
                }
            }
            else if(update_username.getText().isEmpty())
            {
                try (Connection connection = DriverManager
                        .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                     PreparedStatement dl = connection.prepareStatement(UPDATE_PASSWORD)) {
                    dl.setString(1, new_password);
                    dl.setString(2, old_password);
                    dl.executeUpdate();
                    Alerts_infos.infoBox("The new password is: " + new_password , null, "Failed");
                }
            }
            else

            {
                try (Connection connection = DriverManager
                        .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                     PreparedStatement dl = connection.prepareStatement(UPDATE_USERNAME)) {
                    dl.setString(1, new_username);
                    dl.setString(2, old_username);
                    dl.executeUpdate();

                }

                try (Connection connection = DriverManager
                        .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                     PreparedStatement dl = connection.prepareStatement(UPDATE_PASSWORD)) {
                    dl.setString(1, new_password);
                    dl.setString(2, old_password);
                    dl.executeUpdate();
                    Alerts_infos.infoBox("The new username and password is: " + new_username + " " + new_password , null, "Failed");
                }
            }
        }
        else {
            Alerts_infos.infoBox("Please enter the old User Username and Password", null, "Failed");

        }

    }

    @FXML
    public void deleteUser(ActionEvent event) throws SQLException {

        String old_username = username_user.getText();

        if(data_verification==true) {
            try (Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                 PreparedStatement dl = connection.prepareStatement(DELETE_USER)) {
                dl.setString(1, old_username);
                dl.executeUpdate();
                Alerts_infos.infoBox("User with username: " + username_user.getText() + " was DELETED!", null, "DELETED");
            }

        }
        else
        {
            Alerts_infos.infoBox("Please enter the User Username and Password to delete it.", null, "Failed");
        }

    }

    @FXML
    public void updateUserVoucher(ActionEvent event) throws SQLException {

        String username = username_user.getText();

        if(set_voucher.getText().isEmpty())
        {
            Alerts_infos.infoBox("Please enter the voucher value!",null,"Failed");
            return;
        }

        int voucher = Integer.parseInt(set_voucher.getText());

        if(voucher >=0)
        {
            Alerts_infos.infoBox("The voucher value must be grater than 0!",null,"Failed");
        }
        else
        {

            try (Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                 PreparedStatement state = connection.prepareStatement(UPDATE_USER_VOUCHER)) {
                state.setInt(1,voucher);
                state.setString(2,username);

                state.executeUpdate();

                Alerts_infos.infoBox("The user vouchers was set " + voucher,null,"Changed");

            }
        }

    }

    @FXML
    public void updateUserLimit(ActionEvent event) throws SQLException {
        String username = username_user.getText();

        if(set_limit.getText().isEmpty())
        {
            Alerts_infos.infoBox("Please enter the limit value!",null,"Failed");
            return;
        }

        int limit = Integer.parseInt(set_limit.getText());

        if(limit <=0)
        {
            Alerts_infos.infoBox("The limit value must be grater than 0!",null,"Failed");
        }
        else
        {

            try (Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                 PreparedStatement state = connection.prepareStatement(UPDATE_USER_LIMIT)) {
                state.setInt(1,limit);
                state.setString(2,username);

                state.executeUpdate();

                Alerts_infos.infoBox("The user limit was set " + limit,null,"Changed");

            }
        }

    }

}
