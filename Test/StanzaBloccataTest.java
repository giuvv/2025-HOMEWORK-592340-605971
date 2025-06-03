

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StanzaBloccataTest {

    @Test
    public void testStanzaBloccataSenzaAttrezzo() {
        Stanza stanzaAdiacente = new Stanza("Stanza Nord");
        StanzaBloccata stanzaBloccata = new StanzaBloccata("Bloccata", "nord", "chiave");
        stanzaBloccata.impostaStanzaAdiacente("nord", stanzaAdiacente);

        // Senza chiave, deve rimanere nella stessa stanza
        assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
    }

    @Test
    public void testStanzaBloccataConAttrezzo() {
        Stanza stanzaAdiacente = new Stanza("Stanza Nord");
        StanzaBloccata stanzaBloccata = new StanzaBloccata("Bloccata", "nord", "chiave");
        stanzaBloccata.impostaStanzaAdiacente("nord", stanzaAdiacente);

        stanzaBloccata.addAttrezzo(new Attrezzo("chiave", 1));

        // Con la chiave, la direzione viene sbloccata
        assertEquals(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente("nord"));
    }
}
