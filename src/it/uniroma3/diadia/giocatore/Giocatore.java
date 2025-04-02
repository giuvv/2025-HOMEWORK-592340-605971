package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Borsa; 
import it.uniroma3.diadia.Borsa;
public class Giocatore {
	private int cfu;
	private Borsa borsa;
	
	
	public Giocatore(){
		this.cfu = 20;
		this.borsa = new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	
	public Attrezzo removeAttrezzo(Stringa nomeAttrezzo) {
		return this.borsa.removeAttrezzo(nomeAttrezzo);
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.borsa.hasAttrezzo(nomeAttrezzo);
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public boolean isVivo() {
		return this.cfu > 0;
	}
	
	public void riduciCfu() {
		this.cfu--;
    }
	
	public String toString() {
		return "CFU: "+ this.cfu + ", " + this.borsa.toString();
	}
}
