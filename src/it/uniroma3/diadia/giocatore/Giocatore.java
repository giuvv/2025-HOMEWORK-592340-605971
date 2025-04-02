package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Borsa;  // Assicurati di importare la classe Borsa

public class Giocatore {

    private int cfu;
    private Borsa borsa;

    // Costruttore del giocatore
    public Giocatore() {
        this.cfu = 20; // CFU iniziali (valore di default)
        this.borsa = new Borsa(); // Inizializza la borsa del giocatore
    }

    // Getter per i CFU
    public int getCfu() {
        return this.cfu;
    }

    // Setter per i CFU
    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    // Aggiungi un attrezzo alla borsa
    public boolean addAttrezzo(Attrezzo attrezzo) {
        return this.borsa.addAttrezzo(attrezzo);
    }

    // Rimuovi un attrezzo dalla borsa
    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
        return this.borsa.removeAttrezzo(nomeAttrezzo);
    }

    // Verifica se la borsa contiene un attrezzo
    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.borsa.hasAttrezzo(nomeAttrezzo);
    }

    // Restituisce la borsa del giocatore
    public Borsa getBorsa() {
        return this.borsa;
    }

    // Metodo per verificare se il giocatore è ancora in vita (cioè ha CFU positivi)
    public boolean isVivo() {
        return this.cfu > 0;
    }

    // Metodo per ridurre i CFU del giocatore
    public void riduciCfu() {
        this.cfu--;
    }

    // Metodo per vedere lo stato della borsa
    public String toString() {
        return "CFU: " + this.cfu + ", " + this.borsa.toString();
    }
}
