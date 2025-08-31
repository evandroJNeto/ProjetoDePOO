package projetodepoo.modelos;

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
        this.jogos   = jogos;
        this.equipes = new HashMap<>();
    }

    public void processarResultados() {
        for (Jogo jogo : jogos) {
            String m = jogo.getMandante();
            String v = jogo.getVisitante();

            equipes.putIfAbsent(m, new Time(m));
            equipes.putIfAbsent(v, new Time(v));

            Time mandante  = equipes.get(m);
            Time visitante = equipes.get(v);

            mandante.registrarJogo(jogo.getMandantePlacar(), jogo.getVisitantePlacar());
            visitante.registrarJogo(jogo.getVisitantePlacar(), jogo.getMandantePlacar());
        }
    }

    public List<Time> getTabelaClassificacao() {
        List<Time> tabela = new ArrayList<>(equipes.values());
        Collections.sort(tabela, new Comparator<Time>() {
            @Override
            public int compare(Time t1, Time t2) {
                if (t1.getPontos() != t2.getPontos())
                    return Integer.compare(t2.getPontos(), t1.getPontos());
                if (t1.getVitorias() != t2.getVitorias())
                    return Integer.compare(t2.getVitorias(), t1.getVitorias());
                if (t1.getSaldoGols() != t2.getSaldoGols())
                    return Integer.compare(t2.getSaldoGols(), t1.getSaldoGols());
                if (t1.getGolsPro() != t2.getGolsPro())
                    return Integer.compare(t2.getGolsPro(), t1.getGolsPro());
                return t1.getNome().compareTo(t2.getNome());
            }
        });
        return tabela;
    }
    public Time buscarTime(String nomeBuscado) {
    for (Time t : equipes.values()) {
        if (t.getNome().equalsIgnoreCase(nomeBuscado.trim())) {
            return t;
        }
    }
    return null;
}
}
