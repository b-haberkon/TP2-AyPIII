package edu.fiuba.algo3;

import edu.fiuba.algo3.componentes.Imagen.Imagen;
import edu.fiuba.algo3.controlador.Ciudad.LibroCiudadControlador;
import edu.fiuba.algo3.controlador.Edificio.EdificiosControlador;
import edu.fiuba.algo3.controlador.Mapa.MapaDestinosControlador;
import edu.fiuba.algo3.controlador.Orden.OrdenControlador;
import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Juego.Mision;
import edu.fiuba.algo3.modelo.Policia.Policia;
import edu.fiuba.algo3.vista.Ciudad.LibroCiudad;
import edu.fiuba.algo3.vista.Edificio.Edificios;
import edu.fiuba.algo3.vista.Juego.GrupoInterno;
import edu.fiuba.algo3.vista.Mapa.MapaDestinos;
import edu.fiuba.algo3.vista.Orden.Orden;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class ControlStage {
    private final Stage stage;
    private final GrupoInterno raiz;
    private final Scene scene;
    private final Scale escalado = new Scale();
    private final DoubleProperty escala = new SimpleDoubleProperty(1.0);
    private final DoubleProperty ancho = new SimpleDoubleProperty(1024);

    public ControlStage(Stage stage, Parent interno) {
        this.stage = stage;
        this.raiz = new GrupoInterno(interno);
        this.scene = new Scene(raiz);
        final String pathEstilo = "src/main/java/edu/fiuba/algo3/recursos/estilos.css";
        final String url = Imagen.urlDesdePath(pathEstilo);
        scene.getStylesheets().addAll(url);
        escalado.setPivotX(0d);
        escalado.setPivotY(0d);
        escalado.xProperty().bind(escala);
        escalado.yProperty().bind(escala);
        raiz.getTransforms().setAll(escalado);
        escala.bind(new DoubleBinding() {
            {
                this.bind(stage.widthProperty(), raiz.boundsInLocalProperty());
            }
            @Override
            protected double computeValue() {
                return stage.getWidth()/raiz.getBoundsInLocal().getWidth();
            }
        });
    }

    /**************************************************************************/
    /**************************************************************************/

    public boolean abrirOrden(Juego juego, Mision mision, OrdenControlador controlador) {
        try {
            Orden nuevaVista = new Orden(juego, mision, controlador);
            cambiar(nuevaVista);
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir la orden: " + ex, ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }
    public boolean abrirOrden(Juego juego, Mision mision) {
        return abrirOrden(juego,mision,new OrdenControlador(juego, mision, this));
    }

    /**************************************************************************/

    public boolean abrirEdificios(Juego juego, Mision mision, EdificiosControlador controlador) {
        try {
            Edificios nuevaVista = new Edificios(juego, mision, controlador);
            cambiar(nuevaVista);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir plano de la ciudad: " + ex, ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }

    public boolean abrirEdificios(Juego juego, Mision mision) {
        return abrirEdificios(juego, mision, new EdificiosControlador(juego, mision, this));
    }

    /**************************************************************************/

    public boolean abrirLibroCiudad(Juego juego, Mision mision, LibroCiudadControlador controlador) {
        try {
            LibroCiudad libro = new LibroCiudad(juego, mision, controlador);
            cambiar(libro);
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir libro: " + ex, ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }

    public boolean abrirLibroCiudad(Juego juego, Mision mision) {
        return abrirLibroCiudad(juego, mision, new LibroCiudadControlador(juego, mision, this));
    }

    /**************************************************************************/

    public boolean abrirMapaCiudades(Juego juego, Mision mision, MapaDestinosControlador controlador) {
        try {
            MapaDestinos nuevaVista = new MapaDestinos(juego, mision, controlador);
            cambiar(nuevaVista);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir mapamundi: " + ex, ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }
    public boolean abrirMapaCiudades(Juego juego, Mision mision) {
        MapaDestinosControlador controlador = new MapaDestinosControlador(juego, mision, this);
        return abrirMapaCiudades(juego, mision, controlador);
    }

    /**************************************************************************/

    public boolean abrirMisionNueva(Juego juego, Policia policia, LibroCiudadControlador controlador) {
        try {
            Mision mision = juego.nuevaMision(policia);
            if(null==controlador) {
                return abrirLibroCiudad(juego, mision);
            } else {
                return abrirLibroCiudad(juego, mision, controlador);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "No pudo crearse la misión: " + ex, ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }

    public boolean abrirMisionNueva(Juego juego, Policia policia) {
        return abrirMisionNueva(juego, policia, null);
    }

    /**************************************************************************/
    /**************************************************************************/

    public Point2D getCentro() {
        double x = stage.getX() + (stage.getWidth()/2);
        double y = stage.getY() + (stage.getHeight()/2);
        if(Double.isNaN(Math.max(x,y))) {
            return getCentroPantalla();
        }
        return new Point2D(x,y);
    }

    public static Point2D getCentro(Rectangle2D rect) {
        double x = (rect.getMinX() + rect.getMaxX()) / 2;
        double y = (rect.getMinY() + rect.getMaxY()) / 2;
        return new Point2D(Math.max(0,x),Math.max(0,y));
    }

    public Point2D getCentroPantalla() {
        return getCentro(getDimensionPantalla());
    }

    private static double siNaNEntonces(double talVezNaN, double reemplazo) {
        return Double.isNaN(talVezNaN) ? reemplazo : talVezNaN;
    }

    public Rectangle2D getDimensionPantalla() {
        List<Screen> screens = Screen.getScreensForRectangle(stage.getX(), stage.getY(),1, 1);
        // Si no está inicializado el stage, getX() y getY() devuelven NaN y la lista estará vacía:
        Screen screen = (screens.size()>0) ? screens.get(0) : Screen.getPrimary();
        return screen.getBounds();
    }

    public void setCentro(Point2D nuevoCentro) {
        double ancho = siNaNEntonces(stage.getWidth(), 0);
        double alto =  siNaNEntonces(stage.getHeight(), 0);
        double x = nuevoCentro.getX()-(ancho/2);
        double y = nuevoCentro.getY()-(alto/2);
        stage.setX(x);
        stage.setY(y);
    }

    public void start() {
        stage.setScene(scene);
        stage.show();
        stage.sizeToScene();
        setCentro(getCentroPantalla());
    }

    public Scene getScene() {
        return scene;
    }

    public void cambiar(Node interno) {
        raiz.cambiar(interno);
    }

}
