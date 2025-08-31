package projetodepoo;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsPro;
    private int golsContra;
    private int qtdJogos;
    private List<Integer> golsPorPartida;
    private List<Integer> evolucaoPontos; // NOVO: armazena pontos a cada rodada

    public Time(String nome) {
        this.nome = nome;
        this.pontos = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golsPro = 0;
        this.golsContra = 0;
        this.qtdJogos = 0;
        this.golsPorPartida = new ArrayList<>();
        this.evolucaoPontos = new ArrayList<>(); // Inicializa a lista
        this.evolucaoPontos.add(0); // Começa com 0 pontos
    }

    public void registrarJogo(int golsFeitos, int golsSofridos) {
        this.qtdJogos++;
        this.golsPro += golsFeitos;
        this.golsContra += golsSofridos;
        this.golsPorPartida.add(golsFeitos);

        int pontosAntes = this.pontos;
        
        if (golsFeitos > golsSofridos) {
            this.vitorias++;
            this.pontos += 3;
        } else if (golsFeitos == golsSofridos) {
            this.empates++;
            this.pontos++;
        } else {
            this.derrotas++;
        }
        
        // NOVO: Adiciona os pontos atuais à evolução
        this.evolucaoPontos.add(this.pontos);
    }
    
    // NOVO: Getter para a evolução de pontos
    public List<Integer> getEvolucaoPontos() {
        return evolucaoPontos;
    }
    
    public String getNome()          { return nome; }
    public int getPontos()           { return pontos; }
    public int getVitorias()         { return vitorias; }
    public int getEmpates()          { return empates; }
    public int getDerrotas()         { return derrotas; }
    public int getGolsPro()          { return golsPro; }
    public int getGolsContra()       { return golsContra; }
    public int getJogosDisputados()  { return qtdJogos; }
    public int getSaldoGols()        { return golsPro - golsContra; }
}