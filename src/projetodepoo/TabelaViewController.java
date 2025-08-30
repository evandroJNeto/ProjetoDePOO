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

public class TabelaViewController implements Initializable {

    @FXML private TableView<Time> tabelaView;
    @FXML private TableColumn<Time, String> colunaNome;
    @FXML private TableColumn<Time, Integer> colunaPontos, colunaJogos, colunaVitorias, colunaEmpates, colunaDerrotas, colunaGolsPro, colunaGolsContra, colunaSaldoGols;
    
    @FXML private TableColumn<Time, Integer> colunaPos;

    @FXML private TextField buscaTextField;
    @FXML private Button graficoButton;

    private Campeonato brasileirao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPontos.setCellValueFactory(new PropertyValueFactory<>("pontos"));
        colunaJogos.setCellValueFactory(new PropertyValueFactory<>("jogosDisputados"));
        colunaVitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias"));
        colunaEmpates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        colunaDerrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));
        colunaGolsPro.setCellValueFactory(new PropertyValueFactory<>("golsPro"));
        colunaGolsContra.setCellValueFactory(new PropertyValueFactory<>("golsContra"));
        colunaSaldoGols.setCellValueFactory(new PropertyValueFactory<>("saldoGols"));
    }

    public void setCampeonato(Campeonato camp) {
        this.brasileirao = camp;
        preencherTabela();
    }

    private void preencherTabela() {
        List<Time> classificacao = brasileirao.getTabelaClassificacao();
        ObservableList<Time> dadosDaTabela = FXCollections.observableArrayList(classificacao);
        tabelaView.setItems(dadosDaTabela);
        
        colunaPos.setCellFactory(col -> new PosTabelaCell());
    }

    @FXML
    private void criarGrafico() {
        String nome = buscaTextField.getText().trim();
        if (nome.isEmpty()) {
            showAlert("Erro", "Digite o nome de um time.");
            return;
        }

        Time encontrado = brasileirao.buscarTime(nome);

        if (encontrado != null) {
            List<Graficos.Time> grafTimes = Collections.singletonList(
                new Graficos.Time(
                    encontrado.getNome(),
                    encontrado.getPontos(),
                    encontrado.getVitorias(),
                    encontrado.getEmpates(),
                    encontrado.getDerrotas()
                )
            );

            Platform.runLater(() -> {
                Graficos.criarGrafico(grafTimes);
            });

        } else {
            showAlert("Erro", "Time \"" + nome + "\" não encontrado.");
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}