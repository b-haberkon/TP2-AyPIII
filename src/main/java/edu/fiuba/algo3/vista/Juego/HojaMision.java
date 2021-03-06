package edu.fiuba.algo3.vista.Juego;

import edu.fiuba.algo3.componentes.Imagen.Imagen;
import edu.fiuba.algo3.componentes.RelativoAImagen.RelativoAImagen;
import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Juego.Mision;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class HojaMision extends RelativoAImagen {
    private final static Image fondo = new Image(Imagen.urlDesdeRecursos("Juego/Mision_fondo.jpeg"));

    public HojaMision(Juego juego, Mision mision) {
        super(fondo);

        Label textoCiudad = new Label();
        textoCiudad.setAlignment(Pos.CENTER);
        textoCiudad.setText(getTextoMision(mision));
        textoCiudad.setMaxWidth(960);
        textoCiudad.setMaxHeight(960);
        textoCiudad.setWrapText(true);
        textoCiudad.setStyle("-fx-font: 60 \"Times New Roman\"");
        textoCiudad.getStyleClass().add("etiquetaTextoLibroCiudad");
        agregar(textoCiudad, 0.5, 0.5);

    }

    private String getTextoMision(Mision mision) {
        String texto = mision.getMensajeMision();
        texto = mision.getRangoYNombrePolicia()+":\n\n" + texto+"\n\n";
        texto +="AlgoThief — Agencia de investigación";
        return texto;
    }

    public static void precargar() {
        /** No necesita cuerpo. La sola invocación de este método precargará los static. **/
    }
}
