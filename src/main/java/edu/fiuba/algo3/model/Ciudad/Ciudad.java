package edu.fiuba.algo3.model.Ciudad;

public class Ciudad {

    private final String ciudad;
    private final String bandera;
    private final String moneda;
    private final String terreno;
    private final String puntosDeInteres;
    private final String industria;
    private final String animales;
    private final String etnias;
    private final String lenguaje;
    private final String arte;
    private final String religion;
    private final String gobernante;
    private final String origen;
    private final String nivel;

    public Ciudad(String ciudad, String bandera, String moneda, String terreno, String puntosDeInteres, String industria, String animales, String etnias, String lenguaje, String arte, String religion, String gobernante, String origen, String nivel) {

        this.ciudad = ciudad;
        this.bandera = bandera;
        this.moneda = moneda;
        this.terreno = terreno;
        this.puntosDeInteres = puntosDeInteres;
        this.industria = industria;
        this.animales = animales;
        this.etnias = etnias;
        this.lenguaje = lenguaje;
        this.arte = arte;
        this.religion = religion;
        this.gobernante = gobernante;
        this.origen = origen;
        this.nivel = nivel;
    }

    public boolean esLaCiudad(String ciudad) {
        return this.ciudad.equals(ciudad);
    }
}