module com.example.slotmachine1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.slotmachine1 to javafx.fxml;
    exports com.slotmachine1;
    exports com.slotmachine1.log_reg_pages;
    opens com.slotmachine1.log_reg_pages to javafx.fxml;
    exports com.slotmachine1.admin_pages;
    opens com.slotmachine1.admin_pages to javafx.fxml;
    exports com.slotmachine1.main_pages;
    opens com.slotmachine1.main_pages to javafx.fxml;
    exports com.slotmachine1.extras;
    opens com.slotmachine1.extras to javafx.fxml;
}