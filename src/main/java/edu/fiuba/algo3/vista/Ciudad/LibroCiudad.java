package edu.fiuba.algo3.vista.Ciudad;

import edu.fiuba.algo3.componentes.Imagen.IconoEdificios;
import edu.fiuba.algo3.componentes.Imagen.IconoVolver;
import edu.fiuba.algo3.componentes.Imagen.Mapita;
import edu.fiuba.algo3.componentes.Imagen.Tarjetas;
import edu.fiuba.algo3.componentes.Libro.Libro;
import edu.fiuba.algo3.componentes.Binding.SimplePoint2DBinding;
import edu.fiuba.algo3.controlador.Ciudad.LibroCiudadControlador;
import edu.fiuba.algo3.controlador.Juego.PantallaControlador;
import edu.fiuba.algo3.modelo.Ciudad.Ciudad;
import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Juego.Mision;
import edu.fiuba.algo3.vista.Juego.HojaMision;
import edu.fiuba.algo3.vista.Computadora.IconoOrden;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.transform.Rotate;

public class LibroCiudad extends Libro {
    private static final String loremIpsum = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
    private final Mapita mapita;
    private final IconoEdificios edificios;
    private IconoVolver volver;
    private final IconoOrden orden;

    public LibroCiudad(Juego juego, Mision mision) {
        super();

        Ciudad ciudad = mision.getCiudadActual();
        Label tituloCiudad = new Label();
        tituloCiudad.setText(ciudad.getNombre());
        tituloCiudad.setAlignment(Pos.CENTER);
        tituloCiudad.setMaxWidth(960);
        tituloCiudad.setMaxHeight(384);
        tituloCiudad.setWrapText(true);
        tituloCiudad.setStyle("-fx-font: 120 Impact");
        tituloCiudad.getStyleClass().add("etiquetaTituloLibroCiudad");
        tituloCiudad.getTransforms().setAll(new Rotate(2d, 0,0));
        agregar(tituloCiudad, 0.368, 0.225);

        FotoCiudad fotoCiudad = new FotoCiudad(ciudad);
        fotoCiudad.anguloProperty().set(1d);
        agregar(fotoCiudad, 0.368, 0.41);

        Label textoCiudad = new Label();
        textoCiudad.setAlignment(Pos.CENTER);
        String descripcion = ciudad.getDescripcion();
        textoCiudad.setText( (null == descripcion)? loremIpsum : descripcion);
        textoCiudad.setMaxWidth(960);
        textoCiudad.setMaxHeight(576);
        textoCiudad.setWrapText(true);
        textoCiudad.setStyle("-fx-font: 60 \"Times New Roman\"");
        textoCiudad.getStyleClass().add("etiquetaTextoLibroCiudad");
        agregar(textoCiudad, 0.368, 0.675);

        HojaMision hojaMision = new HojaMision(juego, mision);
        hojaMision.setScaleX(0.85);
        hojaMision.setScaleY(0.85);
        hojaMision.setRotate(15);
        agregar(hojaMision, new SimplePoint2DBinding(0.68, 0.5));

        mapita = new Mapita(640);
        agregar(mapita, 0.08, 0.4);

        edificios = new IconoEdificios(640);
        agregar(edificios, 0.08, 0.5);

        orden = new IconoOrden(480);
        agregar(orden, 0.08, 0.6);

        setCalendario(mision.getCalendario());
        setRelojVisible(true);

        setRadio(juego.getRadio());
        setTarjetasVisible(true);
        ponerVolver();
    }

    public LibroCiudad(Juego juego, Mision mision, LibroCiudadControlador controlador) {
        this(juego, mision);
        iniciarControlador(controlador);
    }

    private void ponerVolver() {
        volver = new IconoVolver(320);
        agregar(volver, 0.9, 0.1);
    }

    @Override
    protected void iniciarControlador(PantallaControlador controlador) {
        super.iniciarControlador(controlador);
        if(null == controlador) {
            return;
        }
        observadorAccionesProperty().bind(controlador.getObservadorLiberable());
        if(null != volver) {
            volver.setOnMouseClicked(controlador::volverClicked);
            volver.setOnKeyPressed(controlador::volverKeyPressed);
        }
        if(null != mapita) {
            mapita.setOnMouseClicked(controlador::mapitaClicked);
            mapita.setOnKeyPressed(controlador::mapitaKeyPressed);
        }
        if(null != edificios) {
            edificios.setOnMouseClicked(controlador::edificiosClicked);
            edificios.setOnKeyPressed(controlador::edificiosKeyPressed);
        }
        if(null != orden) {
            orden.setOnMouseClicked(controlador::ordenClicked);
            orden.setOnKeyPressed(controlador::ordenKeyPressed);
        }
        Tarjetas tarjetas = getTarjetas();
        if(null != tarjetas) {
            tarjetas.setOnMouseClicked(controlador::tarjetasClicked);
            tarjetas.setOnKeyPressed(controlador::tarjetasKeyPressed);
        }
    }

    @Override
    public String getTitulo() {
        return "Elija mapa de ciudades, plano de edificios u orden de arresto";
    }

    public static void precargar() {
        /** No necesita cuerpo. La sola invocaci??n de este m??todo precargar?? los static. **/
    }
}
