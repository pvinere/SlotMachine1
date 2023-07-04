package com.slotmachine1.main_pages;

import com.slotmachine1.extras.Validate_account;
import com.slotmachine1.log_reg_pages.Login_user;
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

import static com.slotmachine1.extras.Alerts_infos.infoBox;
import static com.slotmachine1.log_reg_pages.Login_user.public_username_user;
import static com.slotmachine1.slot_functions.LimitUser.LimitUserSpins;
import static com.slotmachine1.slot_functions.PlayConditions.*;

public class User_page implements Initializable {

    long start = System.currentTimeMillis();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label setUsernameLabel;

    private static final String GET_USERNAME = "SELECT username FROM users WHERE username = ?";


    public static String getUsername(String username) throws SQLException {
        String name = null;
        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(GET_USERNAME)) {
            state.setString(1, username);

            System.out.println(state);
            ResultSet rs = state.executeQuery();

            while(rs.next()){
                System.out.println("Name: " + rs.getString("username"));
                name = rs.getString("username");
            }

        }

        return name;
    }

    @FXML
    public void backButton(ActionEvent event)
    {
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
    }
    @FXML
    public void btt_casino1(ActionEvent event) throws SQLException {

        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;

        if(LimitUserSpins(public_username_user)!=true)
        {

            infoBox("Ai atins numarul de spinuri maxime " + 100 + "Trebuie sa astepti 10 secunde pentru a se reseta",null,"Limita spin-uri");

            if(timeElapsed>10000)
            {
                updateLimit(public_username_user);
            }

        }

        else if(countUserVouchers(public_username_user)==false)
        {
            infoBox("Ai ramas fara vouchere, Trebuie sa astepti 10 secunde pentru a se reseta!",null,"Limita vouchere");

            if(timeElapsed>10000)
            {
                updateVouchers(public_username_user);
            }
        }
        else
        {

            try{
                URL url = new File("src/main/resources/fxml_files/casino1.fxml").toURI().toURL();
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
    public void btt_casino2(ActionEvent event) throws SQLException {

        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;


        if(LimitUserSpins(public_username_user)!=true)
        {

            infoBox("Ai atins numarul de spinuri maxime " + 100 + "Trebuie sa astepti 10 secunde pentru a se reseta",null,"Limita spin-uri");

            if(timeElapsed>10000)
            {
                updateLimit(public_username_user);
            }

        }
        else if(countUserVouchers(public_username_user)==false)
        {
            infoBox("Ai ramas fara vouchere, Trebuie sa astepti 10 secunde pentru a se reseta!",null,"Limita vouchere");

            if(timeElapsed>10000)
            {
                updateVouchers(public_username_user);
            }
        }
        else {
            try{
                URL url = new File("src/main/resources/fxml_files/casino2.fxml").toURI().toURL();
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setUsernameLabel.setText(getUsername(Login_user.public_username_user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
