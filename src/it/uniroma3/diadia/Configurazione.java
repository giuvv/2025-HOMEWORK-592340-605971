package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurazione {

    private static final Properties properties = new Properties();

    static {
        // Il file viene caricato dal classpath: NON usare un percorso fisico!
        try (InputStream input = Configurazione.class.getClassLoader().getResourceAsStream("diadia.properties")) {
            if (input == null) {
                throw new RuntimeException("Il file di proprietà 'diadia.properties' non è stato trovato nel classpath");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Errore durante il caricamento del file di proprietà: " + ex.getMessage(), ex);
        }
    }

    /**
     * Ritorna il numero di CFU iniziali come impostato nel file di proprietà.
     * Se il valore non è presente, viene usato un valore default (ad esempio, 20).
     */
    public static int getCfuIniziali() {
        String valore = properties.getProperty("cfu.iniziali", "20");
        return Integer.parseInt(valore);
    }

    /**
     * Ritorna il peso massimo della borsa come impostato nel file di proprietà.
     * Se il valore non è presente, viene usato un valore default (ad esempio, 10).
     */
    public static int getPesoMaxBorsa() {
        String valore = properties.getProperty("peso.max.borsa", "10");
        return Integer.parseInt(valore);
    }
    
    /**
     * ritorna il messaggio di benvento definito nel file di propeties. Se non presente restituisce un valore
     * di default
     * */
    public static String getMessaggioBenvenuto() {
    	return properties.getProperty("messaggio benvenuto","benvenuto nel gioco").trim();
    }
}
