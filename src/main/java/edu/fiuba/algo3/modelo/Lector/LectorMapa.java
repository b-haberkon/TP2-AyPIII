package edu.fiuba.algo3.modelo.Lector;

import edu.fiuba.algo3.modelo.Juego.Mapa;
import org.json.simple.JSONObject;

import java.util.Map;

public class LectorMapa {
    Mapa mapa = new Mapa();

    public Mapa leerMapa(JSONObject entrada) {
        LectorJson lectorJson = new LectorJson();
        Map mapaJson = lectorJson.leerPropiedadComo(Map.class,entrada,"mapa");
        for (Object clave:mapaJson.keySet()) {
            if(!(clave instanceof String))
            {
                throw new RuntimeException("Hay un origen que no es String, es "+clave.getClass().getName()+": "+clave.toString());
            }
            String origen = (String) clave;
            Map destinosJson = lectorJson.leerPropiedadComo(Map.class,mapaJson,origen);
            interpretarOrigen(origen,destinosJson);
        }
        return mapa;
    }

    private void interpretarOrigen(String origen, Map destinosJson) {
        for (Object clave:destinosJson.keySet()) {
            if (!(clave instanceof String)) {
                throw new RuntimeException("En " + origen + " hay un destino que no es String, es " + clave.getClass().getName() + ": " + clave.toString());
            }
            String destino = (String) clave;
            mapa.agregarConexion(origen, destino, 0);
        }
    }
}
