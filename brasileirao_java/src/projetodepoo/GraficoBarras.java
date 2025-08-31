package projetodepoo;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

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
        series.getData().add(new XYChart.Data<>("Vitórias", time.getVitorias()));
        series.getData().add(new XYChart.Data<>("Empates", time.getEmpates()));
        series.getData().add(new XYChart.Data<>("Derrotas", time.getDerrotas()));

        grafico.getData().add(series);

        Stage stage = new Stage();
        stage.setScene(new Scene(grafico, 600, 400));
        stage.setTitle("Gráfico de Barras");
        stage.show();
    }
}
