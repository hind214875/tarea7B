/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eej12;
import eej11.Apps;
import eej11.App;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author hinda
 */
public class LeerXml {
    
    public static ArrayList<App> LeerFicheroXml(String idFichero){
         // Crea el contexto JAXB 
        JAXBContext contexto;
        ArrayList<App> listaApps = new ArrayList<>();
        
        try {
            contexto = JAXBContext.newInstance(Apps.class);
            // Crea el objeto Unmarshaller
           Unmarshaller um = contexto.createUnmarshaller();
            // Llama al método de unmarshalling
           Apps apps = (Apps) um.unmarshal(new File(idFichero));
           listaApps = apps.getListaApps();
              
        } catch (JAXBException ex) {
            System.out.println(ex.getMessage());
        }
        return listaApps;
    }
}
