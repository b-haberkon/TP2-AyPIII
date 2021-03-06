package edu.fiuba.algo3.vista.Computadora;

import edu.fiuba.algo3.controlador.Juego.PantallaControlador;
import edu.fiuba.algo3.controlador.Computadora.OrdenControlador;
import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Juego.Mision;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.transform.Rotate;

import static javafx.beans.binding.Bindings.createBooleanBinding;
import static javafx.beans.binding.Bindings.createStringBinding;

public class Orden extends Documentos {
    private final Juego juego;
    private final Mision mision;
    private final OrdenControlador controlador;
    private final Button botonEmitir;

    public Orden(Juego juego, Mision mision, OrdenControlador controlador) {
        super(juego, mision, mision, controlador);
        this.juego = juego;
        this.mision = mision;
        this.controlador = controlador;

        detallesPane.setTitulo("Información recolectada:");

        Label labelEstado = new Label();
        StringBinding bindingEstado = createStringBinding(this::textoEstado, mision.getOrden());
        labelEstado.textProperty().bind(bindingEstado);
        labelEstado.setAlignment(Pos.CENTER);
        labelEstado.setMinWidth(720);
        labelEstado.setMinHeight(256);
        labelEstado.setWrapText(true);
        labelEstado.setStyle("-fx-font: 90 \"Comic Sans\"");
        labelEstado.getStyleClass().add("etiquetaEstadoOrden");
        labelEstado.getTransforms().setAll(new Rotate(5d, 0, 0));
        agregar(labelEstado, 0.4, 0.70);

        botonEmitir = new Button("SOLICITAR ORDEN");
        botonEmitir.setAlignment(Pos.CENTER);
        final double widthBotonEmitir = 720;
        botonEmitir.setMinWidth(widthBotonEmitir);
        botonEmitir.setPrefWidth(widthBotonEmitir);
        botonEmitir.setMaxWidth(widthBotonEmitir);
        botonEmitir.setStyle("-fx-font: 60 \"Comic Sans\"");
        botonEmitir.getStyleClass().add("botonEmitirOrden");
        botonEmitir.getTransforms().setAll(new Rotate(5d, 0, 0));
        BooleanBinding bindingEmitirVisible = createBooleanBinding(
                () -> (1 == mision.getSospechososObservables().size()),
                mision.getSospechososObservables());
        botonEmitir.visibleProperty().bind(bindingEmitirVisible);
        agregar(botonEmitir, 0.81, 0.5);

        setCalendario(mision.getCalendario());
        setRelojVisible(true);
        setRadio(juego.getRadio());
        iniciarControlador(controlador);
    }

    private String textoEstado() {
        String emitidaPara = mision.getOrden().get().getEmitidaPara();
        if (null == emitidaPara) {
            return "Sin emitir";
        }
        return "Emitida para\n" + emitidaPara;
    }

    @Override
    protected void iniciarControlador(PantallaControlador controlador) {
        super.iniciarControlador(controlador);
        if(null == controlador) {
            return;
        }
        if(controlador instanceof OrdenControlador) {
            botonEmitir.setOnAction(((OrdenControlador) controlador)::emitir);
        }
    }

    @Override
    public String getTitulo() {
        return "Complete detalles para obtener Orden de arresto";
    }
}
