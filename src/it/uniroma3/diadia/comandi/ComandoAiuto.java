package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	IOConsole ioConsole = new IOConsole();
			
	public void esegui(Partita partita) {
		ioConsole.mostraMessaggio("\"vai\", \"aiuto\", \"fine\", \"prendi\", \"posa\", \"guarda\"");
	}
	
	@Override
	public void setParametro(String parametro) {
	}
	
	@Override
	public String getNome() {
		return null;
	}
	
	@Override
	public String getParametro() {
		return null;
	}

}
