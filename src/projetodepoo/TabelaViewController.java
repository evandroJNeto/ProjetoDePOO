package projetodepoo;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert;

public class TabelaViewController implements Initializable {

    @FXML private TableView<Time> tabelaView;
    @FXML private TableColumn<Time, String> colunaNome;
    @FXML private TableColumn<Time, Integer> colunaPontos, colunaJogos, colunaVitorias, colunaEmpates, colunaDerrotas, colunaGolsPro, colunaGolsContra, colunaSaldoGols;
    @FXML private TableColumn<Time, Integer> colunaPos;
    @FXML private TextField buscaTextField;
    @FXML private Button graficoButton;

    private Campeonato brasileirao;
    private ObservableList<Time> dadosOriginais;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColunas();
    
        tabelaView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void criarGraficoEvolucao() {
        List<Time> timesSelecionados = tabelaView.getSelectionModel().getSelectedItems();
    
        if (timesSelecionados.isEmpty()) {
            showAlert("Aviso", "Selecione pelo menos um time para ver a evolução.");
            return;
        }
    
        if (timesSelecionados.size() > 5) {
            showAlert("Aviso", "Selecione no máximo 5 times para melhor visualização.");
            return;
        }
    
        Graficos.criarGraficoEvolucao(timesSelecionados);
    }
    
    @FXML
    private void criarGraficoPizza() {
        Time timeSelecionado = tabelaView.getSelectionModel().getSelectedItem();

        if (timeSelecionado != null) {
            List<Time> grafTimes = Collections.singletonList(timeSelecionado);
            Platform.runLater(() -> {
                Graficos.graficoPizza(grafTimes);
            });
        } else {
            showAlert("Aviso", "Selecione um time na tabela antes de clicar no botão.");
        }
    }

    private void configurarColunas() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPontos.setCellValueFactory(new PropertyValueFactory<>("pontos"));
        colunaJogos.setCellValueFactory(new PropertyValueFactory<>("jogosDisputados"));
        colunaVitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias"));
        colunaEmpates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        colunaDerrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));
        colunaGolsPro.setCellValueFactory(new PropertyValueFactory<>("golsPro"));
        colunaGolsContra.setCellValueFactory(new PropertyValueFactory<>("golsContra"));
        colunaSaldoGols.setCellValueFactory(new PropertyValueFactory<>("saldoGols"));
        colunaPos.setCellFactory(col -> new PosTabelaCell());
    }

    public void setCampeonato(Campeonato camp) {
        this.brasileirao = camp;
        preencherTabela();
    }

    private void preencherTabela() {
        List<Time> classificacao = brasileirao.getTabelaClassificacao();
        dadosOriginais = FXCollections.observableArrayList(classificacao);
        tabelaView.setItems(dadosOriginais);
    }

    @FXML
    private void criarGrafico() {
        Time timeSelecionado = tabelaView.getSelectionModel().getSelectedItem();

        if (timeSelecionado != null) {
            List<Time> grafTimes = Collections.singletonList(timeSelecionado);
            Platform.runLater(() -> {
                Graficos.criarGrafico(grafTimes);
            });
        } else {
            showAlert("Aviso", "Selecione um time na tabela antes de clicar no botão.");
        }
    }
    private void showAlert(String titulo, String menssagem){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(menssagem);
        alert.showAndWait();
    }
}