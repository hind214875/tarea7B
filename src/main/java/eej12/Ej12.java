/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eej12;

/**
 *
 * @author hinda
 */
public class Ej12 {
    public static void main(String[] args) {
       // En un programa Java, procede a leer el fichero XML generado en el ejercicio anterior y muestra el resultado por pantalla. 
       String idFichero="./appsxml/aplicacionesxml.xml";
       LeerXml.LeerFicheroXml(idFichero).forEach(System.out::println);
    }
}
