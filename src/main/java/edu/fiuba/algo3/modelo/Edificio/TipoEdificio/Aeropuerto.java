package edu.fiuba.algo3.modelo.Edificio.TipoEdificio;

import java.util.Collection;
import java.util.List;

import edu.fiuba.algo3.modelo.Ciudad.Ciudad;
import edu.fiuba.algo3.modelo.Edificio.Testigo.Testigo;
import edu.fiuba.algo3.modelo.Juego.Calendario;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Pista.PistaCiudad;
import edu.fiuba.algo3.modelo.Policia.Policia;

public class Aeropuerto implements ITipoEdificio{

    @Override
    public List<PistaCiudad> filtrarPistas(Collection<PistaCiudad> sinFiltrar) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNombreTipo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Testigo getTestigo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean mostrarPista(Ladron unLadron) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void visitadoPorLadron(Ladron ladron, Ciudad destino) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visitar(Policia policia, Calendario cal) {
        // TODO Auto-generated method stub
        
    }
    
}
