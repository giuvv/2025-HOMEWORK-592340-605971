import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.Partita;

class StanzaBuiaTest {

    @Test
    void testDescrizioneConAttrezzo() {
        // Crea la stanza buia con l'attrezzo particolare (ad esempio, "lanterna")
        StanzaBuia stanza = new StanzaBuia("Stanza misteriosa", "lanterna");

        // Crea un attrezzo "lanterna" e aggiungilo alla stanza
        Attrezzo lanterna = new Attrezzo("lanterna", 1);
        stanza.addAttrezzo(lanterna);

        // Verifica che la descrizione della stanza sia quella normale, visto che la stanza ha l'attrezzo
        assertEquals(stanza.toString(), stanza.getDescrizione());
    }

    @Test
    void testDescrizioneSenzaAttrezzo() {
        // Crea la stanza buia con l'attrezzo particolare (ad esempio, "lanterna")
        StanzaBuia stanza = new StanzaBuia("Stanza misteriosa", "lanterna");

        // Verifica che la descrizione della stanza sia "Qui c'è un buio pesto", visto che la stanza non ha l'attrezzo
        assertEquals("Qui c'è un buio pesto", stanza.getDescrizione());
    }
    
    @Test
    void testDescrizioneSenzaAttrezzoInUnaStanzaConAltroAttrezzo() {
        // Crea una stanza buia con l'attrezzo particolare (ad esempio, "lanterna")
        StanzaBuia stanza = new StanzaBuia("Stanza misteriosa", "lanterna");

        // Aggiungi un altro attrezzo, diverso da "lanterna", alla stanza
        Attrezzo spada = new Attrezzo("spada", 2);
        stanza.addAttrezzo(spada);

        // Verifica che la descrizione della stanza sia "Qui c'è un buio pesto" visto che manca l'attrezzo "lanterna"
        assertEquals("Qui c'è un buio pesto", stanza.getDescrizione());
    }
}
