

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;



public class StanzaBloccataTest {

	private StanzaBloccata sb;
	private Stanza s;
	private Attrezzo a;
	
	@BeforeEach
	public void setUp() throws Exception {
		sb = new StanzaBloccata("StanzaBloccata", Direzione.ovest, "grimaldello");
		s = new Stanza("Stanzetta");
		a = new Attrezzo("grimaldello", 1);
		sb.impostaStanzaAdiacente(Direzione.ovest, s);
		
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		assertEquals(sb, sb.getStanzaAdiacente(Direzione.ovest));
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccata() {
		sb.addAttrezzo(a);
		assertEquals(s, sb.getStanzaAdiacente(Direzione.ovest));
		
	}

	@Test
	public void testGetDescrizioneDirezioneSbloccata() {
		sb.addAttrezzo(a);
		assertEquals(sb.toString(), sb.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneDirezioneBloccata() {
		
		String e = "La stanza è bloccata in direzione ovest"+"/nPrendi il grimaldello per sbloccare la direzione";
		assertEquals(e, sb.getDescrizione());
		
	}

}
