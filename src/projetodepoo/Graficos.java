package projetodepoo;

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
}