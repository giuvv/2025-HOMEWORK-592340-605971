import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ComandoPosaTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Partita partita;
    private Giocatore giocatore;
    private Attrezzo attrezzo;
    private ComandoPosa comandoPosa;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));  

        partita = new Partita();
        giocatore = partita.getGiocatore();
        
        attrezzo = new Attrezzo("spada", 1);
        
        giocatore.getBorsa().addAttrezzo(attrezzo);
        
       
        comandoPosa = new ComandoPosa();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);  
    }

    @Test
    void testPosaAttrezzoPresente() {
        
        comandoPosa.setParametro("spada");

        
        comandoPosa.esegui(partita);

        
        assertNull(giocatore.getBorsa().getAttrezzo("spada"));
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));

        
        String messaggioStampato = output.toString().trim();
        assertTrue(messaggioStampato.contains("Hai posato: spada"));
    }

    @Test
    void testPosaAttrezzoNonInBorsa() {
        Partita partita = new Partita();  
        Giocatore giocatore = partita.getGiocatore();
        Stanza stanza = partita.getStanzaCorrente();
        ComandoPosa comando = new ComandoPosa();
        
       
        comando.setParametro("spada");  
        comando.esegui(partita);  

        
        assertTrue(ioConsole.getLastMessage().contains("Non hai questo attrezzo nella borsa"));
        
       
        Attrezzo attrezzo = new Attrezzo("spada", 3);
        giocatore.getBorsa().addAttrezzo(attrezzo);
        
        
        comando.setParametro("spada");  
        comando.esegui(partita);

        
        assertTrue(ioConsole.getLastMessage().contains("Hai posato: spada"));

        
        comando.setParametro("scudo");
        giocatore.getBorsa().addAttrezzo(new Attrezzo("scudo", 5));
        comando.esegui(partita);  

        
        assertTrue(ioConsole.getLastMessage().contains("La stanza è già piena"));
    }


    @Test
    void testPosaStanzaPiena() {
        
        for (int i = 0; i < 10; i++) {
            partita.getStanzaCorrente().addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
        comandoPosa.setParametro("spada");
        comandoPosa.esegui(partita);

        
        String messaggioStampato = output.toString().trim();
        assertTrue(messaggioStampato.contains("La stanza è già piena"));
    }
}
