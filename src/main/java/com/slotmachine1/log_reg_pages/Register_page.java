package com.slotmachine1.log_reg_pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;

import static com.slotmachine1.extras.Alerts_infos.infoBox;
import static com.slotmachine1.extras.Alerts_infos.showAlert;

public class Register_page {
    //variabile conexiune baza de date
    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/slot_machine_db";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "1234";

    public int vouchers;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private static final String INSERT_IN_DB_USER = "INSERT INTO users (username, password,vouchers) VALUES (?,?,?)";

    private static final String GET_INIT_VOUCHERS = "SELECT value FROM controls WHERE name_control = 'initial_voucher'";


    public boolean checkUsername(String username)
    {
        String query = "SELECT username FROM users WHERE username='" + username + "'";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);

             PreparedStatement state = connection.prepareStatement(query)){
            ResultSet rs = state.executeQuery(query);

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setInitialVouchers()
    {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);

             PreparedStatement state = connection.prepareStatement(GET_INIT_VOUCHERS)) {

            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                vouchers = rs.getInt("value");
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    public void registerNewAccount(ActionEvent event)  {

        String username = usernameField.getText();
        String password = passwordField.getText();


        if(checkUsername(username))
        {
            showAlert(Alert.AlertType.ERROR,  "ERROR!",
                    "Username "+ username + " already exists ! Try a new username!");
        }
        else if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,  "ERROR!",
                    "Please fill the username and the password!");
        }
        else
        {

            try (Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);

                 PreparedStatement state = connection.prepareStatement(INSERT_IN_DB_USER)){
                state.setString(1,username);
                state.setString(2,password);
                setInitialVouchers();
                state.setInt(3,vouchers);
                state.executeUpdate();

                infoBox("Hello " + username + "! " + "Welcome to THE SLOT MACHINE! For first registration you got " +vouchers +" Vouchers to play!",null,"Register successfully");


                try{
                    URL url = new File("src/main/resources/fxml_files/login_user_page.fxml").toURI().toURL();
                    root = FXMLLoader.load(url);
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
    @FXML
    public void backButton(ActionEvent event)
    {
        try{
            URL url = new File("src/main/resources/fxml_files/welcome_page.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
