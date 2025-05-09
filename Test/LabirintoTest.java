import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LabirintoTest {
		private Labirinto labirinto;
		
		@BeforeEach
		public void setUp() {
			labirinto = new Labirinto();
			labirinto.creaStanze();
		}
		
		@Test
		public void testStanzaIniziale() {
			assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
		}
		
		@Test
		public void testStanzaFinale() {
			assertEquals("Biblioteca", labirinto.getStanzaFinale().getNome());
		}
		
		@Test
		public void testAttrezzoAtrio() {
			Stanza atrio = labirinto.getStanzaIniziale();
			
			assertNotNull(atrio.getAttrezzo("osso"));
		}
		
		@Test
		public void testAttrezzoN10() {
			Stanza aulaN10 = labirinto.getStanzaIniziale().getStanzaAdiacente("sud");
			
			assertNotNull(aulaN10.getAttrezzo("lanterna"));
		}
		
		@Test
		public void testStanzaAdiacenteAtrioN10() {
			assertEquals("Aula N10",labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteAtrioN11() {
			assertEquals("Aula N11",labirinto.getStanzaIniziale().getStanzaAdiacente("est").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteAtrioBiblioteca() {
			assertEquals("Biblioteca",labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteAtrioLaboratorioCampus() {
			assertEquals("Laboratorio Campus",labirinto.getStanzaIniziale().getStanzaAdiacente("ovest").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteN11LaboratorioCampus() {
			assertEquals("Laboratorio Campus",labirinto.getStanzaIniziale().getStanzaAdiacente("est").getStanzaAdiacente("est").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteN11Atrio() {
			assertEquals("Atrio",labirinto.getStanzaIniziale().getStanzaAdiacente("est").getStanzaAdiacente("ovest").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteN10Atrio() {
			assertEquals("Atrio",labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getStanzaAdiacente("nord").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteN10N11() {
			assertEquals("Aula N11",labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getStanzaAdiacente("est").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteN10LaboratorioCampus() {
			assertEquals("Laboratorio Campus",labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getStanzaAdiacente("ovest").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteLaboratorioAtrio() {
			assertEquals("Atrio",labirinto.getStanzaIniziale().getStanzaAdiacente("ovest").getStanzaAdiacente("est").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteLaboratorioN11() {
			assertEquals("Aula N11",labirinto.getStanzaIniziale().getStanzaAdiacente("ovest").getStanzaAdiacente("ovest").getNome());
		}
		
		@Test
		public void testStanzaAdiacenteBibliotecaAtrio() {
			assertEquals("Atrio",labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getStanzaAdiacente("sud").getNome());
		}
}
