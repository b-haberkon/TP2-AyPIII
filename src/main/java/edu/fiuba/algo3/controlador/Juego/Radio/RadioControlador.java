package edu.fiuba.algo3.controlador.Juego.Radio;

import edu.fiuba.algo3.modelo.Juego.Radio.Radio;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class RadioControlador {
    private final Radio radio;

    public RadioControlador(Radio radio) {
        this.radio = radio;
    }

    public void enlazar(Node nodo) {
        nodo.setOnKeyPressed(this::handleKeyPressed);
        nodo.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleClick);
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        try {
            KeyCode code = keyEvent.getCode();
            boolean consumida = true;
            switch (code) {
                case ESCAPE:
                    if (radio.estaEncendida()) {
                        radio.pulsarBotonPrender();
                    }
                    break;
                case ENTER:
                case SPACE:
                case RIGHT:
                    if (keyEvent.isControlDown() || keyEvent.isMetaDown() || keyEvent.isShiftDown()) {
                        radio.pulsarBotonPrender();
                    } else if (!radio.estaEncendida()) {
                        radio.pulsarBotonPrender();
                    } else {
                        radio.pulsarBotonSiguiente();
                    }
                    break;
                case BACK_SPACE:
                case LEFT:
                    radio.pulsarBotonAnterior();
                    if (!radio.estaEncendida()) {
                        radio.pulsarBotonPrender();
                    }
                    break;
                case UP:
                case PLUS:
                case ADD:
                    if (!radio.estaEncendida()) {
                        radio.pulsarBotonPrender();
                    }
                    radio.subirVolumen();
                    break;
                case DOWN:
                case MINUS:
                case SUBTRACT:
                    if (!radio.estaEncendida()) {
                        radio.pulsarBotonPrender();
                    }
                    radio.bajarVolumen();
                    break;
                default:
                    consumida = false;
            }
            if (consumida) {
                keyEvent.consume();
            }
        } catch (Exception ex) {
            alertaError(ex);
        }
    }

    public void handleClick(MouseEvent mouseEvent) {
        if(mouseEvent.isConsumed()) {
            return;
        }
        try {
            switch (mouseEvent.getButton().ordinal()) {
                case 1:
                    radio.pulsarBotonSiguiente();
                    mouseEvent.consume();
                    break;
                case 2:
                case 3:
                    radio.pulsarBotonAnterior();
                    mouseEvent.consume();
                    break;
            }
        } catch (Exception ex) {
            alertaError(ex);
        }
    }

    private void alertaError(Exception ex) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
        alerta.showAndWait();
    }

}
