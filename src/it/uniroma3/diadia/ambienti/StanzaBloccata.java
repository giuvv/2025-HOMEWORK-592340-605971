package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String attrezzoSblocca;
	
	public StanzaBloccata (String nome, String direzioneBloccata, String attrezzoSblocca) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSblocca = attrezzoSblocca;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if (direzioneBloccata.equals(dir) && !this.hasAttrezzo(attrezzoSblocca))
			return this; //non fa nulla, rimane nella stanza corrente
		else
			return super.getStanzaAdiacente(dir);
	}

	@Override
	public String getDescrizione() {
        String descrizione = "La stanza è bloccata in direzione "+direzioneBloccata+"/nPrendi "+attrezzoSblocca+"per sbloccare la direzione";
        if (!this.hasAttrezzo(attrezzoSblocca))
        	return descrizione;
        else 
        	return super.getDescrizione();
    }
}
