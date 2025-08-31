package projetodepoo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.List;

public class Graficos {

    public static void criarGrafico(List<Time> lista) {
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

    // Mostra porcentagens junto ao nome, se houver total > 0
    if (total > 0) {
        for (PieChart.Data dado : pie.getData()) {
            double pct = (dado.getPieValue() / total) * 100.0;
            dado.nameProperty().set(String.format("%s (%.1f%%)", dado.getName(), pct));
        }
    }

    // Layout e exibição
    BorderPane root = new BorderPane(pie);
    Stage stage = new Stage();
    stage.setScene(new Scene(root, 600, 400));
    stage.setTitle("Gráfico de Desempenho (Pizza) - " + time.getNome());
    stage.show();
    }
}