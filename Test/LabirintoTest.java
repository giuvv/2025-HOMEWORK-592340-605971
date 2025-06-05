import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


import java.io.FileNotFoundException;


public class LabirintoTest {
	Labirinto l;
	Stanza biblioteca;
	Stanza DS1;

	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		l = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		
		biblioteca = new Stanza("Biblioteca");
		DS1 = new Stanza("DS1");

	}


	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", l.getStanzaVincente().getNome());
	}


	@Test
	public void testSetStanzaCorrente() {
		l.setStanzaCorrente(DS1);
		assertEquals(DS1, l.getStanzaCorrente());
	}
	@Test
	public void testGetStanzaCorrente() {
		assertEquals("Atrio", l.getStanzaCorrente().getNome());
	}

}
