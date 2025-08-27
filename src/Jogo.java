/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fagundes
 */
class Jogo {
    String rodada;
    String data;
    String hora;
    String mandante;
    String visitante;
    int mandantePlacar;
    int visitantePlacar;
    String vencedor;
    String arena;

    public Jogo(String rodada, String data, String hora, String mandante, String visitante,
                int mandantePlacar, int visitantePlacar, String vencedor, String arena) {
        this.rodada = rodada;
        this.data = data;
        this.hora = hora;
        this.mandante = mandante;
        this.visitante = visitante;
        this.mandantePlacar = mandantePlacar;
        this.visitantePlacar = visitantePlacar;
        this.vencedor = vencedor;
        this.arena = arena;
    }
}