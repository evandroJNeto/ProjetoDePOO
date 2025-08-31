module projetodepoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.desktop;
    requires java.base;
    
    opens projetodepoo.view to javafx.fxml;
    exports projetodepoo;
    exports projetodepoo.modelos;
    exports projetodepoo.processamento;
    exports projetodepoo.view;
    exports projetodepoo.grafico;
}