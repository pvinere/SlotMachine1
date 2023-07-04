package com.slotmachine1.admin_pages;

import com.slotmachine1.extras.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class _admin_viewUser implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private static final String SELECT_VIEW = "SELECT * from users";
    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/slot_machine_db";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "1234";

    //obiecte din javafx
    @FXML
    private TableView<Users> tableView;
    @FXML
    private TableColumn<Users, Integer> col_id;
    @FXML
    private TableColumn<Users, String> col_username;
    @FXML
    private TableColumn<Users, String> col_password;
    @FXML
    private TableColumn<Users, Integer>col_vouchers;
    @FXML
    private TableColumn<Users, Integer>col_wins;
    @FXML
    private TableColumn<Users, Integer>col_loses;
    @FXML
    private TableColumn<Users, Integer>col_limit;
    @FXML
    private TableColumn<Users, Integer>col_time;

    ObservableList<Users> listU;


    public static ObservableList<Users> getDataUsers() throws SQLException {

        ObservableList<Users> list = FXCollections.observableArrayList();

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(SELECT_VIEW)) {
            ResultSet rs = state.executeQuery();

            while (rs.next()) {

                list.add(new Users(Integer.parseInt(rs.getString("id_users")),
                        rs.getString("username"),
                        rs.getString("password"),
                        (Integer.parseInt(rs.getString("vouchers"))),
                                (Integer.parseInt(rs.getString("wins"))),
                                        (Integer.parseInt(rs.getString("loses"))),
                                                (Integer.parseInt(rs.getString("limits"))),
                        (Integer.parseInt(rs.getString("timer")))));

            }
        }
        return list;
    }


    @FXML
    public void backButton(ActionEvent event)
    {
        try{
            URL url = new File("src/main/resources/fxml_files/admin_page.fxml").toURI().toURL();
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
    public void initialize(URL url, ResourceBundle rb) {
        col_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        col_vouchers.setCellValueFactory(new PropertyValueFactory<Users,Integer>("vouchers"));
        col_wins.setCellValueFactory(new PropertyValueFactory<Users,Integer>("wins"));
        col_loses.setCellValueFactory(new PropertyValueFactory<Users,Integer>("loses"));
        col_limit.setCellValueFactory(new PropertyValueFactory<Users,Integer>("limits"));
        col_time.setCellValueFactory(new PropertyValueFactory<Users,Integer>("timer"));


        try {
            listU = getDataUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableView.setItems(listU);
    }
}
