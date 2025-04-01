



/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
	
    private Attrezzo[] attrezzi;
    private int numeroAttrezzi;
    
    private Stanza[] stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
    
	private String[] direzioni;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false; // Variabile per tenere traccia se la direzione è già stata aggiornata

        // Ciclo su tutte le direzioni già esistenti nella stanza corrente
        for (int i = 0; i < this.direzioni.length; i++) {
            if (direzione.equals(this.direzioni[i])) { // Se la direzione è già presente
                this.stanzeAdiacenti[i] = stanza; // Imposto la stanza in quella direzione
                aggiornato = true; // Segno che la direzione è stata trovata e aggiornata
                break; // Interrompo il ciclo, perché non è necessario cercare ulteriormente
            }
        }

        // Se la direzione non è stata trovata, la aggiungiamo
        if (!aggiornato) {
            // Verifico se ci sono ancora posti liberi per aggiungere una nuova direzione
            if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
                this.direzioni[this.numeroStanzeAdiacenti] = direzione; // Aggiungo la direzione
                this.stanzeAdiacenti[this.numeroStanzeAdiacenti] = stanza; // Aggiungo la stanza
                this.numeroStanzeAdiacenti++; // Incremento il contatore delle stanze adiacenti
            } else {
                System.out.println("Non ci sono più direzioni disponibili.");
            }
        }
    }


    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++) {
        	if (this.direzioni[i].equals(direzione)) {
        		stanza = this.stanzeAdiacenti[i];
        	}
		}
        return stanza;
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi[numeroAttrezzi] = attrezzo;
        	this.numeroAttrezzi++;
        	return true;
        }
        else {
        	return false;
        }
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    @Override
    public String toString() {
        StringBuilder risultato = new StringBuilder();

        // Aggiungi il nome della stanza
        risultato.append(this.nome);
        risultato.append("\n");

        // Aggiungi le direzioni (uscite) disponibili
        risultato.append("Uscite: ");
        boolean hasDirezioni = false;
        for (String direzione : this.direzioni) {
            if (direzione != null) {
                risultato.append(direzione + " ");
                hasDirezioni = true;
            }
        }
        if (!hasDirezioni) {
            risultato.append("Nessuna uscita.");
        }
        
        risultato.append("\n");

        // Aggiungi gli attrezzi presenti nella stanza
        risultato.append("Attrezzi nella stanza: ");
        boolean hasAttrezzi = false;
        for (Attrezzo attrezzo : this.attrezzi) {
            if (attrezzo != null) {
                risultato.append(attrezzo.getNome() + " ");
                hasAttrezzi = true;
            }
        }
        if (!hasAttrezzi) {
            risultato.append("Nessun attrezzo.");
        }

        return risultato.toString();
    }


    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
    public boolean hasAttrezzo(String nomeAttrezzo) {
        boolean trovato = false;
        for (Attrezzo attrezzo : this.attrezzi) {
            if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo)) { // Controllo se l'attrezzo non è null
                trovato = true;
                break; // Esci dal ciclo appena trovato l'attrezzo
            }
        }
        return trovato;
    }

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzo;
			break;
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++) {
            if (this.attrezzi[i] != null && this.attrezzi[i].equals(attrezzo)) {
                // Spostiamo tutti gli attrezzi successivi di una posizione
                for (int j = i; j < this.numeroAttrezzi - 1; j++) {
                    this.attrezzi[j] = this.attrezzi[j + 1];
                }
                // Impostiamo l'ultimo elemento come null
                this.attrezzi[this.numeroAttrezzi - 1] = null;
                this.numeroAttrezzi--; // Riduciamo il numero di attrezzi
                return true; // Attrezzo rimosso con successo
            }
        }
		return false;
	}


	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
	    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
	    	direzioni[i] = this.direzioni[i];
	    return direzioni;
    }

}