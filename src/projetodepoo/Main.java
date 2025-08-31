package projetodepoo;

import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Leitor leitor = new Leitor();
        List<Jogo> todosOsJogos = leitor.lerJogos("campeonato-brasileiro.csv");
        Campeonato brasileirao = new Campeonato(todosOsJogos);
        brasileirao.processarResultados();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TabelaView.fxml"));
        Parent root = loader.load();
        
        TabelaViewController controller = loader.getController();
        controller.setCampeonato(brasileirao);
        
        Scene scene = new Scene(root);
        stage.setTitle("Tabela do Brasileirão");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}