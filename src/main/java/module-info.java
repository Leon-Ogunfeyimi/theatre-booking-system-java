module com.example.ca1bookingfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;


    opens com.example.ca1bookingfx to javafx.fxml;
    exports com.example.ca1bookingfx;
}