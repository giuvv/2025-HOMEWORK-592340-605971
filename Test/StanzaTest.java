import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StanzaTest {

    private Stanza Atrio;
    private Stanza Biblioteca;
    private Stanza N11;
    private Attrezzo spada;
    private Attrezzo chiave;

    @BeforeEach
    public void setUp() {
        // Setup iniziale
        Atrio = new Stanza("Atrio");
        Biblioteca = new Stanza("Biblioteca");
        N11 = new Stanza("N11");

        // Creiamo alcuni attrezzi
        spada = new Attrezzo("spada", 1);
        chiave = new Attrezzo("chiave", 0);
        
        // Impostiamo stanze adiacenti
        Atrio.impostaStanzaAdiacente("nord", Biblioteca);
        Atrio.impostaStanzaAdiacente("est", N11);
    }

    @Test
    public void testAddAttrezzo() {
        // Testiamo che aggiungere un attrezzo alla stanza funzioni
        assertTrue(Atrio.addAttrezzo(spada), "Attrezzo dovrebbe essere aggiunto");
        assertTrue(Atrio.hasAttrezzo("spada"), "La stanza dovrebbe contenere l'attrezzo 'spada'");
    }

    @Test
    public void testGetStanzaAdiacente() {
        // Testiamo che la stanza adiacente venga restituita correttamente
        assertEquals(Biblioteca, Atrio.getStanzaAdiacente("nord"), "La stanza adiacente a nord dovrebbe essere la Cucina");
        assertEquals(N11, Atrio.getStanzaAdiacente("est"), "La stanza adiacente a est dovrebbe essere il Giardino");
    }

    @Test
    public void testHasAttrezzo() {
        // Testiamo che un attrezzo sia correttamente trovato
    	Atrio.addAttrezzo(spada);
    	Atrio.addAttrezzo(chiave);
        
        assertTrue(Atrio.hasAttrezzo("spada"), "La stanza dovrebbe contenere l'attrezzo 'spada'");
        assertTrue(Atrio.hasAttrezzo("chiave"), "La stanza dovrebbe contenere l'attrezzo 'chiave'");
        assertFalse(Atrio.hasAttrezzo("pala"), "La stanza non dovrebbe contenere l'attrezzo 'pala'");
    }

    @Test
    public void testGetAttrezzo() {
        // Testiamo che un attrezzo venga restituito correttamente dalla stanza
    	Atrio.addAttrezzo(spada);
        Attrezzo attrezzoTrovato = Atrio.getAttrezzo("spada");

        assertNotNull(attrezzoTrovato, "L'attrezzo dovrebbe essere trovato");
        assertEquals("spada", attrezzoTrovato.getNome(), "Il nome dell'attrezzo dovrebbe essere 'spada'");

        // Verifica che l'attrezzo non esista nella stanza
        Attrezzo attrezzoNonTrovato = Atrio.getAttrezzo("pala");
        assertNull(attrezzoNonTrovato, "L'attrezzo 'pala' non dovrebbe essere presente nella stanza");
    }

    @Test
    public void testRemoveAttrezzo() {
        // Testiamo la rimozione dell'attrezzo
    	Atrio.addAttrezzo(spada);
        assertTrue(Atrio.removeAttrezzo(spada), "L'attrezzo 'spada' dovrebbe essere rimosso dalla stanza");
        assertFalse(Atrio.hasAttrezzo("spada"), "La stanza non dovrebbe contenere l'attrezzo 'spada' dopo la rimozione");
    }
    
    @Test
    public void testDescrizioneStanza() {
        // Crea la stanza
        Stanza Atrio = new Stanza("Atrio");

        // Aggiungi attrezzi
        Attrezzo spada = new Attrezzo("spada", 1);
        Attrezzo chiave = new Attrezzo("chiave", 1);
        Atrio.addAttrezzo(spada);
        Atrio.addAttrezzo(chiave);

        // Imposta uscite
        Stanza salaAdiacente = new Stanza("Uscita");
        Atrio.impostaStanzaAdiacente("nord", salaAdiacente);

        // Ottieni la descrizione della stanza
        String descrizione = Atrio.getDescrizione();
        
        // Stampa la descrizione per il debug
        System.out.println(descrizione);

        // Verifica che la descrizione contenga il nome della stanza
        assertTrue(descrizione.contains("Atrio"), "La descrizione dovrebbe contenere il nome della stanza.");

        // Verifica che la descrizione contenga le uscite
        assertTrue(descrizione.contains("Uscite:"), "La descrizione dovrebbe contenere la parola 'Uscite'.");
        assertTrue(descrizione.contains("nord"), "La descrizione dovrebbe contenere la direzione 'nord' come uscita.");

        // Verifica che la descrizione contenga gli attrezzi
        assertTrue(descrizione.contains("spada"), "La descrizione dovrebbe contenere l'attrezzo 'spada'.");
        assertTrue(descrizione.contains("chiave"), "La descrizione dovrebbe contenere l'attrezzo 'chiave'.");
    }


}
