package com.slotmachine1.admin_pages;

import com.slotmachine1.extras.Validate_account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class _admin_Casino_Results implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private BarChart barChart;


    private static final String SELECT_CASINO1_WIN = "SELECT casino_total_win FROM casinos WHERE casino_name = 'casino1'";

    private static final String SELECT_CASINO1_LOSE = "SELECT casino_total_lose FROM casinos WHERE casino_name = 'casino1'";

    private static final String SELECT_CASINO2_WIN = "SELECT casino_total_win FROM casinos WHERE casino_name = 'casino2'";

    private static final String SELECT_CASINO2_LOSE = "SELECT casino_total_lose FROM casinos WHERE casino_name = 'casino2'";

    public static int getDataWin(String states) throws SQLException {

        int data = 0;

        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(states)) {

            ResultSet rs = state.executeQuery();
            while(rs.next()){

                data = rs.getInt("casino_total_win");
            }

        }


        return data;
    }

    public static int getDataLose(String states) throws SQLException {

        int data = 0;

        try (Connection connection = DriverManager
                .getConnection(Validate_account.DATABASE_URL, Validate_account.DATABASE_USERNAME, Validate_account.DATABASE_PASSWORD);
             PreparedStatement state = connection.prepareStatement(states)) {

            ResultSet rs = state.executeQuery();
            while(rs.next()){

                data = rs.getInt("casino_total_lose");
            }

        }

        if(data<0)
        {
            data=Math.abs(data);
        }

        return data;
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

    @Override
    public void initialize(URL url, ResourceBundle rb){

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();

        series1.setName("WINS");
        series2.setName("LOSES");
        series3.setName("WINS");
        series4.setName("LOSES");
        try {

            series1.getData().add(new XYChart.Data("Casino1",getDataWin(SELECT_CASINO1_WIN)));
            series2.getData().add(new XYChart.Data("Casino1",getDataLose(SELECT_CASINO1_LOSE)));
            series3.getData().add(new XYChart.Data("Casino2",getDataWin(SELECT_CASINO2_WIN)));
            series4.getData().add(new XYChart.Data("Casino2",getDataLose(SELECT_CASINO2_LOSE)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        barChart.getData().addAll(series1);
        barChart.getData().addAll(series2);
        barChart.getData().addAll(series3);
        barChart.getData().addAll(series4);


    }

}
