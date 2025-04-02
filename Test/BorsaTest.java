import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorsaTest {
	private Borsa borsa;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	
	@BeforeEach
	public void setUp() {
		borsa = new Borsa();
		attrezzo1 = new Attrezzo("lanterna", 3);
		attrezzo2 = new Attrezzo("osso", 1);
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
	}
	
	@Test
	public void testPesoMax() {
		assertEquals(10,borsa.getPesoMax());
	}
	
	@Test
	public void testAddAttrezzo() {
		boolean risultato = borsa.addAttrezzo(attrezzo1);
		
		assertTrue(risultato);
	}
	@Test
	public void testHasAttrezzo() {
		assertTrue(borsa.hasAttrezzo("lanterna"));
	}
	@Test
	public void testRemoveAttrezzo() {

	    Attrezzo rimosso = borsa.removeAttrezzo("lanterna");
	    
	    assertNotNull(rimosso);
	    assertEquals("lanterna", rimosso.getNome());
	    
	    assertNull(borsa.getAttrezzo("lanterna"));
	    assertFalse(borsa.hasAttrezzo("lanterna"));
	}
	
	@Test
	public void testGetAttrezzo() {
		borsa.addAttrezzo(attrezzo1);
		assertNotNull(attrezzo1);
		assertEquals(3,attrezzo1.getPeso());
	}
	@Test
	public void testToString() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		
		String borsaString = borsa.toString();
		
		assertTrue(borsaString.contains("lanterna"));
		assertTrue(borsaString.contains("osso"));
		assertFalse(borsaString.contains("Borsa vuota"));
	}
}
