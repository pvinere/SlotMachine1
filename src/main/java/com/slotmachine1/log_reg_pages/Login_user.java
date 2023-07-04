package com.slotmachine1.log_reg_pages;

import com.slotmachine1.extras.Validate_account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.slotmachine1.extras.Alerts_infos.infoBox;
import static com.slotmachine1.extras.Alerts_infos.showAlert;

public class Login_user {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public static String public_username_user;

    @FXML
    public void UserLogin(ActionEvent event) throws IOException {

        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,  "ERROR!",
                    "Please fill the username and password");
        }
        else
        {
            String username = usernameField.getText();
            String password = passwordField.getText();

            Validate_account connection = new Validate_account();
            boolean flag = connection.validate_user(username, password);

            if (!flag) {
                infoBox("Please enter correct Username and Password", null, "Failed");
            } else {
                infoBox("Login Successful!", null, "Successful");

            public_username_user = usernameField.getText();

            URL url = new File("src/main/resources/fxml_files/user_page.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
        }
    }

    @FXML
    public void backButton(ActionEvent event) throws IOException
    {
        URL url = new File("src/main/resources/fxml_files/welcome_page.fxml").toURI().toURL();
        root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
