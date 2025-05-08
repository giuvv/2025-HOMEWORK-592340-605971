import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
public class ComandoVaiTest {

    private ComandoVai comandoVai;
    private Partita partita;
    private Stanza atrio;
    private Stanza biblioteca;

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        comandoVai = new ComandoVai();
        partita = new Partita();
        atrio = new Stanza("Atrio");
        biblioteca = new Stanza("Biblioteca");
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        partita.setStanzaCorrente(atrio);

        // Redirect di System.out per leggere l'output
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        // Ripristina System.out
        System.setOut(originalOut);
    }

    @Test
    public void testVaiDirezioneValida() {
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
    }

    @Test
    public void testConsumoCfu() {
        int cfuIniziali = partita.getGiocatore().getCfu();
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
    }

    @Test
    public void testDirezioneNull() {
        comandoVai.setParametro(null);
        comandoVai.esegui(partita);
        String out = output.toString();
        assertTrue(out.contains("Dove vuoi andare?"));
        assertTrue(out.contains("Devi specificare una direzione"));
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());  // non cambia stanza
    }

    @Test
    public void testDirezioneInesistente() {
        comandoVai.setParametro("sud"); // non impostata
        comandoVai.esegui(partita);
        String out = output.toString();
        assertTrue(out.contains("Direzione inesistente"));
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());  // non cambia stanza
    }

    @Test
    public void testStampaNomeNuovaStanza() {
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        String out = output.toString();
        assertTrue(out.contains("Biblioteca"));
    }

    @Test
    public void testCfuZeroDopoSpostamento() {
        partita.getGiocatore().setCfu(1); // CFU = 1
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals(0, partita.getGiocatore().getCfu());
    }
}
