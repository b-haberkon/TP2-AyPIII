package edu.fiuba.algo3.modelo.Policia;

import edu.fiuba.algo3.modelo.Edificio.Edificio;
import edu.fiuba.algo3.modelo.Juego.Calendario;
import edu.fiuba.algo3.modelo.Acciones.AccionCuchilloUnica;
import edu.fiuba.algo3.modelo.Juego.Mision;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Pista.IPista;
import edu.fiuba.algo3.modelo.Policia.RangoPolicia.RangoPolicia;
import edu.fiuba.algo3.modelo.Juego.*;

import java.util.ArrayList;

public class Policia {

    private final String nombre;
    private RangoPolicia rango;
    private Calendario calendario;
    private String ciudadActual;

    public Policia(RangoPolicia rango, String nombre){

        this.rango = rango;
        this.nombre = nombre;
    }

    public void viajar(int distancia, Calendario calendario)
    {

    }
    public ArrayList<IPista> filtrarPistas(ArrayList<IPista> pistas){

        return rango.filtrarPistas(pistas);

    }

    public boolean soyElAgente(String nombreAgente) {
        return this.nombre.equals(nombreAgente);
    }

    public void visitar(Edificio unEdificio, Ladron unLadron) {

        unEdificio.visitar(this,calendario);
        return;

    }

    public void hacerAccion(AccionCuchilloUnica mockAccion) {
    }

    public Mision tomarCaso(Caso unCaso) {

        return null;
    }
}
