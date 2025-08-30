package projetodepoo;

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

        public String getNome() { return this.nome; }
        public int getVitorias() { return this.vitorias; }
        public int getEmpates() { return this.empates; }
        public int getDerrotas() { return this.derrotas; }
    }


    public static void criarGrafico(List<Time> lista) {
        Stage stage = new Stage(); 

        int width = 800, height = 600, margem = 50;
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        String[] series = {"Vitórias", "Empates", "Derrotas"};
        Color[] cores = {Color.GREEN, Color.GOLD, Color.RED};

        int maxValue = lista.stream()
                .flatMapToInt(t -> Arrays.stream(new int[]{t.getVitorias(), t.getEmpates(), t.getDerrotas()}))
                .max()
                .orElse(1);

        int nTimes = lista.size(), nSeries = series.length;
        int larguraPainel = width - 2 * margem;
        int alturaPainel = height - 2 * margem;
        int larguraGrupo = larguraPainel / nTimes;
        int larguraBarra = (int) (larguraGrupo * 0.8 / nSeries);
        int separacaoGrupo = larguraGrupo - larguraBarra * nSeries;

        gc.setStroke(Color.BLACK);
        gc.strokeLine(margem, margem, margem, margem + alturaPainel);
        gc.strokeLine(margem, margem + alturaPainel, margem + larguraPainel, margem + alturaPainel);

        for (int i = 0; i < nTimes; i++) {
            Time t = lista.get(i);
            int[] v = {t.getVitorias(), t.getEmpates(), t.getDerrotas()};
            int xGrupo = margem + i * larguraGrupo;

            for (int j = 0; j < nSeries; j++) {
                double p = (double) v[j] / maxValue;
                int hBarra = (int) (p * (alturaPainel - 20));
                int xBarra = xGrupo + j * larguraBarra + j * (separacaoGrupo / (nSeries - 1));
                int yBarra = margem + alturaPainel - hBarra;

                gc.setFill(cores[j]);
                gc.fillRect(xBarra, yBarra, larguraBarra, hBarra);
                gc.setStroke(Color.BLACK);
                gc.strokeRect(xBarra, yBarra, larguraBarra, hBarra);
            }

            gc.setFill(Color.BLACK);
            Text temp = new Text(t.getNome());
            double textWidth = temp.getLayoutBounds().getWidth();
            double tx = xGrupo + larguraGrupo / 2.0 - textWidth / 2.0;
            gc.fillText(t.getNome(), tx, margem + alturaPainel + 15);
        }
        

        Pane root = new Pane(canvas);
        stage.setScene(new Scene(root));
        stage.setTitle("Gráfico de Desempenho");
        stage.show();
    }
}