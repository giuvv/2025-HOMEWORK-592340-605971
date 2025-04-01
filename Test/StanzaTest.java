import java.util.Scanner;

public class StanzaTest {
    public static void main(String[] args) {
        // Creazione delle stanze
        Stanza stanza1 = new Stanza("Ingresso");
        Stanza stanza2 = new Stanza("Cucina");
        Stanza stanza3 = new Stanza("Soggiorno");
        
        // Creazione degli attrezzi
        Attrezzo attrezzo1 = new Attrezzo("Martello", 3);
        Attrezzo attrezzo2 = new Attrezzo("Chiave", 1);
        Attrezzo attrezzo3 = new Attrezzo("Cacciavite", 2);
        
        // Aggiunta degli attrezzi nelle stanze
        stanza1.addAttrezzo(attrezzo1);
        stanza2.addAttrezzo(attrezzo2);
        stanza3.addAttrezzo(attrezzo3);
        
        // Impostazione delle stanze adiacenti
        stanza1.impostaStanzaAdiacente("est", stanza2);
        stanza2.impostaStanzaAdiacente("ovest", stanza1);
        stanza2.impostaStanzaAdiacente("sud", stanza3);
        stanza3.impostaStanzaAdiacente("nord", stanza2);
        
        // Scanner per prendere input dall'utente
        Scanner scanner = new Scanner(System.in);
        
        Stanza stanzaCorrente = stanza1;  // Partiamo dalla stanza1 (Ingresso)
        
        while (true) {
            System.out.println("\nSei nella stanza: " + stanzaCorrente.getDescrizione());
            
            // Opzioni per l'utente
            System.out.println("\nCosa vuoi fare?");
            System.out.println("1. Spostarsi in un'altra stanza");
            System.out.println("2. Prendere un attrezzo");
            System.out.println("3. Rimuovere un attrezzo");
            System.out.println("4. Esci");
            
            // Lettura della scelta dell'utente
            int scelta = scanner.nextInt();
            scanner.nextLine();  // Consuma la newline lasciata da nextInt()
            
            switch (scelta) {
                case 1: // Spostarsi in un'altra stanza
                    System.out.println("In quale direzione vuoi andare?");
                    String direzione = scanner.nextLine();
                    Stanza stanzaDestinazione = stanzaCorrente.getStanzaAdiacente(direzione);
                    if (stanzaDestinazione != null) {
                        stanzaCorrente = stanzaDestinazione;
                        System.out.println("Sei stato spostato nella stanza: " + stanzaCorrente.getNome());
                    } else {
                        System.out.println("Non ci sono stanze in quella direzione.");
                    }
                    break;
                    
                case 2: // Prendere un attrezzo
                    System.out.println("Quale attrezzo vuoi prendere?");
                    String nomeAttrezzo = scanner.nextLine();
                    Attrezzo attrezzoTrovato = stanzaCorrente.getAttrezzo(nomeAttrezzo);
                    if (attrezzoTrovato != null) {
                        System.out.println("Hai preso l'attrezzo: " + attrezzoTrovato.getNome());
                        stanzaCorrente.removeAttrezzo(attrezzoTrovato); // Rimuove l'attrezzo dalla stanza
                    } else {
                        System.out.println("Attrezzo non trovato nella stanza.");
                    }
                    break;
                    
                case 3: // Rimuovere un attrezzo
                    System.out.println("Quale attrezzo vuoi rimuovere?");
                    String nomeAttrezzoDaRimuovere = scanner.nextLine();
                    Attrezzo attrezzoDaRimuovere = stanzaCorrente.getAttrezzo(nomeAttrezzoDaRimuovere);

                    if (attrezzoDaRimuovere != null) {
                        boolean rimosso = stanzaCorrente.removeAttrezzo(attrezzoDaRimuovere);
                        if (rimosso) {
                            System.out.println("Attrezzo rimosso con successo!");
                        } else {
                            System.out.println("Errore nel rimuovere l'attrezzo.");
                        }
                    } else {
                        System.out.println("L'attrezzo non esiste nella stanza.");
                    }
                    break;
                    
                case 4: // Esci
                    System.out.println("Esci dal gioco...");
                    scanner.close();
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }
}
