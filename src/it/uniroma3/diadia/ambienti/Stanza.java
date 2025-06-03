package it.uniroma3.diadia.ambienti;
import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;


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
	
    private Map<Attrezzo , String> attrezzo2nome;
    private int numeroAttrezzi;
    
    private Map<Direzione , Stanza> direzioni2stanze;
    private int numeroStanzeAdiacenti;
    
	
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.direzioni2stanze = new HashMap<Direzione, Stanza>();       
        this.attrezzo2nome = new HashMap<>();
    }

    public List<Stanza> getStanzeAdiacenti(){
    	List<Stanza> stanzeAdiacenti = new ArrayList<>();
    	
    	for(Stanza s : direzioni2stanze.values()) {
    		stanzeAdiacenti.add(s);
    	}
    	
    	return stanzeAdiacenti;
    }
    
	public void setStanzeAdiacenti(Map<Direzione, Stanza> stanzeAdiacenti) {
		this.direzioni2stanze = stanzeAdiacenti;
	}

	public int getNumeroStanzeAdiacenti() {
		return numeroStanzeAdiacenti;
	}

	public void setNumeroStanzeAdiacenti(int numeroStanzeAdiacenti) {
		this.numeroStanzeAdiacenti = numeroStanzeAdiacenti;
	}

	public int getNumeroAttrezziPossibili() {
		return NUMERO_MASSIMO_ATTREZZI-this.numeroAttrezzi;
	}
	
    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
        boolean aggiornato = false;
        
        if(direzioni2stanze.containsKey(direzione)) {
        	direzioni2stanze.put(direzione, stanza);
        	aggiornato = true;
        }
        if(!aggiornato) 
        	if(this.numeroStanzeAdiacenti<NUMERO_MASSIMO_DIREZIONI) {
        		this.direzioni2stanze.put(direzione, stanza);
        		this.numeroStanzeAdiacenti++;
        	}
        	
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza stanza = null;
		if(direzioni2stanze.containsKey(direzione))
			stanza = direzioni2stanze.get(direzione);
		
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
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.direzioni) {
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	}
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if (attrezzo!=null)
    			risultato.append(attrezzo.toString()+" ");
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
	            return true;
	        }
	    }
	    return false; // L'attrezzo non è stato trovato
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
	            return this.attrezzi[i];
	        }
	    }
	    return null; // Se l'attrezzo non è stato trovato
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
	    for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i].equals(attrezzo)) {
	            for (int j = i; j < this.numeroAttrezzi - 1; j++) {
	                this.attrezzi[j] = this.attrezzi[j + 1];
	            }
	            this.attrezzi[this.numeroAttrezzi - 1] = null;
	            this.numeroAttrezzi--;
	            return true;
	        }
	    }
	    return false;
	}

	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
	    for(int i=0; i<this.numeroStanzeAdiacenti; i++) {
	    	direzioni[i] = this.direzioni[i];
	    }
	    return direzioni;
    }

}