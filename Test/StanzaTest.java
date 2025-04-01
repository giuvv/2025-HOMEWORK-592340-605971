import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StanzaTest {

    private Stanza sala;
    private Stanza cucina;
    private Stanza giardino;
    private Attrezzo spada;
    private Attrezzo chiave;

    @BeforeEach
    public void setUp() {
        // Setup iniziale
        sala = new Stanza("Sala");
        cucina = new Stanza("Cucina");
        giardino = new Stanza("Giardino");

        // Creiamo alcuni attrezzi
        spada = new Attrezzo("spada", 1);
        chiave = new Attrezzo("chiave", 0);
        
        // Impostiamo stanze adiacenti
        sala.impostaStanzaAdiacente("nord", cucina);
        sala.impostaStanzaAdiacente("est", giardino);
    }

    @Test
    public void testAddAttrezzo() {
        // Testiamo che aggiungere un attrezzo alla stanza funzioni
        assertTrue(sala.addAttrezzo(spada), "Attrezzo dovrebbe essere aggiunto");
        assertTrue(sala.hasAttrezzo("spada"), "La stanza dovrebbe contenere l'attrezzo 'spada'");
    }

    @Test
    public void testGetStanzaAdiacente() {
        // Testiamo che la stanza adiacente venga restituita correttamente
        assertEquals(cucina, sala.getStanzaAdiacente("nord"), "La stanza adiacente a nord dovrebbe essere la Cucina");
        assertEquals(giardino, sala.getStanzaAdiacente("est"), "La stanza adiacente a est dovrebbe essere il Giardino");
    }

    @Test
    public void testHasAttrezzo() {
        // Testiamo che un attrezzo sia correttamente trovato
    	sala.addAttrezzo(spada);
        sala.addAttrezzo(chiave);
        
        assertTrue(sala.hasAttrezzo("spada"), "La stanza dovrebbe contenere l'attrezzo 'spada'");
        assertTrue(sala.hasAttrezzo("chiave"), "La stanza dovrebbe contenere l'attrezzo 'chiave'");
        assertFalse(sala.hasAttrezzo("pala"), "La stanza non dovrebbe contenere l'attrezzo 'pala'");
    }

    @Test
    public void testGetAttrezzo() {
        // Testiamo che un attrezzo venga restituito correttamente dalla stanza
        sala.addAttrezzo(spada);
        Attrezzo attrezzoTrovato = sala.getAttrezzo("spada");

        assertNotNull(attrezzoTrovato, "L'attrezzo dovrebbe essere trovato");
        assertEquals("spada", attrezzoTrovato.getNome(), "Il nome dell'attrezzo dovrebbe essere 'spada'");

        // Verifica che l'attrezzo non esista nella stanza
        Attrezzo attrezzoNonTrovato = sala.getAttrezzo("pala");
        assertNull(attrezzoNonTrovato, "L'attrezzo 'pala' non dovrebbe essere presente nella stanza");
    }

    @Test
    public void testRemoveAttrezzo() {
        // Testiamo la rimozione dell'attrezzo
        sala.addAttrezzo(spada);
        assertTrue(sala.removeAttrezzo(spada), "L'attrezzo 'spada' dovrebbe essere rimosso dalla stanza");
        assertFalse(sala.hasAttrezzo("spada"), "La stanza non dovrebbe contenere l'attrezzo 'spada' dopo la rimozione");
    }
    
    @Test
    public void testDescrizioneStanza() {
        // Crea la stanza
        Stanza sala = new Stanza("Sala dei Tesori");

        // Aggiungi attrezzi
        Attrezzo spada = new Attrezzo("spada", 1);
        Attrezzo chiave = new Attrezzo("chiave", 1);
        sala.addAttrezzo(spada);
        sala.addAttrezzo(chiave);

        // Imposta uscite
        Stanza salaAdiacente = new Stanza("Sala Segreta");
        sala.impostaStanzaAdiacente("nord", salaAdiacente);

        // Ottieni la descrizione della stanza
        String descrizione = sala.getDescrizione();
        
        // Stampa la descrizione per il debug
        System.out.println(descrizione);

        // Verifica che la descrizione contenga il nome della stanza
        assertTrue(descrizione.contains("Sala dei Tesori"), "La descrizione dovrebbe contenere il nome della stanza.");

        // Verifica che la descrizione contenga le uscite
        assertTrue(descrizione.contains("Uscite:"), "La descrizione dovrebbe contenere la parola 'Uscite'.");
        assertTrue(descrizione.contains("nord"), "La descrizione dovrebbe contenere la direzione 'nord' come uscita.");

        // Verifica che la descrizione contenga gli attrezzi
        assertTrue(descrizione.contains("spada"), "La descrizione dovrebbe contenere l'attrezzo 'spada'.");
        assertTrue(descrizione.contains("chiave"), "La descrizione dovrebbe contenere l'attrezzo 'chiave'.");
    }


}
