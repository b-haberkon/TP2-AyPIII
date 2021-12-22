package edu.fiuba.algo3.componentes.Imagen;

import javafx.scene.image.Image;

public class Tarjetas extends ImagenSeleccionable {
    private final static Image sel = new Image(urlDesdeRecursos("Ayuda/tarjetas_sel.png"));
    private final static Image desel = new Image(urlDesdeRecursos("Ayuda/tarjetas_desel.png"));

    public Tarjetas() {
        super(sel, desel);
    }

    public Tarjetas(double ancho) {
        this();
        setWidth(ancho);
    }

}
