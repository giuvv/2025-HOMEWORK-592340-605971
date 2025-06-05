import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;




   /* private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private Partita partita;
    private Giocatore giocatore;
    private Attrezzo attrezzo;
    private ComandoPosa comandoPosa;

    @BeforeEach
    void setUp() throws Exception{
        System.setOut(new PrintStream(output));  
        Labirinto labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
        partita = new Partita(labirinto);
        giocatore = partita.getGiocatore();
         
        attrezzo = new Attrezzo("spada", 1);
        
        giocatore.getBorsa().addAttrezzo(attrezzo);
        
       
        comandoPosa = new ComandoPosa();
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
    void testPosaStanzaPiena() {
        
        for (int i = 0; i < 10; i++) {
            partita.getStanzaCorrente().addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
        comandoPosa.setParametro("spada");
        comandoPosa.esegui(partita);

        
        String messaggioStampato = output.toString().trim();
        assertTrue(messaggioStampato.contains("La stanza è già piena"));
    }*/
	

public class ComandoPosaTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comando;
	Labirinto labirinto;

	@BeforeEach
	public void setUp() throws Exception {
		 labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();

		partita = new Partita(labirinto);
		attrezzo = new Attrezzo("martello", 2);
		comando = new ComandoPosa();
		io = new IOConsole(new Scanner(System.in));
		comando.setIo(io);
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testAttrezzoPosato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoPosatoNull() {
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}


	public void creatoreAttrezzi() {
		for(int i= 0; i<10;i++) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("utensile"+i, 1));
		}
	}
	
	@Test
	public void testTroppiAttrezzi() {
		this.creatoreAttrezzi();
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}

}
