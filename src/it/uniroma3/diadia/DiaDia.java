package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole ioConsole;

	public DiaDia() {
		this.partita = new Partita();
		this.ioConsole = new IOConsole();
	}

	public void gioca() {
		String istruzione;
		ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do		
			istruzione = ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if (comandoDaEseguire.getNome()!=null)
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))
				this.vai(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();
			else if (comandoDaEseguire.getNome().equals("prendi"))
				this.prendiAttrezzo(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("posa"))
				this.posaAttrezzo(comandoDaEseguire.getParametro());
			else
				ioConsole.mostraMessaggio("Comando sconosciuto");
			if (this.partita.vinta()) {
				ioConsole.mostraMessaggio("Hai vinto!");
				return true;
			} else
				return false;
	}   

	// implementazioni dei comandi dell'utente:

	/* Comando per prendere un attrezzo:
	 * rimuove dalla stanza e aggiunge alla borsa
	 */
	private void prendiAttrezzo(String nomeAttrezzo) {
		
		Stanza stanza = this.partita.getStanzaCorrente();
		Attrezzo attrezzo = stanza.getAttrezzo(nomeAttrezzo);
		
		if (attrezzo==null)
			ioConsole.mostraMessaggio("L'attrezzo " + attrezzo + " non è nella stanza");
		else if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
			stanza.removeAttrezzo(attrezzo);
			ioConsole.mostraMessaggio("Hai preso: " + attrezzo);
		}
		else 
			ioConsole.mostraMessaggio("La borsa è già piena");
	}
	
	/* Comando per posare un attrezzo:
	 * rimuove dalla borsa e aggiunge nella stanza
	 */
	private void posaAttrezzo(String nomeAttrezzo) {
		
		Giocatore giocatore = this.partita.getGiocatore();
		Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		
		if (attrezzo==null) 
			ioConsole.mostraMessaggio("L'attrezzo " + attrezzo + " non ce l'hai");
		else if (this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			giocatore.removeAttrezzo(nomeAttrezzo);
			ioConsole.mostraMessaggio("Hai posato: " + attrezzo);
		}
		else 
			ioConsole.mostraMessaggio("La stanza è già piena");
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			ioConsole.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.ioConsole.mostraMessaggio("Direzione inesistente");
		else if (this.partita.getGiocatore().getCfu()<=0) {
			this.partita.setFinita();
			ioConsole.mostraMessaggio("Non hai CFU a disposizione per muoverti");
			this.fine();
		}
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		ioConsole.mostraMessaggio("Hai a disposizione "+ this.partita.getGiocatore().getCfu() +" CFU");
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}