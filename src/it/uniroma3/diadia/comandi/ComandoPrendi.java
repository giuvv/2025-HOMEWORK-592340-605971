package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	
	private String nomeAttrezzo;
	IOConsole ioConsole = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanza = partita.getStanzaCorrente();
		Attrezzo attrezzo = stanza.getAttrezzo(nomeAttrezzo);
			
			if (attrezzo==null)
				ioConsole.mostraMessaggio("L'attrezzo " + attrezzo + " non è nella stanza");
			else if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
				stanza.removeAttrezzo(attrezzo);
				ioConsole.mostraMessaggio("Hai preso: " + attrezzo);
			}
			else 
				ioConsole.mostraMessaggio("La borsa è già piena");
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
