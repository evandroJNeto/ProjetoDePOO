module projetodepoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.desktop;
    requires java.base;
    
    opens projetodepoo to javafx.fxml;
    exports projetodepoo;
}