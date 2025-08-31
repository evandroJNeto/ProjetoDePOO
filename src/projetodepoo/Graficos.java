package projetodepoo;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;

public class Graficos {

    public static void criarGrafico(List<Time> lista) {
        if (lista == null || lista.isEmpty()) return;
        
        Time time = lista.get(0);
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantidade");

        BarChart<String, Number> graficoDeBarras = new BarChart<>(xAxis, yAxis);
        graficoDeBarras.setTitle("Desempenho de " + time.getNome());
        graficoDeBarras.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Vitórias", time.getVitorias()));
        series.getData().add(new XYChart.Data<>("Empates", time.getEmpates()));
        series.getData().add(new XYChart.Data<>("Derrotas", time.getDerrotas()));
        
        graficoDeBarras.getData().add(series);

        Stage stage = new Stage();
        stage.setScene(new Scene(graficoDeBarras, 600, 400)); 
        stage.setTitle("Gráfico de Desempenho");
        stage.show();
    }

        public static void criarGraficoEvolucao(List<Time> timesSelecionados) {
        if (timesSelecionados == null || timesSelecionados.isEmpty()) {
            return;
        }

 
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Rodadas");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
    
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Pontos");
        yAxis.setTickUnit(1);          
        yAxis.setMinorTickCount(0);    

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Evolução dos Pontos no Campeonato");
        lineChart.setCreateSymbols(true);

        for (Time time : timesSelecionados) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(time.getNome());
        
            List<Integer> evolucao = time.getEvolucaoPontos();
            for (int rodada = 0; rodada < evolucao.size(); rodada++) {
                series.getData().add(new XYChart.Data<>(rodada, evolucao.get(rodada)));
            }
        
            lineChart.getData().add(series);
        }

        if (!timesSelecionados.isEmpty()) {
            Time primeiroTime = timesSelecionados.get(0);
            int maxRodadas = primeiroTime.getEvolucaoPontos().size();
            xAxis.setUpperBound(maxRodadas);
            xAxis.setTickUnit(1.0);
        }

        int maxPontos = timesSelecionados.stream()
                .mapToInt(time -> time.getPontos())
                .max()
                .orElse(50);
        yAxis.setUpperBound(maxPontos + 2); 
        yAxis.setAutoRanging(false);        

        Stage stage = new Stage();
        stage.setScene(new Scene(lineChart, 800, 600));
        stage.setTitle("Evolução dos Times");
        stage.show();
    }
         public static void graficoPizza(List<Time> lista){
            if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("Lista de times vazia.");
        }

        Time time = lista.get(0);
        int v = Math.max(0, time.getVitorias());
        int e = Math.max(0, time.getEmpates());
        int d = Math.max(0, time.getDerrotas());
        int total = v + e + d;

        ObservableList<PieChart.Data> dados = FXCollections.observableArrayList(
            new PieChart.Data("Derrotas", d),
            new PieChart.Data("Empates", e),
            new PieChart.Data("Vitorias", v)
        );

        PieChart pie = new PieChart(dados);
        pie.setTitle("Desempenho de " + time.getNome());
        pie.setLegendVisible(true);
        pie.setLabelsVisible(true);

        if (total > 0) {
            for (PieChart.Data dado : pie.getData()) {
                double pct = (dado.getPieValue() / total) * 100.0;
                dado.nameProperty().set(String.format("%s (%.1f%%)", dado.getName(), pct));
            }
        }

        BorderPane root = new BorderPane(pie);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Gráfico de Desempenho (Pizza) - " + time.getNome());
        stage.show();
        }
    }