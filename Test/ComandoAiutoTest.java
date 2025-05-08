import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoAiuto;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ComandoAiutoTest {

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
    void testMessaggioAiutoStampato() {
        ComandoAiuto comando = new ComandoAiuto();
        Partita partita = new Partita();

        comando.esegui(partita);

        String messaggioStampato = output.toString();
        assertTrue(messaggioStampato.contains("vai"));
        assertTrue(messaggioStampato.contains("aiuto"));
        assertTrue(messaggioStampato.contains("fine"));
        assertTrue(messaggioStampato.contains("prendi"));
        assertTrue(messaggioStampato.contains("posa"));
        assertTrue(messaggioStampato.contains("guarda"));
    }
}
