package edu.fiuba.algo3;

import edu.fiuba.algo3.componentes.Cuaderno.Cuaderno;
import edu.fiuba.algo3.componentes.FakeLoader.FakeService;
import edu.fiuba.algo3.componentes.bindings.CargandoBinding;
import edu.fiuba.algo3.controlador.Splash.SplashControlador;
import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.vista.Policias;
import edu.fiuba.algo3.vista.Splash;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    Juego juego = new Juego();
    Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        startSplash(stage);
    }

    ControladorStage controlador;

    private void startSplash(Stage stage) {
        juego = new Juego();
        FakeService lector = new FakeService(5);
        CargandoBinding tituloBinding = new CargandoBinding(lector.progressProperty(),
                "AlgoThief — ¡Listo!",
                "AlgoThief — Cargando… %.2f%%");
        stage.titleProperty().bind(tituloBinding);

        Splash splash = new Splash();
        Group root = new Group(splash);
        controlador = new ControladorStage(stage, root);
        //Rectangle2D scr = controlador.getDimensionPantalla();
        controlador.start();
        stage.sizeToScene();
        stage.centerOnScreen();
        splash.progresoProperty().bind(lector.progressProperty());
        lector.start();
        stage.show();
        SplashControlador splashControlador = new SplashControlador(splash);
        lector.setOnSucceeded(ev -> {
            stage.titleProperty().unbind();
            splashControlador.bind(this::mostrarPolicias);
        });git
        lector.setOnFailed(this::onSplashFailed);
    }

    private void onSplashExitoso(SplashControlador splashControlador) {

    }

    private void onSplashFailed(WorkerStateEvent workerStateEvent) {
        /** \todo Mínimo, mostrar un mensaje de error. **/
        /** \todo Mejorar: reintentar (vuelve a llamar a startSplash). **/
    }

    private void mostrarPolicias(Event event) {
        Group principal = new Policias(juego.getPolicias());
        Group root = new Group(principal);
        controlador = new ControladorStage(stage, root);
        controlador.start();
        stage.setTitle("AlgoThief — Elija el agente para iniciar una misión");
        stage.sizeToScene();
        stage.centerOnScreen();
    }
    public static void main(String[] args) {
        launch();
    }

}