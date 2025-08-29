package projetodepoo;

// Imports necessários para o JavaFX
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;

public class Graficos {
    
    static class Time {
        String nome;
        int pontos, vitorias, empates, derrotas;
        
        Time(String n, int p, int v, int e, int d) {
            nome = n; pontos = p; vitorias = v; empates = e; derrotas = d;
        }
        
        public String getNome() {
            return this.nome;
        }
    }

    public static void criarGrafico(List<Time> lista) {
        Stage stage = new Stage();

        int width = 800, height = 600, margem = 50;
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane root = new Pane(canvas);
        stage.setScene(new Scene(root));
        stage.setTitle("Gráfico de Desempenho");
        stage.show();
    }
}