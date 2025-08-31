/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetodepoo;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GraficoPizza extends Grafico {

    public GraficoPizza(List<Time> times) {
        super(times);
    }

    @Override
    public void criar() {
        if (times == null || times.isEmpty()) return;

        Time time = times.get(0);
        int v = Math.max(0, time.getVitorias());
        int e = Math.max(0, time.getEmpates());
        int d = Math.max(0, time.getDerrotas());
        int total = v + e + d;

        ObservableList<PieChart.Data> dados = FXCollections.observableArrayList(
            new PieChart.Data("Derrotas", d),
            new PieChart.Data("Empates", e),
            new PieChart.Data("Vitórias", v)
        );

        PieChart pie = new PieChart(dados);
        pie.setTitle("Desempenho de " + time.getNome());

        BorderPane root = new BorderPane(pie);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Gráfico de Pizza - " + time.getNome());
        stage.show();
    }
}