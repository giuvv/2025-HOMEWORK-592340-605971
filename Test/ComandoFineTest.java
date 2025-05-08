import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoFine;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ComandoFineTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));  
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); 
    }

    @Test
    void testMessaggioDiFine() {
        ComandoFine comando = new ComandoFine();
        Partita partita = new Partita();

        comando.esegui(partita);

        String messaggioStampato = output.toString();
        assertTrue(messaggioStampato.contains("Grazie di aver giocato!"));
    }
}
