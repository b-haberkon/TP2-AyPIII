package edu.fiuba.algo3.componentes.Imagen;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

import java.net.URL;

public class Imagen extends ImageView {
    private final DoubleProperty angulo = new SimpleDoubleProperty(0.0);
    private final DoubleProperty escala = new SimpleDoubleProperty(1.0);

    public Imagen(String path) {
        super(path);
        inicializar();
    }
    public Imagen(Image image) {
        super(image);
        inicializar();
    }
    private void inicializar() {
        setFitWidth(getImage().getWidth());
        setFitHeight(getImage().getHeight());
        Rotate rotacionEfecto = new Rotate();
        rotacionEfecto.pivotXProperty().bind(fitWidthProperty().divide(2));
        rotacionEfecto.pivotYProperty().bind(fitHeightProperty().divide(2));
        rotacionEfecto.angleProperty().bind(angulo);
        Scale escaladoEfecto = new Scale();
        escaladoEfecto.xProperty().bind(escala);
        escaladoEfecto.yProperty().bind(escala);
        this.getTransforms().setAll(rotacionEfecto, escaladoEfecto);
    }

    public DoubleProperty anguloProperty() {
        return angulo;
    }

    public DoubleProperty escalaProperty() {
        return escala;
    }

    public final double getAngulo() {
        return anguloProperty().get();
    }

    public final void setAngulo(double nuevoAngulo) {
        anguloProperty().set(nuevoAngulo);
    }

    public static String urlDesdeRecursos(String recurso) {
        URL res = Imagen.class.getResource("/edu/fiuba/algo3/" + recurso);
        return (null == res)? null : res.toExternalForm();
    }
}
