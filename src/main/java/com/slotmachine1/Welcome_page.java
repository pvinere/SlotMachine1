package com.slotmachine1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Welcome_page extends Application {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) {
        try {

            URL url = new File("src/main/resources/fxml_files/welcome_page.fxml").toURI().toURL();

            Parent root = FXMLLoader.load(url);
            Scene first_page = new Scene(root);
            stage.setScene(first_page);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void adminLoginButton(ActionEvent event)
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
    public void registerButton(ActionEvent event)
    {
        try{
            URL url = new File("src/main/resources/fxml_files/register_page.fxml").toURI().toURL();
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
    public void loginButton(ActionEvent event)
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

    public static void main(String[] args) {
        launch();
    }
}
