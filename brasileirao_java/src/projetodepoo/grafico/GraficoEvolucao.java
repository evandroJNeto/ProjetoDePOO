package projetodepoo.grafico;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import projetodepoo.modelos.Time;

public class GraficoEvolucao extends Grafico {

    public GraficoEvolucao(List<Time> times) {
        super(times);
    }

    @Override
    public void criar() {
        if (times == null || times.isEmpty()) return;

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Rodadas");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Pontos");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Evolução dos Pontos");

        for (Time time : times) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(time.getNome());
            List<Integer> evolucao = time.getEvolucaoPontos();
            for (int i = 0; i < evolucao.size(); i++) {
                series.getData().add(new XYChart.Data<>(i, evolucao.get(i)));
            }
            lineChart.getData().add(series);
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(lineChart, 800, 600));
        stage.setTitle("Gráfico de Evolução");
        stage.show();
    }
}
