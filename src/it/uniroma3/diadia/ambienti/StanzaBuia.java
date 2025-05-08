package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzo;
	
	public StanzaBuia (String nome, String attrezzoParticolare) {
		super(nome);
		this.nomeAttrezzo = attrezzoParticolare;
	}

	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(nomeAttrezzo))
			return this.toString();
		else 
			return "Qui c'è un buio pesto";
    } 
	
}
