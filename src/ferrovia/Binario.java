package ferrovia;

import java.awt.Graphics;
import java.util.Hashtable;

public abstract class Binario {
	
	public boolean libero=true;//false se appartiene ad una soluzione
	
	
	
	public static final double fattrad=3.1415/180;
	public static final double angolo_giro = 360.0;
	
	double inclinazione =0;
	public double getInclinazione() {
		return inclinazione;
	}


	public abstract void ricalcolaInclinazioneGiunti(double inclinazione);
		
	
	public final void setInclinazione(double inclinazione) {
		this.inclinazione = inclinazione;
		this.ricalcolaInclinazioneGiunti(inclinazione);
		this.ricalcolaPosizioneGiunti();
	}


	public double inclinazioneGiunto =0;
	public int id =0;
	public String forma ="STANDARD";
	
	public static int newid =0;
	
	
	public String nome ="";//dritto, curvo, biforcatoIn, biforcatoOut
	public Binario precedente = null;
	public Binario successivo = null;
	public Giunto giuntoMaschio;
	public Giunto giuntoFemmina;



	public Hashtable listaNonBuoni=new Hashtable();;

	

	
	@Override
	public String toString() {
		String sPrec;
		if (precedente==null) sPrec="nessuno";
		else sPrec=precedente.nome;
		return "------\nnome=" + nome+
		"\nincl:" + inclinazione +
		"\nincl M:" + giuntoMaschio.inclinGiunto+
	
		"\nprec:"+sPrec;
	}
	

	public Binario(int newid) {
		super();
	
		id=newid;
		System.out.println("creato binario "+id);
	}
	
	
	public void stampa() {
	}
	

	public final double getRadianti(double angolo) {
		return fattrad*angolo%angolo_giro;
	}
	
	
	abstract void ricalcolaPosizioneGiunti();
	abstract void disegna(Graphics g) ;
	
	
	public void sgancia() {
		Binario _precedente = this.precedente;

		if (_precedente == null) {
			System.out.println("sgancio"+this.id+   " from "+_precedente);
			System.exit(0);;
		}
		_precedente.listaNonBuoni.put(""+this.getClass()+inclinazioneGiunto, this);
		//System.out.println("sgancio"+this.id+  " from "+_precedente.id);
		this.precedente=null;
		_precedente.successivo=null;
		this.giuntoFemmina.posX = 400;
		this.giuntoFemmina.posY = 400;
		this.inclinazione = 0;
		this.libero = true;
		this.listaNonBuoni.clear();
	}
	
	public  final void agganciaAl( Binario giacollocato) {
		//System.out.println("aggancio"+this.id+ " at "+giacollocato.id);
		this.precedente = giacollocato;
		giacollocato.successivo = this;
		this.giuntoFemmina.posX = giacollocato.giuntoMaschio.posX;
		this.giuntoFemmina.posY = giacollocato.giuntoMaschio.posY;
		this.setInclinazione( (giacollocato.giuntoMaschio.inclinGiunto) % Binario.angolo_giro );
		this.libero = false;
	}


	public void addNonBuoni(Binario candidato) {
		this.listaNonBuoni.put(""+candidato.getClass(),candidato);
		
	}
}
