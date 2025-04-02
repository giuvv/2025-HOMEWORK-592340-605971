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
        
        Atrio = new Stanza("Atrio");
        Biblioteca = new Stanza("Biblioteca");
        N11 = new Stanza("N11");

        spada = new Attrezzo("spada", 1);
        chiave = new Attrezzo("chiave", 0);
        
        Atrio.impostaStanzaAdiacente("nord", Biblioteca);
        Atrio.impostaStanzaAdiacente("est", N11);
    }

    @Test
    public void testAddAttrezzo() {
        assertTrue(Atrio.addAttrezzo(spada), "Attrezzo dovrebbe essere aggiunto");
        assertTrue(Atrio.hasAttrezzo("spada"), "La stanza dovrebbe contenere l'attrezzo 'spada'");
    }

    @Test
    public void testGetStanzaAdiacente() {
        assertEquals(Biblioteca, Atrio.getStanzaAdiacente("nord"), "La stanza adiacente a nord dovrebbe essere la Cucina");
        assertEquals(N11, Atrio.getStanzaAdiacente("est"), "La stanza adiacente a est dovrebbe essere il Giardino");
    }

    @Test
    public void testHasAttrezzo() {
    	Atrio.addAttrezzo(spada);
    	Atrio.addAttrezzo(chiave);
        
        assertTrue(Atrio.hasAttrezzo("spada"), "La stanza dovrebbe contenere l'attrezzo 'spada'");
        assertTrue(Atrio.hasAttrezzo("chiave"), "La stanza dovrebbe contenere l'attrezzo 'chiave'");
        assertFalse(Atrio.hasAttrezzo("pala"), "La stanza non dovrebbe contenere l'attrezzo 'pala'");
    }

    @Test
    public void testGetAttrezzo() {
    	Atrio.addAttrezzo(spada);
        Attrezzo attrezzoTrovato = Atrio.getAttrezzo("spada");

        assertNotNull(attrezzoTrovato, "L'attrezzo dovrebbe essere trovato");
        assertEquals("spada", attrezzoTrovato.getNome(), "Il nome dell'attrezzo dovrebbe essere 'spada'");

        Attrezzo attrezzoNonTrovato = Atrio.getAttrezzo("pala");
        assertNull(attrezzoNonTrovato, "L'attrezzo 'pala' non dovrebbe essere presente nella stanza");
    }

    @Test
    public void testRemoveAttrezzo() {
    	Atrio.addAttrezzo(spada);
        assertTrue(Atrio.removeAttrezzo(spada), "L'attrezzo 'spada' dovrebbe essere rimosso dalla stanza");
        assertFalse(Atrio.hasAttrezzo("spada"), "La stanza non dovrebbe contenere l'attrezzo 'spada' dopo la rimozione");
    }
    
    @Test
    public void testDescrizioneStanza() {
        Stanza Atrio = new Stanza("Atrio");

        Attrezzo spada = new Attrezzo("spada", 1);
        Attrezzo chiave = new Attrezzo("chiave", 1);
        Atrio.addAttrezzo(spada);
        Atrio.addAttrezzo(chiave);

        Stanza salaAdiacente = new Stanza("Uscita");
        Atrio.impostaStanzaAdiacente("nord", salaAdiacente);

        String descrizione = Atrio.getDescrizione();
        
        System.out.println(descrizione);

        assertTrue(descrizione.contains("Atrio"), "La descrizione dovrebbe contenere il nome della stanza.");

        assertTrue(descrizione.contains("Uscite:"), "La descrizione dovrebbe contenere la parola 'Uscite'.");
        assertTrue(descrizione.contains("nord"), "La descrizione dovrebbe contenere la direzione 'nord' come uscita.");

        assertTrue(descrizione.contains("spada"), "La descrizione dovrebbe contenere l'attrezzo 'spada'.");
        assertTrue(descrizione.contains("chiave"), "La descrizione dovrebbe contenere l'attrezzo 'chiave'.");
    }


}
