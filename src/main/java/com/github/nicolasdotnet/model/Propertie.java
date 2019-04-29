/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;

/**
 *
 * Propertie est la classe qui importe le fichier config.properties.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class Propertie {
    
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Propertie.class);

    boolean modeDev;
    int nbrTours;
    int nbrCombinaison;
    int nbrRange;

    public void uploadProperties() {

        InputStream upload = null;
        Properties prop = null;
        try {
            prop = new Properties();
            upload = Propertie.class.getResourceAsStream("/config.properties");
            prop.load(upload);
            upload.close();

            this.modeDev = Boolean.parseBoolean(prop.getProperty("modeDev"));
            this.nbrCombinaison = Integer.parseInt(prop.getProperty("nbrCombinaison"));
            this.nbrTours =  Integer.parseInt(prop.getProperty("nbrTours"));
            this.nbrRange =  Integer.parseInt(prop.getProperty("nbrRange"));
            
        } catch (FileNotFoundException ex) {

            log.debug("Fichier parametres non trouvé", ex);
            
        } catch (IOException ex) {
            
            log.debug("Erreur pendant l'import", ex);
        
        } finally {
            try {
                upload.close();
            } catch (IOException ex) {
                log.debug("Erreur pendant la fermeture de l'import", ex);
            }
        }
        
        log.info("Fichier parametres importé");

    }

    public boolean isModeDev() {
        return modeDev;
    }

    public int getNbrTours() {
        return nbrTours;
    }

    public int getNbrCombinaison() {
        return nbrCombinaison;
    }

    public int getNbrRange() {
        return nbrRange;
    }

}
