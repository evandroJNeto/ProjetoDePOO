package projetodepoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projetodepoo.view.TabelaViewController;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetodepoo/view/TabelaView.fxml"));
        Parent root = loader.load();
        
        TabelaViewController controller = loader.getController();
        
        Scene scene = new Scene(root);
        stage.setTitle("Tabela do Brasileirão");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}