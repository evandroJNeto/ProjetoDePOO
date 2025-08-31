package projetodepoo;

import java.util.List;

public abstract class Grafico {
    protected List<Time> times;

    public Grafico(List<Time> times) {
        this.times = times;
    }

    public abstract void criar();
}
