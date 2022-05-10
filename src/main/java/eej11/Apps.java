/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eej11;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hinda
 */
@XmlRootElement(name = "apps")
@XmlAccessorType(XmlAccessType.FIELD)
public class Apps {
     // XmLElementWrapper define un contenedor para la lista 
    // de App
    @XmlElementWrapper(name = "apps")
    
    // XmlElement establece el nombre de los elementos
    // Cada elemento de la lista llevará esta etiqueta en el fichero xml
    @XmlElement(name = "app",type = App.class)
    
    private ArrayList<App> listaApps=new ArrayList<>();

    public Apps() {
    }

    public Apps(ArrayList<App> listaApps) {
        this.listaApps = listaApps;
    }
    

    public ArrayList<App> getListaApps() {
        return listaApps;
    }

    public void setListaApps(ArrayList<App> listaApps) {
        this.listaApps = listaApps;
    }
    
    
}
