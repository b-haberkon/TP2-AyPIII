package edu.fiuba.algo3.controlador.Juego.Splash;

import edu.fiuba.algo3.controlador.Juego.Radio.RadioControlador;
import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.vista.Juego.Splash;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public class SplashControlador {
    private final Juego juego;
    private Splash splash;
    public EventHandler<InputEvent> handler = inputEvent -> { /* No hacer nada. */ };

    public SplashControlador(Juego juego) {
        this.juego = juego;
    }

    public void enlazar(Splash splash) {
        this.splash = splash;
        RadioControlador radioControlador = new RadioControlador(juego.getRadio());
        splash.setOnKeyPressed(radioControlador::handleKeyPressed);
    }

    public void enlazarIniciar(EventHandler<InputEvent> nuevoHandler) {
        splash.setOnMouseClicked(this::iniciar);
        splash.setOnKeyPressed(this::iniciar);
        handler = nuevoHandler;
    }

    public void iniciar(InputEvent event) {
        handler.handle(event);
    }
}
