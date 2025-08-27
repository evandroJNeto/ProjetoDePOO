/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fagundes
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Campeonato {

    private List<Jogo> jogos;
    
    private Map<String, Time> equipes;

    public Campeonato(List<Jogo> jogos) {
        this.jogos = jogos;
        this.equipes = new HashMap<>();
    }

    public void processarResultados() {
        for (Jogo jogo : this.jogos) {
            
            String nomeMandante = jogo.mandante;
            String nomeVisitante = jogo.visitante;

            if (!this.equipes.containsKey(nomeMandante)) {
                this.equipes.put(nomeMandante, new Time(nomeMandante));
            }
            
            if (!this.equipes.containsKey(nomeVisitante)) {
                this.equipes.put(nomeVisitante, new Time(nomeVisitante));
            }

            Time mandante = this.equipes.get(nomeMandante);
            Time visitante = this.equipes.get(nomeVisitante);

            mandante.registrarJogo(jogo.mandantePlacar, jogo.visitantePlacar);
            visitante.registrarJogo(jogo.visitantePlacar, jogo.mandantePlacar);
        }
    }

    public List<Time> getTabelaClassificacao() {
        List<Time> classificacao = new ArrayList<>(this.equipes.values());

        Collections.sort(classificacao, new Comparator<Time>() {
            @Override
            public int compare(Time time1, Time time2) {

                if (time1.getPontos() != time2.getPontos()) {
                    return Integer.compare(time2.getPontos(), time1.getPontos());
                }
                
                if (time1.getVitorias() != time2.getVitorias()) {
                    return Integer.compare(time2.getVitorias(), time1.getVitorias());
                }

                if (time1.getSaldoGols() != time2.getSaldoGols()) {
                    return Integer.compare(time2.getSaldoGols(), time1.getSaldoGols());
                }

                if (time1.getGolsPro() != time2.getGolsPro()) {
                    return Integer.compare(time2.getGolsPro(), time1.getGolsPro());
                }
                
                return time1.getNome().compareTo(time2.getNome());
            }
        });

        return classificacao;
    }
}
