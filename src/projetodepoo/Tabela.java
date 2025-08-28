package projetodepoo;

import java.util.List;
import javax.swing.SwingUtilities;
public class Tabela {

    public static void main(String[] args) {
        
        Leitor leitor = new Leitor();
        List<Jogo> todosOsJogos = leitor.lerJogos("campeonato-brasileiro.csv");

        // 2. Processa resultados
        Campeonato brasileirao = new Campeonato(todosOsJogos);
        brasileirao.processarResultados();

        // 3. Inicia a GUI passando o Campeonato inteiro
        SwingUtilities.invokeLater(() -> {
            new MainWindow(brasileirao).setVisible(true);
        });
    }
}