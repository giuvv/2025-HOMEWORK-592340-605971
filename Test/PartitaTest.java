import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PartitaTest {

	private Partita partita;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
		stanzaIniziale = partita.getStanzaCorrente();
		stanzaVincente = new Stanza("Stanza Vincente");
		partita.setStanzaCorrente(stanzaIniziale);
	}
	
	@Test
	public void testPartitaVinta() {
		partita.setStanzaCorrente(stanzaVincente);
		assertTrue(partita.vinta(),"La partita dovrebbe essere vinta");
	}
	
	@Test
	public void testPartitaPersa() {
		partita.setStanzaCorrente(stanzaIniziale);
		assertFalse(partita.vinta(),"La partita dev'essere persa");
	}
	
	@Test
	public void testPartitaIsFinita() {
		partita.setStanzaCorrente(stanzaVincente);
		assertTrue(partita.isFinita(),"La partita dovrebbe essere finita");
	}
	
	@Test
    public void testPartitaFinitaPerCfuZero() {
        partita.setCfu(0); // Imposta i CFU a 0

        // Verifica che la partita sia finita quando i CFU sono 0
        assertTrue(partita.isFinita(), "La partita dovrebbe essere finita quando i CFU sono 0.");
    }
	
	@Test
	public void testSetCfu() {
		partita.setCfu(15);
		
		assertEquals(15,partita.getCfu(),"Il numero di cfu dev'essere cambiato");
	}
	
	@Test
	public void testCfuIniziali() {
		assertEquals(20,partita.getCfu(),"il numero di CFU iniziali dev'essere 20");
	}
	
	@Test
	public void testPartitaCompleta() {
		partita.setStanzaCorrente(stanzaVincente);
		assertFalse(partita.isFinita());
		partita.setFinita(True);
		assertTrue(partita.isFinita());
	}
	
	
}
