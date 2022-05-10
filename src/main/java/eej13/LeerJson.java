/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eej13;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import eej11.App;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hinda
 */
public class LeerJson {

    public static ArrayList<App> LeerFicheroJson(String idFichero) {
        ArrayList<App> listaApp = new ArrayList<>();
        ObjectMapper mapeador = new ObjectMapper();
        
        try {
            mapeador.registerModule(new JavaTimeModule());

            listaApp = mapeador.readValue(new File(idFichero),
                    mapeador.getTypeFactory().constructCollectionType(ArrayList.class, App.class));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listaApp;
    }

    public static void main(String[] args) {
        /*En un programa Java, procede a leer el fichero JSON generado en el ejercicio 11 y muestra el resultado por pantalla.*/
        String idFichero = "./appsjson/aplicacionesxml.json";
        LeerFicheroJson(idFichero).forEach(System.out::println);
    }
}
