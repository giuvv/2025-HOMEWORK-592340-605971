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
		partita.setStanzaCorrente(partita.getStanzaFinale());
		assertTrue(partita.vinta(),"La partita dovrebbe essere vinta");
	}
	
	@Test
	public void testPartitaPersa() {
		partita.setStanzaCorrente(stanzaIniziale);
		assertFalse(partita.vinta(),"La partita dev'essere persa");
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
	public void testPartitaNonFinitaAllInizio() {
	    assertFalse(partita.isFinita(), "Una nuova partita non dovrebbe essere finita.");
	}

	@Test
	public void testSetFinitaImpostaCorrettamente() {
	    partita.setFinita();
	    assertTrue(partita.isFinita(), "Dopo aver chiamato setFinita(), la partita dovrebbe essere finita.");
	}

	@Test
	public void testPartitaNonVintaSeNonNellaStanzaFinale() {
	    Stanza altraStanza = new Stanza("Stanza Intermedia");
	    partita.setStanzaCorrente(altraStanza);
	    assertFalse(partita.vinta(), "La partita non dovrebbe essere vinta se il giocatore non è nella stanza finale.");
	}

	
	
	
}
