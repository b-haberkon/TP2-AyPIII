package edu.fiuba.algo3.modelo.Pista.NivelPista;

import edu.fiuba.algo3.modelo.Pista.IPista;

import java.util.ArrayList;
import java.util.Collection;

public abstract class NivelPista {
    protected NivelPista() {}

    public Collection<IPista> filtrarPistas(Collection<IPista> pistas) {
        if(pistas == null)
            throw new IllegalArgumentException("Error. Las Pistas pasadas por parametro no son validas");

        ArrayList<IPista> pistasFiltradas = new ArrayList<IPista>();
        for (IPista pista : pistas) {
            pista.agregarAListaSiEsNivel(pistasFiltradas,this);
        }
        return pistasFiltradas;
    }

    public abstract boolean esEquivalente(NivelPista nivel);
}
