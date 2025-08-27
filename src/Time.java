/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fagundes
 */
public class Time {
    private String nome;
    private int pontos = 0;
    private int vitorias = 0;
    private int empates = 0;
    private int derrotas = 0;
    private int golsPro = 0;
    private int golsContra = 0;
    private int qtdJogos = 0;

    public Time(String nome) {
        this.nome = nome;
    }

    public void registrarJogo(int golsFeitos, int golsSofridos) {
        this.qtdJogos++;
        this.golsPro += golsFeitos;
        this.golsContra += golsSofridos;

        if (golsFeitos > golsSofridos) {
            this.vitorias++;
            this.pontos += 3;
        } else if (golsFeitos == golsSofridos) {
            this.empates++;
            this.pontos += 1;
        } else {
            this.derrotas++;
        }
    }

    public String getNome() { 
        return nome; 
    }
    public int getPontos() {
        return pontos;
    }
    public int getVitorias() {
        return vitorias; 
    }
    public int getEmpates() {
        return empates; 
    }
    public int getDerrotas() {
        return derrotas; 
    }
    public int getGolsPro() {
        return golsPro; 
    }
    public int getGolsContra() {
        return golsContra; 
    }
    public int getJogosDisputados() {
        return qtdJogos; 
    }
    public int getSaldoGols() {
        return this.golsPro - this.golsContra; 
    }
}
