/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eej11;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author hinda
 */
public class Prueba {
    //metodos

    public static void crearDirectorio(String ruta) {
        Path file = Paths.get(ruta);
        try {
            if (!Files.exists(file)) {
                Files.createDirectory(file);
                System.out.println("el directorio se crea correctamente");
            }
        } catch (FileAlreadyExistsException faee) {
            System.out.println("no puede crear porque existe");
        } catch (AccessDeniedException ade) {
            System.out.println("No tienes permisos");
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public static void copiarFicherosEnDirectorio(String rutaOrigen, String rutaDestino) {
        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get(rutaDestino);

        try {
            Files.copy(origen, destino);
            System.out.println("archivos copiandos");
        } catch (DirectoryNotEmptyException dne) {
            System.out.println("no puede replace el fichero porque el directorio not empty");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //metodo escritura fichero texto
    public static void escrituraFicheroTexto(String idFichero, ArrayList<App> lista) {

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            // flujo.newLine();
            //recorrer la lista de App generada y escribir en el fichero
            for (App obj : lista) {
                flujo.write(obj.toString());
                flujo.newLine();
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //metodo escritura de cada app en un fichero
    public static void escrituraAppEnFicheroTxt(String idFichero, App app) {
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            flujo.write(app.toString());
            //flujo.newLine();
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //metodo escritura fichero texto
    public static void escrituraFicheroXml(String idFichero, ArrayList<App> lista) throws JAXBException {
        Apps apps = new Apps();
        apps.setListaApps(lista);

        JAXBContext contexto = JAXBContext.newInstance(Apps.class);
        Marshaller serializador = contexto.createMarshaller();

        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Serialización y salida por consola
        //serializador.marshal(apps, System.out);
        // Volcado al fichero xml
        serializador.marshal(apps, new File(idFichero));
    }

    //metodo escritura fichero json
    public static void escrituraFicheroJson(String idFichero, ArrayList<App> lista) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();
        // Permite a mapeador usar fechas según java time
        // mapeador.registerModule(new JavaTimeModule());   
        // Formato JSON bien formateado. Si se comenta, el fichero queda minificado
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);
        // Escribe en un fichero JSON 
        mapeador.writeValue(new File(idFichero), lista);

    }

    public static void main(String[] args) throws JAXBException, IOException {
        /*Crea 50 aplicaciones usando el constructor por defecto, 
         guárdalas en una lista y muéstralas por pantalla.*/
        ArrayList<App> listApp = new ArrayList<>();
        listApp.removeAll(listApp);
        for (int i = 0; i < 50; i++) {
            listApp.add(new App());
        }

        //muestra la lista
        listApp.forEach(System.out::println);

        /*Guarda los datos de todas las App de la lista, 
        en un fichero de texto llamado aplicacionestxt.txt, dentro del directorio “./appstxt”.*/
        // Creo el directorio
        crearDirectorio("./appstxt");
        String idFichero = "./appstxt/aplicacionestxt.txt";
        //escribir fichero texto
        escrituraFicheroTexto(idFichero, listApp);

        /*Guarda los datos de todas las App de la lista, en un fichero XML llamado aplicacionesxml.xml,
        dentro del directorio “./appsxml”.*/
        crearDirectorio("./appsxml");
        escrituraFicheroXml("./appsxml/aplicacionesxml.xml", listApp);

        /*Guarda los datos de todas las App de la lista, 
        en un fichero JSON llamado aplicacionesxml.json, dentro del directorio “./appsjson”.*/
        crearDirectorio("./appsjson");
        escrituraFicheroJson("./appsjson/aplicacionesxml.json", listApp);

        //Crea una carpeta “./copias” y realiza una copia de los ficheros anteriores dentro de ella.
        crearDirectorio("./copias");
        copiarFicherosEnDirectorio("./appstxt/aplicacionestxt.txt", "./copias/aplicaciones.txt");
        copiarFicherosEnDirectorio("./appsxml/aplicacionesxml.xml", "./copias/aplicacionesxml.xml");
        copiarFicherosEnDirectorio("./appsjson/aplicacionesxml.json", "./copias/aplicacionesxml.json");

        /*En una carpeta “./aplicaciones” crea un archivo de texto por cada aplicación que haya en la lista.
         El archivo se llamará igual que la app y contendrá los datos de la aplicación, separando los campos por el carácter (;).*/
        int i=0;
        crearDirectorio("./aplicaciones");
        do{
            escrituraAppEnFicheroTxt("./aplicaciones/" + listApp.get(i).getNombre(), listApp.get(i));
            i++;
        }while(i<50);
        
        

    }
}
