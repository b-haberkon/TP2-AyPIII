package edu.fiuba.algo3.modelo.Calendario.Acciones;

import edu.fiuba.algo3.modelo.Calendario.Calendario;
import edu.fiuba.algo3.modelo.Policia.ExcepcionesPolicia.PoliciaException;
import edu.fiuba.algo3.modelo.Policia.Policia;

public class HeridaPorCuchillo implements IAccion {
    private Policia policia;

    @Override
    public String getNombreAccion() {
        return "Herido por cuchillo";
    }

    @Override
    public String getTextoAccion()
    {
        return "Te achuchillaron, necesitás recuperarte…";
    }

    @Override
    public void avanzarCalendario(Calendario calendario) throws AccionException {
        try {
            policia.avanzarHorasCuchillada(calendario);
        } catch (PoliciaException ex) {
            new AccionException("No pudo avanzarse el calendario para herida por cuchillo.\n"+ex);
        }
    }

    @Override
    public void setPolicia(Policia policia) {
        this.policia = policia;
    }

    @Override
    public void realizar() {
       policia.recibirCuchillada();
    }
}
