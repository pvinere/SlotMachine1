package com.slotmachine1.main_pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.slotmachine1.extras.Alerts_infos.infoBox;
import static com.slotmachine1.extras.Alerts_infos.showAlert;
import static com.slotmachine1.log_reg_pages.Login_user.public_username_user;
import static com.slotmachine1.slot_functions.Casinos_record_win_loses.updateCasinoRecordLose;
import static com.slotmachine1.slot_functions.Casinos_record_win_loses.updateCasinoRecordWin;
import static com.slotmachine1.slot_functions.LimitUser.LimitUserSpins;
import static com.slotmachine1.slot_functions.LimitUser.countUserSpins;
import static com.slotmachine1.slot_functions.PlayConditions.countUserVouchers;
import static com.slotmachine1.slot_functions.UserTimeCount.updateUserTime;
import static com.slotmachine1.slot_functions.UserVouchers.getUserVouchers;
import static com.slotmachine1.slot_functions.UserWinVouchers.updateVocuhersLose;
import static com.slotmachine1.slot_functions.UserWinVouchers.updateVocuhersWin;
import static com.slotmachine1.slot_functions.UsersTotalWinLose.updateUserRecordLose;
import static com.slotmachine1.slot_functions.UsersTotalWinLose.updateUserRecordWin;


//la fel ca la casino1
public class casino2 implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public long start = System.currentTimeMillis();

    String casino_name="casino2";
    @FXML
    HBox row1;
    @FXML
    ImageView imageView1,imageView2,imageView3;
    @FXML
    TextField betField;
    @FXML
    Label userVouchers;
    @FXML
    Label lbWonSpin;
    @FXML
    Label lbWonTotal;
    int TotalWon = 0;

    int user_voucher_local=0;

    public int getRandomInteger(int maximum, int minimum)
    {
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

    @FXML
    public void Spin(ActionEvent event) throws SQLException {



        if(betField.getText().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,  "ERROR!",
                    "Pune o suma de vouchere");
        }
        else if(LimitUserSpins(public_username_user)!=true)
        {

            infoBox("Ai atins numarul de spinuri maxime " + 100 ,null,"Limita spin-uri");

        }
        else if(countUserVouchers(public_username_user)==false)
        {
            infoBox("Ai ramas fara vouchere!",null,"Limita vouchere");
        }
        else
        {
            int betAmount = Integer.parseInt(betField.getText());
            SetImage(getRandomInteger(12,1),getRandomInteger(12,1),getRandomInteger(12,1),betAmount);
            countUserSpins(public_username_user);
        }

    }


    public void SetImage(int random1,int random2,int random3,int betAmount) throws SQLException {
        row1.getChildren().clear();

        String path="E:\\_CODES\\GitHub\\SlotMachine1\\src\\main\\java\\com\\slotmachine1\\main_pages\\images\\"+random1+".jpg";
        imageView1.setImage(new Image(path));
        row1.getChildren().addAll(imageView1);


        String path1="E:\\_CODES\\GitHub\\SlotMachine1\\src\\main\\java\\com\\slotmachine1\\main_pages\\images\\"+random2+".jpg";
        imageView2.setImage(new Image(path1));
        row1.getChildren().addAll(imageView2);


        String path2="E:\\_CODES\\GitHub\\SlotMachine1\\src\\main\\java\\com\\slotmachine1\\main_pages\\images\\"+random3+".jpg";
        imageView3.setImage(new Image(path2));
        row1.getChildren().addAll(imageView3);

        if (random1== random2 && random1==random3)
        {
            betAmount=betAmount*3;
            System.out.println(betAmount);
            user_voucher_local = user_voucher_local + betAmount;
            updateVocuhersWin(public_username_user,betAmount);
            updateCasinoRecordWin(casino_name,betAmount);
            updateUserRecordWin(public_username_user,betAmount);


        }
        else if (random1== random2 || random1==random3 || random2==random3)
        {
            betAmount=betAmount*2;
            System.out.println(betAmount);
            user_voucher_local = user_voucher_local + betAmount;
            updateVocuhersWin(public_username_user,betAmount);
            updateCasinoRecordWin(casino_name,betAmount);
            updateUserRecordWin(public_username_user,betAmount);

        }
        else
        {
            user_voucher_local = user_voucher_local - betAmount;
            System.out.println(betAmount);

            updateVocuhersLose(public_username_user,betAmount);

            updateCasinoRecordLose(casino_name,betAmount);
            updateUserRecordLose(public_username_user,betAmount);
        }

        TotalWon=TotalWon+betAmount;

        lbWonSpin.setText(Integer.toString(betAmount));
        lbWonTotal.setText(Integer.toString(TotalWon));
        userVouchers.setText(Integer.toString(user_voucher_local));

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

            long finish = System.currentTimeMillis();

            long timeElapsed = finish - start;

            long seconds = (timeElapsed/1000)%60;

            updateUserTime(public_username_user,(int)seconds);


            System.out.println(seconds);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            userVouchers.setText(getUserVouchers(public_username_user));
            user_voucher_local = Integer.parseInt(userVouchers.getText());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
