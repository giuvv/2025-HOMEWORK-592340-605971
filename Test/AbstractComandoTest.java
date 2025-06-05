import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Scanner;


import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class AbstractComandoTest {

	ConcreteComando cc;
	Partita p;
	
	@BeforeEach
	public void setUp() throws Exception {
		cc = new ConcreteComando();
		p = new Partita(Labirinto.newBuilder("labirinto.txt").getLabirinto());
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testConcreteComandoGetNome() {
		 assertNotEquals("AbstractComando", cc.getNome());
		 assertEquals("ConcreteComando", cc.getNome());
	}
	
	@Test
	public void testConcreteComandoEsegui() {
		cc.esegui(p);
		 assertTrue(p.isFinita());
	}

	@Test
	public void testConcreteComandoGetIO() {
		cc.setIo(new IOConsole(new Scanner(System.in)));
		 assertNotNull(cc.getIo());
	}
	
	@Test
	public void testConcreteComandoParametro() {
		cc.setParametro("pippo");
		 assertNotNull(cc.getParametro());
	}


}

