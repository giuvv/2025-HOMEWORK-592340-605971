import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.StanzaBuia;


public class StanzaBuiaTest {

	private StanzaBuia stanza;
	private Attrezzo lumino;
	@BeforeEach
	public void setUp() throws Exception {
		stanza = new StanzaBuia("StanzaBuia", "lumino");
		lumino = new Attrezzo("lumino", 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDescrizioneConAttrezzo() {
		stanza.addAttrezzo(lumino);
		assertEquals(stanza.toString(), stanza.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		String e = "qui c'è un buio pesto";
		assertEquals(e, stanza.getDescrizione());
	}
}

