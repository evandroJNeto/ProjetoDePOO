package projetodepoo.grafico;

import java.util.List;
import projetodepoo.modelos.Time;

public abstract class Grafico {
    protected List<Time> times;

    public Grafico(List<Time> times) {
        this.times = times;
    }

    public abstract void criar();
}
