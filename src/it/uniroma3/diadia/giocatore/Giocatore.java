package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

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

    // Restituisce la borsa del giocatore
    public Borsa getBorsa() {
        return this.borsa;
    }

}
