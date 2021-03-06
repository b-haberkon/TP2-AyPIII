package edu.fiuba.algo3.modelo.Edificio.TipoEdificio;

import edu.fiuba.algo3.modelo.Pista.Filtro.SinFiltro;

public class Puerto extends EdificioAbstracto{
    public Puerto() {
        super(new SinFiltro());
    }

    @Override
    public String getNombreTipo() {
        return "Puerto";
    }

    @Override
    protected String getNombreTestigo() {
        return "Marino";
    }

}

