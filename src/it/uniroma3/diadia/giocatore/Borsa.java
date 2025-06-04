package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMax();
	private Map<String, Attrezzo> nome2attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private int pesoAttuale;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.nome2attrezzi = new TreeMap<>();
		this.numeroAttrezzi = 0;
		this.pesoAttuale = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
        if (!this.puoContenereAttrezzo(attrezzo))
            return false;
        this.nome2attrezzi.put(attrezzo.getNome(), attrezzo);
        this.numeroAttrezzi++;
        this.pesoAttuale += attrezzo.getPeso();
        System.out.println("Aggiunto " + attrezzo.getNome() + ": peso attuale = " + pesoAttuale);
        return true;
    }
	
	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
        if (nomeAttrezzo != null && this.nome2attrezzi.containsKey(nomeAttrezzo))
            return this.nome2attrezzi.get(nomeAttrezzo);
        return null;
    }

	public int getPeso() {
		return this.pesoAttuale;
	}

	public int getPesoRimanente() {
        return this.pesoMax - this.pesoAttuale;
    }
    
    public boolean puoContenereAttrezzo(Attrezzo attrezzo) {
        if (attrezzo == null || attrezzo.getPeso() < 0)
            return false;
        return this.getPesoRimanente() >= attrezzo.getPeso();
    }

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
        if (nomeAttrezzo == null)
            return null;
        Attrezzo a = this.nome2attrezzi.remove(nomeAttrezzo);
        if (a != null) {
            this.numeroAttrezzi--;
            this.pesoAttuale -= a.getPeso();
            if (this.pesoAttuale < 0)
            	this.pesoAttuale = 0;
            System.out.println("Rimosso " + a.getNome() + ": peso attuale = " + pesoAttuale);
        }
        return a;
    }
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Peso max borsa: "+this.getPesoMax()+"kg. Peso rimanente: "+this.getPesoRimanente()+"kg");
			s.append("\n");
			s.append(this.getContenutoOrdinatoPerNome().toString());
			s.append("\n");
			s.append(this.getContenutoRaggruppatoPerPeso().toString());
			s.append("\n");
			s.append(this.getSortedSetOrdinatoPerPeso().toString());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPeso());
		s.addAll(this.nome2attrezzi.values());
		return s;
	}
	
	List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> l = new ArrayList<>();
		l.addAll(this.nome2attrezzi.values());
		Collections.sort(l, new ComparatoreAttrezziPerPeso());
		return l;
	}

	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(this.nome2attrezzi.values());
	}

	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> a2p = new TreeMap<>();
		for(Attrezzo a : this.nome2attrezzi.values()){
			if(a2p.containsKey(a.getPeso())) {
				a2p.get(a.getPeso()).add(a);
			}
			else {
				Set<Attrezzo>  s =new HashSet<Attrezzo>();
				s.add(a);
				a2p.put(a.getPeso(), s);
			}
		}
		return a2p;
	}

}