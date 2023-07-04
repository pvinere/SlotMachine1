package com.slotmachine1.main_pages;

import com.slotmachine1.extras.Validate_account;
import com.slotmachine1.log_reg_pages.Login_admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Admin_page implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label setUsernameAdminLabel;

    private static final String GET_USERNAME = "SELECT username FROM administrators WHERE username = ?";
    public static String getUsername(String username) throws SQLException {
        String name = null;
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(GET_USERNAME)) {
            state.setString(1, username);

            System.out.println(state);
            ResultSet rs = state.executeQuery();

            while(rs.next()){
                name = rs.getString("username");
            }

        }

        return name;
    }

    @FXML
    public void backButton(ActionEvent event)
    {
        try{
            URL url = new File("src/main/resources/fxml_files/login_admin.fxml").toURI().toURL();
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

    @FXML
    public void viewUsers(ActionEvent event)
    {
        try{
            URL url = new File("src/main/resources/fxml_files/view_user_page.fxml").toURI().toURL();
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
    @FXML
    public void updateUsers(ActionEvent event)
    {
        try{
            URL url = new File("src/main/resources/fxml_files/update_user_page.fxml").toURI().toURL();
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
    @FXML
    public void voucher_control(ActionEvent event)
    {
        try{
            URL url = new File("src/main/resources/fxml_files/admin_vouchers.fxml").toURI().toURL();
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
        @FXML
        public void casinos_graf(ActionEvent event)
        {
            try{
                URL url = new File("src/main/resources/fxml_files/casinos_graf.fxml").toURI().toURL();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setUsernameAdminLabel.setText(getUsername(Login_admin.public_admin_username));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
