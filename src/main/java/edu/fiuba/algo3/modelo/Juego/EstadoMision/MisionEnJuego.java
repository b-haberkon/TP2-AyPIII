package edu.fiuba.algo3.modelo.Juego.EstadoMision;

public class MisionEnJuego implements IEstadoMision {
    @Override
    public boolean fueFinalizada() {
        return false;
    }

    @Override
    public boolean fueVictoria() {
        return false;
    }

    @Override
    public IEstadoMision convertirEnDerrota(String explicacion) {
        return new MisionFueDerrota(explicacion);
    }

    @Override
    public IEstadoMision convertirEnVictoria(String explicacion) {
        return new MisionFueVictoria(explicacion);
    }

    @Override
    public String getExplicacion() {
        return "";
    }
}