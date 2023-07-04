package com.slotmachine1.admin_pages;

import com.slotmachine1.extras.Alerts_infos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class _admin_voucher_set {

    private Stage stage;
    private Scene scene;
    private Parent root;



    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/slot_machine_db";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "1234";

    private static final String UPDATE_INITIAL_VOUCHERS = "UPDATE controls SET value = ? WHERE name_control = ?";


    @FXML
    private TextField init_voucher;


    @FXML
    public void setInitialVoucher(ActionEvent event) throws SQLException {


        if(init_voucher.getText().isEmpty())
        {
            Alerts_infos.infoBox("Please enter the voucher value!",null,"Failed");
            return;
        }

        int voucher = Integer.parseInt(init_voucher.getText());

        if(voucher <=0)
        {
            Alerts_infos.infoBox("The voucher value must be grater than 0!",null,"Failed");
        }
        else
        {

            try (Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                 PreparedStatement state = connection.prepareStatement(UPDATE_INITIAL_VOUCHERS)) {
                state.setInt(1,voucher);
                state.setString(2,"initial_voucher");
                state.executeUpdate();

                Alerts_infos.infoBox("The initial vouchers was set " + voucher,null,"Changed");

            }
        }
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

}
