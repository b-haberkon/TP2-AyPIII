package edu.fiuba.algo3.modelo.Computadora.OrdenDeArresto.Estrategia;

import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Policia.Policia;

public class Perder implements IEstrategiaOrden {

    private final String explicacion;

    public Perder(String explicacion) {
        this.explicacion = explicacion;
    }

    @Override
    public void realizar(Policia policia, Ladron ladron) {
        if(policia == null || ladron == null)
            throw new IllegalArgumentException("El Policia o el Ladron pasado por parámetro no es válido.");
        policia.perder(explicacion);
    }
}
