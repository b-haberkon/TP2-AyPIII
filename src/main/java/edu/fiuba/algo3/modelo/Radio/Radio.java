package edu.fiuba.algo3.modelo.Radio;

import edu.fiuba.algo3.modelo.Evento.RadioListener;
import edu.fiuba.algo3.modelo.Radio.EstadoAlimentacion.EstadoAlimentacion;
import edu.fiuba.algo3.modelo.Radio.EstadoTracks.EstadoTracks;
import edu.fiuba.algo3.modelo.Radio.Volumen.Volumen;

public class Radio {

    EstadoAlimentacion encendida = new EstadoAlimentacion();
    EstadoTracks tracks = new EstadoTracks(this);
    Volumen volumen = new Volumen(0.5);
    
    public void pulsarBotonPrender() {
        encendida.pulsarBotonPrender();
    }

    public boolean estaEncendida() {
        return encendida.estaEncendida();
    }

    public int getNumeroTrack() {
        return encendida.getNumeroTrack(tracks);
    }

    public void pulsarBotonSiguiente() {
        encendida.pulsarBotonSiguiente(tracks);
    }

    public void pulsarBotonAnterior() {
        encendida.pulsarBotonAnterior(tracks);
    }

    public void escucharTracks(RadioListener listener) {
        tracks.escucharTracks(listener);
    }

    public void escucharVolumen(RadioListener listener) {
        volumen.escuchar(listener);
    }

    public void setTitulo(String error) {
        /** \todo */
    }

    public void subirVolumen() {
        /** \todo */
    }

    public void bajarVolumen() {
        /** \todo */
    }

    public double getVolumen() {
        return encendida.getVolumen(volumen);
    }
}
