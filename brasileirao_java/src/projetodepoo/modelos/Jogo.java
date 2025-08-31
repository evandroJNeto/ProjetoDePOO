package projetodepoo.modelos;

public class Jogo {
    private String rodada;
    private String data;
    private String hora;
    private String mandante;
    private String visitante;
    private int mandantePlacar;
    private int visitantePlacar;
    private String vencedor;
    private String arena;

    public Jogo(String rodada, String data, String hora,
                String mandante, String visitante,
                int mandantePlacar, int visitantePlacar,
                String vencedor, String arena) {
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

    public String getMandante()       { return mandante; }
    public String getVisitante()      { return visitante; }
    public int getMandantePlacar()    { return mandantePlacar; }
    public int getVisitantePlacar()   { return visitantePlacar; }
}