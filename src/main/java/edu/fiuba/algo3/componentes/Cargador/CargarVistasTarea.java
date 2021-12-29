package edu.fiuba.algo3.componentes.Cargador;

import edu.fiuba.algo3.componentes.Cuaderno.Cuaderno;
import edu.fiuba.algo3.componentes.Imagen.*;
import edu.fiuba.algo3.componentes.Libro.Libro;
import edu.fiuba.algo3.vista.Accion.PantallaAccion;
import edu.fiuba.algo3.vista.Calendario.Reloj;
import edu.fiuba.algo3.vista.Ciudad.FotoCiudad;
import edu.fiuba.algo3.vista.Ciudad.LibroCiudad;
import edu.fiuba.algo3.vista.Edificio.*;
import edu.fiuba.algo3.vista.Juego.HojaMision;
import edu.fiuba.algo3.vista.Juego.Pantalla;
import edu.fiuba.algo3.vista.Orden.Expediente;
import edu.fiuba.algo3.vista.Orden.IconoOrden;
import edu.fiuba.algo3.vista.Policia.Policias;
import edu.fiuba.algo3.vista.Radio.Walkman;
import javafx.concurrent.Task;

import java.lang.reflect.Method;
import java.util.List;

public class CargarVistasTarea extends Task<Void> {
    private final double factor;

    public CargarVistasTarea(double factor) {
        this.factor = factor;
    }

    @Override
    protected Void call() throws Exception {
        List<Class<? extends Object>> vistas = List.of(
                Walkman.class, Tarjetas.class, Reloj.class,
                Destino.class, FotoCiudad.class,
                Pantalla.class, Cuaderno.class,
                Libro.class, LibroCiudad.class,
                IconoEdificios.class, IconoVolver.class, IconoOrden.class,
                Mapita.class,
                Edificios.class, DestinoEdificio.class, Aeropuerto.class,
                Banco.class, Biblioteca.class, Bolsa.class, Puerto.class,
                PantallaAccion.class, HojaMision.class, Expediente.class, Policias.class);
        int progreso = 0;
        int maximo = 300 + 10*vistas.size();

        for(Class<? extends Object> clase : vistas) {
            try {
                Method precargar = clase.getMethod("precargar");
                precargar.invoke(null);
            } catch (Exception ex) {
                System.err.println("Error al precargar "+clase.getName());
                ex.printStackTrace();
            }
            progreso+=10;
            updateProgress(progreso,maximo);
        }

        for(int tarea = 0; tarea<100; tarea++) {
            int demora = (tarea%5) + 1;
            if(0 == tarea%2) {
                demora = 6 - demora;
            }
            Thread.sleep((long) (demora * factor));
            progreso += demora;
            updateProgress(progreso,maximo);
        }
        this.done();
        return null;
    }
}
