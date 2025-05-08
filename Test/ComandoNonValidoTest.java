import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ComandoNonValidoTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    private Partita partita;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));  
        partita = new Partita();  
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);  
    }

    @Test
    void testComandoNonValido() {
       
        ComandoNonValido comando = new ComandoNonValido();
        
        comando.esegui(partita);

        String messaggioStampato = output.toString().trim();

        assertEquals("Comando non valido", messaggioStampato);
    }
}
