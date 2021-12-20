package edu.fiuba.algo3.modelo.Lector;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.function.Function;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.fiuba.algo3.modelo.Ladron.Ladron;

public class LectorLadron {
  LectorJson lector = new LectorJson();
  ArrayList<Ladron> ladrones = new ArrayList<Ladron>();

  public ArrayList<Ladron> leerLadrones() {
    LectorJson lector = new LectorJson();

    JSONParser parser = new JSONParser();

    try (Reader reader = new FileReader("src/main/java/edu/fiuba/algo3/recursos/expediente.json")) {

      JSONObject json = (JSONObject) parser.parse(reader);
      return leerLadrones(json);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (org.json.simple.parser.ParseException e) {
      e.printStackTrace();
    }
    return ladrones;
  }


  public ArrayList<Ladron> leerLadrones(Map entrada) {
    JSONArray jsonLadrones = lector.leerPropiedadComo(JSONArray.class, entrada, "ladrones");
    ArrayList<Ladron> ladrones = lector.interpetarArray(jsonLadrones, obj->interpretarLadron(obj));
    return ladrones;
  }

  public Ladron interpretarLadron(Map jsonLadron) {
    String nombre = lector.leerPropiedadComo(String.class,jsonLadron,"nombre");
    Map<String,String> detalles = new HashMap<String,String>();
    for(String tipo : List.of("sexo", "deporte", "cabello", "distincion", "vehiculo"))
    {
      String detalle = lector.leerPropiedadComo(String.class, jsonLadron, tipo);
      if(null == detalle) {
        detalle = "Desconocido";
      }
      detalles.put(tipo, detalle);
    }
    return new Ladron(nombre,detalles);
  }
}
