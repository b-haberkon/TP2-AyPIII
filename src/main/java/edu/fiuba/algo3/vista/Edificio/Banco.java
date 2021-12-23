package edu.fiuba.algo3.vista.Edificio;

import edu.fiuba.algo3.modelo.Edificio.Edificio;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.List;

public class Banco extends DestinoEdificio {
    private static final Image sel = new Image(urlDesdeRecursos("Edificio/Banco_sel.png"));
    private static final Image desel = new Image(urlDesdeRecursos("Edificio/Banco_desel.png"));
    private static final List<Point2D> posiblesCoordenadas = List.of(
            new Point2D(0.457,0.700), new Point2D(0.480,0.316));

    protected Banco(Edificio edificio) {
        super(sel, desel, edificio);
    }

    @Override
    public Point2D getCoordenadas() {
        return posiblesCoordenadas.get(getIndice(posiblesCoordenadas.size()));
    }
}
