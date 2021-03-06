package edu.fiuba.algo3.modelo.Calendario.Acciones;

import edu.fiuba.algo3.modelo.Calendario.Calendario;
import edu.fiuba.algo3.modelo.Juego.ExcepcionesCalendario.CalendarioException;
import edu.fiuba.algo3.modelo.Policia.Policia;

public class HeridaPorDisparo implements IAccion {

    @Override
    public String getNombreAccion() {
        return "Disparo";
    }

    @Override
    public String getTextoAccion()
    {
        return "Te dispararon, necesitás recuperarte…";
    }

    @Override
    public void avanzarCalendario(Calendario calendario) throws AccionException {
        try {
            calendario.avanzarHoras(4);
        } catch (CalendarioException e) {
            throw new AccionException("No pudo avanzar el calendario para herida por disparo.\n" + e.getMessage());
        }
    }

    @Override
    public void setPolicia(Policia policia) {
        /** No debe hacer nada porque no interactúa. **/
    }

    @Override
    public void realizar() {
        /** No debe hacer nada porque no cambia el esado. **/
    }
}
