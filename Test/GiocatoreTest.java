import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GiocatoreTest {
	private Giocatore giocatore;
	
	@BeforeEach
	public void setUp() {
		giocatore = new Giocatore();
	}
	
	@Test
	public void testCfuIniziali() {
		assertEquals(20,giocatore.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(15);
		assertEquals(15,giocatore.getCfu());
	}
	
	@Test
	public void testBorsaNonNulla() {
		assertNotNull(giocatore.getBorsa());
	}
	
}
