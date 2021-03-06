package edu.fiuba.algo3.modelo.Calendario.Acciones;

import edu.fiuba.algo3.modelo.Calendario.Calendario;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Policia.Policia;

public class Enfrentamiento implements IAccion {
    private final Ladron ladron;
    private Policia policia;

    public Enfrentamiento(Ladron ladron) {
        this.ladron = ladron;
    }

    @Override
    public String getNombreAccion() {
        return "Enfrentamiento";
    }

    @Override
    public String getTextoAccion()
    {
        return "Te enfrentás a alguien…";
    }

    @Override
    public void avanzarCalendario(Calendario calendario) {
        // No avanza el calendario
    }

    @Override
    public void setPolicia(Policia policia) {
        this.policia = policia;
    }

    @Override
    public void realizar() {
        policia.enfrentar(ladron);
    }
}
