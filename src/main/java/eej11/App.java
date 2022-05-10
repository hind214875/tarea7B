/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eej11;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author hinda
 */
@XmlRootElement(name = "app")
@XmlAccessorType(XmlAccessType.FIELD)
public class App {

    //atributos
    private int codigoUni;
    private String nombre;
    private String descripcion;
    private double tamanioKb;
    private int numeroDescargas;

    //constantes
    private static Random rd = new Random();
    private static int instancias;

    //constructores
    public App() {
        this.codigoUni=instancias++;
        this.nombre = "app" + codigoUni + RandomStringUtils.randomAlphabetic(1);
        this.descripcion = generarStringAleatorio();
        this.tamanioKb = doubleStreamGenerator().max().getAsDouble();//para tener el value double del Stream
        this.numeroDescargas = intStreamGenerator().max().getAsInt();//para tener el value double del Stream
    }

    public App(String nombre, String descripcion, double tamanioKb, int numeroDescargas) {
        codigoUni++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tamanioKb = tamanioKb;
        this.numeroDescargas = numeroDescargas;
    }

    //getters y setters
    public int getCodigoUni() {
        return codigoUni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTamanioKb() {
        return tamanioKb;
    }

    public void setTamanioKb(double tamanioKb) {
        this.tamanioKb = tamanioKb;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public void setCodigoUni(int codigoUni) {
        this.codigoUni = codigoUni;
    }
    
    //toString
    @Override
    public String toString() {
        return codigoUni + ";" + nombre + ";" + descripcion + ";" + tamanioKb + ";" + numeroDescargas;
    }

    //metodos
    public static String generarStringAleatorio() {
        String[] lista = new String[]{"Locations App", "Utils App", "Great App", "Generator App", "Games App", "Food App", "Travel App", "Iphone App", "Editor App", "Reader App"};
        return lista[rd.nextInt(lista.length)];
    }

    public static DoubleStream doubleStreamGenerator(){
       return rd.doubles(1, 100.0, 1024.0);  
    }

    public static IntStream intStreamGenerator() {
       return rd.ints(1, 0, 50000);
    }

}
