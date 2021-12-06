package edu.fiuba.algo3.modelo.Ladron;

import edu.fiuba.algo3.modelo.Ciudad.Ciudad;
import edu.fiuba.algo3.modelo.Pista.Filtro.IFiltroCiudad;
import edu.fiuba.algo3.modelo.Policia.Policia;

public class SinSospechoso implements ISospechoso {
    @Override
    public String testimonioAlAzar(Policia policia, Ciudad destino, IFiltroCiudad filtroCiudad) {
        return "Lo lamento, no vi a nadie con esas características.";
    }
}
