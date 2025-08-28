package projetodepoo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Neto
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.Arrays;
import java.util.List;

public class Graficos extends Application {
    private static List<Time> initialData;
    public static void launchWith(List<Time> data) {
        initialData = data;
        // garante que só chame launch uma vez
        Application.launch();
    }
    static class Time {
        String nome;
        int pontos, vitorias, empates, derrotas;
        Time(String n, int p, int v, int e, int d) {
            nome = n; pontos = p; vitorias = v; empates = e; derrotas = d;
        }
        public String getNome(){
            return this.nome;
    }
    }

    @Override
     public void start(Stage stage) {
        List<Time> lista = initialData;

        int width = 800, height = 600, margem = 50;
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        String[] series = {"Vitórias","Empates","Derrotas"};
        Color[] cores = {Color.RED, Color.GOLD, Color.GREEN};

        // calcula escala
        int maxValue = lista.stream()
            .flatMapToInt(t -> Arrays.stream(new int[]{
                t.vitorias, t.empates, t.derrotas}))
            .max().orElse(1);

        int nTimes = lista.size(), nSeries = series.length;
        int larguraPainel = width - 2*margem;
        int alturaPainel = height - 2*margem;
        int larguraGrupo = larguraPainel / nTimes;
        int larguraBarra = (int)(larguraGrupo * 0.8 / nSeries);
        int separacaoGrupo = larguraGrupo - larguraBarra * nSeries;

        // eixo
        gc.setStroke(Color.BLACK);
        gc.strokeLine(margem, margem, margem, margem + alturaPainel);
        gc.strokeLine(margem, margem + alturaPainel, margem + larguraPainel, margem + alturaPainel);

        // legendas
        for (int i = 0; i < nSeries; i++) {
            gc.setFill(cores[i]);
            gc.fillRect(margem + i * 100, 10, 20, 10);
            gc.setFill(Color.BLACK);
            gc.fillText(series[i], margem + i * 100 + 25, 20);
        }

        // desenha barras
        for (int i = 0; i < nTimes; i++) {
            Time t = lista.get(i);
            int[] v = {t.vitorias, t.empates, t.derrotas};
            int xGrupo = margem + i * larguraGrupo;

            for (int j = 0; j < nSeries; j++) {
                double p = (double)v[j] / maxValue;
                int hBarra = (int)(p * (alturaPainel - 20));
                int xBarra = xGrupo + j * larguraBarra + j*(separacaoGrupo/(nSeries-1));
                int yBarra = margem + alturaPainel - hBarra;

                gc.setFill(cores[j]);
                gc.fillRect(xBarra, yBarra, larguraBarra, hBarra);
                gc.setStroke(Color.BLACK);
                gc.strokeRect(xBarra, yBarra, larguraBarra, hBarra);
            }

            // nome do time
            
            gc.setFill(Color.BLACK);
            Text temp = new Text(t.getNome());
            temp.setFont(gc.getFont());
            double textWidth = temp.getLayoutBounds().getWidth();
            double tx = xGrupo + larguraGrupo/2.0 - textWidth/2.0;
            gc.fillText(t.nome, tx, margem + alturaPainel + 15);
        }

        Pane root = new Pane(canvas);
        stage.setScene(new Scene(root));
        stage.setTitle("Classificação do Campeonato (JavaFX)");
        stage.show();
    }
}
