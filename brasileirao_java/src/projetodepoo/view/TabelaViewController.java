package projetodepoo.view;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import projetodepoo.grafico.Grafico;
import projetodepoo.grafico.GraficoBarras;
import projetodepoo.grafico.GraficoEvolucao;
import projetodepoo.grafico.GraficoPizza;
import projetodepoo.modelos.Campeonato;
import projetodepoo.modelos.Jogo;
import projetodepoo.modelos.Time;
import projetodepoo.processamento.Leitor;

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
        Time timeSelecionado = tabelaView.getSelectionModel().getSelectedItem();

        if (timeSelecionado != null) {
            List<Time> times = List.of(timeSelecionado);
            Grafico graficoEvolucao = new GraficoEvolucao(times);
            graficoEvolucao.criar(); 
        } else {
            showAlert("Aviso", "Selecione um time na tabela antes de clicar no botão.");
        }
    }
    
    @FXML
    private void criarGraficoPizza() {
        Time timeSelecionado = tabelaView.getSelectionModel().getSelectedItem();

        if (timeSelecionado != null) {
            List <Time> times = List.of(timeSelecionado);
            Grafico graficoPizza = new GraficoPizza(times);
            graficoPizza.criar(); 
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
    private void criarGraficoBarras() {
        Time timeSelecionado = tabelaView.getSelectionModel().getSelectedItem();

        if (timeSelecionado != null) {
            List<Time> times = List.of(timeSelecionado);
            Grafico graficoBarras = new GraficoBarras(times);
            graficoBarras.criar(); 
        } else {
            showAlert("Aviso", "Selecione um time na tabela antes de clicar no botão.");
        }
    }
    @FXML
    private void abrirCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione o arquivo CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos CSV", "*.csv"));

        File arquivo = fileChooser.showOpenDialog(null);

        if (arquivo != null) {
            Leitor leitor = new Leitor();
            List<Jogo> todosOsJogos = leitor.lerJogos(arquivo);
            Campeonato brasileirao = new Campeonato(todosOsJogos);
            brasileirao.processarResultados();
            List<Time> classificacao = brasileirao.getTabelaClassificacao();
            tabelaView.getItems().setAll(classificacao);

        } else {
            showAlert("Aviso", "Nenhum arquivo selecionado.");
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