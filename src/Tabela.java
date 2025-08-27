import java.util.List;

public class Tabela {

    public static void main(String[] args) {
        // ETAPA 1: Ler os dados do arquivo
        Leitor leitor = new Leitor();
        List<Jogo> todosOsJogos = leitor.lerJogos("campeonato-brasileiro.csv");

        // ETAPA 2: Processar os dados para gerar a classificação
        Campeonato brasileirao = new Campeonato(todosOsJogos);
        brasileirao.processarResultados();
        List<Time> classificacao = brasileirao.getTabelaClassificacao();

        // ETAPA 3: Criar e exibir a janela gráfica, passando os dados prontos
        // O "invokeLater" é a forma mais segura de iniciar uma interface em Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Cria a sua janela principal e entrega a lista da classificação para ela
                new MainWindow(classificacao).setVisible(true);
            }
        });
    }
}