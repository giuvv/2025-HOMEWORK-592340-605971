package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa implements Comando {
	
	private String nomeAttrezzo;
	IOConsole ioConsole = new IOConsole();
	
	public void esegui(Partita partita) {
		
		if (this.nomeAttrezzo==null) {
			ioConsole.mostraMessaggio("Non hai questo attrezzo nella borsa oppure devi specificare il nome dell'attrezzo da posare");
			ioConsole.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		} 
		
		Giocatore giocatore = partita.getGiocatore();
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		
		if (partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			giocatore.removeAttrezzo(nomeAttrezzo);
			ioConsole.mostraMessaggio("Hai posato: " + attrezzo);
		}
		else 
			ioConsole.mostraMessaggio("La stanza è già piena");
	}
	
		@Override
		public void setParametro(String parametro) {
			this.nomeAttrezzo = parametro;
		}

		@Override
		public String getNome() {
			return null;
		}

		@Override
		public String getParametro() {
			return this.nomeAttrezzo;
		}

}
	
	
