package projetodepoo.grafico;

import java.util.List;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import projetodepoo.modelos.Time;

public class GraficoBarras extends Grafico {

    public GraficoBarras(List<Time> times) {
        super(times);
    }

    @Override
    public void criar() {
        if (times == null || times.isEmpty()) return;

        Time time = times.get(0);
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantidade");

        BarChart<String, Number> grafico = new BarChart<>(xAxis, yAxis);
        grafico.setTitle("Desempenho de " + time.getNome());

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Desempenho");
        
        // Adicionar dados
        XYChart.Data<String, Number> vitorias = new XYChart.Data<>("Vitórias", time.getVitorias());
        XYChart.Data<String, Number> empates = new XYChart.Data<>("Empates", time.getEmpates());
        XYChart.Data<String, Number> derrotas = new XYChart.Data<>("Derrotas", time.getDerrotas());

        series.getData().add(vitorias);
        series.getData().add(empates);
        series.getData().add(derrotas);

        grafico.getData().add(series);

        Stage stage = new Stage();
        stage.setScene(new Scene(grafico, 600, 400));
        stage.setTitle("Gráfico de Barras");
        stage.show();

        Platform.runLater(() -> {
            vitorias.getNode().setStyle("-fx-bar-fill: #2ECC71;"); // VERDE
            empates.getNode().setStyle("-fx-bar-fill: #FECA57;"); 
            derrotas.getNode().setStyle("-fx-bar-fill: #E74C3C;");
        });
    }
}