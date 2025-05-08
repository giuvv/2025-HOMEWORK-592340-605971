import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoGuarda;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ComandoGuardaTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    private Partita partita;
    private Stanza stanza;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));  
        partita = new Partita();
        
        
        stanza = new Stanza("Atrio");
        
        stanza.impostaStanzaAdiacente("nord", new Stanza("Giardino"));

        partita.setStanzaCorrente(stanza);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);  
    }

    @Test
    void testGuardaStanza() {
        ComandoGuarda comando = new ComandoGuarda();
        
        comando.esegui(partita);

        String messaggioStampato = output.toString();

        assertTrue(messaggioStampato.contains("Atrio"));
        
        assertTrue(messaggioStampato.contains("nord"));
        
        assertTrue(messaggioStampato.contains("Attrezzi nella stanza:"));
    }
}
