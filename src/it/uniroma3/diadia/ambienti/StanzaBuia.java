package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzo;
	
	public StanzaBuia (String nome, String attrezzoParticolare) {
		super(nome);
		this.nomeAttrezzo = attrezzoParticolare;
	}

	@Override
	public String getDescrizione() {
		String buio = new String();
		buio = "qui c'è un buio pesto";
		if(!this.hasAttrezzo(nomeAttrezzo))
			return buio;
		return super.getDescrizione();
	}
}
